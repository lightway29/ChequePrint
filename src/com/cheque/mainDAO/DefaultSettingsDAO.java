/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cheque.mainDAO;

import com.cheque.main.HomeController;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author lightway
 */
public class DefaultSettingsDAO {
 
    public ArrayList<Boolean> loadSettings(String id) {

        boolean crossCheque = false;
        boolean printPreview = false;
        boolean dateWithYear = false;
        boolean print = false;
        ArrayList list = new ArrayList();

        if (HomeController.con == null) {
            System.out.println("Database connection failiure.");
            return null;

        } else {
            try {

                String query
                        = "SELECT * FROM default_settings where id=? ";
                PreparedStatement pstmt = HomeController.con.prepareStatement(query);
                pstmt.setString(1, id);
                ResultSet r = pstmt.executeQuery();

                while (r.next()) {
                    crossCheque = (r.getInt("cross_cheque")==1);
                    printPreview = (r.getInt("print_preview")==1);
                    dateWithYear = (r.getInt("date_with_year")==1);
                    print = (r.getInt("print")==1);
                    
                    list.add(crossCheque);
                    list.add(printPreview);
                    list.add(dateWithYear);
                    list.add(print);

                }

            } catch (Exception e) {
                System.out.println("Exception tag --> " + "Invalid sql statement "
                            + e.getMessage());
                
                }
        }
        return list;

    }
    
    public String loadSetting(String id) {

        String profileId=null;

        if (HomeController.con == null) {
            System.out.println("Database connection failiure.");
            return null;

        } else {
            try {

                String query
                        = "SELECT * FROM default_settings where id=? ";
                PreparedStatement pstmt = HomeController.con.prepareStatement(query);
                pstmt.setString(1, id);
                ResultSet r = pstmt.executeQuery();

                while (r.next()) {
                    profileId = r.getString("profile_name");
                }

            } catch (Exception e) {
                System.out.println("Exception tag --> " + "Invalid sql statement "
                            + e.getMessage());
                
                }
        }
        return profileId;

    }
    
    public boolean updateSettings(
            boolean crossCheque,
            boolean printPreview,
            boolean dateWithYear,
            boolean print,
            String id,
            String profile) {

        

        if (HomeController.con == null) {

             System.out.println("Database connection failiure.");
            return false;
        } else {
            try {

                PreparedStatement ps = HomeController.con.prepareStatement(
                        "Update default_settings set cross_cheque=? ,"
                                + "print_preview=?,date_with_year=?,"
                                + "print=?, profile_name=? where id=?");
                ps.setInt(1, crossCheque?1:0);
                ps.setInt(2, printPreview?1:0);
                ps.setInt(3, dateWithYear?1:0);
                ps.setInt(4, print?1:0);
                ps.setString(5, profile);
                ps.setString(6, id);

                int val = ps.executeUpdate();
                if (val == 1) {
                    return true;
                } else {
                    return false;
                }

           } catch (Exception e) {
                System.out.println("Exception tag --> " + "Invalid sql statement "
                            + e.getMessage());
                return false;
           }  
        }
        
        
    }
    
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
    
   
    
    
}
