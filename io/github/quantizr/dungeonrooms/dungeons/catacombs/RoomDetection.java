/*     */ package io.github.quantizr.dungeonrooms.dungeons.catacombs;
/*     */ 
/*     */ import com.google.gson.JsonObject;
/*     */ import io.github.quantizr.dungeonrooms.DungeonRooms;
/*     */ import io.github.quantizr.dungeonrooms.utils.MapUtils;
/*     */ import io.github.quantizr.dungeonrooms.utils.RoomDetectionUtils;
/*     */ import io.github.quantizr.dungeonrooms.utils.Utils;
/*     */ import java.awt.Point;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.TreeSet;
/*     */ import java.util.concurrent.ExecutionException;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.Executors;
/*     */ import java.util.concurrent.Future;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.gameevent.TickEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RoomDetection
/*     */ {
/*  43 */   Minecraft mc = Minecraft.func_71410_x();
/*  44 */   static int stage2Ticks = 0;
/*     */   
/*     */   static ExecutorService stage2Executor;
/*     */   
/*     */   public static List<Point> currentMapSegments;
/*     */   
/*     */   public static List<Point> currentPhysicalSegments;
/*  51 */   public static String roomSize = "undefined";
/*  52 */   public static String roomColor = "undefined";
/*  53 */   public static String roomCategory = "undefined";
/*  54 */   public static String roomName = "undefined";
/*  55 */   public static String roomDirection = "undefined";
/*     */   
/*     */   public static Point roomCorner;
/*     */   
/*  59 */   public static HashSet<BlockPos> currentScannedBlocks = new HashSet<>();
/*  60 */   public static HashMap<BlockPos, Integer> blocksToCheck = new HashMap<>();
/*  61 */   public static int totalBlocksAvailableToCheck = 0;
/*  62 */   public static List<BlockPos> blocksUsed = new ArrayList<>();
/*     */   
/*     */   static Future<HashMap<String, List<String>>> futureUpdatePossibleRooms;
/*     */   public static HashMap<String, List<String>> possibleRooms;
/*  66 */   static long incompleteScan = 0L;
/*  67 */   static long redoScan = 0L;
/*     */   
/*  69 */   static int entranceMapNullCount = 0;
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onTick(TickEvent.ClientTickEvent event) {
/*  74 */     if (event.phase != TickEvent.Phase.START)
/*  75 */       return;  EntityPlayerSP player = this.mc.field_71439_g;
/*     */     
/*  77 */     if (!Utils.inCatacombs) {
/*     */       return;
/*     */     }
/*  80 */     if (DungeonManager.gameStage == 2) {
/*  81 */       stage2Ticks++;
/*  82 */       if (stage2Ticks == 10) {
/*  83 */         stage2Ticks = 0;
/*     */         
/*  85 */         if (stage2Executor == null || stage2Executor.isTerminated()) {
/*  86 */           stage2Executor = Executors.newSingleThreadExecutor();
/*  87 */           DungeonRooms.logger.debug("DungeonRooms: New Single Thread Executor Started");
/*     */         } 
/*     */         
/*  90 */         if (DungeonManager.entranceMapCorners == null) {
/*  91 */           DungeonManager.map = MapUtils.updatedMap();
/*  92 */           DungeonManager.entranceMapCorners = MapUtils.entranceMapCorners(DungeonManager.map);
/*  93 */           DungeonRooms.logger.info("DungeonRooms: Getting entrance map corners from hotbar map...");
/*  94 */         } else if (DungeonManager.entranceMapCorners[0] == null || DungeonManager.entranceMapCorners[1] == null) {
/*  95 */           DungeonRooms.logger.warn("DungeonRooms: Entrance room not found, hotbar map possibly bugged");
/*  96 */           entranceMapNullCount++;
/*  97 */           DungeonManager.entranceMapCorners = null;
/*  98 */           if (entranceMapNullCount >= 8) {
/*  99 */             player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "Dungeon Rooms: Error with hotbar map, turning mod off for this run..."));
/*     */             
/* 101 */             DungeonRooms.textToDisplay = new ArrayList(Collections.singletonList("Dungeon Rooms: " + EnumChatFormatting.RED + "Hotbar map may be bugged, mod disabled for this run"));
/*     */ 
/*     */             
/* 104 */             DungeonManager.gameStage = 4;
/* 105 */             DungeonRooms.logger.info("DungeonRooms: gameStage set to " + DungeonManager.gameStage);
/*     */           } 
/* 107 */         } else if (DungeonManager.entrancePhysicalNWCorner == null) {
/* 108 */           DungeonRooms.logger.warn("DungeonRooms: Entrance Room coordinates not found");
/*     */           
/* 110 */           Point playerMarkerPos = MapUtils.playerMarkerPos();
/* 111 */           if (playerMarkerPos != null) {
/* 112 */             Point closestNWMapCorner = MapUtils.getClosestNWMapCorner(playerMarkerPos, DungeonManager.entranceMapCorners[0], DungeonManager.entranceMapCorners[1]);
/* 113 */             if (MapUtils.getMapColor(playerMarkerPos, DungeonManager.map).equals("green") && MapUtils.getMapColor(closestNWMapCorner, DungeonManager.map).equals("green")) {
/* 114 */               if (!player.func_174791_d().equals(new Vec3(0.0D, 0.0D, 0.0D))) {
/* 115 */                 DungeonManager.entrancePhysicalNWCorner = MapUtils.getClosestNWPhysicalCorner(player.func_174791_d());
/* 116 */                 DungeonRooms.logger.info("DungeonRooms: entrancePhysicalNWCorner has been set");
/*     */               } 
/*     */             } else {
/* 119 */               DungeonRooms.textToDisplay = new ArrayList(Arrays.asList((Object[])new String[] { "Dungeon Rooms: " + EnumChatFormatting.RED + "Entrance Room coordinates not found", EnumChatFormatting.RED + "Please go back into the middle of the Green Entrance Room." }));
/*     */             }
/*     */           
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 126 */           Point currentPhysicalCorner = MapUtils.getClosestNWPhysicalCorner(player.func_174791_d());
/* 127 */           if (currentPhysicalSegments != null && !currentPhysicalSegments.contains(currentPhysicalCorner)) {
/*     */             
/* 129 */             resetCurrentRoom();
/* 130 */           } else if (incompleteScan != 0L && System.currentTimeMillis() > incompleteScan) {
/* 131 */             incompleteScan = 0L;
/* 132 */             DungeonRooms.logger.info("DungeonRooms: Rescanning room...");
/* 133 */             raytraceBlocks();
/* 134 */           } else if (redoScan != 0L && System.currentTimeMillis() > redoScan) {
/* 135 */             redoScan = 0L;
/* 136 */             DungeonRooms.logger.info("DungeonRooms: Clearing data and rescanning room...");
/* 137 */             possibleRooms = null;
/* 138 */             raytraceBlocks();
/*     */           } 
/*     */           
/* 141 */           if (currentPhysicalSegments == null || currentMapSegments == null || roomSize.equals("undefined") || roomColor.equals("undefined")) {
/* 142 */             updateCurrentRoom();
/* 143 */             if (roomColor.equals("undefined")) {
/* 144 */               DungeonRooms.textToDisplay = new ArrayList(Collections.singletonList("Dungeon Rooms: " + EnumChatFormatting.RED + "Waiting for hotbar map to update..."));
/*     */             } else {
/*     */               
/* 147 */               switch (roomColor) {
/*     */                 case "brown":
/*     */                 case "purple":
/*     */                 case "orange":
/* 151 */                   raytraceBlocks();
/*     */                   break;
/*     */                 case "yellow":
/* 154 */                   roomName = "Miniboss Room";
/* 155 */                   newRoom();
/*     */                   break;
/*     */                 case "green":
/* 158 */                   roomName = "Entrance Room";
/* 159 */                   newRoom();
/*     */                   break;
/*     */                 case "pink":
/* 162 */                   roomName = "Fairy Room";
/* 163 */                   newRoom();
/*     */                   break;
/*     */                 case "red":
/* 166 */                   roomName = "Blood Room";
/* 167 */                   newRoom();
/*     */                   break;
/*     */                 default:
/* 170 */                   roomName = "undefined";
/*     */                   break;
/*     */               } 
/*     */ 
/*     */ 
/*     */             
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 180 */       if (futureUpdatePossibleRooms != null && futureUpdatePossibleRooms.isDone()) {
/*     */         try {
/* 182 */           possibleRooms = futureUpdatePossibleRooms.get();
/* 183 */           futureUpdatePossibleRooms = null;
/*     */           
/* 185 */           TreeSet<String> possibleRoomsSet = new TreeSet<>();
/*     */           
/* 187 */           String tempDirection = "undefined";
/*     */           
/* 189 */           for (Map.Entry<String, List<String>> entry : possibleRooms.entrySet()) {
/* 190 */             List<String> possibleRoomList = entry.getValue();
/* 191 */             if (!possibleRoomList.isEmpty()) tempDirection = entry.getKey(); 
/* 192 */             possibleRoomsSet.addAll(possibleRoomList);
/*     */           } 
/*     */ 
/*     */           
/* 196 */           if (possibleRoomsSet.size() == 0) {
/* 197 */             DungeonRooms.textToDisplay = new ArrayList(Arrays.asList((Object[])new String[] { "Dungeon Rooms: " + EnumChatFormatting.RED + "No Matching Rooms Detected", EnumChatFormatting.RED + "This mod might not have data for this room.", EnumChatFormatting.WHITE + "Retrying every 5 seconds..." }));
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 202 */             redoScan = System.currentTimeMillis() + 5000L;
/*     */           }
/* 204 */           else if (possibleRoomsSet.size() == 1) {
/* 205 */             roomName = possibleRoomsSet.first();
/* 206 */             roomDirection = tempDirection;
/* 207 */             roomCorner = MapUtils.getPhysicalCornerPos(roomDirection, currentPhysicalSegments);
/* 208 */             DungeonRooms.logger.info("DungeonRooms: 576 raytrace vectors sent, returning " + currentScannedBlocks
/* 209 */                 .size() + " unique line-of-sight blocks, filtered down to " + totalBlocksAvailableToCheck + " blocks, out of which " + blocksUsed
/* 210 */                 .size() + " blocks were used to uniquely identify " + roomName + ".");
/*     */             
/* 212 */             newRoom();
/*     */           } else {
/*     */             
/* 215 */             DungeonRooms.textToDisplay = new ArrayList(Arrays.asList((Object[])new String[] { "Dungeon Rooms: " + EnumChatFormatting.RED + "Unable to Determine Room Name", EnumChatFormatting.RED + "Not enough valid blocks were scanned, look at a more open area.", EnumChatFormatting.WHITE + "Retrying every second..." }));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 221 */             DungeonRooms.logger.debug("DungeonRooms: Possible rooms list = " + new ArrayList<>(possibleRoomsSet));
/* 222 */             incompleteScan = System.currentTimeMillis() + 1000L;
/*     */           } 
/* 224 */         } catch (ExecutionException|InterruptedException e) {
/* 225 */           e.printStackTrace();
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void updateCurrentRoom() {
/* 233 */     EntityPlayerSP player = this.mc.field_71439_g;
/* 234 */     DungeonManager.map = MapUtils.updatedMap();
/* 235 */     if (DungeonManager.map == null)
/* 236 */       return;  Point currentPhysicalCorner = MapUtils.getClosestNWPhysicalCorner(player.func_174791_d());
/* 237 */     Point currentMapCorner = MapUtils.physicalToMapCorner(currentPhysicalCorner, DungeonManager.entrancePhysicalNWCorner, DungeonManager.entranceMapCorners[0], DungeonManager.entranceMapCorners[1]);
/* 238 */     roomColor = MapUtils.getMapColor(currentMapCorner, DungeonManager.map);
/* 239 */     if (roomColor.equals("undefined"))
/* 240 */       return;  currentMapSegments = MapUtils.neighboringSegments(currentMapCorner, DungeonManager.map, DungeonManager.entranceMapCorners[0], DungeonManager.entranceMapCorners[1], new ArrayList());
/* 241 */     currentPhysicalSegments = new ArrayList<>();
/* 242 */     for (Point mapCorner : currentMapSegments) {
/* 243 */       currentPhysicalSegments.add(MapUtils.mapToPhysicalCorner(mapCorner, DungeonManager.entrancePhysicalNWCorner, DungeonManager.entranceMapCorners[0], DungeonManager.entranceMapCorners[1]));
/*     */     }
/* 245 */     roomSize = MapUtils.roomSize(currentMapSegments);
/* 246 */     roomCategory = MapUtils.roomCategory(roomSize, roomColor);
/*     */   }
/*     */   
/*     */   public static void resetCurrentRoom() {
/* 250 */     DungeonRooms.textToDisplay = null;
/* 251 */     Waypoints.allFound = false;
/*     */     
/* 253 */     currentPhysicalSegments = null;
/* 254 */     currentMapSegments = null;
/*     */     
/* 256 */     roomSize = "undefined";
/* 257 */     roomColor = "undefined";
/* 258 */     roomCategory = "undefined";
/* 259 */     roomName = "undefined";
/* 260 */     roomDirection = "undefined";
/* 261 */     roomCorner = null;
/*     */     
/* 263 */     currentScannedBlocks = new HashSet<>();
/* 264 */     blocksToCheck = new HashMap<>();
/* 265 */     totalBlocksAvailableToCheck = 0;
/* 266 */     blocksUsed = new ArrayList<>();
/* 267 */     futureUpdatePossibleRooms = null;
/* 268 */     possibleRooms = null;
/*     */     
/* 270 */     incompleteScan = 0L;
/* 271 */     redoScan = 0L;
/*     */     
/* 273 */     Waypoints.secretNum = 0;
/*     */   }
/*     */   
/*     */   public static void newRoom() {
/* 277 */     if (!roomName.equals("undefined") && !roomCategory.equals("undefined")) {
/*     */       
/* 279 */       if (DungeonRooms.roomsJson.get(roomName) != null) {
/* 280 */         Waypoints.secretNum = DungeonRooms.roomsJson.get(roomName).getAsJsonObject().get("secrets").getAsInt();
/* 281 */         Waypoints.allSecretsMap.putIfAbsent(roomName, new ArrayList<>(Collections.nCopies(Waypoints.secretNum, Boolean.valueOf(true))));
/*     */       } else {
/* 283 */         Waypoints.secretNum = 0;
/* 284 */         Waypoints.allSecretsMap.putIfAbsent(roomName, new ArrayList<>(Collections.nCopies(0, Boolean.valueOf(true))));
/*     */       } 
/* 286 */       Waypoints.secretsList = Waypoints.allSecretsMap.get(roomName);
/*     */ 
/*     */       
/* 289 */       if (DungeonManager.guiToggled) {
/* 290 */         List<String> lineList = new ArrayList<>();
/* 291 */         String line = "Dungeon Rooms: You are in " + EnumChatFormatting.GREEN + roomCategory + EnumChatFormatting.WHITE + " - " + EnumChatFormatting.GREEN + roomName;
/*     */ 
/*     */         
/* 294 */         if (DungeonRooms.roomsJson.get(roomName) != null) {
/* 295 */           JsonObject roomJson = DungeonRooms.roomsJson.get(roomName).getAsJsonObject();
/* 296 */           if (roomJson.get("fairysoul").getAsBoolean()) {
/* 297 */             line = line + EnumChatFormatting.WHITE + " - " + EnumChatFormatting.LIGHT_PURPLE + "Fairy Soul";
/*     */           }
/* 299 */           lineList.add(line);
/*     */           
/* 301 */           if (Waypoints.enabled && roomJson.get("secrets").getAsInt() != 0 && DungeonRooms.waypointsJson.get(roomName) == null) {
/* 302 */             lineList.add(EnumChatFormatting.RED + "No waypoints available");
/* 303 */             lineList.add(EnumChatFormatting.RED + "Press \"" + GameSettings.func_74298_c(DungeonRooms.keyBindings[0].func_151463_i()) + "\" to view images");
/*     */           } 
/*     */         } 
/* 306 */         DungeonRooms.textToDisplay = lineList;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void raytraceBlocks() {
/* 313 */     DungeonRooms.logger.debug("DungeonRooms: Raytracing visible blocks");
/* 314 */     long timeStart = System.currentTimeMillis();
/*     */     
/* 316 */     EntityPlayerSP player = this.mc.field_71439_g;
/*     */     
/* 318 */     List<Vec3> vecList = RoomDetectionUtils.vectorsToRaytrace(24);
/*     */     
/* 320 */     Vec3 eyes = new Vec3(player.field_70165_t, player.field_70163_u + player.func_70047_e(), player.field_70161_v);
/*     */     
/* 322 */     for (Vec3 vec : vecList) {
/*     */ 
/*     */       
/* 325 */       MovingObjectPosition raytraceResult = player.func_130014_f_().func_147447_a(eyes, vec, false, false, true);
/*     */       
/* 327 */       if (raytraceResult != null && raytraceResult.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
/*     */         
/* 329 */         BlockPos raytracedBlockPos = raytraceResult.func_178782_a();
/* 330 */         if (currentScannedBlocks.contains(raytracedBlockPos))
/* 331 */           continue;  currentScannedBlocks.add(raytracedBlockPos);
/*     */         
/* 333 */         if (!currentPhysicalSegments.contains(MapUtils.getClosestNWPhysicalCorner(raytracedBlockPos))) {
/*     */           continue;
/*     */         }
/*     */         
/* 337 */         if (RoomDetectionUtils.blockPartOfDoorway(raytracedBlockPos)) {
/*     */           continue;
/*     */         }
/*     */         
/* 341 */         IBlockState hitBlock = this.mc.field_71441_e.func_180495_p(raytracedBlockPos);
/* 342 */         int identifier = Block.func_149682_b(hitBlock.func_177230_c()) * 100 + hitBlock.func_177230_c().func_180651_a(hitBlock);
/*     */         
/* 344 */         if (RoomDetectionUtils.whitelistedBlocks.contains(Integer.valueOf(identifier))) {
/* 345 */           blocksToCheck.put(raytracedBlockPos, Integer.valueOf(identifier));
/*     */         }
/*     */       } 
/*     */     } 
/* 349 */     DungeonRooms.logger.debug("DungeonRooms: Finished raytracing, amount of blocks to check = " + blocksToCheck.size());
/* 350 */     long timeFinish = System.currentTimeMillis();
/* 351 */     DungeonRooms.logger.debug("DungeonRooms: Time to raytrace and filter (in ms): " + (timeFinish - timeStart));
/*     */     
/* 353 */     if (futureUpdatePossibleRooms == null && stage2Executor != null && !stage2Executor.isTerminated()) {
/* 354 */       DungeonRooms.logger.debug("DungeonRooms: Initializing Room Comparison Executor");
/* 355 */       futureUpdatePossibleRooms = getPossibleRooms();
/*     */     } 
/*     */   }
/*     */   
/*     */   Future<HashMap<String, List<String>>> getPossibleRooms() {
/* 360 */     return stage2Executor.submit(() -> {
/*     */           HashMap<String, List<String>> updatedPossibleRooms;
/*     */           List<String> possibleDirections;
/*     */           long timeStart = System.currentTimeMillis();
/*     */           if (possibleRooms == null) {
/*     */             DungeonRooms.logger.debug("DungeonRooms: No previous possible rooms list, creating new list...");
/*     */             possibleDirections = MapUtils.possibleDirections(roomSize, currentMapSegments);
/*     */             updatedPossibleRooms = new HashMap<>();
/*     */             for (String direction : possibleDirections)
/*     */               updatedPossibleRooms.put(direction, new ArrayList<>(((HashMap)DungeonRooms.ROOM_DATA.get(roomCategory)).keySet())); 
/*     */           } else {
/*     */             DungeonRooms.logger.debug("DungeonRooms: Loading possible rooms from previous room scan...");
/*     */             updatedPossibleRooms = possibleRooms;
/*     */             possibleDirections = new ArrayList<>(possibleRooms.keySet());
/*     */           } 
/*     */           HashMap<String, Point> directionCorners = new HashMap<>();
/*     */           for (String direction : possibleDirections)
/*     */             directionCorners.put(direction, MapUtils.getPhysicalCornerPos(direction, currentPhysicalSegments)); 
/*     */           DungeonRooms.logger.debug("DungeonRooms: directionCorners " + directionCorners.entrySet());
/*     */           List<BlockPos> blocksChecked = new ArrayList<>();
/*     */           int doubleCheckedBlocks = 0;
/*     */           for (Map.Entry<BlockPos, Integer> entry : blocksToCheck.entrySet()) {
/*     */             BlockPos blockPos = entry.getKey();
/*     */             DungeonRooms.logger.debug("DungeonRooms: BlockPos being checked " + blockPos);
/*     */             int combinedMatchingRooms = 0;
/*     */             for (String direction : possibleDirections) {
/*     */               BlockPos relative = MapUtils.actualToRelative(blockPos, direction, directionCorners.get(direction));
/*     */               long idToCheck = Utils.shortToLong((short)relative.func_177958_n(), (short)relative.func_177956_o(), (short)relative.func_177952_p(), ((Integer)entry.getValue()).shortValue());
/*     */               List<String> matchingRooms = new ArrayList<>();
/*     */               for (String roomName : updatedPossibleRooms.get(direction)) {
/*     */                 int index = Arrays.binarySearch((long[])((HashMap)DungeonRooms.ROOM_DATA.get(roomCategory)).get(roomName), idToCheck);
/*     */                 if (index > -1)
/*     */                   matchingRooms.add(roomName); 
/*     */               } 
/*     */               combinedMatchingRooms += matchingRooms.size();
/*     */               updatedPossibleRooms.put(direction, matchingRooms);
/*     */               DungeonRooms.logger.debug("DungeonRooms: direction checked = " + direction + ", longID = " + idToCheck + ", relative = " + relative);
/*     */               DungeonRooms.logger.debug("DungeonRooms: updatedPossibleRooms size = " + ((List)updatedPossibleRooms.get(direction)).size() + " for direction " + direction);
/*     */             } 
/*     */             blocksChecked.add(blockPos);
/*     */             if (combinedMatchingRooms == 0) {
/*     */               DungeonRooms.logger.warn("DungeonRooms: No rooms match the input blocks after checking " + blocksChecked.size() + " blocks, returning");
/*     */               break;
/*     */             } 
/*     */             if (combinedMatchingRooms == 1) {
/*     */               if (doubleCheckedBlocks >= 10) {
/*     */                 DungeonRooms.logger.debug("DungeonRooms: One room matches after checking " + blocksChecked.size() + " blocks");
/*     */                 break;
/*     */               } 
/*     */               doubleCheckedBlocks++;
/*     */             } 
/*     */             DungeonRooms.logger.debug("DungeonRooms: " + combinedMatchingRooms + " possible rooms after checking " + blocksChecked.size() + " blocks");
/*     */           } 
/*     */           if (blocksChecked.size() == blocksToCheck.size())
/*     */             DungeonRooms.logger.warn("DungeonRooms: Multiple rooms match after checking all " + blocksChecked.size() + " blocks"); 
/*     */           blocksUsed.addAll(blocksChecked);
/*     */           totalBlocksAvailableToCheck += blocksToCheck.size();
/*     */           blocksToCheck = new HashMap<>();
/*     */           long timeFinish = System.currentTimeMillis();
/*     */           DungeonRooms.logger.debug("DungeonRooms: Time to check blocks using thread (in ms): " + (timeFinish - timeStart));
/*     */           return updatedPossibleRooms;
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\io\github\quantizr\dungeonrooms\dungeons\catacombs\RoomDetection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */