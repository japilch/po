package agh.ics.oop;

public class Vector2d {
    public final int x;
    public final int y;
    public Vector2d(int x, int y){
        this.x = x;
        this.y = y;
    }
    public String toString(){
        return "("+Integer.toString(this.x)+","+Integer.toString(this.y)+")";
    }
    public boolean precedes(Vector2d other){
        if(this.x <= other.x && this.y <= other.y){
            return true;
        }
        return false;
    }
    public boolean follows(Vector2d other){
        if(this.x >= other.x && this.y >= other.y){
            return true;
        }
        return false;
    }
    public Vector2d add(Vector2d other){
        int x = this.x + other.x;
        int y = this.y + other.y;
        Vector2d returned = new Vector2d(x,y);
        return returned;
    }
    public Vector2d subtract(Vector2d other){
        int x = this.x - other.x;
        int y = this.y - other.y;
        Vector2d returned = new Vector2d(x,y);
        return returned;
    }
    public Vector2d upperRight(Vector2d other){
        int x = Math.max(this.x, other.x);
        int y = Math.max(this.y, other.y);
        Vector2d returned = new Vector2d(x,y);
        return returned;
    }
    public Vector2d lowerLeft(Vector2d other){
        int x = Math.min(this.x, other.x);
        int y = Math.min(this.y, other.y);
        Vector2d returned = new Vector2d(x,y);
        return returned;
    }
    public Vector2d opposite(){
        int x = -1*this.x;
        int y = -1*this.y;
        Vector2d returned = new Vector2d(x,y);
        return returned;
    }
    public boolean equals(Object other){
        if (this == other)
            return true;
        if (!(other instanceof Vector2d))
            return false;
        Vector2d that = (Vector2d) other;
        if(this.x == that.x && this.y == that.y){
            return true;
        }
        return false;
    }
    public int hashCode() {
        return 17 * x + 7 * y;
    }
}
