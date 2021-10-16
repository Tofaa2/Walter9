/*    */ package cheaters.get.banned.features;
/*    */ 
/*    */ import cheaters.get.banned.Shady;
/*    */ import cheaters.get.banned.config.Config;
/*    */ import cheaters.get.banned.utils.KeybindUtils;
/*    */ import cheaters.get.banned.utils.Utils;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraftforge.client.event.RenderWorldLastEvent;
/*    */ import net.minecraftforge.event.entity.player.PlayerInteractEvent;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ 
/*    */ public class GhostBlocks
/*    */ {
/*    */   public GhostBlocks() {
/* 17 */     KeybindUtils.register("Create Ghost Block", 34);
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onRenderWorld(RenderWorldLastEvent event) {
/* 22 */     if (Config.ghostBlockKeybind && KeybindUtils.get("Create Ghost Block").func_151470_d()) {
/* 23 */       BlockPos lookingAtPos = Shady.mc.field_71439_g.func_174822_a(Shady.mc.field_71442_b.func_78757_d(), 1.0F).func_178782_a();
/* 24 */       if (lookingAtPos != null) {
/* 25 */         Block lookingAtblock = Shady.mc.field_71441_e.func_180495_p(lookingAtPos).func_177230_c();
/* 26 */         if (!Utils.isInteractable(lookingAtblock)) {
/* 27 */           Shady.mc.field_71441_e.func_175698_g(lookingAtPos);
/*    */         }
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onInteract(PlayerInteractEvent event) {
/* 35 */     if (Utils.inSkyBlock && event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK && Config.stonkGhostBlock && !Utils.isInteractable(Shady.mc.field_71441_e.func_180495_p(event.pos).func_177230_c())) {
/* 36 */       String itemId = Utils.getSkyBlockID(Shady.mc.field_71439_g.func_70694_bm());
/* 37 */       if (itemId.equals("STONK_PICKAXE") || itemId.equals("GOLD_PICKAXE")) {
/* 38 */         Shady.mc.field_71441_e.func_175698_g(event.pos);
/* 39 */         event.setCanceled(true);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Desktop\ShadyAddons-2.1.0-pre2.jar!\cheaters\get\banned\features\GhostBlocks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */