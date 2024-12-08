package controllers

import models.Program
import utils.isValidListIndex

/**
 * This class manages a list of programs, along with providing functionality for adding, updating, deleting and listing.
 *
 * @constructor Initializes the ProgramController
 */
class ProgramController {
    private val programs = mutableListOf<Program>()
    private var lastId = 0
    private fun getId() = lastId++

    /**
     * Add a new program to the list of programs
     *
     * @param program The "Program" object to be added. programId will be set to a specific value when created
     */
    fun addProgram(program: Program) {
        program.programId = getId()
        programs.add(program)
    }

    /**
     * Add a program to the list of programs
     *
     * This function was created for use in JUnit testing
     *
     * @param program The "Program" object to be added.
     * @return "true" if the program was successfully added, otherwise "false"
     */
    fun add(program: Program): Boolean = programs.add(program)

    /**
     * Returns a string of stored programs.
     *
     * @return a string of either the stored programs or "no program stored"
     */
    fun listPrograms(): String =
        if  (programs.isEmpty()) "No programs stored"
        else formatListString(programs)

    /**
    * Return a list of stored programs.
    *
    * This function is mainly used in the ComputerProgramController
    *
    * @return list of all programs.
    */
    fun listOfPrograms(): List<Program> = programs

    /**
     * Returns the total number of programs stored.
     *
     * @return The number of programs stored.
     */
    fun numberOfPrograms() = programs.size

    /**
     *Deletes a program from the list at a specific index
     *
     * @param indexToDelete The index of the list to be deleted
     * @return Program object that was removed from the list, otherwise null
     */
    fun deleteProgram(indexToDelete: Int): Program? =
        if (isValidListIndex(indexToDelete, programs)) programs.removeAt(indexToDelete)
        else null


    /**
     *Update the program at a specific index with new details
     *
     * @param indexToUpdate The index of the program to update.
     * @param program The new "Program" object containing updated details providing it is not null
     * @return "true" if the update is successful or "false if the index was invalid or the object is null
     */
    fun updateProgram(indexToUpdate: Int, program: Program?): Boolean{
        val foundProgram = findProgram(indexToUpdate)

        if ((foundProgram != null) && (program != null)) {
            foundProgram.programName = program.programName
            foundProgram.programSize = program.programSize
            foundProgram.programVersion = program.programVersion
            return true
        }
        return false
    }

    /**
     * Finds and retrieves a program from the list at the specified index.
     *
     * @param index The index of the program to find.
     * @return The "Program" object at the specified index if the index is valid, otherwise null.
     */
    fun findProgram(index: Int): Program? =
        if (isValidListIndex(index, programs)) programs[index]
        else null


    /**
     * Checks to see if the specified index for the program list is valid
     *
     * @param index The index to validate.
     * @return "true" if the index is valid within the "programs" list, otherwise "false"
     */
    fun isValidIndex(index: Int) :Boolean = isValidListIndex(index, programs);

    /**
     * Formats a list of "Program" objects into a string representation.
     *
     * @param programsToFormat The list of "Program" objects to format.
     * @return A formatted string representation of the given list of programs.
     */
    private fun formatListString(programsToFormat : List<Program>) : String =
        programsToFormat
            .joinToString (separator = "\n") { program ->
                programs.indexOf(program).toString() + ": " + program.toString() }

    /**
    * Retrieve a "Program" object by its unique identifier
    *
    * @param id The unique identifier of the program to retrieve.
    * @return The "Program" object with the matching "programId", or "null" if no program with the specified "id" is found
    */
    fun getProgramId(id: Int): Program? = programs.find{it.programId == id}

}


