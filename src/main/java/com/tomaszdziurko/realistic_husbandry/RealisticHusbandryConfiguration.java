package com.tomaszdziurko.realistic_husbandry;

import org.bukkit.NamespacedKey;

public class RealisticHusbandryConfiguration {

    private final String pluginName;
    private final int initialAnimalWeight;
    private final int minimumAllowedWeight;
    private final int maximumAllowedWeight;
    private final NamespacedKey weightPropertyKey;
    private final double baseGrowModifier;


    public RealisticHusbandryConfiguration() {
        this.pluginName = "RealisticHusbandry";
        this.initialAnimalWeight = 1000;
        this.minimumAllowedWeight = 250;
        this.maximumAllowedWeight = 50000;
        this.weightPropertyKey = new NamespacedKey(pluginName.toLowerCase(), "weight");
        this.baseGrowModifier = 0.25;
    }

    public String getPluginName() {
        return pluginName;
    }

    public int getInitialAnimalWeight() {
        return initialAnimalWeight;
    }

    public int getMinimumAllowedWeight() {
        return minimumAllowedWeight;
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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RealisticHusbandryConfiguration{");
        sb.append("pluginName='").append(pluginName).append('\'');
        sb.append(", initialAnimalWeight=").append(initialAnimalWeight);
        sb.append(", maximumAllowedWeight=").append(maximumAllowedWeight);
        sb.append(", weightPropertyKey=").append(weightPropertyKey);
        sb.append(", baseGrowModifier=").append(baseGrowModifier);
        sb.append('}');
        return sb.toString();
    }

}
