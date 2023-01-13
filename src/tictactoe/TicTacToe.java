/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package tictactoe;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tictactoe.authentication.AuthenticationProvider;
import tictactoe.base.SocketHandler;
import tictactoe.base.TicTacToeHandle;
import tictactoe.landing.LandingViewController;
import tictactoe.resources.ResourcesLoader;
import tictactoe.resources.styles.Styles;
import tictactoe.router.StackRouter;
import tictactoe.router.Router;

public class TicTacToe extends Application implements TicTacToeHandle {

    private Router router;
    private final SocketHandler socketHandler;
    private final AuthenticationProvider authenticationProvider;
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    private final ResourcesLoader resourcesLoader = new ResourcesLoader() {
    };
    
    public TicTacToe() {
        socketHandler = new SocketHandler(this);
        authenticationProvider = new AuthenticationProvider(socketHandler);
    }
    
    @Override
    public Router router() {
        return router;
    }

    @Override
    public ResourcesLoader resourcesLoader() {
        return resourcesLoader;
    }

    @Override
    public SocketHandler socketHandler() {
        return socketHandler;
    }

    @Override
    public AuthenticationProvider authenticationProvider() {
        return authenticationProvider;
    }

    @Override
    public void setupScene(Scene scene) {
        scene.getStylesheets().add(resourcesLoader.getCss(Styles.BASE_STYLE_STRING).toString());
        scene.getStylesheets().add(resourcesLoader.getCss(Styles.TABVIEW_STYLE_STRING).toString());
    }
    
    @Override
    public Future<?> submitJob(Runnable job) {
        return executorService.submit(job);
    }
    
    @Override
    public <T> Future<T> submitJob(Callable<T> job) {
        return executorService.submit(job);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        setupStage(stage);
        router = new StackRouter(this, stage);
        router.push(new LandingViewController());
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        socketHandler.stop();
        executorService.shutdown();
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
