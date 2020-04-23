# Checkers by Lucas Albano and Steven Nissley Jr

## House Rules:
1. To win, players must eliminate all opponents checker pieces! First player to lose all their checker pieces loses the game!
2. If a checker piece is selected and has a jump move available then it must take it, you may select another piece if you do not desire to make the jump move
3. Only diagonal double jumps are allowed (for both standard and king pieces)

## Features:
1. You have the ability to create a player with your own name or nickname
2. You have the ability to save your current game and load it back on. You can not close the program window otherwise program does not run properly
3. After completion of a game, wins will be stored on a leaderboard next to the player's name
4. The leaderboard will be sorted by most wins

## Who coded what:
1. Lucas was in charge of creating the GUI interface and classes
2. Steven coded the logic behind the game

## Interface:
1. The checkerLogic class is where the program runs
2. The entire Game is wrapped around the GameGraphic Class, this class is in charge of switching between the the welcome screen and the game sceen.
3. The Welcome screen has the name of our game, action buttons and the leaderboard. It is in charge of flipping between the different panels that are available.
4. Within the Welcome screen we have the leaderboard, player selection and load game panels. These were built as seperate components and then used within WelcomeScreen for code reuse.
5. When you click start game you can either type in your name or select from a previous made player.
6. After player 2 has been selected the GameFrame will switch to the GameBoardFrame. The GameBoardFrame houses all of the gameboard components such as BottomBoardGraphic, BoardGraphic, and TopBoardGraphic.
7. TopBoardGraphic keeps track of which players turn it is and BottomBoardGraphic houses buttons to go back and to save the game.
8. The BoardGraphic handles the actual gameboard with the tiles and pieces. it does the click handling and repainting when a piece moves or gets jumped over.