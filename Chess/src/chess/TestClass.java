package chess;

public class TestClass {
    public static void main(String[] args) {

        


        
        Game game = new Game(true); // start your chess game here
        Board board = game.getBoard();
        board.printBoardForPlayer();
        //Pawn john = new Pawn(new Position(6, 6), Color.BLACK, 1);
        //board.addPieceAt(john);
        //board.printBoard();
        //System.out.println(john.getPseudoLegalMoves(board));
        //System.out.println(john.getPseudoLegalMoves(board).size());
        
        //Piece john = board.getPieceAt(7, 0);
        //System.out.println(john.getPseudoLegalMoves(board));
        
        //System.out.println(board.getAllLegalMoves(Color.WHITE));
        //System.out.println(board.getAllLegalMoves(Color.WHITE).size());
        
        //System.out.println(board.getAllLegalMoves(Color.BLACK));
        //System.out.println(board.getAllLegalMoves(Color.BLACK).size());
        
        
        
        //Move e4 = new Move(board.getPieceAt(6,4), new Position(6,4), new Position(4,4));
        //Move e5 = new Move(board.getPieceAt(1,4), new Position(1,4), new Position(3,4));
        //Move promote = new Move(board.getPieceAt(6,6), new Position(6,6), new Position(7,6));
        
        //board.movePiece(e4);
        //board.movePiece(e5);
        //board.movePiece(promote);
        
        //board.printBoard();
        
        //System.out.println(board.getAllLegalMoves(Color.WHITE));
        //System.out.println(board.getAllLegalMoves(Color.WHITE).size());
        
        //System.out.println(board.getAllLegalMoves(Color.BLACK));
        //System.out.println(board.getAllLegalMoves(Color.BLACK).size());
        
        
        

        
    }
}
