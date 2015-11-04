/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cheque.main;

import com.cheque.mainDAO.ChequeDesignerDAO;
import com.cheque.msgbox.MessageBox;
import com.cheque.msgbox.SimpleMessageBoxFactory;
import com.cheque.popup.ReportProfilePopup;
import com.cheque.ui.StagePassable;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseButton;
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
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignLine;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
import org.controlsfx.control.PopOver;

/**
 *
 * @author lightway
 */
public class ChequeDesignerController extends AnchorPane implements
        Initializable, StagePassable {

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
    String currentReportName = "";
    @FXML
    private TextField txtAccountPayeeY;
    @FXML
    private TextField txtAccountPayeeX;
    @FXML
    private TextField txtPayY;
    @FXML
    private TextField txtPayX;
    @FXML
    private TextField txtAmountY;
    @FXML
    private TextField txtAmountX;
    @FXML
    private TextField txtDateY;
    @FXML
    private TextField txtDateX;
    @FXML
    private Slider sdPayeeY;
    @FXML
    private Slider sdPayeeX;
    @FXML
    private Slider sdPayY;
    @FXML
    private Slider sdPayX;
    @FXML
    private Slider sdDateY;
    @FXML
    private Slider sdDateX;
    @FXML
    private Slider sdAmountY;
    @FXML
    private Slider sdAmountX;
    @FXML
    private TextField txtDesignerId;
    @FXML
    private TextField txtProfileName;

    //Profile info popup---------------------------------------
    private TableView profileTable = new TableView();
    private PopOver profilePop;
    private ObservableList<ReportProfilePopup> profileData = FXCollections.
            observableArrayList();
    private ReportProfilePopup reportProfilePopup = new ReportProfilePopup();

    private ChequeDesignerDAO chequeDesignerDAO = new ChequeDesignerDAO();
    private MessageBox mb;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //room popup------------------------
        mb = SimpleMessageBoxFactory.createMessageBox();
        currentReportName = "HNBCheqeCross";
        loadReport(currentReportName);
        String id = chequeDesignerDAO.generateId();
        
        txtDesignerId.setText(id);

    }

    @FXML
    private void btnSaveOnAction(ActionEvent event) {

        boolean isSaved = chequeDesignerDAO.
                insertProfile(txtDesignerId.getText(),
                        txtProfileName.getText(),
                        txtAccountPayeeX.getText(),
                        txtAccountPayeeY.getText(),
                        txtPayX.getText(),
                        txtPayY.getText(),
                        txtRupeesX.getText(),
                        txtRupeesY.getText(),
                        txtAmountX.getText(),
                        txtAmountY.getText(),
                        txtDateX.getText(),
                        txtDateY.getText());

        if (isSaved == true) {

            mb.ShowMessage(stage, "Profile saved successfully.", "Information",
                    MessageBox.MessageIcon.MSG_ICON_SUCCESS,
                    MessageBox.MessageType.MSG_OK);

        } else {

            mb.ShowMessage(stage, "Profile not saved successfully.",
                    "Information",
                    MessageBox.MessageIcon.MSG_ICON_FAIL,
                    MessageBox.MessageType.MSG_OK);

        }

    }

    @FXML
    private void btnCancelOnAction(ActionEvent event) {
    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {

        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();

    }

    @FXML
    private void btnSearchRoomOnAction(ActionEvent event) {

        profileTableDataLoader();
        profileTable.setItems(profileData);
        if (!profileData.isEmpty()) {
            profilePop.show(btnSearchRoom);
        }

    }

    @FXML
    private void btnRefreshOnAction(ActionEvent event) {
            clear();
    }

    @FXML
    private void sdRupeesYOnMouseDragged(MouseEvent event) {

        refreshY();
    }

    @FXML
    private void sdRupeesYOnMouseClicked(MouseEvent event) {

        refreshY();

    }

    @FXML
    private void sdRupeesXOnMouseClicked(MouseEvent event) {

        refreshX();

    }

    @FXML
    private void sdRupeesXOnMouseDragged(MouseEvent event) {

        refreshX();

    }

    @FXML
    private void sdPayeeYDragged(MouseEvent event) {
        refreshY();
    }

    @FXML
    private void sdPayeeYOnClicked(MouseEvent event) {
        refreshY();
    }

    @FXML
    private void sdPayeeXOnDragged(MouseEvent event) {
        refreshX();
    }

    @FXML
    private void sdPayeeXOnClicked(MouseEvent event) {
        refreshX();
    }

    @FXML
    private void sdPayYOnDragged(MouseEvent event) {
        refreshY();
    }

    @FXML
    private void sdPayYOnClicked(MouseEvent event) {
        refreshY();
    }

    @FXML
    private void sdPayXOnDragged(MouseEvent event) {
        refreshX();
    }

    @FXML
    private void sdPayXOnClicked(MouseEvent event) {
        refreshX();
    }

    @FXML
    private void sdAmountYOnDragged(MouseEvent event) {
        refreshY();
    }

    @FXML
    private void sdAmountYOnClicked(MouseEvent event) {
        refreshY();
    }

    @FXML
    private void sdAmountXOnDragged(MouseEvent event) {
        refreshX();
    }

    @FXML
    private void sdAmountXOnClicked(MouseEvent event) {
        refreshX();
    }

    @FXML
    private void sdDateYOnDragged(MouseEvent event) {
        refreshY();
    }

    @FXML
    private void sdDateYOnClicked(MouseEvent event) {
        refreshY();
    }

    @FXML
    private void sdDateXOnDragged(MouseEvent event) {
        refreshX();
    }

    @FXML
    private void sdDateXOnClicked(MouseEvent event) {
        refreshX();
    }

    //<editor-fold defaultstate="collapsed" desc="Methods">
    
    private void clear() {

        txtRupeesY.setText("0");
        txtPayY.setText("0");
        txtAmountY.setText("0");
        txtAccountPayeeY.setText("0");
        txtDateY.setText("0");
        
         txtRupeesX.setText("0");
        txtPayX.setText("0");
        txtAmountX.setText("0");
        txtAccountPayeeX.setText("0");
               
        txtDateX.setText("0");
        

        updateReport(currentReportName,
                0, 0,
                0, 0,
                0, 0,
                0, 0,
                0,
                0, true
        );
        
        configureSlider();

    }

    private void loadProfile(String designId) {

        ArrayList<String> list = null;
        list = chequeDesignerDAO.loadProfileDetail(designId);
        if (list != null) {
            try {
                System.out.println("List item : " + list.get(0));
                txtAccountPayeeX.setText(list.get(0));
                txtAccountPayeeY.setText(list.get(1));
                txtPayX.setText(list.get(2));
                txtPayY.setText(list.get(3));
                txtRupeesX.setText(list.get(4));
                txtRupeesY.setText(list.get(5));
                txtAmountX.setText(list.get(6));
                txtAmountY.setText(list.get(7));
                txtDateX.setText(list.get(8));
                txtDateY.setText(list.get(9));

            } catch (Exception e) {
                e.printStackTrace();

            }

        }
    }

    private void profileTableDataLoader() {

        profileData.clear();
        ArrayList<ArrayList<String>> profileInfo
                = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> list = chequeDesignerDAO.
                searchProfileDetailsInfo(txtDesignerId.getText());

        if (list != null) {

            for (int i = 0; i < list.size(); i++) {

                profileInfo.add(list.get(i));
            }

            if (profileInfo != null && profileInfo.size() > 0) {
                for (int i = 0; i < profileInfo.size(); i++) {

                    System.out.println("Profie : " + profileInfo.get(i).get(0));
                    reportProfilePopup = new ReportProfilePopup();

                    reportProfilePopup.colProfileId.setValue(profileInfo.get(i).
                            get(0));
                    reportProfilePopup.colProfileName.setValue(profileInfo.
                            get(i).get(1));

                    profileData.add(reportProfilePopup);
                }
            }

        }

    }

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

                    sdPayeeY.setMax(pageHeight);

                    sdPayeeX.setMax(pageWidth);

                    sdPayY.setMax(pageHeight);

                    sdPayX.setMax(pageWidth);

                    sdDateY.setMax(pageHeight);

                    sdDateX.setMax(pageWidth);

                    sdAmountY.setMax(pageHeight);

                    sdAmountX.setMax(pageWidth);

                    jr.setPreferredSize(d);

                    swingNode.setContent(jr);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    private void updateReport(String reportName, int amountInWordsX,
            int amountInWordsY,
            int payX, int payY,
            int amountX, int amountY,
            int dateX, int dateY,
            int payeeX, int payeeY, boolean enablePayee
    ) {

        String path = ".//Reports//" + reportName + ".jrxml";
        if (new File(path).exists()) {

            try {

                JasperDesign d = JRXmlLoader.load(path);
                JRElement[] field = d.getSummary().getElements();

                JRElement rtxtCash = field[0];//Cash

                //Amount In Words Field
                JRDesignTextField rtxtAmountInWords = (JRDesignTextField) d.
                        getSummary().getElementByKey(
                                "rtxtAmountInWords");

                rtxtAmountInWords.setX(amountInWordsX);
                rtxtAmountInWords.setY(amountInWordsY);

                //Pay Field
                JRDesignTextField rtxtPay = (JRDesignTextField) d.
                        getSummary().getElementByKey(
                                "rtxtPay");

                rtxtPay.setX(payX);
                rtxtPay.setY(payY);

                //Amount
                JRDesignTextField rtxtAmount = (JRDesignTextField) d.
                        getSummary().getElementByKey(
                                "rtxtAmount");

                rtxtAmount.setX(amountX);
                rtxtAmount.setY(amountY);

                //Date
                //Amount
                JRDesignTextField rtxtDateOne = (JRDesignTextField) d.
                        getSummary().getElementByKey(
                                "rtxtDateOne");

                JRDesignTextField rtxtDateTwo = (JRDesignTextField) d.
                        getSummary().getElementByKey(
                                "rtxtDateTwo");

                JRDesignTextField rtxtMonthOne = (JRDesignTextField) d.
                        getSummary().getElementByKey(
                                "rtxtMonthOne");

                JRDesignTextField rtxtMonthTwo = (JRDesignTextField) d.
                        getSummary().getElementByKey(
                                "rtxtMonthTwo");

                JRDesignTextField rtxtYearOne = (JRDesignTextField) d.
                        getSummary().getElementByKey(
                                "rtxtYearOne");

                JRDesignTextField rtxtYearTwo = (JRDesignTextField) d.
                        getSummary().getElementByKey(
                                "rtxtYearTwo");

                JRDesignTextField rtxtYearThree = (JRDesignTextField) d.
                        getSummary().getElementByKey(
                                "rtxtYearThree");

                JRDesignTextField rtxtYearFour = (JRDesignTextField) d.
                        getSummary().getElementByKey(
                                "rtxtYearFour");

                rtxtDateOne.setX(dateX);
                rtxtDateOne.setY(dateY);

                rtxtDateTwo.setX(dateX + 18);
                rtxtDateTwo.setY(dateY);

                rtxtMonthOne.setX(dateX + 36);
                rtxtMonthOne.setY(dateY);

                rtxtMonthTwo.setX(dateX + 54);
                rtxtMonthTwo.setY(dateY);

                rtxtYearOne.setX(dateX + 72);
                rtxtYearOne.setY(dateY);

                rtxtYearTwo.setX(dateX + 90);
                rtxtYearTwo.setY(dateY);

                rtxtYearThree.setX(dateX + 108);
                rtxtYearThree.setY(dateY);

                rtxtYearFour.setX(dateX + 126);
                rtxtYearFour.setY(dateY);

                if (enablePayee == true) {
                    //Account Payee 
                    JRDesignElement rtxtPayee = (JRDesignStaticText) d.
                            getSummary().getElementByKey(
                                    "rtxtPayee");

                    JRDesignElement lineTop = (JRDesignLine) d.
                            getSummary().getElementByKey(
                                    "lineTop");

                    JRDesignElement lineBottom = (JRDesignLine) d.
                            getSummary().getElementByKey(
                                    "lineBottom");

                    rtxtPayee.setX(payeeX);
                    rtxtPayee.setY(payeeY);

                    lineTop.setX(payeeX);
                    lineTop.setY(payeeY + 1);

                    lineBottom.setX(payeeX);
                    lineBottom.setY(payeeY + 13);
                }

                JRReport jRReport = d;

                JasperCompileManager.writeReportToXmlFile(jRReport, path);
                JasperCompileManager.compileReportToFile(path);

                loadReport(reportName);

            } catch (JRException ex) {
                ex.printStackTrace();
            }
        }

    }

    private void refreshY() {

        txtRupeesY.setText(String.valueOf(sdRupeesY.getValue()).split("\\.")[0]);
        txtPayY.setText(String.valueOf(sdPayY.getValue()).split("\\.")[0]);
        txtAmountY.setText(String.valueOf(sdAmountY.getValue()).split("\\.")[0]);
        txtAccountPayeeY.setText(String.valueOf(sdPayeeY.getValue()).
                split("\\.")[0]);
        txtDateY.setText(String.valueOf(sdDateY.getValue()).split("\\.")[0]);

        updateReport(currentReportName,
                Integer.parseInt(txtRupeesX.getText()), Integer.parseInt(
                        txtRupeesY.getText()),
                Integer.parseInt(txtPayX.getText()), Integer.parseInt(txtPayY.
                        getText()),
                Integer.parseInt(txtAmountX.getText()), Integer.parseInt(
                        txtAmountY.getText()),
                Integer.parseInt(txtDateX.getText()), Integer.parseInt(txtDateY.
                        getText()),
                Integer.parseInt(txtAccountPayeeX.getText()), Integer.parseInt(
                        txtAccountPayeeY.getText()), true
        );

    }

    private void refreshX() {

        txtRupeesX.setText(String.valueOf(sdRupeesX.getValue()).split("\\.")[0]);
        txtPayX.setText(String.valueOf(sdPayX.getValue()).split("\\.")[0]);
        txtAmountX.setText(String.valueOf(sdAmountX.getValue()).split("\\.")[0]);
        txtAccountPayeeX.setText(String.valueOf(sdPayeeX.getValue()).
                split("\\.")[0]);
        txtDateX.setText(String.valueOf(sdDateX.getValue()).split("\\.")[0]);

        updateReport(currentReportName,
                Integer.parseInt(txtRupeesX.getText()), Integer.parseInt(
                        txtRupeesY.getText()),
                Integer.parseInt(txtPayX.getText()), Integer.parseInt(txtPayY.
                        getText()),
                Integer.parseInt(txtAmountX.getText()), Integer.parseInt(
                        txtAmountY.getText()),
                Integer.parseInt(txtDateX.getText()), Integer.parseInt(txtDateY.
                        getText()),
                Integer.parseInt(txtAccountPayeeX.getText()), Integer.parseInt(
                        txtAccountPayeeY.getText()), true
        );

    }

    private void configureSlider() {

        sdPayeeY.setValue(Double.parseDouble(txtAccountPayeeY.getText()));

        sdPayeeX.setValue(Double.parseDouble(txtAccountPayeeX.getText()));

        sdPayY.setValue(Double.parseDouble(txtPayY.getText()));

        sdPayX.setValue(Double.parseDouble(txtPayX.getText()));

        sdDateY.setValue(Double.parseDouble(txtDateY.getText()));

        sdDateX.setValue(Double.parseDouble(txtDateX.getText()));

        sdAmountY.setValue(Double.parseDouble(txtAmountY.getText()));

        sdAmountX.setValue(Double.parseDouble(txtAmountX.getText()));

        sdRupeesY.setValue(Double.parseDouble(txtRupeesY.getText()));

        sdRupeesX.setValue(Double.parseDouble(txtRupeesX.getText()));

    }

    @Override
    public void setStage(Stage stage, Object[] obj) {
        this.stage = stage;

        profileTable = reportProfilePopup.tableViewLoader(profileData);

        profileTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                try {
                    ReportProfilePopup p = null;
                    p = (ReportProfilePopup) profileTable.getSelectionModel().
                            getSelectedItem();

                    if (p.getColProfileId() != null) {

                        txtDesignerId.setText(p.getColProfileId());
                        txtProfileName.setText(p.getColProfileName());

                        //loading Part Here.
                        loadProfile(p.getColProfileId());

                        updateReport(currentReportName,
                                Integer.parseInt(txtRupeesX.getText()), Integer.
                                parseInt(
                                        txtRupeesY.getText()),
                                Integer.parseInt(txtPayX.getText()), Integer.
                                parseInt(txtPayY.
                                        getText()),
                                Integer.parseInt(txtAmountX.getText()), Integer.
                                parseInt(
                                        txtAmountY.getText()),
                                Integer.parseInt(txtDateX.getText()), Integer.
                                parseInt(txtDateY.
                                        getText()),
                                Integer.parseInt(txtAccountPayeeX.getText()),
                                Integer.parseInt(
                                        txtAccountPayeeY.getText()), true
                        );

                        configureSlider();

                    }

                } catch (NullPointerException n) {

                }

                profilePop.hide();

            }

        });

        profileTable.setOnMousePressed(e -> {

            if (e.getButton() == MouseButton.SECONDARY) {

                profilePop.hide();

            }

        });

        profilePop = new PopOver(profileTable);

        stage.setOnCloseRequest(e -> {

            if (profilePop.isShowing()) {

                e.consume();
                profilePop.hide();

            }
        });

    }

//</editor-fold>
}
