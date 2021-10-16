/*    */ package cheaters.get.banned.config.components;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.audio.SoundHandler;
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ 
/*    */ public class Scrollbar
/*    */   extends GuiButton {
/*    */   public Scrollbar(int viewport, int contentHeight, int scrollOffset, int x, boolean scrolling) {
/* 10 */     super(0, x, 100 + scrollOffset, "");
/* 11 */     this.field_146120_f = 5;
/* 12 */     this.field_146121_g = (contentHeight > viewport) ? Math.round(viewport / contentHeight * viewport) : 0;
/* 13 */     this.field_146123_n = scrolling;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_146112_a(Minecraft mc, int mouseX, int mouseY) {
/* 18 */     this.field_146123_n = func_146116_c(mc, mouseX, mouseY);
/* 19 */     func_73734_a(this.field_146128_h, this.field_146129_i, this.field_146128_h + this.field_146120_f, this.field_146129_i + this.field_146121_g, this.field_146123_n ? ConfigInput.white.getRGB() : ConfigInput.transparent.getRGB());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_146116_c(Minecraft mc, int mouseX, int mouseY) {
/* 24 */     return (mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g);
/*    */   }
/*    */   
/*    */   public void func_146113_a(SoundHandler soundHandlerIn) {}
/*    */ }


/* Location:              C:\Users\amine\Desktop\ShadyAddons-2.1.0-pre2.jar!\cheaters\get\banned\config\components\Scrollbar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */