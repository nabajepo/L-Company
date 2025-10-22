package com.controleur;

//pour les servlet
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

//pour l'api
import com.model.*;

@WebServlet("/connect")
public class ServiceApiServlet extends HttpServlet {
    //model
    private static CompanyService userservice=new CompanyService();
    private static CheckInfo checkInfo=new CheckInfo();

    //pour l'erreur
    private final String errorType="error";

    //pour les contacts
    private final String users="usersList";
    //post
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

       //on recupere les paramatres 
       String value=request.getParameter("value");//nom ou mot-de-passe
       String password=request.getParameter("password");
       //user
       String[] user1={value.trim(),password.trim()};
       
       //pour check un si la base de donne est initialiser 
       if(!userservice.isInitSuccessed()){
          request.setAttribute(errorType, "Impossible de se connecter à la base de donnee");
          request.getRequestDispatcher("notification.jsp").forward(request, response);
       }
       
       //pour savoir si les info ne sont  pas null 
       else if(!checkInfo.isIdentifiantValid(value) && !checkInfo.isIdentifiantValid(password)){
         request.setAttribute(errorType, "Erreur dans les donnees insere (variable null ) ");
         request.getRequestDispatcher("notification.jsp").forward(request, response);
       }

       //si c'est un nom
       else if(!checkInfo.isEmail(value)){
         if(userservice.isUserExit(value)){//si l'utilisateur existe
            String[] user2=userservice.getUserInfoByName(value);
            if(checkInfo.isUsersMatch(user1,user2)){//si c'est bon 
               request.setAttribute(users, userservice.getUsersExcept(value,"nameType"));
               request.getRequestDispatcher("contacts.jsp").forward(request, response);
            }
            else{
                request.setAttribute(errorType, "Mot de passe incorrect (Nom comme identifiant)");
                request.getRequestDispatcher("notification.jsp").forward(request, response);
            }
         }
         else{//sinon
            request.setAttribute(errorType, "Desole vous n'etes pas membre de L-COMPANY");
            request.getRequestDispatcher("notification.jsp").forward(request, response);
         }
        
       }
       else{//si c'est un email
          if(userservice.isEmailExit(value)){//si l'email existe
             String[] user2=userservice.getUserInfoByEmail(value);
             if(checkInfo.isUsersMatch(user1,user2)){//si c'est bon 
               request.setAttribute(users, userservice.getUsersExcept(value,"email"));
               request.getRequestDispatcher("contacts.jsp").forward(request, response);
            }
            else{
                request.setAttribute(errorType, "Mot de passe incorrect  (Email comme identifiant)");
                request.getRequestDispatcher("notification.jsp").forward(request, response);
            }
          }
          else{//sinon
            request.setAttribute(errorType, "Desole vous n'etes pas membre de L-COMPANY");
            request.getRequestDispatcher("notification.jsp").forward(request, response);
         }
       }
    }
    

}
//--------------------------------------------------------------------> I am L