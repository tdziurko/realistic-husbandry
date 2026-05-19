package com.tomaszdziurko.realistic_husbandry;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class UpdateChecker {

    private final JavaPlugin plugin;
    private final String resourceId;

    public UpdateChecker(JavaPlugin plugin, String spigotResourceId) {
        this.plugin = plugin;
        this.resourceId = spigotResourceId;
    }

    public void checkForNewerVersion() {
        getVersion(latestVersion -> {
            String currentVersion = plugin.getDescription().getVersion();
            if (currentVersion.equals(latestVersion)) {
                plugin.getLogger().info("You are using the latest " + currentVersion + " version of plugin");
            } else {
                plugin.getLogger().info("There is a new update available! Your version is " + currentVersion + ", latest version is " + latestVersion);
                plugin.getLogger().info("Get latest version from (SpigotMC): https://www.spigotmc.org/resources/realistichusbandry.135336/");
                plugin.getLogger().info("Get latest version from (Modrinth): https://modrinth.com/plugin/realistichusbandry");
            }
        });
    }

    private void getVersion(final Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
            try (InputStream is = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + this.resourceId + "/~").openStream(); Scanner scann = new Scanner(is)) {
                if (scann.hasNext()) {
                    consumer.accept(scann.next());
                }
            } catch (IOException e) {
                plugin.getLogger().info("Unable to check for updates: " + e.getMessage());
            }
        });
    }

}
