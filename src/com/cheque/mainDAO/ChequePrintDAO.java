/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cheque.mainDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author lightway
 */



public class ChequePrintDAO {
    
    Connection connection = Database.DatabaseConnection.Connect();
    public String getBank(String bankId) {


        String bank = null;

        if (connection == null) {
            System.out.println("Databse connection failiure.");
           
            return null;
        } else {

            try {
                String query
                        = "SELECT bank_name FROM bank where bank_id=? ";
                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setString(1, bankId);

                ResultSet r = pstmt.executeQuery();

                while (r.next()) {
                    bank = r.getString("bank_name");
                }

            } catch (Exception e) {
                System.out.println("Exception tag --> " + "Invalid sql statement "
                            + e.getMessage());
                
                }
                return bank;
        
        }
      
    }

    
}
