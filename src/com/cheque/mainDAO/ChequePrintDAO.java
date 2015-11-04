/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cheque.mainDAO;

import com.cheque.main.HomeController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author lightway
 */



public class ChequePrintDAO {
    
    public ArrayList loadTable() {

        String profile = null;
        ArrayList profileList = new ArrayList();

        if (HomeController.con == null) {

             System.out.println("Database connection failiure.");
        } else {
            try {
                
                String query = "Select profile_name from cheque_design ";

                PreparedStatement pre = HomeController.con.prepareStatement(query);
                ResultSet r = pre.executeQuery();
                while (r.next()) {
                    profile = r.getString("profile_name");

                    profileList.add(profile);
                }

            } catch (ArrayIndexOutOfBoundsException | SQLException |
                    NullPointerException e) {
                if (e instanceof ArrayIndexOutOfBoundsException) {
                    System.out.println("Exception tag --> "
                            + "Invalid entry location for list");
                } else if (e instanceof SQLException) {
                    System.out.println("Exception tag --> " + "Invalid sql statement "
                            + e.getMessage());
                } else if (e instanceof NullPointerException) {
                    System.out.println("Exception tag --> " + "Empty entry for list");
                }
                return null;
            } catch (Exception e) {
                System.out.println("Exception tag --> " + "Error");
                return null;
            }
        }
        return profileList;
    }
    
    

    public String getBank(String bankId) {


        String bank = null;

        if (HomeController.con == null) {
            System.out.println("Databse connection failiure.");
           
            return null;
        } else {

            try {
                String query
                        = "SELECT bank_name FROM bank where bank_id=? ";
                PreparedStatement pstmt = HomeController.con.prepareStatement(query);
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
