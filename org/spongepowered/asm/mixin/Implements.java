package org.spongepowered.asm.mixin;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
public @interface Implements {
  Interface[] value();
}


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\org\spongepowered\asm\mixin\Implements.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */