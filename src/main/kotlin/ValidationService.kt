class ValidationService {

    fun isAnyDataQuoteMissing(quoteUser:QuoteIdentity):Boolean{

        if(quoteUser.NameUser == null){
            return true
        }else if(quoteUser.Destination == null){
            return true
        }else if(quoteUser.AmountPeople == null){
            return true
        }else if(quoteUser.AmountNights == null){
            return true
        }else if(quoteUser.PlanType == null){
            return true
        }else if(quoteUser.IsUsualTraveler == null){
            return true
        }

        return false

    }

    fun userHaveAnyDiscount(quoteValues:QuoteValuesIdentity):Boolean{
        if(quoteValues.discountByUsualTraveler != 0.0){
            return true
        }else if(quoteValues.discountByPlan != 0.0){
            return true
        }else if(quoteValues.discountByPackage != 0.0){
            return true
        }

        return false
    }

    fun validateOtherQuoteFromString(responseUser:String):Boolean{
        if(responseUser.trim().lowercase() == "y" ){
            return true
        }

        return false
    }
}