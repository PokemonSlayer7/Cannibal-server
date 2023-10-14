package com.iatethebible.test;

import com.google.common.util.concurrent.AbstractScheduledService;
import org.bukkit.*;
import org.bukkit.block.Barrel;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.weather.LightningStrikeEvent;
import org.bukkit.event.world.WorldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import javax.management.ObjectInstance;
import java.awt.event.ItemEvent;
import java.lang.reflect.Type;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.*;

public class Smiter implements Listener
{

    @EventHandler
    public void onLightingStrike(LightningStrikeEvent event)
    {

        Location location = event.getLightning().getLocation();
        List<Entity> entities = (List<Entity>) event.getLightning().getWorld().getNearbyEntities(location, 1,1,1);
        for (Entity entity: entities)
        {
            if(entity instanceof Item item)
            {
                if(item.getItemStack().getType() == Material.TRIDENT)
                {

                    entity.remove();
                    Block bar = event.getWorld().getBlockAt(location);
                    location.getBlock().setType(Material.WATER);
                    new DelayTask(() ->{
                        bar.setType(Material.BARREL);
                        Barrel bar2 = (Barrel)bar.getState();

                        ItemStack trident = new ItemStack(Material.TRIDENT);
                        ItemMeta tridentMeta = trident.getItemMeta();
                        tridentMeta.setDisplayName(ChatColor.GOLD + "Trident Bolt");
                        ArrayList<String> tridentLore = new ArrayList<>();
                        tridentLore.add(ChatColor.MAGIC + "This specific trident has been graced by the LIGHTNING GODS");
                        tridentMeta.setLore(tridentLore);
                        tridentMeta.getPersistentDataContainer().set(Main.itemKey,PersistentDataType.STRING,"Trident-Bolt");
                        trident.setItemMeta(tridentMeta);

                        bar2.getInventory().addItem(trident);
                        Bukkit.getServer().getPlayer("IAteTheBible").sendMessage("new trident");
                    }, 17);




                }
            }
        }

    }
    @EventHandler
    public void onProjectileLaunchEvent(ProjectileLaunchEvent projevent)
    {
        Projectile projectile = (Projectile) getItemFactory().getItemMeta(Material.TRIDENT);
        PersistentDataContainer pdc = projectile.getPersistentDataContainer();
        if (pdc.get(Main.itemKey,PersistentDataType.STRING).equals("Trident-Bolt"))
        {
            Location hit = projevent.getEntity().getLocation();
            hit.getWorld().strikeLightning(hit);
        }

    }

}