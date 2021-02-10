package com.kekn9ne.otomatikpiston.events;

import com.kekn9ne.otomatikpiston.OtomatikPiston;
import com.kekn9ne.otomatikpiston.util.Item;
import com.kekn9ne.otomatikpiston.util.MessageUtil;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PistonPlaceEvent implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        if(e.getBlock().getType() == Material.PISTON) {
            ItemStack checkItem = new ItemStack(e.getBlock().getType());
            ItemMeta checkItemMeta = checkItem.getItemMeta();
            if(Item.checkItemMeta(checkItemMeta)) {
                if(!MessageUtil.ENABLED_WORLDS.contains(e.getBlock().getWorld())) {
                    e.setCancelled(true);
                } else {
                    e.getPlayer().sendMessage(MessageUtil.WORLD);
                }
            }
        }
    }
}
