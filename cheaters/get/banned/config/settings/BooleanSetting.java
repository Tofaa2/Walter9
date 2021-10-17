/*    */ package cheaters.get.banned.config.settings;
/*    */ 
/*    */ import cheaters.get.banned.config.properties.Property;
/*    */ import java.lang.reflect.Field;
/*    */ 
/*    */ public class BooleanSetting
/*    */   extends ParentSetting
/*    */ {
/*    */   public BooleanSetting(Property annotation, Field field) {
/* 10 */     super(annotation, field);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean set(Object value) {
/*    */     try {
/* 16 */       for (Setting child : this.children) {
/* 17 */         if (child instanceof BooleanSetting) child.set(Boolean.valueOf(false)); 
/* 18 */         if (child instanceof SelectSetting) child.set(Integer.valueOf(0));
/*    */       
/*    */       } 
/* 21 */       return super.set(value);
/* 22 */     } catch (Exception exception) {
/* 23 */       System.out.println("Failed to set " + this.name + " to " + value);
/* 24 */       exception.printStackTrace();
/*    */       
/* 26 */       return false;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\cheaters\get\banned\config\settings\BooleanSetting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */