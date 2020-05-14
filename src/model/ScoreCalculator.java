
package model;

import java.util.Arrays;

/**
 * Calculates the score.
 * 
 * @author Justin Neff
 * @version March 9th 2018
 *
 */
public final class ScoreCalculator {
    /**
     * Difficulty level.
     */
    private static int level = 1;
    /**
     * Total lines cleared.
     */
    private static int linesCleared;
    /**
     * Total score.
     */
    private static int score;
    /**
     * Point values for the number of lines cleared.
     */
    private static final int[] POINTS = {40, 100, 300, 1200};

    /**
     * Private constructor.
     */
    private ScoreCalculator() {

    }

    /**
     * New block points.
     */
    public static void newBlock() {
        score += 4;
    }

    /**
     * Set score, lines cleared and level with incoming integer.
     * 
     * @param theLines the number of lines cleared.
     */
    public static void addLines(final Integer[] theLines) {
        score += POINTS[theLines.length - 1] * level;
        linesCleared += theLines.length;
        level = linesCleared / 5 + 1;
    }
   

    /**
     * Returns the score.
     * 
     * @return score
     */
    public static Integer getScore() {

        return score;
    }

    /**
     * Returns the level.
     * 
     * @return level
     */
    public static Integer getLevel() {

        return level;
    }

    /**
     * Returns the total lines cleared.
     * 
     * @return lines cleared
     */
    public static Integer getLinesCleared() {

        return linesCleared;
    }

    /**
     * Resets the scores.
     */
    public static void resetScores() {
        linesCleared = 0;
        level = 1;
        score = 0;
    }
}
