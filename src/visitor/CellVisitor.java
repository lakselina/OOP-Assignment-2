package visitor;

import board.Floor;
import board.Wall;

public interface CellVisitor {

    void visit(Wall wall);

    void visit(Floor floor);

}
