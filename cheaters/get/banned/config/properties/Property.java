/*    */ package cheaters.get.banned.config.properties;@Retention(RetentionPolicy.RUNTIME)
/*    */ @Target({ElementType.FIELD})
/*    */ @Inherited
/*    */ public @interface Property {
/*    */   Type type();
/*    */   
/*    */   String name();
/*    */   
/*    */   String parent() default "";
/*    */   
/*    */   String credit() default "";
/*    */   
/*    */   int step() default 1;
/*    */   
/*    */   String prefix() default "";
/*    */   
/*    */   String suffix() default "";
/*    */   
/*    */   int min() default 0;
/*    */   
/*    */   int max() default 2147483647;
/*    */   
/*    */   String[] options() default {};
/*    */   
/*    */   public enum Type {
/* 26 */     BOOLEAN, FOLDER, NUMBER, SELECT;
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\cheaters\get\banned\config\properties\Property.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */