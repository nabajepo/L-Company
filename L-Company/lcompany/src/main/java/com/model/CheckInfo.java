//------------------------------------SERVICE DE VERIFICATION DES INFOS INSERER-------------------------------------------------
package com.model;

public class CheckInfo {
    //pour verifier si un nom est valid ou password
    public boolean isIdentifiantValid(String value){
        if(value==null ||value.length()==0) return false;
        return true;
    }
    //pour verifier si un email est valid
    public boolean isEmailValid(String email){
        if(email==null || email.length()==0)return false;
        else if(!isEmail(email))return false;
        else return true;
    }

    //pour savoir si ca contient un @
     //pour savoir si l'element rentre est un email ou un  nom
    public boolean isEmail(String email){
        String[] emailValue=email.split("");
        for(int i=0;i<emailValue.length;i++){
            if(emailValue[i].equals("@"))return true;
        }
        return false;
    }
   //pour comparer 2 utilisateurs
   public boolean isUsersMatch(String[] user1,String[] user2){
       return user1[0].equals(user2[0]) &&
              user1[1].equals(user2[1]) ;
   }
    
}
