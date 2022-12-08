package backgammon;

import java.util.*;

public class Display {
	
	Scanner in;
	Scanner in2;

	Display (){
		in = new Scanner(System.in);
	}
	
	public String getName() {
		System.out.print("Enter your name: ");
		String name = in.nextLine();
		return name;
	}
	
	public int getMatchLength(Game game) {
		in2 = new Scanner(System.in);
		int matchLength;
			do {
			System.out.print("\nEnter match length (odd): ");	
			matchLength = in2.nextInt();
			game.setMatchLength(matchLength);
			} while (game.getMatchLength()%2 ==0);
			
			return matchLength;
		
	}
	
	public void displayBoard(Board board, Player player, int matchLength) {
		System.out.println("\n  -----------------------------------------------------------------");
		if(player.getBoardType() == BoardType.ANTICLOCKWISE) {
		System.out.println("  Match Length: " + matchLength + "      " + player.getName()+"'s score: " + player.getScore() + "      " + player.getName() + "'s Bear-Off: " + board.getBear(2));
		System.out.println("  -----------------------------------------------------------------");
		System.out.print("  12   11   10   9    8    7   | BAR |  6    5    4    3    2    1 ");
		}
		else {
		System.out.println("  Match Length: " + matchLength +"      " + player.getName()+"'s score: " + player.getScore() + "      " + player.getName() + "'s Bear-Off: " + board.getBear(1));
		System.out.println("  -----------------------------------------------------------------");
		System.out.print("  13   14   15   16   17   18  | BAR |  19   20   21   22   23   24 ");
		}
		System.out.println("\n  -----------------------------------------------------------------");
		int MaxSize = board.maxStackSize();
		
		for(int m = 0; m < MaxSize ; ++m) {
			for(int n = 12; n <= 23; ++n) {
				
				 if(n == 17) {
				    	if( board.StackIndexEmpty(n,m) == true)
				    	{
				    		System.out.print("      |     | ");
				    	}
				    	else
						System.out.print(board.getStack(n).get(m).toString()+" |     | ");
					}
					
				    else if( board.StackIndexEmpty(n,m) == true)
						System.out.print("     ");
				
				else 
					System.out.print(board.getStack(n).get(m).toString());
					
			}
			System.out.println();
		}
		
		if(board.hasCheckerOnBar()) {
			System.out.println("                                " + board.getBar().toString());
		}
		else {
		System.out.println();
		}
		
		for(int i=MaxSize-1;  i>=0 ; --i) {
			for(int j = 11; j >= 0; --j) {
				
				
			    if(j == 6) {
			    	if( board.StackIndexEmpty(j,i) == true)
			    	{
			    		System.out.print("      |     | ");
			    	}
			    	else
					System.out.print(board.getStack(j).get(i).toString()+" |     | ");
				}
				
			    else if( board.StackIndexEmpty(j,i) == true)
					System.out.print("     ");
				
				else 
					System.out.print(board.getStack(j).get(i).toString());
					
			}
			System.out.println();
		}
		System.out.println("  -----------------------------------------------------------------");
		if(player.getBoardType() == BoardType.ANTICLOCKWISE) {
		System.out.println("  13   14   15   16   17   18  | BAR |  19   20   21   22   23   24 ");
		}
		else {
			System.out.println("  12   11   10   9    8    7   | BAR |   6    5    4    3    2    1 ");
		}
		System.out.println("  -----------------------------------------------------------------\n  Stuck?, enter 'h' for hints!\n");
	}
	
	
	public String getCommand(Player player) {
		System.out.print("\n" + player.getName() + " enter command: ");
		String input = in.nextLine();
		return input;
	}
	
	public void openingScreen(){
		System.out.println("\n-------------WELCOME TO BACKGAMMON - LETS PLAY!---------------\n");
	}

}


