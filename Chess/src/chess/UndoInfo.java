package chess;

public class UndoInfo {

    // Captured piece information
    private Piece capturedPiece;
    private Position capturedPosition;

    // Castling rights before the move
    private boolean whiteKingsideCastle;
    private boolean whiteQueensideCastle;
    private boolean blackKingsideCastle;
    private boolean blackQueensideCastle;

    // Other game state before the move
    private int halfmoveClock;
    private int fullmoveNumber;
    private Color currentTurn;

    // Promotion information
    private Piece originalPiece;

    // Previous last move, if your Board stores it
    private Move lastMove;
    
    
    
    public UndoInfo() {
    	
    }
    
    public UndoInfo(
            Piece capturedPiece,
            Position capturedPosition,
            boolean whiteKingsideCastle,
            boolean whiteQueensideCastle,
            boolean blackKingsideCastle,
            boolean blackQueensideCastle,
            int halfmoveClock,
            Color currentTurn,
            Piece originalPiece,
            Move lastMove) {

        this.capturedPiece = capturedPiece;
        this.capturedPosition = capturedPosition;
        this.whiteKingsideCastle = whiteKingsideCastle;
        this.whiteQueensideCastle = whiteQueensideCastle;
        this.blackKingsideCastle = blackKingsideCastle;
        this.blackQueensideCastle = blackQueensideCastle;
        this.halfmoveClock = halfmoveClock;
        this.currentTurn = currentTurn;
        this.originalPiece = originalPiece;
        this.lastMove = lastMove;
    }
    
    public void setCapturedPiece(Piece capturedPiece) {
        this.capturedPiece = capturedPiece;
    }

    public void setCapturedPosition(Position capturedPosition) {
        this.capturedPosition = capturedPosition;
    }

    public void setWhiteKingsideCastle(boolean whiteKingsideCastle) {
        this.whiteKingsideCastle = whiteKingsideCastle;
    }

    public void setWhiteQueensideCastle(boolean whiteQueensideCastle) {
        this.whiteQueensideCastle = whiteQueensideCastle;
    }

    public void setBlackKingsideCastle(boolean blackKingsideCastle) {
        this.blackKingsideCastle = blackKingsideCastle;
    }

    public void setBlackQueensideCastle(boolean blackQueensideCastle) {
        this.blackQueensideCastle = blackQueensideCastle;
    }

    public void setHalfmoveClock(int halfmoveClock) {
        this.halfmoveClock = halfmoveClock;
    }

    public void setFullmoveNumber(int fullmoveNumber) {
        this.fullmoveNumber = fullmoveNumber;
    }

    public void setCurrentTurn(Color currentTurn) {
        this.currentTurn = currentTurn;
    }

    public void setOriginalPiece(Piece originalPawn) {
        this.originalPiece = originalPiece;
    }

    public void setLastMove(Move lastMove) {
        this.lastMove = lastMove;
    }
    
}
