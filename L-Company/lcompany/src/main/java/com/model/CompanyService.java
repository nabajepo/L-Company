//------------------------------------SERVICE CONNEXION ET STOCKAGE USERS-------------------------------------------------
package com.model;

import java.sql.*;//pour connecter mysql au projet
import java.util.ArrayList;


public class CompanyService {
    private String jdbcurl;//url de jdbc
    private String usermysql;//nom du l'utilisateur
    private String passwordmysql;//mot de passe de mysql

    //Pour la connection l'insertion et la recuperation des donnees dans mysql
    private Connection connDBS;
    private Statement statement;
    private ResultSet getInfos;//pour recuperer les données dans la dbs(SELECT)
    private int result;//pour changer la base de données(INSERT,CREATE,DELETE)
    private String request;//pour recuperer les info
    //pour savoir si l'initialisation est bienfait
    private boolean initSuccessed;
    public CompanyService(){
        jdbcurl="jdbc:mysql://localhost:3306";
        usermysql="";//remplacer ici par votre nom
        passwordmysql="";//remplacer le password par le votre

        //creer une dbs
        if(createDBS() && createTable())initSuccessed=true;
        else initSuccessed=false;

    }

    //pour creer la dbs
    private boolean createDBS(){
        try{
            //pour la connection dans la dbs
            Class.forName("com.mysql.cj.jdbc.Driver");
            connDBS=DriverManager.getConnection(jdbcurl, usermysql, passwordmysql);
            statement=connDBS.createStatement();
            //pour creer la dbs
            request="create database if not exists ldbs";
            result=statement.executeUpdate(request);
            
            //pour verifier que ca marche 
            if (result == 1 || result == 0) return true;
            else return false;
        }
        catch(SQLException |ClassNotFoundException e){return false;}
    }

    //pour creer la table
    private boolean createTable(){
        jdbcurl="jdbc:mysql://localhost:3306/ldbs";
        try{
            //pour la connection dans la dbs
            Class.forName("com.mysql.cj.jdbc.Driver");
            connDBS=DriverManager.getConnection(jdbcurl, usermysql, passwordmysql);
            statement=connDBS.createStatement();
            //pour creer la table d'users
            request="create table if not exists users(name varchar(50),email varchar(50),password varchar(50))";
            result=statement.executeUpdate(request);
            
            //pour verifier que ca marche 
            if (result == 1 || result == 0) return true;
            else return false;
        }
        catch(SQLException |ClassNotFoundException e){return false;}
    } 
    //pour sacoir si la table a ete bien configurer
    public boolean isInitSuccessed(){
        return initSuccessed;
    }
    
    //pour obtenir toute les utilisateurs sauf l'utilisateur en question
    public ArrayList<String[]> getUsersExcept(String userOnline,String typeUser){
        ArrayList<String[]> users=new ArrayList<>();
        try{
            //pour la connection dans la dbs
            Class.forName("com.mysql.cj.jdbc.Driver");
            connDBS=DriverManager.getConnection(jdbcurl, usermysql, passwordmysql);
            statement=connDBS.createStatement();
            //pour recuperer toutes les users 
            if(typeUser.equals("nameType"))request="select name,email from users where name !='" + userOnline + "'";//si c'est un nom
            else request="select name,email from users where email !='" + userOnline + "'";//si c'est un email
            getInfos=statement.executeQuery(request);
            //pour recuper les donnees
            while(getInfos.next()){
                String[] user=new String[2];
                user[0]=getInfos.getString("name");
                user[1]=getInfos.getString("email");
                users.add(user);
            }
        }
        catch(SQLException |ClassNotFoundException e){}
        return users;
    }
    
    //pour savoir si un utilisateur existe à travers le nom
    public  boolean isUserExit(String name){
        try{
            //pour la connection dans la dbs
            Class.forName("com.mysql.cj.jdbc.Driver");
            connDBS=DriverManager.getConnection(jdbcurl, usermysql, passwordmysql);
            statement=connDBS.createStatement();
            //pour recuperer toutes les users 
            request = "select * from users where name='" + name + "'";
            getInfos=statement.executeQuery(request);
            //pour savoir si ca existe
            if(getInfos.next())return true;
            else return false;
        }
        catch(SQLException |ClassNotFoundException e){return false;}
    }
    //pour savoir si un utilisateur existe à travail l'email
    public  boolean isEmailExit(String email){
        try{
            //pour la connection dans la dbs
            Class.forName("com.mysql.cj.jdbc.Driver");
            connDBS=DriverManager.getConnection(jdbcurl, usermysql, passwordmysql);
            statement=connDBS.createStatement();
            //pour recuperer toutes les users 
            request = "select * from users where email='" + email + "'";
            getInfos=statement.executeQuery(request);
            //pour savoir si ca existe
            if(getInfos.next())return true;
            else return false;
        }
        catch(SQLException |ClassNotFoundException e){return false;}
    }

    //pour avoir les infos d'un utilisateur par name
    public String[] getUserInfoByName(String name){
        String[] user=new String[2];
        try{
            //pour la connection dans la dbs
            Class.forName("com.mysql.cj.jdbc.Driver");
            connDBS=DriverManager.getConnection(jdbcurl, usermysql, passwordmysql);
            statement=connDBS.createStatement();
            //pour recuperer les infos par le nom
            request = "select name,password from users where name='" + name + "'";
            getInfos=statement.executeQuery(request);
            //pour recuperer les info d'un utilisateur
            if(getInfos.next()){
                user[0]=getInfos.getString("name");
                user[1]=getInfos.getString("password");  
            }
              
        }
        catch(SQLException |ClassNotFoundException e){}
        return user;
    }

    //pour avoir les infos d'un utilisateur par email
    public String[] getUserInfoByEmail(String email){
        String[] user=new String[2];
        try{
            //pour la connection dans la dbs
            Class.forName("com.mysql.cj.jdbc.Driver");
            connDBS=DriverManager.getConnection(jdbcurl, usermysql, passwordmysql);
            statement=connDBS.createStatement();
            //pour recuperer l'utilisateur par email
            request = "select email,password from users where email='" + email + "'";
            getInfos=statement.executeQuery(request);
            //pour recuperer les info d'un utilisateur
            if(getInfos.next()){
                user[0]=getInfos.getString("email");
                user[1]=getInfos.getString("password");  
            }
        }
        catch(SQLException |ClassNotFoundException e){}
        return user;
    }
    //pour ajouter un nouvel utilisateur 
    public boolean addNewUser(String name,String email,String password){
        try{
            //pour la connection dans la dbs
            Class.forName("com.mysql.cj.jdbc.Driver");
            connDBS=DriverManager.getConnection(jdbcurl, usermysql, passwordmysql);
            statement=connDBS.createStatement();
            //pour recuperer l'utilisateur par email
            request = "insert into users (name, email, password) values ('" + name.trim() + "', '" + email.trim() + "', '" + password.trim() + "')";
            result=statement.executeUpdate(request);
            //pour verifier que ca marche 
            if (result==1) return true;
            else return false;
        }
        catch(SQLException |ClassNotFoundException e){return false;}
    }
    
}

//--------------------------------------------------------------------> I am L