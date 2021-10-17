/*    */ package me.Danker.gui;
/*    */ 
/*    */ import me.Danker.DankersSkyblockMod;
/*    */ import me.Danker.commands.ToggleCommand;
/*    */ import me.Danker.handlers.ConfigHandler;
/*    */ import me.Danker.utils.Utils;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ import net.minecraft.client.gui.ScaledResolution;
/*    */ 
/*    */ public class ExperimentsGui
/*    */   extends GuiScreen
/*    */ {
/*    */   private GuiButton goBack;
/*    */   private GuiButton ultrasequencer;
/*    */   private GuiButton chronomatron;
/*    */   private GuiButton superpairs;
/*    */   private GuiButton hideTooltips;
/*    */   
/*    */   public boolean func_73868_f() {
/* 22 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_73866_w_() {
/* 27 */     super.func_73866_w_();
/*    */     
/* 29 */     ScaledResolution sr = new ScaledResolution(Minecraft.func_71410_x());
/* 30 */     int height = sr.func_78328_b();
/* 31 */     int width = sr.func_78326_a();
/*    */     
/* 33 */     this.goBack = new GuiButton(0, 2, height - 30, 100, 20, "Go Back");
/* 34 */     this.ultrasequencer = new GuiButton(0, width / 2 - 100, (int)(height * 0.1D), "Ultrasequencer Solver: " + Utils.getColouredBoolean(ToggleCommand.ultrasequencerToggled));
/* 35 */     this.chronomatron = new GuiButton(0, width / 2 - 100, (int)(height * 0.2D), "Chronomatron Solver: " + Utils.getColouredBoolean(ToggleCommand.chronomatronToggled));
/* 36 */     this.superpairs = new GuiButton(0, width / 2 - 100, (int)(height * 0.3D), "Superpairs Solver: " + Utils.getColouredBoolean(ToggleCommand.superpairsToggled));
/* 37 */     this.hideTooltips = new GuiButton(0, width / 2 - 100, (int)(height * 0.4D), "Hide Tooltips in Addons: " + Utils.getColouredBoolean(ToggleCommand.hideTooltipsInExperimentAddonsToggled));
/*    */     
/* 39 */     this.field_146292_n.add(this.goBack);
/* 40 */     this.field_146292_n.add(this.ultrasequencer);
/* 41 */     this.field_146292_n.add(this.chronomatron);
/* 42 */     this.field_146292_n.add(this.superpairs);
/* 43 */     this.field_146292_n.add(this.hideTooltips);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
/* 48 */     func_146276_q_();
/* 49 */     super.func_73863_a(mouseX, mouseY, partialTicks);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_146284_a(GuiButton button) {
/* 54 */     if (button == this.goBack) {
/* 55 */       DankersSkyblockMod.guiToOpen = "dankergui1";
/* 56 */     } else if (button == this.ultrasequencer) {
/* 57 */       ToggleCommand.ultrasequencerToggled = !ToggleCommand.ultrasequencerToggled;
/* 58 */       ConfigHandler.writeBooleanConfig("toggles", "UltraSequencer", ToggleCommand.ultrasequencerToggled);
/* 59 */       this.ultrasequencer.field_146126_j = "Ultrasequencer Solver: " + Utils.getColouredBoolean(ToggleCommand.ultrasequencerToggled);
/* 60 */     } else if (button == this.chronomatron) {
/* 61 */       ToggleCommand.chronomatronToggled = !ToggleCommand.chronomatronToggled;
/* 62 */       ConfigHandler.writeBooleanConfig("toggles", "Chronomatron", ToggleCommand.chronomatronToggled);
/* 63 */       this.chronomatron.field_146126_j = "Chronomatron Solver: " + Utils.getColouredBoolean(ToggleCommand.chronomatronToggled);
/* 64 */     } else if (button == this.superpairs) {
/* 65 */       ToggleCommand.superpairsToggled = !ToggleCommand.superpairsToggled;
/* 66 */       ConfigHandler.writeBooleanConfig("toggles", "Superpairs", ToggleCommand.superpairsToggled);
/* 67 */       this.superpairs.field_146126_j = "Superpairs Solver: " + Utils.getColouredBoolean(ToggleCommand.superpairsToggled);
/* 68 */     } else if (button == this.hideTooltips) {
/* 69 */       ToggleCommand.hideTooltipsInExperimentAddonsToggled = !ToggleCommand.hideTooltipsInExperimentAddonsToggled;
/* 70 */       ConfigHandler.writeBooleanConfig("toggles", "HideTooltipsInExperimentAddons", ToggleCommand.hideTooltipsInExperimentAddonsToggled);
/* 71 */       this.hideTooltips.field_146126_j = "Hide Tooltips in Addons: " + Utils.getColouredBoolean(ToggleCommand.hideTooltipsInExperimentAddonsToggled);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\me\Danker\gui\ExperimentsGui.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */