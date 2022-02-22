package game.players;

import game.Move;
import game.Player;
import game.Position;

import java.util.Random;

public class RandomPlayer implements Player {       //player that make random moves
    private final Random random = new Random();
    private final int m, n;

    public RandomPlayer() {
        this.m = 3;
        this.n = 3;
    }

    public RandomPlayer(int m, int n) {
        this.m = m;
        this.n = n;
    }

    @Override
    public Move makeMove(Position position) {
        while (true) {
            final Move move = new Move(
                    random.nextInt(m),
                    random.nextInt(n),
                    position.getTurn()
            );
            if (position.isValid(move)) {
                return move;
            }
        }
    }
}
