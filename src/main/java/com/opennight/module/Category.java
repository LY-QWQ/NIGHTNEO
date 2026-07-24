package com.opennight.module;

public enum Category {
    COMBAT("Combat"),
    MOVEMENT("Movement"),
    PLAYER("Player"),
    RENDER("Render"),
    MISC("Misc"),
    EXPLOIT("Exploit");

    public final String name;

    Category(String name) {
        this.name = name;
    }
}
