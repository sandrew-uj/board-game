package game.hex;

import game.RoundTournamentGame;
import game.players.RandomPlayer;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {    //function for testing
        final int n = 11, cnt = 9;
//        final int result = new TwoPlayerGame(
//                new HexBoard(n),
//                new RandomPlayer(n, n),
//                new RandomPlayer(n, n)
//                new RandomPlayer(m, n)
//                new SequentialPlayer(m, n)
//                new CheatingPlayer()
//                new HumanPlayer(new Scanner(System.in))
//        ).play(true);
//        switch (result) {
//            case 1 -> System.out.println("First player won");
//            case 2 -> System.out.println("Second player won");
//            case 0 -> System.out.println("Draw");
//            default -> throw new AssertionError("Unknown result " + result);
//        }
        var boards = new HexBoard[cnt][cnt];
        for (int i = 0; i < cnt; i++) {
            for (int j = 0; j < cnt; j++) {
                boards[i][j] = new HexBoard(n);
            }
        }
        var players = new RandomPlayer[cnt];
        for (int i = 0; i < cnt; i++) {
            players[i] = new RandomPlayer(n, n);
        }
        var firstTournament = new RoundTournamentGame(boards, players);
        firstTournament.play(false);
        System.out.println(firstTournament);
        System.out.println(Arrays.toString(firstTournament.getResults()));
    }
}
