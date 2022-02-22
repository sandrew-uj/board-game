package game;

public class RoundTournamentGame {
    private final Board[][] boards;
    private final Player[] players;
    private final int[][] standings;

    public RoundTournamentGame(Board[][] boards, Player[] players) {
        this.boards = boards;
        this.players = players;
        standings = new int[players.length][players.length];
    }

    public int[][] play(boolean log) {
        for (int i = 0; i < players.length; i++) {
            for (int j = i + 1; j < players.length; j++) {
                int result = new TwoPlayerGame(boards[i][j], players[i], players[j]).play(log);
                switch (result) {
                    case 1 -> standings[i][j] = 3;
                    case 2 -> standings[j][i] = 3;
                    case 0 -> standings[i][j] = standings[j][i] = 1;
                }
            }
        }
        return standings;
    }

    public int[] getResults() {
        var results = new int[players.length];
        for (int i = 0; i < results.length; i++) {
            for (int j = 0; j < results.length; j++) {
                results[i] += standings[i][j];
            }
        }
        return results;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder("  ");
        for (int i = 1; i <= players.length; i++) {
            sb.append(i).append(" ");
        }
        sb.append(System.lineSeparator());
        for (int r = 1; r  <= players.length; ++r) {
            sb.append(r).append(" ");
            for (var elem: standings[r - 1]) {
                sb.append(elem).append(" ");
            }
            sb.append(System.lineSeparator());
        }
        sb.setLength(sb.length() - System.lineSeparator().length());
        return sb.toString();
    }
}
