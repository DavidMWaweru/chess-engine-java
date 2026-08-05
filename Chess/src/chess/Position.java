package chess;



//this class must be immutable
public class Position {
	private int row;
	private int col;
	
	public Position (int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public String convertToNoation() {
		char file = (char) ('a' + col);
		int rank = 8 - row;
		return "" + file + rank;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (!(obj instanceof Position)) {
	        return false;
	    }

	    Position other = (Position) obj;

	    return row == other.row
	        && col == other.col;
	}
	
	@Override
	public String toString() {
		return convertToNoation();
	}
}
