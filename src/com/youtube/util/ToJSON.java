package com.youtube.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.owasp.esapi.ESAPI;

public class ToJSON {

	public JSONArray toJSONArray(ResultSet rs) throws Exception{
		
		String temp=null;
		
		JSONArray json=new JSONArray();
		try{
			ResultSetMetaData rsmd=rs.getMetaData();
			//this first loop is for records
			while(rs.next()){
				int numColumns= rsmd.getColumnCount();
				JSONObject obj=new JSONObject();
				//this second loop is for columns
				for (int i=1;i<numColumns+1;i++){
					String column_name=rsmd.getColumnName(i);
					if (rsmd.getColumnType(i)==java.sql.Types.ARRAY){
						obj.put(column_name, rs.getArray(column_name));
					}
					else if (rsmd.getColumnType(i)==java.sql.Types.NUMERIC){
						//BE CAREFUL: NUMERIC allways bigdecimal
						obj.put(column_name, rs.getBigDecimal(column_name));
					}
					else if (rsmd.getColumnType(i)==java.sql.Types.BOOLEAN){
						obj.put(column_name, rs.getBoolean(column_name));
					}
					else if (rsmd.getColumnType(i)==java.sql.Types.VARCHAR){
						temp=rs.getString(column_name);
						temp=ESAPI.encoder().canonicalize(temp);
						temp=ESAPI.encoder().encodeForHTML(temp);
						obj.put(column_name, temp);
						
						//obj.put(column_name, rs.getString(column_name));
					}
					else if (rsmd.getColumnType(i)==java.sql.Types.DATE){
						obj.put(column_name, rs.getDate(column_name));
					}
					else{
						obj.put(column_name,  rs.getObject(column_name));
					}
				}
				json.put(obj);
			}
		}catch(Exception ex){
			ex.getStackTrace();
		}
		return json;
	}
}
