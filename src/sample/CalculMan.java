package sample;

public class CalculMan {
    double prixUnitaire;

    public double calculPrixUnitaire(String designation,String couleur,double largeur,double hauteur){
        Produit produit= FichierProduits.getProduitByDesignation(designation);
        double additionParMettre=0;
        double prixInitial=0;

        if (!produit.designation.equals(designation)){AlertClass.displayError("Mal fonctionnement au programme et le prix Unitaires sera Erroné!!!\nVeillez contacter le developpeur");}

        if (couleur.equals("Blanc")){
            prixInitial=produit.prixBlanc;
            additionParMettre=produit.addPerMetreBlanc;

        }else if (couleur.equals("Noir")){
            prixInitial=produit.prixNoir;
            additionParMettre=produit.addPerMetreNoir;

        }else if (couleur.equals("Gris")){
            prixInitial=produit.prixGris;
            additionParMettre=produit.addPerMetreGris;

        }else if (couleur.equals("Faux bois")){
            prixInitial=produit.prixFauxBois;
            additionParMettre=produit.addPerMetreFauxBois;
        }
        double prixUnitaire;
        hauteur=hauteur/100;
        largeur=largeur/100;
        prixUnitaire=prixInitial+(additionParMettre*hauteur*largeur);
        this.prixUnitaire=prixUnitaire;
        return prixUnitaire;
    }

    public double calculMontantHT(double prixUnitaire,int quantite){
        return prixUnitaire*quantite;
    }
    // t3aytelha ken ba3d ma ta3mel calcul prix unitaire
    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public static String doubleStringToPrix(String string){
        String sub=string.substring(0,string.indexOf(".")+2);
        sub= sub.concat("00");
        return sub;
    }

    // tna7i el faza mta3 exemple 120 cm tjik 120.0 cm
    public static String fixDimention(double d){
        String string=new String(d+"");
        if (string.substring(string.indexOf(".")).equals(".0")){
            string=string.substring(0,string.indexOf("."));
        }
        System.out.println(string);
        return string;
    }
}
