/*    */ package cheaters.get.banned.remote;
/*    */ 
/*    */ import cheaters.get.banned.Shady;
/*    */ import cheaters.get.banned.utils.Utils;
/*    */ import java.awt.Color;
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ import net.minecraft.client.gui.GuiMainMenu;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ 
/*    */ 
/*    */ public class UpdateGui
/*    */   extends GuiScreen
/*    */ {
/*    */   public void func_73866_w_() {
/* 15 */     this.field_146292_n.add(new GuiButton(1, this.field_146294_l / 2 - 105, this.field_146295_m / 2 + 10, 100, 20, "Ignore"));
/* 16 */     this.field_146292_n.add(new GuiButton(0, this.field_146294_l / 2 + 5, this.field_146295_m / 2 + 10, 100, 20, "Download"));
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_146284_a(GuiButton button) {
/* 21 */     if (button.field_146127_k == 0) {
/* 22 */       Utils.openUrl(Updater.update.download);
/*    */     }
/* 24 */     Shady.guiToOpen = (GuiScreen)new GuiMainMenu();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
/* 29 */     func_146276_q_();
/*    */     
/* 31 */     String title = "ShadyAddons " + ((Shady.usingSkyBlockAddons && (!Shady.usingPatcher || Shady.usingSkytils)) ? "§z" : "§c") + Updater.update.version + "§f is available!";
/* 32 */     int titleWidth = this.field_146297_k.field_71466_p.func_78256_a(title);
/* 33 */     this.field_146297_k.field_71466_p.func_175063_a(title, (this.field_146294_l - titleWidth) / 2.0F, this.field_146295_m / 2.0F - 20.0F - 5.0F, Color.WHITE
/*    */ 
/*    */ 
/*    */         
/* 37 */         .getRGB());
/*    */ 
/*    */     
/* 40 */     if (Updater.update.description == null) Updater.update.description = "§7§oNo update description"; 
/* 41 */     int descriptionWidth = this.field_146297_k.field_71466_p.func_78256_a(Updater.update.description);
/* 42 */     this.field_146297_k.field_71466_p.func_175063_a(Updater.update.description, (this.field_146294_l - descriptionWidth) / 2.0F, this.field_146295_m / 2.0F - 7.0F, Color.WHITE
/*    */ 
/*    */ 
/*    */         
/* 46 */         .getRGB());
/*    */ 
/*    */     
/* 49 */     super.func_73863_a(mouseX, mouseY, partialTicks);
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\cheaters\get\banned\remote\UpdateGui.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */