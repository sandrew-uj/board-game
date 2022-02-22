package game;

public interface Position {     //position on board interface
    Cell getTurn();

    boolean isValid(Move move);

    Cell getCell(int row, int column);
}
