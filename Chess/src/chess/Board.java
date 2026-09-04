package chess;

import java.util.ArrayList;
import java.util.List;

public class Board {
	public static final int SIZE = 8;
	public static final String BG_YELLOW = "\u001B[43m";
	public static final String RESET  = "\u001B[0m";
	
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
	private Move lastMove;
	private int noProgressCounter;
	
	public Move getLastMove() {
		return lastMove;
	}
	
	public void setLastMove(Move lastMove) {
		this.lastMove = lastMove;
	}
	
	public Board() {
		board = new Piece[SIZE][SIZE];
		noProgressCounter = 0;
	}
	
	public Piece[][] getBoard() {
		return board;
	}
	
	public void setBoard(Piece[][] newBoard) {
		board = newBoard;
	}
	
	public int getNoProgressCounter() {
		return noProgressCounter;
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
		copyBoard.setLastMove(lastMove);
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
	
	public void setPieceAt(Position pos, Piece piece) {
		board[pos.getRow()][pos.getCol()] = piece;
	}
	
	public void setPieceAt(int row, int col, Piece piece) {
		board[row][col] = piece;
	}
	
	public void addPieceAt(Piece p) {
		board[p.getPos().getRow()][p.getPos().getCol()] = p;
	}
	
	public boolean checkForSufficientMaterial() {
		List<Piece> knightsList = new ArrayList<>();
		List<Piece> BishopsList = new ArrayList<>();
	    for (int row = 0; row < SIZE; row++) {
	        for (int col = 0; col < SIZE; col++) {

	            Piece piece = board[row][col];
	            if(piece != null && !(piece instanceof King)) {
	            	if(piece instanceof Bishop) {
	            		BishopsList.add((Bishop) piece);
	            	} else if (piece instanceof Knight) {
	            		knightsList.add((Knight) piece);
	            	} else {
	            		return true;
	            	}
	            }
	        }
	    }
	    if(knightsList.size() + BishopsList.size() <= 2) {
	    	if(knightsList.size() + BishopsList.size() <= 1) {
	    		return false;
	    	}
	    	if(BishopsList.size() != 2) {
	    		return true;
	    	}
	    	
	    	//last check is if the bishops are same color or not check by adding rows and cols (should work i think)
	    	
	    	
	    	if(BishopsList.size() == 2) {
	    		Bishop b1 = (Bishop) BishopsList.get(0);
	    		Bishop b2 = (Bishop) BishopsList.get(1);
	    		if(b1.getSqaureColor() == b2.getSqaureColor()) {
	    			return false;
	    		} else {
	    			return true;
	    		}
	    	}
	    	
	    }
		return true;
	}
	
	private boolean canCastleLegal(Move move) {
		List<Move> movesToCheck = new ArrayList<>();
		King king = (King) move.getPiece();
		int row = king.getPos().getRow();
		
		if(isKingInCheck(king)){
			return false;
		}
		
		movesToCheck.add(move);
		if(move.getMoveType() == MoveType.CASTLE_KINGSIDE) {
			movesToCheck.add(new Move(king,king.getPos(),new Position(row,5),MoveType.NORMAL));
		} else {
			movesToCheck.add(new Move(king,king.getPos(),new Position(row,3),MoveType.NORMAL));
		}
		
		for (Move tempMove : movesToCheck) {
			Board copy = makeCopy();
			King copyKing = (King) copy.getPieceAt(row, 4);
			copy.movePiece(tempMove);
			if(copy.isKingInCheck(copyKing)) {
				return false;
			}
		}
		
		return true;
	}
	
	public List<Move> getAllPieceLegalMoves(Piece piece){
		List<Move> legalMoves = new ArrayList<>();
		List<Move> pseudoLegalMoves = piece.getPseudoLegalMoves(this);
		
		
		
		for (Move move : pseudoLegalMoves) {
			if(move.getMoveType() != MoveType.CASTLE_KINGSIDE && move.getMoveType() != MoveType.CASTLE_QUEENSIDE) {
				Board copy = makeCopy();
				copy.movePiece(move);
				
				King copyKing = copy.findKing(piece.getColor());
				
				if(!copy.isKingInCheck(copyKing)) {
					legalMoves.add(move);
				}
			} else {
				if(canCastleLegal(move)) {
					legalMoves.add(move);
				}
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
	
	public King findKing(Color color) {

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
	
	
	public void movePiece(Move move) {
		Piece piece = getPieceAt(move.getPiece().getPos());
		Position endPos = move.getEndPos();
		noProgressCounter++;
		
		if(move.getMoveType() == MoveType.PAWN) {
			noProgressCounter = 0;
		}
		
		if(move.getMoveType() == MoveType.CAPTURE) {
			noProgressCounter = 0;
			
		}
		
		if(move.getMoveType() == MoveType.CASTLE_KINGSIDE) {
			int row = endPos.getRow();
			Piece rook = getPieceAt(row, 7);
			setPieceAt(row, 5, rook);
			setPieceAt(row, 7, null);
			rook.setPos(new Position(row, 5));
		} else if(move.getMoveType() == MoveType.CASTLE_QUEENSIDE) {
			int row = endPos.getRow();
			Piece rook = getPieceAt(row, 0);
			setPieceAt(row, 3, rook);
			setPieceAt(row, 0, null);
			rook.setPos(new Position(row, 3));
		}
		
		
		setPieceAt(endPos, piece);
		setPieceAt(move.getStartPos(), null);
		piece.setPos(endPos);
		
		if(move.getMoveType() == MoveType.EN_PASSANT) {
			noProgressCounter = 0;
			int s = (move.getPiece().getColor() == Color.WHITE) ? -1 : 1;
			int row = move.getEndPos().getRow() + s;
			int col = move.getEndPos().getCol();
			setPieceAt(row, col, null);
			
		}
		
		piece.hasMoved = true;
		
		//checks for pawn promotion 
		if(move.getMoveType() == MoveType.PROMOTION || move.getMoveType() == MoveType.PROMOTION_CAPTURE) {
			noProgressCounter = 0;
			Pawn p = (Pawn) piece;
			setPieceAt(endPos, p.promote(PieceType.QUEEN));
		}
		lastMove = move;
	}
	
	
	
	
	public void printBoard() {
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				System.out.print("[" + board[i][j] + "]");
			}
			System.out.println("");
		}
		
	}
	
	public void printBoardForPlayer() {
		
		int startRow = 0;
		int startCol = 0;
		int endRow = 0;
		int endCol = 0;
		if(lastMove != null) {
			startRow = lastMove.getStartPos().getRow();
			startCol = lastMove.getStartPos().getCol();
			endRow = lastMove.getEndPos().getRow();
			endCol = lastMove.getEndPos().getCol();
		}

		System.out.println("    a  b  c  d  e  f  g  h");
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				if(j == 0) {
					System.out.print((8 - i) + " | ");
				}
				if( lastMove != null && ((i == startRow && j == startCol) || (i == endRow && j == endCol))) {
					if(board[i][j] != null) {
						System.out.print(BG_YELLOW + board[i][j].getSymbol() + RESET + " ");
					} else {
						System.out.print(BG_YELLOW + ".." + RESET + " ");
					}
				} else {
					if(board[i][j] != null) {
						System.out.print(board[i][j].getSymbol() + " ");
					} else {
						System.out.print(".." + " ");
					}
				}
				if(j == 7) {
					System.out.print("| " + (8 - i));
				}
			}
			System.out.println("");
		}
		System.out.println("    a  b  c  d  e  f  g  h");
		
	}
}
