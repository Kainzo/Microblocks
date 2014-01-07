package net.itscrafted.microblocks;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class MicroblockCommand implements CommandExecutor {

	String[] blocks;
	String[] secondPage;
	int totalLength;
	
	/** Reference the main class **/
	private Microblocks mb;
	public MicroblockCommand(Microblocks mb) {
		this.mb = mb;

		/** Block List **/
		blocks = MicroblockType.BLOCK_MAP.keySet().toArray(new String[0]);

		/** Get the absolute length of both pages, and store it as an int. **/
		totalLength = blocks.length;
		Arrays.sort(blocks);

		/** Block List (Page Two) **/
		secondPage = Arrays.copyOfRange(blocks, (totalLength/2) + 1, totalLength);
		blocks = Arrays.copyOfRange(blocks, 0, totalLength/2);
		
	}
	
	/** Convert an array to a comma-seperated String. **/
	public static String arrayToString(String array[]) {
		if (array.length == 0) return "";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < array.length; ++i) {
			sb.append(ChatColor.RED).append(", ").append(ChatColor.GOLD).append(array[i]);
		}
		return sb.substring(4);
	}
	
	/** Quick method for adding heads. **/
	public ItemStack mblock(ItemStack item, String nick, String microblock) {
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD + "Microblock: " + ChatColor.WHITE + microblock);
		meta.setOwner(nick);
		meta.setLore(mb.lore);
		item.setItemMeta(meta);
		return item;
	}
	
	/** Add a Microblock to a player's inventory. **/
	public void addMB(Player p, String headName, boolean safe, String microblock) {
		/** If safe-mode is enabled, check that the head is safe. **/
		if(mb.getConfig().getBoolean("safe-mode") == true && !safe) {
				p.sendMessage(ChatColor.GOLD + "This is an " + ChatColor.RED + "unsafe head" + ChatColor.GOLD + 
						", you cannot use it.");
				p.sendMessage(ChatColor.GOLD + "If you wish to use it, disable " + ChatColor.RED +
						"'safe-mode' " + ChatColor.GOLD + "in the config.");
		}else {
				p.getInventory().addItem(mblock(new ItemStack(Material.SKULL_ITEM, 1, (byte) 3), headName, microblock));
				p.sendMessage(ChatColor.GOLD + "You have been given the " + ChatColor.GRAY + microblock
						+ ChatColor.GOLD + " microblock.");
		}
	}
	
	/** Command **/
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		/** Microblocks Command **/
		if(cmd.getLabel().equalsIgnoreCase("microblocks") && sender instanceof Player) {
			/** Get the Player **/
			Player p = (Player) sender;
			
			/** Permission Check **/
			if(!p.hasPermission("mb.use")) {
				p.sendMessage(ChatColor.RED + "You do not have permission for this.");
			}else {
				if(args.length == 0) {
					/** Print out a comma separated list of microblocks (sorted), if the argument length is 0 **/
					
					p.sendMessage(ChatColor.RED + "Microblocks (" + totalLength +  "): " + arrayToString(blocks));
					p.sendMessage(ChatColor.RED + "Type /mb 2 for the next page.");
					p.sendMessage(ChatColor.RED + "Usage: /mb <block>");
				}else if(args.length == 1) {
					/** Otherwise, assume the player is trying to spawn a microblock, or get help. **/
					
					if(args[0].equalsIgnoreCase("about") || args[0].equalsIgnoreCase("help")) {
						p.sendMessage(ChatColor.GOLD + "Microblocks " + ChatColor.GRAY + "v" + mb.getDescription().getVersion() + ChatColor.GOLD + " by " + ChatColor.GRAY +
								"CraftyCreator/itsCrafted" + ChatColor.GOLD + ".");
						p.sendMessage(ChatColor.RED + "/mb" + ChatColor.YELLOW + " for a list of blocks.");
						p.sendMessage(ChatColor.RED + "/mb <block>" + ChatColor.YELLOW + " to spawn a Microblock.");
						p.sendMessage(ChatColor.RED + "/mb reload" + ChatColor.YELLOW + " to reload the configuration");
						p.sendMessage(ChatColor.RED + "/skull <player/self>" + ChatColor.YELLOW + " to spawn a skull");
						p.sendMessage(ChatColor.RED + "/givemb <player> <microblock>" + ChatColor.YELLOW + " to give Microblocks to others.");
					}else if(args[0].equalsIgnoreCase("2")) {
						p.sendMessage("");
						p.sendMessage(ChatColor.RED + "Microblocks (Page Two): " + arrayToString(secondPage));
						p.sendMessage(ChatColor.RED + "Type /mb for the first page.");
						p.sendMessage(ChatColor.RED + "Usage: /mb <block>");
					}else if(args[0].equalsIgnoreCase("reload")) {
						mb.reloadConfig();
						p.sendMessage(ChatColor.GRAY + "Microblocks " + ChatColor.GOLD + "configuration reloaded.");
					}else if(MicroblockType.BLOCK_MAP.containsKey(args[0].toLowerCase())) {
						MicroblockType mbt = MicroblockType.BLOCK_MAP.get(args[0].toLowerCase());
						addMB(p, mbt.getPlayerName(), mbt.isSafe(), mbt.getBlockName());
						if (mbt.getBlockName().equalsIgnoreCase("parrot")) {
							p.sendMessage(ChatColor.GOLD + "This microblock is " + ChatColor.GRAY + "diagonal" + ChatColor.GOLD + ".");
						}
					}else {
						p.sendMessage(ChatColor.RED + "Unknown microblock!");
						p.sendMessage(ChatColor.RED + "Use /mb for a list of microblocks.");
					}
				}else if(args.length >= 2) {
					p.sendMessage(ChatColor.RED + "Too many arguments!");
					p.sendMessage(ChatColor.RED + "Use /mb for a list of microblocks.");
				}
			}
		}else {
			sender.sendMessage(ChatColor.RED + "Only ingame players may use this command.");
		}
		
		return false;
	}
	
}
