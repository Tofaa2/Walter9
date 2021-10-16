/*    */ package cheaters.get.banned.config.components;
/*    */ 
/*    */ import cheaters.get.banned.config.settings.BooleanSetting;
/*    */ import cheaters.get.banned.config.settings.NumberSetting;
/*    */ import cheaters.get.banned.config.settings.SelectSetting;
/*    */ import cheaters.get.banned.config.settings.Setting;
/*    */ import java.awt.Color;
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ 
/*    */ public abstract class ConfigInput
/*    */   extends GuiButton
/*    */ {
/* 13 */   public static Color white = new Color(255, 255, 255);
/* 14 */   public static Color green = new Color(85, 255, 85);
/* 15 */   public static Color red = new Color(255, 85, 85);
/* 16 */   public static Color transparent = new Color(255, 255, 255, 64);
/*    */   
/*    */   public Setting setting;
/*    */   
/*    */   public ConfigInput(Setting setting, int x, int y) {
/* 21 */     super(0, x, y, "");
/* 22 */     this.setting = setting;
/*    */   }
/*    */   
/*    */   public static ConfigInput createButtonForSetting(Setting setting, int x, int y) {
/* 26 */     if (setting instanceof BooleanSetting)
/* 27 */       return new SwitchInput((BooleanSetting)setting, x, y); 
/* 28 */     if (setting instanceof NumberSetting)
/* 29 */       return new NumberInput((NumberSetting)setting, x, y); 
/* 30 */     if (setting instanceof SelectSetting) {
/* 31 */       return new SelectInput((SelectSetting)setting, x, y);
/*    */     }
/* 33 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Desktop\ShadyAddons-2.1.0-pre2.jar!\cheaters\get\banned\config\components\ConfigInput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */