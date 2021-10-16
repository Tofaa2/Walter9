/*    */ package cheaters.get.banned.events;
/*    */ 
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.fml.common.eventhandler.Event;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.gameevent.TickEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TickEndEvent
/*    */   extends Event
/*    */ {
/*    */   @SubscribeEvent
/*    */   public void onTick(TickEvent.ClientTickEvent event) {
/* 15 */     if (event.phase == TickEvent.Phase.END)
/* 16 */       MinecraftForge.EVENT_BUS.post(new TickEndEvent()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Desktop\ShadyAddons-2.1.0-pre2.jar!\cheaters\get\banned\events\TickEndEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */