package com.angelcraftonomy.disabler.main;

import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.angelcraftonomy.disabler.configuration.ConfigBuilder;
import com.angelcraftonomy.disabler.listeners.MainListener;

public class MainDriver extends JavaPlugin {
	public static Logger logger;
	public static MainDriver plugin;
	private MainCommandExecutor myCommands;
	private FileConfiguration config;
	private PluginManager pm;
	private MainListener listener;
	private ConfigBuilder builder;

	@Override
	public void onEnable() {

		logger = this.getLogger();
		plugin = this;
		config = this.getConfig();
		builder = new ConfigBuilder(this);

		logger.info("[Disabler] Enabling Disabler v1.0");
		builder.generateConfig();

		myCommands = new MainCommandExecutor(this);
		getCommand("disabler").setExecutor(myCommands);

		pm = getServer().getPluginManager();
		pm.registerEvents(listener = new MainListener(this), this);

		logger.info("[Disabler] Online.");
	}

	// Fired when plugin is disabled
	@Override
	public void onDisable() {
		logger.info("[Disabler] Disabling Disabler v1.0");
		logger.info("--- END OF LINE ---");
	}

}
