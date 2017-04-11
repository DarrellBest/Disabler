package com.angelcraftonomy.disabler.commands;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.angelcraftonomy.disabler.extenders.CommandExtender;
import com.angelcraftonomy.disabler.main.MainDriver;

import net.md_5.bungee.api.ChatColor;

public class HelpCommand extends CommandExtender {

	private ArrayList<String> messages;
	private ChatColor colorOne;

	public HelpCommand(MainDriver plugin, CommandSender sender, Command command, String label, String[] args) {
		super(plugin, sender, command, label, args);
		super.setPermission("disabler.command.help");
	}

	@Override
	public void initialize() {
		messages = new ArrayList<>();
		colorOne = getRandomColor();
	}

	@Override
	public void run() {
		messages.add("-----------------------------------------------------\n");
		messages.add(colorOne + "*** Disabler v1.0 Created and Designed by UsuriousAngel ***\n");
		messages.add(" /" + getCommand().getName() + " author : " + colorOne + "Displays the author");
		messages.add(" /" + getCommand().getName() + " help : " + colorOne + "Shows the commands");
		messages.add(" /" + getCommand().getName() + " reload: " + colorOne + "Reloads the config\n");
		messages.add(colorOne + " You can disable crafting for specific items");
		messages.add(colorOne + " in config.yml. Set to true to disable!");
		messages.add("-----------------------------------------------------");
		sendMessage(messages);
	}

	@Override
	public void cleanup() {
		messages.clear();
	}

	@Override
	public void sendNoPermMessage() {
		super.sendNoPermMessage();
	}

}
