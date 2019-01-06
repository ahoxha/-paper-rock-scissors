# The Paper-Rock-Scissors game

This program is developed in eclise using gradle. It is shipped with **gradle wrapper**, so you don't have to have **gradle** installed to be able to build the project. All you have to do is use `gradlew` (or `./gradlew`) instead of `gradle` when you run **gradle** commands.

## How to build the project?

After extracting the files into a folder, CD into the project's folder and type:
`gradlew build`

## How to run the program?

After the build succeeds, look into this path `{project_folder}/build/libs/` and you will find a `jar` file named `paper-rock-scissors-{version_number}.jar` (for example: `paper-rock-scissors-1.0.0.jar`).
`CD` into this folder, then type: `java -jar paper-rock-scissors-1.0.0.jar`. 
After the program starts you will be asked to play the game.

## How to run the tests?

CD into the project's folder and type:
`gradlew test`

You can see the **test results** in the `{project_folder}/build/reports/tests/test/index.html` file.

## Code coverage

I've used the `jacoco` gradle plugin to run the code coverage analysis. 

**To run the code coverage task**

CD into the project's folder, then type `gradlew build jacocoTestReport`. 
You can see the **code coverage results** in the `{project_folder}/build/jacocoHtml/index.html` file.

## How to make it an eclipse project and continue working on it?

CD into the project directory and type:
`gradlew eclise`

Then you can import it into your Eclipse IDE.