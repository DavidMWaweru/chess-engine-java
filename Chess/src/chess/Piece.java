package chess;

public abstract class Piece {

	protected Position pos;
	protected Color color;
	
	public Piece(Position pos, Color white) {
		this.pos = pos; 
		this.color = white;
	}
	
	public Position getPos() {
		return pos;
	}
	
	public void setPos(Position newPosition) {
		pos = newPosition;
	}
	
	public Color isWhite() {
		return color;
	}
	
	
	
	
	
	@Override
	public String toString() {
		return color.symbol() + getClass().getSimpleName() + "@" + pos;
	}
}
