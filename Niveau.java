package logiciel.ecran;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.List;

public interface Niveau {
    void dessiner(Graphics2D g2, AffineTransform affineTransform );
    int argentDepart();
    PositionTuile [] positionTours();
    List< Generateur > generateurs();
    PositionTuile positionPV();
}
