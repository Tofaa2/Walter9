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
/*     */ import net.minecraft.util.IChatComponent;
/*     */ 
/*     */ public class MoveCommand
/*     */   extends CommandBase {
/*  16 */   public static int[] coordsXY = new int[] { 0, 0 };
/*  17 */   public static int[] displayXY = new int[] { 0, 0 };
/*  18 */   public static int[] dungeonTimerXY = new int[] { 0, 0 };
/*  19 */   public static int[] skill50XY = new int[] { 0, 0 };
/*  20 */   public static int[] lividHpXY = new int[] { 0, 0 };
/*  21 */   public static int[] cakeTimerXY = new int[] { 0, 0 };
/*  22 */   public static int[] skillTrackerXY = new int[] { 0, 0 };
/*  23 */   public static int[] waterAnswerXY = new int[] { 0, 0 };
/*  24 */   public static int[] bonzoTimerXY = new int[] { 0, 0 };
/*     */ 
/*     */   
/*     */   public String func_71517_b() {
/*  28 */     return "move";
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_71518_a(ICommandSender arg0) {
/*  33 */     return "/" + func_71517_b() + " <coords/display/dungeontimer/skill50/lividhp/caketimer/skilltracker/wateranswer/bonzotimer> <x> <y>";
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_82362_a() {
/*  38 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> func_180525_a(ICommandSender sender, String[] args, BlockPos pos) {
/*  43 */     if (args.length == 1) {
/*  44 */       return func_71530_a(args, new String[] { "coords", "display", "dungeontimer", "skill50", "lividhp", "caketimer", "skilltracker", "wateranswer", "bonzotimer" });
/*     */     }
/*  46 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_71515_b(ICommandSender arg0, String[] arg1) throws CommandException {
/*  51 */     EntityPlayer player = (EntityPlayer)arg0;
/*     */     
/*  53 */     if (arg1.length < 2) {
/*  54 */       player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Usage: " + func_71518_a(arg0)));
/*     */       
/*     */       return;
/*     */     } 
/*  58 */     switch (arg1[0].toLowerCase()) {
/*     */       case "coords":
/*  60 */         coordsXY[0] = Integer.parseInt(arg1[1]);
/*  61 */         coordsXY[1] = Integer.parseInt(arg1[2]);
/*  62 */         ConfigHandler.writeIntConfig("locations", "coordsX", coordsXY[0]);
/*  63 */         ConfigHandler.writeIntConfig("locations", "coordsY", coordsXY[1]);
/*  64 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Coords have been moved to " + DankersSkyblockMod.SECONDARY_COLOUR + arg1[1] + ", " + arg1[2]));
/*     */         return;
/*     */       case "display":
/*  67 */         displayXY[0] = Integer.parseInt(arg1[1]);
/*  68 */         displayXY[1] = Integer.parseInt(arg1[2]);
/*  69 */         ConfigHandler.writeIntConfig("locations", "displayX", displayXY[0]);
/*  70 */         ConfigHandler.writeIntConfig("locations", "displayY", displayXY[1]);
/*  71 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Tracker display has been moved to " + DankersSkyblockMod.SECONDARY_COLOUR + arg1[1] + ", " + arg1[2]));
/*     */         return;
/*     */       case "dungeontimer":
/*  74 */         dungeonTimerXY[0] = Integer.parseInt(arg1[1]);
/*  75 */         dungeonTimerXY[1] = Integer.parseInt(arg1[2]);
/*  76 */         ConfigHandler.writeIntConfig("locations", "dungeonTimerX", dungeonTimerXY[0]);
/*  77 */         ConfigHandler.writeIntConfig("locations", "dungeonTimerY", dungeonTimerXY[1]);
/*  78 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Dungeon timer has been moved to " + DankersSkyblockMod.SECONDARY_COLOUR + arg1[1] + ", " + arg1[2]));
/*     */         return;
/*     */       case "skill50":
/*  81 */         skill50XY[0] = Integer.parseInt(arg1[1]);
/*  82 */         skill50XY[1] = Integer.parseInt(arg1[2]);
/*  83 */         ConfigHandler.writeIntConfig("locations", "skill50X", skill50XY[0]);
/*  84 */         ConfigHandler.writeIntConfig("locations", "skill50Y", skill50XY[1]);
/*  85 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Skill 50 display has been moved to " + DankersSkyblockMod.SECONDARY_COLOUR + arg1[1] + ", " + arg1[2]));
/*     */         return;
/*     */       case "lividhp":
/*  88 */         lividHpXY[0] = Integer.parseInt(arg1[1]);
/*  89 */         lividHpXY[1] = Integer.parseInt(arg1[2]);
/*  90 */         ConfigHandler.writeIntConfig("locations", "lividHpX", lividHpXY[0]);
/*  91 */         ConfigHandler.writeIntConfig("locations", "lividHpY", lividHpXY[1]);
/*  92 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Livid HP has been moved to " + DankersSkyblockMod.SECONDARY_COLOUR + arg1[1] + ", " + arg1[2]));
/*     */         return;
/*     */       case "caketimer":
/*  95 */         cakeTimerXY[0] = Integer.parseInt(arg1[1]);
/*  96 */         cakeTimerXY[1] = Integer.parseInt(arg1[2]);
/*  97 */         ConfigHandler.writeIntConfig("locations", "cakeTimerX", cakeTimerXY[0]);
/*  98 */         ConfigHandler.writeIntConfig("locations", "cakeTimerY", cakeTimerXY[1]);
/*  99 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Cake timer has been moved to " + DankersSkyblockMod.SECONDARY_COLOUR + arg1[1] + ", " + arg1[2]));
/*     */         return;
/*     */       case "skilltracker":
/* 102 */         skillTrackerXY[0] = Integer.parseInt(arg1[1]);
/* 103 */         skillTrackerXY[1] = Integer.parseInt(arg1[2]);
/* 104 */         ConfigHandler.writeIntConfig("locations", "skillTrackerX", skillTrackerXY[0]);
/* 105 */         ConfigHandler.writeIntConfig("locations", "skillTrackerY", skillTrackerXY[1]);
/* 106 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Skill tracker has been moved to " + DankersSkyblockMod.SECONDARY_COLOUR + arg1[1] + ", " + arg1[2]));
/*     */         return;
/*     */       case "wateranswer":
/* 109 */         waterAnswerXY[0] = Integer.parseInt(arg1[1]);
/* 110 */         waterAnswerXY[1] = Integer.parseInt(arg1[2]);
/* 111 */         ConfigHandler.writeIntConfig("locations", "waterAnswerX", waterAnswerXY[0]);
/* 112 */         ConfigHandler.writeIntConfig("locations", "waterAnswerY", waterAnswerXY[1]);
/* 113 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Water solver answer has been moved to " + DankersSkyblockMod.SECONDARY_COLOUR + arg1[1] + ", " + arg1[2]));
/*     */         return;
/*     */       case "bonzotimer":
/* 116 */         bonzoTimerXY[0] = Integer.parseInt(arg1[1]);
/* 117 */         bonzoTimerXY[1] = Integer.parseInt(arg1[2]);
/* 118 */         ConfigHandler.writeIntConfig("locations", "bonzoTimerX", bonzoTimerXY[0]);
/* 119 */         ConfigHandler.writeIntConfig("locations", "bonzoTimerX", bonzoTimerXY[1]);
/* 120 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Bonzo's Mask timer has been moved to " + DankersSkyblockMod.SECONDARY_COLOUR + arg1[1] + ", " + arg1[2]));
/*     */         return;
/*     */     } 
/* 123 */     player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Usage: " + func_71518_a(arg0)));
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\me\Danker\commands\MoveCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */