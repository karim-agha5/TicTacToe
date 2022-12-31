/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictactoe.base;

import java.util.HashMap;
import java.util.Map;
import tictactoe.utils.MutableObservableValue;
import tictactoe.utils.ObjectUtils;
import tictactoe.utils.ObservableValue;

public class ViewModel<T> {

    private final MutableObservableValue<T> observableValue = new MutableObservableValue<>();
    private final Map<ViewModelListener<T>, ObservableValue.ListenCanceller> listeners = new HashMap<>();

    protected void updateState(T newState) {
        observableValue.setValue(newState);
    }

    public ObservableValue<T> getObservableValue() {
        return observableValue;
    }

    public void bind(ViewModelListener<T> listener) {
        listeners.put(listener, observableValue.addListener(((newValue) -> {
            listener.didUpdateState(newValue);
        })));
    }

    public void unbind(ViewModelListener<T> listener) {
        ObjectUtils.ifNotNull(listeners.get(listener), (e) -> e.cancel());
    }
}
