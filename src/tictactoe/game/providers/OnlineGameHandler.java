/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictactoe.game.providers;

import TicTacToeCommon.models.MoveModel;
import TicTacToeCommon.models.events.GameEvent;
import TicTacToeCommon.models.requests.GameMoveRequest;
import TicTacToeCommon.models.requests.GameWithdrawRequest;
import TicTacToeCommon.models.responses.GameMoveResponse;
import TicTacToeCommon.models.responses.GameWithdrawResponse;
import TicTacToeCommon.services.engine.piece.League;
import TicTacToeCommon.utils.ObservableValue;
import tictactoe.authentication.AuthenticationProvider;
import tictactoe.base.SocketHandler;

/**
 *
 * @author m-essam
 */
public class OnlineGameHandler extends GameHandler implements ObservableValue.Observer {

    private final SocketHandler socketHandler;
    private final ObservableValue.ListenCanceller connectedCanceller;
    private final ObservableValue.ListenCanceller eventsCanceller;
    
    public OnlineGameHandler(GameEvent.Started event, SocketHandler socketHandler, AuthenticationProvider authenticationProvider) {
        super(event.getGameId(), authenticationProvider.getUser().getValue(), event.getLeague(), event.getPlayer());
        this.socketHandler = socketHandler;
        connectedCanceller = socketHandler.getConnected().addListener(this);
        eventsCanceller = socketHandler.getMessage().addListener(this);
        winner = player1;
        if (player1League == League.Cross) {
            canInput.setValue(true);
            currentPlayer.setValue(1);
        } else {
            canInput.setValue(false);
            currentPlayer.setValue(2);
        }
    }

    @Override
    public void makeMove(Byte position) {
        socketHandler.send(new GameMoveRequest(gameId, position));
    }

    @Override
    public void withdraw() {
        socketHandler.send(new GameWithdrawRequest(gameId));
    }
    
    @Override
    public void didChange(Object newValue) {
        if (newValue instanceof Boolean) {
            if ((Boolean)newValue != true) {
                events.setValue(new GameEvent.Ended(gameId));
            }
        } else if (newValue instanceof GameEvent) {
            events.setValue((GameEvent) newValue);
            if (newValue instanceof GameEvent.Moved) {
                MoveModel move = ((GameEvent.Moved) newValue).getMove();
                moves.add(move);
                if (player1.getId().equals(move.getPlayerId())) {
                    currentPlayer.setValue(2);
                    canInput.setValue(false);
                } else {
                    currentPlayer.setValue(1);
                    canInput.setValue(true);
                }
            }
        } else if (newValue instanceof GameMoveResponse) {
            GameMoveResponse response = ((GameMoveResponse) newValue);
            if (response.isStatus() == false || response.getData() == false) {
                lastMoveResult.setValue(false);
            } else {
                lastMoveResult.setValue(true);
            }
        } else if (newValue instanceof GameWithdrawResponse) {
            GameWithdrawResponse response = ((GameWithdrawResponse) newValue);
            if (response.isStatus() == false || response.getData().equals(gameId)) {
                events.setValue(new GameEvent.Ended(gameId));
            }
        }
    }

    @Override
    public void close() {
        connectedCanceller.cancel();
        eventsCanceller.cancel();
    }
}