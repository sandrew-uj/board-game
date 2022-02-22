package game;

public interface Board {    //board interface
    Position getPosition();
    GameResult makeMove(Move move);
}
