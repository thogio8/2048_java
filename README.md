# 2048 Game

This project is a Java implementation of the popular 2048 game. It includes both the game logic and a graphical user interface (GUI) using Java Swing.

## Features

- **Game Logic**: Implements the core mechanics of the 2048 game, including tile movements and merging.
- **Graphical User Interface**: Provides a GUI for playing the game using Java Swing.
- **Keyboard Controls**: Use arrow keys to move the tiles.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- IntelliJ IDEA or any other Java IDE

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/thogio8/2048-game.git
    ```
2. Open the project in your IDE.

### Running the Game

1. Compile and run the `Game2048GUI` class.
2. The game window will open, and you can start playing using the arrow keys.

## Code Overview

### `Game2048.java`

This class contains the game logic, including methods for moving tiles (`moveLeft`, `moveRight`, `moveUp`, `moveDown`), adding new tiles, and checking for game over conditions.

### `Game2048GUI.java`

This class extends `JPanel` and handles the graphical representation of the game. It includes methods for drawing tiles and handling keyboard input.

## Controls

- **Up Arrow**: Move tiles up
- **Down Arrow**: Move tiles down
- **Left Arrow**: Move tiles left
- **Right Arrow**: Move tiles right