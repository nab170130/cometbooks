# CometBooks

This repository covers an implementation of the CometBooks problem description from the Fall 2022 offering of SE 6329: Object-Oriented Software Engineering (Instructor: Dr. Rym Zalila-Wenkstern; Teaching Assistant: Kirthy Kolluri).

## Build Instructions

This project is developed in [Visual Studio Code](https://code.visualstudio.com/), and it utilizes the Gradle build tool. To run the executables, clone this repository and open the resulting directory as a folder within Visual Studio Code. With the Gradle build tool extension installed (or simply with Gradle), you can run the `assemble` task to build each distribution (CometBooks client, notification server). The executables can then be run by using the generated distributions under each build subfolder (app, server). Alternatively, you can run the client and server using the run configurations of this Visual Studio Code project.