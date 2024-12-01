import controllers.ComputerController
import controllers.ComputerProgramController
import controllers.ProgramController
import io.github.oshai.kotlinlogging.KotlinLogging
import utils.readNextInt
import java.lang.System.exit
import models.Program
import models.Computer
import models.ComputerProgram
import utils.readNextFloat
import utils.readNextLine

private val logger = KotlinLogging.logger {}

val computerController = ComputerController()
val programController = ProgramController()
val computerProgramController = ComputerProgramController()

fun main() {
    runMenu()
}

fun mainMenu(): Int {
    print("""
          > -----------------------------------
          > |      COMPUTER PROGRAM APP       |
          > -----------------------------------
          > | PROGRAM MENU                    |
          > |   1) Add a program              |
          > |   2) List all programs          |
          > |   3) Update a program           |
          > |   4) Delete a program           |
          > -----------------------------------
          > | COMPUTER MENU                   |
          > |   5) Add a computer             |
          > |   6) List all computer          |
          > |   7) Update a computer          |
          > |   8) Delete a computer          |
          > -----------------------------------
          > | COMPUTER PROGRAM MENU           |
          > |   9) Add a computer program     |
          > |   10) List all computer program |
          > |   11) Update a computer program |
          > |   12) Delete a computer program |
          > -----------------------------------
          > |   0) Exit                       |
          > -----------------------------------
          >""".trimMargin(">"))
    return readNextInt("==>> ")
}

fun runMenu() {


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
    val programName = readNextLine("Name: ")
    val programSize = readNextFloat("Size: ")
    val programVersion = readNextLine("Version: ")
    val program = Program(0, programName, programSize, programVersion)
    programController.addProgram(program)
}

fun listPrograms(){
    println("Programs: \n${programController.listPrograms()}")
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
    val computerName = readNextLine("Group Name: ")
    val operatingSystem = readNextLine("Operating System: ")
    val storage = readNextFloat("Storage: ")
    val computer = Computer(0, computerName, operatingSystem, storage)
    computerController.addComputer(computer)
}
fun listComputers() {
    println(" Computers: \n${computerController.listComputers()}")
}

fun updateComputer() {
    logger.info { "updateComputer function invoked" }
}

fun deleteComputer() {
    logger.info { "deleteComputer function invoked" }
}

fun addComputerProgram() {
    val programId = readNextInt("Enter program id: ")
    val computerId = readNextInt("Enter computer id: ")
    computerProgramController.addProgramToComputer(programId, computerId)
}

fun listComputerPrograms() {
    val computerId = readNextInt("Enter computer id: ")
    println(computerProgramController.listProgramsInComputer(computerId))
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