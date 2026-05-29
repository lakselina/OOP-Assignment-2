package game;

import board.Cell;
import ui.MessageCallback;
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
    private MessageCallback messageCallback;

    public GameController(GameBoard board, Player player, MessageCallback callback) {
        this.board = board;
        this.player = player;
        this.scanner = new Scanner(System.in);
        this.messageCallback = callback;
    }


    public void play() {
        while (currentLevelIndex < levelFiles.size()) {

            loadLevel(levelFiles.get(currentLevelIndex));

            while (player.isAlive() && !board.isLevelComplete()) {

                System.out.println(board.toString());
                String input = scanner.nextLine();

                handlePlayerTurn(input);

                if (!player.isAlive()){
                    break;
                }

                if (board.isLevelComplete()){
                    break;
                }

                handleEnemiesTurn();

                if (!player.isAlive()){
                    break;
                }

                player.onTick();
                for (Enemy e : board.getEnemies()) {
                    e.onTick();
                }
            }

            if (!player.isAlive()) {
                messageCallback.send("Game Over!");
                return;
            }

            messageCallback.send("Level " + (currentLevelIndex + 1) + " Complete!");
            currentLevelIndex++;
        }

        messageCallback.send("Congratulations! You won the game!");
    }

    private void loadLevel(String levelPath) {
        this.board = LevelParser.load(levelPath);
        this.player.setPosition(board.getPlayerStartingPosition());
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
                messageCallback.send("Invalid move!");
        }
    }

    private void movePlayer(int dx, int dy) {
        Position newPos = player.getPosition().add(dx, dy);
        Cell targetCell = board.getCell(newPos);

        targetCell.accept(player);    }

    private void handleEnemiesTurn() {
        for (Enemy enemy : board.getEnemies()) {
            if (enemy.isAlive()) {
                enemy.processTurn(this.player, this.board);
            }
        }
    }
}
