package net.itscrafted.microblocks;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class SkullCommand implements CommandExecutor {
	
	/** Reference the main class. **/
	private Microblocks mb;
	public SkullCommand(Microblocks mb) {
		this.mb = mb;
	}
	
	/** Quick method for adding heads. **/
	public ItemStack mblock(ItemStack item, String nick) {
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		/** Nice, correct grammar for player names that end in 's' **/
		if(nick.endsWith("s") || nick.endsWith("S")) {
			meta.setDisplayName(ChatColor.GOLD + nick + ChatColor.WHITE + "' Head");
		}else {
			meta.setDisplayName(ChatColor.GOLD + nick + ChatColor.WHITE + "'s Head");
		}
		meta.setOwner(nick);
		meta.setLore(mb.skullLore);
		item.setItemMeta(meta);
		return item;
	}
	
	/** Command Method **/
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		/** Skull Command **/
		if(cmd.getLabel().equalsIgnoreCase("skull") && sender instanceof Player) {
			/** Get the Player **/
			Player p = (Player) sender;
			
			if(p.hasPermission("mb.skull")) {
				if(args.length == 0) {
					/** Show the player help if the args length is zero **/
					p.sendMessage(ChatColor.RED + "Usage: /skull <player/self>");
				}else if(args.length == 1) {
					/** Assume the player is trying to summon a head, start checking who. **/
					if(args[0].equalsIgnoreCase("self")) {
						/** Self => Give own head **/
						p.sendMessage(ChatColor.GOLD + "You were given your own head.");
						p.getInventory().addItem(mblock(new ItemStack(Material.SKULL_ITEM, 1, (byte) 3), p.getName()));
					}else if(args[0].length() <= 20) {
						/** Anything else => args[0]'s head **/
						p.getInventory().addItem(mblock(new ItemStack(Material.SKULL_ITEM, 1, (byte) 3), args[0]));
						p.sendMessage(ChatColor.GOLD + "You were given the head of " + ChatColor.GRAY + args[0] + 
								ChatColor.GOLD + ".");
					}else {
						/** Name too long. **/
						p.sendMessage(ChatColor.RED + "Name too long.");
						p.sendMessage(ChatColor.RED + "Usage: /skull <player/self>");
					}
				}else if(args.length >= 2) {
					/** Gah! Too many arguments. **/
					p.sendMessage(ChatColor.RED + "Too many arguments!");
					p.sendMessage(ChatColor.RED + "Usage: /skull <player/self>");
				}
			}else {
				p.sendMessage(ChatColor.RED + "You do not have permission for this.");
			}
		}else {
			sender.sendMessage(ChatColor.RED + "Only ingame players may use this command.");
		}
		
		return false;
	}
	
}
