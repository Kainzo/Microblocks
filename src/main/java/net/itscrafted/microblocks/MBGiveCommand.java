package net.itscrafted.microblocks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class MBGiveCommand implements CommandExecutor {
	
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
					}else {
						if(args[1].equalsIgnoreCase("apple")) {
							addMB(reciever, "MHF_Apple", true, "apple");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("arrowdown")) {
							addMB(reciever, "MHF_ArrowDown", true, "arrowdown");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("arrowleft")) {
							addMB(reciever, "MHF_ArrowLeft", true, "arrowleft");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("arrowright")) {
							addMB(reciever, "MHF_ArrowRight", true, "arrowright");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("arrowup")) {
							addMB(reciever, "MHF_ArrowUp", true, "arrowup");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("enderchest")) {
							addMB(reciever, "_Brennian", false, "enderchest");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("monitor")) {
							addMB(reciever, "Alistor", false, "monitor");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("blaze")) {
							addMB(reciever, "MHF_Blaze", true, "blaze");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("bookshelf")) {
							addMB(reciever, "BowAimbot", false, "bookshelf");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("ice")) {
							addMB(reciever, "icytouch", false, "ice");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("ironchest")) {
							addMB(reciever, "godman21", false, "ironchest");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("furnace")) {
							addMB(reciever, "NegativeZeroTV", false, "furnace");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("spawner")) {
							addMB(reciever, "GAMEZENMASTER", false, "spawner");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("qcube")) {
							addMB(reciever, "jarrettgabe", false, "qcube");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("cactus")) {
							addMB(reciever, "MHF_Cactus", true, "cactus");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("cake")) {
							addMB(reciever, "MHF_Cake", true, "cake");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("camera")) {
							addMB(reciever, "FHG_Cam", true, "camera");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("cavespider")) {
							addMB(reciever, "MHF_CaveSpider", true, "cavespider");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("horse")) {
							addMB(reciever, "gavertoso", false, "horse");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("cherry")) {
							addMB(reciever, "TheEvilEnderman", false, "cherry");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("chest")) {
							addMB(reciever, "MHF_Chest", true, "chest");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("chicken")) {
							addMB(reciever, "MHF_Chicken", true, "chicken");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("clock")) {
							addMB(reciever, "nikx004", false, "clock");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("coconut")) {
							addMB(reciever, "KyleWDM", false, "coconut");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("companioncube")) {
							addMB(reciever, "sk8erace1", false, "companioncube");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("cow")) {
							addMB(reciever, "MHF_Cow", true, "cow");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("derpysnow")) {
							addMB(reciever, "GLaDOS", false, "derpysnow");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("diamondblock")) {
							addMB(reciever, "Fyspyguy", false, "diamondblock");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("diamondore")) {
							addMB(reciever, "akaBruce", false, "diamondore");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("glowstone")) {
							addMB(reciever, "samstine11", false, "glowstone");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("beachball")) {
							addMB(reciever, "PurplePenguinLPs", false, "beachball");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("dicewhite")) {
							addMB(reciever, "jmars213", false, "dicewhite");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("dicered")) {
							addMB(reciever, "gumbo632", false, "dicered");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("diceblack")) {
							addMB(reciever, "azbandit2000", false, "diceblack");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("dirt")) {
							addMB(reciever, "zachman228", false, "dirt");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("dispenser")) {
							addMB(reciever, "scemm", false, "dispenser");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("emeraldore")) {
							addMB(reciever, "Tereneckla", false, "emeraldore");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("enderdragon")) {
							addMB(reciever, "KingEndermen", false, "enderdragon");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("enderman")) {
							addMB(reciever, "MHF_Enderman", true, "enderman");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("exclamation")) {
							addMB(reciever, "MHF_Exclamation", true, "exclamation");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("golem")) {
							addMB(reciever, "MHF_Golem", true, "golem");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("grass")) {
							addMB(reciever, "MoulaTime", false, "grass");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("haybale")) {
							addMB(reciever, "Bendablob", false, "haybale");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("headlight")) {
							addMB(reciever, "Toby_The_Coder", false, "headlight");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("herobrine")) {
							addMB(reciever, "MHF_Herobrine", true, "herobrine");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("ironblock")) {
							addMB(reciever, "metalhedd", false, "ironblock");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("witch")) {
							addMB(reciever, "scrafbrothers4", false, "witch");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("jukebox")) {
							addMB(reciever, "C418", true, "jukebox");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("lampon")) {
							addMB(reciever, "AutoSoup", false, "lampon");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("lavaslime")) {
							addMB(reciever, "MHF_LavaSlime", true, "lavaslime");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("leaves")) {
							addMB(reciever, "rsfx", false, "leaves");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("lemon")) {
							addMB(reciever, "Aesixx", false, "lemon");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("lime")) {
							addMB(reciever, "greenskull27", false, "lime");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("machine")) {
							addMB(reciever, "aetherX", false, "machine");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("melon")) {
							addMB(reciever, "MHF_Melon", true, "melon");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("mossycobblestone")) {
							addMB(reciever, "Khrenan", false, "mossycobblestone");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("muffin")) {
							addMB(reciever, "ChoclateMuffin", false, "muffin");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("mushroomcow")) {
							addMB(reciever, "MHF_MushroomCow", true, "mushroomcow");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("netherrack")) {
							addMB(reciever, "Numba_one_Stunna", false, "netherrack");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("notexture")) {
							addMB(reciever, "ddrl46", false, "notexture");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("oaklog2")) {
							addMB(reciever, "MightyMega", false, "oaklog2");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("oaklog")) {
							addMB(reciever, "MHF_OakLog", true, "oaklog");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("obsidian")) {
							addMB(reciever, "loiwiol", false, "obsidian");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("ocelot")) {
							addMB(reciever, "MHF_Ocelot", true, "ocelot");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("orange")) {
							addMB(reciever, "hi1232", false, "orange");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("eyeofender")) {
							addMB(reciever, "Edna_I", false, "eyeofender");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("pigzombie")) {
							addMB(reciever, "MHF_PigZombie", true, "pigzombie");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("pig")) {
							addMB(reciever, "MHF_Pig", true, "pig");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("piston")) {
							addMB(reciever, "JL2579", false, "piston");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("podzol")) {
							addMB(reciever, "PhasePvP", false, "podzol");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("popcorn")) {
							addMB(reciever, "ZachWarnerHD", false, "popcorn");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("present")) {
							addMB(reciever, "I_Xenon_I", false, "present");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("pumpkinface")) {
							addMB(reciever, "Koebasti", false, "pumpkinface");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("pumpkin")) {
							addMB(reciever, "MHF_Pumpkin", true, "pumpkin");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("quartzblock")) {
							addMB(reciever, "bubbadawg01", false, "quartzblock");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("question")) {
							addMB(reciever, "MHF_Question", true, "question");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("radio")) {
							addMB(reciever, "uioz", false, "radio");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("redsand")) {
							addMB(reciever, "OmniSulfur", false, "redsand");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("redstoneore")) {
							addMB(reciever, "annayirb", false, "redstoneore");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("rubixcube")) {
							addMB(reciever, "iTactical17", false, "rubixcube");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("sand")) {
							addMB(reciever, "rugofluk", false, "sand");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("sheep")) {
							addMB(reciever, "MHF_Sheep", true, "sheep");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("slime")) {
							addMB(reciever, "MHF_Slime", true, "slime");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("speaker")) {
							addMB(reciever, "b1418", false, "speaker");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("spider")) {
							addMB(reciever, "MHF_Spider", true, "spider");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("sponge")) {
							addMB(reciever, "pomi44", false, "sponge");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("squid")) {
							addMB(reciever, "MHF_Squid", true, "squid");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("stickypiston")) {
							addMB(reciever, "Panda4994", false, "stickypiston");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("stone")) {
							addMB(reciever, "Robbydeezle", false, "stone");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("taco")) {
							addMB(reciever, "Crunchy_Taco34", false, "taco");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("tnt2")) {
							addMB(reciever, "MHF_TNT2", true, "tnt2");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("tnt")) {
							addMB(reciever, "MHF_TNT", true, "tnt");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("toaster")) {
							addMB(reciever, "pologobbyboy", false, "toaster");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("toiletpaper")) {
							addMB(reciever, "Ethegj", false, "toiletpaper");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("tv")) {
							addMB(reciever, "Metroidling", false, "tv");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("villager")) {
							addMB(reciever, "MHF_Villager", true, "villager");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("ghast")) {
							addMB(reciever, "MHF_Ghast", true, "ghast");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("oakplanks")) {
							addMB(reciever, "terryxu", false, "oakplanks");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("gamecube")) {
							addMB(reciever, "ReflectedNicK", false, "gamecube");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("redstoneblock")) {
							addMB(reciever, "AlexDr0ps", false, "redstoneblock");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("tv2")) {
							addMB(reciever, "nonesuchplace", false, "tv2");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("troll")) {
							addMB(reciever, "Trollface20", false, "troll");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("eye")) {
							addMB(reciever, "Taizun", false, "eye");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("parrot")) {
							addMB(reciever, "Luk3011", false, "parrot");
							reciever.sendMessage(ChatColor.GOLD + "This microblock is " + ChatColor.GRAY + "diagonal" + ChatColor.GOLD + ".");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("pokeball")) {
							addMB(reciever, "Chuzard", false, "pokeball");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("cookie")) {
							addMB(reciever, "QuadratCookie", false, "cookie");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("workbench")) {
							addMB(reciever, "Russellgoo97", false, "workbench");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("orangewool")) {
							addMB(reciever, "titou36", false, "orangewool");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("stonebrick")) {
							addMB(reciever, "Cakers", false, "stonebrick");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("swskeleton")) {
							addMB(reciever, "lesto123", false, "swskeleton");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("swzombie")) {
							addMB(reciever, "maximxc", false, "swzombie");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("lapis")) {
							addMB(reciever, "Apa333", false, "lapis");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("goldblock")) {
							addMB(reciever, "StackedGold", false, "goldblock");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("fox")) {
							addMB(reciever, "hugge75", false, "fox");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("potato")) {
							addMB(reciever, "CraftPotato13", false, "potato");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("leaves2")) {
							addMB(reciever, "half_bit", false, "leaves2");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[1].equalsIgnoreCase("cobblestone")) {
							addMB(reciever, "_Rience", false, "cobblestone");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[0].equalsIgnoreCase("water")) {
							addMB(reciever, "emack0714", false, "water");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[0].equalsIgnoreCase("noteblock")) {
							addMB(reciever, "PixelJuke", false, "noteblock");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else if(args[0].equalsIgnoreCase("brick")) {
							addMB(reciever, "BrickInTheHead", false, "brick");
							p.sendMessage(ChatColor.GOLD + "You have given " + ChatColor.GRAY + args[0] + ChatColor.GOLD
									+ " the " + ChatColor.GRAY + args[1] + ChatColor.GOLD + " microblock.");
						}else {
							p.sendMessage(ChatColor.RED + "Unknown microblock!");
							p.sendMessage(ChatColor.RED + "Use /mb for a list of microblocks.");
						}
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
	
}
