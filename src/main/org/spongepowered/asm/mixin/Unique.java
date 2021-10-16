package org.spongepowered.asm.mixin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Unique {
  boolean silent() default false;
}


/* Location:              C:\Users\amine\Desktop\ShadyAddons-2.1.0-pre2.jar!\org\spongepowered\asm\mixin\Unique.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */