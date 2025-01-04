package logiciel.ecran;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Ennemi {
    private Chemin chemin;
    private int noSegment;
    private double distance;

    private double vitesse;
    private int pointVie;
    private int pointVieMax;

    // Affichage
    private Tuile image;
    private Tuile image1;
    private Tuile image2;
    private Tuile image3;

    private int valeurArgent;
    
    // Etats
    private boolean ralenti;
    private boolean enflammer;

    private int toursAffectants;

    public Ennemi( Chemin chemin, double vitesse, int pointVie, int valeurArgent, Tuile image, Tuile image1, Tuile image2, Tuile image3 ) {
        this.chemin = chemin;
        this.vitesse = vitesse;
        this.pointVie = pointVie;
        this.pointVieMax = pointVie;
        this.valeurArgent = valeurArgent;
        this.image = image;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.distance = 0.0;
        this.noSegment = 0;
        this.ralenti = false;
        this.enflammer = false;
        this.toursAffectants = 0;
    }

    public Ennemi( Ennemi original ) {
        chemin = original.chemin;
        noSegment = original.noSegment;
        distance = original.distance;
        vitesse = original.vitesse;
        pointVie = original.pointVie;
        pointVieMax = original.pointVieMax;
        this.valeurArgent = original.valeurArgent;
        image = original.image;
        image1 = original.image1;
        image2 = original.image2;
        image3 = original.image3;
    }

    public static int comparer( Ennemi e1, Ennemi e2 ) {
        return Integer.compare( e1.distanceChateau(), e2.distanceChateau() );
    }

    public boolean reduireVie( int dommage ) {
        pointVie -= dommage;
        return pointVie <= 0;
    }

    public boolean aAtteintChateau() {
        return chemin.nombreSegment() <= noSegment;
    }

    public void avancer() {
        if( noSegment < chemin.nombreSegment() ) {
            distance += vitesse;
            int longueur = chemin.getSegment( noSegment ).longueur();
            if( longueur < distance ) {
                distance -= longueur;
                ++ noSegment;
            }
        }
    }

    public int distanceChateau() {
        return chemin.getLongueur() - ( (int)distance );
    }

    public PositionPixel getPositionPixel() {
        return chemin.calculerPosition( noSegment, distance );
    }

    public void afficher( Graphics2D g2, AffineTransform affineTransform ) {
        if( noSegment < chemin.nombreSegment() ) {
            AffineTransform pCurseur = (AffineTransform) affineTransform.clone();
            PositionPixel position = getPositionPixel();
            pCurseur.translate( position.x(), position.y() );

            if (enflammer && ralenti) {
                g2.drawImage(image3, pCurseur, null);
            } else if (ralenti) {
                g2.drawImage(image1, pCurseur, null);
            } else if (enflammer) {
                g2.drawImage(image2, pCurseur, null);
            } else {
                g2.drawImage(image, pCurseur, null);   
            }

            pCurseur.translate( 0, - 2 );
            int rPV = ( pointVie * Constantes.FACTEUR_PV ) / pointVieMax;
            g2.drawImage( Constantes.PV_ENNEMI[ rPV ], pCurseur, null );
        }
    }

    public int getValeurArgent() {
        return valeurArgent;
    }

    public double getVitesse() {
        return vitesse;
    }

    public boolean isRalenti() {
        return ralenti;
    }

    public boolean isEnflammer() {
        return enflammer;
    }

    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
    }

    public void setRalenti(boolean ralenti) {
        this.ralenti = ralenti;
    }

    public void setEnflammer(boolean enflammer) {
        this.enflammer = enflammer;
    }
    
    public int getToursAffectants() {
        return toursAffectants;
    }
    public void setToursAffectants(int toursAffectants) {
        this.toursAffectants = toursAffectants;
    }
}
