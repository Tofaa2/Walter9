/*    */ package cheaters.get.banned.events;
/*    */ 
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraftforge.fml.common.eventhandler.Event;
/*    */ 
/*    */ public class BlockChangeEvent
/*    */   extends Event {
/*    */   public BlockPos position;
/*    */   public IBlockState oldBlock;
/*    */   public IBlockState newBlock;
/*    */   
/*    */   public BlockChangeEvent(BlockPos position, IBlockState oldBlock, IBlockState newBlock) {
/* 14 */     this.position = position;
/* 15 */     this.oldBlock = oldBlock;
/* 16 */     this.newBlock = newBlock;
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\cheaters\get\banned\events\BlockChangeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */