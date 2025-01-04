package logiciel.ecran;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class EnnemiEnflamme {
    private Ennemi ennemi;
    private int duree;

    public EnnemiEnflamme(Ennemi ennemi, int duree) {
        this.ennemi = ennemi;
        this.duree = duree;
    }

    public Ennemi getEnnemi() {
        return ennemi;
    }

    public int getDuree() {
        return duree;
    }

    public void decrementerDuree() {
        if (duree > 0) {
            duree--;
        }
    }
}
