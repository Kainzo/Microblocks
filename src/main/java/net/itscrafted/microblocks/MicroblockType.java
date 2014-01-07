package net.itscrafted.microblocks;

import java.util.HashMap;
import java.util.Map;

public class MicroblockType {
	private String blockName;
	private String playerName;
	private boolean safe;

	private MicroblockType(String blockName, String playerName, boolean safe) {
		this.blockName = blockName;
		this.playerName = playerName;
		this.safe = safe;
	}
	
	public String getBlockName() {
		return blockName;
	}
	public String getPlayerName() {
		return playerName;
	}
	public boolean isSafe() {
		return safe;
	}

	public static final Map<String, MicroblockType> BLOCK_MAP = new HashMap<String, MicroblockType>();
	public static final Map<String, MicroblockType> PLAYER_MAP = new HashMap<String, MicroblockType>();

	private static void add(String blockName, String playerName, boolean safe) {
		blockName = blockName.toLowerCase();
		MicroblockType mb = new MicroblockType(blockName, playerName, safe);
		BLOCK_MAP.put(blockName, mb);
		PLAYER_MAP.put(playerName, mb);
	}
	
	static {
		add("apple", "MHF_Apple", true);
		add("arrowdown", "MHF_ArrowDown", true);
		add("arrowleft", "MHF_ArrowLeft", true);
		add("arrowright", "MHF_ArrowRight", true);
		add("arrowup", "MHF_ArrowUp", true);
		add("enderchest", "_Brennian", false);
		add("monitor", "Alistor", false);
		add("blaze", "MHF_Blaze", true);
		add("bookshelf", "BowAimbot", true);
		add("ice", "icytouch", false);
		add("ironchest", "godman21", false);
		add("furnace", "NegativeZeroTV", false);
		//add("spawner", "GAMEZENMASTER", true);
		add("qcube", "jarrettgabe", true);
		add("cactus", "MHF_Cactus", true);
		add("cake", "MHF_Cake", true);
		add("camera", "FHG_Cam", true);
		add("cavespider", "MHF_CaveSpider", true);
		add("horse", "gavertoso", false);
		add("cherry", "TheEvilEnderman", false);
		add("chest", "MHF_Chest", true);
		add("chicken", "MHF_Chicken", true);
		add("clock", "nikx004", false);
		add("coconut", "KyleWDM", false);
		add("companioncube", "sk8erace1", false);
		add("cow", "MHF_Cow", true);
		add("derpysnow", "GLaDOS", false);
		add("diamondblock", "Fyspyguy", false);
		add("diamondore", "akaBruce", false);
		add("glowstone", "samstine11", false);
		add("beachball", "PurplePenguinLPs", false);
		add("dicewhite", "jmars213", false);
		add("dicered", "gumbo632", false);
		add("diceblack", "azbandit2000", false);
		add("dirt", "zachman228", false);
		add("dispenser", "scemm", false);
		add("emeraldore", "Tereneckla", true);
		add("enderdragon", "KingEndermen", true);
		add("enderman", "MHF_Enderman", true);
		add("exclamation", "MHF_Exclamation", true);
		add("golem", "MHF_Golem", true);
		add("grass", "MoulaTime", false);
		add("haybale", "Bendablob", true);
		add("headlight", "Toby_The_Coder", false);
		add("herobrine", "MHF_Herobrine", true);
		add("ironblock", "metalhedd", false);
		add("witch", "scrafbrothers4", false);
		add("jukebox", "C418", true);
		add("lampon", "AutoSoup", true);
		add("lavaslime", "MHF_LavaSlime", true);
		add("leaves", "rsfx", false);
		add("lemon", "Aesixx", false);
		add("lime", "greenskull27", false);
		add("machine", "aetherX", false);
		add("melon", "MHF_Melon", true);
		add("mossycobblestone", "Khrenan", false);
		add("muffin", "ChoclateMuffin", false);
		add("mushroomcow", "MHF_MushroomCow", true);
		add("netherrack", "Numba_one_Stunna", false);
		add("notexture", "ddrl46", false);
		add("oaklog2", "MightyMega", false);
		add("oaklog", "MHF_OakLog", true);
		add("obsidian", "loiwiol", false);
		add("ocelot", "MHF_Ocelot", true);
		add("orange", "hi1232", false);
		add("eyeofender", "Edna_I", true);
		add("pigzombie", "MHF_PigZombie", true);
		add("pig", "MHF_Pig", true);
		add("piston", "JL2579", false);
		add("podzol", "PhasePvP", true);
		add("popcorn", "ZachWarnerHD", false);
		add("present", "I_Xenon_I", false);
		add("pumpkinface", "Koebasti", false);
		add("pumpkin", "MHF_Pumpkin", true);
		add("quartzblock", "bubbadawg01", true);
		add("question", "MHF_Question", true);
		add("radio", "uioz", true);
		add("redsand", "OmniSulfur", true);
		add("redstoneore", "annayirb", false);
		add("rubixcube", "iTactical17", false);
		add("sand", "rugofluk", false);
		add("sheep", "MHF_Sheep", true);
		add("slime", "MHF_Slime", true);
		add("speaker", "b1418", false);
		add("spider", "MHF_Spider", true);
		add("sponge", "pomi44", false);
		add("squid", "MHF_Squid", true);
		add("stickypiston", "Panda4994", false);
		add("stone", "Robbydeezle", false);
		add("taco", "Crunchy_Taco34", false);
		add("tnt2", "MHF_TNT2", true);
		add("tnt", "MHF_TNT", true);
		//add("toaster", "pologobbyboy", false);
		add("toiletpaper", "Ethegj", false);
		add("tv", "Metroidling", false);
		add("villager", "MHF_Villager", true);
		add("ghast", "MHF_Ghast", true);
		add("oakplanks", "terryxu", false);
		add("gamecube", "ReflectedNicK", false);
		add("redstoneblock", "AlexDr0ps", false);
		add("tv2", "nonesuchplace", false);
		add("troll", "Trollface20", false);
		add("eye", "Taizun", false);
		add("parrot", "Luk3011", false);
		add("pokeball", "Chuzard", false);
		add("cookie", "QuadratCookie", false);
		//add("workbench", "Russellgoo97", false);
		add("orangewool", "titou36", false);
		add("stonebrick", "Cakers", false);
		add("swskeleton", "lesto123", false);
		//add("swzombie", "maximxc", false);
		add("goldblock", "StackedGold", false);
		add("fox", "hugge75", false);
		add("potato", "CraftPotato13", false);
		add("leaves2", "half_bit", false);
		add("cobblestone", "_Rience", true);
		add("water", "emack0714", false);
		add("noteblock", "PixelJuke", false);
		add("brick", "BrickInTheHead", false);
	}
}
