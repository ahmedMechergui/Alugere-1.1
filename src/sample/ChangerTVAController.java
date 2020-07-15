package sample;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ChangerTVAController {
    public TextField textTVA;
    public Button buttonChanger;
    public Button buttonAnnuler;

    public void buttonAnnulerClicked(){
        buttonAnnuler.getScene().getWindow().hide();
    }

    public void changerButtonClicked(){
        double tvaDouble=19;
        try {
            tvaDouble=Double.parseDouble(textTVA.getText());
        }catch (Exception e){AlertClass.displayError("TVA invalide!!");}

        if (!Checker.checkIfEmpty(textTVA) && Checker.checkIfDouble(textTVA.getText()) && Checker.checkIfPositive(tvaDouble))
        {
        FileMan.setTVA(tvaDouble);
        buttonAnnulerClicked();
        }
    }
}
