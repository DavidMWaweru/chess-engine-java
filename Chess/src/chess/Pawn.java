package chess;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
	

	public Pawn(Position pos, Color color) {
		super(pos,color);
	}
	
	public Pawn(Position pos, Color color, int moveCount) {
		super(pos, color, moveCount);
	}
	
	@Override
	public Piece copy() {
	    return new Pawn(pos, color, moveCount);
	}
	
	@Override
	public List<Move> getPseudoLegalMoves(Board board){
		List<Move> moves = new ArrayList<>();
		int s = (color == Color.WHITE) ? -1 : 1;
		
		Position endpos = new Position(pos.getRow() + (1 * s), pos.getCol());
		if (board.getPieceAt(endpos) == null) {
			Move temp = new Move(this, pos, endpos);
			moves.add(temp);
		}
		
		if(moveCount == 0) {
			endpos = new Position(pos.getRow() + (2 * s), pos.getCol());
			if (board.getPieceAt(endpos) == null) {
				Move temp = new Move(this, pos, endpos);
				moves.add(temp);
			}
		}
		
		for(int i = 1; i > -2 ; i -= 2) {
	        int row = pos.getRow() + (1 * s);
	        int col = pos.getCol() + i;
	        if(board.validPos(new Position(row, col))) {
	        	Position endPos = new Position(row, col);
	            Piece piece = board.getPieceAt(endPos);
	            if (piece != null && piece.getColor() != color) {
	                moves.add(new Move(this, pos, endPos));
	            }
	        }
		}
		
		return moves;
	}
	
	
	
	

}
