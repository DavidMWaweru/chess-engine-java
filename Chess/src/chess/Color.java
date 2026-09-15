package chess;

public enum Color {
	WHITE,
	BLACK;
	
	public String symbol() {
		return this == WHITE ? "W" : "B";
	}
	
	public Color opposite() {
	    return this == WHITE ? BLACK : WHITE;
	}
}
