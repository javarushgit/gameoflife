package org.example;

import java.io.*;

public class GameOfLife {
    private int iterator;
    private int x;
    private int y;
    private char[][] field;
    File input;
    File output;

    // TODO:
    public void game(String fileNameInput, String fileNameOutput) {
        input = new File("src\\test\\resources", fileNameInput);
        output = new File("src\\test\\resources", fileNameOutput);
        readFileInput(input);
        for (int i = 0; i < iterator; i++) {
            oneLifeCycle();
        }
        fillOutputFile(output);
    }

    private void oneLifeCycle() {
        char[][] newField = new char[x][y];
        int neighbors;
        char element;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                neighbors = countNeighbor(i, j);
                element = field[i][j];
                if (isAlive(element)) {
                    neighbors--;
                    if (neighbors < 2 || neighbors > 3) {
                        element = 'O';
                    }
                } else {
                    if (neighbors == 3) {
                        element = 'X';
                    }
                }
                newField[i][j] = element;
            }
        }
        field = newField;
    }

    private int countNeighbor(int i, int j) {
        int count = 0;
        int elementX;
        int elementY;
        for (int k = -1; k < 2; k++) {
            for (int l = -1; l < 2; l++) {
                elementX = (i + k + x) % x;
                elementY = (j + l + y) % y;
                if (field[elementX][elementY] == 'X') {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isAlive(char element) {
        return element == 'X';
    }

    private void workFirstLine(String line) {
        String[] array = line.split(",");
        x = Integer.parseInt(array[0]);
        y = Integer.parseInt(array[1]);
        iterator = Integer.parseInt(array[2]);
    }

    private void initializeArray() {
        field = new char[x][y];
    }

    private void readFileInput(File input) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(input))) {
            workFirstLine(bufferedReader.readLine());
            initializeArray();
            int i = 0;
            while (bufferedReader.ready()) {
                char[] line = bufferedReader.readLine().toCharArray();
                int j = 0;
                for (char element :
                        line) {
                    if (element == 'O' || element == 'X')
                        field[i][j++] = element;
                }
                i++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillOutputFile(File output) {
        try (FileWriter fileWriter = new FileWriter(output)) {
            char element;
            for (int i = 0; i < field.length; i++) {
                for (int j = 0; j < field[i].length; j++) {
                    element = field[i][j];
                    fileWriter.write(element + ' ');
                }
                if (i < field.length - 1)
                    fileWriter.write("\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        GameOfLife gameOfLife = new GameOfLife();
        gameOfLife.game("FileInput.txt", "FileOutput.txt");
    }
}