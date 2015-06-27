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
import javafx.scene.control.Control;
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
import org.controlsfx.validation.Validator;

/**
 * FXML Controller class
 *
 * @author Sumudu De Zoysa
 */
public class BankRegistrationController extends AnchorPane implements
        Initializable, Validatable, StagePassable {

    //<editor-fold defaultstate="collapsed" desc="InitComponents">
    @FXML
    private Button btnClose;

    @FXML
    private TextField txtBranch;

    @FXML
    private Button btnBankSearch;

    @FXML
    private ImageView imgNext;

    @FXML
    private TextField txtBranchCode;

    @FXML
    private TextField txtBankSearch;

    @FXML
    private TextField txtBankID;

    @FXML
    private TableColumn<Bank, String> tcAccountNo;

    @FXML
    private Label lblSearchBank;

    @FXML
    private TableColumn<Bank, String> tcBank;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnDelete;

    @FXML
    private TextField txtBank;

    @FXML
    private TableView<Bank> tblBankSearch;

    @FXML
    private Insets x31;

    @FXML
    private Insets x3;

    @FXML
    private TextField txtAccountNo;

    @FXML
    private TableColumn<Bank, String> tcBankID;

    @FXML
    private TableColumn<Bank, String> tcBranchCode;
    
    @FXML
    private Button btnRefresh;
    
    //</editor-fold>
    private Bank bank = new Bank();
    private Stage stage;
    private MessageBox mb;
    private ObservableList bankSearchData = FXCollections.observableArrayList();


    //Validations--------------
    private final ValidationSupport validationSupport = new ValidationSupport();
    private final FormatAndValidate fav = FormatAndValidate.getInstance();
    
    BankRegistrationDAO bankRegistrationDAO = new BankRegistrationDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mb = SimpleMessageBoxFactory.createMessageBox();

        //------------------Table Bank Search
        tcBankID.setCellValueFactory(new PropertyValueFactory<Bank, String>(
                "colBankId"));

        tcBank.setCellValueFactory(new PropertyValueFactory<Bank, String>(
                "colBank"));

        tcBranchCode.setCellValueFactory(new PropertyValueFactory<Bank, String>(
                "colBranchCode"));
        
        tcAccountNo.setCellValueFactory(new PropertyValueFactory<Bank, String>(
                "colAccountNo"));

        tblBankSearch.setItems(bankSearchData);
        
        txtBankID.setText(bankRegistrationDAO.generateBankId());
        
        //<editor-fold defaultstate="collapsed" desc="validation">
        validationSupport.registerValidator(txtBank,
                Validator.combine(
                        Validator.createEmptyValidator("Empty"),
                        (Control c, String newValue) -> ValidationResult.
                        fromErrorIf(txtBank,
                                "Invalid Banck",
                                !fav.validName(txtBank.getText()))));

        validationSupport.registerValidator(txtBranchCode,
                Validator.combine(
                        Validator.createEmptyValidator("Empty"),
                        (Control c, String newValue) -> ValidationResult.
                        fromErrorIf(txtBranchCode,
                                "Invalid Branch Code",
                                !fav.validName(txtBranchCode.getText()))));
        
        validationSupport.registerValidator(txtBranch,
                Validator.combine(
                        Validator.createEmptyValidator("Empty"),
                        (Control c, String newValue) -> ValidationResult.
                        fromErrorIf(txtBranch,
                                "Invalid Branch",
                                !fav.validName(txtBranch.getText()))));
        
        validationSupport.registerValidator(txtAccountNo,
                Validator.combine(
                        Validator.createEmptyValidator("Empty"),
                        (Control c, String newValue) -> ValidationResult.
                        fromErrorIf(txtAccountNo,
                                "Invalid Account",
                                !fav.validName(txtAccountNo.getText()))));
//</editor-fold>

    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public void clearInput() {
        txtBankID.setText(bankRegistrationDAO.generateBankId());

    }

    @Override
    public void clearValidations() {

    }

    @Override
    public void setStage(Stage stage, Object[] obj) {
        this.stage = stage;
        
    }
    
    @FXML
    void txtBankIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtBankOnAction(ActionEvent event) {

    }

    @FXML
    void txtBranchOnAction(ActionEvent event) {

    }

    @FXML
    void txtBranchCodeOnAction(ActionEvent event) {

    }

    @FXML
    void txtBankSearchOnAction(ActionEvent event) {

    }

    @FXML
    void txtBankSearchOnKeyRelesed(ActionEvent event) {

    }

    @FXML
    void tblBankSearchOnMouseClicked(ActionEvent event) {

    }

    @FXML
    void txtAccountNoOnAction(ActionEvent event) {

    }
    
    @FXML
    void btnRefreshSearchOnAction(ActionEvent event) {

    }
    
    @FXML
    void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    void btnBankSearchOnAction(ActionEvent event) {

    }
    
    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    public class Bank {

        public SimpleStringProperty colBankID = new SimpleStringProperty(
                "tcBankID");
        public SimpleStringProperty colBank = new SimpleStringProperty(
                "tcBank");
        public SimpleStringProperty colBranchCode = new SimpleStringProperty(
                "tcBranchCode");
        public SimpleStringProperty colAccountNo = new SimpleStringProperty(
                "tcAccountNo");

        public String getColBankId() {
            return colBankID.get();
        }
        
        public String getColBank() {
            return colBank.get();
        }

        public String getColBranchCode() {
            return colBranchCode.get();
        }

        public String getColAccountNo() {
            return colAccountNo.get();
        }

    }
    
}
