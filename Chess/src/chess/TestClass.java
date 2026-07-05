package chess;

public class TestClass {
    public static void main(String[] args) {

        System.out.println("Game starting!!!");

        //Pawn john = new Pawn(new Position(1,0), true);
        //System.out.println(john.isWhite());
        //System.out.println(john);
        
        Game game = new Game(); // start your chess game here
        game.getBoard().printBoard();
    }
}
