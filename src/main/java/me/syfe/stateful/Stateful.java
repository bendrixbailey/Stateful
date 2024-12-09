package me.syfe.stateful;

import me.syfe.stateful.commands.KeepInventoryCommand;
import me.syfe.stateful.listeners.entity.*;
import me.syfe.stateful.listeners.misc.*;
import me.syfe.stateful.listeners.player.*;
import me.syfe.stateful.util.Database;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public final class Stateful extends JavaPlugin {
    private static Stateful instance;
    private static Random random = new Random();

    public static Stateful getInstance() {
        return instance;
    }

    private Database database;

    @Override
    public void onEnable() {
        instance = this;
        database = new Database();

        // saveResource("config.yml", /* replace */ false);
        this.saveDefaultConfig();

        try {
            database.connect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new EntityExplodeListener(), this);
        getServer().getPluginManager().registerEvents(new BlockDamageListener(), this);
        getServer().getPluginManager().registerEvents(new EntityChangeBlockListener(), this);
        getServer().getPluginManager().registerEvents(new VillagerAcquireTradeListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerTradeListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractEntityListener(), this);
        getCommand("keepinventory").setExecutor(new KeepInventoryCommand());

        getLogger().info("Registered everything and ready to flow!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Bye :3");
    }

    public Database getDatabase() {
        return database;
    }

    public static Random getRandom() {
        return random;
    }
}
