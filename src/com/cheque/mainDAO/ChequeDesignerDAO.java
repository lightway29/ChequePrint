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
import org.owasp.esapi.ESAPI;

/**
 *
 * @author lightway
 */
public class ChequeDesignerDAO {

    public boolean insertProfile(
            String designerId,
            String profileName,
            String acPayeeX,
            String acPayeeY,
            String payX,
            String payY,
            String rupeeX,
            String rupeeY,
            String amountX,
            String amountY,
            String dateX,
            String dateY) {

        if (HomeController.con == null) {
            System.out.println("Database connection failiure.");
            return false;
        } else {
            try {

                PreparedStatement ps = HomeController.con.prepareStatement(
                        "INSERT INTO `cheque_design` ("
                        + "`design_id`, "
                        + "`profile_name`,"
                        + "`ac_payee_only_top`, "
                        + "`ac_payee_only_hight`, "
                        + "`pay_top`, "
                        + "`pay_hight`, "
                        + "`rupees_top`, "
                        + "`rupees_hight`, "
                        + "`amount_top`, "
                        + "`amount_hight`, "
                        + "`date_top`, "
                        + "`date_hight`) "
                        + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");

                ps.setString(1, designerId);
                ps.setString(2, profileName);
                ps.setString(3, acPayeeX);
                ps.setString(4, acPayeeY);
                ps.setString(5, payX);
                ps.setString(6, payY);
                ps.setString(7, rupeeX);
                ps.setString(8, rupeeY);
                ps.setString(9, amountX);
                ps.setString(10, amountY);
                ps.setString(11, dateX);
                ps.setString(12, dateY);

                int val = ps.executeUpdate();
                if (val == 1) {
                    return true;
                } else {
                    return false;
                }

            } catch (Exception e) {
                System.out.println("Exception tag --> "
                        + "Invalid sql statement "
                );
                return false;
            }
        }

    }

    public ArrayList<ArrayList<String>> searchProfileDetailsInfo(String date) {

        String rowId = null;
        String profileName = null;
        String summeryId = null;
        String cusId = null;
        String pax = null;

        ArrayList<ArrayList<String>> Mainlist
                = new ArrayList<ArrayList<String>>();

        if (HomeController.con == null) {

            System.out.println("Database connection failiure.");
            return null;

        } else {
            try {

                String query
                        = "SELECT * FROM cheque_design WHERE design_id LIKE ? OR profile_name LIKE ? ";

                PreparedStatement pstmt = HomeController.con.prepareStatement(
                        query);
                pstmt.setString(1, date + "%");
                pstmt.setString(2, date + "%");
                ResultSet r = pstmt.executeQuery();

                while (r.next()) {

                    ArrayList<String> list = new ArrayList<String>();

                    rowId = r.getString("design_id");

                    profileName = r.getString("profile_name");

                    list.add(rowId);
                    list.add(profileName);

                    Mainlist.add(list);

                }

            } catch (ArrayIndexOutOfBoundsException | SQLException |
                    NullPointerException e) {

                if (e instanceof ArrayIndexOutOfBoundsException) {

                    System.out.println("Exception tag --> "
                            + "Invalid entry location for list");

                } else if (e instanceof SQLException) {

                    System.out.println("Exception tag --> "
                            + "Invalid sql statement " + e);

                } else if (e instanceof NullPointerException) {

                    System.out.println("Exception tag --> "
                            + "Empty entry for list");

                }
                return null;
            } catch (Exception e) {

                System.out.println("Exception tag --> " + "Error");

                return null;
            }
        }
        return Mainlist;
    }

    public ArrayList<String> loadProfileDetail(String profileId) {

        String acPayeeX = null;
        String acPayeeY = null;
        String payX = null;
        String payY = null;
        String rupeeX = null;
        String rupeeY = null;
        String amountX = null;
        String amountY = null;
        String dateX = null;
        String dateY = null;

        ArrayList<String> list
                = new ArrayList<String>();

        if (HomeController.con == null) {

            System.out.println("Database connection failiure.");
            return null;

        } else {
            try {

                String query
                        = "SELECT * FROM cheque_design WHERE design_id = ? ";

                PreparedStatement pstmt = HomeController.con.prepareStatement(
                        query);
                pstmt.setString(1, profileId);

                ResultSet r = pstmt.executeQuery();

                while (r.next()) {

                    acPayeeX = r.getString("ac_payee_only_top");
                    acPayeeY = r.getString("ac_payee_only_hight");
                    payX = r.getString("pay_top");
                    payY = r.getString("pay_hight");
                    rupeeX = r.getString("rupees_top");
                    rupeeY = r.getString("rupees_hight");
                    amountX = r.getString("amount_top");
                    amountY = r.getString("amount_hight");
                    dateX = r.getString("date_top");
                    dateY = r.getString("date_hight");

                    list.add(acPayeeX);
                    list.add(acPayeeY);
                    list.add(payX);
                    list.add(payY);
                    list.add(rupeeX);
                    list.add(rupeeY);
                    list.add(amountX);
                    list.add(amountY);
                    list.add(dateX);
                    list.add(dateY);

                }

            } catch (ArrayIndexOutOfBoundsException | SQLException |
                    NullPointerException e) {

                if (e instanceof ArrayIndexOutOfBoundsException) {

                    System.out.println("Exception tag --> "
                            + "Invalid entry location for list");

                } else if (e instanceof SQLException) {

                    System.out.println("Exception tag --> "
                            + "Invalid sql statement " + e);

                } else if (e instanceof NullPointerException) {

                    System.out.println("Exception tag --> "
                            + "Empty entry for list");

                }
                return null;
            } catch (Exception e) {

                System.out.println("Exception tag --> " + "Error");

                return null;
            }
        }
        return list;
    }

     public String generateId() {

        Integer id = null;

        if (HomeController.con == null) {

            System.out.println("Database connection failiure.");
            return null;
        } else {

            String eid = null;
            String final_id = null;
            if (HomeController.con  == null) {
               System.out.println("Database connection failiure.");
                return null;
            } else {
                try {

                    String query = "SELECT MAX(0) as ID FROM cheque_design";

                    PreparedStatement pstmt = HomeController.con .prepareStatement(query);

                    ResultSet r = pstmt.executeQuery();

                    while (r.next()) {
                        id = r.getInt(1);
                    }
                    String queryCurrentId
                            = "SELECT design_id FROM cheque_design WHERE id=?";

                    PreparedStatement pstmtId = HomeController.con.prepareStatement(
                            queryCurrentId);
                    pstmtId.setInt(1, id);

                    ResultSet rss = pstmtId.executeQuery();
                    while (rss.next()) {
                        eid = rss.getString("design_id");

                    }
                    if (id != 0) {
                        String original = eid.split("G")[1];
                        int i = Integer.parseInt(original) + 1;

                        if (i < 10) {
                            final_id = "DSG000" + i;
                        } else if (i >= 10 && i < 100) {
                            final_id = "DSG00" + i;
                        } else if (i >= 100 && i < 1000) {
                            final_id = "DSG0" + i;
                        } else if (i >= 1000) {
                            final_id = "DSG" + i;

                        }
                        return final_id;
                    } else {
                        return "DSG0001";
                    }
                 } catch (ArrayIndexOutOfBoundsException | SQLException |
                    NullPointerException e) {

                if (e instanceof ArrayIndexOutOfBoundsException) {

                    System.out.println("Exception tag --> "
                            + "Invalid entry location for list");

                } else if (e instanceof SQLException) {

                    System.out.println("Exception tag --> "
                            + "Invalid sql statement " + e);

                } else if (e instanceof NullPointerException) {

                    System.out.println("Exception tag --> "
                            + "Empty entry for list");

                }
                return null;
            } catch (Exception e) {

                System.out.println("Exception tag --> " + "Error");

                return null;
            }
            }
        }
    }

    
}
