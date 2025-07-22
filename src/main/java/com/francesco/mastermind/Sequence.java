package com.francesco.mastermind;

/**
 * Abstract class 'Sequence' that represents a generic sequence of elements.
 * It has only one method with implementation, and that is checkIndex()
 */
public abstract class Sequence {
    /**
     * Checks if tha passed index is valid for the sequence (i.e. it is included between 0 and length - 1).
     * If <code>index</code> is not correct, it throws an IndexOutOfBoundException
     * @param length length of the sequence
     * @param index index to check
     * @throws IndexOutOfBoundsException the exception that is thrown when <code>index</code> is not correct
     */
    public void checkIndex(int length, int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException();
        }
    }
}
