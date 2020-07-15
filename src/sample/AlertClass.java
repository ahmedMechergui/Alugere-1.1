package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Optional;

public class AlertClass {
    public AlertClass() {
    }

    public static void displayEmptyField(String champNom) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Champ vide!");
        alert.setHeaderText(null);
        alert.setContentText("veiller spécifier la " + champNom);
        ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("file:icon.png"));
        alert.show();
    }

    public static void displayNegativeNumber() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur : erreur de signe!");
        alert.setHeaderText(null);
        alert.setContentText("Tous nombres doivent etre positives!");
        ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("file:icon.png"));
        alert.showAndWait();
    }

    public static void displayExitAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Vous avez un travail non complet!");

        ButtonType buttonYes = new ButtonType("Sortir quand méme");
        ButtonType buttonNo = new ButtonType("Annuler");

        alert.getButtonTypes().setAll(buttonYes, buttonNo);
        ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("file:icon.png"));
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonYes) {
            System.exit(0);
        } else {
            alert.close();
        }
    }

    public static void displayInformation(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notification");
        alert.setHeaderText(null);
        alert.setContentText(message);
        ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("file:icon.png"));
        alert.show();
    }

    public static void displayError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur!");
        alert.setHeaderText(null);
        alert.setContentText(message);
        ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("file:icon.png"));
        alert.showAndWait();
    }
}
