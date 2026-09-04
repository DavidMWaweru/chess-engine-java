package chess;

import java.util.List;
import java.util.Random;

public class EnginePlayerV1 implements Player {
	
	Color color;
	
	public EnginePlayerV1(Color color){
		this.color = color;
	}
	
	public Move getMove(Game game) {
		List<Move> legalMoves = game.getBoard().getAllLegalMoves(color);
		Random r = new Random();
		int random = r.nextInt(legalMoves.size());
		return legalMoves.get(random);
	}
	
}
