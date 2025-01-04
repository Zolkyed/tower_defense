package logiciel.ecran;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.List;

public abstract class Tour {
    protected PositionTuile position;
    protected Caracteristique [] caracteristiques;

    public Tour( PositionTuile position ) {
        this.position = position;
    }

    public abstract int animer( List< Ennemi > ennemis );

    public void inscrireEvenements( GestionSouris gestionSouris ) {
        for( int i = 0; i < caracteristiques.length; ++ i ) {
            caracteristiques[ i ].inscrireEvenement( gestionSouris );
        }
    }

    public void desinscrireEvenements( GestionSouris gestionSouris ) {
        for( int i = 0; i < caracteristiques.length; ++ i ) {
            caracteristiques[ i ].desinscrireEvenement( gestionSouris );
        }
    }

    public void afficherControl( Graphics2D g2, Jeu jeu ) {
        AffineTransform pCug = (AffineTransform) jeu.origine_pixel.clone();
        pCug.translate( getPositionPixel().x(), getPositionPixel().y() );
        g2.drawImage( Constantes.SOUSLIGNE_TOUR, pCug, null );

        for( int i = 0; i < caracteristiques.length; ++ i ) {
            caracteristiques[ i ].afficherControl( g2, jeu );
        }
    }

    public void afficherTour(Graphics2D g2, AffineTransform origine_pixel) {
        AffineTransform pCurseur = (AffineTransform) origine_pixel.clone();
        PositionPixel pos = getPositionTuile().positionPixel();
        pCurseur.translate(pos.x(), pos.y());

        if (this instanceof TourArcher) {
            g2.drawImage(TourArcher.TOUR_BAS, pCurseur, null);
            pCurseur.translate(0, -Constantes.TAILLE_TUILE);
            g2.drawImage(TourArcher.TOUR_HAUT, pCurseur, null);
        } else if (this instanceof TourFeu) {
            g2.drawImage(TourFeu.TOUR_FEU_BAS, pCurseur, null);
            pCurseur.translate(0, -Constantes.TAILLE_TUILE);
            g2.drawImage(TourFeu.TOUR_FEU_HAUT, pCurseur, null);
        } else if (this instanceof TourGlace) {
            g2.drawImage(TourGlace.TOUR_GLACE_BAS, pCurseur, null);
            pCurseur.translate(0, -Constantes.TAILLE_TUILE);
            g2.drawImage(TourGlace.TOUR_GLACE_HAUT, pCurseur, null);
        }
    }

    public PositionTuile getPositionTuile() {
        return position;
    }

    public PositionPixel getPositionPixel() {
        return position.positionPixel();
    }
}
