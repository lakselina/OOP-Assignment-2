package units;

import utils.Point;
import visitor.OccupantVisitor;

public abstract class Occupant {

    protected Point position;

    public Occupant(Point position) {
        this.position = position;
    }

    public abstract void accept(OccupantVisitor visitor);

    public Point getPosition() { return position; }

}
