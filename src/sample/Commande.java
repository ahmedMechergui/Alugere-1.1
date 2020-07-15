package sample;

public class Commande {
    private String reference;
    private String designation;
    private Dimension dimension;
    private int quantite;
    private double prixUnitaire;
    private double montantHT;
    public String couleur;

    public Commande() {
    }

    public Commande(String reference, String designation, String couleur, Dimension dimension, int quantite, double prixUnitaire, double montantHT) {
        this.reference = reference;
        this.designation = designation;
        this.couleur = couleur;
        this.dimension = dimension;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
        this.montantHT = montantHT;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public double getMontantHT() {
        return montantHT;
    }

    public void setMontantHT(double montantHT) {
        this.montantHT = montantHT;
    }


    public boolean isEqual(Commande o) {
        //return(this.reference.equals(o.reference) && this.designation.equals(o.designation) && this.dimension.getHauteur()==o.dimension.getHauteur() && this.dimension.getLargeur()==o.dimension.getLargeur());
       Boolean test=new Boolean(true);
        if (this.reference.equals(o.reference)) {
        } else {test=false;}
        if (this.designation.equals(o.designation)) {
        }else {test=false;}
        if (this.couleur.equals(o.couleur)) {
        } else {test=false;}
        if (this.dimension.getHauteur() == o.dimension.getHauteur()) {
        }else {test=false;}
        if (this.dimension.getLargeur() == o.dimension.getLargeur()) {
        }else {test=false;}
        return test;
    }


    }


