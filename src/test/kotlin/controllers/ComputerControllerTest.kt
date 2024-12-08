package controllers

import models.Computer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ComputerControllerTest {

    private var diarmuidsPc: Computer? = null
    private var geoffsLaptop: Computer? = null
    private var gavinsMac: Computer? = null
    private var jacksPc: Computer? = null
    private var michaelLaptop: Computer? = null
    private var populatedComputers: ComputerController? = ComputerController()
    private var emptyComputers: ComputerController? = ComputerController()


    @BeforeEach
    fun setup(){
        diarmuidsPc = Computer(0, "Diarmuids Computer", "Windows 11", 128.00)
        geoffsLaptop = Computer(1, "Geoffs Laptop", "Windows 10", 1024.00)
        gavinsMac = Computer(2, "Gavins Macbook", "MacOS 15", 512.00)
        jacksPc = Computer(3, "Jacks Computer", "Windows 10", 256.00)
        michaelLaptop = Computer(4, "Michaels Laptop", "Windows 11", 128.00)

        //adding 5 Computers to the computer controller
        populatedComputers!!.add(diarmuidsPc!!)
        populatedComputers!!.add(geoffsLaptop!!)
        populatedComputers!!.add(gavinsMac!!)
        populatedComputers!!.add(jacksPc!!)
        populatedComputers!!.add(michaelLaptop!!)
    }

    @AfterEach
    fun tearDown(){
        diarmuidsPc = null
        geoffsLaptop = null
        gavinsMac = null
        jacksPc = null
        michaelLaptop = null
        populatedComputers = null
        emptyComputers = null
    }


    @Nested
    inner class AddComputers {
        @Test
        fun `adding a Computer to a populated list adds to ArrayList`() {
            val newComputer = Computer(5, "Test Computer", "Windows 10", 256.00)
            assertEquals(5, populatedComputers!!.numberOfComputers())
            assertTrue(populatedComputers!!.add(newComputer))
            assertEquals(6, populatedComputers!!.numberOfComputers())
            assertEquals(newComputer, populatedComputers!!.findComputer(populatedComputers!!.numberOfComputers() - 1))
        }

        @Test
        fun `adding a Computer to an empty list adds to ArrayList`() {
            val newComputer = Computer(5, "Test Computer", "Windows 10", 256.00)
            assertEquals(0, emptyComputers!!.numberOfComputers())
            assertTrue(emptyComputers!!.add(newComputer))
            assertEquals(1, emptyComputers!!.numberOfComputers())
            assertEquals(newComputer, emptyComputers!!.findComputer(emptyComputers!!.numberOfComputers() - 1))
        }
    }

    @Nested
    inner class ListComputers {

        @Test
        fun `listComputers returns No Computer Stored message when ArrayList is empty`() {
            assertEquals(0, emptyComputers!!.numberOfComputers())
            assertTrue(emptyComputers!!.listComputers().lowercase().contains("no computers"))
        }

        @Test
        fun `listComputers returns Computers when ArrayList has computers stored`() {
            assertEquals(5, populatedComputers!!.numberOfComputers())
            val computersString = populatedComputers!!.listComputers().lowercase()
            assertTrue(computersString.contains("diarmuids computer"))
            assertTrue(computersString.contains("geoffs laptop"))
            assertTrue(computersString.contains("gavins macbook"))
            assertTrue(computersString.contains("jacks computer"))
            assertTrue(computersString.contains("michaels laptop"))
        }
    }

    @Nested
    inner class DeleteComputers {

        @Test
        fun `deleting a Computer that does not exist, returns null`() {
            assertNull(emptyComputers!!.deleteComputer(0))
            assertNull(populatedComputers!!.deleteComputer(-1))
            assertNull(populatedComputers!!.deleteComputer(5))
        }

        @Test
        fun `deleting a Computer that exists delete and returns deleted object`() {
            assertEquals(5, populatedComputers!!.numberOfComputers())
            assertEquals(michaelLaptop, populatedComputers!!.deleteComputer(4))
            assertEquals(4, populatedComputers!!.numberOfComputers())
            assertEquals(diarmuidsPc, populatedComputers!!.deleteComputer(0))
            assertEquals(3, populatedComputers!!.numberOfComputers())
        }
    }


    @Nested
    inner class UpdateComputer {
        @Test
        fun `updating a computer that does not exist returns false`(){
            assertFalse(populatedComputers!!.updateComputer(6, Computer(6,"Johns Computer","MacOS X",2048.00)))
            assertFalse(populatedComputers!!.updateComputer(-1, Computer(6,"Johns Computer","MacOS X",2048.00)))
            assertFalse(emptyComputers!!.updateComputer(0, Computer(6,"Johns Computer","MacOS X",2048.00)))
        }

        @Test
        fun `updating a computer that exists returns true and updates`() {
            //check computer 5 exists and check the contents
            assertEquals(michaelLaptop, populatedComputers!!.findComputer(4))
            assertEquals("Michaels Laptop", populatedComputers!!.findComputer(4)!!.computerName)
            assertEquals("Windows 11", populatedComputers!!.findComputer(4)!!.operatingSystem)
            assertEquals(128.00, populatedComputers!!.findComputer(4)!!.storage)

            //update computer 5 with new information and ensure contents updated successfully
            assertTrue(populatedComputers!!.updateComputer(4, Computer(4, "Updated computer", "Updated system", 2048.00)))
            assertEquals("Updated computer", populatedComputers!!.findComputer(4)!!.computerName)
            assertEquals("Updated system", populatedComputers!!.findComputer(4)!!.operatingSystem)
            assertEquals(2048.00, populatedComputers!!.findComputer(4)!!.storage)
        }
    }
}