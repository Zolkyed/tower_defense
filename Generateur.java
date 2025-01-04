package logiciel.ecran;

public class Generateur {
    private int heure;
    private int nbTicInitiale;
    private int nbTicSpawn;
    private int nbEnnemi;
    private Ennemi ennemi;
    private Jeu parent;
    private boolean phaseInitiale = true;

    public Generateur(int nbTicInitiale, int nbTicSpawn, Ennemi ennemi, int nbEnnemi ) {
        this.heure = 0;
        this.nbTicInitiale = nbTicInitiale;
        this.nbTicSpawn = nbTicSpawn;
        this.ennemi = ennemi;
        this.nbEnnemi = nbEnnemi;
    }

    public void setParent( Jeu parent ) {
        this.parent = parent;
    }

    public boolean estVivant() {
        return 0 < nbEnnemi;
    }

    public void avance() {
        if( ! phaseInitiale ) {
            if( heure == nbTicSpawn && 0 < nbEnnemi ) {
                heure -= nbTicSpawn;
                parent.ennemis.add( new Ennemi( ennemi ) );
                -- nbEnnemi;
            }
        } else {
            if( heure == nbTicInitiale ) {
                heure -= nbTicInitiale;
                phaseInitiale = false;
                parent.ennemis.add( new Ennemi( ennemi ) );
                -- nbEnnemi;
            }
        }
        ++ heure;
    }
}
