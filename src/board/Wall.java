package board;

import utils.Position;
import visitor.CellVisitor;

public class Wall extends Cell{

    public Wall(Position position){
        super(position);
    }

    public void accept(CellVisitor visitor){
        visitor.visit(this);
    }
}
