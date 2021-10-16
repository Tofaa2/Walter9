/*    */ package cheaters.get.banned.utils;
/*    */ 
/*    */ import cheaters.get.banned.Shady;
/*    */ import java.awt.Color;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ 
/*    */ 
/*    */ public class FontUtils
/*    */ {
/*    */   public static String enforceWidth(String text, int width) {
/* 11 */     String[] splitText = text.split(" ");
/* 12 */     int lineWidth = 0;
/* 13 */     StringBuilder result = new StringBuilder();
/* 14 */     for (String word : splitText) {
/* 15 */       int wordWidth = getStringWidth(word);
/* 16 */       if (wordWidth + lineWidth > width) {
/* 17 */         result.append("\n");
/* 18 */         lineWidth = 0;
/*    */       } else {
/* 20 */         result.append(word).append(" ");
/* 21 */         lineWidth += wordWidth + getStringWidth(" ");
/*    */       } 
/*    */     } 
/* 24 */     return result.toString();
/*    */   }
/*    */   
/*    */   public static void drawCenteredString(String text, int x, int y) {
/* 28 */     y -= getStringHeight(text) / 2;
/* 29 */     String[] lines = text.split("\n");
/* 30 */     for (String line : lines) {
/* 31 */       drawString(line, x - getStringWidth(line) / 2, y);
/* 32 */       y += getLineHeight() + 1;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void drawString(String text, int x, int y) {
/* 37 */     String[] lines = text.split("\n");
/* 38 */     for (String line : lines) {
/* 39 */       Shady.mc.field_71466_p.func_175063_a(line, x, y, Color.WHITE.getRGB());
/* 40 */       y += getLineHeight() + 1;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static int getStringHeight(String text) {
/* 45 */     int lines = (text.split("\n")).length;
/* 46 */     return (lines > 1) ? (lines * (getLineHeight() + 1) - 1) : getLineHeight();
/*    */   }
/*    */   
/*    */   public static int getStringWidth(String text) {
/* 50 */     String[] lines = text.split("\n");
/* 51 */     int longestLine = 0;
/* 52 */     for (String line : lines) {
/* 53 */       int lineWidth = Shady.mc.field_71466_p.func_78256_a(line);
/* 54 */       if (lineWidth > longestLine) longestLine = lineWidth; 
/*    */     } 
/* 56 */     return longestLine;
/*    */   }
/*    */   
/*    */   public static int getLineHeight() {
/* 60 */     return Shady.mc.field_71466_p.field_78288_b;
/*    */   }
/*    */   
/*    */   public static void drawScaledString(String string, float scale, int x, int y) {
/* 64 */     GlStateManager.func_179094_E();
/* 65 */     GlStateManager.func_179152_a(scale, scale, scale);
/* 66 */     Shady.mc.field_71466_p.func_78276_b(string, (int)(x / scale), (int)(y / scale), -1);
/* 67 */     GlStateManager.func_179121_F();
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Desktop\ShadyAddons-2.1.0-pre2.jar!\cheaters\get\banne\\utils\FontUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */