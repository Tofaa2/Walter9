/*    */ package cheaters.get.banned.config.components;
/*    */ 
/*    */ import cheaters.get.banned.config.settings.BooleanSetting;
/*    */ import cheaters.get.banned.config.settings.Setting;
/*    */ import net.minecraft.client.Minecraft;
/*    */ 
/*    */ public class SwitchInput extends ConfigInput {
/*    */   public BooleanSetting setting;
/*    */   
/*    */   public SwitchInput(BooleanSetting setting, int x, int y) {
/* 11 */     super((Setting)setting, x - 25, y);
/* 12 */     this.setting = setting;
/* 13 */     this.field_146120_f = 25;
/* 14 */     this.field_146121_g = 10;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_146112_a(Minecraft mc, int mouseX, int mouseY) {
/* 19 */     func_73734_a(this.field_146128_h, this.field_146129_i + 3, this.field_146128_h + 25, this.field_146129_i + 6, white.getRGB());
/* 20 */     func_73734_a(((Boolean)this.setting.get(Boolean.class)).booleanValue() ? (this.field_146128_h + 15) : this.field_146128_h, this.field_146129_i, ((Boolean)this.setting.get(Boolean.class)).booleanValue() ? (this.field_146128_h + 25) : (this.field_146128_h + 10), this.field_146129_i + 10, ((Boolean)this.setting.get(Boolean.class)).booleanValue() ? green.getRGB() : red.getRGB());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_146116_c(Minecraft mc, int mouseX, int mouseY) {
/* 25 */     if (mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g) {
/* 26 */       this.setting.set(Boolean.valueOf(!((Boolean)this.setting.get(Boolean.class)).booleanValue()));
/* 27 */       return true;
/*    */     } 
/*    */     
/* 30 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Desktop\ShadyAddons-2.1.0-pre2.jar!\cheaters\get\banned\config\components\SwitchInput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */