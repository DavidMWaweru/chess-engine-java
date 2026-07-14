package chess;

public class Move {

	private Position startPos;
	private Position endPos;
	private Piece piece;
	
	public Move(Piece piece, Position startPos, Position endPos) {
		this.piece = piece;
		this.startPos = startPos;
		this.endPos = endPos;
		
	}
	
	public Position getEndPos() {
		return endPos;
	}
	
	public Position getStartPos() {
		return startPos;
	}
	
	public Piece getPiece() {
		return piece;
	}
	
	
	public String toString() {
		return "" + piece + " from " + startPos + " to " + endPos;
	}
}
