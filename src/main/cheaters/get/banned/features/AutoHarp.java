/*    */ package cheaters.get.banned.features;
/*    */ 
/*    */ import cheaters.get.banned.Shady;
/*    */ import cheaters.get.banned.config.Config;
/*    */ import cheaters.get.banned.events.TickEndEvent;
/*    */ import cheaters.get.banned.utils.Utils;
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.client.gui.inventory.GuiChest;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.inventory.ContainerChest;
/*    */ import net.minecraft.inventory.Slot;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemBlock;
/*    */ import net.minecraftforge.client.event.GuiOpenEvent;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ public class AutoHarp
/*    */ {
/*    */   private boolean inHarp = false;
/* 21 */   private ArrayList<Item> lastInventory = new ArrayList<>();
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onGuiOpen(GuiOpenEvent event) {
/* 25 */     if (event.gui instanceof GuiChest && Utils.inSkyBlock && Config.autoMelody) {
/* 26 */       String chestName = ((ContainerChest)((GuiChest)event.gui).field_147002_h).func_85151_d().func_145748_c_().func_150260_c();
/* 27 */       if (chestName.startsWith("Harp -")) {
/* 28 */         this.lastInventory.clear();
/* 29 */         this.inHarp = true;
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onTick(TickEndEvent event) {
/* 36 */     if (!this.inHarp || !Config.autoMelody || Shady.mc.field_71439_g == null)
/* 37 */       return;  if (!Utils.getInventoryName().startsWith("Harp -")) this.inHarp = false;
/*    */     
/* 39 */     ArrayList<Item> thisInventory = new ArrayList<>();
/* 40 */     for (Slot slot : Shady.mc.field_71439_g.field_71070_bA.field_75151_b) {
/* 41 */       if (slot.func_75211_c() != null) thisInventory.add(slot.func_75211_c().func_77973_b());
/*    */     
/*    */     } 
/* 44 */     if (!this.lastInventory.toString().equals(thisInventory.toString())) {
/* 45 */       for (Slot slot : Shady.mc.field_71439_g.field_71070_bA.field_75151_b) {
/* 46 */         if (slot.func_75211_c() != null && slot.func_75211_c().func_77973_b() instanceof ItemBlock && ((ItemBlock)slot.func_75211_c().func_77973_b()).func_179223_d() == Blocks.field_150371_ca) {
/* 47 */           Shady.mc.field_71442_b.func_78753_a(Shady.mc.field_71439_g.field_71070_bA.field_75152_c, slot.field_75222_d, 2, 0, (EntityPlayer)Shady.mc.field_71439_g);
/*    */           
/*    */           break;
/*    */         } 
/*    */       } 
/*    */     }
/* 53 */     this.lastInventory.clear();
/* 54 */     this.lastInventory.addAll(thisInventory);
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Desktop\ShadyAddons-2.1.0-pre2.jar!\cheaters\get\banned\features\AutoHarp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */