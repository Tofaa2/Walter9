/*     */ package io.github.quantizr.dungeonrooms.dungeons.catacombs;
/*     */ 
/*     */ import io.github.quantizr.dungeonrooms.DungeonRooms;
/*     */ import io.github.quantizr.dungeonrooms.utils.MapUtils;
/*     */ import io.github.quantizr.dungeonrooms.utils.Utils;
/*     */ import java.awt.Point;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraftforge.client.event.ClientChatReceivedEvent;
/*     */ import net.minecraftforge.event.world.WorldEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.EventPriority;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.gameevent.TickEvent;
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
/*     */ public class DungeonManager
/*     */ {
/*  41 */   Minecraft mc = Minecraft.func_71410_x();
/*  42 */   public static int gameStage = 0;
/*     */   
/*     */   public static boolean guiToggled = true;
/*     */   
/*     */   public static boolean motdToggled = true;
/*     */   
/*     */   public static Integer[][] map;
/*     */   public static Point[] entranceMapCorners;
/*     */   public static Point entrancePhysicalNWCorner;
/*  51 */   public static int tickAmount = 0;
/*     */   
/*     */   boolean bloodDone = false;
/*     */   
/*     */   boolean oddRun = true;
/*     */   
/*     */   @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
/*     */   public void onChat(ClientChatReceivedEvent event) {
/*  59 */     if (!Utils.inCatacombs)
/*  60 */       return;  String message = event.message.func_150254_d();
/*     */ 
/*     */     
/*  63 */     if (message.startsWith("§e[NPC] §bMort§f: §rHere, I found this map when I first entered the dungeon.§r")) {
/*  64 */       gameStage = 2;
/*  65 */       DungeonRooms.logger.info("DungeonRooms: gameStage set to " + gameStage);
/*  66 */     } else if (message.startsWith("§r§c[BOSS] The Watcher§r§f: You have proven yourself. You may pass.§r")) {
/*  67 */       this.bloodDone = true;
/*  68 */       DungeonRooms.logger.info("DungeonRooms: bloodDone has been set to True");
/*  69 */     } else if (this.bloodDone && ((message.startsWith("§r§c[BOSS] ") && !message.contains(" The Watcher§r§f:")) || message.startsWith("§r§4[BOSS] "))) {
/*  70 */       if (gameStage != 3) {
/*  71 */         gameStage = 3;
/*  72 */         DungeonRooms.logger.info("DungeonRooms: gameStage set to " + gameStage);
/*     */ 
/*     */         
/*  75 */         RoomDetection.resetCurrentRoom();
/*  76 */         RoomDetection.roomName = "Boss Room";
/*  77 */         RoomDetection.roomCategory = "General";
/*     */       }
/*     */     
/*  80 */     } else if (message.contains("§r§c☠ §r§eDefeated §r")) {
/*  81 */       gameStage = 4;
/*  82 */       DungeonRooms.logger.info("DungeonRooms: gameStage set to " + gameStage);
/*  83 */       RoomDetection.resetCurrentRoom();
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onTick(TickEvent.ClientTickEvent event) {
/*  89 */     if (event.phase != TickEvent.Phase.START)
/*  90 */       return;  EntityPlayerSP player = this.mc.field_71439_g;
/*     */     
/*  92 */     if (!Utils.inCatacombs)
/*  93 */       return;  tickAmount++;
/*     */     
/*  95 */     if ((gameStage == 0 || gameStage == 1) && tickAmount % 20 == 0) {
/*     */       
/*  97 */       if (DungeonRooms.firstLogin) {
/*  98 */         DungeonRooms.firstLogin = false;
/*  99 */         this.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("§d§l--- Dungeon Rooms Mod ---\n§e This appears to be your first time using DRM v3.2.4.\n§e Press \"" + 
/*     */               
/* 101 */               GameSettings.func_74298_c(DungeonRooms.keyBindings[1].func_151463_i()) + "\" to configure Secret Waypoint settings, If you do not wish to use Waypoints, you can instead press \"" + 
/*     */               
/* 103 */               GameSettings.func_74298_c(DungeonRooms.keyBindings[0].func_151463_i()) + "\" while inside a dungeon room to view images of the secrets for that room.\n§r (If you need help, join the Discord! Run \"/room discord\" to open the Discord invite.)\n§d§l------------------------"));
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 110 */       if (gameStage == 0) {
/* 111 */         Utils.checkForConflictingHotkeys();
/* 112 */         gameStage = 1;
/* 113 */         DungeonRooms.logger.info("DungeonRooms: gameStage set to " + gameStage);
/*     */       } 
/*     */       
/* 116 */       Integer[][] map = MapUtils.updatedMap();
/* 117 */       if (map != null) {
/* 118 */         DungeonRooms.logger.warn("DungeonRooms: Run started but gameStage is not on 2");
/* 119 */         gameStage = 2;
/* 120 */         DungeonRooms.logger.info("DungeonRooms: gameStage set to " + gameStage);
/*     */         
/*     */         return;
/*     */       } 
/* 124 */       if (gameStage == 1 && entrancePhysicalNWCorner == null && 
/* 125 */         !player.func_174791_d().equals(new Vec3(0.0D, 0.0D, 0.0D))) {
/*     */         
/* 127 */         entrancePhysicalNWCorner = MapUtils.getClosestNWPhysicalCorner(player.func_174791_d());
/* 128 */         DungeonRooms.logger.info("DungeonRooms: entrancePhysicalNWCorner has been set");
/*     */       } 
/*     */ 
/*     */       
/* 132 */       if (DungeonRooms.textToDisplay == null && motdToggled) {
/* 133 */         DungeonRooms.logger.info("DungeonRooms: Updating MOTD on screen");
/* 134 */         if ((this.oddRun || !guiToggled) && 
/* 135 */           DungeonRooms.motd != null && 
/* 136 */           !DungeonRooms.motd.isEmpty()) {
/* 137 */           DungeonRooms.textToDisplay = DungeonRooms.motd;
/*     */         }
/*     */ 
/*     */         
/* 141 */         if (DungeonRooms.textToDisplay == null && guiToggled) {
/* 142 */           DungeonRooms.textToDisplay = new ArrayList(Arrays.asList((Object[])new String[] { "Dungeon Rooms: " + EnumChatFormatting.GREEN + "Press the hotkey \"" + 
/* 143 */                   GameSettings.func_74298_c(DungeonRooms.keyBindings[1].func_151463_i()) + "\" to configure", EnumChatFormatting.GREEN + " waypoint settings. Alternatively, press \"" + 
/* 144 */                   GameSettings.func_74298_c(DungeonRooms.keyBindings[0].func_151463_i()) + "\" while in a room", EnumChatFormatting.GREEN + "to view images of secret locations for that room.", "(You can change the keybinds in Minecraft controls menu)" }));
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 149 */         this.oddRun = !this.oddRun;
/*     */       } 
/*     */       
/* 152 */       tickAmount = 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onWorldUnload(WorldEvent.Unload event) {
/* 158 */     Utils.inCatacombs = false;
/* 159 */     tickAmount = 0;
/* 160 */     gameStage = 0;
/*     */     
/* 162 */     map = (Integer[][])null;
/* 163 */     entranceMapCorners = null;
/* 164 */     entrancePhysicalNWCorner = null;
/* 165 */     RoomDetection.entranceMapNullCount = 0;
/*     */     
/* 167 */     this.bloodDone = false;
/*     */     
/* 169 */     if (RoomDetection.stage2Executor != null) RoomDetection.stage2Executor.shutdown();
/*     */     
/* 171 */     Waypoints.allSecretsMap.clear();
/*     */     
/* 173 */     RoomDetection.resetCurrentRoom();
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\io\github\quantizr\dungeonrooms\dungeons\catacombs\DungeonManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */