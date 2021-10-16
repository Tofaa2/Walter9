/*    */ package cheaters.get.banned.mixins;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.client.SplashProgress;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.ModifyVariable;
/*    */ 
/*    */ @Mixin({SplashProgress.class})
/*    */ public abstract class MixinSlashProgress
/*    */ {
/*    */   @ModifyVariable(method = {"start"}, at = @At("STORE"), ordinal = 1, remap = false)
/*    */   private static ResourceLocation setSplash(ResourceLocation resourceLocation) {
/* 14 */     return new ResourceLocation("shadyaddons:splash.png");
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Desktop\ShadyAddons-2.1.0-pre2.jar!\cheaters\get\banned\mixins\MixinSlashProgress.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */