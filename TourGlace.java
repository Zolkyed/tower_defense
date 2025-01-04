package logiciel.ecran;

import java.awt.*;
import java.util.List;

public class TourGlace extends Tour {
    public static final int PERIODE_RECHARGE_TIC = 1; 
    public static final Tuile TOUR_GLACE_BAS = new Tuile(Color.blue, Color.cyan, BitMap.MUR);
    public static final Tuile TOUR_GLACE_HAUT = new Tuile(Color.blue, Color.cyan, BitMap.TOUR_DESSUS);



    private static final int NB_CARACTERISTIQUE = 2;

    private static final int RAYON = 0;
    private static final int REDUCTION = 1;

    private static final int[] PRIX_AUGMENTATION_RAYON = {40, 60};
    private static final int[] RAYON_ACTION_PIXEL = {24, 32, 40};

    private static final int[] PRIX_AUGMENTATION_REDUCTION = {60};
    private static final int[] VALEUR_REDUCTION = {20, 40};

    {
        caracteristiques = new Caracteristique[NB_CARACTERISTIQUE];

        caracteristiques[RAYON] = 
                new Caracteristique("ray", RAYON_ACTION_PIXEL, PRIX_AUGMENTATION_RAYON, Constantes.POSITION_CARACTERISTIQUE_Y);
        caracteristiques[REDUCTION] = 
                new Caracteristique("red", VALEUR_REDUCTION, PRIX_AUGMENTATION_REDUCTION, Constantes.POSITION_CARACTERISTIQUE_Y + 2);
    }
    
    private int recharge_tic = 0;

    public TourGlace(PositionTuile position) {
        super(position);
    }

    private void appliquerEffetRalentissement(Ennemi ennemi, int toursAffectantes, double reduction) {
        double vitesseInitiale = ennemi.getVitesse();

        if (toursAffectantes > ennemi.getToursAffectants()) {
            double vitesseReduction = vitesseInitiale * (1 - (reduction / 100.0));
            ennemi.setVitesse(vitesseReduction);
            ennemi.setRalenti(true);
        }
        else if (toursAffectantes < ennemi.getToursAffectants()) {
            double enleverReduction = vitesseInitiale / (1 - (reduction / 100.0));
            ennemi.setVitesse(enleverReduction);
        }

        ennemi.setToursAffectants(toursAffectantes);

        if (ennemi.getToursAffectants() == 0) {
            ennemi.setRalenti(false);
        }
    }

    private void ralentir(List<Ennemi> ennemis) {
        int rayon = caracteristiques[RAYON].getValeur();
        int reduction = caracteristiques[REDUCTION].getValeur();

        for (Ennemi ennemi : ennemis) {
            try {
                PositionPixel positionEnnemi = ennemi.getPositionPixel();
                int toursAffectantes = 0;

                List<TourGlace> toursGlaceActives = Jeu.getToursGlace();
                for (TourGlace tour : toursGlaceActives) {
                    PositionPixel positionTour = tour.position.positionPixel();
                    double distance = positionEnnemi.distance(positionTour);

                    if (distance <= rayon) {
                        toursAffectantes++;
                    }
                }

                if (toursAffectantes != ennemi.getToursAffectants()) {
                    appliquerEffetRalentissement(ennemi, toursAffectantes, reduction);
                }

            } catch (IndexOutOfBoundsException e) {

            }
        }
    }

    @Override
    public int animer(List<Ennemi> ennemis) {
        int argentGagne = 0;
        boolean pretRalentir = false;

        ++recharge_tic;

        if( PERIODE_RECHARGE_TIC <= recharge_tic) {
            pretRalentir = true;
            recharge_tic = 0;
        }

        if( pretRalentir ) {
            ralentir( ennemis );
        }

        return argentGagne;
    }
}
