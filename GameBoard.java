package BattleShipGame;

import java.util.Scanner;

/**
 * Controls the grid and guesses of the BattleShip game and checks for wins.
 * @author Sumukh Venkatesh, Gurinderpal Gaheer, Ayaan Shaikh, Karun Pai
 * @version 1.0
 */
public class GameBoard {
    Scanner in = new Scanner(System.in);
    /**
     * Constant variable of dimension of the grid.
     */
    private final int GRID_SIZE = 6;
    /**
     * The index that the user's row and column is in the field.
     */
    private int index;
    /**
     * Acts as the grid for our BattleShip game.
     */
    private String field;
    /**
     * Index of the Ship One's first cell.
     */
    private int shipOneIndexOne;
    /**
     * Index of the Ship Two's first cell.
     */
    private int shipTwoIndexOne;
    /**
     * Index of the Ship Two's second cell.
     */
    private int shipTwoIndexTwo;
    /**
     * Index of the Ship Three's first cell.
     */
    private int shipThreeIndexOne;
    /**
     * Index of the Ship Three's second cell.
     */
    private int shipThreeIndexTwo;
    /**
     * Index of the Ship Three's third cell.
     */
    private int shipThreeIndexThree;
    /**
     * The amount of times that Ship One has been hit.
     */
    private int shipOneHits = 0;
    /**
     * The amount of times that Ship Two has been hit.
     */
    private int shipTwoHits = 0;
    /**
     * The amount of times that Ship Three has been hit.
     */
    private int shipThreeHits = 0;

    /**
     * Creates a new game board and initializes the field.
     */
    public GameBoard() {
        field = "....................................";
    }

    /**
     * Prints out the grid in its current state.
     */
    public void printGrid() {
        for (int i = 0; i < 36; i += 6) {
            System.out.println(field.substring(i, i + 6));
        }
    }

    /**
     * Updates the board by check if the user hits any of the ships or sunk a ship and update the board accordingly.
     * @param row the row to update
     * @param column the column to update
     */
    public void updateBoard(int row, int column) {
        index = (row - 1) * 6 + (column - 1);
        if (index == shipOneIndexOne) hitShipOne();
        else if (index == shipTwoIndexOne || index == shipTwoIndexTwo) hitShipTwo();
        else if (index == shipThreeIndexOne || index == shipThreeIndexTwo || index == shipThreeIndexThree) hitShipThree();
        else missedShips();
        checkIfSunk();
    }

    /**
     * Checks if the user has hit ship one, and if it does, updates the board and ship hit counter and prints that it has been hit.
     */
    public void hitShipOne() {
        shipOneHits++;
        System.out.println("You hit my battleship!");
        field = field.substring(0, index) + "X" + field.substring(index + 1);
    }

    /**
     * Checks if the user has hit ship two, and if it does, updates the board and ship hit counter and prints that it has been hit.
     */
    public void hitShipTwo() {
        shipTwoHits++;
        System.out.println("You hit my battleship!");
        field = field.substring(0, index) + "X" + field.substring(index + 1);
    }

    /**
     * Checks if the user has hit ship three, and if it does, updates the board and ship hit counter and prints that it has been hit.
     */
    public void hitShipThree() {
        shipThreeHits++;
        System.out.println("You hit my battleship!");
        field = field.substring(0, index) + "X" + field.substring(index + 1);
    }

    /**
     * If the user has not hit any ship, they must have missed and the board is updated accordingly.
     */
    public void missedShips(){
        System.out.println("You missed!");
        field = field.substring(0, index) + "O" + field.substring(index + 1);
    }

    /**
     * Checks if the cell inputed has already been guessed.
     * @param row the row that has been guessed.
     * @param column the column that has been guessed.
     * @return true if the cell has already been guessed, false if not.
     */
    public boolean isAlreadyGuessed(int row, int column) {
        char cell = field.charAt((row - 1) * 6 + column - 1);
        return (cell == 'X' || cell == 'O');
    }

    /**
     * Generates randomly if the ship is going to be vertical or horizontal.
     * @return either "lr" - the ship is horizontal - or "ud" - the ship is vertical.
     */
    public String getRandomDir()  {
        if (Math.random() >= 0.5) return "ud";
        else return "lr";
    }

    /**
     * Generates the cell that ship one is going to be located in.
     */
    public void createShipOne() {
        shipOneIndexOne = (int) (Math.random() * GRID_SIZE * GRID_SIZE);
    }

