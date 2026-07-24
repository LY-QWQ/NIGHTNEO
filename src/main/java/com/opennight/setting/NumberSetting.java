package com.opennight.setting;

public class NumberSetting extends Setting<Number> {
    private final double min;
    private final double max;
    private final double step;

    public NumberSetting(String name, double def, double min, double max, double step) {
        super(name, def);
        this.min = min;
        this.max = max;
        this.step = step;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public double getStep() {
        return step;
    }
}
