package sample;

import java.io.Serializable;

public class Produit implements Serializable {
    public String designation;
   // soum el metre
    public double prixBlanc;
    public double prixNoir;
    public double prixGris;
    public double prixFauxBois;

    public double addPerMetreBlanc;
    public double addPerMetreNoir;
    public double addPerMetreGris;
    public double addPerMetreFauxBois;


    public Produit(String designation, double prixBlanc, double prixNoir, double prixGris, double prixFauxBois,double addPerMetreBlanc, double addPerMetreNoir, double addPerMetreGris, double addPerMetreFauxBois) {
        this.designation = designation;
        this.prixBlanc = prixBlanc;
        this.prixNoir = prixNoir;
        this.prixGris = prixGris;
        this.prixFauxBois = prixFauxBois;
        this.addPerMetreBlanc = addPerMetreBlanc;
        this.addPerMetreNoir = addPerMetreNoir;
        this.addPerMetreGris = addPerMetreGris;
        this.addPerMetreFauxBois = addPerMetreFauxBois;

    }

    public double getAddPerMetreBlanc() {
        return addPerMetreBlanc;
    }

    public void setAddPerMetreBlanc(double addPerMetreBlanc) {
        this.addPerMetreBlanc = addPerMetreBlanc;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getPrixBlanc() {
        return prixBlanc;
    }

    public void setPrixBlanc(double prixBlanc) {
        this.prixBlanc = prixBlanc;
    }

    public double getPrixNoir() {
        return prixNoir;
    }

    public void setPrixNoir(double prixNoir) {
        this.prixNoir = prixNoir;
    }

    public double getPrixGris() {
        return prixGris;
    }

    public void setPrixGris(double prixGris) {
        this.prixGris = prixGris;
    }

    public double getPrixFauxBois() {
        return prixFauxBois;
    }

    public void setPrixFauxBois(double prixFauxBois) {
        this.prixFauxBois = prixFauxBois;
    }
}
