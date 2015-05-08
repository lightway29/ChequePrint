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
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Miren
 */
public class DefaultSettingsController extends AnchorPane implements Initializable {

    //<editor-fold defaultstate="collapsed" desc="initcomponents">
    @FXML
    private Button btnClose;

    @FXML
    private Button btnSave;

    @FXML
    private CheckBox chkCrossCheque;

    @FXML
    private CheckBox chkDateWithYear;

    @FXML
    private CheckBox chkPrint;

    @FXML
    private CheckBox chkprintPreview;
//</editor-fold>

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void btnCloseOnAction(ActionEvent event) {

    }

}
