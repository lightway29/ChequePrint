/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cheque.mainDAO;

import com.cheque.main.HomeController;
import java.sql.PreparedStatement;

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

}
