package com.angelcraftonomy.disabler.main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.angelcraftonomy.disabler.commands.AuthorCommand;
import com.angelcraftonomy.disabler.commands.HelpCommand;

public class MainCommandExecutor implements CommandExecutor {
	public MainDriver plugin;

	public MainCommandExecutor(MainDriver plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String lable, String[] args) {

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
		// Help Command
		if (args[0].toLowerCase().equals("help")) {
			HelpCommand help = new HelpCommand(plugin, sender, command, lable, args);
			help.initialize();
			help.run();
			help.cleanup();
			return true;
		}

		/**************************************************************************************/
		//

		/**************************************************************************************/
		// if no command is detected, then run the help command
		HelpCommand help = new HelpCommand(plugin, sender, command, lable, args);
		help.initialize();
		help.run();
		help.cleanup();
		return true;
	}
}
