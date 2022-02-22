package game.tictactoe;

import game.TwoPlayerGame;
import game.players.RandomPlayer;

public class Main {
    public static void main(String[] args) {        //tester for tictactoe game
        final int m, n, k;
        m = 6;
        n = 16;
        k = 6;
        final int result = new TwoPlayerGame(
                new TicTacToeBoard(m, n, k),
                new RandomPlayer(m, n),
                new RandomPlayer(m, n)
//                new RandomPlayer(m, n)
//                new SequentialPlayer(m, n)
//                new CheatingPlayer()
        ).play(true);
        switch (result) {
            case 1 -> System.out.println("First player won");
            case 2 -> System.out.println("Second player won");
            case 0 -> System.out.println("Draw");
            default -> throw new AssertionError("Unknown result " + result);
        }
    }
}
