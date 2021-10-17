/*    */ package cheaters.get.banned.utils;
/*    */ import java.io.IOException;
/*    */ import org.apache.http.HttpRequest;
/*    */ import org.apache.http.client.methods.HttpGet;
/*    */ import org.apache.http.client.methods.HttpUriRequest;
/*    */ import org.apache.http.impl.client.HttpClientBuilder;
/*    */ import org.apache.http.impl.client.HttpClients;
/*    */ import org.apache.http.protocol.HttpContext;
/*    */ 
/*    */ public class HttpUtils {
/*    */   public static String fetch(String url) {
/* 12 */     return fetch(url, true);
/*    */   }
/*    */   
/*    */   public static String fetch(String url, boolean includeUserAgent) {
/* 16 */     String response = null;
/*    */     
/* 18 */     HttpClientBuilder client = HttpClients.custom().addInterceptorFirst((request, context) -> {
/*    */           if (!request.containsHeader("Pragma"))
/*    */             request.addHeader("Pragma", "no-cache");  if (!request.containsHeader("Cache-Control"))
/*    */             request.addHeader("Cache-Control", "no-cache"); 
/*    */         });
/* 23 */     if (includeUserAgent) client.setUserAgent("ShadyAddons/2.1.0-pre2");
/*    */     
/*    */     try {
/* 26 */       HttpGet request = new HttpGet(url);
/* 27 */       response = EntityUtils.toString(client.build().execute((HttpUriRequest)request).getEntity(), "UTF-8");
/* 28 */     } catch (Exception exception) {}
/*    */     
/* 30 */     return response;
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\cheaters\get\banne\\utils\HttpUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */