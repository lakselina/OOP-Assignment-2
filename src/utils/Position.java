package utils;

public class Position {

    private int x;
    private int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Position(){
        this(0,0);
    }

    public int getX()
    {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int distance(Position other){
        return (int) Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    public int distance(){
        return this.distance(new Position());
    }

    public void move(int deltaX, int deltaY){
        this.x = this.x + deltaX;
        this.y = this.y + deltaY;
    }

    public Position add(int dx, int dy) {
        return new Position(this.x + dx, this.y + dy);
    }

    @Override
    public String toString(){
        return "(" + this.getX() + "," + this.getY() + ")";
    }

    @Override
    public boolean equals(Object other){
        boolean isEqual = false;
        if (other instanceof Position)
            isEqual = (this.getX() == ((Position)other).getX()) && (this.getY() == ((Position)other).getY());
        return isEqual;
    }
}
