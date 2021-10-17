/*    */ package me.Danker.gui;
/*    */ 
/*    */ import me.Danker.handlers.TextRenderer;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ import net.minecraft.client.gui.ScaledResolution;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WarningGui
/*    */   extends GuiScreen
/*    */ {
/*    */   private GuiButton close;
/*    */   
/*    */   public boolean func_73868_f() {
/* 24 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_73866_w_() {
/* 29 */     super.func_73866_w_();
/*    */     
/* 31 */     ScaledResolution sr = new ScaledResolution(Minecraft.func_71410_x());
/* 32 */     int height = sr.func_78328_b();
/* 33 */     int width = sr.func_78326_a();
/*    */     
/* 35 */     this.close = new GuiButton(0, width / 2 - 100, (int)(height * 0.6D), "Close");
/*    */     
/* 37 */     this.field_146292_n.add(this.close);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
/* 42 */     func_146276_q_();
/* 43 */     String text0 = "§cWARNING!§0";
/* 44 */     int text0Width = this.field_146297_k.field_71466_p.func_78256_a(text0);
/* 45 */     new TextRenderer(this.field_146297_k, text0, (int)(this.field_146294_l * 0.45D) - text0Width / 2, (int)(this.field_146295_m * 0.1D), 2.0D);
/* 46 */     String text1 = "You are using SpiderFrog's Old Animations mod.";
/* 47 */     int text1Width = this.field_146297_k.field_71466_p.func_78256_a(text1);
/* 48 */     new TextRenderer(this.field_146297_k, text1, this.field_146294_l / 2 - text1Width / 2, (int)(this.field_146295_m * 0.3D), 1.0D);
/* 49 */     String text2 = "This mod breaks Danker's Skyblock Mod.";
/* 50 */     int text2Width = this.field_146297_k.field_71466_p.func_78256_a(text2);
/* 51 */     new TextRenderer(this.field_146297_k, text2, this.field_146294_l / 2 - text2Width / 2, (int)(this.field_146295_m * 0.4D), 1.0D);
/* 52 */     super.func_73863_a(mouseX, mouseY, partialTicks);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_146284_a(GuiButton button) {
/* 57 */     if (button == this.close)
/* 58 */       Minecraft.func_71410_x().func_147108_a(null); 
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\me\Danker\gui\WarningGui.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */