package com.vela.binlistkotlin.usecase.service

import com.vela.binlistkotlin.usecase.data.models.CardCount
import com.vela.binlistkotlin.usecase.data.response.BinListApiResponse
import com.vela.binlistkotlin.usecase.data.response.CardStatisticsResponse
import com.vela.binlistkotlin.usecase.data.models.CardVerificationPayload
import com.vela.binlistkotlin.usecase.data.response.CardVerificationResponse
import com.vela.binlistkotlin.usecase.exception.InvalidInputException
import com.vela.binlistkotlin.domain.entities.CardDetail
import com.vela.binlistkotlin.domain.entities.CardVerificationRecord
import com.vela.binlistkotlin.domain.service.CardDetailRepository
import com.vela.binlistkotlin.domain.service.CardVerificationRecordRepository
import com.vela.binlistkotlin.domain.enums.CardType
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.annotation.CacheEvict
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpStatusCodeException
import org.springframework.web.client.RestTemplate
import java.util.concurrent.ConcurrentHashMap


@Service
class CardSchemeServiceImpl : CardSchemeService {
    @Value("\${binlist.url}")
    var binlistURL: String? = null
    private val log = LoggerFactory.getLogger(CardSchemeServiceImpl::class.java)
    @Autowired
    lateinit var cardVerificationRecordRepository: CardVerificationRecordRepository
    @Autowired
    lateinit var restTemplate: RestTemplate
    @Autowired
    lateinit var cardDetailRepository: CardDetailRepository

    /**
     * This function logs a new verification request to the database
     * @param cardNumber:String
     */
    private fun logCardVerificationRecord(cardNumber: String) {
        log.info("Logged a new verification request to the database")
        cardVerificationRecordRepository.save(CardVerificationRecord(cardNumber))
    }

    /**
     * This function validates the length of the card
     * @param cardNumber:String
     * @return the card number (truncated to 6 digits) if the input falls within the range of 6 to 19 characters
     * @throws InvalidInputException if the input is invalid
     */
    @Throws(InvalidInputException::class)
    private fun validateCardNumberLength(cardNumber: String): String {

        if (cardNumber.length > 19 || cardNumber.length < 6) {
            throw InvalidInputException("Invalid Card Number Input")
        }
        log.info("Card Validated")
        return if (cardNumber.length > 6) {
            cardNumber.substring(0, 6)
        } else cardNumber
    }

    /**
     * This function maps the BinList Api Response to the response structure of the client side
     * @param binListApiResponse: an object instantiated with the BinListApiResponse class
     * @return the mapped response
     */
    private fun mapToCardVerificationResponse(binListApiResponse: BinListApiResponse?): CardVerificationResponse {
        val cardVerificationResponse = CardVerificationResponse()
        val cardVerificationPayload = CardVerificationPayload()
        if (binListApiResponse != null) {
            cardVerificationResponse.payload = cardVerificationPayload
            cardVerificationResponse.payload!!.bank = if (binListApiResponse.bank!!.name == null) "" else binListApiResponse.bank!!.name
            cardVerificationResponse.payload!!.scheme = if (binListApiResponse.scheme == null) "" else binListApiResponse.scheme
            cardVerificationResponse.payload!!.type = if (binListApiResponse.type == null) "" else binListApiResponse.type
            cardVerificationResponse.success = true
        }
        return cardVerificationResponse
    }

    /**
     * This function maps the response from the database to the response structure of the client side
     * @param cardDetail: an object instantiated with the CardDetail class
     * @return the mapped response
     */
    private fun mapToCardVerificationResponse(cardDetail: CardDetail?): CardVerificationResponse {
        val cardVerificationResponse = CardVerificationResponse()
        val cardVerificationPayload = CardVerificationPayload()
        if (cardDetail != null) {
            cardVerificationResponse.payload = cardVerificationPayload
            cardVerificationResponse.payload!!.bank = if (cardDetail.bank == null) "" else cardDetail.bank
            cardVerificationResponse.payload!!.scheme = if (cardDetail.scheme == null) "" else cardDetail.scheme
            cardVerificationResponse.payload!!.type = if (cardDetail.type == CardType.DEBIT) "debit" else "credit"
            cardVerificationResponse.success = true
        }
        log.info(cardVerificationResponse.toString())
        return cardVerificationResponse
    }

    /**
     * This function maps the response from the BinList API to the structure of the database
     * @param cardNumber:String
     * @param binListApiResponse: an object instantiated with the BinListApiResponse class
     * @return the new generated object
     */
    private fun mapToCardDetail(cardNumber: String, binListApiResponse: BinListApiResponse): CardDetail {
        val cardDetail = CardDetail()
        cardDetail.cardNumber = cardNumber

        cardDetail.type = if (binListApiResponse.type.equals("debit")) CardType.DEBIT else CardType.CREDIT
        cardDetail.bank = if (binListApiResponse.bank == null) "" else binListApiResponse.bank!!.name
        cardDetail.scheme = if (binListApiResponse.scheme == null) "" else binListApiResponse.scheme
        return cardDetail
    }

    /**
     * This function saves card request details to the database
     * @param cardNumber:String
     * @param binListApiResponse: the response received from the Bin List API
     */
    @Async
    @CacheEvict(value = ["cache"], allEntries = true)
    fun saveRequestReturnObject(cardNumber: String, binListApiResponse: BinListApiResponse) {
        val cardDetail: CardDetail = mapToCardDetail(cardNumber, binListApiResponse)
        cardDetailRepository.save(cardDetail)
    }

    /**
     * This function performs the actual verification of the card by making calls to the external API and returning appropriate responses
     * @param cardNumber:String
     * @param httpEntity
     * @return the converted response after receiving response from the external API
     * @throws HttpStatusCodeException
     * @throws InvalidInputException
     */

    @Throws(HttpStatusCodeException::class, InvalidInputException::class)
    override fun performCardVerification(cardNumber: String?, httpEntity: HttpEntity<*>?): CardVerificationResponse? {
        val validCardNumber = validateCardNumberLength(cardNumber!!)
//        log.info(validCardNumber) //Done
            logCardVerificationRecord(validCardNumber!!)

        var savedResponse: CardDetail = cardDetailRepository.findByCardNumber(validCardNumber) ?: run {
            var binListApiResponse: BinListApiResponse? = null
            var response: ResponseEntity<BinListApiResponse?>? = null

            try {
                response = restTemplate.exchange("$binlistURL/{validCard}",
                        HttpMethod.GET, httpEntity, BinListApiResponse::class.java, validCardNumber)
                binListApiResponse = response.body
                log.info(binListApiResponse.toString())
            } catch (ex: HttpStatusCodeException) {
                log.error("Error performing request to BinList API")
                throw ex
            }

                    saveRequestReturnObject(validCardNumber, binListApiResponse!!)

            return mapToCardVerificationResponse(binListApiResponse)
        }
        log.info("Response had been previously saved")
        log.info(savedResponse.toString())
        return mapToCardVerificationResponse(savedResponse)
    }

    @Throws(RuntimeException::class)
    override fun getCardVerificationRecords(start: Int, limit: Int): CardStatisticsResponse? {
        val cardStatisticsResponse = CardStatisticsResponse()
        var result: List<CardCount> = cardVerificationRecordRepository
                .getCardVerificationRecordByCardNumber().sortedByDescending { it.count }
        log.info(result.toString())
//        if (result == null) {
//            log.error("Invalid Page Exception")
//            throw InvalidPageException()
//        }
//        if (page.size < 1L) {
//            log.error("General Runtime Exception")
//            throw RuntimeException()
//        }
        cardStatisticsResponse.start = start
        cardStatisticsResponse.limit = limit
        cardStatisticsResponse.size = result.size
        cardStatisticsResponse.success = true

        val displayStart = (start - 1)*limit
        val displayStop = (start * limit) -1
        var payload: MutableMap<String, Long> = ConcurrentHashMap()
        for (i in displayStart..displayStop) {
//            println("${result[i].cardNumber} = ${result[i].count}")
            payload[result[i].cardNumber.toString()] = result[i].count
        }
        payload.toSortedMap()
        cardStatisticsResponse.payload = payload

        return cardStatisticsResponse
    }
}




