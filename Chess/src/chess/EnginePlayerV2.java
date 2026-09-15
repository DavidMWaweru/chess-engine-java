package chess;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class EnginePlayerV2 implements Player {
	
	private static final int PAWN_VALUE = 100;
    private static final int KNIGHT_VALUE = 300;
    private static final int BISHOP_VALUE = 300;
    private static final int ROOK_VALUE = 500;
    private static final int QUEEN_VALUE = 900;
    private static final int KING_VALUE = 0;
    private static final int ALPHA = Integer.MIN_VALUE;
    private static final int BETA =Integer.MAX_VALUE;
    private static final int DEPTH = 6;
    
    private static final int[][] KNIGHT_TABLE = {
    	    {-10, -5,  0,  0,  0,  0, -5, -10},
    	    { -5,  0,  5,  5,  5,  5,  0,  -5},
    	    {  0,  5, 10, 15, 15, 10,  5,   0},
    	    {  0,  5, 15, 20, 20, 15,  5,   0},
    	    {  0,  5, 15, 20, 20, 15,  5,   0},
    	    {  0,  5, 10, 15, 15, 10,  5,   0},
    	    { -5,  0,  5,  5,  5,  5,  0,  -5},
    	    {-10, -5,  0,  0,  0,  0, -5, -10}
    	};
    
    private static final int[][] BISHOP_TABLE = {
    	    {-20, -10, -10, -10, -10, -10, -10, -20},
    	    {-10,  10,   0,   0,   0,   0,  10, -10},
    	    {-10,  10,  10,  10,  10,  10,  10, -10},
    	    {-10,   0,  10,  10,  10,  10,   0, -10},
    	    {-10,   5,   5,  10,  10,   5,   5, -10},
    	    {-10,   0,   5,  10,  10,   5,   0, -10},
    	    {-10,   0,   0,   0,   0,   0,   0, -10},
    	    {-20, -10, -10, -10, -10, -10, -10, -20}
    	};
    
    private static final int[][] PAWN_TABLE = {
    	    {  0,   0,   0,   0,   0,   0,   0,   0},
    	    {  5,   5,   5, -25, -25,   5,   5,   5},
    	    {  5,   5,   5,  10,  10,   5,   5,   5},
    	    {  5,   5,   5,  25,  25,   5,   5,   5},
    	    {  5,   5,  10,  30,  30,  10,   5,   5},
    	    { 10,  10,  25,  35,  35,  25,  10,  10},
    	    { 50,  50,  50,  50,  50,  50,  50,  50},
    	    {  0,   0,   0,   0,   0,   0,   0,   0}
    };
    
    private static final int[][] ROOK_TABLE = {
    	    {  0,   0,   0,   5,   5,   0,   0,   0},
    	    { -5,   0,   0,   5,   5,   0,   0,  -5},
    	    { -5,   0,   0,   0,   0,   0,   0,  -5},
    	    { -5,   0,   0,   0,   0,   0,   0,  -5},
    	    { -5,   0,   0,   0,   0,   0,   0,  -5},
    	    { -5,   0,   0,   0,   0,   0,   0,  -5},
    	    {  5,  10,  10,  10,  10,  10,  10,   5},
    	    {  0,   0,   0,   0,   0,   0,   0,   0}
    	};
    
    private static final int[][] QUEEN_TABLE = {
    	    {-20, -10, -10,  -5,  -5, -10, -10, -20},
    	    {-10,   0,   0,   0,   0,   0,   0, -10},
    	    {-10,   0,   5,   5,   5,   5,   0, -10},
    	    { -5,   0,   5,   5,   5,   5,   0,  -5},
    	    {  0,   0,   5,   5,   5,   5,   0,  -5},
    	    {-10,   5,   5,   5,   5,   5,   0, -10},
    	    {-10,   0,   5,   0,   0,   0,   0, -10},
    	    {-20, -10, -10,  -5,  -5, -10, -10, -20}
    	};

	
Color color;
	
	public EnginePlayerV2(Color color){
		this.color = color;
	}
	
	public Move getMove(Game game) {
		//add picking random moves with they are all equal
		boolean whiteToPlay = game.whitesTurn();
		Color color = (whiteToPlay) ? Color.WHITE : Color.BLACK;
		List<Move> tempList = orderMoves(game.getBoard().getAllLegalMoves(color));
		ArrayList<Move> bestMoves = new ArrayList<>();
		ArrayList<Integer> evalList = new ArrayList<>();
		
		if(whiteToPlay) {
			int eval = Integer.MIN_VALUE;
			int maxEval = Integer.MIN_VALUE;
			for(Move temp : tempList) {
				Board copyBoard = game.getBoard().makeCopy();
				copyBoard.movePiece(temp);
				eval = minimax(copyBoard, DEPTH - 1, ALPHA, BETA, !whiteToPlay);
				if(maxEval < eval) {
					bestMoves.clear();
					evalList.clear();
					maxEval = eval;
					bestMoves.add(temp);
					evalList.add(eval);
				} else if(maxEval == eval) {
					bestMoves.add(temp);
					evalList.add(eval);
				}
			}
			System.out.println("Best move eval: " + maxEval);
		} else {
			int eval = Integer.MAX_VALUE;
			int minEval = Integer.MAX_VALUE;
			for(Move temp : tempList) {
				Board copyBoard = game.getBoard().makeCopy();
				copyBoard.movePiece(temp);
				eval = minimax(copyBoard, DEPTH - 1, ALPHA, BETA, !whiteToPlay);
				if(minEval > eval) {
					bestMoves.clear();
					evalList.clear();
					minEval = eval;
					bestMoves.add(temp);
					evalList.add(eval);
				} else if(minEval == eval) {
					bestMoves.add(temp);
					evalList.add(eval);
				}
			}
	        System.out.println("Best move eval: " + minEval);
		}
		
		System.out.println("Current board eval: " + evaluatePosition(game.getBoard()));
		if(bestMoves.size() == 1) {
			System.out.println("eval of move: " + evalList.getFirst());
			return bestMoves.getFirst();
		} else {
			Random r = new Random();
			int random = r.nextInt(bestMoves.size());
			System.out.println("eval of move: " + evalList.get(random));
			return bestMoves.get(random);
		}
	}
	
	private List<Move> orderMoves(List<Move> moves) {
	    List<Move> two = new ArrayList<>();
	    List<Move> four = new ArrayList<>();
	    List<Move> six = new ArrayList<>();
	    List<Move> seven = new ArrayList<>();
	    List<Move> nine = new ArrayList<>();
	    List<Move> eleven = new ArrayList<>();
	    
	    for (Move move : moves) {
		    switch (move.getMoveType()) {
		        case PROMOTION_CAPTURE:
		        case PROMOTION:
		            if(move.givesCheck()) {
		            	eleven.add(move);
		            } else {
		            	six.add(move);
		            }
		            break;
		        case CAPTURE:
		        case EN_PASSANT:
		            if(move.givesCheck()) {
		            	nine.add(move);
		            } else {
		            	four.add(move);
		            }
		            break;
		        case CASTLE_KINGSIDE:
		        case CASTLE_QUEENSIDE:
		        case NORMAL:
		        case PAWN:
		            if(move.givesCheck()) {
		            	seven.add(move);
		            } else {
		            	two.add(move);
		            }
		            break;
		        default:
		            if(move.givesCheck()) {
		            	seven.add(move);
		            } else {
		            	two.add(move);
		            }
		            break;
		    }
	    }
	    
	    eleven.addAll(nine);
	    eleven.addAll(seven);
	    eleven.addAll(six);
	    eleven.addAll(four);
	    eleven.addAll(two);
	    
		return eleven;
	}
	
	
	public int minimax(Board board, int depth,int alpha, int beta, boolean whiteToPlay) {
		if(board.checkForCheckmate(whiteToPlay)) {
			if(whiteToPlay) {
				return -100000 + depth;
			} else {
				return 100000 - depth;
			}
		} else if(board.checkForStalemate(whiteToPlay)) {
			return 0;
		} else if (!board.checkForSufficientMaterial()) {
			return 0;
		} else if(board.checkForProgress()) {
			return 0;
		} 
		if(depth == 0) {
			return evaluatePosition(board);
		}
		
		Color color = (whiteToPlay) ? Color.WHITE : Color.BLACK;
		List<Move> tempList = board.getAllLegalMoves(color);

		if(whiteToPlay) {
			int eval = Integer.MIN_VALUE;
			int maxEval = Integer.MIN_VALUE;
			for(Move temp : tempList) {
				Board copyBoard = board.makeCopy();
				copyBoard.movePiece(temp);
				eval = minimax(copyBoard, depth - 1, alpha, beta, !whiteToPlay);
				maxEval = Math.max(maxEval, eval);
				alpha = Math.max(alpha, eval);
				if(beta <= alpha)
					break;
			}
			return maxEval;
		} else {
			int eval = Integer.MAX_VALUE;
			int minEval = Integer.MAX_VALUE;
			for(Move temp : tempList) {
				Board copyBoard = board.makeCopy();
				copyBoard.movePiece(temp);
				eval = minimax(copyBoard, depth - 1, alpha, beta, !whiteToPlay);
				minEval = Math.min(minEval, eval);
				beta = Math.min(beta, eval);
				if(beta <= alpha)
					break;
			}
			return minEval;
			
		}
	}
	
	public int evaluatePosition(Board board) {
		int i;
		int eval = 0;
		Piece[][] realBoard = board.getBoard();
	    for (int row = 0; row < 8; row++) {
	        for (int col = 0; col < 8; col++) {
	            Piece piece = realBoard[row][col];
	            
	            int tableRow;
	            if (piece == null) {
	                continue;
	            }
	            
	            if(piece.getColor() == Color.WHITE) {
	            	i = 1;
	            	tableRow = row;
	            } else {
	            	i = -1;
	            	tableRow = 7 - row;
	            }
	            
	            
	            if(piece instanceof Pawn) {
	            	eval += (i * PAWN_VALUE); 
	            	eval += (i * PAWN_TABLE[tableRow][col]);
	            } else if (piece instanceof Bishop) {
	            	eval += (i * BISHOP_VALUE);
	            	eval += (i * BISHOP_TABLE[tableRow][col]);
	            } else if (piece instanceof Knight) {
	            	eval += (i * KNIGHT_VALUE);
	            	eval += (i * KNIGHT_TABLE[tableRow][col]);
	            } else if (piece instanceof Rook) {
	            	eval += (i * ROOK_VALUE);
	            	eval += (i * ROOK_TABLE[tableRow][col]);
	            } else if (piece instanceof Queen) {
	            	eval += (i * QUEEN_VALUE);
	            	eval += (i * QUEEN_TABLE[tableRow][col]);
	            } else if (piece instanceof King) {
	            	eval += (i * KING_VALUE);
	            } else {
	            	//bug
	            }
	            	
	            
	        }
	    }
		return eval;
	}
}
