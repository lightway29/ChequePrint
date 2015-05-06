/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cheque.main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Miren
 */
public class Home extends AnchorPane implements Initializable {

    //<editor-fold defaultstate="collapsed" desc="initcomponents">
    @FXML
    private Button PrinterConfig;

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnClose;

    @FXML
    private Button ChequeRegister;

    @FXML
    private Button ChequePrint;

    @FXML
    private Button BankRegistration;
    
    @FXML
    private Button Settings;
//</editor-fold>

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    
    

    @FXML
    void btnLogOutOnAction(ActionEvent event) {

    }

    @FXML
    void btnCloseOnAction(ActionEvent event) {

    }

    @FXML
    void ChequePrintOnAction(ActionEvent event) {

    }

    @FXML
    void BankRegistrationOnAction(ActionEvent event) {

    }

    @FXML
    void ChequeRegisterOnAction(ActionEvent event) {

    }

    @FXML
    void SettingsOnAction(ActionEvent event) {

    }

    @FXML
    void PrinterConfigOnAction(ActionEvent event) {

    }

}
