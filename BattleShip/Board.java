import java.util.HashSet;
import java.util.Set;

public class Board {
    private String[][] board;
    private String playerName;
    private Ship ship;
    private Set<Ship> ships; // A Set to keep track of multiple ships

    public Board(String name) {
        playerName = name;
        board = new String[10][10];
        ships = new HashSet<>(); // Initialize the Set of ships
        for (int i = 0; i < 10; i++) 
        {
            for (int j = 0; j < 10; j++) 
            {
                board[i][j]="_";    
    
            }
        }
    }

    public void printBoard()
    {
        System.out.println(playerName);
        for (int i = 0; i < 10; i++) 
        {   
            if (i == 0)
            {
                printHeader();
            }
            System.out.print(i+ " ");
            for (int j = 0; j < 10; j++) 
            {
                System.out.print(board[i][j] + "     ");   
    
            }
            System.out.println("\n");
        }
    }

    public void updateBoard(Set<Coordinate> positions)
    {
        for (Coordinate position : positions) {
            board[position.x][position.y] ="@";
        }

    }

    public void addShip(Ship ship) {
        ships.add(ship); // Add a new ship to the Set
    }

    public void fire(int x, int y) {
        boolean hit = false;
        for (Ship ship : ships) {
            if (ship.isHit(x, y)) {
                board[x][y] = "X";
                hit = true;
                break;
            }
        }
        if (!hit) {
            board[x][y] = "O";
        }
    }

    public void printBoardWithShots() {
        System.out.println(playerName + "'s Board with Shots:");
        printHeader();
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 10; j++) {
                if ("X".equals(board[i][j]) || "O".equals(board[i][j])) {
                    System.out.print(board[i][j] + "     ");
                } else {
                    System.out.print("_     ");
                }
            }
            System.out.println("\n");
        }
    }

    private void printHeader() {
        System.out.println("  0     1     2     3     4     5     6     7     8     9");
    }

    public boolean allShipsSunk() {
        for (Ship ship : ships) {
            if (!ship.isSunk(board)) {
                return false; // If any ship is not sunk, return false
            }
        }
        return true; // All ships are sunk
    }
}

