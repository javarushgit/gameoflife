package org.example;

import java.io.*;

public class GameOfLife {
  int iterator;
  int x;
  int y;
  String[][] field;

  // TODO:
  public void game(String fileNameInput, String fileNameOutput) {
    readFileInput(fileNameInput);
    for (int i = 0; i < iterator; i++) {
      oneLifeCycle();
    }
    fillOutputFile(fileNameOutput);
  }
  private void oneLifeCycle(){
    String [][] newField = new String[x][y];
    int neighbors;
    String element;
    for (int i = 0; i <field.length ; i++) {
      for (int j = 0; j <field[i].length; j++) {
        neighbors = countNeighbor(i, j);
        element = field[i][j];
        if(isAlive(element)){
          neighbors--;
          if(neighbors < 2 || neighbors > 3){
            element = "O";
          }
        }
        else{
          if(neighbors == 3){
            element = "X";
          }
        }
        newField[i][j] = element;
      }
    }
    this.field = newField;
  }
  private int countNeighbor(int i, int j){
    int count = 0;
    for (int k = -1; k < 2; k++) {
      for (int l = -1; l < 2; l++) {
        if(field[(i + k + x) % x][(j + l + y) % y].equals("X")){
          count++;
        }
      }
    }
    return count;
  }
  private boolean isAlive(String element){
    return element.equals("X");
  }
private void sizeFieldAndIterator(String line){
  String[] array = line.split(",");
  x = Integer.parseInt(array[0]);
  y = Integer.parseInt(array[1]);
  iterator = Integer.parseInt(array[2]);
}
  private void initializeArray(){
    field = new String[x][y];
  }
  private void readFileInput(String fileNameInput){
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileNameInput))) {
      sizeFieldAndIterator(bufferedReader.readLine());
      initializeArray();
      int i = 0;
      while(bufferedReader.ready()){
        String[] line = bufferedReader.readLine().split(" ");
        int j = 0;
        for (String element :
                line) {
          field[i][j++] = element;
        }
        i++;
      }
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  private void fillOutputFile(String file){
    try(FileWriter fileWriter = new FileWriter(file)) {
      for (String[] strings : field) {
        for (String string : strings) {
          String element;
          if ((element = string) != null)
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