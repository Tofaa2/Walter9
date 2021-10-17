/*     */ package io.github.quantizr.dungeonrooms.utils;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WaypointUtils
/*     */ {
/*  34 */   private static final ResourceLocation beaconBeam = new ResourceLocation("textures/entity/beacon_beam.png");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void renderBeaconBeam(double x, double y, double z, int rgb, float alphaMultiplier, float partialTicks) {
/*  42 */     int height = 300;
/*  43 */     int bottomOffset = 0;
/*  44 */     int topOffset = bottomOffset + height;
/*     */     
/*  46 */     Tessellator tessellator = Tessellator.func_178181_a();
/*  47 */     WorldRenderer worldrenderer = tessellator.func_178180_c();
/*     */     
/*  49 */     Minecraft.func_71410_x().func_110434_K().func_110577_a(beaconBeam);
/*  50 */     GL11.glTexParameterf(3553, 10242, 10497.0F);
/*  51 */     GL11.glTexParameterf(3553, 10243, 10497.0F);
/*  52 */     GlStateManager.func_179140_f();
/*  53 */     GlStateManager.func_179089_o();
/*  54 */     GlStateManager.func_179098_w();
/*  55 */     GlStateManager.func_179120_a(770, 1, 1, 0);
/*  56 */     GlStateManager.func_179147_l();
/*  57 */     GlStateManager.func_179120_a(770, 771, 1, 0);
/*     */     
/*  59 */     double time = (Minecraft.func_71410_x()).field_71441_e.func_82737_E() + partialTicks;
/*  60 */     double d1 = MathHelper.func_181162_h(-time * 0.2D - MathHelper.func_76128_c(-time * 0.1D));
/*     */     
/*  62 */     float r = (rgb >> 16 & 0xFF) / 255.0F;
/*  63 */     float g = (rgb >> 8 & 0xFF) / 255.0F;
/*  64 */     float b = (rgb & 0xFF) / 255.0F;
/*  65 */     double d2 = time * 0.025D * -1.5D;
/*  66 */     double d4 = 0.5D + Math.cos(d2 + 2.356194490192345D) * 0.2D;
/*  67 */     double d5 = 0.5D + Math.sin(d2 + 2.356194490192345D) * 0.2D;
/*  68 */     double d6 = 0.5D + Math.cos(d2 + 0.7853981633974483D) * 0.2D;
/*  69 */     double d7 = 0.5D + Math.sin(d2 + 0.7853981633974483D) * 0.2D;
/*  70 */     double d8 = 0.5D + Math.cos(d2 + 3.9269908169872414D) * 0.2D;
/*  71 */     double d9 = 0.5D + Math.sin(d2 + 3.9269908169872414D) * 0.2D;
/*  72 */     double d10 = 0.5D + Math.cos(d2 + 5.497787143782138D) * 0.2D;
/*  73 */     double d11 = 0.5D + Math.sin(d2 + 5.497787143782138D) * 0.2D;
/*  74 */     double d14 = -1.0D + d1;
/*  75 */     double d15 = height * 2.5D + d14;
/*  76 */     worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181709_i);
/*  77 */     worldrenderer.func_181662_b(x + d4, y + topOffset, z + d5).func_181673_a(1.0D, d15).func_181666_a(r, g, b, alphaMultiplier).func_181675_d();
/*  78 */     worldrenderer.func_181662_b(x + d4, y + bottomOffset, z + d5).func_181673_a(1.0D, d14).func_181666_a(r, g, b, 1.0F).func_181675_d();
/*  79 */     worldrenderer.func_181662_b(x + d6, y + bottomOffset, z + d7).func_181673_a(0.0D, d14).func_181666_a(r, g, b, 1.0F).func_181675_d();
/*  80 */     worldrenderer.func_181662_b(x + d6, y + topOffset, z + d7).func_181673_a(0.0D, d15).func_181666_a(r, g, b, alphaMultiplier).func_181675_d();
/*  81 */     worldrenderer.func_181662_b(x + d10, y + topOffset, z + d11).func_181673_a(1.0D, d15).func_181666_a(r, g, b, alphaMultiplier).func_181675_d();
/*  82 */     worldrenderer.func_181662_b(x + d10, y + bottomOffset, z + d11).func_181673_a(1.0D, d14).func_181666_a(r, g, b, 1.0F).func_181675_d();
/*  83 */     worldrenderer.func_181662_b(x + d8, y + bottomOffset, z + d9).func_181673_a(0.0D, d14).func_181666_a(r, g, b, 1.0F).func_181675_d();
/*  84 */     worldrenderer.func_181662_b(x + d8, y + topOffset, z + d9).func_181673_a(0.0D, d15).func_181666_a(r, g, b, alphaMultiplier).func_181675_d();
/*  85 */     worldrenderer.func_181662_b(x + d6, y + topOffset, z + d7).func_181673_a(1.0D, d15).func_181666_a(r, g, b, alphaMultiplier).func_181675_d();
/*  86 */     worldrenderer.func_181662_b(x + d6, y + bottomOffset, z + d7).func_181673_a(1.0D, d14).func_181666_a(r, g, b, 1.0F).func_181675_d();
/*  87 */     worldrenderer.func_181662_b(x + d10, y + bottomOffset, z + d11).func_181673_a(0.0D, d14).func_181666_a(r, g, b, 1.0F).func_181675_d();
/*  88 */     worldrenderer.func_181662_b(x + d10, y + topOffset, z + d11).func_181673_a(0.0D, d15).func_181666_a(r, g, b, alphaMultiplier).func_181675_d();
/*  89 */     worldrenderer.func_181662_b(x + d8, y + topOffset, z + d9).func_181673_a(1.0D, d15).func_181666_a(r, g, b, alphaMultiplier).func_181675_d();
/*  90 */     worldrenderer.func_181662_b(x + d8, y + bottomOffset, z + d9).func_181673_a(1.0D, d14).func_181666_a(r, g, b, 1.0F).func_181675_d();
/*  91 */     worldrenderer.func_181662_b(x + d4, y + bottomOffset, z + d5).func_181673_a(0.0D, d14).func_181666_a(r, g, b, 1.0F).func_181675_d();
/*  92 */     worldrenderer.func_181662_b(x + d4, y + topOffset, z + d5).func_181673_a(0.0D, d15).func_181666_a(r, g, b, alphaMultiplier).func_181675_d();
/*  93 */     tessellator.func_78381_a();
/*     */     
/*  95 */     GlStateManager.func_179129_p();
/*  96 */     double d12 = -1.0D + d1;
/*  97 */     double d13 = height + d12;
/*     */     
/*  99 */     worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181709_i);
/* 100 */     worldrenderer.func_181662_b(x + 0.2D, y + topOffset, z + 0.2D).func_181673_a(1.0D, d13).func_181666_a(r, g, b, 0.25F * alphaMultiplier).func_181675_d();
/* 101 */     worldrenderer.func_181662_b(x + 0.2D, y + bottomOffset, z + 0.2D).func_181673_a(1.0D, d12).func_181666_a(r, g, b, 0.25F).func_181675_d();
/* 102 */     worldrenderer.func_181662_b(x + 0.8D, y + bottomOffset, z + 0.2D).func_181673_a(0.0D, d12).func_181666_a(r, g, b, 0.25F).func_181675_d();
/* 103 */     worldrenderer.func_181662_b(x + 0.8D, y + topOffset, z + 0.2D).func_181673_a(0.0D, d13).func_181666_a(r, g, b, 0.25F * alphaMultiplier).func_181675_d();
/* 104 */     worldrenderer.func_181662_b(x + 0.8D, y + topOffset, z + 0.8D).func_181673_a(1.0D, d13).func_181666_a(r, g, b, 0.25F * alphaMultiplier).func_181675_d();
/* 105 */     worldrenderer.func_181662_b(x + 0.8D, y + bottomOffset, z + 0.8D).func_181673_a(1.0D, d12).func_181666_a(r, g, b, 0.25F).func_181675_d();
/* 106 */     worldrenderer.func_181662_b(x + 0.2D, y + bottomOffset, z + 0.8D).func_181673_a(0.0D, d12).func_181666_a(r, g, b, 0.25F).func_181675_d();
/* 107 */     worldrenderer.func_181662_b(x + 0.2D, y + topOffset, z + 0.8D).func_181673_a(0.0D, d13).func_181666_a(r, g, b, 0.25F * alphaMultiplier).func_181675_d();
/* 108 */     worldrenderer.func_181662_b(x + 0.8D, y + topOffset, z + 0.2D).func_181673_a(1.0D, d13).func_181666_a(r, g, b, 0.25F * alphaMultiplier).func_181675_d();
/* 109 */     worldrenderer.func_181662_b(x + 0.8D, y + bottomOffset, z + 0.2D).func_181673_a(1.0D, d12).func_181666_a(r, g, b, 0.25F).func_181675_d();
/* 110 */     worldrenderer.func_181662_b(x + 0.8D, y + bottomOffset, z + 0.8D).func_181673_a(0.0D, d12).func_181666_a(r, g, b, 0.25F).func_181675_d();
/* 111 */     worldrenderer.func_181662_b(x + 0.8D, y + topOffset, z + 0.8D).func_181673_a(0.0D, d13).func_181666_a(r, g, b, 0.25F * alphaMultiplier).func_181675_d();
/* 112 */     worldrenderer.func_181662_b(x + 0.2D, y + topOffset, z + 0.8D).func_181673_a(1.0D, d13).func_181666_a(r, g, b, 0.25F * alphaMultiplier).func_181675_d();
/* 113 */     worldrenderer.func_181662_b(x + 0.2D, y + bottomOffset, z + 0.8D).func_181673_a(1.0D, d12).func_181666_a(r, g, b, 0.25F).func_181675_d();
/* 114 */     worldrenderer.func_181662_b(x + 0.2D, y + bottomOffset, z + 0.2D).func_181673_a(0.0D, d12).func_181666_a(r, g, b, 0.25F).func_181675_d();
/* 115 */     worldrenderer.func_181662_b(x + 0.2D, y + topOffset, z + 0.2D).func_181673_a(0.0D, d13).func_181666_a(r, g, b, 0.25F * alphaMultiplier).func_181675_d();
/* 116 */     tessellator.func_78381_a();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void drawFilledBoundingBox(AxisAlignedBB aabb, Color c, float alphaMultiplier) {
/* 125 */     GlStateManager.func_179147_l();
/* 126 */     GlStateManager.func_179140_f();
/* 127 */     GlStateManager.func_179120_a(770, 771, 1, 0);
/* 128 */     GlStateManager.func_179090_x();
/*     */     
/* 130 */     Tessellator tessellator = Tessellator.func_178181_a();
/* 131 */     WorldRenderer worldrenderer = tessellator.func_178180_c();
/*     */     
/* 133 */     GlStateManager.func_179131_c(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, c.getAlpha() / 255.0F * alphaMultiplier);
/*     */ 
/*     */     
/* 136 */     worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
/* 137 */     worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72339_c).func_181675_d();
/* 138 */     worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72338_b, aabb.field_72339_c).func_181675_d();
/* 139 */     worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72338_b, aabb.field_72334_f).func_181675_d();
/* 140 */     worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72334_f).func_181675_d();
/* 141 */     tessellator.func_78381_a();
/* 142 */     worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
/* 143 */     worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72334_f).func_181675_d();
/* 144 */     worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72334_f).func_181675_d();
/* 145 */     worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72339_c).func_181675_d();
/* 146 */     worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72339_c).func_181675_d();
/* 147 */     tessellator.func_78381_a();
/*     */ 
/*     */     
/* 150 */     GlStateManager.func_179131_c(c.getRed() / 255.0F * 0.8F, c.getGreen() / 255.0F * 0.8F, c.getBlue() / 255.0F * 0.8F, c.getAlpha() / 255.0F * alphaMultiplier);
/*     */ 
/*     */     
/* 153 */     worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
/* 154 */     worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72334_f).func_181675_d();
/* 155 */     worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72334_f).func_181675_d();
/* 156 */     worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72339_c).func_181675_d();
/* 157 */     worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72339_c).func_181675_d();
/* 158 */     tessellator.func_78381_a();
/* 159 */     worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
/* 160 */     worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72338_b, aabb.field_72339_c).func_181675_d();
/* 161 */     worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72339_c).func_181675_d();
/* 162 */     worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72334_f).func_181675_d();
/* 163 */     worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72338_b, aabb.field_72334_f).func_181675_d();
/* 164 */     tessellator.func_78381_a();
/*     */ 
/*     */     
/* 167 */     GlStateManager.func_179131_c(c.getRed() / 255.0F * 0.9F, c.getGreen() / 255.0F * 0.9F, c.getBlue() / 255.0F * 0.9F, c.getAlpha() / 255.0F * alphaMultiplier);
/*     */     
/* 169 */     worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
/* 170 */     worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72339_c).func_181675_d();
/* 171 */     worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72339_c).func_181675_d();
/* 172 */     worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72338_b, aabb.field_72339_c).func_181675_d();
/* 173 */     worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72339_c).func_181675_d();
/* 174 */     tessellator.func_78381_a();
/* 175 */     worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
/* 176 */     worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72338_b, aabb.field_72334_f).func_181675_d();
/* 177 */     worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72338_b, aabb.field_72334_f).func_181675_d();
/* 178 */     worldrenderer.func_181662_b(aabb.field_72336_d, aabb.field_72337_e, aabb.field_72334_f).func_181675_d();
/* 179 */     worldrenderer.func_181662_b(aabb.field_72340_a, aabb.field_72337_e, aabb.field_72334_f).func_181675_d();
/* 180 */     tessellator.func_78381_a();
/* 181 */     GlStateManager.func_179098_w();
/* 182 */     GlStateManager.func_179084_k();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void renderWaypointText(String str, BlockPos loc, float partialTicks) {
/* 191 */     GlStateManager.func_179092_a(516, 0.1F);
/*     */     
/* 193 */     GlStateManager.func_179094_E();
/*     */     
/* 195 */     Entity viewer = Minecraft.func_71410_x().func_175606_aa();
/* 196 */     double viewerX = viewer.field_70142_S + (viewer.field_70165_t - viewer.field_70142_S) * partialTicks;
/* 197 */     double viewerY = viewer.field_70137_T + (viewer.field_70163_u - viewer.field_70137_T) * partialTicks;
/* 198 */     double viewerZ = viewer.field_70136_U + (viewer.field_70161_v - viewer.field_70136_U) * partialTicks;
/*     */     
/* 200 */     double x = loc.func_177958_n() + 0.5D - viewerX;
/* 201 */     double y = loc.func_177956_o() - viewerY - viewer.func_70047_e();
/* 202 */     double z = loc.func_177952_p() + 0.5D - viewerZ;
/*     */     
/* 204 */     double distSq = x * x + y * y + z * z;
/* 205 */     double dist = Math.sqrt(distSq);
/* 206 */     if (distSq > 144.0D) {
/* 207 */       x *= 12.0D / dist;
/* 208 */       y *= 12.0D / dist;
/* 209 */       z *= 12.0D / dist;
/*     */     } 
/* 211 */     GlStateManager.func_179137_b(x, y, z);
/* 212 */     GlStateManager.func_179109_b(0.0F, viewer.func_70047_e(), 0.0F);
/*     */     
/* 214 */     drawNametag(str);
/*     */     
/* 216 */     GlStateManager.func_179114_b(-(Minecraft.func_71410_x().func_175598_ae()).field_78735_i, 0.0F, 1.0F, 0.0F);
/* 217 */     GlStateManager.func_179114_b((Minecraft.func_71410_x().func_175598_ae()).field_78732_j, 1.0F, 0.0F, 0.0F);
/* 218 */     GlStateManager.func_179109_b(0.0F, -0.25F, 0.0F);
/* 219 */     GlStateManager.func_179114_b(-(Minecraft.func_71410_x().func_175598_ae()).field_78732_j, 1.0F, 0.0F, 0.0F);
/* 220 */     GlStateManager.func_179114_b((Minecraft.func_71410_x().func_175598_ae()).field_78735_i, 0.0F, 1.0F, 0.0F);
/*     */     
/* 222 */     drawNametag(EnumChatFormatting.YELLOW.toString() + Math.round(dist) + "m");
/*     */     
/* 224 */     GlStateManager.func_179121_F();
/*     */     
/* 226 */     GlStateManager.func_179140_f();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void drawNametag(String str) {
/* 235 */     FontRenderer fontrenderer = (Minecraft.func_71410_x()).field_71466_p;
/* 236 */     float f = 1.6F;
/* 237 */     float f1 = 0.016666668F * f;
/* 238 */     GlStateManager.func_179094_E();
/* 239 */     GL11.glNormal3f(0.0F, 1.0F, 0.0F);
/* 240 */     GlStateManager.func_179114_b(-(Minecraft.func_71410_x().func_175598_ae()).field_78735_i, 0.0F, 1.0F, 0.0F);
/* 241 */     GlStateManager.func_179114_b((Minecraft.func_71410_x().func_175598_ae()).field_78732_j, 1.0F, 0.0F, 0.0F);
/* 242 */     GlStateManager.func_179152_a(-f1, -f1, f1);
/* 243 */     GlStateManager.func_179140_f();
/* 244 */     GlStateManager.func_179132_a(false);
/* 245 */     GlStateManager.func_179097_i();
/* 246 */     GlStateManager.func_179147_l();
/* 247 */     GlStateManager.func_179120_a(770, 771, 1, 0);
/* 248 */     Tessellator tessellator = Tessellator.func_178181_a();
/* 249 */     WorldRenderer worldrenderer = tessellator.func_178180_c();
/* 250 */     int i = 0;
/*     */     
/* 252 */     int j = fontrenderer.func_78256_a(str) / 2;
/* 253 */     GlStateManager.func_179090_x();
/* 254 */     worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181706_f);
/* 255 */     worldrenderer.func_181662_b((-j - 1), (-1 + i), 0.0D).func_181666_a(0.0F, 0.0F, 0.0F, 0.25F).func_181675_d();
/* 256 */     worldrenderer.func_181662_b((-j - 1), (8 + i), 0.0D).func_181666_a(0.0F, 0.0F, 0.0F, 0.25F).func_181675_d();
/* 257 */     worldrenderer.func_181662_b((j + 1), (8 + i), 0.0D).func_181666_a(0.0F, 0.0F, 0.0F, 0.25F).func_181675_d();
/* 258 */     worldrenderer.func_181662_b((j + 1), (-1 + i), 0.0D).func_181666_a(0.0F, 0.0F, 0.0F, 0.25F).func_181675_d();
/* 259 */     tessellator.func_78381_a();
/* 260 */     GlStateManager.func_179098_w();
/* 261 */     fontrenderer.func_78276_b(str, -fontrenderer.func_78256_a(str) / 2, i, 553648127);
/* 262 */     GlStateManager.func_179132_a(true);
/*     */     
/* 264 */     fontrenderer.func_78276_b(str, -fontrenderer.func_78256_a(str) / 2, i, -1);
/*     */     
/* 266 */     GlStateManager.func_179126_j();
/* 267 */     GlStateManager.func_179147_l();
/* 268 */     GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
/* 269 */     GlStateManager.func_179121_F();
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\io\github\quantizr\dungeonroom\\utils\WaypointUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */