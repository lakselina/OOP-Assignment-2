package visitor;

import units.enemy.Enemy;
import units.player.*;
import utils.Position;

public interface OccupantVisitor {

    void visit(Player p);
    void visit(Enemy e);

    void visit(Warrior w);
    void visit(Mage m);
    void visit(Rogue r);
    void visit(Hunter h);
}
