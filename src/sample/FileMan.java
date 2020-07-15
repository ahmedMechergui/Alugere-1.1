package sample;

import java.io.*;

public class FileMan {
    public static void setProduitToModifier(String designationItem){
        File file = new File("ProductToModify");

        try {
            StringObject stringObject=new StringObject();
            stringObject.designation=new String(designationItem);
           FileOutputStream fos = new FileOutputStream(file);
           ObjectOutputStream oos = new ObjectOutputStream(fos);
           oos.writeObject(stringObject);
       }catch(Exception e){
           AlertClass.displayError("file Man setter problem");
       }
        }


    public static String getProduitToModifier(){
        File file = new File("ProductToModify");
       // String designation;
        StringObject designationObject=new StringObject();
        try{
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            designationObject=(StringObject) ois.readObject();
            return designationObject.designation;
        }catch(Exception e){
            System.out.println("file Man getter problem");
        }
        System.out.println("file Man Product getter problem fucked up , null string returned");
        return null;
}

    public static double getTVA(){
        File file = new File("TVA");
        if (!file.exists()){return 19;}
        else{
        DoubleObjectTVA doubleObjectTVA=new DoubleObjectTVA();
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            doubleObjectTVA = (DoubleObjectTVA) ois.readObject();
            return doubleObjectTVA.tva;
        }catch(Exception e){
            System.out.println("get TVA problem in FileMan");
        }
        System.out.println("ERROR :null double returned by getTVA in FileMan");
        return 19;
    }}

    public static void setTVA(double tva){
        File file = new File("TVA");
        try{
            DoubleObjectTVA doubleObjectTVA=new DoubleObjectTVA();
            doubleObjectTVA.tva=tva;
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(doubleObjectTVA);
            AlertClass.displayInformation("TVA Changé avec succès!\nVeillez redemarer le programme pour avoir les modifications");
        }catch(Exception e){
            System.out.println("ERROR: setTVA problem in FileMan");
        }
    }

    public static  Entreprise getEntreprise(){
        File file = new File("Entreprise");
        if(!file.exists()){
            return (new Entreprise("","","","",""));
        }
        else
        {
           Entreprise entreprise=new Entreprise();
            try {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                entreprise = (Entreprise) ois.readObject();
                return entreprise;
        }catch (Exception e){
                System.out.println("ERROR : probleme dans getEntreprise a FileMan");
            }
    }
        System.out.println("ERROR :null Entreprise returned by getEntreprise in FileMan");
        return (new Entreprise("","","","",""));
}

    public static void setEntreprise(Entreprise entreprise){
        File file = new File("Entreprise");
        try {
            Entreprise entreprise1 = new Entreprise();
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(entreprise);
            AlertClass.displayInformation("Informations d'entreprise modifié avec succès!");
        }catch(Exception e){
            System.out.println("ERROR: setTVA problem in FileMan!");
        }
    }

    public static int getNumDevis(){
        File file = new File("NumDevis");
        try{
        if (!file.exists()) {
            IntObject x = new IntObject();
            x.numDevis=0;
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(x);
            return 0;
        }else {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                IntObject intObject=(IntObject) ois.readObject();
           // System.out.println("numero dde devis :"+intObject.numDevis);
                return  intObject.numDevis;
            }
        }catch(Exception e){AlertClass.displayError("Erreur en numero de devis");return 1;}
        }

        public static void incrementNumDevis(){
            int oldNum=getNumDevis();
            IntObject x = new IntObject();
            File file = new File("NumDevis");
            x.numDevis=oldNum+1;
            try{
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(x);
            }
            catch(Exception e)
            {AlertClass.displayError("Un erreur a été produit lors d'incrementation de devis!");}
        }

}

