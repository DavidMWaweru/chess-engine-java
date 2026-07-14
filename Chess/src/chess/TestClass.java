package chess;

public class TestClass {
    public static void main(String[] args) {

        System.out.println("Game starting!!!");


        
        Game game = new Game(); // start your chess game here
        Board board = game.getBoard();
        //King john = new King(new Position(7, 7), Color.BLACK);
        //board.addPieceAt(john);
        board.printBoard();
        //System.out.println(john.getPseudoLegalMoves(board));
        //System.out.println(john.getPseudoLegalMoves(board).size());
        
        //Piece john = board.getPieceAt(7, 0);
        //System.out.println(john.getPseudoLegalMoves(board));
        
        System.out.println(board.getAllLegalMoves(Color.WHITE));
        System.out.println(board.getAllLegalMoves(Color.WHITE).size());
        
        System.out.println(board.getAllLegalMoves(Color.BLACK));
        System.out.println(board.getAllLegalMoves(Color.BLACK).size());
        
        
        
        Move e4 = new Move(board.getPieceAt(6,4), new Position(6,4), new Position(4,4));
        Move e5 = new Move(board.getPieceAt(1,4), new Position(1,4), new Position(3,4));
        
        board.movePiece(e4);
        board.movePiece(e5);
        
        board.printBoard();
        
        System.out.println(board.getAllLegalMoves(Color.WHITE));
        System.out.println(board.getAllLegalMoves(Color.WHITE).size());
        
        System.out.println(board.getAllLegalMoves(Color.BLACK));
        System.out.println(board.getAllLegalMoves(Color.BLACK).size());
        
        
        

        
    }
}
