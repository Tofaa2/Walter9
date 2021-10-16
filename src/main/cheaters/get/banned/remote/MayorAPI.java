/*    */ package cheaters.get.banned.remote;
/*    */ 
/*    */ import cheaters.get.banned.utils.HttpUtils;
/*    */ import com.google.gson.Gson;
/*    */ 
/*    */ public class MayorAPI
/*    */ {
/*  8 */   private static Mayor mayor = null;
/*    */   
/*    */   static class Mayor {
/*    */     public String name;
/*    */     public boolean ezpz;
/*    */     
/*    */     public Mayor(String name, boolean ezpz) {
/* 15 */       this.name = name;
/* 16 */       this.ezpz = ezpz;
/*    */     }
/*    */   }
/*    */   
/*    */   public static void fetch() {
/* 21 */     (new Thread(() -> {
/*    */           String response = null;
/*    */           try {
/*    */             response = HttpUtils.fetch("https://cheatersgetbanned.me/api/mayor/");
/* 25 */           } catch (Exception exception) {}
/*    */           if (response != null) {
/*    */             mayor = (Mayor)(new Gson()).fromJson(response, Mayor.class);
/*    */           } else {
/*    */             System.out.println("Error fetching current mayor");
/*    */           } 
/* 31 */         }"ShadyAddons-MayorAPI")).start();
/*    */   }
/*    */   
/*    */   public static boolean isPaulBonus() {
/* 35 */     return (mayor != null && mayor.name.equals("paul") && mayor.ezpz);
/*    */   }
/*    */   
/*    */   public static Mayor getMayor() {
/* 39 */     return mayor;
/*    */   }
/*    */   
/*    */   public static void forcePaul() {
/* 43 */     mayor = new Mayor("paul", true);
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Desktop\ShadyAddons-2.1.0-pre2.jar!\cheaters\get\banned\remote\MayorAPI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */