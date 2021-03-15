package org.paintFX;

public enum PaintMode {
    CLEAR("Clear"),
    FILLED("Filled"),
    BORDERED("Bordered"),
    FILLED_WITH_BORDER("Filled with border");

    private final String name;

    PaintMode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
