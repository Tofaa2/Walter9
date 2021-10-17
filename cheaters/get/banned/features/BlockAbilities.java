/*    */ package cheaters.get.banned.features;
/*    */ 
/*    */ import cheaters.get.banned.Shady;
/*    */ import cheaters.get.banned.config.Config;
/*    */ import cheaters.get.banned.utils.Utils;
/*    */ import net.minecraftforge.event.entity.player.PlayerInteractEvent;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ public class BlockAbilities
/*    */ {
/*    */   @SubscribeEvent
/*    */   public void onInteract(PlayerInteractEvent event) {
/* 13 */     if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR || (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK && Shady.mc.field_71439_g.func_70694_bm() != null)) {
/* 14 */       String skyBlockID = Utils.getSkyBlockID(Shady.mc.field_71439_g.func_70694_bm());
/* 15 */       if ((Config.blockCellsAlignment && skyBlockID
/*    */         
/* 17 */         .equals("GYROKINETIC_WAND")) || (Config.blockGiantsSlam && skyBlockID
/* 18 */         .equals("GIANTS_SWORD")) || (Config.blockValkyrie && Utils.inDungeon && skyBlockID
/* 19 */         .equals("VALKYRIE")))
/*    */       {
/* 21 */         event.setCanceled(true);
/*    */       }
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\cheaters\get\banned\features\BlockAbilities.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */