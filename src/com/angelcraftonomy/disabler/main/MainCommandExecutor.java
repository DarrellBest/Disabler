package com.angelcraftonomy.disabler.main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.angelcraftonomy.disabler.commands.AuthorCommand;
import com.angelcraftonomy.disabler.commands.HelpCommand;
import com.angelcraftonomy.disabler.commands.ReloadCommand;

public class MainCommandExecutor implements CommandExecutor {
	public MainDriver plugin;

	public MainCommandExecutor(MainDriver plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String lable, String[] args) {

		if (command.getName().equalsIgnoreCase("disabler") || command.getName().equalsIgnoreCase("di")) {

			/**************************************************************************************/
			// if no command is detected, then run the help command
			if (args.length == 0 || args[0].toLowerCase().equals("help")) {
				HelpCommand help = new HelpCommand(plugin, sender, command, lable, args);
				help.initialize();
				help.run();
				help.cleanup();
				return true;
			}

			/**************************************************************************************/
			// Author Command
			if (args.length > 0 && args[0].toLowerCase().equals("author")) {
				AuthorCommand author = new AuthorCommand(plugin, sender, command, lable, args);
				// if arg0.hasPermission(author.getPermission())
				author.initialize();
				author.run();
				author.cleanup();
				return true;
			}

			/**************************************************************************************/
			// Reload Command
			if (args.length > 0 && args[0].toLowerCase().equals("reload")) {
				ReloadCommand reload = new ReloadCommand(plugin, sender, command, lable, args);
				if (sender.hasPermission(reload.getPermission())) {
					reload.initialize();
					reload.run();
					reload.cleanup();
					return true;
				} else {
					reload.sendNoPermMessage();
				}
			}

			/**************************************************************************************/
		}
		return true;
	}
}
