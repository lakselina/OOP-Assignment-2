package visitor;

import game.GameBoard;
import units.enemy.Enemy;
import units.player.Mage;
import units.player.Player;
import units.player.Rogue;
import units.player.Warrior;

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
            target.takeDamage(damage);
        }
    }

    @Override
    public void visit(Mage m) {
        List<Enemy> enemiesInRange = board.getEnemiesInRange(m.getPosition(), m.getAbilityRange());

        for (Enemy enemy : enemiesInRange) {
            enemy.takeDamage(m.getSpellPower());
        }
    }

    @Override
    public void visit(Rogue r) {
        List<Enemy> targets = board.getEnemiesInRange(r.getPosition(), 1);
        for(Enemy e : targets) {
            e.takeDamage(r.getAttackPoints());
        }
    }
}
