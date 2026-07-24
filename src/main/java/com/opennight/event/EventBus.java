package com.opennight.event;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventBus {

    private final Map<Object, List<Handler>> registry = new ConcurrentHashMap<>();

    public void register(Object listener) {
        List<Handler> handlers = new CopyOnWriteArrayList<>();
        for (Method method : listener.getClass().getDeclaredMethods()) {
            EventTarget annotation = method.getAnnotation(EventTarget.class);
            if (annotation == null) continue;
            Class<?>[] params = method.getParameterTypes();
            if (params.length != 1) continue;
            if (!Event.class.isAssignableFrom(params[0])) continue;
            method.setAccessible(true);
            handlers.add(new Handler(listener, method, annotation.value()));
        }
        if (!handlers.isEmpty()) {
            registry.put(listener, handlers);
        }
    }

    public void unregister(Object listener) {
        registry.remove(listener);
    }

    public void call(Event event) {
        List<Handler> sorted = new ArrayList<>();
        for (List<Handler> entry : registry.values()) {
            for (Handler handler : entry) {
                if (handler.targetType.isAssignableFrom(event.getClass())) {
                    sorted.add(handler);
                }
            }
        }
        sorted.sort(Comparator.comparingInt(h -> -h.priority));

        if (event instanceof Cancellable) {
            Cancellable c = (Cancellable) event;
            for (Handler handler : sorted) {
                try {
                    handler.method.invoke(handler.owner, event);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (c.isCancelled()) break;
            }
        } else {
            for (Handler handler : sorted) {
                try {
                    handler.method.invoke(handler.owner, event);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Handler {
        final Object owner;
        final Method method;
        final byte priority;
        final Class<?> targetType;

        Handler(Object owner, Method method, byte priority) {
            this.owner = owner;
            this.method = method;
            this.priority = priority;
            this.targetType = method.getParameterTypes()[0];
        }
    }
}
