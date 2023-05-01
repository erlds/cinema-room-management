var currentIncome = 0
var percentage = 0.0
var numberOfPurchasedTickets = 0
var totalIncome = 0

fun main() {
    // write your code here
    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    val columns = readln().toInt()
    val seats = MutableList(rows) { MutableList(columns) { "S " } }
    totalIncome = calculateTotalIncome(rows,columns)
    var session = true
    var option: Int
    while(session){
        println("1. Show the seats")
        println("2. Buy a ticket")
        println("3. Statistics")
        println("0. Exit")
        option = readln().toInt()
        when(option) {
            1 -> printRoomState(seats)
            2 -> buyTickets(seats)
            3 -> printIncome()
            0 -> session = false
        }
    }
}

fun calculateTotalIncome(rows: Int,columns: Int): Int{
    return if(rows*columns < 60){
        rows*columns*10
    } else {
        val firstHalfRows = rows/2
        val secondHalfRows = rows - firstHalfRows
        val result = firstHalfRows*columns*10 + secondHalfRows*columns*8
        result
    }
}

fun printRoomState(seats: MutableList<MutableList<String>>) {
    println("Cinema:")
    var colIndex = 1
    print(" ")
    val rows = seats.size
    val columns = seats[0].size
    repeat(columns){
        print(" $colIndex")
        colIndex++
    }
    println()
    for(row in 0 until rows){
        print("${row+1} ")
        for(col in 0 until columns) {
            print(seats[row][col])
        }
        println()
    }
}

fun buyTickets(seats: MutableList<MutableList<String>>) {
    var purchasing = true
    var rowSelected = 0
    var colSelected: Int
    while(purchasing) {
        println("Enter a row number:")
        rowSelected = readln().toInt()
        println("Enter a seat number in that row:")
        colSelected = readln().toInt()
        try {
            if (seats[rowSelected-1][colSelected-1] == "S ") {
                seats[rowSelected-1][colSelected-1] = "B "
                purchasing = false
            } else {
                println("That ticket has already been purchased!")
            }
        } catch(e: Exception) {
            println("Wrong input!")
        }
    }
    val rows = seats.size
    val columns = seats[0].size

    val isBackHalf = if(rows % 2 == 0) (rowSelected >= rows/2) else (rowSelected >= rows/2 + 1)

    val ticketPrice = if(rows*columns > 60 && isBackHalf) 8 else 10

    currentIncome += ticketPrice
    numberOfPurchasedTickets++
    percentage = (numberOfPurchasedTickets.toDouble()/(rows*columns).toDouble()) * 100.0
    println("Ticket price: $${ticketPrice}")
}

fun printIncome() {
    println("Number of purchased tickets: $numberOfPurchasedTickets")
    val percentageString = "%.2f".format(percentage)
    println("Percentage: ${percentageString}%")
    println("Current income: $$currentIncome")
    println("Total income: $$totalIncome")
}
