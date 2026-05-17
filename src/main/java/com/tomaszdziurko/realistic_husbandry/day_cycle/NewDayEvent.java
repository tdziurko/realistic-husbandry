package com.tomaszdziurko.realistic_husbandry.day_cycle;

import org.bukkit.World;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class NewDayEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();
    private final World world;
    private final long dayNumber;

    public NewDayEvent(World world, long dayNumber) {
        this.world = world;
        this.dayNumber = dayNumber;
    }

    public World getWorld() {
        return world;
    }

    public long getDayNumber() {
        return dayNumber;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}


