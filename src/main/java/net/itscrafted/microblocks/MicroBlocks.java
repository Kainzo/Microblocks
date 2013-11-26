package net.itscrafted.microblocks;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Microblocks extends JavaPlugin implements Listener {
	
	MicroblockCommand mbc;
	ArrayList<String> lore = new ArrayList<String>();
	ArrayList<String> skullLore = new ArrayList<String>();
	
	@Override
	public void onEnable() {
		/** Register Events **/
		Bukkit.getPluginManager().registerEvents(this, this);
		
		/** Quick Messages **/
		getLogger().info("MicroBlocks enabled succesfully.");
		getLogger().info("Please report any head mismatches to itsCrafted on BukkitDev/SpigotMC.");
		
		/** Register Commands **/
		getCommand("microblocks").setExecutor(new MicroblockCommand(this));
		getCommand("skull").setExecutor(new SkullCommand(this));
		
		/** Set Lore Contents **/
		lore.add(ChatColor.GRAY + "Smaller than a block.");
		skullLore.add(ChatColor.GRAY + "Place it, break it, wear it as a hat!");
	}
	
	@Override
	public void onDisable() {
		/** Quick Message **/
		getLogger().info("MicroBlocks has been disabled.");
	}
}
