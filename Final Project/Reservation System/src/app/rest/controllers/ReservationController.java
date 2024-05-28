package app.rest.controllers;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import app.components.ReservationDTO;
import app.components.ReservationResponseDTO;
import app.components.ReservationComponent;


@Path("/reservations")
public class ReservationController 
{
	
	@Autowired
	ReservationComponent reservations;
	
	@POST
	@Path("/make")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ReservationResponseDTO makeReservation(ReservationDTO reserve) throws Exception
	{
		return reservations.makeReservation(reserve);
	
	}
	
	@POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/cancel")
	public ReservationResponseDTO cancelReservations(@FormParam("reservationID") Long reservationId) throws Exception
	{
		return reservations.cancelReservation(reservationId);
	
	}
	@POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/reject")
	public ReservationResponseDTO rejectReservations(@FormParam("reservationID") Long reservationId) throws Exception
	{
		return reservations.rejectReservation(reservationId);
	
	}
	@POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/accept")
	public ReservationResponseDTO acceptReservations(@FormParam("reservationID") Long reservationId) throws Exception
	{
		return reservations.acceptReservation(reservationId);
	
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
    public List<ReservationDTO> allReservation() throws Exception
	{
		return reservations.allReservation();
	
	}
	
	@POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/relocate")
	public ReservationResponseDTO suggestRoomNumber(@FormParam("reservationID") Long reservationId) throws Exception
	{
		return reservations.suggestRoomNumber(reservationId);
	
	}
}
