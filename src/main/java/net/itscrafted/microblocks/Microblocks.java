package net.itscrafted.microblocks;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Microblocks extends JavaPlugin {
	
	/** Reference the Command Class **/
	MicroblockCommand mbc;
	
	/** Create ArrayLists for the item lores **/
	ArrayList<String> lore = new ArrayList<String>();
	ArrayList<String> skullLore = new ArrayList<String>();
	
	public void onEnable() {
		/** Configuration **/
		getConfig().options().copyDefaults(true);
		getConfig().options().header("Enable 'safe-mode' to only allow 'safe' heads.");
		saveConfig();
		
		/** Informative Message(s) **/
		getLogger().info("Please report any head mismatches to itsCrafted on BukkitDev/SpigotMC.");
		
		/** Register Commands **/
		getCommand("microblocks").setExecutor(new MicroblockCommand(this));
		getCommand("skull").setExecutor(new SkullCommand(this));
		
		/** Set Lore Contents **/
		lore.add(ChatColor.GRAY + "Smaller than a block.");
		skullLore.add(ChatColor.GRAY + "Place it, break it, wear it as a hat!");
	}
}
