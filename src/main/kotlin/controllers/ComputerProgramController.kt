package controllers

import models.ComputerProgram

class ComputerProgramController {
    private val computerPrograms = mutableListOf<ComputerProgram>()

    fun addProgramToComputer(programId: Int, computerId: Int) {
        computerPrograms.add(ComputerProgram(programId, computerId))
    }

    fun listProgramsInComputer(computerId: Int) = computerPrograms.filter { it.computerId == computerId }
}