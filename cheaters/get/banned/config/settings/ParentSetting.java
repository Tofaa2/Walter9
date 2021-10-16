/*    */ package cheaters.get.banned.config.settings;
/*    */ 
/*    */ import cheaters.get.banned.config.properties.Property;
/*    */ import java.lang.reflect.Field;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public abstract class ParentSetting
/*    */   extends Setting
/*    */ {
/* 10 */   public ArrayList<Setting> children = new ArrayList<>();
/*    */   
/*    */   public ParentSetting(Property annotation, Field field) {
/* 13 */     super(annotation, field);
/*    */   }
/*    */   
/*    */   public ArrayList<Setting> getChildren(ArrayList<Setting> settings) {
/* 17 */     ArrayList<Setting> children = new ArrayList<>();
/*    */     
/* 19 */     for (Setting setting : settings) {
/* 20 */       if (setting.parent == this) {
/* 21 */         children.add(setting);
/*    */       }
/*    */     } 
/*    */     
/* 25 */     return children;
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Desktop\ShadyAddons-2.1.0-pre2.jar!\cheaters\get\banned\config\settings\ParentSetting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */