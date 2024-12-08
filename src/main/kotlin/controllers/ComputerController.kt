package controllers

import models.Computer
import utils.isValidListIndex

/**
 * This class manages a list of computers, along with providing functionality for adding, updating, deleting and listing.
 *
 * @constructor Initializes the ComputerController
 */
class ComputerController {
    private val computers = mutableListOf<Computer>()
    private var lastId = 0
    private fun getId() = lastId++

    /**
     * Add a new computer to the list of computers
     *
     * @param computer The "Computer" object to be added. computerId will be set to a specific value when created
     */
    fun addComputer(computer: Computer) {
        computer.computerId = getId()
        computers.add(computer)
    }

    /**
     * Add a computer to the list of computers
     *
     * This function was created for use in JUnit testing
     *
     * @param computer The "Computer" object to be added.
     * @return "true" if the computer was successfully added, otherwise "false"
     */
    fun add(computer: Computer): Boolean =
        computers.add(computer)


    /**
     * Returns a string of stored computers
     *
     * @return a string of either the stored computers or "no computers stored"
     */
    fun listComputers(): String =
        if  (computers.isEmpty()) "No computers stored"
        else formatListString(computers)

    /**
     * Return a list of stored computers
     *
     * This function is mainly used in the ComputerProgramController
     *
     * @return list of all computers
     */
    fun listOfComputers(): List<Computer> = computers

    /**
     * Returns the total number of computers stored
     *
     * @return The number of computers stored
     */
    fun numberOfComputers() = computers.size

    /**
     *Deletes a computer from the list at a specific index
     *
     * @param indexToDelete The index of the list to be deleted
     * @return Computer object that was removed from the list, otherwise null
     */
    fun deleteComputer(indexToDelete: Int): Computer? =
        if (isValidListIndex(indexToDelete, computers)) computers.removeAt(indexToDelete)
        else null


    /**
     *Update the computer at a specific index with new details
     *
     * @param indexToUpdate The index of the computer to update.
     * @param computer The new "Computer" object containing updated details providing it is not null
     * @return "true" if the update is successful or "false if the index was invalid or the object is null
     */
    fun updateComputer(indexToUpdate: Int, computer: Computer?): Boolean{
        val foundComputer = findComputer(indexToUpdate)

        if ((foundComputer != null) && (computer != null)) {
            foundComputer.computerName = computer.computerName
            foundComputer.operatingSystem = computer.operatingSystem
            foundComputer.storage = computer.storage
            return true
        }
        return false
    }

    /**
     * Finds and retrieves a computer from the list at the specified index.
     *
     * @param index The index of the computer to find.
     * @return The "Computer" object at the specified index if the index is valid, otherwise null.
     */
    fun findComputer(index: Int): Computer? =
        if (isValidListIndex(index, computers)) computers[index]
        else null


    /**
     * Checks to see if the specified index for the computer list is valid
     *
     * @param index The index to validate.
     * @return "true" if the index is valid within the "computers" list, otherwise "false"
     */
    fun isValidIndex(index: Int) :Boolean = isValidListIndex(index, computers);


    /**
     * Formats a list of "Computer" objects into a string representation.
     *
     * @param programsToFormat The list of "Computer" objects to format.
     * @return A formatted string representation of the given list of computers.
     */
    private fun formatListString(programsToFormat : List<Computer>) : String =
        programsToFormat
            .joinToString (separator = "\n") { computer ->
                computers.indexOf(computer).toString() + ": " + computer.toString() }

    /**
     * Retrieve a "Computer" object by its unique identifier
     *
     * @param id The unique identifier of the computer to retrieve.
     * @return The "Computer" object with the matching "computerId", or "null" if no computer with the specified "id" is found
     */
    fun getComputerId(id: Int): Computer? = computers.find{it.computerId == id}
}

