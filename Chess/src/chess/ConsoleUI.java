package chess;
import java.util.Scanner;

public class ConsoleUI {
	
	public static void main(String[] args) {
		
		startGame();
	}
	
	
	
	private static Position convertPosition(String square) {

	    if(square.length() != 2)
	        return null;

	    char file = square.charAt(0);
	    char rank = square.charAt(1);

	    if(file < 'a' || file > 'h')
	        return null;

	    if(rank < '1' || rank > '8')
	        return null;


	    int col = file - 'a';
	    int row = 8 - Character.getNumericValue(rank);

	    return new Position(row, col);
	}
	
	public static Move getMoveFromInput(Scanner scanner, Game game) {
	    
	    
	    while(true) {
	        System.out.print("Enter move: ");
	        String input = scanner.nextLine();

	        if(input.equals("hint")) {
	        	Color color = (game.whitesTurn()) ? Color.WHITE : Color.BLACK;
	        	System.out.println(game.getBoard().getAllLegalMoves(color));
	        	continue;
	        }
	        String[] parts = input.split(" ");
	        

	        if(parts.length != 2) {
	            System.out.println("Invalid format. Example: e2 e4");
	            continue;
	        }

	        Position start = convertPosition(parts[0]);
	        Position end = convertPosition(parts[1]);

	        if(start == null || end == null) {
	            System.out.println("Invalid square");
	            continue;
	        }

	        
	        return new Move(start, end);
	    }
	}
	
	
	public static void startGame() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("starting game!!!");
        Game game = new Game(true);
        Board board = game.getBoard();
        board.printBoardForPlayer();
        
        do {
        	String color = (game.whitesTurn()) ? "White's" : "Black's";
        	System.out.println(color + " turn to move.");
        	boolean moveMade;
        
        	do {
        	
        		Move temp = getMoveFromInput(scanner, game);
        		moveMade = game.makeMove(temp);
        		if(!moveMade) {
        			System.out.println("illegal move type hint for a move");
        		}
        	} while(!moveMade);
        
        	board.printBoardForPlayer();
        
        } while(!game.checkForCheckmate() && !game.checkForStalemate());
        
        if(game.checkForCheckmate()) {
        	String color = (game.whitesTurn()) ? "Black" : "White";
        	System.out.println(color + " wins by checkmate.");
        } else if(game.checkForStalemate()) {
        	String color = (game.whitesTurn()) ? "White" : "Black";
        	System.out.println(color + " has no legal moves, draw by stalemate");
        } else {
        	System.out.println("something happened");
        }

        scanner.close();
        
        
        
        
        

	}
	
}
