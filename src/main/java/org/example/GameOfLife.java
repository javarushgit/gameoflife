package org.example;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GameOfLife {

  private  int y;
  private  int x;
  private  int numberOfLives;
  private  char[][] fieldOfLife;
  private String path="src/test/resources/";


  public void game(String fileNameInput, String fileNameOutput) throws IOException {
   inputFile(fileNameInput);
   lifeCycle();
    outputFile(fileNameOutput);
  }

  public  void setY(int vertically) {
    y = vertically;
  }

  public  void setX(int horizontally) {
    x = horizontally;
  }

  public  void setNumberOfLives(int numberOL) {
    numberOfLives = numberOL;
  }



  public  void inputFile(String fileNameInput) throws IOException {
    FileReader fr = new FileReader(path+fileNameInput);
    BufferedReader reader = new BufferedReader(fr);
    String line = reader.readLine( );
    String[] taskСonditions = line.split(",");
    int y = Integer.parseInt(taskСonditions[0]);
    setY(y);
    int x = Integer.parseInt(taskСonditions[1]);
    setX(x);
    setNumberOfLives(Integer.parseInt(taskСonditions[2]));
    char[][] input = new char[y][x];
    char[] tmp ;
    for (int i = 0; i < y; i++) {
      line = reader.readLine( );
      if (line==null){ break;}
      line = line.replace(" ", "");
      tmp = line.toCharArray( );
      for (int j = 0; j < x; j++) {
        input[i][j] = tmp[j];
      }

    }
    setFieldOfLife(input);

  }

  private   void setFieldOfLife(char[][] chars) {
   fieldOfLife = chars;
  }

  public  void lifeCycle() {
    char[][] newLife;
    for (int life = 0; life < numberOfLives; life++) {
      newLife = new char[y][x];
      for (int i = 0; i < y; i++) {

        for (int j = 0; j < x; j++) {

          newLife[i][j] = weСheckЕheМiability(i, j);

        }

      }
      fieldOfLife = newLife;

    }

  }

  private  char weСheckЕheМiability(int v, int h) {
    int lifeСounter = 0;
    char celuli = fieldOfLife[v][h];
    char tmp;
    int verticali;
    int horizontali;

    for (int i = -1; i < 2; i++) {
      for (int j = -1; j < 2; j++) {
        verticali = (v + i + y) % y;
        horizontali = (h + j + x) % x;
        tmp = fieldOfLife[verticali][horizontali];
        if (tmp == 'X') {
          lifeСounter++;
        }
      }
    }
    if (celuli=='X') {
      lifeСounter --;
      return getCellByState(shouldAliveCellLive(lifeСounter));
    }
    else {return getCellByState(shouldDeadCellLive(lifeСounter));}

  }

  private static boolean shouldAliveCellLive(int lifCounter) {
    return lifCounter > 1 && lifCounter < 4;
  }

  private static boolean shouldDeadCellLive(int lifCounter) {
    return lifCounter == 3;
  }

  private static char getCellByState(boolean state) {
    return state ? 'X' : 'O';
  }

  public  void outputFile(String fileNameOutput) throws IOException {
    StringBuilder stringBuilder = new StringBuilder( );
    char[][] tmp = fieldOfLife;
    for (int G = 0; G < y; G++) {
      for (int E = 0; E < x; E++) {
        stringBuilder.append(tmp[G][E]).append(" ");
      }
      stringBuilder.deleteCharAt(stringBuilder.length( ) - 1);
      stringBuilder.append("\n");
      BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path+fileNameOutput)));
      out.append(stringBuilder);
      out.close( );
    }

  }
}
