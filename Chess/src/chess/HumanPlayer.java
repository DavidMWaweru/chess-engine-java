package chess;

import java.util.Scanner;

public class HumanPlayer implements Player {

	private Scanner scanner;
	
   public HumanPlayer(Scanner scanner) {
	   this.scanner = scanner;
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
	
	@Override
    public Move getMove(Game game) {
        return getMoveFromInput(scanner, game);
    }
	
}
