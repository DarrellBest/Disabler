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

	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {

		if (arg0.getName().equalsIgnoreCase("disabler") || arg0.getName().equalsIgnoreCase("di")) {

			// Author command
			if (arg3.length > 0 && arg3[0].toLowerCase().equals("author")) {
				AuthorCommand author = new AuthorCommand(plugin, arg0, arg1, arg2, arg3);
				if (arg0.hasPermission("disabler.command.author")) {
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
