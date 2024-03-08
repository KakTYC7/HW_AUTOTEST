fun main() {

}

fun comission(cardType: String = "Мир", previousTransfers: Int = 0, amount: Int = 0): Int {
    val monthlyLimitMastercardMaestro = 75000
    val monthlyLimitVisaMir = 600000
    val dailyLimitPerCard = 150000
    val monthlyLimitVKPay = 40000
    val transferLimitVKPay = 15000

    return when (cardType) {
        "Visa", "Мир" -> {
            if (amount > monthlyLimitVisaMir ||
                previousTransfers + amount > monthlyLimitVisaMir
            ) {
                return -4
            } else {
                (maxOf((0.0075 * amount).toInt(), 35)).toInt()
            }
        }

        "Mastercard", "Maestro" -> {
            if (amount > dailyLimitPerCard ||
                previousTransfers + amount > monthlyLimitMastercardMaestro
            ) {
                return -3
            } else if (amount in 300..monthlyLimitMastercardMaestro) {
                0
            } else {
                (0.006 * amount + 20).toInt()
            }
        }

        "VKPay" -> {
            if (amount > monthlyLimitVKPay ||
                previousTransfers + amount > monthlyLimitVKPay ||
                transferLimitVKPay < amount ||
                previousTransfers + amount > transferLimitVKPay
            ) {
                return -2
            } else {
                0
            }
        }

        else -> {
            println("Не поддерживаемая платежная система")
            return -1
        }
    }
}

