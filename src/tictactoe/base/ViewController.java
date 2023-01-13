/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package tictactoe.base;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tictactoe.resources.ResourcesLoader;
import tictactoe.router.Router;

public abstract class ViewController {

    protected static final Logger logger = Logger.getLogger(ViewController.class.getName());
    private TicTacToeHandle handle;
    public Stage stage;
    private URL location;
    private ResourceBundle resources;

    public ViewController() {
    }

    public abstract URL getViewUri();

    public abstract void initialize(URL location, ResourceBundle resources);

    protected void attach(TicTacToeHandle handle, Stage stage) {
        this.handle = handle;
        this.stage = stage;
        initialize(location, resources);
    }

    protected Node attachController(ViewController controller) {
        try {
            Parent parent = controller.createView();
            controller.attach(handle, stage);
            return parent;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Node attachTo(ViewController controller) {
        return controller.attachController(this);
    }
    
    protected final TicTacToeHandle handle() {
        return handle;
    }

    protected final Stage stage() {
        return stage;
    }

    protected final Router router() {
        return handle.router();
    }

    protected final ResourcesLoader resourcesLoader() {
        return handle.resourcesLoader();
    }

    protected final Scene scene() {
        return stage.getScene();
    }

    protected final <T extends Parent> Parent root() {
        return (T) stage.getScene().getRoot();
    }

    public final Parent createView() throws IOException {
        return createView(this);
    }

    public static Parent createView(ViewController controller) throws IOException {
        FXMLLoader loader = new FXMLLoader(controller.getViewUri());
        loader.setController(controller);
        Parent root = loader.load();
        root.setUserData(controller);
        return root;
    }
}
