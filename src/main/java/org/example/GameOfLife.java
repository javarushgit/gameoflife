package org.example;


import java.io.*;

public class GameOfLife {
  int iterator;
  String[][] field = new String[50][50];

  // TODO:
  public void game(String fileNameInput, String fileNameOutput) {
    readFileInput(fileNameInput);
    for (int i = 0; i < iterator; i++) {
      //oneLifeCycle(field);
    }
    fillOutputFile(fileNameOutput);
  }
  private void howManyTimesToDo(String firstStringInFile){
    String[] array = firstStringInFile.split(",");
    iterator = Integer.parseInt(array[2]);
  }
  private void readFileInput(String fileNameInput){
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileNameInput))) {
      howManyTimesToDo(bufferedReader.readLine());
      int i = 0;
      while(bufferedReader.ready()){
        String[] line = bufferedReader.readLine().split(" ");
        int j = 0;
        for (String element :
                line) {
          if(element != null)
            field[i][j++] = element;
        }
        i++;
      }
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  private void oneLifeCycle(String[][] field){
    String [][] newField = field;
  }
  private void fillOutputFile(String file){
    try(FileWriter fileWriter = new FileWriter(file)) {
      for (int i = 0; i < field.length; i++) {
        for (int j = 0; j < field[i].length; j++) {
          String element;
          if ((element = field[i][j]) != null)
            fileWriter.write(element + " ");
        }
        fileWriter.write("\n");
      }
    }
    catch (IOException e){
      System.out.println(e.getMessage());
    }
  }

  public static void main(String[] args) {
    GameOfLife gameOfLife = new GameOfLife();
    gameOfLife.game("InputText.txt", "OutputText.txt");
  }
}