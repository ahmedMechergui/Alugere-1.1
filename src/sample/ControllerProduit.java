package sample;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ControllerProduit {
public TextField textDesignation;
public String stringDesignationCapitalized;
public TextField textBlanc;
public TextField textNoir;
public TextField textGris;
public TextField textAddPerMetreBlanc;
public TextField textAddPerMetreNoir;
public TextField textAddPerMetreGris;
public TextField textAddPerMetreFauxBois;
public TextField textFauxBois;
public Button buttonAjouter;
public Button buttonAnnuler;

public void annulerButtonClicked(){ buttonAnnuler.getScene().getWindow().hide(); }

public void AjouterButtonClicked(){


if (Checker.checkIFProduitIsFine(textDesignation,textBlanc,textNoir,textGris,textFauxBois,textAddPerMetreBlanc,textAddPerMetreNoir,textAddPerMetreGris,textAddPerMetreFauxBois)){
    // yrod awwel 7arf mel designation majuscule ( wahra level over 9000 )
    stringDesignationCapitalized=new String(textDesignation.getText().substring(0,1).toUpperCase()+textDesignation.getText().substring(1));
    textDesignation.setText(stringDesignationCapitalized);


    // ythabbet ken el nafs esm el designation deja mawjoud sinon : // done
    // ya3mel yzid el produit lel table des produit li ma7tout fi fichier // done

    FichierProduits.ajouter(new Produit(textDesignation.getText(),Double.parseDouble(textBlanc.getText()),Double.parseDouble(textNoir.getText()),Double.parseDouble(textGris.getText()),Double.parseDouble(textFauxBois.getText()),Double.parseDouble(textAddPerMetreBlanc.getText()),Double.parseDouble(textAddPerMetreNoir.getText()),Double.parseDouble(textAddPerMetreGris.getText()),Double.parseDouble(textAddPerMetreFauxBois.getText())),true);
    // ken ma famma 7atta erreur
    if(FichierProduits.canLeaveAlert){
    textDesignation.setText("");
    textBlanc.setText("");
    textNoir.setText("");
    textGris.setText("");
    textFauxBois.setText("");
    textAddPerMetreBlanc.setText("");
    textAddPerMetreNoir.setText("");
    textAddPerMetreGris.setText("");
    textAddPerMetreFauxBois.setText("");
    annulerButtonClicked();}
}

}


}