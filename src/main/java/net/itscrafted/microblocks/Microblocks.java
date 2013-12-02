package net.itscrafted.microblocks;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Microblocks extends JavaPlugin {
	
	/** Reference the Microblock Command Class **/
	MicroblockCommand mbc;
	
	/** Create ArrayLists for the item lores **/
	ArrayList<String> lore = new ArrayList<String>();
	ArrayList<String> skullLore = new ArrayList<String>();
	
	public void onEnable() {
		/** Configuration **/
		getConfig().options().copyDefaults(true);
		getConfig().options().header("Enable 'safe-mode' to only allow 'safe' heads, which won't change.");
		saveConfig();
		
		/** Informative Messages **/
		getLogger().info("Please report any head mismatches to itsCrafted on SpigotMC, or infiniteForge on "
				+ "BukkitDev.");
		
		getLogger().info("The current plugin version is " + getDescription().getVersion() + ". Please use this"
				+ " when sending reports of mismatched heads and such.");
		
		/** Register Commands **/
		getCommand("microblocks").setExecutor(new MicroblockCommand(this));
		getCommand("skull").setExecutor(new SkullCommand(this));
		getCommand("givemb").setExecutor(new MBGiveCommand(this));
		
		/** Set Lore Contents **/
		lore.add(ChatColor.GRAY + "Smaller than a block.");
		skullLore.add(ChatColor.GRAY + "Place it, break it, wear it as a hat!");
	}
}
