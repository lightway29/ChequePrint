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
public class ChequePrintLogDAO {

    public ArrayList<ArrayList<String>> getSearchValues(String SearchInfo) {

        String Amount = null;
        String CrossCheque = null;
        String Date = null;
        String Description = null;
        String Id = null;
        String Pay = null;
        String Profile = null;
        String TimeStamp = null;

        ArrayList<ArrayList<String>> Mainlist
                = new ArrayList<ArrayList<String>>();

        if (HomeController.con == null) {

            System.out.println("Database connection failiure.");
            return null;

        } else {
            try {

                String query = 
                        "select * "
                        + "from cheque_log "
                        + "WHERE description Like '" + SearchInfo + "%' or "
                        + "date Like '" + SearchInfo + "%' or "
                        + "pay Like '" + SearchInfo + "%' or "
                        + "amount Like '" + SearchInfo + "%' ";

                PreparedStatement pstmt = HomeController.con.prepareStatement(query);

                ResultSet r = pstmt.executeQuery();

                while (r.next()) {

                    ArrayList<String> list = new ArrayList<String>();

                    Amount = Double.toString(r.getDouble("amount"));
                    CrossCheque = r.getString("cross_cheque");
                    Date = r.getString("date");
                    Description = r.getString("description");
                    Id = Integer.toString(r.getInt("id"));
                    Pay = r.getString("pay");
                    Profile = r.getString("profile");
                    TimeStamp = r.getString("time_stamp");

                    list.add(Amount);
                    list.add(CrossCheque);
                    list.add(Date);
                    list.add(Description);
                    list.add(Id);
                    list.add(Pay);
                    list.add(Profile);
                    list.add(TimeStamp);

                    Mainlist.add(list);

                }

            } catch (ArrayIndexOutOfBoundsException | SQLException |
                    NullPointerException e) {

                if (e instanceof ArrayIndexOutOfBoundsException) {

                    System.out.println("Exception tag1 --> " + "Invalid sql statement "
                            + e.getMessage());

                } else if (e instanceof SQLException) {

                    System.out.println("Exception tag2 --> " + "Invalid sql statement "
                            + e.getMessage());
                    e.printStackTrace();

                } else if (e instanceof NullPointerException) {

                    System.out.println("Exception tag3 --> " + "Invalid sql statement "
                            + e.getMessage());

                }
                return null;
            } catch (Exception e) {

                System.out.println("Exception tag4 --> " + "Invalid sql statement "
                        + e.getMessage());
                e.printStackTrace();

                return null;
            }
        }
        return Mainlist;
    }   

}
