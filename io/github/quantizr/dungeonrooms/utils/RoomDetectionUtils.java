/*     */ package io.github.quantizr.dungeonrooms.utils;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RoomDetectionUtils
/*     */ {
/*     */   public static final double DEG_TO_RAD = 0.017453292519943295D;
/*     */   public static final double RAD_TO_DEG = 57.29577951308232D;
/*     */   
/*     */   public static Vec3 getVectorFromRotation(float yaw, float pitch) {
/*  41 */     float f = MathHelper.func_76134_b(-yaw * 0.017453292F - 3.1415927F);
/*  42 */     float f1 = MathHelper.func_76126_a(-yaw * 0.017453292F - 3.1415927F);
/*  43 */     float f2 = -MathHelper.func_76134_b(-pitch * 0.017453292F);
/*  44 */     float f3 = MathHelper.func_76126_a(-pitch * 0.017453292F);
/*  45 */     return new Vec3((f1 * f2), f3, (f * f2));
/*     */   }
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
/*     */   public static List<Vec3> vectorsToRaytrace(int vectorQuantity) {
/*  59 */     Minecraft mc = Minecraft.func_71410_x();
/*  60 */     EntityPlayerSP player = mc.field_71439_g;
/*  61 */     List<Vec3> vectorList = new ArrayList<>();
/*     */     
/*  63 */     Vec3 eyes = new Vec3(player.field_70165_t, player.field_70163_u + player.func_70047_e(), player.field_70161_v);
/*  64 */     float aspectRatio = mc.field_71443_c / mc.field_71440_d;
/*     */ 
/*     */     
/*  67 */     double fovV = (mc.field_71474_y.field_74334_X * mc.field_71439_g.func_175156_o());
/*     */     
/*  69 */     double fovH = Math.atan(aspectRatio * Math.tan(fovV * 0.017453292519943295D / 2.0D)) * 2.0D * 57.29577951308232D;
/*     */     
/*  71 */     float verticalSpacing = (float)(fovV * 0.8D / vectorQuantity);
/*  72 */     float horizontalSpacing = (float)(fovH * 0.9D / vectorQuantity);
/*     */     
/*  74 */     float playerYaw = player.field_70177_z;
/*  75 */     float playerPitch = player.field_70125_A;
/*     */     
/*  77 */     if (mc.field_71474_y.field_74320_O == 2) {
/*     */ 
/*     */       
/*  80 */       playerYaw += 180.0F;
/*  81 */       playerPitch = -playerPitch;
/*     */     } 
/*     */     float h;
/*  84 */     for (h = -(vectorQuantity - 1) / 2.0F; h <= (vectorQuantity - 1) / 2.0F; h++) {
/*  85 */       float v; for (v = -(vectorQuantity - 1) / 2.0F; v <= (vectorQuantity - 1) / 2.0F; v++) {
/*  86 */         float yaw = h * horizontalSpacing;
/*  87 */         float pitch = v * verticalSpacing;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  95 */         float yawScaled = yaw * (playerPitch * playerPitch / 8100.0F + 1.0F) / (Math.abs(v / vectorQuantity) + 1.0F);
/*     */ 
/*     */         
/*  98 */         Vec3 direction = getVectorFromRotation(yawScaled + playerYaw, pitch + playerPitch);
/*     */ 
/*     */ 
/*     */         
/* 102 */         vectorList.add(eyes.func_72441_c(direction.field_72450_a * 64.0D, direction.field_72448_b * 64.0D, direction.field_72449_c * 64.0D));
/*     */       } 
/*     */     } 
/* 105 */     return vectorList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 111 */   public static HashSet<Integer> whitelistedBlocks = new HashSet<>(Arrays.asList(new Integer[] {
/*     */           
/* 113 */           Integer.valueOf(100), 
/* 114 */           Integer.valueOf(103), 
/* 115 */           Integer.valueOf(104), 
/* 116 */           Integer.valueOf(105), 
/* 117 */           Integer.valueOf(106), 
/* 118 */           Integer.valueOf(200), 
/* 119 */           Integer.valueOf(300), 
/* 120 */           Integer.valueOf(301), 
/* 121 */           Integer.valueOf(400), 
/* 122 */           Integer.valueOf(700), 
/* 123 */           Integer.valueOf(1800), 
/* 124 */           Integer.valueOf(3507), 
/* 125 */           Integer.valueOf(4300), 
/* 126 */           Integer.valueOf(4800), 
/* 127 */           Integer.valueOf(8200), 
/* 128 */           Integer.valueOf(9800), 
/* 129 */           Integer.valueOf(9801), 
/* 130 */           Integer.valueOf(9803), 
/* 131 */           Integer.valueOf(15907), 
/* 132 */           Integer.valueOf(15909), 
/* 133 */           Integer.valueOf(15915)
/*     */         }));
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
/*     */   public static boolean blockPartOfDoorway(BlockPos blockToCheck) {
/* 146 */     if (blockToCheck.func_177956_o() < 66 || blockToCheck.func_177956_o() > 73) return false;
/*     */     
/* 148 */     int relX = blockToCheck.func_177958_n() % 32;
/* 149 */     int relZ = blockToCheck.func_177952_p() % 32;
/*     */     
/* 151 */     if (relX >= 13 && relX <= 17) {
/* 152 */       if (relZ <= 2) return true; 
/* 153 */       if (relZ >= 28) return true;
/*     */     
/*     */     } 
/* 156 */     if (relZ >= 13 && relZ <= 17) {
/* 157 */       if (relX <= 2) return true; 
/* 158 */       if (relX >= 28) return true;
/*     */     
/*     */     } 
/* 161 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\io\github\quantizr\dungeonroom\\utils\RoomDetectionUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */