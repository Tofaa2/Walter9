/*     */ package cheaters.get.banned.features;
/*     */ 
/*     */ import cheaters.get.banned.Shady;
/*     */ import cheaters.get.banned.config.Config;
/*     */ import cheaters.get.banned.events.BlockChangeEvent;
/*     */ import cheaters.get.banned.events.TickEndEvent;
/*     */ import cheaters.get.banned.utils.RenderUtils;
/*     */ import cheaters.get.banned.utils.Utils;
/*     */ import com.mojang.authlib.properties.Property;
/*     */ import java.awt.Color;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockChest;
/*     */ import net.minecraft.tileentity.TileEntitySkull;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.util.Vec3i;
/*     */ import net.minecraftforge.client.event.RenderWorldLastEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerInteractEvent;
/*     */ import net.minecraftforge.event.world.WorldEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StonklessStonk
/*     */ {
/*  30 */   private static HashMap<BlockPos, Block> blockList = new HashMap<>();
/*  31 */   private static BlockPos selectedBlock = null;
/*  32 */   private static BlockPos lastCheckedPosition = null;
/*  33 */   private static HashSet<BlockPos> usedBlocks = new HashSet<>();
/*     */   
/*  35 */   private static float range = 5.0F;
/*     */   private static final String witherEssenceSkin = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzRkYjRhZGZhOWJmNDhmZjVkNDE3MDdhZTM0ZWE3OGJkMjM3MTY1OWZjZDhjZDg5MzQ3NDlhZjRjY2U5YiJ9fX0=";
/*     */   
/*     */   private static boolean isEnabled() {
/*  39 */     boolean isEnabled = (Utils.inDungeon && Shady.mc.field_71439_g != null);
/*  40 */     if (!Config.alwaysOn && isEnabled) isEnabled = (Config.stonklessStonk && Shady.mc.field_71439_g.func_70093_af()); 
/*  41 */     return isEnabled;
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onTick(TickEndEvent event) {
/*  46 */     if (Shady.mc.field_71439_g == null)
/*  47 */       return;  BlockPos playerPosition = Shady.mc.field_71439_g.func_180425_c();
/*  48 */     if (isEnabled() && (lastCheckedPosition == null || !lastCheckedPosition.equals(playerPosition))) {
/*  49 */       blockList.clear();
/*  50 */       lastCheckedPosition = playerPosition;
/*     */       
/*  52 */       for (int x = playerPosition.func_177958_n() - 6; x < playerPosition.func_177958_n() + 6; x++) {
/*  53 */         for (int y = playerPosition.func_177956_o() - 6; y < playerPosition.func_177956_o() + 6; y++) {
/*  54 */           for (int z = playerPosition.func_177952_p() - 6; z < playerPosition.func_177952_p() + 6; z++) {
/*     */             
/*  56 */             BlockPos position = new BlockPos(x, y, z);
/*  57 */             Block block = Shady.mc.field_71441_e.func_180495_p(position).func_177230_c();
/*     */             
/*  59 */             if (shouldEspBlock(block, position)) {
/*  60 */               blockList.put(position, block);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onRenderWorld(RenderWorldLastEvent event) {
/*  71 */     if (isEnabled()) {
/*  72 */       for (Map.Entry<BlockPos, Block> block : blockList.entrySet()) {
/*     */         
/*  74 */         if (usedBlocks.contains(block.getKey()))
/*     */           continue; 
/*  76 */         if (selectedBlock == null) {
/*  77 */           if (Utils.facingBlock(block.getKey(), range)) {
/*  78 */             selectedBlock = block.getKey();
/*     */           }
/*     */         }
/*  81 */         else if (!Utils.facingBlock(selectedBlock, range)) {
/*  82 */           selectedBlock = null;
/*     */         } 
/*     */ 
/*     */         
/*  86 */         Color color = Utils.addAlpha(Color.WHITE, 51);
/*  87 */         if (block.getValue() instanceof net.minecraft.block.BlockSkull) color = Utils.addAlpha(Color.BLACK, 51); 
/*  88 */         if (block.getValue() instanceof net.minecraft.block.BlockLever) color = Utils.addAlpha(Color.LIGHT_GRAY, 51); 
/*  89 */         if (block.getValue() instanceof BlockChest && ((BlockChest)block.getValue()).field_149956_a == 1) color = Utils.addAlpha(Color.RED, 51); 
/*  90 */         if (((BlockPos)block.getKey()).equals(selectedBlock)) color = Utils.addAlpha(Color.GREEN, 51);
/*     */         
/*  92 */         RenderUtils.highlightBlock(block.getKey(), color, event.partialTicks);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onInteract(PlayerInteractEvent event) {
/* 100 */     if (isEnabled() && selectedBlock != null && !usedBlocks.contains(selectedBlock)) {
/* 101 */       if (Shady.mc.field_71476_x != null && selectedBlock.equals(Shady.mc.field_71476_x.func_178782_a()))
/* 102 */         return;  if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR || event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
/*     */         
/* 104 */         usedBlocks.add(selectedBlock);
/* 105 */         Shady.mc.field_71439_g.func_70095_a(false);
/* 106 */         if (Shady.mc.field_71442_b.func_178890_a(Shady.mc.field_71439_g, Shady.mc.field_71441_e, Shady.mc.field_71439_g.field_71071_by
/*     */ 
/*     */             
/* 109 */             .func_70448_g(), selectedBlock, 
/*     */             
/* 111 */             EnumFacing.func_176733_a(Shady.mc.field_71439_g.field_70177_z), new Vec3(
/* 112 */               Math.random(), Math.random(), Math.random())))
/*     */         {
/* 114 */           Shady.mc.field_71439_g.func_71038_i();
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onBlockChange(BlockChangeEvent event) {
/* 123 */     if (Shady.mc.field_71441_e == null || Shady.mc.field_71439_g == null)
/*     */       return; 
/* 125 */     if (event.position.func_177951_i((Vec3i)Shady.mc.field_71439_g.func_180425_c()) > range)
/* 126 */       return;  if (usedBlocks.contains(event.position))
/* 127 */       return;  if (!shouldEspBlock(event.newBlock.func_177230_c(), event.position))
/*     */       return; 
/* 129 */     blockList.put(event.position, event.newBlock.func_177230_c());
/*     */   }
/*     */   
/*     */   public static boolean shouldEspBlock(Block block, BlockPos position) {
/* 133 */     if (block instanceof BlockChest || block instanceof net.minecraft.block.BlockLever)
/* 134 */       return true; 
/* 135 */     if (block instanceof net.minecraft.block.BlockSkull) {
/* 136 */       TileEntitySkull tileEntity = (TileEntitySkull)Shady.mc.field_71441_e.func_175625_s(position);
/* 137 */       if (tileEntity.func_145904_a() == 3) {
/* 138 */         Property property = (Property)Utils.firstOrNull(tileEntity.func_152108_a().getProperties().get("textures"));
/* 139 */         return (property != null && property.getValue().equals("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzRkYjRhZGZhOWJmNDhmZjVkNDE3MDdhZTM0ZWE3OGJkMjM3MTY1OWZjZDhjZDg5MzQ3NDlhZjRjY2U5YiJ9fX0="));
/*     */       } 
/*     */     } 
/* 142 */     return false;
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onWorldLoad(WorldEvent.Load event) {
/* 147 */     blockList.clear();
/* 148 */     usedBlocks.clear();
/* 149 */     selectedBlock = null;
/* 150 */     lastCheckedPosition = null;
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\cheaters\get\banned\features\StonklessStonk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */