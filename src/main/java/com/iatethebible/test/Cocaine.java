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


public class Cocaine implements Listener {
    @EventHandler

    public void onPlayerInteractEvent(PlayerInteractEvent e) {

        Action act = e.getAction();

        Sound snort = Sound.BLOCK_POWDER_SNOW_BREAK;
        Sound snort2 = Sound.ENTITY_SNIFFER_SNIFFING;
        PotionEffect zoom = PotionEffectType.SPEED.createEffect(60 * 20, 2);
        PotionEffect power = PotionEffectType.DOLPHINS_GRACE.createEffect(60 * 20, 2);
        PotionEffect dig = PotionEffectType.FAST_DIGGING.createEffect(60 * 20, 2);
        PotionEffect hurt = PotionEffectType.WITHER.createEffect(120 * 20, 1);
        PotionEffect[] effects = new PotionEffect[]{zoom, power, dig};


        Player p = e.getPlayer();

        if (act == Action.RIGHT_CLICK_AIR && p.isSneaking()) {
            ItemStack item = e.getItem();

            if (item != null) {
                Material material = item.getType();
                if (material == Material.SUGAR) {
                    PersistentDataContainer pdc = item.getItemMeta().getPersistentDataContainer();
                    if (!pdc.has(Main.itemKey, PersistentDataType.STRING))
                        return;

                    if (pdc.get(Main.itemKey, PersistentDataType.STRING).equals("cocaine")) {
                        if(p.hasPotionEffect(PotionEffectType.WITHER))
                            p.removePotionEffect(PotionEffectType.WITHER);
                        item.setAmount(item.getAmount()-1);
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
            }

        }
    }

}
