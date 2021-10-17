/*     */ package me.Danker.handlers;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.URL;
/*     */ import java.util.Scanner;
/*     */ import me.Danker.DankersSkyblockMod;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ 
/*     */ public class APIHandler {
/*     */   public static JsonObject getResponse(String urlString) {
/*  22 */     EntityPlayerSP entityPlayerSP = (Minecraft.func_71410_x()).field_71439_g;
/*     */     
/*     */     try {
/*  25 */       URL url = new URL(urlString);
/*  26 */       HttpURLConnection conn = (HttpURLConnection)url.openConnection();
/*  27 */       conn.setRequestMethod("GET");
/*     */       
/*  29 */       if (conn.getResponseCode() == 200) {
/*  30 */         BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
/*     */         
/*  32 */         StringBuilder response = new StringBuilder();
/*     */         String input;
/*  34 */         while ((input = in.readLine()) != null) {
/*  35 */           response.append(input);
/*     */         }
/*  37 */         in.close();
/*     */         
/*  39 */         Gson gson = new Gson();
/*     */         
/*  41 */         return (JsonObject)gson.fromJson(response.toString(), JsonObject.class);
/*     */       } 
/*  43 */       if (urlString.startsWith("https://api.hypixel.net/")) {
/*  44 */         InputStream errorStream = conn.getErrorStream();
/*  45 */         try (Scanner scanner = new Scanner(errorStream)) {
/*  46 */           scanner.useDelimiter("\\Z");
/*  47 */           String error = scanner.next();
/*     */           
/*  49 */           Gson gson = new Gson();
/*  50 */           return (JsonObject)gson.fromJson(error, JsonObject.class);
/*     */         } 
/*  52 */       }  if (urlString.startsWith("https://api.mojang.com/users/profiles/minecraft/") && conn.getResponseCode() == 204) {
/*  53 */         entityPlayerSP.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Failed with reason: Player does not exist."));
/*     */       } else {
/*  55 */         entityPlayerSP.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Request failed. HTTP Error Code: " + conn.getResponseCode()));
/*     */       }
/*     */     
/*  58 */     } catch (IOException ex) {
/*  59 */       entityPlayerSP.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "An error has occured. See logs for more details."));
/*  60 */       ex.printStackTrace();
/*     */     } 
/*     */     
/*  63 */     return new JsonObject();
/*     */   }
/*     */ 
/*     */   
/*     */   public static JsonArray getArrayResponse(String urlString) {
/*  68 */     EntityPlayerSP entityPlayerSP = (Minecraft.func_71410_x()).field_71439_g;
/*     */     
/*     */     try {
/*  71 */       URL url = new URL(urlString);
/*  72 */       HttpURLConnection conn = (HttpURLConnection)url.openConnection();
/*  73 */       conn.setRequestMethod("GET");
/*     */       
/*  75 */       if (conn.getResponseCode() == 200) {
/*  76 */         BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
/*     */         
/*  78 */         StringBuilder response = new StringBuilder();
/*     */         String input;
/*  80 */         while ((input = in.readLine()) != null) {
/*  81 */           response.append(input);
/*     */         }
/*  83 */         in.close();
/*     */         
/*  85 */         Gson gson = new Gson();
/*     */         
/*  87 */         return (JsonArray)gson.fromJson(response.toString(), JsonArray.class);
/*     */       } 
/*  89 */       entityPlayerSP.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Request failed. HTTP Error Code: " + conn.getResponseCode()));
/*     */     }
/*  91 */     catch (IOException ex) {
/*  92 */       entityPlayerSP.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "An error has occured. See logs for more details."));
/*  93 */       ex.printStackTrace();
/*     */     } 
/*     */     
/*  96 */     return new JsonArray();
/*     */   }
/*     */   
/*     */   public static String getUUID(String username) {
/* 100 */     JsonObject uuidResponse = getResponse("https://api.mojang.com/users/profiles/minecraft/" + username);
/* 101 */     return uuidResponse.get("id").getAsString();
/*     */   }
/*     */   
/*     */   public static String getLatestProfileID(String UUID, String key) {
/* 105 */     EntityPlayerSP entityPlayerSP = (Minecraft.func_71410_x()).field_71439_g;
/*     */ 
/*     */     
/* 108 */     System.out.println("Fetching profiles...");
/*     */     
/* 110 */     JsonObject profilesResponse = getResponse("https://api.hypixel.net/skyblock/profiles?uuid=" + UUID + "&key=" + key);
/* 111 */     if (!profilesResponse.get("success").getAsBoolean()) {
/* 112 */       String reason = profilesResponse.get("cause").getAsString();
/* 113 */       entityPlayerSP.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Failed with reason: " + reason));
/* 114 */       return null;
/*     */     } 
/* 116 */     if (profilesResponse.get("profiles").isJsonNull()) {
/* 117 */       entityPlayerSP.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "This player doesn't appear to have played SkyBlock."));
/* 118 */       return null;
/*     */     } 
/*     */ 
/*     */     
/* 122 */     System.out.println("Looping through profiles...");
/* 123 */     String latestProfile = "";
/* 124 */     long latestSave = 0L;
/* 125 */     JsonArray profilesArray = profilesResponse.get("profiles").getAsJsonArray();
/*     */     
/* 127 */     for (JsonElement profile : profilesArray) {
/* 128 */       JsonObject profileJSON = profile.getAsJsonObject();
/* 129 */       long profileLastSave = 1L;
/* 130 */       if (profileJSON.get("members").getAsJsonObject().get(UUID).getAsJsonObject().has("last_save")) {
/* 131 */         profileLastSave = profileJSON.get("members").getAsJsonObject().get(UUID).getAsJsonObject().get("last_save").getAsLong();
/*     */       }
/*     */       
/* 134 */       if (profileLastSave > latestSave) {
/* 135 */         latestProfile = profileJSON.get("profile_id").getAsString();
/* 136 */         latestSave = profileLastSave;
/*     */       } 
/*     */     } 
/*     */     
/* 140 */     return latestProfile;
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\me\Danker\handlers\APIHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */