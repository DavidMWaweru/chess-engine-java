package chess;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
	
	private static final int[][] DIRECTIONS = {
		    {-1,-1},
		    {-1, 1},
		    { 1,-1},
		    { 1, 1}
		};

	public Bishop(Position pos, Color color) {
		super(pos,color);
	}
	
	@Override
	public List<Move> getPseudoLegalMoves(Board board){
		return getSlidingMoves(board, DIRECTIONS);
	}
	
	
}
