/*    */ package cheaters.get.banned.config.settings;
/*    */ 
/*    */ import cheaters.get.banned.config.properties.Property;
/*    */ import java.lang.reflect.Field;
/*    */ 
/*    */ 
/*    */ public abstract class Setting
/*    */ {
/*    */   public String name;
/* 10 */   public ParentSetting parent = null;
/*    */   public String credit;
/*    */   public Field field;
/*    */   public Property annotation;
/*    */   
/*    */   public Setting(Property annotation, Field field) {
/* 16 */     this.annotation = annotation;
/* 17 */     this.name = annotation.name();
/* 18 */     if (!annotation.credit().equals("")) this.credit = annotation.credit(); 
/* 19 */     this.field = field;
/*    */   }
/*    */   
/*    */   public int getIndent(int startingIndent) {
/* 23 */     return getIndent(startingIndent, this);
/*    */   }
/*    */   
/*    */   public int getIndent(int startingIndent, Setting setting) {
/* 27 */     if (setting.parent != null) {
/* 28 */       startingIndent += 10;
/* 29 */       return setting.getIndent(startingIndent, setting.parent);
/*    */     } 
/* 31 */     return startingIndent;
/*    */   }
/*    */ 
/*    */   
/*    */   public <T> T get(Class<T> type) {
/*    */     try {
/* 37 */       return type.cast(this.field.get(Object.class));
/* 38 */     } catch (Exception exception) {
/* 39 */       return null;
/*    */     } 
/*    */   }
/*    */   public boolean set(Object value) {
/*    */     try {
/* 44 */       this.field.set(value.getClass(), value);
/* 45 */       return true;
/* 46 */     } catch (Exception exception) {
/* 47 */       return false;
/*    */     } 
/*    */   }
/*    */   public boolean forceSet(Object value) {
/*    */     try {
/* 52 */       this.field.set(value.getClass(), value);
/* 53 */       return true;
/* 54 */     } catch (Exception exception) {
/* 55 */       return false;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\cheaters\get\banned\config\settings\Setting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */