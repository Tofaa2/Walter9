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
/*     */ import org.apache.commons.lang3.time.StopWatch;
/*     */ 
/*     */ public class SkillTrackerCommand
/*     */   extends CommandBase {
/*     */   public static boolean running = false;
/*     */   
/*     */   public String func_71517_b() {
/*  20 */     return "skilltracker";
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_71518_a(ICommandSender arg0) {
/*  25 */     return "/" + func_71517_b() + " <start/stop/reset>";
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_82362_a() {
/*  30 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> func_180525_a(ICommandSender sender, String[] args, BlockPos pos) {
/*  35 */     if (args.length == 1) {
/*  36 */       return func_71530_a(args, new String[] { "start", "resume", "pause", "stop", "reset", "hide", "show" });
/*     */     }
/*  38 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_71515_b(ICommandSender arg0, String[] arg1) throws CommandException {
/*  44 */     (new Thread(() -> {
/*     */           EntityPlayer player = (EntityPlayer)arg0;
/*     */           if (arg1.length < 1) {
/*     */             player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Usage: " + func_71518_a(arg0)));
/*     */             return;
/*     */           } 
/*     */           try {
/*     */             Thread.sleep(50L);
/*     */             switch (arg1[0].toLowerCase()) {
/*     */               case "start":
/*     */               case "resume":
/*     */                 if (DankersSkyblockMod.skillStopwatch.isStarted() && DankersSkyblockMod.skillStopwatch.isSuspended()) {
/*     */                   DankersSkyblockMod.skillStopwatch.resume();
/*     */                 } else if (!DankersSkyblockMod.skillStopwatch.isStarted()) {
/*     */                   DankersSkyblockMod.skillStopwatch.start();
/*     */                 } 
/*     */                 player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Skill tracker started."));
/*     */                 return;
/*     */               
/*     */               case "pause":
/*     */               case "stop":
/*     */                 if (DankersSkyblockMod.skillStopwatch.isStarted() && !DankersSkyblockMod.skillStopwatch.isSuspended()) {
/*     */                   DankersSkyblockMod.skillStopwatch.suspend();
/*     */                 } else {
/*     */                   player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Skill tracker paused."));
/*     */                 } 
/*     */                 return;
/*     */               
/*     */               case "reset":
/*     */                 DankersSkyblockMod.skillStopwatch = new StopWatch();
/*     */                 DankersSkyblockMod.farmingXPGained = 0.0D;
/*     */                 DankersSkyblockMod.miningXPGained = 0.0D;
/*     */                 DankersSkyblockMod.combatXPGained = 0.0D;
/*     */                 DankersSkyblockMod.foragingXPGained = 0.0D;
/*     */                 DankersSkyblockMod.fishingXPGained = 0.0D;
/*     */                 DankersSkyblockMod.enchantingXPGained = 0.0D;
/*     */                 DankersSkyblockMod.alchemyXPGained = 0.0D;
/*     */                 player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Skill tracker reset."));
/*     */                 return;
/*     */               
/*     */               case "hide":
/*     */                 DankersSkyblockMod.showSkillTracker = false;
/*     */                 ConfigHandler.writeBooleanConfig("misc", "showSkillTracker", false);
/*     */                 player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Skill tracker hidden."));
/*     */                 return;
/*     */               
/*     */               case "show":
/*     */                 DankersSkyblockMod.showSkillTracker = true;
/*     */                 ConfigHandler.writeBooleanConfig("misc", "showSkillTracker", true);
/*     */                 player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Skill tracker shown."));
/*     */                 return;
/*     */             } 
/*     */             player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Usage: " + func_71518_a(arg0)));
/*  97 */           } catch (InterruptedException e) {
/*     */             e.printStackTrace();
/*     */           } 
/* 100 */         })).start();
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\me\Danker\commands\SkillTrackerCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */