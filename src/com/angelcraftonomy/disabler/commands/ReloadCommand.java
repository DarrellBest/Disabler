package com.angelcraftonomy.disabler.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.angelcraftonomy.disabler.extenders.CommandExtender;
import com.angelcraftonomy.disabler.main.MainDriver;

public class ReloadCommand extends CommandExtender {

	MainDriver plugin;

	public ReloadCommand(MainDriver plugin, CommandSender sender, Command command, String label, String[] args) {
		super(plugin, sender, command, label, args);
		this.plugin = plugin;
		super.setPermission("disabler.command.reload");
	}

	@Override
	public void initialize() {

	}

	@Override
	public void run() {
		plugin.reloadConfig();
		super.sendMessage("Config.yml reloaded");
	}

	@Override
	public void cleanup() {

	}

	@Override
	public void sendNoPermMessage() {
		super.sendNoPermMessage();
	}
}
