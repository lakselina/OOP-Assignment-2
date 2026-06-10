package ui;
import game.GameController;
import units.player.*;
import utils.Position;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class CLI {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Error: Please provide a path to the levels directory as a command line argument.");
            return;
        }

        String dirPath = args[0];
        File dir = new File(dirPath);

        File[] files = dir.listFiles((d, name) -> name.endsWith(".txt"));
        if (files == null || files.length == 0) {
            System.out.println("No level files found in directory: " + dirPath);
            return;
        }

        List<String> levelPaths = new ArrayList<>();
        for (File f : files) {
            levelPaths.add(f.getAbsolutePath());
        }

        Scanner scanner = new Scanner(System.in);
        Player player = null;
        Position startPos = new Position(0, 0);

        while (player == null) {
            System.out.println("Select player:");
            System.out.println("1. Warrior - Jon Snow");
            System.out.println("2. Warrior - The Hound");
            System.out.println("3. Mage - Melisandre");
            System.out.println("4. Mage - Thoros of Myr");
            System.out.println("5. Rogue - Arya Stark");
            System.out.println("6. Rogue - Bronn");
            System.out.println("7. Hunter - Ygritte");

            String input = scanner.nextLine();

            try {
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 1: player = new Warrior(startPos, "Jon Snow", 300, 300, 30, 4, 3); break;
                    case 2: player = new Warrior(startPos, "The Hound", 400, 400, 20, 6, 5); break;
                    case 3: player = new Mage(startPos, "Melisandre", 100, 100, 5, 1, 300, 30, 15, 5, 6); break;
                    case 4: player = new Mage(startPos, "Thoros of Myr", 250, 250, 25, 4, 150, 20, 20, 3, 4); break;
                    case 5: player = new Rogue(startPos, "Arya Stark", 150, 150, 40, 2, 20); break;
                    case 6: player = new Rogue(startPos, "Bronn", 250, 250, 35, 3, 50); break;
                    case 7: player = new Hunter(startPos, "Ygritte", 220, 220, 30, 2, 6); break;
                    default:
                        System.out.println("Invalid choice. Please choose a number between 1 and 7.");
                }
            } catch (NumberFormatException e) {

                System.out.println("Invalid input. Please enter a valid number.");
            }
        }


        MessageCallback callback = msg -> System.out.println(msg);


        GameController gameManager = new GameController(null, player, levelPaths, callback);
        gameManager.play();
    }
}
