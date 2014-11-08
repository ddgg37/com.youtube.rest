package com.youtube.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Oracle308Tube {
	  
	public Connection getConnection(){
	    Connection connection = null;
        try {
            // Load the JDBC driver
            String driverName = "oracle.jdbc.driver.OracleDriver";
            Class.forName(driverName);
            // Create a connection to the database
            String serverName = "localhost";
            String portNumber = "1521";
            String sid = "xe";
            String url = "jdbc:oracle:thin:@" + serverName + ":" + portNumber + ":" + sid;
            String username = "gorka";
            String password = "admin";
			connection = DriverManager.getConnection(url, username, password);
            System.out.println("Success");
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found Error");
        } catch (SQLException e) {
			e.printStackTrace();
		}
        return connection;
    }
}
