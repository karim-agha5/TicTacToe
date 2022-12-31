/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictactoe.base;

import javafx.scene.Scene;
import tictactoe.resources.ResourcesLoader;
import tictactoe.router.Router;

public interface TicTacToeHandle {
    ResourcesLoader resourcesLoader();
    Router router();
    void setupScene(Scene scene);
}
