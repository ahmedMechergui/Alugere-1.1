package sample;

import java.io.Serializable;

public class  Entreprise implements Serializable {

     public String adresse;
     public String rue;
     public String email;
     public String telephone1;
     public String telephone2;

    public Entreprise() {}

    public Entreprise(String adresse, String rue, String email, String telephone1, String telephone2) {
        this.adresse = adresse;
        this.rue = rue;
        this.email = email;
        this.telephone1 = telephone1;
        this.telephone2 = telephone2;
    }
}
