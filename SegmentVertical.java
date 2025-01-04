package logiciel.ecran;

public class SegmentVertical extends Segment {
    public SegmentVertical( PositionPixel depart, PositionPixel arrive ) {
        super( depart, arrive );
    }

    @Override
    public int longueur() {
        return Math.abs( arrive.y() - depart.y() );
    }

    @Override
    public PositionPixel tween( double t ) {
        return ( depart.y() < arrive.y() )
                ? new PositionPixel( depart.x(), depart.y() + (int)t )
                : new PositionPixel( depart.x(), depart.y() - (int)t );
    }
}
