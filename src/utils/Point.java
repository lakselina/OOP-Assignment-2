package utils;

public class Point {

    private double x;
    private double y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Point(){
        this(0,0);
    }

    public double getX()
    {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double distance(Point other){
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    public double distance(){
        return this.distance(new Point());
    }

    public void move(double deltaX, double deltaY){
        this.x = this.x + deltaX;
        this.y = this.y + deltaY;
    }

    @Override
    public String toString(){
        return "(" + this.getX() + "," + this.getY() + ")";
    }

    @Override
    public boolean equals(Object other){
        boolean isEqual = false;
        if (other instanceof Point)
            isEqual = (this.getX() == ((Point)other).getX()) && (this.getY() == ((Point)other).getY());
        return isEqual;
    }

}
