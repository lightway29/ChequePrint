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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Miren
 */
public class Activation extends AnchorPane implements
        Initializable {

    //<editor-fold defaultstate="collapsed" desc="initcomponents">
    @FXML
    private TextArea txtActivationCode;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnActivate;
//</editor-fold>


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    
    @FXML
    void btnActivateOnAction(ActionEvent event) {

    }

    @FXML
    void btnCloseOnAction(ActionEvent event) {

    }

    
}
