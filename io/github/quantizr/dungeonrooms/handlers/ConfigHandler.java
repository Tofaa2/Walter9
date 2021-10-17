/*     */ package io.github.quantizr.dungeonrooms.handlers;
/*     */ 
/*     */ import io.github.quantizr.dungeonrooms.DungeonRooms;
/*     */ import io.github.quantizr.dungeonrooms.dungeons.catacombs.DungeonManager;
/*     */ import io.github.quantizr.dungeonrooms.dungeons.catacombs.Waypoints;
/*     */ import java.io.File;
/*     */ import net.minecraftforge.common.config.ConfigCategory;
/*     */ import net.minecraftforge.common.config.Configuration;
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
/*     */ public class ConfigHandler
/*     */ {
/*     */   public static Configuration config;
/*     */   private static final String file = "config/DungeonRooms.cfg";
/*     */   
/*     */   public static void init() {
/*  34 */     config = new Configuration(new File("config/DungeonRooms.cfg"));
/*     */     try {
/*  36 */       config.load();
/*  37 */     } catch (Exception ex) {
/*  38 */       ex.printStackTrace();
/*     */     } finally {
/*  40 */       config.save();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static int getInt(String category, String key) {
/*  45 */     config = new Configuration(new File("config/DungeonRooms.cfg"));
/*     */     try {
/*  47 */       config.load();
/*  48 */       if (config.getCategory(category).containsKey(key)) {
/*  49 */         return config.get(category, key, 0).getInt();
/*     */       }
/*  51 */     } catch (Exception ex) {
/*  52 */       ex.printStackTrace();
/*     */     } finally {
/*  54 */       config.save();
/*     */     } 
/*  56 */     return 0;
/*     */   }
/*     */   
/*     */   public static double getDouble(String category, String key) {
/*  60 */     config = new Configuration(new File("config/DungeonRooms.cfg"));
/*     */     try {
/*  62 */       config.load();
/*  63 */       if (config.getCategory(category).containsKey(key)) {
/*  64 */         return config.get(category, key, 0.0D).getDouble();
/*     */       }
/*  66 */     } catch (Exception ex) {
/*  67 */       ex.printStackTrace();
/*     */     } finally {
/*  69 */       config.save();
/*     */     } 
/*  71 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public static String getString(String category, String key) {
/*  75 */     config = new Configuration(new File("config/DungeonRooms.cfg"));
/*     */     try {
/*  77 */       config.load();
/*  78 */       if (config.getCategory(category).containsKey(key)) {
/*  79 */         return config.get(category, key, "").getString();
/*     */       }
/*  81 */     } catch (Exception ex) {
/*  82 */       ex.printStackTrace();
/*     */     } finally {
/*  84 */       config.save();
/*     */     } 
/*  86 */     return "";
/*     */   }
/*     */   
/*     */   public static boolean getBoolean(String category, String key) {
/*  90 */     config = new Configuration(new File("config/DungeonRooms.cfg"));
/*     */     try {
/*  92 */       config.load();
/*  93 */       if (config.getCategory(category).containsKey(key)) {
/*  94 */         return config.get(category, key, false).getBoolean();
/*     */       }
/*  96 */     } catch (Exception ex) {
/*  97 */       ex.printStackTrace();
/*     */     } finally {
/*  99 */       config.save();
/*     */     } 
/* 101 */     return true;
/*     */   }
/*     */   
/*     */   public static void writeIntConfig(String category, String key, int value) {
/* 105 */     config = new Configuration(new File("config/DungeonRooms.cfg"));
/*     */     try {
/* 107 */       config.load();
/* 108 */       int set = config.get(category, key, value).getInt();
/* 109 */       config.getCategory(category).get(key).set(value);
/* 110 */     } catch (Exception ex) {
/* 111 */       ex.printStackTrace();
/*     */     } finally {
/* 113 */       config.save();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void writeDoubleConfig(String category, String key, double value) {
/* 118 */     config = new Configuration(new File("config/DungeonRooms.cfg"));
/*     */     try {
/* 120 */       config.load();
/* 121 */       double set = config.get(category, key, value).getDouble();
/* 122 */       config.getCategory(category).get(key).set(value);
/* 123 */     } catch (Exception ex) {
/* 124 */       ex.printStackTrace();
/*     */     } finally {
/* 126 */       config.save();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void writeStringConfig(String category, String key, String value) {
/* 131 */     config = new Configuration(new File("config/DungeonRooms.cfg"));
/*     */     try {
/* 133 */       config.load();
/* 134 */       String set = config.get(category, key, value).getString();
/* 135 */       config.getCategory(category).get(key).set(value);
/* 136 */     } catch (Exception ex) {
/* 137 */       ex.printStackTrace();
/*     */     } finally {
/* 139 */       config.save();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void writeBooleanConfig(String category, String key, boolean value) {
/* 144 */     config = new Configuration(new File("config/DungeonRooms.cfg"));
/*     */     try {
/* 146 */       config.load();
/* 147 */       boolean set = config.get(category, key, value).getBoolean();
/* 148 */       config.getCategory(category).get(key).set(value);
/* 149 */     } catch (Exception ex) {
/* 150 */       ex.printStackTrace();
/*     */     } finally {
/* 152 */       config.save();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean hasKey(String category, String key) {
/* 157 */     config = new Configuration(new File("config/DungeonRooms.cfg"));
/*     */     try {
/* 159 */       config.load();
/* 160 */       if (!config.hasCategory(category)) return false; 
/* 161 */       return config.getCategory(category).containsKey(key);
/* 162 */     } catch (Exception ex) {
/* 163 */       ex.printStackTrace();
/*     */     } finally {
/* 165 */       config.save();
/*     */     } 
/* 167 */     return false;
/*     */   }
/*     */   
/*     */   public static void deleteCategory(String category) {
/* 171 */     config = new Configuration(new File("config/DungeonRooms.cfg"));
/*     */     try {
/* 173 */       config.load();
/* 174 */       if (config.hasCategory(category)) {
/* 175 */         config.removeCategory(new ConfigCategory(category));
/*     */       }
/* 177 */     } catch (Exception ex) {
/* 178 */       ex.printStackTrace();
/*     */     } finally {
/* 180 */       config.save();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void reloadConfig() {
/* 185 */     if (!hasKey("toggles", "guiToggled")) writeBooleanConfig("toggles", "guiToggled", true); 
/* 186 */     if (!hasKey("toggles", "motdToggled")) writeBooleanConfig("toggles", "motdToggled", true); 
/* 187 */     if (!hasKey("toggles", "waypointsToggled")) writeBooleanConfig("toggles", "waypointsToggled", true);
/*     */     
/* 189 */     if (!hasKey("waypoint", "showEntrance")) writeBooleanConfig("waypoint", "showEntrance", true); 
/* 190 */     if (!hasKey("waypoint", "showSuperboom")) writeBooleanConfig("waypoint", "showSuperboom", true); 
/* 191 */     if (!hasKey("waypoint", "showSecrets")) writeBooleanConfig("waypoint", "showSecrets", true); 
/* 192 */     if (!hasKey("waypoint", "showFairySouls")) writeBooleanConfig("waypoint", "showFairySouls", true); 
/* 193 */     if (!hasKey("waypoint", "sneakToDisable")) writeBooleanConfig("waypoint", "sneakToDisable", true); 
/* 194 */     if (!hasKey("waypoint", "disableWhenAllFound")) writeBooleanConfig("waypoint", "disableWhenAllFound", true);
/*     */     
/* 196 */     if (!hasKey("waypoint", "showWaypointText")) writeBooleanConfig("waypoint", "showWaypointText", true); 
/* 197 */     if (!hasKey("waypoint", "showBoundingBox")) writeBooleanConfig("waypoint", "showBoundingBox", true); 
/* 198 */     if (!hasKey("waypoint", "showBeacon")) writeBooleanConfig("waypoint", "showBeacon", true);
/*     */     
/* 200 */     if (!hasKey("waypoint", "practiceModeOn")) writeBooleanConfig("waypoint", "practiceModeOn", false);
/*     */     
/* 202 */     if (!hasKey("gui", "scaleX")) writeIntConfig("gui", "scaleX", 50); 
/* 203 */     if (!hasKey("gui", "scaleY")) writeIntConfig("gui", "scaleY", 5); 
/* 204 */     if (!hasKey("gui", "hotkeyOpen")) writeStringConfig("gui", "hotkeyOpen", "gui");
/*     */ 
/*     */     
/* 207 */     if (!hasKey("drm", "version")) {
/* 208 */       writeStringConfig("drm", "version", "3.2.4");
/* 209 */       DungeonRooms.firstLogin = true;
/*     */     }
/* 211 */     else if (!getString("drm", "version").equals("3.2.4")) {
/* 212 */       writeStringConfig("drm", "version", "3.2.4");
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 220 */     DungeonManager.guiToggled = getBoolean("toggles", "guiToggled");
/* 221 */     DungeonManager.motdToggled = getBoolean("toggles", "motdToggled");
/*     */     
/* 223 */     Waypoints.enabled = getBoolean("toggles", "waypointsToggled");
/*     */     
/* 225 */     Waypoints.showEntrance = getBoolean("waypoint", "showEntrance");
/* 226 */     Waypoints.showSuperboom = getBoolean("waypoint", "showSuperboom");
/* 227 */     Waypoints.showSecrets = getBoolean("waypoint", "showSecrets");
/* 228 */     Waypoints.showFairySouls = getBoolean("waypoint", "showFairySouls");
/* 229 */     Waypoints.sneakToDisable = getBoolean("waypoint", "sneakToDisable");
/* 230 */     Waypoints.disableWhenAllFound = getBoolean("waypoint", "disableWhenAllFound");
/*     */     
/* 232 */     Waypoints.showWaypointText = getBoolean("waypoint", "showWaypointText");
/* 233 */     Waypoints.showBoundingBox = getBoolean("waypoint", "showBoundingBox");
/* 234 */     Waypoints.showBeacon = getBoolean("waypoint", "showBeacon");
/*     */     
/* 236 */     Waypoints.practiceModeOn = getBoolean("waypoint", "practiceModeOn");
/*     */     
/* 238 */     DungeonRooms.textLocX = getInt("gui", "scaleX");
/* 239 */     DungeonRooms.textLocY = getInt("gui", "scaleY");
/* 240 */     DungeonRooms.imageHotkeyOpen = getString("gui", "hotkeyOpen");
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\io\github\quantizr\dungeonrooms\handlers\ConfigHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */