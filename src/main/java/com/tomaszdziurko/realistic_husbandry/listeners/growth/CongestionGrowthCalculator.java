package com.tomaszdziurko.realistic_husbandry.listeners.growth;


import com.tomaszdziurko.realistic_husbandry.RealisticHusbandryConfiguration;
import com.tomaszdziurko.realistic_husbandry.listeners.common.HusbandryAnimalUtils;
import java.util.logging.Logger;
import org.bukkit.entity.Breedable;

public class CongestionGrowthCalculator {

    private final RealisticHusbandryConfiguration configuration;
    private final HusbandryAnimalUtils utils;
    private final Logger logger;

    public CongestionGrowthCalculator(RealisticHusbandryConfiguration configuration, HusbandryAnimalUtils utils, Logger logger) {
        this.configuration = configuration;
        this.utils = utils;
        this.logger = logger;
    }

    double calculateCongestionModifier(Breedable animal) {
        return 1.0;
    }
}
