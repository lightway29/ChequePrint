/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cheque.main;

import com.cheque.mainDAO.ActivationDAO;
import com.cheque.msgbox.MessageBox;
import com.cheque.msgbox.SimpleMessageBoxFactory;
import com.cheque.util.PasswordEncrypted;
import com.cheque.validations.ErrorMessages;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Miren
 */
public class Activation extends AnchorPane implements
        Initializable {

    //<editor-fold defaultstate="collapsed" desc="initcomponents">
    @FXML
    private TextField txtActivationCode;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnActivate;
//</editor-fold>

    ActivationDAO activationDAO = new ActivationDAO();
    PasswordEncrypted passwordEncrypted = new PasswordEncrypted("WJ5mhK9=%S@ygn%f");
    private Stage stage;
    private MessageBox mb;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mb = SimpleMessageBoxFactory.createMessageBox();
    }

    @FXML
    void btnActivateOnAction(ActionEvent event) {

        if (getMAC() == null) {
            mb.ShowMessage(stage, ErrorMessages.MACFail, "Error",
                        MessageBox.MessageIcon.MSG_ICON_FAIL,
                        MessageBox.MessageType.MSG_OK);
        } else {
            String activationCode= passwordEncrypted.encrypt(txtActivationCode.getText());
            String macCode= passwordEncrypted.encrypt(getMAC());
            boolean val=activationDAO.insertActivationInfo(activationCode, macCode);
            if(val==true){
                System.out.println("true");
                
            }
        }
        
    }

    @FXML
    void btnCloseOnAction(ActionEvent event) {

    }

    public String getMAC() {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);

            byte[] mac = network.getHardwareAddress();

            System.out.print("Current MAC address : ");

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
            System.out.print(sb.toString());
            return sb.toString();

        } catch (UnknownHostException e) {

            e.printStackTrace();
            return null;

        } catch (SocketException e) {

            e.printStackTrace();
            return null;

        }

    }

}
