package com.iatethebible.test;

import jdk.dynalink.beans.StaticClass;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class Main extends JavaPlugin {
    static NamespacedKey key;
    static NamespacedKey itemKey;
    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("good working yes");
        getServer().getPluginManager().registerEvents(new BreadLightning(),this);
        getServer().getPluginManager().registerEvents(new Smiter(),this);
        getServer().getPluginManager().registerEvents(new Cocaine(), this);
        new DelayTask(this);

        itemKey = new NamespacedKey(this, "custom-item");

        //getServer().getPluginManager().registerEvents(new Pee(),this);

        //All Crafting Recipes

        //Cocaine Recipe
        ItemStack cocaine = new ItemStack(Material.SUGAR, 6);
        ItemMeta metaCoke = cocaine.getItemMeta();
        metaCoke.setDisplayName(ChatColor.WHITE + "Cocaine");
        metaCoke.getPersistentDataContainer().set(itemKey, PersistentDataType.STRING,"cocaine");
        cocaine.setItemMeta(metaCoke);

        ShapedRecipe coke = new ShapedRecipe(cocaine);
        coke.shape("TTT","JJJ","GGG");
        coke.setIngredient('T', Material.TORCHFLOWER);
        coke.setIngredient('J', Material.JUNGLE_LEAVES);
        coke.setIngredient('G', Material.GUNPOWDER);

        getServer().addRecipe(coke);

        //Weed Recipe


    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


}
