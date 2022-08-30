package org.example;

public class GameOfLife {

    private char alive = 'X';
    private char dead = 'O';
    public char[][] cells;
    private final String OUTPUT_PATH = "target/test-classes/";
    private final String INPUT_PATH = "src/test/resources/";

    ReadWriteFile rwf = new ReadWriteFile();

    public void game(String inputFileName, String outputFileName){
        cells = rwf.reader(INPUT_PATH + inputFileName);
        for (int i = 0; i < rwf.getCycleNumber(); i++) {
            cells = GameCycle();
            rwf.writer(cells,OUTPUT_PATH + outputFileName);
        }
    }

    public char[][] GameCycle() {
        int countAliveNeighbor;
        int rowCells = rwf.getRowCells();
        int columnCells = rwf.getColumnCells();
        char[][] tempCells = new char[rowCells][columnCells];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                if (cells[i][j] == alive) {
                    countAliveNeighbor = CountNeighborAlive(i, j);
                    tempCells[i][j] = (countAliveNeighbor < 2 || countAliveNeighbor > 3) ? dead : alive;
                } else {
                    countAliveNeighbor = CountNeighborAlive(i, j);
                    tempCells[i][j] = (countAliveNeighbor == 3) ? alive : cells[i][j];
                }
            }
        }
        return cells = tempCells;
    }

    private int CountNeighborAlive(int i, int j) {
        int countAliveNeighbor = 0;
        for (int k = -1; k <= 1; k++) {
            for (int l = -1; l <= 1; l++) {
                int rowIndex = (i + k + rwf.getRowCells()) % rwf.getRowCells();
                int columnIndex = (j + l + rwf.getColumnCells()) % rwf.getColumnCells();
                if (cells[rowIndex][columnIndex] == alive) {
                    countAliveNeighbor++;
                }
            }
        }
        if (cells[i][j] == alive) countAliveNeighbor--;
        return countAliveNeighbor;
    }
}
