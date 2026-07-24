package com.opennight.setting;

public abstract class Setting<T> {
    protected final String name;
    protected T value;
    protected boolean visible;

    public Setting(String name, T defaultValue) {
        this.name = name;
        this.value = defaultValue;
        this.visible = true;
    }

    public String getName() {
        return name;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T v) {
        this.value = v;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean v) {
        this.visible = v;
    }
}
