package com.iatethebible.test;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.net.http.WebSocket;

public class DelayTask implements Listener
{
    private static Plugin plugin = null;
    private int id = -1;
    public DelayTask(Plugin instance)
    {
        plugin = instance;
    }

    public DelayTask(Runnable runnable){
        this(runnable, 0);
    }
    public DelayTask(Runnable runnable, long delay)
    {
        if(plugin.isEnabled())
        {
            id = Bukkit.getScheduler().scheduleSyncDelayedTask(plugin,runnable,delay);
        } else
        {
            runnable.run();
        }
    }

}
