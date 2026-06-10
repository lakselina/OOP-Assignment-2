package game;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import utils.Position;

public class LevelParserTests {

    @TempDir
    Path tempDir;

    @Test
    public void testLoadValidLevelStructure() throws IOException {
        Path levelFile = tempDir.resolve("test_level_basic.txt");
        List<String> fileContent = Arrays.asList(
                "####",
                "#@.#",
                "####"
        );
        Files.write(levelFile, fileContent);

        GameBoard board = LevelParser.load(levelFile.toString());

        assertNotNull(board, "Board should not be null after successful load");

        Position expectedStart = new Position(1, 1);
        assertEquals(expectedStart, board.getPlayerStartingPosition(), "Should correctly identify player starting position");
    }

    @Test
    public void testLoadLevelWithEnemies() throws IOException {
        Path levelFile = tempDir.resolve("test_level_enemies.txt");
        List<String> fileContent = Arrays.asList(
                "#####",
                "#@sD#",
                "#####"
        );
        Files.write(levelFile, fileContent);

        GameBoard board = LevelParser.load(levelFile.toString());

        assertNotNull(board.getEnemies(), "Enemies list should not be null");
        assertEquals(2, board.getEnemies().size(), "Should detect exactly 2 enemies (one monster and one trap)");
    }

    @Test
    public void testLoadLevelWithoutPlayer() throws IOException {
        Path levelFile = tempDir.resolve("test_level_no_player.txt");
        List<String> fileContent = Arrays.asList(
                "###",
                "#.#",
                "###"
        );
        Files.write(levelFile, fileContent);

        GameBoard board = LevelParser.load(levelFile.toString());

        assertNotNull(board);
        assertNull(board.getPlayerStartingPosition(), "Starting position should be null if '@' is missing");
    }

    @Test
    public void testLoadNonExistentFileThrowsException() {
        String invalidPath = "this/path/does/not/exist/level_999.txt";

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            LevelParser.load(invalidPath);
        });

        assertTrue(exception.getMessage().contains("Failed to load level file"),
                "Exception message should match the custom message in LevelParser");
    }
}