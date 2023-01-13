/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictactoe.game.providers;

import TicTacToeCommon.models.MoveModel;
import TicTacToeCommon.models.UserModel;
import TicTacToeCommon.models.events.GameEvent;
import TicTacToeCommon.services.engine.piece.League;
import TicTacToeCommon.utils.ObservableValue;
import java.util.List;

/**
 *
 * @author m-essam
 */
public interface GameProvider {

    ObservableValue<GameEvent> getEvents();

    ObservableValue<Boolean> getCanInput();

    ObservableValue<Boolean> getLastMoveResult();
    
    ObservableValue<Integer> getCurrentPlayer();

    UserModel getPlayer1();

    League getPlayer1League();

    UserModel getPlayer2();

    League getPlayer2League();
    
    UserModel getWinner();
    
    List<MoveModel> getMoves();

    void makeMove(Byte position);

    void withdraw();

    void setIsRecording(boolean isRecording);

    boolean getIsRecording();

    void close();
    
}
