/*    */ package cheaters.get.banned.features.dungeonmap;
/*    */ 
/*    */ import cheaters.get.banned.Shady;
/*    */ import java.util.HashMap;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.util.BlockPos;
/*    */ 
/*    */ 
/*    */ public class BlockList
/*    */ {
/*    */   public HashMap<Block, Integer> blocks;
/*    */   
/*    */   public BlockList(BlockPos position) {
/* 14 */     this.blocks = getBlockFrequencyList(position.func_177958_n(), position.func_177956_o(), position.func_177952_p());
/*    */   }
/*    */   
/*    */   public BlockList(int x, int y, int z) {
/* 18 */     this.blocks = getBlockFrequencyList(x, y, z);
/*    */   }
/*    */   
/*    */   public HashMap<Block, Integer> getBlocks() {
/* 22 */     return this.blocks;
/*    */   }
/*    */   
/*    */   public static HashMap<Block, Integer> getBlockFrequencyList(int xPosition, int yPosition, int zPosition) {
/* 26 */     HashMap<Block, Integer> blockFrequency = new HashMap<>();
/* 27 */     for (int x = xPosition - 16; x < xPosition + 16; x++) {
/* 28 */       for (int z = zPosition - 16; z < zPosition + 16; z++) {
/* 29 */         Block block = Shady.mc.field_71441_e.func_180495_p(new BlockPos(x, yPosition, z)).func_177230_c();
/* 30 */         blockFrequency.put(block, Integer.valueOf(blockFrequency.containsKey(block) ? (((Integer)blockFrequency.get(block)).intValue() + 1) : 1));
/*    */       } 
/*    */     } 
/* 33 */     return blockFrequency;
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Desktop\ShadyAddons-2.1.0-pre2.jar!\cheaters\get\banned\features\dungeonmap\BlockList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */