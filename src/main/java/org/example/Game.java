package org.example;

public class Game {

    private char alive = 'X';
    private char dead = 'O';
    public char[][] cells;
    public int rowCells;
    public int columnCells;

    public char[][] cycle() {
        int countAliveNeighbor;
        int rowCells = this.rowCells;
        int columnCells = this.columnCells;
        char[][] tempCells = new char[rowCells][columnCells];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                if (cells[i][j] == alive) {
                    countAliveNeighbor = isNeighborAlive(i, j);
                    tempCells[i][j] = (countAliveNeighbor < 2 || countAliveNeighbor > 3) ? dead : alive;
                } else {
                    countAliveNeighbor = isNeighborAlive(i, j);
                    tempCells[i][j] = (countAliveNeighbor == 3) ? alive : cells[i][j];
                }
            }
        }
        return cells = tempCells;
    }

    private int isNeighborAlive(int i, int j) {
        int countAliveNeighbor = 0;
        for (int k = -1; k <= 1; k++) {
            for (int l = -1; l <= 1; l++) {
                int rowIndex = (i + k + this.rowCells) % this.rowCells;
                int columnIndex = (j + l + this.columnCells) % this.columnCells;
                if (cells[rowIndex][columnIndex] == alive) {
                    countAliveNeighbor++;
                }
            }
        }
        if (cells[i][j] == alive) countAliveNeighbor--;
        return countAliveNeighbor;
    }
}
