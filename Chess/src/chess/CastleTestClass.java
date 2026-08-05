package chess;

import java.util.List;

public class CastleTestClass {
	
	public static boolean canCastleKingSide(List<Move> moves) {
		for (Move move : moves) {
			if(move.getMoveType() == MoveType.CASTLE_KINGSIDE) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean canCastleQueenSide(List<Move> moves) {
		for (Move move : moves) {
			if(move.getMoveType() == MoveType.CASTLE_QUEENSIDE) {
				return true;
			}
		}
		return false;
	}
	
	private static void check(String testName, boolean expected, boolean actual) {
	    if (expected == actual) {
	        System.out.println("PASS: " + testName);
	    } else {
	        System.err.println("FAIL: " + testName);
	        System.out.println("    Expected: " + expected);
	        System.out.println("    Actual:   " + actual);
	    }
	}
	
	public static void main(String[] args) {
		Board board;
		Game test1 = new Game(true); 
        Board board1 = test1.getBoard();
        board = board1;
        board.printBoard();
        System.out.println("\n");
        
        board.setPieceAt(0, 0, null);
        board.setPieceAt(0, 1, null);
        board.setPieceAt(0, 2, null);
        board.setPieceAt(0, 3, null);
        board.setPieceAt(0, 5, null);
        board.setPieceAt(0, 6, null);
        board.setPieceAt(7, 1, null);
        board.setPieceAt(7, 2, null);
        board.setPieceAt(7, 3, null);
        board.setPieceAt(7, 5, null);
        board.setPieceAt(7, 6, null);
        
        board.printBoard();
        System.out.println("\n");
        System.out.println("Test 1");
        
        check("normal king side castle with black", true, canCastleKingSide(board.getAllPieceLegalMoves(board.getPieceAt(0, 4))));
        check("no rook no castle", false, canCastleQueenSide(board.getAllPieceLegalMoves(board.getPieceAt(0, 4))));
        check("normal king side castle with white", true, canCastleKingSide(board.getAllPieceLegalMoves(board.getPieceAt(7, 4))));
        check("normal queen side castle with white", true, canCastleQueenSide(board.getAllPieceLegalMoves(board.getPieceAt(7, 4))));
        System.out.println("\n");
        
		Game test2 = new Game(true); 
        Board board2 = test2.getBoard();
        board = board2;
        
        board.setPieceAt(0, 1, null);
        board.setPieceAt(0, 2, null);
        board.setPieceAt(0, 6, null);
        board.setPieceAt(7, 1, null);
        board.setPieceAt(7, 2, null);
        board.setPieceAt(7, 3, null);
        board.setPieceAt(7, 5, null);
        
        board.printBoard();
        System.out.println("\n");
        System.out.println("Test 2");

        
        check("", false, canCastleKingSide(board.getAllPieceLegalMoves(board.getPieceAt(0, 4))));
        check("", false, canCastleQueenSide(board.getAllPieceLegalMoves(board.getPieceAt(0, 4))));
        check("", false, canCastleKingSide(board.getAllPieceLegalMoves(board.getPieceAt(7, 4))));
        check("", true, canCastleQueenSide(board.getAllPieceLegalMoves(board.getPieceAt(7, 4))));
        System.out.println("\n"); 
        

		Game test3 = new Game(true); 
        Board board3 = test3.getBoard();
        board = board3;
        
        board.setPieceAt(0, 1, null);
        board.setPieceAt(0, 2, null);
        board.setPieceAt(0, 3, null);
        board.setPieceAt(0, 5, null);
        board.setPieceAt(0, 6, null);
        board.setPieceAt(7, 1, null);
        board.setPieceAt(7, 2, null);
        board.setPieceAt(7, 3, null);
        board.setPieceAt(7, 5, null);
        board.setPieceAt(7, 6, null);
        board.getPieceAt(0, 4).setHasMoved(true);
        board.getPieceAt(7, 7).setHasMoved(true);
        
        board.printBoard();
        System.out.println("\n");
        System.out.println("Test 3");
        
        check("", false, canCastleKingSide(board.getAllPieceLegalMoves(board.getPieceAt(0, 4))));
        check("", false, canCastleQueenSide(board.getAllPieceLegalMoves(board.getPieceAt(0, 4))));
        check("", false, canCastleKingSide(board.getAllPieceLegalMoves(board.getPieceAt(7, 4))));
        check("", true, canCastleQueenSide(board.getAllPieceLegalMoves(board.getPieceAt(7, 4))));
        System.out.println("\n"); 
        
        
		Game test4 = new Game(); 
        Board board4 = test4.getBoard();
        board = board4;
        
        board.printBoard();
        System.out.println("\n");
        
        King bKingT4 = new King(new Position(0, 4), Color.BLACK, false);
        board.addPieceAt(bKingT4);
        
        King wKingT4 = new King(new Position(7, 4), Color.WHITE, false);
        board.addPieceAt(wKingT4);
        
        Rook bRook1T4 = new Rook(new Position(0, 7), Color.BLACK, false);
        board.addPieceAt(bRook1T4);
        
        Rook bRook2T4 = new Rook(new Position(0, 0), Color.BLACK, false);
        board.addPieceAt(bRook2T4);
        
        Rook wRook1T4 = new Rook(new Position(7, 7), Color.WHITE, false);
        board.addPieceAt(wRook1T4);
        
        Rook wRook2T4 = new Rook(new Position(7, 0), Color.WHITE, false);
        board.addPieceAt(wRook2T4);
        
        Bishop bBishopT4 = new Bishop(new Position(4, 1), Color.BLACK, false);
        board.addPieceAt(bBishopT4);
        
        Bishop wBishopT4 = new Bishop(new Position(3, 5), Color.WHITE, false);
        board.addPieceAt(wBishopT4); 
        
        board.printBoard(); 
        System.out.println("\n");
        System.out.println("Test 4");
        
        check("", true, canCastleKingSide(board.getAllPieceLegalMoves(bKingT4)));
        check("", false, canCastleQueenSide(board.getAllPieceLegalMoves(bKingT4)));
        check("", false, canCastleKingSide(board.getAllPieceLegalMoves(wKingT4)));
        check("", false, canCastleQueenSide(board.getAllPieceLegalMoves(wKingT4)));
        System.out.println("\n"); 
        
        
		Game test5 = new Game(); 
        Board board5 = test5.getBoard();
        board = board5;
        
        board.printBoard();
        System.out.println("\n");
        
        King bKingT5 = new King(new Position(0, 4), Color.BLACK, false);
        board.addPieceAt(bKingT5);
        
        King wKingT5 = new King(new Position(7, 4), Color.WHITE, false);
        board.addPieceAt(wKingT5);
        
        Rook bRook1T5 = new Rook(new Position(0, 7), Color.BLACK, false);
        board.addPieceAt(bRook1T5);
        
        Rook bRook2T5 = new Rook(new Position(0, 0), Color.BLACK, false);
        board.addPieceAt(bRook2T5);
        
        Rook wRook1T5 = new Rook(new Position(7, 7), Color.WHITE, false);
        board.addPieceAt(wRook1T5);
        
        Rook wRook2T5 = new Rook(new Position(7, 0), Color.WHITE, false);
        board.addPieceAt(wRook2T5);
        
        Bishop bBishopT5 = new Bishop(new Position(6, 6), Color.BLACK, false);
        board.addPieceAt(bBishopT5);
        
        Queen wQueenT5 = new Queen(new Position(2, 3), Color.WHITE, false);
        board.addPieceAt(wQueenT5);
        
        board.printBoard();
        System.out.println("\n");
        System.out.println("Test 5");
        
        check("can't castle though check with black king on side", false, canCastleKingSide(board.getAllPieceLegalMoves(bKingT5)));
        check("can't castle though check with black queen on side", false, canCastleQueenSide(board.getAllPieceLegalMoves(bKingT5)));
        check("can't castle though check with white king on side", false, canCastleKingSide(board.getAllPieceLegalMoves(wKingT5)));
        check("normal castle", true, canCastleQueenSide(board.getAllPieceLegalMoves(wKingT5)));
        System.out.println("\n");
        
        
		Game test6 = new Game(true); 
        Board board6 = test6.getBoard();
        board = board6;
        
        board.setPieceAt(7, 1, null);
        board.setPieceAt(7, 2, null);
        board.setPieceAt(7, 3, null);
        
        Move temp = new Move(board.getPieceAt(7, 4), new Position(7, 4), new Position(7,2), MoveType.CASTLE_QUEENSIDE);
        board.movePiece(temp);
        
        board.printBoard();
        System.out.println("\n");
        System.out.println("Test 6");
        
        
        check("can't castle after castling", false, canCastleKingSide(board.getAllPieceLegalMoves(board.getPieceAt(7, 2))));
        check("can't castle after castling", false, canCastleQueenSide(board.getAllPieceLegalMoves(board.getPieceAt(7, 2))));
        System.out.println("\n");
        

	}
}
