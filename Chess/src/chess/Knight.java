package chess;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
	
	private static final int[][] DIRECTIONS = {
		    { 2,-1}, { 2, 1}, { 1,-2}, { 1, 2},
		    {-2, 1}, {-2,-1}, {-1, 2}, {-1,-2}
		};

	public Knight(Position pos, Color color) {
		super(pos,color);
	}
	
	public Knight(Position pos, Color color, int moveCount) {
		super(pos, color, moveCount);
	}
	
	@Override
	public Piece copy() {
	    return new Knight(pos, color, moveCount);
	}
	
	@Override
	public List<Move> getPseudoLegalMoves(Board board){
		return getNonSlidingMoves(board, DIRECTIONS);
	}
	
}
