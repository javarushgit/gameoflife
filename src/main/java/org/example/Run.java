package org.example;

public class Run {

    private final String OUTPUT_PATH = "target/test-classes/";
    private final String INPUT_PATH = "src/test/resources/";

    public void run(String inputFileName, String outputFileName){

        ReadWriteFile rwf = new ReadWriteFile();
        Game game = new Game();
        game.cells = rwf.reader(INPUT_PATH + inputFileName);
        game.rowCells = rwf.getRowCells();
        game.columnCells = rwf.getColumnCells();

        for (int i = 0; i < rwf.getLifeCount(); i++) {
            rwf.writer(game.cycle(),OUTPUT_PATH + outputFileName);
        }

    }
}
