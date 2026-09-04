package chess;

import java.util.List;
import java.util.Random;

public class EnginePlayerV2 implements Player {
	
	private static final int PAWN_VALUE = 100;
    private static final int KNIGHT_VALUE = 300;
    private static final int BISHOP_VALUE = 300;
    private static final int ROOK_VALUE = 500;
    private static final int QUEEN_VALUE = 900;
    private static final int KING_VALUE = 0;

	
Color color;
	
	public EnginePlayerV2(Color color){
		this.color = color;
	}
	
	public Move getMove(Game game) {

		return;
	}
	
	public int evaluatePosition(Board board) {
		int i;
		int eval = 0;
		Piece[][] realBoard = board.getBoard();
	    for (int row = 0; row < 8; row++) {
	        for (int col = 0; col < 8; col++) {
	            Piece piece = realBoard[row][col];
	            
	            if(piece.getColor() == Color.WHITE) {
	            	i = 1;
	            } else {
	            	i = -1;
	            }
	            
	            if(piece instanceof Pawn) {
	            	eval += (i * PAWN_VALUE); 
	            } else if (piece instanceof Bishop) {
	            	eval += (i * BISHOP_VALUE);
	            } else if (piece instanceof Knight) {
	            	eval += (i * KNIGHT_VALUE);
	            } else if (piece instanceof Rook) {
	            	eval += (i * ROOK_VALUE);
	            } else if (piece instanceof Queen) {
	            	eval += (i * QUEEN_VALUE);
	            } else if (piece instanceof King) {
	            	eval += (i * KING_VALUE);
	            } else {
	            	//bug
	            }
	            	
	            
	        }
	    }
		return eval;
	}
}
