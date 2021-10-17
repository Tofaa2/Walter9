/*    */ package cheaters.get.banned.mixins;
/*    */ 
/*    */ import cheaters.get.banned.Shady;
/*    */ import cheaters.get.banned.events.BlockChangeEvent;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.chunk.Chunk;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.fml.common.eventhandler.Event;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ 
/*    */ @Mixin({Chunk.class})
/*    */ public abstract class MixinChunk {
/*    */   @Shadow
/*    */   public abstract IBlockState func_177435_g(BlockPos paramBlockPos);
/*    */   
/*    */   @Inject(method = {"setBlockState"}, at = {@At("HEAD")})
/*    */   private void onBlockChange(BlockPos position, IBlockState newBlock, CallbackInfoReturnable<IBlockState> callbackInfoReturnable) {
/* 23 */     IBlockState oldBlock = func_177435_g(position);
/*    */     
/* 25 */     if (oldBlock != newBlock && Shady.mc.field_71441_e != null)
/*    */       try {
/* 27 */         MinecraftForge.EVENT_BUS.post((Event)new BlockChangeEvent(position, oldBlock, newBlock));
/* 28 */       } catch (Exception exception) {} 
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\cheaters\get\banned\mixins\MixinChunk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */