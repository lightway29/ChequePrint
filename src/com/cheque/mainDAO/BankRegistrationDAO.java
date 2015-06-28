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
import org.apache.log4j.Logger;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.codecs.Codec;
import org.owasp.esapi.codecs.OracleCodec;

/**
 *
 * @author Sumudu De Zoysa
 */
public class BankRegistrationDAO {

    public static HomeController star;//db connection
    Codec ORACLE_CODEC = new OracleCodec();
    private Logger log = Logger.getLogger(this.getClass());

    public String generateBankId() {

        Integer id = null;
        String cid = null;
        String final_id = null;
        if (star.con == null) {
            log.error("Databse connection failiure.");
            return null;
        } else {
            try {

                Statement st = star.con.createStatement();
                Statement ste = star.con.createStatement();
                ResultSet rs = st.executeQuery(
                        "SELECT MAX(id) as ID FROM bank");

                while (rs.next()) {
                    id = rs.getInt("id");
                }
                ResultSet rss = ste.executeQuery(
                        "SELECT bank_id FROM bank WHERE id= "
                        + id + "");

                while (rss.next()) {
                    cid = rss.getString("bank_id");
                }

                if (id != 0) {
                    String original = cid.split("K")[1];
                    int i = Integer.parseInt(original) + 1;

                    if (i < 10) {
                        final_id = "BNK000" + i;
                    } else if (i >= 10 && i < 100) {
                        final_id = "BNK00" + i;
                    } else if (i >= 100 && i < 1000) {
                        final_id = "BNK0" + i;
                    } else if (i >= 1000 && i < 10000) {
                        final_id = "BNK" + i;
                    }
                    return final_id;

                } else {
                    return "BNK0001";
                }
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException |
                    SQLException e) {

                if (e instanceof ArrayIndexOutOfBoundsException) {
                    log.error("Exception tag --> " + "Split character error");
                } else if (e instanceof NumberFormatException) {
                    log.error("Exception tag --> "
                            + "Invalid number found in current id");
                } else if (e instanceof SQLException) {
                    log.error("Exception tag --> " + "Invalid sql statement "
                            + e.getMessage());
                }
                return null;

            } catch (Exception e) {
                log.error("Exception tag --> " + "Error");
                return null;
            }
        }
    }

    public Boolean insertBankDetails(
            String bankID,
            String bankName,
            String branchCode,
            String branch,
            String accountNo) {

        if (star.con == null) {
            log.error("Databse connection failiure.");
            return null;
        } else {
            try {
                PreparedStatement ps = star.con.prepareStatement(
                        "INSERT INTO bank(bank_id, "
                        + " bank_name, branch_code, branch, account_no ) "
                        + " VALUES (?,?,?,?,?) ");

                ps.setString(1, bankID);
                ps.setString(2, bankName);
                ps.setString(3, branchCode);
                ps.setString(4, branch);
                ps.setString(5, accountNo);

                int val = ps.executeUpdate();
                if (val == 1) {
                    return true;
                } else {
                    return false;
                }

            } catch (SQLException e) {

                if (e instanceof SQLException) {
                    log.error("Exception tag --> " + "Invalid sql statement "
                            + e.getMessage());
                }
                return false;

            } catch (Exception e) {
                log.error("Exception tag --> " + "Error");
                return false;
            }

        }
    }

    public ArrayList<ArrayList<String>> searchBankDetails(String search) {

        String bankId = null;
        String bankName = null;
        String branchCode = null;
        String branch = null;
        String accountNo = null;

        ArrayList<ArrayList<String>> Mainlist = new ArrayList<ArrayList<String>>();

        if (star.con == null) {

            log.info(" Exception tag --> " + "Databse connection failiure. ");
            return null;

        } else {
            try {

                String query = "SELECT * FROM bank WHERE (bank_id LIKE ? OR bank_name LIKE ? "
                        + "OR branch_code LIKE ? OR account_no LIKE ? OR branch LIKE ? )";

                PreparedStatement pstmt = star.con.prepareStatement(query);
                pstmt.setString(1, search + "%");
                pstmt.setString(2, search + "%");
                pstmt.setString(3, search + "%");
                pstmt.setString(4, search + "%");

                ResultSet r = pstmt.executeQuery();

                while (r.next()) {

                    ArrayList<String> list = new ArrayList<String>();

                    bankId = r.getString("bank_id");
                    bankName = r.getString("bank_name");
                    branchCode = r.getString("branch_code");
                    branch = r.getString("branch");
                    accountNo = r.getString("account_no");

                    list.add(bankId);
                    list.add(bankName);
                    list.add(branchCode);
                    list.add(branch);
                    list.add(accountNo);

                    Mainlist.add(list);

                }

            } catch (ArrayIndexOutOfBoundsException | SQLException | NullPointerException e) {

                if (e instanceof ArrayIndexOutOfBoundsException) {

                    log.error("Exception tag --> " + "Invalid entry location for list");

                } else if (e instanceof SQLException) {

                    log.error("Exception tag --> " + "Invalid sql statement");

                } else if (e instanceof NullPointerException) {

                    log.error("Exception tag --> " + "Empty entry for list");

                }
                return null;
            } catch (Exception e) {

                log.error("Exception tag --> " + "Error");

                return null;
            }
        }
        return Mainlist;
    }

