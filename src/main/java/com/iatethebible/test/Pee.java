package com.iatethebible.test;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.util.Vector;

import java.awt.event.InputEvent;
import java.awt.event.KeyListener;
import java.net.http.WebSocket;

public class Pee implements Listener
{
    @EventHandler

    public void onInteractEvent (PlayerInteractEvent event)
    {
        Action act = event.getAction();
        if(act == Action.RIGHT_CLICK_BLOCK)
        {
           Player player = event.getPlayer();
           Location pLocation = player.getEyeLocation();
           Vector direction = new Vector(player.getEyeLocation().getDirection().normalize().getX(),-1.5,player.getEyeLocation().getDirection().normalize().getZ()).normalize();



           player.spawnParticle(Particle.TOTEM, pLocation.add(direction),0,  .5 * pLocation.getDirection().getX(),-.5,.5 * pLocation.getDirection().getZ());
           //player.spawnParticle(Particle.WATER_SPLASH, pLocation.add(direction),0,  .5 * pLocation.getDirection().getX(),-.5,.5 * pLocation.getDirection().getZ());



        }
    }
}
