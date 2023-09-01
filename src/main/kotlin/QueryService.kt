class QueryService {
    var dataService = DataService()

    fun querySearchDataByDestination(destination:String?):Map<String, Int>?{
        var dataDestinationsTable: Map<String, Int>? = dataService.dataServerDb[destination];

        return  dataDestinationsTable
    }

    fun querySearchPromotionByUsualTraveler(namePromotion:String):Double?{
        var percentagePromotion: Double = dataService.dataPromotionsByUsualTraveler.get(namePromotion) ?: 0.0
        return percentagePromotion
    }

    fun querySearchPromotionByPlan(typePlan:String):Map<String,Double>?{
        var dataPromotionRow:Map<String,Double>? = dataService.dataPromotionsByPlan[typePlan]
        return dataPromotionRow
    }

    fun querySearchPromotionByPackage(namePromotion:String):Map<String,Double>?{
         var dataPromotionPackage:Map<String,Double>? = dataService.dataPromotionsByPackage[namePromotion]
        return dataPromotionPackage
    }
}