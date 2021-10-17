/*     */ package me.Danker.handlers;
/*     */ import java.io.File;
/*     */ import me.Danker.DankersSkyblockMod;
/*     */ import me.Danker.commands.BlockSlayerCommand;
/*     */ import me.Danker.commands.DisplayCommand;
/*     */ import me.Danker.commands.LootCommand;
/*     */ import me.Danker.commands.MoveCommand;
/*     */ import me.Danker.commands.ScaleCommand;
/*     */ import me.Danker.commands.ToggleCommand;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraftforge.common.config.Configuration;
/*     */ 
/*     */ public class ConfigHandler {
/*     */   public static Configuration config;
/*     */   
/*     */   public static void init() {
/*  18 */     config = new Configuration(new File("config/Danker's Skyblock Mod.cfg"));
/*     */     try {
/*  20 */       config.load();
/*  21 */     } catch (Exception ex) {
/*  22 */       ex.printStackTrace();
/*     */     } finally {
/*  24 */       config.save();
/*     */     } 
/*     */   }
/*     */   private static final String file = "config/Danker's Skyblock Mod.cfg";
/*     */   public static int getInt(String category, String key) {
/*  29 */     config = new Configuration(new File("config/Danker's Skyblock Mod.cfg"));
/*     */     try {
/*  31 */       config.load();
/*  32 */       if (config.getCategory(category).containsKey(key)) {
/*  33 */         return config.get(category, key, 0).getInt();
/*     */       }
/*  35 */     } catch (Exception ex) {
/*  36 */       ex.printStackTrace();
/*     */     } finally {
/*  38 */       config.save();
/*     */     } 
/*  40 */     return 0;
/*     */   }
/*     */   
/*     */   public static double getDouble(String category, String key) {
/*  44 */     config = new Configuration(new File("config/Danker's Skyblock Mod.cfg"));
/*     */     try {
/*  46 */       config.load();
/*  47 */       if (config.getCategory(category).containsKey(key)) {
/*  48 */         return config.get(category, key, 0.0D).getDouble();
/*     */       }
/*  50 */     } catch (Exception ex) {
/*  51 */       ex.printStackTrace();
/*     */     } finally {
/*  53 */       config.save();
/*     */     } 
/*  55 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public static String getString(String category, String key) {
/*  59 */     config = new Configuration(new File("config/Danker's Skyblock Mod.cfg"));
/*     */     try {
/*  61 */       config.load();
/*  62 */       if (config.getCategory(category).containsKey(key)) {
/*  63 */         return config.get(category, key, "").getString();
/*     */       }
/*  65 */     } catch (Exception ex) {
/*  66 */       ex.printStackTrace();
/*     */     } finally {
/*  68 */       config.save();
/*     */     } 
/*  70 */     return "";
/*     */   }
/*     */   
/*     */   public static boolean getBoolean(String category, String key) {
/*  74 */     config = new Configuration(new File("config/Danker's Skyblock Mod.cfg"));
/*     */     try {
/*  76 */       config.load();
/*  77 */       if (config.getCategory(category).containsKey(key)) {
/*  78 */         return config.get(category, key, false).getBoolean();
/*     */       }
/*  80 */     } catch (Exception ex) {
/*  81 */       ex.printStackTrace();
/*     */     } finally {
/*  83 */       config.save();
/*     */     } 
/*  85 */     return true;
/*     */   }
/*     */   
/*     */   public static void writeIntConfig(String category, String key, int value) {
/*  89 */     config = new Configuration(new File("config/Danker's Skyblock Mod.cfg"));
/*     */     try {
/*  91 */       config.load();
/*  92 */       int set = config.get(category, key, value).getInt();
/*  93 */       config.getCategory(category).get(key).set(value);
/*  94 */     } catch (Exception ex) {
/*  95 */       ex.printStackTrace();
/*     */     } finally {
/*  97 */       config.save();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void writeDoubleConfig(String category, String key, double value) {
/* 102 */     config = new Configuration(new File("config/Danker's Skyblock Mod.cfg"));
/*     */     try {
/* 104 */       config.load();
/* 105 */       double set = config.get(category, key, value).getDouble();
/* 106 */       config.getCategory(category).get(key).set(value);
/* 107 */     } catch (Exception ex) {
/* 108 */       ex.printStackTrace();
/*     */     } finally {
/* 110 */       config.save();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void writeStringConfig(String category, String key, String value) {
/* 115 */     config = new Configuration(new File("config/Danker's Skyblock Mod.cfg"));
/*     */     try {
/* 117 */       config.load();
/* 118 */       String set = config.get(category, key, value).getString();
/* 119 */       config.getCategory(category).get(key).set(value);
/* 120 */     } catch (Exception ex) {
/* 121 */       ex.printStackTrace();
/*     */     } finally {
/* 123 */       config.save();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void writeBooleanConfig(String category, String key, boolean value) {
/* 128 */     config = new Configuration(new File("config/Danker's Skyblock Mod.cfg"));
/*     */     try {
/* 130 */       config.load();
/* 131 */       boolean set = config.get(category, key, value).getBoolean();
/* 132 */       config.getCategory(category).get(key).set(value);
/* 133 */     } catch (Exception ex) {
/* 134 */       ex.printStackTrace();
/*     */     } finally {
/* 136 */       config.save();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean hasKey(String category, String key) {
/* 141 */     config = new Configuration(new File("config/Danker's Skyblock Mod.cfg"));
/*     */     try {
/* 143 */       config.load();
/* 144 */       if (!config.hasCategory(category)) return false; 
/* 145 */       return config.getCategory(category).containsKey(key);
/* 146 */     } catch (Exception ex) {
/* 147 */       ex.printStackTrace();
/*     */     } finally {
/* 149 */       config.save();
/*     */     } 
/* 151 */     return false;
/*     */   }
/*     */   
/*     */   public static void deleteCategory(String category) {
/* 155 */     config = new Configuration(new File("config/Danker's Skyblock Mod.cfg"));
/*     */     try {
/* 157 */       config.load();
/* 158 */       if (config.hasCategory(category)) {
/* 159 */         config.removeCategory(new ConfigCategory(category));
/*     */       }
/* 161 */     } catch (Exception ex) {
/* 162 */       ex.printStackTrace();
/*     */     } finally {
/* 164 */       config.save();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void reloadConfig() {
/* 170 */     if (!hasKey("toggles", "GParty")) writeBooleanConfig("toggles", "GParty", false); 
/* 171 */     if (!hasKey("toggles", "Coords")) writeBooleanConfig("toggles", "Coords", false); 
/* 172 */     if (!hasKey("toggles", "Golden")) writeBooleanConfig("toggles", "Golden", false); 
/* 173 */     if (!hasKey("toggles", "SlayerCount")) writeBooleanConfig("toggles", "SlayerCount", true); 
/* 174 */     if (!hasKey("toggles", "RNGesusAlerts")) writeBooleanConfig("toggles", "RNGesusAlerts", true); 
/* 175 */     if (!hasKey("toggles", "SplitFishing")) writeBooleanConfig("toggles", "SplitFishing", true); 
/* 176 */     if (!hasKey("toggles", "ChatMaddox")) writeBooleanConfig("toggles", "ChatMaddox", true); 
/* 177 */     if (!hasKey("toggles", "SpiritBearAlerts")) writeBooleanConfig("toggles", "SpiritBearAlerts", true); 
/* 178 */     if (!hasKey("toggles", "AOTD")) writeBooleanConfig("toggles", "AOTD", false); 
/* 179 */     if (!hasKey("toggles", "LividDagger")) writeBooleanConfig("toggles", "LividDagger", false); 
/* 180 */     if (!hasKey("toggles", "MidasStaffMessages")) writeBooleanConfig("toggles", "MidasStaffMessages", true); 
/* 181 */     if (!hasKey("toggles", "ImplosionMessages")) writeBooleanConfig("toggles", "ImplosionMessages", true); 
/* 182 */     if (!hasKey("toggles", "HealMessages")) writeBooleanConfig("toggles", "HealMessages", true); 
/* 183 */     if (!hasKey("toggles", "PetColors")) writeBooleanConfig("toggles", "PetColors", false); 
/* 184 */     if (!hasKey("toggles", "BlockSlayer")) writeStringConfig("toggles", "BlockSlayer", ""); 
/* 185 */     if (!hasKey("toggles", "GolemAlerts")) writeBooleanConfig("toggles", "GolemAlerts", false); 
/* 186 */     if (!hasKey("toggles", "ExpertiseLore")) writeBooleanConfig("toggles", "ExpertiseLore", true); 
/* 187 */     if (!hasKey("toggles", "Skill50Display")) writeBooleanConfig("toggles", "Skill50Display", false); 
/* 188 */     if (!hasKey("toggles", "OutlineText")) writeBooleanConfig("toggles", "OutlineText", false); 
/* 189 */     if (!hasKey("toggles", "CakeTimer")) writeBooleanConfig("toggles", "CakeTimer", false);
/*     */     
/* 191 */     if (!hasKey("toggles", "SceptreMessages")) writeBooleanConfig("toggles", "SceptreMessages", true); 
/* 192 */     if (!hasKey("toggles", "MidasStaffMessages")) writeBooleanConfig("toggles", "MidasStaffMessages", true); 
/* 193 */     if (!hasKey("toggles", "ImplosionMessages")) writeBooleanConfig("toggles", "ImplosionMessages", true); 
/* 194 */     if (!hasKey("toggles", "HealMessages")) writeBooleanConfig("toggles", "HealMessages", true); 
/* 195 */     if (!hasKey("toggles", "CooldownMessages")) writeBooleanConfig("toggles", "CooldownMessages", true); 
/* 196 */     if (!hasKey("toggles", "ManaMessages")) writeBooleanConfig("toggles", "ManaMessages", true);
/*     */     
/* 198 */     if (!hasKey("toggles", "DungeonTimer")) writeBooleanConfig("toggles", "DungeonTimer", false); 
/* 199 */     if (!hasKey("toggles", "LowHealthNotify")) writeBooleanConfig("toggles", "LowHealthNotify", false); 
/* 200 */     if (!hasKey("toggles", "LividSolver")) writeBooleanConfig("toggles", "LividSolver", false); 
/* 201 */     if (!hasKey("toggles", "StopSalvageStarred")) writeBooleanConfig("toggles", "StopSalvageStarred", false); 
/* 202 */     if (!hasKey("toggles", "WatcherReadyMessage")) writeBooleanConfig("toggles", "WatcherReadyMessage", false); 
/* 203 */     if (!hasKey("toggles", "PickBlock")) writeBooleanConfig("toggles", "PickBlock", false); 
/* 204 */     if (!hasKey("toggles", "FlowerWeapons")) writeBooleanConfig("toggles", "FlowerWeapons", false); 
/* 205 */     if (!hasKey("toggles", "NotifySlayerSlain")) writeBooleanConfig("toggles", "NotifySlayerSlain", false); 
/* 206 */     if (!hasKey("toggles", "NecronNotifications")) writeBooleanConfig("toggles", "NecronNotifications", false); 
/* 207 */     if (!hasKey("toggles", "BonzoTimer")) writeBooleanConfig("toggles", "BonzoTimer", false); 
/* 208 */     if (!hasKey("toggles", "BlockBreakingFarms")) writeBooleanConfig("toggles", "BlockBreakingFarms", false); 
/* 209 */     if (!hasKey("toggles", "AutoSkillTracker")) writeBooleanConfig("toggles", "AutoSkillTracker", false);
/*     */     
/* 211 */     if (!hasKey("toggles", "ThreeManPuzzle")) writeBooleanConfig("toggles", "ThreeManPuzzle", false); 
/* 212 */     if (!hasKey("toggles", "OruoPuzzle")) writeBooleanConfig("toggles", "OruoPuzzle", false); 
/* 213 */     if (!hasKey("toggles", "BlazePuzzle")) writeBooleanConfig("toggles", "BlazePuzzle", false); 
/* 214 */     if (!hasKey("toggles", "CreeperPuzzle")) writeBooleanConfig("toggles", "CreeperPuzzle", false); 
/* 215 */     if (!hasKey("toggles", "WaterPuzzle")) writeBooleanConfig("toggles", "WaterPuzzle", false); 
/* 216 */     if (!hasKey("toggles", "TicTacToePuzzle")) writeBooleanConfig("toggles", "TicTacToePuzzle", false); 
/* 217 */     if (!hasKey("toggles", "StartsWithTerminal")) writeBooleanConfig("toggles", "StartsWithTerminal", false); 
/* 218 */     if (!hasKey("toggles", "SelectAllTerminal")) writeBooleanConfig("toggles", "SelectAllTerminal", false); 
/* 219 */     if (!hasKey("toggles", "ClickInOrderTerminal")) writeBooleanConfig("toggles", "ClickInOrderTerminal", false); 
/* 220 */     if (!hasKey("toggles", "BlockWrongTerminalClicks")) writeBooleanConfig("toggles", "BlockWrongTerminalClicks", false); 
/* 221 */     if (!hasKey("toggles", "IgnoreItemFrameOnSeaLanterns")) writeBooleanConfig("toggles", "IgnoreItemFrameOnSeaLanterns", false);
/*     */     
/* 223 */     if (!hasKey("toggles", "UltraSequencer")) writeBooleanConfig("toggles", "UltraSequencer", false); 
/* 224 */     if (!hasKey("toggles", "Chronomatron")) writeBooleanConfig("toggles", "Chronomatron", false); 
/* 225 */     if (!hasKey("toggles", "Superpairs")) writeBooleanConfig("toggles", "Superpairs", false); 
/* 226 */     if (!hasKey("toggles", "HideTooltipsInExperimentAddons")) writeBooleanConfig("toggles", "HideTooltipsInExperimentAddons", false);
/*     */ 
/*     */     
/* 229 */     if (!hasKey("api", "APIKey")) writeStringConfig("api", "APIKey", "");
/*     */ 
/*     */     
/* 232 */     if (!hasKey("wolf", "svens")) writeIntConfig("wolf", "svens", 0); 
/* 233 */     if (!hasKey("wolf", "teeth")) writeIntConfig("wolf", "teeth", 0); 
/* 234 */     if (!hasKey("wolf", "wheel")) writeIntConfig("wolf", "wheel", 0); 
/* 235 */     if (!hasKey("wolf", "wheelDrops")) writeIntConfig("wolf", "wheelDrops", 0); 
/* 236 */     if (!hasKey("wolf", "spirit")) writeIntConfig("wolf", "spirit", 0); 
/* 237 */     if (!hasKey("wolf", "book")) writeIntConfig("wolf", "book", 0); 
/* 238 */     if (!hasKey("wolf", "egg")) writeIntConfig("wolf", "egg", 0); 
/* 239 */     if (!hasKey("wolf", "couture")) writeIntConfig("wolf", "couture", 0); 
/* 240 */     if (!hasKey("wolf", "bait")) writeIntConfig("wolf", "bait", 0); 
/* 241 */     if (!hasKey("wolf", "flux")) writeIntConfig("wolf", "flux", 0); 
/* 242 */     if (!hasKey("wolf", "timeRNG")) writeDoubleConfig("wolf", "timeRNG", -1.0D); 
/* 243 */     if (!hasKey("wolf", "bossRNG")) writeIntConfig("wolf", "bossRNG", -1);
/*     */     
/* 245 */     if (!hasKey("spider", "tarantulas")) writeIntConfig("spider", "tarantulas", 0); 
/* 246 */     if (!hasKey("spider", "web")) writeIntConfig("spider", "web", 0); 
/* 247 */     if (!hasKey("spider", "tap")) writeIntConfig("spider", "tap", 0); 
/* 248 */     if (!hasKey("spider", "tapDrops")) writeIntConfig("spider", "tapDrops", 0); 
/* 249 */     if (!hasKey("spider", "bite")) writeIntConfig("spider", "bite", 0); 
/* 250 */     if (!hasKey("spider", "catalyst")) writeIntConfig("spider", "catalyst", 0); 
/* 251 */     if (!hasKey("spider", "book")) writeIntConfig("spider", "book", 0); 
/* 252 */     if (!hasKey("spider", "swatter")) writeIntConfig("spider", "swatter", 0); 
/* 253 */     if (!hasKey("spider", "talisman")) writeIntConfig("spider", "talisman", 0); 
/* 254 */     if (!hasKey("spider", "mosquito")) writeIntConfig("spider", "mosquito", 0); 
/* 255 */     if (!hasKey("spider", "timeRNG")) writeDoubleConfig("spider", "timeRNG", -1.0D); 
/* 256 */     if (!hasKey("spider", "bossRNG")) writeIntConfig("spider", "bossRNG", -1);
/*     */     
/* 258 */     if (!hasKey("zombie", "revs")) writeIntConfig("zombie", "revs", 0); 
/* 259 */     if (!hasKey("zombie", "revFlesh")) writeIntConfig("zombie", "revFlesh", 0); 
/* 260 */     if (!hasKey("zombie", "foulFlesh")) writeIntConfig("zombie", "foulFlesh", 0); 
/* 261 */     if (!hasKey("zombie", "foulFleshDrops")) writeIntConfig("zombie", "foulFleshDrops", 0); 
/* 262 */     if (!hasKey("zombie", "pestilence")) writeIntConfig("zombie", "pestilence", 0); 
/* 263 */     if (!hasKey("zombie", "undeadCatalyst")) writeIntConfig("zombie", "undeadCatalyst", 0); 
/* 264 */     if (!hasKey("zombie", "book")) writeIntConfig("zombie", "book", 0); 
/* 265 */     if (!hasKey("zombie", "beheaded")) writeIntConfig("zombie", "beheaded", 0); 
/* 266 */     if (!hasKey("zombie", "revCatalyst")) writeIntConfig("zombie", "revCatalyst", 0); 
/* 267 */     if (!hasKey("zombie", "snake")) writeIntConfig("zombie", "snake", 0); 
/* 268 */     if (!hasKey("zombie", "scythe")) writeIntConfig("zombie", "scythe", 0); 
/* 269 */     if (!hasKey("zombie", "timeRNG")) writeDoubleConfig("zombie", "timeRNG", -1.0D); 
/* 270 */     if (!hasKey("zombie", "bossRNG")) writeIntConfig("zombie", "bossRNG", -1);
/*     */     
/* 272 */     if (!hasKey("macro", "delay")) writeIntConfig("macro", "delay", 0); 
/* 273 */     if (!hasKey("macro", "delay")) writeIntConfig("macro", "terminal", 150); 
/* 274 */     if (!hasKey("macro", "swap")) writeIntConfig("macro", "swap", 150); 
/* 275 */     if (!hasKey("macro", "simon")) writeIntConfig("macro", "simon", 50);
/*     */     
/* 277 */     if (!hasKey("fishing", "seaCreature")) writeIntConfig("fishing", "seaCreature", 0); 
/* 278 */     if (!hasKey("fishing", "goodCatch")) writeIntConfig("fishing", "goodCatch", 0); 
/* 279 */     if (!hasKey("fishing", "greatCatch")) writeIntConfig("fishing", "greatCatch", 0); 
/* 280 */     if (!hasKey("fishing", "squid")) writeIntConfig("fishing", "squid", 0); 
/* 281 */     if (!hasKey("fishing", "seaWalker")) writeIntConfig("fishing", "seaWalker", 0); 
/* 282 */     if (!hasKey("fishing", "nightSquid")) writeIntConfig("fishing", "nightSquid", 0); 
/* 283 */     if (!hasKey("fishing", "seaGuardian")) writeIntConfig("fishing", "seaGuardian", 0); 
/* 284 */     if (!hasKey("fishing", "seaWitch")) writeIntConfig("fishing", "seaWitch", 0); 
/* 285 */     if (!hasKey("fishing", "seaArcher")) writeIntConfig("fishing", "seaArcher", 0); 
/* 286 */     if (!hasKey("fishing", "monsterOfDeep")) writeIntConfig("fishing", "monsterOfDeep", 0); 
/* 287 */     if (!hasKey("fishing", "catfish")) writeIntConfig("fishing", "catfish", 0); 
/* 288 */     if (!hasKey("fishing", "carrotKing")) writeIntConfig("fishing", "carrotKing", 0); 
/* 289 */     if (!hasKey("fishing", "seaLeech")) writeIntConfig("fishing", "seaLeech", 0); 
/* 290 */     if (!hasKey("fishing", "guardianDefender")) writeIntConfig("fishing", "guardianDefender", 0); 
/* 291 */     if (!hasKey("fishing", "deepSeaProtector")) writeIntConfig("fishing", "deepSeaProtector", 0); 
/* 292 */     if (!hasKey("fishing", "hydra")) writeIntConfig("fishing", "hydra", 0); 
/* 293 */     if (!hasKey("fishing", "seaEmperor")) writeIntConfig("fishing", "seaEmperor", 0); 
/* 294 */     if (!hasKey("fishing", "empTime")) writeDoubleConfig("fishing", "empTime", -1.0D); 
/* 295 */     if (!hasKey("fishing", "empSC")) writeIntConfig("fishing", "empSC", -1); 
/* 296 */     if (!hasKey("fishing", "milestone")) writeIntConfig("fishing", "milestone", 0);
/*     */     
/* 298 */     if (!hasKey("fishing", "frozenSteve")) writeIntConfig("fishing", "frozenSteve", 0); 
/* 299 */     if (!hasKey("fishing", "snowman")) writeIntConfig("fishing", "snowman", 0); 
/* 300 */     if (!hasKey("fishing", "grinch")) writeIntConfig("fishing", "grinch", 0); 
/* 301 */     if (!hasKey("fishing", "yeti")) writeIntConfig("fishing", "yeti", 0); 
/* 302 */     if (!hasKey("fishing", "yetiTime")) writeDoubleConfig("fishing", "yetiTime", -1.0D); 
/* 303 */     if (!hasKey("fishing", "yetiSC")) writeIntConfig("fishing", "yetiSC", -1);
/*     */     
/* 305 */     if (!hasKey("fishing", "nurseShark")) writeIntConfig("fishing", "nurseShark", 0); 
/* 306 */     if (!hasKey("fishing", "blueShark")) writeIntConfig("fishing", "blueShark", 0); 
/* 307 */     if (!hasKey("fishing", "tigerShark")) writeIntConfig("fishing", "tigerShark", 0); 
/* 308 */     if (!hasKey("fishing", "greatWhiteShark")) writeIntConfig("fishing", "greatWhiteShark", 0);
/*     */     
/* 310 */     if (!hasKey("fishing", "scarecrow")) writeIntConfig("fishing", "scarecrow", 0); 
/* 311 */     if (!hasKey("fishing", "nightmare")) writeIntConfig("fishing", "nightmare", 0); 
/* 312 */     if (!hasKey("fishing", "werewolf")) writeIntConfig("fishing", "werewolf", 0); 
/* 313 */     if (!hasKey("fishing", "phantomFisher")) writeIntConfig("fishing", "phantomFisher", 0); 
/* 314 */     if (!hasKey("fishing", "grimReaper")) writeIntConfig("fishing", "grimReaper", 0);
/*     */ 
/*     */     
/* 317 */     if (!hasKey("mythological", "coins")) writeDoubleConfig("mythological", "coins", 0.0D); 
/* 318 */     if (!hasKey("mythological", "griffinFeather")) writeIntConfig("mythological", "griffinFeather", 0); 
/* 319 */     if (!hasKey("mythological", "crownOfGreed")) writeIntConfig("mythological", "crownOfGreed", 0); 
/* 320 */     if (!hasKey("mythological", "washedUpSouvenir")) writeIntConfig("mythological", "washedUpSouvenir", 0); 
/* 321 */     if (!hasKey("mythological", "minosHunter")) writeIntConfig("mythological", "minosHunter", 0); 
/* 322 */     if (!hasKey("mythological", "siameseLynx")) writeIntConfig("mythological", "siameseLynx", 0); 
/* 323 */     if (!hasKey("mythological", "minotaur")) writeIntConfig("mythological", "minotaur", 0); 
/* 324 */     if (!hasKey("mythological", "gaiaConstruct")) writeIntConfig("mythological", "gaiaConstruct", 0); 
/* 325 */     if (!hasKey("mythological", "minosChampion")) writeIntConfig("mythological", "minosChampion", 0); 
/* 326 */     if (!hasKey("mythological", "minosInquisitor")) writeIntConfig("mythological", "minosInquisitor", 0);
/*     */ 
/*     */     
/* 329 */     if (!hasKey("catacombs", "recombobulator")) writeIntConfig("catacombs", "recombobulator", 0); 
/* 330 */     if (!hasKey("catacombs", "fumingBooks")) writeIntConfig("catacombs", "fumingBooks", 0);
/*     */     
/* 332 */     if (!hasKey("catacombs", "bonzoStaff")) writeIntConfig("catacombs", "bonzoStaff", 0); 
/* 333 */     if (!hasKey("catacombs", "floorOneCoins")) writeDoubleConfig("catacombs", "floorOneCoins", 0.0D); 
/* 334 */     if (!hasKey("catacombs", "floorOneTime")) writeDoubleConfig("catacombs", "floorOneTime", 0.0D);
/*     */     
/* 336 */     if (!hasKey("catacombs", "scarfStudies")) writeIntConfig("catacombs", "scarfStudies", 0); 
/* 337 */     if (!hasKey("catacombs", "floorTwoCoins")) writeDoubleConfig("catacombs", "floorTwoCoins", 0.0D); 
/* 338 */     if (!hasKey("catacombs", "floorTwoTime")) writeDoubleConfig("catacombs", "floorTwoTime", 0.0D);
/*     */     
/* 340 */     if (!hasKey("catacombs", "adaptiveHelm")) writeIntConfig("catacombs", "adaptiveHelm", 0); 
/* 341 */     if (!hasKey("catacombs", "adaptiveChest")) writeIntConfig("catacombs", "adaptiveChest", 0); 
/* 342 */     if (!hasKey("catacombs", "adaptiveLegging")) writeIntConfig("catacombs", "adaptiveLegging", 0); 
/* 343 */     if (!hasKey("catacombs", "adaptiveBoot")) writeIntConfig("catacombs", "adaptiveBoot", 0); 
/* 344 */     if (!hasKey("catacombs", "adaptiveSword")) writeIntConfig("catacombs", "adaptiveSword", 0); 
/* 345 */     if (!hasKey("catacombs", "floorThreeCoins")) writeDoubleConfig("catacombs", "floorThreeCoins", 0.0D); 
/* 346 */     if (!hasKey("catacombs", "floorThreeTime")) writeDoubleConfig("catacombs", "floorThreeTime", 0.0D);
/*     */     
/* 348 */     if (!hasKey("catacombs", "spiritWing")) writeIntConfig("catacombs", "spiritWing", 0); 
/* 349 */     if (!hasKey("catacombs", "spiritBone")) writeIntConfig("catacombs", "spiritBone", 0); 
/* 350 */     if (!hasKey("catacombs", "spiritBoot")) writeIntConfig("catacombs", "spiritBoot", 0); 
/* 351 */     if (!hasKey("catacombs", "spiritSword")) writeIntConfig("catacombs", "spiritSword", 0); 
/* 352 */     if (!hasKey("catacombs", "spiritBow")) writeIntConfig("catacombs", "spiritBow", 0); 
/* 353 */     if (!hasKey("catacombs", "spiritPetEpic")) writeIntConfig("catacombs", "spiritPetEpic", 0); 
/* 354 */     if (!hasKey("catacombs", "spiritPetLeg")) writeIntConfig("catacombs", "spiritPetLeg", 0); 
/* 355 */     if (!hasKey("catacombs", "floorFourCoins")) writeDoubleConfig("catacombs", "floorFourCoins", 0.0D); 
/* 356 */     if (!hasKey("catacombs", "floorFourTime")) writeDoubleConfig("catacombs", "floorFourTime", 0.0D);
/*     */     
/* 358 */     if (!hasKey("catacombs", "warpedStone")) writeIntConfig("catacombs", "warpedStone", 0); 
/* 359 */     if (!hasKey("catacombs", "shadowAssassinHelm")) writeIntConfig("catacombs", "shadowAssassinHelm", 0); 
/* 360 */     if (!hasKey("catacombs", "shadowAssassinChest")) writeIntConfig("catacombs", "shadowAssassinChest", 0); 
/* 361 */     if (!hasKey("catacombs", "shadowAssassinLegging")) writeIntConfig("catacombs", "shadowAssassinLegging", 0); 
/* 362 */     if (!hasKey("catacombs", "shadowAssassinBoot")) writeIntConfig("catacombs", "shadowAssassinBoot", 0); 
/* 363 */     if (!hasKey("catacombs", "lastBreath")) writeIntConfig("catacombs", "lastBreath", 0); 
/* 364 */     if (!hasKey("catacombs", "lividDagger")) writeIntConfig("catacombs", "lividDagger", 0); 
/* 365 */     if (!hasKey("catacombs", "shadowFury")) writeIntConfig("catacombs", "shadowFury", 0); 
/* 366 */     if (!hasKey("catacombs", "floorFiveCoins")) writeDoubleConfig("catacombs", "floorFiveCoins", 0.0D); 
/* 367 */     if (!hasKey("catacombs", "floorFiveTime")) writeDoubleConfig("catacombs", "floorFiveTime", 0.0D);
/*     */     
/* 369 */     if (!hasKey("catacombs", "ancientRose")) writeIntConfig("catacombs", "ancientRose", 0); 
/* 370 */     if (!hasKey("catacombs", "precursorEye")) writeIntConfig("catacombs", "precursorEye", 0); 
/* 371 */     if (!hasKey("catacombs", "giantsSword")) writeIntConfig("catacombs", "giantsSword", 0); 
/* 372 */     if (!hasKey("catacombs", "necroLordHelm")) writeIntConfig("catacombs", "necroHelm", 0); 
/* 373 */     if (!hasKey("catacombs", "necroLordChest")) writeIntConfig("catacombs", "necroChest", 0); 
/* 374 */     if (!hasKey("catacombs", "necroLordLegging")) writeIntConfig("catacombs", "necroLegging", 0); 
/* 375 */     if (!hasKey("catacombs", "necroLordBoot")) writeIntConfig("catacombs", "necroBoot", 0); 
/* 376 */     if (!hasKey("catacombs", "necroSword")) writeIntConfig("catacombs", "necroSword", 0); 
/* 377 */     if (!hasKey("catacombs", "floorSixCoins")) writeDoubleConfig("catacombs", "floorSixCoins", 0.0D); 
/* 378 */     if (!hasKey("catacombs", "floorSixTime")) writeDoubleConfig("catacombs", "floorSixTime", 0.0D);
/*     */     
/* 380 */     if (!hasKey("catacombs", "witherBlood")) writeIntConfig("catacombs", "witherBlood", 0); 
/* 381 */     if (!hasKey("catacombs", "witherCloak")) writeIntConfig("catacombs", "witherCloak", 0); 
/* 382 */     if (!hasKey("catacombs", "implosion")) writeIntConfig("catacombs", "implosion", 0); 
/* 383 */     if (!hasKey("catacombs", "witherShield")) writeIntConfig("catacombs", "witherShield", 0); 
/* 384 */     if (!hasKey("catacombs", "shadowWarp")) writeIntConfig("catacombs", "shadowWarp", 0); 
/* 385 */     if (!hasKey("catacombs", "necronsHandle")) writeIntConfig("catacombs", "necronsHandle", 0); 
/* 386 */     if (!hasKey("catacombs", "autoRecomb")) writeIntConfig("catacombs", "autoRecomb", 0); 
/* 387 */     if (!hasKey("catacombs", "witherHelm")) writeIntConfig("catacombs", "witherHelm", 0); 
/* 388 */     if (!hasKey("catacombs", "witherChest")) writeIntConfig("catacombs", "witherChest", 0); 
/* 389 */     if (!hasKey("catacombs", "witherLegging")) writeIntConfig("catacombs", "witherLegging", 0); 
/* 390 */     if (!hasKey("catacombs", "witherBoot")) writeIntConfig("catacombs", "witherBoot", 0); 
/* 391 */     if (!hasKey("catacombs", "floorSevenCoins")) writeDoubleConfig("catacombs", "floorSevenCoins", 0.0D); 
/* 392 */     if (!hasKey("catacombs", "floorSevenTime")) writeDoubleConfig("catacombs", "floorSevenTime", 0.0D);
/*     */     
/* 394 */     if (!hasKey("misc", "display")) writeStringConfig("misc", "display", "off"); 
/* 395 */     if (!hasKey("misc", "autoDisplay")) writeBooleanConfig("misc", "autoDisplay", false); 
/* 396 */     if (!hasKey("misc", "skill50Time")) writeIntConfig("misc", "skill50Time", 3); 
/* 397 */     if (!hasKey("misc", "cakeTime")) writeDoubleConfig("misc", "cakeTime", 0.0D); 
/* 398 */     if (!hasKey("misc", "showSkillTracker")) writeBooleanConfig("misc", "showSkillTracker", false); 
/* 399 */     if (!hasKey("misc", "firstLaunch")) writeBooleanConfig("misc", "firstLaunch", true);
/*     */ 
/*     */     
/* 402 */     ScaledResolution scaled = new ScaledResolution(Minecraft.func_71410_x());
/* 403 */     int height = scaled.func_78328_b();
/* 404 */     if (!hasKey("locations", "coordsX")) writeIntConfig("locations", "coordsX", 5); 
/* 405 */     if (!hasKey("locations", "coordsY")) writeIntConfig("locations", "coordsY", height - 25); 
/* 406 */     if (!hasKey("locations", "displayX")) writeIntConfig("locations", "displayX", 80); 
/* 407 */     if (!hasKey("locations", "displayY")) writeIntConfig("locations", "displayY", 5); 
/* 408 */     if (!hasKey("locations", "dungeonTimerX")) writeIntConfig("locations", "dungeonTimerX", 5); 
/* 409 */     if (!hasKey("locations", "dungeonTimerY")) writeIntConfig("locations", "dungeonTimerY", 5); 
/* 410 */     if (!hasKey("locations", "skill50X")) writeIntConfig("locations", "skill50X", 40); 
/* 411 */     if (!hasKey("locations", "skill50Y")) writeIntConfig("locations", "skill50Y", 10); 
/* 412 */     if (!hasKey("locations", "lividHpX")) writeIntConfig("locations", "lividHpX", 40); 
/* 413 */     if (!hasKey("locations", "lividHpY")) writeIntConfig("locations", "lividHpY", 20); 
/* 414 */     if (!hasKey("locations", "cakeTimerX")) writeIntConfig("locations", "cakeTimerX", 40); 
/* 415 */     if (!hasKey("locations", "cakeTimerY")) writeIntConfig("locations", "cakeTimerY", 30); 
/* 416 */     if (!hasKey("locations", "skillTrackerX")) writeIntConfig("locations", "skillTrackerX", 40); 
/* 417 */     if (!hasKey("locations", "skillTrackerY")) writeIntConfig("locations", "skillTrackerY", 50); 
/* 418 */     if (!hasKey("locations", "waterAnswerX")) writeIntConfig("locations", "waterAnswerX", 100); 
/* 419 */     if (!hasKey("locations", "waterAnswerY")) writeIntConfig("locations", "waterAnswerY", 100); 
/* 420 */     if (!hasKey("locations", "bonzoTimerX")) writeIntConfig("locations", "bonzoTimerX", 40); 
/* 421 */     if (!hasKey("locations", "bonzoTimerY")) writeIntConfig("locations", "bonzoTimerY", 80); 
/* 422 */     if (!hasKey("scales", "coordsScale")) writeDoubleConfig("scales", "coordsScale", 1.0D); 
/* 423 */     if (!hasKey("scales", "displayScale")) writeDoubleConfig("scales", "displayScale", 1.0D); 
/* 424 */     if (!hasKey("scales", "dungeonTimerScale")) writeDoubleConfig("scales", "dungeonTimerScale", 1.0D); 
/* 425 */     if (!hasKey("scales", "skill50Scale")) writeDoubleConfig("scales", "skill50Scale", 1.0D); 
/* 426 */     if (!hasKey("scales", "lividHpScale")) writeDoubleConfig("scales", "lividHpScale", 1.0D); 
/* 427 */     if (!hasKey("scales", "cakeTimerScale")) writeDoubleConfig("scales", "cakeTimerScale", 1.0D); 
/* 428 */     if (!hasKey("scales", "skillTrackerScale")) writeDoubleConfig("scales", "skillTrackerScale", 1.0D); 
/* 429 */     if (!hasKey("scales", "waterAnswerScale")) writeDoubleConfig("scales", "waterAnswerScale", 1.0D); 
/* 430 */     if (!hasKey("scales", "bonzoTimerScale")) writeDoubleConfig("scales", "bonzoTimerScale", 1.0D);
/*     */     
/* 432 */     if (!hasKey("colors", "main")) writeStringConfig("colors", "main", EnumChatFormatting.GREEN.toString()); 
/* 433 */     if (!hasKey("colors", "secondary")) writeStringConfig("colors", "secondary", EnumChatFormatting.DARK_GREEN.toString()); 
/* 434 */     if (!hasKey("colors", "delimiter")) writeStringConfig("colors", "delimiter", EnumChatFormatting.AQUA.toString() + EnumChatFormatting.STRIKETHROUGH.toString()); 
/* 435 */     if (!hasKey("colors", "error")) writeStringConfig("colors", "error", EnumChatFormatting.RED.toString()); 
/* 436 */     if (!hasKey("colors", "type")) writeStringConfig("colors", "type", EnumChatFormatting.GREEN.toString()); 
/* 437 */     if (!hasKey("colors", "value")) writeStringConfig("colors", "value", EnumChatFormatting.DARK_GREEN.toString()); 
/* 438 */     if (!hasKey("colors", "skillAverage")) writeStringConfig("colors", "skillAverage", EnumChatFormatting.GOLD.toString()); 
/* 439 */     if (!hasKey("colors", "answer")) writeStringConfig("colors", "answer", EnumChatFormatting.DARK_GREEN.toString()); 
/* 440 */     if (!hasKey("colors", "skill50Display")) writeStringConfig("colors", "skill50Display", EnumChatFormatting.AQUA.toString()); 
/* 441 */     if (!hasKey("colors", "coordsDisplay")) writeStringConfig("colors", "coordsDisplay", EnumChatFormatting.WHITE.toString()); 
/* 442 */     if (!hasKey("colors", "cakeDisplay")) writeStringConfig("colors", "cakeDisplay", EnumChatFormatting.GOLD.toString()); 
/* 443 */     if (!hasKey("colors", "skillTracker")) writeStringConfig("colors", "skillTracker", EnumChatFormatting.AQUA.toString()); 
/* 444 */     if (!hasKey("colors", "triviaWrongAnswer")) writeStringConfig("colors", "triviaWrongAnswer", EnumChatFormatting.RED.toString()); 
/* 445 */     if (!hasKey("colors", "bonzoDisplay")) writeStringConfig("colors", "bonzoDisplay", EnumChatFormatting.RED.toString()); 
/* 446 */     if (!hasKey("colors", "blazeLowest")) writeIntConfig("colors", "blazeLowest", 16711680); 
/* 447 */     if (!hasKey("colors", "blazeHighest")) writeIntConfig("colors", "blazeHighest", 4259648); 
/* 448 */     if (!hasKey("colors", "pet1To9")) writeIntConfig("colors", "pet1To9", 10066329); 
/* 449 */     if (!hasKey("colors", "pet10To19")) writeIntConfig("colors", "pet10To19", 14033984); 
/* 450 */     if (!hasKey("colors", "pet20To29")) writeIntConfig("colors", "pet20To29", 15684144); 
/* 451 */     if (!hasKey("colors", "pet30To39")) writeIntConfig("colors", "pet30To39", 16761856); 
/* 452 */     if (!hasKey("colors", "pet40To49")) writeIntConfig("colors", "pet40To49", 961589); 
/* 453 */     if (!hasKey("colors", "pet50To59")) writeIntConfig("colors", "pet50To59", 35544); 
/* 454 */     if (!hasKey("colors", "pet60To69")) writeIntConfig("colors", "pet60To69", 8277958); 
/* 455 */     if (!hasKey("colors", "pet70To79")) writeIntConfig("colors", "pet70To79", 14045128); 
/* 456 */     if (!hasKey("colors", "pet80To89")) writeIntConfig("colors", "pet80To89", 6037301); 
/* 457 */     if (!hasKey("colors", "pet90To99")) writeIntConfig("colors", "pet90To99", 10385742); 
/* 458 */     if (!hasKey("colors", "pet100")) writeIntConfig("colors", "pet100", 15913545);
/*     */ 
/*     */     
/* 461 */     if (!hasKey("commands", "reparty")) writeBooleanConfig("commands", "reparty", false);
/*     */     
/* 463 */     ToggleCommand.gpartyToggled = getBoolean("toggles", "GParty");
/* 464 */     ToggleCommand.coordsToggled = getBoolean("toggles", "Coords");
/* 465 */     ToggleCommand.goldenToggled = getBoolean("toggles", "Golden");
/* 466 */     ToggleCommand.slayerCountTotal = getBoolean("toggles", "SlayerCount");
/* 467 */     ToggleCommand.rngesusAlerts = getBoolean("toggles", "RNGesusAlerts");
/* 468 */     ToggleCommand.splitFishing = getBoolean("toggles", "SplitFishing");
/* 469 */     ToggleCommand.chatMaddoxToggled = getBoolean("toggles", "ChatMaddox");
/* 470 */     ToggleCommand.spiritBearAlerts = getBoolean("toggles", "SpiritBearAlerts");
/* 471 */     ToggleCommand.aotdToggled = getBoolean("toggles", "AOTD");
/* 472 */     ToggleCommand.lividDaggerToggled = getBoolean("toggles", "LividDagger");
/* 473 */     ToggleCommand.petColoursToggled = getBoolean("toggles", "PetColors");
/* 474 */     ToggleCommand.golemAlertToggled = getBoolean("toggles", "GolemAlerts");
/* 475 */     ToggleCommand.expertiseLoreToggled = getBoolean("toggles", "ExpertiseLore");
/* 476 */     ToggleCommand.skill50DisplayToggled = getBoolean("toggles", "Skill50Display");
/* 477 */     ToggleCommand.outlineTextToggled = getBoolean("toggles", "OutlineText");
/* 478 */     ToggleCommand.cakeTimerToggled = getBoolean("toggles", "CakeTimer");
/*     */     
/* 480 */     ToggleCommand.sceptreMessages = getBoolean("toggles", "SceptreMessages");
/* 481 */     ToggleCommand.midasStaffMessages = getBoolean("toggles", "MidasStaffMessages");
/* 482 */     ToggleCommand.implosionMessages = getBoolean("toggles", "ImplosionMessages");
/* 483 */     ToggleCommand.healMessages = getBoolean("toggles", "HealMessages");
/* 484 */     ToggleCommand.cooldownMessages = getBoolean("toggles", "CooldownMessages");
/* 485 */     ToggleCommand.manaMessages = getBoolean("toggles", "ManaMessages");
/*     */     
/* 487 */     ToggleCommand.dungeonTimerToggled = getBoolean("toggles", "DungeonTimer");
/* 488 */     ToggleCommand.lowHealthNotifyToggled = getBoolean("toggles", "LowHealthNotify");
/* 489 */     ToggleCommand.lividSolverToggled = getBoolean("toggles", "LividSolver");
/* 490 */     ToggleCommand.stopSalvageStarredToggled = getBoolean("toggles", "StopSalvageStarred");
/* 491 */     ToggleCommand.watcherReadyToggled = getBoolean("toggles", "WatcherReadyMessage");
/* 492 */     ToggleCommand.notifySlayerSlainToggled = getBoolean("toggles", "NotifySlayerSlain");
/* 493 */     ToggleCommand.necronNotificationsToggled = getBoolean("toggles", "NecronNotifications");
/* 494 */     ToggleCommand.bonzoTimerToggled = getBoolean("toggles", "BonzoTimer");
/* 495 */     ToggleCommand.blockBreakingFarmsToggled = getBoolean("toggles", "BlockBreakingFarms");
/* 496 */     ToggleCommand.swapToPickBlockToggled = getBoolean("toggles", "PickBlock");
/* 497 */     ToggleCommand.flowerWeaponsToggled = getBoolean("toggles", "FlowerWeapons");
/* 498 */     ToggleCommand.autoSkillTrackerToggled = getBoolean("toggles", "AutoSkillTracker");
/*     */     
/* 500 */     ToggleCommand.threeManToggled = getBoolean("toggles", "ThreeManPuzzle");
/* 501 */     ToggleCommand.oruoToggled = getBoolean("toggles", "OruoPuzzle");
/* 502 */     ToggleCommand.blazeToggled = getBoolean("toggles", "BlazePuzzle");
/* 503 */     ToggleCommand.creeperToggled = getBoolean("toggles", "CreeperPuzzle");
/* 504 */     ToggleCommand.waterToggled = getBoolean("toggles", "WaterPuzzle");
/* 505 */     ToggleCommand.ticTacToeToggled = getBoolean("toggles", "TicTacToePuzzle");
/* 506 */     ToggleCommand.startsWithToggled = getBoolean("toggles", "StartsWithTerminal");
/* 507 */     ToggleCommand.selectAllToggled = getBoolean("toggles", "SelectAllTerminal");
/* 508 */     ToggleCommand.clickInOrderToggled = getBoolean("toggles", "ClickInOrderTerminal");
/* 509 */     ToggleCommand.blockWrongTerminalClicksToggled = getBoolean("toggles", "BlockWrongTerminalClicks");
/* 510 */     ToggleCommand.itemFrameOnSeaLanternsToggled = getBoolean("toggles", "IgnoreItemFrameOnSeaLanterns");
/*     */     
/* 512 */     ToggleCommand.ultrasequencerToggled = getBoolean("toggles", "UltraSequencer");
/* 513 */     ToggleCommand.chronomatronToggled = getBoolean("toggles", "Chronomatron");
/* 514 */     ToggleCommand.superpairsToggled = getBoolean("toggles", "Superpairs");
/* 515 */     ToggleCommand.hideTooltipsInExperimentAddonsToggled = getBoolean("toggles", "HideTooltipsInExperimentAddons");
/*     */     
/* 517 */     String onlySlayer = getString("toggles", "BlockSlayer");
/* 518 */     if (!onlySlayer.equals("")) {
/* 519 */       BlockSlayerCommand.onlySlayerName = onlySlayer.substring(0, onlySlayer.lastIndexOf(" "));
/* 520 */       BlockSlayerCommand.onlySlayerNumber = onlySlayer.substring(onlySlayer.lastIndexOf(" ") + 1);
/*     */     } 
/*     */ 
/*     */     
/* 524 */     LootCommand.wolfSvens = getInt("wolf", "svens");
/* 525 */     LootCommand.wolfTeeth = getInt("wolf", "teeth");
/* 526 */     LootCommand.wolfWheels = getInt("wolf", "wheel");
/* 527 */     LootCommand.wolfWheelsDrops = getInt("wolf", "wheelDrops");
/* 528 */     LootCommand.wolfSpirits = getInt("wolf", "spirit");
/* 529 */     LootCommand.wolfBooks = getInt("wolf", "book");
/* 530 */     LootCommand.wolfEggs = getInt("wolf", "egg");
/* 531 */     LootCommand.wolfCoutures = getInt("wolf", "couture");
/* 532 */     LootCommand.wolfBaits = getInt("wolf", "bait");
/* 533 */     LootCommand.wolfFluxes = getInt("wolf", "flux");
/* 534 */     LootCommand.wolfTime = getDouble("wolf", "timeRNG");
/* 535 */     LootCommand.wolfBosses = getInt("wolf", "bossRNG");
/*     */     
/* 537 */     LootCommand.spiderTarantulas = getInt("spider", "tarantulas");
/* 538 */     LootCommand.spiderWebs = getInt("spider", "web");
/* 539 */     LootCommand.spiderTAP = getInt("spider", "tap");
/* 540 */     LootCommand.spiderTAPDrops = getInt("spider", "tapDrops");
/* 541 */     LootCommand.spiderBites = getInt("spider", "bite");
/* 542 */     LootCommand.spiderCatalysts = getInt("spider", "catalyst");
/* 543 */     LootCommand.spiderBooks = getInt("spider", "book");
/* 544 */     LootCommand.spiderSwatters = getInt("spider", "swatter");
/* 545 */     LootCommand.spiderTalismans = getInt("spider", "talisman");
/* 546 */     LootCommand.spiderMosquitos = getInt("spider", "mosquito");
/* 547 */     LootCommand.spiderTime = getDouble("spider", "timeRNG");
/* 548 */     LootCommand.spiderBosses = getInt("spider", "bossRNG");
/*     */     
/* 550 */     LootCommand.zombieRevs = getInt("zombie", "revs");
/* 551 */     LootCommand.zombieRevFlesh = getInt("zombie", "revFlesh");
/* 552 */     LootCommand.zombieFoulFlesh = getInt("zombie", "foulFlesh");
/* 553 */     LootCommand.zombieFoulFleshDrops = getInt("zombie", "foulFleshDrops");
/* 554 */     LootCommand.zombiePestilences = getInt("zombie", "pestilence");
/* 555 */     LootCommand.zombieUndeadCatas = getInt("zombie", "undeadCatalyst");
/* 556 */     LootCommand.zombieBooks = getInt("zombie", "book");
/* 557 */     LootCommand.zombieBeheadeds = getInt("zombie", "beheaded");
/* 558 */     LootCommand.zombieRevCatas = getInt("zombie", "revCatalyst");
/* 559 */     LootCommand.zombieSnakes = getInt("zombie", "snake");
/* 560 */     LootCommand.zombieScythes = getInt("zombie", "scythe");
/* 561 */     LootCommand.zombieTime = getDouble("zombie", "timeRNG");
/* 562 */     LootCommand.zombieBosses = getInt("zombie", "bossRNG");
/*     */ 
/*     */     
/* 565 */     DelayCommand.boneDelay = getInt("macro", "delay");
/* 566 */     SwapCommand.swapDelay = getInt("macro", "swap");
/* 567 */     SleepCommand.waitAmount = getInt("macro", "terminal");
/* 568 */     SimonCommand.simonAmount = getInt("macro", "simon");
/*     */ 
/*     */     
/* 571 */     LootCommand.seaCreatures = getInt("fishing", "seaCreature");
/* 572 */     LootCommand.goodCatches = getInt("fishing", "goodCatch");
/* 573 */     LootCommand.greatCatches = getInt("fishing", "greatCatch");
/* 574 */     LootCommand.squids = getInt("fishing", "squid");
/* 575 */     LootCommand.seaWalkers = getInt("fishing", "seaWalker");
/* 576 */     LootCommand.nightSquids = getInt("fishing", "nightSquid");
/* 577 */     LootCommand.seaGuardians = getInt("fishing", "seaGuardian");
/* 578 */     LootCommand.seaWitches = getInt("fishing", "seaWitch");
/* 579 */     LootCommand.seaArchers = getInt("fishing", "seaArcher");
/* 580 */     LootCommand.monsterOfTheDeeps = getInt("fishing", "monsterOfDeep");
/* 581 */     LootCommand.catfishes = getInt("fishing", "catfish");
/* 582 */     LootCommand.carrotKings = getInt("fishing", "carrotKing");
/* 583 */     LootCommand.seaLeeches = getInt("fishing", "seaLeech");
/* 584 */     LootCommand.guardianDefenders = getInt("fishing", "guardianDefender");
/* 585 */     LootCommand.deepSeaProtectors = getInt("fishing", "deepSeaProtector");
/* 586 */     LootCommand.hydras = getInt("fishing", "hydra");
/* 587 */     LootCommand.seaEmperors = getInt("fishing", "seaEmperor");
/* 588 */     LootCommand.empTime = getDouble("fishing", "empTime");
/* 589 */     LootCommand.empSCs = getInt("fishing", "empSC");
/* 590 */     LootCommand.fishingMilestone = getInt("fishing", "milestone");
/*     */     
/* 592 */     LootCommand.frozenSteves = getInt("fishing", "frozenSteve");
/* 593 */     LootCommand.frostyTheSnowmans = getInt("fishing", "snowman");
/* 594 */     LootCommand.grinches = getInt("fishing", "grinch");
/* 595 */     LootCommand.yetis = getInt("fishing", "yeti");
/* 596 */     LootCommand.yetiTime = getDouble("fishing", "yetiTime");
/* 597 */     LootCommand.yetiSCs = getInt("fishing", "yetiSC");
/*     */     
/* 599 */     LootCommand.nurseSharks = getInt("fishing", "nurseShark");
/* 600 */     LootCommand.blueSharks = getInt("fishing", "blueShark");
/* 601 */     LootCommand.tigerSharks = getInt("fishing", "tigerShark");
/* 602 */     LootCommand.greatWhiteSharks = getInt("fishing", "greatWhiteShark");
/*     */     
/* 604 */     LootCommand.scarecrows = getInt("fishing", "scarecrow");
/* 605 */     LootCommand.nightmares = getInt("fishing", "nightmare");
/* 606 */     LootCommand.werewolfs = getInt("fishing", "werewolf");
/* 607 */     LootCommand.phantomFishers = getInt("fishing", "phantomFisher");
/* 608 */     LootCommand.grimReapers = getInt("fishing", "grimReaper");
/*     */ 
/*     */     
/* 611 */     LootCommand.mythCoins = getDouble("mythological", "coins");
/* 612 */     LootCommand.griffinFeathers = getInt("mythological", "griffinFeather");
/* 613 */     LootCommand.crownOfGreeds = getInt("mythological", "crownOfGreed");
/* 614 */     LootCommand.washedUpSouvenirs = getInt("mythological", "washedUpSouvenir");
/* 615 */     LootCommand.minosHunters = getInt("mythological", "minosHunter");
/* 616 */     LootCommand.siameseLynxes = getInt("mythological", "siameseLynx");
/* 617 */     LootCommand.minotaurs = getInt("mythological", "minotaur");
/* 618 */     LootCommand.gaiaConstructs = getInt("mythological", "gaiaConstruct");
/* 619 */     LootCommand.minosChampions = getInt("mythological", "minosChampion");
/* 620 */     LootCommand.minosInquisitors = getInt("mythological", "minosInquisitor");
/*     */ 
/*     */     
/* 623 */     LootCommand.recombobulators = getInt("catacombs", "recombobulator");
/* 624 */     LootCommand.fumingPotatoBooks = getInt("catacombs", "fumingBooks");
/*     */     
/* 626 */     LootCommand.bonzoStaffs = getInt("catacombs", "bonzoStaff");
/* 627 */     LootCommand.f1CoinsSpent = getDouble("catacombs", "floorOneCoins");
/* 628 */     LootCommand.f1TimeSpent = getDouble("catacombs", "floorOneTime");
/*     */     
/* 630 */     LootCommand.scarfStudies = getInt("catacombs", "scarfStudies");
/* 631 */     LootCommand.f2CoinsSpent = getDouble("catacombs", "floorTwoCoins");
/* 632 */     LootCommand.f2TimeSpent = getDouble("catacombs", "floorTwoTime");
/*     */     
/* 634 */     LootCommand.adaptiveHelms = getInt("catacombs", "adaptiveHelm");
/* 635 */     LootCommand.adaptiveChests = getInt("catacombs", "adaptiveChest");
/* 636 */     LootCommand.adaptiveLegs = getInt("catacombs", "adaptiveLegging");
/* 637 */     LootCommand.adaptiveBoots = getInt("catacombs", "adaptiveBoot");
/* 638 */     LootCommand.adaptiveSwords = getInt("catacombs", "adaptiveSword");
/* 639 */     LootCommand.f3CoinsSpent = getDouble("catacombs", "floorThreeCoins");
/* 640 */     LootCommand.f3TimeSpent = getDouble("catacombs", "floorThreeTime");
/*     */     
/* 642 */     LootCommand.spiritWings = getInt("catacombs", "spiritWing");
/* 643 */     LootCommand.spiritBones = getInt("catacombs", "spiritBone");
/* 644 */     LootCommand.spiritBoots = getInt("catacombs", "spiritBoot");
/* 645 */     LootCommand.spiritSwords = getInt("catacombs", "spiritSword");
/* 646 */     LootCommand.spiritBows = getInt("catacombs", "spiritBow");
/* 647 */     LootCommand.epicSpiritPets = getInt("catacombs", "spiritPetEpic");
/* 648 */     LootCommand.legSpiritPets = getInt("catacombs", "spiritPetLeg");
/* 649 */     LootCommand.f4CoinsSpent = getDouble("catacombs", "floorFourCoins");
/* 650 */     LootCommand.f4TimeSpent = getDouble("catacombs", "floorFourTime");
/*     */     
/* 652 */     LootCommand.warpedStones = getInt("catacombs", "warpedStone");
/* 653 */     LootCommand.shadowAssHelms = getInt("catacombs", "shadowAssassinHelm");
/* 654 */     LootCommand.shadowAssChests = getInt("catacombs", "shadowAssassinChest");
/* 655 */     LootCommand.shadowAssLegs = getInt("catacombs", "shadowAssassinLegging");
/* 656 */     LootCommand.shadowAssBoots = getInt("catacombs", "shadowAssassinBoot");
/* 657 */     LootCommand.lastBreaths = getInt("catacombs", "lastBreath");
/* 658 */     LootCommand.lividDaggers = getInt("catacombs", "lividDagger");
/* 659 */     LootCommand.shadowFurys = getInt("catacombs", "shadowFury");
/* 660 */     LootCommand.f5CoinsSpent = getDouble("catacombs", "floorFiveCoins");
/* 661 */     LootCommand.f5TimeSpent = getDouble("catacombs", "floorFiveTime");
/*     */     
/* 663 */     LootCommand.ancientRoses = getInt("catacombs", "ancientRose");
/* 664 */     LootCommand.precursorEyes = getInt("catacombs", "precursorEye");
/* 665 */     LootCommand.giantsSwords = getInt("catacombs", "giantsSword");
/* 666 */     LootCommand.necroLordHelms = getInt("catacombs", "necroLordHelm");
/* 667 */     LootCommand.necroLordChests = getInt("catacombs", "necroLordChest");
/* 668 */     LootCommand.necroLordLegs = getInt("catacombs", "necroLordLegging");
/* 669 */     LootCommand.necroLordBoots = getInt("catacombs", "necroLordBoot");
/* 670 */     LootCommand.necroSwords = getInt("catacombs", "necroSword");
/* 671 */     LootCommand.f6CoinsSpent = getDouble("catacombs", "floorSixCoins");
/* 672 */     LootCommand.f6TimeSpent = getDouble("catacombs", "floorSixTime");
/*     */     
/* 674 */     LootCommand.witherBloods = getInt("catacombs", "witherBlood");
/* 675 */     LootCommand.witherCloaks = getInt("catacombs", "witherCloak");
/* 676 */     LootCommand.implosions = getInt("catacombs", "implosion");
/* 677 */     LootCommand.witherShields = getInt("catacombs", "witherShield");
/* 678 */     LootCommand.shadowWarps = getInt("catacombs", "shadowWarp");
/* 679 */     LootCommand.necronsHandles = getInt("catacombs", "necronsHandle");
/* 680 */     LootCommand.autoRecombs = getInt("catacombs", "autoRecomb");
/* 681 */     LootCommand.witherHelms = getInt("catacombs", "witherHelm");
/* 682 */     LootCommand.witherChests = getInt("catacombs", "witherChest");
/* 683 */     LootCommand.witherLegs = getInt("catacombs", "witherLegging");
/* 684 */     LootCommand.witherBoots = getInt("catacombs", "witherBoot");
/* 685 */     LootCommand.f7CoinsSpent = getDouble("catacombs", "floorSevenCoins");
/* 686 */     LootCommand.f7TimeSpent = getDouble("catacombs", "floorSevenTime");
/*     */ 
/*     */     
/* 689 */     DisplayCommand.display = getString("misc", "display");
/* 690 */     DisplayCommand.auto = getBoolean("misc", "autoDisplay");
/* 691 */     DankersSkyblockMod.SKILL_TIME = getInt("misc", "skill50Time") * 20;
/* 692 */     DankersSkyblockMod.cakeTime = getDouble("misc", "cakeTime");
/* 693 */     DankersSkyblockMod.showSkillTracker = getBoolean("misc", "showSkillTracker");
/* 694 */     DankersSkyblockMod.firstLaunch = getBoolean("misc", "firstLaunch");
/*     */     
/* 696 */     MoveCommand.coordsXY[0] = getInt("locations", "coordsX");
/* 697 */     MoveCommand.coordsXY[1] = getInt("locations", "coordsY");
/* 698 */     MoveCommand.displayXY[0] = getInt("locations", "displayX");
/* 699 */     MoveCommand.displayXY[1] = getInt("locations", "displayY");
/* 700 */     MoveCommand.dungeonTimerXY[0] = getInt("locations", "dungeonTimerX");
/* 701 */     MoveCommand.dungeonTimerXY[1] = getInt("locations", "dungeonTimerY");
/* 702 */     MoveCommand.skill50XY[0] = getInt("locations", "skill50X");
/* 703 */     MoveCommand.skill50XY[1] = getInt("locations", "skill50Y");
/* 704 */     MoveCommand.lividHpXY[0] = getInt("locations", "lividHpX");
/* 705 */     MoveCommand.lividHpXY[1] = getInt("locations", "lividHpY");
/* 706 */     MoveCommand.cakeTimerXY[0] = getInt("locations", "cakeTimerX");
/* 707 */     MoveCommand.cakeTimerXY[1] = getInt("locations", "cakeTimerY");
/* 708 */     MoveCommand.skillTrackerXY[0] = getInt("locations", "skillTrackerX");
/* 709 */     MoveCommand.skillTrackerXY[1] = getInt("locations", "skillTrackerY");
/* 710 */     MoveCommand.waterAnswerXY[0] = getInt("locations", "waterAnswerX");
/* 711 */     MoveCommand.waterAnswerXY[1] = getInt("locations", "waterAnswerY");
/* 712 */     MoveCommand.bonzoTimerXY[0] = getInt("locations", "bonzoTimerX");
/* 713 */     MoveCommand.bonzoTimerXY[1] = getInt("locations", "bonzoTimerY");
/*     */ 
/*     */     
/* 716 */     ScaleCommand.coordsScale = getDouble("scales", "coordsScale");
/* 717 */     ScaleCommand.displayScale = getDouble("scales", "displayScale");
/* 718 */     ScaleCommand.dungeonTimerScale = getDouble("scales", "dungeonTimerScale");
/* 719 */     ScaleCommand.skill50Scale = getDouble("scales", "skill50Scale");
/* 720 */     ScaleCommand.lividHpScale = getDouble("scales", "lividHpScale");
/* 721 */     ScaleCommand.cakeTimerScale = getDouble("scales", "cakeTimerScale");
/* 722 */     ScaleCommand.skillTrackerScale = getDouble("scales", "skillTrackerScale");
/* 723 */     ScaleCommand.waterAnswerScale = getDouble("scales", "waterAnswerScale");
/* 724 */     ScaleCommand.bonzoTimerScale = getDouble("scales", "bonzoTimerScale");
/*     */     
/* 726 */     DankersSkyblockMod.MAIN_COLOUR = getString("colors", "main");
/* 727 */     DankersSkyblockMod.SECONDARY_COLOUR = getString("colors", "secondary");
/* 728 */     DankersSkyblockMod.DELIMITER_COLOUR = getString("colors", "delimiter");
/* 729 */     DankersSkyblockMod.ERROR_COLOUR = getString("colors", "error");
/* 730 */     DankersSkyblockMod.TYPE_COLOUR = getString("colors", "type");
/* 731 */     DankersSkyblockMod.VALUE_COLOUR = getString("colors", "value");
/* 732 */     DankersSkyblockMod.SKILL_AVERAGE_COLOUR = getString("colors", "skillAverage");
/* 733 */     DankersSkyblockMod.ANSWER_COLOUR = getString("colors", "answer");
/* 734 */     DankersSkyblockMod.SKILL_50_COLOUR = getString("colors", "skill50Display");
/* 735 */     DankersSkyblockMod.COORDS_COLOUR = getString("colors", "coordsDisplay");
/* 736 */     DankersSkyblockMod.CAKE_COLOUR = getString("colors", "cakeDisplay");
/* 737 */     DankersSkyblockMod.SKILL_TRACKER_COLOUR = getString("colors", "skillTracker");
/* 738 */     DankersSkyblockMod.TRIVIA_WRONG_ANSWER_COLOUR = getString("colors", "triviaWrongAnswer");
/* 739 */     DankersSkyblockMod.BONZO_COLOR = getString("colors", "bonzoDisplay");
/* 740 */     DankersSkyblockMod.LOWEST_BLAZE_COLOUR = getInt("colors", "blazeLowest");
/* 741 */     DankersSkyblockMod.HIGHEST_BLAZE_COLOUR = getInt("colors", "blazeHighest");
/* 742 */     DankersSkyblockMod.PET_1_TO_9 = getInt("colors", "pet1To9");
/* 743 */     DankersSkyblockMod.PET_10_TO_19 = getInt("colors", "pet10To19");
/* 744 */     DankersSkyblockMod.PET_20_TO_29 = getInt("colors", "pet20To29");
/* 745 */     DankersSkyblockMod.PET_30_TO_39 = getInt("colors", "pet30To39");
/* 746 */     DankersSkyblockMod.PET_40_TO_49 = getInt("colors", "pet40To49");
/* 747 */     DankersSkyblockMod.PET_50_TO_59 = getInt("colors", "pet50To59");
/* 748 */     DankersSkyblockMod.PET_60_TO_69 = getInt("colors", "pet60To69");
/* 749 */     DankersSkyblockMod.PET_70_TO_79 = getInt("colors", "pet70To79");
/* 750 */     DankersSkyblockMod.PET_80_TO_89 = getInt("colors", "pet80To89");
/* 751 */     DankersSkyblockMod.PET_90_TO_99 = getInt("colors", "pet90To99");
/* 752 */     DankersSkyblockMod.PET_100 = getInt("colors", "pet100");
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\me\Danker\handlers\ConfigHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */