package com.tomaszdziurko.realistic_husbandry.day_cycle;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class NewDayEventPublisher extends BukkitRunnable {

    private final World world;
    private final JavaPlugin javaPlugin;
    private long lastDay;

    public NewDayEventPublisher(World world, JavaPlugin javaPlugin) {
        this.world = world;
        this.lastDay = world.getFullTime() / 24000;
        this.javaPlugin = javaPlugin;
    }

    @Override
    public void run() {
        long currentTotalTime = world.getFullTime();
        long currentDay = currentTotalTime / 24000;

        if (currentDay > lastDay) {
            NewDayEvent event = new NewDayEvent(world, currentDay);
            Bukkit.getPluginManager().callEvent(event);

            lastDay = currentDay;
            javaPlugin.getLogger().info("Day number " + lastDay + " started at world " + world.getName());
        }

    }

}
