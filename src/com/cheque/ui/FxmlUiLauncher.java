package com.cheque.ui;

import insidefx.undecorator.Undecorator;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * simplify UI launching (to be used with Un-decorator)
 *
 * @author Bhathiya Perera
 */
public class FxmlUiLauncher {

    //=========================================================================
    //          Internal Classes,Interfaces
    //=========================================================================
    /**
     * A class that holds return information
     */
    public static class FxmlLaunchInfo {

        private final boolean success;
        private final Stage stage;
        private final Scene scene;

        /**
         * constructor
         *
         * @param success is Ui launch success
         * @param stage related stage
         * @param scene related scene
         */
        public FxmlLaunchInfo(boolean success, Stage stage, Scene scene) {
            this.success = success;
            this.stage = stage;
            this.scene = scene;
        }

        /**
         * is ui launch success
         *
         * @return true if success else false
         */
        public boolean isSuccess() {
            return success;
        }

        /**
         * @return stage object or null
         */
        public Stage getStage() {
            return stage;
        }

        /**
         *
         * @return scene object or null
         */
        public Scene getScene() {
            return scene;
        }
    }

    /**
     * Use this to apply a modification to the scene
     */
    public static interface ApplySceneModification {

        /**
         * implement this to modify the scene ex : hide elements.
         *
         * @param scene related scene object
         */
        public void modifyScene(Scene scene);
    }

    //=========================================================================
    //          Fxml based UI launching : Content replacement
    //=========================================================================
    /**
     * A function to launch a FXML based UI, by replacing the content of the
     * current stage if the controller implements the StagePassable then the
     * controller's setStage function will be called
     *
     * @param FXML path to the FXML
     * @param windowTitle UI window title
     * @param stage stage
     * @param parameters parameters
     *
     * @return true if success
     */
    public static boolean launchContentReplacement(String FXML,
            String windowTitle, Stage stage, Object[] parameters) {

        if (FXML == null || windowTitle == null || stage == null) {
            return false;
        }
        try {
            stage.setTitle(windowTitle);
            stage.setResizable(false);

            Region root;
            FXMLLoader loader = new FXMLLoader(FxmlUiLauncher.class.getResource(
                    FXML));
            root = loader.load();
            Object controller = loader.getController();
            if (controller instanceof StagePassable) {
                StagePassable s = (StagePassable) controller;
                s.setStage(stage, parameters);
            }
            Undecorator u = new Undecorator(stage, root);
            u.getStylesheets().add("skin/undecorator.css");

            Scene scene = new Scene(u);
            scene.lookup("#StageMenu").setVisible(false);
            scene.setFill(Color.TRANSPARENT); //transparency for Undecorator

            stage.setScene(scene);

        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * A function to launch a FXML based UI, by replacing the content of the
     * current stage if the controller implements the StagePassable then the
     * controller's setStage function will be called
     *
     * @param FXML path to the FXML
     * @param windowTitle UI window title
     * @param stage stage
     * @return true if success
     */

    public static boolean launchOnNewStageWait(String FXML, String windowTitle,
            Object[] parameters) {
        Stage stage = new Stage();

        stage.initStyle(StageStyle.TRANSPARENT);
        // this is needed for the Undecorator
        boolean ret = launchContentReplacement(FXML, windowTitle, stage,
                parameters);

        if (ret) {
            stage.showAndWait();
        }
        return ret;
    }
    
}
