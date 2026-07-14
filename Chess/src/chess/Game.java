package chess;

public class Game {
	private boolean live;
	private boolean whitesTurn;
	private Board board;
	
	public Game() {
		live = true;
		whitesTurn = true;
		board = new Board();
		board.setUpBoard();
	}
	
	public Board getBoard() {
		return board;
	}
	
}
