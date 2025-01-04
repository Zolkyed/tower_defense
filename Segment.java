package logiciel.ecran;

public abstract class Segment {
    protected PositionPixel depart;
    protected PositionPixel arrive;

    public Segment( PositionPixel depart, PositionPixel arrive ) {
        this.depart = depart;
        this.arrive = arrive;
    }

    public static Segment construire( PositionPixel depart, PositionPixel arrive ) {
        return ( depart.y() == arrive.y() )
                ? new SegmentHorizontal( depart, arrive )
                : new SegmentVertical( depart, arrive );
    }

    public abstract int longueur();

    public abstract PositionPixel tween( double t );
}
