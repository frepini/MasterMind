package com.francesco.mastermind;

import java.util.*;

public class Game {
    final private ColorSequence correct;
    final private List<Attempt> attempts;
    private int attemptsCount;

    public Game() {
        correct = ColorSequence.getRandomColorSequence();
        attempts = new ArrayList<>();
        attemptsCount = 0;
    }

    public ColorSequence getCorrectSequence() {
        return correct;
    }

    public List<Attempt> getAttempts() {
        return attempts;
    }

    public int getAttemptsCount() {
        return attemptsCount;
    }

    public Attempt makeAttempt(ColorSequence guess) {
        int countRightColorRightPosition = correct.countRightColorRightPosition(guess);
        int countRightColorWrongPosition = correct.countRightColorWrongPosition(guess);
        Attempt attempt = new Attempt(guess, countRightColorRightPosition, countRightColorWrongPosition);
        attempts.addFirst(attempt);
        attemptsCount++;
        return attempt;
    }

    public boolean isCorrect(ColorSequence guess) {
        return this.correct.equals(guess);
    }
}
