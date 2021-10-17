/*    */ package io.github.quantizr.dungeonrooms.handlers;
/*    */ 
/*    */ import io.github.quantizr.dungeonrooms.events.PacketEvent;
/*    */ import io.netty.channel.ChannelDuplexHandler;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.channel.ChannelPromise;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraftforge.common.MinecraftForge;
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
/*    */ public class PacketHandler
/*    */   extends ChannelDuplexHandler
/*    */ {
/*    */   public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
/* 32 */     if (msg instanceof Packet) MinecraftForge.EVENT_BUS.post((Event)new PacketEvent.ReceiveEvent((Packet)msg)); 
/* 33 */     super.channelRead(ctx, msg);
/*    */   }
/*    */ 
/*    */   
/*    */   public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
/* 38 */     if (msg instanceof Packet) MinecraftForge.EVENT_BUS.post((Event)new PacketEvent.SendEvent((Packet)msg)); 
/* 39 */     super.write(ctx, msg, promise);
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\io\github\quantizr\dungeonrooms\handlers\PacketHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */