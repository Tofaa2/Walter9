/*    */ package cheaters.get.banned.features;
/*    */ 
/*    */ import cheaters.get.banned.Shady;
/*    */ import cheaters.get.banned.config.Config;
/*    */ import cheaters.get.banned.utils.KeybindUtils;
/*    */ import cheaters.get.banned.utils.Utils;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraftforge.event.entity.player.PlayerInteractEvent;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ public class AutoSimonSays
/*    */ {
/*    */   private boolean clicking = false;
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onInteract(PlayerInteractEvent event) {
/* 17 */     if (Config.autoSimonSays && !this.clicking && Utils.inDungeon && event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK && 
/* 18 */       Shady.mc.field_71441_e.func_180495_p(event.pos).func_177230_c() == Blocks.field_150430_aB) {
/* 19 */       int x = event.pos.func_177958_n();
/* 20 */       int y = event.pos.func_177956_o();
/* 21 */       int z = event.pos.func_177952_p();
/*    */       
/* 23 */       if (x == 309 && y >= 120 && y <= 123 && z >= 291 && z <= 294) {
/* 24 */         this.clicking = true;
/* 25 */         for (int i = 0; i < 4; i++) {
/* 26 */           KeybindUtils.rightClick();
/*    */         }
/* 28 */         this.clicking = false;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Desktop\ShadyAddons-2.1.0-pre2.jar!\cheaters\get\banned\features\AutoSimonSays.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */