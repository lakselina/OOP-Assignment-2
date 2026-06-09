package units.player;

import utils.Position;

public class PlayerFactory {

    public static Player getPlayer(String choice, Position startPos) {
        switch (choice) {
            case "Jon Snow":
                return new Warrior(startPos, "Jon Snow", 300, 300, 30, 4, 3);
            case "The Hound":
                return new Warrior(startPos, "The Hound", 400, 400, 20, 6, 5);
            case "Melisandre":
                return new Mage(startPos, "Melisandre", 100, 100, 5, 1, 300,30,15,5,6);
            case "Thoros of Myr":
                return new Mage(startPos, "Thoros of Myr", 250, 250, 25, 4, 150,20,20,3,4);
            case "Arya Stark":
                return new Rogue(startPos, "Arya Stark", 150, 150, 40, 2, 10);
            case "Bronn":
                return new Rogue(startPos, "Bronn", 250, 250, 35, 3, 50);
            case "Ygritte":
                return new Hunter(startPos, "Ygritte", 200, 200, 30, 2, 6);
            default:
                throw new IllegalArgumentException("Unknown character choice: " + choice);        }
    }
}