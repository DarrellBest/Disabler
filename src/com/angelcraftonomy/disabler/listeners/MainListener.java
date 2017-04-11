package com.angelcraftonomy.disabler.listeners;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

import com.angelcraftonomy.disabler.main.MainDriver;

import net.md_5.bungee.api.ChatColor;

public class MainListener implements Listener {

	private MainDriver plugin;
	private List<String> list;

	public MainListener(MainDriver plugin) {
		super();
		this.plugin = plugin;
		// list = plugin.getConfig().getStringList("disallow");
	}

	@EventHandler
	public void craftItem(PrepareItemCraftEvent e) {
		String itemName = e.getRecipe().getResult().getData().getItemType().name();
		if (plugin.getConfig().getBoolean("disallow." + itemName)) {
			e.getInventory().setResult(new ItemStack(Material.AIR));
			for (HumanEntity he : e.getViewers()) {
				if (he instanceof Player) {
					((Player) he).sendMessage(ChatColor.RED + "You cannot craft this!");
					// e.getViewers().get(0).sendMessage(itemName);
				}
			}
		}
	}

}
