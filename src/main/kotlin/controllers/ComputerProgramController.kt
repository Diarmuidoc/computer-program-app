package controllers

import computerController
import models.Computer
import programController

import models.ComputerProgram
import models.Program
import utils.isValidListIndex

/**
 * This class manages a list of computerPrograms, along with providing functionality for adding, deleting, updating and listing
 *
 * @constructor Initializes the ComputerProgramController
 */
class ComputerProgramController(){
    private val computerPrograms = mutableListOf<ComputerProgram>()

    /**
     * Associates a program with a computer by adding it to the list of computerPrograms
     *
     * @param computerId The unique identifier of the computer to associate the program with.
     * @param programId The unique identifier of the program to add to the computer.
     */
    fun addProgramToComputer(computerId: Int, programId: Int){
        val program = programController.getProgramId(programId)
        val computer = computerController.getComputerId(computerId)

        if(computer != null && program != null){
            computerPrograms.add(ComputerProgram(programId, computerId))
        }else{
            println("No computer program added")
        }
    }

    /**
     * Returns a formatted string representation of all stored computerPrograms
     *
     * @return A string indicating either no computerPrograms are stored or a formatted list of all stored computerPrograms
     */
    fun listAllComputerPrograms(): String =
    if  (computerPrograms.isEmpty()) "No computer programs stored"
    else formatListString(computerPrograms)

    /**
     *Retrieves a list of computers assigned to a specific program
     *
     * @param programId The unique identifier of the program for which the assigned computers are to be listed.
     * @return A list of "Computer" objects that are assigned to the program with the given "programId".
     */
    //Program show computer
    fun listProgramsAssignedComputers(programId: Int): List<Computer> {
        val computersWithPrograms = computerPrograms.filter { it.programId == programId}.map{it.computerId}
        return computerController.listOfComputers().filter {it.computerId in computersWithPrograms}
    }

    /**
     * Retrieves a list of programs assigned to a specific computer
     *
     * @param computerId The unique identifier of the computer for which the assigned programs are to be listed.
     * @return A list of "Program" objects that are assigned to the program with the given "computerId".
     */
    //Computer show program
    fun listComputersWithPrograms(computerId: Int): List<Program> {
        val programsWithComputers = computerPrograms.filter { it.computerId == computerId}.map{it.programId}
        return programController.listOfPrograms().filter{it.programId in programsWithComputers}
    }

    /**
     * Deletes a computerProgram association form the list at a specific index
     *
     * @param indexToDelete The index of the computerProgram association to delete.
     * @return The "ComputerProgram" object that was removed if the index is valid, or "null" if invalid
     */
    fun deleteComputerProgram(indexToDelete: Int): ComputerProgram? {
        return if (isValidListIndex(indexToDelete, computerPrograms)) {
            computerPrograms.removeAt(indexToDelete)
        } else null
    }

    /**
     * Retrieve the total number of computerProgram associations
     *
     * @return The number of computerProgram associations currently stored.
     */
    fun numberOfComputerPrograms() = computerPrograms.size

    /**
     * Update the computerProgram association at a specific index
     *
     *  @param indexToUpdate The index of the computerProgram association to update.
     *  @param computerProgram The new "ComputerProgram" object containing the updated values.
     *  @return "true" if the update was successful, or "false" if the index is invalid or the provided computerProgram is "null"
     */
    fun updateComputerProgram(indexToUpdate: Int, computerProgram: ComputerProgram?): Boolean{
        val foundComputerProgram = findComputerProgram(indexToUpdate)
        //Attempt to get update to ensure the values picked belong to actual computers and programs
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

    /**
     *Check if the specific index is valid for the computerProgram list
     *
     * @param index The index to validate.
     * @return true if the index is valid within the "computerPrograms" list, false otherwise
     */
    fun isValidIndex(index: Int) :Boolean{
        return isValidListIndex(index, computerPrograms);
    }

    /**
     *Find and retrieve a computerProgram association by its index
     *
     * @param index The index of the computerProgram association to retrieve.
     * @return The "ComputerProgram" object at the specified index if the index is valid, null if invalid
     */
    fun findComputerProgram(index: Int): ComputerProgram? =
        if (isValidListIndex(index, computerPrograms)) computerPrograms[index]
        else null


    /**
     * Formats a list of "ComputerProgram" objects into a string representation.
     *
     * @param programsToFormat The list of "ComputerProgram" objects to format.
     *  @return A formatted string representation of the given list of computerPrograms.
     */
    private fun formatListString(programsToFormat : List<ComputerProgram>) : String =
        programsToFormat
            .joinToString (separator = "\n") { computerProgram ->
                computerPrograms.indexOf(computerProgram).toString() + ": " + computerProgram.toString() }

}



