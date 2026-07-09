package chess;

public class TestClass {
    public static void main(String[] args) {

        System.out.println("Game starting!!!");


        
        Game game = new Game(); // start your chess game here
        Board board = game.getBoard();
        King john = new King(new Position(7, 7), Color.BLACK);
        board.addPieceAt(john);
        board.printBoard();
        System.out.println(john.getPseudoLegalMoves(board));
        System.out.println(john.getPseudoLegalMoves(board).size());
        
        //Piece john = board.getPieceAt(7, 0);
        //System.out.println(john.getPseudoLegalMoves(board));
        

        
    }
}
