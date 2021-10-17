/*     */ package cheaters.get.banned.features.dungeonmap;
/*     */ 
/*     */ import cheaters.get.banned.Shady;
/*     */ import cheaters.get.banned.config.Config;
/*     */ import cheaters.get.banned.events.TickEndEvent;
/*     */ import cheaters.get.banned.utils.DungeonUtils;
/*     */ import cheaters.get.banned.utils.FontUtils;
/*     */ import cheaters.get.banned.utils.RenderUtils;
/*     */ import cheaters.get.banned.utils.Utils;
/*     */ import java.awt.Color;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import net.minecraft.client.gui.Gui;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraftforge.client.event.RenderGameOverlayEvent;
/*     */ import net.minecraftforge.client.event.RenderWorldLastEvent;
/*     */ import net.minecraftforge.event.world.WorldEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ 
/*     */ 
/*     */ public class DungeonMap
/*     */ {
/*     */   private static int xCorner;
/*     */   private static int zCorner;
/*  29 */   public static DungeonLayout activeDungeonLayout = null;
/*     */ 
/*     */   
/*     */   public static boolean scanning = false;
/*     */   
/*     */   public static boolean debug = false;
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onWorldLoad(WorldEvent.Load event) {
/*  39 */     activeDungeonLayout = null;
/*  40 */     scanning = false;
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onTick(TickEndEvent event) {
/*  45 */     if (Utils.inDungeon) {
/*  46 */       if (activeDungeonLayout == null) {
/*  47 */         if (DungeonUtils.dungeonRun != null && DungeonUtils.dungeonRun.floor != null && !scanning) {
/*  48 */           switch (DungeonUtils.dungeonRun.floor) {
/*     */             case FLOOR_1:
/*     */             case MASTER_1:
/*  51 */               xCorner = 127;
/*  52 */               zCorner = 159;
/*     */               break;
/*     */             
/*     */             case FLOOR_2:
/*     */             case MASTER_2:
/*  57 */               xCorner = 159;
/*  58 */               zCorner = 159;
/*     */               break;
/*     */             
/*     */             default:
/*  62 */               xCorner = 191;
/*  63 */               zCorner = 191;
/*     */               break;
/*     */           } 
/*  66 */           if (Shady.mc.field_71441_e.func_175726_f(new BlockPos(xCorner, 70, zCorner)).func_177410_o() && Shady.mc.field_71441_e.func_175726_f(new BlockPos(0, 70, 0)).func_177410_o()) {
/*  67 */             scanning = true;
/*  68 */             (new Thread(() -> { activeDungeonLayout = DungeonScanner.scan(xCorner, zCorner); scanning = false; }"ShadyAddons-DungeonScanner"))
/*     */ 
/*     */               
/*  71 */               .start();
/*     */           } 
/*     */         } 
/*  74 */       } else if (Config.announceScore && !activeDungeonLayout.sentScoreMessage && DungeonUtils.calculateScore() >= Config.announceScoreNumber) {
/*  75 */         activeDungeonLayout.sentScoreMessage = true;
/*  76 */         (new String[4])[0] = "/pc"; (new String[4])[1] = "/ac"; (new String[4])[2] = "/gc"; (new String[4])[3] = "/r"; String chatPrefix = (new String[4])[Config.announceScoreChat];
/*  77 */         Utils.sendMessageAsPlayer(chatPrefix + " ShadyAddons: " + Config.announceScoreNumber + " score reached");
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onRenderWorld(RenderWorldLastEvent event) {
/*  84 */     if (activeDungeonLayout != null) {
/*  85 */       if (debug) {
/*  86 */         for (DungeonLayout.ConnectorTile connectorTile : activeDungeonLayout.connectorTiles) {
/*  87 */           if (connectorTile.type == null)
/*  88 */             continue;  RenderUtils.highlightBlock(new BlockPos(connectorTile.x, 69, connectorTile.z), (connectorTile.type == DungeonLayout.ConnectorTile.Type.CONNECTOR) ? Color.BLUE : connectorTile.type.color, event.partialTicks);
/*     */         } 
/*     */         
/*  91 */         for (DungeonLayout.RoomTile roomTile : activeDungeonLayout.roomTiles) {
/*  92 */           RenderUtils.highlightBlock(new BlockPos(roomTile.x, 99, roomTile.z), roomTile.room.type.color, event.partialTicks);
/*     */         }
/*     */       } 
/*     */       
/*  96 */       if (Config.witherDoorESP && DungeonUtils.dungeonRun != null && !DungeonUtils.dungeonRun.inBoss) {
/*  97 */         for (DungeonLayout.ConnectorTile door : activeDungeonLayout.connectorTiles) {
/*  98 */           if ((!door.isOpen && door.type == DungeonLayout.ConnectorTile.Type.WITHER_DOOR) || door.type == DungeonLayout.ConnectorTile.Type.BLOOD_DOOR) {
/*  99 */             Iterable<BlockPos> positions; Color color = (Config.witherDoorColor == 0) ? Color.WHITE : Color.BLACK;
/* 100 */             if (door.type == DungeonLayout.ConnectorTile.Type.BLOOD_DOOR) color = Color.RED;
/*     */ 
/*     */             
/* 103 */             if (door.direction == EnumFacing.NORTH || door.direction == EnumFacing.SOUTH) { positions = BlockPos.func_177980_a(door.getPosition(69).func_177985_f(1), door.getPosition(72).func_177965_g(1)); }
/* 104 */             else { positions = BlockPos.func_177980_a(door.getPosition(69).func_177964_d(1), door.getPosition(72).func_177970_e(1)); }
/*     */             
/* 106 */             for (BlockPos position : positions) {
/* 107 */               RenderUtils.highlightBlock(position, color, event.partialTicks);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onRenderOverlay(RenderGameOverlayEvent.Post event) {
/* 117 */     if (!Config.dungeonMap || event.type != RenderGameOverlayEvent.ElementType.HOTBAR || !Utils.inDungeon || DungeonUtils.dungeonRun == null || DungeonUtils.dungeonRun.inBoss)
/*     */       return; 
/* 119 */     float scale = Config.mapScale / 100.0F;
/*     */     
/* 121 */     GlStateManager.func_179094_E();
/* 122 */     GlStateManager.func_179152_a(scale, scale, scale);
/* 123 */     GlStateManager.func_179109_b(Config.mapXOffset, Config.mapYOffset, 0.0F);
/*     */ 
/*     */     
/* 126 */     if (!scanning && activeDungeonLayout != null && Config.showDungeonInformation) {
/* 127 */       Gui.func_73734_a(0, 0, 200, 230, (new Color(0, 0, 0, 255 * Config.mapBackgroundOpacity / 100)).getRGB());
/*     */     } else {
/* 129 */       Gui.func_73734_a(0, 0, 200, 200, (new Color(0, 0, 0, 255 * Config.mapBackgroundOpacity / 100)).getRGB());
/*     */     } 
/*     */ 
/*     */     
/* 133 */     if (scanning) {
/* 134 */       FontUtils.drawCenteredString("Scanning Dungeon...", 100, 100);
/* 135 */       GlStateManager.func_179121_F(); return;
/*     */     } 
/* 137 */     if (activeDungeonLayout == null) {
/* 138 */       FontUtils.drawCenteredString("§cNot Scanned", 100, 100);
/* 139 */       GlStateManager.func_179121_F();
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 144 */     GlStateManager.func_179109_b((200 - xCorner) / 2.0F, (200 - zCorner) / 2.0F, 0.0F);
/*     */ 
/*     */     
/* 147 */     for (DungeonLayout.ConnectorTile connector : activeDungeonLayout.connectorTiles) {
/* 148 */       if (connector.type == null)
/*     */         continue; 
/* 150 */       int x1 = connector.x - 4;
/* 151 */       int y1 = connector.z - 2;
/* 152 */       int x2 = connector.x + 4;
/* 153 */       int y2 = connector.z + 2;
/*     */       
/* 155 */       if (connector.direction == EnumFacing.EAST || connector.direction == EnumFacing.WEST) {
/* 156 */         x1 = connector.x - 2;
/* 157 */         y1 = connector.z - 6;
/* 158 */         x2 = connector.x + 2;
/* 159 */         y2 = connector.z + 6;
/*     */       } 
/*     */       
/* 162 */       if (connector.type == DungeonLayout.ConnectorTile.Type.CONNECTOR) {
/* 163 */         x1 = connector.x - 14;
/* 164 */         y1 = connector.z - 2;
/* 165 */         x2 = connector.x + 14;
/* 166 */         y2 = connector.z + 2;
/*     */         
/* 168 */         if (connector.direction == EnumFacing.EAST || connector.direction == EnumFacing.WEST) {
/* 169 */           x1 = connector.x - 2;
/* 170 */           y1 = connector.z - 14;
/* 171 */           x2 = connector.x + 2;
/* 172 */           y2 = connector.z + 14;
/*     */         } 
/*     */       } 
/*     */       
/* 176 */       Gui.func_73734_a(x1, y1, x2, y2, connector.type.color.getRGB());
/*     */     } 
/*     */ 
/*     */     
/* 180 */     for (DungeonLayout.RoomTile room : activeDungeonLayout.roomTiles) {
/* 181 */       Gui.func_73734_a(room.x - 14, room.z - 14, room.x + 14, room.z + 14, room.room.type.color.getRGB());
/*     */     }
/*     */ 
/*     */     
/* 185 */     for (DungeonLayout.RoomTile room : activeDungeonLayout.roomTiles) {
/* 186 */       if (room.room.type == Room.Type.PUZZLE || (room.room.type == Room.Type.TRAP && Config.significantRoomNameStyle != 2)) {
/* 187 */         switch (Config.significantRoomNameStyle) {
/*     */           case 0:
/* 189 */             FontUtils.drawCenteredString(getShortenedRoomName(room.room.name), room.x, room.z);
/*     */ 
/*     */           
/*     */           case 1:
/* 193 */             FontUtils.drawCenteredString(room.room.name.replace(" ", "\n"), room.x, room.z);
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */     } 
/* 202 */     if (Shady.mc.field_71439_g != null) {
/* 203 */       int size = 14;
/*     */       
/* 205 */       HashSet<String> players = DungeonUtils.dungeonRun.team;
/* 206 */       players.add(Shady.mc.func_110432_I().func_111285_a());
/*     */       
/* 208 */       for (String player : DungeonUtils.dungeonRun.team) {
/* 209 */         EntityPlayer playerEntity = Shady.mc.field_71441_e.func_72924_a(player);
/* 210 */         if (playerEntity == null)
/* 211 */           continue;  int playerX = MathHelper.func_76125_a(playerEntity.func_180425_c().func_177958_n() - size / 2, 0, xCorner - 14);
/* 212 */         int playerZ = MathHelper.func_76125_a(playerEntity.func_180425_c().func_177952_p() - size / 2, 0, zCorner - 14);
/* 213 */         float playerRotation = playerEntity.func_70079_am() - 180.0F;
/* 214 */         drawPlayerIcon(playerEntity, size, playerX, playerZ, (int)playerRotation);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 219 */     GlStateManager.func_179109_b(-((200 - xCorner) / 2.0F), -((200 - zCorner) / 2.0F), 0.0F);
/*     */ 
/*     */     
/* 222 */     if (Config.showDungeonInformation) {
/* 223 */       String dungeonStats = "§7Secrets: §a" + DungeonUtils.dungeonRun.secretsFound + "§7/" + activeDungeonLayout.totalSecrets + "   Crypts: §a" + DungeonUtils.dungeonRun.cryptsFound + "§7/" + (activeDungeonLayout.uncertainCrypts ? "~" : "") + activeDungeonLayout.totalCrypts + "\n";
/* 224 */       dungeonStats = dungeonStats + "§7Puzzles: §a" + activeDungeonLayout.totalPuzzles + "§7   Deaths: §c" + DungeonUtils.dungeonRun.deaths + "§7   Score: §e~" + DungeonUtils.calculateScore();
/* 225 */       FontUtils.drawCenteredString(dungeonStats, 100, 212);
/*     */     } 
/*     */     
/* 228 */     GlStateManager.func_179121_F();
/*     */   }
/*     */   
/*     */   private static void drawPlayerIcon(EntityPlayer player, int size, int x, int y, int angle) {
/* 232 */     GlStateManager.func_179094_E();
/*     */     
/* 234 */     GlStateManager.func_179109_b(x + size / 2.0F, y + size / 2.0F, 0.0F);
/* 235 */     GlStateManager.func_179114_b(angle, 0.0F, 0.0F, 1.0F);
/* 236 */     GlStateManager.func_179109_b(-x - size / 2.0F, -y - size / 2.0F, 0.0F);
/*     */     
/* 238 */     Gui.func_73734_a(x, y, x + size, y + size, Color.BLACK.getRGB());
/* 239 */     GlStateManager.func_179124_c(255.0F, 255.0F, 255.0F);
/* 240 */     RenderUtils.drawPlayerIcon(player, size - 2, x + 1, y + 1);
/*     */     
/* 242 */     GlStateManager.func_179121_F();
/*     */   }
/*     */   
/* 245 */   public static final HashMap<String, String> shortNames = new HashMap<String, String>()
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String getShortenedRoomName(String longName) {
/* 259 */     String shortName = shortNames.get(longName);
/* 260 */     return (shortName == null) ? longName : shortName;
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\cheaters\get\banned\features\dungeonmap\DungeonMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */