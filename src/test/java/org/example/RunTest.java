package org.example;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;


class RunTest {
    Run game = new Run();

    public boolean equalsFile(String expected, String result) {
        Stream<String> gameStreamInput = new BufferedReader(
            new InputStreamReader(ClassLoader.getSystemResourceAsStream(expected))).lines();
        List<String> gameListExpected = gameStreamInput.collect(Collectors.toList());
        ClassLoader classLoader = Run.class.getClassLoader();
        Stream<String> gameStreamResult = new BufferedReader(
            new InputStreamReader(classLoader.getSystemResourceAsStream(result))).lines();
        List<String> gameListResult = gameStreamResult.collect(Collectors.toList());
        return gameListExpected.equals(gameListResult);
    }

    @Test
    public void stableFigure() {
        game.run("inputStable1.txt", "outputStable1.txt");
        assertTrue(equalsFile("expectedStable1.txt", "outputStable1.txt"));
    }

    @Test
    public void stableFigure2() {
        game.run("inputStable2.txt", "outputStable2.txt");
        assertTrue(equalsFile("expectedStable2.txt", "outputStable2.txt"));
    }

    @Test
    public void oscillatorFigure() {
        game.run("inputOscillator.txt", "outputOscillator.txt");
        assertTrue(equalsFile("expectedOscillator.txt", "outputOscillator.txt"));
    }

    @Test
    public void oscillatorFigure2() {
        game.run("inputOscillator2.txt", "outputOscillator2.txt");
        assertTrue(equalsFile("expectedOscillator2.txt", "outputOscillator2.txt"));
    }

    @Test
    public void gliderFigureEasy() {
        game.run("inputGliderEasy.txt", "outputGliderEasy.txt");
        assertTrue(equalsFile("expectedGliderEasy.txt", "outputGliderEasy.txt"));
    }

    @Test
    public void gliderFigure() {
        game.run("inputGlider.txt", "outputGlider.txt");
        assertTrue(equalsFile("expectedGlider.txt", "outputGlider.txt"));
    }
}

