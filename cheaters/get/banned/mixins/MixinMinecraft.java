/*    */ package cheaters.get.banned.mixins;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.texture.TextureManager;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Mutable;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({Minecraft.class})
/*    */ public abstract class MixinMinecraft
/*    */ {
/*    */   @Inject(method = {"drawSplashScreen"}, at = {@At("HEAD")})
/*    */   public void modifyMojangLogo(TextureManager textureManagerInstance, CallbackInfo callbackInfo) {
/* 22 */     field_110444_H = new ResourceLocation("shadyaddons:splash.png");
/*    */   }
/*    */   
/*    */   @Mutable
/*    */   @Shadow
/*    */   @Final
/*    */   private static ResourceLocation field_110444_H;
/*    */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\cheaters\get\banned\mixins\MixinMinecraft.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */