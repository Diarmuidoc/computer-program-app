package controllers

import computerController
import models.Computer
import programController

import models.ComputerProgram
import models.Program

//From contacts app skeleton

//class ComputerProgramController {
//    private val computerPrograms = mutableListOf<ComputerProgram>()
//
//    fun addProgramToComputer(programId: Int, computerId: Int) {
//        computerPrograms.add(ComputerProgram(programId, computerId))
//    }
//
//    fun listProgramsInComputer(computerId: Int) = computerPrograms.filter { it.computerId == computerId }
//
////    fun addProgramToComputer(programId: Int, computerId: Int) {
////        if(programController.programs.any {})
////        computerPrograms.add(ComputerProgram(programId, computerId))
////    }
//}



class ComputerProgramController(

){
    private val computerPrograms = mutableListOf<ComputerProgram>()

    fun addProgramToComputer(computerId: Int, programId: Int){
        val computer = computerController.getComputerId(computerId)
        val program = programController.getProgramId(programId)

        if(computer != null && program != null){
            computerPrograms.add(ComputerProgram(computerId, programId))
        }else{
            throw IllegalArgumentException("Invalid computer or program ID")
        }
    }

    fun listAllComputerPrograms(): String =
    if  (computerPrograms.isEmpty()) "No computers stored"
    else formatListString(computerPrograms)

    //Other way?
    fun listProgramsAssignedComputers(programId: Int): List<Computer> {
        val computersWithPrograms = computerPrograms.filter { it.programId == programId}.map{it.computerId}
        return computerController.listOfComputers().filter {it.computerId in computersWithPrograms}
    }

    fun listComputersWithPrograms(computerId: Int): List<Program> {
        val programsWithComputers = computerPrograms.filter { it.computerId == computerId}.map{it.programId}
        return programController.listOfPrograms().filter{it.programId in programsWithComputers}
    }


    private fun formatListString(programsToFormat : List<ComputerProgram>) : String =
        programsToFormat
            .joinToString (separator = "\n") { computerProgram ->
                computerPrograms.indexOf(computerProgram).toString() + ": " + computerProgram.toString() }

}



