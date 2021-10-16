/*    */ package cheaters.get.banned.features;
/*    */ 
/*    */ import cheaters.get.banned.config.Config;
/*    */ import cheaters.get.banned.utils.RenderUtils;
/*    */ import cheaters.get.banned.utils.Utils;
/*    */ import net.minecraft.client.entity.EntityOtherPlayerMP;
/*    */ import net.minecraftforge.client.event.RenderWorldLastEvent;
/*    */ import net.minecraftforge.event.entity.EntityJoinWorldEvent;
/*    */ import net.minecraftforge.event.world.WorldEvent;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ public class BossCorleoneFinder
/*    */ {
/* 14 */   private EntityOtherPlayerMP corleone = null;
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onEntityJoin(EntityJoinWorldEvent event) {
/* 18 */     if (Utils.inSkyBlock && this.corleone == null && event.entity instanceof EntityOtherPlayerMP && event.entity.func_70005_c_().equals("Team Treasurite") && ((EntityOtherPlayerMP)event.entity).func_110138_aP() == 1000000.0F) {
/* 19 */       this.corleone = (EntityOtherPlayerMP)event.entity;
/* 20 */       if (Config.corleoneFinder) {
/* 21 */         Utils.sendModMessage("Boss Corleone Found at " + this.corleone.field_70165_t + "/" + this.corleone.field_70163_u + "/" + this.corleone.field_70161_v);
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onRenderWorld(RenderWorldLastEvent event) {
/* 28 */     if (this.corleone != null && Config.corleoneFinder) {
/* 29 */       RenderUtils.renderWaypointText("Boss Corleone", this.corleone.field_70165_t, this.corleone.field_70163_u + 1.0D, this.corleone.field_70161_v, event.partialTicks);
/*    */     }
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onWorldLoad(WorldEvent.Load event) {
/* 35 */     this.corleone = null;
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Desktop\ShadyAddons-2.1.0-pre2.jar!\cheaters\get\banned\features\BossCorleoneFinder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */