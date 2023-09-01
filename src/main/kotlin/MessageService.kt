class MessageService {

    var validationService = ValidationService()

    var quoteIdentity = QuoteIdentity()

    fun requestDataUser(): QuoteIdentity{
        println("")
        println("-------WELCOME TO THE TRAVEL AGENCY XYZ-----")

        println("¡You are doing a quote!")

        print("Please enter your name:")
        quoteIdentity.NameUser = readln()

        print("Where would you like to travel? :")
        quoteIdentity.Destination = readln()

        print("How many people will travel? :")
        var amountOfPeople:String = readln()

        print("How many nights will you are? :")
        var amountOfNight: String = readln()

        print("What type of plan would you like? (basico - premium) :")
        quoteIdentity.PlanType = readln()

        print("are you a member of usual travel plan? (Y(yes) - N(No)) :")
        var usualTraveler:String = readln()

        if(amountOfPeople != null && !amountOfPeople.trim().isEmpty()){
            quoteIdentity.AmountPeople = amountOfPeople.toInt()
        }

        if(amountOfNight != null && !amountOfNight.trim().isEmpty()){
            quoteIdentity.AmountNights = amountOfNight.toInt()
        }

        if(usualTraveler != null && !usualTraveler.trim().isEmpty()){
            if(usualTraveler.trim().lowercase() == "y"){
                quoteIdentity.IsUsualTraveler = true
            }else{
                quoteIdentity.IsUsualTraveler = false
            }
        }


        return quoteIdentity

    }

    fun showMessageLine(message:String){
        println("")
        print(message)
    }

    fun errorMessage(message:String){
        println("ERROR: ${message} ")
    }

    fun showSummaryOfQuote(dataQuoteUser: QuoteIdentity, quoteValues: QuoteValuesIdentity){
        println("")
        println("**************************************************")
        println("------------------QUOTE-SUMMARY-------------------")
        println("Client:${dataQuoteUser.NameUser}")
        println("Gross value of package: ${quoteValues.grossValueOfPackage}");
        println("*DISCOUNTS:");

        if(!validationService.userHaveAnyDiscount(quoteValues)){
            println("   -You don't apply for any discount :(")
        }else{
            showDiscounts(quoteValues)
        }
        println("_________________________________________________")
        println("TOTAL: $${quoteValues.totalValueOfQuote}")

    }

    fun showDiscounts(quoteValues:QuoteValuesIdentity){
        println("   -usual traveler = ${quoteValues.discountByUsualTraveler} OFF")
        println("   -plan = ${quoteValues.discountByPlan} OFF")
        println("   -package = ${quoteValues.discountByPackage} OFF")
        println("   -Total discounts = ${quoteValues.totalDiscount} OFF")
    }
}