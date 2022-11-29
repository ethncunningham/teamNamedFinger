package backgammon;

public class BackGammon {
	public static final int NUMBER_OF_PLAYERS = 2;

	public static void main(String[] args) {

		System.out.println("------WELCOME TO BACKGAMMON - LETS PLAY!--------");
		Player[] names = new Player[2];
		Display display = new Display();
		Dice dice = new Dice();
		names[0] = new Player(display.getName());
		names[1] = new Player(display.getName());

		String input;
		Command command = new Command();
		Game game;
		do {
			Board board = new Board();
			Player[] player = dice.goesFirst(names[0], names[1]);
			player[0].setBoard(BoardType.CLOCKWISE);
			player[1].setBoard(BoardType.ANTICLOCKWISE);
			player[0].setColour(CheckerColour.WHITE);
			display.displayBoard(board, player[0]);


			do {
				for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {

					boolean isTurnOver = true;

					do {
						System.out.println(player[i].getName() + " , Enter 'r' to roll, or 'q' to quit: ");

						input = display.getCommand(player[i]);
						command.setCommand(input);

						if (command.isQuit()) {
							break;
						} else if (!command.isValid()) {
							System.out.println("Invalid Syntax - Try Again");
							isTurnOver = false;
						} else if (command.isRoll()) {
							player[i].setRoll(dice.roll(), dice.roll());

							System.out.print(player[i].getName() + " rolls : " + player[i].getRoll(0) + " " + player[i].getRoll(1) +" ");

							if (player[i].isDouble()) { 
								System.out.print(player[i].getRoll(0) + " " + player[i].getRoll(0) + "\n");
							}
							 
							System.out.println();
						
							int moves = 2;
							
							if (player[i].isDouble()){ 
								moves = 4; 
							}
							
							
							do {
							game = new Game(board, player[i].getBoardType(), player[i].getColour());
							
							game.findHints(player[i].getRoll(0));
							if(moves==2 && !player[i].isDouble()) {
								game.findHints(player[i].getRoll(1));
							}
							
							game.giveHints();
							
							if(game.isMoveAvailable()) { 
									boolean isMoveDone = false; 
									do {
									input = display.getCommand(player[i]);
									System.out.println(input);
									if(game.isInputValid(input)) {
										game.Move(input); 
										isMoveDone=true;
										moves--;
										if(game.diceRollUsed(input)==player[i].getRoll(0)) {
											int rollLeft = player[i].getRoll(1); 
											player[i].setRoll(rollLeft, rollLeft);
										}
										display.displayBoard(board, player[i]);
										} 
									else {
										System.out.println("Invalid input - try again");
										} 
									} 
									while(!isMoveDone);
									}
								else { 
									System.out.println("No Valid Moves - Skipping Your Turn"); 
								}
							}
							while(moves>0);
							}
						 else {
							System.out.println("Invalid Command - Please Enter 'r' to roll, or 'q' to quit: ");
							isTurnOver = false;
						}
					} while (!isTurnOver);
				}
			}

			while (!command.isQuit());// && !game.isOver());

			System.out.println("GAME OVER!");
			System.out.println("PLAYER 1. You decide. Would you like to play again? (y/n): ");
			input = display.getCommand(player[0]);
			command.setCommand(input);

		}
		while (command.isReplay());

}
}