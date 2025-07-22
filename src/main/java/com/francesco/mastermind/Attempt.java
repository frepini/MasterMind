package com.francesco.mastermind;

/**
 * Class 'Attempt' that represents an attempt to guess the sequence.
 * Since it's a guess it has 3 attributes:
 * - the sequence
 * - the number of right colors in right position
 * - the number of right colors in wrong position
 */
public class Attempt {
    final private ColorSequence sequence;
    final private int countRightColorRightPosition;
    final private int countRightColorWrongPosition;

    /**
     * Constructor that initialize all the attributes of the attempt
     * @param sequence the sequence of the attempt
     * @param countRightColorRightPosition the number of right colors in right position
     * @param countRightColorWrongPosition the number of right colors in wrong position
     */
    public Attempt(ColorSequence sequence, int countRightColorRightPosition, int countRightColorWrongPosition) {
        this.sequence = sequence;
        this.countRightColorRightPosition = countRightColorRightPosition;
        this.countRightColorWrongPosition = countRightColorWrongPosition;
    }

    /**
     * Returns the sequence of the attempt
     * @return the sequence of the attempt
     */
    public ColorSequence getSequence() {
        return sequence;
    }

    /**
     * Returns the number of right colors in right position
     * @return the number of right colors in right position
     */
    public int getCountRightColorRightPosition() {
        return countRightColorRightPosition;
    }

    /**
     * Returns the number of right colors in wrong position
     * @return the number of right colors in wrong position
     */
    public int getCountRightColorWrongPosition() {
        return countRightColorWrongPosition;
    }

    @Override
    public String toString() {
        return sequence.toString() +
                " | Numero di colori giusti nella posizione giusta: " + countRightColorRightPosition +
                " | Numero di colori giusti nella posizione sbagliata: " + countRightColorWrongPosition;
    }
}
