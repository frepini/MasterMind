package com.francesco.mastermind;

/**
 * enum Color that lists the possible color of each element of a sequence.
 * Every Color has:
 * - name
 * - hex
 */
public enum Color {
    ARANCIONE("#FF7700"),
    BIANCO("#FFFFFF"),
    BLU("#00C8FF"),
    FUCSIA("#F203FF"),
    GIALLO("#FFDD00"),
    ROSSO("#FF0000"),
    VERDE("#00A579"),
    VIOLA("#6A00FF");
    /* english version
    BLUE("#00C8FF"),
    FUCHSIA("#F203FF"),
    GREEN("#00A579"),
    ORANGE("#FF7700"),
    PURPLE("#6A00FF"),
    RED("#FF0000"),
    WHITE("#FFFFFF"),
    YELLOW("#FFDD00");
     */

    private final String hex;

    /**
     * Constructor for color object: it simply set hex for every color
     * @param hex hex code of the color
     */
    Color(String hex) {
        this.hex = hex;
    }

    /**
     * Returns the hex code of the color
     * @return the hex code of the color
     */
    public String getHex() {
        return hex;
    }
}