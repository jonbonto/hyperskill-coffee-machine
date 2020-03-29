package machine

enum class State {
    ACTION, MAKING, FILLING
}

class CoffeeMachine {
    private var water = 400
    private var milk = 540
    private var beans = 120
    private var cups = 9
    private var money = 550
    var state = State.ACTION

    private fun printStatus() {
        println("The coffee machine has:\n" +
                "$water of water\n" +
                "$milk of milk\n" +
                "$beans of coffee beans\n" +
                "$cups of disposable cups\n" +
                "$money of money\n"
        )
    }

    private fun processTake() {
        println("I gave you $money")
        money = 0
    }

    fun processFill(water: Int, milk: Int, beans: Int, cups: Int) {
        this.water += water
        this.milk += milk
        this.beans += beans
        this.cups += cups
    }

    fun makingCoffee(choose: Int) {
        if (cups < 1) {
            println("Sorry, not enough cup!")
            return
        }
        when (choose) {
            1 -> {
                if (water < 250) {
                    println("Sorry, not enough water!")
                    return
                }
                if (beans < 16) {
                    println("Sorry, not enough beans!")
                    return
                }
                water -= 250
                beans -= 16
                money += 4
            }
            2 -> {
                if (water < 350) {
                    println("Sorry, not enough water!")
                    return
                }
                if (milk < 75) {
                    println("Sorry, not enough milk!")
                    return
                }
                if (beans < 20) {
                    println("Sorry, not enough beans!")
                    return
                }
                water -= 350
                milk -= 75
                beans -= 20
                money += 7
            }
            3 -> {
                if (water < 200) {
                    println("Sorry, not enough water!")
                    return
                }
                if (milk < 100) {
                    println("Sorry, not enough milk!")
                    return
                }
                if (beans < 12) {
                    println("Sorry, not enough beans!")
                    return
                }
                water -= 200
                milk -= 100
                beans -= 12
                money += 6
            }
            else -> "Unknown coffe"
        }
        println("I have enough resources, making you a coffee!")
        cups--
    }

    fun processAction(action: String) {
        when (action) {
            "buy" -> state = State.MAKING
            "fill" -> state = State.FILLING
            "take" -> processTake()
            "remaining" -> printStatus()
        }
    }
}



fun main() {
    val coffeeMachine = CoffeeMachine()
    var exit = false
    while (!exit) {
        println("Write action (buy, fill, take, remaining, exit): ")
        val action = readLine()!!.toString()
        if (action != "exit") {
            coffeeMachine.processAction(action)
            if (coffeeMachine.state == State.FILLING) {
                print("Write how many ml of water do you want to add: ")
                val water = readLine()!!.toInt()
                print("Write how many ml of milk do you want to add: ")
                val milk = readLine()!!.toInt()
                print("Write how many grams of coffee beans do you want to add: ")
                val beans = readLine()!!.toInt()
                print("Write how many disposable cups of coffee do you want to add: ")
                val cups = readLine()!!.toInt()
                coffeeMachine.processFill(water, milk, beans, cups)
            } else if (coffeeMachine.state == State.MAKING) {
                print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
                val input = readLine()
                if (input != "back") {
                    coffeeMachine.makingCoffee(input!!.toInt())
                }
            }
            coffeeMachine.state = State.ACTION
        } else {
            exit = true
        }
        println()
    }
}

