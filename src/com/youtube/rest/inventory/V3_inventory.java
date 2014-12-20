package com.youtube.rest.inventory;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.youtube.dao.Schema308Tube;

@Path("/v3/inventory")
public class V3_inventory {
	
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPCPartsPOST(String incomingData){	
		Schema308Tube dao=new Schema308Tube();
		JSONArray jsonArray=new JSONArray();
		JSONObject jsonObject=new JSONObject();	
		String returnString=null;
		try {
			JSONObject partsData=new JSONObject(incomingData);
			//ObjectMapper mapper=new ObjectMapper();
			//ItemEntry itemEntry=mapper.readValue(incomingData, ItemEntry.class);			
			int http_code=dao.insertNewParts(partsData.optString("PC_PARTS_TITLE"), partsData.optString("PC_PARTS_CODE"), partsData.optString("PC_PARTS_MAKER"), partsData.optString("PC_PARTS_AVAIL"), partsData.optString("PC_PARTS_DESC"));
	
			if (http_code==200){
				jsonObject.put("HTTP_CODE", "200");
				jsonObject.put("MSG","item has successfully version3");
				returnString=jsonArray.put(jsonObject).toString();
			}else{
				Response.status(500).entity("Unable to porcess item").build();
			}
			
			System.out.print("returnString " + returnString);
		} catch (Exception e) {
				e.printStackTrace();
		} 
		return Response.ok(returnString).build();
	}
	
	@Path("/{brand}/{item_number}")
	@PUT
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateItem(@PathParam("brand")String brand,@PathParam("item_number") String item_number,String incomingData) throws Exception{	
		int pk;
		int avail;
		
		JSONArray jsonArray=new JSONArray();
		Schema308Tube dao=new Schema308Tube();
		JSONObject jsonObject=new JSONObject();
		String returnString=null;
		
		try {
			JSONObject partsData=new JSONObject(incomingData);
			pk=partsData.optInt("PC_PARTS_PK",0);
			avail=partsData.optInt("PC_PARTS_AVAIL",0);
			
			int http_code=dao.updatePC_PARTS(pk,avail);
	
			if (http_code==200){
				jsonObject.put("HTTP_CODE", "200");
				jsonObject.put("MSG","item has successfully version3");
				returnString=jsonArray.put(jsonObject).toString();
			}else{
				Response.status(500).entity("Unable to porcess item").build();
			}
			System.out.print("returnString " + returnString);
		} catch (Exception e) {
				e.printStackTrace();
		}
		return Response.ok(returnString).build();
	}
	
	@Path("/{brand}/{item_number}")
	@DELETE
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteItem(@PathParam("brand")String brand,@PathParam("item_number") String item_number,String incomingData) throws Exception{	
		int pk;
		int avail;
		
		JSONArray jsonArray=new JSONArray();
		Schema308Tube dao=new Schema308Tube();
		JSONObject jsonObject=new JSONObject();
		String returnString=null;
		
		try {
			JSONObject partsData=new JSONObject(incomingData);
			pk=partsData.optInt("PC_PARTS_PK",0);
			avail=partsData.optInt("PC_PARTS_AVAIL",0);
			
			int http_code=dao.deletePC_PARTS(pk,avail);
	
			if (http_code==200){
				jsonObject.put("HTTP_CODE", "200");
				jsonObject.put("MSG","item has successfully version3");
				returnString=jsonArray.put(jsonObject).toString();
			}else{
				Response.status(500).entity("Unable to porcess item").build();
			}
			System.out.print("returnString " + returnString);
		} catch (Exception e) {
				e.printStackTrace();
		}
		return Response.ok(returnString).build();
	}
}
