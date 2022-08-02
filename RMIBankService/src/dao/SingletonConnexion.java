package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnexion {
	
	private static Connection con = null;
	  
    static
    {
        try {
            Class.forName("org.h2.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:h2:"+"./Database/banque_service"+"root"+"password");
            System.out.println("Database Created");
        }
        catch (ClassNotFoundException  | SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection()
    {
        return con;
    }

}
