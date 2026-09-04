package chess;

import java.util.List;

public class Game {
	private boolean live;
	private boolean whitesTurn;
	private Board board;
	
	public Game() {
		live = true;
		whitesTurn = true;
		board = new Board();
	}
	
	public Game(boolean setupBoard) {
		live = true;
		whitesTurn = true;
		board = new Board();
		board.setUpBoard();
	}
	
	public boolean whitesTurn() {
		return whitesTurn;
	}
	
	public boolean checkForCheckmate() {
		Color color = (whitesTurn) ? Color.WHITE : Color.BLACK;
		if(board.isKingInCheck(board.findKing(color))) {
			if(board.getAllLegalMoves(color).size() == 0) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkForStalemate() {
		Color color = (whitesTurn) ? Color.WHITE : Color.BLACK;
		if(!board.isKingInCheck(board.findKing(color))) {
			if(board.getAllLegalMoves(color).size() == 0) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkForProgress() {
		if(board.getNoProgressCounter() >= 100) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean makeMove(Move move) {
		Color color = (whitesTurn) ? Color.WHITE : Color.BLACK;
		List<Move> tempList = board.getAllLegalMoves(color);
		for(Move tempMove : tempList) {
			if(move.equals(tempMove)) {
				board.movePiece(tempMove);
				whitesTurn = !whitesTurn;
				return true;
			}
		}
		return false;
		
	}
	
	public Board getBoard() {
		return board;
	}
	
	public void startGame() {

	}
}
