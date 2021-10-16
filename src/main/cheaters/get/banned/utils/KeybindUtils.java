/*    */ package cheaters.get.banned.utils;
/*    */ 
/*    */ import cheaters.get.banned.Shady;
/*    */ import java.util.HashMap;
/*    */ import net.minecraft.client.settings.KeyBinding;
/*    */ 
/*    */ 
/*    */ public class KeybindUtils
/*    */ {
/* 10 */   public static HashMap<String, KeyBinding> keyBindings = new HashMap<>();
/*    */   
/*    */   public static void register(String name, int key) {
/* 13 */     keyBindings.put(name, new KeyBinding(name, key, "ShadyAddons"));
/*    */   }
/*    */   
/*    */   public static boolean isPressed(String name) {
/* 17 */     return get(name).func_151468_f();
/*    */   }
/*    */   
/*    */   public static KeyBinding get(String name) {
/* 21 */     return keyBindings.get(name);
/*    */   }
/*    */   
/*    */   public static void rightClick() {
/* 25 */     if (!ReflectionUtils.invoke(Shady.mc.getClass(), "func_147121_ag")) {
/* 26 */       ReflectionUtils.invoke(Shady.mc.getClass(), "rightClickMouse");
/*    */     }
/*    */   }
/*    */   
/*    */   public static void leftClick() {
/* 31 */     if (!ReflectionUtils.invoke(Shady.mc.getClass(), "func_147116_af")) {
/* 32 */       ReflectionUtils.invoke(Shady.mc.getClass(), "clickMouse");
/*    */     }
/*    */   }
/*    */   
/*    */   public static void middleClick() {
/* 37 */     if (!ReflectionUtils.invoke(Shady.mc.getClass(), "func_147112_ai"))
/* 38 */       ReflectionUtils.invoke(Shady.mc.getClass(), "middleClickMouse"); 
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Desktop\ShadyAddons-2.1.0-pre2.jar!\cheaters\get\banne\\utils\KeybindUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */