/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cs.ttt;

import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author s506571
 */
public class Main {
    public static void main(String argv[]) {
        Board gameBoard = new Board();
        Board.Symbol currentPlayer = Board.Symbol.X;
        Scanner s = new Scanner(System.in);
        while (!gameBoard.isTerminal()) {
            gameBoard.print();
            System.out.println();
            System.out.println("Scores:");
            int maxScore = currentPlayer.max() ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            ArrayList<Integer> maxMoves = new ArrayList<Integer>();
            for (int i : gameBoard.getMovePositions()) {
                Board consider = gameBoard.move(i, currentPlayer);
                int score = consider.minimax(currentPlayer.other());
                System.out.println(i + ": " + score);
                if (currentPlayer.max() && score > maxScore || currentPlayer.min() && score < maxScore) {
                    maxMoves.clear();
                    maxScore = score;
                }
                if (score == maxScore) {
                    maxMoves.add(i);
                }
            }
            System.out.println("It is " + currentPlayer.getSymbol() + "'s turn");
            System.out.println("We are " + (currentPlayer.max() ? "maximizing" : "minimizing"));
            System.out.print("Best score: " + maxScore);
            System.out.println(", moves: " + maxMoves);
            int move = s.nextInt();
            gameBoard = gameBoard.move(move, currentPlayer);
            currentPlayer = currentPlayer.other();
        }
    }
}