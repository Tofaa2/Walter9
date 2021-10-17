/*     */ package io.github.quantizr.dungeonrooms;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParser;
/*     */ import io.github.quantizr.dungeonrooms.commands.RoomCommand;
/*     */ import io.github.quantizr.dungeonrooms.dungeons.catacombs.DungeonManager;
/*     */ import io.github.quantizr.dungeonrooms.dungeons.catacombs.RoomDetection;
/*     */ import io.github.quantizr.dungeonrooms.dungeons.catacombs.Waypoints;
/*     */ import io.github.quantizr.dungeonrooms.gui.WaypointsGUI;
/*     */ import io.github.quantizr.dungeonrooms.handlers.ConfigHandler;
/*     */ import io.github.quantizr.dungeonrooms.handlers.OpenLink;
/*     */ import io.github.quantizr.dungeonrooms.handlers.PacketHandler;
/*     */ import io.github.quantizr.dungeonrooms.handlers.TextRenderer;
/*     */ import io.github.quantizr.dungeonrooms.utils.Utils;
/*     */ import io.netty.channel.ChannelHandler;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.nio.file.Path;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.ExecutionException;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.Executors;
/*     */ import java.util.concurrent.Future;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.client.settings.KeyBinding;
/*     */ import net.minecraft.command.ICommand;
/*     */ import net.minecraft.event.ClickEvent;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.client.ClientCommandHandler;
/*     */ import net.minecraftforge.client.event.RenderGameOverlayEvent;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.fml.client.registry.ClientRegistry;
/*     */ import net.minecraftforge.fml.common.Loader;
/*     */ import net.minecraftforge.fml.common.Mod;
/*     */ import net.minecraftforge.fml.common.Mod.EventHandler;
/*     */ import net.minecraftforge.fml.common.event.FMLInitializationEvent;
/*     */ import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
/*     */ import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.gameevent.InputEvent;
/*     */ import net.minecraftforge.fml.common.gameevent.TickEvent;
/*     */ import net.minecraftforge.fml.common.network.FMLNetworkEvent;
/*     */ import net.minecraftforge.fml.common.versioning.ArtifactVersion;
/*     */ import net.minecraftforge.fml.common.versioning.DefaultArtifactVersion;
/*     */ import org.apache.logging.log4j.Level;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Mod(modid = "dungeonrooms", version = "3.2.4", acceptedMinecraftVersions = "[1.8.9]")
/*     */ public class DungeonRooms
/*     */ {
/*     */   public static final String MODID = "dungeonrooms";
/*     */   public static final String VERSION = "3.2.4";
/*  79 */   Minecraft mc = Minecraft.func_71410_x();
/*     */   
/*     */   public static Logger logger;
/*     */   public static JsonObject roomsJson;
/*     */   public static JsonObject waypointsJson;
/*  84 */   public static HashMap<String, HashMap<String, long[]>> ROOM_DATA = new HashMap<>();
/*     */   
/*     */   public static boolean usingSBPSecrets = false;
/*  87 */   public static KeyBinding[] keyBindings = new KeyBinding[3];
/*  88 */   public static String imageHotkeyOpen = "gui";
/*  89 */   static int tickAmount = 1;
/*     */   
/*  91 */   public static List<String> textToDisplay = null;
/*  92 */   public static int textLocX = 50;
/*  93 */   public static int textLocY = 5;
/*     */   
/*  95 */   public static List<String> motd = null;
/*     */   public static String configDir;
/*     */   public static boolean firstLogin = false;
/*     */   
/*     */   @EventHandler
/*     */   public void preInit(FMLPreInitializationEvent event) {
/* 101 */     ClientCommandHandler.instance.func_71560_a((ICommand)new RoomCommand());
/* 102 */     configDir = event.getModConfigurationDirectory().toString();
/*     */ 
/*     */     
/* 105 */     logger = LogManager.getLogger(DungeonRooms.class);
/* 106 */     Utils.setLogLevel(LogManager.getLogger(DungeonRooms.class), Level.INFO);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void init(FMLInitializationEvent event) {
/* 111 */     long time1 = System.currentTimeMillis();
/*     */ 
/*     */     
/* 114 */     List<Path> paths = Utils.getAllPaths("catacombs");
/* 115 */     ExecutorService executor = Executors.newFixedThreadPool(4);
/* 116 */     Future<HashMap<String, long[]>> future1x1 = executor.submit(() -> Utils.pathsToRoomData("1x1", paths));
/* 117 */     Future<HashMap<String, long[]>> future1x2 = executor.submit(() -> Utils.pathsToRoomData("1x2", paths));
/* 118 */     Future<HashMap<String, long[]>> future1x3 = executor.submit(() -> Utils.pathsToRoomData("1x3", paths));
/* 119 */     Future<HashMap<String, long[]>> future1x4 = executor.submit(() -> Utils.pathsToRoomData("1x4", paths));
/* 120 */     Future<HashMap<String, long[]>> future2x2 = executor.submit(() -> Utils.pathsToRoomData("2x2", paths));
/* 121 */     Future<HashMap<String, long[]>> futureLShape = executor.submit(() -> Utils.pathsToRoomData("L-shape", paths));
/* 122 */     Future<HashMap<String, long[]>> futurePuzzle = executor.submit(() -> Utils.pathsToRoomData("Puzzle", paths));
/* 123 */     Future<HashMap<String, long[]>> futureTrap = executor.submit(() -> Utils.pathsToRoomData("Trap", paths));
/*     */ 
/*     */     
/* 126 */     MinecraftForge.EVENT_BUS.register(this);
/* 127 */     MinecraftForge.EVENT_BUS.register(new DungeonManager());
/* 128 */     MinecraftForge.EVENT_BUS.register(new RoomDetection());
/* 129 */     MinecraftForge.EVENT_BUS.register(new Waypoints());
/*     */ 
/*     */     
/* 132 */     ConfigHandler.reloadConfig();
/*     */ 
/*     */     
/* 135 */     keyBindings[0] = new KeyBinding("Open Room Images in DSG/SBP", 24, "Dungeon Rooms Mod");
/* 136 */     keyBindings[1] = new KeyBinding("Open Waypoint Config Menu", 25, "Dungeon Rooms Mod");
/* 137 */     keyBindings[2] = new KeyBinding("Show Waypoints in Practice Mode", 23, "Dungeon Rooms Mod");
/* 138 */     for (KeyBinding keyBinding : keyBindings) {
/* 139 */       ClientRegistry.registerKeyBinding(keyBinding);
/*     */     }
/*     */ 
/*     */     
/* 143 */     try(BufferedReader roomsReader = new BufferedReader(new InputStreamReader(this.mc.func_110442_L()
/* 144 */             .func_110536_a(new ResourceLocation("dungeonrooms", "dungeonrooms.json")).func_110527_b())); 
/* 145 */         BufferedReader waypointsReader = new BufferedReader(new InputStreamReader(this.mc.func_110442_L()
/* 146 */             .func_110536_a(new ResourceLocation("dungeonrooms", "secretlocations.json")).func_110527_b()))) {
/*     */       
/* 148 */       Gson gson = new Gson();
/* 149 */       roomsJson = (JsonObject)gson.fromJson(roomsReader, JsonObject.class);
/* 150 */       logger.info("DungeonRooms: Loaded dungeonrooms.json");
/*     */       
/* 152 */       waypointsJson = (JsonObject)gson.fromJson(waypointsReader, JsonObject.class);
/* 153 */       logger.info("DungeonRooms: Loaded secretlocations.json");
/* 154 */     } catch (IOException e) {
/* 155 */       e.printStackTrace();
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 161 */       long time2 = System.currentTimeMillis();
/* 162 */       ROOM_DATA.put("1x1", future1x1.get());
/* 163 */       long time3 = System.currentTimeMillis();
/* 164 */       ROOM_DATA.put("1x2", future1x2.get());
/* 165 */       ROOM_DATA.put("1x3", future1x3.get());
/* 166 */       ROOM_DATA.put("1x4", future1x4.get());
/* 167 */       ROOM_DATA.put("2x2", future2x2.get());
/* 168 */       ROOM_DATA.put("L-shape", futureLShape.get());
/* 169 */       ROOM_DATA.put("Puzzle", futurePuzzle.get());
/* 170 */       ROOM_DATA.put("Trap", futureTrap.get());
/* 171 */       long time4 = System.currentTimeMillis();
/* 172 */       logger.debug("DungeonRooms: Time(ms) for init before get futures: " + (time2 - time1));
/* 173 */       logger.debug("DungeonRooms: Blocked Time(ms) for 1x1: " + (time3 - time2));
/* 174 */       logger.debug("DungeonRooms: Blocked Time(ms) remaining for other rooms: " + (time4 - time3));
/* 175 */     } catch (ExecutionException|InterruptedException e) {
/* 176 */       e.printStackTrace();
/*     */     } 
/* 178 */     executor.shutdown();
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void postInit(FMLPostInitializationEvent event) {
/* 183 */     usingSBPSecrets = Loader.isModLoaded("sbp");
/* 184 */     logger.info("DungeonRooms: SBP Dungeon Secrets detection: " + usingSBPSecrets);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onServerConnect(FMLNetworkEvent.ClientConnectedToServerEvent event) {
/* 194 */     if (this.mc.func_147104_D() == null)
/* 195 */       return;  if ((this.mc.func_147104_D()).field_78845_b.toLowerCase().contains("hypixel.")) {
/* 196 */       logger.info("DungeonRooms: Connecting to Hypixel...");
/*     */ 
/*     */       
/* 199 */       event.manager.channel().pipeline().addBefore("packet_handler", "drm_packet_handler", (ChannelHandler)new PacketHandler());
/* 200 */       logger.info("DungeonRooms: Packet Handler added");
/*     */       
/* 202 */       (new Thread(() -> {
/*     */             try {
/*     */               while (this.mc.field_71439_g == null) {
/*     */                 Thread.sleep(100L);
/*     */               }
/*     */               
/*     */               Thread.sleep(3000L);
/*     */               
/*     */               logger.info("DungeonRooms: Checking for conflicting keybindings...");
/*     */               
/*     */               Utils.checkForConflictingHotkeys();
/*     */               
/*     */               logger.info("DungeonRooms: Checking for updates...");
/*     */               
/*     */               URL url = new URL("https://api.github.com/repos/Quantizr/DungeonRoomsMod/releases/latest");
/*     */               
/*     */               URLConnection request = url.openConnection();
/*     */               request.connect();
/*     */               JsonParser json = new JsonParser();
/*     */               JsonObject latestRelease = json.parse(new InputStreamReader((InputStream)request.getContent())).getAsJsonObject();
/*     */               String latestTag = latestRelease.get("tag_name").getAsString();
/*     */               DefaultArtifactVersion currentVersion = new DefaultArtifactVersion("3.2.4");
/*     */               DefaultArtifactVersion latestVersion = new DefaultArtifactVersion(latestTag.substring(1));
/*     */               if (currentVersion.compareTo((ArtifactVersion)latestVersion) < 0) {
/*     */                 String releaseURL = "https://github.com/Quantizr/DungeonRoomsMod/releases/latest";
/*     */                 ChatComponentText update = new ChatComponentText(EnumChatFormatting.GREEN + "" + EnumChatFormatting.BOLD + "  [UPDATE]  ");
/*     */                 update.func_150255_a(update.func_150256_b().func_150241_a(new ClickEvent(ClickEvent.Action.OPEN_URL, releaseURL)));
/*     */                 this.mc.field_71439_g.func_145747_a((new ChatComponentText(EnumChatFormatting.RED + "Dungeon Rooms Mod is outdated. Please update to " + latestTag + ".\n")).func_150257_a((IChatComponent)update));
/*     */               } else {
/*     */                 logger.info("DungeonRooms: No update found");
/*     */               } 
/*     */               logger.info("DungeonRooms: Getting MOTD...");
/*     */               url = new URL("https://gist.githubusercontent.com/Quantizr/50ed64a7a0f3b28dc742d5268d7a3217/raw/");
/*     */               BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
/*     */               motd = new ArrayList<>();
/*     */               String line;
/*     */               while ((line = in.readLine()) != null) {
/*     */                 motd.add(line);
/*     */               }
/*     */               in.close();
/*     */               logger.info("DungeonRooms: MOTD has been checked");
/* 243 */             } catch (IOException|InterruptedException e) {
/*     */               this.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "Dungeon Rooms: An error has occured. See logs for more details."));
/*     */               
/*     */               e.printStackTrace();
/*     */             } 
/* 248 */           })).start();
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onTick(TickEvent.ClientTickEvent event) {
/* 254 */     if (event.phase != TickEvent.Phase.START)
/* 255 */       return;  EntityPlayerSP player = this.mc.field_71439_g;
/*     */     
/* 257 */     tickAmount++;
/* 258 */     if (tickAmount % 20 == 0 && 
/* 259 */       player != null) {
/* 260 */       Utils.checkForSkyblock();
/* 261 */       Utils.checkForCatacombs();
/* 262 */       tickAmount = 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onKey(InputEvent.KeyInputEvent event) {
/* 269 */     EntityPlayerSP player = (Minecraft.func_71410_x()).field_71439_g;
/* 270 */     if (keyBindings[0].func_151468_f()) {
/* 271 */       if (!Utils.inCatacombs) {
/* 272 */         player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "Dungeon Rooms: Use this hotkey inside of a dungeon room"));
/*     */         
/*     */         return;
/*     */       } 
/* 276 */       switch (imageHotkeyOpen) {
/*     */         case "gui":
/* 278 */           OpenLink.checkForLink("gui");
/*     */           break;
/*     */         case "dsg":
/* 281 */           OpenLink.checkForLink("dsg");
/*     */           break;
/*     */         case "sbp":
/* 284 */           OpenLink.checkForLink("sbp");
/*     */           break;
/*     */         default:
/* 287 */           player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "Dungeon Rooms: hotkeyOpen config value improperly set, do \"/room set <gui | dsg | sbp>\" to change the value"));
/*     */           break;
/*     */       } 
/*     */     
/*     */     } 
/* 292 */     if (keyBindings[1].func_151468_f()) {
/* 293 */       this.mc.func_152344_a(() -> this.mc.func_147108_a((GuiScreen)new WaypointsGUI()));
/*     */     }
/* 295 */     if (keyBindings[2].func_151468_f()) {
/* 296 */       if (Waypoints.enabled && !Waypoints.practiceModeOn) {
/* 297 */         player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "Dungeon Rooms: Run \"/room toggle practice\" to enable Practice Mode."));
/*     */       }
/* 299 */       else if (!Waypoints.enabled && Waypoints.practiceModeOn) {
/* 300 */         player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "Dungeon Rooms: Waypoints must be enabled for Practice Mode to work."));
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void renderPlayerInfo(RenderGameOverlayEvent.Post event) {
/* 308 */     if (event.type != RenderGameOverlayEvent.ElementType.ALL)
/* 309 */       return;  if (Utils.inSkyblock && 
/* 310 */       textToDisplay != null && !textToDisplay.isEmpty()) {
/* 311 */       ScaledResolution scaledResolution = new ScaledResolution(this.mc);
/* 312 */       int y = 0;
/* 313 */       for (String line : textToDisplay) {
/* 314 */         int roomStringWidth = this.mc.field_71466_p.func_78256_a(line);
/* 315 */         TextRenderer.drawText(this.mc, line, scaledResolution.func_78326_a() * textLocX / 100 - roomStringWidth / 2, scaledResolution
/* 316 */             .func_78328_b() * textLocY / 100 + y, 1.0D, true);
/* 317 */         y += this.mc.field_71466_p.field_78288_b;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\io\github\quantizr\dungeonrooms\DungeonRooms.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */