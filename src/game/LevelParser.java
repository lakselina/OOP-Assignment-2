package game;

import board.Cell;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class LevelParser {
    public static GameBoard load(String path) {
        List<String> lines = Files.readAllLines(Paths.get(path));

        int height = lines.size();
        int width = lines.get(0).length();
        Cell[][] grid = new Cell[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                char c = lines.get(y).charAt(x);
                grid[y][x] = CellFactory.create(c, new Position(x, y));
            }
        }
        return new GameBoard(grid);
    }
}
