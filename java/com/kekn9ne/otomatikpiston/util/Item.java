package com.kekn9ne.otomatikpiston.util;

import com.kekn9ne.otomatikpiston.OtomatikPiston;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Item {
    public ItemStack piston;
    public ItemMeta pistonMeta;

    public static void loadItem(Item item) {
        item.piston = new ItemStack(Material.PISTON);
        item.pistonMeta = item.piston.getItemMeta();
        item.pistonMeta.setDisplayName(MessageUtil.ITEMNAME);
        item.pistonMeta.setLore(MessageUtil.ITEMLORE);
        item.pistonMeta.addEnchant(Enchantment.DURABILITY, 10, true);
        item.pistonMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.piston.setItemMeta(item.pistonMeta);
    }

    public static boolean checkInventory(Player p) {
        int kontrol=0;
        for(ItemStack i : p.getInventory()) {
            if(i == null) {
                kontrol ++;
            }
        }
        if(kontrol != 0) {
            return true;
        }
        return false;
    }

    public static boolean checkItemMeta(ItemMeta meta) {
        if(meta.hasDisplayName()) {
            if(meta.getDisplayName().equalsIgnoreCase(MessageUtil.ITEMNAME)) {
                if(meta.getLore().equals(MessageUtil.ITEMLORE)) {
                    if(meta.getEnchants().equals(OtomatikPiston.item.pistonMeta.getEnchants())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}