/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioproject;

/**
 *
 * @author Yltherson
 */
public class Adherant {
    private int idEtu;
    private String nomEtu;
    private String sexe;
    private String faculte;
    private String niveau;
    private String email;
    private String phone;
    private String adresse;
    
    public Adherant(int idEt, String nomEt, String sexeEtu,
            String fact, String niv, String mail, String ph, String adr){
        this.idEtu = idEt;
        this.nomEtu = nomEt;
        this.sexe = sexeEtu;
        this.faculte = fact;
        this.niveau = niv;
        this.email = mail;
        this.phone = ph;
        this.adresse = adr;
        
        
    }
    
//    accesseur id Etudiant
    public int getIdEtu(){
        return idEtu;
    }
    
//    mutateur id Etudiant
    public void setIdEtu(int idEt){
        this.idEtu = idEt;
    }
    
    
    //    accesseur nom Etudiant
    public String getNomEtu(){
        return nomEtu;
    }
    
//    mutateur nom Etudiant
    public void setNomEtu(String nomEt){
        this.nomEtu = nomEt;
    }

    //    accesseur id Etudiant
    public String getSexe(){
        return sexe;
    }
    
//    mutateur sexe
    public void setSexe(String sexeEtu){
        this.sexe = sexeEtu;
    }

//    accesseur faculte
    public String getFaculte(){
        return faculte;
    }
    
//    mutateur faculte
    public void setFaculte(String fact){
        this.faculte = fact;
    }
    
//    accesseur niveau
    public String getNiveau(){
        return niveau;
    }
    
//    mutateur niveau
    public void setNiveau(String niv){
        this.niveau = niv;
    }
    
//    accesseur mail
    public String getEmail(){
        return email;
    }
    
//    mutateur mail
    public void setMail(String mail){
        this.email = mail;
    }
    
//    accesseur phone
    public String getPhone(){
        return phone;
    }
    
//    mutateur phone
    public void setPhone(String ph){
        this.phone = ph;
    }
    
//    accesseur adresse
    public String getAdresse(){
        return adresse;
    }
    
//    mutateur adresse
    public void setAdresse(String adr){
        this.adresse = adr;
    }
    
    public void printObj(){
        System.out.println("ID : "+getIdEtu()+"\nNOM : "+getNomEtu()+"\nSEXE : "+getSexe()+
                "\nFACULTE : "+getFaculte()+"\nNIVEAU : "+getNiveau()+"\nEMAIL : "+getEmail()+
                "\nTELEPHONE : "+getPhone()+"\nADRESSE : "+getAdresse());
    }
}

