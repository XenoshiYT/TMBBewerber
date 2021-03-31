package me.xenodev.tmbbe.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

public class ItemBuilder {

    private ItemStack item;
    private ItemMeta meta;

    public ItemBuilder(Material material, short subID){
        item = new ItemStack(material, 1, subID);
        meta = item.getItemMeta();
    }

    public ItemBuilder(Material material){
        this(material, (short) 0);
    }

    public ItemBuilder setName(String name){
        meta.setDisplayName(name);
        return this;
    }

    public ItemBuilder setAmount(Integer amount){
        item.setAmount(amount);

        return this;
    }

    public ItemBuilder setLore(String... lore){
        meta.setLore(Arrays.asList(lore));
        return this;
    }

    public ItemBuilder setUnbreakable(){
        meta.setUnbreakable(true);
        return this;
    }

    public ItemBuilder setOwner(String name){
        SkullMeta smeta = (SkullMeta)meta;
        smeta.setOwner(name);
        return this;
    }

    public ItemStack build(){
        item.setItemMeta(meta);
        return item;
    }

}
