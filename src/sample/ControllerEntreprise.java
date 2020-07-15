package sample;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerEntreprise
{
       public Button buttonAnnuler;
       public Button buttonValider;
       public TextField textAdresse;
       public TextField textRue;
       public TextField textEmail;
       public TextField texttelephone1;
       public TextField texttelephone2;
// kammel rakka7 (jibou mel ControllerEditerProduit) el firstMouseMove fel button Valider;
    public boolean firstMouseMove=true;

    public void buttonAnnulerClicked(){
        buttonAnnuler.getScene().getWindow().hide();
        firstMouseMove=true;
    }

    public void initializeFields(){
        if (firstMouseMove){
        Entreprise entreprise= FileMan.getEntreprise();
        textAdresse.setText(entreprise.adresse);
        textRue.setText(entreprise.rue);
        textEmail.setText(entreprise.email);
        texttelephone1.setText(entreprise.telephone1);
        texttelephone2.setText(entreprise.telephone2);
        firstMouseMove=false;
    }}

    public void validerButtonClicked(){
        if (!Checker.checkIfEmpty(textAdresse) && !Checker.checkIfEmpty(textEmail) && !Checker.checkIfEmpty(textRue) && !Checker.checkIfEmpty(texttelephone1) && !Checker.checkIfEmpty(texttelephone2))
        {
            FileMan.setEntreprise(new Entreprise(textAdresse.getText(),textRue.getText(),textEmail.getText(),texttelephone1.getText(),texttelephone2.getText()));
            buttonAnnulerClicked();
        }
    }
}
