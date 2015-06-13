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
            String bank_ID,
            String bank_name,
            String bank_code,
            String description,
            String user_id) {

        String encoded_bankId = ESAPI.encoder().encodeForSQL(
                ORACLE_CODEC, bank_ID);

        String encoded_bank_name = ESAPI.encoder().encodeForSQL(
                ORACLE_CODEC, bank_name);

        String encoded_bank_code = ESAPI.encoder().encodeForSQL(
                ORACLE_CODEC, bank_code);

        String encoded_description = ESAPI.encoder().encodeForSQL(
                ORACLE_CODEC, description);

        String encoded_user_id = ESAPI.encoder().encodeForSQL(
                ORACLE_CODEC, user_id);

        if (star.con == null) {
            log.error("Databse connection failiure.");
            return null;
        } else {
            try {
                PreparedStatement ps = star.con.prepareStatement(
                        "INSERT INTO bank(bank_id, "
                        + " bank_name, bank_code, description, user_id ) "
                        + " VALUES (?,?,?,?,?) ");

                ps.setString(1, encoded_bankId);
                ps.setString(2, encoded_bank_name);
                ps.setString(3, encoded_bank_code);
                ps.setString(4, encoded_description);
                ps.setString(5, encoded_user_id);

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

        String encodedSearch = ESAPI.encoder().encodeForSQL(ORACLE_CODEC, search);

        String bankId = null;
        String bank_name = null;
        String bank_code = null;

        ArrayList<ArrayList<String>> Mainlist = new ArrayList<ArrayList<String>>();

        if (star.con == null) {

            log.info(" Exception tag --> " + "Databse connection failiure. ");
            return null;

        } else {
            try {

                String query = "SELECT * FROM bank WHERE (bank_id LIKE ? OR bank_name LIKE ? "
                        + "OR bank_code LIKE ? )";

                PreparedStatement pstmt = star.con.prepareStatement(query);
                pstmt.setString(1, encodedSearch + "%");
                pstmt.setString(2, encodedSearch + "%");
                pstmt.setString(3, encodedSearch + "%");

                ResultSet r = pstmt.executeQuery();

                while (r.next()) {

                    ArrayList<String> list = new ArrayList<String>();

                    bankId = r.getString("bank_id");
                    bank_name = r.getString("bank_name");
                    bank_code = r.getString("bank_code");

                    list.add(bankId);
                    list.add(bank_name);
                    list.add(bank_code);

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

    public boolean checkAccountTypeAvailability(String desc) {

        String encodedDesc = ESAPI.encoder().encodeForSQL(ORACLE_CODEC, desc);
        boolean available = false;

        if (star.con == null) {

            log.error("Exception tag --> " + "Databse connection failiure. ");

        } else {
            try {

                String query
                        = "SELECT * FROM bank_account_type where type= ? ";

                PreparedStatement pstmt = star.con.prepareStatement(query);
                pstmt.setString(1, encodedDesc);
                ResultSet r = pstmt.executeQuery();

                while (r.next()) {
                    available = true;
                }
                if (available == false) {
                    insertAccountType(desc);
                }

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

    public boolean insertAccountType(String desc) {
        String encodedDesc = ESAPI.encoder().encodeForSQL(ORACLE_CODEC, desc);
        if (star.con == null) {
            log.info(" Exception tag --> " + "Databse connection failiure. ");
            return false;
        } else {
            try {
                String sql
                        = "INSERT INTO bank_account_type (type) VALUES (?);";
                PreparedStatement stmt = star.con.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, encodedDesc);
                int val = stmt.executeUpdate();
                if (val == 1) {
                    return true;
                } else {
                    return false;
                }

            } catch (SQLException e) {
                log.error("Exception tag --> " + "Invalid sql statement " + e.
                        getMessage());
                return false;
            } catch (Exception e) {
                log.error("Exception tag --> " + "Error");
                return false;
            }
        }

    }

    public ArrayList<String> loadAccountType() {

        String extraType = null;
        ArrayList list = new ArrayList();

        if (star.con == null) {
            log.error(" Exception tag --> " + "Databse connection failiure. ");
            return null;

        } else {
            try {

                String query = "SELECT * FROM bank_account_type ";
                PreparedStatement pstmt = star.con.prepareStatement(query);
                ResultSet r = pstmt.executeQuery();

                while (r.next()) {
                    extraType = r.getString("type");
                    list.add(extraType);

                }

            } catch (ArrayIndexOutOfBoundsException | SQLException |
                    NullPointerException e) {

                if (e instanceof ArrayIndexOutOfBoundsException) {
                    log.error("Exception tag --> "
                            + "Invalid entry location for list");
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
        return list;
    }

    public ArrayList<String> loadBranchNames(String bankId) {

        String encodedBankId = ESAPI.encoder().encodeForSQL(ORACLE_CODEC, bankId);

        String extraType = null;
        ArrayList list = new ArrayList();

        if (star.con == null) {
            log.error(" Exception tag --> " + "Databse connection failiure. ");
            return null;

        } else {
            try {

                String query = "SELECT * FROM bank_account_branch WHERE bank_id= ? ";

                PreparedStatement pstmt = star.con.prepareStatement(query);
                pstmt.setString(1, encodedBankId);
                ResultSet r = pstmt.executeQuery();

                while (r.next()) {
                    extraType = r.getString("branch");
                    list.add(extraType);

                }

            } catch (ArrayIndexOutOfBoundsException | SQLException |
                    NullPointerException e) {

                if (e instanceof ArrayIndexOutOfBoundsException) {
                    log.error("Exception tag --> "
                            + "Invalid entry location for list");
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
        return list;
    }

    public ArrayList<ArrayList<String>> searchBranch(String searchBankId, String search) {

        String encoded_BankId = ESAPI.encoder().
                encodeForSQL(ORACLE_CODEC, searchBankId);

        String encodedSearch = ESAPI.encoder().
                encodeForSQL(ORACLE_CODEC, search);

        String bankId = null;
        String bank = null;
        String branchCode = null;
        String branch = null;
        String id = null;

        ArrayList<ArrayList<String>> Mainlist
                = new ArrayList<ArrayList<String>>();

        if (star.con == null) {

            log.info(" Exception tag --> " + "Databse connection failiure. ");
            return null;

        } else {
            try {

                String query
                        = "select br.id, br.bank_id , ba.bank_name, br.branch_code, br.branch "
                        + "from bank_account_branch br , bank ba "
                        + "where ba.bank_id = br.bank_id AND br.bank_id = ? "
                        + "GROUP BY br.branch_code , br.branch "
                        + "HAVING br.branch LIKE ? OR br.branch_code LIKE ?  ";

                PreparedStatement pstmt = star.con.prepareStatement(query);
                pstmt.setString(1, encoded_BankId);
                pstmt.setString(2, encodedSearch + "%");
                pstmt.setString(3, encodedSearch + "%");

                ResultSet r = pstmt.executeQuery();

                while (r.next()) {

                    ArrayList<String> list = new ArrayList<String>();

                    bankId = r.getString("br.bank_id");
                    bank = r.getString("ba.bank_name");
                    branch = r.getString("br.branch");
                    branchCode = r.getString("br.branch_code");
                    id = r.getString("br.id");

                    list.add(bankId);
                    list.add(bank);
                    list.add(branchCode);
                    list.add(branch);
                    list.add(id);

                    Mainlist.add(list);

                }

            } catch (ArrayIndexOutOfBoundsException | SQLException |
                    NullPointerException e) {

                if (e instanceof ArrayIndexOutOfBoundsException) {

                    log.error("Exception tag --> "
                            + "Invalid entry location for list");

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

    public Boolean insertBranch_Telephone(
            int branchId,
            String contact,
            String telephoneNo) {

        String encoded_contact = ESAPI.encoder().encodeForSQL(
                ORACLE_CODEC, contact);
        String encoded_telephoneNo = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                telephoneNo);

        if (star.con == null) {
            log.error("Databse connection failiure.");
            return null;
        } else {
            try {
                PreparedStatement ps = star.con.prepareStatement(
                        "INSERT INTO bank_tel(bank_account_branch_id, contact, tel_no) VALUES (?,?,?) ");

                ps.setInt(1, branchId);
                ps.setString(2, encoded_contact);
                ps.setString(3, encoded_telephoneNo);

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

    public Boolean insertBranch_Mobile(
            int branchId,
            String contact,
            String mobileNo) {

        String encoded_contact = ESAPI.encoder().encodeForSQL(
                ORACLE_CODEC, contact);
        String encoded_mobileNo = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                mobileNo);

        if (star.con == null) {
            log.error("Databse connection failiure.");
            return null;
        } else {
            try {
                PreparedStatement ps = star.con.prepareStatement(
                        "INSERT INTO bank_mobile(bank_account_branch_id, contact, mob_no) VALUES (?,?,?) ");

                ps.setInt(1, branchId);
                ps.setString(2, encoded_contact);
                ps.setString(3, encoded_mobileNo);

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

    public Boolean insertBranch_Email(
            int branchId,
            String contact,
            String email) {

        String encoded_contact = ESAPI.encoder().encodeForSQL(
                ORACLE_CODEC, contact);
        String encoded_email = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                email);

        if (star.con == null) {
            log.error("Databse connection failiure.");
            return null;
        } else {
            try {
                PreparedStatement ps = star.con.prepareStatement(
                        "INSERT INTO bank_email(bank_account_branch_id, contact, email) VALUES (?,?,?) ");

                ps.setInt(1, branchId);
                ps.setString(2, encoded_contact);
                ps.setString(3, encoded_email);

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

    public Boolean insertBranch_Fax(
            int branchId,
            String contact,
            String fax) {

        String encoded_contact = ESAPI.encoder().encodeForSQL(
                ORACLE_CODEC, contact);
        String encoded_fax = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                fax);

        if (star.con == null) {
            log.error("Databse connection failiure.");
            return null;
        } else {
            try {
                PreparedStatement ps = star.con.prepareStatement(
                        "INSERT INTO bank_fax(bank_account_branch_id, contact, fax) VALUES (?,?,?) ");

                ps.setInt(1, branchId);
                ps.setString(2, encoded_contact);
                ps.setString(3, encoded_fax);

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

    public String getBranchId(String bankId, String branch) {

        String encoded_bankID = ESAPI.encoder().encodeForSQL(
                ORACLE_CODEC, bankId);
        String encoded_branch = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                branch);

        String id = null;
        if (star.con == null) {
            log.info(" Exception tag --> " + "Databse connection failiure. ");
            return id;
        } else {
            try {

                String query = "select id from bank_account_branch where bank_id = ? and branch = ? ";

                PreparedStatement pstmt = star.con.prepareStatement(query);
                pstmt.setString(1, encoded_bankID);
                pstmt.setString(2, encoded_branch);

                ResultSet r = pstmt.executeQuery();

                while (r.next()) {
                    id = r.getString("id");
                }

            } catch (ArrayIndexOutOfBoundsException | SQLException |
                    NullPointerException e) {

                if (e instanceof ArrayIndexOutOfBoundsException) {

                    log.error(
                            "Exception tag --> "
                            + "Invalid entry location for list");

                } else if (e instanceof SQLException) {

                    log.error("Exception tag --> " + "Invalid sql statement");

                } else if (e instanceof NullPointerException) {

                    log.error("Exception tag --> " + "Empty entry for list");

                }
                return id;
            } catch (Exception e) {

                log.error("Exception tag --> " + "Error");

                return id;
            }
        }
        return id;
    }

    public ArrayList<String> loadAccountNumbers(
            int branchId,
            int AccountType
    ) {

        String accountNumber = null;
        ArrayList list = new ArrayList();

        if (star.con == null) {
            log.error(" Exception tag --> " + "Databse connection failiure. ");
            return null;

        } else {
            try {

                String query = "SELECT account_no FROM bank_account_no WHERE bank_account_branch_id = ? AND bank_account_type = ? ";
                PreparedStatement pstmt = star.con.prepareStatement(query);
                pstmt.setInt(1, branchId);
                pstmt.setInt(2, AccountType);

                ResultSet r = pstmt.executeQuery();

                while (r.next()) {
                    accountNumber = r.getString("account_no");

                    list.add(accountNumber);

                }

            } catch (ArrayIndexOutOfBoundsException | SQLException |
                    NullPointerException e) {

                if (e instanceof ArrayIndexOutOfBoundsException) {
                    log.error("Exception tag --> "
                            + "Invalid entry location for list");
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
        return list;
    }

    public int getAccountTypeId(String accountName) {
        String encodedMarket = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                accountName);

        int marketID = 0;
        if (star.con == null) {
            log.info(" Exception tag --> " + "Databse connection failiure. ");
            return marketID;
        } else {
            try {

                String query = "SELECT id FROM bank_account_type where type = ? ";

                PreparedStatement pstmt = star.con.prepareStatement(query);
                pstmt.setString(1, encodedMarket);

                ResultSet r = pstmt.executeQuery();

                while (r.next()) {
                    marketID = Integer.parseInt(r.getString("id"));
                }

            } catch (ArrayIndexOutOfBoundsException | SQLException |
                    NullPointerException e) {

                if (e instanceof ArrayIndexOutOfBoundsException) {

                    log.error(
                            "Exception tag --> "
                            + "Invalid entry location for list");

                } else if (e instanceof SQLException) {

                    log.error("Exception tag --> " + "Invalid sql statement");

                } else if (e instanceof NullPointerException) {

                    log.error("Exception tag --> " + "Empty entry for list");

                }
                return marketID;
            } catch (Exception e) {

                log.error("Exception tag --> " + "Error");

                return marketID;
            }
        }
        return marketID;
    }

    public ArrayList<ArrayList<String>> searchBranchDetails(String bankId) {

        String encodedBankId = ESAPI.encoder().encodeForSQL(ORACLE_CODEC, bankId);

        String id = null;
        String branch = null;
        String branch_code = null;
        String account_No = null;

        ArrayList<ArrayList<String>> Mainlist = new ArrayList<ArrayList<String>>();

        if (star.con == null) {

            log.info(" Exception tag --> " + "Databse connection failiure. ");
            return null;

        } else {
            try {

                String query = "SELECT b.id,  b.branch, b.branch_code, a.account_no "
                        + "from bank_account_branch b, bank_account_no a "
                        + "WHERE b.id=a.bank_account_branch_id AND b.bank_id = ?";

                PreparedStatement pstmt = star.con.prepareStatement(query);
                pstmt.setString(1, encodedBankId);

                ResultSet r = pstmt.executeQuery();

                while (r.next()) {

                    ArrayList<String> list = new ArrayList<String>();

                    id = r.getString("b.id");
                    branch = r.getString("b.branch");
                    branch_code = r.getString("b.branch_code");
                    account_No = r.getString("a.account_no");

                    list.add(id);
                    list.add(branch);
                    list.add(branch_code);
                    list.add(account_No);

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

    public Boolean insertBranch(
            String bank_id,
            String branch_code,
            String branch,
            String user_id) {

        String encoded_bank_id = ESAPI.encoder().encodeForSQL(
                ORACLE_CODEC, bank_id);
        String encoded_branch_code = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                branch_code);
        String encoded_branch = ESAPI.encoder().encodeForSQL(
                ORACLE_CODEC, branch);
        String encoded_user_id = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                user_id);

        if (star.con == null) {
            log.error("Databse connection failiure.");
            return null;
        } else {
            try {
                PreparedStatement ps = star.con.prepareStatement(
                        "INSERT INTO bank_account_branch (bank_id, branch_code, branch, user_id) VALUES (?,?,?,?) ");

                ps.setString(1, encoded_bank_id);
                ps.setString(2, encoded_branch_code);
                ps.setString(3, encoded_branch);
                ps.setString(4, encoded_user_id);

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

    /*
     public Boolean insertAccountNo(
     int bank_account_branch_id,
     int bank_account_type,
     String account_no,
     String user_id) {

     String encoded_account_no = ESAPI.encoder().encodeForSQL(
     ORACLE_CODEC, account_no);
     String encoded_user_id = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
     user_id);

     if (star.con == null) {
     log.error("Databse connection failiure.");
     return null;
     } else {
     try {
     PreparedStatement ps = star.con.prepareStatement(
     "INSERT INTO bank_account_no (bank_account_branch_id, bank_account_type, account_no, user_id) VALUES (?,?,?,?) ");

     ps.setInt(1, bank_account_branch_id);
     ps.setInt(2, bank_account_type);
     ps.setString(3, encoded_account_no);
     ps.setString(4, encoded_user_id);

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
     */
    public int getMaxBank_account_branch() {

        int maxId = 0;
        String final_id = null;
        if (star.con == null) {
            log.error("Databse connection failiure.");
            return 0;
        } else {
            try {

                Statement st = star.con.createStatement();
                Statement ste = star.con.createStatement();
                ResultSet rs = st.executeQuery(
                        "SELECT MAX(id) as ID FROM bank_account_branch");

                while (rs.next()) {
                    maxId = rs.getInt("id");
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
                return 0;

            } catch (Exception e) {
                log.error("Exception tag --> " + "Error");
                return 0;
            }
        }

        return maxId;
    }

    public boolean checkingBankAvailability(String bankId) {
        String encodedBankId = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                bankId);

        boolean available = false;

        if (star.con == null) {
            log.error("Databse connection failiure.");
            return false;
        } else {

            try {
                String query
                        = "SELECT * FROM bank where bank_id= ? ";
                PreparedStatement pstmt = star.con.prepareStatement(query);
                pstmt.setString(1, encodedBankId);

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

    public boolean deleteBank(String bankID) {
        String encodedBankId = ESAPI.encoder().encodeForSQL(ORACLE_CODEC,
                bankID);
        if (star.con == null) {
            log.info(" Exception tag --> " + "Databse connection failiure. ");
            return false;
        } else {
            try {
                String sql
                        = "DELETE FROM bank WHERE bank_id=? ";
                PreparedStatement stmt = star.con.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, encodedBankId);
                stmt.executeUpdate();
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
            return true;
        }
    }

    public boolean updateBank_Details(
            String bank_ID,
            String bank_name,
            String bank_code,
            String description,
            String user_id) {

        String encoded_bankId = ESAPI.encoder().encodeForSQL(
                ORACLE_CODEC, bank_ID);

        String encoded_bank_name = ESAPI.encoder().encodeForSQL(
                ORACLE_CODEC, bank_name);

        String encoded_bank_code = ESAPI.encoder().encodeForSQL(
                ORACLE_CODEC, bank_code);

        String encoded_description = ESAPI.encoder().encodeForSQL(
                ORACLE_CODEC, description);

        String encoded_user_id = ESAPI.encoder().encodeForSQL(
                ORACLE_CODEC, user_id);

        if (star.con == null) {
            log.info(" Exception tag --> " + "Databse connection failiure. ");
            return false;
        } else {
            try {
                String sql = "Update bank "
                        + "SET bank_name=? ,bank_code=? ,description=?, "
                        + "user_id=?  "
                        + "where bank_id= ? ";

                PreparedStatement stmt = star.con.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);

                stmt.setString(1, encoded_bank_name);
                stmt.setString(2, encoded_bank_code);
                stmt.setString(3, encoded_description);
                stmt.setString(4, encoded_user_id);
                stmt.setString(5, encoded_bankId);

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

    public boolean delete_Branch(int id) {

        if (star.con == null) {
            log.info(" Exception tag --> " + "Databse connection failiure. ");
            return false;
        } else {
            try {
                String sql = "DELETE FROM bank_account_branch WHERE id=? ";
                PreparedStatement stmt = star.con.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);

                stmt.setInt(1, id);
                stmt.executeUpdate();
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
            return true;
        }
    }

    public boolean checkAccountNoAvailability(
            int bankAccount_branch_id,
            int bank_account_type,
            String account_no,
            String userId) {

        String encoded_accountNo = ESAPI.encoder().encodeForSQL(ORACLE_CODEC, account_no);
        boolean available = false;

        if (star.con == null) {

            log.error("Exception tag --> " + "Databse connection failiure. ");

        } else {
            try {

                String query
                        = "SELECT * FROM bank_account_no WHERE bank_account_branch_id = ? AND bank_account_type = ? AND account_no = ? ";

                PreparedStatement pstmt = star.con.prepareStatement(query);
                pstmt.setInt(1, bankAccount_branch_id);
                pstmt.setInt(2, bank_account_type);
                pstmt.setString(3, encoded_accountNo);

                ResultSet r = pstmt.executeQuery();

                while (r.next()) {
                    available = true;
                }
                if (available == false) {
                    insert_NewAccount_Number(bankAccount_branch_id, bank_account_type, account_no, userId);
                }

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

    public boolean insert_NewAccount_Number(
            int bankAccount_branch_id,
            int bank_account_type,
            String account_no,
            String userId) {

        String encoded_accountNo = ESAPI.encoder().encodeForSQL(ORACLE_CODEC, account_no);
        String encoded_userId = ESAPI.encoder().encodeForSQL(ORACLE_CODEC, userId);

        if (star.con == null) {
            log.info(" Exception tag --> " + "Databse connection failiure. ");
            return false;
        } else {
            try {
                String sql
                        = "INSERT INTO bank_account_no (bank_account_branch_id, bank_account_type, account_no, user_id) VALUES (?,?,?,?) ;";
                PreparedStatement stmt = star.con.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS);

                stmt.setInt(1, bankAccount_branch_id);
                stmt.setInt(2, bank_account_type);
                stmt.setString(3, encoded_accountNo);
                stmt.setString(4, encoded_userId);

                int val = stmt.executeUpdate();
                if (val == 1) {
                    return true;
                } else {
                    return false;
                }

            } catch (SQLException e) {
                log.error("Exception tag --> " + "Invalid sql statement " + e.
                        getMessage());
                return false;
            } catch (Exception e) {
                log.error("Exception tag --> " + "Error");
                return false;
            }
        }

    }

    public boolean delete_AllContacts_before_insert(int branchId) {

        if (star.con == null) {
            log.info(" Exception tag --> " + "Databse connection failiure. ");
            return false;
        } else {
            try {

                //delete telephone
                String sqlTelephone = "DELETE FROM bank_tel WHERE bank_account_branch_id= ? ";
                PreparedStatement stmt_tp = star.con.prepareStatement(sqlTelephone,
                        Statement.RETURN_GENERATED_KEYS);

                stmt_tp.setInt(1, branchId);
                stmt_tp.executeUpdate();

                //delete mobile
                String sqlMobile = "DELETE FROM bank_mobile WHERE bank_account_branch_id= ? ";
                PreparedStatement stmt_mb = star.con.prepareStatement(sqlMobile,
                        Statement.RETURN_GENERATED_KEYS);

                stmt_mb.setInt(1, branchId);
                stmt_mb.executeUpdate();

                //delete email
                String sqlEmail = "DELETE FROM bank_email WHERE bank_account_branch_id= ? ";
                PreparedStatement stmt_em = star.con.prepareStatement(sqlEmail,
                        Statement.RETURN_GENERATED_KEYS);

                stmt_em.setInt(1, branchId);
                stmt_em.executeUpdate();

                //delete fax
                String sqlFax = "DELETE FROM bank_fax WHERE bank_account_branch_id= ? ";
                PreparedStatement stmt_fx = star.con.prepareStatement(sqlFax,
                        Statement.RETURN_GENERATED_KEYS);

                stmt_fx.setInt(1, branchId);
                stmt_fx.executeUpdate();

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
            return true;
        }
    }

    public ArrayList<ArrayList<String>> search_All_Contact(int branchId, String ContactType) {

        String contact_person = null;
        String contact = null;

        ArrayList<ArrayList<String>> Mainlist
                = new ArrayList<ArrayList<String>>();

        if (star.con == null) {

            log.info(" Exception tag --> " + "Databse connection failiure. ");
            return null;

        } else {
            try {

                String query = null;

                if (ContactType == "TELEPHONE") {
                    query = "SELECT contact As CONTACT_PERSON, tel_no AS CONTACT FROM bank_tel WHERE  bank_account_branch_id = ? ";
                } else if (ContactType == "MOBILE") {
                    query = "SELECT contact As CONTACT_PERSON, mob_no AS CONTACT FROM bank_mobile WHERE  bank_account_branch_id = ? ";
                } else if (ContactType == "EMAIL") {
                    query = "SELECT contact As CONTACT_PERSON, email AS CONTACT FROM bank_email WHERE  bank_account_branch_id = ? ";
                } else if ((ContactType == "FAX")) {
                    query = "SELECT contact As CONTACT_PERSON, fax AS CONTACT FROM bank_fax WHERE  bank_account_branch_id = ? ";
                }

                PreparedStatement pstmt = star.con.prepareStatement(query);
                pstmt.setInt(1, branchId);

                ResultSet r = pstmt.executeQuery();

                while (r.next()) {

                    ArrayList<String> list = new ArrayList<String>();

                    contact_person = r.getString("CONTACT_PERSON");
                    contact = r.getString("CONTACT");

                    list.add(contact_person);
                    list.add(contact);

                    Mainlist.add(list);

                }

            } catch (ArrayIndexOutOfBoundsException | SQLException |
                    NullPointerException e) {

                if (e instanceof ArrayIndexOutOfBoundsException) {

                    log.error("Exception tag --> "
                            + "Invalid entry location for list");

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

    public boolean checkBranchAvailability(
            String bankId,
            String branchCode,
            String branch,
            String user_id) {

        String encoded_bankId = ESAPI.encoder().encodeForSQL(ORACLE_CODEC, bankId);

        String encoded_branchCode = ESAPI.encoder().encodeForSQL(ORACLE_CODEC, branchCode);

        String encoded_branch = ESAPI.encoder().encodeForSQL(ORACLE_CODEC, branch);

        boolean available = false;

        if (star.con == null) {

            log.error("Exception tag --> " + "Databse connection failiure. ");

        } else {
            try {

                String query
                        = "SELECT * FROM bank_account_branch WHERE bank_id = ? AND branch_code = ? AND branch = ? ";

                PreparedStatement pstmt = star.con.prepareStatement(query);
                pstmt.setString(1, encoded_bankId);
                pstmt.setString(2, encoded_branchCode);
                pstmt.setString(3, encoded_branch);

                ResultSet r = pstmt.executeQuery();

                while (r.next()) {
                    available = true;
                }
                if (available == false) {
                    insertBranch(bankId, branch, branchCode, user_id);
                }

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

    public String getBankDescription(String bankId) {
        String encoded_bankId = ESAPI.encoder().
                encodeForSQL(ORACLE_CODEC, bankId);

        String description = null;

        if (star.con == null) {
            log.info(" Exception tag --> " + "Databse connection failiure. ");
            return description;
        } else {
            try {

                String query
                        = "SELECT description from bank where bank_id = ? ";

                PreparedStatement pstmt = star.con.prepareStatement(query);
                pstmt.setString(1, encoded_bankId);

                ResultSet r = pstmt.executeQuery();

                while (r.next()) {
                    description = r.getString("description");
                }

            } catch (ArrayIndexOutOfBoundsException | SQLException |
                    NullPointerException e) {

                if (e instanceof ArrayIndexOutOfBoundsException) {

                    log.error(
                            "Exception tag --> "
                            + "Invalid entry location for list");

                } else if (e instanceof SQLException) {

                    log.error("Exception tag --> " + "Invalid sql statement");

                } else if (e instanceof NullPointerException) {

                    log.error("Exception tag --> " + "Empty entry for list");

                }
                return description;
            } catch (Exception e) {

                log.error("Exception tag --> " + "Error");

                return description;
            }
        }
        return description;
    }

}
