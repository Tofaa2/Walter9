/*    */ package cheaters.get.banned.features;
/*    */ 
/*    */ import cheaters.get.banned.Shady;
/*    */ import cheaters.get.banned.config.Config;
/*    */ import cheaters.get.banned.utils.Utils;
/*    */ import net.minecraft.client.gui.inventory.GuiChest;
/*    */ import net.minecraft.inventory.ContainerChest;
/*    */ import net.minecraftforge.client.event.GuiOpenEvent;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ public class AutoCloseChest
/*    */ {
/*    */   @SubscribeEvent
/*    */   public void onDrawBackground(GuiOpenEvent event) {
/* 15 */     if (event.gui instanceof GuiChest && Utils.inSkyBlock) {
/* 16 */       String chestName = ((ContainerChest)((GuiChest)event.gui).field_147002_h).func_85151_d().func_145748_c_().func_150260_c();
/*    */       
/* 18 */       if (Utils.inDungeon && Config.closeSecretChests && chestName.equals("Chest")) {
/* 19 */         closeChest();
/*    */       }
/*    */       
/* 22 */       if (Utils.inSkyBlock && Config.closeCrystalHollowsChests && (chestName.contains("Loot Chest") || chestName.contains("Treasure Chest"))) {
/* 23 */         closeChest();
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void closeChest() {
/* 29 */     Shady.mc.field_71439_g.func_71053_j();
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\cheaters\get\banned\features\AutoCloseChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */