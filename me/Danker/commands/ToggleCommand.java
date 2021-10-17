/*     */ package me.Danker.commands;
/*     */ 
/*     */ import java.util.List;
/*     */ import me.Danker.DankersSkyblockMod;
/*     */ import me.Danker.handlers.ConfigHandler;
/*     */ import net.minecraft.command.CommandBase;
/*     */ import net.minecraft.command.CommandException;
/*     */ import net.minecraft.command.ICommand;
/*     */ import net.minecraft.command.ICommandSender;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ToggleCommand
/*     */   extends CommandBase
/*     */   implements ICommand
/*     */ {
/*     */   public static boolean gpartyToggled;
/*     */   public static boolean coordsToggled;
/*     */   public static boolean goldenToggled;
/*     */   public static boolean slayerCountTotal;
/*     */   public static boolean rngesusAlerts;
/*     */   public static boolean splitFishing;
/*     */   public static boolean chatMaddoxToggled;
/*     */   public static boolean spiritBearAlerts;
/*     */   public static boolean aotdToggled;
/*     */   public static boolean lividDaggerToggled;
/*     */   public static boolean petColoursToggled;
/*     */   public static boolean dungeonTimerToggled;
/*     */   public static boolean golemAlertToggled;
/*     */   public static boolean expertiseLoreToggled;
/*     */   public static boolean skill50DisplayToggled;
/*     */   public static boolean outlineTextToggled;
/*     */   public static boolean cakeTimerToggled;
/*     */   public static boolean sceptreMessages;
/*     */   public static boolean midasStaffMessages;
/*     */   public static boolean implosionMessages;
/*     */   public static boolean healMessages;
/*     */   public static boolean cooldownMessages;
/*     */   public static boolean manaMessages;
/*     */   public static boolean lowHealthNotifyToggled;
/*     */   public static boolean lividSolverToggled;
/*     */   public static boolean stopSalvageStarredToggled;
/*     */   public static boolean watcherReadyToggled;
/*     */   public static boolean swapToPickBlockToggled;
/*     */   public static boolean flowerWeaponsToggled;
/*     */   public static boolean notifySlayerSlainToggled;
/*     */   public static boolean necronNotificationsToggled;
/*     */   public static boolean bonzoTimerToggled;
/*     */   public static boolean blockBreakingFarmsToggled;
/*     */   public static boolean autoSkillTrackerToggled;
/*     */   public static boolean threeManToggled;
/*     */   public static boolean oruoToggled;
/*     */   public static boolean blazeToggled;
/*     */   public static boolean creeperToggled;
/*     */   public static boolean waterToggled;
/*     */   public static boolean ticTacToeToggled;
/*     */   public static boolean startsWithToggled;
/*     */   public static boolean selectAllToggled;
/*     */   public static boolean clickInOrderToggled;
/*     */   public static boolean blockWrongTerminalClicksToggled;
/*     */   public static boolean itemFrameOnSeaLanternsToggled;
/*     */   public static boolean ultrasequencerToggled;
/*     */   public static boolean chronomatronToggled;
/*     */   public static boolean superpairsToggled;
/*     */   public static boolean hideTooltipsInExperimentAddonsToggled;
/*     */   
/*     */   public String func_71517_b() {
/*  73 */     return "toggle";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String func_71518_a(ICommandSender arg0) {
/*  79 */     return "/" + func_71517_b() + " <gparty/coords/golden/slayercount/rngesusalerts/splitfishing/chatmaddox/spiritbearalert/aotd/lividdagger/flowerweapons/sceptremessages/petcolors/dungeontimer/golemalerts/expertiselore/skill50display/outlinetext/midasstaffmessages/implosionmessages/healmessages/cooldownmessages/manamessages/caketimer/lowhealthnotify/lividsolver/stopsalvagestarred/notifyslayerslain/necronnotifications/bonzotimer/blockbreakingfarms/threemanpuzzle/oruopuzzle/blazepuzzle/creeperpuzzle/waterpuzzle/tictactoepuzzle/watchermessage/startswithterminal/selectallterminal/clickinorderterminal/blockwrongterminalclicks/itemframeonsealanterns/ultrasequencer/chronomatron/superpairs/hidetooltipsinaddons/pickblock/list>";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_82362_a() {
/*  89 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> func_180525_a(ICommandSender sender, String[] args, BlockPos pos) {
/*  94 */     if (args.length == 1) {
/*  95 */       return func_71530_a(args, new String[] { "gparty", "coords", "golden", "slayercount", "rngesusalerts", "splitfishing", "chatmaddox", "spiritbearalerts", "aotd", "lividdagger", "flowerweapons", "sceptremessages", "petcolors", "dungeontimer", "golemalerts", "expertiselore", "skill50display", "outlinetext", "midasstaffmessages", "implosionmessages", "healmessages", "cooldownmessages", "manamessages", "caketimer", "lowhealthnotify", "autoskilltracker", "lividsolver", "stopsalvagestarred", "notifyslayerslain", "necronnotifications", "bonzotimer", "blockbreakingfarms", "threemanpuzzle", "oruopuzzle", "blazepuzzle", "creeperpuzzle", "waterpuzzle", "tictactoepuzzle", "watchermessage", "startswithterminal", "selectallterminal", "clickinorderterminal", "blockwrongterminalclicks", "itemframeonsealanterns", "ultrasequencer", "chronomatron", "superpairs", "hidetooltipsinaddons", "pickblock", "list" });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 106 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_71515_b(ICommandSender arg0, String[] arg1) throws CommandException {
/* 111 */     EntityPlayer player = (EntityPlayer)arg0;
/*     */     
/* 113 */     if (arg1.length == 0) {
/* 114 */       player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Usage: " + func_71518_a(arg0)));
/*     */       
/*     */       return;
/*     */     } 
/* 118 */     switch (arg1[0].toLowerCase()) {
/*     */       case "gparty":
/* 120 */         gpartyToggled = !gpartyToggled;
/* 121 */         ConfigHandler.writeBooleanConfig("toggles", "GParty", gpartyToggled);
/* 122 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Guild party notifications has been set to " + DankersSkyblockMod.SECONDARY_COLOUR + gpartyToggled + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "coords":
/* 125 */         coordsToggled = !coordsToggled;
/* 126 */         ConfigHandler.writeBooleanConfig("toggles", "Coords", coordsToggled);
/* 127 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Coord/Angle display has been set to " + DankersSkyblockMod.SECONDARY_COLOUR + coordsToggled + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "golden":
/* 130 */         goldenToggled = !goldenToggled;
/* 131 */         ConfigHandler.writeBooleanConfig("toggles", "Golden", goldenToggled);
/* 132 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Golden T6 enchants has been set to " + DankersSkyblockMod.SECONDARY_COLOUR + goldenToggled + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "slayercount":
/* 135 */         slayerCountTotal = !slayerCountTotal;
/* 136 */         ConfigHandler.writeBooleanConfig("toggles", "SlayerCount", slayerCountTotal);
/* 137 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Counting total 20% slayer drops has been set to " + DankersSkyblockMod.SECONDARY_COLOUR + slayerCountTotal + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "rngesusalerts":
/* 140 */         rngesusAlerts = !rngesusAlerts;
/* 141 */         ConfigHandler.writeBooleanConfig("toggles", "RNGesusAlerts", rngesusAlerts);
/* 142 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Slayer RNGesus alerts has been set to " + DankersSkyblockMod.SECONDARY_COLOUR + rngesusAlerts + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "splitfishing":
/* 145 */         splitFishing = !splitFishing;
/* 146 */         ConfigHandler.writeBooleanConfig("toggles", "SplitFishing", splitFishing);
/* 147 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Split fishing display has been set to " + DankersSkyblockMod.SECONDARY_COLOUR + splitFishing + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "chatmaddox":
/* 150 */         chatMaddoxToggled = !chatMaddoxToggled;
/* 151 */         ConfigHandler.writeBooleanConfig("toggles", "ChatMaddox", chatMaddoxToggled);
/* 152 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Click screen to open Maddox menu has been set to " + DankersSkyblockMod.SECONDARY_COLOUR + chatMaddoxToggled + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "spiritbearalerts":
/* 155 */         spiritBearAlerts = !spiritBearAlerts;
/* 156 */         ConfigHandler.writeBooleanConfig("toggles", "SpiritBearAlerts", spiritBearAlerts);
/* 157 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Spirit Bear alerts have been set to " + DankersSkyblockMod.SECONDARY_COLOUR + spiritBearAlerts + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "aotd":
/* 160 */         aotdToggled = !aotdToggled;
/* 161 */         ConfigHandler.writeBooleanConfig("toggles", "AOTD", aotdToggled);
/* 162 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Block AOTD ability been set to " + DankersSkyblockMod.SECONDARY_COLOUR + aotdToggled + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "lividdagger":
/* 165 */         lividDaggerToggled = !lividDaggerToggled;
/* 166 */         ConfigHandler.writeBooleanConfig("toggles", "LividDagger", lividDaggerToggled);
/* 167 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Block Livid Dagger ability been set to " + DankersSkyblockMod.SECONDARY_COLOUR + lividDaggerToggled + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "flowerweapons":
/* 170 */         flowerWeaponsToggled = !flowerWeaponsToggled;
/* 171 */         ConfigHandler.writeBooleanConfig("toggles", "FlowerWeapons", flowerWeaponsToggled);
/* 172 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Prevent Placing FoT/Spirit Sceptre been set to " + DankersSkyblockMod.SECONDARY_COLOUR + flowerWeaponsToggled + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "sceptremessages":
/* 175 */         sceptreMessages = !sceptreMessages;
/* 176 */         ConfigHandler.writeBooleanConfig("toggles", "SceptreMessages", sceptreMessages);
/* 177 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Spirit Sceptre messages have been set to " + DankersSkyblockMod.SECONDARY_COLOUR + sceptreMessages + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "midasstaffmessages":
/* 180 */         midasStaffMessages = !midasStaffMessages;
/* 181 */         ConfigHandler.writeBooleanConfig("toggles", "MidasStaffMessages", midasStaffMessages);
/* 182 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Midas Staff messages have been set to " + DankersSkyblockMod.SECONDARY_COLOUR + midasStaffMessages + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "implosionmessages":
/* 185 */         implosionMessages = !implosionMessages;
/* 186 */         ConfigHandler.writeBooleanConfig("toggles", "ImplosionMessages", implosionMessages);
/* 187 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Implosion messages have been set to " + DankersSkyblockMod.SECONDARY_COLOUR + implosionMessages + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "healmessages":
/* 190 */         healMessages = !healMessages;
/* 191 */         ConfigHandler.writeBooleanConfig("toggles", "HealMessages", healMessages);
/* 192 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Heal messages have been set to " + DankersSkyblockMod.SECONDARY_COLOUR + healMessages + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "cooldownmessages":
/* 195 */         cooldownMessages = !cooldownMessages;
/* 196 */         ConfigHandler.writeBooleanConfig("toggles", "CooldownMessages", cooldownMessages);
/* 197 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Ability cooldown messages has been set to " + DankersSkyblockMod.SECONDARY_COLOUR + cooldownMessages + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "manamessages":
/* 200 */         manaMessages = !manaMessages;
/* 201 */         ConfigHandler.writeBooleanConfig("toggles", "ManaMessages", manaMessages);
/* 202 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Out of mana messages has been set to " + DankersSkyblockMod.SECONDARY_COLOUR + manaMessages + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "petcolors":
/*     */       case "petcolours":
/* 206 */         petColoursToggled = !petColoursToggled;
/* 207 */         ConfigHandler.writeBooleanConfig("toggles", "PetColors", petColoursToggled);
/* 208 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Pet colours have been set to " + DankersSkyblockMod.SECONDARY_COLOUR + petColoursToggled + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "dungeontimer":
/* 211 */         dungeonTimerToggled = !dungeonTimerToggled;
/* 212 */         ConfigHandler.writeBooleanConfig("toggles", "DungeonTimer", dungeonTimerToggled);
/* 213 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Dungeon timer has been set to " + DankersSkyblockMod.SECONDARY_COLOUR + dungeonTimerToggled + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "golemalerts":
/* 216 */         golemAlertToggled = !golemAlertToggled;
/* 217 */         ConfigHandler.writeBooleanConfig("toggles", "GolemAlerts", golemAlertToggled);
/* 218 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Golem spawn alerts has been set to " + DankersSkyblockMod.SECONDARY_COLOUR + golemAlertToggled + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "expertiselore":
/* 221 */         expertiseLoreToggled = !expertiseLoreToggled;
/* 222 */         ConfigHandler.writeBooleanConfig("toggles", "ExpertiseLore", expertiseLoreToggled);
/* 223 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Expertise in lore has been set to " + DankersSkyblockMod.SECONDARY_COLOUR + expertiseLoreToggled + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "skill50display":
/* 226 */         skill50DisplayToggled = !skill50DisplayToggled;
/* 227 */         ConfigHandler.writeBooleanConfig("toggles", "Skill50Display", skill50DisplayToggled);
/* 228 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Skill 50 display has been set to " + DankersSkyblockMod.SECONDARY_COLOUR + skill50DisplayToggled + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "outlinetext":
/* 231 */         outlineTextToggled = !outlineTextToggled;
/* 232 */         ConfigHandler.writeBooleanConfig("toggles", "OutlineText", outlineTextToggled);
/* 233 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Outline displayed text has been set to " + DankersSkyblockMod.SECONDARY_COLOUR + outlineTextToggled + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "caketimer":
/* 236 */         cakeTimerToggled = !cakeTimerToggled;
/* 237 */         ConfigHandler.writeBooleanConfig("toggles", "CakeTimer", cakeTimerToggled);
/* 238 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Cake timer has been set to " + DankersSkyblockMod.SECONDARY_COLOUR + cakeTimerToggled + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "lowhealthnotify":
/* 241 */         lowHealthNotifyToggled = !lowHealthNotifyToggled;
/* 242 */         ConfigHandler.writeBooleanConfig("toggles", "LowHealthNotify", lowHealthNotifyToggled);
/* 243 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Low health notify has been set to " + DankersSkyblockMod.SECONDARY_COLOUR + lowHealthNotifyToggled + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "lividsolver":
/* 246 */         lividSolverToggled = !lividSolverToggled;
/* 247 */         ConfigHandler.writeBooleanConfig("toggles", "LividSolver", lividSolverToggled);
/* 248 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Livid solver has been set to " + DankersSkyblockMod.SECONDARY_COLOUR + lividSolverToggled + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "stopsalvagestarred":
/* 251 */         stopSalvageStarredToggled = !stopSalvageStarredToggled;
/* 252 */         ConfigHandler.writeBooleanConfig("toggles", "StopSalvageStarred", stopSalvageStarredToggled);
/* 253 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Stop salvaging starred items has been set to " + DankersSkyblockMod.SECONDARY_COLOUR + stopSalvageStarredToggled + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "notifyslayerslain":
/* 256 */         notifySlayerSlainToggled = !notifySlayerSlainToggled;
/* 257 */         ConfigHandler.writeBooleanConfig("toggles", "NotifySlayerSlain", notifySlayerSlainToggled);
/* 258 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Notify when slayer slain has been set to " + DankersSkyblockMod.SECONDARY_COLOUR + notifySlayerSlainToggled + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "necronnotifications":
/* 261 */         necronNotificationsToggled = !necronNotificationsToggled;
/* 262 */         ConfigHandler.writeBooleanConfig("toggles", "NecronNotifications", necronNotificationsToggled);
/* 263 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Necron phase notifications has been set to " + DankersSkyblockMod.SECONDARY_COLOUR + necronNotificationsToggled + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "bonzotimer":
/* 266 */         bonzoTimerToggled = !bonzoTimerToggled;
/* 267 */         ConfigHandler.writeBooleanConfig("toggles", "BonzoTimer", bonzoTimerToggled);
/* 268 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Bonzo's Mask timer has been set to " + DankersSkyblockMod.SECONDARY_COLOUR + necronNotificationsToggled + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "blockbreakingfarms":
/* 271 */         blockBreakingFarmsToggled = !blockBreakingFarmsToggled;
/* 272 */         ConfigHandler.writeBooleanConfig("toggles", "BlockBreakingFarms", blockBreakingFarmsToggled);
/* 273 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Prevent breaking farms has been set to " + DankersSkyblockMod.SECONDARY_COLOUR + necronNotificationsToggled + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */       case "autoskilltracker":
/* 275 */         autoSkillTrackerToggled = !autoSkillTrackerToggled;
/* 276 */         ConfigHandler.writeBooleanConfig("toggles", "AutoSkillTracker", autoSkillTrackerToggled);
/* 277 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Auto start/stop skill tracker has been set to " + DankersSkyblockMod.SECONDARY_COLOUR + autoSkillTrackerToggled + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "threemanpuzzle":
/* 280 */         threeManToggled = !threeManToggled;
/* 281 */         ConfigHandler.writeBooleanConfig("toggles", "ThreeManPuzzle", threeManToggled);
/* 282 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Three man puzzle solver has been set to " + DankersSkyblockMod.SECONDARY_COLOUR + threeManToggled + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "oruopuzzle":
/* 285 */         oruoToggled = !oruoToggled;
/* 286 */         ConfigHandler.writeBooleanConfig("toggles", "OruoPuzzle", oruoToggled);
/* 287 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Oruo trivia solver has been set to " + DankersSkyblockMod.SECONDARY_COLOUR + oruoToggled + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "blazepuzzle":
/* 290 */         blazeToggled = !blazeToggled;
/* 291 */         ConfigHandler.writeBooleanConfig("toggles", "BlazePuzzle", blazeToggled);
/* 292 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Blaze puzzle solver has been set to " + DankersSkyblockMod.SECONDARY_COLOUR + blazeToggled + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "creeperpuzzle":
/* 295 */         creeperToggled = !creeperToggled;
/* 296 */         ConfigHandler.writeBooleanConfig("toggles", "CreeperPuzzle", creeperToggled);
/* 297 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Creeper puzzle solver has been set to " + DankersSkyblockMod.SECONDARY_COLOUR + creeperToggled + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "waterpuzzle":
/* 300 */         waterToggled = !waterToggled;
/* 301 */         ConfigHandler.writeBooleanConfig("toggles", "WaterPuzzle", waterToggled);
/* 302 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Water puzzle solver has been set to " + DankersSkyblockMod.SECONDARY_COLOUR + waterToggled + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "tictactoepuzzle":
/* 305 */         ticTacToeToggled = !ticTacToeToggled;
/* 306 */         ConfigHandler.writeBooleanConfig("toggles", "TicTacToePuzzle", ticTacToeToggled);
/* 307 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Tic tac toe puzzle solver has been set to " + DankersSkyblockMod.SECONDARY_COLOUR + ticTacToeToggled + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "watchermessage":
/* 310 */         watcherReadyToggled = !watcherReadyToggled;
/* 311 */         ConfigHandler.writeBooleanConfig("toggles", "WatcherReadyMessage", watcherReadyToggled);
/* 312 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Display Watcher ready message has been set to " + DankersSkyblockMod.SECONDARY_COLOUR + watcherReadyToggled + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "startswithterminal":
/* 315 */         startsWithToggled = !startsWithToggled;
/* 316 */         ConfigHandler.writeBooleanConfig("toggles", "StartsWithTerminal", startsWithToggled);
/* 317 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Starts with letter terminal solver has been set to " + DankersSkyblockMod.SECONDARY_COLOUR + startsWithToggled + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "selectallterminal":
/* 320 */         selectAllToggled = !selectAllToggled;
/* 321 */         ConfigHandler.writeBooleanConfig("toggles", "SelectAllTerminal", selectAllToggled);
/* 322 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Select all color items terminal solver has been set to " + DankersSkyblockMod.SECONDARY_COLOUR + selectAllToggled + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "clickinorderterminal":
/* 325 */         clickInOrderToggled = !clickInOrderToggled;
/* 326 */         ConfigHandler.writeBooleanConfig("toggles", "ClickInOrderTerminal", clickInOrderToggled);
/* 327 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Click in order terminal helper has been set to " + DankersSkyblockMod.SECONDARY_COLOUR + selectAllToggled + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "blockwrongterminalclicks":
/* 330 */         blockWrongTerminalClicksToggled = !blockWrongTerminalClicksToggled;
/* 331 */         ConfigHandler.writeBooleanConfig("toggles", "BlockWrongTerminalClicks", blockWrongTerminalClicksToggled);
/* 332 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Block wrong clicks on terminals has been set to " + DankersSkyblockMod.SECONDARY_COLOUR + blockWrongTerminalClicksToggled + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "itemframeonsealanterns":
/* 335 */         itemFrameOnSeaLanternsToggled = !itemFrameOnSeaLanternsToggled;
/* 336 */         ConfigHandler.writeBooleanConfig("toggles", "IgnoreItemFrameOnSeaLanterns", itemFrameOnSeaLanternsToggled);
/* 337 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Ignore item frames on sea lanterns has been set to " + DankersSkyblockMod.SECONDARY_COLOUR + itemFrameOnSeaLanternsToggled + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "ultrasequencer":
/* 340 */         ultrasequencerToggled = !ultrasequencerToggled;
/* 341 */         ConfigHandler.writeBooleanConfig("toggles", "UltraSequencer", ultrasequencerToggled);
/* 342 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Ultrasequencer solver has been set to " + DankersSkyblockMod.SECONDARY_COLOUR + ultrasequencerToggled + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "chronomatron":
/* 345 */         chronomatronToggled = !chronomatronToggled;
/* 346 */         ConfigHandler.writeBooleanConfig("toggles", "Chronomatron", chronomatronToggled);
/* 347 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Chronomatron solver has been set to " + DankersSkyblockMod.SECONDARY_COLOUR + chronomatronToggled + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "superpairs":
/* 350 */         superpairsToggled = !superpairsToggled;
/* 351 */         ConfigHandler.writeBooleanConfig("toggles", "Superpairs", superpairsToggled);
/* 352 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Superpairs solver has been set to " + DankersSkyblockMod.SECONDARY_COLOUR + superpairsToggled + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "hidetooltipsinaddons":
/* 355 */         hideTooltipsInExperimentAddonsToggled = !hideTooltipsInExperimentAddonsToggled;
/* 356 */         ConfigHandler.writeBooleanConfig("toggles", "HideTooltipsInExperimentAddons", hideTooltipsInExperimentAddonsToggled);
/* 357 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Hide tooltips in ultrasequencer and chronomatron has been set to " + DankersSkyblockMod.SECONDARY_COLOUR + hideTooltipsInExperimentAddonsToggled + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "pickblock":
/* 360 */         swapToPickBlockToggled = !swapToPickBlockToggled;
/* 361 */         ConfigHandler.writeBooleanConfig("toggles", "PickBlock", swapToPickBlockToggled);
/* 362 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Auto-swap to pick block has been set to " + DankersSkyblockMod.SECONDARY_COLOUR + swapToPickBlockToggled + DankersSkyblockMod.MAIN_COLOUR + "."));
/*     */         return;
/*     */       case "list":
/* 365 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.TYPE_COLOUR + "Guild party notifications: " + DankersSkyblockMod.VALUE_COLOUR + gpartyToggled + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Coord/Angle display: " + DankersSkyblockMod.VALUE_COLOUR + coordsToggled + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Golden T6 enchants: " + DankersSkyblockMod.VALUE_COLOUR + goldenToggled + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Counting total 20% slayer drops: " + DankersSkyblockMod.VALUE_COLOUR + slayerCountTotal + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Slayer RNGesus alerts: " + DankersSkyblockMod.VALUE_COLOUR + rngesusAlerts + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Split fishing display: " + DankersSkyblockMod.VALUE_COLOUR + splitFishing + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Chat Maddox menu: " + DankersSkyblockMod.VALUE_COLOUR + chatMaddoxToggled + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Spirit Bear alerts: " + DankersSkyblockMod.VALUE_COLOUR + spiritBearAlerts + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Block AOTD ability: " + DankersSkyblockMod.VALUE_COLOUR + aotdToggled + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Block Livid Dagger ability: " + DankersSkyblockMod.VALUE_COLOUR + lividDaggerToggled + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Prevent Placing FoT/Spirit Sceptre: " + DankersSkyblockMod.VALUE_COLOUR + flowerWeaponsToggled + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Spirit Sceptre messages: " + DankersSkyblockMod.VALUE_COLOUR + sceptreMessages + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Midas Staff messages: " + DankersSkyblockMod.VALUE_COLOUR + midasStaffMessages + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Implosion messages: " + DankersSkyblockMod.VALUE_COLOUR + implosionMessages + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Heal messages: " + DankersSkyblockMod.VALUE_COLOUR + healMessages + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Ability cooldown messages: " + DankersSkyblockMod.VALUE_COLOUR + cooldownMessages + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Out of mana messages: " + DankersSkyblockMod.VALUE_COLOUR + manaMessages + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Pet colours: " + DankersSkyblockMod.VALUE_COLOUR + petColoursToggled + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Dungeon timer: " + DankersSkyblockMod.VALUE_COLOUR + dungeonTimerToggled + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Golem spawn alerts: " + DankersSkyblockMod.VALUE_COLOUR + golemAlertToggled + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Expertise in lore: " + DankersSkyblockMod.VALUE_COLOUR + expertiseLoreToggled + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Skill 50 display: " + DankersSkyblockMod.VALUE_COLOUR + skill50DisplayToggled + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Outline displayed text: " + DankersSkyblockMod.VALUE_COLOUR + outlineTextToggled + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Cake timer: " + DankersSkyblockMod.VALUE_COLOUR + cakeTimerToggled + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Low health notify: " + DankersSkyblockMod.VALUE_COLOUR + lowHealthNotifyToggled + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Auto start/stop skill tracker: " + DankersSkyblockMod.VALUE_COLOUR + autoSkillTrackerToggled + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Livid solver: " + DankersSkyblockMod.VALUE_COLOUR + lividSolverToggled + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Three man puzzle solver: " + DankersSkyblockMod.VALUE_COLOUR + threeManToggled + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Oruo trivia solver: " + DankersSkyblockMod.VALUE_COLOUR + oruoToggled + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Blaze puzzle solver: " + DankersSkyblockMod.VALUE_COLOUR + blazeToggled + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Creeper puzzle solver: " + DankersSkyblockMod.VALUE_COLOUR + creeperToggled + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Water puzzle solver: " + DankersSkyblockMod.VALUE_COLOUR + waterToggled + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Tic tac toe puzzle solver: " + DankersSkyblockMod.VALUE_COLOUR + ticTacToeToggled + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Watcher ready message: " + DankersSkyblockMod.VALUE_COLOUR + watcherReadyToggled + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Starts with letter terminal solver: " + DankersSkyblockMod.VALUE_COLOUR + startsWithToggled + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Select all color items terminal solver: " + DankersSkyblockMod.VALUE_COLOUR + selectAllToggled + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Ignore item frames on sea lanterns: " + DankersSkyblockMod.VALUE_COLOUR + itemFrameOnSeaLanternsToggled + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Ultra sequencer solver: " + DankersSkyblockMod.VALUE_COLOUR + ultrasequencerToggled + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Chronomatron solver: " + DankersSkyblockMod.VALUE_COLOUR + chronomatronToggled + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Superpairs solver: " + DankersSkyblockMod.VALUE_COLOUR + superpairsToggled + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Hide tooltips in experiment addons: " + DankersSkyblockMod.VALUE_COLOUR + hideTooltipsInExperimentAddonsToggled + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Auto-swap to pick block " + DankersSkyblockMod.VALUE_COLOUR + swapToPickBlockToggled));
/*     */         return;
/*     */     } 
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
/*     */ 
/*     */     
/* 410 */     player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Usage: " + func_71518_a(arg0)));
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\me\Danker\commands\ToggleCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */