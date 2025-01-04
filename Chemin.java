package logiciel.ecran;

import java.util.ArrayList;
import java.util.List;

public class Chemin {
    private List< Segment > segments;
    private int longueur;

    public Chemin( PositionTuile ... extremites ) {
        longueur = 0;
        segments = new ArrayList<>();
        for( int i = 1; i < extremites.length; ++ i ) {
            Segment s = Segment.construire( extremites[ i - 1 ].positionPixel(), extremites[ i ].positionPixel() );
            segments.add( s );
            longueur += s.longueur();
        }
    }

    public int nombreSegment() {
        return segments.size();
    }

    public Segment getSegment( int pos ) {
        return segments.get( pos );
    }

    public PositionPixel calculerPosition( int noSegment, double t ) {
        return segments.get( noSegment ).tween( t );
    }

    public int getLongueur() {
        return longueur;
    }
}
