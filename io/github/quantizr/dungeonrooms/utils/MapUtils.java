/*     */ package io.github.quantizr.dungeonrooms.utils;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.TreeSet;
/*     */ import net.minecraft.block.material.MapColor;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.util.Vec4b;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.storage.MapData;
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
/*     */ 
/*     */ 
/*     */ public class MapUtils
/*     */ {
/*     */   public static boolean mapExists() {
/*  41 */     Minecraft mc = Minecraft.func_71410_x();
/*  42 */     ItemStack mapSlot = mc.field_71439_g.field_71071_by.func_70301_a(8);
/*  43 */     if (mapSlot == null || mapSlot.func_77973_b() != Items.field_151098_aY || !mapSlot.func_82837_s()) return false; 
/*  44 */     return mapSlot.func_82833_r().contains("Magical Map");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Integer[][] updatedMap() {
/*  54 */     if (!mapExists()) return (Integer[][])null; 
/*  55 */     Minecraft mc = Minecraft.func_71410_x();
/*  56 */     ItemStack mapSlot = mc.field_71439_g.field_71071_by.func_70301_a(8);
/*  57 */     MapData mapData = Items.field_151098_aY.func_77873_a(mapSlot, (World)mc.field_71441_e);
/*  58 */     if (mapData == null) return (Integer[][])null; 
/*  59 */     Integer[][] map = new Integer[128][128];
/*     */ 
/*     */     
/*  62 */     for (int i = 0; i < 16384; i++) {
/*  63 */       int rgba, x = i % 128;
/*  64 */       int y = i / 128;
/*  65 */       int j = mapData.field_76198_e[i] & 0xFF;
/*     */       
/*  67 */       if (j / 4 == 0) {
/*  68 */         rgba = (i + i / 128 & 0x1) * 8 + 16 << 24;
/*     */       } else {
/*  70 */         rgba = MapColor.field_76281_a[j / 4].func_151643_b(j & 0x3);
/*     */       } 
/*  72 */       map[x][y] = Integer.valueOf(rgba & 0xFFFFFF);
/*     */     } 
/*     */     
/*  75 */     return map;
/*     */   }
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
/*     */   public static Point[] entranceMapCorners(Integer[][] map) {
/*  94 */     if (map == null) return null; 
/*  95 */     Point[] corners = new Point[2];
/*     */     
/*  97 */     for (int x = 0; x < 128; x++) {
/*  98 */       for (int y = 0; y < 128; y++) {
/*  99 */         if (map[x][y] != null && map[x][y].intValue() == 31744 && map[x][y - 1] != null && map[x][y - 1].intValue() == 0) {
/* 100 */           if (map[x - 1][y] != null && map[x - 1][y].intValue() == 0) {
/* 101 */             corners[0] = new Point(x, y);
/* 102 */           } else if (map[x + 1][y] != null && map[x + 1][y].intValue() == 0) {
/* 103 */             corners[1] = new Point(x, y);
/*     */           } 
/*     */         }
/*     */       } 
/* 107 */       if (corners[0] != null && corners[1] != null)
/*     */         break; 
/* 109 */     }  return corners;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Point getClosestNWMapCorner(Point mapPos, Point leftCorner, Point rightCorner) {
/* 116 */     int roomWidthAndGap = rightCorner.x - leftCorner.x + 1 + 4;
/* 117 */     Point origin = new Point(leftCorner.x % roomWidthAndGap, leftCorner.y % roomWidthAndGap);
/*     */     
/* 119 */     mapPos.x += 2;
/* 120 */     mapPos.y += 2;
/*     */     
/* 122 */     int x = mapPos.x - mapPos.x % roomWidthAndGap + origin.x;
/* 123 */     int y = mapPos.y - mapPos.y % roomWidthAndGap + origin.y;
/*     */     
/* 125 */     if (x > mapPos.x) x -= roomWidthAndGap; 
/* 126 */     if (y > mapPos.y) y -= roomWidthAndGap;
/*     */     
/* 128 */     return new Point(x, y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Point getClosestNWPhysicalCorner(Vec3 vectorPos) {
/* 136 */     Vec3 shiftedPos = vectorPos.func_72441_c(0.5D, 0.0D, 0.5D);
/* 137 */     int x = (int)(shiftedPos.field_72450_a - shiftedPos.field_72450_a % 32.0D);
/* 138 */     int z = (int)(shiftedPos.field_72449_c - shiftedPos.field_72449_c % 32.0D);
/* 139 */     return new Point(x, z);
/*     */   }
/*     */   
/*     */   public static Point getClosestNWPhysicalCorner(BlockPos blockPos) {
/* 143 */     return getClosestNWPhysicalCorner(new Vec3(blockPos.func_177958_n(), blockPos.func_177956_o(), blockPos.func_177952_p()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Point physicalToMapCorner(Point physicalClosestCorner, Point physicalLeftCorner, Point leftCorner, Point rightCorner) {
/* 150 */     int roomWidthAndGap = rightCorner.x - leftCorner.x + 1 + 4;
/* 151 */     int xShift = (physicalClosestCorner.x - physicalLeftCorner.x) / 32;
/* 152 */     int yShift = (physicalClosestCorner.y - physicalLeftCorner.y) / 32;
/*     */     
/* 154 */     int x = leftCorner.x + roomWidthAndGap * xShift;
/* 155 */     int y = leftCorner.y + roomWidthAndGap * yShift;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 160 */     return new Point(x, y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Point mapToPhysicalCorner(Point mapCorner, Point physicalLeftCorner, Point leftCorner, Point rightCorner) {
/* 167 */     int roomWidthAndGap = rightCorner.x - leftCorner.x + 1 + 4;
/* 168 */     int xShift = (mapCorner.x - leftCorner.x) / roomWidthAndGap;
/* 169 */     int yShift = (mapCorner.y - leftCorner.y) / roomWidthAndGap;
/*     */     
/* 171 */     int x = physicalLeftCorner.x + 32 * xShift;
/* 172 */     int y = physicalLeftCorner.y + 32 * yShift;
/*     */     
/* 174 */     return new Point(x, y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getMapColor(Point point, Integer[][] map) {
/* 181 */     int x = point.x;
/* 182 */     int y = point.y;
/*     */ 
/*     */ 
/*     */     
/* 186 */     if (x < 0 || y < 0 || x > 127 || y > 127) {
/* 187 */       return "undefined";
/*     */     }
/*     */     
/* 190 */     if (map != null) {
/* 191 */       switch (map[x][y].intValue()) {
/*     */         case 7488283:
/* 193 */           return "brown";
/*     */         case 11685080:
/* 195 */           return "purple";
/*     */         case 15066419:
/* 197 */           return "yellow";
/*     */         case 31744:
/* 199 */           return "green";
/*     */         case 15892389:
/* 201 */           return "pink";
/*     */         case 14188339:
/* 203 */           return "orange";
/*     */         case 16711680:
/* 205 */           return "red";
/*     */       } 
/* 207 */       return "undefined";
/*     */     } 
/*     */     
/* 210 */     return "undefined";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<Point> neighboringSegments(Point originCorner, Integer[][] map, Point leftCorner, Point rightCorner, List<Point> list) {
/* 220 */     if (!list.contains(originCorner)) {
/* 221 */       list.add(originCorner);
/*     */     }
/* 223 */     if (!getMapColor(originCorner, map).equals("brown")) return list;
/*     */     
/* 225 */     int roomWidth = rightCorner.x - leftCorner.x + 1;
/*     */ 
/*     */     
/* 228 */     List<Point> pointsToCheck = new ArrayList<>();
/* 229 */     pointsToCheck.add(new Point(originCorner.x, originCorner.y - 1));
/* 230 */     pointsToCheck.add(new Point(originCorner.x, originCorner.y + roomWidth));
/* 231 */     pointsToCheck.add(new Point(originCorner.x - 1, originCorner.y));
/* 232 */     pointsToCheck.add(new Point(originCorner.x + roomWidth, originCorner.y));
/*     */     
/* 234 */     List<Point> pointsToTransform = new ArrayList<>();
/* 235 */     pointsToTransform.add(new Point(originCorner.x, originCorner.y - 1 - 4));
/* 236 */     pointsToTransform.add(new Point(originCorner.x, originCorner.y + roomWidth + 4));
/* 237 */     pointsToTransform.add(new Point(originCorner.x - 1 - 4, originCorner.y));
/* 238 */     pointsToTransform.add(new Point(originCorner.x + roomWidth + 4, originCorner.y));
/*     */     
/* 240 */     for (int i = 0; i < 4; i++) {
/* 241 */       if (getMapColor(pointsToCheck.get(i), map).equals("brown")) {
/* 242 */         Point newCorner = getClosestNWMapCorner(pointsToTransform.get(i), leftCorner, rightCorner);
/*     */         
/* 244 */         if (!list.contains(newCorner)) {
/* 245 */           list.add(newCorner);
/* 246 */           list = neighboringSegments(newCorner, map, leftCorner, rightCorner, list);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 251 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String roomSize(List<Point> segments) {
/* 259 */     if (segments.size() == 1) return "1x1"; 
/* 260 */     if (segments.size() == 2) return "1x2";
/*     */     
/* 262 */     HashSet<Integer> x = new HashSet<>();
/* 263 */     HashSet<Integer> y = new HashSet<>();
/* 264 */     for (Point segment : segments) {
/* 265 */       x.add(Integer.valueOf(segment.x));
/* 266 */       y.add(Integer.valueOf(segment.y));
/*     */     } 
/* 268 */     if (segments.size() == 3) {
/* 269 */       if (x.size() == 2 && y.size() == 2) return "L-shape"; 
/* 270 */       return "1x3";
/*     */     } 
/* 272 */     if (segments.size() == 4) {
/* 273 */       if (x.size() == 2 && y.size() == 2) return "2x2"; 
/* 274 */       return "1x4";
/*     */     } 
/*     */     
/* 277 */     return "undefined";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String roomCategory(String roomSize, String roomColor) {
/* 284 */     if (roomSize.equals("1x1")) {
/* 285 */       switch (roomColor) {
/*     */         case "brown":
/* 287 */           return "1x1";
/*     */         case "purple":
/* 289 */           return "Puzzle";
/*     */         case "orange":
/* 291 */           return "Trap";
/*     */         case "green":
/*     */         case "red":
/*     */         case "pink":
/*     */         case "yellow":
/* 296 */           return "General";
/*     */       } 
/* 298 */       return "undefined";
/*     */     } 
/*     */     
/* 301 */     return roomSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Point playerMarkerPos() {
/* 309 */     if (!mapExists()) return null; 
/* 310 */     Minecraft mc = Minecraft.func_71410_x();
/* 311 */     ItemStack mapSlot = mc.field_71439_g.field_71071_by.func_70301_a(8);
/* 312 */     MapData mapData = Items.field_151098_aY.func_77873_a(mapSlot, (World)mc.field_71441_e);
/* 313 */     if (mapData == null) return null; 
/* 314 */     if (mapData.field_76203_h != null) {
/* 315 */       for (Map.Entry<String, Vec4b> entry : (Iterable<Map.Entry<String, Vec4b>>)mapData.field_76203_h.entrySet()) {
/* 316 */         if (((Vec4b)entry.getValue()).func_176110_a() == 1) {
/* 317 */           int x = ((Vec4b)entry.getValue()).func_176112_b() / 2 + 64;
/* 318 */           int y = ((Vec4b)entry.getValue()).func_176113_c() / 2 + 64;
/* 319 */           return new Point(x, y);
/*     */         } 
/*     */       } 
/*     */     }
/* 323 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Point getPhysicalCornerPos(String direction, List<Point> currentPhysicalSegments) {
/* 331 */     TreeSet<Integer> xSet = new TreeSet<>();
/* 332 */     TreeSet<Integer> ySet = new TreeSet<>();
/* 333 */     for (Point segment : currentPhysicalSegments) {
/* 334 */       xSet.add(Integer.valueOf(segment.x));
/* 335 */       ySet.add(Integer.valueOf(segment.y));
/*     */     } 
/*     */     
/* 338 */     switch (direction) {
/*     */       case "NW":
/* 340 */         return new Point(((Integer)xSet.first()).intValue(), ((Integer)ySet.first()).intValue());
/*     */       case "NE":
/* 342 */         return new Point(((Integer)xSet.last()).intValue() + 30, ((Integer)ySet.first()).intValue());
/*     */       case "SE":
/* 344 */         return new Point(((Integer)xSet.last()).intValue() + 30, ((Integer)ySet.last()).intValue() + 30);
/*     */       case "SW":
/* 346 */         return new Point(((Integer)xSet.first()).intValue(), ((Integer)ySet.last()).intValue() + 30);
/*     */     } 
/* 348 */     return null;
/*     */   }
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
/*     */   public static List<String> possibleDirections(String roomSize, List<Point> currentRoomSegments) {
/* 361 */     List<String> directions = new ArrayList<>();
/* 362 */     if (roomSize.equals("1x1") || roomSize.equals("2x2")) {
/* 363 */       directions.add("NW");
/* 364 */       directions.add("NE");
/* 365 */       directions.add("SE");
/* 366 */       directions.add("SW");
/*     */     } else {
/* 368 */       TreeSet<Integer> xSet = new TreeSet<>();
/* 369 */       TreeSet<Integer> ySet = new TreeSet<>();
/* 370 */       for (Point segment : currentRoomSegments) {
/* 371 */         xSet.add(Integer.valueOf(segment.x));
/* 372 */         ySet.add(Integer.valueOf(segment.y));
/*     */       } 
/* 374 */       if (roomSize.equals("L-shape")) {
/* 375 */         List<Integer> x = new ArrayList<>(xSet);
/* 376 */         List<Integer> y = new ArrayList<>(ySet);
/*     */         
/* 378 */         if (!currentRoomSegments.contains(new Point(((Integer)x.get(0)).intValue(), ((Integer)y.get(0)).intValue()))) { directions.add("SW"); }
/* 379 */         else if (!currentRoomSegments.contains(new Point(((Integer)x.get(0)).intValue(), ((Integer)y.get(1)).intValue()))) { directions.add("SE"); }
/* 380 */         else if (!currentRoomSegments.contains(new Point(((Integer)x.get(1)).intValue(), ((Integer)y.get(0)).intValue()))) { directions.add("NW"); }
/* 381 */         else if (!currentRoomSegments.contains(new Point(((Integer)x.get(1)).intValue(), ((Integer)y.get(1)).intValue()))) { directions.add("NE"); }
/*     */       
/* 383 */       } else if (roomSize.startsWith("1x")) {
/* 384 */         if (xSet.size() >= 2 && ySet.size() == 1) {
/* 385 */           directions.add("NW");
/* 386 */           directions.add("SE");
/* 387 */         } else if (xSet.size() == 1 && ySet.size() >= 2) {
/* 388 */           directions.add("NE");
/* 389 */           directions.add("SW");
/*     */         } 
/*     */       } 
/*     */     } 
/* 393 */     return directions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BlockPos actualToRelative(BlockPos actual, String cornerDirection, Point locationOfCorner) {
/* 400 */     double x = 0.0D;
/* 401 */     double z = 0.0D;
/* 402 */     switch (cornerDirection) {
/*     */       case "NW":
/* 404 */         x = actual.func_177958_n() - locationOfCorner.getX();
/* 405 */         z = actual.func_177952_p() - locationOfCorner.getY();
/*     */         break;
/*     */       case "NE":
/* 408 */         x = actual.func_177952_p() - locationOfCorner.getY();
/* 409 */         z = -(actual.func_177958_n() - locationOfCorner.getX());
/*     */         break;
/*     */       case "SE":
/* 412 */         x = -(actual.func_177958_n() - locationOfCorner.getX());
/* 413 */         z = -(actual.func_177952_p() - locationOfCorner.getY());
/*     */         break;
/*     */       case "SW":
/* 416 */         x = -(actual.func_177952_p() - locationOfCorner.getY());
/* 417 */         z = actual.func_177958_n() - locationOfCorner.getX();
/*     */         break;
/*     */     } 
/* 420 */     return new BlockPos(x, actual.func_177956_o(), z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BlockPos relativeToActual(BlockPos relative, String cornerDirection, Point locationOfCorner) {
/* 427 */     double x = 0.0D;
/* 428 */     double z = 0.0D;
/* 429 */     switch (cornerDirection) {
/*     */       case "NW":
/* 431 */         x = relative.func_177958_n() + locationOfCorner.getX();
/* 432 */         z = relative.func_177952_p() + locationOfCorner.getY();
/*     */         break;
/*     */       case "NE":
/* 435 */         x = -(relative.func_177952_p() - locationOfCorner.getX());
/* 436 */         z = relative.func_177958_n() + locationOfCorner.getY();
/*     */         break;
/*     */       case "SE":
/* 439 */         x = -(relative.func_177958_n() - locationOfCorner.getX());
/* 440 */         z = -(relative.func_177952_p() - locationOfCorner.getY());
/*     */         break;
/*     */       case "SW":
/* 443 */         x = relative.func_177952_p() + locationOfCorner.getX();
/* 444 */         z = -(relative.func_177958_n() - locationOfCorner.getY());
/*     */         break;
/*     */     } 
/* 447 */     return new BlockPos(x, relative.func_177956_o(), z);
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\io\github\quantizr\dungeonroom\\utils\MapUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */