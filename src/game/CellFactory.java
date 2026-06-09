package game;

import board.Cell;
import board.Floor;
import board.Wall;
import utils.Position;

public class CellFactory {

    public static Cell create(char c, Position p) {
        switch (c) {
            case '#':
                return new Wall(p);
            case '.':
            case '@':
                return new Floor(p);
            default:
                Floor enemyFloor = new Floor(p);

                return enemyFloor;
        }
    }
}
