/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package tictactoe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tictactoe.base.TicTacToeHandle;
import tictactoe.landing.LandingViewController;
import tictactoe.resources.ResourcesLoader;
import tictactoe.resources.styles.Styles;
import tictactoe.router.StackRouter;
import tictactoe.router.Router;

public class TicTacToe extends Application implements TicTacToeHandle {

    private Router router;
    private final ResourcesLoader resourcesLoader = new ResourcesLoader() {
    };
    
    @Override
    public Router router() {
        return router;
    }

    @Override
    public ResourcesLoader resourcesLoader() {
        return resourcesLoader;
    }

    @Override
    public void setupScene(Scene scene) {
        scene.getStylesheets().add(resourcesLoader.getCss(Styles.BASE_STYLE_STRING).toString());
        scene.getStylesheets().add(resourcesLoader.getCss(Styles.TABVIEW_STYLE_STRING).toString());
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        setupStage(stage);
        router = new StackRouter(this, stage);
        router.push(new LandingViewController());
        stage.show();
    }
    
    private void setupStage(Stage stage) {
        stage.setMinHeight(720);
        stage.setMinWidth(1024);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
