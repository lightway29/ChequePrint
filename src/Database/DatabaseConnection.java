/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

/**
 *
 * @author lightway
 */

import java.sql.*;
import javax.swing.*;
public class DatabaseConnection {
    
    Connection con = null;
    
    public static Connection Connect(){
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:ChequePrint.sqlite");
       return connection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    
    
    
    }
    
}
