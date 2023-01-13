/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.record;

import TicTacToeCommon.models.MoveModel;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import tictactoe.game.GameViewController;


/**
 *
 * @author ITI
 */
public class RecordGame {
   
     private FileOutputStream file;
     private File dir;
     String fileName;
     
   public void WriteGame (String id , List<MoveModel> moveModel) throws FileNotFoundException, IOException{
        dir = new File("TicTacToe\\src\\tictactoe\\resources\\files");
         MoveModel gameId = new MoveModel();
         fileName = gameId.getGameId();
         file = new FileOutputStream(new File(dir, fileName + ".txt"));
        ObjectOutputStream writeStream = new ObjectOutputStream(file);
        writeStream.writeObject(moveModel);
        writeStream.flush();
        writeStream.close();
           
   }
   public List<MoveModel> readGame(String id) throws IOException, ClassNotFoundException{
           List<MoveModel> result = new ArrayList<>();
            FileInputStream readData = new FileInputStream(new File(dir, fileName + ".txt"));
           ObjectInputStream readStream = new ObjectInputStream(readData);
           result = (ArrayList)readStream.readObject();
        return result;
    }
       
   public List<String> getGameList(String directoryName){
       
        List<String> results = new ArrayList<String>();

       File[] files = new File("TicTacToe\\src\\tictactoe\\resources\\files").listFiles();

       for (File file : files) {
           if (file.isFile()) {
               results.add(file.getName());
           }
       }
       return results;
   }
  
}