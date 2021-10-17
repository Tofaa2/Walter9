/*     */ package io.github.quantizr.dungeonrooms.commands;
/*     */ 
/*     */ import com.google.gson.JsonObject;
/*     */ import io.github.quantizr.dungeonrooms.DungeonRooms;
/*     */ import io.github.quantizr.dungeonrooms.dungeons.catacombs.DungeonManager;
/*     */ import io.github.quantizr.dungeonrooms.dungeons.catacombs.RoomDetection;
/*     */ import io.github.quantizr.dungeonrooms.dungeons.catacombs.Waypoints;
/*     */ import io.github.quantizr.dungeonrooms.gui.WaypointsGUI;
/*     */ import io.github.quantizr.dungeonrooms.handlers.ConfigHandler;
/*     */ import io.github.quantizr.dungeonrooms.handlers.OpenLink;
/*     */ import io.github.quantizr.dungeonrooms.utils.MapUtils;
/*     */ import io.github.quantizr.dungeonrooms.utils.Utils;
/*     */ import java.awt.Desktop;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.datatransfer.StringSelection;
/*     */ import java.io.IOException;
/*     */ import java.net.URI;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.multiplayer.WorldClient;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.command.CommandBase;
/*     */ import net.minecraft.command.ICommandSender;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.MovingObjectPosition;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RoomCommand
/*     */   extends CommandBase
/*     */ {
/*     */   public String func_71517_b() {
/*  57 */     return "room";
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_71518_a(ICommandSender arg0) {
/*  62 */     return "/" + func_71517_b();
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> func_71514_a() {
/*  67 */     return Collections.singletonList("drm");
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_82362_a() {
/*  72 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<String> func_180525_a(ICommandSender sender, String[] args, BlockPos pos) {
/*  78 */     if (args.length == 1) {
/*  79 */       return func_71530_a(args, new String[] { "help", "waypoints", "move", "toggle", "set", "discord" });
/*     */     }
/*  81 */     if (args.length > 1) {
/*  82 */       if (args[0].equalsIgnoreCase("toggle"))
/*  83 */         return func_71530_a(args, new String[] { "help", "gui", "waypointtext", "waypointboundingbox", "waypointbeacon" }); 
/*  84 */       if (args[0].equalsIgnoreCase("set")) {
/*  85 */         return func_71530_a(args, new String[] { "gui", "dsg", "sbp" });
/*     */       }
/*     */     } 
/*  88 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_71515_b(ICommandSender arg0, String[] arg1) {
/*  93 */     Minecraft mc = Minecraft.func_71410_x();
/*  94 */     EntityPlayer player = (EntityPlayer)arg0;
/*     */     
/*  96 */     if (arg1.length < 1) {
/*  97 */       if (!Utils.inCatacombs) {
/*  98 */         player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "Dungeon Rooms: Use this command in dungeons or run \"/room help\" for additional options"));
/*     */       
/*     */       }
/* 101 */       else if (DungeonManager.gameStage == 2) {
/* 102 */         for (String line : DungeonRooms.textToDisplay) {
/* 103 */           player.func_145747_a((IChatComponent)new ChatComponentText(line));
/*     */         }
/* 105 */         player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.GREEN + "Dungeon Rooms: You can also run \"/room help\" for additional options"));
/*     */       } else {
/*     */         
/* 108 */         player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "Dungeon Rooms: Use this command while clearing rooms or run \"/room help\" for additional options"));
/*     */       } 
/*     */     } else {
/*     */       String toggleHelp; WorldClient worldClient;
/*     */       BlockPos playerPos, entrancePos, batPos;
/* 113 */       switch (arg1[0].toLowerCase()) {
/*     */         case "help":
/* 115 */           player.func_145747_a((IChatComponent)new ChatComponentText("\n" + EnumChatFormatting.GOLD + "Dungeon Rooms Mod Version " + "3.2.4" + "\n" + EnumChatFormatting.DARK_PURPLE + "Hotkeys: (Configurable in Controls Menu)\n" + EnumChatFormatting.AQUA + " " + 
/*     */ 
/*     */                 
/* 118 */                 GameSettings.func_74298_c(DungeonRooms.keyBindings[1].func_151463_i()) + EnumChatFormatting.WHITE + " - Opens Secret Waypoints configuration GUI\n" + EnumChatFormatting.AQUA + " " + 
/* 119 */                 GameSettings.func_74298_c(DungeonRooms.keyBindings[0].func_151463_i()) + EnumChatFormatting.WHITE + " - (old) Opens images of secret locations\n" + EnumChatFormatting.AQUA + " " + 
/* 120 */                 GameSettings.func_74298_c(DungeonRooms.keyBindings[2].func_151463_i()) + EnumChatFormatting.WHITE + " - View waypoints in Practice Mode (\"/room toggle practice\")\n" + EnumChatFormatting.DARK_PURPLE + "Commands:\n" + EnumChatFormatting.AQUA + " /room" + EnumChatFormatting.WHITE + " - Tells you in chat what room you are standing in.\n" + EnumChatFormatting.AQUA + " /room help" + EnumChatFormatting.WHITE + " - Displays this message.\n" + EnumChatFormatting.AQUA + " /room waypoints" + EnumChatFormatting.WHITE + " - Opens Secret Waypoints config GUI, alternatively can be opened with hotkey\n" + EnumChatFormatting.AQUA + " /room move <x> <y>" + EnumChatFormatting.WHITE + " - Moves the GUI room name text to a coordinate. <x> and <y> are numbers between 0 and 100. Default is 50 for <x> and 5 for <y>.\n" + EnumChatFormatting.AQUA + " /room toggle [argument]" + EnumChatFormatting.WHITE + " - Run \"/room toggle help\" for full list of toggles.\n" + EnumChatFormatting.AQUA + " /room set <gui | dsg | sbp>" + EnumChatFormatting.WHITE + " - Configure whether the hotkey opens the selector GUI or directly goes to DSG/SBP.\n" + EnumChatFormatting.AQUA + " /room discord" + EnumChatFormatting.WHITE + " - Opens the Discord invite for this mod in your browser.\n"));
/*     */           return;
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
/*     */         
/*     */         case "gui":
/*     */         case "open":
/* 137 */           if (!Utils.inCatacombs) {
/* 138 */             player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "Dungeon Rooms: Use this command in dungeons"));
/*     */             
/*     */             return;
/*     */           } 
/* 142 */           OpenLink.checkForLink("gui");
/*     */           return;
/*     */         
/*     */         case "dsg":
/* 146 */           if (!Utils.inCatacombs) {
/* 147 */             player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "Dungeon Rooms: Use this command in dungeons"));
/*     */             
/*     */             return;
/*     */           } 
/* 151 */           OpenLink.checkForLink("dsg");
/*     */           return;
/*     */         
/*     */         case "sbp":
/* 155 */           if (!Utils.inCatacombs) {
/* 156 */             player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "Dungeon Rooms: Use this command in dungeons"));
/*     */             
/*     */             return;
/*     */           } 
/* 160 */           OpenLink.checkForLink("sbp");
/*     */           return;
/*     */         
/*     */         case "set":
/* 164 */           switch (arg1[1].toLowerCase()) {
/*     */             case "gui":
/* 166 */               DungeonRooms.imageHotkeyOpen = "gui";
/* 167 */               player.func_145747_a((IChatComponent)new ChatComponentText("Dungeon Rooms: Hotkey has been set to open: GUI"));
/* 168 */               ConfigHandler.writeStringConfig("gui", "hotkeyOpen", "gui");
/*     */               return;
/*     */             case "dsg":
/* 171 */               DungeonRooms.imageHotkeyOpen = "dsg";
/* 172 */               player.func_145747_a((IChatComponent)new ChatComponentText("Dungeon Rooms: Hotkey has been set to open: DSG"));
/* 173 */               ConfigHandler.writeStringConfig("gui", "hotkeyOpen", "dsg");
/*     */               return;
/*     */             case "sbp":
/* 176 */               DungeonRooms.imageHotkeyOpen = "sbp";
/* 177 */               player.func_145747_a((IChatComponent)new ChatComponentText("Dungeon Rooms: Hotkey has been set to open: SBP"));
/* 178 */               ConfigHandler.writeStringConfig("gui", "hotkeyOpen", "sbp");
/*     */               return;
/*     */           } 
/* 181 */           player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "Dungeon Rooms: Valid options are <gui | dsg | sbp>"));
/*     */           return;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case "wp":
/*     */         case "waypoint":
/*     */         case "waypoints":
/* 190 */           (new Thread(() -> mc.func_152344_a(()))).start();
/*     */           return;
/*     */         
/*     */         case "move":
/* 194 */           DungeonRooms.textLocX = Integer.parseInt(arg1[1]);
/* 195 */           DungeonRooms.textLocY = Integer.parseInt(arg1[2]);
/* 196 */           ConfigHandler.writeIntConfig("gui", "scaleX", DungeonRooms.textLocX);
/* 197 */           ConfigHandler.writeIntConfig("gui", "scaleY", DungeonRooms.textLocY);
/* 198 */           player.func_145747_a((IChatComponent)new ChatComponentText("Dungeon Rooms: Room GUI has been moved to " + arg1[1] + ", " + arg1[2]));
/*     */           return;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case "toggle":
/* 207 */           toggleHelp = "\n" + EnumChatFormatting.GOLD + " Dungeon Rooms Mod Toggle Commands:\n" + EnumChatFormatting.AQUA + " /room toggle gui" + EnumChatFormatting.WHITE + " - Toggles displaying current room in gui.\n" + EnumChatFormatting.AQUA + " /room toggle motd" + EnumChatFormatting.WHITE + " - Toggles displaying the Welcome/MOTD message at the start of a dungeon run.\n" + EnumChatFormatting.AQUA + " /room toggle practice" + EnumChatFormatting.WHITE + " - Toggles Practice Mode, where waypoints are only displayed when holding down " + GameSettings.func_74298_c(DungeonRooms.keyBindings[2].func_151463_i()) + "\".\n" + EnumChatFormatting.AQUA + " /room toggle waypoints" + EnumChatFormatting.WHITE + " - Toggles Waypoints, does the same thing as pressing \"" + GameSettings.func_74298_c(DungeonRooms.keyBindings[1].func_151463_i()) + "\" then clicking \"Waypoints\".\n" + EnumChatFormatting.AQUA + " /room toggle waypointtext" + EnumChatFormatting.WHITE + " - Toggles displaying waypoint names above waypoints.\n" + EnumChatFormatting.AQUA + " /room toggle waypointboundingbox" + EnumChatFormatting.WHITE + " - Toggles displaying the bounding box on waypoints.\n" + EnumChatFormatting.AQUA + " /room toggle waypointbeacon" + EnumChatFormatting.WHITE + " - Toggles displaying the beacon above waypoints.\n";
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 212 */           if (arg1.length == 1) {
/* 213 */             player.func_145747_a((IChatComponent)new ChatComponentText(toggleHelp));
/*     */           } else {
/*     */             
/* 216 */             switch (arg1[1].toLowerCase()) {
/*     */               case "gui":
/* 218 */                 DungeonManager.guiToggled = !DungeonManager.guiToggled;
/* 219 */                 ConfigHandler.writeBooleanConfig("toggles", "guiToggled", DungeonManager.guiToggled);
/* 220 */                 player.func_145747_a((IChatComponent)new ChatComponentText("Dungeon Rooms: Display room names in GUI has been set to: " + DungeonManager.guiToggled));
/*     */                 return;
/*     */               
/*     */               case "welcome":
/*     */               case "motd":
/* 225 */                 DungeonManager.motdToggled = !DungeonManager.motdToggled;
/* 226 */                 ConfigHandler.writeBooleanConfig("toggles", "motdToggled", DungeonManager.motdToggled);
/* 227 */                 player.func_145747_a((IChatComponent)new ChatComponentText("Dungeon Rooms: Display Welcome/MOTD has been set to: " + DungeonManager.motdToggled));
/*     */                 return;
/*     */               
/*     */               case "practice":
/*     */               case "practicemode":
/* 232 */                 Waypoints.practiceModeOn = !Waypoints.practiceModeOn;
/* 233 */                 ConfigHandler.writeBooleanConfig("waypoint", "practiceModeOn", Waypoints.practiceModeOn);
/* 234 */                 if (Waypoints.practiceModeOn) {
/* 235 */                   player.func_145747_a((IChatComponent)new ChatComponentText("§eDungeon Rooms: Practice Mode has been enabled.\n§e Waypoints will only show up while you are pressing \"" + 
/* 236 */                         GameSettings.func_74298_c(DungeonRooms.keyBindings[2].func_151463_i()) + "\".\n§r (Hotkey is configurable in Minecraft Controls menu)"));
/*     */                 }
/*     */                 else {
/*     */                   
/* 240 */                   player.func_145747_a((IChatComponent)new ChatComponentText("§eDungeon Rooms: Practice Mode has been disabled."));
/*     */                 } 
/*     */                 return;
/*     */               
/*     */               case "waypoint":
/*     */               case "waypoints":
/* 246 */                 Waypoints.enabled = !Waypoints.enabled;
/* 247 */                 ConfigHandler.writeBooleanConfig("waypoint", "waypointsToggled", Waypoints.enabled);
/* 248 */                 if (Waypoints.enabled) {
/* 249 */                   player.func_145747_a((IChatComponent)new ChatComponentText("§eDungeon Rooms: Waypoints will now automatically show up when you enter a new dungeon room."));
/*     */                 } else {
/* 251 */                   player.func_145747_a((IChatComponent)new ChatComponentText("§eDungeon Rooms: Waypoints have been disabled."));
/*     */                 } 
/*     */                 return;
/*     */               
/*     */               case "text":
/*     */               case "waypointtext":
/* 257 */                 Waypoints.showWaypointText = !Waypoints.showWaypointText;
/* 258 */                 ConfigHandler.writeBooleanConfig("waypoint", "showWaypointText", Waypoints.showWaypointText);
/* 259 */                 player.func_145747_a((IChatComponent)new ChatComponentText("Dungeon Rooms: Show Waypoint Text has been set to: " + Waypoints.showWaypointText));
/*     */                 return;
/*     */               
/*     */               case "boundingbox":
/*     */               case "waypointboundingbox":
/* 264 */                 Waypoints.showBoundingBox = !Waypoints.showBoundingBox;
/* 265 */                 ConfigHandler.writeBooleanConfig("waypoint", "showBoundingBox", Waypoints.showBoundingBox);
/* 266 */                 player.func_145747_a((IChatComponent)new ChatComponentText("Dungeon Rooms: Show Waypoint Bounding Box has been set to: " + Waypoints.showBoundingBox));
/*     */                 return;
/*     */               
/*     */               case "beacon":
/*     */               case "waypointbeacon":
/* 271 */                 Waypoints.showBeacon = !Waypoints.showBeacon;
/* 272 */                 ConfigHandler.writeBooleanConfig("waypoint", "showBeacon", Waypoints.showBeacon);
/* 273 */                 player.func_145747_a((IChatComponent)new ChatComponentText("Dungeon Rooms: Show Waypoint Beacon has been set to: " + Waypoints.showBeacon));
/*     */                 return;
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
/*     */               case "override":
/* 286 */                 Utils.dungeonOverride = !Utils.dungeonOverride;
/* 287 */                 player.func_145747_a((IChatComponent)new ChatComponentText("Dungeon Rooms: Force inCatacombs has been set to: " + Utils.dungeonOverride));
/*     */                 return;
/*     */             } 
/*     */ 
/*     */             
/* 292 */             player.func_145747_a((IChatComponent)new ChatComponentText(toggleHelp));
/*     */           } 
/*     */           return;
/*     */ 
/*     */ 
/*     */         
/*     */         case "reload":
/* 299 */           ConfigHandler.reloadConfig();
/* 300 */           player.func_145747_a((IChatComponent)new ChatComponentText("Dungeon Rooms: Reloaded config file"));
/*     */           return;
/*     */         
/*     */         case "discord":
/*     */           try {
/* 305 */             player.func_145747_a((IChatComponent)new ChatComponentText("Dungeon Rooms: Opening Dungeon Rooms Discord invite in browser..."));
/* 306 */             Desktop.getDesktop().browse(new URI("https://discord.gg/7B5RbsArYK"));
/* 307 */           } catch (IOException|java.net.URISyntaxException e) {
/* 308 */             e.printStackTrace();
/*     */           } 
/*     */           return;
/*     */         
/*     */         case "relative":
/* 313 */           if (!RoomDetection.roomDirection.equals("undefined") && RoomDetection.roomCorner != null) {
/* 314 */             if (mc.field_71476_x != null && mc.field_71476_x.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK && mc.field_71476_x.func_178782_a() != null) {
/* 315 */               BlockPos relativePos = MapUtils.actualToRelative(mc.field_71476_x.func_178782_a(), RoomDetection.roomDirection, RoomDetection.roomCorner);
/* 316 */               player.func_145747_a((IChatComponent)new ChatComponentText("Dungeon Rooms: You are looking at relative blockPos: " + relativePos));
/*     */             } 
/*     */           } else {
/* 319 */             player.func_145747_a((IChatComponent)new ChatComponentText("Dungeon Rooms: Unable to get relative blockPos at this time."));
/*     */           } 
/*     */           return;
/*     */         
/*     */         case "json":
/* 324 */           if (!Utils.inCatacombs && DungeonManager.gameStage != 2 && DungeonManager.gameStage != 3) {
/* 325 */             player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "Dungeon Rooms: Use this command in dungeons"));
/*     */             
/*     */             return;
/*     */           } 
/* 329 */           if (DungeonRooms.roomsJson.get(RoomDetection.roomName) != null) {
/* 330 */             JsonObject json = DungeonRooms.roomsJson.get(RoomDetection.roomName).getAsJsonObject();
/* 331 */             json.addProperty("name", RoomDetection.roomName);
/* 332 */             player.func_145747_a((IChatComponent)new ChatComponentText(json.toString()));
/*     */           } 
/*     */           return;
/*     */ 
/*     */         
/*     */         case "roominfo":
/* 338 */           if (!Utils.inCatacombs || DungeonManager.gameStage != 2) {
/* 339 */             player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "Dungeon Rooms: Not in room clearing phase of dungeons"));
/*     */           }
/*     */           
/* 342 */           if (DungeonManager.entranceMapCorners != null) {
/* 343 */             player.func_145747_a((IChatComponent)new ChatComponentText("dev: entranceMapCorners = " + new ArrayList(Arrays.asList((Object[])DungeonManager.entranceMapCorners))));
/*     */           } else {
/* 345 */             player.func_145747_a((IChatComponent)new ChatComponentText("dev: entranceMapCorners = null"));
/*     */           } 
/* 347 */           if (DungeonManager.entrancePhysicalNWCorner != null) {
/* 348 */             player.func_145747_a((IChatComponent)new ChatComponentText("dev: entrancePhysicalNWCorner = " + DungeonManager.entrancePhysicalNWCorner));
/*     */           } else {
/* 350 */             player.func_145747_a((IChatComponent)new ChatComponentText("dev: entrancePhysicalNWCorner = null"));
/*     */           } 
/* 352 */           player.func_145747_a((IChatComponent)new ChatComponentText("dev: currentMapSegments = " + RoomDetection.currentMapSegments));
/* 353 */           player.func_145747_a((IChatComponent)new ChatComponentText("dev: currentPhysicalSegments = " + RoomDetection.currentPhysicalSegments));
/* 354 */           player.func_145747_a((IChatComponent)new ChatComponentText("dev: roomName = " + RoomDetection.roomName));
/* 355 */           player.func_145747_a((IChatComponent)new ChatComponentText("dev: roomSize = " + RoomDetection.roomSize));
/* 356 */           player.func_145747_a((IChatComponent)new ChatComponentText("dev: roomColor = " + RoomDetection.roomColor));
/* 357 */           player.func_145747_a((IChatComponent)new ChatComponentText("dev: roomCategory = " + RoomDetection.roomCategory));
/*     */           
/* 359 */           player.func_145747_a((IChatComponent)new ChatComponentText("dev: roomDirection = " + RoomDetection.roomDirection));
/* 360 */           if (RoomDetection.roomCorner != null) {
/* 361 */             player.func_145747_a((IChatComponent)new ChatComponentText("dev: roomCorner = " + RoomDetection.roomCorner));
/*     */           } else {
/* 363 */             player.func_145747_a((IChatComponent)new ChatComponentText("dev: roomCorner = null"));
/*     */           } 
/*     */           return;
/*     */ 
/*     */         
/*     */         case "blocksused":
/* 369 */           if (!Utils.inCatacombs || DungeonManager.gameStage != 2) {
/* 370 */             player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "Dungeon Rooms: Not in room clearing phase of dungeons"));
/*     */           }
/*     */           
/* 373 */           player.func_145747_a((IChatComponent)new ChatComponentText(RoomDetection.blocksUsed.toString()));
/*     */           return;
/*     */ 
/*     */         
/*     */         case "add":
/* 378 */           worldClient = mc.field_71441_e;
/* 379 */           if (!Utils.inCatacombs || DungeonManager.gameStage != 2 || RoomDetection.roomDirection.equals("undefined") || RoomDetection.roomCorner == null) {
/* 380 */             player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "Dungeon Rooms: Current dungeon room is undefined"));
/*     */             
/*     */             return;
/*     */           } 
/* 384 */           switch (arg1[1].toLowerCase()) {
/*     */             case "chest":
/* 386 */               if (mc.field_71476_x != null && mc.field_71476_x.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK && mc.field_71476_x.func_178782_a() != null) {
/* 387 */                 BlockPos viewingPos = MapUtils.actualToRelative(mc.field_71476_x.func_178782_a(), RoomDetection.roomDirection, RoomDetection.roomCorner);
/* 388 */                 if (worldClient.func_180495_p(mc.field_71476_x.func_178782_a()).func_177230_c() == Blocks.field_150486_ae) {
/* 389 */                   player.func_145747_a((IChatComponent)new ChatComponentText("{\n  \"secretName\":\"# - Chest\",\n  \"category\":\"chest\",\n  \"x\":" + viewingPos
/*     */ 
/*     */                         
/* 392 */                         .func_177958_n() + ",\n  \"y\":" + viewingPos
/* 393 */                         .func_177956_o() + ",\n  \"z\":" + viewingPos
/* 394 */                         .func_177952_p() + "\n}"));
/*     */                   
/* 396 */                   Toolkit.getDefaultToolkit()
/* 397 */                     .getSystemClipboard()
/* 398 */                     .setContents(new StringSelection("{\n  \"secretName\":\"# - Chest\",\n  \"category\":\"chest\",\n  \"x\":" + viewingPos
/*     */ 
/*     */ 
/*     */                         
/* 402 */                         .func_177958_n() + ",\n  \"y\":" + viewingPos
/* 403 */                         .func_177956_o() + ",\n  \"z\":" + viewingPos
/* 404 */                         .func_177952_p() + "\n}"), null);
/*     */                 
/*     */                 }
/*     */                 else {
/*     */                   
/* 409 */                   player.func_145747_a((IChatComponent)new ChatComponentText("You are not looking at a Chest Secret"));
/*     */                 } 
/*     */               } else {
/* 412 */                 player.func_145747_a((IChatComponent)new ChatComponentText("You are not looking at anything"));
/*     */               } 
/*     */               return;
/*     */             case "wither":
/* 416 */               if (mc.field_71476_x != null && mc.field_71476_x.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK && mc.field_71476_x.func_178782_a() != null) {
/* 417 */                 BlockPos viewingPos = MapUtils.actualToRelative(mc.field_71476_x.func_178782_a(), RoomDetection.roomDirection, RoomDetection.roomCorner);
/* 418 */                 if (worldClient.func_180495_p(mc.field_71476_x.func_178782_a()).func_177230_c() == Blocks.field_150465_bP) {
/* 419 */                   player.func_145747_a((IChatComponent)new ChatComponentText("{\n  \"secretName\":\"# - Wither Essence\",\n  \"category\":\"wither\",\n  \"x\":" + viewingPos
/*     */ 
/*     */                         
/* 422 */                         .func_177958_n() + ",\n  \"y\":" + viewingPos
/* 423 */                         .func_177956_o() + ",\n  \"z\":" + viewingPos
/* 424 */                         .func_177952_p() + "\n}"));
/*     */                   
/* 426 */                   Toolkit.getDefaultToolkit()
/* 427 */                     .getSystemClipboard()
/* 428 */                     .setContents(new StringSelection("{\n  \"secretName\":\"# - Wither Essence\",\n  \"category\":\"wither\",\n  \"x\":" + viewingPos
/*     */ 
/*     */ 
/*     */                         
/* 432 */                         .func_177958_n() + ",\n  \"y\":" + viewingPos
/* 433 */                         .func_177956_o() + ",\n  \"z\":" + viewingPos
/* 434 */                         .func_177952_p() + "\n}"), null);
/*     */                 
/*     */                 }
/*     */                 else {
/*     */                   
/* 439 */                   player.func_145747_a((IChatComponent)new ChatComponentText("You are not looking at a Wither Essence Secret"));
/*     */                 } 
/*     */               } else {
/* 442 */                 player.func_145747_a((IChatComponent)new ChatComponentText("You are not looking at anything"));
/*     */               } 
/*     */               return;
/*     */             case "superboom":
/* 446 */               if (mc.field_71476_x != null && mc.field_71476_x.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK && mc.field_71476_x.func_178782_a() != null) {
/* 447 */                 BlockPos viewingPos = MapUtils.actualToRelative(mc.field_71476_x.func_178782_a(), RoomDetection.roomDirection, RoomDetection.roomCorner);
/* 448 */                 if (worldClient.func_180495_p(mc.field_71476_x.func_178782_a()).func_177230_c() == Blocks.field_150417_aV) {
/* 449 */                   player.func_145747_a((IChatComponent)new ChatComponentText("{\n  \"secretName\":\"# - Superboom\",\n  \"category\":\"superboom\",\n  \"x\":" + viewingPos
/*     */ 
/*     */                         
/* 452 */                         .func_177958_n() + ",\n  \"y\":" + viewingPos
/* 453 */                         .func_177956_o() + ",\n  \"z\":" + viewingPos
/* 454 */                         .func_177952_p() + "\n}"));
/*     */                   
/* 456 */                   Toolkit.getDefaultToolkit()
/* 457 */                     .getSystemClipboard()
/* 458 */                     .setContents(new StringSelection("{\n  \"secretName\":\"# - Superboom\",\n  \"category\":\"superboom\",\n  \"x\":" + viewingPos
/*     */ 
/*     */ 
/*     */                         
/* 462 */                         .func_177958_n() + ",\n  \"y\":" + viewingPos
/* 463 */                         .func_177956_o() + ",\n  \"z\":" + viewingPos
/* 464 */                         .func_177952_p() + "\n}"), null);
/*     */                 
/*     */                 }
/*     */                 else {
/*     */                   
/* 469 */                   player.func_145747_a((IChatComponent)new ChatComponentText("You are not looking at a Superboom entrance"));
/*     */                 } 
/*     */               } else {
/* 472 */                 player.func_145747_a((IChatComponent)new ChatComponentText("You are not looking at anything"));
/*     */               } 
/*     */               return;
/*     */             case "lever":
/* 476 */               if (mc.field_71476_x != null && mc.field_71476_x.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK && mc.field_71476_x.func_178782_a() != null) {
/* 477 */                 BlockPos viewingPos = MapUtils.actualToRelative(mc.field_71476_x.func_178782_a(), RoomDetection.roomDirection, RoomDetection.roomCorner);
/* 478 */                 if (worldClient.func_180495_p(mc.field_71476_x.func_178782_a()).func_177230_c() == Blocks.field_150442_at) {
/* 479 */                   player.func_145747_a((IChatComponent)new ChatComponentText("{\n  \"secretName\":\"# - Lever\",\n  \"category\":\"lever\",\n  \"x\":" + viewingPos
/*     */ 
/*     */                         
/* 482 */                         .func_177958_n() + ",\n  \"y\":" + viewingPos
/* 483 */                         .func_177956_o() + ",\n  \"z\":" + viewingPos
/* 484 */                         .func_177952_p() + "\n}"));
/*     */                   
/* 486 */                   Toolkit.getDefaultToolkit()
/* 487 */                     .getSystemClipboard()
/* 488 */                     .setContents(new StringSelection("{\n  \"secretName\":\"# - Lever\",\n  \"category\":\"lever\",\n  \"x\":" + viewingPos
/*     */ 
/*     */ 
/*     */                         
/* 492 */                         .func_177958_n() + ",\n  \"y\":" + viewingPos
/* 493 */                         .func_177956_o() + ",\n  \"z\":" + viewingPos
/* 494 */                         .func_177952_p() + "\n}"), null);
/*     */                 
/*     */                 }
/*     */                 else {
/*     */                   
/* 499 */                   player.func_145747_a((IChatComponent)new ChatComponentText("You are not looking at a Lever"));
/*     */                 } 
/*     */               } else {
/* 502 */                 player.func_145747_a((IChatComponent)new ChatComponentText("You are not looking at anything"));
/*     */               } 
/*     */               return;
/*     */             case "fairysoul":
/* 506 */               if (mc.field_71476_x != null && mc.field_71476_x.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK && mc.field_71476_x.func_178782_a() != null) {
/* 507 */                 BlockPos viewingPos = MapUtils.actualToRelative(mc.field_71476_x.func_178782_a().func_177981_b(1), RoomDetection.roomDirection, RoomDetection.roomCorner);
/* 508 */                 if (worldClient.func_180495_p(mc.field_71476_x.func_178782_a().func_177981_b(1)).func_177230_c() == Blocks.field_150350_a) {
/* 509 */                   player.func_145747_a((IChatComponent)new ChatComponentText("{\n  \"secretName\":\"Fairy Soul\",\n  \"category\":\"fairysoul\",\n  \"x\":" + viewingPos
/*     */ 
/*     */                         
/* 512 */                         .func_177958_n() + ",\n  \"y\":" + viewingPos
/* 513 */                         .func_177956_o() + ",\n  \"z\":" + viewingPos
/* 514 */                         .func_177952_p() + "\n}"));
/*     */                   
/* 516 */                   Toolkit.getDefaultToolkit()
/* 517 */                     .getSystemClipboard()
/* 518 */                     .setContents(new StringSelection("{\n  \"secretName\":\"Fairy Soul\",\n  \"category\":\"fairysoul\",\n  \"x\":" + viewingPos
/*     */ 
/*     */ 
/*     */                         
/* 522 */                         .func_177958_n() + ",\n  \"y\":" + viewingPos
/* 523 */                         .func_177956_o() + ",\n  \"z\":" + viewingPos
/* 524 */                         .func_177952_p() + "\n}"), null);
/*     */                 
/*     */                 }
/*     */                 else {
/*     */                   
/* 529 */                   player.func_145747_a((IChatComponent)new ChatComponentText("You are not looking at the block below a Fairy Soul"));
/*     */                 } 
/*     */               } else {
/* 532 */                 player.func_145747_a((IChatComponent)new ChatComponentText("You are not looking at anything"));
/*     */               } 
/*     */               return;
/*     */             case "item":
/* 536 */               playerPos = MapUtils.actualToRelative(new BlockPos(player.field_70165_t, player.field_70163_u, player.field_70161_v), RoomDetection.roomDirection, RoomDetection.roomCorner);
/* 537 */               player.func_145747_a((IChatComponent)new ChatComponentText("{\n  \"secretName\":\"# - Item\",\n  \"category\":\"item\",\n  \"x\":" + playerPos
/*     */ 
/*     */                     
/* 540 */                     .func_177958_n() + ",\n  \"y\":" + playerPos
/* 541 */                     .func_177956_o() + ",\n  \"z\":" + playerPos
/* 542 */                     .func_177952_p() + "\n}"));
/*     */               
/* 544 */               Toolkit.getDefaultToolkit()
/* 545 */                 .getSystemClipboard()
/* 546 */                 .setContents(new StringSelection("{\n  \"secretName\":\"# - Item\",\n  \"category\":\"item\",\n  \"x\":" + playerPos
/*     */ 
/*     */ 
/*     */                     
/* 550 */                     .func_177958_n() + ",\n  \"y\":" + playerPos
/* 551 */                     .func_177956_o() + ",\n  \"z\":" + playerPos
/* 552 */                     .func_177952_p() + "\n}"), null);
/*     */               return;
/*     */ 
/*     */ 
/*     */             
/*     */             case "entrance":
/* 558 */               entrancePos = MapUtils.actualToRelative(new BlockPos(player.field_70165_t, player.field_70163_u + 1.0D, player.field_70161_v), RoomDetection.roomDirection, RoomDetection.roomCorner);
/* 559 */               player.func_145747_a((IChatComponent)new ChatComponentText("{\n  \"secretName\":\"# - Entrance\",\n  \"category\":\"entrance\",\n  \"x\":" + entrancePos
/*     */ 
/*     */                     
/* 562 */                     .func_177958_n() + ",\n  \"y\":" + entrancePos
/* 563 */                     .func_177956_o() + ",\n  \"z\":" + entrancePos
/* 564 */                     .func_177952_p() + "\n}"));
/*     */               
/* 566 */               Toolkit.getDefaultToolkit()
/* 567 */                 .getSystemClipboard()
/* 568 */                 .setContents(new StringSelection("{\n  \"secretName\":\"# - Entrance\",\n  \"category\":\"entrance\",\n  \"x\":" + entrancePos
/*     */ 
/*     */ 
/*     */                     
/* 572 */                     .func_177958_n() + ",\n  \"y\":" + entrancePos
/* 573 */                     .func_177956_o() + ",\n  \"z\":" + entrancePos
/* 574 */                     .func_177952_p() + "\n}"), null);
/*     */               return;
/*     */ 
/*     */ 
/*     */             
/*     */             case "bat":
/* 580 */               batPos = MapUtils.actualToRelative(new BlockPos(player.field_70165_t, player.field_70163_u + 1.0D, player.field_70161_v), RoomDetection.roomDirection, RoomDetection.roomCorner);
/* 581 */               player.func_145747_a((IChatComponent)new ChatComponentText("{\n  \"secretName\":\"# - Bat\",\n  \"category\":\"bat\",\n  \"x\":" + batPos
/*     */ 
/*     */                     
/* 584 */                     .func_177958_n() + ",\n  \"y\":" + batPos
/* 585 */                     .func_177956_o() + ",\n  \"z\":" + batPos
/* 586 */                     .func_177952_p() + "\n}"));
/*     */               
/* 588 */               Toolkit.getDefaultToolkit()
/* 589 */                 .getSystemClipboard()
/* 590 */                 .setContents(new StringSelection("{\n  \"secretName\":\"# - Bat\",\n  \"category\":\"bat\",\n  \"x\":" + batPos
/*     */ 
/*     */ 
/*     */                     
/* 594 */                     .func_177958_n() + ",\n  \"y\":" + batPos
/* 595 */                     .func_177956_o() + ",\n  \"z\":" + batPos
/* 596 */                     .func_177952_p() + "\n}"), null);
/*     */               return;
/*     */           } 
/*     */ 
/*     */ 
/*     */           
/* 602 */           player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "Dungeon Rooms: Valid options are <chest | wither | superboom | lever | fairysoul | item | entrance | bat>"));
/*     */           return;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 609 */       player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "Dungeon Rooms: Run \"/room\" by itself to see the room name or run \"/room help\" for additional options"));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\io\github\quantizr\dungeonrooms\commands\RoomCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */