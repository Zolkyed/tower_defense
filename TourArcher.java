package logiciel.ecran;

import java.awt.*;
import java.util.List;

public class TourArcher extends Tour {
    public static final int PERIODE_RECHARGE_TIC = 15;
    public static final Tuile TOUR_BAS = new Tuile( Color.darkGray, Color.lightGray, BitMap.MUR );
    public static final Tuile TOUR_HAUT = new Tuile( Color.darkGray, Color.lightGray, BitMap.TOUR_DESSUS );



    private static final int NB_CARACTERISTIQUE = 3;

    private static final int DOMMAGE = 0;
    private static final int NB_TIR = 1;
    private static final int DISTANCE = 2;

    private static final int [] PRIX_AUGMENTATION_DOMMAGE = { 20, 30, 50 };
    private static final int [] DOMMAGE_PV = { 2, 3, 4, 5 };

    private static final int [] PRIX_AUGMENTATION_NB_TIR = { 40, 80 };
    private static final int [] NOMBRE_PROJECTILE_PAR_TIR = { 1, 2, 3 };

    private static final int [] PRIX_AUGMENTATION_DISTANCE = { 40, 80, 120 };
    private static final int [] DISTANCE_MAX_TIR_PIXEL = { 24, 36, 48 };

    {
        caracteristiques = new Caracteristique[NB_CARACTERISTIQUE];

        caracteristiques[ DOMMAGE ] =
                new Caracteristique( "dom", DOMMAGE_PV, PRIX_AUGMENTATION_DOMMAGE, Constantes.POSITION_CARACTERISTIQUE_Y );
        caracteristiques[ NB_TIR ] =
                new Caracteristique( "nb", NOMBRE_PROJECTILE_PAR_TIR, PRIX_AUGMENTATION_NB_TIR, Constantes.POSITION_CARACTERISTIQUE_Y + 2 );
        caracteristiques[ DISTANCE ] =
                new Caracteristique( "dis", DISTANCE_MAX_TIR_PIXEL, PRIX_AUGMENTATION_DISTANCE, Constantes.POSITION_CARACTERISTIQUE_Y + 4 );
    }

    private int recharge_tic = 0;

    public TourArcher( PositionTuile position ) {
        super( position );
    }

    private int tirer( List< Ennemi > ennemis ) {
        int argentGagne = 0;
        int j = 0;

        for( int k = 0; k < caracteristiques[ NB_TIR ].getValeur(); ++ k ) {
            try {
                while( j < ennemis.size() && caracteristiques[ DISTANCE ].getValeur() < ennemis.get( j ).getPositionPixel().distance( position.positionPixel() ) ) {
                    ++ j;
                }
                if( 0 <= j ) {
                    Ennemi ennemi = ennemis.get( j );
                    boolean estMort = ennemi.reduireVie( caracteristiques[ DOMMAGE ].getValeur() );
                    if( estMort ) {
                        argentGagne += ennemi.getValeurArgent();
                        ennemis.remove( j );
                    }
                }
            } catch( IndexOutOfBoundsException e ) {
                // l'ennemi choisi a deja atteint le chateau, donc ne peut tirer sur l'ennemi.
            }
        }
        return argentGagne;
    }

    @Override
    public int animer( List< Ennemi > ennemis ) {
        int argentGagne = 0;
        boolean pretTirer = false;

        ++ recharge_tic;

        if( PERIODE_RECHARGE_TIC <= recharge_tic) {
            pretTirer = true;
            recharge_tic = 0;
        }

        if( pretTirer ) {
            argentGagne = tirer( ennemis );
        }

        return argentGagne;
    }
}
