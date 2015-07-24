/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cheque.main;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRReport;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JRViewer;

/**
 *
 * @author lightway
 */
public class ChequeDesignerController extends AnchorPane implements Initializable{
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnClose;
    @FXML
    private Button btnSearchRoom;
    @FXML
    private Button btnRefresh;
    @FXML
    private Pane paneDisplay;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        SwingNode swingNode = new SwingNode();
        
//       paneDisplay.sets
         paneDisplay.getChildren().add(swingNode); // Adding swing node
//         .setScene(new Scene(paneDisplay, 100, 50));
         
          SwingUtilities.invokeLater(new Runnable() {
             @Override
             public void run() {
//                 JPanel panel = new JPanel();
//                 panel.setBounds(0, 0, 500, 500);
                 String path = ".//Reports//HNBCheqe.jasper";
                 
                 
                 JasperPrint jasperPrint;
                 try {
                     jasperPrint = JasperFillManager.fillReport(path, null);
//                     JRViewer jrViewer = new JRViewer(jasperPrint);
//                     jrViewer.setBounds(0,
//                             0, 400, 400);
                     swingNode.setContent(new JRViewer(jasperPrint));
//                     panel.add(new JRViewer(jasperPrint));
//                     swingNode.setContent(panel);
                 } catch (JRException ex) {
                     ex.printStackTrace();
                 }
                 
                 
//                 
             }
         });
        
    }

    @FXML
    private void btnSaveOnAction(ActionEvent event) {
    }

    @FXML
    private void btnCancelOnAction(ActionEvent event) {
    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
    }

    @FXML
    private void btnSearchRoomOnAction(ActionEvent event) {
    }

    @FXML
    private void btnRefreshOnAction(ActionEvent event) {
    }
    
}
