class CalculateService {
    fun calculateCostTikets(amountPeople:Int, tiketsPrice:Double):Double{
        return tiketsPrice * amountPeople
    }

    fun calculateCostPlan(amountPeople:Int, planPrice:Double, amountNights:Int):Double{
        return planPrice * amountNights * amountPeople
    }

    fun calculateCostPackage(ticketsPrice:Double, planPrice: Double):Double{
        return ticketsPrice + planPrice
    }

    fun calculateTicketsDiscount(percentageDiscount: Double, valueTickets:Double):Double{
        return valueTickets * percentageDiscount
    }

    fun calculatePlanDiscount(percentDiscount:Double, valuePlan:Double):Double{
        return valuePlan * percentDiscount
    }

    fun calculatePackageDiscount(percentageDiscount: Double, valuePackage:Double):Double{
        return valuePackage * percentageDiscount
    }

    fun calculateTotalDiscount(discountByUsualTraveler:Double, discountByPlan:Double, discountByPackage: Double):Double{
        return discountByUsualTraveler + discountByPlan + discountByPackage
    }

    fun calculateTotalValueQuote(priceOfPackage:Double,priceOfAllDiscounts:Double):Double{
        return priceOfPackage - priceOfAllDiscounts
    }
}