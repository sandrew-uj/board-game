package game.players;

import game.Move;
import game.Player;
import game.Position;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanPlayer implements Player {        //human player in console
    private final Scanner in;

    public HumanPlayer(Scanner in) {
        this.in = in;
    }

    @Override
    public Move makeMove(Position position) {
        System.out.println();
        System.out.println("Current position");
        System.out.println(position);
        System.out.println("Enter you move for " + position.getTurn());
        Move move;
        try {
            move = new Move(in.nextInt() - 1, in.nextInt() - 1, position.getTurn());
            if (!position.isValid(move)) {
                System.out.println("Please, enter valid position!");
                return makeMove(position);
            }
        } catch (InputMismatchException e) {
            System.out.println("Please, enter the number: ");
            in.nextLine();
            return makeMove(position);
        }
        return move;
    }
}
