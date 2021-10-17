/*    */ package me.Danker.gui;
/*    */ 
/*    */ import me.Danker.DankersSkyblockMod;
/*    */ import me.Danker.handlers.ConfigHandler;
/*    */ import me.Danker.handlers.TextRenderer;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ import net.minecraft.client.gui.ScaledResolution;
/*    */ import org.apache.commons.lang3.time.StopWatch;
/*    */ 
/*    */ public class SkillTrackerGui
/*    */   extends GuiScreen
/*    */ {
/*    */   private GuiButton goBack;
/*    */   private GuiButton start;
/*    */   private GuiButton stop;
/*    */   private GuiButton reset;
/*    */   private GuiButton hide;
/*    */   private GuiButton show;
/*    */   
/*    */   public boolean func_73868_f() {
/* 23 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_73866_w_() {
/* 28 */     super.func_73866_w_();
/*    */     
/* 30 */     ScaledResolution sr = new ScaledResolution(Minecraft.func_71410_x());
/* 31 */     int height = sr.func_78328_b();
/* 32 */     int width = sr.func_78326_a();
/*    */     
/* 34 */     this.goBack = new GuiButton(0, 2, height - 30, 100, 20, "Go Back");
/* 35 */     this.start = new GuiButton(0, width / 2 - 140, (int)(height * 0.45D), 80, 20, "Start");
/* 36 */     this.stop = new GuiButton(0, width / 2 - 40, (int)(height * 0.45D), 80, 20, "Stop");
/* 37 */     this.reset = new GuiButton(0, width / 2 + 60, (int)(height * 0.45D), 80, 20, "Reset");
/* 38 */     this.hide = new GuiButton(0, width / 2 - 70, (int)(height * 0.55D), 60, 20, "Hide");
/* 39 */     this.show = new GuiButton(0, width / 2 + 10, (int)(height * 0.55D), 60, 20, "Show");
/*    */     
/* 41 */     this.field_146292_n.add(this.start);
/* 42 */     this.field_146292_n.add(this.stop);
/* 43 */     this.field_146292_n.add(this.reset);
/* 44 */     this.field_146292_n.add(this.hide);
/* 45 */     this.field_146292_n.add(this.show);
/* 46 */     this.field_146292_n.add(this.goBack);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
/* 51 */     func_146276_q_();
/* 52 */     String stateText = "";
/* 53 */     if (DankersSkyblockMod.skillStopwatch.isStarted() && !DankersSkyblockMod.skillStopwatch.isSuspended()) {
/* 54 */       stateText = "Timer: Running";
/* 55 */     } else if (!DankersSkyblockMod.skillStopwatch.isStarted() || DankersSkyblockMod.skillStopwatch.isSuspended()) {
/* 56 */       stateText = "Timer: Paused";
/*    */     } 
/* 58 */     if (!DankersSkyblockMod.showSkillTracker) {
/* 59 */       stateText = stateText + " (Hidden)";
/*    */     }
/* 61 */     int stateTextWidth = this.field_146297_k.field_71466_p.func_78256_a(stateText);
/* 62 */     new TextRenderer(this.field_146297_k, stateText, this.field_146294_l / 2 - stateTextWidth / 2, 10, 1.0D);
/* 63 */     super.func_73863_a(mouseX, mouseY, partialTicks);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_146284_a(GuiButton button) {
/* 68 */     if (button == this.goBack) {
/* 69 */       DankersSkyblockMod.guiToOpen = "dankergui1";
/* 70 */     } else if (button == this.start) {
/* 71 */       if (DankersSkyblockMod.skillStopwatch.isStarted() && DankersSkyblockMod.skillStopwatch.isSuspended()) {
/* 72 */         DankersSkyblockMod.skillStopwatch.resume();
/* 73 */       } else if (!DankersSkyblockMod.skillStopwatch.isStarted()) {
/* 74 */         DankersSkyblockMod.skillStopwatch.start();
/*    */       } 
/* 76 */     } else if (button == this.stop) {
/* 77 */       if (DankersSkyblockMod.skillStopwatch.isStarted() && !DankersSkyblockMod.skillStopwatch.isSuspended()) {
/* 78 */         DankersSkyblockMod.skillStopwatch.suspend();
/*    */       }
/* 80 */     } else if (button == this.reset) {
/* 81 */       DankersSkyblockMod.skillStopwatch = new StopWatch();
/* 82 */       DankersSkyblockMod.farmingXPGained = 0.0D;
/* 83 */       DankersSkyblockMod.miningXPGained = 0.0D;
/* 84 */       DankersSkyblockMod.combatXPGained = 0.0D;
/* 85 */       DankersSkyblockMod.foragingXPGained = 0.0D;
/* 86 */       DankersSkyblockMod.fishingXPGained = 0.0D;
/* 87 */       DankersSkyblockMod.enchantingXPGained = 0.0D;
/* 88 */       DankersSkyblockMod.alchemyXPGained = 0.0D;
/* 89 */     } else if (button == this.hide) {
/* 90 */       DankersSkyblockMod.showSkillTracker = false;
/* 91 */       ConfigHandler.writeBooleanConfig("misc", "showSkillTracker", false);
/* 92 */     } else if (button == this.show) {
/* 93 */       DankersSkyblockMod.showSkillTracker = true;
/* 94 */       ConfigHandler.writeBooleanConfig("misc", "showSkillTracker", true);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\me\Danker\gui\SkillTrackerGui.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */