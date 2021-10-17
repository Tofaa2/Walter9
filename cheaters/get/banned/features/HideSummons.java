/*    */ package cheaters.get.banned.features;
/*    */ 
/*    */ import cheaters.get.banned.Shady;
/*    */ import cheaters.get.banned.config.Config;
/*    */ import cheaters.get.banned.utils.Utils;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.monster.EntityMob;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.Vec3;
/*    */ import net.minecraftforge.client.event.RenderLivingEvent;
/*    */ import net.minecraftforge.event.entity.player.AttackEntityEvent;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HideSummons
/*    */ {
/* 25 */   private static final ArrayList<String> summonItemIDs = new ArrayList<>(Arrays.asList(new String[] { "HEAVY_HELMET", "ZOMBIE_KNIGHT_HELMET", "SKELETOR_HELMET" }));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean isSummon(Entity entity) {
/* 32 */     if (entity instanceof EntityPlayer)
/* 33 */       return entity.func_70005_c_().equals("Lost Adventurer"); 
/* 34 */     if (entity instanceof net.minecraft.entity.monster.EntityZombie || entity instanceof net.minecraft.entity.monster.EntitySkeleton) {
/* 35 */       for (int i = 0; i < 5; i++) {
/* 36 */         ItemStack item = ((EntityMob)entity).func_71124_b(i);
/* 37 */         if (summonItemIDs.contains(Utils.getSkyBlockID(item))) {
/* 38 */           return true;
/*    */         }
/*    */       } 
/*    */     }
/* 42 */     return false;
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onPreRenderEntity(RenderLivingEvent.Pre<EntityLivingBase> event) {
/* 47 */     if (Utils.inSkyBlock && !Utils.inDungeon && 
/* 48 */       isSummon((Entity)event.entity) && 
/* 49 */       Config.hideSummons && event.entity.func_70032_d((Entity)Shady.mc.field_71439_g) < 5.0F) {
/* 50 */       event.setCanceled(true);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onAttack(AttackEntityEvent event) {
/* 58 */     if (Config.clickThroughSummons && Utils.inSkyBlock && !Utils.inDungeon && isSummon(event.target)) {
/* 59 */       Entity excludedEntity = Shady.mc.func_175606_aa();
/* 60 */       double reach = Shady.mc.field_71442_b.func_78757_d();
/* 61 */       Vec3 look = excludedEntity.func_70676_i(0.0F);
/*    */       
/* 63 */       AxisAlignedBB boundingBox = excludedEntity.func_174813_aQ().func_72321_a(look.field_72450_a * reach, look.field_72448_b * reach, look.field_72449_c * reach).func_72314_b(1.0D, 1.0D, 1.0D);
/* 64 */       List<Entity> entitiesInRange = Shady.mc.field_71441_e.func_72839_b(excludedEntity, boundingBox);
/* 65 */       entitiesInRange.removeIf(entity -> !entity.func_70067_L());
/* 66 */       entitiesInRange.removeIf(HideSummons::isSummon);
/*    */       
/* 68 */       if (entitiesInRange.size() > 0) {
/* 69 */         event.setCanceled(true);
/* 70 */         Shady.mc.field_71439_g.func_71038_i();
/* 71 */         Shady.mc.field_71442_b.func_78764_a((EntityPlayer)Shady.mc.field_71439_g, entitiesInRange.get(0));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\cheaters\get\banned\features\HideSummons.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */