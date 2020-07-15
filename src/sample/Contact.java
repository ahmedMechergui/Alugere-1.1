package sample;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Contact {
    public static  void display(){
        /*Stage window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Contact");
      //  window.setResizable(false);

        Label label1=new Label("Ce Logiciel est developpé par \" Ahmed Mechergui \".");
        Label label2=new Label("Pour tout informations veillez contacter :");
        Label label3=new Label("Tel : 50 280 853 ");
        Label label4=new Label("Mail : ahmed.michrgui@hotmail.fr");

        label3.setStyle("-fx-font-weight: bold");
        label4.setStyle("-fx-font-weight: bold");


        VBox panel=new VBox();
        panel.getChildren().addAll(label1,label2,label3,label4);

        Scene scene=new Scene(panel,300,200);
        window.setScene(scene);
        window.show(); */

        Label label1=new Label("Ce Logiciel est développé par \" TchikiSoft \"");
        Label label2=new Label("Pour tout informations veillez contacter :");
        Label label3=new Label("Tel : 50 280 853 ");
        Label label4=new Label("Mail : ahmed.michrgui@hotmail.fr");

        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.show();
        alert.setHeaderText(null);
        alert.setTitle("Contact");
        alert.setHeight(170);
        alert.setWidth(470);
        ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("file:icon.png"));
        alert.setResizable(false);
        alert.setContentText(label1.getText()+ "\n" +label2.getText() + "\n" +label3.getText() +"\n" +label4.getText());
    }
}
