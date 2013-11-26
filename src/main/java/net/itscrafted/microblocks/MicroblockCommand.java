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

	/** Required Stuff **/
	private Microblocks mb;
	public MicroblockCommand(Microblocks mb) {
		this.mb = mb;
	}
	
	/** Block List **/
	String[] blocks =
		{
			ChatColor.GOLD + "apple", ChatColor.GOLD + "arrowdown", ChatColor.GOLD + "arrowleft", ChatColor.GOLD + "arrowright",
			ChatColor.GOLD + "arrowup", ChatColor.GOLD + "bacon", ChatColor.GOLD + "enderchest", ChatColor.GOLD + "monitor",
			ChatColor.GOLD + "blaze", ChatColor.GOLD + "bookshelf", ChatColor.GOLD + "ice", ChatColor.GOLD + "ironchest", 
			ChatColor.GOLD + "furnace", ChatColor.GOLD + "spawner", ChatColor.GOLD + "qcube", ChatColor.GOLD + "cactus",
			ChatColor.GOLD + "cake", ChatColor.GOLD + "camera", ChatColor.GOLD + "cavespider", ChatColor.GOLD + "horse",
			ChatColor.GOLD + "cherry", ChatColor.GOLD + "chest", ChatColor.GOLD + "chicken", ChatColor.GOLD + "clock",
			ChatColor.GOLD + "coconut", ChatColor.GOLD + "companioncube", ChatColor.GOLD + "cow", ChatColor.GOLD + "derpysnow",
			ChatColor.GOLD + "diamondblock", ChatColor.GOLD + "diamondore", ChatColor.GOLD + "glowstone", 
			ChatColor.GOLD + "beachball", ChatColor.GOLD + "dicewhite", ChatColor.GOLD + "dicered", ChatColor.GOLD + "dirt", 
			ChatColor.GOLD + "dispenser", ChatColor.GOLD + "diceblack", ChatColor.GOLD +  "oakplanks", ChatColor.GOLD +  "gamecube",
			ChatColor.GOLD + "redstoneblock", ChatColor.GOLD + "emeraldore", ChatColor.GOLD + "enderdragon", 
			ChatColor.GOLD + "enderman", ChatColor.GOLD + "exclamation", ChatColor.GOLD + "golem",
			ChatColor.GOLD + "grass", ChatColor.GOLD + "haybale", ChatColor.GOLD + "headlight", ChatColor.GOLD + "herobrine",
			ChatColor.GOLD + "ironblock", ChatColor.GOLD + "witch", ChatColor.GOLD + "jukebox", ChatColor.GOLD + "lampon",
			ChatColor.GOLD + "lavaslime", ChatColor.GOLD + "leaves", ChatColor.GOLD + "lemon", ChatColor.GOLD + "lime",
			ChatColor.GOLD + "machine", ChatColor.GOLD + "melon", ChatColor.GOLD + "mossycobblestone", ChatColor.GOLD + "muffin", 
			ChatColor.GOLD + "mushroomcow", ChatColor.GOLD + "netherrack", ChatColor.GOLD + "notexture", ChatColor.GOLD + "oaklog2", 
			ChatColor.GOLD + "oaklog", ChatColor.GOLD + "obsidian", ChatColor.GOLD + "ocelot", ChatColor.GOLD + "orange", 
			ChatColor.GOLD + "eyeofender", ChatColor.GOLD + "pigzombie", ChatColor.GOLD + "pig", ChatColor.GOLD + "piston", ChatColor.GOLD + "podzol",
			ChatColor.GOLD + "popcorn", ChatColor.GOLD + "present", ChatColor.GOLD + "pumpkinface",
			ChatColor.GOLD + "pumpkin", ChatColor.GOLD + "quartzblock", ChatColor.GOLD + "question", ChatColor.GOLD + "radio", 
			ChatColor.GOLD + "redsand", ChatColor.GOLD + "redstoneore", ChatColor.GOLD + "rubixcube2", ChatColor.GOLD + "rubixcube", ChatColor.GOLD + "sand",
			ChatColor.GOLD + "sheep", ChatColor.GOLD + "slime", ChatColor.GOLD + "speaker", ChatColor.GOLD + "spider", ChatColor.GOLD + "sponge",
			ChatColor.GOLD + "squid", ChatColor.GOLD + "stickypiston", ChatColor.GOLD + "stone", ChatColor.GOLD + "taco",
			ChatColor.GOLD + "tnt2", ChatColor.GOLD + "tnt", ChatColor.GOLD + "toaster", ChatColor.GOLD + "toiletpaper",
			ChatColor.GOLD + "tv", ChatColor.GOLD + "villager", ChatColor.GOLD + "ghast", ChatColor.GOLD + "tv2", 
			ChatColor.GOLD + "troll", ChatColor.GOLD + "eye", ChatColor.GOLD + "pokeball", ChatColor.GOLD + "cookie",
		};
	
	/** Block List (Page Two) **/
	String[] secondPage = {
		ChatColor.GOLD + "workbench", ChatColor.GOLD + "orangewool", ChatColor.GOLD + "stonebrick", 
		ChatColor.GOLD + "swskeleton", ChatColor.GOLD + "swzombie", ChatColor.GOLD + "lapis"
	};
	
	/** Array to String **/
	public static String arrayToString(String array[]) {
	    if (array.length == 0) return "";
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < array.length; ++i)
	    {
	        sb.append(", ").append(array[i]).append("");
	    }
	    return sb.substring(1);
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
	public void addMB(Player p, String headName, boolean deprecated, String microblock) {
		if(deprecated) {
			p.sendMessage(ChatColor.RED + "This head is currently deprecated.");
		}else {
			p.getInventory().addItem(mblock(new ItemStack(Material.SKULL_ITEM, 1, (byte) 3), headName, microblock));
			p.sendMessage(ChatColor.GOLD + "You have been given the " + ChatColor.GRAY + microblock
					+ ChatColor.GOLD + " microblock.");
		}
	}
	
	/** Get the absolute length of both pages, and store it as an int. **/
	int totalLength = blocks.length + secondPage.length;
	
	/** Command **/
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		/** Get the Player **/
		Player p = (Player) sender;
		
		/** /microblocks command **/
		if(cmd.getLabel().equalsIgnoreCase("microblocks")) {
			/** Permission Check **/
			if(!p.hasPermission("mb.use")) {
				p.sendMessage(ChatColor.RED + "You do not have permission for this.");
			}else {
				if(args.length == 0) {
					/** Print out a comma separated list of microblocks (sorted), if the argument length is 0 **/
					Arrays.sort(blocks);
					p.sendMessage(ChatColor.RED + "Microblocks (" + totalLength +  "):" + ChatColor.YELLOW + arrayToString(blocks));
					p.sendMessage(ChatColor.RED + "Type /mb 2 for the next page.");
					p.sendMessage(ChatColor.RED + "Usage: /mb <block>");
				}else if(args.length == 1) {
					/** Otherwise, assume the player is trying to spawn a microblock. **/
					
					if(args[0].equalsIgnoreCase("apple")) {
						addMB(p, "MHF_Apple", false, "apple");
					}else if(args[0].equalsIgnoreCase("arrowdown")) {
						addMB(p, "MHF_ArrowDown", false, "arrowdown");
					}else if(args[0].equalsIgnoreCase("arrowleft")) {
						addMB(p, "MHF_ArrowLeft", false, "arrowleft");
					}else if(args[0].equalsIgnoreCase("arrowright")) {
						addMB(p, "MHF_ArrowRight", false, "arrowright");
					}else if(args[0].equalsIgnoreCase("arrowup")) {
						addMB(p, "MHF_ArrowUp", false, "arrowup");
					}else if(args[0].equalsIgnoreCase("bacon")) {
						addMB(p, "Baby_Potato", true, "bacon");
					}else if(args[0].equalsIgnoreCase("enderchest")) {
						addMB(p, "_Brennian", false, "enderchest");
					}else if(args[0].equalsIgnoreCase("monitor")) {
						addMB(p, "Alistor", false, "monitor");
					}else if(args[0].equalsIgnoreCase("blaze")) {
						addMB(p, "MHF_Blaze", false, "blaze");
					}else if(args[0].equalsIgnoreCase("bookshelf")) {
						addMB(p, "BowAimbot", false, "bookshelf");
					}else if(args[0].equalsIgnoreCase("ice")) {
						addMB(p, "icytouch", false, "ice");
					}else if(args[0].equalsIgnoreCase("ironchest")) {
						addMB(p, "godman21", false, "ironchest");
					}else if(args[0].equalsIgnoreCase("furnace")) {
						addMB(p, "NegativeZeroTV", false, "furnace");
					}else if(args[0].equalsIgnoreCase("spawner")) {
						addMB(p, "GAMEZENMASTER", false, "spawner");
					}else if(args[0].equalsIgnoreCase("qcube")) {
						addMB(p, "jarrettgabe", false, "qcube");
					}else if(args[0].equalsIgnoreCase("cactus")) {
						addMB(p, "MHF_Cactus", false, "cactus");
					}else if(args[0].equalsIgnoreCase("cake")) {
						addMB(p, "MHF_Cake", false, "cake");
					}else if(args[0].equalsIgnoreCase("camera")) {
						addMB(p, "FHG_Cam", false, "camera");
					}else if(args[0].equalsIgnoreCase("cavespider")) {
						addMB(p, "MHF_CaveSpider", false, "cavespider");
					}else if(args[0].equalsIgnoreCase("horse")) {
						addMB(p, "gavertoso", false, "horse");
					}else if(args[0].equalsIgnoreCase("cherry")) {
						addMB(p, "TheEvilEnderman", false, "cherry");
					}else if(args[0].equalsIgnoreCase("chest")) {
						addMB(p, "MHF_Chest", false, "chest");
					}else if(args[0].equalsIgnoreCase("chicken")) {
						addMB(p, "MHF_Chicken", false, "chicken");
					}else if(args[0].equalsIgnoreCase("clock")) {
						addMB(p, "nikx004", false, "clock");
					}else if(args[0].equalsIgnoreCase("coconut")) {
						addMB(p, "KyleWDM", false, "coconut");
					}else if(args[0].equalsIgnoreCase("companioncube")) {
						addMB(p, "sk8erace1", false, "companioncube");
					}else if(args[0].equalsIgnoreCase("cow")) {
						addMB(p, "MHF_Cow", false, "cow");
					}else if(args[0].equalsIgnoreCase("derpysnow")) {
						addMB(p, "GLaDOS", false, "derpysnow");
					}else if(args[0].equalsIgnoreCase("diamondblock")) {
						addMB(p, "Fyspyguy", false, "diamondblock");
					}else if(args[0].equalsIgnoreCase("diamondore")) {
						addMB(p, "akaBruce", false, "diamondore");
					}else if(args[0].equalsIgnoreCase("glowstone")) {
						addMB(p, "samstine11", false, "glowstone");
					}else if(args[0].equalsIgnoreCase("beachball")) {
						addMB(p, "PurplePenguinLPs", false, "beachball");
					}else if(args[0].equalsIgnoreCase("dicewhite")) {
						addMB(p, "jmars213", false, "dicewhite");
					}else if(args[0].equalsIgnoreCase("dicered")) {
						addMB(p, "gumbo632", false, "dicered");
					}else if(args[0].equalsIgnoreCase("diceblack")) {
						addMB(p, "azbandit2000", false, "diceblack");
					}else if(args[0].equalsIgnoreCase("dirt")) {
						addMB(p, "zachman228", false, "dirt");
					}else if(args[0].equalsIgnoreCase("dispenser")) {
						addMB(p, "scemm", false, "dispenser");
					}else if(args[0].equalsIgnoreCase("emeraldore")) {
						addMB(p, "Tereneckla", false, "emeraldore");
					}else if(args[0].equalsIgnoreCase("enderdragon")) {
						addMB(p, "KingEndermen", false, "enderdragon");
					}else if(args[0].equalsIgnoreCase("enderman")) {
						addMB(p, "MHF_Enderman", false, "enderman");
					}else if(args[0].equalsIgnoreCase("exclamation")) {
						addMB(p, "MHF_Exclamation", false, "exclamation");
					}else if(args[0].equalsIgnoreCase("golem")) {
						addMB(p, "MHF_Golem", false, "golem");
					}else if(args[0].equalsIgnoreCase("grass")) {
						addMB(p, "MoulaTime", false, "grass");
					}else if(args[0].equalsIgnoreCase("haybale")) {
						addMB(p, "Bendablob", false, "haybale");
					}else if(args[0].equalsIgnoreCase("headlight")) {
						addMB(p, "Toby_The_Coder", false, "headlight");
					}else if(args[0].equalsIgnoreCase("herobrine")) {
						addMB(p, "MHF_Herobrine", false, "herobrine");
					}else if(args[0].equalsIgnoreCase("ironblock")) {
						addMB(p, "metalhedd", false, "ironblock");
					}else if(args[0].equalsIgnoreCase("witch")) {
						addMB(p, "scrafbrothers4", false, "witch");
					}else if(args[0].equalsIgnoreCase("jukebox")) {
						addMB(p, "C418", false, "jukebox");
					}else if(args[0].equalsIgnoreCase("lampon")) {
						addMB(p, "AutoSoup", false, "lampon");
					}else if(args[0].equalsIgnoreCase("lavaslime")) {
						addMB(p, "MHF_LavaSlime", false, "lavaslime");
					}else if(args[0].equalsIgnoreCase("leaves")) {
						addMB(p, "rsfx", false, "leaves");
					}else if(args[0].equalsIgnoreCase("lemon")) {
						addMB(p, "Aesixx", false, "lemon");
					}else if(args[0].equalsIgnoreCase("lime")) {
						addMB(p, "greenskull27", false, "lime");
					}else if(args[0].equalsIgnoreCase("machine")) {
						addMB(p, "aetherX", false, "machine");
					}else if(args[0].equalsIgnoreCase("melon")) {
						addMB(p, "MHF_Melon", false, "melon");
					}else if(args[0].equalsIgnoreCase("mossycobblestone")) {
						addMB(p, "Khrenan", false, "mossycobblestone");
					}else if(args[0].equalsIgnoreCase("muffin")) {
						addMB(p, "ChoclateMuffin", false, "muffin");
					}else if(args[0].equalsIgnoreCase("mushroomcow")) {
						addMB(p, "MHF_MushroomCow", false, "mushroomcow");
					}else if(args[0].equalsIgnoreCase("netherrack")) {
						addMB(p, "Numba_one_Stunna", false, "netherrack");
					}else if(args[0].equalsIgnoreCase("notexture")) {
						addMB(p, "ddrl46", false, "notexture");
					}else if(args[0].equalsIgnoreCase("oaklog2")) {
						addMB(p, "MightyMega", false, "oaklog2");
					}else if(args[0].equalsIgnoreCase("oaklog")) {
						addMB(p, "MHF_OakLog", false, "oaklog");
					}else if(args[0].equalsIgnoreCase("obsidian")) {
						addMB(p, "loiwiol", false, "obsidian");
					}else if(args[0].equalsIgnoreCase("ocelot")) {
						addMB(p, "MHF_Ocelot", false, "ocelot");
					}else if(args[0].equalsIgnoreCase("orange")) {
						addMB(p, "hi1232", false, "orange");
					}else if(args[0].equalsIgnoreCase("eyeofender")) {
						addMB(p, "Edna_I", false, "eyeofender");
					}else if(args[0].equalsIgnoreCase("pigzombie")) {
						addMB(p, "MHF_PigZombie", false, "pigzombie");
					}else if(args[0].equalsIgnoreCase("pig")) {
						addMB(p, "MHF_Pig", false, "pig");
					}else if(args[0].equalsIgnoreCase("piston")) {
						addMB(p, "JL2579", false, "piston");
					}else if(args[0].equalsIgnoreCase("podzol")) {
						addMB(p, "PhasePvP", false, "podzol");
					}else if(args[0].equalsIgnoreCase("popcorn")) {
						addMB(p, "ZachWarnerHD", false, "popcorn");
					}else if(args[0].equalsIgnoreCase("present")) {
						addMB(p, "I_Xenon_I", false, "present");
					}else if(args[0].equalsIgnoreCase("pumpkinface")) {
						addMB(p, "Koebasti", false, "pumpkinface");
					}else if(args[0].equalsIgnoreCase("pumpkin")) {
						addMB(p, "MHF_Pumpkin", false, "pumpkin");
					}else if(args[0].equalsIgnoreCase("quartzblock")) {
						addMB(p, "bubbadawg01", false, "quartzblock");
					}else if(args[0].equalsIgnoreCase("question")) {
						addMB(p, "MHF_Question", false, "question");
					}else if(args[0].equalsIgnoreCase("radio")) {
						addMB(p, "uioz", false, "radio");
					}else if(args[0].equalsIgnoreCase("redsand")) {
						addMB(p, "OmniSulfur", false, "redsand");
					}else if(args[0].equalsIgnoreCase("redstoneore")) {
						addMB(p, "annayirb", false, "redstoneore");
					}else if(args[0].equalsIgnoreCase("rubixcube2")) {
						addMB(p, "Unreal", true, "rubixcube2");
					}else if(args[0].equalsIgnoreCase("rubixcube")) {
						addMB(p, "iTactical17", false, "rubixcube");
					}else if(args[0].equalsIgnoreCase("sand")) {
						addMB(p, "rugofluk", false, "sand");
					}else if(args[0].equalsIgnoreCase("sheep")) {
						addMB(p, "MHF_Sheep", false, "sheep");
					}else if(args[0].equalsIgnoreCase("slime")) {
						addMB(p, "MHF_Slime", false, "slime");
					}else if(args[0].equalsIgnoreCase("speaker")) {
						addMB(p, "b1418", false, "speaker");
					}else if(args[0].equalsIgnoreCase("spider")) {
						addMB(p, "MHF_Spider", false, "spider");
					}else if(args[0].equalsIgnoreCase("sponge")) {
						addMB(p, "pomi44", false, "sponge");
					}else if(args[0].equalsIgnoreCase("squid")) {
						addMB(p, "MHF_Squid", false, "squid");
					}else if(args[0].equalsIgnoreCase("stickypiston")) {
						addMB(p, "Panda4994", false, "stickypiston");
					}else if(args[0].equalsIgnoreCase("stone")) {
						addMB(p, "Robbydeezle", false, "stone");
					}else if(args[0].equalsIgnoreCase("taco")) {
						addMB(p, "Crunchy_Taco34", false, "taco");
					}else if(args[0].equalsIgnoreCase("tnt2")) {
						addMB(p, "MHF_TNT2", false, "tnt2");
					}else if(args[0].equalsIgnoreCase("tnt")) {
						addMB(p, "MHF_TNT", false, "tnt");
					}else if(args[0].equalsIgnoreCase("toaster")) {
						addMB(p, "pologobbyboy", false, "toaster");
					}else if(args[0].equalsIgnoreCase("toiletpaper")) {
						addMB(p, "Ethegj", false, "toiletpaper");
					}else if(args[0].equalsIgnoreCase("tv")) {
						addMB(p, "Metroidling", false, "tv");
					}else if(args[0].equalsIgnoreCase("villager")) {
						addMB(p, "MHF_Villager", false, "villager");
					}else if(args[0].equalsIgnoreCase("ghast")) {
						addMB(p, "MHF_Ghast", false, "ghast");
					}else if(args[0].equalsIgnoreCase("oakplanks")) {
						addMB(p, "terryxu", false, "oakplanks");
					}else if(args[0].equalsIgnoreCase("gamecube")) {
						addMB(p, "ReflectedNicK", false, "gamecube");
					}else if(args[0].equalsIgnoreCase("redstoneblock")) {
						addMB(p, "AlexDr0ps", false, "redstoneblock");
					}else if(args[0].equalsIgnoreCase("tv2")) {
						addMB(p, "nonesuchplace", false, "tv2");
					}else if(args[0].equalsIgnoreCase("troll")) {
						addMB(p, "Trollface20", false, "troll");
					}else if(args[0].equalsIgnoreCase("eye")) {
						addMB(p, "Taizun", false, "eye");
					}else if(args[0].equalsIgnoreCase("parrot")) {
						addMB(p, "Luk3011", false, "parrot");
						p.sendMessage(ChatColor.GOLD + "This microblock is " + ChatColor.GRAY + "diagonal" + ChatColor.GOLD + ".");
					}else if(args[0].equalsIgnoreCase("about") || args[0].equalsIgnoreCase("help")) {
						p.sendMessage(ChatColor.GOLD + "Microblocks " + ChatColor.GRAY + "v" + mb.getDescription().getVersion() + ChatColor.GOLD + " by " + ChatColor.GRAY +
								"CraftyCreator/itsCrafted" + ChatColor.GOLD + ".");
						p.sendMessage(ChatColor.RED + "/mb for a list of blocks.");
						p.sendMessage(ChatColor.RED + "/mb <block> to spawn a Microblock.");
						p.sendMessage(ChatColor.RED + "/skull <player/self> to spawn a skull");
					}else if(args[0].equalsIgnoreCase("pokeball")) {
						addMB(p, "Chuzard", false, "pokeball");
					}else if(args[0].equalsIgnoreCase("cookie")) {
						addMB(p, "QuadratCookie", false, "cookie");
					}else if(args[0].equalsIgnoreCase("2")) {
						p.sendMessage("");
						p.sendMessage("");
						p.sendMessage(ChatColor.RED + "Microblocks (Page Two):" + ChatColor.YELLOW + arrayToString(secondPage));
						p.sendMessage(ChatColor.RED + "Type /mb for the first page.");
						p.sendMessage(ChatColor.RED + "Usage: /mb <block>");
					}else if(args[0].equalsIgnoreCase("workbench")) {
						addMB(p, "Russellgoo97", false, "workbench");
					}else if(args[0].equalsIgnoreCase("orangewool")) {
						addMB(p, "titou36", false, "orangewool");
					}else if(args[0].equalsIgnoreCase("stonebrick")) {
						addMB(p, "Cakers", false, "stonebrick");
					}else if(args[0].equalsIgnoreCase("swskeleton")) {
						addMB(p, "lesto123", false, "swskeleton");
					}else if(args[0].equalsIgnoreCase("swzombie")) {
						addMB(p, "maximxc", false, "swzombie");
					}else if(args[0].equalsIgnoreCase("lapis")) {
						addMB(p, "Apa333", false, "lapis");
					}else {
						p.sendMessage(ChatColor.RED + "Unknown microblock!");
						p.sendMessage(ChatColor.RED + "Use /mb for a list of microblocks.");
					}
				}else if(args.length >= 2) {
					p.sendMessage(ChatColor.RED + "Too many arguments!");
					p.sendMessage(ChatColor.RED + "Use /mb for a list of microblocks.");
				}
			}
		}
		
		return false;
	}
	
}
