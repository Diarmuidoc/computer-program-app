package controllers

import models.Computer
import models.Program
import utils.isValidListIndex
import utils.readNextFloat
import utils.readNextLine

class ProgramController {
    private val programs = mutableListOf<Program>()
    private var lastId = 0
    private fun getId() = lastId++

    fun addProgram(program: Program) {
        program.programId = getId()
        programs.add(program)
    }

    fun listPrograms(): String =
        if  (programs.isEmpty()) "No programs stored"
        else formatListString(programs)

    fun listOfPrograms(): List<Program> {
        return programs
    }

    fun numberOfPrograms() = programs.size

    fun deleteProgram(indexToDelete: Int): Program? {
        return if (isValidListIndex(indexToDelete, programs)) {
            programs.removeAt(indexToDelete)
        } else null
    }

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

    fun findProgram(index: Int): Program? {
        return if (isValidListIndex(index, programs)) {
            programs[index]
        } else null
    }

    fun isValidIndex(index: Int) :Boolean{
        return isValidListIndex(index, programs);
    }

    private fun formatListString(programsToFormat : List<Program>) : String =
        programsToFormat
            .joinToString (separator = "\n") { program ->
                programs.indexOf(program).toString() + ": " + program.toString() }

    fun getProgramId(id: Int): Program? {
        return programs.find{it.programId == id}
    }
}


