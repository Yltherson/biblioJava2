/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioproject;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;
/**
 *
 * @author PC
 */

public class Main {
    static boolean val;
    static Scanner input = new Scanner(System.in);
    
    private static final Connexion jdb = new Connexion();
    
    private static PreparedStatement state;
    private static final Connection con = jdb.api();
    /**
     *
     * @param args
     * @throws java.sql.SQLException
     */
    
//    Fonction principal
    public static void main(String[] args) throws SQLException{
        
        String reponse;
        do{
            System.out.println("""
                               Appuyer sur un des nombres ci-dessous pour: 
                               
                               1- Ajouter un etudiant
                               2- Ajouter un livre
                               3- Ajouter un pret
                               4- afficher les etudiants
                               5- afficher les livres
                               6- afficher les prets
                               7- Supprimer etudiant
                               8- Modifier etudiant
                               9- Supprimer un livre
                               10- Modifier un livre
                               11- Afficher un etudiant
                               12- Suprimer un pret
                               """);
            String choix = input.nextLine();
            switch(choix){
                case "1" -> ajoutEtu();
                case "2" -> ajoutLivre();
                case "3" -> ajoutPret();
                case "4" -> affichageEtudiant();
                case "5" -> affichageLivre();
                case "6" -> affichagePret();
                case "7" -> {
                    System.out.println("Entrer l'id de l'etudiant a supprimer :");
                    int id = input.nextInt();
                    input.nextLine();
                    delEtudiant(id);
                }
                case "8" -> {
                    System.out.println("Entrer l'id de l'etudiant a modifier :\n");
                    modifierEt(input.nextInt());
                }
                case "9" -> {
                    System.out.println("Entrer le numero du livre a supprimer :");
                    int num = input.nextInt();
                    input.nextLine();
                    supprimerLivre(num);
                }
                case "10" -> {
                    System.out.println("Entrer le numero du livre a modifier : ");
                    modifierL(input.nextInt());
                }
                case "11" -> afficherUnEtudiant();
                
//                case "12" -> afficherUnLivre();
                case "12" -> {
                    System.out.println("Entrer le numero du pret a supprimer : ");
                    int numPret = input.nextInt();
                    input.nextLine();
                    supprimerP(numPret);
                }
                default -> System.out.println("Vous n'avez pas fait un choix valide!");
            }
            
            System.out.println("""
                               
                               Appuyer sur <<Y>> pour continuer le programme et
                               retourner au menu principal.
                               ou
                               Appuyer sur n'importe quelle autre touche
                               sauf(power) pour quitter le programme.
                               """);
            
            reponse = input.nextLine();
        }while("Y".equals(reponse.toUpperCase()));
        
    }
    
//    variable passer en parametre a l'objet etudiant
    static int idEt;
    static String nomEt,sexe,fact,niveau,email,phone,adresse;
//    instanciation de l'objet etudiant et de sa liste
    static Adherant Ad;
    static ArrayList<Adherant> LAdherant = new ArrayList<>();
    
//    fonction permettant d'ajouter un etudiant
    
    public static void ajoutEtu() throws SQLException{
        System.out.println("--AJOUTER UN ADHERANT--\n");
        System.out.println("Entrer l'identifiant de l'etudiant");
        idEt = input.nextInt();
        while(verifE(idEt)){
            System.out.println("""
                                                Cet(te) etudiant(e) est deja enregistrer.
                                                Entrer un autre identifiant svp
                                """);
            idEt = input.nextInt();
        }
        input.nextLine();
        
        System.out.println("Entrer le nom et prenom de l'etudiant.");
        nomEt = input.nextLine();
        
        System.out.println("Entrer le sexe :");
        sexe = input.nextLine();
        
        System.out.println("Entrer la faculte :");
        fact = input.nextLine();
        
        System.out.println("Entrer le niveau :");
        niveau = input.nextLine();
        
        System.out.println("Entrer l'email :");
        email = input.nextLine();
        
        System.out.println("Entrer le numero de telephone :");
        phone = input.nextLine();
        
        System.out.println("Entrer l'adresse :\n");
        adresse = input.nextLine();
        
        
        Ad = new Adherant(idEt, nomEt, sexe, fact, niveau, email, phone, adresse);
//        ajout d'un objet etudiant dans la liste
//        LAdherant.add(Ad);
//        Ad.printObj();
        String rq = "insert into adherant values(?,?,?,?,?,?,?,?)";
        state = con.prepareStatement(rq);
        
        state.setInt(1, Ad.getIdEtu());
        state.setString(2, Ad.getNomEtu());
        state.setString(3, Ad.getSexe());
        state.setString(4, Ad.getFaculte());
        state.setString(5, Ad.getNiveau());
        state.setString(6, Ad.getEmail());
        state.setString(7, Ad.getPhone());
        state.setString(8, Ad.getAdresse());
        
        int n = state.executeUpdate();
        
        System.out.println(n+" enregistrement effectuer avec succes.");
    }
    
  
 //    fonction permettant de verifier l'unicite d'un etudiant
    public static boolean verifE(int num) throws SQLException{
//        boucle parcourant la liste des etudiants
        String requeteV = "select * from adherant";
        state = con.prepareStatement(requeteV);
        ResultSet resultat = state.executeQuery();
        
        while(resultat.next()){
            if(resultat.getInt("id") == num){
//                val prend la valeur true
                val = true;
//                si on trouve un etudiant on arrete la boucle
                break;
            }
            else{
                val = false;
            }
        }
//        la valeur retourner est soit vrai soit faux
        return val;
    }
    
    
            //    Fonction affichage liste etudiant
    public static void affichageEtudiant() throws SQLException{
        System.out.println("\nAffichage etudiant\n");
//        for( Adherant Adr : LAdherant){
//            Adr.printObj();
//            System.out.println("\n-----------------\n");
//        }
        
        state = con.prepareStatement("select * from adherant");
        ResultSet result = state.executeQuery();
        
        while(result.next()){
            idEt = result.getInt("id");
            System.out.println("ID : "+idEt);
            
            nomEt = result.getString("nomC");
            System.out.println("NOM : "+nomEt);
            
            sexe = result.getString("sexe");
            System.out.println("SEXE : "+sexe);
            
            fact = result.getString("faculte");
            System.out.println("FACULTE : "+fact);
            
            niveau = result.getString("niveau");
            System.out.println("NIVEAU : "+niveau);
            
            email = result.getString("email");
            System.out.println("EMAIL : "+email);
            
            phone = result.getString("telephone");
            System.out.println("PHONE : "+phone);
            
            adresse = result.getString("adresse");
            System.out.println("ADRESSE : "+adresse+"\n");
        }
    }
    
    
    
//    instanciation et creation de l'objet liste de livre
    static Livre livre;
//    static ArrayList<Livre> livreL = new ArrayList<>();
//    variable passer en parametre a l'objet livre
    static int numLivre;
    static String titre, auteur, annee;
    
    
//    fonction permettant d'ajouter un livre
    public static void ajoutLivre() throws SQLException{
        System.out.println("--AJOUTER UN LIVRE--\n");
        System.out.println("Entrer le numero du livre");
        numLivre = input.nextInt();
        while(verifL(numLivre)){
            System.out.println("""
                                                Ce livre est deja enregistrer !
                                                Veuillez entrer un autre numero !""");
            numLivre = input.nextInt();
        }
        input.nextLine();
        
        System.out.println("Entrer le titre du livre :");
        titre = input.nextLine();
        
        System.out.println("Entrer le nom de l'auteur :");
        auteur = input.nextLine();
        
        System.out.println("Entrer l'annee du livre :");
        annee = input.nextLine();
        
//        instanciation de l'objet livre
        livre = new Livre(numLivre, titre, auteur, annee);
//        ajout du livre dans la liste
//        livreL.add(livre);
        
        String reqL = "insert into livre values(?, ?, ?, ?)";
        
        state = con.prepareStatement(reqL);
        
        state.setInt(1, livre.getNumLivre());
        state.setString(2, livre.getTitre());
        state.setString(3, livre.getAuteur());
        state.setString(4, livre.getAnnee());
        
        int n = state.executeUpdate();
        System.out.println(n+" enregistrement effectuer avec succes.");
    }
    
    
//    fonction permettant de verifier l'unicite d'un livre
    
    public static boolean verifL(int num) throws SQLException{
//        boucle parcourant la liste des livres
        String requeteV = "select * from livre ";
        state = con.prepareStatement(requeteV);
        ResultSet result = state.executeQuery();
        
        while(result.next()){
            if(result.getInt("numLivre") == num){
//                val prend la valeur true
                val = true;
//                si on trouve un livre on arrete la boucle
                break;
            }
            else{
                val = false;
            }
        }

//        la valeur retourner est soit vrai soit faux
        return val;
    }
    
