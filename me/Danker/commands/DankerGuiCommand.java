/*     */ package me.Danker.commands;
/*     */ 
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.datatransfer.StringSelection;
/*     */ import me.Danker.DankersSkyblockMod;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.resources.ResourcePackRepository;
/*     */ import net.minecraft.command.CommandBase;
/*     */ import net.minecraft.command.CommandException;
/*     */ import net.minecraft.command.ICommandSender;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.StringUtils;
/*     */ 
/*     */ public class DankerGuiCommand
/*     */   extends CommandBase
/*     */ {
/*     */   public String func_71517_b() {
/*  19 */     return "dsm";
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_71518_a(ICommandSender arg0) {
/*  24 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_82362_a() {
/*  29 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_71515_b(ICommandSender arg0, String[] arg1) throws CommandException {
/*  34 */     if (arg1.length > 0 && arg1[0].equalsIgnoreCase("debug")) {
/*  35 */       StringBuilder debug = new StringBuilder();
/*  36 */       debug.append("```md\n");
/*  37 */       debug.append("# Toggles\n");
/*  38 */       debug.append("[gparty][").append(ToggleCommand.gpartyToggled).append("]\n");
/*  39 */       debug.append("[coords][").append(ToggleCommand.coordsToggled).append("]\n");
/*  40 */       debug.append("[golden][").append(ToggleCommand.goldenToggled).append("]\n");
/*  41 */       debug.append("[slayercount][").append(ToggleCommand.slayerCountTotal).append("]\n");
/*  42 */       debug.append("[rngesusalerts][").append(ToggleCommand.rngesusAlerts).append("]\n");
/*  43 */       debug.append("[splitfishing][").append(ToggleCommand.splitFishing).append("]\n");
/*  44 */       debug.append("[chatmaddox][").append(ToggleCommand.chatMaddoxToggled).append("]\n");
/*  45 */       debug.append("[spiritbearalerts][").append(ToggleCommand.spiritBearAlerts).append("]\n");
/*  46 */       debug.append("[aotd][").append(ToggleCommand.aotdToggled).append("]\n");
/*  47 */       debug.append("[lividdagger][").append(ToggleCommand.lividDaggerToggled).append("]\n");
/*  48 */       debug.append("[flowerweapons][").append(ToggleCommand.flowerWeaponsToggled).append("]\n");
/*  49 */       debug.append("[sceptremessages][").append(ToggleCommand.sceptreMessages).append("]\n");
/*  50 */       debug.append("[petcolors][").append(ToggleCommand.petColoursToggled).append("]\n");
/*  51 */       debug.append("[dungeontimer][").append(ToggleCommand.dungeonTimerToggled).append("]\n");
/*  52 */       debug.append("[golemalerts][").append(ToggleCommand.golemAlertToggled).append("]\n");
/*  53 */       debug.append("[expertiselore][").append(ToggleCommand.expertiseLoreToggled).append("]\n");
/*  54 */       debug.append("[skill50display][").append(ToggleCommand.skill50DisplayToggled).append("]\n");
/*  55 */       debug.append("[outlinetext][").append(ToggleCommand.outlineTextToggled).append("]\n");
/*  56 */       debug.append("[midasstaffmessages][").append(ToggleCommand.midasStaffMessages).append("]\n");
/*  57 */       debug.append("[implosionmessages][").append(ToggleCommand.implosionMessages).append("]\n");
/*  58 */       debug.append("[healmessages][").append(ToggleCommand.healMessages).append("]\n");
/*  59 */       debug.append("[caketimer][").append(ToggleCommand.cakeTimerToggled).append("]\n");
/*  60 */       debug.append("[lowhealthnotify][").append(ToggleCommand.lowHealthNotifyToggled).append("]\n");
/*  61 */       debug.append("[lividsolver][").append(ToggleCommand.lividSolverToggled).append("]\n");
/*  62 */       debug.append("[stopsalvagestarred][").append(ToggleCommand.stopSalvageStarredToggled).append("]\n");
/*  63 */       debug.append("[notifyslayerslain][").append(ToggleCommand.notifySlayerSlainToggled).append("]\n");
/*  64 */       debug.append("[necronnotifications][").append(ToggleCommand.necronNotificationsToggled).append("]\n");
/*  65 */       debug.append("[bonzotimer][").append(ToggleCommand.bonzoTimerToggled).append("]\n");
/*  66 */       debug.append("[blockbreakingfarms][").append(ToggleCommand.blockBreakingFarmsToggled).append("]\n");
/*  67 */       debug.append("[autoskilltracker][").append(ToggleCommand.autoSkillTrackerToggled).append("]\n");
/*  68 */       debug.append("[threemanpuzzle][").append(ToggleCommand.threeManToggled).append("]\n");
/*  69 */       debug.append("[oruopuzzle][").append(ToggleCommand.oruoToggled).append("]\n");
/*  70 */       debug.append("[blazepuzzle][").append(ToggleCommand.blazeToggled).append("]\n");
/*  71 */       debug.append("[creeperpuzzle][").append(ToggleCommand.creeperToggled).append("]\n");
/*  72 */       debug.append("[waterpuzzle][").append(ToggleCommand.waterToggled).append("]\n");
/*  73 */       debug.append("[tictactoepuzzle][").append(ToggleCommand.ticTacToeToggled).append("]\n");
/*  74 */       debug.append("[watchermessage][").append(ToggleCommand.watcherReadyToggled).append("]\n");
/*  75 */       debug.append("[startswithterminal][").append(ToggleCommand.startsWithToggled).append("]\n");
/*  76 */       debug.append("[selectallterminal][").append(ToggleCommand.selectAllToggled).append("]\n");
/*  77 */       debug.append("[clickinorderterminal][").append(ToggleCommand.clickInOrderToggled).append("]\n");
/*  78 */       debug.append("[blockwrongterminalclicks][").append(ToggleCommand.blockWrongTerminalClicksToggled).append("]\n");
/*  79 */       debug.append("[itemframeonsealanterns][").append(ToggleCommand.itemFrameOnSeaLanternsToggled).append("]\n");
/*  80 */       debug.append("[ultrasequencer][").append(ToggleCommand.ultrasequencerToggled).append("]\n");
/*  81 */       debug.append("[chronomatron][").append(ToggleCommand.chronomatronToggled).append("]\n");
/*  82 */       debug.append("[superpairs][").append(ToggleCommand.superpairsToggled).append("]\n");
/*  83 */       debug.append("[hidetooltipsinaddons][").append(ToggleCommand.hideTooltipsInExperimentAddonsToggled).append("]\n");
/*  84 */       debug.append("[pickblock][").append(ToggleCommand.swapToPickBlockToggled).append("]\n");
/*  85 */       debug.append("# Locations\n");
/*  86 */       debug.append("[coords][").append(MoveCommand.coordsXY[0]).append(", ").append(MoveCommand.coordsXY[1]).append("]\n");
/*  87 */       debug.append("[display][").append(MoveCommand.displayXY[0]).append(", ").append(MoveCommand.displayXY[1]).append("]\n");
/*  88 */       debug.append("[dungeontimer][").append(MoveCommand.dungeonTimerXY[0]).append(", ").append(MoveCommand.dungeonTimerXY[1]).append("]\n");
/*  89 */       debug.append("[skill50][").append(MoveCommand.skill50XY[0]).append(", ").append(MoveCommand.skill50XY[1]).append("]\n");
/*  90 */       debug.append("[lividhp][").append(MoveCommand.lividHpXY[0]).append(", ").append(MoveCommand.lividHpXY[1]).append("]\n");
/*  91 */       debug.append("[caketimer][").append(MoveCommand.cakeTimerXY[0]).append(", ").append(MoveCommand.cakeTimerXY[1]).append("]\n");
/*  92 */       debug.append("[skilltracker][").append(MoveCommand.skillTrackerXY[0]).append(", ").append(MoveCommand.skillTrackerXY[1]).append("]\n");
/*  93 */       debug.append("[wateranswer][").append(MoveCommand.waterAnswerXY[0]).append(", ").append(MoveCommand.waterAnswerXY[1]).append("]\n");
/*  94 */       debug.append("# Other Settings\n");
/*  95 */       debug.append("[Current Display][").append(DisplayCommand.display).append("]\n");
/*  96 */       debug.append("[Auto Display][").append(DisplayCommand.auto).append("]\n");
/*  97 */       debug.append("[Skill Tracker Visible][").append(DankersSkyblockMod.showSkillTracker).append("]\n");
/*  98 */       debug.append("# Resource Packs\n");
/*  99 */       if (Minecraft.func_71410_x().func_110438_M().func_110613_c().size() == 0) {
/* 100 */         debug.append("<None>\n");
/*     */       } else {
/* 102 */         for (ResourcePackRepository.Entry resource : Minecraft.func_71410_x().func_110438_M().func_110613_c()) {
/* 103 */           debug.append("< ").append(StringUtils.func_76338_a(resource.func_110515_d())).append(" >\n");
/*     */         }
/*     */       } 
/* 106 */       debug.append("```");
/* 107 */       StringSelection clipboard = new StringSelection(debug.toString());
/* 108 */       Toolkit.getDefaultToolkit().getSystemClipboard().setContents(clipboard, clipboard);
/* 109 */       (Minecraft.func_71410_x()).field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Debug stats copied to clipboard."));
/*     */       
/*     */       return;
/*     */     } 
/* 113 */     DankersSkyblockMod.guiToOpen = "dankergui1";
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\me\Danker\commands\DankerGuiCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */