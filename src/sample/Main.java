package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.Arrays;


public class Main extends Application {


    TableView<Commande> table;
    TextField textTVA, textHT, textTTC, textHauteur, textLargeur, textQuantite;
    ComboBox<String> cmbType, cmbReference, cmbCouleur, cmbLam;
    Button bajouter;
    HBox panelLow;
    double ht = 0, ttc = 0, tva = 0, tvaPercent = FileMan.getTVA(), tvaAlwaysThere = 0;
    Boolean firstTimeAdd = true;
    HBox hBox;
    boolean printedWork = true;
    Label labelTVA, labelHT, labelTTC;
    Boolean tvaIsUsed = false;
    Menu menuSupprimer, menuEditer;
    Stage rootModifier;
    boolean firstPresicionError = true; // na3mel bih check bech el blaka mta3 erreur ma tatla3ch barcha marat wra b3adh'hom
    boolean canIncrementNumDevis = false; // tgammer bih bech noumrou el num devis ma yzidech wa9t ta3mel exporter wella imprimer wra b3adh'hom el num devis ma yzidech

    @Override
    public void start(Stage primaryStage) throws Exception {


        Parent entrepriseGui = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Parent produitGui = FXMLLoader.load(getClass().getResource("produitAjouter.fxml"));
        Parent editGui = FXMLLoader.load(getClass().getResource("produitEditer.fxml"));
        Parent ChangerTVAGui = FXMLLoader.load(getClass().getResource("ChangerTva.fxml"));
        primaryStage.setTitle(" Alugère v1.1");
// Menu
        Menu menuFichier = new Menu(" _Fichier ");
        Menu menuModifier = new Menu("_Modifier");
        Menu menuEntreprise = new Menu(" _Entreprise ");
        Menu menuAide = new Menu(" _Aide ");

        // menu ajouter en tant qu'item : item to5rej menha menu o5ra
        // yet3amrou Dynamique wa9t howa yzid produit
        menuSupprimer = new Menu("Supprimer");
        menuEditer = new Menu("Editer");
        remplirMenuSupprimer();
        remplirEditerMenu();

// items menu Fichier
        MenuItem itemVider = new MenuItem("Vider tableau");
        MenuItem itemImprimer = new MenuItem("Imprimer devis");
        MenuItem itemExporter = new MenuItem("Exporter devis PDF");
        menuFichier.getItems().addAll(itemVider, itemExporter, itemImprimer);

// items menu Modifer
        MenuItem itemAjouter = new MenuItem("Ajouter");
        MenuItem itemEditTVa = new MenuItem("Changer TVA");
        menuModifier.getItems().addAll(itemAjouter, menuSupprimer, menuEditer, itemEditTVa);

// items menu Entreprise
        MenuItem itemInformationEntreprise = new MenuItem("Informations d'entreprise...");
        menuEntreprise.getItems().addAll(itemInformationEntreprise);

// items menu Aide
        MenuItem itemContactDev = new MenuItem("Contacter le developpeur");
        MenuItem itemActiver = new MenuItem("Activer le logiciel...");
        itemActiver.setDisable(true);
        menuAide.getItems().addAll(itemActiver, itemContactDev);

// MenuBar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menuFichier, menuModifier, menuEntreprise, menuAide);

// Labels
        String tvaPercentString = new String(tvaPercent + "");
        tvaPercentString = tvaPercentString.substring(0, tvaPercentString.indexOf("."));

        labelTVA = new Label(" TVA " + tvaPercentString + "% :");
        CheckBox useTVA = new CheckBox();
        labelHT = new Label("Total HT :");
        labelTTC = new Label("Total TTC :");

        labelTVA.getStyleClass().add("label-bold");
        labelHT.getStyleClass().add("label-bold");
        labelTTC.getStyleClass().add("label-bold");


// TextField TVA,HT,TTC
        textTVA = new TextField();
        textTVA.setMaxWidth(150);
        textHT = new TextField();
        textHT.setMaxWidth(150);
        textTTC = new TextField();
        textTTC.setMaxWidth(150);


        textHT.setEditable(false);
        textTTC.setEditable(false);
        textTVA.setEditable(false);

        textTVA.getStyleClass().add("button-border");
        textTTC.getStyleClass().add("button-border");
        textHT.getStyleClass().add("button-border");

// Table des commandes
        TableColumn<Commande, String> reference = new TableColumn<>("Reference");
        reference.setMinWidth(100);
        reference.setCellValueFactory(new PropertyValueFactory<>("reference"));

        TableColumn<Commande, String> designation = new TableColumn<>("Designation");
        designation.setMinWidth(220);
        designation.setCellValueFactory(new PropertyValueFactory<>("designation"));

        TableColumn<Commande, String> dimension = new TableColumn<>("Dimension");
        dimension.setMinWidth(150);
        dimension.setCellValueFactory(new PropertyValueFactory<>("dimension"));

        TableColumn<Commande, String> quantite = new TableColumn<>("Quantite");
        quantite.setMinWidth(100);
        quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));

        TableColumn<Commande, String> prixUnitaire = new TableColumn<>("Prix Unitaire");
        prixUnitaire.setMinWidth(100);
        prixUnitaire.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));

        TableColumn<Commande, String> montantHT = new TableColumn<>("Montant HT");
        montantHT.setMinWidth(100);
        montantHT.setCellValueFactory(new PropertyValueFactory<>("montantHT"));

        TableColumn<Commande, String> couleur = new TableColumn<>("Couleur");
        couleur.setMinWidth(150);
        couleur.setCellValueFactory(new PropertyValueFactory<>("couleur"));

// creation du tableau
        table = new TableView<>();
        table.setItems(getCommande());
        table.getColumns().addAll(reference, designation, couleur, dimension, quantite, prixUnitaire, montantHT);
        table.setId("my-tableHidden");


// AutoComplete ComboBox
        // Declaration
        cmbReference = new ComboBox<>();
        cmbType = new ComboBox<>();
        cmbCouleur = new ComboBox<>();
        cmbLam = new ComboBox<>();
        // Tooltip
        cmbType.setTooltip(new Tooltip("Choisir la designation"));
        cmbReference.setTooltip(new Tooltip("Choisir la Reference"));
        cmbCouleur.setTooltip(new Tooltip("Choisir le Couleur"));
        cmbLam.setTooltip(new Tooltip("Choisir la Lam"));

        // setting placeHolders
        cmbType.setPromptText("Designation");
        cmbReference.setPromptText("Reference");
        cmbCouleur.setPromptText("Couleur");
        cmbLam.setPromptText("Lam");

        // add items to lists
        // sorting lists alphabetically
        Arrays.sort(listType); // le premier caractere doit etre majiscule
        Arrays.sort(listReference);
        Arrays.sort(listCouleur);
        Arrays.sort(listLam);

        // filling items
        String[] listType = new String[FichierProduits.getTableDesignation().size()];
        listType = FichierProduits.getTableDesignation().toArray(listType);

        cmbType.getItems().addAll(listType);
        cmbReference.getItems().addAll(listReference);
        cmbCouleur.getItems().addAll(listCouleur);
        cmbLam.getItems().addAll(listLam);

        // setting sizes
        cmbReference.setMaxWidth(140);
        cmbReference.setMinWidth(140);

        cmbType.setMaxWidth(140);
        cmbType.setMinWidth(140);

        cmbCouleur.setMaxWidth(140);
        cmbCouleur.setMinWidth(140);

        cmbLam.setMaxWidth(140);
        cmbLam.setMinWidth(140);

        //setting styles
        cmbReference.getStyleClass().add("combobox-style");
        cmbType.getStyleClass().add("combobox-style");
        cmbCouleur.getStyleClass().add("combobox-style");
        cmbLam.getStyleClass().add("combobox-style");

        // making them autoComplete
        new AutoCompleteComboBoxListener<>(cmbReference);
        new AutoCompleteComboBoxListener<>(cmbType);
        new AutoCompleteComboBoxListener<>(cmbCouleur);
        new AutoCompleteComboBoxListener<>(cmbLam);

// TextFields largeur,hauteur,quantité
        textHauteur = new TextField();
        textLargeur = new TextField();
        textQuantite = new TextField();

        // resize textFields

        textHauteur.setMaxWidth(100);
        textLargeur.setMaxWidth(100);
        textQuantite.setMaxWidth(100);

        // place Holer for text fields
        textHauteur.setPromptText("Hauteur");
        textLargeur.setPromptText("Largeur");
        textQuantite.setPromptText("Quantité");
// Button ajouter
        bajouter = new Button("Ajouter");
        bajouter.getStyleClass().add("buton-rounded");
        bajouter.setOnAction(e ->
                {
                    addButtonClicked();
                }
        );

// actions MenuItem
        // Exporter

        itemExporter.setOnAction(e -> {
            if (table.getItems().get(0).getQuantite() == 0) {
                AlertClass.displayError("Tableau Vide!");
            } else {
                if (canIncrementNumDevis) {
                    FileMan.incrementNumDevis();
                    canIncrementNumDevis = false;
                }
                double tvaToCheck = 0;
                if (tvaIsUsed) {
                    tvaToCheck = tvaPercent;
                }
                HtmlMaker.GenererDevis(FileMan.getEntreprise().adresse, FileMan.getEntreprise().rue, FileMan.getEntreprise().email, FileMan.getEntreprise().telephone1, FileMan.getEntreprise().telephone2, FileMan.getNumDevis(), table, textHT.getText(), tvaToCheck, textTVA.getText(), textTTC.getText());
                printedWork = true;
            }
        });

        itemImprimer.setOnAction(e -> {
            if (table.getItems().get(0).getQuantite() == 0) {
                AlertClass.displayError("Tableau Vide!");
            } else {
                if (canIncrementNumDevis) {
                    FileMan.incrementNumDevis();
                    canIncrementNumDevis = false;
                }
                double tvaToCheck = 0;
                if (tvaIsUsed) {
                    tvaToCheck = tvaPercent;
                }
                HtmlMaker.GenererDevis(FileMan.getEntreprise().adresse, FileMan.getEntreprise().rue, FileMan.getEntreprise().email, FileMan.getEntreprise().telephone1, FileMan.getEntreprise().telephone2, FileMan.getNumDevis(), table, textHT.getText(), tvaToCheck, textTVA.getText(), textTTC.getText());
                HtmlMaker.printDevisPdf();
                printedWork = true;

            }
        });

        // Contacter
        itemContactDev.setOnAction(e -> Contact.display());

        // Activer

        itemActiver.setOnAction(event -> LicenceKey.display());

        // Informations d'entreprise
        // itemInformationEntreprise.setOnAction(e->Entreprise.display());
        Stage root = new Stage();
        root.setScene(new Scene(entrepriseGui, 424, 525));
        root.initStyle(StageStyle.UNDECORATED);
        itemInformationEntreprise.setOnAction(e -> root.show());

        // Ajouter un produit
        Stage root2 = new Stage();
        root2.setScene(new Scene(produitGui, 570, 290));
        root2.initStyle(StageStyle.UNDECORATED);
        itemAjouter.setOnAction(e -> {
            root2.show();
        });

        // Modifier un produit
        rootModifier = new Stage();
        rootModifier.setScene(new Scene(editGui, 560, 290));
        rootModifier.initStyle(StageStyle.UNDECORATED);

        // Changer TVA
        Stage rootTVA = new Stage();
        rootTVA.setScene(new Scene(ChangerTVAGui, 285, 120));
        rootTVA.initStyle(StageStyle.UNDECORATED);
        itemEditTVa.setOnAction(e -> {
            rootTVA.show();
        });
        // vider
        itemVider.setOnAction(e -> {
            viderTable();
            Dimension dimension6 = new Dimension(0, 0);
            Commande commande = new Commande("", "", "", dimension6, 0, 0, 0);
            table.getItems().add(commande);
            table.setId("my-tableHidden");
            firstTimeAdd = true;
            textTTC.setText("");
            textHT.setText("");
            textTVA.setText("");
            cmbType.getSelectionModel().clearSelection();
            cmbReference.getSelectionModel().clearSelection();
            printedWork = true;

            ht = 0;
            tvaAlwaysThere = 0;
        });


// Action useTVA
        useTVAChecked(tvaIsUsed);
        useTVA.setOnAction(e -> {
            tvaIsUsed = !tvaIsUsed;
            useTVAChecked(tvaIsUsed);
        });
// Panel TVA,HT,TTC
        hBox = new HBox();
        HBox hBox1 = new HBox();
        hBox1.getChildren().addAll(useTVA, labelTVA, textTVA);
        HBox hBox2 = new HBox();
        hBox2.getChildren().addAll(labelHT, textHT);
        HBox hBox3 = new HBox();
        hBox3.getChildren().addAll(labelTTC, textTTC);
        hBox.getChildren().addAll(hBox1, hBox2, hBox3);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(30);

// Panel reference,designation,Couleur,largeur,hauteur,quantité,ajouter;
        panelLow = new HBox();
        panelLow.getChildren().addAll(cmbReference, cmbType, cmbCouleur, textLargeur, textHauteur, textQuantite, bajouter);
        panelLow.setAlignment(Pos.CENTER);
        panelLow.setSpacing(10);
        panelLow.setPadding(new Insets(10, 10, 10, 10));
//  Panel Principal
        VBox panelPrincipal = new VBox();
        panelPrincipal.getChildren().addAll(menuBar, hBox, table, panelLow);
        panelPrincipal.setSpacing(15);


// Scene
        Scene scenePrincipal = new Scene(panelPrincipal, 930, 530);
// scene Actions (KeyPressed)
        scenePrincipal.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case DELETE:
                        deleteButtonClicked();
                        if (table.getItems().isEmpty()) {
                            printedWork = true;
                            table.setId("my-tableHidden");
                            Dimension dimension6 = new Dimension(0, 0);
                            Commande commandeVide = new Commande("", "", "", dimension6, 0, 0, 0);
                            table.getItems().add(commandeVide);
                            textTTC.setText("");
                            textTVA.setText("");
                            textHT.setText("");
                            firstTimeAdd = true;
                        }
                        break;
                    case ENTER:
                        if (Checker.checkIfEmpty(textLargeur) || Checker.checkIfEmpty(textHauteur) || Checker.checkIfEmpty(textQuantite)) {
                        } else {

                            addButtonClicked();
                        }
                        break;
                }
            }
        });
// StyleSheet
        scenePrincipal.getStylesheets().add("PrincipalStyle.css");
// Fermer le logiciel
        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            if (!printedWork) {
                AlertClass.displayExitAlert();
            } else {
                System.exit(0);
            }

        });
// Stage
        primaryStage.setScene(scenePrincipal);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("file:icon.png"));
        panelLow.requestFocus();
        primaryStage.show();
    }


    public ObservableList<Commande> getCommande() {
        ObservableList<Commande> commandes = FXCollections.observableArrayList();

        Dimension dimension = new Dimension(0, 0);
        Commande commande = new Commande("", "", "", dimension, 0, 0, 0);
        commandes.add(commande);
// jibhom men fichier data
        return commandes;
    }

    // tjibhom men fichier
    private static String[] listType = {"Fenetre", "porte ouvrant", "Porte fenetre collissant", "fenetre petit modele", "alorem", "zipsum", "garage"};
    private static String[] listReference = {"S98", "S67", "S03", "A81", "L34", "A79"};
    private static String[] listCouleur = {"Blanc", "Noir", "Gris", "Faux bois"};
    private static String[] listLam = {"Lam45", "Lam55", "LamXtrueD", "LamPVC"};


// Actions

    //Add button clicked
    public void addButtonClicked() {
        Checker check = new Checker();
        Commande commande;

        if (!(check.checkIfselected(cmbReference, "Reference"))) {
            cmbReference.getSelectionModel().clearSelection();
            cmbReference.requestFocus();
        } else if (!(check.checkIfselected(cmbType, "Type"))) {
            cmbType.getSelectionModel().clearSelection();
            cmbType.requestFocus();
        } else if (!(check.checkIfselected(cmbCouleur, "Couleur"))) {
            cmbCouleur.getSelectionModel().clearSelection();
            cmbCouleur.requestFocus();
        } else if (textLargeur.getText().isEmpty()) {
            AlertClass.displayEmptyField("Hauteur");
        } else if (!(check.checkIfDouble(textLargeur.getText()))) {
            textLargeur.requestFocus();
        } else if (textHauteur.getText().isEmpty()) {
            AlertClass.displayEmptyField("Largeur");
        } else if (!(check.checkIfDouble(textHauteur.getText()))) {
            textHauteur.requestFocus();
        } else if (textQuantite.getText().isEmpty()) {
            AlertClass.displayEmptyField("Quantité");
        } else if (!(check.checkIfInt(textQuantite.getText()))) {
            textQuantite.requestFocus();
        } else {
            double largeurDouble = Double.parseDouble(textLargeur.getText());
            double hauteurDouble = Double.parseDouble(textHauteur.getText());
            int quantiteInt = Integer.parseInt(textQuantite.getText());
            if (check.checkIfPositive(largeurDouble) && check.checkIfPositive(hauteurDouble) && check.checkIfPositive(quantiteInt)) {
                Dimension dimension = new Dimension(largeurDouble, hauteurDouble);
                CalculMan calculMan = new CalculMan();
                double prixUnitaire;
                double montantHT;
                prixUnitaire = calculMan.calculPrixUnitaire(cmbType.getValue(), cmbCouleur.getValue(), dimension.getLargeur(), dimension.getHauteur());
                montantHT = calculMan.calculMontantHT(prixUnitaire, quantiteInt);

                // tna9selhom fel a3ded ba3d el fasel
                prixUnitaire = precision(prixUnitaire);
                montantHT = precision(montantHT);

                commande = new Commande(cmbReference.getValue().toString(), cmbType.getValue(), cmbCouleur.getValue(), dimension, quantiteInt, prixUnitaire, montantHT/*30 * Double.parseDouble(textQuantite.getText())*/);
                ht += commande.getMontantHT();
                tva = (ht * tvaPercent) / 100;
                ttc = ht + tva;
                tvaAlwaysThere = (ht * tvaPercent) / 100;

                // tna9selhom fel a3ded ba3d el fasel
                tva = precision(tva);
                ht = precision(ht);
                ttc = precision(ttc);
                tvaAlwaysThere = precision(tvaAlwaysThere);

                // bech ma ye7sebch el tva wa9t tebda mahech checked
                if (tvaIsUsed) {
                    tva = 0;
                }

                add(commande, table);
                if (firstTimeAdd) {
                    table.setId("my-table");
                    table.getItems().remove(0, 1);
                    firstTimeAdd = false;
                }


                printedWork = false;
                if (tvaIsUsed) {
                    textTVA.setText(CalculMan.doubleStringToPrix(tvaAlwaysThere + "") + " dt");
                    tva = tvaAlwaysThere;
                    textTTC.setText(CalculMan.doubleStringToPrix(ttc + "") + " dt");
                } else {
                    textTVA.setText("");
                    textTTC.setText("");
                }
                textHT.setText(CalculMan.doubleStringToPrix(ht + "") + " dt");
                textHauteur.clear();
                textQuantite.clear();
                textLargeur.clear();
// bech el combobox ma yarja3ch fere8
                cmbType.getSelectionModel().clearSelection();
                cmbType.getItems().clear();
                cmbType.getItems().addAll(FichierProduits.getTableDesignation());

                cmbReference.getSelectionModel().clearSelection();
                cmbReference.getItems().clear();
                cmbReference.getItems().addAll(listReference);

                cmbCouleur.getSelectionModel().clearSelection();
                cmbCouleur.getItems().clear();
                cmbCouleur.getItems().addAll(listCouleur);

                cmbLam.getSelectionModel().clearSelection();
                cmbLam.getItems().clear();
                cmbLam.getItems().addAll(listLam);

                panelLow.requestFocus();
                canIncrementNumDevis = true;
                //cmbReference.requestFocus();

                //     System.out.println(table.getItems().size());
            }
        }
    }

    // verifie si le commande existe deja , si elle existe el incremente la quantité et le Montant HT
    public void add(Commande commande, TableView<Commande> table) {
        boolean test = false;
        int i = 0;
        for (Commande x : table.getItems()) {
            if (commande.isEqual(x)) {
                commande.setQuantite(commande.getQuantite() + x.getQuantite());
                commande.setMontantHT(x.getMontantHT() + commande.getMontantHT());
                table.getItems().remove(i, i + 1);

                // Update HT & TTC


                break;
            }
            i++;
        }
        table.getItems().add(commande);

    }


    public void deleteButtonClicked() {
        firstPresicionError = true;
        ObservableList<Commande> commandSelected, allCommandes;
        allCommandes = table.getItems();
        commandSelected = table.getSelectionModel().getSelectedItems();
        double deletedMontant = commandSelected.get(0).getMontantHT();
        // System.out.println(deletedMontant);
        ht -= deletedMontant;
        ht = precision(ht);
        textHT.setText(CalculMan.doubleStringToPrix(ht + "") + " dt");
        tvaAlwaysThere = (ht * tvaPercent) / 100;
        ttc = ht + tvaAlwaysThere;
        ttc = precision(ttc);
        tvaAlwaysThere = precision(tvaAlwaysThere);
        tva = precision(tva);
        if (tvaIsUsed) {
            textTVA.setText(CalculMan.doubleStringToPrix(tvaAlwaysThere + "") + " dt");
            textTTC.setText(CalculMan.doubleStringToPrix(precision(ttc) + "") + " dt");
        }
        canIncrementNumDevis = true;
        commandSelected.forEach(allCommandes::remove);
        //  System.out.println(table.getItems().size());
    }


    public void viderTable() {
        table.getItems().clear();
        panelLow.requestFocus();
    }


    public void useTVAChecked(Boolean x) {

        x = !x;

        textTVA.setDisable(x);
        textTTC.setDisable(x);
        labelTTC.setDisable(x);
        labelTVA.setDisable(x);

        if (!x && (tvaAlwaysThere > 0)) {
            textTVA.setText(CalculMan.doubleStringToPrix(tvaAlwaysThere + "") + " dt");
            ttc = tvaAlwaysThere + ht;
            ttc = precision(ttc);
            tva = tvaAlwaysThere;
            textTTC.setText(CalculMan.doubleStringToPrix(ttc + "") + "dt");
        }
        if (!tvaIsUsed) {
            textTVA.setText("");
            textTTC.setText("");
        }
    }

    // t3abbi dynamiquement mel fichierProduit el menu editer w supprimer
    public void remplirMenuSupprimer() {
        ArrayList<String> list = FichierProduits.getTableDesignation();
        for (int i = 0; i < list.size(); i++) {
            menuSupprimer.getItems().add(new MenuItem(list.get(i)));
            final int x = i;
            menuSupprimer.getItems().get(i).setOnAction(e -> FichierProduits.supprimer(menuSupprimer.getItems().get(x).getText(), true));
            //    System.out.println("variable final x : "+x);
        }

    }


    public void remplirEditerMenu() {
        ArrayList<String> list = FichierProduits.getTableDesignation();
        for (int i = 0; i < list.size(); i++) {
            menuEditer.getItems().add(new MenuItem(list.get(i)));
            final int x = i;
            menuEditer.getItems().get(i).setOnAction(event -> {
                rootModifier.show();
                FileMan.setProduitToModifier(menuEditer.getItems().get(x).getText());
            });
        }
    }

    // te5ou 7aja hakka 13.5648234 trajja3 haka 13.56

    public double precision(double x) {
        // System.out.println("value entred : "+x);
        double d = x;
        try {
            String s = x + "0";
            if (s.contains("E") && firstPresicionError) {
                AlertClass.displayError("Taille ou prix imaginaire a été entré!\nTous resultats sera Erroné \n\nvous pouvez supprimer la commande dernier ajouté pour fixer la problème");
                firstPresicionError = false;
                return x;
            }
            s = s.substring(0, s.indexOf(".") + 3);
            d = Double.parseDouble(s);
            //      System.out.println("value returned : "+d);
            return d;
        } catch (Exception e) {
            AlertClass.displayError("Erreur de conversion!\n veuillez changer les valeurs d'entré");
        }
        //  System.out.println("ERROR : value untouched : "+d);
        return d;
    }


    public static void main(String[] args) {
        launch(args);
    }

}