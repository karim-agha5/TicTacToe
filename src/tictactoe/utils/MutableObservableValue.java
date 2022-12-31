/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictactoe.utils;

public class MutableObservableValue<T> extends ObservableValue<T> {
    
    public MutableObservableValue() {
    }

    public MutableObservableValue(T value) {
        this.value = value;
    }

    public void setValue(T value) {
        boolean changed = value != this.value || 
                (value != null && !value.equals(this.value)) || 
                (this.value != null || !this.value.equals(value));
        this.value = value;
        if (changed) {
            observable.notifyObservers(value);
        }
    }
}
