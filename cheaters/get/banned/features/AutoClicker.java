/*    */ package cheaters.get.banned.features;
/*    */ 
/*    */ import cheaters.get.banned.Shady;
/*    */ import cheaters.get.banned.config.Config;
/*    */ import cheaters.get.banned.config.components.ConfigInput;
/*    */ import cheaters.get.banned.utils.KeybindUtils;
/*    */ import cheaters.get.banned.utils.ThreadUtils;
/*    */ import net.minecraft.client.gui.Gui;
/*    */ import net.minecraft.client.gui.ScaledResolution;
/*    */ import net.minecraftforge.client.event.GuiOpenEvent;
/*    */ import net.minecraftforge.client.event.RenderGameOverlayEvent;
/*    */ import net.minecraftforge.event.world.WorldEvent;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.gameevent.InputEvent;
/*    */ 
/*    */ 
/*    */ public class AutoClicker
/*    */ {
/*    */   public AutoClicker() {
/* 20 */     KeybindUtils.register("Autoclicker", 21);
/*    */   }
/*    */   
/*    */   private static boolean toggled = false;
/*    */   private static boolean burstActive = false;
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onKeyInput(InputEvent.KeyInputEvent event) {
/* 28 */     if (KeybindUtils.get("Autoclicker").func_151468_f()) {
/* 29 */       if (Config.autoClickerMode == 0 && !burstActive) {
/* 30 */         burstActive = true;
/* 31 */         (new Thread(() -> { for (int i = 0; i < 25; i++) { KeybindUtils.rightClick(); ThreadUtils.sleep(1000 / Config.autoClickerCps); }  burstActive = false; }"ShadyAddons-Autoclicker"))
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */           
/* 37 */           .start();
/* 38 */       } else if (Config.autoClickerMode == 1) {
/* 39 */         toggled = !toggled;
/* 40 */         if (toggled) {
/* 41 */           (new Thread(() -> { while (toggled) { KeybindUtils.rightClick(); ThreadUtils.sleep(1000 / Config.autoClickerCps); }  }"ShadyAddons-Autoclicker"))
/*    */ 
/*    */ 
/*    */ 
/*    */             
/* 46 */             .start();
/*    */         }
/*    */       } 
/*    */     }
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onOpenGui(GuiOpenEvent event) {
/* 54 */     if (Config.stopAutoClickerInGui) toggled = false; 
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onRenderOverlay(RenderGameOverlayEvent.Post event) {
/* 59 */     if (toggled && Config.autoClickerIndicator && event.type == RenderGameOverlayEvent.ElementType.CROSSHAIRS) {
/* 60 */       int x = (new ScaledResolution(Shady.mc)).func_78326_a() / 2 + 10;
/* 61 */       int y = (new ScaledResolution(Shady.mc)).func_78328_b() / 2 - 2;
/* 62 */       Gui.func_73734_a(x, y, x + 5, y + 5, ConfigInput.red.getRGB());
/*    */     } 
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onWorldLoad(WorldEvent.Load event) {
/* 68 */     toggled = false;
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Desktop\ShadyAddons-2.1.0-pre2.jar!\cheaters\get\banned\features\AutoClicker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */