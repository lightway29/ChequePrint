/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cheque.main;

import com.cheque.mainDAO.DefaultSettingsDAO;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Miren
 */
public class DefaultSettingsController extends AnchorPane implements
        Initializable {

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
    
    @FXML
    private ComboBox<String> cmbProfile;
//</editor-fold>

    DefaultSettingsDAO defaultSettingsDAO = new DefaultSettingsDAO();
    private String defaultProfile = "SET0001";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadTableToCombobox();
        loadSettings();
    }
    
    private void loadTableToCombobox() {

        cmbProfile.setItems(null);
        ArrayList<String> professionList = null;
        professionList = defaultSettingsDAO.loadTable();
        if (professionList != null) {
            try {
                ObservableList<String> List = FXCollections.observableArrayList(
                        professionList);
                cmbProfile.setItems(List);
                cmbProfile.setValue(List.get(0));
            } catch (Exception e) {

            }

        }

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

        boolean updateStatus = defaultSettingsDAO.updateSettings(
                chkCrossCheque.isSelected(),
                chkprintPreview.isSelected(),
                chkDateWithYear.isSelected(),
                chkPrint.isSelected(),
                "SET0001",
                cmbProfile.getValue());
        if (updateStatus) {

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Successfully Saved.");

            alert.showAndWait();

        }

    }

    @FXML
    void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    //<editor-fold defaultstate="collapsed" desc="Method">
    private void loadSettings() {

        ArrayList<Boolean> list = null;
        list = defaultSettingsDAO.loadSettings(defaultProfile);
        if (list != null) {
            try {

                chkCrossCheque.setSelected(list.get(0));
                chkprintPreview.setSelected(list.get(1));
                chkDateWithYear.setSelected(list.get(2));
                chkPrint.setSelected(list.get(3));
                cmbProfile.setValue(defaultSettingsDAO.loadSetting(defaultProfile));

            } catch (Exception e) {

            }

        }

    }

//</editor-fold>
}
