package com.opennight;

import com.opennight.event.EventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.IEventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod("night")
public class NightNeo {
    public static final Logger LOG = LoggerFactory.getLogger("OpenNIGHT");
    public static final EventBus eventBus = new EventBus();
    public static NightNeo instance;
    public static boolean isMCPMapped = false;

    public static boolean isReady() { return instance != null; }
    public EventBus getEventBus() { return eventBus; }

    public NightNeo(IEventBus modBus) {
        instance = this;
        LOG.info("OpenNIGHT v1.0-Neo loading on MC 1.21.8");
        new NightEventHandler();
        registerModules();
    }

        private void registerModules() {}
