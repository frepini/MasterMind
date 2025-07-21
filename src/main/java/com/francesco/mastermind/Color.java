package com.francesco.mastermind;


public enum Color {
    BLUE("#00C8FF"),
    FUCHSIA("#F203FF"),
    GREEN("#00A579"),
    ORANGE("#FF7700"),
    PURPLE("#6A00FF"),
    RED("#FF0000"),
    WHITE("#FFFFFF"),
    YELLOW("#FFDD00");

    private final String hex;

    Color(String hex) {
        this.hex = hex;
    }

    public String getHex() {
        return hex;
    }

    public String getCssClassName() {
        return "color-" + this.name().toLowerCase();
    }
}