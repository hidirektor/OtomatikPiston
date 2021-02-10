package com.kekn9ne.otomatikpiston;

import com.kekn9ne.otomatikpiston.commands.PistonCommand;
import com.kekn9ne.otomatikpiston.events.OtomatikPistonEvents;
import com.kekn9ne.otomatikpiston.events.PistonPlaceEvent;
import com.kekn9ne.otomatikpiston.util.Item;
import com.kekn9ne.otomatikpiston.util.MessageUtil;
import com.kekn9ne.otomatikpiston.util.SettingsManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class OtomatikPiston extends JavaPlugin {
    public static Plugin plugin;
    SettingsManager manager = SettingsManager.getInstance();
    public static Item item = new Item();

    @Override
    public void onEnable() {
        initialize();
    }

    public void registerListeners() {
        getServer().getPluginManager().registerEvents(new OtomatikPistonEvents(), this);
        getServer().getPluginManager().registerEvents(new PistonPlaceEvent(), this);
    }

    public void registerCommands() {
        getCommand("otopiston").setExecutor((CommandExecutor)new PistonCommand());
    }

    public void initialize() {
        plugin = this;
        registerListeners();
        registerCommands();
        manager.setup(this);
        MessageUtil.loadMessages();
        item.loadItem(item);
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[OtomatikPiston] " + ChatColor.YELLOW + "Eklenti aktifleştirildi!");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[OtomatikPiston] " + ChatColor.YELLOW + "Eklenti devre dışı bırakıldı!");
    }

}
