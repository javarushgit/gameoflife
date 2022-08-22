package domain;

public class Engine {
    public Engine() {
    }

    static int y;
    static int x;
    static int numberOfLives;
    static char[][] fieldOfLife;

    public static void setFieldOfLife(char[][] fieldOfLife) {
        Engine.fieldOfLife = fieldOfLife;
    }

    public static char[][] getFieldOfLife() {
        return fieldOfLife;
    }

    public static int getY() {
        return y;
    }

    public static int getX() {
        return x;
    }

    public static void setY(int vertically) {
        y = vertically;
    }

    public static void setX(int horizontally) {
        x = horizontally;
    }

    public static void setNumberOfLives(int numberOL) {
        numberOfLives = numberOL;
    }


    public static void lifeCycle() {
        char[][] newLife = new char[y][x];
        for (int life = 0; life < numberOfLives; life++) {

            for (int i = 0; i < y; i++) {

                for (int j = 0; j < x; j++) {

                    newLife[i][j] = weСheckЕheМiability(i, j);

                }

            } fieldOfLife = newLife;
        }


    }


    static char weСheckЕheМiability(int v, int h) {
        int lifeСounter = 0;
        String celuli = String.valueOf(fieldOfLife[v][h]);
        String tmp = "";
        int verticali;
        int horizontali;
        if (celuli.equals("X")) {
            lifeСounter = lifeСounter - 1;
        }
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                verticali = (v + i + y) % y;
                horizontali = (h + j + x) % x;
                tmp = String.valueOf(fieldOfLife[verticali][horizontali]);
                if (tmp.equals("X")) {
                    lifeСounter++;
                } else {
                    continue;
                }
            }
        }
        if (celuli.equals("O")) {
            if (lifeСounter == 3) {
                return 'X';
            } else {
                return 'O';
            }
        } else if (lifeСounter > 3 || lifeСounter < 2) {
            return 'O';
        }
        return 'X';
    }
}





