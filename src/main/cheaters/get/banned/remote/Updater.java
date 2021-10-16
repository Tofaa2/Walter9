/*    */ package cheaters.get.banned.remote;
/*    */ 
/*    */ import cheaters.get.banned.utils.HttpUtils;
/*    */ import com.google.gson.Gson;
/*    */ 
/*    */ 
/*    */ public class Updater
/*    */ {
/*    */   public static boolean shouldUpdate = false;
/* 10 */   public static Update update = null;
/*    */   
/*    */   public static class Update {
/*    */     public String version;
/*    */     public String download;
/*    */     public String description;
/*    */     
/*    */     public Update(String version, String download, String description) {
/* 18 */       this.version = version;
/* 19 */       this.download = download;
/* 20 */       this.description = description;
/*    */     }
/*    */   }
/*    */   
/*    */   public static void check() {
/* 25 */     (new Thread(() -> {
/*    */           String url = "https://cheatersgetbanned.me/api/updates";
/*    */           String response = null;
/*    */           try {
/*    */             response = HttpUtils.fetch(url);
/* 30 */           } catch (Exception exception) {}
/*    */           
/*    */           if (response != null) {
/*    */             update = (Update)(new Gson()).fromJson(response, Update.class);
/*    */             shouldUpdate = !update.version.equals("2.1.0-pre2");
/*    */           } else {
/*    */             System.out.println("Error checking for updates");
/*    */           } 
/* 38 */         }"ShadyAddons-Updater")).start();
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Desktop\ShadyAddons-2.1.0-pre2.jar!\cheaters\get\banned\remote\Updater.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */