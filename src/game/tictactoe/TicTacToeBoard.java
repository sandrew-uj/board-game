package game.tictactoe;

import game.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.lang.Math.min;

class TicTacToeBoard implements Board, Position {
    //board for tictactoe game with board m*n sizes && k (X or O) in row column or diagonal

    private static final Map<Cell, String> CELL_TO_STRING = Map.of(
            Cell.E, ".",        //for empty condition
            Cell.X, "X",
            Cell.O, "0"
    );

    private final Cell[][] field;
    private Cell turn;
    private final int m, n, k;
    private int filledCnt;

    TicTacToeBoard() {      //default values for m, n, k like classic tictactoe game
        this.m = 3;
        this.n = 3;
        this.k = 3;
        filledCnt = 0;
        field = new Cell[3][3];
        for (Cell[] row : field) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    TicTacToeBoard(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        filledCnt = 0;
        field = new Cell[m][n];
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
    public GameResult makeMove(Move move) {
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

    private boolean checkDraw() {
        return ++filledCnt == m * n;
    }

    private boolean checkWin(Move move) {       //win checker
        int col = move.getCol(), row = move.getRow();
        var list = new ArrayList<ArrayList<Cell>>(4);
        for (int i = 0; i < 4; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 1 - k; i < k; i++) {
            if (col + i >= 0 && col + i < n) {
                list.get(0).add(field[row][col + i]);
            }
            if (row + i >= 0 && row + i < m) {
                list.get(1).add(field[row + i][col]);
            }
            if (col + i >= 0 && col + i < n && row + i >= 0 && row + i < m) {
                list.get(2).add(field[row + i][col + i]);
            }
            if (col - i >= 0 && col - i < n && row + i >= 0 && row + i < m) {
                list.get(3).add(field[row + i][col - i]);
            }
        }
        for(var elem: list) {
            if (checkWinStreak(elem)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkWinStreak(List<Cell> list) {
        int cnt = 0;
        for (int i = 0; i < min(k, list.size()) - 1; i++) {
            if (list.get(i) == turn) {
                cnt++;
            }
        }
        for (int i = k - 1; i < list.size(); i++) {
            if (list.get(i) == turn) {
                cnt++;
            }
            if (i >= k && list.get(i - k) == turn) {
                cnt--;
            }
            if (cnt == k) {
                return true;
            }
        }
        return false;
    }

    public boolean isValid(final Move move) {       //validator
        return 0 <= move.getRow() && move.getRow() < m
                && 0 <= move.getCol() && move.getCol() < n
                && field[move.getRow()][move.getCol()] == Cell.E
                && turn == move.getValue();
    }

    @Override
    public Cell getCell(int row, int column) {
        return field[row][column];
    }

    @Override
    public String toString() {      //dump on console
        final StringBuilder sb = new StringBuilder("    ");
        for (int i = 1; i <= n; i++) {
            sb.append(String.format("%4d", i));
        }
        sb.append(System.lineSeparator());
        for (int r = 0; r < m; r++) {
            sb.append(String.format("%4d", r + 1));
            for (Cell cell : field[r]) {
                sb.append(String.format("%4s", CELL_TO_STRING.get(cell)));
            }
            sb.append(System.lineSeparator());
        }
        sb.setLength(sb.length() - System.lineSeparator().length());
        return sb.toString();
    }
}
