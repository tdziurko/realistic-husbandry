package com.tomaszdziurko.realistic_husbandry;

import org.bukkit.NamespacedKey;

public class RealisticHusbandryConfiguration {

    private final String pluginName;
    private final int initialAnimalWeight;
    private final int maximumAllowedWeight;
    private final NamespacedKey weightPropertyKey;
    private final double baseGrowModifier;


    public RealisticHusbandryConfiguration() {
        this.pluginName = "RealisticHusbandry";
        this.initialAnimalWeight = 1000;
        this.maximumAllowedWeight = 50000;
        this.weightPropertyKey = new NamespacedKey(pluginName.toLowerCase(), "weight");
        this.baseGrowModifier = 0.3;
    }

    public String getPluginName() {
        return pluginName;
    }

    public int getInitialAnimalWeight() {
        return initialAnimalWeight;
    }

    public int getMaximumAllowedWeight() {
        return maximumAllowedWeight;
    }

    public NamespacedKey getWeightPropertyKey() {
        return weightPropertyKey;
    }

    public double getBaseGrowModifier() {
        return baseGrowModifier;
    }

}
