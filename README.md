# Computer Program App
The Computer Program App is a menu driven console app, developed as part of the main assignment for Software Development.

## About the App
The app, developed in Kotlin, is modeled to add programs and computers to lists, these can then be associated through a many to many relationship called computerProgram.

The functions of the app are:
- create, read, update and delete a computer entry.
- create, read, update and delete a program entry.
- create, read, update and delete an association between computer and program called computerProgram.
- read computerProgram for specific computerId or programId.

The app contains:
- Three controllers: ComputerController, ProgramController, and ComputerProgramController.
- Three models: Computer, Program, and ComputerProgram
- Main Kotlin file, to run the app

## To Run the App
Download the app, this can be done by pressing the "Code" button above and "Download ZIP", once downloaded, unzip.\
Open the app in your prefered development enviroment.\
Open the main.kt file and run it, this should launch a build menu however if it does not, check the IDE settings.

## App Menu
The menu of the app is split into 3 parts: Program; Computer; and Computer Program. A user can select a function by entering a the relative number associated with the action on the menu.\
The menu is laid out as follows:
### Program Menu
- "1) Add a Program" Create a program to add. Entering its name, size and version.
- "2) List all Programs" List out all programs added to the array.
- "3) Update a Program" Update a program, the updated program is choosen by its index number.
- "4) Delete a Program" Delete a program, once again choosen by its index number.
### Computer Menu
- "5) Add a Computer" Create a computer to add. Entering its name, operating system and storage size.
- "6) List all Computers" List out all computers added to the array. 
- "7) Update a Computer" Update a computer, choosing a specific computer by selecting its index.
- "8) Delete a Computer" Delete a computer, by selecting its index number.
### Computer Program Menu
- "9) Add a Computer Program" Create a new relationship between a program and computer. This is done by selecting the index number of both computer and program.
- "10) List Programs with Computers" Select a specific program, then list all relationships it has with computers.
- "11) List Computers with Programs" Select a specific computer, then list all relationships it has with programs.
- "12) Update a Computer Program" Update a specific computer program.
- "13) Delete a Computer Program" Delete a specific computer program.
- "14) List all Computer Programs" List every computer program.

Also:
- "0) Exit" Exits the application.

### ALso included:
JUnit5 tests for ComputerController and ProgramController.
