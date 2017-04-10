package com.angelcraftonomy.disabler.main;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class MainDriver extends JavaPlugin {
	public static Logger logger;
	public static MainDriver plugin;
	private MainCommandExecutor myCommands;

	@Override
	public void onEnable() {
		System.out.println("[Disabler] Enabling Disabler v1.0");
		logger = this.getLogger();
		plugin = this;

		myCommands = new MainCommandExecutor(this);
		getCommand("disabler").setExecutor(myCommands);

		logger.info("[Disabler] Online.");
	}

	// Fired when plugin is disabled
	@Override
	public void onDisable() {
		System.out.println("[Disabler] Disabling Disabler v1.0");
		logger.info("--- END OF LINE ---");
	}

}
