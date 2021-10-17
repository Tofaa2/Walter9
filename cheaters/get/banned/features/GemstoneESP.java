/*     */ package cheaters.get.banned.features;
/*     */ 
/*     */ import cheaters.get.banned.Shady;
/*     */ import cheaters.get.banned.config.Config;
/*     */ import cheaters.get.banned.events.BlockChangeEvent;
/*     */ import cheaters.get.banned.events.TickEndEvent;
/*     */ import cheaters.get.banned.utils.LocationUtils;
/*     */ import cheaters.get.banned.utils.RenderUtils;
/*     */ import cheaters.get.banned.utils.Utils;
/*     */ import java.awt.Color;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import net.minecraft.block.BlockStainedGlass;
/*     */ import net.minecraft.block.BlockStainedGlassPane;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.EnumDyeColor;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.Vec3i;
/*     */ import net.minecraftforge.client.event.RenderWorldLastEvent;
/*     */ import net.minecraftforge.event.world.WorldEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ 
/*     */ public class GemstoneESP {
/*  27 */   private ConcurrentHashMap<BlockPos, Gemstone> gemstones = new ConcurrentHashMap<>();
/*  28 */   private HashSet<BlockPos> checked = new HashSet<>();
/*  29 */   private BlockPos lastChecked = null;
/*     */   private boolean isScanning = false;
/*     */   
/*     */   enum Gemstone {
/*  33 */     RUBY((String)new Color(188, 3, 29), EnumDyeColor.RED),
/*  34 */     AMETHYST((String)new Color(137, 0, 201), EnumDyeColor.PURPLE),
/*  35 */     JADE((String)new Color(157, 249, 32), EnumDyeColor.LIME),
/*  36 */     SAPPHIRE((String)new Color(60, 121, 224), EnumDyeColor.LIGHT_BLUE),
/*  37 */     AMBER((String)new Color(237, 139, 35), EnumDyeColor.ORANGE),
/*  38 */     TOPAZ((String)new Color(249, 215, 36), EnumDyeColor.YELLOW),
/*  39 */     JASPER((String)new Color(214, 15, 150), EnumDyeColor.MAGENTA);
/*     */     public Color color;
/*     */     public EnumDyeColor dyeColor;
/*     */     
/*     */     Gemstone(Color color, EnumDyeColor dyeColor) {
/*  44 */       this.color = color;
/*  45 */       this.dyeColor = dyeColor;
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onTick(TickEndEvent event) {
/*  51 */     if (isEnabled() && !this.isScanning && (this.lastChecked == null || !this.lastChecked.equals(Shady.mc.field_71439_g.field_71081_bT))) {
/*  52 */       this.isScanning = true;
/*  53 */       (new Thread(() -> { BlockPos playerPosition = Shady.mc.field_71439_g.func_180425_c(); this.lastChecked = playerPosition; for (int x = playerPosition.func_177958_n() - Config.gemstoneRadius; x < playerPosition.func_177958_n() + Config.gemstoneRadius; x++) { for (int y = playerPosition.func_177956_o() - Config.gemstoneRadius; y < playerPosition.func_177956_o() + Config.gemstoneRadius; y++) { for (int z = playerPosition.func_177952_p() - Config.gemstoneRadius; z < playerPosition.func_177952_p() + Config.gemstoneRadius; z++) { BlockPos position = new BlockPos(x, y, z); if (!this.checked.contains(position) && !Shady.mc.field_71441_e.func_175623_d(position)) { Gemstone gemstone = getGemstone(Shady.mc.field_71441_e.func_180495_p(position)); if (gemstone != null) this.gemstones.put(position, gemstone);  }  this.checked.add(position); }  }  }  this.isScanning = false; }"ShadyAddons-GemstoneScanner"))
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  76 */         .start();
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onBlockChange(BlockChangeEvent event) {
/*  82 */     if (isEnabled() && event.position.func_177951_i((Vec3i)Shady.mc.field_71439_g.func_180425_c()) < Math.pow(Config.gemstoneRadius, 2.0D) && (event.oldBlock.func_177230_c() == Blocks.field_150399_cn || event.oldBlock.func_177230_c() == Blocks.field_150397_co) && event.newBlock.func_177230_c() == Blocks.field_150350_a) {
/*  83 */       this.gemstones.remove(event.position);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onRenderWorld(RenderWorldLastEvent event) {
/*  89 */     if (isEnabled()) {
/*  90 */       for (Map.Entry<BlockPos, Gemstone> gemstone : this.gemstones.entrySet()) {
/*  91 */         if (!isGemstoneEnabled(gemstone.getValue()))
/*  92 */           continue;  double distance = Math.sqrt(((BlockPos)gemstone.getKey()).func_177954_c(Shady.mc.field_71439_g.field_70165_t, Shady.mc.field_71439_g.field_70163_u, Shady.mc.field_71439_g.field_70161_v));
/*  93 */         if (distance > (Config.gemstoneRadius + 2))
/*     */           continue; 
/*  95 */         int alpha = (int)Math.abs(100.0D - distance / Config.gemstoneRadius * 100.0D);
/*  96 */         Color color = Utils.addAlpha(((Gemstone)gemstone.getValue()).color, alpha);
/*     */         
/*  98 */         RenderUtils.highlightBlock(gemstone.getKey(), color, event.partialTicks);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private static boolean isEnabled() {
/* 104 */     return (Shady.mc.field_71439_g != null && Shady.mc.field_71441_e != null && Config.gemstoneEsp && Utils.inSkyBlock && LocationUtils.onIsland(LocationUtils.Island.CRYSTAL_HOLLOWS));
/*     */   }
/*     */   
/*     */   private static Gemstone getGemstone(IBlockState block) {
/* 108 */     if (block.func_177230_c() != Blocks.field_150399_cn && block.func_177230_c() != Blocks.field_150397_co) return null;
/*     */     
/* 110 */     EnumDyeColor color = (EnumDyeColor)Utils.firstNotNull((Object[])new EnumDyeColor[] { (EnumDyeColor)block.func_177229_b((IProperty)BlockStainedGlass.field_176547_a), (EnumDyeColor)block.func_177229_b((IProperty)BlockStainedGlassPane.field_176245_a) });
/*     */     
/* 112 */     if (color == Gemstone.RUBY.dyeColor) return Gemstone.RUBY; 
/* 113 */     if (color == Gemstone.AMETHYST.dyeColor) return Gemstone.AMETHYST; 
/* 114 */     if (color == Gemstone.JADE.dyeColor) return Gemstone.JADE; 
/* 115 */     if (color == Gemstone.SAPPHIRE.dyeColor) return Gemstone.SAPPHIRE; 
/* 116 */     if (color == Gemstone.AMBER.dyeColor) return Gemstone.AMBER; 
/* 117 */     if (color == Gemstone.TOPAZ.dyeColor) return Gemstone.TOPAZ; 
/* 118 */     if (color == Gemstone.JASPER.dyeColor) return Gemstone.JASPER;
/*     */     
/* 120 */     return null;
/*     */   }
/*     */   
/*     */   private static boolean isGemstoneEnabled(Gemstone gemstone) {
/* 124 */     switch (gemstone) {
/*     */       case RUBY:
/* 126 */         return Config.rubyEsp;
/*     */       case AMETHYST:
/* 128 */         return Config.amethystEsp;
/*     */       case JADE:
/* 130 */         return Config.jadeEsp;
/*     */       case SAPPHIRE:
/* 132 */         return Config.sapphireEsp;
/*     */       case AMBER:
/* 134 */         return Config.amberEsp;
/*     */       case TOPAZ:
/* 136 */         return Config.topazEsp;
/*     */       case JASPER:
/* 138 */         return Config.jasperEsp;
/*     */     } 
/* 140 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onWorldChange(WorldEvent.Load event) {
/* 146 */     this.gemstones.clear();
/* 147 */     this.checked.clear();
/* 148 */     this.lastChecked = null;
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\cheaters\get\banned\features\GemstoneESP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */