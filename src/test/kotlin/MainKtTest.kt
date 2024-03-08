import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun comissionMirMinimum() {
        val cardType = "Мир"
        val previousTransfers = 0
        val amount = 2000

        val result = comission(cardType, previousTransfers, amount)

        assertEquals(35, result)
    }

    @Test
    fun comissionMirNormal() {
        val cardType = "Мир"
        val previousTransfers = 0
        val amount = 20_000

        val result = comission(cardType, previousTransfers, amount)

        assertEquals(150, result)
    }
}