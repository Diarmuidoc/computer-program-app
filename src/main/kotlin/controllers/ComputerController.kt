package controllers

import models.Computer

class ComputerController {
    private val computers = mutableListOf<Computer>()
    private var lastId = 0
    private fun getId() = lastId++

    fun addComputer(computer: Computer) {
        computer.computerId = getId()
        computers.add(computer)
    }

    fun listComputers() = computers
}