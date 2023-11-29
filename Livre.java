/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioproject;

/**
 *
 * @author Yltherson
 */
public class Livre {
    private int numLivre;
    private String titre, auteur, annee;
    
//    constructeur
    public Livre(int _numLivre, String _titre, String _auteur, String _annee){
        this.numLivre = _numLivre;
        this.titre = _titre;
        this.auteur = _auteur;
        this.annee = _annee;
    }
    
//    accesseur
    public int getNumLivre(){
        return numLivre;
    }
    
    public String getTitre(){
        return titre;
    }
    
    public String getAuteur(){
        return auteur;
    }
    
    public String getAnnee(){
        return annee;
    }
    
//    Moutateur
    public void setNum(int id){
        this.numLivre = id;
    }
    
    public void setTitre(String nTitre){
        this.titre = nTitre;
    }
    
    public void setAuteur(String nAuteur){
        this.titre = nAuteur;
    }
    
    public void setAnnee(String nAnnee){
        this.titre = nAnnee;
    }
    
    public void printObjL(){
        System.out.println("NUMERO : "+getNumLivre()+"\nTITRE : "+getTitre()+"\nAUTEUR : "+getAuteur()+
                "\nANNEE : "+getAnnee());
    }
}
