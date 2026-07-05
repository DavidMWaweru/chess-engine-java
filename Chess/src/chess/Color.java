package chess;

public enum Color {
	WHITE,
	BLACK;
	
	public String symbol() {
		return this == WHITE ? "W" : "B";
	}
}
