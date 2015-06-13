/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cheque.main;

import com.cheque.mainDAO.BankRegistrationDAO;
import com.cheque.msgbox.MessageBox;
import com.cheque.msgbox.SimpleMessageBoxFactory;
import com.cheque.popup.BranchPopUp;
import com.cheque.ui.ComponentControl;
import com.cheque.ui.LogType;
import com.cheque.ui.StagePassable;
import com.cheque.ui.UiMode;
import com.cheque.validations.CustomComboboxValidationImpl;
import com.cheque.validations.CustomTableViewValidationImpl;
import com.cheque.validations.CustomTextFieldValidationImpl;
import com.cheque.validations.ErrorMessages;
import com.cheque.validations.FormatAndValidate;
import com.cheque.validations.OptionalMessage;
import com.cheque.validations.Validatable;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.PopOver;
import org.controlsfx.dialog.Dialogs;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;

/**
 * FXML Controller class
 *
 * @author Sumudu De Zoysa
 */
public class BankRegistrationController extends AnchorPane implements
        Initializable, Validatable, StagePassable {

    //<editor-fold defaultstate="collapsed" desc="InitComponents">
    @FXML
    private TextField txtContactFax;

    @FXML
    private Button btnSaveBranch;

    @FXML
    private TextField txtBranchAdd;

    @FXML
    private TextField txtAddBranchCode;

    @FXML
    private TextField txtContactMobile;

    @FXML
    private Button btnAddMobile;

    @FXML
    private ComboBox<String> cmbBranchSelectForContact;

    @FXML
    private Tab tabContactDetails;

    @FXML
    private TableView<Mobile> tblMobile;

    @FXML
    private TextArea txtBankDescription;

    @FXML
    private TableView<Email> tblEmail;

    @FXML
    private TableColumn<Branch, String> tcAccountNo;

    @FXML
    private Button btnAddEmail;

    @FXML
    private Tab tabBankInfo;

    @FXML
    private TableColumn<Bank, String> tcBankCode;

    @FXML
    private TextField txtBranchMobile;

    @FXML
    private TableColumn<Bank, String> tcBankID;

    @FXML
    private TableColumn<Mobile, String> tcMobileContact;

    @FXML
    private TableColumn<Branch, String> tcBranchCode;

    @FXML
    private Button btnBack;

    @FXML
    private TableView<Branch> tblSearchBranch;

    @FXML
    private Button btnBankSearch;

    @FXML
    private Button btnNext;

    @FXML
    private TableColumn<Telephone, String> tcTelephoneContact;

    @FXML
    private TextField txtContactEmail;

    @FXML
    private TableColumn<Email, String> tcEmail;

    @FXML
    private Button btnSearchBranch;

    @FXML
    private Label lblBranchCode;

    @FXML
    private TableColumn<Mobile, String> tcMobile;

    @FXML
    private TableColumn<Email, String> tcEmailContact;

    @FXML
    private Button btnSearchBranchCode;

    @FXML
    private ComboBox<String> cmbAccountType;

    @FXML
    private Insets x3;

    @FXML
    private Label lblAccountNo;

    @FXML
    private TableColumn<Fax, String> tcFax;

    @FXML
    private Label lblBranch;

    @FXML
    private TableView<Telephone> tblTelephone;

    @FXML
    private Label lblAccountType;

    @FXML
    private TextField txtBranchEmail;

    @FXML
    private TextField txtBankCode;

    @FXML
    private Tab tabBranchInfo;

    @FXML
    private ComboBox<String> cmbAccountNo;

    @FXML
    private TableColumn<Bank, String> tcBank;

    @FXML
    private Button btnAddTelephone;

    @FXML
    private TableColumn<Branch, String> tcBranch;

    @FXML
    private Button btnDelete;

    @FXML
    private TextField txtBank;

    @FXML
    private Insets x31;

    @FXML
    private Button btnAddAccountNo;

    @FXML
    private Button btnClose;

    @FXML
    private TextField txtContactTelephone;

    @FXML
    private ImageView imgNext;

    @FXML
    private TextField txtBankSearch;

    @FXML
    private TextField txtBankID;

    @FXML
    private Label lblSearchBank;

    @FXML
    private Button btnRemoveMobile;

    @FXML
    private Button btnRemoveEmail;

    @FXML
    private Button btnRemoveFax;

    @FXML
    private TextField txtBranchFax;

    @FXML
    private TableView<?> tblBankSearch;

    @FXML
    private Button btnAddAccountType;

    @FXML
    private TableView<Fax> tblFax;

    @FXML
    private Button btnRemoveTelephone;

    @FXML
    private TextField txtBranchTelephone;

    @FXML
    private Button btnAddFax;

    @FXML
    private TableColumn<Telephone, String> tcTelephone;

    @FXML
    private TableColumn<Fax, String> tcFaxContact;

    @FXML
    private TabPane tabPane;

    //</editor-fold>
    private String userId;
    private String userName;
    private String userCategory;
    private boolean insert = false;
    private boolean update = false;
    private boolean delete = false;
    private boolean view = false;

    private Stage stage;

    boolean isBankDetailsInserted = false;
    boolean isBranchTelephoneInserted = false;
    boolean isBranchMobileInserted = false;
    boolean isBranchEmailInserted = false;
    boolean isBranchFaxInserted = false;
    boolean isBranchInserted = false;

    private int updateInt = 0;

    private MessageBox mb;
    private ComponentControl componentControl = new ComponentControl();

    BankRegistrationDAO bankRegistrationDAO = new BankRegistrationDAO();
    private Bank bank = new Bank();
    private Branch branch = new Branch();

    private ObservableList bankSearchData = FXCollections.observableArrayList();
    private ObservableList branchSearchData = FXCollections.observableArrayList();
    private ObservableList telephoneData = FXCollections.observableArrayList();
    private ObservableList mobileData = FXCollections.observableArrayList();
    private ObservableList emailData = FXCollections.observableArrayList();
    private ObservableList faxData = FXCollections.observableArrayList();

    //Validations--------------
    private final ValidationSupport validationSupport = new ValidationSupport();
    private final ValidationSupport validationSupportBranch = new ValidationSupport();
    private final ValidationSupport validationSupportBranchCode = new ValidationSupport();
    private final ValidationSupport validationSupportBranchAccountNo = new ValidationSupport();

    private final ValidationSupport validationSupportBank = new ValidationSupport();
    private final ValidationSupport validationSupportBankID = new ValidationSupport();

    private final ValidationSupport validationSupportBranchTable = new ValidationSupport();

    private final ValidationSupport validateTelephone = new ValidationSupport();
    private final ValidationSupport validateMobile = new ValidationSupport();
    private final ValidationSupport validateEmail = new ValidationSupport();
    private final ValidationSupport validateFax = new ValidationSupport();

    private final FormatAndValidate fav = FormatAndValidate.getInstance();

    Telephone telephone = new Telephone();
    Mobile mobile = new Mobile();
    Email email = new Email();
    Fax fax = new Fax();

    String selectedBranchId = null;

    //branch popup---------------------------------------
    private TableView branchTable = new TableView();
    private ObservableList<BranchPopUp> branchData = FXCollections.
            observableArrayList();
    private PopOver branchPop;
    private BranchPopUp branchPopUp
            = new BranchPopUp();

    //branchCode popup---------------------------------------
    private TableView branch_CodeTable = new TableView();
    private ObservableList<BranchPopUp> branch_CodeData = FXCollections.
            observableArrayList();
    private PopOver branch_CodePop;
    private BranchPopUp branch_CodePopUp
            = new BranchPopUp();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mb = SimpleMessageBoxFactory.createMessageBox();

        //------------------Table Bank Search
        tcBankID.setCellValueFactory(new PropertyValueFactory<Bank, String>(
                "colBankId"));

        tcBank.setCellValueFactory(new PropertyValueFactory<Bank, String>(
                "colBank"));

        tcBankCode.setCellValueFactory(new PropertyValueFactory<Bank, String>(
                "colBankCode"));

        tblBankSearch.setItems(bankSearchData);

        //------------------Table Branch Search
        tcBranch.setCellValueFactory(new PropertyValueFactory<Branch, String>(
                "colBranch"));

        tcBranchCode.setCellValueFactory(new PropertyValueFactory<Branch, String>(
                "colBranchCode"));

        tcAccountNo.setCellValueFactory(new PropertyValueFactory<Branch, String>(
                "colAccountNo"));

        tblSearchBranch.setItems(branchSearchData);

        //<editor-fold defaultstate="collapsed" desc="Table Telephone">
        tcTelephoneContact.setCellValueFactory(new PropertyValueFactory<Telephone, String>(
                "colContactTelephone"));

        tcTelephone.setCellValueFactory(new PropertyValueFactory<Telephone, String>(
                "colTelephone"));

        tblTelephone.setItems(telephoneData);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Table Mobile">
        tcMobileContact.setCellValueFactory(new PropertyValueFactory<Mobile, String>(
                "colContactMobile"));

        tcMobile.setCellValueFactory(new PropertyValueFactory<Mobile, String>(
                "colMobile"));

        tblMobile.setItems(mobileData);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Table Email">
        tcEmailContact.setCellValueFactory(new PropertyValueFactory<Email, String>(
                "colContactEmail"));

        tcEmail.setCellValueFactory(new PropertyValueFactory<Email, String>(
                "colEmail"));

        tblEmail.setItems(emailData);
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Table Fax">
        tcFaxContact.setCellValueFactory(new PropertyValueFactory<Fax, String>(
                "colContactFax"));

        tcFax.setCellValueFactory(new PropertyValueFactory<Fax, String>(
                "colFax"));

        tblFax.setItems(faxData);
//</editor-fold> 

        hidePrinterRemark();

        validatorInitialization();

    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public void clearInput() {
        btnBack.setVisible(false);
        txtBankID.setText(bankRegistrationDAO.generateBankId());
        loadAccountTypeToComboBox();
        tableBankDataLoader(txtBankSearch.getText().trim());

        isBankDetailsInserted = false;
        isBranchTelephoneInserted = false;
        isBranchMobileInserted = false;
        isBranchEmailInserted = false;
        isBranchFaxInserted = false;
        isBranchInserted = false;
        updateInt = 0;

        telephoneData.clear();
        mobileData.clear();
        emailData.clear();
        faxData.clear();

        SingleSelectionModel<Tab> selectionModel = tabPane.
                getSelectionModel();

        selectionModel.select(0);

        txtBank.clear();
        txtBankDescription.clear();
        txtBankCode.clear();
        txtBankSearch.clear();

        branchData.clear();

        btnDelete.setDisable(true);
        btnDelete.setVisible(false);

    }

    @Override
    public void clearValidations() {

    }

    @Override
    public void setStage(Stage stage, Object[] obj) {
        this.stage = stage;

        hidePrinterRemark();

        //Branch PopUp---------
        branchTable = branchPopUp.tableViewLoader(branchData);

        branchTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                try {
                    BranchPopUp p = null;
                    p = (BranchPopUp) branchTable.
                            getSelectionModel().
                            getSelectedItem();

                    if (p.getColBankId() != null) {
                        txtBranchAdd.setText(p.getColBranch());
                        selectedBranchId = p.getColId();

                    }

                } catch (NullPointerException n) {

                }

                branchPop.hide();
                validatorInitialization();

            }

        });

        branchTable.setOnMousePressed(e -> {

            if (e.getButton() == MouseButton.SECONDARY) {

                branchPop.hide();
                validatorInitialization();

            }

        });

        //BranchCode PopUp---------
        branch_CodeTable = branchPopUp.tableViewLoader(branch_CodeData);

        branch_CodeTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                try {
                    BranchPopUp p = null;
                    p = (BranchPopUp) branch_CodeTable.
                            getSelectionModel().
                            getSelectedItem();

                    if (p.getColBranchId() != null) {
                        txtAddBranchCode.setText(p.getColBranchId());

                    }

                } catch (NullPointerException n) {

                }

                branch_CodePop.hide();
                validatorInitialization();

            }

        });

        branch_CodeTable.setOnMousePressed(e -> {

            if (e.getButton() == MouseButton.SECONDARY) {

                branch_CodePop.hide();
                validatorInitialization();

            }

        });

        branchPop = new PopOver(branchTable);
        branch_CodePop = new PopOver(branch_CodeTable);

        stage.setOnCloseRequest(e -> {

            if (branchPop.isShowing() || branch_CodePop.isShowing()) {
                e.consume();
                branchPop.hide();
                branch_CodePop.hide();

            }
        });
    }

    //<editor-fold defaultstate="collapsed" desc="Action Events">
    @FXML
    void btnSaveBranchOnAction(ActionEvent event) {
        validatorInitialization();

        //-------Insert Branch Information to DataBase
        boolean validationSupportBranchResult = false;
        boolean validationSupportBranch_CodeResult = false;
        boolean validationSupportBranch_AccountNo = false;

        boolean isAccountNumberInserted = false;
        boolean isUpdated_BranchDetails = false;

        ValidationResult v = validationSupportBranch.getValidationResult();
        ValidationResult v1 = validationSupportBranchCode.getValidationResult();
        ValidationResult v2 = validationSupportBranchAccountNo.getValidationResult();

        if (v != null && v1 != null && v2 != null) {
            validationSupportBranchResult = validationSupportBranch.isInvalid();
            validationSupportBranch_CodeResult = validationSupportBranchCode.isInvalid();
            validationSupportBranch_AccountNo = validationSupportBranchAccountNo.isInvalid();

            if (validationSupportBranchResult == true || validationSupportBranch_CodeResult == true || validationSupportBranch_AccountNo == true) {
                mb.ShowMessage(stage, ErrorMessages.MandatoryError, "Error",
                        MessageBox.MessageIcon.MSG_ICON_FAIL,
                        MessageBox.MessageType.MSG_OK);

                validatorInitialization();

            } else if (validationSupportBranchResult == false && validationSupportBranch_CodeResult == false && validationSupportBranch_AccountNo == false) {

                boolean isAccountNo_Available
                        = bankRegistrationDAO.checkAccountNoAvailability(
                                Integer.parseInt(selectedBranchId),
                                bankRegistrationDAO.getAccountTypeId(cmbAccountType.getSelectionModel().getSelectedItem()),
                                cmbAccountNo.getSelectionModel().getSelectedItem(),
                                userId);

                if (isAccountNo_Available == false) {
                    isBranchInserted = bankRegistrationDAO.insertBranch(
                            txtBankID.getText(), txtBranchAdd.getText(), txtAddBranchCode.getText(), userId);
                }

//
//                
//                if (isBranchInserted == true) {
//                    isAccountNumberInserted = bankRegistrationDAO.insertAccountNo(
//                            bankRegistrationDAO.getMaxBank_account_branch(),
//                            bankRegistrationDAO.getAccountTypeId(cmbAccountType.getSelectionModel().getSelectedItem()),
//                            cmbAccountNo.getSelectionModel().getSelectedItem(),
//                            userId);
//                }
                tableBranchDataLoader(txtBankID.getText());

                txtBranchAdd.clear();
                txtAddBranchCode.clear();
                cmbAccountType.getSelectionModel().selectFirst();
                cmbAccountNo.setItems(null);

            }

        }

    }

    @FXML
    private void txtBankIdOnAction(ActionEvent event) {
    }

    @FXML
    private void txtAddBranchCodeOnAction(ActionEvent event) {
    }

    @FXML
    private void txtBankOnAction(ActionEvent event) {
    }

    @FXML
    private void txtBankCodeOnAction(ActionEvent event) {
    }

    @FXML
    private void txtBankSearchOnAction(ActionEvent event) {
    }

    @FXML
    private void btnBankSearchOnAction(ActionEvent event) {
    }

    @FXML
    private void cmbBranchSelectForContactOnAction(ActionEvent event) {
        validatorInitialization();

        try {

            load_contact_data(Integer.parseInt(bankRegistrationDAO.getBranchId(txtBankID.getText(), cmbBranchSelectForContact.getSelectionModel().getSelectedItem())));
        } catch (Exception ex) {

        }
    }

    @FXML
    private void txtBranchAddOnAction(ActionEvent event) {

    }

    @FXML
    private void btnSearchBranchOnAction(ActionEvent event) {

        branchDataLoader(txtBankID.getText(), txtBranchAdd.getText());
        branchTable.setItems(branchData);
        if (!branchData.isEmpty()) {

            branchPop.show(btnSearchBranch);
        }
        validatorInitialization();
    }

    private void branchDataLoader(String bankID, String keyWord) {
        branchData.clear();
        ArrayList<ArrayList<String>> sundryInfo
                = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> list = bankRegistrationDAO.
                searchBranch(bankID, keyWord);

        if (list != null) {

            for (int i = 0; i < list.size(); i++) {

                sundryInfo.add(list.get(i));
            }

            if (sundryInfo != null && sundryInfo.size() > 0) {
                for (int i = 0; i < sundryInfo.size(); i++) {

                    branchPopUp = new BranchPopUp();
                    branchPopUp.colBankId.setValue(sundryInfo.
                            get(i).get(0));
                    branchPopUp.colBank.setValue(sundryInfo.get(i).
                            get(1));
                    branchPopUp.colBranchId.setValue(sundryInfo.get(i).
                            get(2));
                    branchPopUp.colBranch.setValue(sundryInfo.get(i).
                            get(3));
                    branchPopUp.colId.setValue(sundryInfo.get(i).
                            get(4));
                    branchData.add(branchPopUp);
                }
            }
        }
    }

    @FXML
    private void btnSeachBranchCodeOnAction(ActionEvent event) {
        branchCodeDataLoader(txtBankID.getText(), txtAddBranchCode.getText());
        branch_CodeTable.setItems(branch_CodeData);
        if (!branch_CodeData.isEmpty()) {

            branch_CodePop.show(btnSearchBranchCode);
        }
        validatorInitialization();

        if (!txtAddBranchCode.getText().isEmpty() || !txtBranchAdd.getText().isEmpty()) {
            loadAccountNumberToComboBox();
        }

    }

    private void branchCodeDataLoader(String bankID, String keyWord) {
        branch_CodeData.clear();
        ArrayList<ArrayList<String>> sundryInfo
                = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> list = bankRegistrationDAO.
                searchBranch(bankID, keyWord);

        if (list != null) {

            for (int i = 0; i < list.size(); i++) {

                sundryInfo.add(list.get(i));
            }

            if (sundryInfo != null && sundryInfo.size() > 0) {
                for (int i = 0; i < sundryInfo.size(); i++) {

                    branch_CodePopUp = new BranchPopUp();
                    branch_CodePopUp.colBankId.setValue(sundryInfo.
                            get(i).get(0));
                    branch_CodePopUp.colBank.setValue(sundryInfo.get(i).
                            get(1));
                    branch_CodePopUp.colBranchId.setValue(sundryInfo.get(i).
                            get(2));
                    branch_CodePopUp.colBranch.setValue(sundryInfo.get(i).
                            get(3));
                    branch_CodeData.add(branch_CodePopUp);
                }
            }
        }
    }

    @FXML
    private void cmbAccountTypeOnAction(ActionEvent event) {
    }

    @FXML
    private void btnAddAccountTypeOnAction(ActionEvent event) {
        Optional<String> response = null;
        try {
            response = Dialogs.create().title(OptionalMessage.NEW_TYPE.toString()).masthead(
                    OptionalMessage.ADD_NEW_TYPE.toString()).message(OptionalMessage.TITLE.toString()).
                    showTextInput();

            if (response != null) {
                if (!response.get().isEmpty()) {

                    boolean valid = fav.validName(response.get().trim());
                    if (valid == true) {

                        boolean isDataAvailable
                                = bankRegistrationDAO.
                                checkAccountTypeAvailability(response.get());

                        if (isDataAvailable == false) {

                            loadAccountTypeToComboBox();

                        } else if (isDataAvailable == true) {
                            mb.ShowMessage(stage, ErrorMessages.AllreadyExist,
                                    "Error",
                                    MessageBox.MessageIcon.MSG_ICON_FAIL,
                                    MessageBox.MessageType.MSG_OK);
                        }
                    }

                }
            }
        } catch (NoSuchElementException e) {

            if (e instanceof NoSuchElementException) {

                mb.ShowMessage(stage, ErrorMessages.UnsavedData, "Error",
                        MessageBox.MessageIcon.MSG_ICON_FAIL,
                        MessageBox.MessageType.MSG_OK);

            }

        } catch (Exception e) {
            mb.ShowMessage(stage, ErrorMessages.Error, "Error",
                    MessageBox.MessageIcon.MSG_ICON_FAIL,
                    MessageBox.MessageType.MSG_OK);

        }
        validatorInitialization();
    }

    private void loadAccountTypeToComboBox() {
        cmbAccountType.setItems(null);
        ArrayList<String> professionList = null;
        professionList = bankRegistrationDAO.loadAccountType();
        if (professionList != null) {
            try {
                ObservableList<String> List = FXCollections.observableArrayList(
                        professionList);
                cmbAccountType.setItems(List);
                cmbAccountType.setValue(List.get(0));
            } catch (Exception e) {

            }

        }
    }

    @FXML
    private void cmbAccountNoOnAction(ActionEvent event) {
    }

    @FXML
    private void btnAddAccountNoOnAction(ActionEvent event) {

        validatorInitialization();

        boolean validationSupportBranchResult = false;

        ValidationResult v = validationSupportBranch.getValidationResult();

        if (v != null) {
            validationSupportBranchResult = validationSupportBranch.isInvalid();

            if (validationSupportBranchResult == true) {
                mb.ShowMessage(stage, ErrorMessages.BranchNotAssigned, "Error",
                        MessageBox.MessageIcon.MSG_ICON_FAIL,
                        MessageBox.MessageType.MSG_OK);

                validatorInitialization();

            } else if (validationSupportBranchResult == false) {
                Optional<String> response = null;
                try {
                    response = Dialogs.create().title(OptionalMessage.NEW_ACCOUNT_NO.toString()).masthead(
                            OptionalMessage.ADD_NEW_ACCOUNT_NO.toString()).message(OptionalMessage.ACCOUNTNO.toString()).
                            showTextInput();

                    if (response != null) {
                        if (!response.get().isEmpty()) {

                            boolean valid = fav.validName(response.get().trim());
                            if (valid == true) {

                                boolean isBranchAvailable = bankRegistrationDAO.checkBranchAvailability(
                                        txtBankID.getText(), txtBranchAdd.getText(), txtAddBranchCode.getText(), userId);

                                if (isBranchAvailable == false) {
                                    selectedBranchId = String.valueOf(bankRegistrationDAO.getMaxBank_account_branch());
                                }

                                boolean isAccountNo_Available
                                        = bankRegistrationDAO.checkAccountNoAvailability(
                                                Integer.parseInt(selectedBranchId),
                                                bankRegistrationDAO.getAccountTypeId(cmbAccountType.getSelectionModel().getSelectedItem()),
                                                response.get(),
                                                userId);

                                if (isAccountNo_Available == false) {

                                    loadAccountNumberToComboBox();

                                } else if (isAccountNo_Available == true) {
                                    mb.ShowMessage(stage, ErrorMessages.AllreadyExist,
                                            "Error",
                                            MessageBox.MessageIcon.MSG_ICON_FAIL,
                                            MessageBox.MessageType.MSG_OK);
                                }
                            }

                        }
                    }
                } catch (NoSuchElementException e) {

                    if (e instanceof NoSuchElementException) {

                        mb.ShowMessage(stage, ErrorMessages.UnsavedData, "Error",
                                MessageBox.MessageIcon.MSG_ICON_FAIL,
                                MessageBox.MessageType.MSG_OK);

                    }

                } catch (Exception e) {
                    mb.ShowMessage(stage, ErrorMessages.Error, "Error",
                            MessageBox.MessageIcon.MSG_ICON_FAIL,
                            MessageBox.MessageType.MSG_OK);

                }
                validatorInitialization();
            }

        }

    }

    @FXML
    private void btnRemoveTelephoneOnAction(ActionEvent event) {
        try {

            boolean model = tblTelephone.getSelectionModel().isEmpty();

            if (model == false) {
                Double value = Double.parseDouble(tblTelephone.
                        getSelectionModel().
                        getSelectedItem().getColTelephone());

                telephoneData.remove(tblTelephone.getSelectionModel().
                        getSelectedIndex());

            }
            validatorInitialization();

        } catch (Exception e) {

        }

    }

    @FXML
    private void txtBranchMobileOnAction(ActionEvent event) {
    }

    @FXML
    private void btnAddMobileOnAction(ActionEvent event) {
        validatorInitialization();

        boolean validationSupportOtherCharges = false;

        if (validationSupportOtherCharges == true) {

        } else if (validationSupportOtherCharges == false) {

            if (tblMobile.getItems().size() != 0) {
                int n = tblMobile.getItems().size();
                for (int s = 0; s < n; s++) {
                    mobile
                            = (Mobile) tblMobile.getItems().
                            get(s);
                    if (txtContactMobile.getText().equals(mobile.getColContactMobile())
                            && txtBranchMobile.getText().equals(mobile.getColMobile())
                            && tblMobile.getItems().size() > 0) {

                        mobileData.remove(s);
                        n--;
                    }
                }
            }

            mobile = new Mobile();
            mobile.colContactMobile.setValue(txtContactMobile.getText());
            mobile.colMobile.setValue(txtBranchMobile.getText());

            mobileData.add(mobile);

            txtContactMobile.clear();
            txtBranchMobile.clear();

        }
    }

    @FXML
    private void txtBranchTelephoneOnAction(ActionEvent event) {
    }

    @FXML
    private void btnAddTelephoneOnAction(ActionEvent event) {

        validatorInitialization();

        boolean validationSupportOtherCharges = false;

        if (validationSupportOtherCharges == true) {

        } else if (validationSupportOtherCharges == false) {

            if (tblTelephone.getItems().size() != 0) {
                int n = tblTelephone.getItems().size();
                for (int s = 0; s < n; s++) {
                    telephone
                            = (Telephone) tblTelephone.getItems().
                            get(s);
                    if (txtContactTelephone.getText().equals(telephone.getColContactTelephone())
                            && txtBranchTelephone.getText().equals(telephone.getColTelephone())
                            && tblTelephone.getItems().size() > 0) {

                        telephoneData.remove(s);
                        n--;
                    }
                }
            }

            telephone = new Telephone();
            telephone.colContactTelephone.setValue(txtContactTelephone.getText());
            telephone.colTelephone.setValue(txtBranchTelephone.getText());

            telephoneData.add(telephone);

            txtContactTelephone.clear();
            txtBranchTelephone.clear();

        }

    }

    @FXML
    private void txtBranchEmailOnAction(ActionEvent event) {
    }

    @FXML
    private void btnAddEmailOnAction(ActionEvent event) {
        validatorInitialization();

        boolean validationSupportEmail = false;

        if (validationSupportEmail == true) {

        } else if (validationSupportEmail == false) {

            if (tblEmail.getItems().size() != 0) {
                int n = tblEmail.getItems().size();
                for (int s = 0; s < n; s++) {
                    email
                            = (Email) tblEmail.getItems().
                            get(s);
                    if (txtContactEmail.getText().equals(email.getColContactEmail())
                            && txtBranchEmail.getText().equals(email.getColEmail())
                            && tblEmail.getItems().size() > 0) {

                        emailData.remove(s);
                        n--;
                    }
                }
            }

            email = new Email();
            email.colContactEmail.setValue(txtContactEmail.getText());
            email.colEmail.setValue(txtBranchEmail.getText());

            emailData.add(email);

            txtContactEmail.clear();
            txtBranchEmail.clear();

        }

    }

    @FXML
    private void txtBranchFaxOnAction(ActionEvent event) {
    }

    @FXML
    private void btnAddFaxOnAction(ActionEvent event) {
        validatorInitialization();

        boolean validationSupportEmail = false;

        if (validationSupportEmail == true) {

        } else if (validationSupportEmail == false) {

            if (tblFax.getItems().size() != 0) {
                int n = tblFax.getItems().size();
                for (int s = 0; s < n; s++) {
                    fax
                            = (Fax) tblFax.getItems().
                            get(s);
                    if (txtContactFax.getText().equals(fax.getColContactFax())
                            && txtBranchFax.getText().equals(fax.getColFax())
                            && tblFax.getItems().size() > 0) {

                        faxData.remove(s);
                        n--;
                    }
                }
            }

            fax = new Fax();
            fax.colContactFax.setValue(txtContactFax.getText());
            fax.colFax.setValue(txtBranchFax.getText());

            faxData.add(fax);

            txtContactFax.clear();
            txtBranchFax.clear();

        }
    }

    @FXML
    private void txtContactTelephoneOnAction(ActionEvent event) {
    }

    @FXML
    private void txtContactMobileOnAction(ActionEvent event) {
    }

    @FXML
    private void txtContactEmailOnAction(ActionEvent event) {
    }

    @FXML
    private void txtContactFaxOnAction(ActionEvent event) {
    }

    @FXML
    private void btnRemoveMobileOnAction(ActionEvent event) {
        try {

            boolean model = tblMobile.getSelectionModel().isEmpty();

            if (model == false) {
                Double value = Double.parseDouble(tblMobile.
                        getSelectionModel().
                        getSelectedItem().getColMobile());

                mobileData.remove(tblMobile.getSelectionModel().
                        getSelectedIndex());

            }
            validatorInitialization();

        } catch (Exception e) {

        }
    }

    @FXML
    private void btnRemoveEmailOnAction(ActionEvent event) {
        try {

            boolean model = tblEmail.getSelectionModel().isEmpty();

            if (model == false) {
                Double value = Double.parseDouble(tblEmail.
                        getSelectionModel().
                        getSelectedItem().getColEmail());

                emailData.remove(tblEmail.getSelectionModel().
                        getSelectedIndex());

            }
            validatorInitialization();

        } catch (Exception e) {

        }
    }

    @FXML
    private void btnRemoveFaxOnAction(ActionEvent event) {
        try {

            boolean model = tblFax.getSelectionModel().isEmpty();

            if (model == false) {
                Double value = Double.parseDouble(tblFax.
                        getSelectionModel().
                        getSelectedItem().getColFax());

                faxData.remove(tblFax.getSelectionModel().
                        getSelectedIndex());

            }
            validatorInitialization();

        } catch (Exception e) {

        }
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
        boolean isDeletedBank = false;
        validatorInitialization();

        if (bankRegistrationDAO.checkingBankAvailability(
                txtBankID.getText())) {
            MessageBox.MessageOutput option = mb.ShowMessage(stage,
                    ErrorMessages.Delete, "Information",
                    MessageBox.MessageIcon.MSG_ICON_NONE,
                    MessageBox.MessageType.MSG_YESNO);
            if (option.equals(MessageBox.MessageOutput.MSG_YES)) {
                isDeletedBank = bankRegistrationDAO.
                        deleteBank(txtBankID.getText());

                if (isDeletedBank == true) {

                    mb.ShowMessage(stage,
                            ErrorMessages.SuccesfullyDeleted,
                            "Information",
                            MessageBox.MessageIcon.MSG_ICON_SUCCESS,
                            MessageBox.MessageType.MSG_OK);
                    clearInput();

                } else {
                    mb.ShowMessage(stage, ErrorMessages.Error, "Error",
                            MessageBox.MessageIcon.MSG_ICON_FAIL,
                            MessageBox.MessageType.MSG_OK);
                }
            }

        } else {
            mb.ShowMessage(stage, ErrorMessages.InvalidId, "Error",
                    MessageBox.MessageIcon.MSG_ICON_FAIL,
                    MessageBox.MessageType.MSG_OK);
        }
    }

    @FXML
    private void btnBackOnAction(ActionEvent event) {
        //Tab Handling----------------------------
        SingleSelectionModel<Tab> selectionModel = tabPane.
                getSelectionModel();

        if (selectionModel.getSelectedIndex() == 0) {

            btnBack.setVisible(false);
            selectionModel.select(1);

        } else if (selectionModel.getSelectedIndex() == 1) {

            btnBack.setVisible(false);
            btnNext.setText("Next");
            selectionModel.select(0); //Select Branch Information

        } else if (selectionModel.getSelectedIndex() == 2) {

            btnBack.setVisible(true);
            btnNext.setText("Next");
            selectionModel.select(1);
        }

    }

    @FXML
    private void btnNextOnAction(ActionEvent event) {

        //Tab Handling----------------------------
        SingleSelectionModel<Tab> selectionModel = tabPane.
                getSelectionModel();

        //<editor-fold defaultstate="collapsed" desc="Tab-Bank details">
        if (selectionModel.getSelectedIndex() == 0) { //------------->>>Stay at first tab ( Bank Information)

            validatorInitialization();

            //-------Insert Bank Information to DataBase
            boolean validationSupportBankResult = false;
            boolean validationSupportBankIDResult = false;

            boolean isUpdated_BankDetails = false;

            ValidationResult v = validationSupportBank.getValidationResult();
            ValidationResult v1 = validationSupportBankID.getValidationResult();

            if (v != null && v1 != null) {
                validationSupportBankResult = validationSupportBank.isInvalid();
                validationSupportBankIDResult = validationSupportBankID.isInvalid();

                if (validationSupportBankResult == true || validationSupportBankIDResult == true) {
                    mb.ShowMessage(stage, ErrorMessages.MandatoryError, "Error",
                            MessageBox.MessageIcon.MSG_ICON_FAIL,
                            MessageBox.MessageType.MSG_OK);

                    validatorInitialization();

                } else if (validationSupportBankResult == false && validationSupportBankIDResult == false) {

                    validatorInitialization();
                    //-------------Update------------------------ 

                    if (updateInt == 1) {
                        isUpdated_BankDetails = bankRegistrationDAO.updateBank_Details(
                                txtBankID.getText(),
                                txtBank.getText(),
                                txtBankCode.getText(),
                                txtBankDescription.getText(),
                                userId
                        );
                    } //-------------Insert------------------------ 
                    else if (updateInt == 0) {
                        isBankDetailsInserted = bankRegistrationDAO.insertBankDetails(
                                txtBankID.getText(),
                                txtBank.getText(),
                                txtBankCode.getText(),
                                txtBankDescription.getText(),
                                userId);
                    }

                    if (isBankDetailsInserted == true || isUpdated_BankDetails == true) {
                        btnBack.setVisible(true);
                        btnBack.setVisible(true);
                        tableBranchDataLoader(txtBankID.getText());
                        btnNext.setText("Next");
                        tableBankDataLoader(txtBankSearch.getText().trim());
                        selectionModel.select(1); //Select Branch Information

                    }
                }

            }

        } //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Tab-Branch">
        else if (selectionModel.getSelectedIndex() == 1) { //------------->>>Stay at Second tab (Branch Information)

            validatorInitialization();

            loadBranchNamesToComboBox(txtBankID.getText());

            try {
                load_contact_data(Integer.parseInt(bankRegistrationDAO.getBranchId(txtBankID.getText(), cmbBranchSelectForContact.getSelectionModel().getSelectedItem())));

            } catch (Exception e) {
            }

            btnNext.setText("Finish");
            btnBack.setVisible(true);
            selectionModel.select(2); //Select Bank Contact Details

        } //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Tab-Contact Information">
        else if (selectionModel.getSelectedIndex() == 2) {

            boolean isDeleteContacts = false;

            isDeleteContacts = bankRegistrationDAO.delete_AllContacts_before_insert(Integer.parseInt(bankRegistrationDAO.getBranchId(txtBankID.getText(), cmbBranchSelectForContact.getSelectionModel().getSelectedItem())));

            if (isDeleteContacts) {

                //<editor-fold defaultstate="collapsed" desc="Insert Telephone">
                Telephone telephone;
                if (tblTelephone.getItems().size() != 0) {
                    for (int i = 0; i < tblTelephone.getItems().
                            size();
                            i++) {
                        telephone
                                = (Telephone) tblTelephone.getItems().
                                get(i);

                        isBranchTelephoneInserted
                                = bankRegistrationDAO.
                                insertBranch_Telephone(
                                        Integer.parseInt(bankRegistrationDAO.getBranchId(txtBankID.getText(), cmbBranchSelectForContact.getSelectionModel().getSelectedItem())),
                                        telephone.getColContactTelephone(),
                                        telephone.getColTelephone());

                    }
                } else if (tblTelephone.getItems().size() == 0) {
                    isBranchTelephoneInserted = true;
                }

                //</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="Insert Mobile">
                if (isBranchTelephoneInserted == true) {
                    Mobile mobile;
                    if (tblMobile.getItems().size() != 0) {
                        for (int i = 0; i < tblMobile.getItems().
                                size();
                                i++) {
                            mobile
                                    = (Mobile) tblMobile.getItems().
                                    get(i);

                            isBranchMobileInserted
                                    = bankRegistrationDAO.
                                    insertBranch_Mobile(
                                            Integer.parseInt(bankRegistrationDAO.getBranchId(txtBankID.getText(), cmbBranchSelectForContact.getSelectionModel().getSelectedItem())),
                                            mobile.getColContactMobile(),
                                            mobile.getColMobile());

                        }
                    } else if (tblMobile.getItems().size() == 0) {
                        isBranchMobileInserted = true;
                    }
                }
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="Insert Fax">
                if (isBranchMobileInserted == true) {
                    Email email;
                    if (tblEmail.getItems().size() != 0) {
                        for (int i = 0; i < tblEmail.getItems().
                                size();
                                i++) {
                            email
                                    = (Email) tblEmail.getItems().
                                    get(i);

                            isBranchEmailInserted
                                    = bankRegistrationDAO.
                                    insertBranch_Email(
                                            Integer.parseInt(bankRegistrationDAO.getBranchId(txtBankID.getText(), cmbBranchSelectForContact.getSelectionModel().getSelectedItem())),
                                            email.getColContactEmail(),
                                            email.getColEmail());

                        }
                    } else if (tblEmail.getItems().size() == 0) {
                        isBranchEmailInserted = true;
                    }
                }

                //</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="Insert Fax">
                if (isBranchEmailInserted == true) {
                    Fax fax;
                    if (tblFax.getItems().size() != 0) {
                        for (int i = 0; i < tblFax.getItems().
                                size();
                                i++) {
                            fax
                                    = (Fax) tblFax.getItems().
                                    get(i);

                            isBranchFaxInserted
                                    = bankRegistrationDAO.
                                    insertBranch_Fax(
                                            Integer.parseInt(bankRegistrationDAO.getBranchId(txtBankID.getText(), cmbBranchSelectForContact.getSelectionModel().getSelectedItem())),
                                            fax.getColContactFax(),
                                            fax.getColFax());

                        }
                    } else if (tblFax.getItems().size() == 0) {
                        isBranchFaxInserted = true;
                    }
                }
//</editor-fold>

            }
            if (isBranchTelephoneInserted == true
                    && isBranchMobileInserted == true
                    && isBranchEmailInserted == true
                    && isBranchFaxInserted == true) {

                mb.ShowMessage(stage,
                        ErrorMessages.SuccesfullyCreated,
                        "Information",
                        MessageBox.MessageIcon.MSG_ICON_SUCCESS,
                        MessageBox.MessageType.MSG_OK);

                clearInput();

            } else {

                mb.ShowMessage(stage,
                        ErrorMessages.NotSuccesfullyCreated,
                        "Error",
                        MessageBox.MessageIcon.MSG_ICON_FAIL,
                        MessageBox.MessageType.MSG_OK);
            }

        }

        //</editor-fold>
    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Mouse Events">
    @FXML
    private void tblBankSearchOnMouseClicked(javafx.scene.input.MouseEvent mouseEvent) {
        validatorInitialization();
        if (mouseEvent.getClickCount() == 2) {
            Bank itemData = (Bank) tblBankSearch.getSelectionModel().
                    getSelectedItem();
            String bankId = itemData.colBankId.get();

            if (bankId != null) {
                txtBankID.setText(itemData.getColBankId());
                txtBank.setText(itemData.getColBank());
                txtBankCode.setText(itemData.getColBankCode());

                txtBankDescription.setText(bankRegistrationDAO.getBankDescription(itemData.getColBankId()));
                updateInt = 1;

                if (delete == true) {
                    btnDelete.setDisable(false);
                    btnDelete.setVisible(true);
                }

                validatorInitialization();
            }

        }

    }

    @FXML
    private void tblSearchBranchOnMouseClicked(MouseEvent event) {
        validatorInitialization();
    }

    @FXML
    private void tblTelephoneOnMouseClicked(MouseEvent event) {
    }

    @FXML
    private void tblMobileOnMouseClicked(MouseEvent event) {
    }

    @FXML
    private void tblEmailOnMouseClicked(MouseEvent event) {
    }

    @FXML
    private void tblFaxOnMouseClicked(MouseEvent event) {
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="KeyEvents">
    @FXML
    private void txtBankSearchOnKeyRelesed(KeyEvent event) {
        tableBankDataLoader(txtBankSearch.getText().trim());

    }

    @FXML
    private void txtBranchMobileOnKeyRelesed(KeyEvent event) {
    }

    @FXML
    private void txtBranchTelephoneOnKeyRelesed(KeyEvent event) {
    }

    @FXML
    private void txtBranchEmailOnKeyRelesed(KeyEvent event) {
    }

    @FXML
    private void txtBranchFaxOnKeyRelesed(KeyEvent event) {
    }

    @FXML
    void tblSearchBranchOnKeyRelesed(KeyEvent event) {

        boolean isDeleteBranch = false;
        validatorInitialization();

        try {

            boolean model = tblSearchBranch.getSelectionModel().isEmpty();
            boolean validationSupportBranchTableData = false;

            if (model == false) {

                if (event.getCode() == KeyCode.DELETE) {
                    validationSupportBranchTableData = validationSupportBranchTable.isInvalid();

                    if (validationSupportBranchTableData == true) {

                    } else if (validationSupportBranchTableData == false) {

                        MessageBox.MessageOutput option = mb.ShowMessage(stage,
                                ErrorMessages.Delete, "Information",
                                MessageBox.MessageIcon.MSG_ICON_NONE,
                                MessageBox.MessageType.MSG_YESNO);
                        if (option.equals(MessageBox.MessageOutput.MSG_YES)) {
                            isDeleteBranch = bankRegistrationDAO.delete_Branch(Integer.parseInt(tblSearchBranch.getSelectionModel().getSelectedItem().getColBranchId()));

                            if (isDeleteBranch == true) {

                                mb.ShowMessage(stage,
                                        ErrorMessages.SuccesfullyDeleted,
                                        "Information",
                                        MessageBox.MessageIcon.MSG_ICON_SUCCESS,
                                        MessageBox.MessageType.MSG_OK);

                                tableBranchDataLoader(txtBankID.getText());

                            } else {
                                mb.ShowMessage(stage, ErrorMessages.Error, "Error",
                                        MessageBox.MessageIcon.MSG_ICON_FAIL,
                                        MessageBox.MessageType.MSG_OK);
                            }
                        }

                        //---Delete Branch and related accounts
                        validatorInitialization();

                    }

                }
            }
        } catch (Exception e) {

        }
    }

    @FXML
    void txtBranchAddOnKeyRelesed(KeyEvent event) {
        validatorInitialization();
        if (txtBranchAdd.getText().length() >= 3) {
            branchDataLoader(txtBankID.getText(), txtBranchAdd.getText());
            branchTable.setItems(branchData);
            if (!branchData.isEmpty()) {

                branchPop.show(btnSearchBranch);
            }
            validatorInitialization();
        }

    }

    @FXML
    void txtAddBranchCodeOnKeyRelesed(KeyEvent event) {

        if (txtAddBranchCode.getText().length() >= 3) {
            branchCodeDataLoader(txtBankID.getText(), txtAddBranchCode.getText());
            branch_CodeTable.setItems(branch_CodeData);
            if (!branch_CodeData.isEmpty()) {

                branch_CodePop.show(btnSearchBranchCode);
            }
            validatorInitialization();
        }
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Methods">
    private void validatorInitialization() {

        validationSupportBranch.registerValidator(txtBranchAdd,
                new CustomTextFieldValidationImpl(txtBranchAdd,
                        !fav.validName(txtBranchAdd.getText()),
                        ErrorMessages.InvalidName));

        validationSupportBranchCode.registerValidator(txtAddBranchCode,
                new CustomTextFieldValidationImpl(txtAddBranchCode,
                        !fav.validName(txtAddBranchCode.getText()),
                        ErrorMessages.InvalidName));

        validationSupportBranchAccountNo.registerValidator(cmbAccountNo,
                new CustomComboboxValidationImpl(cmbAccountNo,
                        !fav.validName(cmbAccountNo.getSelectionModel().
                                getSelectedItem()),
                        ErrorMessages.Error));

        validationSupportBank.registerValidator(txtBank,
                new CustomTextFieldValidationImpl(txtBank,
                        !fav.validName(txtBank.getText()),
                        ErrorMessages.InvalidName));

        validationSupportBankID.registerValidator(txtBankID,
                new CustomTextFieldValidationImpl(txtBankID,
                        !fav.validName(txtBankID.getText()),
                        ErrorMessages.InvalidId));

        validationSupportBranchTable.registerValidator(tblSearchBranch,
                new CustomTableViewValidationImpl(tblSearchBranch,
                        !fav.validTableView(tblSearchBranch),
                        ErrorMessages.EmptyListView));

        //validation for Telephone
//        validateTelephone.registerValidator(txtBranchTelephone,
//                new CustomTextFieldValidationImpl(txtBranchTelephone,
//                        !fav.isValidUniqueTelephoneNumber(tblTelephone.getC,
//                                txtBranchTelephone.getText().trim()),
//                        ErrorMessages.InvalidTelephoneOrDuplicate));
//
//        validateMobile.registerValidator(txtMobile,
//                new CustomTextFieldValidationImpl(txtMobile,
//                        !fav.isValidUniqueTelephoneNumber(lstMobile, txtMobile.
//                                getText().trim()),
//                        ErrorMessages.InvalidMobileOrDuplicate));
//
//        validateEmail.registerValidator(txtEmail,
//                new CustomTextFieldValidationImpl(txtEmail,
//                        !fav.isValidUniqueEmail(lstEmail, txtEmail.getText().
//                                trim()),
//                        ErrorMessages.InvalidEmailAddressOrDuplicate));
    }

    private void hidePrinterRemark() {
        validatorInitialization();
        btnBack.setVisible(false);
        loadAccountTypeToComboBox();

        txtBankID.setText(bankRegistrationDAO.generateBankId());
        tableBankDataLoader(txtBankSearch.getText().trim());

        btnDelete.setDisable(true);
        btnDelete.setVisible(false);

        validatorInitialization();
    }

    private void HideAddContactButtons() {
        //hide add contact buttons
        btnAddTelephone.setVisible(false);
        btnAddTelephone.setMinWidth(0.0);
        btnAddTelephone.setPrefWidth(0.0);

        btnAddMobile.setVisible(false);
        btnAddMobile.setMinWidth(0.0);
        btnAddMobile.setPrefWidth(0.0);

        btnAddEmail.setVisible(false);
        btnAddEmail.setMinWidth(0.0);
        btnAddEmail.setPrefWidth(0.0);

        btnAddFax.setVisible(false);
        btnAddFax.setMinWidth(0.0);
        btnAddFax.setPrefWidth(0.0);

        txtBranchTelephone.setPrefWidth(100.00);
        txtBranchMobile.setPrefWidth(100.00);
        txtBranchEmail.setPrefWidth(100.00);
        txtBranchFax.setPrefWidth(100.00);
    }

    private void hideRemoveContactButtons() {
        btnRemoveTelephone.setVisible(false);
        btnRemoveMobile.setVisible(false);
        btnRemoveEmail.setVisible(false);
        btnRemoveFax.setVisible(false);
    }

    private void tableBankDataLoader(String keyword) {
        bankSearchData.clear();

        ArrayList<ArrayList<String>> custInfo
                = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> list = bankRegistrationDAO.
                searchBankDetails(keyword);

        if (list != null) {

            for (int i = 0; i < list.size(); i++) {

                custInfo.add(list.get(i));
            }

            if (custInfo != null && custInfo.size() > 0) {
                for (int i = 0; i < custInfo.size(); i++) {

                    bank = new Bank();

                    bank.colBankId.setValue(custInfo.get(i).get(0));
                    bank.colBank.setValue(custInfo.get(i).get(1));
                    bank.colBankCode.setValue(custInfo.get(i).get(2));

                    bankSearchData.add(bank);
                }
            }

        }

    }

    private void tableBranchDataLoader(String bankID) {
        branchSearchData.clear();

        ArrayList<ArrayList<String>> custInfo
                = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> list = bankRegistrationDAO.
                searchBranchDetails(bankID);

        if (list != null) {

            for (int i = 0; i < list.size(); i++) {

                custInfo.add(list.get(i));
            }

            if (custInfo != null && custInfo.size() > 0) {
                for (int i = 0; i < custInfo.size(); i++) {

                    branch = new Branch();

                    branch.colBranchId.setValue(custInfo.get(i).get(0));
                    branch.colBranch.setValue(custInfo.get(i).get(1));
                    branch.colBranchCode.setValue(custInfo.get(i).get(2));
                    branch.colAccountNo.setValue(custInfo.get(i).get(3));

                    branchSearchData.add(branch);
                }
            }

        }

    }

    private void loadBranchNamesToComboBox(String bankId) {
        cmbBranchSelectForContact.setItems(null);
        ArrayList<String> professionList = null;
        professionList = bankRegistrationDAO.loadBranchNames(bankId);
        if (professionList != null) {
            try {
                ObservableList<String> List = FXCollections.observableArrayList(
                        professionList);
                cmbBranchSelectForContact.setItems(List);
                cmbBranchSelectForContact.setValue(List.get(0));
            } catch (Exception e) {

            }

        }
    }

    private void loadAccountNumberToComboBox() {
        cmbAccountNo.setItems(null);
        ArrayList<String> professionList = null;
        professionList = bankRegistrationDAO.loadAccountNumbers(
                Integer.parseInt(selectedBranchId),
                bankRegistrationDAO.getAccountTypeId(cmbAccountType.getSelectionModel().getSelectedItem()));
        if (professionList != null) {
            try {
                ObservableList<String> List = FXCollections.observableArrayList(
                        professionList);
                cmbAccountNo.setItems(List);
                cmbAccountNo.setValue(List.get(0));
            } catch (Exception e) {

            }

        }
    }

    private void load_contact_data(int branchId) {

        //<editor-fold defaultstate="collapsed" desc="Load Telephone">
        telephoneData.clear();
        ArrayList<ArrayList<String>> telephoneList
                = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> tpList = bankRegistrationDAO.
                search_All_Contact(branchId, "TELEPHONE");

        if (tpList != null) {

            for (int i = 0; i < tpList.size(); i++) {

                telephoneList.add(tpList.get(i));
            }

            if (telephoneList != null && telephoneList.size() > 0) {
                for (int i = 0; i < telephoneList.size(); i++) {

                    telephone = new Telephone();

                    telephone.colContactTelephone.setValue(telephoneList.get(i).get(0));
                    telephone.colTelephone.setValue(telephoneList.get(i).get(1));

                    telephoneData.add(telephone);
                }

            }
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Load Mobile">
        mobileData.clear();
        ArrayList<ArrayList<String>> mobileList
                = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> mbList = bankRegistrationDAO.
                search_All_Contact(branchId, "MOBILE");

        if (mbList != null) {

            for (int i = 0; i < mbList.size(); i++) {

                mobileList.add(mbList.get(i));
            }

            if (mobileList != null && mobileList.size() > 0) {
                for (int i = 0; i < mobileList.size(); i++) {

                    mobile = new Mobile();

                    mobile.colContactMobile.setValue(mobileList.get(i).get(0));
                    mobile.colMobile.setValue(mobileList.get(i).get(1));

                    mobileData.add(mobile);
                }

            }
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Load Email">
        emailData.clear();
        ArrayList<ArrayList<String>> emailList
                = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> eList = bankRegistrationDAO.
                search_All_Contact(branchId, "EMAIL");

        if (eList != null) {

            for (int i = 0; i < eList.size(); i++) {

                emailList.add(eList.get(i));
            }

            if (emailList != null && emailList.size() > 0) {
                for (int i = 0; i < emailList.size(); i++) {

                    email = new Email();

                    email.colContactEmail.setValue(emailList.get(i).get(0));
                    email.colEmail.setValue(emailList.get(i).get(1));

                    emailData.add(email);
                }

            }
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Load Fax">
        faxData.clear();
        ArrayList<ArrayList<String>> faxList
                = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> fList = bankRegistrationDAO.
                search_All_Contact(branchId, "FAX");

        if (fList != null) {

            for (int i = 0; i < fList.size(); i++) {

                faxList.add(fList.get(i));
            }

            if (faxList != null && faxList.size() > 0) {
                for (int i = 0; i < faxList.size(); i++) {

                    fax = new Fax();

                    fax.colContactFax.setValue(faxList.get(i).get(0));
                    fax.colFax.setValue(faxList.get(i).get(1));

                    faxData.add(fax);
                }

            }
        }
        //</editor-fold>

    }

//</editor-fold>


    public class Bank {

        public SimpleStringProperty colBankId = new SimpleStringProperty(
                "tcBankID");
        public SimpleStringProperty colBank = new SimpleStringProperty(
                "tcBank");
        public SimpleStringProperty colBankCode = new SimpleStringProperty("tcBankCode");

        public String getColBankId() {
            return colBankId.get();
        }

        public String getColBank() {
            return colBank.get();
        }

        public String getColBankCode() {
            return colBankCode.get();
        }

    }

    public class Branch {

        public SimpleStringProperty colBranchId = new SimpleStringProperty(
                "tcBranchId");
        public SimpleStringProperty colBranch = new SimpleStringProperty(
                "tcBranch");
        public SimpleStringProperty colBranchCode = new SimpleStringProperty(
                "tcBranchCode");
        public SimpleStringProperty colAccountNo = new SimpleStringProperty("tcAccountNo");

        public String getColBranchId() {
            return colBranchId.get();
        }

        public String getColBranch() {
            return colBranch.get();
        }

        public String getColBranchCode() {
            return colBranchCode.get();
        }

        public String getColAccountNo() {
            return colAccountNo.get();
        }

    }

    public class Telephone {

        public SimpleStringProperty colContactTelephone = new SimpleStringProperty(
                "tcTelephoneContact");
        public SimpleStringProperty colTelephone = new SimpleStringProperty(
                "tcTelephone");

        public String getColContactTelephone() {
            return colContactTelephone.get();
        }

        public String getColTelephone() {
            return colTelephone.get();
        }

    }

    public class Mobile {

        public SimpleStringProperty colContactMobile = new SimpleStringProperty(
                "tcMobileContact");
        public SimpleStringProperty colMobile = new SimpleStringProperty(
                "tcMobile");

        public String getColContactMobile() {
            return colContactMobile.get();
        }

        public String getColMobile() {
            return colMobile.get();
        }

    }

    public class Email {

        public SimpleStringProperty colContactEmail = new SimpleStringProperty(
                "tcEmailContact");
        public SimpleStringProperty colEmail = new SimpleStringProperty(
                "tcEmail");

        public String getColContactEmail() {
            return colContactEmail.get();
        }

        public String getColEmail() {
            return colEmail.get();
        }

    }

    public class Fax {

        public SimpleStringProperty colContactFax = new SimpleStringProperty(
                "tcFaxContact");
        public SimpleStringProperty colFax = new SimpleStringProperty(
                "tcFax");

        public String getColContactFax() {
            return colContactFax.get();
        }

        public String getColFax() {
            return colFax.get();
        }

    }

}
