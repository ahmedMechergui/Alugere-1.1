package sample;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ControllerEditerProduit {
    public TextField textDesignation;
    public TextField textBlanc;
    public TextField textNoir;
    public TextField textGris;
    public TextField textFauxBois;
    public TextField textAddPerMetreBlanc;
    public TextField textAddPerMetreNoir;
    public TextField textAddPerMetreGris;
    public TextField textAddPerMetreFauxBois;
    public Button buttonModifier;
    public  Button buttonAnnuler;
    public String stringDesignationCapitalized;
    public boolean firstMouseMove=true;

public void annulerButtonClicked(){ buttonAnnuler.getScene().getWindow().hide();firstMouseMove=true; }

public void modifierButtonClicked(){

    if (Checker.checkIFProduitIsFine(textDesignation,textBlanc,textNoir,textGris,textFauxBois,textAddPerMetreBlanc,textAddPerMetreNoir,textAddPerMetreGris,textAddPerMetreFauxBois)){
        // yrod awwel 7arf mel designation majuscule
        stringDesignationCapitalized=new String(textDesignation.getText().substring(0,1).toUpperCase()+textDesignation.getText().substring(1));
        textDesignation.setText(stringDesignationCapitalized);

        FichierProduits.modifier(new Produit(textDesignation.getText(),Double.parseDouble(textBlanc.getText()),Double.parseDouble(textNoir.getText()),Double.parseDouble(textGris.getText()),Double.parseDouble(textFauxBois.getText()),Double.parseDouble(textAddPerMetreBlanc.getText()),Double.parseDouble(textAddPerMetreNoir.getText()),Double.parseDouble(textAddPerMetreGris.getText()),Double.parseDouble(textAddPerMetreFauxBois.getText())));
       // System.out.println(FileMan.getProduitToModifier()+" est modifier");
        // ken ma famma 7atta erreur
        if(FichierProduits.canLeaveAlert)
            annulerButtonClicked();
    }
    }

    // bech ki y7el el item yal9a fiha les info mta3 el proguit 7adhrin
public void initializeFields(){
    if (firstMouseMove){
    Produit produit=FichierProduits.getProduitByDesignation(FileMan.getProduitToModifier());

        textDesignation.setText(produit.designation);
        textBlanc.setText(""+produit.prixBlanc);
        textNoir.setText(""+produit.prixNoir);
        textGris.setText(""+produit.prixGris);
        textFauxBois.setText(""+produit.prixFauxBois);
        textAddPerMetreBlanc.setText(""+produit.addPerMetreBlanc);
        textAddPerMetreNoir.setText(""+produit.addPerMetreNoir);
        textAddPerMetreGris.setText(""+produit.addPerMetreGris);
        textAddPerMetreFauxBois.setText(""+produit.addPerMetreFauxBois);

        firstMouseMove=false;
}
}



}

