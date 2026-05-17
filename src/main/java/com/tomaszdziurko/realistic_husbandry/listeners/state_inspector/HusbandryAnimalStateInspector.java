package com.tomaszdziurko.realistic_husbandry.listeners.state_inspector;

import com.tomaszdziurko.realistic_husbandry.RealisticHusbandryConfiguration;
import com.tomaszdziurko.realistic_husbandry.listeners.common.AbstractRealisticHusbandryListener;
import com.tomaszdziurko.realistic_husbandry.listeners.common.HusbandryAnimalUtils;
import java.util.logging.Logger;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Breedable;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

public class HusbandryAnimalStateInspector extends AbstractRealisticHusbandryListener {

    public HusbandryAnimalStateInspector(RealisticHusbandryConfiguration configuration, HusbandryAnimalUtils utils, Logger logger) {
        super(configuration, utils, logger);
    }

    @EventHandler
    public void checkAnimalWeightOnRightClick(PlayerInteractEntityEvent event) {

        if (event.getHand() == EquipmentSlot.OFF_HAND) {
            return;
        }

        Entity clickedEntity = event.getRightClicked();
        if (entitySupportsRealisticHusbandry(clickedEntity)) {
            int weight = getUtils().getWeight(clickedEntity);
            String text = "Animal: " + clickedEntity.getClass().getSimpleName() + "(id: " + clickedEntity.getEntityId() +
                    "), health: " + getUtils().percentageOfFullHealth((Breedable) clickedEntity) + "%, weight: " + weight;
            event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(text));
        }
    }

}
