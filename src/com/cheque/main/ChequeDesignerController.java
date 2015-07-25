/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cheque.main;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.File;
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
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRReport;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignTextField;
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
    private TextField txtRupeesY;
    @FXML
    private TextField txtRupeesX;
    @FXML
    private Slider sdRupeesY;
    @FXML
    private Slider sdRupeesX;

    int pageWidth = 0;
    int pageHeight = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadReport("HNBCheqe");
        sdRupeesX.setValue(0);
        sdRupeesY.setValue(0);
//        sdRupeesX.setMax(pageWidth);
        System.out.println("Height : " + pageHeight);

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

        updateReport("HNBCheqe", Integer.parseInt(txtRupeesX.getText()),
                Integer.parseInt(txtRupeesY.getText()));
    }

    @FXML
    private void sdRupeesXOnDragDetected(MouseEvent event) {

        System.out.println("Y");

//        txtRupeesX.setText(sdRupeesX.getValue() + "");
//        txtRupeesY.setText(sdRupeesY.getValue() + "");
//
//        updateReport("HNBCheqe", Integer.parseInt(txtRupeesX.getText()),
//                Integer.parseInt(txtRupeesY.getText()));
    }

    @FXML
    private void sdRupeesYOnMouseDragged(MouseEvent event) {

        String value = String.valueOf(sdRupeesY.getValue());
        txtRupeesY.setText(value.split("\\.")[0]);
        
        updateReport("HNBCheqe", Integer.parseInt(txtRupeesX.getText()),
                Integer.parseInt(txtRupeesY.getText()));

    }

    @FXML
    private void sdRupeesYOnMouseClicked(MouseEvent event) {

        String value = String.valueOf(sdRupeesY.getValue());
        txtRupeesY.setText(value.split("\\.")[0]);
        
        updateReport("HNBCheqe", Integer.parseInt(txtRupeesX.getText()),
                Integer.parseInt(txtRupeesY.getText()));

    }

    @FXML
    private void sdRupeesXOnMouseClicked(MouseEvent event) {

       String value = String.valueOf(sdRupeesX.getValue());
        txtRupeesX.setText(value.split("\\.")[0]);
        
        updateReport("HNBCheqe", Integer.parseInt(txtRupeesX.getText()),
                Integer.parseInt(txtRupeesY.getText()));

    }

    @FXML
    private void sdRupeesXOnMouseDragged(MouseEvent event) {

        String value = String.valueOf(sdRupeesX.getValue());
        txtRupeesX.setText(value.split("\\.")[0]);
        
        updateReport("HNBCheqe", Integer.parseInt(txtRupeesX.getText()), Integer.parseInt(txtRupeesY.getText()));

    }

    //<editor-fold defaultstate="collapsed" desc="Methods">
    private void loadReport(String reportName) {

        String path = ".//Reports//" + reportName + ".jasper";

        SwingNode swingNode = new SwingNode();

        paneDisplay.getChildren().add(swingNode); // Adding swing node

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                JasperPrint jasperPrint;
                try {
                    jasperPrint = JasperFillManager.fillReport(path, null);
                    JRViewer jr = new JRViewer(jasperPrint);
                    Dimension d = new Dimension(880, 420);

                    pageWidth = jasperPrint.getPageWidth();
                    pageHeight = jasperPrint.getPageHeight();
                    
                    sdRupeesY.setMax(pageHeight);
                    sdRupeesX.setMax(pageWidth);

                    jr.setPreferredSize(d);

                    System.out.println("X - " + jr.getPreferredSize().
                            getHeight() + " Y - " + jr.getAlignmentY());

                    swingNode.setContent(jr);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    private void updateReport(String reportName, int amountInWordsX,
            int amountInWordsY) {

        String path = ".//Reports//" + reportName + ".jrxml";
        if (new File(path).exists()) {

            try {

                JasperDesign d = JRXmlLoader.load(path);
                JRElement[] field = d.getSummary().getElements();

                System.out.println("Count : " + field.length);

                JRElement rtxtCash = field[0];//Cash

                //Amount In Words Field
                JRDesignTextField rtxtAmountInWords = (JRDesignTextField) d.
                        getSummary().getElementByKey(
                                "rtxtAmountInWords");

                rtxtAmountInWords.setX(amountInWordsX);
                rtxtAmountInWords.setY(amountInWordsY);

                System.out.println("X : " + rtxtAmountInWords.getX() + " - Y : "
                        + rtxtAmountInWords.getY());

                JRReport jRReport = d;

                JasperCompileManager.writeReportToXmlFile(jRReport, path);
                JasperCompileManager.compileReportToFile(path);

                loadReport(reportName);

            } catch (JRException ex) {
                ex.printStackTrace();
            }
        }

    }

//</editor-fold>
}
