package board;

import units.Occupant;
import utils.Position;
import visitor.CellVisitor;

public class Floor extends Cell {

    private Occupant occupant;

    public Floor(Position position){
        super(position);
        this.occupant = null;
    }

    public void accept(CellVisitor visitor){
        visitor.visit(this);
    }

    public Occupant getOccupant(){
        return occupant;
    }

    public void setOccupant(Occupant occupant){
        this.occupant = occupant;
    }

    @Override
    public String toString(){
        if (this.occupant != null){
            return this.occupant.toString();
        }
        return ".";
    }
}
