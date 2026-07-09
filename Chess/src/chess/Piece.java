package chess;
import java.util.List;
import java.util.ArrayList;

public abstract class Piece {

	protected Position pos;
	protected Color color;
	protected int moveCount;
	
	public Piece(Position pos, Color white) {
		moveCount = 0;
		this.pos = pos; 
		this.color = white;
	}
	
	public Position getPos() {
		return pos;
	}
	
	public void setPos(Position newPosition) {
		pos = newPosition;
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
	                moves.add(new Move(this, pos, endPos));
	            } else {
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
		                moves.add(new Move(this, pos, endPos));
		            } else {
		                break;
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
