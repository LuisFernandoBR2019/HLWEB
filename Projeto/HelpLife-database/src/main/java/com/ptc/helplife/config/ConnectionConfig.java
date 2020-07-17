package com.ptc.helplife.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionConfig {
	private static Connection conn;

    private static final String url = "jdbc:postgresql://localhost:5432/helplife";
    private static final String user = "postgres";
    private static final String pass = "postgres";

    public static Connection getConnection() {

        try {
            conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return conn;
    }
    
    
     private static ConnectionConfig instance;
    
    public static ConnectionConfig getInstance() {
        if (instance == null) {
            instance = new ConnectionConfig();
        }
        return instance;
    }
}
