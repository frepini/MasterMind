package com.francesco.mastermind;

import java.util.*;

/**
 * Class 'Game' that represents a game of mastermind.
 * Since every object of the 'Game' class is a game it has 3 fields:
 * - the color sequence to guess (<code>correct</code>)
 * - the list of attempts that have been submitted (<code>attempts</code>)
 * - the count of attempts that have been submitted (<code>attemptsCount</code>)
 */
public class Game {
    final private ColorSequence correct;
    final private List<Attempt> attempts;
    private int attemptsCount;

    /**
     * Constructor with no parameters that initializes the 3 fields:
     * - the sequence to guess is obtained randomly
     * - the attempts list is initialized
     * - the attempts count is set to 0
     */
    public Game() {
        correct = ColorSequence.getRandomColorSequence();
        attempts = new ArrayList<>();
        attemptsCount = 0;
    }

    /**
     * Returns the correct sequence of the game.
     * This method is fundamental because when the player wants to end the game, we need the correct sequence
     * to be displayed. With this method we obtain that sequence in order to show it on screen.
     * @return the correct sequence of the game
     */
    public ColorSequence getCorrectSequence() {
        return correct;
    }

    /**
     * Returns the list of attempts submitted
     * @return the list of attempts submitted
     */
    public List<Attempt> getAttempts() {
        return attempts;
    }

    /**
     * Returns the number of attempts submitted
     * @return the number of attempts submitted
     */
    public int getAttemptsCount() {
        return attemptsCount;
    }

    /**
     * Given a color sequence that represent the guess (color sequence) the player is making, this method adds the attempt corresponding to
     * that sequence to the list, calculating the number of right colors in right position and the number of
     * right colors in wrong position. It also increments the number of attempts submitted and returns the attempt object
     * corresponding to the color sequence passed.
     * @param guess the guess the user is making, which is a color sequence
     * @return the attempt object corresponding to the color sequence passed
     */
    public Attempt makeAttempt(ColorSequence guess) {
        int countRightColorRightPosition = correct.countRightColorRightPosition(guess);
        int countRightColorWrongPosition = correct.countRightColorWrongPosition(guess);
        Attempt attempt = new Attempt(guess, countRightColorRightPosition, countRightColorWrongPosition);
        attempts.addFirst(attempt);
        attemptsCount++;
        return attempt;
    }

    /**
     * Checks if the color sequence passed (<code>guess</code>) is correct or not.
     * To check if it's correct there 2 possible ways:
     * 1. using the equals method (as below)
     * 2. checking that the number of right colors in right position is equal to the length of the color sequence
     * @param guess the guess the player is making, which is a color sequence
     * @return true if the color sequence passed (<code>guess</code>) is correct, false otherwise
     */
    public boolean isCorrect(ColorSequence guess) {
        return this.correct.equals(guess);
    }
}
