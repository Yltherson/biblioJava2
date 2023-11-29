/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioproject;

/**
 *
 * @author Yltherson
 */
public class PretLivre {
    private int idPret;
    private int idEtu;
    private int numL;
    private String datePret;
    private String dateLimit;
    
    public PretLivre(int idP, int idE, int num, String dateP, String dateL){
        this.idPret = idP;
        this.idEtu = idE;
        this.numL = num;
        this.datePret = dateP;
        this.dateLimit = dateL;
        
    }
    
//    accesseur
    public int getIdP(){
        return idPret;
    }
    
    public int getIdE(){
        return idEtu;
    }
    
    public int getNumL(){
        return numL;
    }
    
    public String getDateP(){
        return datePret;
    }
    
     public String getDateL(){
        return dateLimit;
    }
     
//     mutateur
     
    public void setIdPret(int idP){
        this.idPret = idP;
    }
     
    public void setIdEtu(int idE){
        this.idPret = idE;
    }
    
    public void setNumL(int num){
        this.numL = num;
    }
    
    public void setDateP(String dateP){
        this.datePret = dateP;
    }
    
    public void setDateL(String dateL){
        this.dateLimit = dateL;
    }
    
    public void printObjP(){
        System.out.println("NUMERO PRET : "+getIdP()+"\nID ETUDIANT : "+getIdE()+"\nNUMERO LIVRE : "+getNumL()+
                "\nDATE PRET : "+getDateP()+"\nDATE REMISE : "+getDateL());
    }
}
