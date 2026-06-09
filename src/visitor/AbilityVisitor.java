package visitor;

import game.GameBoard;
import units.player.*;
import utils.Position;
import units.enemy.Enemy;

import java.util.List;
import java.util.Random;

public class AbilityVisitor implements OccupantVisitor {

    private GameBoard board;

    public AbilityVisitor(GameBoard board) {
        this.board = board;
    }

    @Override
    public void visit(Player p) {}
    @Override
    public void visit(Enemy e) {}

    @Override
    public void visit(Warrior w) {
        int heal = 10 * w.getDefensePoints();
        w.setHealthAmount(Math.min(w.getHealthPool(), w.getHealthAmount() + heal));

        List<Enemy> enemiesInRange = board.getEnemiesInRange(w.getPosition(), 3);

        if (!enemiesInRange.isEmpty()) {
            Enemy target = enemiesInRange.get(new Random().nextInt(enemiesInRange.size()));
            int damage = (int) (w.getHealthPool() * 0.1);
            target.defend(damage);
        }
    }

    @Override
    public void visit(Mage m) {
        List<Enemy> enemiesInRange = board.getEnemiesInRange(m.getPosition(), m.getAbilityRange());

        for (int i = 0; i < m.getHitsCount(); i++) {
            if (!enemiesInRange.isEmpty()) {
                Enemy target = enemiesInRange.get((int) (Math.random() * enemiesInRange.size()));
                m.castAbility(target);
            }
        }
    }

    @Override
    public void visit(Rogue r) {
        List<Enemy> targets = board.getEnemiesInRange(r.getPosition(), 1);
        for(Enemy e : targets) {
            e.defend(r.getAttackPoints());
        }
    }

    @Override
    public void visit(Hunter h) {
        List<Enemy> enemiesInRange = board.getEnemiesInRange(h.getPosition(), h.getAbilityRange());
        Enemy closest = board.findClosestEnemy(h.getPosition(), enemiesInRange);
        if (closest != null) {
            closest.defend(h.getAttackPoints());
        }
    }
}
