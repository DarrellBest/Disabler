package com.angelcraftonomy.disabler.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.angelcraftonomy.disabler.extenders.CommandExtender;
import com.angelcraftonomy.disabler.interfaces.CommandInterface;
import com.angelcraftonomy.disabler.main.MainDriver;

public class AuthorCommand extends CommandExtender implements CommandInterface {

	private String permission;

	public AuthorCommand(MainDriver plugin, CommandSender sender, Command command, String label, String[] args) {
		super(plugin, sender, command, label, args);
	}

	public void initialize() {
		permission = "disabler.command.author";
	}

	public void run() {
		sendMessage(" Created and designed by UsuriousAngel.");
	}

	public void cleanup() {
		// TODO Auto-generated method stub
	}

	@Override
	public void sendNoPermMessage() {
		super.sendNoPermMessage();
	}

	public String getPermission() {
		return this.permission;
	}

}
