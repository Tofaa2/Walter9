/*    */ package cheaters.get.banned.features;
/*    */ 
/*    */ import cheaters.get.banned.Shady;
/*    */ import cheaters.get.banned.config.Config;
/*    */ import cheaters.get.banned.utils.KeybindUtils;
/*    */ import cheaters.get.banned.utils.Utils;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.gameevent.InputEvent;
/*    */ 
/*    */ 
/*    */ public class AbilityKeybind
/*    */ {
/*    */   public AbilityKeybind() {
/* 14 */     KeybindUtils.register("Use Normal Ability", 45);
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onKeyInput(InputEvent.KeyInputEvent event) {
/* 19 */     if (Config.normalAbilityKeybind && KeybindUtils.get("Use Normal Ability").func_151468_f() && Utils.inDungeon)
/* 20 */       Shady.mc.field_71439_g.func_71040_bB(true); 
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Desktop\ShadyAddons-2.1.0-pre2.jar!\cheaters\get\banned\features\AbilityKeybind.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */