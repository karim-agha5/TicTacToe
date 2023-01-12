/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.Alert;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

/**
 * FXML Controller class
 *
 * @author ITI
 */
public class AlertController implements Initializable {

    @FXML
    private Button button;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
               alert.showAndWait();
               alert.setTitle("");
               alert.setHeaderText("");
               alert.setContentText("");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.isPresent()&& result.get()==ButtonType.OK){
                    System.out.println("ok button clicked");
                }
            }
        });
       
    }    
    
}
