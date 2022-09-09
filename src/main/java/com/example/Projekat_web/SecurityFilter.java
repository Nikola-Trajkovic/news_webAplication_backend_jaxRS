package com.example.Projekat_web;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class SecurityFilter implements ContainerResponseFilter {


    private static final String SECURED_URL_PREFIX = "admin";

    @Override
    public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException {

        //System.out.println("USAO U SECURITY");
//        System.out.println(HelloApplication.getEmail());
//        System.out.println(HelloApplication.getLozinka());

        if(request.getUriInfo().getPath().contains(SECURED_URL_PREFIX)){



            if(!HelloApplication.getEmail().equals("admin") || !HelloApplication.getLozinka().equals("8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918")){

               // System.out.println("Usao u if");
                Response unauthorizedStatus = Response
                        .status(Response.Status.UNAUTHORIZED)
                        .entity("User cannot access the resourse")
                        .build();
                request.abortWith(unauthorizedStatus);

            }
            return;


        }

        return;


    }
}
