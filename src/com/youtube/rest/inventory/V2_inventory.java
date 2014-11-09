package com.youtube.rest.inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;

import com.youtube.dao.Schema308Tube;


@Path("/v2/inventory")
public class V2_inventory {
	Connection conn=null;
	PreparedStatement query=null;
	String returnString=null;
	Response rb=null;
	JSONArray json=null;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnAllPcParts(
			@QueryParam("brand") String  brand){	
		try {
			
			if (brand==null){
				return Response.status(400).entity("Error: Please especify a brand").build();
			}
			Schema308Tube dao=new Schema308Tube();
			json=dao.queryReturnBrandParts(brand);
	
			returnString=json.toString();
			rb=Response.ok(returnString).build();
		} catch (Exception e) {
				e.printStackTrace();
		} finally{
			try {
				if (conn!=null)	conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rb;
	}
	
	//There is not special difference but pathparamaetr is more used. 
	@Path("/{brand}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnBrand(
			@PathParam("brand") String  brand){	
		try {
			
			if (brand==null){
				return Response.status(400).entity("Error: Please especify a brand").build();
			}
			Schema308Tube dao=new Schema308Tube();
			json=dao.queryReturnBrandParts(brand);
	
			returnString=json.toString();
			rb=Response.ok(returnString).build();
		} catch (Exception e) {
				e.printStackTrace();
		} finally{
			try {
				if (conn!=null)	conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rb;
	}
}