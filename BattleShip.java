package BattleShipGame;

import java.util.Scanner;

/**
 * Manages the flow of the game including its starting and ending, allows for replayability, and keeps track of the turns.
 * @author Sumukh Venkatesh, Gurinderpal Gaheer, Ayaan Shaikh, Karun Pai
 * @version 1.0
 */
public class BattleShip {
    Scanner in = new Scanner(System.in);
    
    /**
     * Creating a gameBoard object for the game be played with.
     */
    private GameBoard board;

    /**
     * Constructs an object of the 'BattleShip' class with a new object of the 'GameBoard' class.
     */
    public BattleShip() {
        board = new GameBoard();
    }

    /**
     * Starts and manages the overall flow of the game.
     * Allows the user to enter the number of turns they want to play.
     */
    public void playOnce() {
        System.out.print("Welcome to Battleship!\nHow many turns do you want the game to be? (Between 6-36): ");
        int turns = in.nextInt();
        while (turns < 6 || turns > 36) {
            System.out.print("Invalid number of turns. Please enter a number between 6 and 36: ");
            turns = in.nextInt();
        }
        board.createShipLocations();
        board.printGrid();
        playTurns(turns);
    }

    /**
     * Manages the number of turns, allowing play until the user has no turns left.
     * @param turns the amount of turns the user wanted to play.
     */
    public void playTurns(int turns) {
        while (!board.checkForWins() && turns > 0) {
            board.takeTurn();
            board.printGrid();
            turns--;
        }
        System.out.println();
        if (turns == 0) System.out.println("Game over! You've run out of turns.");
        if(board.checkForWins()) System.out.println("Good job! You have won!");
    }

    /**
     * Runs the game once, and asks the user if they want to play the game.
     * Allows the player to play the game as many times as the user wants, initializing a new board each time.
     */
    public void playGame() {
        boolean again = true;
        while(again) {
            playOnce();
            again = playAgain();
            if (!again) {
                System.out.println("Thanks for playing!");
            }
            board = new GameBoard();
        }
    }
    
    /**
     * Prompts the user if they want to play the again.
     * @return true, if the user enters 'Y' to if they want to play again or false if the user enters 'N'.
     */
    public boolean playAgain() {
        System.out.print("Do you want to play again (Y/N): ");
        in.nextLine();
        String ans = in.nextLine();
        while (!ans.equalsIgnoreCase("Y") && !ans.equalsIgnoreCase("N")) {
            System.out.print("Invalid input. Do you want to play again (Y/N): ");
            ans = in.nextLine();
        }
        return ans.equalsIgnoreCase("Y");
    }
}