package com.opennight;

import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.bus.api.IEventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod("night")
public class NightNeo {
    public static final Logger LOG = LoggerFactory.getLogger("OpenNIGHT");
    public static NightNeo instance;

    public NightNeo(IEventBus modBus) {
        instance = this;
        LOG.info("OpenNIGHT v1.0-Neo loading on {} {}", 
                 FMLLoader.versionInfo().mcVersion(),
                 FMLLoader.versionInfo().neoForgeVersion());
    }
}
