package game.players;

//package game.players;
//
//import game.tictactoe.TicTacToeBoard;
//import game.*;
//
//public class CheatingPlayer implements Player {
//    private final int m, n;
//
//    public CheatingPlayer(int m, int n) {
//        this.m = m;
//        this.n = n;
//    }
//
//    @Override
//    public Move makeMove(Position position) {     //cheating player can't change the results of the game by accessing board
//        final TicTacToeBoard board = (TicTacToeBoard) position;
//        Move first = null;
//        for (int r = 0; r < m; r++) {
//            for (int c = 0; c < n; c++) {
//                final Move move = new Move(r, c, position.getTurn());
//                if (position.isValid(move)) {
//                    if (first == null) {
//                        first = move;
//                    } else {
//                        board.makeMove(move);
//                    }
//                }
//            }
//        }
//        return first;
//    }
//}
