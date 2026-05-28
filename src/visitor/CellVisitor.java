package visitor;

import board.Floor;
import board.Wall;

public interface CellVisitor {

    void visit(Wall w);
    void visit(Floor f);

}
