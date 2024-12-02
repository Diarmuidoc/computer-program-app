package controllers

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

    fun listPrograms() = programs

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
}


