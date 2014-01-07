package net.itscrafted.microblocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class MBGiveCommand implements CommandExecutor, TabCompleter {
	
	/** Reference the main class **/
	private Microblocks mb;
	public MBGiveCommand(Microblocks mb) {
		this.mb = mb;
	}
	
	/** Quick method for adding heads. **/
	public ItemStack mblock(ItemStack item, String nick, String microblock) {
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD + "Microblock: " + ChatColor.WHITE + microblock);
		meta.setOwner(nick);
		meta.setLore(Arrays.asList(ChatColor.GRAY + "Smaller than a block."));
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
		if(cmd.getLabel().equalsIgnoreCase("givemb") && sender instanceof Player) {
			/** Get the Player **/
			Player p = (Player) sender;
			
			/** Permission Check **/
			if(!p.hasPermission("mb.give")) {
				p.sendMessage(ChatColor.RED + "You do not have permission for this.");
			}else {
				if(args.length == 0) {
					p.sendMessage(ChatColor.RED + "Usage: /givemb <player> <microblock>");
				}else if(args.length == 1) {
					p.sendMessage(ChatColor.RED + "Usage: /givemb <player> <microblock>");
				}else if(args.length == 2) {
					/** Otherwise, assume the player is trying to spawn a microblock, or get help. **/
					Player reciever = Bukkit.getPlayerExact(args[0]);
					
					if(reciever == null || args[0].length() >= 20) {
						p.sendMessage(ChatColor.RED + args[0] + " is not online or is an invalid player name.");
					}else if(MicroblockType.BLOCK_MAP.containsKey(args[1].toLowerCase())) {
						MicroblockType mbt = MicroblockType.BLOCK_MAP.get(args[1].toLowerCase());
						addMB(reciever, mbt.getPlayerName(), mbt.isSafe(), mbt.getBlockName());
						if (mbt.getBlockName().equalsIgnoreCase("parrot")) {
							reciever.sendMessage(ChatColor.GOLD + "This microblock is " + ChatColor.GRAY + "diagonal" + ChatColor.GOLD + ".");
						}
						p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
								+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
					}else {
						p.sendMessage(ChatColor.RED + "Unknown microblock!");
						p.sendMessage(ChatColor.RED + "Use /mb for a list of microblocks.");
					}
					
				}else if(args.length >= 3) {
					p.sendMessage(ChatColor.RED + "Usage: /givemb <player> <microblock>");
				}
			}
		}else {
			sender.sendMessage(ChatColor.RED + "Only ingame players may use this command.");
		}
		
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		if(command.getLabel().equalsIgnoreCase("givemb") && sender.hasPermission("mb.give") && args.length == 2) {
			List<String> completions = new ArrayList<String>();

			for (String key : MicroblockType.BLOCK_MAP.keySet()) {
				if (key.startsWith(args[1])) {
					completions.add(key);
				}
			}
			Collections.sort(completions, String.CASE_INSENSITIVE_ORDER);
			return completions;
		}
		return null;
	}
	
}
