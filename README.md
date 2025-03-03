# HUBBM Duck Hunt - JavaFX Project

## Introduction
This project is a simplified version of the classic Duck Hunt game, developed using JavaFX as part of the BBM 104: Introduction to Programming Laboratory II course. The goal of this assignment is to gain experience in developing a Graphical User Interface (GUI) application using Java, without relying on external assets or additional libraries.

## Getting Started
### Prerequisites
- Java 8 (Oracle) must be installed on your system.
- A JavaFX-compatible development environment such as IntelliJ IDEA or Eclipse.

### Installation & Execution
1. Clone or download the project repository.
2. Ensure all necessary Java files (*.java) are included.
3. Compile the Java files using:
   ```sh
   javac *.java
   ```
4. Run the application using:
   ```sh
   java DuckHunt
   ```

## Game Overview
### Title Screen
- Displays flashing text: "PRESS ENTER TO PLAY" and "PRESS ESC TO EXIT".
- Background music (`assets/effects/Title.mp3`) loops continuously.
- Pressing `ENTER` navigates to the Background Selection Screen.
- Pressing `ESC` exits the game.
- Game title: "HUBBM Duck Hunt".
- Favicon: `assets/favicon/1.png`.

### Background Selection Screen
- Users can select background and crosshair using arrow keys:
  - Left/Right: Change background
  - Up/Down: Change crosshair
- Options reset if the user returns to this screen from the title screen.
- Background music continues uninterrupted.
- Pressing `ENTER` starts the game (playing `assets/effects/Intro.mp3` before the game begins).
- Pressing `ESC` returns to the Title Screen.

### Game Mechanics
- Cursor changes to the selected crosshair.
- Ducks move in various directions and reflect off screen edges and other ducks.
- Ammo is limited per level (3x the number of ducks per level).
- If all ducks are shot, the level advances; otherwise, it's game over.
- Text indicators:
  - Top: Level info (e.g., "Level 1/6")
  - Right corner: Ammo count (e.g., "Ammo Left: 2")
- Sound Effects:
  - Gunshot: `assets/effects/Gunshot.mp3`
  - Duck hit & falling: `assets/effects/DuckFalls.mp3`
  - Level completed: `assets/effects/LevelCompleted.mp3`
  - Game over: `assets/effects/GameOver.mp3`
  - Game completed: `assets/effects/GameCompleted.mp3`
- Winning and losing scenarios:
  - Completing all levels triggers game completion.
  - Running out of ammo with ducks remaining results in game over.
  - Players can restart or return to the title screen.

### Scaling Feature
- Controlled via `SCALE` parameter in `DuckHunt.java`.
- Default value: `3.0`.
- Adjusts the size of game objects proportionally.

### Volume Control
- Controlled via `VOLUME` parameter in `DuckHunt.java`.
- Default value: `0.025`.
- Value range: `[0, 1]` (0 = mute, 1 = max volume).

## Restrictions
- Use only **pure Java** (No CSS, FXML, Scene Builder, or extra libraries).
- Use **relative paths** for assets (no absolute paths allowed).

## Author
BBM 104: Introduction to Programming Laboratory II - Spring 2024

## Acknowledgments
- Inspired by the original **Duck Hunt** game developed by Nintendo (1984).
- JavaFX framework used for GUI development.







Please specify the location (e.g. src/ or src/mypackage) 
where your implementation should be compiled at:
/////////// Type Below: LOCATION OF DIR ///////////
src/
///////////////////////////////////////////////////


Please specify the command that is needed 
to COMPILE your implementation:
(e.g. javac DuckHunt.java)
Note that any external libraries are not allowed!
///////////// Type Below: COMPILATION //////////////
javac *.java
///////////////////////////////////////////////////


Please specify the command that is needed 
to RUN your implementation:
(e.g. java DuckHunt)
Note that any external libraries are not allowed!
///////////////// Type Below: RUN /////////////////
java DuckHunt <DOUBLE_SCALE_VALUE> <DOUBLE_VOLUME_VALUE_FROM_0_TO_1>
///////////////////////////////////////////////////

Please specify the location (e.g. src/ or src/mypackage)
where assets folder should be inserted:
////////// Type Below: LOCATION OF ASSETS //////////
src/
///////////////////////////////////////////////////

Please specify extra information if you have any
////////// Type Below: EXTRA INFORMATION //////////

///////////////////////////////////////////////////
