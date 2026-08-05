package chess;

import java.util.List;
import java.util.ArrayList;

public class Rook extends Piece {
	
	private static final int[][] DIRECTIONS = {
		    {-1, 0},
		    { 1, 0},
		    { 0,-1},
		    { 0, 1}
		};

	public Rook(Position pos, Color color) {
		super(pos,color);
	}
	
	public Rook(Position pos, Color color, boolean hasMoved) {
		super(pos, color, hasMoved);
	}
	
	@Override
	public Piece copy() {
	    return new Rook(pos, color, hasMoved);
	}
	
	@Override
	public List<Move> getPseudoLegalMoves(Board board){
		return getSlidingMoves(board, DIRECTIONS);
	}
	
}
