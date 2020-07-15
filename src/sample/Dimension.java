package sample;

public class Dimension {
    private double hauteur;
    private double largeur;

    public Dimension() {
    }


    public Dimension(double largeur, double hauteur) {
        this.hauteur = hauteur;
        this.largeur = largeur;
    }

    public double getHauteur() {
        return hauteur;
    }

    public void setHauteur(double hauteur) {
        this.hauteur = hauteur;
    }

    public double getLargeur() {
        return largeur;
    }

    public void setLargeur(double largeur) {
        this.largeur = largeur;
    }
    public String toString(){
        return ("("+CalculMan.fixDimention(this.largeur) +" cm/"+CalculMan.fixDimention(this.hauteur)+" cm)");
    }
}
