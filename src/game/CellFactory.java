package game;

import board.Cell;
import board.Floor;
import board.Wall;
import units.enemy.Monster;
import units.enemy.Trap;
import utils.Position;

public class CellFactory {
    public static Cell create(char c, Position p) {
        Floor floor;

        switch (c) {
            case '#':
                return new Wall(p);
            case '.':
                return new Floor(p);
            case '@':
                return new Floor(p);

            case 's':
                floor = new Floor(p);
                floor.setOccupant(new Monster(p, "Gold Cloak", 's', 80, 80, 8, 3, 25, 3));
                return floor;
            case 'k':
                floor = new Floor(p);
                floor.setOccupant(new Monster(p, "Knight", 'k', 200, 200, 14, 8, 50, 4));
                return floor;
            case 'q':
                floor = new Floor(p);
                floor.setOccupant(new Monster(p, "Queen's Guard", 'q', 400, 400, 20, 15, 100, 5));
                return floor;
            case 'z':
                floor = new Floor(p);
                floor.setOccupant(new Monster(p, "Wright", 'z', 600, 600, 30, 15, 100, 3));
                return floor;
            case 'b':
                floor = new Floor(p);
                floor.setOccupant(new Monster(p, "Bear", 'b', 1000, 1000, 75, 30, 250, 4));
                return floor;
            case 'g':
                floor = new Floor(p);
                floor.setOccupant(new Monster(p, "Giant", 'g', 1500, 1500, 100, 40, 500, 5));
                return floor;
            case 'w':
                floor = new Floor(p);
                floor.setOccupant(new Monster(p, "White Walker", 'w', 2000, 2000, 150, 50, 1000, 6));
                return floor;
            case 'M':
                floor = new Floor(p);
                floor.setOccupant(new Monster(p, "The Mountain", 'M', 1000, 1000, 60, 25, 500, 6));
                return floor;
            case 'C':
                floor = new Floor(p);
                floor.setOccupant(new Monster(p, "Queen Cersei", 'C', 100, 100, 10, 10, 1000, 1));
                return floor;
            case 'K':
                floor = new Floor(p);
                floor.setOccupant(new Monster(p, "Night's King", 'K', 5000, 5000, 300, 150, 5000, 8));
                return floor;

            case 'B':
                floor = new Floor(p);
                floor.setOccupant(new Trap(p, "Bonus Trap", 'B', 1, 1, 1, 1, 250, 1, 5));
                return floor;
            case 'Q':
                floor = new Floor(p);
                floor.setOccupant(new Trap(p, "Queen's Trap", 'Q', 250, 250, 50, 10, 100, 3, 7));
                return floor;
            case 'D':
                floor = new Floor(p);
                floor.setOccupant(new Trap(p, "Death Trap", 'D', 500, 500, 100, 20, 250, 1, 10));
                return floor;

            default:
                throw new IllegalArgumentException("Unknown character in level file: " + c);
        }
    }
}
