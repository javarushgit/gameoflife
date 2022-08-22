package org.example;


public class GameOfLife {

  // TODO:
  public void game(String fileNameInput, String fileNameOutput) {
    Streams.inputFile(fileNameInput);
    Engine.lifeCycle();
    Streams.outputFile(fileNameOutput);
  }
  
}