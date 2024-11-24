import io.github.oshai.kotlinlogging.KotlinLogging
import utils.readIntNotNull
import utils.readNextInt
import java.lang.System.exit

private val logger = KotlinLogging.logger {}

fun main() {
    runMenu()
}

fun mainMenu(): Int {
    print("""
          ----------------------------------
          |        NOTE KEEPER APP         |
          ----------------------------------
          | NOTE MENU                      |
          |   1) Add a program             |
          |   2) List all programs         |
          |   3) Update a program          |
          |   4) Delete a program          |
          ----------------------------------
          |   0) Exit                      |
          ----------------------------------
          ==>> """.trimMargin(">"))
    return readNextInt(" > ==>>")
}

fun runMenu() {
    do {
        val option = mainMenu()
        when (option) {
            1  -> addProgram()
            2  -> listPrograms()
            3  -> updateProgram()
            4  -> deleteProgram()
            0  -> exitApp()
            else -> println("Invalid option entered: ${option}")
        }
    } while (true)
}

fun addProgram(){
    println("You chose Add Note")
    logger.info { "addProgram() function invoked" }
}

fun listPrograms(){
    println("You chose List Notes")
    logger.info { "listProgrammes() function invoked" }
}

fun updateProgram(){
    println("You chose Update Note")
    logger.info { "updateProgram() function invoked" }
}

fun deleteProgram(){
    println("You chose Delete Note")
    logger.info { "deleteProgram() function invoked" }
}

fun exitApp(){
    println("Exiting...bye")
    exit(0)
}