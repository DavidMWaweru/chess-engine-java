package chess;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
	

	public Pawn(Position pos, Color color) {
		super(pos,color);
	}
	
	@Override
	public List<Move> getPseudoLegalMoves(Board board){
		List<Move> moves = new ArrayList<>();
		int s;
		if(color == Color.WHITE) {
			s = -1;
		} else {
			s = 1;
		}
		
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
		
		return moves;
	}
	
	
	
	

}
