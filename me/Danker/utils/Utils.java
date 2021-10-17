/*     */ package me.Danker.utils;
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.regex.Matcher;
/*     */ import me.Danker.DankersSkyblockMod;
/*     */ import me.Danker.handlers.ScoreboardHandler;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.client.network.NetworkPlayerInfo;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.item.EntityItemFrame;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.scoreboard.ScoreObjective;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.StringUtils;
/*     */ import net.minecraft.util.Vec3;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class Utils {
/*  38 */   public static int[] skillXPPerLevel = new int[] { 0, 50, 125, 200, 300, 500, 750, 1000, 1500, 2000, 3500, 5000, 7500, 10000, 15000, 20000, 30000, 50000, 75000, 100000, 200000, 300000, 400000, 500000, 600000, 700000, 800000, 900000, 1000000, 1100000, 1200000, 1300000, 1400000, 1500000, 1600000, 1700000, 1800000, 1900000, 2000000, 2100000, 2200000, 2300000, 2400000, 2500000, 2600000, 2750000, 2900000, 3100000, 3400000, 3700000, 4000000, 4300000, 4600000, 4900000, 5200000, 5500000, 5800000, 6100000, 6400000, 6700000, 7000000 };
/*     */   
/*     */   public static boolean inSkyblock = false;
/*     */   
/*     */   public static boolean inDungeons = false;
/*  43 */   static int[] dungeonsXPPerLevel = new int[] { 0, 50, 75, 110, 160, 230, 330, 470, 670, 950, 1340, 1890, 2665, 3760, 5260, 7380, 10300, 14400, 20000, 27600, 38000, 52500, 71500, 97000, 132000, 180000, 243000, 328000, 445000, 600000, 800000, 1065000, 1410000, 1900000, 2500000, 3300000, 4300000, 5600000, 7200000, 9200000, 12000000, 15000000, 19000000, 24000000, 30000000, 38000000, 48000000, 60000000, 75000000, 93000000, 116250000 };
/*     */ 
/*     */ 
/*     */   
/*  47 */   static int[] expertiseKills = new int[] { 50, 100, 250, 500, 1000, 2500, 5500, 10000, 15000 };
/*     */   
/*     */   public static int getItems(String item) {
/*  50 */     Minecraft mc = Minecraft.func_71410_x();
/*  51 */     EntityPlayerSP entityPlayerSP = mc.field_71439_g;
/*     */     
/*  53 */     double x = ((EntityPlayer)entityPlayerSP).field_70165_t;
/*  54 */     double y = ((EntityPlayer)entityPlayerSP).field_70163_u;
/*  55 */     double z = ((EntityPlayer)entityPlayerSP).field_70161_v;
/*  56 */     AxisAlignedBB scan = new AxisAlignedBB(x - 6.0D, y - 6.0D, z - 6.0D, x + 6.0D, y + 6.0D, z + 6.0D);
/*  57 */     List<EntityItem> items = mc.field_71441_e.func_72872_a(EntityItem.class, scan);
/*     */     
/*  59 */     for (EntityItem i : items) {
/*  60 */       String itemName = StringUtils.func_76338_a(i.func_92059_d().func_82833_r());
/*  61 */       if (itemName.equals(item)) return (i.func_92059_d()).field_77994_a;
/*     */     
/*     */     } 
/*  64 */     return 0;
/*     */   }
/*     */   
/*     */   public static String returnGoldenEnchants(String line) {
/*  68 */     Matcher matcher = DankersSkyblockMod.t6EnchantPattern.matcher(line);
/*  69 */     StringBuffer out = new StringBuffer();
/*     */     
/*  71 */     while (matcher.find()) {
/*  72 */       matcher.appendReplacement(out, (String)DankersSkyblockMod.t6Enchants.get(matcher.group(1)));
/*     */     }
/*  74 */     matcher.appendTail(out);
/*     */     
/*  76 */     return out.toString();
/*     */   }
/*     */   
/*     */   public static List<String> getMatchingPlayers(String arg) {
/*  80 */     List<String> matchingPlayers = new ArrayList<>();
/*  81 */     Collection<NetworkPlayerInfo> players = Minecraft.func_71410_x().func_147114_u().func_175106_d();
/*     */     
/*  83 */     for (NetworkPlayerInfo player : players) {
/*  84 */       String playerName = player.func_178845_a().getName();
/*  85 */       if (!playerName.startsWith("!") && 
/*  86 */         playerName.toLowerCase().startsWith(arg.toLowerCase())) {
/*  87 */         matchingPlayers.add(playerName);
/*     */       }
/*     */     } 
/*     */     
/*  91 */     return matchingPlayers;
/*     */   }
/*     */   
/*     */   public static void createTitle(String text, int seconds) {
/*  95 */     (Minecraft.func_71410_x()).field_71439_g.func_85030_a("random.orb", 1.0F, 0.5F);
/*  96 */     DankersSkyblockMod.titleTimer = seconds * 20;
/*  97 */     DankersSkyblockMod.showTitle = true;
/*  98 */     DankersSkyblockMod.titleText = text;
/*     */   }
/*     */   
/*     */   public static void drawTitle(String text) {
/* 102 */     Minecraft mc = Minecraft.func_71410_x();
/* 103 */     ScaledResolution scaledResolution = new ScaledResolution(mc);
/*     */     
/* 105 */     int height = scaledResolution.func_78328_b();
/* 106 */     int width = scaledResolution.func_78326_a();
/* 107 */     int drawHeight = 0;
/* 108 */     String[] splitText = text.split("\n");
/* 109 */     for (String title : splitText) {
/* 110 */       int textLength = mc.field_71466_p.func_78256_a(title);
/*     */       
/* 112 */       double scale = 4.0D;
/* 113 */       if (textLength * scale > (width * 0.9F)) {
/* 114 */         scale = (width * 0.9F / textLength);
/*     */       }
/*     */       
/* 117 */       int titleX = (int)((width / 2) - textLength * scale / 2.0D);
/* 118 */       int titleY = (int)(height * 0.45D / scale) + (int)(drawHeight * scale);
/* 119 */       new TextRenderer(mc, title, titleX, titleY, scale);
/* 120 */       drawHeight += mc.field_71466_p.field_78288_b;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean isOnHypixel() {
/* 125 */     Minecraft mc = Minecraft.func_71410_x();
/* 126 */     if (mc != null && mc.field_71441_e != null && !mc.func_71356_B()) {
/* 127 */       return (mc.func_147104_D()).field_78845_b.toLowerCase().contains("hypixel");
/*     */     }
/* 129 */     return false;
/*     */   }
/*     */   
/*     */   public static void checkForSkyblock() {
/* 133 */     Minecraft mc = Minecraft.func_71410_x();
/* 134 */     if (mc != null && mc.field_71441_e != null && !mc.func_71356_B()) {
/* 135 */       ScoreObjective scoreboardObj = mc.field_71441_e.func_96441_U().func_96539_a(1);
/* 136 */       if (scoreboardObj != null) {
/* 137 */         String scObjName = ScoreboardHandler.cleanSB(scoreboardObj.func_96678_d());
/* 138 */         if (scObjName.contains("SKYBLOCK")) {
/* 139 */           inSkyblock = true;
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/* 144 */     inSkyblock = false;
/*     */   }
/*     */   
/*     */   public static void checkForDungeons() {
/* 148 */     if (inSkyblock) {
/* 149 */       List<String> scoreboard = ScoreboardHandler.getSidebarLines();
/* 150 */       for (String s : scoreboard) {
/* 151 */         String sCleaned = ScoreboardHandler.cleanSB(s);
/* 152 */         if (sCleaned.contains("The Catacombs")) {
/* 153 */           inDungeons = true;
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/* 158 */     inDungeons = false;
/*     */   }
/*     */   
/*     */   public static void sleep(int time) {
/*     */     try {
/* 163 */       Thread.sleep(time);
/* 164 */     } catch (InterruptedException e) {
/* 165 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String capitalizeString(String string) {
/* 170 */     String[] words = string.split("_");
/*     */     
/* 172 */     for (int i = 0; i < words.length; i++) {
/* 173 */       words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();
/*     */     }
/*     */     
/* 176 */     return String.join(" ", (CharSequence[])words);
/*     */   }
/*     */   
/*     */   public static double getPercentage(int num1, int num2) {
/* 180 */     if (num2 == 0) return 0.0D; 
/* 181 */     double result = num1 * 100.0D / num2;
/* 182 */     result = Math.round(result * 100.0D) / 100.0D;
/* 183 */     return result;
/*     */   }
/*     */   
/*     */   public static void drawOnSlot(int size, int xSlotPos, int ySlotPos, int colour) {
/* 187 */     ScaledResolution sr = new ScaledResolution(Minecraft.func_71410_x());
/* 188 */     int guiLeft = (sr.func_78326_a() - 176) / 2;
/* 189 */     int guiTop = (sr.func_78328_b() - 222) / 2;
/* 190 */     int x = guiLeft + xSlotPos;
/* 191 */     int y = guiTop + ySlotPos;
/*     */     
/* 193 */     if (size != 90) y += (6 - (size - 36) / 9) * 9;
/*     */     
/* 195 */     GL11.glTranslated(0.0D, 0.0D, 1.0D);
/* 196 */     Gui.func_73734_a(x, y, x + 16, y + 16, colour);
/* 197 */     GL11.glTranslated(0.0D, 0.0D, -1.0D);
/*     */   }
/*     */   public static String getTimeBetween(double timeOne, double timeTwo) {
/*     */     String timeFormatted;
/* 201 */     double secondsBetween = Math.floor(timeTwo - timeOne);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 209 */     if (secondsBetween > 86400.0D) {
/*     */       
/* 211 */       int days = (int)(secondsBetween / 86400.0D);
/* 212 */       int hours = (int)(secondsBetween % 86400.0D / 3600.0D);
/* 213 */       timeFormatted = days + "d" + hours + "h";
/* 214 */     } else if (secondsBetween > 3600.0D) {
/*     */       
/* 216 */       int hours = (int)(secondsBetween / 3600.0D);
/* 217 */       int minutes = (int)(secondsBetween % 3600.0D / 60.0D);
/* 218 */       timeFormatted = hours + "h" + minutes + "m";
/*     */     } else {
/*     */       
/* 221 */       int minutes = (int)(secondsBetween / 60.0D);
/* 222 */       int seconds = (int)(secondsBetween % 60.0D);
/* 223 */       timeFormatted = minutes + "m" + seconds + "s";
/*     */     } 
/*     */     
/* 226 */     return timeFormatted;
/*     */   }
/*     */   
/*     */   public static String getMoneySpent(double coins) {
/* 230 */     double coinsSpentMillions = coins / 1000000.0D;
/* 231 */     coinsSpentMillions = Math.floor(coinsSpentMillions * 100.0D) / 100.0D;
/* 232 */     return coinsSpentMillions + "M";
/*     */   }
/*     */   
/*     */   public static double xpToSkillLevel(double xp, int limit) {
/* 236 */     for (int i = 0, xpAdded = 0; i < limit + 1; i++) {
/* 237 */       xpAdded += skillXPPerLevel[i];
/* 238 */       if (xp < xpAdded) {
/* 239 */         return (i - 1) + (xp - (xpAdded - skillXPPerLevel[i])) / skillXPPerLevel[i];
/*     */       }
/*     */     } 
/* 242 */     return limit;
/*     */   }
/*     */   
/*     */   public static double xpToDungeonsLevel(double xp) {
/* 246 */     for (int i = 0, xpAdded = 0; i < dungeonsXPPerLevel.length; i++) {
/* 247 */       xpAdded += dungeonsXPPerLevel[i];
/* 248 */       if (xp < xpAdded) {
/* 249 */         double level = (i - 1) + (xp - (xpAdded - dungeonsXPPerLevel[i])) / dungeonsXPPerLevel[i];
/* 250 */         return Math.round(level * 100.0D) / 100.0D;
/*     */       } 
/*     */     } 
/* 253 */     return 50.0D;
/*     */   }
/*     */   
/*     */   public static int expertiseKillsLeft(int kills) {
/* 257 */     for (int i = 0; i < expertiseKills.length; i++) {
/* 258 */       if (kills < expertiseKills[i]) {
/* 259 */         return expertiseKills[i] - kills;
/*     */       }
/*     */     } 
/* 262 */     return -1;
/*     */   }
/*     */   
/*     */   public static int getPastXpEarned(int currentLevelXp, int limit) {
/* 266 */     if (currentLevelXp == 0) {
/* 267 */       int j = 0;
/* 268 */       for (int k = 1; k <= limit; k++) {
/* 269 */         j += skillXPPerLevel[k];
/*     */       }
/* 271 */       return j;
/*     */     } 
/* 273 */     for (int i = 1, xpAdded = 0; i <= limit; i++) {
/* 274 */       xpAdded += skillXPPerLevel[i - 1];
/* 275 */       if (currentLevelXp == skillXPPerLevel[i]) return xpAdded; 
/*     */     } 
/* 277 */     return 0;
/*     */   }
/*     */   
/*     */   public static String getColouredBoolean(boolean bool) {
/* 281 */     return bool ? (EnumChatFormatting.GREEN + "On") : (EnumChatFormatting.RED + "Off");
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<String> getItemLore(ItemStack itemStack) {
/* 286 */     int NBT_INTEGER = 3;
/* 287 */     int NBT_STRING = 8;
/* 288 */     int NBT_LIST = 9;
/* 289 */     int NBT_COMPOUND = 10;
/*     */     
/* 291 */     if (itemStack.func_77942_o() && itemStack.func_77978_p().func_150297_b("display", 10)) {
/* 292 */       NBTTagCompound display = itemStack.func_77978_p().func_74775_l("display");
/*     */       
/* 294 */       if (display.func_150297_b("Lore", 9)) {
/* 295 */         NBTTagList lore = display.func_150295_c("Lore", 8);
/*     */         
/* 297 */         List<String> loreAsList = new ArrayList<>();
/* 298 */         for (int lineNumber = 0; lineNumber < lore.func_74745_c(); lineNumber++) {
/* 299 */           loreAsList.add(lore.func_150307_f(lineNumber));
/*     */         }
/*     */         
/* 302 */         return Collections.unmodifiableList(loreAsList);
/*     */       } 
/*     */     } 
/*     */     
/* 306 */     return Collections.emptyList();
/*     */   }
/*     */   
/*     */   public static boolean hasRightClickAbility(ItemStack itemStack) {
/* 310 */     return getItemLore(itemStack).stream().anyMatch(line -> {
/*     */           String stripped = StringUtils.func_76338_a(line);
/* 312 */           return (stripped.startsWith("Item Ability:") && stripped.endsWith("RIGHT CLICK"));
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public static void draw3DLine(Vec3 pos1, Vec3 pos2, int colourInt, float partialTicks) {
/* 318 */     Entity render = Minecraft.func_71410_x().func_175606_aa();
/* 319 */     WorldRenderer worldRenderer = Tessellator.func_178181_a().func_178180_c();
/* 320 */     Color colour = new Color(colourInt);
/*     */     
/* 322 */     double realX = render.field_70142_S + (render.field_70165_t - render.field_70142_S) * partialTicks;
/* 323 */     double realY = render.field_70137_T + (render.field_70163_u - render.field_70137_T) * partialTicks;
/* 324 */     double realZ = render.field_70136_U + (render.field_70161_v - render.field_70136_U) * partialTicks;
/*     */     
/* 326 */     GlStateManager.func_179094_E();
/* 327 */     GlStateManager.func_179137_b(-realX, -realY, -realZ);
/* 328 */     GlStateManager.func_179090_x();
/* 329 */     GlStateManager.func_179147_l();
/* 330 */     GlStateManager.func_179118_c();
/* 331 */     GlStateManager.func_179120_a(770, 771, 1, 0);
/* 332 */     GL11.glLineWidth(2.0F);
/* 333 */     GlStateManager.func_179131_c(colour.getRed() / 255.0F, colour.getGreen() / 255.0F, colour.getBlue() / 255.0F, colour.getAlpha() / 255.0F);
/* 334 */     worldRenderer.func_181668_a(3, DefaultVertexFormats.field_181705_e);
/*     */     
/* 336 */     worldRenderer.func_181662_b(pos1.field_72450_a, pos1.field_72448_b, pos1.field_72449_c).func_181675_d();
/* 337 */     worldRenderer.func_181662_b(pos2.field_72450_a, pos2.field_72448_b, pos2.field_72449_c).func_181675_d();
/* 338 */     Tessellator.func_178181_a().func_78381_a();
/*     */     
/* 340 */     GlStateManager.func_179137_b(realX, realY, realZ);
/* 341 */     GlStateManager.func_179084_k();
/* 342 */     GlStateManager.func_179141_d();
/* 343 */     GlStateManager.func_179098_w();
/* 344 */     GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/* 345 */     GlStateManager.func_179121_F();
/*     */   }
/*     */   
/*     */   public static void draw3DString(BlockPos pos, String text, int colour, float partialTicks) {
/* 349 */     Minecraft mc = Minecraft.func_71410_x();
/* 350 */     EntityPlayerSP entityPlayerSP = mc.field_71439_g;
/* 351 */     double x = pos.func_177958_n() - ((EntityPlayer)entityPlayerSP).field_70142_S + (pos.func_177958_n() - ((EntityPlayer)entityPlayerSP).field_70165_t - pos.func_177958_n() - ((EntityPlayer)entityPlayerSP).field_70142_S) * partialTicks;
/* 352 */     double y = pos.func_177956_o() - ((EntityPlayer)entityPlayerSP).field_70137_T + (pos.func_177956_o() - ((EntityPlayer)entityPlayerSP).field_70163_u - pos.func_177956_o() - ((EntityPlayer)entityPlayerSP).field_70137_T) * partialTicks;
/* 353 */     double z = pos.func_177952_p() - ((EntityPlayer)entityPlayerSP).field_70136_U + (pos.func_177952_p() - ((EntityPlayer)entityPlayerSP).field_70161_v - pos.func_177952_p() - ((EntityPlayer)entityPlayerSP).field_70136_U) * partialTicks;
/* 354 */     RenderManager renderManager = mc.func_175598_ae();
/*     */     
/* 356 */     float f = 1.6F;
/* 357 */     float f1 = 0.016666668F * f;
/* 358 */     int width = mc.field_71466_p.func_78256_a(text) / 2;
/* 359 */     GlStateManager.func_179094_E();
/* 360 */     GlStateManager.func_179137_b(x, y, z);
/* 361 */     GL11.glNormal3f(0.0F, 1.0F, 0.0F);
/* 362 */     GlStateManager.func_179114_b(-renderManager.field_78735_i, 0.0F, 1.0F, 0.0F);
/* 363 */     GlStateManager.func_179114_b(renderManager.field_78732_j, 1.0F, 0.0F, 0.0F);
/* 364 */     GlStateManager.func_179152_a(-f1, -f1, -f1);
/* 365 */     GlStateManager.func_179147_l();
/* 366 */     GlStateManager.func_179120_a(770, 771, 1, 0);
/* 367 */     mc.field_71466_p.func_78276_b(text, -width, 0, colour);
/* 368 */     GlStateManager.func_179084_k();
/* 369 */     GlStateManager.func_179121_F();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void draw3DBox(AxisAlignedBB aabb, int colourInt, float partialTicks) {
/* 374 */     Entity render = Minecraft.func_71410_x().func_175606_aa();
/* 375 */     WorldRenderer worldRenderer = Tessellator.func_178181_a().func_178180_c();
/* 376 */     Color colour = new Color(colourInt);
/*     */     
/* 378 */     double realX = render.field_70142_S + (render.field_70165_t - render.field_70142_S) * partialTicks;
/* 379 */     double realY = render.field_70137_T + (render.field_70163_u - render.field_70137_T) * partialTicks;
/* 380 */     double realZ = render.field_70136_U + (render.field_70161_v - render.field_70136_U) * partialTicks;
/*     */     
/* 382 */     GlStateManager.func_179094_E();
/* 383 */     GlStateManager.func_179137_b(-realX, -realY, -realZ);
/* 384 */     GlStateManager.func_179090_x();
/* 385 */     GlStateManager.func_179147_l();
/* 386 */     GlStateManager.func_179118_c();
/* 387 */     GlStateManager.func_179120_a(770, 771, 1, 0);
/* 388 */     GL11.glLineWidth(2.0F);
/* 389 */     GlStateManager.func_179131_c(colour.getRed() / 255.0F, colour.getGreen() / 255.0F, colour.getBlue() / 255.0F, colour.getAlpha() / 255.0F);
/* 390 */     worldRenderer.func_181668_a(3, DefaultVertexFormats.field_181705_e);
/*     */     
/* 392 */     worldRenderer.func_181662_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72339_c).func_181675_d();
/* 393 */     worldRenderer.func_181662_b(aabb.field_72336_d, aabb.field_72338_b, aabb.field_72339_c).func_181675_d();
/* 394 */     worldRenderer.func_181662_b(aabb.field_72336_d, aabb.field_72338_b, aabb.field_72334_f).func_181675_d();
/* 395 */     worldRenderer.func_181662_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72334_f).func_181675_d();
/* 396 */     worldRenderer.func_181662_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72339_c).func_181675_d();
/* 397 */     Tessellator.func_178181_a().func_78381_a();
/* 398 */     worldRenderer.func_181668_a(3, DefaultVertexFormats.field_181705_e);
/* 399 */     worldRenderer.func_181662_b(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72339_c).func_181675_d();
/* 400 */     worldRenderer.func_181662_b(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72339_c).func_181675_d();
/* 401 */     worldRenderer.func_181662_b(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72334_f).func_181675_d();
/* 402 */     worldRenderer.func_181662_b(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72334_f).func_181675_d();
/* 403 */     worldRenderer.func_181662_b(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72339_c).func_181675_d();
/* 404 */     Tessellator.func_178181_a().func_78381_a();
/* 405 */     worldRenderer.func_181668_a(3, DefaultVertexFormats.field_181705_e);
/* 406 */     worldRenderer.func_181662_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72339_c).func_181675_d();
/* 407 */     worldRenderer.func_181662_b(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72339_c).func_181675_d();
/* 408 */     worldRenderer.func_181662_b(aabb.field_72336_d, aabb.field_72338_b, aabb.field_72339_c).func_181675_d();
/* 409 */     worldRenderer.func_181662_b(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72339_c).func_181675_d();
/* 410 */     worldRenderer.func_181662_b(aabb.field_72336_d, aabb.field_72338_b, aabb.field_72334_f).func_181675_d();
/* 411 */     worldRenderer.func_181662_b(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72334_f).func_181675_d();
/* 412 */     worldRenderer.func_181662_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72334_f).func_181675_d();
/* 413 */     worldRenderer.func_181662_b(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72334_f).func_181675_d();
/* 414 */     Tessellator.func_178181_a().func_78381_a();
/*     */     
/* 416 */     GlStateManager.func_179137_b(realX, realY, realZ);
/* 417 */     GlStateManager.func_179084_k();
/* 418 */     GlStateManager.func_179141_d();
/* 419 */     GlStateManager.func_179098_w();
/* 420 */     GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/* 421 */     GlStateManager.func_179121_F();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderItem(ItemStack item, float x, float y, float z) {
/* 426 */     GlStateManager.func_179091_B();
/* 427 */     RenderHelper.func_74520_c();
/* 428 */     GlStateManager.func_179126_j();
/*     */     
/* 430 */     GlStateManager.func_179094_E();
/* 431 */     GlStateManager.func_179109_b(x, y, z);
/* 432 */     Minecraft.func_71410_x().func_175599_af().func_175042_a(item, 0, 0);
/* 433 */     GlStateManager.func_179121_F();
/*     */     
/* 435 */     GlStateManager.func_179097_i();
/* 436 */     RenderHelper.func_74518_a();
/* 437 */     GlStateManager.func_179101_C();
/*     */   }
/*     */   
/*     */   public static BlockPos getFirstBlockPosAfterVectors(Minecraft mc, Vec3 pos1, Vec3 pos2, int strength, int distance) {
/* 441 */     double x = pos2.field_72450_a - pos1.field_72450_a;
/* 442 */     double y = pos2.field_72448_b - pos1.field_72448_b;
/* 443 */     double z = pos2.field_72449_c - pos1.field_72449_c;
/*     */     
/* 445 */     for (int i = strength; i < distance * strength; i++) {
/* 446 */       double newX = pos1.field_72450_a + x / strength * i;
/* 447 */       double newY = pos1.field_72448_b + y / strength * i;
/* 448 */       double newZ = pos1.field_72449_c + z / strength * i;
/*     */       
/* 450 */       BlockPos newBlock = new BlockPos(newX, newY, newZ);
/* 451 */       if (mc.field_71441_e.func_180495_p(newBlock).func_177230_c() != Blocks.field_150350_a) {
/* 452 */         return newBlock;
/*     */       }
/*     */     } 
/*     */     
/* 456 */     return null;
/*     */   }
/*     */   
/*     */   public static BlockPos getNearbyBlock(Minecraft mc, BlockPos pos, Block... blockTypes) {
/* 460 */     if (pos == null) return null; 
/* 461 */     BlockPos pos1 = new BlockPos(pos.func_177958_n() - 2, pos.func_177956_o() - 3, pos.func_177952_p() - 2);
/* 462 */     BlockPos pos2 = new BlockPos(pos.func_177958_n() + 2, pos.func_177956_o() + 3, pos.func_177952_p() + 2);
/*     */     
/* 464 */     BlockPos closestBlock = null;
/* 465 */     double closestBlockDistance = 99.0D;
/* 466 */     Iterable<BlockPos> blocks = BlockPos.func_177980_a(pos1, pos2);
/*     */     
/* 468 */     for (BlockPos block : blocks) {
/* 469 */       for (Block blockType : blockTypes) {
/* 470 */         if (mc.field_71441_e.func_180495_p(block).func_177230_c() == blockType && block.func_177951_i((Vec3i)pos) < closestBlockDistance) {
/* 471 */           closestBlock = block;
/* 472 */           closestBlockDistance = block.func_177951_i((Vec3i)pos);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 477 */     return closestBlock;
/*     */   }
/*     */   
/*     */   public static BlockPos getBlockUnderItemFrame(EntityItemFrame itemFrame) {
/* 481 */     switch (itemFrame.field_174860_b) {
/*     */       case NORTH:
/* 483 */         return new BlockPos(itemFrame.field_70165_t, itemFrame.field_70163_u, itemFrame.field_70161_v + 1.0D);
/*     */       case EAST:
/* 485 */         return new BlockPos(itemFrame.field_70165_t - 1.0D, itemFrame.field_70163_u, itemFrame.field_70161_v - 0.5D);
/*     */       case SOUTH:
/* 487 */         return new BlockPos(itemFrame.field_70165_t, itemFrame.field_70163_u, itemFrame.field_70161_v - 1.0D);
/*     */       case WEST:
/* 489 */         return new BlockPos(itemFrame.field_70165_t + 1.0D, itemFrame.field_70163_u, itemFrame.field_70161_v - 0.5D);
/*     */     } 
/* 491 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\me\Danke\\utils\Utils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */