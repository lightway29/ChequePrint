/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cheque.main;

import com.cheque.Report.BandReport;
import com.cheque.Report.SimpleAdhocReport;
import com.cheque.Report.VariableReport;
import com.cheque.mainDAO.ChequePrintDAO;
import com.cheque.msgbox.MessageBox;
import com.cheque.msgbox.SimpleMessageBoxFactory;
import com.cheque.ui.ReportGenerator;
import com.cheque.validations.FormatAndValidate;
import java.awt.Color;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import net.sf.jasperreports.engine.JRBand;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRReport;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

/**
 *
 * @author Miren
 */
public class ChequePrintController extends AnchorPane implements Initializable {

    //<editor-fold defaultstate="collapsed" desc="initcomponents">
    @FXML
    private Button btnCancel;

    @FXML
    private Button btnClose;

    private DatePicker dtpDate;


    private TextField txtAmount;

    private TextField txtPay;

    private CheckBox chkCrossCheque;

    private CheckBox chkRemoveDate;

    private Stage stage;
    private MessageBox mb;
    private final ValidationSupport validationSupport = new ValidationSupport();
    private final FormatAndValidate fav = new FormatAndValidate();
    private EnglishNumberToWords englishNumberToWords
            = new EnglishNumberToWords();
//</editor-fold>

    ChequePrintDAO chequePrint = new ChequePrintDAO();
    @FXML
    private Button btnSave;
    @FXML
    private Button btnSearchRoom;
    @FXML
    private Button btnRefresh;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mb = SimpleMessageBoxFactory.createMessageBox();
        dateFormatterArrivalDate("yyyy-MM-dd");
        dtpDate.setValue(LocalDate.now());

        //<editor-fold defaultstate="collapsed" desc="validation">
        validationSupport.registerValidator(txtPay,
                Validator.combine(
                        Validator.createEmptyValidator("Empty"),
                        (Control c, String newValue) -> ValidationResult.
                        fromErrorIf(txtPay,
                                "Invalid Payment",
                                !fav.validName(txtPay.getText()))));

        validationSupport.registerValidator(txtAmount,
                Validator.combine(
                        Validator.createEmptyValidator("Empty"),
                        (Control c, String newValue) -> ValidationResult.
                        fromErrorIf(txtAmount,
                                "Invalid Amount",
                                !fav.chkPrice(txtAmount.getText()))));
//</editor-fold>
        chkRemoveDate.setSelected(true);
    }

    private void dateFormatterArrivalDate(String pattern) {

        dtpDate.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(
                    pattern);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });

    }

    void btnPrintOnAction(ActionEvent event) {
        boolean validationSupportResult = false;
        ValidationResult v = validationSupport.getValidationResult();

        if (v != null) {
            validationSupportResult = validationSupport.isInvalid();

            if (validationSupportResult == true) {
                mb.ShowMessage(stage, "The component marked in red "
                        + "\nmust be filled with valid data.", "Error",
                        MessageBox.MessageIcon.MSG_ICON_FAIL,
                        MessageBox.MessageType.MSG_OK);

            } else if (validationSupportResult == false) {

                String Date = dtpDate.getValue().toString();

                String[] parts = Date.split("-");
                String part1 = parts[0];
                String part2 = parts[1];
                String part3 = parts[2];

                String Y1 = part1.substring(0, 1);
                String Y2 = part1.substring(1, 2);
                String Y3 = part1.substring(2, 3);
                String Y4 = part1.substring(3, 4);

                String M1 = part2.substring(0, 1);
                String M2 = part2.substring(1, 2);

                String D1 = part3.substring(0, 1);
                String D2 = part3.substring(1, 2);

                HashMap param = new HashMap();
                param.put("pay", "**" + txtPay.getText() + "**");
                param.put("amount", Double.parseDouble(txtAmount.getText()));
                param.put("rupees", "**" + convertToWords(txtAmount.getText())
                        + "**");
                if (chkRemoveDate.isSelected()) {
                    param.put("D1", D1);
                    param.put("D2", D2);
                    param.put("M1", M1);
                    param.put("M2", M2);
                    param.put("Y1", "");
                    param.put("Y2", "");
                    param.put("Y3", Y3);
                    param.put("Y4", Y4);
                } else {
                    param.put("D1", D1);
                    param.put("D2", D2);
                    param.put("M1", M1);
                    param.put("M2", M2);
                    param.put("Y1", Y1);
                    param.put("Y2", Y2);
                    param.put("Y3", Y3);
                    param.put("Y4", Y4);
                }

                String ReportParth = null;
                if (chkCrossCheque.isSelected()) {
                    ReportParth = ".//Reports//HNBCheqeCross.jasper";
                } else {
                    ReportParth = ".//Reports//HNBCheqe.jasper";
                }
                String path = new File(ReportParth).getAbsolutePath();
                ReportGenerator r = new ReportGenerator(path, param);
                r.setVisible(true);
            }
        }
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        txtAmount.setText("0.00");
        txtPay.setText("Cash");
        dtpDate.setValue(LocalDate.now());
        chkCrossCheque.setSelected(false);
        chkRemoveDate.setSelected(false);
    }

    @FXML
    void btnCloseOnAction(ActionEvent event) {

//        accountWidth
//        accountRow
//        dateWidth 
//        dateRow
//        cashWidth 
//        cashRow
//        amountWordWidth 
//        amountWordRow
//        amountWidth 
//        amountRow
        
//        new VariableReport(200, 0, 400, 0, 46, 30, 50, 72, 330, 43);
//        new BandReport();
//        Stage stage = (Stage) btnClose.getScene().getWindow();
//        stage.close();
        
//        String path = ".//Reports//HNBCheqeCross.jrxml";
        String path = ".//Reports//HNBCheqe.jrxml";
        if (new File(path).exists()) {
            System.out.println("Exists");
               try {
            
                  
            JasperDesign d = JRXmlLoader.load(path);
                   JRElement[] field = d.getSummary().getElements();
                   
                   System.out.println("Count : "+field.length);
                   
                   JRElement rtxtCash = field[0];//Cash
                   JRDesignTextField rtxtAmountInWords = (JRDesignTextField) d.getSummary().getElementByKey(
                           "rtxtAmountInWords");
                   rtxtAmountInWords.setY(90);
                   System.out.println("X : "+rtxtAmountInWords.getX()+" - Y : "+rtxtAmountInWords.getY());;
                   
//                   System.out.println("Height : "+rtxtCash.getX()+"-"+rtxtCash.getPositionTypeValue().name());
//                   rtxtCash.setX(70);
//                   rtxtCash.setForecolor(Color.RED);
//                   
//                  ;
//                    System.out.println("Height : "+rtxtCash.getX()+" "+ rtxtCash.getY());
//                    JRReport jRReport = d;
//                    JasperCompileManager.writeReportToXmlFile(jRReport, path);
//                    JasperCompileManager.compileReportToFile( path);
//                    
//                    
//                   
                   
                   
                   
        } catch (JRException ex) {
            ex.printStackTrace();
        }
        }
        
     
        
        
    }

    private String convertToWords(String value) {

        double amount = 0;
        double cents = 0;
        String[] valueTxt = null;
        String amountTxt = null;
        String inWords = null;
        String centsTxt = null;

        if (value.contains(".")) {

            valueTxt = value.split("\\.");

            centsTxt = valueTxt[1];
            amountTxt = valueTxt[0];
        }

        if (amountTxt != null) {
            amount = Double.parseDouble(amountTxt);
        }

        if (centsTxt != null) {
            cents = Double.parseDouble(centsTxt);

        }

        if (cents != 0) {

            inWords = EnglishNumberToWords.convert((long) amount) + " And "
                    + EnglishNumberToWords.convert((long) cents)
                    + " Cents Only.";
        } else {

            inWords = englishNumberToWords.convert(
                    (long) Double.parseDouble(value));

        }

        return inWords;
    }

    @FXML
    private void btnSaveOnAction(ActionEvent event) {
    }

    @FXML
    private void btnSearchRoomOnAction(ActionEvent event) {
    }

    @FXML
    private void btnRefreshOnAction(ActionEvent event) {
    }

}
