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
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Sumudu De Zoysa
 */
public class ActivationDAO {

    public static HomeController star;//db connection

    public Boolean insertActivationInfo(
            String activationCode,
            String macAddress) {

        if (star.con == null) {
            System.out.println("Databse connection failiure.");
            return null;
        } else {

            try {
                PreparedStatement ps = star.con.prepareStatement(
                        "update activation set "
                        + " activationCode = ?, macAddress = ? where id=1 ");

                ps.setString(1, activationCode);
                ps.setString(2, macAddress);

                int val = ps.executeUpdate();
                if (val == 1) {
                    return true;
                } else {
                    return false;
                }

            } catch (SQLException e) {

                if (e instanceof SQLException) {
                    System.out.println("Exception tag --> " + "Invalid sql statement "
                            + e.getMessage());
                }
                return false;

            } catch (Exception e) {
                System.out.println("Exception tag --> " + "Error");
                return false;
            }

        }
    }

}
