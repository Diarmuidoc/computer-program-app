package controllers

import models.Computer
import models.Program
import utils.isValidListIndex

class ComputerController {
    private val computers = mutableListOf<Computer>()
    private var lastId = 0
    private fun getId() = lastId++

    fun addComputer(computer: Computer) {
        computer.computerId = getId()
        computers.add(computer)
    }

    fun listComputers() = computers

    fun numberOfComputers() = computers.size

    fun deleteComputer(indexToDelete: Int): Computer? {
        return if (isValidListIndex(indexToDelete, computers)) {
            computers.removeAt(indexToDelete)
        } else null
    }

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

    fun findComputer(index: Int): Computer? {
        return if (isValidListIndex(index, computers)) {
            computers[index]
        } else null
    }

    fun isValidIndex(index: Int) :Boolean{
        return isValidListIndex(index, computers);
    }
}

