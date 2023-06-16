# TicTacToe
A game of Tictactoe I have written in Java for Object-Oriented Programming 2 classes. The game allows playing in local mode (two players on one computer), gameplay against a bot, as well as online mode using a server-client mechanism.
## GUI
The program uses Java Swing and Awt library to create a simple, yet functional frame which allows user to choose the type of gameplay. 
- After clicking *Game Against Bot or Local Game button*, the main windows closes and game window pops up.
- In case of clicking *Host Game button*, the main window doesn't dissapear and game window pops up, but it is empty until client connects to the server. The waiting process makes both window frozen and I don't know how to fix it apart from restarting program in IDE.
- Clicking *Join Online button* causes the client to connect with active server and windows act the same as for *Game Against Bot/Local Game button*. If no server is on, choosing this option throws exception and user is unable to play.
  
![image](https://github.com/Eteiz/TicTacToe/assets/97179185/dee30d98-cdbc-443b-b4f3-cf31b780e791)
## Gameplay
### Game Against Bot
The bot written for game purposes could hardly be called a bot, because in this game, it randomly chooses a tile on which it will place its mark. The player always starts the game and places *X symbol*. After the player's move, the buttons are locked for 1 second and bot makes its move.

![image](https://github.com/Eteiz/TicTacToe/assets/97179185/1852d738-ac60-4823-a850-04f8007ccf6c)
![image](https://github.com/Eteiz/TicTacToe/assets/97179185/f528f914-ed88-4a3c-b4c9-5279aca514b6)
### Local Game 
Local Game allows two player to play against each other on one computer. The first player places *X symbol* and its up to the players to decide who is who and who makes the move first.

![image](https://github.com/Eteiz/TicTacToe/assets/97179185/2dafa432-1c8f-484c-ba8a-cae67f673f74)
![image](https://github.com/Eteiz/TicTacToe/assets/97179185/ec625fff-4202-43a2-8661-d51135c6ffb7)
![image](https://github.com/Eteiz/TicTacToe/assets/97179185/51d50683-10aa-4d90-90a3-93e4b025de15)

### Online Game
In order for two players to play online on two different devices, they have to be connected to the same network. The program which is Server has to have host set on localhost and the one which is a Client has 
to have host set on Server device's IP. Alternatively, if someone would like to run Server and Client on the same device, both hosts should be set on localhost. **It is important that the Server is turned on first.**
Although programs lock buttons when game ends, the label informing of player's turn is not updated to victory message, maybe I will fix it in the future;
![image](https://github.com/Eteiz/TicTacToe/assets/97179185/9cccf9cb-5944-4f66-ab80-a65fd9315012)










