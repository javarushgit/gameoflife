package org.example;

import java.io.*;
import java.util.Arrays;

public class GameOfLife {
  private char[][] initialGeneration;
  private char[][] newGeneration;
  private final String PATH = "src\\test\\resources\\";
  private int axisY;
  private int axisX;
  private int cycleNumber;

  // TODO:
  public void game(String fileNameInput, String fileNameOutput)
  {
    readData(fileNameInput);
    getNewGeneration();
    writeOutput(fileNameOutput);
  }
  private String[] splitSettings(String settings) {
    String[] array = new String[3];
    int begin = 0;
    int index = 0;
    for (int i = 0; i < settings.length(); i++) {
      if (settings.charAt(i) == ',') {
        array[index++] = settings.substring(begin, i);
        begin = i + 1;
      }
    }
    array[index] = settings.substring(begin);
    return array;
  }

  public  void readData(String fileNameInput) {
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH + fileNameInput))) {
      String[] inputData = splitSettings(bufferedReader.readLine());
      axisY = Integer.parseInt(inputData[0]);
      axisX = Integer.parseInt(inputData[1]);
      cycleNumber = Integer.parseInt(inputData[2]);
      initialGeneration = new char[axisY][axisX];
          for (int i = 0; i < axisY; i++) {
            for (int j = 0; j < axisX; j++) {
             initialGeneration[i][j] = (char) bufferedReader.read();
             bufferedReader.read();
            }
          }
      } catch (IOException e) {
         throw new RuntimeException();
    }
  }
  public void getNewGeneration() {
    int cycle = 0;
    newGeneration = new char[axisY][axisX];
    while (cycle < cycleNumber) {
      cycle += 1;
      for (int x = 0; x < axisY; x++) {
        for (int y = 0; y < axisX; y++) {
          int neighbours = 0;
          int col;
          int row;
          if (initialGeneration[x][y] == ('X')) {
            neighbours -= 1;
          }
          char aliveCell = 'X';
          for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
              col = (x + i + axisY) % axisY;
              row = (y + j + axisX) % axisX;
              if (initialGeneration[col][row] == aliveCell) {
                neighbours++;
              }
            }
          }
          char deadCell = 'O';
          if (initialGeneration[x][y] == aliveCell && neighbours < 2) {
            newGeneration[x][y] = deadCell;
          } else if (initialGeneration[x][y] == aliveCell && neighbours > 3) {
            newGeneration[x][y] = deadCell;
          } else if (initialGeneration[x][y] == deadCell && neighbours == 3) {
            newGeneration[x][y] = aliveCell;
          } else
            newGeneration[x][y] = initialGeneration[x][y];
        }
      }
      for (int i = 0; i < axisY; i++) {
        System.arraycopy(newGeneration[i], 0, initialGeneration[i], 0, initialGeneration[i].length);
      }
    }
  }
  public void writeOutput(String fileNameOutput){
      StringBuilder sb = new StringBuilder();
      try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH + fileNameOutput))){
      for(char[] string: newGeneration) {
        for (char c : string) {
          if (c != ' ') {
            sb.append(c).append(' ');
          }
        }
        sb.deleteCharAt(sb.lastIndexOf(" "));
        sb.append('\n');
      }
      sb.deleteCharAt(sb.lastIndexOf("\n"));
      bufferedWriter.write(sb.toString());
      }catch (IOException e){
      throw new RuntimeException();
    }
  }
}