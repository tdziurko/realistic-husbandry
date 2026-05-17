package com.tomaszdziurko.realistic_husbandry.listeners.growth;

import com.tomaszdziurko.realistic_husbandry.RealisticHusbandryConfiguration;
import com.tomaszdziurko.realistic_husbandry.day_cycle.NewDayEvent;
import com.tomaszdziurko.realistic_husbandry.listeners.common.AbstractRealisticHusbandryListener;
import com.tomaszdziurko.realistic_husbandry.listeners.common.HusbandryAnimalUtils;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import org.bukkit.entity.Breedable;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;

public class DailyGrowthAnimalSimulator extends AbstractRealisticHusbandryListener {

    private final AnimalGrowthSimulatingEngine growthEngine;

    public DailyGrowthAnimalSimulator(RealisticHusbandryConfiguration configuration, HusbandryAnimalUtils utils, Logger logger) {
        super(configuration, utils, logger);
        growthEngine = new AnimalGrowthSimulatingEngine(configuration, utils, logger);
    }

    @EventHandler
    public void simulateGrowthOnNewDay(NewDayEvent event) {
        long start = System.currentTimeMillis();

        Collection<Entity> breedableAnimals = event.getWorld().getEntitiesByClasses(Breedable.class);
        List<Entity> animalsToAdjustWeight = breedableAnimals
                .stream()
                .filter(entity1 -> getUtils().entitySupportsRealisticHusbandry(entity1))
                .toList();

        animalsToAdjustWeight.forEach(
                entity -> growthEngine.growAnimal((Breedable) entity)
        );

        long end = System.currentTimeMillis();
        getLogger().info("Adjusting weight for " + animalsToAdjustWeight.size() + " animals took " + (end-start) + "ms");
    }

}
