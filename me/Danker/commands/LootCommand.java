/*      */ package me.Danker.commands;
/*      */ 
/*      */ import java.text.NumberFormat;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import me.Danker.DankersSkyblockMod;
/*      */ import me.Danker.utils.Utils;
/*      */ import net.minecraft.command.CommandBase;
/*      */ import net.minecraft.command.CommandException;
/*      */ import net.minecraft.command.ICommandSender;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.util.BlockPos;
/*      */ import net.minecraft.util.ChatComponentText;
/*      */ import net.minecraft.util.EnumChatFormatting;
/*      */ import net.minecraft.util.IChatComponent;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class LootCommand
/*      */   extends CommandBase
/*      */ {
/*      */   public static int wolfSvens;
/*      */   public static int wolfTeeth;
/*      */   public static int wolfWheels;
/*      */   public static int wolfWheelsDrops;
/*      */   public static int wolfSpirits;
/*      */   public static int wolfBooks;
/*      */   public static int wolfEggs;
/*      */   public static int wolfCoutures;
/*      */   public static int wolfBaits;
/*      */   public static int wolfFluxes;
/*      */   public static double wolfTime;
/*      */   public static int wolfBosses;
/*      */   public static int spiderTarantulas;
/*      */   public static int spiderWebs;
/*      */   public static int spiderTAP;
/*      */   public static int spiderTAPDrops;
/*      */   public static int spiderBites;
/*      */   public static int spiderCatalysts;
/*      */   public static int spiderBooks;
/*      */   public static int spiderSwatters;
/*      */   public static int spiderTalismans;
/*      */   public static int spiderMosquitos;
/*      */   public static double spiderTime;
/*      */   public static int spiderBosses;
/*      */   public static int zombieRevs;
/*      */   public static int zombieRevFlesh;
/*      */   public static int zombieFoulFlesh;
/*      */   public static int zombieFoulFleshDrops;
/*      */   public static int zombiePestilences;
/*      */   public static int zombieUndeadCatas;
/*      */   public static int zombieBooks;
/*      */   public static int zombieBeheadeds;
/*      */   public static int zombieRevCatas;
/*      */   public static int zombieSnakes;
/*      */   public static int zombieScythes;
/*      */   public static double zombieTime;
/*      */   public static int zombieBosses;
/*      */   public static int seaCreatures;
/*      */   public static int goodCatches;
/*      */   public static int greatCatches;
/*      */   public static int squids;
/*      */   public static int seaWalkers;
/*      */   public static int nightSquids;
/*      */   public static int seaGuardians;
/*      */   public static int seaWitches;
/*      */   public static int seaArchers;
/*      */   public static int monsterOfTheDeeps;
/*      */   public static int catfishes;
/*      */   public static int carrotKings;
/*      */   public static int seaLeeches;
/*      */   public static int guardianDefenders;
/*      */   public static int deepSeaProtectors;
/*      */   public static int hydras;
/*      */   public static int seaEmperors;
/*      */   public static double empTime;
/*      */   public static int empSCs;
/*      */   public static int fishingMilestone;
/*      */   public static int frozenSteves;
/*      */   public static int frostyTheSnowmans;
/*      */   public static int grinches;
/*      */   public static int yetis;
/*      */   public static double yetiTime;
/*      */   public static int yetiSCs;
/*      */   public static int nurseSharks;
/*      */   public static int blueSharks;
/*      */   public static int tigerSharks;
/*      */   public static int greatWhiteSharks;
/*      */   public static int scarecrows;
/*      */   public static int nightmares;
/*      */   public static int werewolfs;
/*      */   public static int phantomFishers;
/*      */   public static int grimReapers;
/*      */   public static double mythCoins;
/*      */   public static int griffinFeathers;
/*      */   public static int crownOfGreeds;
/*      */   public static int washedUpSouvenirs;
/*      */   public static int minosHunters;
/*      */   public static int siameseLynxes;
/*      */   public static int minotaurs;
/*      */   public static int gaiaConstructs;
/*      */   public static int minosChampions;
/*      */   public static int minosInquisitors;
/*      */   public static int recombobulators;
/*      */   public static int fumingPotatoBooks;
/*      */   public static int bonzoStaffs;
/*      */   public static double f1CoinsSpent;
/*      */   public static double f1TimeSpent;
/*      */   public static int scarfStudies;
/*      */   public static int adaptiveSwords;
/*      */   public static double f2CoinsSpent;
/*      */   public static double f2TimeSpent;
/*      */   public static int adaptiveHelms;
/*      */   public static int adaptiveChests;
/*      */   public static int adaptiveLegs;
/*      */   public static int adaptiveBoots;
/*      */   public static double f3CoinsSpent;
/*      */   public static double f3TimeSpent;
/*      */   public static int spiritWings;
/*      */   public static int spiritBones;
/*      */   public static int spiritBoots;
/*      */   public static int spiritSwords;
/*      */   public static int spiritBows;
/*      */   public static int epicSpiritPets;
/*      */   public static int legSpiritPets;
/*      */   public static double f4CoinsSpent;
/*      */   public static double f4TimeSpent;
/*      */   public static int warpedStones;
/*      */   public static int shadowAssHelms;
/*      */   public static int shadowAssChests;
/*      */   public static int shadowAssLegs;
/*      */   public static int shadowAssBoots;
/*      */   public static int lastBreaths;
/*      */   public static int lividDaggers;
/*      */   public static int shadowFurys;
/*      */   public static double f5CoinsSpent;
/*      */   public static double f5TimeSpent;
/*      */   public static int ancientRoses;
/*      */   public static int precursorEyes;
/*      */   public static int giantsSwords;
/*      */   public static int necroLordHelms;
/*      */   public static int necroLordChests;
/*      */   public static int necroLordLegs;
/*      */   public static int necroLordBoots;
/*      */   public static int necroSwords;
/*      */   public static double f6CoinsSpent;
/*      */   public static double f6TimeSpent;
/*      */   public static int witherBloods;
/*      */   public static int witherCloaks;
/*      */   public static int implosions;
/*      */   public static int witherShields;
/*      */   public static int shadowWarps;
/*      */   public static int necronsHandles;
/*      */   public static int autoRecombs;
/*      */   public static int witherHelms;
/*      */   public static int witherChests;
/*      */   public static int witherLegs;
/*      */   public static int witherBoots;
/*      */   public static double f7CoinsSpent;
/*      */   public static double f7TimeSpent;
/*  179 */   public static int wolfSvensSession = 0;
/*  180 */   public static int wolfTeethSession = 0;
/*  181 */   public static int wolfWheelsSession = 0;
/*  182 */   public static int wolfWheelsDropsSession = 0;
/*  183 */   public static int wolfSpiritsSession = 0;
/*  184 */   public static int wolfBooksSession = 0;
/*  185 */   public static int wolfEggsSession = 0;
/*  186 */   public static int wolfCouturesSession = 0;
/*  187 */   public static int wolfBaitsSession = 0;
/*  188 */   public static int wolfFluxesSession = 0;
/*  189 */   public static double wolfTimeSession = -1.0D;
/*  190 */   public static int wolfBossesSession = -1;
/*      */   
/*  192 */   public static int spiderTarantulasSession = 0;
/*  193 */   public static int spiderWebsSession = 0;
/*  194 */   public static int spiderTAPSession = 0;
/*  195 */   public static int spiderTAPDropsSession = 0;
/*  196 */   public static int spiderBitesSession = 0;
/*  197 */   public static int spiderCatalystsSession = 0;
/*  198 */   public static int spiderBooksSession = 0;
/*  199 */   public static int spiderSwattersSession = 0;
/*  200 */   public static int spiderTalismansSession = 0;
/*  201 */   public static int spiderMosquitosSession = 0;
/*  202 */   public static double spiderTimeSession = -1.0D;
/*  203 */   public static int spiderBossesSession = -1;
/*      */   
/*  205 */   public static int zombieRevsSession = 0;
/*  206 */   public static int zombieRevFleshSession = 0;
/*  207 */   public static int zombieFoulFleshSession = 0;
/*  208 */   public static int zombieFoulFleshDropsSession = 0;
/*  209 */   public static int zombiePestilencesSession = 0;
/*  210 */   public static int zombieUndeadCatasSession = 0;
/*  211 */   public static int zombieBooksSession = 0;
/*  212 */   public static int zombieBeheadedsSession = 0;
/*  213 */   public static int zombieRevCatasSession = 0;
/*  214 */   public static int zombieSnakesSession = 0;
/*  215 */   public static int zombieScythesSession = 0;
/*  216 */   public static double zombieTimeSession = -1.0D;
/*  217 */   public static int zombieBossesSession = -1;
/*      */ 
/*      */   
/*  220 */   public static int seaCreaturesSession = 0;
/*  221 */   public static int goodCatchesSession = 0;
/*  222 */   public static int greatCatchesSession = 0;
/*  223 */   public static int squidsSession = 0;
/*  224 */   public static int seaWalkersSession = 0;
/*  225 */   public static int nightSquidsSession = 0;
/*  226 */   public static int seaGuardiansSession = 0;
/*  227 */   public static int seaWitchesSession = 0;
/*  228 */   public static int seaArchersSession = 0;
/*  229 */   public static int monsterOfTheDeepsSession = 0;
/*  230 */   public static int catfishesSession = 0;
/*  231 */   public static int carrotKingsSession = 0;
/*  232 */   public static int seaLeechesSession = 0;
/*  233 */   public static int guardianDefendersSession = 0;
/*  234 */   public static int deepSeaProtectorsSession = 0;
/*  235 */   public static int hydrasSession = 0;
/*  236 */   public static int seaEmperorsSession = 0;
/*  237 */   public static double empTimeSession = -1.0D;
/*  238 */   public static int empSCsSession = -1;
/*  239 */   public static int fishingMilestoneSession = 0;
/*      */   
/*  241 */   public static int frozenStevesSession = 0;
/*  242 */   public static int frostyTheSnowmansSession = 0;
/*  243 */   public static int grinchesSession = 0;
/*  244 */   public static int yetisSession = 0;
/*  245 */   public static double yetiTimeSession = -1.0D;
/*  246 */   public static int yetiSCsSession = -1;
/*      */   
/*  248 */   public static int nurseSharksSession = 0;
/*  249 */   public static int blueSharksSession = 0;
/*  250 */   public static int tigerSharksSession = 0;
/*  251 */   public static int greatWhiteSharksSession = 0;
/*      */   
/*  253 */   public static int scarecrowsSession = 0;
/*  254 */   public static int nightmaresSession = 0;
/*  255 */   public static int werewolfsSession = 0;
/*  256 */   public static int phantomFishersSession = 0;
/*  257 */   public static int grimReapersSession = 0;
/*      */ 
/*      */   
/*  260 */   public static double mythCoinsSession = 0.0D;
/*  261 */   public static int griffinFeathersSession = 0;
/*  262 */   public static int crownOfGreedsSession = 0;
/*  263 */   public static int washedUpSouvenirsSession = 0;
/*  264 */   public static int minosHuntersSession = 0;
/*  265 */   public static int siameseLynxesSession = 0;
/*  266 */   public static int minotaursSession = 0;
/*  267 */   public static int gaiaConstructsSession = 0;
/*  268 */   public static int minosChampionsSession = 0;
/*  269 */   public static int minosInquisitorsSession = 0;
/*      */ 
/*      */   
/*  272 */   public static int recombobulatorsSession = 0;
/*  273 */   public static int fumingPotatoBooksSession = 0;
/*      */   
/*  275 */   public static int bonzoStaffsSession = 0;
/*  276 */   public static double f1CoinsSpentSession = 0.0D;
/*  277 */   public static double f1TimeSpentSession = 0.0D;
/*      */   
/*  279 */   public static int scarfStudiesSession = 0;
/*  280 */   public static int adaptiveSwordsSession = 0;
/*  281 */   public static double f2CoinsSpentSession = 0.0D;
/*  282 */   public static double f2TimeSpentSession = 0.0D;
/*      */   
/*  284 */   public static int adaptiveHelmsSession = 0;
/*  285 */   public static int adaptiveChestsSession = 0;
/*  286 */   public static int adaptiveLegsSession = 0;
/*  287 */   public static int adaptiveBootsSession = 0;
/*  288 */   public static double f3CoinsSpentSession = 0.0D;
/*  289 */   public static double f3TimeSpentSession = 0.0D;
/*      */   
/*  291 */   public static int spiritWingsSession = 0;
/*  292 */   public static int spiritBonesSession = 0;
/*  293 */   public static int spiritBootsSession = 0;
/*  294 */   public static int spiritSwordsSession = 0;
/*  295 */   public static int spiritBowsSession = 0;
/*  296 */   public static int epicSpiritPetsSession = 0;
/*  297 */   public static int legSpiritPetsSession = 0;
/*  298 */   public static double f4CoinsSpentSession = 0.0D;
/*  299 */   public static double f4TimeSpentSession = 0.0D;
/*      */   
/*  301 */   public static int warpedStonesSession = 0;
/*  302 */   public static int shadowAssHelmsSession = 0;
/*  303 */   public static int shadowAssChestsSession = 0;
/*  304 */   public static int shadowAssLegsSession = 0;
/*  305 */   public static int shadowAssBootsSession = 0;
/*  306 */   public static int lastBreathsSession = 0;
/*  307 */   public static int lividDaggersSession = 0;
/*  308 */   public static int shadowFurysSession = 0;
/*  309 */   public static double f5CoinsSpentSession = 0.0D;
/*  310 */   public static double f5TimeSpentSession = 0.0D;
/*      */   
/*  312 */   public static int ancientRosesSession = 0;
/*  313 */   public static int precursorEyesSession = 0;
/*  314 */   public static int giantsSwordsSession = 0;
/*  315 */   public static int necroLordHelmsSession = 0;
/*  316 */   public static int necroLordChestsSession = 0;
/*  317 */   public static int necroLordLegsSession = 0;
/*  318 */   public static int necroLordBootsSession = 0;
/*  319 */   public static int necroSwordsSession = 0;
/*  320 */   public static double f6CoinsSpentSession = 0.0D;
/*  321 */   public static double f6TimeSpentSession = 0.0D;
/*      */   
/*  323 */   public static int witherBloodsSession = 0;
/*  324 */   public static int witherCloaksSession = 0;
/*  325 */   public static int implosionsSession = 0;
/*  326 */   public static int witherShieldsSession = 0;
/*  327 */   public static int shadowWarpsSession = 0;
/*  328 */   public static int necronsHandlesSession = 0;
/*  329 */   public static int autoRecombsSession = 0;
/*  330 */   public static int witherHelmsSession = 0;
/*  331 */   public static int witherChestsSession = 0;
/*  332 */   public static int witherLegsSession = 0;
/*  333 */   public static int witherBootsSession = 0;
/*  334 */   public static double f7CoinsSpentSession = 0.0D;
/*  335 */   public static double f7TimeSpentSession = 0.0D;
/*      */ 
/*      */   
/*      */   public String func_71517_b() {
/*  339 */     return "loot";
/*      */   }
/*      */ 
/*      */   
/*      */   public String func_71518_a(ICommandSender arg0) {
/*  344 */     return "/" + func_71517_b() + " <zombie/spider/wolf/fishing/catacombs/mythological> [winter/festival/spooky/f(1-7)/session]";
/*      */   }
/*      */ 
/*      */   
/*      */   public int func_82362_a() {
/*  349 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> func_180525_a(ICommandSender sender, String[] args, BlockPos pos) {
/*  354 */     if (args.length == 1)
/*  355 */       return func_71530_a(args, new String[] { "wolf", "spider", "zombie", "fishing", "catacombs", "mythological" }); 
/*  356 */     if (args.length == 2 && args[0].equalsIgnoreCase("fishing"))
/*  357 */       return func_71530_a(args, new String[] { "winter", "festival", "spooky", "session" }); 
/*  358 */     if (args.length == 2 && args[0].equalsIgnoreCase("catacombs"))
/*  359 */       return func_71530_a(args, new String[] { "f1", "floor1", "f2", "floor2", "f3", "floor3", "f4", "floor4", "f5", "floor5", "f6", "floor6", "f7", "floor7" }); 
/*  360 */     if (args.length > 1) {
/*  361 */       return func_71530_a(args, new String[] { "session" });
/*      */     }
/*  363 */     return null;
/*      */   }
/*      */   
/*      */   public void func_71515_b(ICommandSender arg0, String[] arg1) throws CommandException {
/*      */     String timeBetween, bossesBetween, drop20;
/*  368 */     EntityPlayer player = (EntityPlayer)arg0;
/*      */     
/*  370 */     if (arg1.length == 0) {
/*  371 */       player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Usage: " + func_71518_a(arg0)));
/*      */       
/*      */       return;
/*      */     } 
/*  375 */     double timeNow = (System.currentTimeMillis() / 1000L);
/*      */ 
/*      */ 
/*      */     
/*  379 */     NumberFormat nf = NumberFormat.getIntegerInstance(Locale.US);
/*  380 */     boolean showSession = false;
/*      */     
/*  382 */     if (arg1[arg1.length - 1].equalsIgnoreCase("session")) showSession = true;
/*      */     
/*  384 */     switch (arg1[0].toLowerCase()) {
/*      */       case "wolf":
/*  386 */         if (showSession) {
/*  387 */           if (wolfTimeSession == -1.0D) {
/*  388 */             timeBetween = "Never";
/*      */           } else {
/*  390 */             timeBetween = Utils.getTimeBetween(wolfTimeSession, timeNow);
/*      */           } 
/*  392 */           if (wolfBossesSession == -1) {
/*  393 */             bossesBetween = "Never";
/*      */           } else {
/*  395 */             bossesBetween = nf.format(wolfBossesSession);
/*      */           } 
/*  397 */           if (ToggleCommand.slayerCountTotal) {
/*  398 */             drop20 = nf.format(wolfWheelsSession);
/*      */           } else {
/*  400 */             drop20 = nf.format(wolfWheelsDropsSession) + " times";
/*      */           } 
/*      */           
/*  403 */           player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.AQUA + "" + EnumChatFormatting.BOLD + "-------------------\n" + EnumChatFormatting.DARK_AQUA + EnumChatFormatting.BOLD + "  Sven Loot Summary (Current Session):\n" + EnumChatFormatting.GOLD + "    Svens Killed: " + nf
/*      */                 
/*  405 */                 .format(wolfSvensSession) + "\n" + EnumChatFormatting.GREEN + "    Wolf Teeth: " + nf
/*  406 */                 .format(wolfTeethSession) + "\n" + EnumChatFormatting.BLUE + "    Hamster Wheels: " + drop20 + "\n" + EnumChatFormatting.AQUA + "    Spirit Runes: " + wolfSpiritsSession + "\n" + EnumChatFormatting.WHITE + "    Critical VI Books: " + wolfBooksSession + "\n" + EnumChatFormatting.DARK_RED + "    Red Claw Eggs: " + wolfEggsSession + "\n" + EnumChatFormatting.GOLD + "    Couture Runes: " + wolfCouturesSession + "\n" + EnumChatFormatting.AQUA + "    Grizzly Baits: " + wolfBaitsSession + "\n" + EnumChatFormatting.DARK_PURPLE + "    Overfluxes: " + wolfFluxesSession + "\n" + EnumChatFormatting.AQUA + "    Time Since RNG: " + timeBetween + "\n" + EnumChatFormatting.AQUA + "    Bosses Since RNG: " + bossesBetween + "\n" + EnumChatFormatting.AQUA + EnumChatFormatting.BOLD + " -------------------"));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           return;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  420 */         if (wolfTime == -1.0D) {
/*  421 */           timeBetween = "Never";
/*      */         } else {
/*  423 */           timeBetween = Utils.getTimeBetween(wolfTime, timeNow);
/*      */         } 
/*  425 */         if (wolfBosses == -1) {
/*  426 */           bossesBetween = "Never";
/*      */         } else {
/*  428 */           bossesBetween = nf.format(wolfBosses);
/*      */         } 
/*  430 */         if (ToggleCommand.slayerCountTotal) {
/*  431 */           drop20 = nf.format(wolfWheels);
/*      */         } else {
/*  433 */           drop20 = nf.format(wolfWheelsDrops) + " times";
/*      */         } 
/*      */         
/*  436 */         player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.AQUA + "" + EnumChatFormatting.BOLD + "-------------------\n" + EnumChatFormatting.DARK_AQUA + EnumChatFormatting.BOLD + "  Sven Loot Summary:\n" + EnumChatFormatting.GOLD + "    Svens Killed: " + nf
/*      */               
/*  438 */               .format(wolfSvens) + "\n" + EnumChatFormatting.GREEN + "    Wolf Teeth: " + nf
/*  439 */               .format(wolfTeeth) + "\n" + EnumChatFormatting.BLUE + "    Hamster Wheels: " + drop20 + "\n" + EnumChatFormatting.AQUA + "    Spirit Runes: " + wolfSpirits + "\n" + EnumChatFormatting.WHITE + "    Critical VI Books: " + wolfBooks + "\n" + EnumChatFormatting.DARK_RED + "    Red Claw Eggs: " + wolfEggs + "\n" + EnumChatFormatting.GOLD + "    Couture Runes: " + wolfCoutures + "\n" + EnumChatFormatting.AQUA + "    Grizzly Baits: " + wolfBaits + "\n" + EnumChatFormatting.DARK_PURPLE + "    Overfluxes: " + wolfFluxes + "\n" + EnumChatFormatting.AQUA + "    Time Since RNG: " + timeBetween + "\n" + EnumChatFormatting.AQUA + "    Bosses Since RNG: " + bossesBetween + "\n" + EnumChatFormatting.AQUA + EnumChatFormatting.BOLD + " -------------------"));
/*      */         return;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case "spider":
/*  452 */         if (showSession) {
/*  453 */           if (spiderTimeSession == -1.0D) {
/*  454 */             timeBetween = "Never";
/*      */           } else {
/*  456 */             timeBetween = Utils.getTimeBetween(spiderTimeSession, timeNow);
/*      */           } 
/*  458 */           if (spiderBossesSession == -1) {
/*  459 */             bossesBetween = "Never";
/*      */           } else {
/*  461 */             bossesBetween = nf.format(spiderBossesSession);
/*      */           } 
/*  463 */           if (ToggleCommand.slayerCountTotal) {
/*  464 */             drop20 = nf.format(spiderTAPSession);
/*      */           } else {
/*  466 */             drop20 = nf.format(spiderTAPDropsSession) + " times";
/*      */           } 
/*      */           
/*  469 */           player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "" + EnumChatFormatting.BOLD + "-------------------\n" + EnumChatFormatting.DARK_RED + EnumChatFormatting.BOLD + "  Spider Loot Summary (Current Session):\n" + EnumChatFormatting.GOLD + "    Tarantulas Killed: " + nf
/*      */                 
/*  471 */                 .format(spiderTarantulasSession) + "\n" + EnumChatFormatting.GREEN + "    Tarantula Webs: " + nf
/*  472 */                 .format(spiderWebsSession) + "\n" + EnumChatFormatting.DARK_GREEN + "    Arrow Poison: " + drop20 + "\n" + EnumChatFormatting.DARK_GRAY + "    Bite Runes: " + spiderBitesSession + "\n" + EnumChatFormatting.WHITE + "    Bane VI Books: " + spiderBooksSession + "\n" + EnumChatFormatting.AQUA + "    Spider Catalysts: " + spiderCatalystsSession + "\n" + EnumChatFormatting.DARK_PURPLE + "    Tarantula Talismans: " + spiderTalismansSession + "\n" + EnumChatFormatting.LIGHT_PURPLE + "    Fly Swatters: " + spiderSwattersSession + "\n" + EnumChatFormatting.GOLD + "    Digested Mosquitos: " + spiderMosquitosSession + "\n" + EnumChatFormatting.AQUA + "    Time Since RNG: " + timeBetween + "\n" + EnumChatFormatting.AQUA + "    Bosses Since RNG: " + bossesBetween + "\n" + EnumChatFormatting.RED + EnumChatFormatting.BOLD + " -------------------"));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           return;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  486 */         if (spiderTime == -1.0D) {
/*  487 */           timeBetween = "Never";
/*      */         } else {
/*  489 */           timeBetween = Utils.getTimeBetween(spiderTime, timeNow);
/*      */         } 
/*  491 */         if (spiderBosses == -1) {
/*  492 */           bossesBetween = "Never";
/*      */         } else {
/*  494 */           bossesBetween = nf.format(spiderBosses);
/*      */         } 
/*  496 */         if (ToggleCommand.slayerCountTotal) {
/*  497 */           drop20 = nf.format(spiderTAP);
/*      */         } else {
/*  499 */           drop20 = nf.format(spiderTAPDrops) + " times";
/*      */         } 
/*      */         
/*  502 */         player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "" + EnumChatFormatting.BOLD + "-------------------\n" + EnumChatFormatting.DARK_RED + EnumChatFormatting.BOLD + "  Spider Loot Summary:\n" + EnumChatFormatting.GOLD + "    Tarantulas Killed: " + nf
/*      */               
/*  504 */               .format(spiderTarantulas) + "\n" + EnumChatFormatting.GREEN + "    Tarantula Webs: " + nf
/*  505 */               .format(spiderWebs) + "\n" + EnumChatFormatting.DARK_GREEN + "    Arrow Poison: " + drop20 + "\n" + EnumChatFormatting.DARK_GRAY + "    Bite Runes: " + spiderBites + "\n" + EnumChatFormatting.WHITE + "    Bane VI Books: " + spiderBooks + "\n" + EnumChatFormatting.AQUA + "    Spider Catalysts: " + spiderCatalysts + "\n" + EnumChatFormatting.DARK_PURPLE + "    Tarantula Talismans: " + spiderTalismans + "\n" + EnumChatFormatting.LIGHT_PURPLE + "    Fly Swatters: " + spiderSwatters + "\n" + EnumChatFormatting.GOLD + "    Digested Mosquitos: " + spiderMosquitos + "\n" + EnumChatFormatting.AQUA + "    Time Since RNG: " + timeBetween + "\n" + EnumChatFormatting.AQUA + "    Bosses Since RNG: " + bossesBetween + "\n" + EnumChatFormatting.RED + EnumChatFormatting.BOLD + " -------------------"));
/*      */         return;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case "zombie":
/*  518 */         if (showSession) {
/*  519 */           if (zombieTimeSession == -1.0D) {
/*  520 */             timeBetween = "Never";
/*      */           } else {
/*  522 */             timeBetween = Utils.getTimeBetween(zombieTimeSession, timeNow);
/*      */           } 
/*  524 */           if (zombieBossesSession == -1) {
/*  525 */             bossesBetween = "Never";
/*      */           } else {
/*  527 */             bossesBetween = nf.format(zombieBossesSession);
/*      */           } 
/*  529 */           if (ToggleCommand.slayerCountTotal) {
/*  530 */             drop20 = nf.format(zombieFoulFleshSession);
/*      */           } else {
/*  532 */             drop20 = nf.format(zombieFoulFleshDropsSession) + " times";
/*      */           } 
/*      */           
/*  535 */           player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.GREEN + "" + EnumChatFormatting.BOLD + "-------------------\n" + EnumChatFormatting.DARK_GREEN + EnumChatFormatting.BOLD + "  Zombie Loot Summary (Current Session):\n" + EnumChatFormatting.GOLD + "    Revs Killed: " + nf
/*      */                 
/*  537 */                 .format(zombieRevsSession) + "\n" + EnumChatFormatting.GREEN + "    Revenant Flesh: " + nf
/*  538 */                 .format(zombieRevFleshSession) + "\n" + EnumChatFormatting.BLUE + "    Foul Flesh: " + drop20 + "\n" + EnumChatFormatting.DARK_GREEN + "    Pestilence Runes: " + zombiePestilencesSession + "\n" + EnumChatFormatting.WHITE + "    Smite VI Books: " + zombieBooksSession + "\n" + EnumChatFormatting.AQUA + "    Undead Catalysts: " + zombieUndeadCatasSession + "\n" + EnumChatFormatting.DARK_PURPLE + "    Beheaded Horrors: " + zombieBeheadedsSession + "\n" + EnumChatFormatting.RED + "    Revenant Catalysts: " + zombieRevCatasSession + "\n" + EnumChatFormatting.DARK_GREEN + "    Snake Runes: " + zombieSnakesSession + "\n" + EnumChatFormatting.GOLD + "    Scythe Blades: " + zombieScythesSession + "\n" + EnumChatFormatting.AQUA + "    Time Since RNG: " + timeBetween + "\n" + EnumChatFormatting.AQUA + "    Bosses Since RNG: " + bossesBetween + "\n" + EnumChatFormatting.GREEN + EnumChatFormatting.BOLD + " -------------------"));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           return;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  553 */         if (zombieTime == -1.0D) {
/*  554 */           timeBetween = "Never";
/*      */         } else {
/*  556 */           timeBetween = Utils.getTimeBetween(zombieTime, timeNow);
/*      */         } 
/*  558 */         if (zombieBosses == -1) {
/*  559 */           bossesBetween = "Never";
/*      */         } else {
/*  561 */           bossesBetween = nf.format(zombieBosses);
/*      */         } 
/*  563 */         if (ToggleCommand.slayerCountTotal) {
/*  564 */           drop20 = nf.format(zombieFoulFlesh);
/*      */         } else {
/*  566 */           drop20 = nf.format(zombieFoulFleshDrops) + " times";
/*      */         } 
/*      */         
/*  569 */         player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.GREEN + "" + EnumChatFormatting.BOLD + "-------------------\n" + EnumChatFormatting.DARK_GREEN + EnumChatFormatting.BOLD + "  Zombie Loot Summary:\n" + EnumChatFormatting.GOLD + "    Revs Killed: " + nf
/*      */               
/*  571 */               .format(zombieRevs) + "\n" + EnumChatFormatting.GREEN + "    Revenant Flesh: " + nf
/*  572 */               .format(zombieRevFlesh) + "\n" + EnumChatFormatting.BLUE + "    Foul Flesh: " + drop20 + "\n" + EnumChatFormatting.DARK_GREEN + "    Pestilence Runes: " + zombiePestilences + "\n" + EnumChatFormatting.WHITE + "    Smite VI Books: " + zombieBooks + "\n" + EnumChatFormatting.AQUA + "    Undead Catalysts: " + zombieUndeadCatas + "\n" + EnumChatFormatting.DARK_PURPLE + "    Beheaded Horrors: " + zombieBeheadeds + "\n" + EnumChatFormatting.RED + "    Revenant Catalysts: " + zombieRevCatas + "\n" + EnumChatFormatting.DARK_GREEN + "    Snake Runes: " + zombieSnakes + "\n" + EnumChatFormatting.GOLD + "    Scythe Blades: " + zombieScythes + "\n" + EnumChatFormatting.AQUA + "    Time Since RNG: " + timeBetween + "\n" + EnumChatFormatting.AQUA + "    Bosses Since RNG: " + bossesBetween + "\n" + EnumChatFormatting.GREEN + EnumChatFormatting.BOLD + " -------------------"));
/*      */         return;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case "fishing":
/*  586 */         if (arg1.length > 1) {
/*  587 */           if (arg1[1].equalsIgnoreCase("winter")) {
/*  588 */             if (showSession) {
/*  589 */               if (yetiTimeSession == -1.0D) {
/*  590 */                 timeBetween = "Never";
/*      */               } else {
/*  592 */                 timeBetween = Utils.getTimeBetween(yetiTimeSession, timeNow);
/*      */               } 
/*  594 */               if (yetiSCsSession == -1) {
/*  595 */                 bossesBetween = "Never";
/*      */               } else {
/*  597 */                 bossesBetween = nf.format(yetiSCsSession);
/*      */               } 
/*      */               
/*  600 */               player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.AQUA + "" + EnumChatFormatting.BOLD + "-------------------\n" + EnumChatFormatting.WHITE + EnumChatFormatting.BOLD + "  Winter Fishing Summary (Current Session):\n" + EnumChatFormatting.AQUA + "    Frozen Steves: " + nf
/*      */                     
/*  602 */                     .format(frozenStevesSession) + "\n" + EnumChatFormatting.WHITE + "    Snowmans: " + nf
/*  603 */                     .format(frostyTheSnowmansSession) + "\n" + EnumChatFormatting.DARK_GREEN + "    Grinches: " + nf
/*  604 */                     .format(grinchesSession) + "\n" + EnumChatFormatting.GOLD + "    Yetis: " + nf
/*  605 */                     .format(yetisSession) + "\n" + EnumChatFormatting.AQUA + "    Time Since Yeti: " + timeBetween + "\n" + EnumChatFormatting.AQUA + "    Creatures Since Yeti: " + bossesBetween + "\n" + EnumChatFormatting.AQUA + EnumChatFormatting.BOLD + " -------------------"));
/*      */ 
/*      */               
/*      */               return;
/*      */             } 
/*      */ 
/*      */             
/*  612 */             if (yetiTime == -1.0D) {
/*  613 */               timeBetween = "Never";
/*      */             } else {
/*  615 */               timeBetween = Utils.getTimeBetween(yetiTime, timeNow);
/*      */             } 
/*  617 */             if (yetiSCs == -1) {
/*  618 */               bossesBetween = "Never";
/*      */             } else {
/*  620 */               bossesBetween = nf.format(yetiSCs);
/*      */             } 
/*      */             
/*  623 */             player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.AQUA + "" + EnumChatFormatting.BOLD + "-------------------\n" + EnumChatFormatting.WHITE + EnumChatFormatting.BOLD + "  Winter Fishing Summary:\n" + EnumChatFormatting.AQUA + "    Frozen Steves: " + nf
/*      */                   
/*  625 */                   .format(frozenSteves) + "\n" + EnumChatFormatting.WHITE + "    Snowmans: " + nf
/*  626 */                   .format(frostyTheSnowmans) + "\n" + EnumChatFormatting.DARK_GREEN + "    Grinches: " + nf
/*  627 */                   .format(grinches) + "\n" + EnumChatFormatting.GOLD + "    Yetis: " + nf
/*  628 */                   .format(yetis) + "\n" + EnumChatFormatting.AQUA + "    Time Since Yeti: " + timeBetween + "\n" + EnumChatFormatting.AQUA + "    Creatures Since Yeti: " + bossesBetween + "\n" + EnumChatFormatting.AQUA + EnumChatFormatting.BOLD + " -------------------"));
/*      */             
/*      */             return;
/*      */           } 
/*      */           
/*  633 */           if (arg1[1].equalsIgnoreCase("festival")) {
/*  634 */             if (showSession) {
/*  635 */               player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.AQUA + "" + EnumChatFormatting.BOLD + "-------------------\n" + EnumChatFormatting.DARK_BLUE + EnumChatFormatting.BOLD + " Fishing Festival Summary (Current Session):\n" + EnumChatFormatting.LIGHT_PURPLE + "    Nurse Sharks: " + nf
/*      */                     
/*  637 */                     .format(nurseSharksSession) + "\n" + EnumChatFormatting.BLUE + "    Blue Sharks: " + nf
/*  638 */                     .format(blueSharksSession) + "\n" + EnumChatFormatting.GOLD + "    Tiger Sharks: " + nf
/*  639 */                     .format(tigerSharksSession) + "\n" + EnumChatFormatting.WHITE + "    Great White Sharks: " + nf
/*  640 */                     .format(greatWhiteSharksSession) + "\n" + EnumChatFormatting.AQUA + EnumChatFormatting.BOLD + " -------------------"));
/*      */               
/*      */               return;
/*      */             } 
/*      */             
/*  645 */             player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.AQUA + "" + EnumChatFormatting.BOLD + "-------------------\n" + EnumChatFormatting.DARK_BLUE + EnumChatFormatting.BOLD + " Fishing Festival Summary:\n" + EnumChatFormatting.LIGHT_PURPLE + "    Nurse Sharks: " + nf
/*      */                   
/*  647 */                   .format(nurseSharks) + "\n" + EnumChatFormatting.BLUE + "    Blue Sharks: " + nf
/*  648 */                   .format(blueSharks) + "\n" + EnumChatFormatting.GOLD + "    Tiger Sharks: " + nf
/*  649 */                   .format(tigerSharks) + "\n" + EnumChatFormatting.WHITE + "    Great White Sharks: " + nf
/*  650 */                   .format(greatWhiteSharks) + "\n" + EnumChatFormatting.AQUA + EnumChatFormatting.BOLD + " -------------------"));
/*      */           }
/*  652 */           else if (arg1[1].equalsIgnoreCase("spooky")) {
/*  653 */             if (showSession) {
/*  654 */               player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.AQUA + "" + EnumChatFormatting.BOLD + "-------------------\n" + EnumChatFormatting.GOLD + EnumChatFormatting.BOLD + " Spooky Fishing Summary (Current Session):\n" + EnumChatFormatting.BLUE + "    Scarecrows: " + nf
/*      */                     
/*  656 */                     .format(scarecrowsSession) + "\n" + EnumChatFormatting.GRAY + "    Nightmares: " + nf
/*  657 */                     .format(nightmaresSession) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Werewolves: " + nf
/*  658 */                     .format(werewolfsSession) + "\n" + EnumChatFormatting.GOLD + "    Phantom Fishers: " + nf
/*  659 */                     .format(phantomFishersSession) + "\n" + EnumChatFormatting.GOLD + "    Grim Reapers: " + nf
/*  660 */                     .format(grimReapersSession) + "\n" + EnumChatFormatting.AQUA + "" + EnumChatFormatting.BOLD + "-------------------"));
/*      */               
/*      */               return;
/*      */             } 
/*      */             
/*  665 */             player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.AQUA + "" + EnumChatFormatting.BOLD + "-------------------\n" + EnumChatFormatting.GOLD + EnumChatFormatting.BOLD + " Spooky Fishing Summary:\n" + EnumChatFormatting.BLUE + "    Scarecrows: " + nf
/*      */                   
/*  667 */                   .format(scarecrows) + "\n" + EnumChatFormatting.GRAY + "    Nightmares: " + nf
/*  668 */                   .format(nightmares) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Werewolves: " + nf
/*  669 */                   .format(werewolfs) + "\n" + EnumChatFormatting.GOLD + "    Phantom Fishers: " + nf
/*  670 */                   .format(phantomFishers) + "\n" + EnumChatFormatting.GOLD + "    Grim Reapers: " + nf
/*  671 */                   .format(grimReapers) + "\n" + EnumChatFormatting.AQUA + "" + EnumChatFormatting.BOLD + "-------------------"));
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/*  676 */         if (showSession) {
/*  677 */           if (empTimeSession == -1.0D) {
/*  678 */             timeBetween = "Never";
/*      */           } else {
/*  680 */             timeBetween = Utils.getTimeBetween(empTimeSession, timeNow);
/*      */           } 
/*  682 */           if (empSCsSession == -1) {
/*  683 */             bossesBetween = "Never";
/*      */           } else {
/*  685 */             bossesBetween = nf.format(empSCsSession);
/*      */           } 
/*      */           
/*  688 */           player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.DARK_AQUA + "" + EnumChatFormatting.BOLD + "-------------------\n" + EnumChatFormatting.AQUA + EnumChatFormatting.BOLD + "  Fishing Summary (Current Session):\n" + EnumChatFormatting.AQUA + "    Sea Creatures Caught: " + nf
/*      */                 
/*  690 */                 .format(seaCreaturesSession) + "\n" + EnumChatFormatting.GOLD + "    Good Catches: " + nf
/*  691 */                 .format(goodCatchesSession) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Great Catches: " + nf
/*  692 */                 .format(greatCatchesSession) + "\n\n" + EnumChatFormatting.GRAY + "    Squids: " + nf
/*  693 */                 .format(squidsSession) + "\n" + EnumChatFormatting.GREEN + "    Sea Walkers: " + nf
/*  694 */                 .format(seaWalkersSession) + "\n" + EnumChatFormatting.DARK_GRAY + "    Night Squids: " + nf
/*  695 */                 .format(nightSquidsSession) + "\n" + EnumChatFormatting.DARK_AQUA + "    Sea Guardians: " + nf
/*  696 */                 .format(seaGuardiansSession) + "\n" + EnumChatFormatting.BLUE + "    Sea Witches: " + nf
/*  697 */                 .format(seaWitchesSession) + "\n" + EnumChatFormatting.GREEN + "    Sea Archers: " + nf
/*  698 */                 .format(seaArchersSession) + "\n" + EnumChatFormatting.GREEN + "    Monster of the Deeps: " + nf
/*  699 */                 .format(monsterOfTheDeepsSession) + "\n" + EnumChatFormatting.YELLOW + "    Catfishes: " + nf
/*  700 */                 .format(catfishesSession) + "\n" + EnumChatFormatting.GOLD + "    Carrot Kings: " + nf
/*  701 */                 .format(carrotKingsSession) + "\n" + EnumChatFormatting.GRAY + "    Sea Leeches: " + nf
/*  702 */                 .format(seaLeechesSession) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Guardian Defenders: " + nf
/*  703 */                 .format(guardianDefendersSession) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Deep Sea Protectors: " + nf
/*  704 */                 .format(deepSeaProtectorsSession) + "\n" + EnumChatFormatting.GOLD + "    Hydras: " + nf
/*  705 */                 .format(hydrasSession) + "\n" + EnumChatFormatting.GOLD + "    Sea Emperors: " + nf
/*  706 */                 .format(seaEmperorsSession) + "\n" + EnumChatFormatting.AQUA + "    Time Since Sea Emperor: " + timeBetween + "\n" + EnumChatFormatting.AQUA + "    Sea Creatures Since Sea Emperor: " + bossesBetween + "\n" + EnumChatFormatting.DARK_AQUA + EnumChatFormatting.BOLD + " -------------------"));
/*      */ 
/*      */           
/*      */           return;
/*      */         } 
/*      */ 
/*      */         
/*  713 */         if (empTime == -1.0D) {
/*  714 */           timeBetween = "Never";
/*      */         } else {
/*  716 */           timeBetween = Utils.getTimeBetween(empTime, timeNow);
/*      */         } 
/*  718 */         if (empSCs == -1) {
/*  719 */           bossesBetween = "Never";
/*      */         } else {
/*  721 */           bossesBetween = nf.format(empSCs);
/*      */         } 
/*      */         
/*  724 */         player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.DARK_AQUA + "" + EnumChatFormatting.BOLD + "-------------------\n" + EnumChatFormatting.AQUA + EnumChatFormatting.BOLD + "  Fishing Summary:\n" + EnumChatFormatting.AQUA + "    Sea Creatures Caught: " + nf
/*      */               
/*  726 */               .format(seaCreatures) + "\n" + EnumChatFormatting.GOLD + "    Good Catches: " + nf
/*  727 */               .format(goodCatches) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Great Catches: " + nf
/*  728 */               .format(greatCatches) + "\n\n" + EnumChatFormatting.GRAY + "    Squids: " + nf
/*  729 */               .format(squids) + "\n" + EnumChatFormatting.GREEN + "    Sea Walkers: " + nf
/*  730 */               .format(seaWalkers) + "\n" + EnumChatFormatting.DARK_GRAY + "    Night Squids: " + nf
/*  731 */               .format(nightSquids) + "\n" + EnumChatFormatting.DARK_AQUA + "    Sea Guardians: " + nf
/*  732 */               .format(seaGuardians) + "\n" + EnumChatFormatting.BLUE + "    Sea Witches: " + nf
/*  733 */               .format(seaWitches) + "\n" + EnumChatFormatting.GREEN + "    Sea Archers: " + nf
/*  734 */               .format(seaArchers) + "\n" + EnumChatFormatting.GREEN + "    Monster of the Deeps: " + nf
/*  735 */               .format(monsterOfTheDeeps) + "\n" + EnumChatFormatting.YELLOW + "    Catfishes: " + nf
/*  736 */               .format(catfishes) + "\n" + EnumChatFormatting.GOLD + "    Carrot Kings: " + nf
/*  737 */               .format(carrotKings) + "\n" + EnumChatFormatting.GRAY + "    Sea Leeches: " + nf
/*  738 */               .format(seaLeeches) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Guardian Defenders: " + nf
/*  739 */               .format(guardianDefenders) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Deep Sea Protectors: " + nf
/*  740 */               .format(deepSeaProtectors) + "\n" + EnumChatFormatting.GOLD + "    Hydras: " + nf
/*  741 */               .format(hydras) + "\n" + EnumChatFormatting.GOLD + "    Sea Emperors: " + nf
/*  742 */               .format(seaEmperors) + "\n" + EnumChatFormatting.AQUA + "    Time Since Sea Emperor: " + timeBetween + "\n" + EnumChatFormatting.AQUA + "    Sea Creatures Since Sea Emperor: " + bossesBetween + "\n" + EnumChatFormatting.DARK_AQUA + EnumChatFormatting.BOLD + " -------------------"));
/*      */         return;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case "mythological":
/*  749 */         if (showSession) {
/*  750 */           player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.GOLD + "" + EnumChatFormatting.BOLD + "-------------------\n" + EnumChatFormatting.YELLOW + EnumChatFormatting.BOLD + "  Mythological Event Summary (Current Session):\n" + EnumChatFormatting.YELLOW + "    Coins: " + 
/*      */                 
/*  752 */                 Utils.getMoneySpent(mythCoinsSession) + "\n" + EnumChatFormatting.WHITE + "    Griffin Feathers: " + nf
/*  753 */                 .format(griffinFeathersSession) + "\n" + EnumChatFormatting.GOLD + "    Crown of Greeds: " + nf
/*  754 */                 .format(crownOfGreedsSession) + "\n" + EnumChatFormatting.AQUA + "    Washed-up Souvenirs: " + nf
/*  755 */                 .format(washedUpSouvenirsSession) + "\n" + EnumChatFormatting.RED + "    Minos Hunters: " + nf
/*  756 */                 .format(minosHuntersSession) + "\n" + EnumChatFormatting.GRAY + "   Siamese Lynxes: " + nf
/*  757 */                 .format(siameseLynxesSession) + "\n" + EnumChatFormatting.RED + "   Minotaurs: " + nf
/*  758 */                 .format(minotaursSession) + "\n" + EnumChatFormatting.WHITE + "   Gaia Constructs: " + nf
/*  759 */                 .format(gaiaConstructsSession) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Minos Champions: " + nf
/*  760 */                 .format(minosChampionsSession) + "\n" + EnumChatFormatting.GOLD + "    Minos Inquisitors: " + nf
/*  761 */                 .format(minosInquisitorsSession) + "\n" + EnumChatFormatting.GOLD + EnumChatFormatting.BOLD + "-------------------"));
/*      */           
/*      */           return;
/*      */         } 
/*  765 */         player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.GOLD + "" + EnumChatFormatting.BOLD + "-------------------\n" + EnumChatFormatting.YELLOW + EnumChatFormatting.BOLD + "  Mythological Event Summary:\n" + EnumChatFormatting.YELLOW + "    Coins: " + 
/*      */               
/*  767 */               Utils.getMoneySpent(mythCoins) + "\n" + EnumChatFormatting.WHITE + "    Griffin Feathers: " + nf
/*  768 */               .format(griffinFeathers) + "\n" + EnumChatFormatting.GOLD + "    Crown of Greeds: " + nf
/*  769 */               .format(crownOfGreeds) + "\n" + EnumChatFormatting.AQUA + "    Washed-up Souvenirs: " + nf
/*  770 */               .format(washedUpSouvenirs) + "\n" + EnumChatFormatting.RED + "    Minos Hunters: " + nf
/*  771 */               .format(minosHunters) + "\n" + EnumChatFormatting.GRAY + "   Siamese Lynxes: " + nf
/*  772 */               .format(siameseLynxes) + "\n" + EnumChatFormatting.RED + "   Minotaurs: " + nf
/*  773 */               .format(minotaurs) + "\n" + EnumChatFormatting.WHITE + "   Gaia Constructs: " + nf
/*  774 */               .format(gaiaConstructs) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Minos Champions: " + nf
/*  775 */               .format(minosChampions) + "\n" + EnumChatFormatting.GOLD + "    Minos Inquisitors: " + nf
/*  776 */               .format(minosInquisitors) + "\n" + EnumChatFormatting.GOLD + EnumChatFormatting.BOLD + "-------------------"));
/*      */         return;
/*      */       
/*      */       case "catacombs":
/*  780 */         if (arg1.length == 1) {
/*  781 */           player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Usage: /loot catacombs <f1/f2/f3/f4/f5/f6/f7>"));
/*      */           return;
/*      */         } 
/*  784 */         switch (arg1[1].toLowerCase()) {
/*      */           case "f1":
/*      */           case "floor1":
/*  787 */             if (showSession) {
/*  788 */               player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.DARK_RED + "" + EnumChatFormatting.BOLD + "-------------------\n" + EnumChatFormatting.RED + EnumChatFormatting.BOLD + "  Catacombs F1 Summary (Current Session):\n" + EnumChatFormatting.GOLD + "    Recombobulator 3000s: " + nf
/*      */                     
/*  790 */                     .format(recombobulatorsSession) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Fuming Potato Books: " + nf
/*  791 */                     .format(fumingPotatoBooksSession) + "\n" + EnumChatFormatting.BLUE + "    Bonzo's Staffs: " + nf
/*  792 */                     .format(bonzoStaffsSession) + "\n" + EnumChatFormatting.AQUA + "    Coins Spent: " + 
/*  793 */                     Utils.getMoneySpent(f1CoinsSpentSession) + "\n" + EnumChatFormatting.AQUA + "    Time Spent: " + 
/*  794 */                     Utils.getTimeBetween(0.0D, f1TimeSpentSession) + "\n" + EnumChatFormatting.DARK_RED + EnumChatFormatting.BOLD + " -------------------"));
/*      */               
/*      */               return;
/*      */             } 
/*  798 */             player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.DARK_RED + "" + EnumChatFormatting.BOLD + "-------------------\n" + EnumChatFormatting.RED + EnumChatFormatting.BOLD + "  Catacombs F1 Summary:\n" + EnumChatFormatting.GOLD + "    Recombobulator 3000s: " + nf
/*      */                   
/*  800 */                   .format(recombobulators) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Fuming Potato Books: " + nf
/*  801 */                   .format(fumingPotatoBooks) + "\n" + EnumChatFormatting.BLUE + "    Bonzo's Staffs: " + nf
/*  802 */                   .format(bonzoStaffs) + "\n" + EnumChatFormatting.AQUA + "    Coins Spent: " + 
/*  803 */                   Utils.getMoneySpent(f1CoinsSpent) + "\n" + EnumChatFormatting.AQUA + "    Time Spent: " + 
/*  804 */                   Utils.getTimeBetween(0.0D, f1TimeSpent) + "\n" + EnumChatFormatting.DARK_RED + EnumChatFormatting.BOLD + " -------------------"));
/*      */             return;
/*      */           
/*      */           case "f2":
/*      */           case "floor2":
/*  809 */             if (showSession) {
/*  810 */               player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.DARK_RED + "" + EnumChatFormatting.BOLD + "-------------------\n" + EnumChatFormatting.RED + EnumChatFormatting.BOLD + "  Catacombs F2 Summary (Current Session):\n" + EnumChatFormatting.GOLD + "    Recombobulator 3000s: " + nf
/*      */                     
/*  812 */                     .format(recombobulatorsSession) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Fuming Potato Books: " + nf
/*  813 */                     .format(fumingPotatoBooksSession) + "\n" + EnumChatFormatting.BLUE + "    Scarf's Studies: " + nf
/*  814 */                     .format(scarfStudiesSession) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Adaptive Blades: " + nf
/*  815 */                     .format(adaptiveSwordsSession) + "\n" + EnumChatFormatting.AQUA + "    Coins Spent: " + 
/*  816 */                     Utils.getMoneySpent(f2CoinsSpentSession) + "\n" + EnumChatFormatting.AQUA + "    Time Spent: " + 
/*  817 */                     Utils.getTimeBetween(0.0D, f2TimeSpentSession) + "\n" + EnumChatFormatting.DARK_RED + EnumChatFormatting.BOLD + " -------------------"));
/*      */               
/*      */               return;
/*      */             } 
/*  821 */             player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.DARK_RED + "" + EnumChatFormatting.BOLD + "-------------------\n" + EnumChatFormatting.RED + EnumChatFormatting.BOLD + "  Catacombs F2 Summary:\n" + EnumChatFormatting.GOLD + "    Recombobulator 3000s: " + nf
/*      */                   
/*  823 */                   .format(recombobulators) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Fuming Potato Books: " + nf
/*  824 */                   .format(fumingPotatoBooks) + "\n" + EnumChatFormatting.BLUE + "    Scarf's Studies: " + nf
/*  825 */                   .format(scarfStudies) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Adaptive Blades: " + nf
/*  826 */                   .format(adaptiveSwords) + "\n" + EnumChatFormatting.AQUA + "    Coins Spent: " + 
/*  827 */                   Utils.getMoneySpent(f2CoinsSpent) + "\n" + EnumChatFormatting.AQUA + "    Time Spent: " + 
/*  828 */                   Utils.getTimeBetween(0.0D, f2TimeSpent) + "\n" + EnumChatFormatting.DARK_RED + EnumChatFormatting.BOLD + " -------------------"));
/*      */             return;
/*      */           
/*      */           case "f3":
/*      */           case "floor3":
/*  833 */             if (showSession) {
/*  834 */               player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.DARK_RED + "" + EnumChatFormatting.BOLD + "-------------------\n" + EnumChatFormatting.RED + EnumChatFormatting.BOLD + "  Catacombs F3 Summary (Current Session):\n" + EnumChatFormatting.GOLD + "    Recombobulator 3000s: " + nf
/*      */                     
/*  836 */                     .format(recombobulatorsSession) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Fuming Potato Books: " + nf
/*  837 */                     .format(fumingPotatoBooksSession) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Adaptive Helmets: " + nf
/*  838 */                     .format(adaptiveHelmsSession) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Adaptive Chestplates: " + nf
/*  839 */                     .format(adaptiveChestsSession) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Adaptive Leggings: " + nf
/*  840 */                     .format(adaptiveLegsSession) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Adaptive Boots: " + nf
/*  841 */                     .format(adaptiveBootsSession) + "\n" + EnumChatFormatting.AQUA + "    Coins Spent: " + 
/*  842 */                     Utils.getMoneySpent(f3CoinsSpentSession) + "\n" + EnumChatFormatting.AQUA + "    Time Spent: " + 
/*  843 */                     Utils.getTimeBetween(0.0D, f3TimeSpentSession) + "\n" + EnumChatFormatting.DARK_RED + EnumChatFormatting.BOLD + " -------------------"));
/*      */               
/*      */               return;
/*      */             } 
/*  847 */             player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.DARK_RED + "" + EnumChatFormatting.BOLD + "-------------------\n" + EnumChatFormatting.RED + EnumChatFormatting.BOLD + "  Catacombs F3 Summary:\n" + EnumChatFormatting.GOLD + "    Recombobulator 3000s: " + nf
/*      */                   
/*  849 */                   .format(recombobulators) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Fuming Potato Books: " + nf
/*  850 */                   .format(fumingPotatoBooks) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Adaptive Helmets: " + nf
/*  851 */                   .format(adaptiveHelms) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Adaptive Chestplates: " + nf
/*  852 */                   .format(adaptiveChests) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Adaptive Leggings: " + nf
/*  853 */                   .format(adaptiveLegs) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Adaptive Boots: " + nf
/*  854 */                   .format(adaptiveBoots) + "\n" + EnumChatFormatting.AQUA + "    Coins Spent: " + 
/*  855 */                   Utils.getMoneySpent(f3CoinsSpent) + "\n" + EnumChatFormatting.AQUA + "    Time Spent: " + 
/*  856 */                   Utils.getTimeBetween(0.0D, f3TimeSpent) + "\n" + EnumChatFormatting.DARK_RED + EnumChatFormatting.BOLD + " -------------------"));
/*      */             return;
/*      */           
/*      */           case "f4":
/*      */           case "floor4":
/*  861 */             if (showSession) {
/*  862 */               player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.DARK_RED + "" + EnumChatFormatting.BOLD + "-------------------\n" + EnumChatFormatting.RED + EnumChatFormatting.BOLD + "  Catacombs F4 Summary (Current Session):\n" + EnumChatFormatting.GOLD + "    Recombobulator 3000s: " + nf
/*      */                     
/*  864 */                     .format(recombobulatorsSession) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Fuming Potato Books: " + nf
/*  865 */                     .format(fumingPotatoBooksSession) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Spirit Wings: " + nf
/*  866 */                     .format(spiritWingsSession) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Spirit Bones: " + nf
/*  867 */                     .format(spiritBonesSession) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Spirit Boots: " + nf
/*  868 */                     .format(spiritBootsSession) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Spirit Swords: " + nf
/*  869 */                     .format(spiritSwordsSession) + "\n" + EnumChatFormatting.GOLD + "    Spirit Bows: " + nf
/*  870 */                     .format(spiritBowsSession) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Epic Spirit Pets: " + nf
/*  871 */                     .format(epicSpiritPetsSession) + "\n" + EnumChatFormatting.GOLD + "    Leg Spirit Pets: " + nf
/*  872 */                     .format(legSpiritPetsSession) + "\n" + EnumChatFormatting.AQUA + "    Coins Spent: " + 
/*  873 */                     Utils.getMoneySpent(f4CoinsSpentSession) + "\n" + EnumChatFormatting.AQUA + "    Time Spent: " + 
/*  874 */                     Utils.getTimeBetween(0.0D, f4TimeSpentSession) + "\n" + EnumChatFormatting.DARK_RED + EnumChatFormatting.BOLD + " -------------------"));
/*      */               
/*      */               return;
/*      */             } 
/*  878 */             player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.DARK_RED + "" + EnumChatFormatting.BOLD + "-------------------\n" + EnumChatFormatting.RED + EnumChatFormatting.BOLD + "  Catacombs F4 Summary:\n" + EnumChatFormatting.GOLD + "    Recombobulator 3000s: " + nf
/*      */                   
/*  880 */                   .format(recombobulators) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Fuming Potato Books: " + nf
/*  881 */                   .format(fumingPotatoBooks) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Spirit Wings: " + nf
/*  882 */                   .format(spiritWings) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Spirit Bones: " + nf
/*  883 */                   .format(spiritBones) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Spirit Boots: " + nf
/*  884 */                   .format(spiritBoots) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Spirit Swords: " + nf
/*  885 */                   .format(spiritSwords) + "\n" + EnumChatFormatting.GOLD + "    Spirit Bows: " + nf
/*  886 */                   .format(spiritBows) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Epic Spirit Pets: " + nf
/*  887 */                   .format(epicSpiritPets) + "\n" + EnumChatFormatting.GOLD + "    Leg Spirit Pets: " + nf
/*  888 */                   .format(legSpiritPets) + "\n" + EnumChatFormatting.AQUA + "    Coins Spent: " + 
/*  889 */                   Utils.getMoneySpent(f4CoinsSpent) + "\n" + EnumChatFormatting.AQUA + "    Time Spent: " + 
/*  890 */                   Utils.getTimeBetween(0.0D, f4TimeSpent) + "\n" + EnumChatFormatting.DARK_RED + EnumChatFormatting.BOLD + " -------------------"));
/*      */             return;
/*      */           
/*      */           case "f5":
/*      */           case "floor5":
/*  895 */             if (showSession) {
/*  896 */               player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.DARK_RED + "" + EnumChatFormatting.BOLD + "-------------------\n" + EnumChatFormatting.RED + EnumChatFormatting.BOLD + "  Catacombs F5 Summary (Current Session):\n" + EnumChatFormatting.GOLD + "    Recombobulator 3000s: " + nf
/*      */                     
/*  898 */                     .format(recombobulatorsSession) + "\n" + EnumChatFormatting.BLUE + "    Warped Stones: " + nf
/*  899 */                     .format(warpedStonesSession) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Fuming Potato Books: " + nf
/*  900 */                     .format(fumingPotatoBooksSession) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Shadow Assassin Helmets: " + nf
/*  901 */                     .format(shadowAssHelmsSession) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Shadow Assassin Chests: " + nf
/*  902 */                     .format(shadowAssChestsSession) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Shadow Assassin Legs: " + nf
/*  903 */                     .format(shadowAssLegsSession) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Shadow Assassin Boots: " + nf
/*  904 */                     .format(shadowAssBootsSession) + "\n" + EnumChatFormatting.GOLD + "    Last Breaths: " + nf
/*  905 */                     .format(lastBreathsSession) + "\n" + EnumChatFormatting.GOLD + "    Livid Daggers: " + nf
/*  906 */                     .format(lividDaggersSession) + "\n" + EnumChatFormatting.GOLD + "    Shadow Furys: " + nf
/*  907 */                     .format(shadowFurysSession) + "\n" + EnumChatFormatting.AQUA + "    Coins Spent: " + 
/*  908 */                     Utils.getMoneySpent(f5CoinsSpentSession) + "\n" + EnumChatFormatting.AQUA + "    Time Spent: " + 
/*  909 */                     Utils.getTimeBetween(0.0D, f5TimeSpentSession) + "\n" + EnumChatFormatting.DARK_RED + EnumChatFormatting.BOLD + " -------------------"));
/*      */               
/*      */               return;
/*      */             } 
/*  913 */             player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.DARK_RED + "" + EnumChatFormatting.BOLD + "-------------------\n" + EnumChatFormatting.RED + EnumChatFormatting.BOLD + "  Catacombs F5 Summary:\n" + EnumChatFormatting.GOLD + "    Recombobulator 3000s: " + nf
/*      */                   
/*  915 */                   .format(recombobulators) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Fuming Potato Books: " + nf
/*  916 */                   .format(fumingPotatoBooks) + "\n" + EnumChatFormatting.BLUE + "    Warped Stones: " + nf
/*  917 */                   .format(warpedStones) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Shadow Assassin Helmets: " + nf
/*  918 */                   .format(shadowAssHelms) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Shadow Assassin Chests: " + nf
/*  919 */                   .format(shadowAssChests) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Shadow Assassin Legs: " + nf
/*  920 */                   .format(shadowAssLegs) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Shadow Assassin Boots: " + nf
/*  921 */                   .format(shadowAssBoots) + "\n" + EnumChatFormatting.GOLD + "    Last Breaths: " + nf
/*  922 */                   .format(lastBreaths) + "\n" + EnumChatFormatting.GOLD + "    Livid Daggers: " + nf
/*  923 */                   .format(lividDaggers) + "\n" + EnumChatFormatting.GOLD + "    Shadow Furys: " + nf
/*  924 */                   .format(shadowFurys) + "\n" + EnumChatFormatting.AQUA + "    Coins Spent: " + 
/*  925 */                   Utils.getMoneySpent(f5CoinsSpent) + "\n" + EnumChatFormatting.AQUA + "    Time Spent: " + 
/*  926 */                   Utils.getTimeBetween(0.0D, f5TimeSpent) + "\n" + EnumChatFormatting.DARK_RED + EnumChatFormatting.BOLD + " -------------------"));
/*      */             return;
/*      */           
/*      */           case "f6":
/*      */           case "floor6":
/*  931 */             if (showSession) {
/*  932 */               player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.DARK_RED + "" + EnumChatFormatting.BOLD + "-------------------\n" + EnumChatFormatting.RED + EnumChatFormatting.BOLD + "  Catacombs F6 Summary (Current Session):\n" + EnumChatFormatting.GOLD + "    Recombobulator 3000s: " + nf
/*      */                     
/*  934 */                     .format(recombobulatorsSession) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Fuming Potato Books: " + nf
/*  935 */                     .format(fumingPotatoBooksSession) + "\n" + EnumChatFormatting.BLUE + "    Ancient Roses: " + nf
/*  936 */                     .format(ancientRosesSession) + "\n" + EnumChatFormatting.GOLD + "    Precursor Eyes: " + nf
/*  937 */                     .format(precursorEyesSession) + "\n" + EnumChatFormatting.GOLD + "    Giant's Swords: " + nf
/*  938 */                     .format(giantsSwordsSession) + "\n" + EnumChatFormatting.GOLD + "    Necro Lord Helmets: " + nf
/*  939 */                     .format(necroLordHelmsSession) + "\n" + EnumChatFormatting.GOLD + "    Necro Lord Chestplates: " + nf
/*  940 */                     .format(necroLordChestsSession) + "\n" + EnumChatFormatting.GOLD + "    Necro Lord Leggings: " + nf
/*  941 */                     .format(necroLordLegsSession) + "\n" + EnumChatFormatting.GOLD + "    Necro Lord Boots: " + nf
/*  942 */                     .format(necroLordBootsSession) + "\n" + EnumChatFormatting.GOLD + "    Necro Swords: " + nf
/*  943 */                     .format(necroSwordsSession) + "\n" + EnumChatFormatting.AQUA + "    Coins Spent: " + 
/*  944 */                     Utils.getMoneySpent(f6CoinsSpentSession) + "\n" + EnumChatFormatting.AQUA + "    Time Spent: " + 
/*  945 */                     Utils.getTimeBetween(0.0D, f6TimeSpentSession) + "\n" + EnumChatFormatting.DARK_RED + EnumChatFormatting.BOLD + " -------------------"));
/*      */               
/*      */               return;
/*      */             } 
/*  949 */             player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.DARK_RED + "" + EnumChatFormatting.BOLD + "-------------------\n" + EnumChatFormatting.RED + EnumChatFormatting.BOLD + "  Catacombs F6 Summary:\n" + EnumChatFormatting.GOLD + "    Recombobulator 3000s: " + nf
/*      */                   
/*  951 */                   .format(recombobulators) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Fuming Potato Books: " + nf
/*  952 */                   .format(fumingPotatoBooks) + "\n" + EnumChatFormatting.BLUE + "    Ancient Roses: " + nf
/*  953 */                   .format(ancientRoses) + "\n" + EnumChatFormatting.GOLD + "    Precursor Eyes: " + nf
/*  954 */                   .format(precursorEyes) + "\n" + EnumChatFormatting.GOLD + "    Giant's Swords: " + nf
/*  955 */                   .format(giantsSwords) + "\n" + EnumChatFormatting.GOLD + "    Necro Lord Helmets: " + nf
/*  956 */                   .format(necroLordHelms) + "\n" + EnumChatFormatting.GOLD + "    Necro Lord Chestplates: " + nf
/*  957 */                   .format(necroLordChests) + "\n" + EnumChatFormatting.GOLD + "    Necro Lord Leggings: " + nf
/*  958 */                   .format(necroLordLegs) + "\n" + EnumChatFormatting.GOLD + "    Necro Lord Boots: " + nf
/*  959 */                   .format(necroLordBoots) + "\n" + EnumChatFormatting.GOLD + "    Necro Swords: " + nf
/*  960 */                   .format(necroSwords) + "\n" + EnumChatFormatting.AQUA + "    Coins Spent: " + 
/*  961 */                   Utils.getMoneySpent(f6CoinsSpent) + "\n" + EnumChatFormatting.AQUA + "    Time Spent: " + 
/*  962 */                   Utils.getTimeBetween(0.0D, f6TimeSpent) + "\n" + EnumChatFormatting.DARK_RED + EnumChatFormatting.BOLD + " -------------------"));
/*      */             return;
/*      */           
/*      */           case "f7":
/*      */           case "floor7":
/*  967 */             if (showSession) {
/*  968 */               player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.DARK_RED + "" + EnumChatFormatting.BOLD + "-------------------\n" + EnumChatFormatting.RED + EnumChatFormatting.BOLD + "  Catacombs F7 Summary (Current Session):\n" + EnumChatFormatting.GOLD + "    Recombobulator 3000s: " + nf
/*      */                     
/*  970 */                     .format(recombobulatorsSession) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Fuming Potato Books: " + nf
/*  971 */                     .format(fumingPotatoBooksSession) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Wither Bloods: " + nf
/*  972 */                     .format(witherBloodsSession) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Wither Cloaks: " + nf
/*  973 */                     .format(witherCloaksSession) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Implosions: " + nf
/*  974 */                     .format(implosionsSession) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Wither Shields: " + nf
/*  975 */                     .format(witherShieldsSession) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Shadow Warps: " + nf
/*  976 */                     .format(shadowWarpsSession) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Necron's Handles: " + nf
/*  977 */                     .format(necronsHandlesSession) + "\n" + EnumChatFormatting.GOLD + "    Auto Recombobulator: " + nf
/*  978 */                     .format(autoRecombsSession) + "\n" + EnumChatFormatting.GOLD + "    Wither Helmets: " + nf
/*  979 */                     .format(witherHelmsSession) + "\n" + EnumChatFormatting.GOLD + "    Wither Chesplates: " + nf
/*  980 */                     .format(witherChestsSession) + "\n" + EnumChatFormatting.GOLD + "    Wither Leggings: " + nf
/*  981 */                     .format(witherLegsSession) + "\n" + EnumChatFormatting.GOLD + "    Wither Boots: " + nf
/*  982 */                     .format(witherBootsSession) + "\n" + EnumChatFormatting.AQUA + "    Coins Spent: " + 
/*  983 */                     Utils.getMoneySpent(f7CoinsSpentSession) + "\n" + EnumChatFormatting.AQUA + "    Time Spent: " + 
/*  984 */                     Utils.getTimeBetween(0.0D, f7TimeSpentSession) + "\n" + EnumChatFormatting.DARK_RED + EnumChatFormatting.BOLD + " -------------------"));
/*      */               
/*      */               return;
/*      */             } 
/*  988 */             player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.DARK_RED + "" + EnumChatFormatting.BOLD + "-------------------\n" + EnumChatFormatting.RED + EnumChatFormatting.BOLD + "  Catacombs F7 Summary:\n" + EnumChatFormatting.GOLD + "    Recombobulator 3000s: " + nf
/*      */                   
/*  990 */                   .format(recombobulators) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Fuming Potato Books: " + nf
/*  991 */                   .format(fumingPotatoBooks) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Wither Bloods: " + nf
/*  992 */                   .format(witherBloods) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Wither Cloaks: " + nf
/*  993 */                   .format(witherCloaks) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Implosions: " + nf
/*  994 */                   .format(implosions) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Wither Shields: " + nf
/*  995 */                   .format(witherShields) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Shadow Warps: " + nf
/*  996 */                   .format(shadowWarps) + "\n" + EnumChatFormatting.DARK_PURPLE + "    Necron's Handles: " + nf
/*  997 */                   .format(necronsHandles) + "\n" + EnumChatFormatting.GOLD + "    Auto Recombobulator: " + nf
/*  998 */                   .format(autoRecombs) + "\n" + EnumChatFormatting.GOLD + "    Wither Helmets: " + nf
/*  999 */                   .format(witherHelms) + "\n" + EnumChatFormatting.GOLD + "    Wither Chesplates: " + nf
/* 1000 */                   .format(witherChests) + "\n" + EnumChatFormatting.GOLD + "    Wither Leggings: " + nf
/* 1001 */                   .format(witherLegs) + "\n" + EnumChatFormatting.GOLD + "    Wither Boots: " + nf
/* 1002 */                   .format(witherBoots) + "\n" + EnumChatFormatting.AQUA + "    Coins Spent: " + 
/* 1003 */                   Utils.getMoneySpent(f7CoinsSpent) + "\n" + EnumChatFormatting.AQUA + "    Time Spent: " + 
/* 1004 */                   Utils.getTimeBetween(0.0D, f7TimeSpent) + "\n" + EnumChatFormatting.DARK_RED + EnumChatFormatting.BOLD + " -------------------"));
/*      */             return;
/*      */         } 
/*      */         
/* 1008 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Usage: /loot catacombs <f1/f2/f3/f4/f5/f6/f7>"));
/*      */         return;
/*      */     } 
/*      */     
/* 1012 */     player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Usage: " + func_71518_a(arg0)));
/*      */   }
/*      */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\me\Danker\commands\LootCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */