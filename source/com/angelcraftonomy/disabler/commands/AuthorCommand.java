package com.angelcraftonomy.disabler.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.angelcraftonomy.disabler.extenders.CommandExtender;
import com.angelcraftonomy.disabler.main.MainDriver;

public class AuthorCommand extends CommandExtender {

	private String permission;

	public AuthorCommand(MainDriver plugin, CommandSender sender, Command command, String label, String[] args) {
		super(plugin, sender, command, label, args);
	}

	@Override
	public void initialize() {
		permission = "disabler.command.author";
	}

	@Override
	public void run() {
		sendMessage(" Created and designed by UsuriousAngel.");
	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub
	}

	@Override
	public void sendNoPermMessage() {
		super.sendNoPermMessage();
	}

	@Override
	public String getPermission() {
		return this.permission;
	}

}
