package chess;

import java.util.ArrayList;
import java.util.List;

public class Board {
	public static final int SIZE = 8;
	
	public static final int[][] bishopDirections = {
		    {-1,-1},
		    {-1, 1},
		    { 1,-1},
		    { 1, 1}
		};
	
	public static final int[][] rookDirections = {
		    {-1, 0},
		    { 1, 0},
		    { 0,-1},
		    { 0, 1}
		};
	
	public static final int[][] knightDirections = {
		    { 2,-1}, { 2, 1}, { 1,-2}, { 1, 2},
		    {-2, 1}, {-2,-1}, {-1, 2}, {-1,-2}
		};
	
	public static final int[][] kingDirections = {
		    { 1, 1}, {-1,-1}, {-1, 1}, { 1,-1},
		    { 0, 1}, { 0,-1}, {-1, 0}, { 1, 0}
		};
	
	private Piece[][] board;	
	
	public Board() {
		board = new Piece[SIZE][SIZE];
	}
	
	public Piece[][] getBoard() {
		return board;
	}
	
	public void setBoard(Piece[][] newBoard) {
		board = newBoard;
	}
	
	public boolean validPos(Position pos) {
		if(pos.getCol() >= 0 && pos.getRow() >= 0 && pos.getCol() < SIZE && pos.getRow() < SIZE) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean validPos(int row, int col) {
		if(col >= 0 && row >= 0 && col < SIZE && row < SIZE) {
			return true;
		} else {
			return false;
		}
	}
	
	public void setUpBoard() {
		for(int i = 0; i < SIZE; i++) {
			board[1][i] = new Pawn(new Position(1,i), Color.BLACK);
		}
		board[0][1] = new Knight(new Position(0,1), Color.BLACK);
		board[0][6] = new Knight(new Position(0,6), Color.BLACK);
		board[0][2] = new Bishop(new Position(0,2), Color.BLACK);
		board[0][5] = new Bishop(new Position(0,5), Color.BLACK);
		board[0][0] = new Rook(new Position(0,0), Color.BLACK);
		board[0][7] = new Rook(new Position(0,7), Color.BLACK);
		board[0][3] = new Queen(new Position(0,3), Color.BLACK);
		board[0][4] = new King(new Position(0,4), Color.BLACK);
		
		for(int i = 0; i < SIZE; i++) {
			board[6][i] = new Pawn(new Position(6,i), Color.WHITE);
		}
		
		board[7][1] = new Knight(new Position(7,1), Color.WHITE);
		board[7][6] = new Knight(new Position(7,6), Color.WHITE);
		board[7][2] = new Bishop(new Position(7,2), Color.WHITE);
		board[7][5] = new Bishop(new Position(7,5), Color.WHITE);
		board[7][0] = new Rook(new Position(7,0), Color.WHITE);
		board[7][7] = new Rook(new Position(7,7), Color.WHITE);
		board[7][3] = new Queen(new Position(7,3), Color.WHITE);
		board[7][4] = new King(new Position(7,4), Color.WHITE);
	}
	
	public Board makeCopy() {
		Board copyBoard = new Board();
	    for (int row = 0; row < SIZE; row++) {
	        for (int col = 0; col < SIZE; col++) {
	        	Piece piece = board[row][col];
	        	
	        	if(piece != null) {
	        		copyBoard.setPieceAt(row, col, piece.copy());
	        	}
	        }
	    }
		return copyBoard;
	}
	
	public Piece getPieceAt(Position pos) {
		return board[pos.getRow()][pos.getCol()];
	}
	
	public Piece getPieceAt(int row, int col) {
		return board[row][col];
	}
	
	private void setPieceAt(Position pos, Piece piece) {
		board[pos.getRow()][pos.getCol()] = piece;
	}
	
	private void setPieceAt(int row, int col, Piece piece) {
		board[row][col] = piece;
	}
	
	public void addPieceAt(Piece p) {
		board[p.getPos().getRow()][p.getPos().getCol()] = p;
	}
	
	public List<Move> getAllPieceLegalMoves(Piece piece){
		List<Move> legalMoves = new ArrayList<>();
		List<Move> pseudoLegalMoves = piece.getPseudoLegalMoves(this);
		King king = findKing(piece.getColor());
		
		for (Move move : pseudoLegalMoves) {
			Board copy = makeCopy();
			copy.movePiece(move);
			if(!copy.isKingInCheck(king)) {
				legalMoves.add(move);
			}
		}
		return legalMoves;
	}
	
	
 
	
	public List<Move> getAllLegalMoves(Color color){
		List<Move> legalMoves = new ArrayList<>();
		
	    for (int row = 0; row < SIZE; row++) {
	        for (int col = 0; col < SIZE; col++) {

	            Piece piece = board[row][col];

	            if (piece != null && piece.getColor() == color) {
	            	List<Move> piecelegalMoves = getAllPieceLegalMoves(piece);
	            	for(Move move : piecelegalMoves) {
	            		legalMoves.add(move);
	            	}
	            	
	            }
	        }
	    }
	    
		return legalMoves;
	}
	
	private King findKing(Color color) {

	    for (int row = 0; row < SIZE; row++) {
	        for (int col = 0; col < SIZE; col++) {

	            Piece piece = board[row][col];

	            if (piece instanceof King && piece.getColor() == color) {
	                return (King) piece;
	            }
	        }
	    }

	    return null;
	}
	
	public boolean isKingInCheck(King king) {
		Color color = king.getColor();
		Position pos = king.getPos();
		
		int s = (color == Color.WHITE) ? -1 : 1;
		
		for(int i = 1; i > -2 ; i -= 2) {
	        int row = pos.getRow() + (1 * s);
	        int col = pos.getCol() + i;
	        if(validPos(row, col)) {
	            Piece piece = getPieceAt(row, col);
	            if (piece != null && piece.getColor() != color && piece instanceof Pawn) {
	                return true;
	            }
	        }
		}
		


	    for (int[] direction : rookDirections) {

	        int row = pos.getRow() + direction[0];
	        int col = pos.getCol() + direction[1];

	        while (validPos(row, col)) {
	            Piece piece = getPieceAt(row, col);
	            if (piece != null) {
	            	if(color != piece.getColor() && (piece instanceof Rook || piece instanceof Queen)) {
	            		return true;
	            	}
	                break;
	            }

	            row += direction[0];
	            col += direction[1];
	        }
	    }
	    
	    for (int[] direction : bishopDirections) {

	        int row = pos.getRow() + direction[0];
	        int col = pos.getCol() + direction[1];

	        while (validPos(row, col)) {
	            Piece piece = getPieceAt(row, col);
	            if (piece != null) {
	            	if(color != piece.getColor() && (piece instanceof Bishop || piece instanceof Queen)) {
	            		return true;
	            	}
	                break;
	            }

	            row += direction[0];
	            col += direction[1];
	        }
	    }
	    
	    
		 for (int[] direction : knightDirections) {

		        int row = pos.getRow() + direction[0];
		        int col = pos.getCol() + direction[1];

		        if(validPos(row, col)) {
		            Piece piece = getPieceAt(row, col);
		            if (piece != null && color != piece.getColor() && piece instanceof Knight) {
		            	return true;
		            }
		        }
		 }
		 
		 for (int[] direction : kingDirections) {

		        int row = pos.getRow() + direction[0];
		        int col = pos.getCol() + direction[1];

		        if(validPos(row, col)) {
		            Piece piece = getPieceAt(row, col);
		            if (piece != null && color != piece.getColor() && piece instanceof King) {
		            	return true;
		            }
		        }
		 }
		
		
		
		
		
		
		return false;
	}
	
	public void movePiece(Position endPos, Piece piece) {
		List<Move> pseudoLegalMoves = piece.getPseudoLegalMoves(this);
		for (Move move : pseudoLegalMoves) {
			if(move.getEndPos() == endPos) {
				//check for real legal moves
				if(true) {
					setPieceAt(move.getEndPos(), move.getPiece());
					setPieceAt(move.getStartPos(), null);
				}
				break;
			}
		}
	}
	
	public void movePiece(Move move) {
		Piece piece = getPieceAt(move.getPiece().getPos());
		Position endPos = move.getEndPos();
		
		setPieceAt(endPos, piece);
		setPieceAt(move.getStartPos(), null);
		piece.setPos(endPos);
		
		piece.moveCount = piece.moveCount + 1;
	}
	
	
	public void printBoard() {
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				System.out.print("[" + board[i][j] + "]");
			}
			System.out.println("");
		}
		
	}
}
