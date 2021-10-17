/*    */ package cheaters.get.banned.utils;
/*    */ 
/*    */ import cheaters.get.banned.Shady;
/*    */ import java.lang.reflect.Method;
/*    */ 
/*    */ 
/*    */ public class ReflectionUtils
/*    */ {
/*    */   public static boolean invoke(Class<?> clazz, String methodName) {
/*    */     try {
/* 11 */       Method method = clazz.getDeclaredMethod(methodName, new Class[0]);
/* 12 */       method.setAccessible(true);
/* 13 */       method.invoke(Shady.mc, new Object[0]);
/* 14 */       return true;
/* 15 */     } catch (Exception exception) {
/* 16 */       return false;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\cheaters\get\banne\\utils\ReflectionUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */