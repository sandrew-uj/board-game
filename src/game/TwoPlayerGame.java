package game;

public class TwoPlayerGame {        //class for game for two players
    private final Board board;
    private final Player player1;
    private final Player player2;

    public TwoPlayerGame(Board board, Player player1, Player player2) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
    }

    public int play(boolean log) {      //log or not this game
        while (true) {
            final int result1 = makeMove(player1, 1, log);  //first player move
            if (result1 != -1)  {
                return result1;
            }
            final int result2 = makeMove(player2, 2, log);  //second palyer move
            if (result2 != -1)  {
                return result2;
            }
        }
    }

    private int makeMove(Player player, int no, boolean log) {  //function for player's move
        final Move move = player.makeMove(board.getPosition());
        final GameResult result = board.makeMove(move);
        if (log) {                      //dump result of the move on console
            System.out.println();
            System.out.println("Player: " + no);
            System.out.println(move);
            System.out.println(board);
            System.out.println("Result: " + result);
        }
        return switch (result) {        //result
            case WIN -> no;
            case LOOSE -> 3 - no;
            case DRAW -> 0;
            case UNKNOWN -> -1;
        };
    }
}
