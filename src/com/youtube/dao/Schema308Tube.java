package com.youtube.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	
	public JSONArray queryReturnBrandItemnumber(String  brand,int item_number){	
		ToJSON converter=new ToJSON();
		JSONArray json=new JSONArray();
		try {
			Oracle308Tube oracle308Tube=new Oracle308Tube();
			conn=oracle308Tube.getConnection();
			query = conn.prepareStatement("select PC_PARTS_TITLE,PC_PARTS_MAKER from PC_PARTS where UPPER(PC_PARTS_MAKER)=? AND PC_PARTS_CODE=?");
			query.setString(1, brand.toUpperCase());
			query.setInt(2, item_number);
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
	
	public int insertNewParts(String  PC_PARTS_TITLE,String PC_PARTS_CODE,String PC_PARTS_MAKER,String PC_PARTS_AVAIL, String PC_PARTS_DESC){	
		try {
			Oracle308Tube oracle308Tube=new Oracle308Tube();
			conn=oracle308Tube.getConnection();
			query=conn.prepareStatement("insert into PC_PARTS (PC_PARTS_CODE,PC_PARTS_AVAIL,PC_PARTS_MAKER,PC_PARTS_TITLE,PC_PARTS_DESC) values (?,?,?,?,?)");
			query.setString(1, PC_PARTS_CODE);
			query.setInt(2, Integer.parseInt(PC_PARTS_AVAIL));
			query.setString(3, PC_PARTS_MAKER);
			query.setString(4, PC_PARTS_TITLE);
			query.setString(5, PC_PARTS_DESC);
			query.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 500;
		} catch (Exception e) {
			e.printStackTrace();
			return 500;
		} finally{
			try {
				if (conn!=null)	conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 200;
	}
	
}
