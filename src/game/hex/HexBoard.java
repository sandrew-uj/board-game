package game.hex;

import game.*;

import java.util.Arrays;
import java.util.Map;


public class HexBoard implements Board, Position {
    /*
    Board for playing in hex
     */

    private static final Map<Cell, String> CELL_TO_STRING = Map.of(
            Cell.E, ".",
            Cell.X, "X",
            Cell.O, "O"
    );

    private final Cell[][] field;
    private Cell turn;
    private final int n;
    private int filledCnt;


    public HexBoard(int n) {
        this.n = n;
        filledCnt = 0;
        field = new Cell[n][n];
        for (Cell[] row : field) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    @Override
    public Cell getTurn() {
        return turn;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public GameResult makeMove(Move move) {     //method for making move
        if (!isValid(move)) {
            return GameResult.LOOSE;
        }

        field[move.getRow()][move.getCol()] = turn;
        if (checkWin(move)) {
            return GameResult.WIN;
        }

        if (checkDraw()) {
            return GameResult.DRAW;
        }

        turn = turn == Cell.X ? Cell.O : Cell.X;
        return GameResult.UNKNOWN;
    }

    private boolean checkDraw() {   //draw checker
        return ++filledCnt == n * n;
    }

    private boolean checkWin(Move move) {   //win checker
        int col = move.getCol(), row = move.getRow();
        return checkDown(row, col, new boolean[n][n]) && checkUp(row, col, new boolean[n][n]);
    }

    private boolean checkDown(int row, int col, boolean[][] isChecked) {    //check number of sequential X or O in down
        if (row < 0 || col < 0 || row >= n || col >= n || field[row][col] != turn || isChecked[row][col]) {
            return false;
        } else if (row == 0 && turn == Cell.X || col == 0 && turn == Cell.O) {
            return true;
        } else {
            isChecked[row][col] = true;
            return turn == Cell.X ? checkDown(row, col - 1, isChecked) || checkDown(row, col + 1, isChecked)
                    || checkDown(row - 1, col, isChecked) || checkDown(row - 1, col - 1, isChecked)
                    : checkDown(row - 1, col, isChecked) || checkDown(row + 1, col, isChecked)
                    || checkDown(row, col - 1, isChecked) || checkDown(row - 1, col - 1, isChecked);
        }
    }

    private boolean checkUp(int row, int col, boolean[][] isChecked) {    //check number of sequential X or O in up
        if (row < 0 || col < 0 || row >= n || col >= n || field[row][col] != turn || isChecked[row][col]) {
            return false;
        } else if (row == n - 1 && turn == Cell.X || col == n - 1 && turn == Cell.O) {
            return true;
        } else {
            isChecked[row][col] = true;
            return turn == Cell.X ? checkUp(row, col - 1, isChecked) || checkUp(row, col + 1, isChecked)
                    || checkUp(row + 1, col, isChecked) || checkUp(row + 1, col + 1, isChecked)
                    : checkUp(row - 1, col, isChecked) || checkUp(row + 1, col, isChecked)
                    || checkUp(row, col + 1, isChecked) || checkUp(row + 1, col + 1, isChecked);
        }
    }

    public boolean isValid(final Move move) {       //validator
        return 0 <= move.getRow() && move.getRow() < n
                && 0 <= move.getCol() && move.getCol() < n
                && field[move.getRow()][move.getCol()] == Cell.E
                && turn == move.getValue();
    }

    @Override
    public Cell getCell(int row, int column) {
        return field[row][column];
    }

    @Override
    public String toString() {      //dumping in console
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append("    ");
        }
        for (int i = 1; i <= n; i++) {
            sb.append(String.format("%4d", i));
        }
        sb.append(System.lineSeparator());
        for (int r = 0; r < n; r++) {
            sb.append(String.format("%4d", r + 1));
            for (int i = 0; i < n - r - 1; i++) {
                sb.append("    ");
            }
            for (Cell cell : field[r]) {
                sb.append(String.format("%4s", CELL_TO_STRING.get(cell)));
            }
            sb.append(System.lineSeparator());
        }
        sb.setLength(sb.length() - System.lineSeparator().length());
        return sb.toString();
    }
}
