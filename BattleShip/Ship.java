import java.util.HashSet;
import java.util.Set;

public class Ship {
    private Set<Coordinate> shipPositions;

    public Ship() {
        shipPositions = new HashSet<>();
    }

    public boolean isValidPlacement(Set<Coordinate> positions, int shipLength) {
        // Check if all positions are within bounds and not diagonal
        return isWithinBounds(positions) && isContinuous(positions, shipLength) && !isDiagonal(positions);
    }

    private boolean isWithinBounds(Set<Coordinate> positions) {
        for (Coordinate position : positions) {
            if (position.x < 0 || position.x >= 10 || position.y < 0 || position.y >= 10) {
                return false;
            }
        }
        return true;
    }

    private boolean isContinuous(Set<Coordinate> positions, int shipLength) {
        // Additional logic to check continuity can be added here
        return positions.size() == shipLength;
    }

    private boolean isDiagonal(Set<Coordinate> positions) {
        int prevX = -1, prevY = -1;
        boolean first = true;
        boolean isHorizontal = true, isVertical = true;

        for (Coordinate position : positions) {
            if (first) {
                first = false;
            } else {
                if (position.x != prevX) isHorizontal = false;
                if (position.y != prevY) isVertical = false;
            }
            prevX = position.x;
            prevY = position.y;
        }

        return !(isHorizontal || isVertical);
    }

    public boolean placeShip(Set<Coordinate> positions, int shipLength) {
        if (isValidPlacement(positions, shipLength)) {
            shipPositions.addAll(positions);
            return true;
        }
        return false;
    }

    public boolean isHit(int x, int y) {
        return shipPositions.contains(new Coordinate(x, y));
    }
    
    public boolean isSunk(String[][] board) {
        for (Coordinate position : shipPositions) {
            if (!"X".equals(board[position.x][position.y])) {
                return false; // If any position of the ship is not hit, the ship is not sunk
            }
        }
        return true; // All positions of this ship are hit
    }
}


