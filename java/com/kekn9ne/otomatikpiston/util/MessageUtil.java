package com.kekn9ne.otomatikpiston.util;

import java.util.ArrayList;
import java.util.List;

public class MessageUtil {
    public static String PREFIX;
    public static List<String> ENABLED_WORLDS = new ArrayList<>();
    public static String ITEMNAME;
    public static List<String> ITEMLORE;
    public static List<String> INFO;
    public static String PERMERROR;
    public static String RELOAD;
    public static String CONSOLE;
    public static String GIVEERROR;
    public static String TAKEERROR;
    public static String INVENTORY_IS_FULL;
    public static String INVENTORY_FULL;
    public static String ADDED;
    public static String ADD;
    public static String NUMBER;
    public static String WORLD;

    static SettingsManager manager = SettingsManager.getInstance();

    public static void loadMessages() {
        PREFIX = colorize(manager.getConfig().getString("Prefix"));
        ENABLED_WORLDS = manager.getConfig().getStringList("Settings.enabled-worlds");
        ITEMNAME = colorize(manager.getConfig().getString("Item.name"));
        ITEMLORE = colorizeList(manager.getConfig().getStringList("Item.lore"));
        INFO = colorizeList(manager.getConfig().getStringList("Info"));
        PERMERROR = PREFIX + colorize(manager.getConfig().getString("Messages.PermError"));
        RELOAD = PREFIX + colorize(manager.getConfig().getString("Messages.Reload"));
        CONSOLE = PREFIX + colorize(manager.getConfig().getString("Messages.Console"));
        GIVEERROR = PREFIX + colorize(manager.getConfig().getString("Messages.GiveError"));
        TAKEERROR = PREFIX + colorize(manager.getConfig().getString("Messages.TakeError"));
        INVENTORY_IS_FULL = PREFIX + colorize(manager.getConfig().getString("Messages.InventoryIsFull"));
        INVENTORY_FULL = PREFIX + colorize(manager.getConfig().getString("Messages.InventoryFull"));
        ADDED = PREFIX + colorize(manager.getConfig().getString("Messages.Added"));
        ADD = PREFIX + colorize(manager.getConfig().getString("Messages.Add"));
        NUMBER = PREFIX + colorize(manager.getConfig().getString("Messages.Number"));
        WORLD = PREFIX + colorize(manager.getConfig().getString("Messages.WorldError"));
    }

    public static String colorize(String str) {
        return str.replace("&", "§");
    }

    public static List<String> colorizeList(List<String> str) {
        for(int x=0; x<str.size(); x++) {
            str.set(x, str.get(x).replace("&", "§"));
        }
        return str;
    }
}