package logiciel.ecran;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.function.BiConsumer;

public class Caracteristique {
    private String nom;
    private int valeur;
    private int niveau;
    private int [] valeursParNiveau;
    private int [] coutsProchainNiveau;

    private PositionTuile positionNom_tuile;
    private PositionTuile positionValeur_tuile;
    private PositionTuile positionBouton_tuile;
    private PositionPixel positionBouton_pixel;
    private PositionTuile positionCout_tuile;

    public
    BiConsumer<Jeu, PositionTuile> augmenterFct = ( jeu, position) ->
    {
        if ( peutAugmenter() ) {
            int prix = coutProchainNiveau();
            if( prix <= jeu.argent ) {
                jeu.argent -= prix;
                augmenter();

                if( ! jeu.action ) {
                    jeu.repaint();
                }
            }
        }
    };

    public Caracteristique( String nom, int[] valeursParNiveau, int[] coutsProchainNiveau, int posY ) {
        this.nom = nom;
        this.niveau = 0;
        this.valeursParNiveau = valeursParNiveau;
        this.coutsProchainNiveau = coutsProchainNiveau;
        this.valeur = valeursParNiveau[ niveau ];
        this.positionNom_tuile = new PositionTuile( Constantes.POSITION_CARACTERISTIQUE_X_NOM, posY );
        this.positionValeur_tuile = new PositionTuile( Constantes.POSITION_CARACTERISTIQUE_X_NOMBRE, posY );
        this.positionBouton_tuile = new PositionTuile( Constantes.POSITION_CARACTERISTIQUE_X_BOUTON, posY );
        this.positionBouton_pixel = this.positionBouton_tuile.positionPixel();
        this.positionCout_tuile = new PositionTuile( Constantes.POSITION_CARACTERISTIQUE_X_NOMBRE, posY + 1 );
    }

    public void afficherControl( Graphics2D g2, Jeu jeu ) {
        jeu.afficherMessage( g2, nom, positionNom_tuile );
        jeu.afficherNombre( g2, positionValeur_tuile, valeur );

        if( peutAugmenter() ) {
            AffineTransform pC = (AffineTransform) jeu.origine_pixel.clone();
            pC.translate( positionBouton_pixel.x(), positionBouton_pixel.y() );

            g2.drawImage( Constantes.BOUTON_AUGMENTER, pC, null );
            jeu.afficherNombre( g2, positionCout_tuile, coutProchainNiveau() );

            pC.translate( 0, Constantes.TAILLE_TUILE );
            g2.drawImage( Constantes.DOLLAR, pC, null );
        }
    }

    public void inscrireEvenement( GestionSouris gestionSouris ) {
        gestionSouris.inscrireEvenement( positionBouton_tuile, augmenterFct );
    }

    public void desinscrireEvenement( GestionSouris gestionSouris ) {
        gestionSouris.desinscrireEvenement( positionBouton_tuile );
    }

    public int getValeur() {
        return valeur;
    }

    public boolean peutAugmenter() {
        return niveau < coutsProchainNiveau.length;
    }

    public void augmenter() {
        ++ niveau;
        valeur = valeursParNiveau[ niveau ];
    }

    public int coutProchainNiveau() {
        return coutsProchainNiveau[ niveau ];
    }
}
