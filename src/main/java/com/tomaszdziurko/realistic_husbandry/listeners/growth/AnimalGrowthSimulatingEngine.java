package com.tomaszdziurko.realistic_husbandry.listeners.growth;

import static java.text.MessageFormat.format;

import com.tomaszdziurko.realistic_husbandry.RealisticHusbandryConfiguration;
import com.tomaszdziurko.realistic_husbandry.listeners.common.HusbandryAnimalUtils;
import java.util.logging.Logger;
import org.bukkit.entity.Breedable;

public class AnimalGrowthSimulatingEngine {

    private final RealisticHusbandryConfiguration configuration;
    private final HusbandryAnimalUtils utils;
    private final Logger logger;
    private final CongestionGrowthCalculator congestionGrowthCalculator;

    public AnimalGrowthSimulatingEngine(RealisticHusbandryConfiguration configuration, HusbandryAnimalUtils utils, Logger logger) {
        this.configuration = configuration;
        this.utils = utils;
        this.logger = logger;
        this.congestionGrowthCalculator = new CongestionGrowthCalculator(configuration, utils, logger);
    }

    public void growAnimal(Breedable animal) {
        if (utils.getWeight(animal) == 0 && animal.getAge() >= 0) {
            utils.setWeight(animal, configuration.getInitialAnimalWeight());
        }
        // if health <=60%, do not grow
        int healthPercentage = utils.percentageOfFullHealth(animal);
        if (healthPercentage <= 60) {
            return;
        }
        if (animal.getAge() < 0) {
            return;
        }

        double healthGrowthModifier = healthPercentage/100.0d;
        double congestionGrowthModifier = congestionGrowthCalculator.calculateCongestionModifier(animal, healthPercentage);

        double weightModifier = 1 + configuration.getBaseGrowModifier() * healthGrowthModifier*congestionGrowthModifier;
        logger.info(format("Animal {0}:{1} weight modified by {2}",
                animal.getClass().getSimpleName(),
                animal.getEntityId(),
                weightModifier
                )
        );
        utils.growAnimalWeightBy(weightModifier, animal);
    }


}
