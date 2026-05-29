package game;

import units.player.Player;
import units.enemy.Enemy;
import utils.Position;
import visitor.AbilityVisitor;

import java.util.List;
import java.util.Scanner;

public class GameController {
    private GameBoard board;
    private Player player;
    private Scanner scanner;
    private List<String> levelFiles;
    private int currentLevelIndex;

    public GameController(GameBoard board, Player player) {
        this.board = board;
        this.player = player;
        this.scanner = new Scanner(System.in);
    }

    public void play() {
        boolean gameRunning = true;

        while (gameRunning) {
            System.out.println(board.toString());

            String input = scanner.nextLine();
            handlePlayerTurn(input);

            if (!player.isAlive()) {
                System.out.println("Game Over!");
                gameRunning = false;
                break;
            }

            handleEnemiesTurn();

            if (!player.isAlive()) {
                System.out.println("Game Over!");
                gameRunning = false;
                break;
            }

            player.onTick();
            for (Enemy e : board.getEnemies()) {
                e.onTick();
            }
        }

        if (player.isAlive()) {
            System.out.println("Level Complete!");
            // כאן תכניסי לוגיקה לטעינת השלב הבא (LevelParser)
        }
    }

    private void handlePlayerTurn(String input) {
        AbilityVisitor visitor = new AbilityVisitor(this.board);

        switch (input.toLowerCase()) {
            case "w":
                movePlayer(0, -1);
                break;
            case "s":
                movePlayer(0, 1);
                break;
            case "a":
                movePlayer(-1, 0);
                break;
            case "d":
                movePlayer(1, 0);
                break;
            case "e":
                player.castAbility(visitor);
                break;
            default:
                System.out.println("Invalid move!");
        }
    }

    private void movePlayer(int dx, int dy) {
        Position newPos = player.getPosition().add(dx, dy);
    }

    private void handleEnemiesTurn() {
        for (Enemy enemy : board.getEnemies()) {
            if (enemy.isAlive()) {
                enemy.processTurn(this.player, this.board);
            }
        }
    }
}
