package org.example;

import java.io.*;
import java.util.Arrays;

public class GameOfLife {
  // TODO:
  public void game(String fileNameInput, String fileNameOutput)
  {
    String[] inputData = readFieldParameters(fileNameInput);
    int axisX = Integer.parseInt(inputData[0]);
    int axisY = Integer.parseInt(inputData[1]);
    int cycleNumber = Integer.parseInt(inputData[2]);
    String[][] initialGeneration = readData(fileNameInput, axisX, axisY);
    String[][] newGeneration = computeNewGeneration(initialGeneration, axisX, axisY, cycleNumber);
    writeOutput(fileNameOutput, newGeneration);
  }
  public String[] readFieldParameters(String fileNameInput)
  {
    String[] inputData = new String[3];
    try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileNameInput)))
    {
      inputData = bufferedReader.readLine().split(",");
    }catch (IOException e)
    {
      e.printStackTrace();
    }
    return inputData;
  }
  public String[][] readData(String fileNameInput, int axisX, int axisY) {
    String[][] initialGeneration = new String[axisX][axisY];
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileNameInput))) {
      int count = -1;
      while (bufferedReader.ready())
      {
        if(count ==-1)
        {
          bufferedReader.readLine();
          count++;
        }else {
          String[] rowData = bufferedReader.readLine().split(" ");
          System.arraycopy(rowData, 0, initialGeneration[count], 0, axisX);
          count++;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return initialGeneration;
  }
  public String[][] computeNewGeneration(String[][] initialGeneration, int axisX, int axisY, int cycleNumber) {
    int cycle = 0;
    String[][] newGeneration = new String[axisX][axisY];
    while (cycle < cycleNumber) {
      cycle += 1;
      for (int x = 0; x < axisX; x++) {
        for (int y = 0; y < axisY; y++) {
          int neighbours = 0;
          int col;
          int row;
          if (initialGeneration[x][y].equals("X")) {
            neighbours -= 1;
          }
          for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
              col = (x + i + axisX) % axisX;
              row = (y + j + axisY) % axisY;
              if (initialGeneration[col][row].equals("X")) {
                neighbours++;
              }
            }
          }
          if (initialGeneration[x][y].equals("X") && neighbours < 2) {
            newGeneration[x][y] = "O";
          } else if (initialGeneration[x][y].equals("X") && neighbours > 3) {
            newGeneration[x][y] = "O";
          } else if (initialGeneration[x][y].equals("O") && neighbours == 3) {
            newGeneration[x][y] = "X";
          } else
            newGeneration[x][y] = initialGeneration[x][y];
        }
      }
      for (int i = 0; i < axisX; i++) {
        System.arraycopy(newGeneration[i], 0, initialGeneration[i], 0, initialGeneration[i].length);
      }
    }
    return newGeneration;
  }
  public void writeOutput(String fileNameOutput, String[][] newGeneration){
      int lineCount = 0;
      try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileNameOutput))){
      for(String[] string: newGeneration) {
        String stringOut = Arrays.toString(string)
                .replaceAll("[][,]]", "")
                .replaceAll("\\[", "");
        if (lineCount == 0) {
          bufferedWriter.write(stringOut);
          lineCount++;
        }
        else
          bufferedWriter.write("\n"+stringOut);
      }
    }catch (IOException e){
      e.printStackTrace();
    }
  }
}