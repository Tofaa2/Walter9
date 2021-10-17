package org.spongepowered.asm.mixin.extensibility;

import org.spongepowered.asm.mixin.MixinEnvironment;

public interface IEnvironmentTokenProvider {
  public static final int DEFAULT_PRIORITY = 1000;
  
  int getPriority();
  
  Integer getToken(String paramString, MixinEnvironment paramMixinEnvironment);
}


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\org\spongepowered\asm\mixin\extensibility\IEnvironmentTokenProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */