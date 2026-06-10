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

    public GameController(GameBoard board, Player player, List<String> levelFiles, MessageCallback callback) {
        this.board = board;
        this.player = player;
        this.levelFiles = levelFiles;
        this.scanner = new Scanner(System.in);
        this.messageCallback = callback;
        this.currentLevelIndex = 0;
    }


    public void play() {
        while (currentLevelIndex < levelFiles.size()) {

            loadLevel(levelFiles.get(currentLevelIndex));

            while (player.isAlive() && !board.isLevelComplete()) {

                messageCallback.send(board.toString());
                String input = scanner.nextLine();

                handlePlayerTurn(input);
                board.removeDeadEnemies();

                if (!player.isAlive()){
                    break;
                }

                if (board.isLevelComplete()){
                    break;
                }

                handleEnemiesTurn();
                board.removeDeadEnemies();

                if (!player.isAlive()){
                    break;
                }

                player.onTick();
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

        this.player.setMessageCallback(this.messageCallback);

        for (units.enemy.Enemy e : this.board.getEnemies()) {
            e.setMessageCallback(this.messageCallback);
        }

        Position startPos = board.getPlayerStartingPosition();
        this.player.setPosition(startPos);
        this.board.setPlayer(this.player);
        this.board.setOccupant(startPos, this.player);
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
            case "q":
                break;
            default:
                messageCallback.send("Invalid move!");
        }
    }

    private void movePlayer(int dx, int dy) {
        Position oldPos = player.getPosition();
        Position newPos = oldPos.add(dx, dy);
        Cell targetCell = board.getCell(newPos);

        targetCell.accept(player);

        if (!oldPos.equals(player.getPosition())) {
            board.setOccupant(oldPos, null);
            board.setOccupant(player.getPosition(), player);
        }
    }

    private void handleEnemiesTurn() {
        for (Enemy enemy : board.getEnemies()) {
            if (enemy.isAlive()) {
                enemy.onEnemyTurn(this.player, this.board);
            }
        }
    }
}
