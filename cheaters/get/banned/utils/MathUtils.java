/*    */ package cheaters.get.banned.utils;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ public class MathUtils
/*    */ {
/*  7 */   private static final Random random = new Random();
/*    */   
/*    */   public static int random(int min, int max) {
/* 10 */     return random.nextInt(max - min + 1) + min;
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Desktop\ShadyAddons-2.1.0-pre2.jar!\cheaters\get\banne\\utils\MathUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */