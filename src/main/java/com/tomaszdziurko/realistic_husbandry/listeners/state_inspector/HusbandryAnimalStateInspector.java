package com.tomaszdziurko.realistic_husbandry.listeners.state_inspector;

import com.tomaszdziurko.realistic_husbandry.RealisticHusbandryConfiguration;
import com.tomaszdziurko.realistic_husbandry.listeners.common.AbstractRealisticHusbandryListener;
import com.tomaszdziurko.realistic_husbandry.listeners.common.HusbandryAnimalUtils;
import java.text.MessageFormat;
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
        if (getUtils().entitySupportsRealisticHusbandry(clickedEntity)) {
            int weight = getUtils().getWeight(clickedEntity);
            int nearbyAnimals = getUtils().countHospitableAnimalsAround((Breedable)clickedEntity);
            String animalDetails = MessageFormat.format("Animal: {0} (id: {1}), health: {2}%, weight: {3}, nearby animals: {4}",
                    clickedEntity.getClass().getSimpleName(),
                    clickedEntity.getEntityId(),
                    getUtils().percentageOfFullHealth((Breedable) clickedEntity),
                    weight,
                    nearbyAnimals
            );
            event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(animalDetails));
        }
    }

}
