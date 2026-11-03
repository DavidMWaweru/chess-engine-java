package chess;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class CastleTest {

    // ---------- helpers ----------

    private static boolean canCastleKingSide(List<Move> moves) {
        return moves.stream().anyMatch(m -> m.getMoveType() == MoveType.CASTLE_KINGSIDE);
    }

    private static boolean canCastleQueenSide(List<Move> moves) {
        return moves.stream().anyMatch(m -> m.getMoveType() == MoveType.CASTLE_QUEENSIDE);
    }

    private static boolean kingSide(Board board, Piece king) {
        return canCastleKingSide(board.getAllPieceLegalMoves(king));
    }

    private static boolean queenSide(Board board, Piece king) {
        return canCastleQueenSide(board.getAllPieceLegalMoves(king));
    }

    private static void clear(Board board, int[][] squares) {
        for (int[] sq : squares) {
            board.setPieceAt(sq[0], sq[1], null);
        }
    }

    /** Empty board with both kings and all four rooks on their starting squares, none moved. */
    private static Board emptyBoardWithKingsAndRooks() {
        Board board = new Game().getBoard();
        board.addPieceAt(new King(new Position(0, 4), Color.BLACK, false));
        board.addPieceAt(new King(new Position(7, 4), Color.WHITE, false));
        board.addPieceAt(new Rook(new Position(0, 7), Color.BLACK, false));
        board.addPieceAt(new Rook(new Position(0, 0), Color.BLACK, false));
        board.addPieceAt(new Rook(new Position(7, 7), Color.WHITE, false));
        board.addPieceAt(new Rook(new Position(7, 0), Color.WHITE, false));
        return board;
    }

    // ---------- Test 1 ----------

    @Nested
    @DisplayName("Test 1: paths cleared, black queen-side rook removed")
    class ClearPaths {
        private final Board board;

        ClearPaths() {
            board = new Game(true).getBoard();
            clear(board, new int[][] {
                {0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 5}, {0, 6},
                {7, 1}, {7, 2}, {7, 3}, {7, 5}, {7, 6}
            });
        }

        @Test
        @DisplayName("black can castle king side")
        void blackKingSide() {
            assertTrue(kingSide(board, board.getPieceAt(0, 4)));
        }

        @Test
        @DisplayName("black cannot castle queen side without a rook")
        void blackQueenSideNoRook() {
            assertFalse(queenSide(board, board.getPieceAt(0, 4)));
        }

        @Test
        @DisplayName("white can castle king side")
        void whiteKingSide() {
            assertTrue(kingSide(board, board.getPieceAt(7, 4)));
        }

        @Test
        @DisplayName("white can castle queen side")
        void whiteQueenSide() {
            assertTrue(queenSide(board, board.getPieceAt(7, 4)));
        }
    }

    // ---------- Test 2 ----------

    @Nested
    @DisplayName("Test 2: pieces blocking the castling path")
    class BlockedPaths {
        private final Board board;

        BlockedPaths() {
            board = new Game(true).getBoard();
            clear(board, new int[][] {
                {0, 1}, {0, 2}, {0, 6},
                {7, 1}, {7, 2}, {7, 3}, {7, 5}
            });
        }

        @Test
        @DisplayName("black cannot castle king side (bishop on f8 blocks)")
        void blackKingSideBlocked() {
            assertFalse(kingSide(board, board.getPieceAt(0, 4)));
        }

        @Test
        @DisplayName("black cannot castle queen side (queen on d8 blocks)")
        void blackQueenSideBlocked() {
            assertFalse(queenSide(board, board.getPieceAt(0, 4)));
        }

        @Test
        @DisplayName("white cannot castle king side (knight on g1 blocks)")
        void whiteKingSideBlocked() {
            assertFalse(kingSide(board, board.getPieceAt(7, 4)));
        }

        @Test
        @DisplayName("white can castle queen side (path clear)")
        void whiteQueenSideClear() {
            assertTrue(queenSide(board, board.getPieceAt(7, 4)));
        }
    }

    // ---------- Test 3 ----------

    @Nested
    @DisplayName("Test 3: king or rook has already moved")
    class AlreadyMoved {
        private final Board board;

        AlreadyMoved() {
            board = new Game(true).getBoard();
            clear(board, new int[][] {
                {0, 1}, {0, 2}, {0, 3}, {0, 5}, {0, 6},
                {7, 1}, {7, 2}, {7, 3}, {7, 5}, {7, 6}
            });
            board.getPieceAt(0, 4).setHasMoved(true); // black king moved
            board.getPieceAt(7, 7).setHasMoved(true); // white king-side rook moved
        }

        @Test
        @DisplayName("black cannot castle king side after king moved")
        void blackKingSideKingMoved() {
            assertFalse(kingSide(board, board.getPieceAt(0, 4)));
        }

        @Test
        @DisplayName("black cannot castle queen side after king moved")
        void blackQueenSideKingMoved() {
            assertFalse(queenSide(board, board.getPieceAt(0, 4)));
        }

        @Test
        @DisplayName("white cannot castle king side after that rook moved")
        void whiteKingSideRookMoved() {
            assertFalse(kingSide(board, board.getPieceAt(7, 4)));
        }

        @Test
        @DisplayName("white can still castle queen side with the unmoved rook")
        void whiteQueenSideStillAllowed() {
            assertTrue(queenSide(board, board.getPieceAt(7, 4)));
        }
    }

    // ---------- Test 4 ----------

    @Nested
    @DisplayName("Test 4: king in check / landing square attacked (bishops)")
    class BishopAttacks {
        private final Board board;
        private final Piece blackKing;
        private final Piece whiteKing;

        BishopAttacks() {
            board = emptyBoardWithKingsAndRooks();
            board.addPieceAt(new Bishop(new Position(4, 1), Color.BLACK, false)); // checks white king
            board.addPieceAt(new Bishop(new Position(3, 5), Color.WHITE, false)); // attacks c8
            blackKing = board.getPieceAt(0, 4);
            whiteKing = board.getPieceAt(7, 4);
        }

        @Test
        @DisplayName("black can castle king side")
        void blackKingSide() {
            assertTrue(kingSide(board, blackKing));
        }

        @Test
        @DisplayName("black cannot castle queen side into an attacked square")
        void blackQueenSideAttacked() {
            assertFalse(queenSide(board, blackKing));
        }

        @Test
        @DisplayName("white cannot castle king side while in check")
        void whiteKingSideInCheck() {
            assertFalse(kingSide(board, whiteKing));
        }

        @Test
        @DisplayName("white cannot castle queen side while in check")
        void whiteQueenSideInCheck() {
            assertFalse(queenSide(board, whiteKing));
        }
    }

    // ---------- Test 5 ----------

    @Nested
    @DisplayName("Test 5: castling through attacked squares (queen and bishop)")
    class CastlingThroughCheck {
        private final Board board;
        private final Piece blackKing;
        private final Piece whiteKing;

        CastlingThroughCheck() {
            board = emptyBoardWithKingsAndRooks();
            board.addPieceAt(new Bishop(new Position(6, 6), Color.BLACK, false));
            board.addPieceAt(new Queen(new Position(2, 3), Color.WHITE, false));
            blackKing = board.getPieceAt(0, 4);
            whiteKing = board.getPieceAt(7, 4);
        }

        @Test
        @DisplayName("black cannot castle king side through check")
        void blackKingSideThroughCheck() {
            assertFalse(kingSide(board, blackKing));
        }

        @Test
        @DisplayName("black cannot castle queen side through check")
        void blackQueenSideThroughCheck() {
            assertFalse(queenSide(board, blackKing));
        }

        @Test
        @DisplayName("white cannot castle king side through check")
        void whiteKingSideThroughCheck() {
            assertFalse(kingSide(board, whiteKing));
        }

        @Test
        @DisplayName("white can castle queen side normally")
        void whiteQueenSideNormal() {
            assertTrue(queenSide(board, whiteKing));
        }
    }

    // ---------- Test 6 ----------

    @Nested
    @DisplayName("Test 6: cannot castle again after castling")
    class AfterCastling {
        private final Board board;
        private final Piece whiteKing;

        AfterCastling() {
            board = new Game(true).getBoard();
            clear(board, new int[][] { {7, 1}, {7, 2}, {7, 3} });
            board.movePiece(new Move(board.getPieceAt(7, 4),
                    new Position(7, 4), new Position(7, 2), MoveType.CASTLE_QUEENSIDE));
            whiteKing = board.getPieceAt(7, 2);
        }

        @Test
        @DisplayName("white cannot castle king side after castling")
        void noKingSideAfterCastling() {
            assertFalse(kingSide(board, whiteKing));
        }

        @Test
        @DisplayName("white cannot castle queen side after castling")
        void noQueenSideAfterCastling() {
            assertFalse(queenSide(board, whiteKing));
        }
    }
}
