package service;

import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import vo.CiudadanoRestVO;

import dao.CiudadanoRestDAO;

@Path("/Ciudadano")
public class CiudadanoRestController {

	CiudadanoRestDAO CiudadanoDAO = new CiudadanoRestDAO();
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response GetCiudadanoById(@PathParam("id") Long id) {
		
	CiudadanoRestVO UsuarioObtenidoPorId = CiudadanoRestDAO.GetCiudadanoByIdDAO(id); 
	
	if(UsuarioObtenidoPorId != null) {
		return Response.status(Response.Status.OK).
			   entity(UsuarioObtenidoPorId).
			   type(MediaType.APPLICATION_JSON).
			   build();
	} else {
		return Response.status(Response.Status.NOT_FOUND).
			   entity("{\"ERROR\":\"NO EXISTE UN CIUDADANO CON LA ID:" + id + "\"}").
			   type(MediaType.APPLICATION_JSON).
			   build();	
		}	
	}
	
	
	
	@POST
	@Path("/AñadirCiudadano")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCiudadano (CiudadanoRestVO ciudadano) {
		
	if (CiudadanoRestDAO.AddCiudadano(ciudadano)) {
		return Response.status(Response.Status.OK).
				entity("{\"MENSAJE\":\"SE INGRESO CIUDADANO CORRECTAMENTE:\"}").
				type(MediaType.APPLICATION_JSON).
				build();
	} else {
		return Response.status(Response.Status.CONFLICT).
				   entity("{\"MENSAJE\":\"CIUDADANO NO INGRESADO:\"}").
				   type(MediaType.APPLICATION_JSON).
				   build();	
		}	
	}
	
	@GET
	@Path("/ConsultarCiudadanos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response RequestCiudadanos(){
	
		ArrayList<CiudadanoRestVO> ListaCiudadanos = CiudadanoRestDAO.RequestCiudadanos();
		
		if (ListaCiudadanos != null) {
			return Response.status(Response.Status.OK).
					entity(ListaCiudadanos).
					type(MediaType.APPLICATION_JSON).
					build();
		} else {
			return Response.status(Response.Status.CONFLICT).
					entity("{\"MENSAJE\":\"CONSULTA DE CIUDADANOS NO ENCONTRADO:\"}").
					type(MediaType.APPLICATION_JSON).
					build();
		}	
	}
	
	
	@DELETE
	@Path("/EliminarCiudadanoPorId/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response DeleteCiudadanoById(@PathParam(value = "id")Long id) {
	
	if (CiudadanoRestDAO.DeleteCiudadanoById(id)) {
		return Response.status(Response.Status.OK).
				entity("{\"MENSAJE\":\"CIUDADANO ELIMINADO CON ID: " + id + ":\"}").
				type(MediaType.APPLICATION_JSON).
				build();
	} else {
		return Response.status(Response.Status.CONFLICT).
				entity("{\"MENSAJE\":\"ERROR, USUARIO CON ID: " + id + "NO ENCONTRADO/NO ELIMINADO\"}").
				type(MediaType.APPLICATION_JSON).
				build();
		}
	}
	
	// http://localhost:8080/Ejercicio7_API/jwa/Ciudadano/EliminarCiudadanoPorId/5
}
