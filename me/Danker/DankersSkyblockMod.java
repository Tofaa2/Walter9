/*      */ package me.Danker;
/*      */ import java.awt.Color;
/*      */ import java.awt.SystemTray;
/*      */ import java.awt.TrayIcon;
/*      */ import java.text.NumberFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.regex.Matcher;
/*      */ import java.util.regex.Pattern;
/*      */ import me.Danker.commands.BlockSlayerCommand;
/*      */ import me.Danker.commands.DisplayCommand;
/*      */ import me.Danker.commands.LootCommand;
/*      */ import me.Danker.commands.MoveCommand;
/*      */ import me.Danker.commands.RepartyCommand;
/*      */ import me.Danker.commands.ScaleCommand;
/*      */ import me.Danker.commands.SleepCommand;
/*      */ import me.Danker.commands.SwapCommand;
/*      */ import me.Danker.commands.ToggleCommand;
/*      */ import me.Danker.handlers.ConfigHandler;
/*      */ import me.Danker.handlers.ScoreboardHandler;
/*      */ import me.Danker.handlers.TextRenderer;
/*      */ import me.Danker.utils.Utils;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.client.Minecraft;
/*      */ import net.minecraft.client.entity.EntityPlayerSP;
/*      */ import net.minecraft.client.gui.GuiScreen;
/*      */ import net.minecraft.client.gui.ScaledResolution;
/*      */ import net.minecraft.client.gui.inventory.GuiChest;
/*      */ import net.minecraft.client.multiplayer.WorldClient;
/*      */ import net.minecraft.client.settings.GameSettings;
/*      */ import net.minecraft.client.settings.KeyBinding;
/*      */ import net.minecraft.command.ICommand;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.item.EntityArmorStand;
/*      */ import net.minecraft.entity.item.EntityItemFrame;
/*      */ import net.minecraft.entity.monster.EntityCreeper;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.inventory.Container;
/*      */ import net.minecraft.inventory.ContainerChest;
/*      */ import net.minecraft.inventory.IInventory;
/*      */ import net.minecraft.inventory.Slot;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.util.AxisAlignedBB;
/*      */ import net.minecraft.util.BlockPos;
/*      */ import net.minecraft.util.ChatComponentText;
/*      */ import net.minecraft.util.EnumChatFormatting;
/*      */ import net.minecraft.util.IChatComponent;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import net.minecraft.util.StringUtils;
/*      */ import net.minecraft.util.Vec3;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraft.world.storage.MapData;
/*      */ import net.minecraftforge.client.ClientCommandHandler;
/*      */ import net.minecraftforge.client.event.ClientChatReceivedEvent;
/*      */ import net.minecraftforge.client.event.GuiOpenEvent;
/*      */ import net.minecraftforge.client.event.GuiScreenEvent;
/*      */ import net.minecraftforge.client.event.RenderGameOverlayEvent;
/*      */ import net.minecraftforge.client.event.RenderWorldLastEvent;
/*      */ import net.minecraftforge.event.entity.player.EntityInteractEvent;
/*      */ import net.minecraftforge.event.entity.player.ItemTooltipEvent;
/*      */ import net.minecraftforge.event.entity.player.PlayerInteractEvent;
/*      */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*      */ import net.minecraftforge.fml.common.gameevent.TickEvent;
/*      */ import org.lwjgl.input.Keyboard;
/*      */ import org.lwjgl.input.Mouse;
/*      */ import org.lwjgl.opengl.GL11;
/*      */ 
/*      */ @Mod(modid = "Danker's Skyblock Mod", version = "1.8.6-beta2", clientSideOnly = true, acceptedMinecraftVersions = "[1.8.9]")
/*      */ public class DankersSkyblockMod {
/*      */   public static final String MODID = "Danker's Skyblock Mod";
/*   79 */   public static Map<String, String> t6Enchants = new HashMap<>(); public static final String VERSION = "1.8.6-beta2";
/*   80 */   public static Pattern t6EnchantPattern = Pattern.compile("");
/*   81 */   static Pattern petPattern = Pattern.compile("\\[Lvl [\\d]{1,3}]");
/*      */   static boolean updateChecked = false;
/*   83 */   public static int titleTimer = -1;
/*      */   public static boolean showTitle = false;
/*   85 */   public static String titleText = "";
/*      */   public static int SKILL_TIME;
/*   87 */   public static int skillTimer = -1;
/*      */   public static boolean showSkill = false;
/*   89 */   public static String skillText = "";
/*   90 */   public static int until = 0;
/*   91 */   public static int lastSlot = -1;
/*   92 */   public static int slotIn = -1;
/*   93 */   static int tickAmount = 1;
/*   94 */   static String lastMaddoxCommand = "/cb placeholder";
/*   95 */   static double lastMaddoxTime = 0.0D;
/*   96 */   static KeyBinding[] keyBindings = new KeyBinding[8];
/*      */   static boolean usingLabymod = false;
/*      */   static boolean usingOAM = false;
/*      */   static boolean OAMWarning = false;
/*  100 */   public static String guiToOpen = null;
/*      */   static boolean foundLivid = false;
/*  102 */   static Entity livid = null;
/*      */   public static double cakeTime;
/*  104 */   public static double nextBonzoUse = 0.0D;
/*      */   public static boolean firstLaunch = false;
/*  106 */   static String[] harpInv = new String[54];
/*  107 */   public static int chestOpen = 0;
/*      */   
/*      */   public static long lastInteractTime;
/*  110 */   public static final ResourceLocation CAKE_ICON = new ResourceLocation("dsm", "icons/cake.png");
/*  111 */   public static final ResourceLocation BONZO_ICON = new ResourceLocation("dsm", "icons/bonzo.png");
/*      */   
/*  113 */   static String[] riddleSolutions = new String[] { "The reward is not in my chest!", "At least one of them is lying, and the reward is not in", "My chest doesn't have the reward. We are all telling the truth", "My chest has the reward and I'm telling the truth", "The reward isn't in any of our chests", "Both of them are telling the truth." };
/*      */ 
/*      */   
/*  116 */   static Map<String, String[]> triviaSolutions = (Map)new HashMap<>();
/*  117 */   static String[] triviaAnswers = null;
/*  118 */   static Entity highestBlaze = null;
/*  119 */   static Entity lowestBlaze = null;
/*      */   
/*  121 */   static final int[] CREEPER_COLOURS = new int[] { 5304121, 12914961, 1257169, 1146669, 15553722, 15695117, 16119127, 14082288, 7024571, 3800796 };
/*      */   static boolean drawCreeperLines = false;
/*  123 */   static Vec3 creeperLocation = new Vec3(0.0D, 0.0D, 0.0D);
/*  124 */   static List<Vec3[]> creeperLines = (List)new ArrayList<>();
/*      */   static boolean prevInWaterRoom = false;
/*      */   static boolean inWaterRoom = false;
/*  127 */   static String waterAnswers = null;
/*  128 */   static AxisAlignedBB correctTicTacToeButton = null;
/*  129 */   static Pattern startsWithTerminalPattern = Pattern.compile("[A-Z]{2,}");
/*  130 */   static Slot[] clickInOrderSlots = new Slot[36];
/*  131 */   static int lastChronomatronRound = 0;
/*  132 */   static List<String> chronomatronPattern = new ArrayList<>();
/*  133 */   static int chronomatronMouseClicks = 0;
/*  134 */   static int lastUltraSequencerClicked = 0;
/*  135 */   static ItemStack[] experimentTableSlots = new ItemStack[54];
/*      */   static int pickBlockBind;
/*      */   static boolean pickBlockBindSwapped = false;
/*      */   static String terminalColorNeeded;
/*  139 */   static int[] terminalNumberNeeded = new int[13];
/*  140 */   static int[] chest = new int[54];
/*      */   
/*  142 */   static double dungeonStartTime = 0.0D;
/*  143 */   static double bloodOpenTime = 0.0D;
/*  144 */   static double watcherClearTime = 0.0D;
/*  145 */   static double bossClearTime = 0.0D;
/*  146 */   static int witherDoors = 0;
/*  147 */   static int dungeonDeaths = 0;
/*  148 */   static int puzzleFails = 0;
/*      */   
/*  150 */   public int mazeId = 0;
/*  151 */   public int sword = 10;
/*  152 */   public int bow = 10;
/*      */   
/*  154 */   static String lastSkill = "Farming";
/*      */   public static boolean showSkillTracker;
/*  156 */   public static StopWatch skillStopwatch = new StopWatch();
/*  157 */   static double farmingXP = 0.0D;
/*  158 */   public static double farmingXPGained = 0.0D;
/*  159 */   static double miningXP = 0.0D;
/*  160 */   public static double miningXPGained = 0.0D;
/*  161 */   static double combatXP = 0.0D;
/*  162 */   public static double combatXPGained = 0.0D;
/*  163 */   static double foragingXP = 0.0D;
/*  164 */   public static double foragingXPGained = 0.0D;
/*  165 */   static double fishingXP = 0.0D;
/*  166 */   public static double fishingXPGained = 0.0D;
/*  167 */   static double enchantingXP = 0.0D;
/*  168 */   public static double enchantingXPGained = 0.0D;
/*  169 */   static double alchemyXP = 0.0D;
/*  170 */   public static double alchemyXPGained = 0.0D;
/*  171 */   static double xpLeft = 0.0D;
/*  172 */   static double timeSinceGained = 0.0D;
/*      */   
/*      */   public static String MAIN_COLOUR;
/*      */   public static String SECONDARY_COLOUR;
/*      */   public static String ERROR_COLOUR;
/*      */   public static String DELIMITER_COLOUR;
/*      */   public static String TYPE_COLOUR;
/*      */   public static String VALUE_COLOUR;
/*      */   public static String SKILL_AVERAGE_COLOUR;
/*      */   public static String ANSWER_COLOUR;
/*      */   public static String SKILL_50_COLOUR;
/*      */   public static String COORDS_COLOUR;
/*      */   public static String CAKE_COLOUR;
/*      */   public static String SKILL_TRACKER_COLOUR;
/*      */   public static String TRIVIA_WRONG_ANSWER_COLOUR;
/*      */   public static String BONZO_COLOR;
/*      */   public static int LOWEST_BLAZE_COLOUR;
/*      */   public static int HIGHEST_BLAZE_COLOUR;
/*      */   public static int PET_1_TO_9;
/*      */   public static int PET_10_TO_19;
/*      */   public static int PET_20_TO_29;
/*      */   public static int PET_30_TO_39;
/*      */   public static int PET_40_TO_49;
/*      */   public static int PET_50_TO_59;
/*      */   public static int PET_60_TO_69;
/*      */   public static int PET_70_TO_79;
/*      */   public static int PET_80_TO_89;
/*      */   public static int PET_90_TO_99;
/*      */   public static int PET_100;
/*      */   
/*      */   @EventHandler
/*      */   public void init(FMLInitializationEvent event) {
/*  204 */     MinecraftForge.EVENT_BUS.register(this);
/*  205 */     MinecraftForge.EVENT_BUS.register(new PacketHandler());
/*      */     
/*  207 */     ConfigHandler.reloadConfig();
/*      */ 
/*      */     
/*  210 */     t6Enchants.put("9Angler VI", "6Angler VI");
/*  211 */     t6Enchants.put("9Bane of Arthropods VI", "6Bane of Arthropods VI");
/*  212 */     t6Enchants.put("9Caster VI", "6Caster VI");
/*  213 */     t6Enchants.put("9Compact X", "6Compact X");
/*  214 */     t6Enchants.put("9Critical VI", "6Critical VI");
/*  215 */     t6Enchants.put("9Dragon Hunter V", "6Dragon Hunter V");
/*  216 */     t6Enchants.put("9Efficiency VI", "6Efficiency VI");
/*  217 */     t6Enchants.put("9Ender Slayer VI", "6Ender Slayer VI");
/*  218 */     t6Enchants.put("9Experience IV", "6Experience IV");
/*  219 */     t6Enchants.put("9Expertise X", "6Expertise X");
/*  220 */     t6Enchants.put("9Feather Falling X", "6Feather Falling X");
/*  221 */     t6Enchants.put("9Frail VI", "6Frail VI");
/*  222 */     t6Enchants.put("9Giant Killer VI", "6Giant Killer VI");
/*  223 */     t6Enchants.put("9Growth VI", "6Growth VI");
/*  224 */     t6Enchants.put("9Infinite Quiver X", "6Infinite Quiver X");
/*  225 */     t6Enchants.put("9Lethality VI", "6Lethality VI");
/*  226 */     t6Enchants.put("9Life Steal IV", "6Life Steal IV");
/*  227 */     t6Enchants.put("9Looting IV", "6Looting IV");
/*  228 */     t6Enchants.put("9Luck VI", "6Luck VI");
/*  229 */     t6Enchants.put("9Luck of the Sea VI", "6Luck of the Sea VI");
/*  230 */     t6Enchants.put("9Lure VI", "6Lure VI");
/*  231 */     t6Enchants.put("9Magnet VI", "6Magnet VI");
/*  232 */     t6Enchants.put("9Overload V", "6Overload V");
/*  233 */     t6Enchants.put("9Power VI", "6Power VI");
/*  234 */     t6Enchants.put("9Protection VI", "6Protection VI");
/*  235 */     t6Enchants.put("9Scavenger IV", "6Scavenger IV");
/*  236 */     t6Enchants.put("9Scavenger V", "6Scavenger V");
/*  237 */     t6Enchants.put("9Sharpness VI", "6Sharpness VI");
/*  238 */     t6Enchants.put("9Smite VI", "6Smite VI");
/*  239 */     t6Enchants.put("9Spiked Hook VI", "6Spiked Hook VI");
/*  240 */     t6Enchants.put("9Thunderlord VI", "6Thunderlord VI");
/*  241 */     t6Enchants.put("9Vampirism VI", "6Vampirism VI");
/*      */     
/*  243 */     triviaSolutions.put("What is the status of The Watcher?", new String[] { "Stalker" });
/*  244 */     triviaSolutions.put("What is the status of Bonzo?", new String[] { "New Necromancer" });
/*  245 */     triviaSolutions.put("What is the status of Scarf?", new String[] { "Apprentice Necromancer" });
/*  246 */     triviaSolutions.put("What is the status of The Professor?", new String[] { "Professor" });
/*  247 */     triviaSolutions.put("What is the status of Thorn?", new String[] { "Shaman Necromancer" });
/*  248 */     triviaSolutions.put("What is the status of Livid?", new String[] { "Master Necromancer" });
/*  249 */     triviaSolutions.put("What is the status of Sadan?", new String[] { "Necromancer Lord" });
/*  250 */     triviaSolutions.put("What is the status of Maxor?", new String[] { "Young Wither" });
/*  251 */     triviaSolutions.put("What is the status of Goldor?", new String[] { "Wither Soldier" });
/*  252 */     triviaSolutions.put("What is the status of Storm?", new String[] { "Elementalist" });
/*  253 */     triviaSolutions.put("What is the status of Necron?", new String[] { "Wither Lord" });
/*  254 */     triviaSolutions.put("How many total Fairy Souls are there?", new String[] { "220 Fairy Souls" });
/*  255 */     triviaSolutions.put("How many Fairy Souls are there in Spider's Den?", new String[] { "17 Fairy Souls" });
/*  256 */     triviaSolutions.put("How many Fairy Souls are there in The End?", new String[] { "12 Fairy Souls" });
/*  257 */     triviaSolutions.put("How many Fairy Souls are there in The Barn?", new String[] { "7 Fairy Souls" });
/*  258 */     triviaSolutions.put("How many Fairy Souls are there in Mushroom Desert?", new String[] { "8 Fairy Souls" });
/*  259 */     triviaSolutions.put("How many Fairy Souls are there in Blazing Fortress?", new String[] { "19 Fairy Souls" });
/*  260 */     triviaSolutions.put("How many Fairy Souls are there in The Park?", new String[] { "11 Fairy Souls" });
/*  261 */     triviaSolutions.put("How many Fairy Souls are there in Jerry's Workshop?", new String[] { "5 Fairy Souls" });
/*  262 */     triviaSolutions.put("How many Fairy Souls are there in Hub?", new String[] { "79 Fairy Souls" });
/*  263 */     triviaSolutions.put("How many Fairy Souls are there in The Hub?", new String[] { "79 Fairy Souls" });
/*  264 */     triviaSolutions.put("How many Fairy Souls are there in Deep Caverns?", new String[] { "21 Fairy Souls" });
/*  265 */     triviaSolutions.put("How many Fairy Souls are there in Gold Mine?", new String[] { "12 Fairy Souls" });
/*  266 */     triviaSolutions.put("How many Fairy Souls are there in Dungeon Hub?", new String[] { "7 Fairy Souls" });
/*  267 */     triviaSolutions.put("Which brother is on the Spider's Den?", new String[] { "Rick" });
/*  268 */     triviaSolutions.put("What is the name of Rick's brother?", new String[] { "Pat" });
/*  269 */     triviaSolutions.put("What is the name of the Painter in the Hub?", new String[] { "Marco" });
/*  270 */     triviaSolutions.put("What is the name of the person that upgrades pets?", new String[] { "Kat" });
/*  271 */     triviaSolutions.put("What is the name of the lady of the Nether?", new String[] { "Elle" });
/*  272 */     triviaSolutions.put("Which villager in the Village gives you a Rogue Sword?", new String[] { "Jamie" });
/*  273 */     triviaSolutions.put("How many unique minions are there?", new String[] { "53 Minions" });
/*  274 */     triviaSolutions.put("Which of these enemies does not spawn in the Spider's Den?", new String[] { "Zombie Spider", "Cave Spider", "Wither Skeleton", "Dashing Spooder", "Broodfather", "Night Spider" });
/*      */     
/*  276 */     triviaSolutions.put("Which of these monsters only spawns at night?", new String[] { "Zombie Villager", "Ghast" });
/*  277 */     triviaSolutions.put("Which of these is not a dragon in The End?", new String[] { "Zoomer Dragon", "Weak Dragon", "Stonk Dragon", "Holy Dragon", "Boomer Dragon", "Booger Dragon", "Older Dragon", "Elder Dragon", "Stable Dragon", "Professor Dragon" });
/*      */ 
/*      */     
/*  280 */     String patternString = "(" + String.join("|", t6Enchants.keySet()) + ")";
/*  281 */     t6EnchantPattern = Pattern.compile(patternString);
/*      */     
/*  283 */     keyBindings[0] = new KeyBinding("Open Maddox Menu", 50, "Danker's Skyblock Mod");
/*  284 */     keyBindings[1] = new KeyBinding("Regular Ability", 75, "Danker's Skyblock Mod");
/*  285 */     keyBindings[2] = new KeyBinding("Start/Stop Skill Tracker", 76, "Danker's Skyblock Mod");
/*  286 */     keyBindings[3] = new KeyBinding("Bone Macro", 48, "Danker's Skyblock Mod");
/*  287 */     keyBindings[4] = new KeyBinding("Reset Fake ID", 25, "Danker's Skyblock Mod");
/*  288 */     keyBindings[5] = new KeyBinding("Right Click Spam", 45, "Danker's Skyblock Mod");
/*  289 */     keyBindings[6] = new KeyBinding("Hyperion Bind", 23, "Danker's Skyblock Mod");
/*  290 */     keyBindings[7] = new KeyBinding("Ghost Block Bind", 34, "Danker's Skyblock Mod");
/*      */     
/*  292 */     for (KeyBinding keyBinding : keyBindings) {
/*  293 */       ClientRegistry.registerKeyBinding(keyBinding);
/*      */     }
/*      */   }
/*      */   
/*      */   @EventHandler
/*      */   public void preInit(FMLPreInitializationEvent event) {
/*  299 */     ClientCommandHandler.instance.func_71560_a((ICommand)new ToggleCommand());
/*  300 */     ClientCommandHandler.instance.func_71560_a((ICommand)new SetkeyCommand());
/*  301 */     ClientCommandHandler.instance.func_71560_a((ICommand)new GetkeyCommand());
/*  302 */     ClientCommandHandler.instance.func_71560_a((ICommand)new DelayCommand());
/*  303 */     ClientCommandHandler.instance.func_71560_a((ICommand)new SimonCommand());
/*  304 */     ClientCommandHandler.instance.func_71560_a((ICommand)new SwapCommand());
/*  305 */     ClientCommandHandler.instance.func_71560_a((ICommand)new SleepCommand());
/*  306 */     ClientCommandHandler.instance.func_71560_a((ICommand)new LootCommand());
/*  307 */     ClientCommandHandler.instance.func_71560_a((ICommand)new ReloadConfigCommand());
/*  308 */     ClientCommandHandler.instance.func_71560_a((ICommand)new DisplayCommand());
/*  309 */     ClientCommandHandler.instance.func_71560_a((ICommand)new MoveCommand());
/*  310 */     ClientCommandHandler.instance.func_71560_a((ICommand)new SlayerCommand());
/*  311 */     ClientCommandHandler.instance.func_71560_a((ICommand)new SkillsCommand());
/*  312 */     ClientCommandHandler.instance.func_71560_a((ICommand)new GuildOfCommand());
/*  313 */     ClientCommandHandler.instance.func_71560_a((ICommand)new DHelpCommand());
/*  314 */     ClientCommandHandler.instance.func_71560_a((ICommand)new PetsCommand());
/*  315 */     ClientCommandHandler.instance.func_71560_a((ICommand)new BankCommand());
/*  316 */     ClientCommandHandler.instance.func_71560_a((ICommand)new ArmourCommand());
/*  317 */     ClientCommandHandler.instance.func_71560_a((ICommand)new ImportFishingCommand());
/*  318 */     ClientCommandHandler.instance.func_71560_a((ICommand)new ResetLootCommand());
/*  319 */     ClientCommandHandler.instance.func_71560_a((ICommand)new ScaleCommand());
/*  320 */     ClientCommandHandler.instance.func_71560_a((ICommand)new SkyblockPlayersCommand());
/*  321 */     ClientCommandHandler.instance.func_71560_a((ICommand)new BlockSlayerCommand());
/*  322 */     ClientCommandHandler.instance.func_71560_a((ICommand)new DungeonsCommand());
/*  323 */     ClientCommandHandler.instance.func_71560_a((ICommand)new LobbySkillsCommand());
/*  324 */     ClientCommandHandler.instance.func_71560_a((ICommand)new DankerGuiCommand());
/*  325 */     ClientCommandHandler.instance.func_71560_a((ICommand)new SkillTrackerCommand());
/*      */   }
/*      */   
/*      */   @EventHandler
/*      */   public void postInit(FMLPostInitializationEvent event) {
/*  330 */     Package[] packages = Package.getPackages();
/*  331 */     for (Package p : packages) {
/*  332 */       if (p.getName().startsWith("com.spiderfrog.gadgets") || p.getName().startsWith("com.spiderfrog.oldanimations")) {
/*  333 */         usingOAM = true;
/*      */       }
/*      */     } 
/*  336 */     System.out.println("OAM detection: " + usingOAM);
/*      */     
/*  338 */     usingLabymod = Loader.isModLoaded("labymod");
/*  339 */     System.out.println("LabyMod detection: " + usingLabymod);
/*      */     
/*  341 */     if (!ClientCommandHandler.instance.func_71555_a().containsKey("reparty")) {
/*  342 */       ClientCommandHandler.instance.func_71560_a((ICommand)new RepartyCommand());
/*  343 */     } else if (ConfigHandler.getBoolean("commands", "reparty")) {
/*  344 */       for (Map.Entry<String, ICommand> entry : (Iterable<Map.Entry<String, ICommand>>)ClientCommandHandler.instance.func_71555_a().entrySet()) {
/*  345 */         if (((String)entry.getKey()).equals("reparty") || ((String)entry.getKey()).equals("rp")) {
/*  346 */           entry.setValue(new RepartyCommand());
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   @SubscribeEvent
/*      */   public void onGuiOpenEvent(GuiOpenEvent event) {
/*  355 */     if (event.gui instanceof net.minecraft.client.gui.GuiMainMenu && usingOAM && !OAMWarning && 
/*  356 */       !(event.gui instanceof WarningGui)) {
/*  357 */       event.gui = (GuiScreen)new WarningGuiRedirect((GuiScreen)new WarningGui());
/*  358 */       OAMWarning = true;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   @SubscribeEvent
/*      */   public void onWorldChange(WorldEvent.Load event) {
/*  365 */     foundLivid = false;
/*  366 */     livid = null;
/*  367 */     nextBonzoUse = 0.0D;
/*  368 */     this.mazeId = 0;
/*      */   }
/*      */ 
/*      */   
/*      */   @SubscribeEvent(priority = EventPriority.HIGHEST)
/*      */   public void onChat(ClientChatReceivedEvent event) {
/*  374 */     String message = StringUtils.func_76338_a(event.message.func_150260_c());
/*      */     
/*  376 */     if (message.startsWith("Your new API key is ") && Utils.isOnHypixel()) {
/*  377 */       String apiKey = ((IChatComponent)event.message.func_150253_a().get(0)).func_150256_b().func_150235_h().func_150668_b();
/*  378 */       ConfigHandler.writeStringConfig("api", "APIKey", apiKey);
/*  379 */       (Minecraft.func_71410_x()).field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(MAIN_COLOUR + "Set API key to " + SECONDARY_COLOUR + apiKey));
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  384 */     if (RepartyCommand.gettingParty) {
/*  385 */       if (message.contains("-----")) {
/*  386 */         switch (RepartyCommand.Delimiter) {
/*      */           case 0:
/*  388 */             System.out.println("Get Party Delimiter Cancelled");
/*  389 */             RepartyCommand.Delimiter++;
/*  390 */             event.setCanceled(true);
/*      */             return;
/*      */           case 1:
/*  393 */             System.out.println("Done querying party");
/*  394 */             RepartyCommand.gettingParty = false;
/*  395 */             RepartyCommand.Delimiter = 0;
/*  396 */             event.setCanceled(true);
/*      */             return;
/*      */         } 
/*  399 */       } else if (message.startsWith("Party M") || message.startsWith("Party Leader")) {
/*  400 */         EntityPlayerSP player = (Minecraft.func_71410_x()).field_71439_g;
/*      */         
/*  402 */         Pattern party_start_pattern = Pattern.compile("^Party Members \\((\\d+)\\)$");
/*  403 */         Pattern leader_pattern = Pattern.compile("^Party Leader: (?:\\[.+?] )?(\\w+) ●$");
/*  404 */         Pattern members_pattern = Pattern.compile(" (?:\\[.+?] )?(\\w+) ●");
/*  405 */         Matcher party_start = party_start_pattern.matcher(message);
/*  406 */         Matcher leader = leader_pattern.matcher(message);
/*  407 */         Matcher members = members_pattern.matcher(message);
/*      */         
/*  409 */         if (party_start.matches() && Integer.parseInt(party_start.group(1)) == 1) {
/*  410 */           player.func_145747_a((IChatComponent)new ChatComponentText(ERROR_COLOUR + "You cannot reparty yourself."));
/*  411 */           RepartyCommand.partyThread.interrupt();
/*  412 */         } else if (leader.matches() && !leader.group(1).equals(player.func_70005_c_())) {
/*  413 */           player.func_145747_a((IChatComponent)new ChatComponentText(ERROR_COLOUR + "You are not party leader."));
/*  414 */           RepartyCommand.partyThread.interrupt();
/*      */         } else {
/*  416 */           while (members.find()) {
/*  417 */             String partyMember = members.group(1);
/*  418 */             if (!partyMember.equals(player.func_70005_c_())) {
/*  419 */               RepartyCommand.party.add(partyMember);
/*  420 */               System.out.println(partyMember);
/*      */             } 
/*      */           } 
/*      */         } 
/*  424 */         event.setCanceled(true);
/*      */         
/*      */         return;
/*      */       } 
/*      */     }
/*  429 */     if (RepartyCommand.disbanding) {
/*  430 */       if (message.contains("-----")) {
/*  431 */         switch (RepartyCommand.Delimiter) {
/*      */           case 0:
/*  433 */             System.out.println("Disband Delimiter Cancelled");
/*  434 */             RepartyCommand.Delimiter++;
/*  435 */             event.setCanceled(true);
/*      */             return;
/*      */           case 1:
/*  438 */             System.out.println("Done disbanding");
/*  439 */             RepartyCommand.disbanding = false;
/*  440 */             RepartyCommand.Delimiter = 0;
/*  441 */             event.setCanceled(true);
/*      */             return;
/*      */         } 
/*  444 */       } else if (message.endsWith("has disbanded the party!")) {
/*  445 */         event.setCanceled(true);
/*      */         
/*      */         return;
/*      */       } 
/*      */     }
/*  450 */     if (RepartyCommand.inviting) {
/*  451 */       if (message.contains("-----")) {
/*  452 */         switch (RepartyCommand.Delimiter) {
/*      */           case 1:
/*  454 */             event.setCanceled(true);
/*  455 */             RepartyCommand.Delimiter = 0;
/*  456 */             System.out.println("Player Invited!");
/*  457 */             RepartyCommand.inviting = false;
/*      */             return;
/*      */           case 0:
/*  460 */             RepartyCommand.Delimiter++;
/*  461 */             event.setCanceled(true); return;
/*      */         } 
/*      */       } else {
/*  464 */         if (message.endsWith(" to the party! They have 60 seconds to accept.")) {
/*  465 */           Pattern invitePattern = Pattern.compile("(?:(?:\\[.+?] )?(?:\\w+) invited )(?:\\[.+?] )?(\\w+)");
/*  466 */           Matcher invitee = invitePattern.matcher(message);
/*  467 */           if (invitee.find()) {
/*  468 */             System.out.println("" + invitee.group(1) + ": " + RepartyCommand.repartyFailList.remove(invitee.group(1)));
/*      */           }
/*  470 */           event.setCanceled(true); return;
/*      */         } 
/*  472 */         if (message.contains("Couldn't find a player") || message.contains("You cannot invite that player")) {
/*  473 */           event.setCanceled(true);
/*      */           return;
/*      */         } 
/*      */       } 
/*      */     }
/*  478 */     if (RepartyCommand.failInviting) {
/*  479 */       if (message.contains("-----")) {
/*  480 */         switch (RepartyCommand.Delimiter) {
/*      */           case 1:
/*  482 */             event.setCanceled(true);
/*  483 */             RepartyCommand.Delimiter = 0;
/*  484 */             System.out.println("Player Invited!");
/*  485 */             RepartyCommand.inviting = false;
/*      */             return;
/*      */           case 0:
/*  488 */             RepartyCommand.Delimiter++;
/*  489 */             event.setCanceled(true); return;
/*      */         } 
/*      */       } else {
/*  492 */         if (message.endsWith(" to the party! They have 60 seconds to accept.")) {
/*  493 */           Pattern invitePattern = Pattern.compile("(?:(?:\\[.+?] )?(?:\\w+) invited )(?:\\[.+?] )?(\\w+)");
/*  494 */           Matcher invitee = invitePattern.matcher(message);
/*  495 */           if (invitee.find()) {
/*  496 */             System.out.println("" + invitee.group(1) + ": " + RepartyCommand.repartyFailList.remove(invitee.group(1)));
/*      */           }
/*  498 */           event.setCanceled(true); return;
/*      */         } 
/*  500 */         if (message.contains("Couldn't find a player") || message.contains("You cannot invite that player")) {
/*  501 */           event.setCanceled(true);
/*      */           return;
/*      */         } 
/*      */       } 
/*      */     }
/*  506 */     if (!Utils.inSkyblock) {
/*      */       return;
/*      */     }
/*  509 */     if (event.type == 2) {
/*  510 */       EntityPlayerSP player = (Minecraft.func_71410_x()).field_71439_g;
/*  511 */       String[] actionBarSections = event.message.func_150260_c().split(" {3,}");
/*      */       
/*  513 */       for (String section : actionBarSections) {
/*  514 */         if (section.contains("+") && section.contains("/") && section.contains("(")) {
/*  515 */           if (!section.contains("Runecrafting") && !section.contains("Carpentry")) {
/*  516 */             if (ToggleCommand.autoSkillTrackerToggled && (System.currentTimeMillis() / 1000L) - timeSinceGained <= 2.0D) {
/*  517 */               if (skillStopwatch.isStarted() && skillStopwatch.isSuspended()) {
/*  518 */                 skillStopwatch.resume();
/*  519 */               } else if (!skillStopwatch.isStarted()) {
/*  520 */                 skillStopwatch.start();
/*      */               } 
/*      */             }
/*  523 */             timeSinceGained = (System.currentTimeMillis() / 1000L);
/*      */             
/*  525 */             int limit = (section.contains("Farming") || section.contains("Enchanting")) ? 60 : 50;
/*  526 */             double currentXP = Double.parseDouble(section.substring(section.indexOf("(") + 1, section.indexOf("/")).replace(",", ""));
/*  527 */             int xpToLevelUp = Integer.parseInt(section.substring(section.indexOf("/") + 1, section.indexOf(")")).replaceAll(",", ""));
/*  528 */             xpLeft = xpToLevelUp - currentXP;
/*  529 */             int previousXP = Utils.getPastXpEarned(xpToLevelUp, limit);
/*  530 */             double totalXP = currentXP + previousXP;
/*      */             
/*  532 */             String skill = section.substring(section.indexOf(" ") + 1, section.lastIndexOf(" "));
/*  533 */             switch (skill) {
/*      */               case "Farming":
/*  535 */                 lastSkill = "Farming";
/*  536 */                 if (farmingXP != 0.0D && 
/*  537 */                   skillStopwatch.isStarted() && !skillStopwatch.isSuspended()) farmingXPGained += totalXP - farmingXP;
/*      */                 
/*  539 */                 farmingXP = totalXP;
/*      */                 break;
/*      */               case "Mining":
/*  542 */                 lastSkill = "Mining";
/*  543 */                 if (miningXP != 0.0D && 
/*  544 */                   skillStopwatch.isStarted() && !skillStopwatch.isSuspended()) miningXPGained += totalXP - miningXP;
/*      */                 
/*  546 */                 miningXP = totalXP;
/*      */                 break;
/*      */               case "Combat":
/*  549 */                 lastSkill = "Combat";
/*  550 */                 if (combatXP != 0.0D && 
/*  551 */                   skillStopwatch.isStarted() && !skillStopwatch.isSuspended()) combatXPGained += totalXP - combatXP;
/*      */                 
/*  553 */                 combatXP = totalXP;
/*      */                 break;
/*      */               case "Foraging":
/*  556 */                 lastSkill = "Foraging";
/*  557 */                 if (foragingXP != 0.0D && 
/*  558 */                   skillStopwatch.isStarted() && !skillStopwatch.isSuspended()) foragingXPGained += totalXP - foragingXP;
/*      */                 
/*  560 */                 foragingXP = totalXP;
/*      */                 break;
/*      */               case "Fishing":
/*  563 */                 lastSkill = "Fishing";
/*  564 */                 if (fishingXP != 0.0D && 
/*  565 */                   skillStopwatch.isStarted() && !skillStopwatch.isSuspended()) fishingXPGained += totalXP - fishingXP;
/*      */                 
/*  567 */                 fishingXP = totalXP;
/*      */                 break;
/*      */               case "Enchanting":
/*  570 */                 lastSkill = "Enchanting";
/*  571 */                 if (enchantingXP != 0.0D && 
/*  572 */                   skillStopwatch.isStarted() && !skillStopwatch.isSuspended()) enchantingXPGained += totalXP - enchantingXP;
/*      */                 
/*  574 */                 enchantingXP = totalXP;
/*      */                 break;
/*      */               case "Alchemy":
/*  577 */                 lastSkill = "Alchemy";
/*  578 */                 if (alchemyXP != 0.0D && 
/*  579 */                   skillStopwatch.isStarted() && !skillStopwatch.isSuspended()) alchemyXPGained += totalXP - alchemyXP;
/*      */                 
/*  581 */                 alchemyXP = totalXP;
/*      */                 break;
/*      */               default:
/*  584 */                 System.err.println("Unknown skill.");
/*      */                 break;
/*      */             } 
/*      */           } 
/*  588 */           if (ToggleCommand.skill50DisplayToggled && !section.contains("Runecrafting")) {
/*  589 */             int limit, totalXp; String xpGained = section.substring(section.indexOf("+"), section.indexOf("(") - 1);
/*  590 */             double currentXp = Double.parseDouble(section.substring(section.indexOf("(") + 1, section.indexOf("/")).replace(",", ""));
/*      */ 
/*      */             
/*  593 */             if (section.contains("Farming") || section.contains("Enchanting")) {
/*  594 */               limit = 60;
/*  595 */               totalXp = 111672425;
/*      */             } else {
/*  597 */               limit = 50;
/*  598 */               totalXp = 55172425;
/*      */             } 
/*  600 */             int previousXp = Utils.getPastXpEarned(Integer.parseInt(section.substring(section.indexOf("/") + 1, section.indexOf(")")).replaceAll(",", "")), limit);
/*  601 */             double percentage = Math.floor((currentXp + previousXp) / totalXp * 10000.0D) / 100.0D;
/*      */             
/*  603 */             NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
/*  604 */             skillTimer = SKILL_TIME;
/*  605 */             showSkill = true;
/*  606 */             skillText = SKILL_50_COLOUR + xpGained + " (" + nf.format(currentXp + previousXp) + "/" + nf.format(totalXp) + ") " + percentage + "%";
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/*      */       return;
/*      */     } 
/*  613 */     if (ToggleCommand.bonzoTimerToggled && Utils.inDungeons && message.contains("Bonzo's Mask") && message.contains("saved your life!")) {
/*  614 */       double usedTime = (System.currentTimeMillis() / 1000L);
/*  615 */       Minecraft mc = Minecraft.func_71410_x();
/*  616 */       EntityPlayerSP player = mc.field_71439_g;
/*  617 */       ItemStack bonzoMask = player.func_82169_q(3);
/*  618 */       if (bonzoMask != null && bonzoMask.func_77973_b() == Items.field_151144_bL) {
/*  619 */         int cooldownSeconds = 0;
/*  620 */         for (String line : Utils.getItemLore(bonzoMask)) {
/*  621 */           String stripped = StringUtils.func_76338_a(line);
/*  622 */           if (stripped.startsWith("Cooldown: "))
/*  623 */             cooldownSeconds = Integer.parseInt(stripped.replaceAll("[^\\d]", "")); 
/*      */         } 
/*  625 */         System.out.println("Parsed Bonzo Mask Cooldown: " + cooldownSeconds);
/*  626 */         if (cooldownSeconds > 0) {
/*  627 */           nextBonzoUse = usedTime + cooldownSeconds;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  632 */     if (ToggleCommand.threeManToggled && Utils.inDungeons && message.contains("[NPC]")) {
/*  633 */       for (String solution : riddleSolutions) {
/*  634 */         if (message.contains(solution)) {
/*  635 */           String npcName = message.substring(message.indexOf("]") + 2, message.indexOf(":"));
/*  636 */           (Minecraft.func_71410_x()).field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(ANSWER_COLOUR + EnumChatFormatting.BOLD + StringUtils.func_76338_a(npcName) + MAIN_COLOUR + " has the blessing."));
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     }
/*  642 */     if (ToggleCommand.necronNotificationsToggled && Utils.inDungeons && message.contains("[BOSS] Necron:")) {
/*  643 */       Minecraft mc = Minecraft.func_71410_x();
/*  644 */       WorldClient worldClient = mc.field_71441_e;
/*  645 */       if (message.contains("You tricked me!") || message.contains("That beam, it hurts! IT HURTS!!")) {
/*  646 */         Utils.createTitle(EnumChatFormatting.RED + "NECRON STUCK!", 2);
/*  647 */       } else if (message.contains("STOP USING MY FACTORY AGAINST ME!") || message.contains("OOF") || message.contains("ANOTHER TRAP!! YOUR TRICKS ARE FUTILE!") || message.contains("SERIOUSLY? AGAIN?!") || message.contains("STOP!!!!!")) {
/*  648 */         List<EntityArmorStand> necronLabels = worldClient.func_175644_a(EntityArmorStand.class, entity -> !entity.func_145818_k_() ? false : (!!entity.func_95999_t().contains("Necron")));
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  653 */         if (necronLabels.size() == 0) {
/*  654 */           Utils.createTitle(EnumChatFormatting.WHITE + "NECRON STUNNED!", 2);
/*      */         } else {
/*  656 */           EntityArmorStand necron = necronLabels.get(0);
/*  657 */           double x = necron.field_70165_t;
/*  658 */           double z = necron.field_70161_v;
/*      */           
/*  660 */           BlockPos blockPos = new BlockPos(x, 168.0D, z);
/*      */           
/*  662 */           IBlockState blockState = worldClient.func_180495_p(blockPos);
/*  663 */           Block block = blockState.func_177230_c();
/*      */           
/*  665 */           if (block != Blocks.field_150406_ce) {
/*  666 */             Utils.createTitle(EnumChatFormatting.WHITE + "NECRON STUNNED!", 2);
/*      */           } else {
/*  668 */             switch (block.func_176222_j((World)worldClient, blockPos)) {
/*      */               case 4:
/*  670 */                 Utils.createTitle(EnumChatFormatting.YELLOW + "YELLOW PILLAR!", 2);
/*      */                 break;
/*      */               case 5:
/*  673 */                 Utils.createTitle(EnumChatFormatting.GREEN + "LIME PILLAR!", 2);
/*      */                 break;
/*      */               case 11:
/*  676 */                 Utils.createTitle(EnumChatFormatting.BLUE + "BLUE PILLAR!", 2);
/*      */                 break;
/*      */               default:
/*  679 */                 Utils.createTitle(EnumChatFormatting.WHITE + "NECRON STUNNED!", 2);
/*      */                 break;
/*      */             } 
/*      */           } 
/*      */         } 
/*  684 */       } else if (message.contains("I'VE HAD ENOUGH! YOU'RE NOT HITTING ME WITH ANY MORE PILLARS!")) {
/*  685 */         Utils.createTitle(EnumChatFormatting.RED + "RED PILLAR!", 2);
/*  686 */       } else if (message.contains("ARGH!")) {
/*  687 */         Utils.createTitle(EnumChatFormatting.RED + "EXPLOSION OVER!", 2);
/*      */       } 
/*      */     } 
/*      */     
/*  691 */     if (message.contains("[BOSS] The Watcher: You have proven yourself. You may pass.")) {
/*  692 */       watcherClearTime = (System.currentTimeMillis() / 1000L);
/*      */     }
/*  694 */     if (message.contains("[BOSS] The Watcher: That will be enough for now.") && 
/*  695 */       ToggleCommand.watcherReadyToggled) Utils.createTitle(EnumChatFormatting.RED + "WATCHER READY", 2);
/*      */     
/*  697 */     if (message.contains("PUZZLE FAIL! ") || message.contains("chose the wrong answer! I shall never forget this moment")) {
/*  698 */       puzzleFails++;
/*      */     }
/*      */     
/*  701 */     if (message.contains(":")) {
/*      */       return;
/*      */     }
/*  704 */     if (!ToggleCommand.sceptreMessages && message.contains("Your Spirit Sceptre hit ")) {
/*  705 */       event.setCanceled(true);
/*      */       
/*      */       return;
/*      */     } 
/*  709 */     if (!ToggleCommand.midasStaffMessages && message.contains("Your Molten Wave hit ")) {
/*  710 */       event.setCanceled(true);
/*      */       
/*      */       return;
/*      */     } 
/*  714 */     if (!ToggleCommand.healMessages && message.contains(" health!") && (message.contains("You healed ") || message.contains(" healed you for "))) {
/*  715 */       event.setCanceled(true);
/*      */       
/*      */       return;
/*      */     } 
/*  719 */     if (!ToggleCommand.cooldownMessages && message.contains("This ability is on cooldown for ")) {
/*  720 */       event.setCanceled(true);
/*      */       
/*      */       return;
/*      */     } 
/*  724 */     if (!ToggleCommand.manaMessages && message.contains("You do not have enough mana to do this!")) {
/*  725 */       event.setCanceled(true);
/*      */       
/*      */       return;
/*      */     } 
/*  729 */     if (!ToggleCommand.implosionMessages && (
/*  730 */       message.contains("Your Implosion hit ") || message.contains("There are blocks in the way"))) {
/*  731 */       event.setCanceled(true);
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/*  736 */     if (ToggleCommand.oruoToggled && Utils.inDungeons) {
/*  737 */       if (message.contains("What SkyBlock year is it?")) {
/*  738 */         double currentTime = (System.currentTimeMillis() / 1000L);
/*      */         
/*  740 */         double diff = Math.floor(currentTime - 1.560276E9D);
/*      */         
/*  742 */         int year = (int)(diff / 446400.0D + 1.0D);
/*  743 */         triviaAnswers = new String[] { "Year " + year };
/*      */       } else {
/*  745 */         for (String question : triviaSolutions.keySet()) {
/*  746 */           if (message.contains(question)) {
/*  747 */             triviaAnswers = triviaSolutions.get(question);
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/*  754 */       if (triviaAnswers != null && (message.contains("ⓐ") || message.contains("ⓑ") || message.contains("ⓒ"))) {
/*  755 */         boolean isSolution = false;
/*  756 */         for (String solution : triviaAnswers) {
/*  757 */           if (message.contains(solution)) {
/*  758 */             isSolution = true;
/*      */             break;
/*      */           } 
/*      */         } 
/*  762 */         if (!isSolution) {
/*  763 */           char letter = message.charAt(5);
/*  764 */           String option = message.substring(6);
/*  765 */           event.message = (IChatComponent)new ChatComponentText("     " + EnumChatFormatting.GOLD + letter + TRIVIA_WRONG_ANSWER_COLOUR + option);
/*      */           
/*      */           return;
/*      */         } 
/*      */       } 
/*      */     } 
/*  771 */     if (ToggleCommand.gpartyToggled && 
/*  772 */       message.contains(" has invited all members of ")) {
/*      */       try {
/*  774 */         SystemTray tray = SystemTray.getSystemTray();
/*  775 */         Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
/*  776 */         TrayIcon trayIcon = new TrayIcon(image, "Guild Party Notifier");
/*  777 */         trayIcon.setImageAutoSize(true);
/*  778 */         trayIcon.setToolTip("Guild Party Notifier");
/*  779 */         tray.add(trayIcon);
/*  780 */         trayIcon.displayMessage("Guild Party", message, TrayIcon.MessageType.INFO);
/*  781 */         tray.remove(trayIcon);
/*  782 */       } catch (Exception ex) {
/*  783 */         ex.printStackTrace();
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*  788 */     if (ToggleCommand.golemAlertToggled && 
/*  789 */       message.contains("The ground begins to shake as an Endstone Protector rises from below!")) {
/*  790 */       Utils.createTitle(EnumChatFormatting.RED + "GOLEM SPAWNING!", 3);
/*      */     }
/*      */ 
/*      */     
/*  794 */     if (message.contains("Yum! You gain +") && message.contains(" for 48 hours!")) {
/*  795 */       cakeTime = (System.currentTimeMillis() / 1000L + 172800L);
/*  796 */       ConfigHandler.writeDoubleConfig("misc", "cakeTime", cakeTime);
/*      */     } 
/*      */     
/*  799 */     boolean wolfRNG = false;
/*  800 */     boolean spiderRNG = false;
/*  801 */     boolean zombieRNG = false;
/*      */     
/*  803 */     if (message.contains("VERY RARE DROP!  (Enchanted Book)") || message.contains("CRAZY RARE DROP!  (Enchanted Book)")) {
/*      */       
/*  805 */       List<String> scoreboard = ScoreboardHandler.getSidebarLines();
/*  806 */       for (String s : scoreboard) {
/*  807 */         String sCleaned = ScoreboardHandler.cleanSB(s);
/*  808 */         if (sCleaned.contains("Sven Packmaster")) {
/*  809 */           LootCommand.wolfBooks++;
/*  810 */           ConfigHandler.writeIntConfig("wolf", "book", LootCommand.wolfBooks); continue;
/*  811 */         }  if (sCleaned.contains("Tarantula Broodfather")) {
/*  812 */           LootCommand.spiderBooks++;
/*  813 */           ConfigHandler.writeIntConfig("spider", "book", LootCommand.spiderBooks); continue;
/*  814 */         }  if (sCleaned.contains("Revenant Horror")) {
/*  815 */           LootCommand.zombieBooks++;
/*  816 */           ConfigHandler.writeIntConfig("zombie", "book", LootCommand.zombieBooks);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  822 */     if (message.contains("Talk to Maddox to claim your Wolf Slayer XP!")) {
/*  823 */       LootCommand.wolfSvens++;
/*  824 */       LootCommand.wolfSvensSession++;
/*  825 */       if (LootCommand.wolfBosses != -1) {
/*  826 */         LootCommand.wolfBosses++;
/*      */       }
/*  828 */       if (LootCommand.wolfBossesSession != -1) {
/*  829 */         LootCommand.wolfBossesSession++;
/*      */       }
/*  831 */       ConfigHandler.writeIntConfig("wolf", "svens", LootCommand.wolfSvens);
/*  832 */       ConfigHandler.writeIntConfig("wolf", "bossRNG", LootCommand.wolfBosses);
/*  833 */     } else if (message.contains("RARE DROP! (Hamster Wheel)")) {
/*  834 */       LootCommand.wolfWheelsDrops++;
/*  835 */       LootCommand.wolfWheelsDropsSession++;
/*  836 */       ConfigHandler.writeIntConfig("wolf", "wheelDrops", LootCommand.wolfWheelsDrops);
/*  837 */     } else if (message.contains("VERY RARE DROP!  (") && message.contains(" Spirit Rune I)")) {
/*  838 */       LootCommand.wolfSpirits++;
/*  839 */       LootCommand.wolfSpiritsSession++;
/*  840 */       ConfigHandler.writeIntConfig("wolf", "spirit", LootCommand.wolfSpirits);
/*  841 */     } else if (message.contains("CRAZY RARE DROP!  (Red Claw Egg)")) {
/*  842 */       wolfRNG = true;
/*  843 */       LootCommand.wolfEggs++;
/*  844 */       LootCommand.wolfEggsSession++;
/*  845 */       ConfigHandler.writeIntConfig("wolf", "egg", LootCommand.wolfEggs);
/*  846 */       if (ToggleCommand.rngesusAlerts) Utils.createTitle(EnumChatFormatting.DARK_RED + "RED CLAW EGG!", 3); 
/*  847 */     } else if (message.contains("CRAZY RARE DROP!  (") && message.contains(" Couture Rune I)")) {
/*  848 */       wolfRNG = true;
/*  849 */       LootCommand.wolfCoutures++;
/*  850 */       LootCommand.wolfCouturesSession++;
/*  851 */       ConfigHandler.writeIntConfig("wolf", "couture", LootCommand.wolfCoutures);
/*  852 */       if (ToggleCommand.rngesusAlerts) Utils.createTitle(EnumChatFormatting.GOLD + "COUTURE RUNE!", 3); 
/*  853 */     } else if (message.contains("CRAZY RARE DROP!  (Grizzly Bait)") || message.contains("CRAZY RARE DROP! (Rename Me)")) {
/*  854 */       wolfRNG = true;
/*  855 */       LootCommand.wolfBaits++;
/*  856 */       LootCommand.wolfBaitsSession++;
/*  857 */       ConfigHandler.writeIntConfig("wolf", "bait", LootCommand.wolfBaits);
/*  858 */       if (ToggleCommand.rngesusAlerts) Utils.createTitle(EnumChatFormatting.AQUA + "GRIZZLY BAIT!", 3); 
/*  859 */     } else if (message.contains("CRAZY RARE DROP!  (Overflux Capacitor)")) {
/*  860 */       wolfRNG = true;
/*  861 */       LootCommand.wolfFluxes++;
/*  862 */       LootCommand.wolfFluxesSession++;
/*  863 */       ConfigHandler.writeIntConfig("wolf", "flux", LootCommand.wolfFluxes);
/*  864 */       if (ToggleCommand.rngesusAlerts) Utils.createTitle(EnumChatFormatting.DARK_PURPLE + "OVERFLUX CAPACITOR!", 5); 
/*  865 */     } else if (message.contains("Talk to Maddox to claim your Spider Slayer XP!")) {
/*  866 */       LootCommand.spiderTarantulas++;
/*  867 */       LootCommand.spiderTarantulasSession++;
/*  868 */       if (LootCommand.spiderBosses != -1) {
/*  869 */         LootCommand.spiderBosses++;
/*      */       }
/*  871 */       if (LootCommand.spiderBossesSession != -1) {
/*  872 */         LootCommand.spiderBossesSession++;
/*      */       }
/*  874 */       ConfigHandler.writeIntConfig("spider", "tarantulas", LootCommand.spiderTarantulas);
/*  875 */       ConfigHandler.writeIntConfig("spider", "bossRNG", LootCommand.spiderBosses);
/*  876 */     } else if (message.contains("RARE DROP! (Toxic Arrow Poison)")) {
/*  877 */       LootCommand.spiderTAPDrops++;
/*  878 */       LootCommand.spiderTAPDropsSession++;
/*  879 */       ConfigHandler.writeIntConfig("spider", "tapDrops", LootCommand.spiderTAPDrops);
/*  880 */     } else if (message.contains("VERY RARE DROP!  (") && message.contains(" Bite Rune I)")) {
/*  881 */       LootCommand.spiderBites++;
/*  882 */       LootCommand.spiderBitesSession++;
/*  883 */       ConfigHandler.writeIntConfig("spider", "bite", LootCommand.spiderBites);
/*  884 */     } else if (message.contains("VERY RARE DROP!  (Spider Catalyst)")) {
/*  885 */       LootCommand.spiderCatalysts++;
/*  886 */       LootCommand.spiderCatalystsSession++;
/*  887 */       ConfigHandler.writeIntConfig("spider", "catalyst", LootCommand.spiderCatalysts);
/*  888 */     } else if (message.contains("CRAZY RARE DROP!  (Fly Swatter)")) {
/*  889 */       spiderRNG = true;
/*  890 */       LootCommand.spiderSwatters++;
/*  891 */       LootCommand.spiderSwattersSession++;
/*  892 */       ConfigHandler.writeIntConfig("spider", "swatter", LootCommand.spiderSwatters);
/*  893 */       if (ToggleCommand.rngesusAlerts) Utils.createTitle(EnumChatFormatting.LIGHT_PURPLE + "FLY SWATTER!", 3); 
/*  894 */     } else if (message.contains("CRAZY RARE DROP!  (Tarantula Talisman")) {
/*  895 */       spiderRNG = true;
/*  896 */       LootCommand.spiderTalismans++;
/*  897 */       LootCommand.spiderTalismansSession++;
/*  898 */       ConfigHandler.writeIntConfig("spider", "talisman", LootCommand.spiderTalismans);
/*  899 */       if (ToggleCommand.rngesusAlerts) Utils.createTitle(EnumChatFormatting.DARK_PURPLE + "TARANTULA TALISMAN!", 3); 
/*  900 */     } else if (message.contains("CRAZY RARE DROP!  (Digested Mosquito)")) {
/*  901 */       spiderRNG = true;
/*  902 */       LootCommand.spiderMosquitos++;
/*  903 */       LootCommand.spiderMosquitosSession++;
/*  904 */       ConfigHandler.writeIntConfig("spider", "mosquito", LootCommand.spiderMosquitos);
/*  905 */       if (ToggleCommand.rngesusAlerts) Utils.createTitle(EnumChatFormatting.GOLD + "DIGESTED MOSQUITO!", 5); 
/*  906 */     } else if (message.contains("Talk to Maddox to claim your Zombie Slayer XP!")) {
/*  907 */       LootCommand.zombieRevs++;
/*  908 */       LootCommand.zombieRevsSession++;
/*  909 */       if (LootCommand.zombieBosses != -1) {
/*  910 */         LootCommand.zombieBosses++;
/*      */       }
/*  912 */       if (LootCommand.zombieBossesSession != 1) {
/*  913 */         LootCommand.zombieBossesSession++;
/*      */       }
/*  915 */       ConfigHandler.writeIntConfig("zombie", "revs", LootCommand.zombieRevs);
/*  916 */       ConfigHandler.writeIntConfig("zombie", "bossRNG", LootCommand.zombieBosses);
/*  917 */     } else if (message.contains("RARE DROP! (Foul Flesh)")) {
/*  918 */       LootCommand.zombieFoulFleshDrops++;
/*  919 */       LootCommand.zombieFoulFleshDropsSession++;
/*  920 */       ConfigHandler.writeIntConfig("zombie", "foulFleshDrops", LootCommand.zombieFoulFleshDrops);
/*  921 */     } else if (message.contains("VERY RARE DROP!  (Revenant Catalyst)")) {
/*  922 */       LootCommand.zombieRevCatas++;
/*  923 */       LootCommand.zombieRevCatasSession++;
/*  924 */       ConfigHandler.writeIntConfig("zombie", "revCatalyst", LootCommand.zombieRevCatas);
/*  925 */     } else if (message.contains("VERY RARE DROP!  (") && message.contains(" Pestilence Rune I)")) {
/*  926 */       LootCommand.zombiePestilences++;
/*  927 */       LootCommand.zombiePestilencesSession++;
/*  928 */       ConfigHandler.writeIntConfig("zombie", "pestilence", LootCommand.zombiePestilences);
/*  929 */     } else if (message.contains("VERY RARE DROP!  (Undead Catalyst)")) {
/*  930 */       LootCommand.zombieUndeadCatas++;
/*  931 */       LootCommand.zombieUndeadCatasSession++;
/*  932 */       ConfigHandler.writeIntConfig("zombie", "undeadCatalyst", LootCommand.zombieUndeadCatas);
/*  933 */     } else if (message.contains("CRAZY RARE DROP!  (Beheaded Horror)")) {
/*  934 */       zombieRNG = true;
/*  935 */       LootCommand.zombieBeheadeds++;
/*  936 */       LootCommand.zombieBeheadedsSession++;
/*  937 */       ConfigHandler.writeIntConfig("zombie", "beheaded", LootCommand.zombieBeheadeds);
/*  938 */       if (ToggleCommand.rngesusAlerts) Utils.createTitle(EnumChatFormatting.DARK_PURPLE + "BEHEADED HORROR!", 3); 
/*  939 */     } else if (message.contains("CRAZY RARE DROP!  (") && message.contains(" Snake Rune I)")) {
/*  940 */       zombieRNG = true;
/*  941 */       LootCommand.zombieSnakes++;
/*  942 */       LootCommand.zombieSnakesSession++;
/*  943 */       ConfigHandler.writeIntConfig("zombie", "snake", LootCommand.zombieSnakes);
/*  944 */       if (ToggleCommand.rngesusAlerts) Utils.createTitle(EnumChatFormatting.DARK_GREEN + "SNAKE RUNE!", 3); 
/*  945 */     } else if (message.contains("CRAZY RARE DROP!  (Scythe Blade)")) {
/*  946 */       zombieRNG = true;
/*  947 */       LootCommand.zombieScythes++;
/*  948 */       LootCommand.zombieScythesSession++;
/*  949 */       ConfigHandler.writeIntConfig("zombie", "scythe", LootCommand.zombieScythes);
/*  950 */       if (ToggleCommand.rngesusAlerts) Utils.createTitle(EnumChatFormatting.GOLD + "SCYTHE BLADE!", 5); 
/*  951 */     } else if (message.contains("GOOD CATCH!")) {
/*  952 */       LootCommand.goodCatches++;
/*  953 */       LootCommand.goodCatchesSession++;
/*  954 */       ConfigHandler.writeIntConfig("fishing", "goodCatch", LootCommand.goodCatches);
/*  955 */     } else if (message.contains("GREAT CATCH!")) {
/*  956 */       LootCommand.greatCatches++;
/*  957 */       LootCommand.greatCatchesSession++;
/*  958 */       ConfigHandler.writeIntConfig("fishing", "greatCatch", LootCommand.greatCatches);
/*  959 */     } else if (message.contains("A Squid appeared")) {
/*  960 */       LootCommand.squids++;
/*  961 */       LootCommand.squidsSession++;
/*  962 */       ConfigHandler.writeIntConfig("fishing", "squid", LootCommand.squids);
/*  963 */       increaseSeaCreatures();
/*  964 */     } else if (message.contains("You caught a Sea Walker")) {
/*  965 */       LootCommand.seaWalkers++;
/*  966 */       LootCommand.seaWalkersSession++;
/*  967 */       ConfigHandler.writeIntConfig("fishing", "seaWalker", LootCommand.seaWalkers);
/*  968 */       increaseSeaCreatures();
/*  969 */     } else if (message.contains("Pitch darkness reveals a Night Squid")) {
/*  970 */       LootCommand.nightSquids++;
/*  971 */       LootCommand.nightSquidsSession++;
/*  972 */       ConfigHandler.writeIntConfig("fishing", "nightSquid", LootCommand.nightSquids);
/*  973 */       increaseSeaCreatures();
/*  974 */     } else if (message.contains("You stumbled upon a Sea Guardian")) {
/*  975 */       LootCommand.seaGuardians++;
/*  976 */       LootCommand.seaGuardiansSession++;
/*  977 */       ConfigHandler.writeIntConfig("fishing", "seaGuardian", LootCommand.seaGuardians);
/*  978 */       increaseSeaCreatures();
/*  979 */     } else if (message.contains("It looks like you've disrupted the Sea Witch's brewing session. Watch out, she's furious")) {
/*  980 */       LootCommand.seaWitches++;
/*  981 */       LootCommand.seaWitchesSession++;
/*  982 */       ConfigHandler.writeIntConfig("fishing", "seaWitch", LootCommand.seaWitches);
/*  983 */       increaseSeaCreatures();
/*  984 */     } else if (message.contains("You reeled in a Sea Archer")) {
/*  985 */       LootCommand.seaArchers++;
/*  986 */       LootCommand.seaArchersSession++;
/*  987 */       ConfigHandler.writeIntConfig("fishing", "seaArcher", LootCommand.seaArchers);
/*  988 */       increaseSeaCreatures();
/*  989 */     } else if (message.contains("The Monster of the Deep has emerged")) {
/*  990 */       LootCommand.monsterOfTheDeeps++;
/*  991 */       LootCommand.monsterOfTheDeepsSession++;
/*  992 */       ConfigHandler.writeIntConfig("fishing", "monsterOfDeep", LootCommand.monsterOfTheDeeps);
/*  993 */       increaseSeaCreatures();
/*  994 */     } else if (message.contains("Huh? A Catfish")) {
/*  995 */       LootCommand.catfishes++;
/*  996 */       LootCommand.catfishesSession++;
/*  997 */       ConfigHandler.writeIntConfig("fishing", "catfish", LootCommand.catfishes);
/*  998 */       increaseSeaCreatures();
/*  999 */     } else if (message.contains("Is this even a fish? It's the Carrot King")) {
/* 1000 */       LootCommand.carrotKings++;
/* 1001 */       LootCommand.carrotKingsSession++;
/* 1002 */       ConfigHandler.writeIntConfig("fishing", "carrotKing", LootCommand.carrotKings);
/* 1003 */       increaseSeaCreatures();
/* 1004 */     } else if (message.contains("Gross! A Sea Leech")) {
/* 1005 */       LootCommand.seaLeeches++;
/* 1006 */       LootCommand.seaLeechesSession++;
/* 1007 */       ConfigHandler.writeIntConfig("fishing", "seaLeech", LootCommand.seaLeeches);
/* 1008 */       increaseSeaCreatures();
/* 1009 */     } else if (message.contains("You've discovered a Guardian Defender of the sea")) {
/* 1010 */       LootCommand.guardianDefenders++;
/* 1011 */       LootCommand.guardianDefendersSession++;
/* 1012 */       ConfigHandler.writeIntConfig("fishing", "guardianDefender", LootCommand.guardianDefenders);
/* 1013 */       increaseSeaCreatures();
/* 1014 */     } else if (message.contains("You have awoken the Deep Sea Protector, prepare for a battle")) {
/* 1015 */       LootCommand.deepSeaProtectors++;
/* 1016 */       LootCommand.deepSeaProtectorsSession++;
/* 1017 */       ConfigHandler.writeIntConfig("fishing", "deepSeaProtector", LootCommand.deepSeaProtectors);
/* 1018 */       increaseSeaCreatures();
/* 1019 */     } else if (message.contains("The Water Hydra has come to test your strength")) {
/* 1020 */       LootCommand.hydras++;
/* 1021 */       LootCommand.hydrasSession++;
/* 1022 */       ConfigHandler.writeIntConfig("fishing", "hydra", LootCommand.hydras);
/* 1023 */       increaseSeaCreatures();
/* 1024 */     } else if (message.contains("The Sea Emperor arises from the depths")) {
/* 1025 */       increaseSeaCreatures();
/*      */       
/* 1027 */       LootCommand.seaEmperors++;
/* 1028 */       LootCommand.empTime = (System.currentTimeMillis() / 1000L);
/* 1029 */       LootCommand.empSCs = 0;
/* 1030 */       LootCommand.seaEmperorsSession++;
/* 1031 */       LootCommand.empTimeSession = (System.currentTimeMillis() / 1000L);
/* 1032 */       LootCommand.empSCsSession = 0;
/* 1033 */       ConfigHandler.writeIntConfig("fishing", "seaEmperor", LootCommand.seaEmperors);
/* 1034 */       ConfigHandler.writeDoubleConfig("fishing", "empTime", LootCommand.empTime);
/* 1035 */       ConfigHandler.writeIntConfig("fishing", "empSC", LootCommand.empSCs);
/* 1036 */     } else if (message.contains("Frozen Steve fell into the pond long ago")) {
/* 1037 */       LootCommand.frozenSteves++;
/* 1038 */       LootCommand.frozenStevesSession++;
/* 1039 */       ConfigHandler.writeIntConfig("fishing", "frozenSteve", LootCommand.frozenSteves);
/* 1040 */       increaseSeaCreatures();
/* 1041 */     } else if (message.contains("It's a snowman! He looks harmless")) {
/* 1042 */       LootCommand.frostyTheSnowmans++;
/* 1043 */       LootCommand.frostyTheSnowmansSession++;
/* 1044 */       ConfigHandler.writeIntConfig("fishing", "snowman", LootCommand.frostyTheSnowmans);
/* 1045 */       increaseSeaCreatures();
/* 1046 */     } else if (message.contains("stole Jerry's Gifts...get them back")) {
/* 1047 */       LootCommand.grinches++;
/* 1048 */       LootCommand.grinchesSession++;
/* 1049 */       ConfigHandler.writeIntConfig("fishing", "grinch", LootCommand.grinches);
/* 1050 */       increaseSeaCreatures();
/* 1051 */     } else if (message.contains("What is this creature")) {
/* 1052 */       LootCommand.yetis++;
/* 1053 */       LootCommand.yetiTime = (System.currentTimeMillis() / 1000L);
/* 1054 */       LootCommand.yetiSCs = 0;
/* 1055 */       LootCommand.yetisSession++;
/* 1056 */       LootCommand.yetiTimeSession = (System.currentTimeMillis() / 1000L);
/* 1057 */       LootCommand.yetiSCsSession = 0;
/* 1058 */       ConfigHandler.writeIntConfig("fishing", "yeti", LootCommand.yetis);
/* 1059 */       ConfigHandler.writeDoubleConfig("fishing", "yetiTime", LootCommand.yetiTime);
/* 1060 */       ConfigHandler.writeIntConfig("fishing", "yetiSC", LootCommand.yetiSCs);
/* 1061 */       increaseSeaCreatures();
/* 1062 */     } else if (message.contains("A tiny fin emerges from the water, you've caught a Nurse Shark")) {
/* 1063 */       LootCommand.nurseSharks++;
/* 1064 */       LootCommand.nurseSharksSession++;
/* 1065 */       ConfigHandler.writeIntConfig("fishing", "nurseShark", LootCommand.nurseSharks);
/* 1066 */       increaseSeaCreatures();
/* 1067 */     } else if (message.contains("You spot a fin as blue as the water it came from, it's a Blue Shark")) {
/* 1068 */       LootCommand.blueSharks++;
/* 1069 */       LootCommand.blueSharksSession++;
/* 1070 */       ConfigHandler.writeIntConfig("fishing", "blueShark", LootCommand.blueSharks);
/* 1071 */       increaseSeaCreatures();
/* 1072 */     } else if (message.contains("A striped beast bounds from the depths, the wild Tiger Shark")) {
/* 1073 */       LootCommand.tigerSharks++;
/* 1074 */       LootCommand.tigerSharksSession++;
/* 1075 */       ConfigHandler.writeIntConfig("fishing", "tigerShark", LootCommand.tigerSharks);
/* 1076 */       increaseSeaCreatures();
/* 1077 */     } else if (message.contains("Hide no longer, a Great White Shark has tracked your scent and thirsts for your blood")) {
/* 1078 */       LootCommand.greatWhiteSharks++;
/* 1079 */       LootCommand.greatWhiteSharksSession++;
/* 1080 */       ConfigHandler.writeIntConfig("fishing", "greatWhiteShark", LootCommand.greatWhiteSharks);
/* 1081 */       increaseSeaCreatures();
/* 1082 */     } else if (message.contains("Phew! It's only a Scarecrow")) {
/* 1083 */       LootCommand.scarecrows++;
/* 1084 */       LootCommand.scarecrowsSession++;
/* 1085 */       ConfigHandler.writeIntConfig("fishing", "scarecrow", LootCommand.scarecrows);
/* 1086 */       increaseSeaCreatures();
/* 1087 */     } else if (message.contains("You hear trotting from beneath the waves, you caught a Nightmare")) {
/* 1088 */       LootCommand.nightmares++;
/* 1089 */       LootCommand.nightmaresSession++;
/* 1090 */       ConfigHandler.writeIntConfig("fishing", "nightmare", LootCommand.nightmares);
/* 1091 */       increaseSeaCreatures();
/* 1092 */     } else if (message.contains("It must be a full moon, a Werewolf appears")) {
/* 1093 */       LootCommand.werewolfs++;
/* 1094 */       LootCommand.werewolfsSession++;
/* 1095 */       ConfigHandler.writeIntConfig("fishing", "werewolf", LootCommand.werewolfs);
/* 1096 */       increaseSeaCreatures();
/* 1097 */     } else if (message.contains("The spirit of a long lost Phantom Fisher has come to haunt you")) {
/* 1098 */       LootCommand.phantomFishers++;
/* 1099 */       LootCommand.phantomFishersSession++;
/* 1100 */       ConfigHandler.writeIntConfig("fishing", "phantomFisher", LootCommand.phantomFishers);
/* 1101 */       increaseSeaCreatures();
/* 1102 */     } else if (message.contains("This can't be! The manifestation of death himself")) {
/* 1103 */       LootCommand.grimReapers++;
/* 1104 */       LootCommand.grimReapersSession++;
/* 1105 */       ConfigHandler.writeIntConfig("fishing", "grimReaper", LootCommand.grimReapers);
/* 1106 */       increaseSeaCreatures();
/* 1107 */     } else if (message.contains("Dungeon starts in 1 second.")) {
/* 1108 */       dungeonStartTime = (System.currentTimeMillis() / 1000L + 1L);
/* 1109 */       bloodOpenTime = dungeonStartTime;
/* 1110 */       watcherClearTime = dungeonStartTime;
/* 1111 */       bossClearTime = dungeonStartTime;
/* 1112 */       witherDoors = 0;
/* 1113 */       dungeonDeaths = 0;
/* 1114 */       puzzleFails = 0;
/* 1115 */     } else if (message.contains("The BLOOD DOOR has been opened!")) {
/* 1116 */       bloodOpenTime = (System.currentTimeMillis() / 1000L);
/* 1117 */     } else if (message.contains(" opened a WITHER door!")) {
/* 1118 */       witherDoors++;
/* 1119 */     } else if (message.contains(" and became a ghost.")) {
/* 1120 */       dungeonDeaths++;
/* 1121 */     } else if (message.contains(" Defeated ") && message.contains(" in ")) {
/* 1122 */       bossClearTime = (System.currentTimeMillis() / 1000L);
/* 1123 */     } else if (message.contains("EXTRA STATS ")) {
/* 1124 */       List<String> scoreboard = ScoreboardHandler.getSidebarLines();
/* 1125 */       int timeToAdd = 0;
/* 1126 */       for (String s : scoreboard) {
/* 1127 */         String sCleaned = ScoreboardHandler.cleanSB(s);
/* 1128 */         if (sCleaned.contains("The Catacombs (")) {
/*      */           
/* 1130 */           if (sCleaned.contains("F1")) {
/* 1131 */             LootCommand.f1TimeSpent = Math.floor(LootCommand.f1TimeSpent + timeToAdd);
/* 1132 */             LootCommand.f1TimeSpentSession = Math.floor(LootCommand.f1TimeSpentSession + timeToAdd);
/* 1133 */             ConfigHandler.writeDoubleConfig("catacombs", "floorOneTime", LootCommand.f1TimeSpent); continue;
/* 1134 */           }  if (sCleaned.contains("F2")) {
/* 1135 */             LootCommand.f2TimeSpent = Math.floor(LootCommand.f2TimeSpent + timeToAdd);
/* 1136 */             LootCommand.f2TimeSpentSession = Math.floor(LootCommand.f2TimeSpentSession + timeToAdd);
/* 1137 */             ConfigHandler.writeDoubleConfig("catacombs", "floorTwoTime", LootCommand.f2TimeSpent); continue;
/* 1138 */           }  if (sCleaned.contains("F3")) {
/* 1139 */             LootCommand.f3TimeSpent = Math.floor(LootCommand.f3TimeSpent + timeToAdd);
/* 1140 */             LootCommand.f3TimeSpentSession = Math.floor(LootCommand.f3TimeSpentSession + timeToAdd);
/* 1141 */             ConfigHandler.writeDoubleConfig("catacombs", "floorThreeTime", LootCommand.f3TimeSpent); continue;
/* 1142 */           }  if (sCleaned.contains("F4")) {
/* 1143 */             LootCommand.f4TimeSpent = Math.floor(LootCommand.f4TimeSpent + timeToAdd);
/* 1144 */             LootCommand.f4TimeSpentSession = Math.floor(LootCommand.f4TimeSpentSession + timeToAdd);
/* 1145 */             ConfigHandler.writeDoubleConfig("catacombs", "floorFourTime", LootCommand.f4TimeSpent); continue;
/* 1146 */           }  if (sCleaned.contains("F5")) {
/* 1147 */             LootCommand.f5TimeSpent = Math.floor(LootCommand.f5TimeSpent + timeToAdd);
/* 1148 */             LootCommand.f5TimeSpentSession = Math.floor(LootCommand.f5TimeSpentSession + timeToAdd);
/* 1149 */             ConfigHandler.writeDoubleConfig("catacombs", "floorFiveTime", LootCommand.f5TimeSpent); continue;
/* 1150 */           }  if (sCleaned.contains("F6")) {
/* 1151 */             LootCommand.f6TimeSpent = Math.floor(LootCommand.f6TimeSpent + timeToAdd);
/* 1152 */             LootCommand.f6TimeSpentSession = Math.floor(LootCommand.f6TimeSpentSession + timeToAdd);
/* 1153 */             ConfigHandler.writeDoubleConfig("catacombs", "floorSixTime", LootCommand.f6TimeSpent); continue;
/* 1154 */           }  if (sCleaned.contains("F7")) {
/* 1155 */             LootCommand.f7TimeSpent = Math.floor(LootCommand.f7TimeSpent + timeToAdd);
/* 1156 */             LootCommand.f7TimeSpentSession = Math.floor(LootCommand.f7TimeSpentSession + timeToAdd);
/* 1157 */             ConfigHandler.writeDoubleConfig("catacombs", "floorSevenTime", LootCommand.f7TimeSpent);
/*      */           }  continue;
/* 1159 */         }  if (sCleaned.contains("Time Elapsed:")) {
/*      */           
/* 1161 */           String time = sCleaned.substring(sCleaned.indexOf(":") + 2);
/* 1162 */           time = time.replaceAll("\\s", "");
/* 1163 */           int minutes = Integer.parseInt(time.substring(0, time.indexOf("m")));
/* 1164 */           int seconds = Integer.parseInt(time.substring(time.indexOf("m") + 1, time.indexOf("s")));
/* 1165 */           timeToAdd = minutes * 60 + seconds;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1170 */     if (wolfRNG) {
/* 1171 */       LootCommand.wolfTime = (System.currentTimeMillis() / 1000L);
/* 1172 */       LootCommand.wolfBosses = 0;
/* 1173 */       LootCommand.wolfTimeSession = (System.currentTimeMillis() / 1000L);
/* 1174 */       LootCommand.wolfBossesSession = 0;
/* 1175 */       ConfigHandler.writeDoubleConfig("wolf", "timeRNG", LootCommand.wolfTime);
/* 1176 */       ConfigHandler.writeIntConfig("wolf", "bossRNG", 0);
/*      */     } 
/* 1178 */     if (spiderRNG) {
/* 1179 */       LootCommand.spiderTime = (System.currentTimeMillis() / 1000L);
/* 1180 */       LootCommand.spiderBosses = 0;
/* 1181 */       LootCommand.spiderTimeSession = (System.currentTimeMillis() / 1000L);
/* 1182 */       LootCommand.spiderBossesSession = 0;
/* 1183 */       ConfigHandler.writeDoubleConfig("spider", "timeRNG", LootCommand.spiderTime);
/* 1184 */       ConfigHandler.writeIntConfig("spider", "bossRNG", 0);
/*      */     } 
/* 1186 */     if (zombieRNG) {
/* 1187 */       LootCommand.zombieTime = (System.currentTimeMillis() / 1000L);
/* 1188 */       LootCommand.zombieBosses = 0;
/* 1189 */       LootCommand.zombieTimeSession = (System.currentTimeMillis() / 1000L);
/* 1190 */       LootCommand.zombieBossesSession = 0;
/* 1191 */       ConfigHandler.writeDoubleConfig("zombie", "timeRNG", LootCommand.zombieTime);
/* 1192 */       ConfigHandler.writeIntConfig("zombie", "bossRNG", 0);
/*      */     } 
/*      */ 
/*      */     
/* 1196 */     if (message.contains("You dug out")) {
/* 1197 */       if (message.contains(" coins!")) {
/* 1198 */         double coinsEarned = Double.parseDouble(message.replaceAll("[^\\d]", ""));
/* 1199 */         LootCommand.mythCoins += coinsEarned;
/* 1200 */         LootCommand.mythCoinsSession += coinsEarned;
/* 1201 */         ConfigHandler.writeDoubleConfig("mythological", "coins", LootCommand.mythCoins);
/* 1202 */       } else if (message.contains("a Griffin Feather!")) {
/* 1203 */         LootCommand.griffinFeathers++;
/* 1204 */         LootCommand.griffinFeathersSession++;
/* 1205 */         ConfigHandler.writeIntConfig("mythological", "griffinFeather", LootCommand.griffinFeathers);
/* 1206 */       } else if (message.contains("a Crown of Greed!")) {
/* 1207 */         LootCommand.crownOfGreeds++;
/* 1208 */         LootCommand.crownOfGreedsSession++;
/* 1209 */         ConfigHandler.writeIntConfig("mythological", "crownOfGreed", LootCommand.crownOfGreeds);
/* 1210 */       } else if (message.contains("a Washed-up Souvenir!")) {
/* 1211 */         LootCommand.washedUpSouvenirs++;
/* 1212 */         LootCommand.washedUpSouvenirsSession++;
/* 1213 */         ConfigHandler.writeIntConfig("mythological", "washedUpSouvenir", LootCommand.washedUpSouvenirs);
/* 1214 */       } else if (message.contains("a Minos Hunter!")) {
/* 1215 */         LootCommand.minosHunters++;
/* 1216 */         LootCommand.minosHuntersSession++;
/* 1217 */         ConfigHandler.writeIntConfig("mythological", "minosHunter", LootCommand.minosHunters);
/* 1218 */       } else if (message.contains("Siamese Lynxes!")) {
/* 1219 */         LootCommand.siameseLynxes++;
/* 1220 */         LootCommand.siameseLynxesSession++;
/* 1221 */         ConfigHandler.writeIntConfig("mythological", "siameseLynx", LootCommand.siameseLynxes);
/* 1222 */       } else if (message.contains("a Minotaur!")) {
/* 1223 */         LootCommand.minotaurs++;
/* 1224 */         LootCommand.minotaursSession++;
/* 1225 */         ConfigHandler.writeIntConfig("mythological", "minotaur", LootCommand.minotaurs);
/* 1226 */       } else if (message.contains("a Gaia Construct!")) {
/* 1227 */         LootCommand.gaiaConstructs++;
/* 1228 */         LootCommand.gaiaConstructsSession++;
/* 1229 */         ConfigHandler.writeIntConfig("mythological", "gaiaConstruct", LootCommand.gaiaConstructs);
/* 1230 */       } else if (message.contains("a Minos Champion!")) {
/* 1231 */         LootCommand.minosChampions++;
/* 1232 */         LootCommand.minosChampionsSession++;
/* 1233 */         ConfigHandler.writeIntConfig("mythological", "minosChampion", LootCommand.minosChampions);
/* 1234 */       } else if (message.contains("a Minos Inquisitor!")) {
/* 1235 */         LootCommand.minosInquisitors++;
/* 1236 */         LootCommand.minosInquisitorsSession++;
/* 1237 */         ConfigHandler.writeIntConfig("mythological", "minosInquisitor", LootCommand.minosInquisitors);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 1242 */     if (message.contains("    ")) {
/* 1243 */       if (message.contains("Recombobulator 3000")) {
/* 1244 */         LootCommand.recombobulators++;
/* 1245 */         LootCommand.recombobulatorsSession++;
/* 1246 */         ConfigHandler.writeIntConfig("catacombs", "recombobulator", LootCommand.recombobulators);
/* 1247 */       } else if (message.contains("Fuming Potato Book")) {
/* 1248 */         LootCommand.fumingPotatoBooks++;
/* 1249 */         LootCommand.fumingPotatoBooksSession++;
/* 1250 */         ConfigHandler.writeIntConfig("catacombs", "fumingBooks", LootCommand.fumingPotatoBooks);
/* 1251 */       } else if (message.contains("Bonzo's Staff")) {
/* 1252 */         LootCommand.bonzoStaffs++;
/* 1253 */         LootCommand.bonzoStaffsSession++;
/* 1254 */         ConfigHandler.writeIntConfig("catacombs", "bonzoStaff", LootCommand.bonzoStaffs);
/* 1255 */       } else if (message.contains("Scarf's Studies")) {
/* 1256 */         LootCommand.scarfStudies++;
/* 1257 */         LootCommand.scarfStudiesSession++;
/* 1258 */         ConfigHandler.writeIntConfig("catacombs", "scarfStudies", LootCommand.scarfStudies);
/* 1259 */       } else if (message.contains("Adaptive Helmet")) {
/* 1260 */         LootCommand.adaptiveHelms++;
/* 1261 */         LootCommand.adaptiveHelmsSession++;
/* 1262 */         ConfigHandler.writeIntConfig("catacombs", "adaptiveHelm", LootCommand.adaptiveHelms);
/* 1263 */       } else if (message.contains("Adaptive Chestplate")) {
/* 1264 */         LootCommand.adaptiveChests++;
/* 1265 */         LootCommand.adaptiveChestsSession++;
/* 1266 */         ConfigHandler.writeIntConfig("catacombs", "adaptiveChest", LootCommand.adaptiveChests);
/* 1267 */       } else if (message.contains("Adaptive Leggings")) {
/* 1268 */         LootCommand.adaptiveLegs++;
/* 1269 */         LootCommand.adaptiveLegsSession++;
/* 1270 */         ConfigHandler.writeIntConfig("catacombs", "adaptiveLegging", LootCommand.adaptiveLegs);
/* 1271 */       } else if (message.contains("Adaptive Boots")) {
/* 1272 */         LootCommand.adaptiveBoots++;
/* 1273 */         LootCommand.adaptiveBootsSession++;
/* 1274 */         ConfigHandler.writeIntConfig("catacombs", "adaptiveBoot", LootCommand.adaptiveBoots);
/* 1275 */       } else if (message.contains("Adaptive Blade")) {
/* 1276 */         LootCommand.adaptiveSwords++;
/* 1277 */         LootCommand.adaptiveSwordsSession++;
/* 1278 */         ConfigHandler.writeIntConfig("catacombs", "adaptiveSword", LootCommand.adaptiveSwords);
/* 1279 */       } else if (message.contains("Spirit Wing")) {
/* 1280 */         LootCommand.spiritWings++;
/* 1281 */         LootCommand.spiritWingsSession++;
/* 1282 */         ConfigHandler.writeIntConfig("catacombs", "spiritWing", LootCommand.spiritWings);
/* 1283 */       } else if (message.contains("Spirit Bone")) {
/* 1284 */         LootCommand.spiritBones++;
/* 1285 */         LootCommand.spiritBonesSession++;
/* 1286 */         ConfigHandler.writeIntConfig("catacombs", "spiritBone", LootCommand.spiritBones);
/* 1287 */       } else if (message.contains("Spirit Boots")) {
/* 1288 */         LootCommand.spiritBoots++;
/* 1289 */         LootCommand.spiritBootsSession++;
/* 1290 */         ConfigHandler.writeIntConfig("catacombs", "spiritBoot", LootCommand.spiritBoots);
/* 1291 */       } else if (message.contains("[Lvl 1] Spirit")) {
/* 1292 */         String formattedMessage = event.message.func_150254_d();
/*      */         
/* 1294 */         if (formattedMessage.contains("5Spirit")) {
/* 1295 */           LootCommand.epicSpiritPets++;
/* 1296 */           LootCommand.epicSpiritPetsSession++;
/* 1297 */           ConfigHandler.writeIntConfig("catacombs", "spiritPetEpic", LootCommand.epicSpiritPets);
/* 1298 */         } else if (formattedMessage.contains("6Spirit")) {
/* 1299 */           LootCommand.legSpiritPets++;
/* 1300 */           LootCommand.legSpiritPetsSession++;
/* 1301 */           ConfigHandler.writeIntConfig("catacombs", "spiritPetLeg", LootCommand.legSpiritPets);
/*      */         } 
/* 1303 */       } else if (message.contains("Spirit Sword")) {
/* 1304 */         LootCommand.spiritSwords++;
/* 1305 */         LootCommand.spiritSwordsSession++;
/* 1306 */         ConfigHandler.writeIntConfig("catacombs", "spiritSword", LootCommand.spiritSwords);
/* 1307 */       } else if (message.contains("Spirit Bow")) {
/* 1308 */         LootCommand.spiritBows++;
/* 1309 */         LootCommand.spiritBowsSession++;
/* 1310 */         ConfigHandler.writeIntConfig("catacombs", "spiritBow", LootCommand.spiritBows);
/* 1311 */       } else if (message.contains("Warped Stone")) {
/* 1312 */         LootCommand.warpedStones++;
/* 1313 */         LootCommand.warpedStonesSession++;
/* 1314 */         ConfigHandler.writeIntConfig("catacombs", "warpedStone", LootCommand.warpedStones);
/* 1315 */       } else if (message.contains("Shadow Assassin Helmet")) {
/* 1316 */         LootCommand.shadowAssHelms++;
/* 1317 */         LootCommand.shadowAssHelmsSession++;
/* 1318 */         ConfigHandler.writeIntConfig("catacombs", "shadowAssassinHelm", LootCommand.shadowAssHelms);
/* 1319 */       } else if (message.contains("Shadow Assassin Chestplate")) {
/* 1320 */         LootCommand.shadowAssChests++;
/* 1321 */         LootCommand.shadowAssChestsSession++;
/* 1322 */         ConfigHandler.writeIntConfig("catacombs", "shadowAssassinChest", LootCommand.shadowAssChests);
/* 1323 */       } else if (message.contains("Shadow Assassin Leggings")) {
/* 1324 */         LootCommand.shadowAssLegs++;
/* 1325 */         LootCommand.shadowAssLegsSession++;
/* 1326 */         ConfigHandler.writeIntConfig("catacombs", "shadowAssassinLegging", LootCommand.shadowAssLegs);
/* 1327 */       } else if (message.contains("Shadow Assassin Boots")) {
/* 1328 */         LootCommand.shadowAssBoots++;
/* 1329 */         LootCommand.shadowAssBootsSession++;
/* 1330 */         ConfigHandler.writeIntConfig("catacombs", "shadowAssassinBoot", LootCommand.shadowAssBoots);
/* 1331 */       } else if (message.contains("Livid Dagger")) {
/* 1332 */         LootCommand.lividDaggers++;
/* 1333 */         LootCommand.lividDaggersSession++;
/* 1334 */         ConfigHandler.writeIntConfig("catacombs", "lividDagger", LootCommand.lividDaggers);
/* 1335 */       } else if (message.contains("Shadow Fury")) {
/* 1336 */         LootCommand.shadowFurys++;
/* 1337 */         LootCommand.shadowFurysSession++;
/* 1338 */         ConfigHandler.writeIntConfig("catacombs", "shadowFury", LootCommand.shadowFurys);
/* 1339 */       } else if (message.contains("Ancient Rose")) {
/* 1340 */         LootCommand.ancientRoses++;
/* 1341 */         LootCommand.ancientRosesSession++;
/* 1342 */         ConfigHandler.writeIntConfig("catacombs", "ancientRose", LootCommand.ancientRoses);
/* 1343 */       } else if (message.contains("Precursor Eye")) {
/* 1344 */         LootCommand.precursorEyes++;
/* 1345 */         LootCommand.precursorEyesSession++;
/* 1346 */         ConfigHandler.writeIntConfig("catacombs", "precursorEye", LootCommand.precursorEyes);
/* 1347 */       } else if (message.contains("Giant's Sword")) {
/* 1348 */         LootCommand.giantsSwords++;
/* 1349 */         LootCommand.giantsSwordsSession++;
/* 1350 */         ConfigHandler.writeIntConfig("catacombs", "giantsSword", LootCommand.giantsSwords);
/* 1351 */       } else if (message.contains("Necromancer Lord Helmet")) {
/* 1352 */         LootCommand.necroLordHelms++;
/* 1353 */         LootCommand.necroLordHelmsSession++;
/* 1354 */         ConfigHandler.writeIntConfig("catacombs", "necroLordHelm", LootCommand.necroLordHelms);
/* 1355 */       } else if (message.contains("Necromancer Lord Chestplate")) {
/* 1356 */         LootCommand.necroLordChests++;
/* 1357 */         LootCommand.necroLordChestsSession++;
/* 1358 */         ConfigHandler.writeIntConfig("catacombs", "necroLordChest", LootCommand.necroLordChests);
/* 1359 */       } else if (message.contains("Necromancer Lord Leggings")) {
/* 1360 */         LootCommand.necroLordLegs++;
/* 1361 */         LootCommand.necroLordLegsSession++;
/* 1362 */         ConfigHandler.writeIntConfig("catacombs", "necroLordLegging", LootCommand.necroLordLegs);
/* 1363 */       } else if (message.contains("Necromancer Lord Boots")) {
/* 1364 */         LootCommand.necroLordBoots++;
/* 1365 */         LootCommand.necroLordBootsSession++;
/* 1366 */         ConfigHandler.writeIntConfig("catacombs", "necroLordBoot", LootCommand.necroLordBoots);
/* 1367 */       } else if (message.contains("Necromancer Sword")) {
/* 1368 */         LootCommand.necroSwords++;
/* 1369 */         LootCommand.necroSwordsSession++;
/* 1370 */         ConfigHandler.writeIntConfig("catacombs", "necroSword", LootCommand.necroSwords);
/* 1371 */       } else if (message.contains("Wither Blood")) {
/* 1372 */         LootCommand.witherBloods++;
/* 1373 */         LootCommand.witherBloodsSession++;
/* 1374 */         ConfigHandler.writeIntConfig("catacombs", "witherBlood", LootCommand.witherBloods);
/* 1375 */       } else if (message.contains("Wither Cloak")) {
/* 1376 */         LootCommand.witherCloaks++;
/* 1377 */         LootCommand.witherCloaksSession++;
/* 1378 */         ConfigHandler.writeIntConfig("catacombs", "witherCloak", LootCommand.witherCloaks);
/* 1379 */       } else if (message.contains("Implosion")) {
/* 1380 */         LootCommand.implosions++;
/* 1381 */         LootCommand.implosionsSession++;
/* 1382 */         ConfigHandler.writeIntConfig("catacombs", "implosion", LootCommand.implosions);
/* 1383 */       } else if (message.contains("Wither Shield")) {
/* 1384 */         LootCommand.witherShields++;
/* 1385 */         LootCommand.witherShieldsSession++;
/* 1386 */         ConfigHandler.writeIntConfig("catacombs", "witherShield", LootCommand.witherShields);
/* 1387 */       } else if (message.contains("Shadow Warp")) {
/* 1388 */         LootCommand.shadowWarps++;
/* 1389 */         LootCommand.shadowWarpsSession++;
/* 1390 */         ConfigHandler.writeIntConfig("catacombs", "shadowWarp", LootCommand.shadowWarps);
/* 1391 */       } else if (message.contains("Necron's Handle")) {
/* 1392 */         LootCommand.necronsHandles++;
/* 1393 */         LootCommand.necronsHandlesSession++;
/* 1394 */         ConfigHandler.writeIntConfig("catacombs", "necronsHandle", LootCommand.necronsHandles);
/* 1395 */       } else if (message.contains("Auto Recombobulator")) {
/* 1396 */         LootCommand.autoRecombs++;
/* 1397 */         LootCommand.autoRecombsSession++;
/* 1398 */         ConfigHandler.writeIntConfig("catacombs", "autoRecomb", LootCommand.autoRecombs);
/* 1399 */       } else if (message.contains("Wither Helmet")) {
/* 1400 */         LootCommand.witherHelms++;
/* 1401 */         LootCommand.witherHelmsSession++;
/* 1402 */         ConfigHandler.writeIntConfig("catacombs", "witherHelm", LootCommand.witherHelms);
/* 1403 */       } else if (message.contains("Wither Chestplate")) {
/* 1404 */         LootCommand.witherChests++;
/* 1405 */         LootCommand.witherChestsSession++;
/* 1406 */         ConfigHandler.writeIntConfig("catacombs", "witherChest", LootCommand.witherChests);
/* 1407 */       } else if (message.contains("Wither Leggings")) {
/* 1408 */         LootCommand.witherLegs++;
/* 1409 */         LootCommand.witherLegsSession++;
/* 1410 */         ConfigHandler.writeIntConfig("catacombs", "witherLegging", LootCommand.witherLegs);
/* 1411 */       } else if (message.contains("Wither Boots")) {
/* 1412 */         LootCommand.witherBoots++;
/* 1413 */         LootCommand.witherBootsSession++;
/* 1414 */         ConfigHandler.writeIntConfig("catacombs", "witherBoot", LootCommand.witherBoots);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 1419 */     if (message.contains("[OPEN MENU]")) {
/* 1420 */       List<IChatComponent> listOfSiblings = event.message.func_150253_a();
/* 1421 */       for (IChatComponent sibling : listOfSiblings) {
/* 1422 */         if (sibling.func_150260_c().contains("[OPEN MENU]")) {
/* 1423 */           lastMaddoxCommand = sibling.func_150256_b().func_150235_h().func_150668_b();
/* 1424 */           lastMaddoxTime = (System.currentTimeMillis() / 1000L);
/*      */         } 
/*      */       } 
/* 1427 */       if (ToggleCommand.chatMaddoxToggled) (Minecraft.func_71410_x()).field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(MAIN_COLOUR + "Open chat then click anywhere on-screen to open Maddox"));
/*      */     
/*      */     } 
/*      */     
/* 1431 */     if (ToggleCommand.spiritBearAlerts && message.contains("The Spirit Bear has appeared!")) {
/* 1432 */       Utils.createTitle(EnumChatFormatting.DARK_PURPLE + "SPIRIT BEAR", 2);
/*      */     }
/*      */   }
/*      */   
/*      */   @SubscribeEvent
/*      */   public void renderPlayerInfo(RenderGameOverlayEvent.Post event) {
/* 1438 */     if (usingLabymod && !((Minecraft.func_71410_x()).field_71456_v instanceof net.minecraftforge.client.GuiIngameForge))
/* 1439 */       return;  if (event.type != RenderGameOverlayEvent.ElementType.EXPERIENCE && event.type != RenderGameOverlayEvent.ElementType.JUMPBAR)
/*      */       return; 
/* 1441 */     renderEverything();
/*      */   }
/*      */ 
/*      */   
/*      */   @SubscribeEvent
/*      */   public void renderPlayerInfoLabyMod(RenderGameOverlayEvent event) {
/* 1447 */     if (!usingLabymod)
/* 1448 */       return;  if (event.type != null)
/* 1449 */       return;  renderEverything();
/*      */   }
/*      */   
/*      */   public void renderEverything() {
/* 1453 */     if ((Minecraft.func_71410_x()).field_71462_r instanceof EditLocationsGui)
/*      */       return; 
/* 1455 */     Minecraft mc = Minecraft.func_71410_x();
/*      */     
/* 1457 */     if (ToggleCommand.coordsToggled) {
/* 1458 */       EntityPlayerSP entityPlayerSP = mc.field_71439_g;
/*      */       
/* 1460 */       double xDir = ((((EntityPlayer)entityPlayerSP).field_70177_z % 360.0F + 360.0F) % 360.0F);
/* 1461 */       if (xDir > 180.0D) xDir -= 360.0D; 
/* 1462 */       xDir = Math.round(xDir * 10.0D) / 10.0D;
/* 1463 */       double yDir = Math.round(((EntityPlayer)entityPlayerSP).field_70125_A * 10.0D) / 10.0D;
/*      */       
/* 1465 */       String coordText = COORDS_COLOUR + (int)((EntityPlayer)entityPlayerSP).field_70165_t + " / " + (int)((EntityPlayer)entityPlayerSP).field_70163_u + " / " + (int)((EntityPlayer)entityPlayerSP).field_70161_v + " (" + xDir + " / " + yDir + ")";
/* 1466 */       new TextRenderer(mc, coordText, MoveCommand.coordsXY[0], MoveCommand.coordsXY[1], ScaleCommand.coordsScale);
/*      */     } 
/*      */     
/* 1469 */     if (ToggleCommand.dungeonTimerToggled && Utils.inDungeons) {
/* 1470 */       String dungeonTimerText = EnumChatFormatting.GRAY + "Wither Doors:\n" + EnumChatFormatting.DARK_RED + "Blood Open:\n" + EnumChatFormatting.RED + "Watcher Clear:\n" + EnumChatFormatting.BLUE + "Boss Clear:\n" + EnumChatFormatting.YELLOW + "Deaths:\n" + EnumChatFormatting.YELLOW + "Puzzle Fails:";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1479 */       String dungeonTimers = EnumChatFormatting.GRAY + "" + witherDoors + "\n" + EnumChatFormatting.DARK_RED + Utils.getTimeBetween(dungeonStartTime, bloodOpenTime) + "\n" + EnumChatFormatting.RED + Utils.getTimeBetween(dungeonStartTime, watcherClearTime) + "\n" + EnumChatFormatting.BLUE + Utils.getTimeBetween(dungeonStartTime, bossClearTime) + "\n" + EnumChatFormatting.YELLOW + dungeonDeaths + "\n" + EnumChatFormatting.YELLOW + puzzleFails;
/*      */ 
/*      */       
/* 1482 */       new TextRenderer(mc, dungeonTimerText, MoveCommand.dungeonTimerXY[0], MoveCommand.dungeonTimerXY[1], ScaleCommand.dungeonTimerScale);
/* 1483 */       new TextRenderer(mc, dungeonTimers, (int)(MoveCommand.dungeonTimerXY[0] + 80.0D * ScaleCommand.dungeonTimerScale), MoveCommand.dungeonTimerXY[1], ScaleCommand.dungeonTimerScale);
/*      */     } 
/*      */     
/* 1486 */     if (ToggleCommand.lividSolverToggled && foundLivid && livid != null) {
/* 1487 */       new TextRenderer(mc, livid.func_70005_c_().replace("" + EnumChatFormatting.BOLD, ""), MoveCommand.lividHpXY[0], MoveCommand.lividHpXY[1], ScaleCommand.lividHpScale);
/*      */     }
/*      */     
/* 1490 */     if (ToggleCommand.cakeTimerToggled) {
/* 1491 */       String cakeText; double scale = ScaleCommand.cakeTimerScale;
/* 1492 */       double scaleReset = Math.pow(scale, -1.0D);
/* 1493 */       GL11.glScaled(scale, scale, scale);
/*      */       
/* 1495 */       double timeNow = (System.currentTimeMillis() / 1000L);
/* 1496 */       mc.func_110434_K().func_110577_a(CAKE_ICON);
/* 1497 */       Gui.func_146110_a(MoveCommand.cakeTimerXY[0], MoveCommand.cakeTimerXY[1], 0.0F, 0.0F, 16, 16, 16.0F, 16.0F);
/*      */ 
/*      */       
/* 1500 */       if (cakeTime - timeNow < 0.0D) {
/* 1501 */         cakeText = EnumChatFormatting.RED + "NONE";
/*      */       } else {
/* 1503 */         cakeText = CAKE_COLOUR + Utils.getTimeBetween(timeNow, cakeTime);
/*      */       } 
/* 1505 */       new TextRenderer(mc, cakeText, MoveCommand.cakeTimerXY[0] + 20, MoveCommand.cakeTimerXY[1] + 5, 1.0D);
/*      */       
/* 1507 */       GL11.glScaled(scaleReset, scaleReset, scaleReset);
/*      */     } 
/*      */     
/* 1510 */     if (ToggleCommand.bonzoTimerToggled && Utils.inDungeons) {
/*      */       
/* 1512 */       ItemStack helmetSlot = mc.field_71439_g.func_82169_q(3);
/* 1513 */       if ((helmetSlot != null && helmetSlot.func_82833_r().contains("Bonzo's Mask")) || nextBonzoUse > 0.0D) {
/*      */         String bonzoText;
/* 1515 */         double scale = ScaleCommand.bonzoTimerScale;
/* 1516 */         double scaleReset = Math.pow(scale, -1.0D);
/* 1517 */         GL11.glScaled(scale, scale, scale);
/*      */         
/* 1519 */         double timeNow = (System.currentTimeMillis() / 1000L);
/* 1520 */         mc.func_110434_K().func_110577_a(BONZO_ICON);
/* 1521 */         Gui.func_146110_a(MoveCommand.bonzoTimerXY[0], MoveCommand.bonzoTimerXY[1], 0.0F, 0.0F, 16, 16, 16.0F, 16.0F);
/*      */ 
/*      */         
/* 1524 */         if (nextBonzoUse - timeNow < 0.0D) {
/* 1525 */           bonzoText = EnumChatFormatting.GREEN + "READY";
/*      */         } else {
/* 1527 */           bonzoText = BONZO_COLOR + Utils.getTimeBetween(timeNow, nextBonzoUse);
/*      */         } 
/* 1529 */         new TextRenderer(mc, bonzoText, MoveCommand.bonzoTimerXY[0] + 20, MoveCommand.bonzoTimerXY[1] + 5, 1.0D);
/*      */         
/* 1531 */         GL11.glScaled(scaleReset, scaleReset, scaleReset);
/*      */       } 
/*      */     } 
/*      */     
/* 1535 */     if (showSkillTracker) {
/*      */       
/* 1537 */       double xpToShow = 0.0D;
/* 1538 */       switch (lastSkill) {
/*      */         case "Farming":
/* 1540 */           xpToShow = farmingXPGained;
/*      */           break;
/*      */         case "Mining":
/* 1543 */           xpToShow = miningXPGained;
/*      */           break;
/*      */         case "Combat":
/* 1546 */           xpToShow = combatXPGained;
/*      */           break;
/*      */         case "Foraging":
/* 1549 */           xpToShow = foragingXPGained;
/*      */           break;
/*      */         case "Fishing":
/* 1552 */           xpToShow = fishingXPGained;
/*      */           break;
/*      */         case "Enchanting":
/* 1555 */           xpToShow = enchantingXPGained;
/*      */           break;
/*      */         case "Alchemy":
/* 1558 */           xpToShow = alchemyXPGained;
/*      */           break;
/*      */         default:
/* 1561 */           System.err.println("Unknown skill in rendering."); break;
/*      */       } 
/* 1563 */       int xpPerHour = (int)Math.round(xpToShow / (skillStopwatch.getTime() + 1L) / 3600000.0D);
/*      */ 
/*      */       
/* 1566 */       String skillTrackerText = SKILL_TRACKER_COLOUR + lastSkill + " XP Earned: " + NumberFormat.getNumberInstance(Locale.US).format(xpToShow) + "\n" + SKILL_TRACKER_COLOUR + "Time Elapsed: " + Utils.getTimeBetween(0.0D, skillStopwatch.getTime() / 1000.0D) + "\n" + SKILL_TRACKER_COLOUR + "XP Per Hour: " + NumberFormat.getIntegerInstance(Locale.US).format(xpPerHour);
/* 1567 */       if (xpLeft >= 0.0D) {
/* 1568 */         String time = (xpPerHour == 0) ? "Never" : Utils.getTimeBetween(0.0D, xpLeft / xpPerHour / 3600.0D);
/* 1569 */         skillTrackerText = skillTrackerText + "\n" + SKILL_TRACKER_COLOUR + "Time Until Next Level: " + time;
/*      */       } 
/* 1571 */       if (!skillStopwatch.isStarted() || skillStopwatch.isSuspended()) {
/* 1572 */         skillTrackerText = skillTrackerText + "\n" + EnumChatFormatting.RED + "PAUSED";
/*      */       }
/*      */       
/* 1575 */       new TextRenderer(mc, skillTrackerText, MoveCommand.skillTrackerXY[0], MoveCommand.skillTrackerXY[1], ScaleCommand.skillTrackerScale);
/*      */     } 
/*      */     
/* 1578 */     if (ToggleCommand.waterToggled && Utils.inDungeons && waterAnswers != null) {
/* 1579 */       new TextRenderer(mc, waterAnswers, MoveCommand.waterAnswerXY[0], MoveCommand.waterAnswerXY[1], ScaleCommand.waterAnswerScale);
/*      */     }
/*      */     
/* 1582 */     if (!DisplayCommand.display.equals("off")) {
/* 1583 */       String dropsTextTwo, countTextTwo, timeBetween, bossesBetween, drop20, dropsText = "";
/* 1584 */       String countText = "";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1590 */       double timeNow = (System.currentTimeMillis() / 1000L);
/* 1591 */       NumberFormat nf = NumberFormat.getIntegerInstance(Locale.US);
/*      */       
/* 1593 */       switch (DisplayCommand.display) {
/*      */         case "wolf":
/* 1595 */           if (LootCommand.wolfTime == -1.0D) {
/* 1596 */             timeBetween = "Never";
/*      */           } else {
/* 1598 */             timeBetween = Utils.getTimeBetween(LootCommand.wolfTime, timeNow);
/*      */           } 
/* 1600 */           if (LootCommand.wolfBosses == -1) {
/* 1601 */             bossesBetween = "Never";
/*      */           } else {
/* 1603 */             bossesBetween = nf.format(LootCommand.wolfBosses);
/*      */           } 
/* 1605 */           if (ToggleCommand.slayerCountTotal) {
/* 1606 */             drop20 = nf.format(LootCommand.wolfWheels);
/*      */           } else {
/* 1608 */             drop20 = nf.format(LootCommand.wolfWheelsDrops) + " times";
/*      */           } 
/*      */           
/* 1611 */           dropsText = EnumChatFormatting.GOLD + "Svens Killed:\n" + EnumChatFormatting.GREEN + "Wolf Teeth:\n" + EnumChatFormatting.BLUE + "Hamster Wheels:\n" + EnumChatFormatting.AQUA + "Spirit Runes:\n" + EnumChatFormatting.WHITE + "Critical VI Books:\n" + EnumChatFormatting.DARK_RED + "Red Claw Eggs:\n" + EnumChatFormatting.GOLD + "Couture Runes:\n" + EnumChatFormatting.AQUA + "Grizzly Baits:\n" + EnumChatFormatting.DARK_PURPLE + "Overfluxes:\n" + EnumChatFormatting.AQUA + "Time Since RNG:\n" + EnumChatFormatting.AQUA + "Bosses Since RNG:";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1623 */           countText = EnumChatFormatting.GOLD + nf.format(LootCommand.wolfSvens) + "\n" + EnumChatFormatting.GREEN + nf.format(LootCommand.wolfTeeth) + "\n" + EnumChatFormatting.BLUE + drop20 + "\n" + EnumChatFormatting.AQUA + LootCommand.wolfSpirits + "\n" + EnumChatFormatting.WHITE + LootCommand.wolfBooks + "\n" + EnumChatFormatting.DARK_RED + LootCommand.wolfEggs + "\n" + EnumChatFormatting.GOLD + LootCommand.wolfCoutures + "\n" + EnumChatFormatting.AQUA + LootCommand.wolfBaits + "\n" + EnumChatFormatting.DARK_PURPLE + LootCommand.wolfFluxes + "\n" + EnumChatFormatting.AQUA + timeBetween + "\n" + EnumChatFormatting.AQUA + bossesBetween;
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case "wolf_session":
/* 1635 */           if (LootCommand.wolfTimeSession == -1.0D) {
/* 1636 */             timeBetween = "Never";
/*      */           } else {
/* 1638 */             timeBetween = Utils.getTimeBetween(LootCommand.wolfTimeSession, timeNow);
/*      */           } 
/* 1640 */           if (LootCommand.wolfBossesSession == -1) {
/* 1641 */             bossesBetween = "Never";
/*      */           } else {
/* 1643 */             bossesBetween = nf.format(LootCommand.wolfBossesSession);
/*      */           } 
/* 1645 */           if (ToggleCommand.slayerCountTotal) {
/* 1646 */             drop20 = nf.format(LootCommand.wolfWheelsSession);
/*      */           } else {
/* 1648 */             drop20 = nf.format(LootCommand.wolfWheelsDropsSession) + " times";
/*      */           } 
/*      */           
/* 1651 */           dropsText = EnumChatFormatting.GOLD + "Svens Killed:\n" + EnumChatFormatting.GREEN + "Wolf Teeth:\n" + EnumChatFormatting.BLUE + "Hamster Wheels:\n" + EnumChatFormatting.AQUA + "Spirit Runes:\n" + EnumChatFormatting.WHITE + "Critical VI Books:\n" + EnumChatFormatting.DARK_RED + "Red Claw Eggs:\n" + EnumChatFormatting.GOLD + "Couture Runes:\n" + EnumChatFormatting.AQUA + "Grizzly Baits:\n" + EnumChatFormatting.DARK_PURPLE + "Overfluxes:\n" + EnumChatFormatting.AQUA + "Time Since RNG:\n" + EnumChatFormatting.AQUA + "Bosses Since RNG:";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1663 */           countText = EnumChatFormatting.GOLD + nf.format(LootCommand.wolfSvensSession) + "\n" + EnumChatFormatting.GREEN + nf.format(LootCommand.wolfTeethSession) + "\n" + EnumChatFormatting.BLUE + drop20 + "\n" + EnumChatFormatting.AQUA + LootCommand.wolfSpiritsSession + "\n" + EnumChatFormatting.WHITE + LootCommand.wolfBooksSession + "\n" + EnumChatFormatting.DARK_RED + LootCommand.wolfEggsSession + "\n" + EnumChatFormatting.GOLD + LootCommand.wolfCouturesSession + "\n" + EnumChatFormatting.AQUA + LootCommand.wolfBaitsSession + "\n" + EnumChatFormatting.DARK_PURPLE + LootCommand.wolfFluxesSession + "\n" + EnumChatFormatting.AQUA + timeBetween + "\n" + EnumChatFormatting.AQUA + bossesBetween;
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case "spider":
/* 1675 */           if (LootCommand.spiderTime == -1.0D) {
/* 1676 */             timeBetween = "Never";
/*      */           } else {
/* 1678 */             timeBetween = Utils.getTimeBetween(LootCommand.spiderTime, timeNow);
/*      */           } 
/* 1680 */           if (LootCommand.spiderBosses == -1) {
/* 1681 */             bossesBetween = "Never";
/*      */           } else {
/* 1683 */             bossesBetween = nf.format(LootCommand.spiderBosses);
/*      */           } 
/* 1685 */           if (ToggleCommand.slayerCountTotal) {
/* 1686 */             drop20 = nf.format(LootCommand.spiderTAP);
/*      */           } else {
/* 1688 */             drop20 = nf.format(LootCommand.spiderTAPDrops) + " times";
/*      */           } 
/*      */           
/* 1691 */           dropsText = EnumChatFormatting.GOLD + "Tarantulas Killed:\n" + EnumChatFormatting.GREEN + "Tarantula Webs:\n" + EnumChatFormatting.DARK_GREEN + "Arrow Poison:\n" + EnumChatFormatting.DARK_GRAY + "Bite Runes:\n" + EnumChatFormatting.WHITE + "Bane VI Books:\n" + EnumChatFormatting.AQUA + "Spider Catalysts:\n" + EnumChatFormatting.DARK_PURPLE + "Tarantula Talismans:\n" + EnumChatFormatting.LIGHT_PURPLE + "Fly Swatters:\n" + EnumChatFormatting.GOLD + "Digested Mosquitos:\n" + EnumChatFormatting.AQUA + "Time Since RNG:\n" + EnumChatFormatting.AQUA + "Bosses Since RNG:";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1703 */           countText = EnumChatFormatting.GOLD + nf.format(LootCommand.spiderTarantulas) + "\n" + EnumChatFormatting.GREEN + nf.format(LootCommand.spiderWebs) + "\n" + EnumChatFormatting.DARK_GREEN + drop20 + "\n" + EnumChatFormatting.DARK_GRAY + LootCommand.spiderBites + "\n" + EnumChatFormatting.WHITE + LootCommand.spiderBooks + "\n" + EnumChatFormatting.AQUA + LootCommand.spiderCatalysts + "\n" + EnumChatFormatting.DARK_PURPLE + LootCommand.spiderTalismans + "\n" + EnumChatFormatting.LIGHT_PURPLE + LootCommand.spiderSwatters + "\n" + EnumChatFormatting.GOLD + LootCommand.spiderMosquitos + "\n" + EnumChatFormatting.AQUA + timeBetween + "\n" + EnumChatFormatting.AQUA + bossesBetween;
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case "spider_session":
/* 1715 */           if (LootCommand.spiderTimeSession == -1.0D) {
/* 1716 */             timeBetween = "Never";
/*      */           } else {
/* 1718 */             timeBetween = Utils.getTimeBetween(LootCommand.spiderTimeSession, timeNow);
/*      */           } 
/* 1720 */           if (LootCommand.spiderBossesSession == -1) {
/* 1721 */             bossesBetween = "Never";
/*      */           } else {
/* 1723 */             bossesBetween = nf.format(LootCommand.spiderBossesSession);
/*      */           } 
/* 1725 */           if (ToggleCommand.slayerCountTotal) {
/* 1726 */             drop20 = nf.format(LootCommand.spiderTAPSession);
/*      */           } else {
/* 1728 */             drop20 = nf.format(LootCommand.spiderTAPDropsSession) + " times";
/*      */           } 
/*      */           
/* 1731 */           dropsText = EnumChatFormatting.GOLD + "Tarantulas Killed:\n" + EnumChatFormatting.GREEN + "Tarantula Webs:\n" + EnumChatFormatting.DARK_GREEN + "Arrow Poison:\n" + EnumChatFormatting.DARK_GRAY + "Bite Runes:\n" + EnumChatFormatting.WHITE + "Bane VI Books:\n" + EnumChatFormatting.AQUA + "Spider Catalysts:\n" + EnumChatFormatting.DARK_PURPLE + "Tarantula Talismans:\n" + EnumChatFormatting.LIGHT_PURPLE + "Fly Swatters:\n" + EnumChatFormatting.GOLD + "Digested Mosquitos:\n" + EnumChatFormatting.AQUA + "Time Since RNG:\n" + EnumChatFormatting.AQUA + "Bosses Since RNG:";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1743 */           countText = EnumChatFormatting.GOLD + nf.format(LootCommand.spiderTarantulasSession) + "\n" + EnumChatFormatting.GREEN + nf.format(LootCommand.spiderWebsSession) + "\n" + EnumChatFormatting.DARK_GREEN + drop20 + "\n" + EnumChatFormatting.DARK_GRAY + LootCommand.spiderBitesSession + "\n" + EnumChatFormatting.WHITE + LootCommand.spiderBooksSession + "\n" + EnumChatFormatting.AQUA + LootCommand.spiderCatalystsSession + "\n" + EnumChatFormatting.DARK_PURPLE + LootCommand.spiderTalismansSession + "\n" + EnumChatFormatting.LIGHT_PURPLE + LootCommand.spiderSwattersSession + "\n" + EnumChatFormatting.GOLD + LootCommand.spiderMosquitosSession + "\n" + EnumChatFormatting.AQUA + timeBetween + "\n" + EnumChatFormatting.AQUA + bossesBetween;
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case "zombie":
/* 1755 */           if (LootCommand.zombieTime == -1.0D) {
/* 1756 */             timeBetween = "Never";
/*      */           } else {
/* 1758 */             timeBetween = Utils.getTimeBetween(LootCommand.zombieTime, timeNow);
/*      */           } 
/* 1760 */           if (LootCommand.zombieBosses == -1) {
/* 1761 */             bossesBetween = "Never";
/*      */           } else {
/* 1763 */             bossesBetween = nf.format(LootCommand.zombieBosses);
/*      */           } 
/* 1765 */           if (ToggleCommand.slayerCountTotal) {
/* 1766 */             drop20 = nf.format(LootCommand.zombieFoulFlesh);
/*      */           } else {
/* 1768 */             drop20 = nf.format(LootCommand.zombieFoulFleshDrops) + " times";
/*      */           } 
/*      */           
/* 1771 */           dropsText = EnumChatFormatting.GOLD + "Revs Killed:\n" + EnumChatFormatting.GREEN + "Revenant Flesh:\n" + EnumChatFormatting.BLUE + "Foul Flesh:\n" + EnumChatFormatting.DARK_GREEN + "Pestilence Runes:\n" + EnumChatFormatting.WHITE + "Smite VI Books:\n" + EnumChatFormatting.AQUA + "Undead Catalysts:\n" + EnumChatFormatting.DARK_PURPLE + "Beheaded Horrors:\n" + EnumChatFormatting.RED + "Revenant Catalysts:\n" + EnumChatFormatting.DARK_GREEN + "Snake Runes:\n" + EnumChatFormatting.GOLD + "Scythe Blades:\n" + EnumChatFormatting.AQUA + "Time Since RNG:\n" + EnumChatFormatting.AQUA + "Bosses Since RNG:";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1784 */           countText = EnumChatFormatting.GOLD + nf.format(LootCommand.zombieRevs) + "\n" + EnumChatFormatting.GREEN + nf.format(LootCommand.zombieRevFlesh) + "\n" + EnumChatFormatting.BLUE + drop20 + "\n" + EnumChatFormatting.DARK_GREEN + LootCommand.zombiePestilences + "\n" + EnumChatFormatting.WHITE + LootCommand.zombieBooks + "\n" + EnumChatFormatting.AQUA + LootCommand.zombieUndeadCatas + "\n" + EnumChatFormatting.DARK_PURPLE + LootCommand.zombieBeheadeds + "\n" + EnumChatFormatting.RED + LootCommand.zombieRevCatas + "\n" + EnumChatFormatting.DARK_GREEN + LootCommand.zombieSnakes + "\n" + EnumChatFormatting.GOLD + LootCommand.zombieScythes + "\n" + EnumChatFormatting.AQUA + timeBetween + "\n" + EnumChatFormatting.AQUA + bossesBetween;
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case "zombie_session":
/* 1797 */           if (LootCommand.zombieTimeSession == -1.0D) {
/* 1798 */             timeBetween = "Never";
/*      */           } else {
/* 1800 */             timeBetween = Utils.getTimeBetween(LootCommand.zombieTimeSession, timeNow);
/*      */           } 
/* 1802 */           if (LootCommand.zombieBossesSession == -1) {
/* 1803 */             bossesBetween = "Never";
/*      */           } else {
/* 1805 */             bossesBetween = nf.format(LootCommand.zombieBossesSession);
/*      */           } 
/* 1807 */           if (ToggleCommand.slayerCountTotal) {
/* 1808 */             drop20 = nf.format(LootCommand.zombieFoulFleshSession);
/*      */           } else {
/* 1810 */             drop20 = nf.format(LootCommand.zombieFoulFleshDropsSession) + " times";
/*      */           } 
/*      */           
/* 1813 */           dropsText = EnumChatFormatting.GOLD + "Revs Killed:\n" + EnumChatFormatting.GREEN + "Revenant Flesh:\n" + EnumChatFormatting.BLUE + "Foul Flesh:\n" + EnumChatFormatting.DARK_GREEN + "Pestilence Runes:\n" + EnumChatFormatting.WHITE + "Smite VI Books:\n" + EnumChatFormatting.AQUA + "Undead Catalysts:\n" + EnumChatFormatting.DARK_PURPLE + "Beheaded Horrors:\n" + EnumChatFormatting.RED + "Revenant Catalysts:\n" + EnumChatFormatting.DARK_GREEN + "Snake Runes:\n" + EnumChatFormatting.GOLD + "Scythe Blades:\n" + EnumChatFormatting.AQUA + "Time Since RNG:\n" + EnumChatFormatting.AQUA + "Bosses Since RNG:";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1826 */           countText = EnumChatFormatting.GOLD + nf.format(LootCommand.zombieRevsSession) + "\n" + EnumChatFormatting.GREEN + nf.format(LootCommand.zombieRevFleshSession) + "\n" + EnumChatFormatting.BLUE + drop20 + "\n" + EnumChatFormatting.DARK_GREEN + LootCommand.zombiePestilencesSession + "\n" + EnumChatFormatting.WHITE + LootCommand.zombieBooksSession + "\n" + EnumChatFormatting.AQUA + LootCommand.zombieUndeadCatasSession + "\n" + EnumChatFormatting.DARK_PURPLE + LootCommand.zombieBeheadedsSession + "\n" + EnumChatFormatting.RED + LootCommand.zombieRevCatasSession + "\n" + EnumChatFormatting.DARK_GREEN + LootCommand.zombieSnakesSession + "\n" + EnumChatFormatting.GOLD + LootCommand.zombieScythes + "\n" + EnumChatFormatting.AQUA + timeBetween + "\n" + EnumChatFormatting.AQUA + bossesBetween;
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case "fishing":
/* 1839 */           if (LootCommand.empTime == -1.0D) {
/* 1840 */             timeBetween = "Never";
/*      */           } else {
/* 1842 */             timeBetween = Utils.getTimeBetween(LootCommand.empTime, timeNow);
/*      */           } 
/* 1844 */           if (LootCommand.empSCs == -1) {
/* 1845 */             bossesBetween = "Never";
/*      */           } else {
/* 1847 */             bossesBetween = nf.format(LootCommand.empSCs);
/*      */           } 
/*      */           
/* 1850 */           dropsText = EnumChatFormatting.AQUA + "Creatures Caught:\n" + EnumChatFormatting.AQUA + "Fishing Milestone:\n" + EnumChatFormatting.GOLD + "Good Catches:\n" + EnumChatFormatting.DARK_PURPLE + "Great Catches:\n" + EnumChatFormatting.GRAY + "Squids:\n" + EnumChatFormatting.GREEN + "Sea Walkers:\n" + EnumChatFormatting.DARK_GRAY + "Night Squids:\n" + EnumChatFormatting.DARK_AQUA + "Sea Guardians:\n" + EnumChatFormatting.BLUE + "Sea Witches:\n" + EnumChatFormatting.GREEN + "Sea Archers:";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1869 */           countText = EnumChatFormatting.AQUA + nf.format(LootCommand.seaCreatures) + "\n" + EnumChatFormatting.AQUA + nf.format(LootCommand.fishingMilestone) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.goodCatches) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.greatCatches) + "\n" + EnumChatFormatting.GRAY + nf.format(LootCommand.squids) + "\n" + EnumChatFormatting.GREEN + nf.format(LootCommand.seaWalkers) + "\n" + EnumChatFormatting.DARK_GRAY + nf.format(LootCommand.nightSquids) + "\n" + EnumChatFormatting.DARK_AQUA + nf.format(LootCommand.seaGuardians) + "\n" + EnumChatFormatting.BLUE + nf.format(LootCommand.seaWitches) + "\n" + EnumChatFormatting.GREEN + nf.format(LootCommand.seaArchers);
/*      */           
/* 1871 */           dropsTextTwo = EnumChatFormatting.GREEN + "Monster of Deeps:\n" + EnumChatFormatting.YELLOW + "Catfishes:\n" + EnumChatFormatting.GOLD + "Carrot Kings:\n" + EnumChatFormatting.GRAY + "Sea Leeches:\n" + EnumChatFormatting.DARK_PURPLE + "Guardian Defenders:\n" + EnumChatFormatting.DARK_PURPLE + "Deep Sea Protectors:\n" + EnumChatFormatting.GOLD + "Hydras:\n" + EnumChatFormatting.GOLD + "Sea Emperors:\n" + EnumChatFormatting.AQUA + "Time Since Emp:\n" + EnumChatFormatting.AQUA + "Creatures Since Emp:";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1888 */           countTextTwo = EnumChatFormatting.GREEN + nf.format(LootCommand.monsterOfTheDeeps) + "\n" + EnumChatFormatting.YELLOW + nf.format(LootCommand.catfishes) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.carrotKings) + "\n" + EnumChatFormatting.GRAY + nf.format(LootCommand.seaLeeches) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.guardianDefenders) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.deepSeaProtectors) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.hydras) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.seaEmperors) + "\n" + EnumChatFormatting.AQUA + timeBetween + "\n" + EnumChatFormatting.AQUA + bossesBetween;
/*      */ 
/*      */ 
/*      */           
/* 1892 */           if (ToggleCommand.splitFishing) {
/* 1893 */             new TextRenderer(mc, dropsTextTwo, (int)(MoveCommand.displayXY[0] + 160.0D * ScaleCommand.displayScale), MoveCommand.displayXY[1], ScaleCommand.displayScale);
/* 1894 */             new TextRenderer(mc, countTextTwo, (int)(MoveCommand.displayXY[0] + 270.0D * ScaleCommand.displayScale), MoveCommand.displayXY[1], ScaleCommand.displayScale); break;
/*      */           } 
/* 1896 */           dropsText = dropsText + "\n" + dropsTextTwo;
/* 1897 */           countText = countText + "\n" + countTextTwo;
/*      */           break;
/*      */         
/*      */         case "fishing_session":
/* 1901 */           if (LootCommand.empTimeSession == -1.0D) {
/* 1902 */             timeBetween = "Never";
/*      */           } else {
/* 1904 */             timeBetween = Utils.getTimeBetween(LootCommand.empTimeSession, timeNow);
/*      */           } 
/* 1906 */           if (LootCommand.empSCsSession == -1) {
/* 1907 */             bossesBetween = "Never";
/*      */           } else {
/* 1909 */             bossesBetween = nf.format(LootCommand.empSCsSession);
/*      */           } 
/*      */           
/* 1912 */           dropsText = EnumChatFormatting.AQUA + "Creatures Caught:\n" + EnumChatFormatting.AQUA + "Fishing Milestone:\n" + EnumChatFormatting.GOLD + "Good Catches:\n" + EnumChatFormatting.DARK_PURPLE + "Great Catches:\n" + EnumChatFormatting.GRAY + "Squids:\n" + EnumChatFormatting.GREEN + "Sea Walkers:\n" + EnumChatFormatting.DARK_GRAY + "Night Squids:\n" + EnumChatFormatting.DARK_AQUA + "Sea Guardians:\n" + EnumChatFormatting.BLUE + "Sea Witches:\n" + EnumChatFormatting.GREEN + "Sea Archers:";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1931 */           countText = EnumChatFormatting.AQUA + nf.format(LootCommand.seaCreaturesSession) + "\n" + EnumChatFormatting.AQUA + nf.format(LootCommand.fishingMilestoneSession) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.goodCatchesSession) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.greatCatchesSession) + "\n" + EnumChatFormatting.GRAY + nf.format(LootCommand.squidsSession) + "\n" + EnumChatFormatting.GREEN + nf.format(LootCommand.seaWalkersSession) + "\n" + EnumChatFormatting.DARK_GRAY + nf.format(LootCommand.nightSquidsSession) + "\n" + EnumChatFormatting.DARK_AQUA + nf.format(LootCommand.seaGuardiansSession) + "\n" + EnumChatFormatting.BLUE + nf.format(LootCommand.seaWitchesSession) + "\n" + EnumChatFormatting.GREEN + nf.format(LootCommand.seaArchersSession);
/*      */           
/* 1933 */           dropsTextTwo = EnumChatFormatting.GREEN + "Monster of Deeps:\n" + EnumChatFormatting.YELLOW + "Catfishes:\n" + EnumChatFormatting.GOLD + "Carrot Kings:\n" + EnumChatFormatting.GRAY + "Sea Leeches:\n" + EnumChatFormatting.DARK_PURPLE + "Guardian Defenders:\n" + EnumChatFormatting.DARK_PURPLE + "Deep Sea Protectors:\n" + EnumChatFormatting.GOLD + "Hydras:\n" + EnumChatFormatting.GOLD + "Sea Emperors:\n" + EnumChatFormatting.AQUA + "Time Since Emp:\n" + EnumChatFormatting.AQUA + "Creatures Since Emp:";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1950 */           countTextTwo = EnumChatFormatting.GREEN + nf.format(LootCommand.monsterOfTheDeepsSession) + "\n" + EnumChatFormatting.YELLOW + nf.format(LootCommand.catfishesSession) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.carrotKingsSession) + "\n" + EnumChatFormatting.GRAY + nf.format(LootCommand.seaLeechesSession) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.guardianDefendersSession) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.deepSeaProtectorsSession) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.hydrasSession) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.seaEmperorsSession) + "\n" + EnumChatFormatting.AQUA + timeBetween + "\n" + EnumChatFormatting.AQUA + bossesBetween;
/*      */ 
/*      */ 
/*      */           
/* 1954 */           if (ToggleCommand.splitFishing) {
/* 1955 */             new TextRenderer(mc, dropsTextTwo, (int)(MoveCommand.displayXY[0] + 160.0D * ScaleCommand.displayScale), MoveCommand.displayXY[1], ScaleCommand.displayScale);
/* 1956 */             new TextRenderer(mc, countTextTwo, (int)(MoveCommand.displayXY[0] + 270.0D * ScaleCommand.displayScale), MoveCommand.displayXY[1], ScaleCommand.displayScale); break;
/*      */           } 
/* 1958 */           dropsText = dropsText + "\n" + dropsTextTwo;
/* 1959 */           countText = countText + "\n" + countTextTwo;
/*      */           break;
/*      */         
/*      */         case "fishing_winter":
/* 1963 */           if (LootCommand.yetiTime == -1.0D) {
/* 1964 */             timeBetween = "Never";
/*      */           } else {
/* 1966 */             timeBetween = Utils.getTimeBetween(LootCommand.yetiTime, timeNow);
/*      */           } 
/* 1968 */           if (LootCommand.yetiSCs == -1) {
/* 1969 */             bossesBetween = "Never";
/*      */           } else {
/* 1971 */             bossesBetween = nf.format(LootCommand.yetiSCs);
/*      */           } 
/*      */           
/* 1974 */           dropsText = EnumChatFormatting.AQUA + "Creatures Caught:\n" + EnumChatFormatting.AQUA + "Fishing Milestone:\n" + EnumChatFormatting.GOLD + "Good Catches:\n" + EnumChatFormatting.DARK_PURPLE + "Great Catches:\n" + EnumChatFormatting.AQUA + "Frozen Steves:\n" + EnumChatFormatting.WHITE + "Snowmans:\n" + EnumChatFormatting.DARK_GREEN + "Grinches:\n" + EnumChatFormatting.GOLD + "Yetis:\n" + EnumChatFormatting.AQUA + "Time Since Yeti:\n" + EnumChatFormatting.AQUA + "Creatures Since Yeti:";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1991 */           countText = EnumChatFormatting.AQUA + nf.format(LootCommand.seaCreatures) + "\n" + EnumChatFormatting.AQUA + nf.format(LootCommand.fishingMilestone) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.goodCatches) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.greatCatches) + "\n" + EnumChatFormatting.AQUA + nf.format(LootCommand.frozenSteves) + "\n" + EnumChatFormatting.WHITE + nf.format(LootCommand.frostyTheSnowmans) + "\n" + EnumChatFormatting.DARK_GREEN + nf.format(LootCommand.grinches) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.yetis) + "\n" + EnumChatFormatting.AQUA + timeBetween + "\n" + EnumChatFormatting.AQUA + bossesBetween;
/*      */           break;
/*      */ 
/*      */         
/*      */         case "fishing_winter_session":
/* 1996 */           if (LootCommand.yetiTimeSession == -1.0D) {
/* 1997 */             timeBetween = "Never";
/*      */           } else {
/* 1999 */             timeBetween = Utils.getTimeBetween(LootCommand.yetiTimeSession, timeNow);
/*      */           } 
/* 2001 */           if (LootCommand.yetiSCsSession == -1) {
/* 2002 */             bossesBetween = "Never";
/*      */           } else {
/* 2004 */             bossesBetween = nf.format(LootCommand.yetiSCsSession);
/*      */           } 
/*      */           
/* 2007 */           dropsText = EnumChatFormatting.AQUA + "Creatures Caught:\n" + EnumChatFormatting.AQUA + "Fishing Milestone:\n" + EnumChatFormatting.GOLD + "Good Catches:\n" + EnumChatFormatting.DARK_PURPLE + "Great Catches:\n" + EnumChatFormatting.AQUA + "Frozen Steves:\n" + EnumChatFormatting.WHITE + "Snowmans:\n" + EnumChatFormatting.DARK_GREEN + "Grinches:\n" + EnumChatFormatting.GOLD + "Yetis:\n" + EnumChatFormatting.AQUA + "Time Since Yeti:\n" + EnumChatFormatting.AQUA + "Creatures Since Yeti:";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2024 */           countText = EnumChatFormatting.AQUA + nf.format(LootCommand.seaCreaturesSession) + "\n" + EnumChatFormatting.AQUA + nf.format(LootCommand.fishingMilestoneSession) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.goodCatchesSession) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.greatCatchesSession) + "\n" + EnumChatFormatting.AQUA + nf.format(LootCommand.frozenStevesSession) + "\n" + EnumChatFormatting.WHITE + nf.format(LootCommand.frostyTheSnowmansSession) + "\n" + EnumChatFormatting.DARK_GREEN + nf.format(LootCommand.grinchesSession) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.yetisSession) + "\n" + EnumChatFormatting.AQUA + timeBetween + "\n" + EnumChatFormatting.AQUA + bossesBetween;
/*      */           break;
/*      */ 
/*      */         
/*      */         case "fishing_festival":
/* 2029 */           dropsText = EnumChatFormatting.AQUA + "Creatures Caught:\n" + EnumChatFormatting.AQUA + "Fishing Milestone:\n" + EnumChatFormatting.GOLD + "Good Catches:\n" + EnumChatFormatting.DARK_PURPLE + "Great Catches:\n" + EnumChatFormatting.LIGHT_PURPLE + "Nurse Sharks:\n" + EnumChatFormatting.BLUE + "Blue Sharks:\n" + EnumChatFormatting.GOLD + "Tiger Sharks:\n" + EnumChatFormatting.WHITE + "Great White Sharks:";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2044 */           countText = EnumChatFormatting.AQUA + nf.format(LootCommand.seaCreatures) + "\n" + EnumChatFormatting.AQUA + nf.format(LootCommand.fishingMilestone) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.goodCatches) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.greatCatches) + "\n" + EnumChatFormatting.LIGHT_PURPLE + nf.format(LootCommand.nurseSharks) + "\n" + EnumChatFormatting.BLUE + nf.format(LootCommand.blueSharks) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.tigerSharks) + "\n" + EnumChatFormatting.WHITE + nf.format(LootCommand.greatWhiteSharks);
/*      */           break;
/*      */         case "fishing_festival_session":
/* 2047 */           dropsText = EnumChatFormatting.AQUA + "Creatures Caught:\n" + EnumChatFormatting.AQUA + "Fishing Milestone:\n" + EnumChatFormatting.GOLD + "Good Catches:\n" + EnumChatFormatting.DARK_PURPLE + "Great Catches:\n" + EnumChatFormatting.LIGHT_PURPLE + "Nurse Sharks:\n" + EnumChatFormatting.BLUE + "Blue Sharks:\n" + EnumChatFormatting.GOLD + "Tiger Sharks:\n" + EnumChatFormatting.WHITE + "Great White Sharks:";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2062 */           countText = EnumChatFormatting.AQUA + nf.format(LootCommand.seaCreaturesSession) + "\n" + EnumChatFormatting.AQUA + nf.format(LootCommand.fishingMilestoneSession) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.goodCatchesSession) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.greatCatchesSession) + "\n" + EnumChatFormatting.LIGHT_PURPLE + nf.format(LootCommand.nurseSharksSession) + "\n" + EnumChatFormatting.BLUE + nf.format(LootCommand.blueSharksSession) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.tigerSharksSession) + "\n" + EnumChatFormatting.WHITE + nf.format(LootCommand.greatWhiteSharksSession);
/*      */           break;
/*      */         case "fishing_spooky":
/* 2065 */           dropsText = EnumChatFormatting.AQUA + "Creatures Caught:\n" + EnumChatFormatting.AQUA + "Fishing Milestone:\n" + EnumChatFormatting.GOLD + "Good Catches:\n" + EnumChatFormatting.DARK_PURPLE + "Great Catches:\n" + EnumChatFormatting.BLUE + "Scarecrows:\n" + EnumChatFormatting.GRAY + "Nightmares:\n" + EnumChatFormatting.DARK_PURPLE + "Werewolves:\n" + EnumChatFormatting.GOLD + "Phantom Fishers:\n" + EnumChatFormatting.GOLD + "Grim Reapers:";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2082 */           countText = EnumChatFormatting.AQUA + nf.format(LootCommand.seaCreatures) + "\n" + EnumChatFormatting.AQUA + nf.format(LootCommand.fishingMilestone) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.goodCatches) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.greatCatches) + "\n" + EnumChatFormatting.BLUE + nf.format(LootCommand.scarecrows) + "\n" + EnumChatFormatting.GRAY + nf.format(LootCommand.nightmares) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.werewolfs) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.phantomFishers) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.grimReapers);
/*      */           break;
/*      */         case "fishing_spooky_session":
/* 2085 */           dropsText = EnumChatFormatting.AQUA + "Creatures Caught:\n" + EnumChatFormatting.AQUA + "Fishing Milestone:\n" + EnumChatFormatting.GOLD + "Good Catches:\n" + EnumChatFormatting.DARK_PURPLE + "Great Catches:\n" + EnumChatFormatting.BLUE + "Scarecrows:\n" + EnumChatFormatting.GRAY + "Nightmares:\n" + EnumChatFormatting.DARK_PURPLE + "Werewolves:\n" + EnumChatFormatting.GOLD + "Phantom Fishers:\n" + EnumChatFormatting.GOLD + "Grim Reapers:";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2102 */           countText = EnumChatFormatting.AQUA + nf.format(LootCommand.seaCreaturesSession) + "\n" + EnumChatFormatting.AQUA + nf.format(LootCommand.fishingMilestoneSession) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.goodCatchesSession) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.greatCatchesSession) + "\n" + EnumChatFormatting.BLUE + nf.format(LootCommand.scarecrowsSession) + "\n" + EnumChatFormatting.GRAY + nf.format(LootCommand.nightmaresSession) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.werewolfsSession) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.phantomFishersSession) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.grimReapersSession);
/*      */           break;
/*      */         case "mythological":
/* 2105 */           dropsText = EnumChatFormatting.GOLD + "Coins:\n" + EnumChatFormatting.WHITE + "Griffin Feathers:\n" + EnumChatFormatting.GOLD + "Crown of Greeds:\n" + EnumChatFormatting.AQUA + "Washed up Souvenirs:\n" + EnumChatFormatting.RED + "Minos Hunters:\n" + EnumChatFormatting.GRAY + "Siamese Lynxes:\n" + EnumChatFormatting.RED + "Minotaurs:\n" + EnumChatFormatting.WHITE + "Gaia Constructs:\n" + EnumChatFormatting.DARK_PURPLE + "Minos Champions:\n" + EnumChatFormatting.GOLD + "Minos Inquisitors:";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2124 */           countText = EnumChatFormatting.GOLD + nf.format(LootCommand.mythCoins) + "\n" + EnumChatFormatting.WHITE + nf.format(LootCommand.griffinFeathers) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.crownOfGreeds) + "\n" + EnumChatFormatting.AQUA + nf.format(LootCommand.washedUpSouvenirs) + "\n" + EnumChatFormatting.RED + nf.format(LootCommand.minosHunters) + "\n" + EnumChatFormatting.GRAY + nf.format(LootCommand.siameseLynxes) + "\n" + EnumChatFormatting.RED + nf.format(LootCommand.minotaurs) + "\n" + EnumChatFormatting.WHITE + nf.format(LootCommand.gaiaConstructs) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.minosChampions) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.minosInquisitors);
/*      */           break;
/*      */         case "mythological_session":
/* 2127 */           dropsText = EnumChatFormatting.GOLD + "Coins:\n" + EnumChatFormatting.WHITE + "Griffin Feathers:\n" + EnumChatFormatting.GOLD + "Crown of Greeds:\n" + EnumChatFormatting.AQUA + "Washed up Souvenirs:\n" + EnumChatFormatting.RED + "Minos Hunters:\n" + EnumChatFormatting.GRAY + "Siamese Lynxes:\n" + EnumChatFormatting.RED + "Minotaurs:\n" + EnumChatFormatting.WHITE + "Gaia Constructs:\n" + EnumChatFormatting.DARK_PURPLE + "Minos Champions:\n" + EnumChatFormatting.GOLD + "Minos Inquisitors:";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2146 */           countText = EnumChatFormatting.GOLD + nf.format(LootCommand.mythCoinsSession) + "\n" + EnumChatFormatting.WHITE + nf.format(LootCommand.griffinFeathersSession) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.crownOfGreedsSession) + "\n" + EnumChatFormatting.AQUA + nf.format(LootCommand.washedUpSouvenirsSession) + "\n" + EnumChatFormatting.RED + nf.format(LootCommand.minosHuntersSession) + "\n" + EnumChatFormatting.GRAY + nf.format(LootCommand.siameseLynxesSession) + "\n" + EnumChatFormatting.RED + nf.format(LootCommand.minotaursSession) + "\n" + EnumChatFormatting.WHITE + nf.format(LootCommand.gaiaConstructsSession) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.minosChampionsSession) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.minosInquisitorsSession);
/*      */           break;
/*      */         case "catacombs_floor_one":
/* 2149 */           dropsText = EnumChatFormatting.GOLD + "Recombobulators:\n" + EnumChatFormatting.DARK_PURPLE + "Fuming Potato Books:\n" + EnumChatFormatting.BLUE + "Bonzo's Staffs:\n" + EnumChatFormatting.AQUA + "Coins Spent:\n" + EnumChatFormatting.AQUA + "Time Spent:";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2158 */           countText = EnumChatFormatting.GOLD + nf.format(LootCommand.recombobulators) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.fumingPotatoBooks) + "\n" + EnumChatFormatting.BLUE + nf.format(LootCommand.bonzoStaffs) + "\n" + EnumChatFormatting.AQUA + Utils.getMoneySpent(LootCommand.f1CoinsSpent) + "\n" + EnumChatFormatting.AQUA + Utils.getTimeBetween(0.0D, LootCommand.f1TimeSpent);
/*      */           break;
/*      */         case "catacombs_floor_one_session":
/* 2161 */           dropsText = EnumChatFormatting.GOLD + "Recombobulators:\n" + EnumChatFormatting.DARK_PURPLE + "Fuming Potato Books:\n" + EnumChatFormatting.BLUE + "Bonzo's Staffs:\n" + EnumChatFormatting.AQUA + "Coins Spent:\n" + EnumChatFormatting.AQUA + "Time Spent:";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2170 */           countText = EnumChatFormatting.GOLD + nf.format(LootCommand.recombobulatorsSession) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.fumingPotatoBooksSession) + "\n" + EnumChatFormatting.BLUE + nf.format(LootCommand.bonzoStaffsSession) + "\n" + EnumChatFormatting.AQUA + Utils.getMoneySpent(LootCommand.f1CoinsSpentSession) + "\n" + EnumChatFormatting.AQUA + Utils.getTimeBetween(0.0D, LootCommand.f1TimeSpentSession);
/*      */           break;
/*      */         case "catacombs_floor_two":
/* 2173 */           dropsText = EnumChatFormatting.GOLD + "Recombobulators:\n" + EnumChatFormatting.DARK_PURPLE + "Fuming Potato Books:\n" + EnumChatFormatting.BLUE + "Scarf's Studies:\n" + EnumChatFormatting.DARK_PURPLE + "Adaptive Blades:\n" + EnumChatFormatting.AQUA + "Coins Spent:\n" + EnumChatFormatting.AQUA + "Time Spent:";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2184 */           countText = EnumChatFormatting.GOLD + nf.format(LootCommand.recombobulators) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.fumingPotatoBooks) + "\n" + EnumChatFormatting.BLUE + nf.format(LootCommand.scarfStudies) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.adaptiveSwords) + "\n" + EnumChatFormatting.AQUA + Utils.getMoneySpent(LootCommand.f2CoinsSpent) + "\n" + EnumChatFormatting.AQUA + Utils.getTimeBetween(0.0D, LootCommand.f2TimeSpent);
/*      */           break;
/*      */         case "catacombs_floor_two_session":
/* 2187 */           dropsText = EnumChatFormatting.GOLD + "Recombobulators:\n" + EnumChatFormatting.DARK_PURPLE + "Fuming Potato Books:\n" + EnumChatFormatting.BLUE + "Scarf's Studies:\n" + EnumChatFormatting.DARK_PURPLE + "Adaptive Blades:\n" + EnumChatFormatting.AQUA + "Coins Spent:\n" + EnumChatFormatting.AQUA + "Time Spent:";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2198 */           countText = EnumChatFormatting.GOLD + nf.format(LootCommand.recombobulatorsSession) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.fumingPotatoBooksSession) + "\n" + EnumChatFormatting.BLUE + nf.format(LootCommand.scarfStudiesSession) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.adaptiveSwordsSession) + "\n" + EnumChatFormatting.AQUA + Utils.getMoneySpent(LootCommand.f2CoinsSpentSession) + "\n" + EnumChatFormatting.AQUA + Utils.getTimeBetween(0.0D, LootCommand.f2TimeSpentSession);
/*      */           break;
/*      */         case "catacombs_floor_three":
/* 2201 */           dropsText = EnumChatFormatting.GOLD + "Recombobulators:\n" + EnumChatFormatting.DARK_PURPLE + "Fuming Potato Books:\n" + EnumChatFormatting.DARK_PURPLE + "Adaptive Helmets:\n" + EnumChatFormatting.DARK_PURPLE + "Adaptive Chestplates:\n" + EnumChatFormatting.DARK_PURPLE + "Adaptive Leggings:\n" + EnumChatFormatting.DARK_PURPLE + "Adaptive Boots:\n" + EnumChatFormatting.AQUA + "Coins Spent:\n" + EnumChatFormatting.AQUA + "Time Spent:";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2216 */           countText = EnumChatFormatting.GOLD + nf.format(LootCommand.recombobulators) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.fumingPotatoBooks) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.adaptiveHelms) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.adaptiveChests) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.adaptiveLegs) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.adaptiveBoots) + "\n" + EnumChatFormatting.AQUA + Utils.getMoneySpent(LootCommand.f3CoinsSpent) + "\n" + EnumChatFormatting.AQUA + Utils.getTimeBetween(0.0D, LootCommand.f3TimeSpent);
/*      */           break;
/*      */         case "catacombs_floor_three_session":
/* 2219 */           dropsText = EnumChatFormatting.GOLD + "Recombobulators:\n" + EnumChatFormatting.DARK_PURPLE + "Fuming Potato Books:\n" + EnumChatFormatting.DARK_PURPLE + "Adaptive Helmets:\n" + EnumChatFormatting.DARK_PURPLE + "Adaptive Chestplates:\n" + EnumChatFormatting.DARK_PURPLE + "Adaptive Leggings:\n" + EnumChatFormatting.DARK_PURPLE + "Adaptive Boots:\n" + EnumChatFormatting.AQUA + "Coins Spent:\n" + EnumChatFormatting.AQUA + "Time Spent:";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2234 */           countText = EnumChatFormatting.GOLD + nf.format(LootCommand.recombobulatorsSession) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.fumingPotatoBooksSession) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.adaptiveHelmsSession) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.adaptiveChestsSession) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.adaptiveLegsSession) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.adaptiveBootsSession) + "\n" + EnumChatFormatting.AQUA + Utils.getMoneySpent(LootCommand.f3CoinsSpentSession) + "\n" + EnumChatFormatting.AQUA + Utils.getTimeBetween(0.0D, LootCommand.f3TimeSpentSession);
/*      */           break;
/*      */         case "catacombs_floor_four":
/* 2237 */           dropsText = EnumChatFormatting.GOLD + "Recombobulators:\n" + EnumChatFormatting.DARK_PURPLE + "Fuming Potato Books:\n" + EnumChatFormatting.DARK_PURPLE + "Spirit Wings:\n" + EnumChatFormatting.DARK_PURPLE + "Spirit Bones:\n" + EnumChatFormatting.DARK_PURPLE + "Spirit Boots:\n" + EnumChatFormatting.DARK_PURPLE + "Spirit Swords:\n" + EnumChatFormatting.GOLD + "Spirit Bows:\n" + EnumChatFormatting.DARK_PURPLE + "Epic Spirit Pets:\n" + EnumChatFormatting.GOLD + "Leg Spirit Pets:\n" + EnumChatFormatting.AQUA + "Coins Spent:\n" + EnumChatFormatting.AQUA + "Time Spent:";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2258 */           countText = EnumChatFormatting.GOLD + nf.format(LootCommand.recombobulators) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.fumingPotatoBooks) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.spiritWings) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.spiritBones) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.spiritBoots) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.spiritSwords) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.spiritBows) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.epicSpiritPets) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.legSpiritPets) + "\n" + EnumChatFormatting.AQUA + Utils.getMoneySpent(LootCommand.f4CoinsSpent) + "\n" + EnumChatFormatting.AQUA + Utils.getTimeBetween(0.0D, LootCommand.f4TimeSpent);
/*      */           break;
/*      */         case "catacombs_floor_four_session":
/* 2261 */           dropsText = EnumChatFormatting.GOLD + "Recombobulators:\n" + EnumChatFormatting.DARK_PURPLE + "Fuming Potato Books:\n" + EnumChatFormatting.DARK_PURPLE + "Spirit Wings:\n" + EnumChatFormatting.DARK_PURPLE + "Spirit Bones:\n" + EnumChatFormatting.DARK_PURPLE + "Spirit Boots:\n" + EnumChatFormatting.DARK_PURPLE + "Spirit Swords:\n" + EnumChatFormatting.GOLD + "Spirit Bows:\n" + EnumChatFormatting.DARK_PURPLE + "Epic Spirit Pets:\n" + EnumChatFormatting.GOLD + "Leg Spirit Pets:\n" + EnumChatFormatting.AQUA + "Coins Spent:\n" + EnumChatFormatting.AQUA + "Time Spent:";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2282 */           countText = EnumChatFormatting.GOLD + nf.format(LootCommand.recombobulatorsSession) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.fumingPotatoBooksSession) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.spiritWingsSession) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.spiritBonesSession) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.spiritBootsSession) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.spiritSwordsSession) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.spiritBowsSession) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.epicSpiritPetsSession) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.legSpiritPetsSession) + "\n" + EnumChatFormatting.AQUA + Utils.getMoneySpent(LootCommand.f4CoinsSpentSession) + "\n" + EnumChatFormatting.AQUA + Utils.getTimeBetween(0.0D, LootCommand.f4TimeSpentSession);
/*      */           break;
/*      */         case "catacombs_floor_five":
/* 2285 */           dropsText = EnumChatFormatting.GOLD + "Recombobulators:\n" + EnumChatFormatting.DARK_PURPLE + "Fuming Potato Books:\n" + EnumChatFormatting.BLUE + "Warped Stones:\n" + EnumChatFormatting.DARK_PURPLE + "Shadow Helmets:\n" + EnumChatFormatting.DARK_PURPLE + "Shadow Chestplates:\n" + EnumChatFormatting.DARK_PURPLE + "Shadow Leggings:\n" + EnumChatFormatting.DARK_PURPLE + "Shadow Boots:\n" + EnumChatFormatting.GOLD + "Last Breaths:\n" + EnumChatFormatting.GOLD + "Livid Daggers:\n" + EnumChatFormatting.GOLD + "Shadow Furys:\n" + EnumChatFormatting.AQUA + "Coins Spent:\n" + EnumChatFormatting.AQUA + "Time Spent:";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2308 */           countText = EnumChatFormatting.GOLD + nf.format(LootCommand.recombobulators) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.fumingPotatoBooks) + "\n" + EnumChatFormatting.BLUE + nf.format(LootCommand.warpedStones) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.shadowAssHelms) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.shadowAssChests) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.shadowAssLegs) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.shadowAssBoots) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.lastBreaths) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.lividDaggers) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.shadowFurys) + "\n" + EnumChatFormatting.AQUA + Utils.getMoneySpent(LootCommand.f5CoinsSpent) + "\n" + EnumChatFormatting.AQUA + Utils.getTimeBetween(0.0D, LootCommand.f5TimeSpent);
/*      */           break;
/*      */         case "catacombs_floor_five_session":
/* 2311 */           dropsText = EnumChatFormatting.GOLD + "Recombobulators:\n" + EnumChatFormatting.DARK_PURPLE + "Fuming Potato Books:\n" + EnumChatFormatting.BLUE + "Warped Stones:\n" + EnumChatFormatting.DARK_PURPLE + "Shadow Helmets:\n" + EnumChatFormatting.DARK_PURPLE + "Shadow Chestplates:\n" + EnumChatFormatting.DARK_PURPLE + "Shadow Leggings:\n" + EnumChatFormatting.DARK_PURPLE + "Shadow Boots:\n" + EnumChatFormatting.GOLD + "Last Breaths:\n" + EnumChatFormatting.GOLD + "Livid Daggers:\n" + EnumChatFormatting.GOLD + "Shadow Furys:\n" + EnumChatFormatting.AQUA + "Coins Spent:\n" + EnumChatFormatting.AQUA + "Time Spent:";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2334 */           countText = EnumChatFormatting.GOLD + nf.format(LootCommand.recombobulatorsSession) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.fumingPotatoBooksSession) + "\n" + EnumChatFormatting.BLUE + nf.format(LootCommand.warpedStonesSession) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.shadowAssHelmsSession) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.shadowAssChestsSession) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.shadowAssLegsSession) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.shadowAssBootsSession) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.lastBreathsSession) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.lividDaggersSession) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.shadowFurysSession) + "\n" + EnumChatFormatting.AQUA + Utils.getMoneySpent(LootCommand.f5CoinsSpentSession) + "\n" + EnumChatFormatting.AQUA + Utils.getTimeBetween(0.0D, LootCommand.f5TimeSpentSession);
/*      */           break;
/*      */         case "catacombs_floor_six":
/* 2337 */           dropsText = EnumChatFormatting.GOLD + "Recombobulators:\n" + EnumChatFormatting.DARK_PURPLE + "Fuming Potato Books:\n" + EnumChatFormatting.BLUE + "Ancient Roses:\n" + EnumChatFormatting.GOLD + "Precursor Eyes:\n" + EnumChatFormatting.GOLD + "Giant's Swords:\n" + EnumChatFormatting.GOLD + "Necro Lord Helmets:\n" + EnumChatFormatting.GOLD + "Necro Lord Chests:\n" + EnumChatFormatting.GOLD + "Necro Lord Leggings:\n" + EnumChatFormatting.GOLD + "Necro Lord Boots:\n" + EnumChatFormatting.GOLD + "Necro Swords:\n" + EnumChatFormatting.AQUA + "Coins Spent:\n" + EnumChatFormatting.AQUA + "Time Spent:";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2360 */           countText = EnumChatFormatting.GOLD + nf.format(LootCommand.recombobulators) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.fumingPotatoBooks) + "\n" + EnumChatFormatting.BLUE + nf.format(LootCommand.ancientRoses) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.precursorEyes) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.giantsSwords) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.necroLordHelms) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.necroLordChests) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.necroLordLegs) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.necroLordBoots) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.necroSwords) + "\n" + EnumChatFormatting.AQUA + Utils.getMoneySpent(LootCommand.f6CoinsSpent) + "\n" + EnumChatFormatting.AQUA + Utils.getTimeBetween(0.0D, LootCommand.f6TimeSpent);
/*      */           break;
/*      */         case "catacombs_floor_six_session":
/* 2363 */           dropsText = EnumChatFormatting.GOLD + "Recombobulators:\n" + EnumChatFormatting.DARK_PURPLE + "Fuming Potato Books:\n" + EnumChatFormatting.BLUE + "Ancient Roses:\n" + EnumChatFormatting.GOLD + "Precursor Eyes:\n" + EnumChatFormatting.GOLD + "Giant's Swords:\n" + EnumChatFormatting.GOLD + "Necro Lord Helmets:\n" + EnumChatFormatting.GOLD + "Necro Lord Chests:\n" + EnumChatFormatting.GOLD + "Necro Lord Leggings:\n" + EnumChatFormatting.GOLD + "Necro Lord Boots:\n" + EnumChatFormatting.GOLD + "Necro Swords:\n" + EnumChatFormatting.AQUA + "Coins Spent:\n" + EnumChatFormatting.AQUA + "Time Spent:";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2386 */           countText = EnumChatFormatting.GOLD + nf.format(LootCommand.recombobulatorsSession) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.fumingPotatoBooksSession) + "\n" + EnumChatFormatting.BLUE + nf.format(LootCommand.ancientRosesSession) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.precursorEyesSession) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.giantsSwordsSession) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.necroLordHelmsSession) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.necroLordChestsSession) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.necroLordLegsSession) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.necroLordBootsSession) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.necroSwordsSession) + "\n" + EnumChatFormatting.AQUA + Utils.getMoneySpent(LootCommand.f6CoinsSpentSession) + "\n" + EnumChatFormatting.AQUA + Utils.getTimeBetween(0.0D, LootCommand.f6TimeSpentSession);
/*      */           break;
/*      */         case "catacombs_floor_seven":
/* 2389 */           dropsText = EnumChatFormatting.GOLD + "Recombobulators:\n" + EnumChatFormatting.DARK_PURPLE + "Fuming Potato Books:\n" + EnumChatFormatting.DARK_PURPLE + "Wither Bloods:\n" + EnumChatFormatting.DARK_PURPLE + "Wither Cloaks:\n" + EnumChatFormatting.DARK_PURPLE + "Implosions:\n" + EnumChatFormatting.DARK_PURPLE + "Wither Shields:\n" + EnumChatFormatting.DARK_PURPLE + "Shadow Warps:\n" + EnumChatFormatting.DARK_PURPLE + "Necron's Handles:\n" + EnumChatFormatting.GOLD + "Auto Recombobs:\n" + EnumChatFormatting.GOLD + "Wither Helmets:\n" + EnumChatFormatting.GOLD + "Wither Chests:\n" + EnumChatFormatting.GOLD + "Wither Leggings:\n" + EnumChatFormatting.GOLD + "Wither Boots:\n" + EnumChatFormatting.AQUA + "Coins Spent:\n" + EnumChatFormatting.AQUA + "Time Spent:";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2418 */           countText = EnumChatFormatting.GOLD + nf.format(LootCommand.recombobulators) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.fumingPotatoBooks) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.witherBloods) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.witherCloaks) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.implosions) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.witherShields) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.shadowWarps) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.necronsHandles) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.autoRecombs) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.witherHelms) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.witherChests) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.witherLegs) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.witherBoots) + "\n" + EnumChatFormatting.AQUA + Utils.getMoneySpent(LootCommand.f7CoinsSpent) + "\n" + EnumChatFormatting.AQUA + Utils.getTimeBetween(0.0D, LootCommand.f7TimeSpent);
/*      */           break;
/*      */         case "catacombs_floor_seven_session":
/* 2421 */           dropsText = EnumChatFormatting.GOLD + "Recombobulators:\n" + EnumChatFormatting.DARK_PURPLE + "Fuming Potato Books:\n" + EnumChatFormatting.DARK_PURPLE + "Wither Bloods:\n" + EnumChatFormatting.DARK_PURPLE + "Wither Cloaks:\n" + EnumChatFormatting.DARK_PURPLE + "Implosions:\n" + EnumChatFormatting.DARK_PURPLE + "Wither Shields:\n" + EnumChatFormatting.DARK_PURPLE + "Shadow Warps:\n" + EnumChatFormatting.DARK_PURPLE + "Necron's Handles:\n" + EnumChatFormatting.GOLD + "Auto Recombobulators:\n" + EnumChatFormatting.GOLD + "Wither Helmets:\n" + EnumChatFormatting.GOLD + "Wither Chests:\n" + EnumChatFormatting.GOLD + "Wither Leggings:\n" + EnumChatFormatting.GOLD + "Wither Boots:\n" + EnumChatFormatting.AQUA + "Coins Spent:\n" + EnumChatFormatting.AQUA + "Time Spent:";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2450 */           countText = EnumChatFormatting.GOLD + nf.format(LootCommand.recombobulatorsSession) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.fumingPotatoBooksSession) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.witherBloodsSession) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.witherCloaksSession) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.implosionsSession) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.witherShieldsSession) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.shadowWarpsSession) + "\n" + EnumChatFormatting.DARK_PURPLE + nf.format(LootCommand.necronsHandlesSession) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.autoRecombsSession) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.witherHelmsSession) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.witherChestsSession) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.witherLegsSession) + "\n" + EnumChatFormatting.GOLD + nf.format(LootCommand.witherBootsSession) + "\n" + EnumChatFormatting.AQUA + Utils.getMoneySpent(LootCommand.f7CoinsSpentSession) + "\n" + EnumChatFormatting.AQUA + Utils.getTimeBetween(0.0D, LootCommand.f7TimeSpentSession);
/*      */           break;
/*      */         default:
/* 2453 */           System.out.println("Display was an unknown value, turning off.");
/* 2454 */           DisplayCommand.display = "off";
/* 2455 */           ConfigHandler.writeStringConfig("misc", "display", "off"); break;
/*      */       } 
/* 2457 */       new TextRenderer(mc, dropsText, MoveCommand.displayXY[0], MoveCommand.displayXY[1], ScaleCommand.displayScale);
/* 2458 */       new TextRenderer(mc, countText, (int)(MoveCommand.displayXY[0] + 110.0D * ScaleCommand.displayScale), MoveCommand.displayXY[1], ScaleCommand.displayScale);
/*      */     } 
/*      */     
/* 2461 */     if (showTitle) {
/* 2462 */       Utils.drawTitle(titleText);
/*      */     }
/* 2464 */     if (showSkill) {
/* 2465 */       new TextRenderer(mc, skillText, MoveCommand.skill50XY[0], MoveCommand.skill50XY[1], ScaleCommand.skill50Scale);
/*      */     }
/*      */   }
/*      */   
/*      */   @SubscribeEvent(priority = EventPriority.LOW)
/*      */   public void onTooltipLow(ItemTooltipEvent event) {
/* 2471 */     if (event.toolTip == null)
/*      */       return; 
/* 2473 */     Minecraft mc = Minecraft.func_71410_x();
/* 2474 */     EntityPlayerSP player = mc.field_71439_g;
/*      */     
/* 2476 */     if (mc.field_71462_r instanceof GuiChest) {
/* 2477 */       ContainerChest chest = (ContainerChest)player.field_71070_bA;
/* 2478 */       IInventory inv = chest.func_85151_d();
/* 2479 */       String chestName = inv.func_145748_c_().func_150260_c();
/*      */       
/* 2481 */       if (ToggleCommand.hideTooltipsInExperimentAddonsToggled && (chestName.startsWith("Ultrasequencer (") || chestName.startsWith("Chronomatron ("))) {
/* 2482 */         event.toolTip.clear();
/*      */       }
/*      */       
/* 2485 */       if (ToggleCommand.clickInOrderToggled && chestName.equals("Click in order!")) {
/* 2486 */         event.toolTip.clear();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   @SubscribeEvent
/*      */   public void onTick(TickEvent.ClientTickEvent event) {
/* 2494 */     if (event.phase != TickEvent.Phase.START)
/*      */       return; 
/* 2496 */     Minecraft mc = Minecraft.func_71410_x();
/* 2497 */     WorldClient worldClient = mc.field_71441_e;
/* 2498 */     EntityPlayerSP player = mc.field_71439_g;
/*      */     
/* 2500 */     if (mc.field_71462_r == null && System.currentTimeMillis() - lastInteractTime >= 250L) {
/* 2501 */       slotIn = -1;
/* 2502 */       lastSlot = -1;
/* 2503 */       this.mazeId = 0;
/*      */     } 
/* 2505 */     if (keyBindings[6].func_151470_d())
/* 2506 */       for (int i = 0; i <= 8; i++) {
/* 2507 */         ItemStack item = player.field_71071_by.func_70301_a(i);
/* 2508 */         if ((item != null && item.func_82833_r().contains("Hyperion")) || (item != null && item.func_82833_r().contains("Aspect of the End"))) {
/* 2509 */           player.field_71071_by.field_70461_c = i;
/* 2510 */           mc.field_71442_b.func_78769_a((EntityPlayer)mc.field_71439_g, (World)worldClient, player.field_71071_by.func_70301_a(i));
/*      */           break;
/*      */         } 
/*      */       }  
/* 2514 */     if (keyBindings[7].func_151470_d()) {
/* 2515 */       if (mc.field_71476_x.func_178782_a() == null)
/* 2516 */         return;  Block block = (Minecraft.func_71410_x()).field_71441_e.func_180495_p(mc.field_71476_x.func_178782_a()).func_177230_c();
/* 2517 */       ArrayList<Block> interactables = new ArrayList<>(Arrays.asList(new Block[] { Blocks.field_180410_as, Blocks.field_150467_bQ, (Block)Blocks.field_150461_bJ, Blocks.field_150324_C, Blocks.field_180412_aq, Blocks.field_150382_bo, Blocks.field_150483_bI, Blocks.field_150462_ai, (Block)Blocks.field_150486_ae, Blocks.field_180409_at, (Block)Blocks.field_150453_bW, (Block)Blocks.field_180402_cm, Blocks.field_150367_z, Blocks.field_150409_cd, Blocks.field_150381_bn, Blocks.field_150477_bB, Blocks.field_150460_al, (Block)Blocks.field_150438_bZ, Blocks.field_180411_ar, Blocks.field_150442_at, Blocks.field_150323_B, (Block)Blocks.field_150455_bV, (Block)Blocks.field_150441_bU, (Block)Blocks.field_150416_aS, (Block)Blocks.field_150413_aR, Blocks.field_150472_an, Blocks.field_150444_as, Blocks.field_150415_aT, Blocks.field_150447_bR, Blocks.field_150471_bO, Blocks.field_150430_aB, Blocks.field_180413_ao, (Block)Blocks.field_150465_bP }));
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2522 */       if (!interactables.contains(block)) {
/* 2523 */         worldClient.func_175698_g(mc.field_71476_x.func_178782_a());
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 2528 */     tickAmount++;
/* 2529 */     if (tickAmount % 20 == 0) {
/* 2530 */       if (player != null) {
/* 2531 */         Utils.checkForSkyblock();
/* 2532 */         Utils.checkForDungeons();
/*      */       } 
/*      */       
/* 2535 */       if (DisplayCommand.auto && worldClient != null && player != null) {
/* 2536 */         List<String> scoreboard = ScoreboardHandler.getSidebarLines();
/* 2537 */         boolean found = false;
/* 2538 */         for (String s : scoreboard) {
/* 2539 */           String sCleaned = ScoreboardHandler.cleanSB(s);
/* 2540 */           if (sCleaned.contains("Sven Packmaster")) {
/* 2541 */             DisplayCommand.display = "wolf";
/* 2542 */             found = true; continue;
/* 2543 */           }  if (sCleaned.contains("Tarantula Broodfather")) {
/* 2544 */             DisplayCommand.display = "spider";
/* 2545 */             found = true; continue;
/* 2546 */           }  if (sCleaned.contains("Revenant Horror")) {
/* 2547 */             DisplayCommand.display = "zombie";
/* 2548 */             found = true; continue;
/* 2549 */           }  if (sCleaned.contains("The Catacombs (")) {
/* 2550 */             if (sCleaned.contains("F1")) {
/* 2551 */               DisplayCommand.display = "catacombs_floor_one";
/* 2552 */             } else if (sCleaned.contains("F2")) {
/* 2553 */               DisplayCommand.display = "catacombs_floor_two";
/* 2554 */             } else if (sCleaned.contains("F3")) {
/* 2555 */               DisplayCommand.display = "catacombs_floor_three";
/* 2556 */             } else if (sCleaned.contains("F4")) {
/* 2557 */               DisplayCommand.display = "catacombs_floor_four";
/* 2558 */             } else if (sCleaned.contains("F5")) {
/* 2559 */               DisplayCommand.display = "catacombs_floor_five";
/* 2560 */             } else if (sCleaned.contains("F6")) {
/* 2561 */               DisplayCommand.display = "catacombs_floor_six";
/* 2562 */             } else if (sCleaned.contains("F7")) {
/* 2563 */               DisplayCommand.display = "catacombs_floor_seven";
/*      */             } 
/* 2565 */             found = true;
/*      */           } 
/*      */         } 
/* 2568 */         for (int i = 0; i < 8; i++) {
/* 2569 */           ItemStack hotbarItem = player.field_71071_by.func_70301_a(i);
/* 2570 */           if (hotbarItem != null && 
/* 2571 */             hotbarItem.func_82833_r().contains("Ancestral Spade")) {
/* 2572 */             DisplayCommand.display = "mythological";
/* 2573 */             found = true;
/*      */           } 
/*      */         } 
/* 2576 */         if (!found) DisplayCommand.display = "off"; 
/* 2577 */         ConfigHandler.writeStringConfig("misc", "display", DisplayCommand.display);
/*      */       } 
/*      */       
/* 2580 */       if (ToggleCommand.creeperToggled && Utils.inDungeons && worldClient != null && player != null) {
/* 2581 */         double x = player.field_70165_t;
/* 2582 */         double y = player.field_70163_u;
/* 2583 */         double z = player.field_70161_v;
/*      */         
/* 2585 */         AxisAlignedBB creeperScan = new AxisAlignedBB(x - 14.0D, y - 8.0D, z - 13.0D, x + 14.0D, y + 8.0D, z + 13.0D);
/* 2586 */         List<EntityCreeper> creepers = worldClient.func_72872_a(EntityCreeper.class, creeperScan);
/*      */         
/* 2588 */         if (creepers.size() > 0 && !((EntityCreeper)creepers.get(0)).func_82150_aj()) {
/* 2589 */           EntityCreeper creeper = creepers.get(0);
/*      */           
/* 2591 */           creeperLines.clear();
/* 2592 */           if (!drawCreeperLines) creeperLocation = new Vec3(creeper.field_70165_t, creeper.field_70163_u + 1.0D, creeper.field_70161_v); 
/* 2593 */           drawCreeperLines = true;
/*      */           
/* 2595 */           BlockPos point1 = new BlockPos(creeper.field_70165_t - 14.0D, creeper.field_70163_u - 7.0D, creeper.field_70161_v - 13.0D);
/* 2596 */           BlockPos point2 = new BlockPos(creeper.field_70165_t + 14.0D, creeper.field_70163_u + 10.0D, creeper.field_70161_v + 13.0D);
/* 2597 */           Iterable<BlockPos> blocks = BlockPos.func_177980_a(point1, point2);
/* 2598 */           for (BlockPos blockPos : blocks) {
/* 2599 */             Block block = worldClient.func_180495_p(blockPos).func_177230_c();
/* 2600 */             if (block == Blocks.field_180398_cJ || block == Blocks.field_180397_cI) {
/*      */               
/* 2602 */               Vec3 startBlock = new Vec3(blockPos.func_177958_n() + 0.5D, blockPos.func_177956_o() + 0.5D, blockPos.func_177952_p() + 0.5D);
/* 2603 */               BlockPos oppositeBlock = Utils.getFirstBlockPosAfterVectors(mc, startBlock, creeperLocation, 10, 20);
/* 2604 */               BlockPos endBlock = Utils.getNearbyBlock(mc, oppositeBlock, new Block[] { Blocks.field_180398_cJ, Blocks.field_180397_cI });
/* 2605 */               if (endBlock != null && startBlock.field_72448_b > 68.0D && endBlock.func_177956_o() > 68) {
/*      */                 
/* 2607 */                 Vec3[] insertArray = { startBlock, new Vec3(endBlock.func_177958_n() + 0.5D, endBlock.func_177956_o() + 0.5D, endBlock.func_177952_p() + 0.5D) };
/* 2608 */                 creeperLines.add(insertArray);
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } else {
/* 2613 */           drawCreeperLines = false;
/*      */         } 
/*      */       } 
/*      */       
/* 2617 */       if (ToggleCommand.waterToggled && Utils.inDungeons && worldClient != null && player != null)
/*      */       {
/* 2619 */         (new Thread(() -> {
/*      */               prevInWaterRoom = inWaterRoom;
/*      */               inWaterRoom = false;
/*      */               boolean foundPiston = false;
/*      */               boolean done = false;
/*      */               int x;
/*      */               for (x = (int)(player.field_70165_t - 13.0D); x <= player.field_70165_t + 13.0D; x++) {
/*      */                 for (int z = (int)(player.field_70161_v - 13.0D); z <= player.field_70161_v + 13.0D; z++) {
/*      */                   BlockPos blockPos = new BlockPos(x, 54, z);
/*      */                   if (world.func_180495_p(blockPos).func_177230_c() == Blocks.field_150320_F) {
/*      */                     foundPiston = true;
/*      */                     break;
/*      */                   } 
/*      */                 } 
/*      */                 if (foundPiston) {
/*      */                   break;
/*      */                 }
/*      */               } 
/*      */               if (foundPiston) {
/*      */                 for (x = (int)(player.field_70165_t - 25.0D); x <= player.field_70165_t + 25.0D; x++) {
/*      */                   for (int z = (int)(player.field_70161_v - 25.0D); z <= player.field_70161_v + 25.0D; z++) {
/*      */                     BlockPos blockPos = new BlockPos(x, 82, z);
/*      */                     if (world.func_180495_p(blockPos).func_177230_c() == Blocks.field_150332_K) {
/*      */                       inWaterRoom = true;
/*      */                       if (!prevInWaterRoom) {
/*      */                         String purple;
/*      */                         String orange;
/*      */                         String blue;
/*      */                         String green;
/*      */                         String red;
/*      */                         boolean foundGold = false;
/*      */                         boolean foundClay = false;
/*      */                         boolean foundEmerald = false;
/*      */                         boolean foundQuartz = false;
/*      */                         boolean foundDiamond = false;
/*      */                         BlockPos scan1 = new BlockPos(x + 1, 78, z + 1);
/*      */                         BlockPos scan2 = new BlockPos(x - 1, 77, z - 1);
/*      */                         Iterable<BlockPos> blocks = BlockPos.func_177980_a(scan1, scan2);
/*      */                         for (BlockPos puzzleBlockPos : blocks) {
/*      */                           Block block = world.func_180495_p(puzzleBlockPos).func_177230_c();
/*      */                           if (block == Blocks.field_150340_R) {
/*      */                             foundGold = true;
/*      */                             continue;
/*      */                           } 
/*      */                           if (block == Blocks.field_150405_ch) {
/*      */                             foundClay = true;
/*      */                             continue;
/*      */                           } 
/*      */                           if (block == Blocks.field_150475_bE) {
/*      */                             foundEmerald = true;
/*      */                             continue;
/*      */                           } 
/*      */                           if (block == Blocks.field_150371_ca) {
/*      */                             foundQuartz = true;
/*      */                             continue;
/*      */                           } 
/*      */                           if (block == Blocks.field_150484_ah)
/*      */                             foundDiamond = true; 
/*      */                         } 
/*      */                         int variant = 0;
/*      */                         if (foundGold && foundClay) {
/*      */                           variant = 1;
/*      */                         } else if (foundEmerald && foundQuartz) {
/*      */                           variant = 2;
/*      */                         } else if (foundQuartz && foundDiamond) {
/*      */                           variant = 3;
/*      */                         } else if (foundGold && foundQuartz) {
/*      */                           variant = 4;
/*      */                         } 
/*      */                         switch (variant) {
/*      */                           case 1:
/*      */                             purple = EnumChatFormatting.WHITE + "Quartz, " + EnumChatFormatting.YELLOW + "Gold, " + EnumChatFormatting.AQUA + "Diamond, " + EnumChatFormatting.RED + "Clay";
/*      */                             orange = EnumChatFormatting.YELLOW + "Gold, " + EnumChatFormatting.DARK_GRAY + "Coal, " + EnumChatFormatting.GREEN + "Emerald";
/*      */                             blue = EnumChatFormatting.WHITE + "Quartz, " + EnumChatFormatting.YELLOW + "Gold, " + EnumChatFormatting.GREEN + "Emerald, " + EnumChatFormatting.RED + "Clay";
/*      */                             green = EnumChatFormatting.GREEN + "Emerald";
/*      */                             red = EnumChatFormatting.GRAY + "None";
/*      */                             break;
/*      */                           case 2:
/*      */                             purple = EnumChatFormatting.DARK_GRAY + "Coal";
/*      */                             orange = EnumChatFormatting.WHITE + "Quartz, " + EnumChatFormatting.YELLOW + "Gold, " + EnumChatFormatting.GREEN + "Emerald, " + EnumChatFormatting.RED + "Clay";
/*      */                             blue = EnumChatFormatting.WHITE + "Quartz, " + EnumChatFormatting.AQUA + "Diamond, " + EnumChatFormatting.GREEN + "Emerald";
/*      */                             green = EnumChatFormatting.WHITE + "Quartz, " + EnumChatFormatting.GREEN + "Emerald";
/*      */                             red = EnumChatFormatting.WHITE + "Quartz, " + EnumChatFormatting.DARK_GRAY + "Coal, " + EnumChatFormatting.GREEN + "Emerald";
/*      */                             break;
/*      */                           case 3:
/*      */                             purple = EnumChatFormatting.WHITE + "Quartz, " + EnumChatFormatting.YELLOW + "Gold, " + EnumChatFormatting.AQUA + "Diamond";
/*      */                             orange = EnumChatFormatting.GREEN + "Emerald";
/*      */                             blue = EnumChatFormatting.WHITE + "Quartz, " + EnumChatFormatting.AQUA + "Diamond";
/*      */                             green = EnumChatFormatting.GRAY + "None";
/*      */                             red = EnumChatFormatting.YELLOW + "Gold, " + EnumChatFormatting.GREEN + "Emerald";
/*      */                             break;
/*      */                           case 4:
/*      */                             purple = EnumChatFormatting.WHITE + "Quartz, " + EnumChatFormatting.YELLOW + "Gold, " + EnumChatFormatting.GREEN + "Emerald, " + EnumChatFormatting.RED + "Clay";
/*      */                             orange = EnumChatFormatting.YELLOW + "Gold, " + EnumChatFormatting.DARK_GRAY + "Coal";
/*      */                             blue = EnumChatFormatting.WHITE + "Quartz, " + EnumChatFormatting.YELLOW + "Gold, " + EnumChatFormatting.DARK_GRAY + "Coal, " + EnumChatFormatting.GREEN + "Emerald, " + EnumChatFormatting.RED + "Clay";
/*      */                             green = EnumChatFormatting.YELLOW + "Gold, " + EnumChatFormatting.GREEN + "Emerald";
/*      */                             red = EnumChatFormatting.YELLOW + "Gold, " + EnumChatFormatting.AQUA + "Diamond, " + EnumChatFormatting.GREEN + "Emerald, " + EnumChatFormatting.RED + "Clay";
/*      */                             break;
/*      */                           default:
/*      */                             purple = orange = blue = green = red = ERROR_COLOUR + "Error detecting water puzzle variant.";
/*      */                             break;
/*      */                         } 
/*      */                         waterAnswers = MAIN_COLOUR + "The following levers must be down:\n" + EnumChatFormatting.DARK_PURPLE + "Purple: " + purple + "\n" + EnumChatFormatting.GOLD + "Orange: " + orange + "\n" + EnumChatFormatting.BLUE + "Blue: " + blue + "\n" + EnumChatFormatting.GREEN + "Green: " + green + "\n" + EnumChatFormatting.RED + "Red: " + red;
/*      */                         done = true;
/*      */                         break;
/*      */                       } 
/*      */                     } 
/*      */                   } 
/*      */                   if (done)
/*      */                     break; 
/*      */                 } 
/*      */               } else {
/*      */                 waterAnswers = null;
/*      */               } 
/* 2733 */             })).start();
/*      */       }
/*      */       
/* 2736 */       if (ToggleCommand.lividSolverToggled && Utils.inDungeons && !foundLivid && worldClient != null) {
/* 2737 */         boolean inF5 = false;
/*      */         
/* 2739 */         List<String> scoreboard = ScoreboardHandler.getSidebarLines();
/* 2740 */         for (String s : scoreboard) {
/* 2741 */           String sCleaned = ScoreboardHandler.cleanSB(s);
/* 2742 */           if (sCleaned.contains("The Catacombs (F5)")) {
/* 2743 */             inF5 = true;
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/* 2748 */         if (inF5) {
/* 2749 */           List<Entity> loadedLivids = new ArrayList<>();
/* 2750 */           List<Entity> entities = worldClient.func_72910_y();
/* 2751 */           for (Entity entity : entities) {
/* 2752 */             String name = entity.func_70005_c_();
/* 2753 */             if (name.contains("Livid") && name.length() > 5 && name.charAt(1) == name.charAt(5) && !loadedLivids.contains(entity)) {
/* 2754 */               loadedLivids.add(entity);
/*      */             }
/*      */           } 
/* 2757 */           if (loadedLivids.size() > 8) {
/* 2758 */             livid = loadedLivids.get(0);
/* 2759 */             foundLivid = true;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 2764 */       if (ToggleCommand.ticTacToeToggled && Utils.inDungeons && worldClient != null && player != null) {
/* 2765 */         correctTicTacToeButton = null;
/* 2766 */         AxisAlignedBB aabb = new AxisAlignedBB(player.field_70165_t - 6.0D, player.field_70163_u - 6.0D, player.field_70161_v - 6.0D, player.field_70165_t + 6.0D, player.field_70163_u + 6.0D, player.field_70161_v + 6.0D);
/* 2767 */         List<EntityItemFrame> itemFrames = worldClient.func_72872_a(EntityItemFrame.class, aabb);
/* 2768 */         List<EntityItemFrame> itemFramesWithMaps = new ArrayList<>();
/*      */         
/* 2770 */         for (EntityItemFrame itemFrame : itemFrames) {
/* 2771 */           ItemStack item = itemFrame.func_82335_i();
/* 2772 */           if (item == null || !(item.func_77973_b() instanceof ItemMap))
/* 2773 */             continue;  MapData mapData = ((ItemMap)item.func_77973_b()).func_77873_a(item, (World)worldClient);
/* 2774 */           if (mapData == null)
/*      */             continue; 
/* 2776 */           itemFramesWithMaps.add(itemFrame);
/*      */         } 
/*      */ 
/*      */         
/* 2780 */         if (itemFramesWithMaps.size() != 9 && itemFramesWithMaps.size() % 2 == 1) {
/* 2781 */           char[][] board = new char[3][3];
/* 2782 */           BlockPos leftmostRow = null;
/* 2783 */           int sign = 1;
/* 2784 */           char facing = 'X';
/* 2785 */           for (EntityItemFrame itemFrame : itemFramesWithMaps) {
/* 2786 */             int column; ItemStack map = itemFrame.func_82335_i();
/* 2787 */             MapData mapData = ((ItemMap)map.func_77973_b()).func_77873_a(map, (World)worldClient);
/*      */ 
/*      */             
/* 2790 */             int row = 0;
/*      */             
/* 2792 */             sign = 1;
/*      */             
/* 2794 */             if (itemFrame.field_174860_b == EnumFacing.SOUTH || itemFrame.field_174860_b == EnumFacing.WEST) {
/* 2795 */               sign = -1;
/*      */             }
/*      */             
/* 2798 */             BlockPos itemFramePos = new BlockPos(itemFrame.field_70165_t, Math.floor(itemFrame.field_70163_u), itemFrame.field_70161_v);
/* 2799 */             for (int i = 2; i >= 0; i--) {
/* 2800 */               int realI = i * sign;
/* 2801 */               BlockPos blockPos = itemFramePos;
/* 2802 */               if (itemFrame.field_70165_t % 0.5D == 0.0D) {
/* 2803 */                 blockPos = itemFramePos.func_177982_a(realI, 0, 0);
/* 2804 */               } else if (itemFrame.field_70161_v % 0.5D == 0.0D) {
/* 2805 */                 blockPos = itemFramePos.func_177982_a(0, 0, realI);
/* 2806 */                 facing = 'Z';
/*      */               } 
/* 2808 */               Block block = worldClient.func_180495_p(blockPos).func_177230_c();
/* 2809 */               if (block == Blocks.field_150350_a || block == Blocks.field_150430_aB) {
/* 2810 */                 leftmostRow = blockPos;
/* 2811 */                 row = i;
/*      */                 
/*      */                 break;
/*      */               } 
/*      */             } 
/* 2816 */             if (itemFrame.field_70163_u == 72.5D) {
/* 2817 */               column = 0;
/* 2818 */             } else if (itemFrame.field_70163_u == 71.5D) {
/* 2819 */               column = 1;
/* 2820 */             } else if (itemFrame.field_70163_u == 70.5D) {
/* 2821 */               column = 2;
/*      */             } else {
/*      */               continue;
/*      */             } 
/*      */ 
/*      */ 
/*      */             
/* 2828 */             int colourInt = mapData.field_76198_e[8256] & 0xFF;
/* 2829 */             if (colourInt == 114) {
/* 2830 */               board[column][row] = 'X'; continue;
/* 2831 */             }  if (colourInt == 33) {
/* 2832 */               board[column][row] = 'O';
/*      */             }
/*      */           } 
/* 2835 */           System.out.println("Board: " + Arrays.deepToString((Object[])board));
/*      */ 
/*      */           
/* 2838 */           int bestMove = TicTacToeUtils.getBestMove(board) - 1;
/* 2839 */           System.out.println("Best move slot: " + bestMove);
/* 2840 */           if (leftmostRow != null) {
/* 2841 */             double drawX = (facing == 'X') ? (leftmostRow.func_177958_n() - sign * bestMove % 3) : leftmostRow.func_177958_n();
/* 2842 */             double drawY = 72.0D - Math.floor((bestMove / 3));
/* 2843 */             double drawZ = (facing == 'Z') ? (leftmostRow.func_177952_p() - sign * bestMove % 3) : leftmostRow.func_177952_p();
/*      */             
/* 2845 */             correctTicTacToeButton = new AxisAlignedBB(drawX, drawY, drawZ, drawX + 1.0D, drawY + 1.0D, drawZ + 1.0D);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 2850 */       tickAmount = 0;
/*      */     } 
/*      */ 
/*      */     
/* 2854 */     if (tickAmount % 4 == 0 && 
/* 2855 */       ToggleCommand.blazeToggled && Utils.inDungeons && worldClient != null) {
/* 2856 */       List<Entity> entities = worldClient.func_72910_y();
/* 2857 */       int highestHealth = 0;
/* 2858 */       highestBlaze = null;
/* 2859 */       int lowestHealth = 99999999;
/* 2860 */       lowestBlaze = null;
/*      */       
/* 2862 */       for (Entity entity : entities) {
/* 2863 */         if (entity.func_70005_c_().contains("Blaze") && entity.func_70005_c_().contains("/")) {
/* 2864 */           String blazeName = StringUtils.func_76338_a(entity.func_70005_c_());
/*      */           try {
/* 2866 */             int health = Integer.parseInt(blazeName.substring(blazeName.indexOf("/") + 1, blazeName.length() - 1));
/* 2867 */             if (health > highestHealth) {
/* 2868 */               highestHealth = health;
/* 2869 */               highestBlaze = entity;
/*      */             } 
/* 2871 */             if (health < lowestHealth) {
/* 2872 */               lowestHealth = health;
/* 2873 */               lowestBlaze = entity;
/*      */             } 
/* 2875 */           } catch (NumberFormatException ex) {
/* 2876 */             ex.printStackTrace();
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2884 */     if (tickAmount % 2 == 0 && 
/* 2885 */       ToggleCommand.lowHealthNotifyToggled && Utils.inDungeons && worldClient != null) {
/* 2886 */       List<String> scoreboard = ScoreboardHandler.getSidebarLines();
/* 2887 */       for (String score : scoreboard) {
/* 2888 */         if (score.endsWith("❤") && score.matches(".*§c\\d.*")) {
/* 2889 */           String name = score.substring(score.indexOf(" ") + 1);
/* 2890 */           Utils.createTitle(EnumChatFormatting.RED + "LOW HEALTH!\n" + name, 1);
/*      */ 
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 2898 */     if (mc.field_71462_r instanceof GuiChest) {
/* 2899 */       if (player == null)
/* 2900 */         return;  ContainerChest chest = (ContainerChest)player.field_71070_bA;
/* 2901 */       List<Slot> invSlots = ((GuiChest)mc.field_71462_r).field_147002_h.field_75151_b;
/* 2902 */       String chestName = chest.func_85151_d().func_145748_c_().func_150260_c().trim();
/*      */       
/* 2904 */       if (ToggleCommand.ultrasequencerToggled && chestName.startsWith("Ultrasequencer (") && (
/* 2905 */         (Slot)invSlots.get(49)).func_75211_c() != null && ((Slot)invSlots.get(49)).func_75211_c().func_82833_r().equals("§aRemember the pattern!")) {
/* 2906 */         for (int i = 9; i <= 44; i++) {
/* 2907 */           if (invSlots.get(i) != null && ((Slot)invSlots.get(i)).func_75211_c() != null) {
/* 2908 */             String itemName = StringUtils.func_76338_a(((Slot)invSlots.get(i)).func_75211_c().func_82833_r());
/* 2909 */             if (itemName.matches("\\d+")) {
/* 2910 */               int number = Integer.parseInt(itemName);
/* 2911 */               clickInOrderSlots[number - 1] = invSlots.get(i);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       }
/*      */       
/* 2917 */       if (chestName.equals("Click in order!") && until == 100000) {
/* 2918 */         for (int i = 10; i <= 25; i++) {
/* 2919 */           if (i != 17 && i != 18) {
/* 2920 */             Container container = mc.field_71439_g.field_71070_bA;
/* 2921 */             IInventory chestInventory = ((ContainerChest)container).func_85151_d();
/* 2922 */             ItemStack click = chestInventory.func_70301_a(i);
/* 2923 */             if (click != null) {
/* 2924 */               if (click.func_77952_i() != 14)
/* 2925 */                 break;  if ((chestInventory.func_70301_a(i)).field_77994_a == 1 && terminalNumberNeeded[0] == 0) {
/* 2926 */                 terminalNumberNeeded[0] = i;
/*      */               }
/* 2928 */               if (terminalNumberNeeded[0] != 0 || terminalNumberNeeded[1] != 0) {
/* 2929 */                 for (int j = 1; j < terminalNumberNeeded.length; j++) {
/* 2930 */                   ItemStack prevClick = chestInventory.func_70301_a(terminalNumberNeeded[j - 1]);
/* 2931 */                   if (prevClick != null) {
/* 2932 */                     if (prevClick.func_77952_i() != 14)
/*      */                       break; 
/* 2934 */                     if (terminalNumberNeeded[j] == 0 && click.field_77994_a - (chestInventory.func_70301_a(terminalNumberNeeded[j - 1])).field_77994_a == 1) {
/* 2935 */                       terminalNumberNeeded[j] = i;
/*      */                       break;
/*      */                     } 
/*      */                   } 
/*      */                 } 
/*      */               }
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       }
/* 2945 */       if (ToggleCommand.superpairsToggled && chestName.startsWith("Superpairs ("))
/* 2946 */         for (int i = 0; i < 53; i++) {
/* 2947 */           ItemStack itemStack = ((Slot)invSlots.get(i)).func_75211_c();
/* 2948 */           if (itemStack != null) {
/* 2949 */             String itemName = itemStack.func_82833_r();
/* 2950 */             if (Item.func_150891_b(itemStack.func_77973_b()) != 95 && Item.func_150891_b(itemStack.func_77973_b()) != 160 && 
/* 2951 */               !itemName.contains("Instant Find") && !itemName.contains("Gained +")) {
/* 2952 */               if (itemName.contains("Enchanted Book")) {
/* 2953 */                 itemName = itemStack.func_82840_a((EntityPlayer)mc.field_71439_g, false).get(3);
/*      */               }
/* 2955 */               if (itemStack.field_77994_a > 1) {
/* 2956 */                 itemName = itemStack.field_77994_a + " " + itemName;
/*      */               }
/* 2958 */               if (experimentTableSlots[i] == null)
/* 2959 */                 experimentTableSlots[i] = itemStack.func_77946_l().func_151001_c(itemName); 
/*      */             } 
/*      */           } 
/*      */         }  
/*      */     } 
/* 2964 */     if (titleTimer >= 0) {
/* 2965 */       if (titleTimer == 0) {
/* 2966 */         showTitle = false;
/*      */       }
/* 2968 */       titleTimer--;
/*      */     } 
/* 2970 */     if (skillTimer >= 0) {
/* 2971 */       if (skillTimer == 0) {
/* 2972 */         showSkill = false;
/*      */       }
/* 2974 */       skillTimer--;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   @SubscribeEvent
/*      */   public void onRenderTick(TickEvent.RenderTickEvent event) {
/* 2981 */     if (guiToOpen != null) {
/* 2982 */       Minecraft mc = Minecraft.func_71410_x();
/* 2983 */       if (guiToOpen.startsWith("dankergui")) {
/* 2984 */         int page = Character.getNumericValue(guiToOpen.charAt(guiToOpen.length() - 1));
/* 2985 */         mc.func_147108_a((GuiScreen)new DankerGui(page));
/*      */       } else {
/* 2987 */         switch (guiToOpen) {
/*      */           case "displaygui":
/* 2989 */             mc.func_147108_a((GuiScreen)new DisplayGui());
/*      */             break;
/*      */           case "onlyslayergui":
/* 2992 */             mc.func_147108_a((GuiScreen)new OnlySlayerGui());
/*      */             break;
/*      */           case "editlocations":
/* 2995 */             mc.func_147108_a((GuiScreen)new EditLocationsGui());
/*      */             break;
/*      */           case "puzzlesolvers":
/* 2998 */             mc.func_147108_a((GuiScreen)new PuzzleSolversGui(1));
/*      */             break;
/*      */           case "experimentsolvers":
/* 3001 */             mc.func_147108_a((GuiScreen)new ExperimentsGui());
/*      */             break;
/*      */           case "skilltracker":
/* 3004 */             mc.func_147108_a((GuiScreen)new SkillTrackerGui());
/*      */             break;
/*      */         } 
/*      */       } 
/* 3008 */       guiToOpen = null;
/*      */     } 
/*      */   }
/*      */   
/*      */   @SubscribeEvent
/*      */   public void onWorldRender(RenderWorldLastEvent event) {
/* 3014 */     if (ToggleCommand.blazeToggled) {
/* 3015 */       if (lowestBlaze != null) {
/* 3016 */         BlockPos stringPos = new BlockPos(lowestBlaze.field_70165_t, lowestBlaze.field_70163_u + 1.0D, lowestBlaze.field_70161_v);
/* 3017 */         Utils.draw3DString(stringPos, EnumChatFormatting.BOLD + "Smallest", LOWEST_BLAZE_COLOUR, event.partialTicks);
/* 3018 */         AxisAlignedBB aabb = new AxisAlignedBB(lowestBlaze.field_70165_t - 0.5D, lowestBlaze.field_70163_u - 2.0D, lowestBlaze.field_70161_v - 0.5D, lowestBlaze.field_70165_t + 0.5D, lowestBlaze.field_70163_u, lowestBlaze.field_70161_v + 0.5D);
/* 3019 */         Utils.draw3DBox(aabb, LOWEST_BLAZE_COLOUR, event.partialTicks);
/*      */       } 
/* 3021 */       if (highestBlaze != null) {
/* 3022 */         BlockPos stringPos = new BlockPos(highestBlaze.field_70165_t, highestBlaze.field_70163_u + 1.0D, highestBlaze.field_70161_v);
/* 3023 */         Utils.draw3DString(stringPos, EnumChatFormatting.BOLD + "Biggest", HIGHEST_BLAZE_COLOUR, event.partialTicks);
/* 3024 */         AxisAlignedBB aabb = new AxisAlignedBB(highestBlaze.field_70165_t - 0.5D, highestBlaze.field_70163_u - 2.0D, highestBlaze.field_70161_v - 0.5D, highestBlaze.field_70165_t + 0.5D, highestBlaze.field_70163_u, highestBlaze.field_70161_v + 0.5D);
/* 3025 */         Utils.draw3DBox(aabb, HIGHEST_BLAZE_COLOUR, event.partialTicks);
/*      */       } 
/*      */     } 
/* 3028 */     if (ToggleCommand.creeperToggled && drawCreeperLines && !creeperLines.isEmpty()) {
/* 3029 */       for (int i = 0; i < creeperLines.size(); i++) {
/* 3030 */         Utils.draw3DLine(((Vec3[])creeperLines.get(i))[0], ((Vec3[])creeperLines.get(i))[1], CREEPER_COLOURS[i % 10], event.partialTicks);
/*      */       }
/*      */     }
/* 3033 */     if (ToggleCommand.ticTacToeToggled && correctTicTacToeButton != null) {
/* 3034 */       Utils.draw3DBox(correctTicTacToeButton, 4259648, event.partialTicks);
/*      */     }
/*      */   }
/*      */   
/*      */   @SubscribeEvent
/*      */   public void onInteract(PlayerInteractEvent event) {
/* 3040 */     if ((Minecraft.func_71410_x()).field_71439_g != event.entityPlayer)
/* 3041 */       return;  ItemStack item = event.entityPlayer.func_70694_bm();
/* 3042 */     if (item == null)
/*      */       return; 
/* 3044 */     if (event.action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK) {
/*      */       
/* 3046 */       Block block = (Minecraft.func_71410_x()).field_71441_e.func_180495_p(event.pos).func_177230_c();
/*      */       
/* 3048 */       if (ToggleCommand.blockBreakingFarmsToggled) {
/* 3049 */         ArrayList<Item> tools = new ArrayList<>(Arrays.asList(new Item[] { Items.field_151017_I, Items.field_151018_J, Items.field_151019_K, Items.field_151013_M, Items.field_151012_L, Items.field_151053_p, Items.field_151049_t, Items.field_151036_c, Items.field_151006_E, Items.field_151056_x }));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3062 */         ArrayList<Block> farmBlocks = new ArrayList<>(Arrays.asList(new Block[] { Blocks.field_150346_d, Blocks.field_150458_ak, Blocks.field_150404_cg, Blocks.field_150426_aN, Blocks.field_180398_cJ }));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3070 */         if (tools.contains(item.func_77973_b()) && farmBlocks.contains(block)) {
/* 3071 */           event.setCanceled(true);
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 3077 */     if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR) {
/* 3078 */       Minecraft mc = Minecraft.func_71410_x();
/* 3079 */       if (ToggleCommand.aotdToggled && item.func_82833_r().contains("Aspect of the Dragons")) {
/* 3080 */         event.setCanceled(true);
/*      */       }
/* 3082 */       if (ToggleCommand.lividDaggerToggled && item.func_82833_r().contains("Livid Dagger")) {
/* 3083 */         event.setCanceled(true);
/*      */       }
/* 3085 */       if (ToggleCommand.notifySlayerSlainToggled && 
/* 3086 */         ScoreboardHandler.getSidebarLines().stream().anyMatch(x -> ScoreboardHandler.cleanSB(x).contains("Boss slain!")) && 
/* 3087 */         ScoreboardHandler.getSidebarLines().stream().anyMatch(x -> {
/*      */             String line = ScoreboardHandler.cleanSB(x);
/*      */             return Arrays.<String>stream(new String[] { "Howling Cave", "Ruins", "Graveyard", "Coal Mine", "Spider's Den" }).anyMatch(line::contains);
/*      */           })) {
/* 3091 */         if (Utils.hasRightClickAbility(item)) {
/* 3092 */           List<String> lore = Utils.getItemLore(item);
/*      */           
/* 3094 */           int abilityLine = -1;
/* 3095 */           for (int i = 0; i < lore.size(); i++) {
/* 3096 */             String line = StringUtils.func_76338_a(lore.get(i));
/* 3097 */             if (line.startsWith("Item Ability:")) abilityLine = i; 
/* 3098 */             if (abilityLine != -1 && i > abilityLine && 
/* 3099 */               line.toLowerCase().contains("damage")) {
/* 3100 */               Utils.createTitle(EnumChatFormatting.RED + "Boss slain!", 2);
/*      */ 
/*      */               
/*      */               break;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 3111 */     if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
/* 3112 */       Minecraft mc = Minecraft.func_71410_x();
/* 3113 */       Block block = (Minecraft.func_71410_x()).field_71441_e.func_180495_p(event.pos).func_177230_c();
/*      */       
/* 3115 */       ArrayList<Block> interactables = new ArrayList<>(Arrays.asList(new Block[] { Blocks.field_180410_as, Blocks.field_150467_bQ, (Block)Blocks.field_150461_bJ, Blocks.field_150324_C, Blocks.field_180412_aq, Blocks.field_150382_bo, Blocks.field_150483_bI, Blocks.field_150462_ai, (Block)Blocks.field_150486_ae, Blocks.field_180409_at, (Block)Blocks.field_150453_bW, (Block)Blocks.field_180402_cm, Blocks.field_150367_z, Blocks.field_150409_cd, Blocks.field_150381_bn, Blocks.field_150477_bB, Blocks.field_150460_al, (Block)Blocks.field_150438_bZ, Blocks.field_180411_ar, Blocks.field_150442_at, Blocks.field_150323_B, (Block)Blocks.field_150455_bV, (Block)Blocks.field_150441_bU, (Block)Blocks.field_150416_aS, (Block)Blocks.field_150413_aR, Blocks.field_150472_an, Blocks.field_150444_as, Blocks.field_150415_aT, Blocks.field_150447_bR, Blocks.field_150471_bO, Blocks.field_150430_aB, Blocks.field_180413_ao, (Block)Blocks.field_150465_bP }));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3150 */       ArrayList<Block> flowerPlaceable = new ArrayList<>(Arrays.asList(new Block[] { (Block)Blocks.field_150349_c, Blocks.field_150346_d, Blocks.field_150457_bL, (Block)Blocks.field_150329_H, (Block)Blocks.field_150398_cm }));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3157 */       if (Utils.inDungeons) {
/* 3158 */         interactables.add(Blocks.field_150402_ci);
/* 3159 */         interactables.add(Blocks.field_150406_ce);
/*      */       } 
/* 3161 */       if (flowerPlaceable.contains(block)) {
/* 3162 */         if (ToggleCommand.flowerWeaponsToggled && item.func_82833_r().contains("Flower of Truth")) {
/* 3163 */           event.setCanceled(true);
/*      */         }
/* 3165 */         if (ToggleCommand.flowerWeaponsToggled && item.func_82833_r().contains("Spirit Sceptre")) {
/* 3166 */           event.setCanceled(true);
/*      */         }
/*      */       } 
/* 3169 */       if (!interactables.contains(block)) {
/* 3170 */         if (ToggleCommand.aotdToggled && item.func_82833_r().contains("Aspect of the Dragons")) {
/* 3171 */           event.setCanceled(true);
/*      */         }
/* 3173 */         if (ToggleCommand.lividDaggerToggled && item.func_82833_r().contains("Livid Dagger")) {
/* 3174 */           event.setCanceled(true);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   @SubscribeEvent
/*      */   public void onEntityInteract(EntityInteractEvent event) {
/* 3182 */     Minecraft mc = Minecraft.func_71410_x();
/* 3183 */     if (mc.field_71439_g != event.entityPlayer)
/*      */       return; 
/* 3185 */     if (ToggleCommand.itemFrameOnSeaLanternsToggled && Utils.inDungeons && event.target instanceof EntityItemFrame) {
/* 3186 */       EntityItemFrame itemFrame = (EntityItemFrame)event.target;
/* 3187 */       ItemStack item = itemFrame.func_82335_i();
/* 3188 */       if (item == null || item.func_77973_b() != Items.field_151032_g)
/* 3189 */         return;  BlockPos blockPos = Utils.getBlockUnderItemFrame(itemFrame);
/* 3190 */       if (mc.field_71441_e.func_180495_p(blockPos).func_177230_c() == Blocks.field_180398_cJ) {
/* 3191 */         event.setCanceled(true);
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   @SubscribeEvent
/*      */   public void onKey(InputEvent.KeyInputEvent event) {
/* 3198 */     EntityPlayerSP player = (Minecraft.func_71410_x()).field_71439_g;
/* 3199 */     Minecraft mc = Minecraft.func_71410_x();
/* 3200 */     WorldClient worldClient = mc.field_71441_e;
/*      */     
/* 3202 */     if (keyBindings[0].func_151468_f()) {
/* 3203 */       player.func_71165_d(lastMaddoxCommand);
/*      */     }
/* 3205 */     if (keyBindings[1].func_151468_f() && 
/* 3206 */       Utils.inDungeons) {
/* 3207 */       player.func_71040_bB(true);
/*      */     }
/*      */     
/* 3210 */     if (keyBindings[2].func_151468_f()) {
/* 3211 */       if (skillStopwatch.isStarted() && skillStopwatch.isSuspended()) {
/* 3212 */         skillStopwatch.resume();
/* 3213 */         player.func_145747_a((IChatComponent)new ChatComponentText(MAIN_COLOUR + "Skill tracker started."));
/* 3214 */       } else if (!skillStopwatch.isStarted()) {
/* 3215 */         skillStopwatch.start();
/* 3216 */         player.func_145747_a((IChatComponent)new ChatComponentText(MAIN_COLOUR + "Skill tracker started."));
/* 3217 */       } else if (skillStopwatch.isStarted() && !skillStopwatch.isSuspended()) {
/* 3218 */         skillStopwatch.suspend();
/* 3219 */         player.func_145747_a((IChatComponent)new ChatComponentText(MAIN_COLOUR + "Skill tracker paused."));
/*      */       } 
/*      */     }
/* 3222 */     if (keyBindings[3].func_151468_f()) {
/* 3223 */       int[] order = new int[9];
/* 3224 */       for (int i = 0; i <= 8; i++) {
/* 3225 */         ItemStack item = player.field_71071_by.func_70301_a(i);
/* 3226 */         if (item != null && item.func_82833_r().contains("Bonemerang"))
/* 3227 */           order[i] = 1; 
/* 3228 */         if ((item != null && item.func_82833_r().contains("Giant's Sword")) || (item != null && item.func_82833_r().contains("Emerald")))
/* 3229 */           this.sword = i; 
/* 3230 */         if (item != null && item.func_82833_r().contains("Bow"))
/* 3231 */           this.bow = i; 
/*      */       } 
/* 3233 */       (new Thread(() -> {
/*      */             for (int i = 0; i <= 8; i++) {
/*      */               if (order[i] != 0) {
/*      */                 player.field_71071_by.field_70461_c = i;
/*      */                 mc.field_71442_b.func_78769_a((EntityPlayer)mc.field_71439_g, world, player.field_71071_by.func_70301_a(i));
/*      */                 try {
/*      */                   Thread.sleep(DelayCommand.boneDelay);
/* 3240 */                 } catch (InterruptedException e) {
/*      */                   e.printStackTrace();
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */             if (this.sword != 10 && this.bow != 10 && SwapCommand.swapDelay != 0) {
/*      */               player.field_71071_by.field_70461_c = this.sword;
/*      */               try {
/*      */                 Thread.sleep(SwapCommand.swapDelay);
/* 3249 */               } catch (InterruptedException e) {
/*      */                 e.printStackTrace();
/*      */               } 
/*      */               player.field_71071_by.field_70461_c = this.bow;
/*      */             } 
/* 3254 */           })).start();
/*      */     } 
/* 3256 */     if (keyBindings[4].func_151468_f()) {
/* 3257 */       this.mazeId = 0;
/* 3258 */       slotIn = -1;
/*      */     } 
/* 3260 */     if (keyBindings[5].func_151468_f()) {
/* 3261 */       for (int i = 0; i <= SimonCommand.simonAmount; i++) {
/*      */         try {
/* 3263 */           Method method = mc.getClass().getDeclaredMethod("func_147121_ag", new Class[0]);
/* 3264 */           method.setAccessible(true);
/* 3265 */           method.invoke(mc, new Object[0]);
/* 3266 */         } catch (Exception exception) {
/* 3267 */           exception.printStackTrace();
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   @SubscribeEvent
/*      */   public void onGuiMouseInputPre(GuiScreenEvent.MouseInputEvent.Pre event) {
/* 3275 */     if (Mouse.getEventButton() != 0 && Mouse.getEventButton() != 1 && Mouse.getEventButton() != 2)
/*      */       return; 
/* 3277 */     if (!Mouse.getEventButtonState())
/*      */       return; 
/* 3279 */     if (event.gui instanceof GuiChest) {
/* 3280 */       Container containerChest = ((GuiChest)event.gui).field_147002_h;
/* 3281 */       if (containerChest instanceof ContainerChest) {
/*      */         
/* 3283 */         GuiChest chest = (GuiChest)event.gui;
/* 3284 */         IInventory inventory = ((ContainerChest)containerChest).func_85151_d();
/* 3285 */         Slot mouseSlot = chest.getSlotUnderMouse();
/* 3286 */         if (mouseSlot == null)
/* 3287 */           return;  ItemStack item = mouseSlot.func_75211_c();
/* 3288 */         String inventoryName = inventory.func_145748_c_().func_150260_c();
/*      */         
/* 3290 */         if (ToggleCommand.stopSalvageStarredToggled && inventoryName.startsWith("Salvage")) {
/* 3291 */           if (item == null)
/* 3292 */             return;  boolean inSalvageGui = false;
/* 3293 */           if (item.func_82833_r().contains("Salvage") || item.func_82833_r().contains("Essence")) {
/* 3294 */             ItemStack salvageItem = inventory.func_70301_a(13);
/* 3295 */             if (salvageItem == null)
/* 3296 */               return;  item = salvageItem;
/* 3297 */             inSalvageGui = true;
/*      */           } 
/* 3299 */           if (item.func_82833_r().contains("✪") && (mouseSlot.field_75222_d > 53 || inSalvageGui)) {
/* 3300 */             (Minecraft.func_71410_x()).field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(ERROR_COLOUR + "Danker's Skyblock Mod has stopped you from salvaging that item!"));
/* 3301 */             event.setCanceled(true);
/*      */             
/*      */             return;
/*      */           } 
/*      */         } 
/* 3306 */         if (inventoryName.endsWith(" Chest") && item != null && item.func_82833_r().contains("Open Reward Chest")) {
/* 3307 */           List<String> tooltip = item.func_82840_a((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g, (Minecraft.func_71410_x()).field_71474_y.field_82882_x);
/* 3308 */           for (String lineUnclean : tooltip) {
/* 3309 */             String line = StringUtils.func_76338_a(lineUnclean);
/* 3310 */             if (line.contains("FREE"))
/*      */               break; 
/* 3312 */             if (line.contains(" Coins")) {
/* 3313 */               int coinsSpent = Integer.parseInt(line.substring(0, line.indexOf(" ")).replaceAll(",", ""));
/*      */               
/* 3315 */               List<String> scoreboard = ScoreboardHandler.getSidebarLines();
/* 3316 */               for (String s : scoreboard) {
/* 3317 */                 String sCleaned = ScoreboardHandler.cleanSB(s);
/* 3318 */                 if (sCleaned.contains("The Catacombs (")) {
/* 3319 */                   if (sCleaned.contains("F1")) {
/* 3320 */                     LootCommand.f1CoinsSpent += coinsSpent;
/* 3321 */                     LootCommand.f1CoinsSpentSession += coinsSpent;
/* 3322 */                     ConfigHandler.writeDoubleConfig("catacombs", "floorOneCoins", LootCommand.f1CoinsSpent); break;
/* 3323 */                   }  if (sCleaned.contains("F2")) {
/* 3324 */                     LootCommand.f2CoinsSpent += coinsSpent;
/* 3325 */                     LootCommand.f2CoinsSpentSession += coinsSpent;
/* 3326 */                     ConfigHandler.writeDoubleConfig("catacombs", "floorTwoCoins", LootCommand.f2CoinsSpent); break;
/* 3327 */                   }  if (sCleaned.contains("F3")) {
/* 3328 */                     LootCommand.f3CoinsSpent += coinsSpent;
/* 3329 */                     LootCommand.f3CoinsSpentSession += coinsSpent;
/* 3330 */                     ConfigHandler.writeDoubleConfig("catacombs", "floorThreeCoins", LootCommand.f3CoinsSpent); break;
/* 3331 */                   }  if (sCleaned.contains("F4")) {
/* 3332 */                     LootCommand.f4CoinsSpent += coinsSpent;
/* 3333 */                     LootCommand.f4CoinsSpentSession += coinsSpent;
/* 3334 */                     ConfigHandler.writeDoubleConfig("catacombs", "floorFourCoins", LootCommand.f4CoinsSpent); break;
/* 3335 */                   }  if (sCleaned.contains("F5")) {
/* 3336 */                     LootCommand.f5CoinsSpent += coinsSpent;
/* 3337 */                     LootCommand.f5CoinsSpentSession += coinsSpent;
/* 3338 */                     ConfigHandler.writeDoubleConfig("catacombs", "floorFiveCoins", LootCommand.f5CoinsSpent); break;
/* 3339 */                   }  if (sCleaned.contains("F6")) {
/* 3340 */                     LootCommand.f6CoinsSpent += coinsSpent;
/* 3341 */                     LootCommand.f6CoinsSpentSession += coinsSpent;
/* 3342 */                     ConfigHandler.writeDoubleConfig("catacombs", "floorSixCoins", LootCommand.f6CoinsSpent); break;
/* 3343 */                   }  if (sCleaned.contains("F7")) {
/* 3344 */                     LootCommand.f7CoinsSpent += coinsSpent;
/* 3345 */                     LootCommand.f7CoinsSpentSession += coinsSpent;
/* 3346 */                     ConfigHandler.writeDoubleConfig("catacombs", "floorSevenCoins", LootCommand.f7CoinsSpent);
/*      */                   } 
/*      */                   
/*      */                   break;
/*      */                 } 
/*      */               } 
/*      */               break;
/*      */             } 
/*      */           } 
/*      */         } 
/* 3356 */         if (ToggleCommand.chronomatronToggled && inventoryName.startsWith("Chronomatron (")) {
/* 3357 */           if (item == null) {
/* 3358 */             if (event.isCancelable() && !Keyboard.isKeyDown(29) && !Keyboard.isKeyDown(157))
/* 3359 */               event.setCanceled(true); 
/*      */             return;
/*      */           } 
/* 3362 */           if (inventory.func_70301_a(49).func_82833_r().startsWith("§7Timer: §a") && (item.func_77973_b() == Item.func_150898_a((Block)Blocks.field_150399_cn) || item.func_77973_b() == Item.func_150898_a(Blocks.field_150406_ce))) {
/* 3363 */             if (chronomatronPattern.size() > chronomatronMouseClicks && !item.func_82833_r().equals(chronomatronPattern.get(chronomatronMouseClicks))) {
/* 3364 */               if (event.isCancelable() && !Keyboard.isKeyDown(29) && !Keyboard.isKeyDown(157))
/* 3365 */                 event.setCanceled(true); 
/*      */               return;
/*      */             } 
/* 3368 */             chronomatronMouseClicks++;
/* 3369 */           } else if (inventory.func_70301_a(49).func_82833_r().startsWith("§aRemember the pattern!")) {
/* 3370 */             if (event.isCancelable()) event.setCanceled(true);
/*      */             
/*      */             return;
/*      */           } 
/*      */         } 
/* 3375 */         if (ToggleCommand.ultrasequencerToggled && inventoryName.startsWith("Ultrasequencer (")) {
/* 3376 */           if (item == null) {
/* 3377 */             if (event.isCancelable() && !Keyboard.isKeyDown(29) && !Keyboard.isKeyDown(157))
/* 3378 */               event.setCanceled(true); 
/*      */             return;
/*      */           } 
/* 3381 */           if (inventory.func_70301_a(49).func_82833_r().equals("§aRemember the pattern!")) {
/* 3382 */             if (event.isCancelable()) event.setCanceled(true);  return;
/*      */           } 
/* 3384 */           if (inventory.func_70301_a(49).func_82833_r().startsWith("§7Timer: §a") && 
/* 3385 */             clickInOrderSlots[lastUltraSequencerClicked] != null && mouseSlot.getSlotIndex() != clickInOrderSlots[lastUltraSequencerClicked].getSlotIndex()) {
/* 3386 */             if (event.isCancelable() && !Keyboard.isKeyDown(29) && !Keyboard.isKeyDown(157)) {
/* 3387 */               event.setCanceled(true);
/*      */             }
/*      */             
/*      */             return;
/*      */           } 
/*      */         } 
/* 3393 */         if (ToggleCommand.blockWrongTerminalClicksToggled && Utils.inDungeons) {
/* 3394 */           boolean isValid; int slotIndex, needed; boolean shouldCancel = false;
/*      */           
/* 3396 */           if (item == null) {
/*      */             return;
/*      */           }
/*      */           
/* 3400 */           switch (inventoryName) {
/*      */             case "Correct all the panes!":
/* 3402 */               shouldCancel = !StringUtils.func_76338_a(item.func_82833_r()).startsWith("Off");
/*      */               break;
/*      */             case "Navigate the maze!":
/* 3405 */               if (item.func_77973_b() != Item.func_150898_a((Block)Blocks.field_150397_co)) {
/* 3406 */                 shouldCancel = true;
/*      */                 
/*      */                 break;
/*      */               } 
/* 3410 */               if (item.func_77952_i() != 0) {
/* 3411 */                 shouldCancel = true;
/*      */                 
/*      */                 break;
/*      */               } 
/* 3415 */               isValid = false;
/*      */               
/* 3417 */               slotIndex = mouseSlot.getSlotIndex();
/*      */               
/* 3419 */               if (slotIndex % 9 != 8 && slotIndex != 53) {
/* 3420 */                 ItemStack itemStack = inventory.func_70301_a(slotIndex + 1);
/* 3421 */                 if (itemStack != null && itemStack.func_77952_i() == 5) isValid = true;
/*      */               
/*      */               } 
/* 3424 */               if (!isValid && slotIndex % 9 != 0 && slotIndex != 0) {
/* 3425 */                 ItemStack itemStack = inventory.func_70301_a(slotIndex - 1);
/* 3426 */                 if (itemStack != null && itemStack.func_77952_i() == 5) isValid = true;
/*      */               
/*      */               } 
/* 3429 */               if (!isValid && slotIndex <= 44) {
/* 3430 */                 ItemStack itemStack = inventory.func_70301_a(slotIndex + 9);
/* 3431 */                 if (itemStack != null && itemStack.func_77952_i() == 5) isValid = true;
/*      */               
/*      */               } 
/* 3434 */               if (!isValid && slotIndex >= 9) {
/* 3435 */                 ItemStack itemStack = inventory.func_70301_a(slotIndex - 9);
/* 3436 */                 if (itemStack != null && itemStack.func_77952_i() == 5) isValid = true;
/*      */               
/*      */               } 
/* 3439 */               shouldCancel = !isValid;
/*      */               break;
/*      */ 
/*      */             
/*      */             case "Click in order!":
/* 3444 */               if (mouseSlot.getSlotIndex() > 35) {
/*      */                 break;
/*      */               }
/*      */               
/* 3448 */               if (item.func_77973_b() != Item.func_150898_a((Block)Blocks.field_150397_co)) {
/* 3449 */                 shouldCancel = true;
/*      */                 break;
/*      */               } 
/* 3452 */               if (item.func_77952_i() != 14) {
/* 3453 */                 shouldCancel = true;
/*      */                 break;
/*      */               } 
/* 3456 */               needed = terminalNumberNeeded[0];
/* 3457 */               if (needed == 0)
/* 3458 */                 break;  shouldCancel = (needed != -1 && item.field_77994_a != needed);
/*      */               break;
/*      */           } 
/*      */           
/* 3462 */           if (!shouldCancel) {
/* 3463 */             if (inventoryName.startsWith("What starts with:")) {
/* 3464 */               char letter = inventoryName.charAt(inventoryName.indexOf("'") + 1);
/* 3465 */               shouldCancel = (StringUtils.func_76338_a(item.func_82833_r()).charAt(0) != letter);
/* 3466 */             } else if (inventoryName.startsWith("Select all the")) {
/* 3467 */               if (terminalColorNeeded == null)
/* 3468 */                 return;  String itemName = StringUtils.func_76338_a(item.func_82833_r()).toUpperCase();
/* 3469 */               shouldCancel = (!itemName.contains(terminalColorNeeded) && (!terminalColorNeeded.equals("SILVER") || !itemName.contains("LIGHT GRAY")) && (!terminalColorNeeded.equals("WHITE") || !itemName.equals("WOOL")));
/*      */             } 
/*      */           }
/*      */           
/* 3473 */           event.setCanceled((shouldCancel && !Keyboard.isKeyDown(29) && !Keyboard.isKeyDown(157)));
/*      */         } 
/*      */         
/* 3476 */         if (!BlockSlayerCommand.onlySlayerName.equals("") && item != null) {
/* 3477 */           if (inventoryName.equals("Slayer")) {
/* 3478 */             if (!item.func_82833_r().contains("Revenant Horror") && !item.func_82833_r().contains("Tarantula Broodfather") && !item.func_82833_r().contains("Sven Packmaster"))
/*      */               return; 
/* 3480 */             if (!item.func_82833_r().contains(BlockSlayerCommand.onlySlayerName)) {
/* 3481 */               (Minecraft.func_71410_x()).field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(ERROR_COLOUR + "Danker's Skyblock Mod has stopped you from starting this quest (Set to " + BlockSlayerCommand.onlySlayerName + " " + BlockSlayerCommand.onlySlayerNumber + ")"));
/* 3482 */               event.setCanceled(true);
/*      */             } 
/* 3484 */           } else if ((inventoryName.equals("Revenant Horror") || inventoryName.equals("Tarantula Broodfather") || inventoryName.equals("Sven Packmaster")) && (
/* 3485 */             item.func_82833_r().contains("Revenant Horror") || item.func_82833_r().contains("Tarantula Broodfather") || item.func_82833_r().contains("Sven Packmaster"))) {
/*      */             
/* 3487 */             String slayerNumber = item.func_82833_r().substring(item.func_82833_r().lastIndexOf(" ") + 1);
/* 3488 */             if (!slayerNumber.equals(BlockSlayerCommand.onlySlayerNumber)) {
/* 3489 */               (Minecraft.func_71410_x()).field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(ERROR_COLOUR + "Danker's Skyblock Mod has stopped you from starting this quest (Set to " + BlockSlayerCommand.onlySlayerName + " " + BlockSlayerCommand.onlySlayerNumber + ")"));
/* 3490 */               event.setCanceled(true);
/*      */             } 
/*      */           } 
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   @SubscribeEvent
/*      */   public void onMouseInputPost(GuiScreenEvent.MouseInputEvent.Post event) {
/* 3501 */     if (Mouse.getEventButton() == 0 && event.gui instanceof net.minecraft.client.gui.GuiChat && 
/* 3502 */       ToggleCommand.chatMaddoxToggled && (System.currentTimeMillis() / 1000L) - lastMaddoxTime < 10.0D) {
/* 3503 */       (Minecraft.func_71410_x()).field_71439_g.func_71165_d(lastMaddoxCommand);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   @SubscribeEvent
/*      */   public void onGuiOpen(GuiOpenEvent event) {
/* 3510 */     Minecraft mc = Minecraft.func_71410_x();
/* 3511 */     GameSettings gameSettings = mc.field_71474_y;
/* 3512 */     if (event.gui instanceof GuiChest) {
/* 3513 */       Container containerChest = ((GuiChest)event.gui).field_147002_h;
/* 3514 */       if (containerChest instanceof ContainerChest) {
/* 3515 */         GuiChest chest = (GuiChest)event.gui;
/* 3516 */         IInventory inventory = ((ContainerChest)containerChest).func_85151_d();
/* 3517 */         String inventoryName = inventory.func_145748_c_().func_150260_c();
/*      */         
/* 3519 */         if (ToggleCommand.swapToPickBlockToggled) {
/* 3520 */           if (inventoryName.startsWith("Chronomatron (") || inventoryName.startsWith("Superpairs (") || inventoryName.startsWith("Ultrasequencer (") || inventoryName.startsWith("What starts with:") || inventoryName.startsWith("Select all the") || inventoryName.startsWith("Navigate the maze!") || inventoryName.startsWith("Correct all the panes!") || inventoryName.startsWith("Click in order!") || inventoryName.startsWith("Harp -")) {
/* 3521 */             if (!pickBlockBindSwapped) {
/* 3522 */               pickBlockBind = gameSettings.field_74322_I.func_151463_i();
/* 3523 */               gameSettings.field_74322_I.func_151462_b(-100);
/* 3524 */               pickBlockBindSwapped = true;
/*      */             }
/*      */           
/* 3527 */           } else if (pickBlockBindSwapped) {
/* 3528 */             gameSettings.field_74322_I.func_151462_b(pickBlockBind);
/* 3529 */             pickBlockBindSwapped = false;
/*      */           }
/*      */         
/*      */         }
/*      */       }
/*      */     
/* 3535 */     } else if (pickBlockBindSwapped) {
/* 3536 */       gameSettings.field_74322_I.func_151462_b(pickBlockBind);
/* 3537 */       pickBlockBindSwapped = false;
/*      */     } 
/*      */ 
/*      */     
/* 3541 */     if (ToggleCommand.autoSkillTrackerToggled && 
/* 3542 */       skillStopwatch.isStarted() && !skillStopwatch.isSuspended()) {
/* 3543 */       skillStopwatch.suspend();
/*      */     }
/*      */ 
/*      */     
/* 3547 */     clickInOrderSlots = new Slot[36];
/* 3548 */     lastChronomatronRound = 0;
/* 3549 */     chronomatronPattern.clear();
/* 3550 */     chronomatronMouseClicks = 0;
/* 3551 */     experimentTableSlots = new ItemStack[54];
/* 3552 */     terminalColorNeeded = null;
/*      */   }
/*      */   
/*      */   @SubscribeEvent
/*      */   public void onGuiRender(GuiScreenEvent.BackgroundDrawnEvent event) {
/* 3557 */     if (event.gui instanceof GuiChest) {
/* 3558 */       GuiChest inventory = (GuiChest)event.gui;
/* 3559 */       Container containerChest = inventory.field_147002_h;
/* 3560 */       if (containerChest instanceof ContainerChest) {
/* 3561 */         Minecraft mc = Minecraft.func_71410_x();
/* 3562 */         ScaledResolution sr = new ScaledResolution(mc);
/* 3563 */         int guiLeft = (sr.func_78326_a() - 176) / 2;
/* 3564 */         int guiTop = (sr.func_78328_b() - 222) / 2;
/*      */         
/* 3566 */         List<Slot> invSlots = inventory.field_147002_h.field_75151_b;
/* 3567 */         String displayName = ((ContainerChest)containerChest).func_85151_d().func_145748_c_().func_150260_c().trim();
/* 3568 */         int chestSize = inventory.field_147002_h.field_75151_b.size();
/*      */         
/* 3570 */         if (ToggleCommand.petColoursToggled) {
/* 3571 */           for (Slot slot : invSlots) {
/* 3572 */             int colour; ItemStack item = slot.func_75211_c();
/* 3573 */             if (item == null)
/* 3574 */               continue;  String name = item.func_82833_r();
/* 3575 */             if (!petPattern.matcher(StringUtils.func_76338_a(name)).find() || 
/* 3576 */               name.endsWith("aHealer") || name.endsWith("aMage") || name.endsWith("aBerserk") || name.endsWith("aArcher") || name.endsWith("aTank")) {
/*      */               continue;
/*      */             }
/* 3579 */             int petLevel = Integer.parseInt(item.func_82833_r().substring(item.func_82833_r().indexOf(" ") + 1, item.func_82833_r().indexOf("]")));
/* 3580 */             if (petLevel == 100) {
/* 3581 */               colour = PET_100;
/* 3582 */             } else if (petLevel >= 90) {
/* 3583 */               colour = PET_90_TO_99;
/* 3584 */             } else if (petLevel >= 80) {
/* 3585 */               colour = PET_80_TO_89;
/* 3586 */             } else if (petLevel >= 70) {
/* 3587 */               colour = PET_70_TO_79;
/* 3588 */             } else if (petLevel >= 60) {
/* 3589 */               colour = PET_60_TO_69;
/* 3590 */             } else if (petLevel >= 50) {
/* 3591 */               colour = PET_50_TO_59;
/* 3592 */             } else if (petLevel >= 40) {
/* 3593 */               colour = PET_40_TO_49;
/* 3594 */             } else if (petLevel >= 30) {
/* 3595 */               colour = PET_30_TO_39;
/* 3596 */             } else if (petLevel >= 20) {
/* 3597 */               colour = PET_20_TO_29;
/* 3598 */             } else if (petLevel >= 10) {
/* 3599 */               colour = PET_10_TO_19;
/*      */             } else {
/* 3601 */               colour = PET_1_TO_9;
/*      */             } 
/* 3603 */             Utils.drawOnSlot(chestSize, slot.field_75223_e, slot.field_75221_f, colour + -1090519040);
/*      */           } 
/*      */         }
/*      */         
/* 3607 */         if (ToggleCommand.clickInOrderToggled && Utils.inDungeons && displayName.equals("Chest") && tickAmount != until) {
/* 3608 */           if (chestOpen == 0) {
/* 3609 */             until = tickAmount;
/* 3610 */             chestOpen = 1;
/*      */             return;
/*      */           } 
/* 3613 */           mc.field_71439_g.func_71053_j();
/* 3614 */           chestOpen = 0;
/*      */         } 
/* 3616 */         if (displayName.contains("Harp") && ToggleCommand.startsWithToggled) {
/* 3617 */           String[] currentInv = new String[54];
/* 3618 */           Container playerContainer = mc.field_71439_g.field_71070_bA;
/* 3619 */           IInventory chestInventory = ((ContainerChest)playerContainer).func_85151_d();
/* 3620 */           for (int i = 37; i <= 43; i++) {
/* 3621 */             ItemStack itemStack = chestInventory.func_70301_a(i);
/* 3622 */             if (itemStack != null && 
/* 3623 */               itemStack.func_77977_a().toLowerCase().contains("quartz")) {
/* 3624 */               for (int j = 0; j <= 53; j++) {
/* 3625 */                 ItemStack name = chestInventory.func_70301_a(j);
/* 3626 */                 if (name != null)
/* 3627 */                   currentInv[j] = name.toString(); 
/*      */               } 
/* 3629 */               if (!Arrays.toString((Object[])currentInv).equals(Arrays.toString((Object[])harpInv))) {
/* 3630 */                 mc.field_71442_b.func_78753_a(mc.field_71439_g.field_71070_bA.field_75152_c, i, 2, 0, (EntityPlayer)mc.field_71439_g);
/* 3631 */                 mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("clicked"));
/* 3632 */                 until = tickAmount;
/* 3633 */                 harpInv = currentInv;
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */         
/* 3639 */         if (displayName.equals("Navigate the maze!") && invSlots.size() == 90 && ToggleCommand.startsWithToggled && System.currentTimeMillis() - lastInteractTime >= SleepCommand.waitAmount) {
/* 3640 */           if (this.mazeId <= mc.field_71439_g.field_71070_bA.field_75152_c) {
/* 3641 */             this.mazeId = mc.field_71439_g.field_71070_bA.field_75152_c;
/*      */           }
/* 3643 */           Container playerContainer = mc.field_71439_g.field_71070_bA;
/* 3644 */           IInventory chestInventory = ((ContainerChest)playerContainer).func_85151_d();
/* 3645 */           if (slotIn == -1) {
/* 3646 */             System.out.println("checking chest");
/* 3647 */             for (int i = 0; i <= 53; i++) {
/* 3648 */               ItemStack itemStack = chestInventory.func_70301_a(i);
/* 3649 */               if (itemStack != null) {
/* 3650 */                 int type = itemStack.func_77952_i();
/* 3651 */                 if (type == 0)
/* 3652 */                   chest[i] = 1; 
/* 3653 */                 if (type == 5) {
/* 3654 */                   slotIn = i;
/* 3655 */                   chest[i] = 2;
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */           } 
/* 3660 */           int firstCheck = slotIn + 1;
/* 3661 */           int secondCheck = slotIn + 9;
/* 3662 */           int thirdCheck = slotIn - 1;
/* 3663 */           int fourthCheck = slotIn - 9;
/* 3664 */           System.out.println("attempt " + slotIn);
/* 3665 */           if (firstCheck % 9 != 0 && firstCheck <= 53 && chest[firstCheck] == 1) {
/* 3666 */             chest[firstCheck] = 0;
/* 3667 */             slotIn = firstCheck;
/* 3668 */             System.out.println("1");
/* 3669 */             mc.field_71442_b.func_78753_a(this.mazeId, firstCheck, 0, 0, (EntityPlayer)mc.field_71439_g);
/* 3670 */             this.mazeId++;
/* 3671 */             lastInteractTime = System.currentTimeMillis();
/* 3672 */           } else if (secondCheck <= 53 && chest[secondCheck] == 1) {
/* 3673 */             chest[secondCheck] = 0;
/* 3674 */             slotIn = secondCheck;
/* 3675 */             System.out.println("2");
/* 3676 */             mc.field_71442_b.func_78753_a(this.mazeId, secondCheck, 0, 0, (EntityPlayer)mc.field_71439_g);
/* 3677 */             this.mazeId++;
/* 3678 */             lastInteractTime = System.currentTimeMillis();
/* 3679 */           } else if (thirdCheck % 9 != 8 && thirdCheck >= 0 && thirdCheck <= 53 && chest[thirdCheck] == 1) {
/* 3680 */             chest[thirdCheck] = 0;
/* 3681 */             slotIn = thirdCheck;
/* 3682 */             System.out.println("3");
/* 3683 */             mc.field_71442_b.func_78753_a(this.mazeId, thirdCheck, 0, 0, (EntityPlayer)mc.field_71439_g);
/* 3684 */             this.mazeId++;
/* 3685 */             lastInteractTime = System.currentTimeMillis();
/* 3686 */           } else if (fourthCheck >= 0 && (Minecraft.func_71410_x()).field_71462_r != null && chest[fourthCheck] == 1) {
/* 3687 */             chest[fourthCheck] = 0;
/* 3688 */             slotIn = fourthCheck;
/* 3689 */             System.out.println("4");
/* 3690 */             mc.field_71442_b.func_78753_a(this.mazeId, fourthCheck, 0, 0, (EntityPlayer)mc.field_71439_g);
/* 3691 */             this.mazeId++;
/* 3692 */             lastInteractTime = System.currentTimeMillis();
/*      */           } 
/*      */         } 
/*      */         
/* 3696 */         if (ToggleCommand.startsWithToggled && System.currentTimeMillis() - lastInteractTime >= SleepCommand.waitAmount && displayName.startsWith("What starts with:")) {
/* 3697 */           char letter = displayName.charAt(displayName.indexOf("'") + 1);
/* 3698 */           if (this.mazeId <= mc.field_71439_g.field_71070_bA.field_75152_c)
/* 3699 */             this.mazeId = mc.field_71439_g.field_71070_bA.field_75152_c; 
/* 3700 */           for (Slot startsWith : invSlots) {
/* 3701 */             if (startsWith.field_75222_d <= lastSlot || startsWith
/* 3702 */               .getSlotIndex() <= lastSlot)
/*      */               continue; 
/* 3704 */             ItemStack item = startsWith.func_75211_c();
/* 3705 */             if (item == null || item
/* 3706 */               .func_77948_v())
/*      */               continue; 
/* 3708 */             if (StringUtils.func_76338_a(item.func_82833_r()).charAt(0) == letter) {
/* 3709 */               mc.field_71442_b.func_78753_a(this.mazeId, startsWith.field_75222_d, 0, 0, (EntityPlayer)mc.field_71439_g);
/* 3710 */               this.mazeId++;
/* 3711 */               lastSlot = startsWith.getSlotIndex();
/* 3712 */               lastInteractTime = System.currentTimeMillis();
/*      */               break;
/*      */             } 
/* 3715 */             if (this.mazeId - 15 > mc.field_71439_g.field_71070_bA.field_75152_c) {
/*      */               break;
/*      */             }
/*      */           } 
/*      */         } 
/* 3720 */         if (ToggleCommand.startsWithToggled && System.currentTimeMillis() - lastInteractTime >= SleepCommand.waitAmount && displayName.equals("Correct all the panes!")) {
/* 3721 */           for (Slot startsWith : invSlots) {
/* 3722 */             if (startsWith.getSlotIndex() > 53 || startsWith
/* 3723 */               .getSlotIndex() <= lastSlot)
/*      */               continue; 
/* 3725 */             ItemStack item = startsWith.func_75211_c();
/* 3726 */             if (item == null || item
/* 3727 */               .func_77948_v())
/*      */               continue; 
/* 3729 */             if (item.func_82833_r().contains("Off") && (Minecraft.func_71410_x()).field_71462_r != null) {
/* 3730 */               if (this.mazeId <= mc.field_71439_g.field_71070_bA.field_75152_c)
/* 3731 */                 this.mazeId = mc.field_71439_g.field_71070_bA.field_75152_c; 
/* 3732 */               mc.field_71442_b.func_78753_a(this.mazeId, startsWith.field_75222_d, 0, 0, (EntityPlayer)mc.field_71439_g);
/* 3733 */               this.mazeId++;
/* 3734 */               lastSlot = startsWith.getSlotIndex();
/* 3735 */               lastInteractTime = System.currentTimeMillis();
/*      */               break;
/*      */             } 
/* 3738 */             if (this.mazeId - 15 > mc.field_71439_g.field_71070_bA.field_75152_c) {
/*      */               break;
/*      */             }
/*      */           } 
/*      */         }
/* 3743 */         if (ToggleCommand.startsWithToggled && System.currentTimeMillis() - lastInteractTime >= SleepCommand.waitAmount && displayName.startsWith("Select all the")) {
/* 3744 */           String colour = displayName.split(" ")[3];
/* 3745 */           for (Slot slot : invSlots) {
/* 3746 */             if (slot.getSlotIndex() > 53 || slot.getSlotIndex() <= lastSlot)
/* 3747 */               continue;  ItemStack item = slot.func_75211_c();
/* 3748 */             if (item == null || item.func_77948_v())
/* 3749 */               continue;  String itemName = StringUtils.func_76338_a(item.func_82833_r()).toUpperCase();
/* 3750 */             if (item.func_82833_r().toUpperCase().contains(colour) || (colour.equals("SILVER") && itemName.contains("LIGHT GRAY")) || (colour.equals("WHITE") && itemName.equals("WOOL")) || (colour.equals("BLACK") && itemName.contains("INK")) || (colour.equals("BROWN") && itemName.contains("COCOA")) || (colour.equals("BLUE") && itemName.contains("LAPIS")) || (colour.equals("WHITE") && itemName.contains("BONE"))) {
/* 3751 */               if (this.mazeId <= mc.field_71439_g.field_71070_bA.field_75152_c)
/* 3752 */                 this.mazeId = mc.field_71439_g.field_71070_bA.field_75152_c; 
/* 3753 */               lastInteractTime = System.currentTimeMillis();
/* 3754 */               mc.field_71442_b.func_78753_a(this.mazeId, slot.field_75222_d, 2, 0, (EntityPlayer)mc.field_71439_g);
/* 3755 */               lastSlot = slot.getSlotIndex();
/* 3756 */               this.mazeId++;
/*      */               break;
/*      */             } 
/* 3759 */             if (this.mazeId - 15 > mc.field_71439_g.field_71070_bA.field_75152_c)
/*      */               break; 
/*      */           } 
/*      */         } 
/* 3763 */         if (displayName.equals("Click in order!") && System.currentTimeMillis() - lastInteractTime >= SleepCommand.waitAmount && ToggleCommand.startsWithToggled) {
/* 3764 */           Container playerContainer = mc.field_71439_g.field_71070_bA;
/* 3765 */           IInventory chestInventory = ((ContainerChest)playerContainer).func_85151_d();
/* 3766 */           if (this.mazeId <= mc.field_71439_g.field_71070_bA.field_75152_c)
/* 3767 */             this.mazeId = mc.field_71439_g.field_71070_bA.field_75152_c; 
/* 3768 */           int[] order = new int[14];
/*      */           int i;
/* 3770 */           for (i = 10; i <= 25; i++) {
/* 3771 */             if (i != 17 && i != 18) {
/* 3772 */               ItemStack click = chestInventory.func_70301_a(i);
/* 3773 */               if (click == null)
/*      */                 break; 
/* 3775 */               order[(chestInventory.func_70301_a(i)).field_77994_a - 1] = i;
/*      */             } 
/*      */           } 
/* 3778 */           for (i = 0; i < order.length && order[i] != 0; 
/* 3779 */             i++) {
/* 3780 */             if (i > lastSlot) {
/* 3781 */               ItemStack check = chestInventory.func_70301_a(order[i]);
/* 3782 */               if (order[i] != 0 && check != null && check.func_77952_i() == 14) {
/* 3783 */                 mc.field_71442_b.func_78753_a(this.mazeId, order[i], 2, 0, (EntityPlayer)mc.field_71439_g);
/* 3784 */                 this.mazeId++;
/* 3785 */                 lastSlot = i;
/* 3786 */                 lastInteractTime = System.currentTimeMillis();
/*      */                 break;
/*      */               } 
/* 3789 */               if (this.mazeId - 15 > mc.field_71439_g.field_71070_bA.field_75152_c) {
/*      */                 break;
/*      */               }
/*      */             } 
/*      */           } 
/*      */         } 
/* 3795 */         if (ToggleCommand.ultrasequencerToggled && displayName.startsWith("Ultrasequencer (")) {
/* 3796 */           EntityPlayerSP player = mc.field_71439_g;
/* 3797 */           if (invSlots.size() > 48 && ((Slot)invSlots.get(49)).func_75211_c() != null && player.field_71071_by.func_70445_o() == null && (
/* 3798 */             (Slot)invSlots.get(49)).func_75211_c().func_82833_r().startsWith("§7Timer: §a")) {
/* 3799 */             lastUltraSequencerClicked = 0;
/* 3800 */             for (Slot slot : clickInOrderSlots) {
/* 3801 */               if (slot != null && slot.func_75211_c() != null && StringUtils.func_76338_a(slot.func_75211_c().func_82833_r()).matches("\\d+")) {
/* 3802 */                 int number = Integer.parseInt(StringUtils.func_76338_a(slot.func_75211_c().func_82833_r()));
/* 3803 */                 if (number > lastUltraSequencerClicked) {
/* 3804 */                   lastUltraSequencerClicked = number;
/*      */                 }
/*      */               } 
/*      */             } 
/* 3808 */             if (clickInOrderSlots[lastUltraSequencerClicked] != null && player.field_71071_by.func_70445_o() == null && tickAmount % 2 == 0 && lastUltraSequencerClicked != 0 && until == lastUltraSequencerClicked) {
/* 3809 */               Slot nextSlot = clickInOrderSlots[lastUltraSequencerClicked];
/* 3810 */               new TextRenderer(mc, String.valueOf(mc.field_71439_g.field_71070_bA.field_75152_c), 50, 50, 1.0D);
/* 3811 */               mc.field_71442_b.func_78753_a(mc.field_71439_g.field_71070_bA.field_75152_c, nextSlot.field_75222_d, 0, 0, (EntityPlayer)mc.field_71439_g);
/* 3812 */               Utils.drawOnSlot(chestSize, nextSlot.field_75223_e, nextSlot.field_75221_f, -448725184);
/* 3813 */               until = lastUltraSequencerClicked + 1;
/* 3814 */               tickAmount = 0;
/*      */             } 
/* 3816 */             if (clickInOrderSlots[lastUltraSequencerClicked] != null && player.field_71071_by.func_70445_o() == null && tickAmount == 18 && lastUltraSequencerClicked < 1) {
/* 3817 */               Slot nextSlot = clickInOrderSlots[lastUltraSequencerClicked];
/* 3818 */               new TextRenderer(mc, String.valueOf(mc.field_71439_g.field_71070_bA.field_75152_c), 50, 50, 1.0D);
/* 3819 */               mc.field_71442_b.func_78753_a(mc.field_71439_g.field_71070_bA.field_75152_c, nextSlot.field_75222_d, 0, 0, (EntityPlayer)mc.field_71439_g);
/* 3820 */               Utils.drawOnSlot(chestSize, nextSlot.field_75223_e, nextSlot.field_75221_f, -448725184);
/* 3821 */               tickAmount = 0;
/* 3822 */               until = 1;
/*      */             } 
/* 3824 */             if (lastUltraSequencerClicked + 1 < clickInOrderSlots.length && clickInOrderSlots[lastUltraSequencerClicked + 1] != null) {
/* 3825 */               Slot nextSlot = clickInOrderSlots[lastUltraSequencerClicked + 1];
/* 3826 */               Utils.drawOnSlot(chestSize, nextSlot.field_75223_e, nextSlot.field_75221_f, -683615514);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/* 3832 */         if (ToggleCommand.chronomatronToggled && displayName.startsWith("Chronomatron (")) {
/* 3833 */           EntityPlayerSP player = mc.field_71439_g;
/* 3834 */           if (player.field_71071_by.func_70445_o() == null && invSlots.size() > 48 && ((Slot)invSlots.get(49)).func_75211_c() != null) {
/* 3835 */             if (((Slot)invSlots.get(49)).func_75211_c().func_82833_r().startsWith("§7Timer: §a") && ((Slot)invSlots.get(4)).func_75211_c() != null) {
/* 3836 */               int round = (((Slot)invSlots.get(4)).func_75211_c()).field_77994_a;
/* 3837 */               int timerSeconds = Integer.parseInt(StringUtils.func_76338_a(((Slot)invSlots.get(49)).func_75211_c().func_82833_r()).replaceAll("[^\\d]", ""));
/* 3838 */               if (round != lastChronomatronRound && timerSeconds == round + 2) {
/* 3839 */                 lastChronomatronRound = round;
/* 3840 */                 for (int i = 10; i <= 43; i++) {
/* 3841 */                   ItemStack stack = ((Slot)invSlots.get(i)).func_75211_c();
/* 3842 */                   if (stack != null && 
/* 3843 */                     stack.func_77973_b() == Item.func_150898_a(Blocks.field_150406_ce)) {
/* 3844 */                     chronomatronPattern.add(stack.func_82833_r());
/*      */                     break;
/*      */                   } 
/*      */                 } 
/*      */               } 
/* 3849 */               if (chronomatronMouseClicks < chronomatronPattern.size() && player.field_71071_by.func_70445_o() == null) {
/* 3850 */                 for (int i = 10; i <= 43; i++) {
/* 3851 */                   ItemStack glass = ((Slot)invSlots.get(i)).func_75211_c();
/* 3852 */                   if (glass != null && 
/* 3853 */                     player.field_71071_by.func_70445_o() == null && 
/* 3854 */                     tickAmount % 5 == 0) {
/* 3855 */                     Slot glassSlot = invSlots.get(i);
/* 3856 */                     if (glass.func_82833_r().equals(chronomatronPattern.get(chronomatronMouseClicks))) {
/* 3857 */                       Utils.drawOnSlot(chestSize, glassSlot.field_75223_e, glassSlot.field_75221_f, -448725184);
/* 3858 */                       mc.field_71442_b.func_78753_a(mc.field_71439_g.field_71070_bA.field_75152_c, glassSlot.field_75222_d, 0, 0, (EntityPlayer)mc.field_71439_g);
/* 3859 */                       chronomatronMouseClicks++;
/*      */ 
/*      */                       
/*      */                       break;
/*      */                     } 
/*      */                   } 
/*      */                 } 
/*      */               }
/* 3867 */             } else if (((Slot)invSlots.get(49)).func_75211_c().func_82833_r().equals("§aRemember the pattern!")) {
/* 3868 */               chronomatronMouseClicks = 0;
/*      */             } 
/*      */           }
/* 3871 */           new TextRenderer(mc, String.join("\n", (Iterable)chronomatronPattern), (int)(guiLeft * 0.8D), 10, 1.0D);
/*      */         } 
/*      */         
/* 3874 */         if (ToggleCommand.superpairsToggled && displayName.contains("Superpairs (")) {
/* 3875 */           new TextRenderer(mc, String.valueOf(mc.field_71439_g.field_71070_bA.field_75152_c), 50, 50, 1.0D);
/* 3876 */           HashMap<String, HashSet<Integer>> matches = new HashMap<>();
/* 3877 */           for (int i = 0; i < 53; i++) {
/* 3878 */             ItemStack itemStack = experimentTableSlots[i];
/* 3879 */             if (itemStack != null) {
/* 3880 */               Slot slot = invSlots.get(i);
/* 3881 */               int x = guiLeft + slot.field_75223_e;
/* 3882 */               int y = guiTop + slot.field_75221_f;
/* 3883 */               if (chestSize != 90) y += (6 - (chestSize - 36) / 9) * 9;
/*      */ 
/*      */ 
/*      */               
/* 3887 */               String itemName = itemStack.func_82833_r();
/* 3888 */               String keyName = itemName + itemStack.func_77977_a();
/* 3889 */               matches.computeIfAbsent(keyName, k -> new HashSet());
/* 3890 */               ((HashSet<Integer>)matches.get(keyName)).add(Integer.valueOf(i));
/*      */             } 
/*      */           } 
/* 3893 */           Color[] colors = { new Color(255, 0, 0, 100), new Color(0, 0, 255, 100), new Color(100, 179, 113, 100), new Color(255, 114, 255, 100), new Color(255, 199, 87, 100), new Color(119, 105, 198, 100), new Color(135, 199, 112, 100), new Color(240, 37, 240, 100), new Color(178, 132, 190, 100), new Color(63, 135, 163, 100), new Color(146, 74, 10, 100), new Color(255, 255, 255, 100), new Color(217, 252, 140, 100), new Color(255, 82, 82, 100) };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 3910 */           Iterator<Color> colorIterator = Arrays.<Color>stream(colors).iterator();
/*      */           
/* 3912 */           matches.forEach((itemName, slotSet) -> {
/*      */                 if (slotSet.size() < 2) {
/*      */                   return;
/*      */                 }
/*      */                 ArrayList<Slot> slots = new ArrayList<>();
/*      */                 slotSet.forEach(());
/*      */                 Color color = colorIterator.next();
/*      */                 slots.forEach(());
/*      */               });
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   @SubscribeEvent
/*      */   public void onServerConnect(FMLNetworkEvent.ClientConnectedToServerEvent event) {
/* 3929 */     event.manager.channel().pipeline().addBefore("packet_handler", "danker_packet_handler", (ChannelHandler)new PacketHandler());
/* 3930 */     System.out.println("Added packet handler to channel pipeline.");
/*      */   }
/*      */   
/*      */   public void increaseSeaCreatures() {
/* 3934 */     if (LootCommand.empSCs != -1) {
/* 3935 */       LootCommand.empSCs++;
/*      */     }
/* 3937 */     if (LootCommand.empSCsSession != -1) {
/* 3938 */       LootCommand.empSCsSession++;
/*      */     }
/*      */     
/* 3941 */     List<String> scoreboard = ScoreboardHandler.getSidebarLines();
/* 3942 */     for (String s : scoreboard) {
/* 3943 */       String sCleaned = ScoreboardHandler.cleanSB(s);
/* 3944 */       if (sCleaned.contains("Jerry's Workshop") || sCleaned.contains("Jerry Pond")) {
/* 3945 */         if (LootCommand.yetiSCs != -1) {
/* 3946 */           LootCommand.yetiSCs++;
/*      */         }
/* 3948 */         if (LootCommand.yetiSCsSession != -1) {
/* 3949 */           LootCommand.yetiSCsSession++;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 3954 */     LootCommand.seaCreatures++;
/* 3955 */     LootCommand.fishingMilestone++;
/* 3956 */     LootCommand.seaCreaturesSession++;
/* 3957 */     LootCommand.fishingMilestoneSession++;
/* 3958 */     ConfigHandler.writeIntConfig("fishing", "seaCreature", LootCommand.seaCreatures);
/* 3959 */     ConfigHandler.writeIntConfig("fishing", "milestone", LootCommand.fishingMilestone);
/* 3960 */     ConfigHandler.writeIntConfig("fishing", "empSC", LootCommand.empSCs);
/* 3961 */     ConfigHandler.writeIntConfig("fishing", "yetiSC", LootCommand.yetiSCs);
/*      */   }
/*      */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\me\Danker\DankersSkyblockMod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */