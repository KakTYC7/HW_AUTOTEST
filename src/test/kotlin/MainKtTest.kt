import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun comissionVisaMirMinimum() {
        val cardType = "Mir"
        val previousTransfers = 0
        val amount = 2000

        val result = comission(cardType, previousTransfers, amount)

        assertEquals(35, result)
    }

    @Test
    fun comissionVisaMirNormal() {
        val cardType = "Mir"
        val previousTransfers = 0
        val amount = 18_000

        val result = comission(cardType, previousTransfers, amount)

        assertEquals(135, result)
    }

    @Test
    fun comissionVisaMirMonthlyLimit() {
        val cardType = "Visa"
        val previousTransfers = 300_000
        val amount = 305_000

        val result = comission(cardType, previousTransfers, amount)

        assertEquals(ERROR_LIMIT, result)
    }

    @Test
    fun comissionVisaMirMonthlyLimitAtOnce() {
        val cardType = "Visa"
        val previousTransfers = 0
        val amount = 605_000

        val result = comission(cardType, previousTransfers, amount)

        assertEquals(ERROR_LIMIT, result)
    }

    @Test
    fun comissionVisaMirDailyLimitAtOnce() {
        val cardType = "Visa"
        val previousTransfers = 0
        val amount = 150_001

        val result = comission(cardType, previousTransfers, amount)

        assertEquals(ERROR_LIMIT, result)
    }

    @Test
    fun comissionVisaMirDailyLimit() {
        val cardType = "Visa"
        val previousTransfers = 1
        val amount = 150_000

        val result = comission(cardType, previousTransfers, amount)

        assertEquals(ERROR_LIMIT, result)
    }

    @Test
    fun comissionMasterMaestroNormal() {
        val cardType = "Mastercard"
        val previousTransfers = 0
        val amount = 400

        val result = comission(cardType, previousTransfers, amount)

        assertEquals(0, result)
    }

    @Test
    fun comissionMasterMaestroNormalComission1() {
        val cardType = "Mastercard"
        val previousTransfers = 0
        val amount = 76_000

        val result = comission(cardType, previousTransfers, amount)

        assertEquals(476, result)
    }

    @Test
    fun comissionMasterMaestroNormalComission2() {
        val cardType = "Maestro"
        val previousTransfers = 0
        val amount = 200

        val result = comission(cardType, previousTransfers, amount)

        assertEquals(21, result)
    }

    @Test
    fun comissionMasterMaestroDailyComission1() {
        val cardType = "Mastercard"
        val previousTransfers = 0
        val amount = 151_000

        val result = comission(cardType, previousTransfers, amount)

        assertEquals(ERROR_LIMIT, result)
    }

    @Test
    fun comissionMasterMaestroDailyComission2() {
        val cardType = "Mastercard"
        val previousTransfers = 1_000
        val amount = 150_000

        val result = comission(cardType, previousTransfers, amount)

        assertEquals(ERROR_LIMIT, result)
    }

    @Test
    fun comissionMasterMaestroMonthlyComission() {
        val cardType = "Mastercard"
        val previousTransfers = 1_000
        val amount = 600_000

        val result = comission(cardType, previousTransfers, amount)

        assertEquals(ERROR_LIMIT, result)
    }

    @Test
    fun comissionDefault() {

        val result = comission()

        assertEquals(35, result)
    }

    @Test
    fun comissionVKpayNonComission() {
        val cardType = "VKPay"
        val previousTransfers = 5_00
        val amount = 14_000

        val result = comission(cardType, previousTransfers, amount)

        assertEquals(0, result)
    }

    @Test
    fun comissionVKpayOneTransferLimit() {
        val cardType = "VKPay"
        val previousTransfers = 0
        val amount = 16_000

        val result = comission(cardType, previousTransfers, amount)

        assertEquals(ERROR_LIMIT, result)
    }

    @Test
    fun comissionVKpayMonthlyTransfers() {
        val cardType = "VKPay"
        val previousTransfers = 382_000
        val amount = 14_000

        val result = comission(cardType, previousTransfers, amount)

        assertEquals(0, result)
    }

    @Test
    fun comissionVKpayMonthlyTransfersLimit() {
        val cardType = "VKPay"
        val previousTransfers = 385_000
        val amount = 16_000

        val result = comission(cardType, previousTransfers, amount)

        assertEquals(ERROR_LIMIT, result)
    }

    @Test
    fun comissionCardTypeError() {
        val cardType = "Accord"
        val previousTransfers = 0
        val amount = 16_000

        val result = comission(cardType, previousTransfers, amount)

        assertEquals(ERROR_TYPE, result)
    }
}