        //    Fonction affichage liste livre
    public static void affichageLivre() throws SQLException{
        System.out.println("\nAffichage livre\n");
        state = con.prepareStatement("select * from livre");
        ResultSet result = state.executeQuery();
        
        while(result.next()){
            System.out.println("NUMERO : "+result.getInt("numLivre"));
            
            System.out.println("TITRE : "+result.getString("titre"));
            
            System.out.println("AUTEUR : "+result.getString("auteur"));
            
            System.out.println("ANNEE : "+result.getString("annee")+"\n");
            
        }
    }
    
    
    
//    instanciation de l'objet pret et de sa liste
    static PretLivre pLivre;
//    static ArrayList<PretLivre> pretL = new ArrayList<>();
//    declaration des variables globale passe en parametre a l'objet pret
    static int idPret, idEtu, numL;
    static String datePret, dateLimit;
    
    
//    fonction permettant de faire des pret
    public static void ajoutPret() throws SQLException{
        System.out.println("--AJOUTER UN PRET--\n");
        System.out.println("Entrer le numero de pret");
        idPret = input.nextInt();
//        verification de l'unicite du pret
        while(verifP(idPret)){
            System.out.println("""
                                                Ce livre est deja preter !
                                                Veuillez entrer un autre numero !
                               """);
            idPret = input.nextInt();
        }
        input.nextLine();
        
        System.out.println("Entrer le numero de l'etudiant :");
        idEtu = input.nextInt();
        
        while(!verifE(idEtu)){
            System.out.println("""
                               cet etudiant n'existe pas.
                               Entrer un autre identifiant :""");
            idEtu = input.nextInt();
        }
        
        do{
            System.out.println("Entrer le numero du livre :");
            numL = input.nextInt();
            if(!verifL(numL)){
                System.out.println("""
                                   ce livre n'existe pas.
                                   Entrer un autre numero :""");
            }
        }while(!verifL(numL));
        input.nextLine();
        
        System.out.println("Entrer la date du pret :");
        datePret = input.nextLine();
        
        System.out.println("Entrer la date limite du pret :");
        dateLimit = input.nextLine();
        
//        instanciatipn
        pLivre = new PretLivre(idPret, idEtu, numL, datePret, dateLimit);
//        ajout de l'objet dans la liste
//        pretL.add(pLivre);
        String reqP = "insert into pret values(?,?,?,?,?)";
        state = con.prepareStatement(reqP);
        
        state.setInt(1,pLivre.getIdP());
        state.setInt(2,pLivre.getIdE());
        state.setInt(3,pLivre.getNumL());
        state.setString(4, pLivre.getDateP());
        state.setString(5,pLivre.getDateL());
        
        int n = state.executeUpdate();
        System.out.println(n+" enregistrement effectuer avec succes.");
    }
    
    
//    fonction permettant de verifier l'unicite d'un pret
    public static boolean verifP(int num) throws SQLException{
//        boucle parcourant la liste des livres
        String req = "select * from pret ";
        state = con.prepareStatement(req);
        ResultSet res = state.executeQuery();
        
        while(res.next()){
            if(res.getInt("idPret") == num){
//                val prend la valeur true
                val = true;
//                si on trouve un livre on arrete la boucle
                break;
            }
            else{
                val = false;
            }
        }

//        la valeur retourner est soit vrai soit faux
        return val;
    }
    
    
//    Fonction affichage liste des livres
    public static void affichagePret() throws SQLException{
        System.out.println("\nAffichage pret\n");
        state = con.prepareStatement("select * from pret");
        ResultSet res = state.executeQuery();
        
        while(res.next()){
            System.out.println("NUMERO PRET : "+res.getInt("idPret"));
            
            System.out.println("ID ETUDIANT : "+res.getInt("id"));
            
            System.out.println("NUMERO LIVRE : "+res.getInt("numLivre"));
            
            System.out.println("DATE PRET : "+res.getString("datePret"));
            
            System.out.println("DATE LIMITE : "+res.getString("dateLimite")+"\n");
            
        }
    }

    
    
//    fonction supprimer etudiant
    public static void delEtudiant(int id) throws SQLException{
//        Iterator<Adherant> it = LAdherant.iterator();
//        Adherant i;
        String rq = "delete from adherant where id = ?";
        
//        on verifie l'exitence de l'etudiant dans la liste avec la fonction
        if(verifE(id)){
            state = con.prepareStatement(rq);
            state.setInt(1, id);
            state.executeUpdate();
        }
        else{
            System.out.println("Cet etudiant n'est pas encore enregistrer.");
        }
    }
    
//    Fonction modifier etudiant
    public static void modifierEt(int id) throws SQLException{
        if(verifE(id)){
            System.out.println("Entrer les nouvelles informations de l'etudiant.");
            
//            System.out.println("Entrer l'identifiant :");
//            idEtu = input.nextInt();
            input.nextLine();
            
            System.out.println("Entrer le nom et prenom :");
            nomEt = input.nextLine();
            
            System.out.println("Entrer le sexe :");
            sexe = input.nextLine();
            
            System.out.println("Entrer faculte :");
            fact = input.nextLine();
            
            System.out.println("entrer le niveau :");
            niveau = input.nextLine();
            
            System.out.println("Entrer l'email :");
            email = input.nextLine();
            
            System.out.println("Entrer le numero telephone :");
            phone = input.nextLine();
            
            System.out.println("Entrer l'adresse :");
            adresse = input.nextLine();
            
            String rq = "UPDATE adherant SET nomC=?, sexe=?, faculte=?, niveau=?, email=?, telephone=?, adresse=? where id=?";
            
            state = con.prepareStatement(rq);
            
//            state.setInt(1, idEtu);
            state.setString(1, nomEt);
            state.setString(2, sexe);
            state.setString(3, fact);
            state.setString(4, niveau);
            state.setString(5, email);
            state.setString(6, phone);
            state.setString(7, adresse);
            state.setInt(8, id);
            
            state.executeUpdate();
        }
        else{
            System.out.println("Cet etudiant n'est pas encore enregistrer.");
        }
    }
    
    //fonction suppression de livre
    static void supprimerLivre(int num) throws SQLException{
//        Iterator<Livre> it = livreL.iterator();
//        Livre i;
        String rq = "delete from livre where numLivre = ?";
//        on verifie l'exitence de l'etudiant dans la liste avec la fonction
        if(verifL(num)){
            state = con.prepareStatement(rq);
            state.setInt(1, num);
            state.executeUpdate();
        }
        else{
            System.out.println("Ce livre n'est pas encore enregistrer.");
        }
    }
    
    //fonction modifier livre
    public static void modifierL(int num) throws SQLException{
        if(verifL(num)){
            input.nextLine();
            System.out.println("Entrer les nouvelles informations du livre.\n");
                    
            System.out.println("Entrer le titre :");
            titre = input.nextLine();
            
            System.out.println("Entrer l'auteur :");
            auteur = input.nextLine();
            
            System.out.println("Entrer l'annee :");
            annee = input.nextLine();
            
            String rqt = "UPDATE livre SET titre=?, auteur=?, annee=? where numLivre=?";
            
            state = con.prepareStatement(rqt);
            
            state.setString(1, titre);
            state.setString(2, auteur);
            state.setString(3, annee);
            state.setInt(4, num);
            
            state.executeUpdate();
        }
        else{
            System.out.println("Ce livre n'est pas encore enregistrer.");
        }
    }
    
    //fonction permettant de supprimer un pret
    public static void supprimerP(int idP) throws SQLException{
//        Iterator<PretLivre> it = pretL.iterator();
//        PretLivre i;
        String rq = "delete from pret where idPret = ?";
//        on verifie l'exitence de l'etudiant dans la liste avec la fonction
        if(verifP(idP)){
            state = con.prepareStatement(rq);
            state.setInt(1, idP);
            state.executeUpdate();
        }
        else{
            System.out.println("Aucun pret trouver");
        }

    }
    
//    fonction permettant d'afficher un etudiant
    public static void afficherUnEtudiant() throws SQLException{
        System.out.println("\nEntrer l'id de l'etudiant a afficher :\n");
        int id = input.nextInt();
        input.nextLine();
        
        String rqt = "SELECT * FROM adherant WHERE id=?";
        if(verifE(id)){
            System.out.println("\nEtudiant\n");
            
            state = con.prepareStatement(rqt);
            state.setInt(1,id);
            
            ResultSet rs = state.executeQuery();
            
            while(rs.next()){
                System.out.println("ID : "+rs.getInt("id"));
                System.out.println("NOM : "+rs.getString("nomC"));
                System.out.println("SEXE : "+rs.getString("sexe"));
                System.out.println("FACULTE : "+rs.getString("faculte"));
                System.out.println("NIVEAU : "+rs.getString("niveau"));
                System.out.println("EMAIL : "+rs.getString("email"));
                System.out.println("TELEPHONE : "+rs.getString("telephone"));
                System.out.println("ADRESSE : "+rs.getString("adresse"));
            }
            
        }
        else{
            System.out.println("Cet etudiant n'est pas encore enregistrer.");
        }
        
    }
    
//    fonction pour afficher un livre
//    public static void afficherUnLivre() throws SQLException{
//        System.out.println("Entrer le numero du livre a afficher :");
//        int num = input.nextInt();
//        input.nextLine();
//        Iterator<Livre> it = livreL.iterator();
//        Livre i;
////        on verifie l'exitence du livre dans la liste avec la fonction
//        if(verifE(num)){
//            while(it.hasNext()){
//                i = it.next();
//                if(i.getNumLivre() == num){
//                    i.printObjL();
//                }
//            }
//        }
//        else{
//            System.out.println("Ce livre n'est pas encore enregistrer.");
//        }
//    }
//    
}
