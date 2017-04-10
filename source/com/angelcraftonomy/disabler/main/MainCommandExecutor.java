package com.angelcraftonomy.disabler.main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.angelcraftonomy.disabler.commands.AuthorCommand;

public class MainCommandExecutor implements CommandExecutor {
	public MainDriver plugin;

	public MainCommandExecutor(MainDriver plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (command.getName().equalsIgnoreCase("disabler") || command.getName().equalsIgnoreCase("di")) {

			// Author command
			if (args.length > 0 && args[0].toLowerCase().equals("author")) {
				AuthorCommand author = new AuthorCommand(plugin, sender, command, label, args);
				if (sender.hasPermission("disabler.command.author")) {
					author.initialize();
					author.run();
					author.cleanup();
				} else {
					author.sendNoPermMessage();
				}
				return true;
			}
		}

		return false;
	}
}
