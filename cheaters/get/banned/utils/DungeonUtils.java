/*     */ package cheaters.get.banned.utils;
/*     */ 
/*     */ import cheaters.get.banned.Shady;
/*     */ import cheaters.get.banned.config.Config;
/*     */ import cheaters.get.banned.events.TickEndEvent;
/*     */ import cheaters.get.banned.features.dungeonmap.DungeonMap;
/*     */ import cheaters.get.banned.remote.MayorAPI;
/*     */ import java.util.HashSet;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import net.minecraft.entity.monster.EntityZombie;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraftforge.client.event.ClientChatReceivedEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingDeathEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ 
/*     */ 
/*     */ public class DungeonUtils
/*     */ {
/*  20 */   private static final Pattern namePattern = Pattern.compile("([A-Za-z0-9_]{0,16}) \\((Mage|Tank|Berserker|Healer|Archer) ([IVXL0]{1,7})\\)");
/*  21 */   private static final Pattern secretsPattern = Pattern.compile(" Secrets Found: (\\d*)");
/*  22 */   private static final Pattern cryptsPattern = Pattern.compile(" Crypts: (\\d*)");
/*  23 */   private static final Pattern deathsPattern = Pattern.compile(" Deaths: \\((\\d*)\\)");
/*     */   
/*  25 */   private static final String[] mimicDeathMessages = new String[] { "Mimic Dead!", "$SKYTILS-DUNGEON-SCORE-MIMIC$", "Child Destroyed!", "Mimic Obliterated!", "Mimic Exorcised!", "Mimic Destroyed!", "Mimic Annhilated!" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  35 */   public static DungeonRun dungeonRun = null;
/*     */   
/*     */   public enum Floor {
/*  38 */     ENTERANCE("(E)"),
/*  39 */     FLOOR_1("(F1)"),
/*  40 */     FLOOR_2("(F2)"),
/*  41 */     FLOOR_3("(F3)"),
/*  42 */     FLOOR_4("(F4)"),
/*  43 */     FLOOR_5("(F5)"),
/*  44 */     FLOOR_6("(F6)"),
/*  45 */     FLOOR_7("(F7)"),
/*  46 */     MASTER_1("(M1)"),
/*  47 */     MASTER_2("(M2)"),
/*  48 */     MASTER_3("(M3)"),
/*  49 */     MASTER_4("(M4)"),
/*  50 */     MASTER_5("(M5)"),
/*  51 */     MASTER_6("(M6)"),
/*  52 */     MASTER_7("(M7)");
/*     */     
/*     */     public String name;
/*     */     
/*     */     Floor(String name) {
/*  57 */       this.name = name;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class DungeonRun {
/*  62 */     public int secretsFound = 0;
/*  63 */     public int cryptsFound = 0;
/*  64 */     public DungeonUtils.Floor floor = null;
/*     */     public boolean inBoss = false;
/*     */     public boolean mimicFound = false;
/*     */     public int deaths;
/*  68 */     public HashSet<String> team = new HashSet<>();
/*  69 */     public long startTime = System.currentTimeMillis();
/*     */     
/*     */     public long getTimeMs() {
/*  72 */       return System.currentTimeMillis() - this.startTime;
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onChat(ClientChatReceivedEvent event) {
/*  78 */     if (Utils.inDungeon && !dungeonRun.mimicFound && onFloorWithMimic()) {
/*  79 */       for (String mimicMessage : mimicDeathMessages) {
/*  80 */         if (event.message.func_150254_d().contains(mimicMessage)) {
/*  81 */           dungeonRun.mimicFound = true;
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*  87 */   private int counter = 0;
/*     */   @SubscribeEvent
/*     */   public void onTick(TickEndEvent event) {
/*  90 */     if (this.counter % 20 == 0) {
/*  91 */       if (Utils.inDungeon) {
/*  92 */         if (dungeonRun == null) dungeonRun = new DungeonRun(); 
/*  93 */         if (dungeonRun.floor == null) {
/*  94 */           String cataLine = ScoreboardUtils.getLineThatContains("The Catacombs");
/*  95 */           if (cataLine != null) {
/*  96 */             for (Floor floor : Floor.values()) {
/*  97 */               if (cataLine.contains(floor.name)) {
/*  98 */                 dungeonRun.floor = floor;
/*     */               }
/*     */             } 
/*     */           }
/*     */         } 
/*     */         
/* 104 */         for (String name : TabUtils.getTabList()) {
/* 105 */           if (name.contains("Crypts: ")) {
/* 106 */             name = Utils.removeFormatting(name);
/* 107 */             Matcher cryptsMatcher = cryptsPattern.matcher(name);
/* 108 */             if (cryptsMatcher.matches())
/* 109 */               dungeonRun.cryptsFound = Integer.parseInt(cryptsMatcher.group(1));  continue;
/*     */           } 
/* 111 */           if (name.contains("Secrets Found: ")) {
/* 112 */             name = Utils.removeFormatting(name);
/* 113 */             Matcher secretsMatcher = secretsPattern.matcher(name);
/* 114 */             if (secretsMatcher.matches())
/* 115 */               dungeonRun.secretsFound = Integer.parseInt(secretsMatcher.group(1));  continue;
/*     */           } 
/* 117 */           if (name.contains("Deaths: ")) {
/* 118 */             name = Utils.removeFormatting(name);
/* 119 */             Matcher deathsMatcher = deathsPattern.matcher(name);
/* 120 */             if (deathsMatcher.matches())
/* 121 */               dungeonRun.deaths = Integer.parseInt(deathsMatcher.group(1));  continue;
/*     */           } 
/* 123 */           if (dungeonRun.team.size() < 5 && (name.contains("Mage") || name.contains("Berserker") || name.contains("Archer") || name.contains("Tank") || name.contains("Healer"))) {
/* 124 */             name = Utils.removeFormatting(name);
/* 125 */             Matcher nameMatcher = namePattern.matcher(name);
/* 126 */             if (nameMatcher.matches()) {
/* 127 */               dungeonRun.team.add(nameMatcher.group(1).trim());
/*     */             }
/*     */           } 
/*     */         } 
/*     */         
/* 132 */         if (Shady.mc.field_71441_e != null && dungeonRun != null && ((
/* 133 */           ScoreboardUtils.scoreboardContains("30,30") && (dungeonRun.floor == Floor.FLOOR_1 || dungeonRun.floor == Floor.MASTER_1)) || (
/* 134 */           ScoreboardUtils.scoreboardContains("30,125") && (dungeonRun.floor == Floor.FLOOR_2 || dungeonRun.floor == Floor.MASTER_2)) || (
/* 135 */           ScoreboardUtils.scoreboardContains("30,225") && (dungeonRun.floor == Floor.FLOOR_3 || dungeonRun.floor == Floor.MASTER_3)) || (
/* 136 */           ScoreboardUtils.scoreboardContains("- Healthy") && (dungeonRun.floor == Floor.FLOOR_3 || dungeonRun.floor == Floor.MASTER_3)) || (
/* 137 */           ScoreboardUtils.scoreboardContains("30,344") && (dungeonRun.floor == Floor.FLOOR_4 || dungeonRun.floor == Floor.MASTER_4)) || (
/* 138 */           ScoreboardUtils.scoreboardContains("livid") && (dungeonRun.floor == Floor.FLOOR_5 || dungeonRun.floor == Floor.MASTER_5)) || (
/* 139 */           ScoreboardUtils.scoreboardContains("sadan") && (dungeonRun.floor == Floor.FLOOR_6 || dungeonRun.floor == Floor.MASTER_6)) || (
/* 140 */           ScoreboardUtils.scoreboardContains("necron") && (dungeonRun.floor == Floor.FLOOR_7 || dungeonRun.floor == Floor.MASTER_7)))) {
/* 141 */           dungeonRun.inBoss = true;
/*     */         }
/*     */       } else {
/*     */         
/* 145 */         dungeonRun = null;
/*     */       } 
/*     */       
/* 148 */       this.counter = 0;
/*     */     } 
/* 150 */     this.counter++;
/*     */   }
/*     */   
/*     */   public static void debug() {
/* 154 */     if (Utils.inDungeon && dungeonRun != null) {
/* 155 */       Utils.sendModMessage("Floor: " + dungeonRun.floor.name());
/* 156 */       Utils.sendModMessage("In Boss: " + dungeonRun.inBoss);
/* 157 */       Utils.sendModMessage("Secrets Found: " + dungeonRun.secretsFound);
/* 158 */       Utils.sendModMessage("Crypts Found: " + dungeonRun.cryptsFound);
/*     */     } else {
/* 160 */       Utils.sendMessage("You must be in a dungeon to debug a dungeon!");
/*     */     } 
/*     */   }
/*     */   
/*     */   public static int calculateScore() {
/* 165 */     if (dungeonRun == null || DungeonMap.activeDungeonLayout == null) return 0; 
/* 166 */     return calculateSkillScore() + calculateExploreScore() + calculateSpeedScore() + calculateBonusScore();
/*     */   }
/*     */ 
/*     */   
/*     */   private static int calculateSkillScore() {
/* 171 */     return 100 - dungeonRun.deaths * 2 + ((Config.assumeSpiritPet && dungeonRun.deaths > 0) ? 1 : 0);
/*     */   }
/*     */ 
/*     */   
/*     */   private static int calculateExploreScore() {
/* 176 */     return 60 + 40 * dungeonRun.secretsFound / DungeonMap.activeDungeonLayout.totalSecrets;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int calculateSpeedScore() {
/* 181 */     return 100;
/*     */   }
/*     */   
/*     */   private static int calculateBonusScore() {
/* 185 */     return (MayorAPI.isPaulBonus() ? 10 : 0) + (dungeonRun.mimicFound ? 5 : 0) + MathHelper.func_76125_a(dungeonRun.cryptsFound, 0, 5);
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onEntityDeath(LivingDeathEvent event) {
/* 190 */     if (onFloorWithMimic() && !dungeonRun.mimicFound && 
/* 191 */       event.entity.getClass() == EntityZombie.class) {
/* 192 */       EntityZombie entity = (EntityZombie)event.entity;
/* 193 */       if (entity.func_70631_g_() && 
/* 194 */         entity.func_82169_q(0) == null && entity.func_82169_q(1) == null && entity.func_82169_q(2) == null && entity.func_82169_q(3) == null) {
/* 195 */         dungeonRun.mimicFound = true;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean inFloor(Floor floor) {
/* 203 */     return (dungeonRun.floor != null && dungeonRun.floor.equals(floor));
/*     */   }
/*     */   
/*     */   public static boolean onFloorWithMimic() {
/* 207 */     return (dungeonRun != null && dungeonRun.floor != null && dungeonRun.floor != Floor.ENTERANCE && dungeonRun.floor != Floor.FLOOR_1 && dungeonRun.floor != Floor.FLOOR_2 && dungeonRun.floor != Floor.FLOOR_3 && dungeonRun.floor != Floor.MASTER_1 && dungeonRun.floor != Floor.MASTER_2 && dungeonRun.floor != Floor.MASTER_3);
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\cheaters\get\banne\\utils\DungeonUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */