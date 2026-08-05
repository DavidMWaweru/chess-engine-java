package chess;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
	
	private static final int[][] DIRECTIONS = {
		    { 1, 1}, {-1,-1}, {-1, 1}, { 1,-1},
		    { 0, 1}, { 0,-1}, {-1, 0}, { 1, 0}
		};

	public King(Position pos, Color color) {
		super(pos,color);
	}
	
	public King(Position pos, Color color, boolean hasMoved) {
		super(pos, color, hasMoved);
	}
	
	@Override
	public Piece copy() {
	    return new King(pos, color, hasMoved);
	}
	

	

	public boolean canPseudoCastle(Board board, boolean kingSide) {
		int r = (color == Color.WHITE) ? 7 : 0;
		if(!hasMoved) {
			if(kingSide) {
				if(board.getPieceAt(r,7) instanceof Rook) {
					Rook Rook = (Rook) board.getPieceAt(r,7);
					if(!Rook.hasMoved()) {
						if (spaceBetweenKingAndRookClear(board,kingSide,color)) {
							return true;
						}
					}			
				}
			} else {
				if(board.getPieceAt(r,0) instanceof Rook) {
					Rook Rook = (Rook) board.getPieceAt(r,0);
					if(!Rook.hasMoved()) {
						if (spaceBetweenKingAndRookClear(board,kingSide,color)) {
							return true;
						}
					}			
				}
			}
		}
		return false;
	}
	
	private boolean spaceBetweenKingAndRookClear(Board board, boolean kingSide, Color color) {
		int c = (kingSide == true) ? 5 : 3;
		int r = (color == Color.WHITE) ? 7 : 0;
		if(kingSide) {
			if(board.getPieceAt(r,c) == null && board.getPieceAt(r,c + 1) == null) {
				return true;
			}
		} else {
			if(board.getPieceAt(r,c) == null && board.getPieceAt(r,c - 1) == null && board.getPieceAt(r,c - 2) == null) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public List<Move> getPseudoLegalMoves(Board board){
		List<Move> moves = getNonSlidingMoves(board, DIRECTIONS);
		int r = (color == Color.WHITE) ? 7 : 0;
		if(canPseudoCastle(board, true)) {
			Move temp = new Move(this, pos,new Position(r,6), MoveType.CASTLE_KINGSIDE);
			moves.add(temp);
		}
		
		if(canPseudoCastle(board, false)) {
			Move temp = new Move(this, pos, new Position(r,2), MoveType.CASTLE_QUEENSIDE);
			moves.add(temp);
		}
		
		return moves;
	}
}
