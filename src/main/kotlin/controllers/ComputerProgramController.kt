package controllers

import computerController
import models.Computer
import programController

import models.ComputerProgram
import models.Program
import utils.isValidListIndex

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
        val program = programController.getProgramId(programId)
        val computer = computerController.getComputerId(computerId)

        if(computer != null && program != null){
            computerPrograms.add(ComputerProgram(programId, computerId))
        }else{
            throw IllegalArgumentException("Invalid computer or program ID")
        }
    }

    fun listAllComputerPrograms(): String =
    if  (computerPrograms.isEmpty()) "No computer programs stored"
    else formatListString(computerPrograms)

    //Program show computer
    fun listProgramsAssignedComputers(programId: Int): List<Computer> {
        val computersWithPrograms = computerPrograms.filter { it.programId == programId}.map{it.computerId}
        return computerController.listOfComputers().filter {it.computerId in computersWithPrograms}
    }

    //Computer show program
    fun listComputersWithPrograms(computerId: Int): List<Program> {
        val programsWithComputers = computerPrograms.filter { it.computerId == computerId}.map{it.programId}
        return programController.listOfPrograms().filter{it.programId in programsWithComputers}
    }

    fun deleteComputerProgram(indexToDelete: Int): ComputerProgram? {
        return if (isValidListIndex(indexToDelete, computerPrograms)) {
            computerPrograms.removeAt(indexToDelete)
        } else null
    }

    fun numberOfComputerPrograms() = computerPrograms.size

    fun updateComputerProgram(indexToUpdate: Int, computerProgram: ComputerProgram?): Boolean{
        val foundComputerProgram = findComputerProgram(indexToUpdate)
        //val computer = computerController.getComputerId(computerId)
        //val program = programController.getProgramId(programId)
        //&& (computer != null && program != null)
        if ((foundComputerProgram != null) && (computerProgram != null)) {
            foundComputerProgram.programId = computerProgram.programId
            foundComputerProgram.computerId = computerProgram.computerId
            return true
        }
        return false
    }
    fun isValidIndex(index: Int) :Boolean{
        return isValidListIndex(index, computerPrograms);
    }

    fun findComputerProgram(index: Int): ComputerProgram? {
        return if (isValidListIndex(index, computerPrograms)) {
            computerPrograms[index]
        } else null
    }

    private fun formatListString(programsToFormat : List<ComputerProgram>) : String =
        programsToFormat
            .joinToString (separator = "\n") { computerProgram ->
                computerPrograms.indexOf(computerProgram).toString() + ": " + computerProgram.toString() }

}



