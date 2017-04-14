package com.angelcraftonomy.disabler.configuration;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

import com.angelcraftonomy.disabler.main.MainDriver;

public class ConfigBuilder {

	private FileConfiguration config;
	private Logger logger;
	private MainDriver plugin;

	public ConfigBuilder(MainDriver plugin) {
		this.config = plugin.getConfig();
		this.logger = plugin.getLogger();
		this.plugin = plugin;
	}

	public void generateConfig() {
		List<String> recipe = new ArrayList<>();

		try {
			if (!plugin.getDataFolder().exists()) {
				plugin.getDataFolder().mkdirs();
			}
			File file = new File(plugin.getDataFolder(), "config.yml");
			if (!file.exists()) {
				logger.info("Config.yml not found, creating!");

				for (Material material : Material.values()) {
					recipe = new ArrayList<>();
					recipe.addAll(
							Arrays.asList("NONE", "NONE", "NONE", "NONE", "NONE", "NONE", "NONE", "NONE", "NONE"));
					config.addDefault("materials." + material + ".craftable", true);
					config.addDefault("materials." + material + ".customrecipe", false);
					config.addDefault("materials." + material + ".shapedrecipe", false);
					config.addDefault("materials." + material + ".recipe.list", recipe);
				}

				config.options().copyDefaults(true);
				plugin.saveConfig();
			} else {
				logger.info("Config.yml found, loading!");
				this.loadConfig();
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	private void loadConfig() {
		boolean craftable;
		boolean customRecipe;
		boolean shapedRecipe;
		Iterator<Recipe> recipes;
		Recipe recipe;
		List list;

		for (Material material : Material.values()) {
			craftable = config.getBoolean("materials." + material + ".craftable");
			customRecipe = config.getBoolean("materials." + material + ".customrecipe");
			shapedRecipe = config.getBoolean("materials." + material + ".shapedrecipe");

			if (!craftable) {
				recipes = plugin.getServer().recipeIterator();
				while (recipes.hasNext()) {
					recipe = recipes.next();
					if (recipe != null && recipe.getResult().getType() == material) {
						recipes.remove();
					}
				}

				logger.info("Disabled crafting of: " + material.toString());
			} else {
				if (customRecipe) {
					if (shapedRecipe) {
						String componets = "";
						list = config.getList("materials." + material + ".recipe.list");

						ItemStack item = new ItemStack(material, 1);

						ShapedRecipe newRecipe = new ShapedRecipe(item);

						ArrayList<Character> symbols = new ArrayList<>();
						symbols.addAll(Arrays.asList('!', '@', '#', '$', '%', '^', '&', '*', '?'));

						for (int i = 0; i < 9; i++) {
							if (!list.get(i).toString().equals("NONE")) {
								componets = componets + symbols.get(i);
							} else {
								componets = componets.concat(" ");
							}
						}

						newRecipe.shape(componets.substring(0, 3), componets.substring(3, 6), componets.substring(6));

						for (int i = 0; i < 9; i++) {
							if (!list.get(i).toString().equals("NONE")) {
								newRecipe.setIngredient(symbols.get(i), Material.getMaterial(list.get(i).toString()));
							}
						}

						plugin.getServer().addRecipe(newRecipe);
						logger.info("Adding custom shaped recipe for: " + material.toString());

					} else if (!shapedRecipe) {
						ShapelessRecipe newRecipe;
						list = config.getList("materials." + material + ".recipe.list");

						ItemStack item = new ItemStack(material, 1);

						newRecipe = new ShapelessRecipe(item);

						for (int i = 0; i < 9; i++) {
							if (!list.get(i).toString().equals("NONE")) {
								newRecipe.addIngredient(1, Material.getMaterial(list.get(i).toString()));
							}
						}

						plugin.getServer().addRecipe(newRecipe);
						logger.info("Adding custom shapless recipe for: " + material.toString());
					}
				}
			}
		}
	}
}
