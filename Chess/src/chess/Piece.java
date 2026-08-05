package chess;
import java.util.List;
import java.util.ArrayList;

public abstract class Piece {

	protected Position pos;
	protected Color color;
	protected boolean hasMoved;
	
	public Piece(Position pos, Color white) {
		hasMoved = false;
		this.pos = pos; 
		this.color = white;
	}
	
	public Piece(Position pos, Color white, boolean hasMoved) {
		this.hasMoved = hasMoved;
		this.pos = pos; 
		this.color = white;
	}
	
	public abstract Piece copy();
	
	public boolean hasMoved() {
		return hasMoved;
	}
	
	public void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}
	
	public Position getPos() {
		return pos;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setPos(Position newPosition) {
		pos = newPosition;
	}
	
	public String getSymbol() {
		char colorLetter;
		char pieceLetter = 'x';
		if(color == Color.WHITE) {
			colorLetter = 'W';
		}  else {
			colorLetter = 'B';
		}
		
		if (this instanceof Pawn)   pieceLetter = 'P';
		if (this instanceof Knight) pieceLetter = 'N';
		if (this instanceof Bishop) pieceLetter = 'B';
		if (this instanceof Rook)   pieceLetter = 'R';
		if (this instanceof Queen)  pieceLetter = 'Q';
		if (this instanceof King)   pieceLetter = 'K';
		
		return "" + colorLetter + pieceLetter;
	}
	
	public abstract List<Move> getPseudoLegalMoves(Board board);
	
	protected List<Move> getSlidingMoves(Board board, int[][] directions) {
	    List<Move> moves = new ArrayList<>();

	    for (int[] direction : directions) {

	        int row = pos.getRow() + direction[0];
	        int col = pos.getCol() + direction[1];

	        while (board.validPos(new Position(row, col))) {
	            Position endPos = new Position(row, col);
	            Piece piece = board.getPieceAt(endPos);
	            if (piece == null) {
	                moves.add(new Move(this, pos, endPos, MoveType.NORMAL));
	            } else {
	            	if(color != piece.getColor()) {
	            		moves.add(new Move(this, pos, endPos, MoveType.CAPTURE));
	            	}
	                break;
	            }

	            row += direction[0];
	            col += direction[1];
	        }
	    }

	    return moves;
	}
	
	protected List<Move> getNonSlidingMoves(Board board, int[][] directions) {
		List<Move> moves = new ArrayList<>();
		 for (int[] direction : directions) {

		        int row = pos.getRow() + direction[0];
		        int col = pos.getCol() + direction[1];

		        if(board.validPos(new Position(row, col))) {
		            Position endPos = new Position(row, col);
		            Piece piece = board.getPieceAt(endPos);
		            if (piece == null) {
		                moves.add(new Move(this, pos, endPos, MoveType.NORMAL));
		            } else if(color != piece.getColor()) {
	            		moves.add(new Move(this, pos, endPos, MoveType.CAPTURE));
	            	}
		        }
		 }
		return moves;
		
	}
	
	
	
	
	
	@Override
	public String toString() {
		return color.symbol() + getClass().getSimpleName() + "@" + pos;
	}
}
