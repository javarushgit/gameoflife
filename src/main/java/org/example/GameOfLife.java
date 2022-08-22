package org.example;


import java.io.*;

public class GameOfLife {

  private int lengthY;
  private int lengthX;
  private int genCounter;
  private char[][] gameState;

  private char aliveCell = 'X';
  private char deadCell = 'O';


  // TODO:
  public void game(String fileNameInput, String fileNameOutput)
  {
    readFile(fileNameInput);
    for (int i = 0; i < genCounter; i++) {
      genStart();
    }
    writeFile(fileNameOutput);
  }

  private void readFile(String inputFileName)
  {
    try {
      BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
      String[] str = reader.readLine().split(",");
      lengthY = Integer.parseInt(str[0]);
      lengthX = Integer.parseInt(str[1]);
      genCounter = Integer.parseInt(str[2]);
      gameState = new char[lengthY][lengthX];
      for (int i = 0; i < lengthY; i++) {
        String[] line = reader.readLine().split(" ");
        for (int j = 0; j < lengthX; j++) {
          gameState[i][j] = line[j].charAt(0);
        }
      }
      reader.close();
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void writeFile(String outputFileName)
  {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < lengthY; i++) {
      for (int j = 0; j < lengthX; j++) {
        builder.append(gameState[i][j]);
        builder.append(' ');
      }
      builder.deleteCharAt(builder.length() - 1);
      builder.append("\n");
    }
    builder.deleteCharAt(builder.length() - 1);
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
      writer.write(String.valueOf(builder));
      writer.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void genStart()
  {
    char[][] result = new char[lengthY][lengthX];
    for (int i = 0; i < lengthY; i++) {
      for (int j = 0; j < lengthX; j++) {
        int countNeighbours = neighboursAlive(i,j);
        if (gameState[i][j] == aliveCell) {
          if (countNeighbours > 1 && countNeighbours < 4)
            result[i][j] = aliveCell;
          else
            result[i][j] = deadCell;
        } else if (gameState[i][j] == deadCell)
        {
          if (countNeighbours == 3)
            result[i][j] = aliveCell;
          else
            result[i][j] = deadCell;
        }
      }
    }
    gameState = result;
  }



  private int neighboursAlive(int y, int x)
  {
    int neighboursCount = 0;
    int col;
    int row;
    for (int i = -1 ; i < 2 ; i++) {
      for (int j = -1; j < 2; j++) {
        col = (y + i + lengthY) % lengthY;
        row = (x + j + lengthX) % lengthX;
        if(gameState[col][row] == aliveCell) {
          neighboursCount++;
        }
      }
    }
    if (gameState[y][x] == aliveCell)
      neighboursCount--;
    return neighboursCount;
  }

  private void printGeneration()
  {
    try{
      Runtime.getRuntime().exec("cmd /C cls");
    }catch(IOException ex) {}
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < lengthY; i++) {
      for (int j = 0; j < lengthX; j++) {
        builder.append(gameState[i][j]);
        builder.append(' ');
      }
      builder.deleteCharAt(builder.length() - 1);
      builder.append("\n");
    }
    System.out.println(builder);
  }

}