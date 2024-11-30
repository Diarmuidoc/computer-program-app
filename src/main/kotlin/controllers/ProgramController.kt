package controllers

import models.Program

class ProgramController {
    private val programs = mutableListOf<Program>()
    private var lastId = 0
    private fun getId() = lastId++

    fun addProgram(program: Program) {
        program.programId = getId()
        programs.add(program)
    }

    fun listPrograms() = programs
}