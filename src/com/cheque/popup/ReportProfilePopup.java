/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cheque.popup;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 *
 * @author lightway
 */
public class ReportProfilePopup {
    
    public SimpleStringProperty colProfileId = new SimpleStringProperty("tcProfileId");
    public SimpleStringProperty colProfileName = new SimpleStringProperty("tcProfileName");


    public String getColProfileId() {
        return colProfileId.get();
    }

    public String getColProfileName() {
        return colProfileName.get();
    }
    
    
  
    public TableView tableViewLoader(ObservableList observableList) {
        TableView tableView = new TableView();

        TableColumn tcProfileId = new TableColumn("Profile ID");
        tcProfileId.setMinWidth(100);
        tcProfileId.setCellValueFactory(
                new PropertyValueFactory<>("colProfileId"));

    TableColumn tcProfileName = new TableColumn("Profile Name");
        tcProfileName.setMinWidth(145);
        tcProfileName.setCellValueFactory(
                new PropertyValueFactory<>("colProfileName"));

      
        tableView.setItems(observableList);
        tableView.getColumns().addAll(tcProfileId,tcProfileName);

        return tableView;
    }
    
}
