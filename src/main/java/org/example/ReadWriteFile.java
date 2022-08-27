package org.example;

import java.io.*;

public class ReadWriteFile {

    private int rowCells;
    private int columnCells;
    private int lifeCount;

    public char[][] reader(String filename) {
        char[][] cells = null;
        try (BufferedReader bf = new BufferedReader(new FileReader(filename))) {
            String[] firstLine = bf.readLine().split(",");
            rowCells = Integer.parseInt(firstLine[0]);
            columnCells = Integer.parseInt(firstLine[1]);
            lifeCount = Integer.parseInt(firstLine[2]);
            cells = new char[rowCells][columnCells];
            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells[0].length; j++) {
                    cells[i][j] = (char) bf.read();
                    bf.read();
                }
                bf.read();
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
        return cells;
    }

    public void writer(char[][] cells, String fileName) {
        try (BufferedWriter bf = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells[0].length; j++) {
                    bf.write(cells[i][j]);
                    if (j < cells[0].length - 1) {
                        bf.write(' ');
                    }
                }
                bf.write('\n');
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    public int getRowCells() {
        return rowCells;
    }
    public int getColumnCells() {
        return columnCells;
    }
    public int getLifeCount() {
        return lifeCount;
    }

}
