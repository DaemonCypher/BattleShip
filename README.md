# CLI text game of Battleship.
# Running the code on Docker
```
docker build -t battleship .
docker run -it --rm battleship
```
# Running the Code Locally
```
javac *.java
java main.java
```

# Instructions
The game will ask for a name where to place the ships. When placing the ships the name of each ship must be spelled correctly with the right case (i.e. Cruiser != cruiser). 
When the name is spelled properly the game will ask the user to pick an (x,y) coordinate to place the ship. Imagine the coordinate as the rear of the ship, and from there 
it will ask the direciton of the ship to be placed (i.e. which orientation). From there the game will automatically find the size of the ship and place the ship on the board
with the intial coordinate and direction by the user. Once all ships are placed the game will proceed as normal until one side has sunked all of the enemey ships.

# Symbols
@ = ship cell


X = ship hit


O = ship miss



# Example of Game Drawn on CLI 
```
Your turn to fire! Enter coordinates to hit (x y):
1
6

Player's Board:
a
  0     1     2     3     4     5     6     7     8     9
0 @     @     _     X     @     _     @     O     _     _

1 @     X     _     @     @     _     X     _     _     _

2 @     X     _     _     X     O     @     O     _     _

3 O     _     _     _     @     _     X     O     _     _

4 _     _     _     O     @     O     _     _     _     _

5 _     O     _     O     _     _     _     O     _     _

6 _     _     O     _     _     O     _     _     _     _

7 _     _     O     _     _     _     _     O     _     _

8 _     O     O     _     _     O     _     O     _     O

9 _     _     _     _     _     _     _     _     _     _


Bot's Board (with player's shots):
Bot's Board with Shots:
  0     1     2     3     4     5     6     7     8     9
0 O     O     O     O     O     O     O     O     O     O

1 _     O     X     X     X     X     X     _     _     _

2 _     _     O     _     _     _     _     _     _     _

3 _     _     _     O     _     _     _     _     _     _

4 _     _     _     _     O     _     _     _     _     _

5 _     _     _     _     _     O     _     _     _     _

6 _     _     _     _     _     _     O     _     _     _

7 _     _     _     _     _     _     _     O     _     _

8 _     _     _     _     _     _     _     _     O     _

9 _     _     _     _     _     _     _     _     _     O
```
