package com.youtube.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ws.rs.core.Response;
import org.codehaus.jettison.json.JSONArray;
import com.youtube.util.ToJSON;

public class Schema308Tube {
	Connection conn=null;
	PreparedStatement query=null;
	String returnString=null;
	Response rb=null;

	public JSONArray queryReturnBrandParts(String  brand){	
		ToJSON converter=new ToJSON();
		JSONArray json=new JSONArray();
		try {
			Oracle308Tube oracle308Tube=new Oracle308Tube();
			conn=oracle308Tube.getConnection();
			query = conn.prepareStatement("select PC_PARTS_TITLE,PC_PARTS_MAKER from PC_PARTS where UPPER(PC_PARTS_MAKER)=?");
			query.setString(1, brand.toUpperCase());
			ResultSet rs=query.executeQuery();		
			json=converter.toJSONArray(rs);

			query.close();

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
		return json;
	}
	
}
