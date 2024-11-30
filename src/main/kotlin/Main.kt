import controllers.ComputerController
import controllers.ComputerProgramController
import controllers.ProgramController
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
          -----------------------------------
          |      COMPUTER PROGRAM APP       |
          -----------------------------------
          | PROGRAM MENU                    |
          |   1) Add a program              |
          |   2) List all programs          |
          |   3) Update a program           |
          |   4) Delete a program           |
          -----------------------------------
          | COMPUTER MENU                   |
          |   5) Add a computer             |
          |   6) List all computer          |
          |   7) Update a computer          |
          |   8) Delete a computer          |
          -----------------------------------
          | COMPUTER PROGRAM MENU           |
          |   9) Add a computer program     |
          |   10) List all computer program |
          |   11) Update a computer program |
          |   12) Delete a computer program |
          -----------------------------------
          |   0) Exit                       |
          -----------------------------------
          ==>> """.trimMargin(">"))
    return readNextInt(" > ==>>")
}

fun runMenu() {
    val computerController = ComputerController()
    val programController = ProgramController()
    val computerProgramController = ComputerProgramController()

    do {
        val option = mainMenu()
        when (option) {
            1  -> addProgram()
            2  -> listPrograms()
            3  -> updateProgram()
            4  -> deleteProgram()
            5  -> addComputer()
            6  -> listComputers()
            7  -> updateComputer()
            8  -> deleteComputer()
            9  -> addComputerProgram()
            10  -> listComputerPrograms()
            11 -> updateComputerProgram()
            12 -> deleteComputerProgram()
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

fun addComputer() {
    logger.info { "addComputer function invoked" }
}
fun listComputers() {
    logger.info { "listComputers function invoked" }
}

fun updateComputer() {
    logger.info { "updateComputer function invoked" }
}

fun deleteComputer() {
    logger.info { "deleteComputer function invoked" }
}

fun addComputerProgram() {
    logger.info { "addComputerProgram function invoked" }
}

fun listComputerPrograms() {
    logger.info { "listComputerPrograms function invoked" }
}

fun updateComputerProgram() {
    logger.info { "updateComputerProgram function invoked" }
}

fun deleteComputerProgram() {
    logger.info { "deleteComputerProgram function invoked" }
}














fun exitApp(){
    println("Exiting...bye")
    exit(0)
}