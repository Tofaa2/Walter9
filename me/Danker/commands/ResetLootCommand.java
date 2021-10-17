/*     */ package me.Danker.commands;
/*     */ 
/*     */ import java.util.List;
/*     */ import me.Danker.DankersSkyblockMod;
/*     */ import me.Danker.handlers.ConfigHandler;
/*     */ import net.minecraft.command.CommandBase;
/*     */ import net.minecraft.command.CommandException;
/*     */ import net.minecraft.command.ICommandSender;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ 
/*     */ public class ResetLootCommand
/*     */   extends CommandBase
/*     */ {
/*     */   static String resetOption;
/*     */   static boolean confirmReset = false;
/*     */   
/*     */   public String func_71517_b() {
/*  22 */     return "resetloot";
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_71518_a(ICommandSender arg0) {
/*  27 */     return "/" + func_71517_b() + "<zombie/spider/wolf/fishing/catacombs/confirm/cancel>";
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_82362_a() {
/*  32 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> func_180525_a(ICommandSender sender, String[] args, BlockPos pos) {
/*  37 */     if (args.length != 1) return null;
/*     */     
/*  39 */     if (confirmReset) {
/*  40 */       return func_71530_a(args, new String[] { "confirm", "cancel" });
/*     */     }
/*  42 */     return func_71530_a(args, new String[] { "zombie", "spider", "wolf", "fishing", "mythological", "catacombs" });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_71515_b(ICommandSender arg0, String[] arg1) throws CommandException {
/*  48 */     EntityPlayer player = (EntityPlayer)arg0;
/*     */     
/*  50 */     if (arg1.length == 0) {
/*  51 */       player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Usage: /resetloot <zombie/spider/wolf/fishing/mythological/catacombs>"));
/*     */       
/*     */       return;
/*     */     } 
/*  55 */     if (confirmReset) {
/*  56 */       switch (arg1[0].toLowerCase()) {
/*     */         case "confirm":
/*  58 */           confirmReset = false;
/*  59 */           player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Resetting " + resetOption + " tracker..."));
/*  60 */           switch (resetOption.toLowerCase()) {
/*     */             case "zombie":
/*  62 */               resetZombie();
/*     */               break;
/*     */             case "spider":
/*  65 */               resetSpider();
/*     */               break;
/*     */             case "wolf":
/*  68 */               resetWolf();
/*     */               break;
/*     */             case "fishing":
/*  71 */               resetFishing();
/*     */               break;
/*     */             case "mythological":
/*  74 */               resetMythological();
/*     */             case "catacombs":
/*  76 */               resetCatacombs();
/*     */             default:
/*  78 */               System.err.println("Resetting unknown tracker.");
/*     */               return;
/*     */           } 
/*  81 */           player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Reset complete."));
/*     */           return;
/*     */         case "cancel":
/*  84 */           confirmReset = false;
/*  85 */           player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Reset cancelled."));
/*     */           return;
/*     */       } 
/*  88 */       player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "Please confirm the reset of the " + resetOption + " tracker by using /resetloot confirm." + EnumChatFormatting.RED + " Cancel by using /resetloot cancel."));
/*     */     }
/*     */     else {
/*     */       
/*  92 */       switch (arg1[0].toLowerCase()) {
/*     */         case "zombie":
/*     */         case "spider":
/*     */         case "wolf":
/*     */         case "fishing":
/*     */         case "mythological":
/*     */         case "catacombs":
/*  99 */           resetOption = arg1[0];
/* 100 */           player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.YELLOW + "Are you sure you want to reset the " + resetOption + " tracker? Confirm with " + DankersSkyblockMod.MAIN_COLOUR + "/resetloot confirm" + EnumChatFormatting.YELLOW + ". Cancel by using " + DankersSkyblockMod.MAIN_COLOUR + "/resetloot cancel" + EnumChatFormatting.YELLOW + "."));
/*     */ 
/*     */           
/* 103 */           confirmReset = true;
/*     */           return;
/*     */         case "confirm":
/*     */         case "cancel":
/* 107 */           player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Pick something to reset first."));
/*     */           return;
/*     */       } 
/* 110 */       player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Usage: " + func_71518_a(arg0)));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   static void resetZombie() {
/* 116 */     LootCommand.zombieRevsSession = 0;
/* 117 */     LootCommand.zombieRevFleshSession = 0;
/* 118 */     LootCommand.zombieFoulFleshSession = 0;
/* 119 */     LootCommand.zombieFoulFleshDropsSession = 0;
/* 120 */     LootCommand.zombiePestilencesSession = 0;
/* 121 */     LootCommand.zombieUndeadCatasSession = 0;
/* 122 */     LootCommand.zombieBooksSession = 0;
/* 123 */     LootCommand.zombieBeheadedsSession = 0;
/* 124 */     LootCommand.zombieRevCatasSession = 0;
/* 125 */     LootCommand.zombieSnakesSession = 0;
/* 126 */     LootCommand.zombieScythesSession = 0;
/* 127 */     LootCommand.zombieTimeSession = -1.0D;
/* 128 */     LootCommand.zombieBossesSession = -1;
/* 129 */     ConfigHandler.deleteCategory("zombie");
/* 130 */     ConfigHandler.reloadConfig();
/*     */   }
/*     */   
/*     */   static void resetSpider() {
/* 134 */     LootCommand.spiderTarantulasSession = 0;
/* 135 */     LootCommand.spiderWebsSession = 0;
/* 136 */     LootCommand.spiderTAPSession = 0;
/* 137 */     LootCommand.spiderTAPDropsSession = 0;
/* 138 */     LootCommand.spiderBitesSession = 0;
/* 139 */     LootCommand.spiderCatalystsSession = 0;
/* 140 */     LootCommand.spiderBooksSession = 0;
/* 141 */     LootCommand.spiderSwattersSession = 0;
/* 142 */     LootCommand.spiderTalismansSession = 0;
/* 143 */     LootCommand.spiderMosquitosSession = 0;
/* 144 */     LootCommand.spiderTimeSession = -1.0D;
/* 145 */     LootCommand.spiderBossesSession = -1;
/* 146 */     ConfigHandler.deleteCategory("spider");
/* 147 */     ConfigHandler.reloadConfig();
/*     */   }
/*     */   
/*     */   static void resetWolf() {
/* 151 */     LootCommand.wolfSvensSession = 0;
/* 152 */     LootCommand.wolfTeethSession = 0;
/* 153 */     LootCommand.wolfWheelsSession = 0;
/* 154 */     LootCommand.wolfWheelsDropsSession = 0;
/* 155 */     LootCommand.wolfSpiritsSession = 0;
/* 156 */     LootCommand.wolfBooksSession = 0;
/* 157 */     LootCommand.wolfEggsSession = 0;
/* 158 */     LootCommand.wolfCouturesSession = 0;
/* 159 */     LootCommand.wolfBaitsSession = 0;
/* 160 */     LootCommand.wolfFluxesSession = 0;
/* 161 */     LootCommand.wolfTimeSession = -1.0D;
/* 162 */     LootCommand.wolfBossesSession = -1;
/* 163 */     ConfigHandler.deleteCategory("wolf");
/* 164 */     ConfigHandler.reloadConfig();
/*     */   }
/*     */   
/*     */   static void resetFishing() {
/* 168 */     LootCommand.seaCreaturesSession = 0;
/* 169 */     LootCommand.goodCatchesSession = 0;
/* 170 */     LootCommand.greatCatchesSession = 0;
/* 171 */     LootCommand.squidsSession = 0;
/* 172 */     LootCommand.seaWalkersSession = 0;
/* 173 */     LootCommand.nightSquidsSession = 0;
/* 174 */     LootCommand.seaGuardiansSession = 0;
/* 175 */     LootCommand.seaWitchesSession = 0;
/* 176 */     LootCommand.seaArchersSession = 0;
/* 177 */     LootCommand.monsterOfTheDeepsSession = 0;
/* 178 */     LootCommand.catfishesSession = 0;
/* 179 */     LootCommand.carrotKingsSession = 0;
/* 180 */     LootCommand.seaLeechesSession = 0;
/* 181 */     LootCommand.guardianDefendersSession = 0;
/* 182 */     LootCommand.deepSeaProtectorsSession = 0;
/* 183 */     LootCommand.hydrasSession = 0;
/* 184 */     LootCommand.seaEmperorsSession = 0;
/* 185 */     LootCommand.empTimeSession = -1.0D;
/* 186 */     LootCommand.empSCsSession = -1;
/* 187 */     LootCommand.fishingMilestoneSession = 0;
/* 188 */     LootCommand.frozenStevesSession = 0;
/* 189 */     LootCommand.frostyTheSnowmansSession = 0;
/* 190 */     LootCommand.grinchesSession = 0;
/* 191 */     LootCommand.yetisSession = 0;
/* 192 */     LootCommand.yetiTimeSession = -1.0D;
/* 193 */     LootCommand.yetiSCsSession = -1;
/* 194 */     LootCommand.nurseSharksSession = 0;
/* 195 */     LootCommand.blueSharksSession = 0;
/* 196 */     LootCommand.tigerSharksSession = 0;
/* 197 */     LootCommand.greatWhiteSharksSession = 0;
/* 198 */     LootCommand.scarecrowsSession = 0;
/* 199 */     LootCommand.nightmaresSession = 0;
/* 200 */     LootCommand.werewolfsSession = 0;
/* 201 */     LootCommand.phantomFishersSession = 0;
/* 202 */     LootCommand.grimReapersSession = 0;
/* 203 */     ConfigHandler.deleteCategory("fishing");
/* 204 */     ConfigHandler.reloadConfig();
/*     */   }
/*     */   
/*     */   static void resetMythological() {
/* 208 */     LootCommand.mythCoinsSession = 0.0D;
/* 209 */     LootCommand.griffinFeathersSession = 0;
/* 210 */     LootCommand.crownOfGreedsSession = 0;
/* 211 */     LootCommand.washedUpSouvenirsSession = 0;
/* 212 */     LootCommand.minosHuntersSession = 0;
/* 213 */     LootCommand.siameseLynxesSession = 0;
/* 214 */     LootCommand.minotaursSession = 0;
/* 215 */     LootCommand.gaiaConstructsSession = 0;
/* 216 */     LootCommand.minosChampionsSession = 0;
/* 217 */     LootCommand.minosInquisitorsSession = 0;
/* 218 */     ConfigHandler.deleteCategory("mythological");
/* 219 */     ConfigHandler.reloadConfig();
/*     */   }
/*     */   
/*     */   static void resetCatacombs() {
/* 223 */     LootCommand.recombobulatorsSession = 0;
/* 224 */     LootCommand.fumingPotatoBooksSession = 0;
/* 225 */     LootCommand.bonzoStaffsSession = 0;
/* 226 */     LootCommand.f1CoinsSpentSession = 0.0D;
/* 227 */     LootCommand.f1TimeSpentSession = 0.0D;
/* 228 */     LootCommand.scarfStudiesSession = 0;
/* 229 */     LootCommand.f2CoinsSpentSession = 0.0D;
/* 230 */     LootCommand.f2TimeSpentSession = 0.0D;
/* 231 */     LootCommand.adaptiveHelmsSession = 0;
/* 232 */     LootCommand.adaptiveChestsSession = 0;
/* 233 */     LootCommand.adaptiveLegsSession = 0;
/* 234 */     LootCommand.adaptiveBootsSession = 0;
/* 235 */     LootCommand.adaptiveSwordsSession = 0;
/* 236 */     LootCommand.f3CoinsSpentSession = 0.0D;
/* 237 */     LootCommand.f3TimeSpentSession = 0.0D;
/* 238 */     LootCommand.spiritWingsSession = 0;
/* 239 */     LootCommand.spiritBonesSession = 0;
/* 240 */     LootCommand.spiritBootsSession = 0;
/* 241 */     LootCommand.spiritSwordsSession = 0;
/* 242 */     LootCommand.epicSpiritPetsSession = 0;
/* 243 */     LootCommand.f4CoinsSpentSession = 0.0D;
/* 244 */     LootCommand.f4TimeSpentSession = 0.0D;
/* 245 */     LootCommand.warpedStonesSession = 0;
/* 246 */     LootCommand.shadowAssHelmsSession = 0;
/* 247 */     LootCommand.shadowAssChestsSession = 0;
/* 248 */     LootCommand.shadowAssLegsSession = 0;
/* 249 */     LootCommand.shadowAssBootsSession = 0;
/* 250 */     LootCommand.lividDaggersSession = 0;
/* 251 */     LootCommand.shadowFurysSession = 0;
/* 252 */     LootCommand.f5CoinsSpentSession = 0.0D;
/* 253 */     LootCommand.f5TimeSpentSession = 0.0D;
/* 254 */     LootCommand.ancientRosesSession = 0;
/* 255 */     LootCommand.precursorEyesSession = 0;
/* 256 */     LootCommand.giantsSwordsSession = 0;
/* 257 */     LootCommand.necroLordHelmsSession = 0;
/* 258 */     LootCommand.necroLordChestsSession = 0;
/* 259 */     LootCommand.necroLordLegsSession = 0;
/* 260 */     LootCommand.necroLordBootsSession = 0;
/* 261 */     LootCommand.necroSwordsSession = 0;
/* 262 */     LootCommand.f6CoinsSpentSession = 0.0D;
/* 263 */     LootCommand.f6TimeSpentSession = 0.0D;
/* 264 */     LootCommand.witherBloodsSession = 0;
/* 265 */     LootCommand.witherCloaksSession = 0;
/* 266 */     LootCommand.implosionsSession = 0;
/* 267 */     LootCommand.witherShieldsSession = 0;
/* 268 */     LootCommand.shadowWarpsSession = 0;
/* 269 */     LootCommand.necronsHandlesSession = 0;
/* 270 */     LootCommand.autoRecombsSession = 0;
/* 271 */     LootCommand.witherHelmsSession = 0;
/* 272 */     LootCommand.witherChestsSession = 0;
/* 273 */     LootCommand.witherLegsSession = 0;
/* 274 */     LootCommand.witherBootsSession = 0;
/* 275 */     LootCommand.f7CoinsSpentSession = 0.0D;
/* 276 */     LootCommand.f7TimeSpentSession = 0.0D;
/* 277 */     ConfigHandler.deleteCategory("catacombs");
/* 278 */     ConfigHandler.reloadConfig();
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\me\Danker\commands\ResetLootCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */