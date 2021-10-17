/*    */ package me.Danker.gui;
/*    */ 
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ import net.minecraft.client.gui.GuiMainMenu;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ 
/*    */ public class WarningGuiRedirect
/*    */   extends GuiMainMenu {
/*    */   public GuiScreen guiToShow;
/*    */   private GuiButton close;
/*    */   
/*    */   protected void func_73869_a(char par1, int par2) {}
/*    */   
/*    */   public WarningGuiRedirect(GuiScreen g) {
/* 15 */     this.guiToShow = g;
/*    */   }
/*    */   
/*    */   public void func_73866_w_() {
/* 19 */     super.func_73866_w_();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_73863_a(int par1, int par2, float par3) {
/* 24 */     super.func_73863_a(par1, par2, par3);
/*    */     
/* 26 */     this.field_146297_k.func_147108_a(this.guiToShow);
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\me\Danker\gui\WarningGuiRedirect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */