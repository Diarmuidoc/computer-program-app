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
          > |      COMPUTER PROGRAM APP          |
          > -----------------------------------
          > | PROGRAM MENU                       |
          > |   1) Add a program                 |
          > |   2) List all programs             |
          > |   3) Update a program              |
          > |   4) Delete a program              |
          > -----------------------------------
          > | COMPUTER MENU                      |
          > |   5) Add a computer                |
          > |   6) List all computer             |
          > |   7) Update a computer             |
          > |   8) Delete a computer             |
          > -----------------------------------
          > | COMPUTER PROGRAM MENU              |
          > |   9) Add a computer program        |
          > |   10) List programs with computers |
          > |   11) List computers with programs |
          > |   12) Update a computer program    |
          > |   13) Delete a computer program    |
          > -----------------------------------
          > |   0) Exit                          |
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
            10  -> listProgramComputers()
            11 -> listComputerPrograms()
            12 -> updateComputerProgram()
            13 -> deleteComputerProgram()
            14 -> listAllComputerPrograms()
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
    //logger.info { "updateProgram() function invoked" }
    listPrograms()
    if (programController.numberOfPrograms() > 0) {
        //only ask the user to choose the note if a program exist
        val indexToUpdate = readNextInt("Enter the index of the program to update: ")
        if (programController.isValidIndex(indexToUpdate)) {
            val programName = readNextLine("Enter a name for the program: ")
            val programSize = readNextFloat("Enter the size for the program: ")
            val programVersion = readNextLine("Enter the version of the Program: ")
            val programId = indexToUpdate

            //pass the index of the program and the new program details to the program controller for updating and check for success.
            if (programController.updateProgram(indexToUpdate, Program(programId, programName, programSize, programVersion))){
                println("Update Successful")
            } else {
                println("Update Failed")
            }
        } else {
            println("There are no notes for this index number")
        }
    }
}

fun deleteProgram(){
    //logger.info { "deleteProgram() function invoked" }
    listPrograms()
    if (programController.numberOfPrograms() > 0) {
        //only ask the user to choose the program to delete if notes exist
        val indexToDelete = readNextInt("Enter the index of the program to delete: ")
        //pass the index of the note to programController for deleting and check for success.
        val programToDelete = programController.deleteProgram(indexToDelete)
        if (programToDelete != null) {
            println("Delete Successful! Deleted note: ${programToDelete.programName}")
        } else {
            println("Delete NOT Successful")
        }
    }
}

fun addComputer() {
    val computerName = readNextLine("Computer Name: ")
    val operatingSystem = readNextLine("Operating System: ")
    val storage = readNextFloat("Storage: ")
    val computer = Computer(0, computerName, operatingSystem, storage)
    computerController.addComputer(computer)
}
fun listComputers() {
    println(" Computers: \n${computerController.listComputers()}")
}

fun updateComputer() {
    listComputers()
    if (computerController.numberOfComputers() > 0) {

        val indexToUpdate = readNextInt("Enter the index of the computer to update: ")
        if (computerController.isValidIndex(indexToUpdate)) {
            val computerName = readNextLine("Enter a name for the computer: ")
            val operatingSystem = readNextLine("Enter the operating system for the computer: ")
            val storage = readNextFloat("Enter the amount of storage: ")


            if (computerController.updateComputer(indexToUpdate, Computer(indexToUpdate, computerName, operatingSystem, storage))){
                println("Update Successful")
            } else {
                println("Update Failed")
            }
        } else {
            println("There are no notes for this index number")
        }
    }
}

fun deleteComputer() {
    listComputers()
    if (computerController.numberOfComputers() > 0) {

        val indexToDelete = readNextInt("Enter the index of the computer to delete: ")

        val computerToDelete = computerController.deleteComputer(indexToDelete)
        if (computerToDelete != null) {
            println("Delete Successful! Deleted computer: ${computerToDelete.computerName}")
        } else {
            println("Delete NOT Successful")
        }
    }
}

fun addComputerProgram() {
    val programId = readNextInt("Enter program id: ")
    val computerId = readNextInt("Enter computer id: ")
    computerProgramController.addProgramToComputer(programId, computerId)
}

fun listProgramComputers() {
    listPrograms()
    val programId = readNextInt("Enter program id: ")
    println(computerProgramController.listProgramsAssignedComputers(programId))
}

fun listComputerPrograms() {
    listComputers()
    val computerId = readNextInt("Enter computer id: ")
    println(computerProgramController.listComputersWithPrograms(computerId))
}

fun listAllComputerPrograms(){
    println(" ComputerPrograms: \n${computerProgramController.listAllComputerPrograms()}")
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





