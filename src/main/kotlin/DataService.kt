class DataService {
    val dataServerDb: Map<String, Map<String, Int>> = getDataDb()
    val dataPromotionsByUsualTraveler: Map<String,Double> = getPromotionsByUsualTraveler()
    val dataPromotionsByPlan:Map<String, Map<String, Double>> = getPromotionsByPlan()
    val dataPromotionsByPackage:Map<String,Map<String,Double>> = getPromotionsByPackage()


    private fun getPromotionsByPackage():Map<String,Map<String,Double>>{
        val promotions = mapOf(
            "byAmountOfPersons" to mapOf(
                "numNightsAtLeast" to 4.0,
                "discountPercentage" to 0.08
            )
        )

        return promotions
    }


    private fun getPromotionsByPlan(): Map<String, Map<String, Double>> {
        val promotions = mapOf(
            "basico" to mapOf(
                "numNightsAlmost" to 6.0,
                "discountPercentage" to 0.05
            ),
            "premium" to mapOf(
                "numNightsAlmost" to 6.0,
                "discountPercentage" to 0.09
            )
        )
        return promotions
    }



    private fun getPromotionsByUsualTraveler(): Map<String,Double> {
        val promotions:Map<String,Double> = mapOf(
            "usualTraveler" to 0.10
        )
        return promotions
    }



    private fun getDataDb(): Map<String, Map<String, Int>> {
        val destinos = mapOf(
            "panama" to mapOf(
                "tiquete" to 715,
                "basico" to 119,
                "premium" to 138
            ),
            "saint maarten" to mapOf(
                "tiquete" to 834,
                "basico" to 122,
                "premium" to 151
            ),
            "aruba" to mapOf(
                "tiquete" to 618,
                "basico" to 134,
                "premium" to 167
            ),
            "jamaica" to mapOf(
                "tiquete" to 1005,
                "basico" to 104,
                "premium" to 112
            ),
            "cancun" to mapOf(
                "tiquete" to 950,
                "basico" to 105,
                "premium" to 142
            )
        )

        return destinos
    }
}