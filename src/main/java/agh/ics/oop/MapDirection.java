package agh.ics.oop;
public enum MapDirection {
    NORTH,
    SOUTH,
    WEST,
    EAST;
    public String toString(){
        switch(this){
            case NORTH:
                return "POLNOC";
            case SOUTH:
                return "POLUDNIE";
            case EAST:
                return "WSCHOD";
            case WEST:
                return "ZACHOD";
        }
        return "";
    }
    public MapDirection next(){
        switch(this){
            case NORTH:
                return EAST;
            case SOUTH:
                return WEST;
            case EAST:
                return SOUTH;
            case WEST:
                return NORTH;
        }
        return NORTH;
    }
    public MapDirection previous(){
        switch(this){
            case NORTH:
                return WEST;
            case SOUTH:
                return EAST;
            case EAST:
                return NORTH;
            case WEST:
                return SOUTH;
        }
        return NORTH;
    }
    public Vector2d toUnitVector(){
        Vector2d vector = new Vector2d(0,0);
        switch(this){
            case NORTH:
                vector = new Vector2d(0,1);
                break;
            case SOUTH:
                vector = new Vector2d(0,-1);
                break;
            case EAST:
                vector = new Vector2d(1,0);
                break;
            case WEST:
                vector = new Vector2d(-1,0);
                break;
        }
        return vector;
    }
}