    /**
     * Generates the cell that ship two is going to be located in.
     */
    public void createShipTwo() {
        String dir2 = getRandomDir();
        if (dir2.equals("lr")) {
            shipTwoIndexOne = (int) (Math.random() * (GRID_SIZE * GRID_SIZE - 2));
            while (shipTwoIndexOne % GRID_SIZE > GRID_SIZE - 2) shipTwoIndexOne = (int) (Math.random() * (GRID_SIZE * GRID_SIZE - 2));
        } else shipTwoIndexOne = (int) (Math.random() * (GRID_SIZE * (GRID_SIZE - 1)));
        placeShipTwo(dir2);
        while (shipTwoIndexOne == shipOneIndexOne || shipTwoIndexTwo == shipOneIndexOne)
            placeShipTwo(dir2);
    }

    /**
     * Places the rest of ship two's cells.
     * @param dir the direction the ship is facing.
     */
    public void placeShipTwo(String dir) {
        if (dir.equals("ud")) shipTwoIndexTwo = shipTwoIndexOne + GRID_SIZE;
        else shipTwoIndexTwo = shipTwoIndexOne + 1;
    }

    /**
     * Generates the cells that ship three is going to be located in.
     */
    public void createShipThree() {
        String dir3 = getRandomDir();
        if (dir3.equals("lr")) {
            shipThreeIndexOne = (int) (Math.random() * (GRID_SIZE * GRID_SIZE - 3));
            while (shipThreeIndexOne % GRID_SIZE > GRID_SIZE - 3) shipThreeIndexOne = (int) (Math.random() * (GRID_SIZE * GRID_SIZE - 3));
        } else shipThreeIndexOne = (int) (Math.random() * (GRID_SIZE * (GRID_SIZE - 2)));
        placeShipThree(dir3);
        while (shipThreeIndexOne == shipOneIndexOne || shipThreeIndexTwo == shipOneIndexOne || shipThreeIndexThree == shipOneIndexOne || 
        		shipThreeIndexOne == shipTwoIndexOne || shipThreeIndexTwo == shipTwoIndexOne || shipThreeIndexThree == shipTwoIndexOne || 
        		shipThreeIndexOne == shipTwoIndexTwo || shipThreeIndexTwo == shipTwoIndexTwo || shipThreeIndexThree == shipTwoIndexTwo)
            placeShipThree(dir3);
    }

    /**
     * Places the rest of ship three's cells.
     * @param dir the direction the ship is facing.
     */
    public void placeShipThree(String dir) {
        if (dir.equals("ud")) shipThreeIndexTwo = shipThreeIndexOne + GRID_SIZE;
        else shipThreeIndexTwo = shipThreeIndexOne + 1;
        if (dir.equals("ud")) shipThreeIndexThree = shipThreeIndexOne + GRID_SIZE * 2;
        else shipThreeIndexThree = shipThreeIndexOne + 2;
    }

    /**
     * Initializes the locations of all three ships on the board.
     */
    public void createShipLocations() {
        createShipOne();
        createShipTwo();
        createShipThree();
    }

    /**
     * Checks if the user has won by hitting the 6 possible targets.
     * @return true if all ships have been hit six times in total, false if not.
     */
    public boolean checkForWins() {
        return shipOneHits + shipTwoHits + shipThreeHits == 6;
    }

    /**
     * Checks if a certain ship has been sunk by being hit a certain number of times.
     */
    public void checkIfSunk() {
        final int SHIP_ONE_SIZE = 1;
        if (shipOneHits == SHIP_ONE_SIZE)
            System.out.println("You have sunk Ship One!");
        final int SHIP_TWO_SIZE = 2;
        if (shipTwoHits == SHIP_TWO_SIZE)
            System.out.println("You have sunk Ship Two!");
        final int SHIP_THREE_SIZE = 3;
        if (shipThreeHits == SHIP_THREE_SIZE)
            System.out.println("You have sunk Ship Three!");
    }

    /**
     * Allows the user to take a turn by prompting for a row and column.
     * If already guessed, the user has to enter a new location that has not been guessed.
     */
    public void takeTurn() {
        int row = promptRow();
        int column = promptColumn();
        while (isAlreadyGuessed(row, column)) {
            System.out.println("You have already guessed that position! Try again.");
            row = promptRow();
            column = promptColumn();
        }
        updateBoard(row, column);
    }

    /**
     * Prompts the user for a row.
     * @return the integer row that the user inputed.
     */
    public int promptRow() {
        System.out.print("Enter a row from 1 to 6: ");
        int row = in.nextInt();
        while (row < 1 || row > GRID_SIZE) {
            System.out.print("Invalid row. Enter a row from 1 to 6: ");
            row = in.nextInt();
        }
        return row;
    }

    /**
     * Prompts the user for a column.
     * @return the integer column that the user inputed.
     */
    public int promptColumn() {
        System.out.print("Enter a column from 1 to 6: ");
        int column = in.nextInt();
        while (column < 1 || column > GRID_SIZE) {
            System.out.print("Invalid column. Enter a column from 1 to 6: ");
            column = in.nextInt();
        }
        return column;
    }
}