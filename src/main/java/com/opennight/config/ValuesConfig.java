package com.opennight.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.opennight.NightNeo;
import com.opennight.manager.ModuleManager;
import com.opennight.modules.Module;
import com.opennight.settings.Setting;
import com.opennight.settings.impl.BooleanSetting;
import com.opennight.settings.impl.ModeSetting;
import com.opennight.settings.impl.MultiSelectSetting;
import com.opennight.settings.impl.NumberSetting;

public class ValuesConfig extends Config {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public ValuesConfig() {
        super("values.json");
    }

    @Override
    public void read(BufferedReader reader) throws IOException {
        JsonObject root = GSON.fromJson(reader, JsonObject.class);
        if (root == null) return;
        ModuleManager mm = NightNeo.getInstance().getModuleManager();
        for (Module module : mm.getModules()) {
            JsonObject modObj = root.getAsJsonObject(module.getName());
            if (modObj == null) continue;
            for (Setting<?> setting : module.getSettings()) {
                JsonElement elem = modObj.get(setting.getName());
                if (elem == null) continue;
                if (setting instanceof BooleanSetting bs) {
                    bs.setValue(elem.getAsBoolean());
                } else if (setting instanceof NumberSetting ns) {
                    ns.setValue(elem.getAsDouble());
                } else if (setting instanceof ModeSetting ms) {
                    ms.setValue(elem.getAsString());
                }
            }
        }
    }

    @Override
    public void save(BufferedWriter writer) throws IOException {
        JsonObject root = new JsonObject();
        ModuleManager mm = NightNeo.getInstance().getModuleManager();
        for (Module module : mm.getModules()) {
            JsonObject modObj = new JsonObject();
            for (Setting<?> setting : module.getSettings()) {
                if (setting instanceof BooleanSetting bs) {
                    modObj.addProperty(setting.getName(), bs.getValue());
                } else if (setting instanceof NumberSetting ns) {
                    modObj.addProperty(setting.getName(), ns.getValue());
                } else if (setting instanceof ModeSetting ms) {
                    modObj.addProperty(setting.getName(), ms.getValue());
                }
            }
            root.add(module.getName(), modObj);
        }
        GSON.toJson(root, writer);
    }
}
