/*    */ package org.spongepowered.asm.launch;
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
/*    */ public class MixinInitialisationError
/*    */   extends Error
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public MixinInitialisationError() {}
/*    */   
/*    */   public MixinInitialisationError(String message) {
/* 38 */     super(message);
/*    */   }
/*    */   
/*    */   public MixinInitialisationError(Throwable cause) {
/* 42 */     super(cause);
/*    */   }
/*    */   
/*    */   public MixinInitialisationError(String message, Throwable cause) {
/* 46 */     super(message, cause);
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Desktop\ShadyAddons-2.1.0-pre2.jar!\org\spongepowered\asm\launch\MixinInitialisationError.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */