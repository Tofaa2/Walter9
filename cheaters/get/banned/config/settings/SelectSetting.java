/*    */ package cheaters.get.banned.config.settings;
/*    */ 
/*    */ import cheaters.get.banned.config.properties.Property;
/*    */ import java.lang.reflect.Field;
/*    */ 
/*    */ public class SelectSetting
/*    */   extends Setting
/*    */ {
/*    */   public String[] options;
/*    */   
/*    */   public SelectSetting(Property annotation, Field field) {
/* 12 */     super(annotation, field);
/* 13 */     this.options = annotation.options();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean set(Object value) {
/* 18 */     if (((Number)value).intValue() > this.options.length - 1) return super.set(Integer.valueOf(0)); 
/* 19 */     if (((Number)value).intValue() < 0) return super.set(Integer.valueOf(this.options.length - 1)); 
/* 20 */     return super.set(value);
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\cheaters\get\banned\config\settings\SelectSetting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */