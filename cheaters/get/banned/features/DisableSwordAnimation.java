/*    */ package cheaters.get.banned.features;
/*    */ 
/*    */ import cheaters.get.banned.Shady;
/*    */ import cheaters.get.banned.config.Config;
/*    */ import cheaters.get.banned.events.TickEndEvent;
/*    */ import cheaters.get.banned.utils.NetworkUtils;
/*    */ import cheaters.get.banned.utils.Utils;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraftforge.event.entity.player.PlayerInteractEvent;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DisableSwordAnimation
/*    */ {
/* 22 */   private final ArrayList<String> swords = new ArrayList<>(Arrays.asList(new String[] { "HYPERION", "VALKYRIE", "SCYLLA", "ASTRAEA", "ASPECT_OF_THE_END", "ROGUE_SWORD" }));
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static boolean isRightClickKeyDown = false;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onTick(TickEndEvent event) {
/* 35 */     isRightClickKeyDown = Shady.mc.field_71474_y.field_74313_G.func_151470_d();
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onInteract(PlayerInteractEvent event) {
/* 40 */     if (Config.disableBlockAnimation && event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR && 
/* 41 */       Shady.mc.field_71439_g.func_70694_bm() != null) {
/* 42 */       String itemID = Utils.getSkyBlockID(Shady.mc.field_71439_g.func_70694_bm());
/* 43 */       if (this.swords.contains(itemID)) {
/* 44 */         event.setCanceled(true);
/* 45 */         if (!isRightClickKeyDown)
/* 46 */           NetworkUtils.sendPacket((Packet)new C08PacketPlayerBlockPlacement(new BlockPos(-1, -1, -1), 255, Shady.mc.field_71439_g.func_70694_bm(), 0.0F, 0.0F, 0.0F)); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\cheaters\get\banned\features\DisableSwordAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */