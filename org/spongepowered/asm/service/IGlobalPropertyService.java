package org.spongepowered.asm.service;

public interface IGlobalPropertyService {
  <T> T getProperty(String paramString);
  
  void setProperty(String paramString, Object paramObject);
  
  <T> T getProperty(String paramString, T paramT);
  
  String getPropertyString(String paramString1, String paramString2);
}


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\org\spongepowered\asm\service\IGlobalPropertyService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */