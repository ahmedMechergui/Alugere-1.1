package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class FichierProduits implements Serializable {
    //List<Produit> listProduit=new ArrayList<Produit>();
    public static boolean canLeaveAlert;
   public static ArrayList<Produit> listProduit=new ArrayList<Produit>();



public static boolean exist(String designation){
        if (getTableDesignation().contains(designation))
        {return true;}
        else{
            return false;
        }
    }



public static void ajouter(Produit produit,boolean showNotification) {
    if (!exist(produit.designation)) {
        File file = new File("ListDesProduits");
        if (!file.exists()) {
            try {
                listProduit.add(produit);
                FileOutputStream fos = new FileOutputStream(file);
                // na3rech fech ta3mel bedhabt
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(listProduit);
                if (showNotification) {
                    AlertClass.displayInformation("Produit ajouté avec succées! \nRedemarrer le programme pour avoir les modification");
                }
            } catch (Exception e) {
                AlertClass.displayError("un erreur a eté produit lors du creation du fichier contenant la liste des produit :(");
            }
        } else {
            try {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                listProduit = (ArrayList<Produit>) ois.readObject();

                listProduit.add(produit);
                file.delete();
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(listProduit);
                canLeaveAlert=true;
                if (showNotification) {
                    AlertClass.displayInformation("Produit ajouté avec succées! \nRedemarrer le programme pour avoir les modification");
                }
// tjarreb biha
/*
                for(int i=0;i<listProduit.size();i++){
                    System.out.println(listProduit.get(i).designation+"\n");
                }

 */
            } catch (Exception e) {
                AlertClass.displayError("un erreur a eté produit lors du lecture du fichier contenant la liste des produit :(");
            }

        }
    }else
    {canLeaveAlert=false;AlertClass.displayError("Produit deja existant!");}
}

public  static ArrayList<String> getTableDesignation(){
        File file = new File("ListDesProduits");
        if (file.exists()){
            try {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                ArrayList<Produit> listProduitLocal;
                listProduitLocal = (ArrayList<Produit>) ois.readObject();
                ArrayList<String> listDesignation = new ArrayList<String>();
                for(int i=0;i<listProduitLocal.size();i++){
                    listDesignation.add(listProduitLocal.get(i).designation);
                }
                Collections.sort(listDesignation);
                return listDesignation;
            }catch(Exception e){AlertClass.displayError("Big problem bro!");return(new ArrayList<String>());}
        }
        else{
            return (new ArrayList<String>());
        }
    }


   // el methode supprimer t'thaber m3a el user mba3d t3ayet lhethi bech tfassa5
public static  void supp(String desgnation,boolean showNotification){
        File file = new File("ListDesProduits");
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            listProduit=(ArrayList<Produit>)ois.readObject();

           for (int i=0;i<listProduit.size();i++) {
                if (desgnation.equals(listProduit.get(i).designation)){listProduit.remove(i);break;}
           }

            file.delete();
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listProduit);
            if (showNotification) {
                AlertClass.displayInformation("Produit supprimer avec succées! \nRedemarrer le programme pour avoir les modification");
            }
// tjarreb biha
/*
                for(int i=0;i<listProduit.size();i++){
                    System.out.println(listProduit.get(i).designation+"\n");
                }

 */
        }catch (Exception e){ AlertClass.displayError("un erreur a eté produit lors du lecture du fichier contenant la liste des produit :("); }

    }

    // tet2akked mel choix mta3 el use 9bal ma tfasa5
public static void supprimer(String desgnation,boolean showNotification){
       if(showNotification){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmez votre choix");
        alert.setHeaderText(null);
        alert.setContentText("Voulez vous vraiment supprimer le : "+desgnation+" ?");
        ButtonType buttonTypeOne = new ButtonType("Oui");
        ButtonType buttonTypeTwo = new ButtonType("Non");
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            supp(desgnation,true);
            // bara lawej 3al usage lo5rin tawa
        } else {
            alert.close();
        }}
       else{supp(desgnation,false);}

    }

public static void modifier(Produit produit){
    try {
        supprimer(FileMan.getProduitToModifier(), false);
        ajouter(produit, false);
        AlertClass.displayInformation("Produit modifier avec succès\nVeillez redemarer le programme pour avoir les modifications");
    }catch(Exception e){AlertClass.displayError("une probleme a été produit lors du modification");}
}

public static Produit getProduitByDesignation(String designation){
    File file = new File("ListDesProduits");
    try {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        ArrayList<Produit> listProduitLocal;
        listProduitLocal = (ArrayList<Produit>) ois.readObject();
        for (int i=0;i<listProduitLocal.size();i++){
            if (listProduitLocal.get(i).designation.equals(designation)){return listProduitLocal.get(i);}
        }
        System.out.println("product not found in getProduitByDesignation , null item returned");
        return null;
    }catch (Exception e){
        System.out.println("problem in getProduitByDesignation");
    }
    System.out.println("product not found in getProduitByDesignation , null item returned");
    return null;
}

}
