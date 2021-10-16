/*     */ package cheaters.get.banned.utils;
/*     */ 
/*     */ import cheaters.get.banned.Shady;
/*     */ import java.awt.Color;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.Gui;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EnumPlayerModelParts;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Vec3;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ public class RenderUtils
/*     */ {
/*     */   public static void drawFilledBoundingBox(AxisAlignedBB aabb, Color c, float alphaMultiplier) {
/*  26 */     GlStateManager.func_179147_l();
/*  27 */     GlStateManager.func_179140_f();
/*  28 */     GlStateManager.func_179120_a(770, 771, 1, 0);
/*  29 */     GlStateManager.func_179090_x();
/*     */     
/*  31 */     Tessellator tessellator = Tessellator.func_178181_a();
/*  32 */     WorldRenderer worldrenderer = tessellator.func_178180_c();
/*     */     
/*  34 */     GlStateManager.func_179131_c(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, c.getAlpha() / 255.0F * alphaMultiplier);
/*     */ 
/*     */     
/*  37 */     worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
/*  38 */     worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72339_c).func_181675_d();
/*  39 */     worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72338_b, aabb.field_72339_c).func_181675_d();
/*  40 */     worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72338_b, aabb.field_72334_f).func_181675_d();
/*  41 */     worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72334_f).func_181675_d();
/*  42 */     tessellator.func_78381_a();
/*  43 */     worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
/*  44 */     worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72334_f).func_181675_d();
/*  45 */     worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72334_f).func_181675_d();
/*  46 */     worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72339_c).func_181675_d();
/*  47 */     worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72339_c).func_181675_d();
/*  48 */     tessellator.func_78381_a();
/*     */ 
/*     */     
/*  51 */     GlStateManager.func_179131_c(c.getRed() / 255.0F * 0.8F, c.getGreen() / 255.0F * 0.8F, c.getBlue() / 255.0F * 0.8F, c.getAlpha() / 255.0F * alphaMultiplier);
/*     */ 
/*     */     
/*  54 */     worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
/*  55 */     worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72334_f).func_181675_d();
/*  56 */     worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72334_f).func_181675_d();
/*  57 */     worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72339_c).func_181675_d();
/*  58 */     worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72339_c).func_181675_d();
/*  59 */     tessellator.func_78381_a();
/*  60 */     worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
/*  61 */     worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72338_b, aabb.field_72339_c).func_181675_d();
/*  62 */     worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72339_c).func_181675_d();
/*  63 */     worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72334_f).func_181675_d();
/*  64 */     worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72338_b, aabb.field_72334_f).func_181675_d();
/*  65 */     tessellator.func_78381_a();
/*     */ 
/*     */     
/*  68 */     GlStateManager.func_179131_c(c.getRed() / 255.0F * 0.9F, c.getGreen() / 255.0F * 0.9F, c.getBlue() / 255.0F * 0.9F, c.getAlpha() / 255.0F * alphaMultiplier);
/*     */     
/*  70 */     worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
/*  71 */     worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72339_c).func_181675_d();
/*  72 */     worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72339_c).func_181675_d();
/*  73 */     worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72338_b, aabb.field_72339_c).func_181675_d();
/*  74 */     worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72339_c).func_181675_d();
/*  75 */     tessellator.func_78381_a();
/*  76 */     worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
/*  77 */     worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72334_f).func_181675_d();
/*  78 */     worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72338_b, aabb.field_72334_f).func_181675_d();
/*  79 */     worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72334_f).func_181675_d();
/*  80 */     worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72334_f).func_181675_d();
/*  81 */     tessellator.func_78381_a();
/*  82 */     GlStateManager.func_179098_w();
/*  83 */     GlStateManager.func_179084_k();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void highlightBlock(BlockPos pos, Color color, float partialTicks) {
/*  91 */     Entity viewer = Minecraft.func_71410_x().func_175606_aa();
/*  92 */     double viewerX = viewer.field_70142_S + (viewer.field_70165_t - viewer.field_70142_S) * partialTicks;
/*  93 */     double viewerY = viewer.field_70137_T + (viewer.field_70163_u - viewer.field_70137_T) * partialTicks;
/*  94 */     double viewerZ = viewer.field_70136_U + (viewer.field_70161_v - viewer.field_70136_U) * partialTicks;
/*     */     
/*  96 */     double x = pos.func_177958_n() - viewerX;
/*  97 */     double y = pos.func_177956_o() - viewerY;
/*  98 */     double z = pos.func_177952_p() - viewerZ;
/*     */     
/* 100 */     GlStateManager.func_179097_i();
/* 101 */     GlStateManager.func_179129_p();
/* 102 */     GlStateManager.func_179140_f();
/* 103 */     drawFilledBoundingBox(new AxisAlignedBB(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D), color, 1.0F);
/* 104 */     GlStateManager.func_179145_e();
/* 105 */     GlStateManager.func_179126_j();
/* 106 */     GlStateManager.func_179089_o();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void draw3DLine(Vec3 pos1, Vec3 pos2, Color color, int lineWidth, boolean depth, float partialTicks) {
/* 114 */     Entity render = Minecraft.func_71410_x().func_175606_aa();
/* 115 */     WorldRenderer worldRenderer = Tessellator.func_178181_a().func_178180_c();
/*     */     
/* 117 */     double realX = render.field_70142_S + (render.field_70165_t - render.field_70142_S) * partialTicks;
/* 118 */     double realY = render.field_70137_T + (render.field_70163_u - render.field_70137_T) * partialTicks;
/* 119 */     double realZ = render.field_70136_U + (render.field_70161_v - render.field_70136_U) * partialTicks;
/*     */     
/* 121 */     GlStateManager.func_179094_E();
/* 122 */     GlStateManager.func_179137_b(-realX, -realY, -realZ);
/* 123 */     GlStateManager.func_179090_x();
/* 124 */     GlStateManager.func_179147_l();
/* 125 */     GlStateManager.func_179118_c();
/* 126 */     GlStateManager.func_179120_a(770, 771, 1, 0);
/* 127 */     GL11.glLineWidth(lineWidth);
/* 128 */     if (!depth) {
/* 129 */       GL11.glDisable(2929);
/* 130 */       GlStateManager.func_179132_a(false);
/*     */     } 
/* 132 */     GlStateManager.func_179131_c(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, color.getAlpha() / 255.0F);
/* 133 */     worldRenderer.func_181668_a(3, DefaultVertexFormats.field_181705_e);
/*     */     
/* 135 */     worldRenderer.func_181662_b(pos1.field_72450_a, pos1.field_72448_b, pos1.field_72449_c).func_181675_d();
/* 136 */     worldRenderer.func_181662_b(pos2.field_72450_a, pos2.field_72448_b, pos2.field_72449_c).func_181675_d();
/* 137 */     Tessellator.func_178181_a().func_78381_a();
/*     */     
/* 139 */     GlStateManager.func_179137_b(realX, realY, realZ);
/* 140 */     if (!depth) {
/* 141 */       GL11.glEnable(2929);
/* 142 */       GlStateManager.func_179132_a(true);
/*     */     } 
/* 144 */     GlStateManager.func_179084_k();
/* 145 */     GlStateManager.func_179141_d();
/* 146 */     GlStateManager.func_179098_w();
/* 147 */     GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/* 148 */     GlStateManager.func_179121_F();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void renderWaypointText(String str, double X, double Y, double Z, float partialTicks) {
/* 156 */     GlStateManager.func_179092_a(516, 0.1F);
/*     */     
/* 158 */     GlStateManager.func_179094_E();
/*     */     
/* 160 */     Entity viewer = Minecraft.func_71410_x().func_175606_aa();
/* 161 */     double viewerX = viewer.field_70142_S + (viewer.field_70165_t - viewer.field_70142_S) * partialTicks;
/* 162 */     double viewerY = viewer.field_70137_T + (viewer.field_70163_u - viewer.field_70137_T) * partialTicks;
/* 163 */     double viewerZ = viewer.field_70136_U + (viewer.field_70161_v - viewer.field_70136_U) * partialTicks;
/*     */     
/* 165 */     double x = X - viewerX;
/* 166 */     double y = Y - viewerY - viewer.func_70047_e();
/* 167 */     double z = Z - viewerZ;
/*     */     
/* 169 */     double distSq = x * x + y * y + z * z;
/* 170 */     double dist = Math.sqrt(distSq);
/* 171 */     if (distSq > 144.0D) {
/* 172 */       x *= 12.0D / dist;
/* 173 */       y *= 12.0D / dist;
/* 174 */       z *= 12.0D / dist;
/*     */     } 
/* 176 */     GlStateManager.func_179137_b(x, y, z);
/* 177 */     GlStateManager.func_179109_b(0.0F, viewer.func_70047_e(), 0.0F);
/*     */     
/* 179 */     drawNametag(str);
/*     */     
/* 181 */     GlStateManager.func_179114_b(-(Minecraft.func_71410_x().func_175598_ae()).field_78735_i, 0.0F, 1.0F, 0.0F);
/* 182 */     GlStateManager.func_179114_b((Minecraft.func_71410_x().func_175598_ae()).field_78732_j, 1.0F, 0.0F, 0.0F);
/* 183 */     GlStateManager.func_179109_b(0.0F, -0.25F, 0.0F);
/* 184 */     GlStateManager.func_179114_b(-(Minecraft.func_71410_x().func_175598_ae()).field_78732_j, 1.0F, 0.0F, 0.0F);
/* 185 */     GlStateManager.func_179114_b((Minecraft.func_71410_x().func_175598_ae()).field_78735_i, 0.0F, 1.0F, 0.0F);
/*     */     
/* 187 */     drawNametag(EnumChatFormatting.YELLOW.toString() + Math.round(dist) + " blocks");
/*     */     
/* 189 */     GlStateManager.func_179121_F();
/*     */     
/* 191 */     GlStateManager.func_179140_f();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void drawNametag(String str) {
/* 199 */     FontRenderer fontrenderer = (Minecraft.func_71410_x()).field_71466_p;
/* 200 */     float f = 1.6F;
/* 201 */     float f1 = 0.016666668F * f;
/* 202 */     GlStateManager.func_179094_E();
/* 203 */     GL11.glNormal3f(0.0F, 1.0F, 0.0F);
/* 204 */     GlStateManager.func_179114_b(-(Minecraft.func_71410_x().func_175598_ae()).field_78735_i, 0.0F, 1.0F, 0.0F);
/* 205 */     GlStateManager.func_179114_b((Minecraft.func_71410_x().func_175598_ae()).field_78732_j, 1.0F, 0.0F, 0.0F);
/* 206 */     GlStateManager.func_179152_a(-f1, -f1, f1);
/* 207 */     GlStateManager.func_179140_f();
/* 208 */     GlStateManager.func_179132_a(false);
/* 209 */     GlStateManager.func_179097_i();
/* 210 */     GlStateManager.func_179147_l();
/* 211 */     GlStateManager.func_179120_a(770, 771, 1, 0);
/* 212 */     Tessellator tessellator = Tessellator.func_178181_a();
/* 213 */     WorldRenderer worldrenderer = tessellator.func_178180_c();
/* 214 */     int i = 0;
/*     */     
/* 216 */     int j = fontrenderer.func_78256_a(str) / 2;
/* 217 */     GlStateManager.func_179090_x();
/* 218 */     worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181706_f);
/* 219 */     worldrenderer.func_181662_b((-j - 1), (-1 + i), 0.0D).func_181666_a(0.0F, 0.0F, 0.0F, 0.25F).func_181675_d();
/* 220 */     worldrenderer.func_181662_b((-j - 1), (8 + i), 0.0D).func_181666_a(0.0F, 0.0F, 0.0F, 0.25F).func_181675_d();
/* 221 */     worldrenderer.func_181662_b((j + 1), (8 + i), 0.0D).func_181666_a(0.0F, 0.0F, 0.0F, 0.25F).func_181675_d();
/* 222 */     worldrenderer.func_181662_b((j + 1), (-1 + i), 0.0D).func_181666_a(0.0F, 0.0F, 0.0F, 0.25F).func_181675_d();
/* 223 */     tessellator.func_78381_a();
/* 224 */     GlStateManager.func_179098_w();
/* 225 */     fontrenderer.func_78276_b(str, -fontrenderer.func_78256_a(str) / 2, i, 553648127);
/* 226 */     GlStateManager.func_179132_a(true);
/*     */     
/* 228 */     fontrenderer.func_78276_b(str, -fontrenderer.func_78256_a(str) / 2, i, -1);
/*     */     
/* 230 */     GlStateManager.func_179126_j();
/* 231 */     GlStateManager.func_179147_l();
/* 232 */     GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/* 233 */     GlStateManager.func_179121_F();
/*     */   }
/*     */   
/*     */   public static void bindColor(Color color) {
/* 237 */     GlStateManager.func_179131_c(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, color.getAlpha() / 255.0F);
/*     */   }
/*     */   
/*     */   public static void drawRotatedTexture(ResourceLocation resourceLocation, int x, int y, int width, int height, int angle) {
/* 241 */     drawRotatedTexture(resourceLocation, x, y, width, height, width, height, 0, 0, angle);
/*     */   }
/*     */   
/*     */   public static void drawRotatedTexture(ResourceLocation resourceLocation, int x, int y, int width, int height, int textureWidth, int textureHeight, int textureX, int textureY, int angle) {
/* 245 */     GlStateManager.func_179094_E();
/*     */     
/* 247 */     GlStateManager.func_179109_b(x + width / 2.0F, y + height / 2.0F, 0.0F);
/* 248 */     GlStateManager.func_179114_b(angle, 0.0F, 0.0F, 1.0F);
/* 249 */     GlStateManager.func_179109_b(-x - width / 2.0F, -y - height / 2.0F, 0.0F);
/*     */     
/* 251 */     Shady.mc.func_110434_K().func_110577_a(resourceLocation);
/* 252 */     Gui.func_146110_a(x, y, textureX, textureY, width, height, textureWidth, textureHeight);
/*     */     
/* 254 */     GlStateManager.func_179121_F();
/*     */   }
/*     */   
/*     */   public static void drawPlayerIcon(EntityPlayer player, int size, int x, int y) {
/* 258 */     Shady.mc.func_110434_K().func_110577_a(Shady.mc.func_147114_u().func_175102_a(player.func_110124_au()).func_178837_g());
/*     */     
/* 260 */     Gui.func_152125_a(x, y, 8.0F, 8.0F, 8, 8, size, size, 64.0F, 64.0F);
/*     */     
/* 262 */     if (player.func_175148_a(EnumPlayerModelParts.HAT))
/* 263 */       Gui.func_152125_a(x, y, 40.0F, 8.0F, 8, 8, size, size, 64.0F, 64.0F); 
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Desktop\ShadyAddons-2.1.0-pre2.jar!\cheaters\get\banne\\utils\RenderUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */