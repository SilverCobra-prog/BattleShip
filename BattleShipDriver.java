package BattleShipGame;

/**
 * The driver class allows the user to play a game of Battleship by calling the method that allows for the playing of the game.
 * @author Sumukh Venkatesh, Gurinderpal Gaheer, Ayaan Shaikh, Karun Pai
 * @version 1.0
 */

public class BattleShipDriver {
    /**
     * The main method for the 'BattleShipDriver' class
     * Initializes a new object of the class 'BattleShip' and starts the game
     */
    public static void main(String[] args) {
        BattleShip b = new BattleShip();
        b.playGame();
    }
}