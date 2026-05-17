package com.tomaszdziurko.realistic_husbandry.listeners.common;

import com.tomaszdziurko.realistic_husbandry.RealisticHusbandryConfiguration;
import java.util.logging.Logger;
import org.bukkit.entity.AbstractCow;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.Breedable;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
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
        return entitySupportsRealisticHusbandry(entity);
    }

    protected boolean entitySupportsRealisticHusbandry(Entity entity) {
        if (!(entity instanceof Breedable)) {
            return false;
        }

        Class<? extends Entity> entityClass = entity.getClass();
        return AbstractCow.class.isAssignableFrom(entityClass)
                || AbstractHorse.class.isAssignableFrom(entityClass)
                || Chicken.class.isAssignableFrom(entityClass)
                || Pig.class.isAssignableFrom(entityClass)
                || Sheep.class.isAssignableFrom(entityClass)
                ;
    }

    protected RealisticHusbandryConfiguration getConfig() {
        return configuration;
    }

    protected HusbandryAnimalUtils getUtils() {
        return utils;
    }
}
