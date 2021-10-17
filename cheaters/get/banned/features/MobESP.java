/*    */ package cheaters.get.banned.features;
/*    */ 
/*    */ import cheaters.get.banned.Shady;
/*    */ import cheaters.get.banned.config.Config;
/*    */ import cheaters.get.banned.utils.RenderUtils;
/*    */ import cheaters.get.banned.utils.Utils;
/*    */ import java.awt.Color;
/*    */ import java.util.HashSet;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraftforge.client.event.RenderLivingEvent;
/*    */ import net.minecraftforge.event.entity.EntityJoinWorldEvent;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MobESP
/*    */ {
/* 22 */   private HashSet<Entity> entitiesToHighlight = new HashSet<>();
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onEntityJoinWorld(EntityJoinWorldEvent event) {
/* 26 */     if (!Utils.inDungeon || !Config.highlightStarredMobs)
/* 27 */       return;  if (event.entity instanceof net.minecraft.entity.player.EntityPlayer) {
/* 28 */       String name = event.entity.func_70005_c_();
/* 29 */       if (name.equals("Shadow Assassin") || name.equals("Lost Adventurer") || name.equals("Diamond Guy")) {
/* 30 */         this.entitiesToHighlight.add(event.entity);
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void beforeRenderEntity(RenderLivingEvent.Pre<EntityLivingBase> event) {
/* 37 */     if (!Utils.inDungeon || !Config.highlightStarredMobs)
/* 38 */       return;  if (event.entity instanceof net.minecraft.entity.item.EntityArmorStand && 
/* 39 */       event.entity.func_145818_k_() && event.entity.func_95999_t().contains("✯")) {
/* 40 */       this.entitiesToHighlight.add(event.entity);
/* 41 */       List<Entity> possibleEntities = event.entity.func_130014_f_().func_175674_a((Entity)event.entity, event.entity.func_174813_aQ().func_72314_b(0.0D, 3.0D, 0.0D), entity -> !(entity instanceof net.minecraft.entity.item.EntityArmorStand));
/* 42 */       if (!possibleEntities.isEmpty()) {
/* 43 */         System.out.println(possibleEntities);
/* 44 */         this.entitiesToHighlight.add(possibleEntities.get(0));
/*    */       } 
/* 46 */       Shady.mc.field_71441_e.func_72900_e((Entity)event.entity);
/*    */     } 
/*    */ 
/*    */     
/* 50 */     if (this.entitiesToHighlight.contains(event.entity)) {
/* 51 */       GlStateManager.func_179090_x();
/* 52 */       GlStateManager.func_179097_i();
/* 53 */       GlStateManager.func_179129_p();
/* 54 */       GlStateManager.func_179140_f();
/* 55 */       GlStateManager.func_179147_l();
/*    */       
/* 57 */       GlStateManager.func_179120_a(770, 771, 1, 0);
/* 58 */       RenderUtils.bindColor(new Color(255, 170, 0));
/*    */     } 
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void afterRenderEntity(RenderLivingEvent.Post<EntityLivingBase> event) {
/* 64 */     if (!Utils.inDungeon || !Config.highlightStarredMobs)
/* 65 */       return;  if (this.entitiesToHighlight.contains(event.entity)) {
/* 66 */       GlStateManager.func_179098_w();
/* 67 */       GlStateManager.func_179126_j();
/* 68 */       GlStateManager.func_179089_o();
/* 69 */       GlStateManager.func_179145_e();
/* 70 */       GlStateManager.func_179084_k();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\cheaters\get\banned\features\MobESP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */