package units;

import utils.Position;
import visitor.OccupantVisitor;

public abstract class Occupant {

    protected Position position;

    public Occupant(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public abstract void accept(OccupantVisitor visitor);

}
