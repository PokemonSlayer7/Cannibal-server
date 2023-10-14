package com.iatethebible.test;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class BreadLightning implements Listener {
    @EventHandler
    public void onEat(PlayerItemConsumeEvent event)
    {
        if(event.getItem().getType() == Material.BREAD)
        {
            Location location = event.getPlayer().getLocation();
            event.getPlayer().getWorld().strikeLightning(location);
            event.getPlayer().sendMessage("You ate Bread");

        }
    }
}

