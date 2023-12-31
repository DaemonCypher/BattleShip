import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;




public class Main 
{

    enum Direction 
    {
        UP, DOWN, LEFT, RIGHT, NEGATIVE
    }

    private static Set<Coordinate> generateShipCoordinates(Coordinate initial, int length, Direction direction) {
        Set<Coordinate> coordinates = new HashSet<>();
        coordinates.add(initial); // Add the initial position

        for (int i = 1; i < length; i++) {
            int x = initial.x, y = initial.y;

            switch (direction) {
                case UP:    x -= i; break;
                case DOWN:  x += i; break;
                case LEFT:  y -= i; break;
                case RIGHT: y += i; break;
            }

            // Check for invalid positions (e.g., negative or beyond the board)
            if (x < 0 || y < 0 || x >= 10 || y >= 10) {
                return new HashSet<>(); // Return an empty set for invalid positions
            }

            coordinates.add(new Coordinate(x, y));
        }

        return coordinates;
    }

    public static int getShipLength(String ship){
        int length;
        switch (ship) {
                case "Carrier":
                    length = 5;
                    break;
                case "Battleship":
                    length = 4;
                    break;
                case "Cruiser":
                    length = 3;
                    break;
                case "Submarine":
                    length = 3;
                    break;
                case "Destroyer":
                    length = 2;
                    break;
                default:
                    return -1;
            }
        return length;
    }

    public static Direction getDirection(String direction)
    {
        Direction dir;
        switch (direction) {
                case "U":
                    dir = Direction.UP;
                    break;
                case "D":
                    dir = Direction.DOWN;
                    break;
                case "L":
                    dir = Direction.LEFT;
                    break;
                case "R":
                    dir = Direction.RIGHT;
                    break;
                default:
                    dir = Direction.NEGATIVE;
                    break;
            }
        return dir;
    }

    public static Board starter(Set<String> ships)
    {
        Scanner objScanner = new Scanner(System.in);
        System.out.println("Enter player name:");
        String name = objScanner.nextLine();
        Board player = new Board(name);
        
        Ship toBeShip;
        Coordinate position; // Initial position
        Set<Coordinate> coordinates;
        boolean placement;
        int x;
        int y;
        String ship = objScanner.nextLine();
        String direction;
        Direction dir;
        int length;
        while (!ships.isEmpty()) {
            System.out.println(ships);
            System.out.println("Enter the name of the ship to place (Carrier, Battleship, Cruiser, Submarine, Destroyer):");
            ship = objScanner.nextLine();
            if (!ships.contains(ship)) {
                System.out.println("Not a valid ship name. Please try again.");
                continue;
            }
            length = getShipLength(ship);
            if (length == -1)
            {
                continue;
            }
            toBeShip = new Ship();
            System.out.println("Where would you like to place the ship:");
            player.printBoard();
            System.out.println("Enter the X coordinate:");
            x = objScanner.nextInt();
            System.out.println("Enter the Y coordinate:");
            y = objScanner.nextInt();
            objScanner.nextLine(); // Add this line to consume the leftover newline character

            System.out.println("Enter the Direction of the ship to be placed (U)p, (D)own, (L)eft, (R)ight:");
            direction = objScanner.nextLine().toUpperCase(); // Corrected to capture the direction input
            dir = getDirection(direction);
            if (dir == Direction.NEGATIVE)
            {
                continue;
            }
            position = new Coordinate(x, y); // Initial position
            coordinates = generateShipCoordinates(position, length, dir);
            placement = toBeShip.placeShip(coordinates, length);
            if (placement == false)
            {
                System.out.println("Cannot place the ship there!");
                System.out.println("Try Again");
                continue;
            }
            else{
                player.addShip(toBeShip);
                player.updateBoard(coordinates);
                ships.remove(ship); // Remove the ship from the set
            }
        }
    player.printBoard();

    return player;  
    }

    public static void placeBotShips(Board botBoard, Set<String> ships) 
    {
        Random random = new Random();
        for (String shipName : ships) {
            boolean placed = false;
            while (!placed) {
                int x = random.nextInt(10);
                int y = random.nextInt(10);
                Direction dir = Direction.values()[random.nextInt(Direction.values().length)];
                int length = getShipLength(shipName); // Implement this based on your ship length logic

                Set<Coordinate> coordinates = generateShipCoordinates(new Coordinate(x, y), length, dir);
                Ship botShip = new Ship();
                if (botShip.placeShip(coordinates, length)) {
                    botBoard.addShip(botShip);
                    botBoard.updateBoard(coordinates);
                    placed = true;
                }
            }
        }
    }

    public static void botFire(Board playerBoard) 
    {
        Random random = new Random();
        int x = random.nextInt(10);
        int y = random.nextInt(10);
        playerBoard.fire(x, y);
    }

    public static void gameLoop(Board player, Board bot, Scanner scanner) {
        while (!player.allShipsSunk() && !bot.allShipsSunk()) {
            // Player's turn
            playerTurn(bot, scanner); // Player fires on bot's board
    
            // Bot's turn
            botFire(player); // Bot fires on player's board
    
            // Print the current state of the boards
            System.out.println("\nPlayer's Board:");
            player.printBoard();
            System.out.println("\nBot's Board (with player's shots):");
            bot.printBoardWithShots();
        }
    
        // Determine the winner
        if (bot.allShipsSunk()) {
            System.out.println("Congratulations! You won!");
        } else {
            System.out.println("Sorry, the bot won. Better luck next time!");
        }
    }
    
    private static void playerTurn(Board botBoard, Scanner scanner) {
        System.out.println("Your turn to fire! Enter coordinates to hit (x y):");
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        scanner.nextLine(); // Consume the leftover newline character
        botBoard.fire(y, x);
    }
    

    public static void main(String[] args) {
        Set<String> ships = new HashSet<>(Arrays.asList("Carrier", "Battleship", "Cruiser", "Submarine", "Destroyer"));
        Board player = starter(new HashSet<>(ships));
        Board bot = new Board("Bot");
        placeBotShips(bot, new HashSet<>(ships));
        Scanner objScanner = new Scanner(System.in);
       

        gameLoop(player, bot, objScanner);
    }
}
