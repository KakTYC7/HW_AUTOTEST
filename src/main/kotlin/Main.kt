
const val ERROR_TYPE = -1
const val ERROR_LIMIT = -2
fun main() {

}

fun comission(cardType: String = "Mir", previousTransfers: Int = 0, amount: Int = 0): Int {
    val monthlyLimitMastercardMaestro = 75000
    val monthlyLimitPerCard = 600_000
    val dailyLimitPerCard = 150_000
    val monthlyLimitVKPay = 400_000
    val transferLimitVKPay = 15_000

    return when (cardType) {
        "Visa", "Mir" -> {
            if (amount > monthlyLimitPerCard ||
                previousTransfers + amount > monthlyLimitPerCard ||
                amount > dailyLimitPerCard ||
                previousTransfers + amount > dailyLimitPerCard
            ) {
                ERROR_LIMIT
            } else {
                (maxOf((0.0075 * amount).toInt(), 35)).toInt()
            }
        }

        "Mastercard", "Maestro" -> {
            if (previousTransfers + amount > monthlyLimitPerCard ||
                amount > dailyLimitPerCard ||
                previousTransfers + amount > dailyLimitPerCard
            ) {
                ERROR_LIMIT
            } else if (amount in 300..monthlyLimitMastercardMaestro) {
               return 0
            } else {
                (0.006 * amount + 20).toInt()
            }
        }

        "VKPay" -> {
            if (previousTransfers + amount > monthlyLimitVKPay ||
                transferLimitVKPay < amount
            ) {
                ERROR_LIMIT
            } else {
                0
            }
        }

        else -> ERROR_TYPE
    }
}

