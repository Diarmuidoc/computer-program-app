package controllers

import models.Program
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ProgramControllerTest {

    private var word: Program? = null
    private var intellij: Program? = null
    private var vlc: Program? = null
    private var chrome: Program? = null
    private var steam: Program? = null
    private var populatedPrograms: ProgramController? = ProgramController()
    private var emptyPrograms: ProgramController? = ProgramController()


    @BeforeEach
    fun setup(){
        word = Program(0, "Microsoft Word", 2.3, "17.1")
        intellij = Program(1, "Intellij", 4.59, "4.2")
        vlc = Program(2, "VLC", 1.7, "20.8")
        chrome = Program(3, "Google Chrome", 5.5, "19.1.1")
        steam = Program(4, "Steam", 10.7, "24.12")

        //adding 5 Programs to the program controller
        populatedPrograms!!.add(word!!)
        populatedPrograms!!.add(intellij!!)
        populatedPrograms!!.add(vlc!!)
        populatedPrograms!!.add(chrome!!)
        populatedPrograms!!.add(steam!!)
    }

    @AfterEach
    fun tearDown(){
        word = null
        intellij = null
        vlc = null
        chrome = null
        steam = null
        populatedPrograms = null
        emptyPrograms = null
    }


    @Nested
    inner class AddPrograms {
        @Test
        fun `adding a Program to a populated list adds to ArrayList`() {
            val newProgram = Program(5, "Test Program", 15.05, "V1")
            assertEquals(5, populatedPrograms!!.numberOfPrograms())
            assertTrue(populatedPrograms!!.add(newProgram))
            assertEquals(6, populatedPrograms!!.numberOfPrograms())
            assertEquals(newProgram, populatedPrograms!!.findProgram(populatedPrograms!!.numberOfPrograms() - 1))
        }

        @Test
        fun `adding a Program to an empty list adds to ArrayList`() {
            val newProgram= Program(5, "Test Program", 15.05, "V1")
            assertEquals(0, emptyPrograms!!.numberOfPrograms())
            assertTrue(emptyPrograms!!.add(newProgram))
            assertEquals(1, emptyPrograms!!.numberOfPrograms())
            assertEquals(newProgram, emptyPrograms!!.findProgram(emptyPrograms!!.numberOfPrograms() - 1))
        }
    }

    @Nested
    inner class ListPrograms {

        @Test
        fun `listPrograms returns No Program Stored message when ArrayList is empty`() {
            assertEquals(0, emptyPrograms!!.numberOfPrograms())
            assertTrue(emptyPrograms!!.listPrograms().lowercase().contains("no programs"))
        }

        @Test
        fun `listPrograms returns Programs when ArrayList has programs stored`() {
            assertEquals(5, populatedPrograms!!.numberOfPrograms())
            val programsString = populatedPrograms!!.listPrograms().lowercase()
            assertTrue(programsString.contains("microsoft word"))
            assertTrue(programsString.contains("intellij"))
            assertTrue(programsString.contains("vlc"))
            assertTrue(programsString.contains("google chrome"))
            assertTrue(programsString.contains("steam"))
        }
    }

    @Nested
    inner class DeletePrograms {

        @Test
        fun `deleting a Program that does not exist, returns null`() {
            assertNull(emptyPrograms!!.deleteProgram(0))
            assertNull(populatedPrograms!!.deleteProgram(-1))
            assertNull(populatedPrograms!!.deleteProgram(5))
        }

        @Test
        fun `deleting a Program that exists delete and returns deleted object`() {
            assertEquals(5, populatedPrograms!!.numberOfPrograms())
            assertEquals(steam, populatedPrograms!!.deleteProgram(4))
            assertEquals(4, populatedPrograms!!.numberOfPrograms())
            assertEquals(word, populatedPrograms!!.deleteProgram(0))
            assertEquals(3, populatedPrograms!!.numberOfPrograms())
        }
    }


    @Nested
    inner class UpdateProgram {
        @Test
        fun `updating a program that does not exist returns false`(){
            assertFalse(populatedPrograms!!.updateProgram(6, Program(6,"Adobe Photoshop",20.12,"25.1")))
            assertFalse(populatedPrograms!!.updateProgram(-1, Program(6,"Adobe Photoshop",20.12,"25.1")))
            assertFalse(emptyPrograms!!.updateProgram(0, Program(6,"Adobe Photoshop",20.12,"25.1")))
        }

        @Test
        fun `updating a program that exists returns true and updates`() {
            //check program 5 exists and check the contents
            assertEquals(steam, populatedPrograms!!.findProgram(4))
            assertEquals("Steam", populatedPrograms!!.findProgram(4)!!.programName)
            assertEquals(10.7, populatedPrograms!!.findProgram(4)!!.programSize)
            assertEquals("24.12", populatedPrograms!!.findProgram(4)!!.programVersion)

            //update program 5 with new information and ensure contents updated successfully
            assertTrue(populatedPrograms!!.updateProgram(4, Program(4, "Updated program", 11.11, "1.00")))
            assertEquals("Updated program", populatedPrograms!!.findProgram(4)!!.programName)
            assertEquals(11.11, populatedPrograms!!.findProgram(4)!!.programSize)
            assertEquals("1.00", populatedPrograms!!.findProgram(4)!!.programVersion)
        }
    }
}