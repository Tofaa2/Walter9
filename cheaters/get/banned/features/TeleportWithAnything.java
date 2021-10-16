/*    */ package cheaters.get.banned.features;
/*    */ import cheaters.get.banned.Shady;
/*    */ import cheaters.get.banned.config.Config;
/*    */ import cheaters.get.banned.utils.Utils;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.event.entity.player.PlayerInteractEvent;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ public class TeleportWithAnything {
/*    */   @SubscribeEvent
/*    */   public void onInteract(PlayerInteractEvent event) {
/* 14 */     if (Config.teleportWithAnything && Utils.inSkyBlock && Shady.mc.field_71439_g.field_71071_by.field_70461_c == 0 && event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR)
/*    */     {
/* 16 */       for (int i = 0; i < 9; i++) {
/* 17 */         ItemStack item = Shady.mc.field_71439_g.field_71071_by.func_70301_a(i);
/* 18 */         String itemID = Utils.getSkyBlockID(item);
/*    */         
/* 20 */         if (itemID.equals("ASPECT_OF_THE_END") || itemID.equals("ASPECT_OF_THE_VOID")) {
/* 21 */           event.setCanceled(true);
/* 22 */           Shady.mc.field_71439_g.field_71071_by.field_70461_c = i;
/* 23 */           Shady.mc.field_71442_b.func_78769_a((EntityPlayer)Shady.mc.field_71439_g, (World)Shady.mc.field_71441_e, item);
/* 24 */           Shady.mc.field_71439_g.field_71071_by.field_70461_c = 0;
/*    */           break;
/*    */         } 
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Desktop\ShadyAddons-2.1.0-pre2.jar!\cheaters\get\banned\features\TeleportWithAnything.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */