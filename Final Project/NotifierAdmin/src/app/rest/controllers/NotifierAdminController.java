package app.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import app.components.NotifierAdminComponent;
import app.entities.EmailRequest; // Assuming EmailRequest is a class representing the JSON request

@Path("/email")
public class NotifierAdminController {

    @Autowired
    NotifierAdminComponent emailComponent;

    @POST
    @Path("/send")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String send(@FormParam("subject") String subject,
                       @FormParam("message") String message,
                       @FormParam("email") String email) {
        return emailComponent.send(subject, message, email);
    }

    @POST
    @Path("/receive")
    @Consumes(MediaType.APPLICATION_JSON)
    public String receive(EmailRequest emailRequest) {
        return emailComponent.receive(emailRequest);
    }
}
