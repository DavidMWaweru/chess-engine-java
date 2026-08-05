package chess;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
	
	private static final int[][] DIRECTIONS = {
		    {-1, 0}, { 1, 0}, { 0,-1}, { 0, 1},
		    {-1,-1}, {-1, 1}, { 1,-1}, { 1, 1}
		};

	public Queen(Position pos, Color color) {
		super(pos,color);
	}
	
	public Queen(Position pos, Color color, boolean hasMoved) {
		super(pos, color, hasMoved);
	}
	
	@Override
	public Piece copy() {
	    return new Queen(pos, color, hasMoved);
	}
	
	@Override
	public List<Move> getPseudoLegalMoves(Board board){
		return getSlidingMoves(board, DIRECTIONS);
	}
}
