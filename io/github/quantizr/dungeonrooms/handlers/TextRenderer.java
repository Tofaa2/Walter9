/*    */ package io.github.quantizr.dungeonrooms.handlers;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.Gui;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.util.StringUtils;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TextRenderer
/*    */   extends Gui
/*    */ {
/*    */   public static void drawText(Minecraft mc, String text, int x, int y, double scale, boolean outline) {
/* 28 */     GlStateManager.func_179094_E();
/* 29 */     GlStateManager.func_179139_a(scale, scale, scale);
/* 30 */     y -= mc.field_71466_p.field_78288_b;
/* 31 */     for (String line : text.split("\n")) {
/* 32 */       y = (int)(y + mc.field_71466_p.field_78288_b * scale);
/* 33 */       if (outline) {
/* 34 */         String noColourLine = StringUtils.func_76338_a(line);
/* 35 */         mc.field_71466_p.func_175065_a(noColourLine, ((int)Math.round(x / scale) - 1), (int)Math.round(y / scale), 0, false);
/* 36 */         mc.field_71466_p.func_175065_a(noColourLine, ((int)Math.round(x / scale) + 1), (int)Math.round(y / scale), 0, false);
/* 37 */         mc.field_71466_p.func_175065_a(noColourLine, (int)Math.round(x / scale), ((int)Math.round(y / scale) - 1), 0, false);
/* 38 */         mc.field_71466_p.func_175065_a(noColourLine, (int)Math.round(x / scale), ((int)Math.round(y / scale) + 1), 0, false);
/* 39 */         mc.field_71466_p.func_175065_a(line, (int)Math.round(x / scale), (int)Math.round(y / scale), 16777215, false);
/*    */       } else {
/* 41 */         mc.field_71466_p.func_175065_a(line, (int)Math.round(x / scale), (int)Math.round(y / scale), 16777215, true);
/*    */       } 
/*    */     } 
/* 44 */     GlStateManager.func_179121_F();
/* 45 */     GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\io\github\quantizr\dungeonrooms\handlers\TextRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */