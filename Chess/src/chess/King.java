package chess;

import java.util.List;

public class King extends Piece {
	
	private static final int[][] DIRECTIONS = {
		    { 1, 1}, {-1,-1}, {-1, 1}, { 1,-1},
		    { 0, 1}, { 0,-1}, {-1, 0}, { 1, 0}
		};

	public King(Position pos, Color color) {
		super(pos,color);
	}
	
	
	@Override
	public List<Move> getPseudoLegalMoves(Board board){
		return getNonSlidingMoves(board, DIRECTIONS);
	}
}
