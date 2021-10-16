/*     */ package cheaters.get.banned.features;
/*     */ 
/*     */ import cheaters.get.banned.Shady;
/*     */ import cheaters.get.banned.config.Config;
/*     */ import cheaters.get.banned.events.TickEndEvent;
/*     */ import cheaters.get.banned.utils.MathUtils;
/*     */ import cheaters.get.banned.utils.RenderUtils;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.client.event.RenderGameOverlayEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ 
/*     */ 
/*     */ public class CatGirls
/*     */ {
/*  17 */   public static ArrayList<CatPerson> currentCatPeople = new ArrayList<>();
/*     */   private static final int catGirlCount = 5;
/*     */   private static final int catBoyCount = 5;
/*     */   
/*     */   private static class CatPerson {
/*     */     float percentage;
/*     */     Side side;
/*     */     ResourceLocation image;
/*     */     int size;
/*     */     
/*     */     public CatPerson(float percentage, Side side, ResourceLocation image, int size) {
/*  28 */       this.percentage = percentage;
/*  29 */       this.side = side;
/*  30 */       this.image = image;
/*  31 */       this.size = size;
/*     */     }
/*     */     
/*     */     enum Side {
/*  35 */       TOP, BOTTOM, LEFT, RIGHT; } } enum Side { TOP, BOTTOM, LEFT, RIGHT; }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void addRandomCatPerson() {
/*  40 */     String sex = (MathUtils.random(0, 1) == 0) ? "boy" : "girl";
/*  41 */     addRandomCatPerson(sex);
/*     */   }
/*     */   
/*     */   public static void addRandomCatPerson(String sex) {
/*     */     int index;
/*  46 */     float percentage = MathUtils.random(20, 80) / 100.0F;
/*     */     
/*  48 */     if (sex.equals("boy")) {
/*  49 */       index = MathUtils.random(1, 5);
/*     */     } else {
/*  51 */       index = MathUtils.random(1, 5);
/*     */     } 
/*     */     
/*  54 */     CatPerson.Side side = CatPerson.Side.values()[MathUtils.random(0, 3)];
/*  55 */     ResourceLocation image = new ResourceLocation("shadyaddons:catpeople/cat" + sex + "_" + index + ".png");
/*  56 */     int size = MathUtils.random(75, 200);
/*     */     
/*  58 */     CatPerson catPerson = new CatPerson(percentage, side, image, size);
/*  59 */     currentCatPeople.add(catPerson);
/*     */   }
/*     */   
/*  62 */   private int counter = 0;
/*     */   @SubscribeEvent
/*     */   public void onTick(TickEndEvent event) {
/*  65 */     if (Config.catGirls) {
/*  66 */       if (this.counter % 20 == 0) {
/*  67 */         if (MathUtils.random(1, 5) == 5) {
/*  68 */           if (Config.catGirlsMode == 0) addRandomCatPerson("girl"); 
/*  69 */           if (Config.catGirlsMode == 1) addRandomCatPerson("boy"); 
/*  70 */           if (Config.catGirlsMode == 2) addRandomCatPerson(); 
/*     */         } 
/*  72 */         this.counter = 0;
/*     */       } 
/*  74 */       this.counter++;
/*     */     } else {
/*  76 */       currentCatPeople.clear();
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onRenderOverlay(RenderGameOverlayEvent.Pre event) {
/*  82 */     if (event.type == RenderGameOverlayEvent.ElementType.HOTBAR && Config.catGirls)
/*  83 */       for (CatPerson catPerson : currentCatPeople) {
/*  84 */         ScaledResolution scaledResolution = new ScaledResolution(Shady.mc);
/*     */         
/*  86 */         int x = 0;
/*  87 */         int y = 0;
/*  88 */         int angle = 0;
/*     */         
/*  90 */         switch (catPerson.side) {
/*     */           case BOTTOM:
/*  92 */             x = (int)(scaledResolution.func_78326_a() * catPerson.percentage);
/*  93 */             y = scaledResolution.func_78328_b() - catPerson.size;
/*     */             break;
/*     */           
/*     */           case TOP:
/*  97 */             x = (int)(scaledResolution.func_78326_a() * catPerson.percentage);
/*  98 */             y = 0;
/*  99 */             angle = 180;
/*     */             break;
/*     */           
/*     */           case RIGHT:
/* 103 */             x = scaledResolution.func_78326_a() - catPerson.size;
/* 104 */             y = (int)(scaledResolution.func_78328_b() * catPerson.percentage);
/* 105 */             angle = -90;
/*     */             break;
/*     */           
/*     */           case LEFT:
/* 109 */             x = 0;
/* 110 */             y = (int)(scaledResolution.func_78328_b() * catPerson.percentage);
/* 111 */             angle = 90;
/*     */             break;
/*     */         } 
/*     */         
/* 115 */         RenderUtils.drawRotatedTexture(catPerson.image, x, y, catPerson.size, catPerson.size, angle);
/*     */       }  
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Desktop\ShadyAddons-2.1.0-pre2.jar!\cheaters\get\banned\features\CatGirls.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */