package com.opennight.network.webui;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import com.opennight.NightNeo;
import com.opennight.exception.ModuleNotFoundException;
import com.opennight.modules.Module;
import com.opennight.modules.impl.world.WebUI;
import com.opennight.utils.render.TextureUtil;

public class ToggleModuleHandler extends AbstractHttpHandler {

    @Override
    public int handleRequest(InputStream in, OutputStream out, HttpExchange exchange) throws Throwable {
        Map<String, String> query = TextureUtil.parseQueryString(exchange.getRequestURI().getQuery());
        Map<String, Object> response = new HashMap<>();
        String reason = null;
        boolean state;
        boolean success;
        if (query.containsKey("module") && query.containsKey("state")) {
            try {
                Module module = lookupModule(query.get("module"));
                if (module == null) {
                    state = false;
                    success = false;
                    reason = "找不到模块";
                } else if (module instanceof WebUI) {
                    state = true;
                    success = true;
                } else {
                    module.setEnabled(Boolean.parseBoolean(query.get("state")));
                    state = module.isEnabled();
                    success = true;
                }
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                state = false;
                success = false;
                reason = throwable.toString();
            }
        } else {
            state = false;
            success = false;
            reason = "参数不足";
        }
        response.put("success", success);
        response.put("reason", reason);
        response.put("result", state);
        out.write(new Gson().toJson(response).getBytes(StandardCharsets.UTF_8));
        return 200;
    }

    private static Module lookupModule(String name) {
        try {
            return NightNeo.instance.getModuleManager().getModule(name);
        } catch (ModuleNotFoundException e) {
            return null;
        }
    }
}
