/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.utils;

import TicTacToeCommon.models.MoveModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author ITI
 */
public class GameRecordService {

    public void WriteGame(String id, List<MoveModel> moveModel) throws FileNotFoundException, IOException {
        try (ObjectOutputStream writeStream = new ObjectOutputStream(new FileOutputStream("Records/" + id))) {
            writeStream.writeObject(moveModel);
        }
    }

    public List<MoveModel> readGame(String id) throws IOException, ClassNotFoundException {
        try (ObjectInputStream readStream = new ObjectInputStream(new FileInputStream("Records/" + id))) {
            return (List<MoveModel>) readStream.readObject();
        }
    }

    public List<String> getGameList(String directoryName) {
        List<String> results = new LinkedList<String>();
        for (File file : new File("Records").listFiles()) {
            if (file.isFile()) {
                results.add(file.getName());
            }
        }
        return results;
    }

}
