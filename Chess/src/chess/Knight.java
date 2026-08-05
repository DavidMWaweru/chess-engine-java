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
	
	public Knight(Position pos, Color color, boolean hasMoved) {
		super(pos, color, hasMoved);
	}
	
	@Override
	public Piece copy() {
	    return new Knight(pos, color, hasMoved);
	}
	
	@Override
	public List<Move> getPseudoLegalMoves(Board board){
		return getNonSlidingMoves(board, DIRECTIONS);
	}
	
}
