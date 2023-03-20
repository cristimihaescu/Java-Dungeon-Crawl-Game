## Dungeon Crawl 🗡️🛡️🕹️
Dungeon Crawl is a roguelike game created using Java programming language. It is tile-based, turn-based, and features permadeath. The goal of the game is to explore a labyrinth and retrieve treasure from its bottom while avoiding monsters and traps.

## Technology Used 🖥️
Dungeon Crawl is built using Object-Oriented Programming (OOP) principles and design patterns, particularly layer separation. The game logic, such as player movement and game rules, is implemented in the logic package, which is completely independent of the user interface code. This means that the same logic code can be used for different interfaces, such as terminal, web, Virtual Reality, and so on.

## The project also utilizes the following technologies:

JavaFX for GUI development 🎨
JUnit for testing 🧪
Git for version control 🗃️
MySQL for database management 📊

## Project Features 🔧
Movement restriction: The game logic restricts the movement of the player so they cannot run into walls and monsters. 🚫🏰👹
Dungeon items: The game features at least two types of items, such as a key and a sword. The items are visible in the GUI, and a player can stand on the same square as an item. 🗝️🗡️
Item pickup: The hero can pick up an item by clicking the "Pick up" button on the right side of the screen. After the player picks up the item, it disappears from the map. 🛡️🔍
Inventory list: Picked up items are displayed in the inventory list on the right side of the screen. 📜🎒

## How to Play 🎮
To play the game, simply run the Main class in your Java IDE. The game GUI should appear, showing the player character in a randomly generated dungeon. Use the arrow keys to move the player, and the "Pick up" button to pick up items. Try to reach the bottom of the dungeon and retrieve the treasure without dying! 💀💰

## Database Configuration 📊
To configure the database, first, you need to create a new MySQL database named "dungeon_crawl". You can do this in your MySQL command-line interface by running the following command:

CREATE DATABASE dungeon_crawl;

Next, you need to run the SQL script located in the "sql" folder to create the necessary tables. To do this, open the "edit_config.txt" file located in the root directory and replace the "DATABASE_PASSWORD" placeholder with your MySQL database password.

After that, run the following command in your command-line interface, making sure to navigate to the root directory of the project first:

mysql -u root -p < sql/dungeon_crawl_tables.sql

This will execute the SQL script and create all the necessary tables for the game.

Note: If you're using a different MySQL user besides root, replace "root" in the command above with your username.


## Level 1
![image](https://user-images.githubusercontent.com/99211882/226342693-e36b1da6-4a77-41b5-bd96-bc99afec3ede.png)
## Hero
![image](https://user-images.githubusercontent.com/99211882/226343062-ea0e7e59-0aa7-469b-83e1-46dad9c66b44.png)

![image](https://user-images.githubusercontent.com/99211882/226343350-9d300d22-528c-4044-afac-7bb67ec2e08e.png)

