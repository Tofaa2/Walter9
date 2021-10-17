/*    */ package cheaters.get.banned.mixins;
/*    */ 
/*    */ import cheaters.get.banned.events.PacketEvent;
/*    */ import net.minecraft.client.network.NetHandlerPlayClient;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.fml.common.eventhandler.Event;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ @Mixin({NetHandlerPlayClient.class})
/*    */ public abstract class MixinNetHandlerPlayClient
/*    */   implements INetHandlerPlayClient {
/*    */   @Inject(method = {"addToSendQueue"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void onSendPacket(Packet<?> packet, CallbackInfo callbackInfo) {
/*    */     try {
/* 20 */       if (MinecraftForge.EVENT_BUS.post((Event)new PacketEvent.SendEvent(packet))) callbackInfo.cancel(); 
/* 21 */     } catch (Exception exception) {}
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\cheaters\get\banned\mixins\MixinNetHandlerPlayClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */