/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cheque.main;

import com.cheque.mainDAO.ChequePrintLogDAO;
import com.cheque.ui.StagePassable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.batik.i18n.Localizable;

/**
 *
 * @author Miren
 */
public class ChequePrintLogController extends AnchorPane implements
        Initializable, StagePassable {

    @FXML
    private Button btnClose;

    @FXML
    private TextField txtSearch;

    @FXML
    private TableColumn<ChequeList, String> tcAmount;

    @FXML
    private TableColumn<ChequeList, String> tcProfile;

    @FXML
    private TableColumn<ChequeList, String> tcTimeStamp;

    @FXML
    private TableColumn<ChequeList, String> tcDate;

    @FXML
    private TableColumn<ChequeList, String> tcPay;

    @FXML
    private TableColumn<ChequeList, String> tcId;

    @FXML
    private TableColumn<ChequeList, String> tcDescription;

    @FXML
    private TableColumn<ChequeList, String> tcCrossCheque;

    @FXML
    private TableView<String> tblChequeLog;

    private ObservableList tableItemData = FXCollections.observableArrayList();
    ChequePrintLogDAO chequePrintLogDAO = new ChequePrintLogDAO();
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tcAmount.setCellValueFactory(new PropertyValueFactory<>(
                "colAmount"));
        tcProfile.setCellValueFactory(new PropertyValueFactory<>(
                "colProfile"));
        tcTimeStamp.setCellValueFactory(new PropertyValueFactory<>(
                "colTimeStamp"));
        tcDate.setCellValueFactory(new PropertyValueFactory<>(
                "colDate"));
        tcPay.setCellValueFactory(new PropertyValueFactory<>(
                "colPay"));

        tcId.setCellValueFactory(new PropertyValueFactory<>(
                "colId"));
        tcDescription.setCellValueFactory(new PropertyValueFactory<>(
                "colDescription"));
        tcCrossCheque.setCellValueFactory(new PropertyValueFactory<>(
                "colCrossCheque"));
        tblChequeLog.setItems(tableItemData);
        setTableValues();

    }

    private void setTableValues() {

        ArrayList<ArrayList<String>> useItems
                = new ArrayList<ArrayList<String>>();

        String value = "";
        if (txtSearch.getText().equalsIgnoreCase(null)) {
            value = "";
        } else {
            value = txtSearch.getText().toString();
        }

        ArrayList<ArrayList<String>> list = chequePrintLogDAO.
                getSearchValues(value);

        if (list != null) {

            for (int i = 0; i < list.size(); i++) {

                useItems.add(list.get(i));
            }

            if (useItems != null && useItems.size() > 0) {
                for (int i = 0; i < useItems.size(); i++) {

                    ChequeList chequeList
                            = new ChequeList();

                    chequeList.colAmount.setValue(useItems.get(i).get(0));
                    chequeList.colCrossCheque.setValue(useItems.get(i).get(1));
                    chequeList.colDate.setValue(useItems.get(i).get(2));
                    chequeList.colDescription.setValue(useItems.get(i).get(3));
                    chequeList.colId.setValue(useItems.get(i).get(4));
                    chequeList.colPay.setValue(useItems.get(i).get(5));
                    chequeList.colProfile.setValue(useItems.get(i).get(6));
                    chequeList.colTimeStamp.setValue(useItems.get(i).get(7));

                    tableItemData.add(chequeList);
                }
            }
        }
    }

    @Override
    public void setStage(Stage stage, Object[] obj) {
        this.stage = stage;
        tableItemData.clear();
        setTableValues();
    }

    @FXML
    void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    void txtSearchOnKeyReleased(KeyEvent event) {
        tableItemData.clear();
        setTableValues();
    }

    public class ChequeList {

        public SimpleStringProperty colAmount = new SimpleStringProperty(
                "tcAmount");
        public SimpleStringProperty colProfile = new SimpleStringProperty(
                "tcProfile");
        public SimpleStringProperty colTimeStamp = new SimpleStringProperty(
                "tcTimeStamp");
        public SimpleStringProperty colDate = new SimpleStringProperty(
                "tcDate");
        public SimpleStringProperty colPay = new SimpleStringProperty(
                "tcPay");
        public SimpleStringProperty colId = new SimpleStringProperty(
                "tcId");
        public SimpleStringProperty colDescription = new SimpleStringProperty(
                "tcDescription");
        public SimpleStringProperty colCrossCheque = new SimpleStringProperty(
                "tcCrossCheque");

        public String getColAmount() {
            return colAmount.get();
        }

        public String getColProfile() {
            return colProfile.get();
        }

        public String getColTimeStamp() {
            return colTimeStamp.get();
        }

        public String getColDate() {
            return colDate.get();
        }

        public String getColPay() {
            return colPay.get();
        }

        public String getColId() {
            return colId.get();
        }

        public String getColDescription() {
            return colDescription.get();
        }

        public String getColCrossCheque() {
            return colCrossCheque.get();
        }
    }

}
