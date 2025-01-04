package logiciel.ecran;

public record PositionTuile( int x, int y ) {
    public PositionPixel positionPixel() {
        return new PositionPixel( x * Constantes.TAILLE_TUILE, y * Constantes.TAILLE_TUILE );
    }
}
