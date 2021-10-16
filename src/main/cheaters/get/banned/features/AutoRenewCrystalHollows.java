/*    */ package cheaters.get.banned.features;
/*    */ 
/*    */ import cheaters.get.banned.config.Config;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraftforge.client.event.ClientChatReceivedEvent;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ public class AutoRenewCrystalHollows
/*    */ {
/*    */   @SubscribeEvent
/*    */   public void onChat(ClientChatReceivedEvent event) {
/* 12 */     if (Config.renewCrystalHollowsPass && event.type == 0 && 
/* 13 */       event.message.func_150260_c().equals("Your pass to the Crystal Hollows will expire in 1 minute"))
/* 14 */       (Minecraft.func_71410_x()).field_71439_g.func_71165_d("/purchasecrystallhollowspass"); 
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Desktop\ShadyAddons-2.1.0-pre2.jar!\cheaters\get\banned\features\AutoRenewCrystalHollows.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */