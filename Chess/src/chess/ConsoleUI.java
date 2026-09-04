package chess;
import java.util.Scanner;
import java.util.Random;

public class ConsoleUI {
	
	public static void main(String[] args) {
		
		startGame();
	}
	
	
	public static void startGame() {
		Player whitePlayer;
		Player blackPlayer;
		Scanner scanner = new Scanner(System.in);
		System.out.println("starting game!!!");
		System.out.println("what kind of game would you like to play");

		int input = 0;

		while (input < 1 || input > 3) {
		    System.out.println("1. Human vs Human");
		    System.out.println("2. Human vs Engine");
		    System.out.println("3. Engine vs Engine");

		    if (scanner.hasNextInt()) {
		        input = scanner.nextInt();
		    } else {
		        scanner.next(); // discard invalid token
		    }
		}
		
			switch (input) {
			case 1:
				System.out.println("1");
				whitePlayer = new HumanPlayer(scanner);
				blackPlayer = new HumanPlayer(scanner);
				break;
			case 2:
				System.out.println("2");
				System.out.println("1. White");
				System.out.println("2. Black");
				System.out.println("3. Random");
			    if (scanner.hasNextInt()) {
			        input = scanner.nextInt();
			    } else {
			        scanner.next(); // discard invalid token
			    }
			    switch (input) {
				case 1:
					System.out.println("1");
					whitePlayer = new HumanPlayer(scanner);
					blackPlayer = new EnginePlayerV1(Color.BLACK);
					break;
				case 2:
					System.out.println("2");
					whitePlayer = new EnginePlayerV1(Color.WHITE);
					blackPlayer = new HumanPlayer(scanner);
					break;
				case 3:
					System.out.println("3");
					Random r = new Random();
					int random = r.nextInt(2);
					if(random == 1) {
						whitePlayer = new HumanPlayer(scanner);
						blackPlayer = new EnginePlayerV1(Color.BLACK);
					} else {
						whitePlayer = new EnginePlayerV1(Color.WHITE);
						blackPlayer = new HumanPlayer(scanner);
					}
					
					break;
				default:
					System.out.println("invaild input, try again:");	
					whitePlayer = new EnginePlayerV1(Color.WHITE);
					blackPlayer = new EnginePlayerV1(Color.BLACK);
				}
				break;
			case 3:
				System.out.println("3");
				whitePlayer = new EnginePlayerV1(Color.WHITE);
				blackPlayer = new EnginePlayerV1(Color.BLACK);
				break;
			default:
				System.out.println("invaild input, try again:");	
				whitePlayer = new EnginePlayerV1(Color.WHITE);
				blackPlayer = new EnginePlayerV1(Color.BLACK);
			}
		
		
        Game game = new Game(true);
        Board board = game.getBoard();
        board.printBoardForPlayer();

        do {
        	String color = (game.whitesTurn()) ? "White's" : "Black's";
        	System.out.println(color + " turn to move.");
        	boolean moveMade;
        
        	do {
        	
        		Player player = (game.whitesTurn()) ? whitePlayer : blackPlayer;
        		Move temp = player.getMove(game);
        		moveMade = game.makeMove(temp);
        		if(!moveMade) {
        			System.out.println("illegal move type hint for a move");
        		}
        	} while(!moveMade);
        
        	board.printBoardForPlayer();
        
        } while(!game.checkForCheckmate() && !game.checkForStalemate() && !game.checkForProgress() && board.checkForSufficientMaterial());
        
        if(game.checkForCheckmate()) {
        	String color = (game.whitesTurn()) ? "Black" : "White";
        	System.out.println(color + " wins by checkmate.");
        } else if(game.checkForStalemate()) {
        	String color = (game.whitesTurn()) ? "White" : "Black";
        	System.out.println(color + " has no legal moves, draw by stalemate");
        } else if (!board.checkForSufficientMaterial()) {
        	System.out.println("draw because of insufficient materials");
        } else {
        	System.out.println("Sufficient material: " + board.checkForSufficientMaterial());
        	System.out.println("Progress: " + game.checkForProgress());
        	System.out.println("draw because no progress in last 50 moves");
        }

        scanner.close();
        
	}
	
}
