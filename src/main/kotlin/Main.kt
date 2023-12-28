fun main(args: Array<String>) {
    print("\u001B[2J") // Set cursor to row 5, column 10
    print("\u001B[0;0H") // Set cursor to row 5, column 10
    println("####")
    println("####")
    println("####")
    println("####")

    print("\u001B[3;3H") // Set cursor to row 5, column 10
    println("0")
    print("\u001B[4;4H")


    val app = App(args[0], args[1])
    app.run()

}
