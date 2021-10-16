package org.spongepowered.asm.mixin.extensibility;

public interface IRemapper {
  String mapMethodName(String paramString1, String paramString2, String paramString3);
  
  String mapFieldName(String paramString1, String paramString2, String paramString3);
  
  String map(String paramString);
  
  String unmap(String paramString);
  
  String mapDesc(String paramString);
  
  String unmapDesc(String paramString);
}


/* Location:              C:\Users\amine\Desktop\ShadyAddons-2.1.0-pre2.jar!\org\spongepowered\asm\mixin\extensibility\IRemapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */