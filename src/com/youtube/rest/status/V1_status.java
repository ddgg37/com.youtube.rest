package com.youtube.rest.status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.youtube.dao.Oracle308Tube;

@Path("/v1/status")
public class V1_status {
	
	Connection conn=null;
	PreparedStatement query=null;
	String returnString=null;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String returnTitle(){
		return "this is a test Gorka";
	}
	
	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String returnVersion(){
		return "this is a Version ";
	}
	
	@Path("/database")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String returnDatabaseColumn(){
		String myString = null;
		
		try {
			Oracle308Tube oracle308Tube=new Oracle308Tube();
			conn=oracle308Tube.getConnection();
			query = conn.prepareStatement("select * from TABLE1");
			ResultSet rs=query.executeQuery();
			if (rs.next()){
				myString=rs.getString("COLUMN1");
			}
			returnString="The column1 vaue is " + myString;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if (conn!=null)	conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return returnString;
	}
}

