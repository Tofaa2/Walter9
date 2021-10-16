/*    */ package cheaters.get.banned.config.components;
/*    */ 
/*    */ import cheaters.get.banned.Shady;
/*    */ import cheaters.get.banned.config.settings.NumberSetting;
/*    */ import cheaters.get.banned.config.settings.Setting;
/*    */ import net.minecraft.client.Minecraft;
/*    */ 
/*    */ public class NumberInput extends ConfigInput {
/*  9 */   private int minusWidth = Shady.mc.field_71466_p.func_78256_a("-");
/* 10 */   private int plusWidth = Shady.mc.field_71466_p.func_78256_a("+");
/* 11 */   private int gap = 3;
/*    */   
/*    */   private boolean minusHovered = false;
/*    */   private boolean plusHovered = false;
/*    */   public NumberSetting setting;
/*    */   
/*    */   public NumberInput(NumberSetting setting, int x, int y) {
/* 18 */     super((Setting)setting, x, y);
/* 19 */     this.setting = setting;
/* 20 */     this.field_146121_g = 10;
/* 21 */     updateText();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_146112_a(Minecraft mc, int mouseX, int mouseY) {
/* 26 */     this.plusHovered = (mouseX >= this.field_146128_h - this.plusWidth - this.gap && mouseY >= this.field_146129_i && mouseX < this.field_146128_h && mouseY < this.field_146129_i + this.field_146121_g);
/* 27 */     this.minusHovered = (mouseX >= this.field_146128_h - this.field_146120_f && mouseY >= this.field_146129_i && mouseX < this.field_146128_h - this.field_146120_f + this.minusWidth + this.gap && mouseY < this.field_146129_i + this.field_146121_g);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 35 */     Shady.mc.field_71466_p.func_78276_b((this.minusHovered ? "§c" : "§7") + "-", this.field_146128_h - this.field_146120_f, this.field_146129_i, -1);
/* 36 */     Shady.mc.field_71466_p.func_78276_b(this.field_146126_j, this.field_146128_h - this.field_146120_f + this.minusWidth + this.gap, this.field_146129_i, -1);
/* 37 */     Shady.mc.field_71466_p.func_78276_b((this.plusHovered ? "§a" : "§7") + "+", this.field_146128_h - this.plusWidth, this.field_146129_i, -1);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_146116_c(Minecraft mc, int mouseX, int mouseY) {
/* 42 */     if (this.plusHovered || this.minusHovered) {
/* 43 */       if (this.plusHovered) this.setting.set(Integer.valueOf(((Integer)this.setting.get(Integer.class)).intValue() + this.setting.step)); 
/* 44 */       if (this.minusHovered) this.setting.set(Integer.valueOf(((Integer)this.setting.get(Integer.class)).intValue() - this.setting.step)); 
/* 45 */       updateText();
/* 46 */       return true;
/*    */     } 
/* 48 */     return false;
/*    */   }
/*    */   
/*    */   public void updateText() {
/* 52 */     this.field_146126_j = ((this.setting.prefix == null) ? "" : this.setting.prefix) + this.setting.get(Integer.class) + ((this.setting.suffix == null) ? "" : this.setting.suffix);
/* 53 */     this.field_146120_f = Shady.mc.field_71466_p.func_78256_a(this.field_146126_j) + this.plusWidth + this.minusWidth + this.gap * 2;
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Desktop\ShadyAddons-2.1.0-pre2.jar!\cheaters\get\banned\config\components\NumberInput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */