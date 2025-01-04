package logiciel.ecran;

public record PositionPixel( int x, int y ) {

    public int distance( PositionPixel p2 ) {
        int deltaX = x - p2.x;
        int deltaY = y - p2.y;
        double d = Math.sqrt( deltaX * deltaX + deltaY * deltaY );
        return (int) d;
    }
}
