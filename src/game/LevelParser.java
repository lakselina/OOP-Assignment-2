package game;

import board.Cell;
import units.Occupant;
import units.enemy.Enemy;
import units.player.*;
import utils.Position;
import visitor.OccupantVisitor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class LevelParser {
    public static GameBoard load(String path) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));

            int height = lines.size();
            int width = lines.get(0).length();
            Cell[][] grid = new Cell[height][width];

            Position startingPosition = null;

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    char c = lines.get(y).charAt(x);
                    grid[y][x] = CellFactory.create(c, new Position(x, y));

                    if (c == '@') {
                        startingPosition = new Position(x, y);
                    }
                }
            }

            GameBoard board = new GameBoard(grid);
            if (startingPosition != null) {
                board.setPlayerStartingPosition(startingPosition);
            }

            OccupantVisitor initVisitor = new OccupantVisitor() {
                @Override public void visit(Player p) { board.addUnit(p); }
                @Override public void visit(Enemy e) { board.addEnemy(e); }

                @Override public void visit(Warrior w) { board.addUnit(w); }
                @Override public void visit(Mage m) { board.addUnit(m); }
                @Override public void visit(Rogue r) { board.addUnit(r); }
                @Override public void visit(Hunter h) { board.addUnit(h); }
            };

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    Occupant occ = grid[y][x].getOccupant();
                    if (occ != null) {
                        occ.accept(initVisitor);
                    }
                }
            }

            return board;

        } catch (IOException e) {
            throw new RuntimeException("Failed to load level file: " + path, e);
        }
    }
}