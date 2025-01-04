package logiciel.ecran;

import javax.swing.*;

public class Principal {
    public static void main( String [] argv ) {

        SwingUtilities.invokeLater( PleineEcran::new );
    }
}
