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
/*     */ public class ScaleCommand
/*     */   extends CommandBase
/*     */ {
/*     */   public static double coordsScale;
/*     */   public static double displayScale;
/*     */   public static double dungeonTimerScale;
/*     */   public static double skill50Scale;
/*     */   public static double lividHpScale;
/*     */   public static double cakeTimerScale;
/*     */   public static double skillTrackerScale;
/*     */   public static double waterAnswerScale;
/*     */   public static double bonzoTimerScale;
/*     */   
/*     */   public String func_71517_b() {
/*  28 */     return "scale";
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_71518_a(ICommandSender arg0) {
/*  33 */     return "/" + func_71517_b() + " <coords/display/dungeontimer/skill50/lividhp/caketimer/skilltracker/wateranswer/bonzotimer> <size (0.1 - 10)>";
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
/*  58 */     double scaleAmount = Math.floor(Double.parseDouble(arg1[1]) * 100.0D) / 100.0D;
/*  59 */     if (scaleAmount < 0.1D || scaleAmount > 10.0D) {
/*  60 */       player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Scale multipler can only be between 0.1x and 10x."));
/*     */       
/*     */       return;
/*     */     } 
/*  64 */     switch (arg1[0].toLowerCase()) {
/*     */       case "coords":
/*  66 */         coordsScale = scaleAmount;
/*  67 */         ConfigHandler.writeDoubleConfig("scales", "coordsScale", coordsScale);
/*  68 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Coords have been scaled to " + DankersSkyblockMod.SECONDARY_COLOUR + coordsScale + "x"));
/*     */         return;
/*     */       case "display":
/*  71 */         displayScale = scaleAmount;
/*  72 */         ConfigHandler.writeDoubleConfig("scales", "displayScale", displayScale);
/*  73 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Display has been scaled to " + DankersSkyblockMod.SECONDARY_COLOUR + displayScale + "x"));
/*     */         return;
/*     */       case "dungeontimer":
/*  76 */         dungeonTimerScale = scaleAmount;
/*  77 */         ConfigHandler.writeDoubleConfig("scales", "dungeonTimerScale", dungeonTimerScale);
/*  78 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Dungeon timer has been scaled to " + DankersSkyblockMod.SECONDARY_COLOUR + dungeonTimerScale + "x"));
/*     */         return;
/*     */       case "skill50":
/*  81 */         skill50Scale = scaleAmount;
/*  82 */         ConfigHandler.writeDoubleConfig("scales", "skill50Scale", skill50Scale);
/*  83 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Skill 50 display has been scaled to " + DankersSkyblockMod.SECONDARY_COLOUR + skill50Scale + "x"));
/*     */         return;
/*     */       case "lividhp":
/*  86 */         lividHpScale = scaleAmount;
/*  87 */         ConfigHandler.writeDoubleConfig("scales", "lividHpScale", lividHpScale);
/*  88 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Livid HP has been scaled to " + DankersSkyblockMod.SECONDARY_COLOUR + lividHpScale + "x"));
/*     */         return;
/*     */       case "caketimer":
/*  91 */         cakeTimerScale = scaleAmount;
/*  92 */         ConfigHandler.writeDoubleConfig("scales", "cakeTimerScale", cakeTimerScale);
/*  93 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Cake timer has been scaled to " + DankersSkyblockMod.SECONDARY_COLOUR + cakeTimerScale + "x"));
/*     */         return;
/*     */       case "skilltracker":
/*  96 */         skillTrackerScale = scaleAmount;
/*  97 */         ConfigHandler.writeDoubleConfig("scales", "skillTrackerScale", skillTrackerScale);
/*  98 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Skill tracker has been scaled to " + DankersSkyblockMod.SECONDARY_COLOUR + skillTrackerScale + "x"));
/*     */         return;
/*     */       case "wateranswer":
/* 101 */         waterAnswerScale = scaleAmount;
/* 102 */         ConfigHandler.writeDoubleConfig("scales", "waterAnswerScale", waterAnswerScale);
/* 103 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Water solver answer has been scaled to " + DankersSkyblockMod.SECONDARY_COLOUR + waterAnswerScale + "x"));
/*     */         return;
/*     */       case "bonzotimer":
/* 106 */         bonzoTimerScale = scaleAmount;
/* 107 */         ConfigHandler.writeDoubleConfig("scales", "bonzoTimerScale", bonzoTimerScale);
/* 108 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Bonzo's Mask timer has been scaled to " + DankersSkyblockMod.SECONDARY_COLOUR + bonzoTimerScale + "x"));
/*     */         return;
/*     */     } 
/* 111 */     player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Usage: " + func_71518_a(arg0)));
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\me\Danker\commands\ScaleCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */