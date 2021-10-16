/*    */ package cheaters.get.banned.events;
/*    */ 
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraftforge.fml.common.eventhandler.Cancelable;
/*    */ import net.minecraftforge.fml.common.eventhandler.Event;
/*    */ 
/*    */ @Cancelable
/*    */ public class PacketEvent
/*    */   extends Event {
/*    */   public Direction direction;
/*    */   public Packet<?> packet;
/*    */   
/*    */   public PacketEvent(Packet<?> packet) {
/* 14 */     this.packet = packet;
/*    */   }
/*    */   
/*    */   public static class ReceiveEvent extends PacketEvent {
/*    */     public ReceiveEvent(Packet<?> packet) {
/* 19 */       super(packet);
/* 20 */       this.direction = PacketEvent.Direction.INBOUND;
/*    */     }
/*    */   }
/*    */   
/*    */   public static class SendEvent extends PacketEvent {
/*    */     public SendEvent(Packet<?> packet) {
/* 26 */       super(packet);
/* 27 */       this.direction = PacketEvent.Direction.OUTBOUND;
/*    */     }
/*    */   }
/*    */   
/*    */   enum Direction {
/* 32 */     INBOUND, OUTBOUND;
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Desktop\ShadyAddons-2.1.0-pre2.jar!\cheaters\get\banned\events\PacketEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */