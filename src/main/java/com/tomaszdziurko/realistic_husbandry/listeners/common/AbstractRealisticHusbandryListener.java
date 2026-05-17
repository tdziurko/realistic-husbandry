package com.tomaszdziurko.realistic_husbandry.listeners.common;

import com.tomaszdziurko.realistic_husbandry.RealisticHusbandryConfiguration;
import java.util.logging.Logger;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public abstract class AbstractRealisticHusbandryListener implements Listener {

    private final RealisticHusbandryConfiguration configuration;
    private final HusbandryAnimalUtils utils;
    private final Logger logger;

    public AbstractRealisticHusbandryListener(RealisticHusbandryConfiguration configuration, HusbandryAnimalUtils utils, Logger logger) {
        this.configuration = configuration;
        this.utils = utils;
        this.logger = logger;
    }

    protected Logger getLogger() {
        return logger;
    }

    protected boolean entitySupportsRealisticHusbandry(LivingEntity entity, Player killer) {
        return getUtils().entitySupportsRealisticHusbandry(entity);
    }

    protected RealisticHusbandryConfiguration getConfig() {
        return configuration;
    }

    protected HusbandryAnimalUtils getUtils() {
        return utils;
    }
}
