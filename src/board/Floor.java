package board;

import utils.Position;
import visitor.CellVisitor;

public class Floor extends Cell {

    public Floor(Position position){
        super(position);
    }

    public void accept(CellVisitor visitor){
        visitor.visit(this);
    }
}
