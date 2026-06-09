package board;
import units.Occupant;
import utils.Position;
import visitor.CellVisitor;

public abstract class Cell {
    protected Position position;
    protected Occupant occupant;


    public Cell(Position position){
        this.position = position;
        this.occupant = null;
    }

    public Position getPosition() {
        return position;
    }

    public void setOccupant(Occupant occupant) {
        this.occupant = occupant;
    }

    public Occupant getOccupant() {
        return occupant;
    }

    public abstract void accept(CellVisitor visitor);


}
