package com.controleur;

import com.model.*;//pour  importer le service

//pour le rest
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/inscription")
public class ServiceApiRest {
    //model
    private static CompanyService userservice=new CompanyService();
    private static CheckInfo checkInfo=new CheckInfo();

    //service;
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addUser(@FormParam("name") String name,
                           @FormParam("email") String email,
                           @FormParam("password") String password){

        if(!userservice.isInitSuccessed()){
            return Response.status(Response.Status.NOT_FOUND)
                   .entity("Erreur au niveau de la creation de la  base de donne et de la table")
                   .build();
        }
        else if(!checkInfo.isIdentifiantValid(name)){
            return Response.status(Response.Status.NOT_FOUND)
                   .entity("Erreur. Vous avez donne un nom non acceptable par L-COMPANY")
                   .build();
        }
        else if(!checkInfo.isEmailValid(email)){
            return Response.status(Response.Status.NOT_FOUND)
                   .entity("Erreur. Vous avez donne un email non acceptable par L-COMPANY")
                   .build();
        }
        else if(!checkInfo.isIdentifiantValid(password)){
            return Response.status(Response.Status.NOT_FOUND)
                   .entity("Erreur. Vous avez donne un mot de passe non acceptable par L-COMPANY")
                   .build();
        }
        else if(userservice.isUserExit(name)){
            return Response.status(Response.Status.NOT_FOUND)
                   .entity("Erreur : Nom existe deja dans la base de donnee ")
                   .build();
        }
        else if(userservice.isEmailExit(email)){
            return Response.status(Response.Status.NOT_FOUND)
                   .entity("Erreur : Email existe deja dans la base de donnee ")
                   .build();
        }
        else{
            if(userservice.addNewUser(name, email, password))
                return Response.ok("Felicitation vous etes maintenant un utilisateur de L-COMPANY.").build();
            
            else{
                return Response.status(Response.Status.NOT_FOUND)
                   .entity("Erreur : Peut pas inserer l utilisateur  ")
                   .build();
            }
        }
    }
}

// --------------------------------------------------------------------> I am L 