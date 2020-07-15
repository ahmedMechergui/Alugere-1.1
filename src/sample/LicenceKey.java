package sample;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LicenceKey {
 static String key;
 static PasswordField textkey;


 public static void display(){
     Stage window=new Stage();
     window.setTitle("Activation");
     Label label=new Label("Veillez entrer votre clé d'activation");
        key=new String();
     textkey=new PasswordField();
     textkey.setPromptText("Ecrire ici");
     textkey.setMaxWidth(200);

     Button buttonEnter=new Button("Activer");
     Button buttonAnnuler=new Button("Annuler");

     HBox hBox=new HBox();
     hBox.getChildren().addAll(buttonEnter,buttonAnnuler);
     hBox.setAlignment(Pos.CENTER);
     hBox.setSpacing(8);

     VBox vBox=new VBox();
     vBox.getChildren().addAll(label,textkey,hBox);
     vBox.setSpacing(8);
     vBox.setAlignment(Pos.CENTER);
     vBox.setPadding(new Insets(30,30,30,30));


     Scene scene=new Scene(vBox,310,130);

     window.setScene(scene);
     window.show();
     //window.setResizable(false);

     // Action activer
     buttonEnter.setOnAction(e->{
         System.out.println(textkey.getText());
     });

     // Action Annuler
     buttonAnnuler.setOnAction(e->{
         window.close();
     });

     scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
         @Override
         public void handle(KeyEvent event) {
            if (event.getCode()== KeyCode.ENTER){
                System.out.println(textkey.getText());
            }
             }
         });
 }

}
