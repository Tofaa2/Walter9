/*    */ package cheaters.get.banned.config.components;
/*    */ 
/*    */ import cheaters.get.banned.Shady;
/*    */ import cheaters.get.banned.config.settings.SelectSetting;
/*    */ import cheaters.get.banned.config.settings.Setting;
/*    */ import net.minecraft.client.Minecraft;
/*    */ 
/*    */ public class SelectInput extends ConfigInput {
/*  9 */   private int leftWidth = Shady.mc.field_71466_p.func_78256_a("<");
/* 10 */   private int rightWidth = Shady.mc.field_71466_p.func_78256_a(">");
/* 11 */   private int gap = 3;
/*    */   
/*    */   private boolean leftHovered = false;
/*    */   private boolean rightHovered = false;
/*    */   public SelectSetting setting;
/*    */   
/*    */   public SelectInput(SelectSetting setting, int x, int y) {
/* 18 */     super((Setting)setting, x, y);
/* 19 */     this.setting = setting;
/* 20 */     this.field_146121_g = 10;
/* 21 */     updateText();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_146112_a(Minecraft mc, int mouseX, int mouseY) {
/* 26 */     this.rightHovered = (mouseX >= this.field_146128_h - this.rightWidth - this.gap && mouseY >= this.field_146129_i && mouseX < this.field_146128_h && mouseY < this.field_146129_i + this.field_146121_g);
/* 27 */     this.leftHovered = (mouseX >= this.field_146128_h - this.field_146120_f && mouseY >= this.field_146129_i && mouseX < this.field_146128_h - this.field_146120_f + this.leftWidth + this.gap && mouseY < this.field_146129_i + this.field_146121_g);
/*    */     
/* 29 */     Shady.mc.field_71466_p.func_78276_b((this.leftHovered ? "§a" : "§7") + "<", this.field_146128_h - this.field_146120_f, this.field_146129_i, -1);
/* 30 */     Shady.mc.field_71466_p.func_78276_b(this.field_146126_j, this.field_146128_h - this.field_146120_f + this.leftWidth + this.gap, this.field_146129_i, -1);
/* 31 */     Shady.mc.field_71466_p.func_78276_b((this.rightHovered ? "§a" : "§7") + ">", this.field_146128_h - this.rightWidth, this.field_146129_i, -1);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_146116_c(Minecraft mc, int mouseX, int mouseY) {
/* 36 */     if (this.rightHovered || this.leftHovered) {
/* 37 */       if (this.rightHovered) this.setting.set(Integer.valueOf(((Integer)this.setting.get(Integer.class)).intValue() + 1)); 
/* 38 */       if (this.leftHovered) this.setting.set(Integer.valueOf(((Integer)this.setting.get(Integer.class)).intValue() - 1)); 
/* 39 */       updateText();
/* 40 */       return true;
/*    */     } 
/* 42 */     return false;
/*    */   }
/*    */   
/*    */   public void updateText() {
/* 46 */     this.field_146126_j = this.setting.options[((Integer)this.setting.get(Integer.class)).intValue()];
/* 47 */     this.field_146120_f = Shady.mc.field_71466_p.func_78256_a(this.field_146126_j) + this.rightWidth + this.leftWidth + this.gap * 2;
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Desktop\ShadyAddons-2.1.0-pre2.jar!\cheaters\get\banned\config\components\SelectInput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */