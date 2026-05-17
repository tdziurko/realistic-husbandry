package com.tomaszdziurko.realistic_husbandry.listeners.looting;

import com.tomaszdziurko.realistic_husbandry.RealisticHusbandryConfiguration;
import com.tomaszdziurko.realistic_husbandry.listeners.common.AbstractRealisticHusbandryListener;
import com.tomaszdziurko.realistic_husbandry.listeners.common.HusbandryAnimalUtils;
import java.util.logging.Logger;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class LootAdjusterForSlaughteredHusbandryAnimals extends AbstractRealisticHusbandryListener {

    public LootAdjusterForSlaughteredHusbandryAnimals(RealisticHusbandryConfiguration configuration, HusbandryAnimalUtils utils, Logger logger) {
        super(configuration, utils, logger);
    }

    @EventHandler
    public void onEntityDeathEvent(EntityDeathEvent event) {
        Player killer = event.getEntity().getKiller();
        if (entitySupportsRealisticHusbandry(event.getEntity(), killer)) {

            int weight = getUtils().getWeight(event.getEntity());
            if (weight > 0) {
                for (ItemStack drop : event.getDrops()) {
                    double dropMultiplied = (double) weight /getConfig().getInitialAnimalWeight();
                    String killerName = killer != null ? killer.getName() : "Environment";
                    getLogger().info("Loot multiplier for animal killed by " + killerName + ": " + dropMultiplied);
                    drop.setAmount((int) (drop.getAmount() * dropMultiplied));
                }
            }

        }
    }

}

