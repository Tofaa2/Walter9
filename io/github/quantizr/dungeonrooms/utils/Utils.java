/*     */ package io.github.quantizr.dungeonrooms.utils;
/*     */ 
/*     */ import io.github.quantizr.dungeonrooms.DungeonRooms;
/*     */ import io.github.quantizr.dungeonrooms.handlers.ScoreboardHandler;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.nio.file.FileSystem;
/*     */ import java.nio.file.FileSystems;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.Paths;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.stream.Stream;
/*     */ import java.util.zip.InflaterInputStream;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.client.settings.KeyBinding;
/*     */ import net.minecraft.scoreboard.ScoreObjective;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import org.apache.logging.log4j.Level;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.apache.logging.log4j.core.LoggerContext;
/*     */ import org.apache.logging.log4j.core.config.BaseConfiguration;
/*     */ import org.apache.logging.log4j.core.config.LoggerConfig;
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
/*     */ public class Utils
/*     */ {
/*     */   public static boolean inSkyblock = false;
/*     */   public static boolean inCatacombs = false;
/*     */   public static boolean dungeonOverride = false;
/*     */   
/*     */   public static void checkForSkyblock() {
/*  58 */     if (dungeonOverride) {
/*  59 */       inSkyblock = true;
/*     */       return;
/*     */     } 
/*  62 */     Minecraft mc = Minecraft.func_71410_x();
/*  63 */     if (mc != null && mc.field_71441_e != null && !mc.func_71356_B()) {
/*  64 */       ScoreObjective scoreboardObj = mc.field_71441_e.func_96441_U().func_96539_a(1);
/*  65 */       if (scoreboardObj != null) {
/*  66 */         String scObjName = ScoreboardHandler.cleanSB(scoreboardObj.func_96678_d());
/*  67 */         if (scObjName.contains("SKYBLOCK")) {
/*  68 */           inSkyblock = true;
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/*  73 */     inSkyblock = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void checkForCatacombs() {
/*  82 */     if (dungeonOverride) {
/*  83 */       inCatacombs = true;
/*     */       return;
/*     */     } 
/*  86 */     if (inSkyblock) {
/*  87 */       List<String> scoreboard = ScoreboardHandler.getSidebarLines();
/*  88 */       for (String s : scoreboard) {
/*  89 */         String sCleaned = ScoreboardHandler.cleanSB(s);
/*  90 */         if (sCleaned.contains("The Catacombs")) {
/*  91 */           inCatacombs = true;
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/*  96 */     inCatacombs = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void checkForConflictingHotkeys() {
/* 101 */     Minecraft mc = Minecraft.func_71410_x();
/* 102 */     for (KeyBinding drmKeybind : DungeonRooms.keyBindings) {
/* 103 */       for (KeyBinding keybinding : mc.field_71474_y.field_74324_K) {
/* 104 */         if (drmKeybind.func_151463_i() != 0 && drmKeybind != keybinding && drmKeybind.func_151463_i() == keybinding.func_151463_i()) {
/* 105 */           mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText("§d§l--- Dungeon Rooms Mod ---\n §r§cThe hotkey \"" + 
/* 106 */                 GameSettings.func_74298_c(drmKeybind.func_151463_i()) + "\", which is used to " + drmKeybind
/* 107 */                 .func_151464_g() + ", has a conflict with a keybinding from \"" + keybinding
/* 108 */                 .func_151466_e() + "\".\n §c§lPlease go into the Minecraft Controls menu and change one of the keybindings.\n§d§l------------------------"));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<Path> getAllPaths(String folderName) {
/* 122 */     List<Path> paths = new ArrayList<>(); try {
/*     */       Path Path;
/* 124 */       URI uri = DungeonRooms.class.getResource("/assets/dungeonrooms/" + folderName).toURI();
/*     */       
/* 126 */       FileSystem fileSystem = null;
/* 127 */       if (uri.getScheme().equals("jar")) {
/* 128 */         fileSystem = FileSystems.newFileSystem(uri, Collections.emptyMap());
/* 129 */         Path = fileSystem.getPath("/assets/dungeonrooms/" + folderName, new String[0]);
/*     */       } else {
/* 131 */         Path = Paths.get(uri);
/*     */       } 
/* 133 */       Stream<Path> walk = Files.walk(Path, 3, new java.nio.file.FileVisitOption[0]);
/* 134 */       for (Iterator<Path> it = walk.iterator(); it.hasNext(); ) {
/* 135 */         Path path = it.next();
/* 136 */         String name = path.getFileName().toString();
/* 137 */         if (name.endsWith(".skeleton")) paths.add(path); 
/*     */       } 
/* 139 */       if (fileSystem != null) fileSystem.close(); 
/* 140 */     } catch (URISyntaxException|IOException e) {
/* 141 */       e.printStackTrace();
/*     */     } 
/* 143 */     return paths;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static HashMap<String, long[]> pathsToRoomData(String parentFolder, List<Path> allPaths) {
/* 151 */     HashMap<String, long[]> allRoomData = (HashMap)new HashMap<>();
/*     */     try {
/* 153 */       for (Path path : allPaths) {
/* 154 */         if (!path.getParent().getFileName().toString().equals(parentFolder))
/* 155 */           continue;  String name = path.getFileName().toString();
/* 156 */         InputStream input = DungeonRooms.class.getResourceAsStream(path.toString());
/* 157 */         ObjectInputStream data = new ObjectInputStream(new InflaterInputStream(input));
/* 158 */         long[] roomData = (long[])data.readObject();
/* 159 */         allRoomData.put(name.substring(0, name.length() - 9), roomData);
/* 160 */         DungeonRooms.logger.debug("DungeonRooms: Loaded " + name);
/*     */       } 
/* 162 */     } catch (IOException|ClassNotFoundException e) {
/* 163 */       e.printStackTrace();
/*     */     } 
/* 165 */     DungeonRooms.logger.info("DungeonRooms: Loaded " + allRoomData.size() + " " + parentFolder + " rooms");
/* 166 */     return allRoomData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setLogLevel(Logger logger, Level level) {
/* 173 */     LoggerContext ctx = (LoggerContext)LogManager.getContext(false);
/* 174 */     BaseConfiguration config = (BaseConfiguration)ctx.getConfiguration();
/*     */     
/* 176 */     LoggerConfig loggerConfig = config.getLoggerConfig(logger.getName());
/* 177 */     LoggerConfig specificConfig = loggerConfig;
/*     */     
/* 179 */     if (!loggerConfig.getName().equals(logger.getName())) {
/* 180 */       specificConfig = new LoggerConfig(logger.getName(), level, true);
/* 181 */       specificConfig.setParent(loggerConfig);
/* 182 */       config.addLogger(logger.getName(), specificConfig);
/*     */     } 
/* 184 */     specificConfig.setLevel(level);
/* 185 */     ctx.updateLoggers();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long shortToLong(short a, short b, short c, short d) {
/* 194 */     return (a << 16 | b & 0xFFFF) << 32L | (c << 16 | d & 0xFFFF) & 0xFFFFFFFFL;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static short[] longToShort(long l) {
/* 201 */     return new short[] { (short)(int)(l >> 48L), (short)(int)(l >> 32L), (short)(int)(l >> 16L), (short)(int)l };
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\io\github\quantizr\dungeonroom\\utils\Utils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */