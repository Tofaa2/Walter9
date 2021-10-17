/*    */ package io.github.quantizr.dungeonrooms.events;
/*    */ 
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraftforge.fml.common.eventhandler.Cancelable;
/*    */ import net.minecraftforge.fml.common.eventhandler.Event;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Cancelable
/*    */ public class PacketEvent
/*    */   extends Event
/*    */ {
/*    */   public Direction direction;
/*    */   public Packet<?> packet;
/*    */   
/*    */   public PacketEvent(Packet<?> packet) {
/* 37 */     this.packet = packet;
/*    */   }
/*    */   
/*    */   public static class ReceiveEvent extends PacketEvent {
/*    */     public ReceiveEvent(Packet<?> packet) {
/* 42 */       super(packet);
/* 43 */       this.direction = PacketEvent.Direction.INBOUND;
/*    */     }
/*    */   }
/*    */   
/*    */   public static class SendEvent extends PacketEvent {
/*    */     public SendEvent(Packet<?> packet) {
/* 49 */       super(packet);
/* 50 */       this.direction = PacketEvent.Direction.OUTBOUND;
/*    */     }
/*    */   }
/*    */   
/*    */   enum Direction {
/* 55 */     INBOUND,
/* 56 */     OUTBOUND;
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\io\github\quantizr\dungeonrooms\events\PacketEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */