/*     */ package cheaters.get.banned.features.dungeonmap;
/*     */ 
/*     */ import cheaters.get.banned.Shady;
/*     */ import cheaters.get.banned.events.BlockChangeEvent;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockColored;
/*     */ import net.minecraft.block.BlockSilverfish;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.EnumDyeColor;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ 
/*     */ public class DungeonScanner {
/*     */   public static DungeonLayout scan(int xCorner, int zCorner) {
/*  20 */     DungeonLayout newDungeonLayout = new DungeonLayout();
/*     */     
/*  22 */     for (int x = 15; x < xCorner + 1; x += 32) {
/*  23 */       for (int z = 15; z < zCorner + 1; z += 32) {
/*  24 */         boolean isColumnEmpty = isColumnNotEmpty(x, z);
/*     */         
/*  26 */         if (isColumnEmpty) {
/*  27 */           Iterator<Integer> iterator; label52: for (iterator = RoomLoader.yLevels.iterator(); iterator.hasNext(); ) { int y = ((Integer)iterator.next()).intValue();
/*  28 */             BlockList blockList = new BlockList(x, y, z);
/*  29 */             for (Room room : RoomLoader.rooms) {
/*  30 */               if (room.matches(blockList)) {
/*  31 */                 newDungeonLayout.totalSecrets += room.secrets;
/*     */                 
/*  33 */                 if (room.crypts == null) {
/*  34 */                   newDungeonLayout.uncertainCrypts = true;
/*     */                 } else {
/*  36 */                   newDungeonLayout.totalCrypts += room.crypts.intValue();
/*     */                 } 
/*     */                 
/*  39 */                 if (room.type == Room.Type.PUZZLE) {
/*  40 */                   newDungeonLayout.totalPuzzles++;
/*     */                 }
/*     */                 
/*  43 */                 if (room.type == Room.Type.TRAP) {
/*  44 */                   if (room.name.contains("Old")) {
/*  45 */                     newDungeonLayout.trapType = DungeonLayout.TrapType.OLD; continue label52;
/*  46 */                   }  if (room.name.contains("New")) {
/*  47 */                     newDungeonLayout.trapType = DungeonLayout.TrapType.NEW;
/*     */                   }
/*     */ 
/*     */                   
/*  51 */                   newDungeonLayout.roomTiles.add(new DungeonLayout.RoomTile(x, z, room)); continue;
/*     */                 } 
/*     */                 continue label52;
/*     */               } 
/*     */             }  }
/*     */         
/*     */         } 
/*  58 */         newDungeonLayout.connectorTiles.add(getConnectorAt(x, z - 16, EnumFacing.NORTH));
/*  59 */         newDungeonLayout.connectorTiles.add(getConnectorAt(x + 16, z, EnumFacing.EAST));
/*  60 */         newDungeonLayout.connectorTiles.add(getConnectorAt(x, z + 16, EnumFacing.SOUTH));
/*  61 */         newDungeonLayout.connectorTiles.add(getConnectorAt(x - 16, z, EnumFacing.WEST));
/*     */       } 
/*     */     } 
/*     */     
/*  65 */     ArrayList<DungeonLayout.RoomTile> roomTilesToAdd = new ArrayList<>();
/*  66 */     for (int i = 15; i < xCorner + 1; i += 32) {
/*  67 */       for (int z = 15; z < zCorner + 1; z += 32) {
/*  68 */         if (!layoutContainsRoomAtPosition(newDungeonLayout, i, z) && isColumnNotEmpty(i, z)) {
/*  69 */           roomTilesToAdd.add(new DungeonLayout.RoomTile(i, z, Room.GENERIC_NORMAL_ROOM));
/*     */         }
/*     */       } 
/*     */     } 
/*  73 */     newDungeonLayout.roomTiles.addAll(roomTilesToAdd);
/*     */     
/*  75 */     return newDungeonLayout;
/*     */   }
/*     */   
/*     */   private static DungeonLayout.ConnectorTile getConnectorAt(int x, int z, EnumFacing direction) {
/*  79 */     BlockPos doorBlock = new BlockPos(x, 69, z);
/*  80 */     IBlockState blockState = Shady.mc.field_71441_e.func_180495_p(doorBlock);
/*  81 */     Block block = blockState.func_177230_c();
/*  82 */     DungeonLayout.ConnectorTile.Type type = DungeonLayout.ConnectorTile.Type.CONNECTOR;
/*     */     
/*  84 */     if (block == Blocks.field_150402_ci) type = DungeonLayout.ConnectorTile.Type.WITHER_DOOR; 
/*  85 */     if (block == Blocks.field_150406_ce && blockState.func_177229_b((IProperty)BlockColored.field_176581_a) == EnumDyeColor.RED) type = DungeonLayout.ConnectorTile.Type.BLOOD_DOOR; 
/*  86 */     if (block == Blocks.field_150418_aU && blockState.func_177229_b((IProperty)BlockSilverfish.field_176378_a) == BlockSilverfish.EnumType.CHISELED_STONEBRICK) type = DungeonLayout.ConnectorTile.Type.ENTRANCE_DOOR; 
/*  87 */     if (block == Blocks.field_150350_a) {
/*  88 */       if (Shady.mc.field_71441_e.func_180495_p(doorBlock.func_177979_c(2)).func_177230_c() == Blocks.field_150357_h && Shady.mc.field_71441_e.func_180495_p(doorBlock.func_177981_b(4)).func_177230_c() != Blocks.field_150350_a) {
/*  89 */         type = DungeonLayout.ConnectorTile.Type.NORMAL_DOOR;
/*  90 */       } else if (isColumnNotEmpty(x, z)) {
/*  91 */         type = DungeonLayout.ConnectorTile.Type.CONNECTOR;
/*     */       } else {
/*  93 */         type = null;
/*     */       } 
/*     */     }
/*     */     
/*  97 */     return new DungeonLayout.ConnectorTile(x, z, type, direction);
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onBlockChange(BlockChangeEvent event) {
/* 102 */     if (DungeonMap.activeDungeonLayout != null && (event.oldBlock.func_177230_c() == Blocks.field_150402_ci || event.oldBlock.func_177230_c() == Blocks.field_150406_ce) && event.newBlock.func_177230_c() == Blocks.field_150350_a) {
/* 103 */       for (DungeonLayout.ConnectorTile door : DungeonMap.activeDungeonLayout.connectorTiles) {
/* 104 */         if (((!door.isOpen && event.oldBlock.func_177230_c() == Blocks.field_150402_ci && door.type == DungeonLayout.ConnectorTile.Type.WITHER_DOOR) || (event.oldBlock.func_177230_c() == Blocks.field_150406_ce && door.type == DungeonLayout.ConnectorTile.Type.BLOOD_DOOR)) && 
/* 105 */           event.position.equals(door.getPosition(69))) {
/* 106 */           door.isOpen = true;
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean isColumnNotEmpty(int x, int z) {
/* 114 */     for (int y = 256; y > 0; y--) {
/* 115 */       if (Shady.mc.field_71441_e != null && !Shady.mc.field_71441_e.func_175623_d(new BlockPos(x, y, z))) {
/* 116 */         return true;
/*     */       }
/*     */     } 
/* 119 */     return false;
/*     */   }
/*     */   
/*     */   private static boolean layoutContainsRoomAtPosition(DungeonLayout layout, int x, int z) {
/* 123 */     for (DungeonLayout.RoomTile room : layout.roomTiles) {
/* 124 */       if (room.x == x && room.z == z) {
/* 125 */         return true;
/*     */       }
/*     */     } 
/* 128 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Desktop\ShadyAddons-2.1.0-pre2.jar!\cheaters\get\banned\features\dungeonmap\DungeonScanner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */