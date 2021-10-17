/*    */ package me.Danker.gui.buttons;
/*    */ 
/*    */ import me.Danker.handlers.TextRenderer;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.audio.SoundHandler;
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ 
/*    */ public class LocationButton
/*    */   extends GuiButton {
/*    */   private int x;
/*    */   private int y;
/*    */   private double scale;
/*    */   private String text;
/*    */   private String text2;
/*    */   private Integer text2Offset;
/*    */   
/*    */   public LocationButton(int buttonId, int x, int y, double width, double height, double scale, String text, String text2, Integer text2Offset) {
/* 18 */     super(buttonId, x, y, text);
/* 19 */     this.x = x;
/* 20 */     this.y = y;
/* 21 */     this.field_146120_f = (int)width;
/* 22 */     this.field_146121_g = (int)height;
/* 23 */     this.scale = scale;
/* 24 */     this.text = text;
/* 25 */     this.text2 = text2;
/* 26 */     this.text2Offset = text2Offset;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_146112_a(Minecraft mc, int mouseX, int mouseY) {
/*    */     String[] splitText;
/* 32 */     if (this.text2 == null) {
/* 33 */       splitText = this.text.split("\n");
/*    */     } else {
/* 35 */       splitText = this.text2.split("\n");
/*    */     } 
/*    */     
/* 38 */     int longestText = -1;
/* 39 */     for (String s : splitText) {
/* 40 */       int stringLength = mc.field_71466_p.func_78256_a(s);
/* 41 */       if (stringLength > longestText) {
/* 42 */         longestText = stringLength;
/*    */       }
/*    */     } 
/*    */     
/* 46 */     if (this.text2 == null) {
/* 47 */       func_73734_a(this.x - 2, this.y - 2, (int)(this.x + longestText * this.scale + 3.0D), (int)(this.y + (splitText.length * 9 + 3) * this.scale), 1087624147);
/*    */     } else {
/* 49 */       func_73734_a(this.x - 2, this.y - 2, (int)(this.x + (longestText + this.text2Offset.intValue()) * this.scale + 3.0D), (int)(this.y + (splitText.length * 9 + 3) * this.scale), 1087624147);
/* 50 */       new TextRenderer(mc, this.text2, (int)(this.x + this.text2Offset.intValue() * this.scale), this.y, this.scale);
/*    */     } 
/* 52 */     new TextRenderer(mc, this.text, this.x, this.y, this.scale);
/*    */   }
/*    */   
/*    */   public void func_146113_a(SoundHandler soundHandler) {}
/*    */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\me\Danker\gui\buttons\LocationButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */