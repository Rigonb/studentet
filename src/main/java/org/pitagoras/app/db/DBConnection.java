package org.pitagoras.app.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection {
    private Connection connation ;
    public DBConnection(){
        this.connect();
    }

    public Connection connect(){



        try {
            String dbURL = "jdbc:postgresql://localhost:5432/shkolla";
            String user = "postgres";
            String password = "macbookpro";
            connation = DriverManager.getConnection(dbURL,user,password);
            System.out.println("Sucssesfully connected to DataBase.");
        } catch (Exception e) {
            System.out.println("We couldnt connect with database for some resons");
            e.printStackTrace();
        }
        return connation;
    }
        System.out.println("O rigon oo");

    public Connection getConnation(){
        return this.connect();
    }
    }
