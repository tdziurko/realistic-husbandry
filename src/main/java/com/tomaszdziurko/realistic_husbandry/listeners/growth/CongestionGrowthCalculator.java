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

    double calculateCongestionModifier(Breedable animal, int healthPercentage) {

        int animalsNearBy = utils.countHospitableAnimalsAround(animal);
        double unhealthyAnimalCongestionVulnerabilityModifier = 2 - healthPercentage/100.0; // the more sick Animal, the worse effect of congestion is
        if (animalsNearBy <= 4) {
            return 1.5;
        } else if (animalsNearBy <= 5) {
            return 1.2;
        } else if (animalsNearBy <= 6) {
            return 1.0;
        } else if (animalsNearBy <= 7) {
            return 0.6;
        } else if (animalsNearBy <= 8) {
            return 0.2;
        } else if (animalsNearBy <= 10) {
            return -0.2 * unhealthyAnimalCongestionVulnerabilityModifier;
        } else if (animalsNearBy <= 12) {
            return -0.4 * unhealthyAnimalCongestionVulnerabilityModifier;
        } else if (animalsNearBy <= 15) {
            return -0.5 * unhealthyAnimalCongestionVulnerabilityModifier;
        } else if (animalsNearBy <= 18) {
            return -0.6 * unhealthyAnimalCongestionVulnerabilityModifier;
        } else if (animalsNearBy <= 20) {
            return -0.7 * unhealthyAnimalCongestionVulnerabilityModifier;
        } else if (animalsNearBy <= 25) {
            return -1.0 * unhealthyAnimalCongestionVulnerabilityModifier;
        } else {
            return -1.5 * unhealthyAnimalCongestionVulnerabilityModifier;
        }
    }

}
