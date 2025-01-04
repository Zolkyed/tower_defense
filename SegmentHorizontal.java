package logiciel.ecran;

public class SegmentHorizontal extends Segment {
    public SegmentHorizontal( PositionPixel depart, PositionPixel arrive ) {
        super( depart, arrive );
    }

    @Override
    public int longueur() {
        return Math.abs( arrive.x() - depart.x() );
    }

    @Override
    public PositionPixel tween( double t ) {
        return ( depart.x() < arrive.x() )
                ? new PositionPixel( depart.x() + (int)t, depart.y() )
                : new PositionPixel( depart.x() - (int)t, depart.y() );
    }
}
