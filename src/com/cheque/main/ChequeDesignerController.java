/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cheque.main;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.InputStream;
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
import javafx.stage.Stage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRReport;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author lightway
 */
public class ChequeDesignerController extends AnchorPane implements
        Initializable {

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

    Stage stage;
    @FXML
    private AnchorPane mainAnchorPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String path = ".//Reports//HNBCheqe.jasper";
        
        SwingNode swingNode = new SwingNode();

        paneDisplay.getChildren().add(swingNode); // Adding swing node
        
 

  

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                JasperPrint jasperPrint;
                try {
                    jasperPrint = JasperFillManager.fillReport(path, null);
                    JRViewer jr = new JRViewer(jasperPrint);
                    Dimension d  = new Dimension(655, 250);
                    
                    jr.setPreferredSize(d);
                    
                    System.out.println("X - "+jr.getPreferredSize().getHeight()+" Y - "+ jr.getAlignmentY());

                    swingNode.setContent(jr);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }                
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
