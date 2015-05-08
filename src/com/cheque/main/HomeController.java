/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cheque.main;

import com.cheque.ui.FxmlUiLauncher;
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
public class HomeController extends AnchorPane implements Initializable {

    //<editor-fold defaultstate="collapsed" desc="initcomponents">
    @FXML
    private Button PrinterConfig;

    @FXML
    private Button btnLogOut;

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

       
        btnLogOut.getStyleClass().add("button-clrx-darkorange");

       ChequePrint.getStyleClass().add("button-clrx-red");
       BankRegistration.getStyleClass().add("button-clrx-dodgerblue");
       ChequeRegister.getStyleClass().add("button-clrx-coral");
       Settings.getStyleClass().add("button-clrx-darkcyan");
       PrinterConfig.getStyleClass().add("button-clrx-darkgray");
       
    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) {

        System.exit(1);

    }

    @FXML
    void ChequePrintOnAction(ActionEvent event) {

        FxmlUiLauncher.launchOnNewStageWait(
                "/com/cheque/main/ChequePrint.fxml",
                "Cheque Print", null);

    }

    @FXML
    void BankRegistrationOnAction(ActionEvent event) {

    }

    @FXML
    void ChequeRegisterOnAction(ActionEvent event) {

    }

    @FXML
    void SettingsOnAction(ActionEvent event) {
        FxmlUiLauncher.launchOnNewStageWait(
                "/com/cheque/main/DefaultSettings.fxml",
                "Default Settings", null);
    }

    @FXML
    void PrinterConfigOnAction(ActionEvent event) {

    }

}
