/**
 * This application will create,delete,update,and display employee records stored
 * in a db.
 * 
 * @author Jose R. Soriano
 * @version 1.0
 * 
 * Date Created: 12.31.2016
 * Last Update: 01.28.2017
**/

import javax.swing.*;
import java.sql.DriverManager;
import java.sql.SQLException;

/*Connection Factory*/
public class Connection{
    public static Connection instance;//singleton
    private java.sql.Connection connection;

    /*1st and only connection*/
    private Connection(){

        final String DB_DRIVER = "net.ucanaccess.jdbc.UcanaccessDriver";
        final String DB_URL = "jdbc:ucanaccess:employees";

        try{
            Class.forName(DB_DRIVER);/////////Driver
            System.out.println("Driver Loaded...");
            connection = DriverManager.getConnection(DB_URL);//////////URL
            connection.setAutoCommit(false);
            System.out.println("Connection Successful...\n");
        }catch(ClassNotFoundException |SQLException ex){// 
            JOptionPane optionPane = new JOptionPane("Could not Connect!", JOptionPane.ERROR_MESSAGE);
            JDialog dialog = optionPane.createDialog("ErrorMsg");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
        }

    }

    /*gets and returns a connection*/
    public java.sql.Connection getConnection(){
        return connection;
    }

    /*confirms connection status and returns instance*/
    public synchronized static Connection conStatus(){
        if(instance == null) instance = new Connection();
        return instance;
    }

    /*closes connection kills instance*/
    public void closeConnection(){
        instance = null;
    }


}
