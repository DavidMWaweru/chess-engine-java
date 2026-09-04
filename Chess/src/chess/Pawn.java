package chess;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
	

	public Pawn(Position pos, Color color) {
		super(pos,color);
	}
	
	public Pawn(Position pos, Color color, boolean hasMoved) {
		super(pos, color, hasMoved);
	}
	
	@Override
	public Piece copy() {
	    return new Pawn(pos, color, hasMoved);
	}
	
	public Piece promote(PieceType p) {
	    switch (p) {
	        case QUEEN:
	        	return new Queen(pos, color, hasMoved);

	        case ROOK:
	        	return new Rook(pos, color, hasMoved);

	        case BISHOP:
	        	return new Bishop(pos, color, hasMoved);

	        case KNIGHT:
	        	return new Knight(pos, color, hasMoved);

	        default:
	            return this;
	    }
	}
	
	@Override
	public List<Move> getPseudoLegalMoves(Board board){
		List<Move> moves = new ArrayList<>();
		int s = (color == Color.WHITE) ? -1 : 1;
		
		Position endpos = new Position(pos.getRow() + (1 * s), pos.getCol());
		if (board.getPieceAt(endpos) == null) {
			if(endpos.getRow() == 0 || endpos.getRow() == 7) {
				Move temp = new Move(this, pos, endpos, MoveType.PROMOTION);
				moves.add(temp);
			} else {
				Move temp = new Move(this, pos, endpos, MoveType.PAWN);
				moves.add(temp);
			}
		}
		
		
		if(!hasMoved) {
			Position endpos1 = new Position(pos.getRow() + (2 * s), pos.getCol());
			Position endpos2 = new Position(pos.getRow() + (1 * s), pos.getCol());
			if (board.getPieceAt(endpos1) == null && board.getPieceAt(endpos2) == null) {
				Move temp = new Move(this, pos, endpos1, MoveType.PAWN);
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
	            	if(endPos.getRow() == 0 || endPos.getRow() == 7) {
	            		moves.add(new Move(this, pos, endPos, MoveType.PROMOTION_CAPTURE));
	            	} else {
	            		moves.add(new Move(this, pos, endPos, MoveType.CAPTURE));
	            	}
	                
	            }
	        }
		}
		
		//checks for en passant
		if(board.getLastMove() != null && board.getLastMove().getPiece() instanceof Pawn) {
			if(Math.abs(board.getLastMove().getStartPos().getRow() - board.getLastMove().getEndPos().getRow()) == 2 && board.getLastMove().getStartPos().getCol() ==
					board.getLastMove().getEndPos().getCol()) {
				for(int i = 1; i > -2 ; i -= 2) {
			        int row = pos.getRow();
			        int col = pos.getCol() + i;
			        if(board.validPos(new Position(row + s, col)) && board.validPos(new Position(row, col))) {
			        	Position endPos = new Position(row + s, col);
			        	Position endPiecePos = new Position(row, col);
			            Piece piece = board.getPieceAt(endPiecePos);
			            if (piece instanceof Pawn && piece.getColor() != color && piece.getPos().equals(board.getLastMove().getEndPos())) {
			                moves.add(new Move(this, pos, endPos, MoveType.EN_PASSANT));
			            }
			        }
				}
			}
			
		}
		
		return moves;
	}
	
	
	
	

}
