package logiciel.ecran;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tuile extends BufferedImage {

    Tuile( Color couleur, BitMap pixels ) {
        super( pixels.largeur, pixels.hauteur, TYPE_INT_ARGB );
        for( int i = 0; i < pixels.datas.length; ++ i ) {
            int k = pixels.datas[ i ];
            for( int j = pixels.largeur - 1; 0 <= j; -- j ) {
                if ( 1 == ( k & 0x0001 ) ) {
                    setRGB(j, i, couleur.getRGB());
                }
                k = k >> 1;
            }
        }
    }

    Tuile( Color bgCouleur, Color fgCouleur, BitMap pixels ) {
        super( pixels.largeur, pixels.hauteur, TYPE_INT_ARGB );
        for( int i = 0; i < pixels.datas.length; ++ i ) {
            int k = pixels.datas[ i ];
            for( int j = pixels.largeur - 1; 0 <= j; -- j ) {
                if ( 1 == ( k & 0x0001 ) ) {
                    setRGB(j, i, fgCouleur.getRGB());
                } else {
                    setRGB(j, i, bgCouleur.getRGB());
                }
                k = k >> 1;
            }
        }
    }
}
