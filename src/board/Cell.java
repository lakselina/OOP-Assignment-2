package board;
import utils.Position;
import visitor.CellVisitor;

public abstract class Cell {
    protected Position position;

    public Cell(Position position){
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public abstract void accept(CellVisitor visitor);
}
