class TravelAgency {
    var calculateService = CalculateService()
    var messageService = MessageService()
    var queryService = QueryService()
    var validationService = ValidationService()
    var quoteUser = QuoteIdentity()
    var quoteResult = QuoteValuesIdentity()

    fun startTravelAgencyProgram():Boolean{

        try{
            //get data of user
            quoteUser =  messageService.requestDataUser()

            //validate that all the fields have a value different to null or empty
            var isAnyDataMissing:Boolean = validationService.isAnyDataQuoteMissing(quoteUser)

            if(isAnyDataMissing){
                messageService.errorMessage("Some of the data was not send")
                return true
            }

            //Searches in data if exist one destination with the specific values that user gave
            var resultSearchDestination:Map<String,Int>? = queryService.querySearchDataByDestination(quoteUser.Destination)

            if(resultSearchDestination == null){
                messageService.errorMessage("Not found anything value with the destination: ${quoteUser.Destination}")
                return true
            }

            //Get the price of tickets
            var amountOfPeople:Int = quoteUser.AmountPeople ?: 0
            var priceOfTickets:Double = resultSearchDestination?.get("tiquete")?.toDouble() ?: 0.0
            var priceTikets:Double = calculateService.calculateCostTikets(amountOfPeople,priceOfTickets)

            //Get the price of plan
            var planPrice:Double = resultSearchDestination?.get(quoteUser.PlanType)?.toDouble() ?: 0.0
            var amountNights:Int = quoteUser.AmountNights ?:0
            var pricePlan:Double = calculateService.calculateCostPlan(amountOfPeople,planPrice,amountNights)

            //Get the price of the package
            var priceOfPackage: Double = calculateService.calculateCostPackage(priceTikets,pricePlan)
            quoteResult.grossValueOfPackage = priceOfPackage

            //Get discount by usual traveler (only if apply) and gets the discount tickets value
            var discountTickets:Double = 0.0
            if(quoteUser.IsUsualTraveler == true){
                var perDiscountByUsualTraveler:Double = queryService.querySearchPromotionByUsualTraveler("usualTraveler") ?: 0.0;

                //the tickets discount is calculated
                discountTickets = calculateService.calculateTicketsDiscount(perDiscountByUsualTraveler,priceTikets)
                quoteResult.discountByUsualTraveler = discountTickets
            }

            //Get discount by Plan (only if apply and also if the number of nights is valid) and gets the discount by plan value
            var planType:String = quoteUser.PlanType ?: ""
            var dataPromotionByPlan: Map<String,Double>? = queryService.querySearchPromotionByPlan(planType);
            var amountNightsAlmost:Double = dataPromotionByPlan?.get("numNightsAlmost") ?: 0.0
            var amountNightsTypeDouble = amountNights.toDouble()

            var discountByPlan:Double = 0.0
            if(dataPromotionByPlan != null){
                if(amountNightsTypeDouble >= amountNightsAlmost){
                    var percentDiscount:Double = dataPromotionByPlan.get("discountPercentage") ?: 0.0

                    //the plan discount is calculated
                    discountByPlan = calculateService.calculatePlanDiscount(percentDiscount,pricePlan)
                    quoteResult.discountByPlan = discountByPlan
                }
            }

            //get discount by package (only if apply and also if the number of persons that will travel apply)
            val namePromotion:String = "byAmountOfPersons"
            var dataPromotionByPackage:Map<String,Double>? = queryService.querySearchPromotionByPackage(namePromotion)
            var discountByPackage:Double = 0.0

            if(dataPromotionByPackage != null){
                var minimumNights:Int = dataPromotionByPackage?.get("numNightsAtLeast")?.toInt() ?: 0
                var perDiscountByPackage:Double = dataPromotionByPackage.get("discountPercentage") ?: 0.0

                if(amountOfPeople >= minimumNights){
                    discountByPackage = calculateService.calculatePackageDiscount(perDiscountByPackage,priceOfPackage)
                    quoteResult.discountByPackage = discountByPackage
                }
            }

            //Calculate the total discount value
            var totalDiscountValue:Double = calculateService.calculateTotalDiscount(discountTickets,discountByPlan, discountByPackage)
            quoteResult.totalDiscount = totalDiscountValue

            //calculate the total value of quote
            var totalValueOfQuote:Double = calculateService.calculateTotalValueQuote(priceOfPackage,totalDiscountValue)
            quoteResult.totalValueOfQuote = totalValueOfQuote

            messageService.showSummaryOfQuote(quoteUser, quoteResult)

            messageService.showMessageLine("Would you like make other quote? (Y(Yes) - N(No)): ")
            var responseUser:String = readln()
            var toMakeOtherQuote:Boolean = validationService.validateOtherQuoteFromString(responseUser)

            if(toMakeOtherQuote){
                return true
            }

            println("Exit Successful!")

        }catch(ex:Exception){
            messageService.errorMessage("An unexpected error occurred. Please contact the help desk")
        }

        return false

    }

}