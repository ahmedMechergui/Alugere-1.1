package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Checker {
    public Checker() {
    }

    public static Boolean checkIfDouble(String s) {
        try {
            double nb = Double.parseDouble(s);
            return true;
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur : Problème de conversion!");
            alert.setHeaderText(null);
            alert.setContentText("\" " + s + "\"" + " n'est pas un nombre");
            ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("file:icon.png"));
            alert.showAndWait();
            return false;
        }
    }

    public static Boolean checkIfInt(String s) {
        try {
            int nb = Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur : Problème de conversion!");
            alert.setHeaderText(null);
            alert.setContentText("\" " + s + "\"" + " n'est pas un nombre entier");
            ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("file:icon.png"));
            alert.showAndWait();
            return false;
        }
    }

    public static Boolean checkIfPositive(int x) {
        if (x > 0) {
            return true;
        } else {
            AlertClass.displayNegativeNumber();
            return false;
        }
    }

    public static Boolean checkIfPositive(double x) {
        if (x > 0) {
            return true;
        } else {
            AlertClass.displayNegativeNumber();
            return false;
        }
    }

    public static Boolean checkIfselected(ComboBox<String> cb, String message) {
        ComboBox<String> myComboBox = cb;
        if (myComboBox.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur : Pas de désignation");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez specifier la" + message + "!");
            ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("file:icon.png"));
            alert.showAndWait();
            return false;
        } else {
            return true;
        }
    }

    public static Boolean checkIfEmpty(TextField next) {
        if (next.getText().isEmpty()) {
            next.requestFocus();
            return true;
        }
        return false;
    }

    public static boolean checkIFProduitIsFine(TextField textDesignation, TextField textBlanc, TextField textNoir, TextField textGris, TextField textFauxBois,TextField textAddPerMetreBlanc, TextField textAddPerMetreNoir, TextField textAddPerMetreGris, TextField textAddPerMetreFauxBois ) {
        if (Checker.checkIfEmpty(textDesignation)) {
            AlertClass.displayEmptyField("Designation");
            return false;
        } else if (Checker.checkIfEmpty(textBlanc)) {
            AlertClass.displayEmptyField("prix initial en couleur blanc");
            return false;
        } else if (!Checker.checkIfDouble(textBlanc.getText())) {
            return false;
        } else if (!Checker.checkIfPositive(Double.parseDouble(textBlanc.getText()))) {
            return false;
        } else if (Checker.checkIfEmpty(textNoir)) {
            AlertClass.displayEmptyField("prix initial en couleur noir");
            return false;
        } else if (!Checker.checkIfDouble(textNoir.getText())) {
            return false;
        } else if (!Checker.checkIfPositive(Double.parseDouble(textNoir.getText()))) {
            return false;
        } else if (Checker.checkIfEmpty(textGris)) {
            AlertClass.displayEmptyField("prix initial en couleur gris");
            return false;
        } else if (!Checker.checkIfDouble(textGris.getText())) {
            return false;
        } else if (!Checker.checkIfPositive(Double.parseDouble(textGris.getText()))) {
            return false;
        } else if (Checker.checkIfEmpty(textFauxBois)) {
            AlertClass.displayEmptyField("prix initial en couleur de faux bois");
            return false;
        } else if (!Checker.checkIfDouble(textFauxBois.getText())) {
            return false;
        } else if (!Checker.checkIfPositive(Double.parseDouble(textFauxBois.getText()))) {
            return false;
        }

        else if (Checker.checkIfEmpty(textAddPerMetreBlanc)) {
            AlertClass.displayEmptyField("prix ajouté par mètre en couleur blanc");
            return false;
        } else if (!Checker.checkIfDouble(textAddPerMetreBlanc.getText())) {
            return false;
        } else if (!Checker.checkIfPositive(Double.parseDouble(textAddPerMetreBlanc.getText()))) {
            return false;
        } else if (Checker.checkIfEmpty(textAddPerMetreNoir)) {
            AlertClass.displayEmptyField("prix ajouté par mètre en couleur noir");
            return false;
        } else if (!Checker.checkIfDouble(textAddPerMetreNoir.getText())) {
            return false;
        } else if (!Checker.checkIfPositive(Double.parseDouble(textAddPerMetreNoir.getText()))) {
            return false;
        } else if (Checker.checkIfEmpty(textAddPerMetreGris)) {
            AlertClass.displayEmptyField("prix ajouté par mètre en couleur gris");
            return false;
        } else if (!Checker.checkIfDouble(textAddPerMetreGris.getText())) {
            return false;
        } else if (!Checker.checkIfPositive(Double.parseDouble(textAddPerMetreGris.getText()))) {
            return false;
        } else if (Checker.checkIfEmpty(textAddPerMetreFauxBois)) {
            AlertClass.displayEmptyField("prix ajouté par mètre en couleur de faux bois");
            return false;
        } else if (!Checker.checkIfDouble(textAddPerMetreFauxBois.getText())) {
            return false;
        } else if (!Checker.checkIfPositive(Double.parseDouble(textAddPerMetreFauxBois.getText()))) {
            return false;
        }
        else {
            return true;
        }
    }
}
