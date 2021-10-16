package org.spongepowered.asm.mixin.extensibility;

import java.util.List;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.mixin.MixinEnvironment;

public interface IMixinInfo {
  IMixinConfig getConfig();
  
  String getName();
  
  String getClassName();
  
  String getClassRef();
  
  byte[] getClassBytes();
  
  boolean isDetachedSuper();
  
  ClassNode getClassNode(int paramInt);
  
  List<String> getTargetClasses();
  
  int getPriority();
  
  MixinEnvironment.Phase getPhase();
}


/* Location:              C:\Users\amine\Desktop\ShadyAddons-2.1.0-pre2.jar!\org\spongepowered\asm\mixin\extensibility\IMixinInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */