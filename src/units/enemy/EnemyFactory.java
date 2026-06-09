package units.enemy;

import utils.Position;

public class EnemyFactory {

    public static Enemy create(char type, Position p) {
        switch (type) {
            case 's':
                return new Monster(p, "Gold Cloak", 's',80, 80, 8, 3, 3, 25);
            case 'k':
                return new Monster(p, "Knight", 'k',200, 200, 14, 8, 4, 50);
            case 'q':
                return new Monster(p, "Queen's Guard",'q',400,400, 20,15,5,100);
            case 'z':
                return new Monster(p, "Wright", 'z',600,600,30,15,3,100);
            case 'b':
                return new Monster(p, "Bear",'b',1000,1000,75,30,4,250);
            case 'g':
                return new Monster(p, "Giant",'g',1500,1500,100,40,5,500);
            case 'w':
                return new Monster(p, "White Walker", 'w',2000,2000,150,50,6,1000);
            case 'M':
                return new Boss(p, "The Mountain",'M',1000,1000,60,25,6,6,5);
            case 'C':
                return new Boss(p, "Queen Cersei",'C',100,100,10,10,1,1,8);
            case 'K':
                return new Boss(p, "Night's King", 'K',5000,5000,300,150,8,8,3);

            case 'B':
                return new Trap(p, "Bonus Trap", 'B', 1, 1, 1, 1,250,1,5);
            case 'Q':
                return new Trap(p, "Queen's Trap", 'Q', 250,250,50,10,100,3,7);
            case 'D':
                return new Trap(p, "Death Trap", 'D', 500,500,100,20,250,1,10);

            default:
                throw new IllegalArgumentException("Unknown enemy type: " + type);
        }
    }
}