    public boolean deleteBank(String bankID) {

        if (star.con == null) {
            log.info(" Exception tag --> " + "Databse connection failiure. ");
            return false;
        } else {
            try {
                String sql
                        = "DELETE FROM bank WHERE bank_id=? ";
                PreparedStatement stmt = star.con.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, bankID);
                int val = stmt.executeUpdate();

                if (val == 1) {
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException e) {

                if (e instanceof SQLException) {
                    log.error("Exception tag --> " + "Invalid sql statement "
                            + e.getMessage());
                }
                return false;
            } catch (Exception e) {
                log.error("Exception tag --> " + "Error");
                return false;
            }
        }
    }

    public boolean checkingBankIdAvailability(String bankId) {

        boolean available = false;

        if (star.con == null) {
            log.error("Databse connection failiure.");
            return false;
        } else {

            try {
                String query
                        = "SELECT * FROM bank where bank_id= ? ";
                PreparedStatement pstmt = star.con.prepareStatement(query);
                pstmt.setString(1, bankId);

                ResultSet r = pstmt.executeQuery();

                while (r.next()) {
                    available = true;
                }

            } catch (NullPointerException | SQLException e) {

                if (e instanceof NullPointerException) {
                    log.error("Exception tag --> " + "Empty entry passed");
                } else if (e instanceof SQLException) {
                    log.error("Exception tag --> " + "Invalid sql statement "
                            + e.getMessage());
                }
                return false;
            } catch (Exception e) {
                log.error("Exception tag --> " + "Error");
                return false;
            }
        }
        return available;
    }

    public boolean updateBankDetails(
            String bankID,
            String bankname,
            String branchCode,
            String branch,
            String accountNo) {

        if (star.con == null) {
            log.info(" Exception tag --> " + "Databse connection failiure. ");
            return false;
        } else {
            try {
                String sql = "Update bank "
                        + "SET bank_name=? ,branch_code=? ,branch=?, "
                        + "account_no=?  "
                        + "where bank_id= ? ";

                PreparedStatement stmt = star.con.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);

                stmt.setString(1, bankname);
                stmt.setString(2, branchCode);
                stmt.setString(3, branch);
                stmt.setString(4, accountNo);
                stmt.setString(5, bankID);

                int val = stmt.executeUpdate();
                if (val == 1) {

                    return true;
                } else {

                    return false;
                }

            } catch (ArrayIndexOutOfBoundsException | NumberFormatException |
                    SQLException e) {

                if (e instanceof ArrayIndexOutOfBoundsException) {
                    log.error("Exception tag --> " + "Split character error");
                } else if (e instanceof NumberFormatException) {
                    log.error("Exception tag --> "
                            + "Invalid number found in current id");
                } else if (e instanceof SQLException) {
                    log.error("Exception tag --> " + "Invalid sql statement "
                            + e.getMessage());
                }
                return false;

            } catch (Exception e) {
                log.error("Exception tag --> " + "Error");
                return false;
            }
        }
    }

    public boolean checkAccountNoAvailability(
            String bankName,
            String accountNo) {

        boolean available = false;

        if (star.con == null) {

            log.error("Exception tag --> " + "Databse connection failiure. ");

        } else {
            try {

                String query
                        = "SELECT * FROM bank WHERE bank_name = ? AND account_no = ? ";

                PreparedStatement pstmt = star.con.prepareStatement(query);
                pstmt.setString(1, bankName);
                pstmt.setString(2, accountNo);

                ResultSet r = pstmt.executeQuery();

                while (r.next()) {
                    available = true;
                }
                return available;

            } catch (NullPointerException | SQLException e) {
                if (e instanceof NullPointerException) {
                    log.error("Exception tag --> " + "Empty entry passed");

                } else if (e instanceof SQLException) {
                    log.error("Exception tag --> " + "Invalid sql statement");
                }
                return false;
            } catch (Exception e) {
                log.error("Exception tag --> " + "Error");
                return false;
            }
        }
        return available;
    }
    
    public boolean checkAccountNoAvailabilityUpdate(
            String bankName,
            String accountNo,
            String bankId) {

        boolean available = false;

        if (star.con == null) {

            log.error("Exception tag --> " + "Databse connection failiure. ");

        } else {
            try {

                String query
                        = "SELECT * FROM bank WHERE bank_name = ? AND account_no = ? AND bank_id != ? ";

                PreparedStatement pstmt = star.con.prepareStatement(query);
                pstmt.setString(1, bankName);
                pstmt.setString(2, accountNo);
                pstmt.setString(3, bankId);

                ResultSet r = pstmt.executeQuery();

                while (r.next()) {
                    available = true;
                }
                return available;

            } catch (NullPointerException | SQLException e) {
                if (e instanceof NullPointerException) {
                    log.error("Exception tag --> " + "Empty entry passed");

                } else if (e instanceof SQLException) {
                    log.error("Exception tag --> " + "Invalid sql statement");
                }
                return false;
            } catch (Exception e) {
                log.error("Exception tag --> " + "Error");
                return false;
            }
        }
        return available;
    }
}
