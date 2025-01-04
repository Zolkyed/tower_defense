package logiciel.ecran;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class DescripteurTour {
    private String nom;
    private int prix;
    private Function< PositionTuile, Tour > fonctionConstructionTour;

    private int posY;
    private PositionTuile positionNom_tuile;
    private PositionTuile positionBouton_tuile;
    private PositionPixel positionBouton_pixel;
    private PositionTuile positionCout_tuile;

    public DescripteurTour( String nom, int prix, Function< PositionTuile, Tour > fonctionConstructionTour, int posY ) {
        this.nom = nom;
        this.prix = prix;
        this.fonctionConstructionTour = fonctionConstructionTour;
        this.posY = posY;

        this.positionNom_tuile = new PositionTuile( Constantes.POSITION_CARACTERISTIQUE_X_NOM, posY );
        this.positionBouton_tuile = new PositionTuile( Constantes.POSITION_CARACTERISTIQUE_X_BOUTON, posY );
        this.positionBouton_pixel = this.positionBouton_tuile.positionPixel();
        this.positionCout_tuile = new PositionTuile( Constantes.POSITION_CARACTERISTIQUE_X_NOMBRE, posY + 1 );
    }

    public BiConsumer<Jeu, PositionTuile> acheterTour = ( jeu, position ) ->
    {
        if( prix <= jeu.argent ) {
            jeu.argent -= prix;

            jeu.tourChoisie.desinscrireEvenements( jeu.gestionSouris );
            jeu.toursConstruites.remove( jeu.tourChoisie );
            PositionTuile p = jeu.tourChoisie.getPositionTuile();

            jeu.tourChoisie = fonctionConstructionTour.apply( p );
            jeu.toursConstruites.add( jeu.tourChoisie );
            jeu.tourChoisie.inscrireEvenements( jeu.gestionSouris );
            jeu.repaint();
        }
    };

    public void inscrireEvenements( GestionSouris gestionSouris ) {
        gestionSouris.inscrireEvenement( positionBouton_tuile, acheterTour );
    }

    public void desinscrireEvenements( GestionSouris gestionSouris ) {
        gestionSouris.desinscrireEvenement( positionBouton_tuile );
    }

    public void afficherControl( Graphics2D g2, Jeu jeu ) {
        jeu.afficherMessage( g2, nom, positionNom_tuile );

        AffineTransform pC = (AffineTransform) jeu.origine_pixel.clone();
        pC.translate( positionBouton_pixel.x(), positionBouton_pixel.y() );

        g2.drawImage( Constantes.BOUTON_AUGMENTER, pC, null );
        jeu.afficherNombre( g2, positionCout_tuile, prix );

        pC.translate( 0, Constantes.TAILLE_TUILE );
        g2.drawImage(Constantes.DOLLAR, pC, null);
    }

}
