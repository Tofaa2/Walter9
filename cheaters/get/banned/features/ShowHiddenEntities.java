/*    */ package cheaters.get.banned.features;
/*    */ 
/*    */ import cheaters.get.banned.config.Config;
/*    */ import cheaters.get.banned.utils.ScoreboardUtils;
/*    */ import cheaters.get.banned.utils.Utils;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraftforge.client.event.RenderLivingEvent;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ShowHiddenEntities
/*    */ {
/*    */   @SubscribeEvent
/*    */   public void onBeforeRenderEntity(RenderLivingEvent.Pre<EntityLivingBase> event) {
/* 17 */     if (event.entity.func_82150_aj()) {
/* 18 */       if (Config.showHiddenFels && event.entity instanceof net.minecraft.entity.monster.EntityEnderman) {
/* 19 */         event.entity.func_82142_c(false);
/*    */       }
/*    */       
/* 22 */       if (Utils.inDungeon && event.entity instanceof net.minecraft.entity.player.EntityPlayer) {
/* 23 */         if (Config.showHiddenShadowAssassins && event.entity.func_70005_c_().contains("Shadow Assassin")) {
/* 24 */           event.entity.func_82142_c(false);
/*    */         }
/*    */         
/* 27 */         if (Config.showStealthyBloodMobs) {
/* 28 */           for (String name : new String[] { "Revoker", "Psycho", "Reaper", "Cannibal", "Mute", "Ooze", "Putrid", "Freak", "Leech", "Tear", "Parasite", "Flamer", "Skull", "Mr. Dead", "Vader", "Frost", "Walker", "Wandering Soul", "Bonzo", "Scarf", "Livid" }) {
/* 29 */             if (event.entity.func_70005_c_().contains(name)) {
/* 30 */               event.entity.func_82142_c(false);
/*    */               
/*    */               break;
/*    */             } 
/*    */           } 
/*    */         }
/*    */       } 
/* 37 */       if (Config.showGhosts && event.entity instanceof net.minecraft.entity.monster.EntityCreeper && ScoreboardUtils.scoreboardContains("The Mist"))
/* 38 */         event.entity.func_82142_c(false); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Desktop\ShadyAddons-2.1.0-pre2.jar!\cheaters\get\banned\features\ShowHiddenEntities.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */