package com.tomaszdziurko.realistic_husbandry.listeners;

import static java.text.MessageFormat.format;

import com.tomaszdziurko.realistic_husbandry.RealisticHusbandryConfiguration;
import com.tomaszdziurko.realistic_husbandry.listeners.common.AbstractRealisticHusbandryListener;
import com.tomaszdziurko.realistic_husbandry.listeners.common.HusbandryAnimalUtils;
import java.util.logging.Logger;
import org.bukkit.entity.Breedable;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.entity.EntityTransformEvent;
import org.bukkit.event.world.ChunkLoadEvent;

public class AnimalStartingWeightInitializer extends AbstractRealisticHusbandryListener {

    public static final int ACCEPTED_EXECUTION_TIME = 250;

    public AnimalStartingWeightInitializer(RealisticHusbandryConfiguration configuration, HusbandryAnimalUtils utils, Logger logger) {
        super(configuration, utils, logger);
    }

    @EventHandler
    public void initializeWeightForBreededAnimals(EntityBreedEvent event) {
        Entity entity = event.getEntity();
        if (!getUtils().entitySupportsRealisticHusbandry(entity)) {
            return;
        }
        getUtils().setInitialWeight((Breedable)event.getEntity());
    }

    @EventHandler
    public void initializeWeightForSpawnedAnimals(CreatureSpawnEvent event) {
        Entity entity = event.getEntity();
        if (!getUtils().entitySupportsRealisticHusbandry(entity)) {
            return;
        }
        getUtils().setInitialWeight((Breedable)event.getEntity());
    }

    @EventHandler
    public void initializeWeightForMushroomCows(EntityTransformEvent event) {
        Entity entity = event.getTransformedEntity();
        if (!getUtils().entitySupportsRealisticHusbandry(entity)) {
            return;
        }
        getUtils().setInitialWeight((Breedable)event.getTransformedEntity());
    }

    @EventHandler
    public void initializeWeightForAnimalsOnLoadedChunk(ChunkLoadEvent event) {
        long start = System.currentTimeMillis();
        int adjustedAnimalsCounter = 0;
        int entitiesCounter = event.getChunk().getEntities().length;

        for (Entity entity : event.getChunk().getEntities()) {
            if (getUtils().entitySupportsRealisticHusbandry(entity)) {
                adjustedAnimalsCounter++;
                getUtils().setInitialWeight((Breedable)entity);
            }
        }
        long executionTimeInMillis = System.currentTimeMillis() - start;

        if (executionTimeInMillis >= ACCEPTED_EXECUTION_TIME) {
            getLogger().warning(
                    format("Setting weight took on ChunkLoadEvent took {0} ms, all entities: {1}, husbandry entities: {2}",
                    executionTimeInMillis, entitiesCounter, adjustedAnimalsCounter
                    )
            );
        }

    }

}
