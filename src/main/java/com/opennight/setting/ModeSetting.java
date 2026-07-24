package com.opennight.setting;

import java.util.Arrays;
import java.util.List;

public class ModeSetting extends Setting<String> {
    private final List<String> options;

    public ModeSetting(String name, String... options) {
        super(name, options.length > 0 ? options[0] : "");
        this.options = Arrays.asList(options);
    }

    public List<String> getOptions() {
        return options;
    }

    public void next() {
        int i = options.indexOf(value);
        value = options.get((i + 1) % options.size());
    }
}
