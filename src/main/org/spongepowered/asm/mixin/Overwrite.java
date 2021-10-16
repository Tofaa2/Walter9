package org.spongepowered.asm.mixin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Overwrite {
  String constraints() default "";
  
  String[] aliases() default {};
  
  boolean remap() default true;
}


/* Location:              C:\Users\amine\Desktop\ShadyAddons-2.1.0-pre2.jar!\org\spongepowered\asm\mixin\Overwrite.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */