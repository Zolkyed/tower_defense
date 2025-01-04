package logiciel.ecran;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TourFeu extends Tour {
    public static final int PERIODE_RECHARGE_TIC = 15;
    public static final Tuile TOUR_FEU_BAS = new Tuile(Color.RED, Color.BLACK, BitMap.MUR);
    public static final Tuile TOUR_FEU_HAUT = new Tuile(Color.RED, Color.BLACK, BitMap.TOUR_DESSUS);

    private static final int NB_CARACTERISTIQUE = 2;

    private static final int DISTANCE = 0;
    private static final int DUREE = 1;

    private static final int[] PRIX_AUGMENTATION_DISTANCE = {40, 80};
    private static final int[] DISTANCE_MAX_TIR_PIXEL = {20, 30, 50};

    private static final int[] PRIX_AUGMENTATION_REDUCTION = {20, 30};
    private static final int[] VALEUR_REDUCTION = {3, 5, 7};

    {
        caracteristiques = new Caracteristique[NB_CARACTERISTIQUE];

        caracteristiques[DISTANCE] = new Caracteristique(
                "dis", DISTANCE_MAX_TIR_PIXEL, PRIX_AUGMENTATION_DISTANCE, Constantes.POSITION_CARACTERISTIQUE_Y);
        caracteristiques[DUREE] = new Caracteristique(
                "dur", VALEUR_REDUCTION, PRIX_AUGMENTATION_REDUCTION, Constantes.POSITION_CARACTERISTIQUE_Y + 2);
    }

    private int recharge_tic  = 0;
    private List<EnnemiEnflamme> ennemisEnflammes = new ArrayList<>();

    public TourFeu(PositionTuile position) {
        super(position);
    }

    private Ennemi trouverEnnemi(List<Ennemi> ennemis) {
        for (Ennemi ennemi : ennemis) {
            double distanceEnnemi = ennemi.getPositionPixel().distance(position.positionPixel());
            if (distanceEnnemi <= caracteristiques[DISTANCE].getValeur() && !ennemi.isEnflammer()) {
                return ennemi;
            }
        }
        return null;
    }

    private void appliquerEffet(Ennemi ennemi) {
        int duree = caracteristiques[DUREE].getValeur();
        ennemi.setEnflammer(true);
        ennemi.reduireVie(1);
        ennemisEnflammes.add(new EnnemiEnflamme(ennemi, duree - 1));
    }

    @Override
    public int animer(List<Ennemi> ennemis) {
        int argentGagne = 0;
        boolean pretEnflammer = false;

        ++recharge_tic;

        if (recharge_tic >=  PERIODE_RECHARGE_TIC) {
            pretEnflammer = true;
            recharge_tic = 0;
        }

        if (pretEnflammer) {
            Ennemi ennemi = trouverEnnemi(ennemis);
            if (ennemi != null) {
                appliquerEffet(ennemi);
            }
        }

        Iterator<EnnemiEnflamme> iterator = ennemisEnflammes.iterator();
        while (iterator.hasNext()) {
            EnnemiEnflamme enflamme = iterator.next();
            if (recharge_tic % 4 == 0) {
                try {
                    Ennemi ennemi = enflamme.getEnnemi();
                    boolean estMort = ennemi.reduireVie(1);
                    if (estMort) {
                        argentGagne += ennemi.getValeurArgent();
                        ennemis.remove(ennemi);
                    }

                    enflamme.decrementerDuree();

                    if (enflamme.getDuree() <= 0) {
                        iterator.remove();
                        ennemi.setEnflammer(false);
                    }
                } catch (IndexOutOfBoundsException e) {
                }
            }
        }
        return argentGagne;
    }
}
