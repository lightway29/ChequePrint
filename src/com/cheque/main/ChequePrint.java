/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cheque.main;

import com.cheque.ui.FxmlUiLauncher;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Miren
 */
public class ChequePrint extends Application {
    
    @Override
    public void start(Stage primaryStage) {

        FxmlUiLauncher.launchOnNewStageWait(
                    "/com/cheque/main/Home.fxml",
                    "Cheque Print", null);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
