/*    */ package org.spongepowered.asm.mixin.throwables;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MixinException
/*    */   extends RuntimeException
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public MixinException() {}
/*    */   
/*    */   public MixinException(String message) {
/* 38 */     super(message);
/*    */   }
/*    */   
/*    */   public MixinException(Throwable cause) {
/* 42 */     super(cause);
/*    */   }
/*    */   
/*    */   public MixinException(String message, Throwable cause) {
/* 46 */     super(message, cause);
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Desktop\ShadyAddons-2.1.0-pre2.jar!\org\spongepowered\asm\mixin\throwables\MixinException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */