/*    */ package me.Danker.handlers;
/*    */ 
/*    */ import me.Danker.commands.ToggleCommand;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.Gui;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.util.StringUtils;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ public class TextRenderer extends Gui {
/*    */   public TextRenderer(Minecraft mc, String text, int x, int y, double scale) {
/* 12 */     double scaleReset = Math.pow(scale, -1.0D);
/*    */     
/* 14 */     GL11.glScaled(scale, scale, scale);
/* 15 */     y -= mc.field_71466_p.field_78288_b;
/* 16 */     for (String line : text.split("\n")) {
/* 17 */       y = (int)(y + mc.field_71466_p.field_78288_b * scale);
/* 18 */       if (ToggleCommand.outlineTextToggled) {
/* 19 */         String noColorLine = StringUtils.func_76338_a(line);
/* 20 */         mc.field_71466_p.func_175065_a(noColorLine, ((int)Math.round(x / scale) - 1), (int)Math.round(y / scale), 0, false);
/* 21 */         mc.field_71466_p.func_175065_a(noColorLine, ((int)Math.round(x / scale) + 1), (int)Math.round(y / scale), 0, false);
/* 22 */         mc.field_71466_p.func_175065_a(noColorLine, (int)Math.round(x / scale), ((int)Math.round(y / scale) - 1), 0, false);
/* 23 */         mc.field_71466_p.func_175065_a(noColorLine, (int)Math.round(x / scale), ((int)Math.round(y / scale) + 1), 0, false);
/* 24 */         mc.field_71466_p.func_175065_a(line, (int)Math.round(x / scale), (int)Math.round(y / scale), 16777215, false);
/*    */       } else {
/* 26 */         mc.field_71466_p.func_175065_a(line, (int)Math.round(x / scale), (int)Math.round(y / scale), 16777215, true);
/*    */       } 
/*    */     } 
/* 29 */     GL11.glScaled(scaleReset, scaleReset, scaleReset);
/* 30 */     GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\me\Danker\handlers\TextRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */