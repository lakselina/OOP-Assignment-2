package game;

import board.Cell;
import units.Occupant;
import units.Unit;
import units.enemy.Enemy;
import utils.Position;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {

    private Cell[][] grid;
    private List<Unit> units;
    private int width;
    private int height;

    public GameBoard(Cell[][] grid) {
        this.grid = grid;
        this.height = grid.length;
        this.width = grid[0].length;
    }

    public Cell getCell(Position p) {
        if (isOutOfBounds(p)) {
            throw new IllegalArgumentException("Out of bound");
        }
        return grid[p.getY()][p.getX()];
    }

    public Occupant getOccupant(Position p) {
        Cell cell = getCell(p);
        if (cell != null && cell.isFloor()) {
            return cell.getOccupant();
        }
        return null;
    }

    public void setOccupant(Position p, Occupant o) {
        Cell cell = getCell(p);
        if (cell != null && cell.isFloor()) {
            cell.setOccupant(o);
        }
    }

    private boolean isOutOfBounds(Position p) {
        return p.getX() < 0 || p.getX() >= width || p.getY() < 0 || p.getY() >= height;
    }


    public List<Enemy> getEnemiesInRange(Position p, double range) {
        List<Enemy> enemiesInRange = new ArrayList<>();

        for (Unit u : this.units) {

            if (u instanceof Enemy) {
                Enemy enemy = (Enemy) u;

                double distance = p.distance(enemy.getPosition());
                if (distance <= range) {
                    enemiesInRange.add(enemy);
                }
            }
        }
        return enemiesInRange;
    }

    public Enemy findClosestEnemy(Position p, List<Enemy> enemies) {
        if (enemies == null || enemies.isEmpty()) {
            return null;
        }

        Enemy closestEnemy = null;
        double minDistance = Double.MAX_VALUE;
        for (Enemy enemy : enemies) {
            double currentDistance = p.distance(enemy.getPosition());
            if (currentDistance < minDistance) {
                minDistance = currentDistance;
                closestEnemy = enemy;
            }
        }

        return closestEnemy;
    }

    public List<Enemy> getEnemies(){
        List<Enemy> enemies = new ArrayList<>();

        for (Unit u: units){
            if (u instanceof Enemy){
                enemies.add((Enemy) u);
            }
        }

        return enemies;
    }
}

