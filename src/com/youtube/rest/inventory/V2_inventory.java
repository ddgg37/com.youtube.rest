package com.youtube.rest.inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
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
	
	//PathParameter is more flexible and can be add more tha one parameter 
	@Path("/{brand}/{item_number}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnBrandItemnumber(
			@PathParam("brand") String  brand,
			@PathParam("item_number") int  item_number){	
		try {
			
			if (brand==null){
				return Response.status(400).entity("Error: Please especify a brand").build();
			}
			Schema308Tube dao=new Schema308Tube();
			json=dao.queryReturnBrandItemnumber(brand,item_number);
	
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
	
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	//@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPCParts(String incomingData){	
		Schema308Tube dao=new Schema308Tube();
		JSONArray jsonArray=new JSONArray();
		try {
			ObjectMapper mapper=new ObjectMapper();
			ItemEntry itemEntry=mapper.readValue(incomingData, ItemEntry.class);			
			int http_code=dao.insertNewParts(itemEntry.PC_PARTS_TITLE, itemEntry.PC_PARTS_CODE, itemEntry.PC_PARTS_MAKER, itemEntry.PC_PARTS_AVAIL, itemEntry.PC_PARTS_DESC);
	
			if (http_code==200){
				returnString=jsonArray.toString();
			}else{
				Response.status(500).entity("Unable to porcess item").build();
			}
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

class ItemEntry{
	String PC_PARTS_TITLE;
	String PC_PARTS_CODE;
	String PC_PARTS_MAKER;
	String PC_PARTS_AVAIL;
	String PC_PARTS_DESC;
	public String getPC_PARTS_TITLE() {
		return PC_PARTS_TITLE;
	}
	public void setPC_PARTS_TITLE(String pC_PARTS_TITLE) {
		PC_PARTS_TITLE = pC_PARTS_TITLE;
	}
	public String getPC_PARTS_CODE() {
		return PC_PARTS_CODE;
	}
	public void setPC_PARTS_CODE(String pC_PARTS_CODE) {
		PC_PARTS_CODE = pC_PARTS_CODE;
	}
	public String getPC_PARTS_MAKER() {
		return PC_PARTS_MAKER;
	}
	public void setPC_PARTS_MAKER(String pC_PARTS_MAKER) {
		PC_PARTS_MAKER = pC_PARTS_MAKER;
	}
	public String getPC_PARTS_AVAIL() {
		return PC_PARTS_AVAIL;
	}
	public void setPC_PARTS_AVAIL(String pC_PARTS_AVAIL) {
		PC_PARTS_AVAIL = pC_PARTS_AVAIL;
	}
	public String getPC_PARTS_DESC() {
		return PC_PARTS_DESC;
	}
	public void setPC_PARTS_DESC(String pC_PARTS_DESC) {
		PC_PARTS_DESC = pC_PARTS_DESC;
	}
	
	
	
}