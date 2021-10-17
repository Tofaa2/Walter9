/*    */ package cheaters.get.banned.features;
/*    */ 
/*    */ import cheaters.get.banned.config.Config;
/*    */ import cheaters.get.banned.utils.Utils;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraftforge.client.event.ClientChatReceivedEvent;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ public class AutoGG
/*    */ {
/*    */   @SubscribeEvent
/*    */   public void onChat(ClientChatReceivedEvent event) {
/* 13 */     if (Config.autoGg && !Utils.inSkyBlock && event.type == 0) {
/* 14 */       String message = event.message.func_150254_d();
/* 15 */       if (message.contains("Reward Summary") && !message.contains(":") && !message.contains("]"))
/* 16 */         (Minecraft.func_71410_x()).field_71439_g.func_71165_d("/ac gg"); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\cheaters\get\banned\features\AutoGG.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */