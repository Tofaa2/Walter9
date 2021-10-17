/*    */ package cheaters.get.banned.config.settings;
/*    */ 
/*    */ import cheaters.get.banned.config.properties.Property;
/*    */ import java.lang.reflect.Field;
/*    */ import net.minecraft.util.MathHelper;
/*    */ 
/*    */ public class NumberSetting
/*    */   extends Setting
/*    */   implements Comparable<Integer> {
/*    */   public int step;
/*    */   public int min;
/*    */   public int max;
/*    */   public String prefix;
/*    */   public String suffix;
/*    */   
/*    */   public NumberSetting(Property annotation, Field field) {
/* 17 */     super(annotation, field);
/* 18 */     this.step = annotation.step();
/* 19 */     this.min = annotation.min();
/* 20 */     this.max = annotation.max();
/* 21 */     this.prefix = annotation.prefix();
/* 22 */     this.suffix = annotation.suffix();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean set(Object value) {
/* 27 */     return super.set(Integer.valueOf(MathHelper.func_76125_a(((Integer)value).intValue(), 0, this.max)));
/*    */   }
/*    */ 
/*    */   
/*    */   public int compareTo(Integer other) {
/*    */     try {
/* 33 */       return Integer.compare(((Integer)get((Class)int.class)).intValue(), other.intValue());
/* 34 */     } catch (Exception exception) {
/* 35 */       return 0;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\cheaters\get\banned\config\settings\NumberSetting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */