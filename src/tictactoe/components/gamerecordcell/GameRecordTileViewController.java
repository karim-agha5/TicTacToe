/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package tictactoe.components.gamerecordcell;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import tictactoe.base.ViewController;

public class GameRecordTileViewController extends ViewController {

    private static final String CHECKED_IMAGE = "CheckedImage";

    @FXML
    private Label gameLabel;
    @FXML
    private ImageView checkedImage;

    private String gameRecord;

    @Override
    public URL getViewUri() {
        return getClass().getResource("GameRecordTileView.fxml");
    }

    public void setGameRecord(String gameRecord) {
        this.gameRecord = gameRecord;
        setGameRecordUI(gameRecord);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setGameRecordUI(gameRecord);
    }

    private void setGameRecordUI(String gameRecord) {
        if (gameRecord != null) {
            gameLabel.setText(gameRecord);
            view().setVisible(true);
        } else {
            view().setVisible(false);
        }
    }

    public void setSelected(boolean selected) {
        List<String> cssClasses = checkedImage.getStyleClass();
        if (selected) {
            if (!cssClasses.contains(CHECKED_IMAGE)) {
                cssClasses.add(CHECKED_IMAGE);
            }
        } else {
            cssClasses.remove(CHECKED_IMAGE);
        }
    }
}
