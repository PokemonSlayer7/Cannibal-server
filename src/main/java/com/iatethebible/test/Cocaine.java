package com.iatethebible.test;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.net.http.WebSocket;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class Cocaine implements Listener {
    private static final Sound snort = Sound.BLOCK_POWDER_SNOW_BREAK;
    private static final Sound snort2 = Sound.ENTITY_SNIFFER_SNIFFING;
    private static final PotionEffect zoom = PotionEffectType.SPEED.createEffect(60 * 20, 2);
    private static final PotionEffect power = PotionEffectType.DOLPHINS_GRACE.createEffect(60 * 20, 2);
    private static final PotionEffect dig = PotionEffectType.FAST_DIGGING.createEffect(60 * 20, 2);
    private static final PotionEffect hurt = PotionEffectType.WITHER.createEffect(120 * 20, 1);
    private static final PotionEffect[] effects = new PotionEffect[]{zoom, power, dig};

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent e) {
        Action act = e.getAction();
        Player p = e.getPlayer();

        //Check if the player right-clicked and is sneaking
        if(act != Action.RIGHT_CLICK_AIR && act != Action.RIGHT_CLICK_BLOCK)
            return;
        if(!p.isSneaking())
            return;

        ItemStack item = e.getItem();

        assert item != null;
        if(!item.hasItemMeta())
            return;

        //if it doesn't have the key or the key is not cocaine, return
        PersistentDataContainer pdc = Objects.requireNonNull(item.getItemMeta()).getPersistentDataContainer();
        if (!pdc.has(Main.itemKey, PersistentDataType.STRING))
            return;
        if (!Objects.equals(pdc.get(Main.itemKey, PersistentDataType.STRING), "cocaine"))
            return;

        //We successfully consumed cocaine
        consumeCocaine(p, item);
    }

    private void consumeCocaine(Player p, ItemStack item){
        //Remove old wither effect
        if(p.hasPotionEffect(PotionEffectType.WITHER))
            p.removePotionEffect(PotionEffectType.WITHER);

        //Use 1 item
        item.setAmount(item.getAmount()-1);

        //Play effects
        Location pLoc = p.getEyeLocation();
        Vector direction = new Vector(pLoc.getDirection().getX(), 0, pLoc.getDirection().getZ()).normalize();
        p.spawnParticle(Particle.SNOW_SHOVEL, pLoc.add(direction), 10,0 , 0, 0);
        p.playSound(p, snort, 10, 1);
        p.playSound(p, snort2, 9, 1);
        p.addPotionEffects(Arrays.asList(effects));

        new DelayTask(() ->{
            p.addPotionEffect(hurt);
        }, 60 * 20);
    }
}
