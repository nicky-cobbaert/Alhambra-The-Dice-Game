# Alhambra The Dice Game

## Introduction

This is the repository for the game we created during my first year (2024-2025) at **University College Ghent**.

The game is a digital reimplementation of the board game [Alhambra The Dice Game](https://boardgamegeek.com/boardgame/25491/alhambra-the-dice-game).



## Creation Process

The game was created during the entire second semester of the academic year and served as an exercise on how to build a large project as a team. We learned the importance of using the 3-layer model in a software project (UI - Domain - Persistence) and of creating analytical models. 

We used an agile workflow during the creation process. We had a weekly meeting with our lecturer, who coached us throughout the project.

Lastly, I want to thank my colleagues because the project wouldn't have been as successful without them. Thanks to our on-point communication and teamwork, we had a good working structure. We could ask each other questions to get a clear answer. 


## Creators

| Name            |
| --------------- |
| Nicky Cobbaert  |
| Lars De Wever   |
| Wout Gheysels   |
| Sverre Lippens  |
| Jelle Van Horen |

## Instructions

### .jar file (recommended for Windows users)

1. Download the `.jar` file **for your operating system** in `https://github.com/nicky-cobbaert/Alhambra-The-Dice-Game/releases`.
2. Double-click the `.jar` file to execute it.

> [!attention]
> The linux `.jar` file can be unstable depending on the distribution being used.

### Setup using Maven (recommended for Linux and MacOS users)

#### IDE version (GUI)

1. Download or clone this repository.
2. Use a IDE of choice to build the Maven dependencies.

> [!example]
> Eclipse:
> 
> 3. Import the (whole) repository as a Maven project (import -> import folder as Maven project)
> 4. Go to the main-class (files inside `src/main`, preferably `main.launcher`) of choice and execute it (green play button).

#### command line version

1. Download or clone this repository.
2. Build the Maven dependencies using: `mvn build`
3. Execute the `main.launcher` class.


### manual setup

The game was created with the intention to be run from an **IDE** (such as *Eclipse* or *IntelliJ*). 

1. Open the project (`src` directory) in your chosen *IDE*.
2. Install the following dependencies:
    - [**JavaFX 21**](https://gluonhq.com/products/javafx/)
    - [**H2 Module**](https://github.com/h2database/h2database/releases/)
    - **MySQL Database Driver module**
    - **JUnit 5**

3. Install **javafx 21** for **java sdk 21**
4. Install **JUnit 5** <br>
    For *Eclipse*: 
    1. Right-click the project folder 
    2. Properties
    3. Java Build Path
    4. Libraries
    5. Modulepath
    6. Add Library
    7. JUnit 5

5. Add **H2 Module** and **MySQL Database Driver module** to the project's *classpath*. <br>
    For *Eclipse*: 
    1. Right-click the project folder 
    2. Properties
    3. Java Build Path
    4. Libraries
    5. Modulepath
    6. Add external JARs

6. Start the GUI or CLI version of the game. The main classes for these versions can be found in `src/main`.
