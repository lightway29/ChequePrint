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


public class BranchPopUp {

    public SimpleStringProperty colId = new SimpleStringProperty("tcId");

    public SimpleStringProperty colBankId = new SimpleStringProperty("tcBankId");
    public SimpleStringProperty colBank = new SimpleStringProperty("tcBank");
    public SimpleStringProperty colBranchId = new SimpleStringProperty("tcBranchId");
    public SimpleStringProperty colBranch = new SimpleStringProperty("tcBranch");

    public String getColId() {
        return colId.get();
    }

    
    
    public String getColBankId() {
        return colBankId.get();
    }

    public String getColBank() {
        return colBank.get();
    }

    public String getColBranchId() {
        return colBranchId.get();
    }

    public String getColBranch() {
        return colBranch.get();
    }

    public TableView tableViewLoader(ObservableList observableList) {
        TableView tableView = new TableView();

        TableColumn tcBankId = new TableColumn("Bank ID");
        tcBankId.setMinWidth(100);
        tcBankId.setCellValueFactory(
                new PropertyValueFactory<>("colBankId"));

        TableColumn tcBank = new TableColumn("Bank");
        tcBank.setMinWidth(100);
        tcBank.setCellValueFactory(
                new PropertyValueFactory<>("colBank"));

        TableColumn tcBranchId = new TableColumn("Branch Code");
        tcBranchId.setMinWidth(100);
        tcBranchId.setCellValueFactory(
                new PropertyValueFactory<>("colBranchId"));

        TableColumn tcBranch = new TableColumn("Branch");
        tcBranch.setMinWidth(100);
        tcBranch.setCellValueFactory(
                new PropertyValueFactory<>("colBranch"));

        tableView.setItems(observableList);
        tableView.getColumns().addAll(tcBankId, tcBank, tcBranchId, tcBranch);

        return tableView;
    }
}
