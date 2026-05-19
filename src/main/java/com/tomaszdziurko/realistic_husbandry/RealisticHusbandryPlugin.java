package com.tomaszdziurko.realistic_husbandry;

import com.tomaszdziurko.realistic_husbandry.day_cycle.NewDayEventPublisher;
import com.tomaszdziurko.realistic_husbandry.listeners.AnimalStartingWeightInitializer;
import com.tomaszdziurko.realistic_husbandry.listeners.common.HusbandryAnimalUtils;
import com.tomaszdziurko.realistic_husbandry.listeners.growth.DailyGrowthAnimalSimulator;
import com.tomaszdziurko.realistic_husbandry.listeners.looting.LootAdjusterForSlaughteredHusbandryAnimals;
import com.tomaszdziurko.realistic_husbandry.listeners.state_inspector.HusbandryAnimalStateInspector;
import java.util.List;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class RealisticHusbandryPlugin extends JavaPlugin {

    private static final int BSTATS_PLUGIN_ID = 31425;
    private static final String SPIGOT_RESOURCE_ID = "135336";

    private RealisticHusbandryConfiguration configuration;
    private HusbandryAnimalUtils utils;

    @Override
    public void onEnable() {
        configuration = new RealisticHusbandryConfiguration();
        utils = new HusbandryAnimalUtils(configuration, getLogger());
        getLogger().info("Configuration values: " + configuration.toString());

        getServer().getPluginManager().registerEvents(new AnimalStartingWeightInitializer(configuration, utils, getLogger()), this);
        getServer().getPluginManager().registerEvents(new LootAdjusterForSlaughteredHusbandryAnimals(configuration, utils, getLogger()), this);
        getServer().getPluginManager().registerEvents(new DailyGrowthAnimalSimulator(configuration, utils, getLogger()), this);
        getServer().getPluginManager().registerEvents(new HusbandryAnimalStateInspector(configuration, utils, getLogger()), this);

        registerDayCycleEventPublisher();
        registerBstatsMetrics();
        new UpdateChecker(this, SPIGOT_RESOURCE_ID).checkForNewerVersion();
    }

    private void registerDayCycleEventPublisher() {
        List<World> worlds = Bukkit.getWorlds();
        for (World world : worlds) {
            new NewDayEventPublisher(world, this).runTaskTimer(this, 0L, 20L);
        }
    }

    private void registerBstatsMetrics() {
        Metrics metrics = new Metrics(this, BSTATS_PLUGIN_ID);
    }

    @Override
    public void onDisable() {

    }

}
