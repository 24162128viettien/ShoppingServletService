package vn.iotstar.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    
    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            String serverName = "localhost";
            String dbName = "ShoppingDB"; 
            String instanceName = "SQLEXPRESS"; 
            String userID = "sa";
            String password = "123456"; 
            
            String url = "jdbc:sqlserver://" + serverName + ";instanceName=" + instanceName + ";databaseName=" + dbName + ";encrypt=true;trustServerCertificate=true;";
            
            conn = DriverManager.getConnection(url, userID, password);
            System.out.println("Kết nối Database thành công!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}