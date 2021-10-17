/*     */ package cheaters.get.banned.config;
/*     */ import cheaters.get.banned.Shady;
/*     */ import cheaters.get.banned.config.properties.Property;
/*     */ import cheaters.get.banned.config.settings.BooleanSetting;
/*     */ import cheaters.get.banned.config.settings.NumberSetting;
/*     */ import cheaters.get.banned.config.settings.ParentSetting;
/*     */ import cheaters.get.banned.config.settings.Setting;
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.reflect.TypeToken;
/*     */ import java.io.File;
/*     */ import java.io.Reader;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Type;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Paths;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ConfigLogic {
/*  22 */   private static String fileName = "config/ShadyAddons.cfg";
/*     */   
/*     */   public static ArrayList<Setting> collect(Class<Config> instance) {
/*  25 */     Field[] fields = instance.getDeclaredFields();
/*  26 */     ArrayList<Setting> settings = new ArrayList<>();
/*     */     
/*  28 */     for (Field field : fields) {
/*  29 */       Property annotation = field.<Property>getAnnotation(Property.class);
/*  30 */       if (annotation != null) {
/*  31 */         switch (annotation.type()) {
/*     */           case BOOLEAN:
/*  33 */             settings.add(new BooleanSetting(annotation, field));
/*     */             break;
/*     */           
/*     */           case NUMBER:
/*  37 */             settings.add(new NumberSetting(annotation, field));
/*     */             break;
/*     */           
/*     */           case SELECT:
/*  41 */             settings.add(new SelectSetting(annotation, field));
/*     */             break;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */     } 
/*  52 */     for (Setting setting : settings) {
/*  53 */       if (!setting.annotation.parent().equals("")) {
/*  54 */         setting.parent = (ParentSetting)getSetting(setting.annotation.parent(), settings);
/*  55 */         if (setting.parent != null) setting.parent.children.add(setting);
/*     */       
/*     */       } 
/*     */     } 
/*  59 */     return settings;
/*     */   }
/*     */   
/*     */   public static Setting getSetting(String name, ArrayList<Setting> settings) {
/*  63 */     for (Setting setting : settings) {
/*  64 */       if (setting.name.equals(name)) return setting; 
/*     */     } 
/*  66 */     return null;
/*     */   }
/*     */   
/*     */   public static void save() {
/*     */     try {
/*  71 */       HashMap<String, Object> convertedSettings = new HashMap<>();
/*  72 */       for (Setting setting : Shady.settings) {
/*  73 */         convertedSettings.put(setting.name, setting.get(Object.class));
/*     */       }
/*  75 */       String json = (new Gson()).toJson(convertedSettings);
/*  76 */       Files.write(Paths.get(fileName, new String[0]), json.getBytes(StandardCharsets.UTF_8), new java.nio.file.OpenOption[0]);
/*  77 */     } catch (Exception error) {
/*  78 */       System.out.println("Error while saving config file");
/*  79 */       error.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void load() {
/*     */     try {
/*  85 */       File file = new File(fileName);
/*  86 */       if (file.exists()) {
/*  87 */         Reader reader = Files.newBufferedReader(Paths.get(fileName, new String[0]));
/*  88 */         Type type = (new TypeToken<HashMap<String, Object>>() {  }).getType();
/*     */         
/*  90 */         HashMap<String, Object> settingsFromConfig = (HashMap<String, Object>)(new Gson()).fromJson(reader, type);
/*     */         
/*  92 */         for (Map.Entry<String, Object> fromConfig : settingsFromConfig.entrySet()) {
/*  93 */           Setting beingUpdated = getSetting(fromConfig.getKey(), Shady.settings);
/*  94 */           if (beingUpdated != null) {
/*  95 */             if (beingUpdated instanceof NumberSetting || beingUpdated instanceof SelectSetting) {
/*  96 */               beingUpdated.set(Integer.valueOf(((Double)fromConfig.getValue()).intValue())); continue;
/*     */             } 
/*  98 */             beingUpdated.forceSet(fromConfig.getValue());
/*     */           }
/*     */         
/*     */         } 
/*     */       } 
/* 103 */     } catch (Exception error) {
/* 104 */       System.out.println("Error while loading config file");
/* 105 */       error.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\cheaters\get\banned\config\ConfigLogic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */