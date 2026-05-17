package com.tomaszdziurko.realistic_husbandry.listeners.common;

import static org.bukkit.attribute.Attribute.MAX_HEALTH;
import static org.bukkit.persistence.PersistentDataType.INTEGER;

import com.tomaszdziurko.realistic_husbandry.RealisticHusbandryConfiguration;
import java.util.Objects;
import org.bukkit.entity.AbstractCow;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.Breedable;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Sheep;
import org.bukkit.persistence.PersistentDataContainer;

public class HusbandryAnimalUtils {

    private final RealisticHusbandryConfiguration configuration;

    public HusbandryAnimalUtils(RealisticHusbandryConfiguration configuration) {
        this.configuration = configuration;
    }

    public int getWeight(Entity entity) {
        PersistentDataContainer pdc = entity.getPersistentDataContainer();
        return Objects.requireNonNullElse(pdc.get(configuration.getWeightPropertyKey(), INTEGER), 0);
    }

    public void setInitialWeight(Breedable entity) {
        PersistentDataContainer pdc = entity.getPersistentDataContainer();
        if (!pdc.has(configuration.getWeightPropertyKey()) && entity.getAge() >= 0) {
            pdc.set(configuration.getWeightPropertyKey(), INTEGER, configuration.getInitialAnimalWeight());
        }
    }

    public int percentageOfFullHealth(Breedable animal) {
        double currentHealth = animal.getHealth();
        double maxHealth = animal.getAttribute(MAX_HEALTH).getValue();
        return Double.valueOf(currentHealth / maxHealth * 100).intValue();
    }

    public void growAnimalWeightBy(double weightModifier, Breedable animal) {
        int currentWeight = getWeight(animal);
        if (currentWeight > 0) {
            int newWeight = (int) (currentWeight * weightModifier);
            setWeight(animal, newWeight);
        }
    }

    public void setWeight(Breedable entity, int weight) {
        PersistentDataContainer pdc = entity.getPersistentDataContainer();
        if (weight >= configuration.getMaximumAllowedWeight() ) {
            weight = configuration.getMaximumAllowedWeight();
        }
        if (entity.getAge() >= 0) {
            pdc.set(configuration.getWeightPropertyKey(), INTEGER, weight);
        }
    }

    public boolean entitySupportsRealisticHusbandry(Entity entity) {
        if (!(entity instanceof Breedable)) {
            return false;
        }

        Class<? extends Entity> entityClass = entity.getClass();
        return AbstractCow.class.isAssignableFrom(entityClass)
                || AbstractHorse.class.isAssignableFrom(entityClass)
                || Chicken.class.isAssignableFrom(entityClass)
                || Pig.class.isAssignableFrom(entityClass)
                || Sheep.class.isAssignableFrom(entityClass)
                ;
    }

}
