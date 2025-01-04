package logiciel.ecran;

import java.awt.*;

public class Constantes {
    public static final int TAILLE_TUILE = 8;

    public static final Tuile EMPLACEMENT_TOUR = new Tuile( Color.gray, Color.white, BitMap.EMPLACEMENT );

    private static final Color COULEUR_CARACTERES = Color.yellow;
    private static final Color COULEUR_PV = Color.red;


    public static final int DIM_LARGEUR_TUILLE = 40;
    public static final int DIM_HAUTEUR_TUILLE = 25;
    public static final int DIM_LARGEUR = DIM_LARGEUR_TUILLE * TAILLE_TUILE;
    public static final int DIM_HAUTEUR = DIM_HAUTEUR_TUILLE * TAILLE_TUILE;
    public static final Tuile[] NIVEAU_CHIFFRES =
            {
                    new Tuile( Color.darkGray, COULEUR_CARACTERES, BitMap.CAR_0 ),
                    new Tuile( Color.darkGray, COULEUR_CARACTERES, BitMap.CAR_1 ),
                    new Tuile( Color.darkGray, COULEUR_CARACTERES, BitMap.CAR_2 ),
                    new Tuile( Color.darkGray, COULEUR_CARACTERES, BitMap.CAR_3 ),
                    new Tuile( Color.darkGray, COULEUR_CARACTERES, BitMap.CAR_4 ),
                    new Tuile( Color.darkGray, COULEUR_CARACTERES, BitMap.CAR_5 ),
                    new Tuile( Color.darkGray, COULEUR_CARACTERES, BitMap.CAR_6 ),
                    new Tuile( Color.darkGray, COULEUR_CARACTERES, BitMap.CAR_7 ),
                    new Tuile( Color.darkGray, COULEUR_CARACTERES, BitMap.CAR_8 ),
                    new Tuile( Color.darkGray, COULEUR_CARACTERES, BitMap.CAR_9 ),
            };
    public static final Tuile[] LETTRES_MIN =
            {
                    new Tuile( Color.darkGray, COULEUR_CARACTERES, BitMap.CAR_a ),
                    new Tuile( Color.darkGray, COULEUR_CARACTERES, BitMap.CAR_b ),
                    new Tuile( Color.darkGray, COULEUR_CARACTERES, BitMap.CAR_c ),
                    new Tuile( Color.darkGray, COULEUR_CARACTERES, BitMap.CAR_d ),
                    new Tuile( Color.darkGray, COULEUR_CARACTERES, BitMap.CAR_e ),
                    new Tuile( Color.darkGray, COULEUR_CARACTERES, BitMap.CAR_f ),
                    new Tuile( Color.darkGray, COULEUR_CARACTERES, BitMap.CAR_g ),
                    new Tuile( Color.darkGray, COULEUR_CARACTERES, BitMap.CAR_h ),
                    new Tuile( Color.darkGray, COULEUR_CARACTERES, BitMap.CAR_i ),
                    new Tuile( Color.darkGray, COULEUR_CARACTERES, BitMap.CAR_j ),
                    new Tuile( Color.darkGray, COULEUR_CARACTERES, BitMap.CAR_k ),
                    new Tuile( Color.darkGray, COULEUR_CARACTERES, BitMap.CAR_l ),
                    new Tuile( Color.darkGray, COULEUR_CARACTERES, BitMap.CAR_m ),
                    new Tuile( Color.darkGray, COULEUR_CARACTERES, BitMap.CAR_n ),
                    new Tuile( Color.darkGray, COULEUR_CARACTERES, BitMap.CAR_o ),
                    new Tuile( Color.darkGray, COULEUR_CARACTERES, BitMap.CAR_p ),
                    new Tuile( Color.darkGray, COULEUR_CARACTERES, BitMap.CAR_q ),
                    new Tuile( Color.darkGray, COULEUR_CARACTERES, BitMap.CAR_r ),
                    new Tuile( Color.darkGray, COULEUR_CARACTERES, BitMap.CAR_s ),
                    new Tuile( Color.darkGray, COULEUR_CARACTERES, BitMap.CAR_t ),
                    new Tuile( Color.darkGray, COULEUR_CARACTERES, BitMap.CAR_u ),
                    new Tuile( Color.darkGray, COULEUR_CARACTERES, BitMap.CAR_v ),
                    new Tuile( Color.darkGray, COULEUR_CARACTERES, BitMap.CAR_w ),
                    new Tuile( Color.darkGray, COULEUR_CARACTERES, BitMap.CAR_x ),
                    new Tuile( Color.darkGray, COULEUR_CARACTERES, BitMap.CAR_y ),
                    new Tuile( Color.darkGray, COULEUR_CARACTERES, BitMap.CAR_z ),
            };

    public static final Tuile[] PV_ENNEMI =
            {
                    new Tuile(Color.darkGray, COULEUR_PV, BitMap.PV_0_8 ),
                    new Tuile(Color.darkGray, COULEUR_PV, BitMap.PV_1_8 ),
                    new Tuile(Color.darkGray, COULEUR_PV, BitMap.PV_2_8 ),
                    new Tuile(Color.darkGray, COULEUR_PV, BitMap.PV_3_8 ),
                    new Tuile(Color.darkGray, COULEUR_PV, BitMap.PV_4_8 ),
                    new Tuile(Color.darkGray, COULEUR_PV, BitMap.PV_5_8 ),
                    new Tuile(Color.darkGray, COULEUR_PV, BitMap.PV_6_8 ),
                    new Tuile(Color.darkGray, COULEUR_PV, BitMap.PV_7_8 ),
                    new Tuile(Color.darkGray, COULEUR_PV, BitMap.PV_8_8 ),
            };

    public static final int FACTEUR_PV = PV_ENNEMI.length - 1;

    public static final Tuile DOLLAR = new Tuile( Color.darkGray, COULEUR_CARACTERES, BitMap.CAR_DOLLAR );
    
    public static final Tuile V_LAPIN = new Tuile( new Color( 128, 0, 128 ), BitMap.LAPIN );
    public static final Tuile V_TORTUE = new Tuile( new Color( 128, 0, 128 ), BitMap.TORTUE );
    public static final Tuile V_BOSS = new Tuile( new Color( 128, 0, 128 ), BitMap.BOSS );

    public static final Tuile B_LAPIN = new Tuile( new Color( 0, 0, 255 ), BitMap.LAPIN );
    public static final Tuile B_TORTUE = new Tuile( new Color( 0, 0, 255 ), BitMap.TORTUE );
    public static final Tuile B_BOSS = new Tuile( new Color( 0, 0, 255 ), BitMap.BOSS );
    
    public static final Tuile R_LAPIN = new Tuile( new Color( 255, 0, 0 ), BitMap.LAPIN );
    public static final Tuile R_TORTUE = new Tuile( new Color( 255, 0, 0 ), BitMap.TORTUE );
    public static final Tuile R_BOSS = new Tuile( new Color( 255, 0, 0 ), BitMap.BOSS );
    
    public static final Tuile E_LAPIN = new Tuile( new Color( 200, 105, 10 ), BitMap.LAPIN );
    public static final Tuile E_TORTUE = new Tuile( new Color( 100, 130, 32 ), BitMap.TORTUE );
    public static final Tuile E_BOSS = new Tuile( new Color( 150, 105, 10 ), BitMap.BOSS );
    public static final Tuile SOUSLIGNE_TOUR = new Tuile( new Color( 10, 100, 255 ), BitMap.SOUSLIGNE );
    public static final Tuile BOUTON_FERMER = new Tuile( Color.lightGray, BitMap.FERMER );
    public static final Tuile BOUTON_PLAY_OFF = new Tuile( Color.red, BitMap.PLAY );
    public static final Tuile BOUTON_PLAY_ON = new Tuile( Color.green, BitMap.PLAY );
    public static final Tuile BOUTON_STOP_OFF = new Tuile( Color.red, BitMap.STOP );
    public static final Tuile BOUTON_STOP_ON = new Tuile( Color.green, BitMap.STOP );
    public static final Tuile BOUTON_AUGMENTER = new Tuile( Color.green, BitMap.AUGMENTER );
    public static final int TEMPS_TIC_MS = 1000 / 25;
    public static final int VIE_CHATEAU = 10;

    public static final int POSITION_ARGENT_Y = 3;
    public static final PositionTuile POSITION_ARGENT = new PositionTuile( DIM_LARGEUR_TUILLE - 2, POSITION_ARGENT_Y );
    public static final PositionTuile POSITION_DOLLAR = new PositionTuile( DIM_LARGEUR_TUILLE - 1, POSITION_ARGENT_Y );

    public static final int POSITION_ACHAT_TOUR_Y = 10;
    public static final int POSITION_CARACTERISTIQUE_Y = 10;

    public static final int POSITION_CARACTERISTIQUE_X_NOM = 33;
    public static final int POSITION_CARACTERISTIQUE_X_NOMBRE = DIM_LARGEUR_TUILLE - 2;
    public static final int POSITION_CARACTERISTIQUE_X_BOUTON = DIM_LARGEUR_TUILLE - 1;
}
