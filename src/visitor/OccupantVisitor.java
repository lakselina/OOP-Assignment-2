package visitor;

import units.enemy.Enemy;
import units.player.Player;

public interface OccupantVisitor {

    void visit(Player p);
    void visit(Enemy e);
}
