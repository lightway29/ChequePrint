/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cheque.main;

import com.cheque.ui.FxmlUiLauncher;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
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

    public static Connection con = com.cheque.database.DatabaseConnection.Connect();
    @FXML
    private ImageView imgViewChequeManager;

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
        FxmlUiLauncher.launchOnNewStageWait(
                "/com/cheque/main/BankRegistration.fxml",
                "Bank Registration", null);
    }

    @FXML
    void ChequeRegisterOnAction(ActionEvent event) {
        FxmlUiLauncher.launchOnNewStageWait(
                "/com/cheque/main/ChequePrintLog.fxml",
                "Cheque Register", null);
    }

    @FXML
    void SettingsOnAction(ActionEvent event) {
        FxmlUiLauncher.launchOnNewStageWait(
                "/com/cheque/main/DefaultSettings.fxml",
                "Default Settings", null);
    }

    @FXML
    void PrinterConfigOnAction(ActionEvent event) {

        FxmlUiLauncher.launchOnNewStageWait(
                "/com/cheque/main/ChequeDesigner.fxml",
                "Printer Config", null);

    }

    @FXML
    private void imgViewChequeManagerOnDragOver(DragEvent event) {

    }

    @FXML
    private void imgViewChequeManagerOnMouseDragOver(MouseDragEvent event) {

    }

    @FXML
    private void imgViewChequeManagerOnDragEntered(DragEvent event) {

    }

    @FXML
    private void imgViewChequeManagerOnDragDone(DragEvent event) {

    }

    @FXML
    private void imgViewChequeManagerOnMouseDragged(MouseEvent event) {

    }

    @FXML
    private void ChequePrintOnMouseDragged(MouseEvent event) {

    }

}
