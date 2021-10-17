/*    */ package cheaters.get.banned.features;
/*    */ 
/*    */ import cheaters.get.banned.Shady;
/*    */ import cheaters.get.banned.config.Config;
/*    */ import cheaters.get.banned.utils.Utils;
/*    */ import net.minecraft.client.gui.inventory.GuiChest;
/*    */ import net.minecraft.inventory.ContainerChest;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.client.event.GuiOpenEvent;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ public class RoyalPigeonMacro
/*    */ {
/*    */   @SubscribeEvent
/*    */   public void onGuiOpen(GuiOpenEvent event) {
/* 16 */     if (Utils.inSkyBlock && Config.royalPigeonMacro && event.gui instanceof GuiChest) {
/* 17 */       String chestName = ((ContainerChest)((GuiChest)event.gui).field_147002_h).func_85151_d().func_145748_c_().func_150260_c();
/*    */       
/* 19 */       if (chestName.contains("Commissions") && 
/* 20 */         Shady.mc.field_71439_g.func_70694_bm().func_82833_r().contains("Royal Pigeon"))
/* 21 */         for (int i = 0; i < 9; i++) {
/* 22 */           ItemStack item = Shady.mc.field_71439_g.field_71071_by.func_70301_a(i);
/* 23 */           if (item != null && 
/* 24 */             item.func_82833_r().contains("Refined")) {
/* 25 */             Shady.mc.field_71439_g.field_71071_by.field_70461_c = i;
/*    */             break;
/*    */           } 
/*    */         }  
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\cheaters\get\banned\features\RoyalPigeonMacro.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */