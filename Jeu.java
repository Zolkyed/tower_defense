package logiciel.ecran;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class Jeu extends JPanel {
    protected Dimension dimension;
    protected AffineTransform origine_pixel;
    protected int margeX;
    protected int margeY;
    protected int r;

    protected Niveau niveauCourant;
    protected int argent;
    protected int vieChateau;
    protected AffineTransform [] emplacementTours;
    protected static List< Tour > toursConstruites;
    protected PositionTuile positionChoisie;
    protected Tour tourChoisie;
    protected List< Ennemi > ennemis;
    protected GestionSouris gestionSouris;
    protected Timer animateur;
    protected List< Generateur > generateurs;
    protected boolean action = false;

    BiConsumer<Jeu, PositionTuile> choisirTour = ( jeu, position ) ->
    {
        if( null != jeu.tourChoisie ) {
            jeu.tourChoisie.desinscrireEvenements( jeu.gestionSouris );
        }

        int i = 0;
        while( i < toursConstruites.size() && ! toursConstruites.get( i ).getPositionTuile().equals( position ) ) {
            ++ i;
        }
        jeu.tourChoisie = toursConstruites.get( i );
        jeu.tourChoisie.inscrireEvenements( jeu.gestionSouris );
        repaint();
    };

    BiConsumer<Jeu, PositionTuile> jouer = ( jeu, position ) ->
    {
        if( 0 < vieChateau ) {
            animateur.start();
            action = true;
        }
    };

    BiConsumer<Jeu, PositionTuile> pause = ( jeu, position ) ->
    {
        animateur.stop();
        action = false;
        repaint();
    };

    public Jeu( Dimension dimension ) {
        super( true );

        this.dimension = dimension;

        setSize( dimension );

        int rx = dimension.width / Constantes.DIM_LARGEUR;
        int ry = dimension.height / Constantes.DIM_HAUTEUR;

        r = Math.min( rx, ry );

        margeX = ( dimension.width - r * Constantes.DIM_LARGEUR ) / 2;
        margeY = ( dimension.height - r * Constantes.DIM_HAUTEUR ) / 2;

        origine_pixel = AffineTransform.getTranslateInstance( margeX, margeY );
        origine_pixel.scale( r, r );

        gestionSouris = new GestionSouris( this );

        niveauCourant = new NiveauTD_1();
        argent = niveauCourant.argentDepart();
        emplacementTours = new AffineTransform[ niveauCourant.positionTours().length ];
        toursConstruites = new ArrayList<>();

        vieChateau = Constantes.VIE_CHATEAU;

        for( int i = 0; i < niveauCourant.positionTours().length; ++ i ) {
            gestionSouris.inscrireEvenement( niveauCourant.positionTours()[ i ], choisirTour );
            PositionPixel p = niveauCourant.positionTours()[ i ].positionPixel();
            emplacementTours[ i ] = (AffineTransform) origine_pixel.clone();
            emplacementTours[ i ].translate( p.x(), p.y() );
            toursConstruites.add( new TourEmplacement( niveauCourant.positionTours()[ i ] ) );
        }
        gestionSouris.inscrireEvenement( new PositionTuile( 34, 21 ), jouer );
        gestionSouris.inscrireEvenement( new PositionTuile( 37, 21 ), pause );
        gestionSouris.inscrireEvenement( new PositionTuile( 39, 0 ), ( jeu, position ) -> System.exit( 0 ) );

        this.addMouseListener( gestionSouris );

        animateur = new Timer( Constantes.TEMPS_TIC_MS, ( e ) -> {
            animerEnnemis();
            if( vieChateau <= 0 ) {
                animateur.stop();
                action = false;
            }
            for( int i = 0; i < generateurs.size(); ++ i ) {
                generateurs.get( i ).avance();
                if( ! generateurs.get( i ).estVivant() ) {
                    generateurs.remove( i );
                    -- i;
                }
            }
            if( generateurs.size() <= 0 && ennemis.size() <= 0 ) {
                animateur.stop();
                action = false;
            }
            tirer();
            repaint();
        } );

        ennemis = new ArrayList<>();
        generateurs = niveauCourant.generateurs();

        generateurs.stream().forEach( ( e ) -> e.setParent( this ) );
        tourChoisie = null;
        positionChoisie = null;
    }

    private void tirer() {
        for( int i = 0; i < toursConstruites.size(); ++ i ) {
            argent += toursConstruites.get( i ).animer( ennemis );
        }
    }

    private void animerEnnemis() {
        for( int i = 0; i < ennemis.size(); ++ i ) {
            if( ! ennemis.get( i ).aAtteintChateau() ) {
                ennemis.get( i ).avancer();
            } else {
                -- vieChateau;
                ennemis.remove( i );
                -- i;
            }
        }
        ennemis.sort( Ennemi::comparer );
    }

    public void afficherNombre( Graphics2D g2, PositionTuile position, int n ) {
        AffineTransform pCurseur = (AffineTransform) origine_pixel.clone();
        PositionPixel p = position.positionPixel();
        pCurseur.translate( p.x(), p.y() );
        if( 0 == n ) {
            g2.drawImage( Constantes.NIVEAU_CHIFFRES[ 0 ], pCurseur, null );
        } else {
            while( 0 != n ) {
                int chiffre = n % 10;
                n = n / 10;
                g2.drawImage( Constantes.NIVEAU_CHIFFRES[ chiffre ], pCurseur, null );
                pCurseur.translate( -Constantes.TAILLE_TUILE, 0 );
            }
        }
    }

    private void afficherTours( Graphics2D g2 ) {
        for( int i = 0; i < toursConstruites.size(); ++ i ) {
            toursConstruites.get( i ).afficherTour( g2, origine_pixel );
        }
    }

    private void afficherEnnemi( Graphics2D g2 ) {
        for( int i = 0; i < ennemis.size(); ++ i ) {
            ennemis.get( i ).afficher( g2, origine_pixel );
        }
    }

    private void afficherControl( Graphics2D g2 ) {
        AffineTransform pCu = (AffineTransform) origine_pixel.clone();
        pCu.translate( 39 * Constantes.TAILLE_TUILE, 0 * Constantes.TAILLE_TUILE );
        g2.drawImage( Constantes.BOUTON_FERMER, pCu, null );

        AffineTransform pCurseur = (AffineTransform) origine_pixel.clone();
        pCurseur.translate( 34 * Constantes.TAILLE_TUILE, 21 * Constantes.TAILLE_TUILE );
        if( action ) {
            g2.drawImage( Constantes.BOUTON_PLAY_ON, pCurseur, null );
        } else {
            g2.drawImage( Constantes.BOUTON_PLAY_OFF, pCurseur, null );
        }
        pCurseur.translate( 3 * Constantes.TAILLE_TUILE, 0 );
        if( action ) {
            g2.drawImage( Constantes.BOUTON_STOP_OFF, pCurseur, null );
        } else {
            g2.drawImage( Constantes.BOUTON_STOP_ON, pCurseur, null );
        }
        if( null != tourChoisie ) {
            tourChoisie.afficherControl( g2, this );
        }
    }

    public void afficherMessage( Graphics2D g2, String mssg, PositionTuile pos ) {
        AffineTransform pC = (AffineTransform) origine_pixel.clone();
        PositionPixel p = pos.positionPixel();
        pC.translate( p.x(), p.y() );
        for( char c : mssg.toCharArray() ) {
            if( ' ' != c ) {
                g2.drawImage(Constantes.LETTRES_MIN[c - 'a'], pC, null);
            }
            pC.translate( 8, 0 );
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g.setColor( Color.darkGray );
        g.fillRect( margeX, margeY, Constantes.DIM_LARGEUR * r, Constantes.DIM_HAUTEUR * r );

        niveauCourant.dessiner( g2, origine_pixel );
        afficherNombre( g2, Constantes.POSITION_ARGENT, argent );
        AffineTransform pC = (AffineTransform) origine_pixel.clone();
        PositionPixel p = Constantes.POSITION_DOLLAR.positionPixel();
        pC.translate( p.x(), p.y() );
        g2.drawImage(Constantes.DOLLAR, pC, null);
        afficherNombre( g2, niveauCourant.positionPV(), vieChateau );
        afficherTours( g2 );
        afficherEnnemi( g2 );
        afficherControl( g2 );
        if( vieChateau <= 0 ) {
            afficherMessage( g2, "vous avez perdu", new PositionTuile( 12, 12 ) );
        }
        if( generateurs.size() <= 0 && ennemis.size() <= 0 ) {
            afficherMessage( g2, "vous avez gagne", new PositionTuile( 12, 12 ) );
        }
    }

    public static List<TourGlace> getToursGlace() {
        List<TourGlace> toursGlaceList = new ArrayList<>();
        for (Tour tour : toursConstruites) {
            if (tour instanceof TourGlace) {
                toursGlaceList.add((TourGlace) tour);
            }
        }
        return toursGlaceList;
    }
}
