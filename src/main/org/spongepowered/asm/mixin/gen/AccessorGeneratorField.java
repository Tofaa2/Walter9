/*    */ package org.spongepowered.asm.mixin.gen;
/*    */ 
/*    */ import org.spongepowered.asm.lib.Type;
/*    */ import org.spongepowered.asm.lib.tree.FieldNode;
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
/*    */ public abstract class AccessorGeneratorField
/*    */   extends AccessorGenerator
/*    */ {
/*    */   protected final FieldNode targetField;
/*    */   protected final Type targetType;
/*    */   protected final boolean isInstanceField;
/*    */   
/*    */   public AccessorGeneratorField(AccessorInfo info) {
/* 52 */     super(info);
/* 53 */     this.targetField = info.getTargetField();
/* 54 */     this.targetType = info.getTargetFieldType();
/* 55 */     this.isInstanceField = ((this.targetField.access & 0x8) == 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Desktop\ShadyAddons-2.1.0-pre2.jar!\org\spongepowered\asm\mixin\gen\AccessorGeneratorField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */