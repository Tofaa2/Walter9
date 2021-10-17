/*    */ package me.Danker.handlers;
/*    */ 
/*    */ import io.netty.channel.ChannelDuplexHandler;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.channel.ChannelPromise;
/*    */ import me.Danker.commands.ToggleCommand;
/*    */ import me.Danker.utils.Utils;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.item.EntityItemFrame;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.network.play.client.C02PacketUseEntity;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
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
/*    */   public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
/* 28 */     if (ToggleCommand.itemFrameOnSeaLanternsToggled && Utils.inDungeons && msg instanceof net.minecraft.network.Packet && msg.getClass().getName().endsWith("C02PacketUseEntity")) {
/* 29 */       Minecraft mc = Minecraft.func_71410_x();
/* 30 */       C02PacketUseEntity packet = (C02PacketUseEntity)msg;
/* 31 */       Entity entityHit = packet.func_149564_a((World)mc.field_71441_e);
/* 32 */       if (entityHit instanceof EntityItemFrame) {
/* 33 */         EntityItemFrame itemFrame = (EntityItemFrame)entityHit;
/* 34 */         ItemStack item = itemFrame.func_82335_i();
/* 35 */         if (item != null && item.func_77973_b() == Items.field_151032_g) {
/* 36 */           BlockPos blockPos = Utils.getBlockUnderItemFrame(itemFrame);
/* 37 */           if (mc.field_71441_e.func_180495_p(blockPos).func_177230_c() == Blocks.field_180398_cJ) {
/*    */             return;
/*    */           }
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 44 */     super.write(ctx, msg, promise);
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\me\Danker\handlers\PacketHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */