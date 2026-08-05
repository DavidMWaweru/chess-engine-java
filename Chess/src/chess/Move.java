package chess;

public class Move {

	private Position startPos;
	private Position endPos;
	private Piece piece;
	private MoveType moveType;
	
	public Move(Piece piece, Position startPos, Position endPos, MoveType moveType) {
		this.moveType = moveType;
		this.piece = piece;
		this.startPos = startPos;
		this.endPos = endPos;
		
	}
	
	public Move(Position startPos, Position endPos) {
		this.startPos = startPos;
		this.endPos = endPos;
		
	}
	
	public MoveType getMoveType() {
		return moveType;
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
	
	@Override
	public boolean equals(Object obj) {
	    if (!(obj instanceof Move)) {
	        return false;
	    }

	    Move other = (Move) obj;

	    return startPos.equals(other.startPos)
	        && endPos.equals(other.endPos);
	}
	
	
	public String toString() {
		if(moveType == MoveType.CASTLE_KINGSIDE) {
			return "O-O";
		} else if (moveType == MoveType.CASTLE_QUEENSIDE) {
			return "O-O-O";
		}
		return "" + piece + " from " + startPos + " to " + endPos;
	}
}
