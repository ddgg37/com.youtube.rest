package com.youtube.rest.inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;

import com.youtube.dao.Oracle308Tube;
import com.youtube.util.ToJSON;

@Path("/v1/inventory")
public class V1_inventory {
	Connection conn=null;
	PreparedStatement query=null;
	String returnString=null;
	Response rb=null;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnAllPcParts(){	
		try {
			Oracle308Tube oracle308Tube=new Oracle308Tube();
			conn=oracle308Tube.getConnection();
			query = conn.prepareStatement("select * from PC_PARTS");
			ResultSet rs=query.executeQuery();
		
			ToJSON converter=new ToJSON();
			JSONArray json=new JSONArray();

			json=converter.toJSONArray(rs);

			query.close();
			
			returnString=json.toString();
			rb=Response.ok(returnString).build();
		} catch (SQLException e) {
			e.printStackTrace();
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
