/*     */ package me.Danker.gui;
/*     */ 
/*     */ import me.Danker.DankersSkyblockMod;
/*     */ import me.Danker.commands.BlockSlayerCommand;
/*     */ import me.Danker.handlers.ConfigHandler;
/*     */ import me.Danker.handlers.TextRenderer;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ 
/*     */ public class OnlySlayerGui
/*     */   extends GuiScreen {
/*  14 */   private int onlyNumberInt = 4;
/*  15 */   private String onlyName = "Revenant Horror";
/*     */   
/*     */   private GuiButton goBack;
/*     */   
/*     */   private GuiButton off;
/*     */   private GuiButton zombie;
/*     */   private GuiButton spider;
/*     */   private GuiButton wolf;
/*     */   private GuiButton one;
/*     */   private GuiButton two;
/*     */   private GuiButton three;
/*     */   private GuiButton four;
/*     */   
/*     */   public boolean func_73868_f() {
/*  29 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_73866_w_() {
/*  34 */     super.func_73866_w_();
/*     */     
/*  36 */     ScaledResolution sr = new ScaledResolution(Minecraft.func_71410_x());
/*  37 */     int height = sr.func_78328_b();
/*  38 */     int width = sr.func_78326_a();
/*     */     
/*  40 */     this.onlyName = BlockSlayerCommand.onlySlayerName;
/*  41 */     switch (BlockSlayerCommand.onlySlayerNumber) {
/*     */       case "I":
/*  43 */         this.onlyNumberInt = 1;
/*     */         break;
/*     */       case "II":
/*  46 */         this.onlyNumberInt = 2;
/*     */         break;
/*     */       case "III":
/*  49 */         this.onlyNumberInt = 3;
/*     */         break;
/*     */       default:
/*  52 */         this.onlyNumberInt = 4;
/*     */         break;
/*     */     } 
/*     */     
/*  56 */     this.goBack = new GuiButton(0, 2, height - 30, 100, 20, "Go Back");
/*  57 */     this.off = new GuiButton(0, width / 2 - 100, (int)(height * 0.3D), "Off");
/*  58 */     this.zombie = new GuiButton(0, width / 2 - 200, (int)(height * 0.4D), 120, 20, "Zombie");
/*  59 */     this.spider = new GuiButton(0, width / 2 - 60, (int)(height * 0.4D), 120, 20, "Spider");
/*  60 */     this.wolf = new GuiButton(0, width / 2 + 80, (int)(height * 0.4D), 120, 20, "Wolf");
/*  61 */     this.one = new GuiButton(0, width / 2 - 190, (int)(height * 0.6D), 85, 20, "I");
/*  62 */     this.two = new GuiButton(0, width / 2 - 95, (int)(height * 0.6D), 85, 20, "II");
/*  63 */     this.three = new GuiButton(0, width / 2 + 10, (int)(height * 0.6D), 85, 20, "III");
/*  64 */     this.four = new GuiButton(0, width / 2 + 115, (int)(height * 0.6D), 85, 20, "IV");
/*     */     
/*  66 */     this.field_146292_n.add(this.off);
/*  67 */     this.field_146292_n.add(this.zombie);
/*  68 */     this.field_146292_n.add(this.spider);
/*  69 */     this.field_146292_n.add(this.wolf);
/*  70 */     this.field_146292_n.add(this.one);
/*  71 */     this.field_146292_n.add(this.two);
/*  72 */     this.field_146292_n.add(this.three);
/*  73 */     this.field_146292_n.add(this.four);
/*  74 */     this.field_146292_n.add(this.goBack);
/*     */   }
/*     */   
/*     */   public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
/*     */     String displayText;
/*  79 */     func_146276_q_();
/*  80 */     Minecraft mc = Minecraft.func_71410_x();
/*     */ 
/*     */     
/*  83 */     if (BlockSlayerCommand.onlySlayerName.equals("")) {
/*  84 */       displayText = "Only Allow Slayer: Off";
/*     */     } else {
/*  86 */       displayText = "Only Allow Slayer: " + BlockSlayerCommand.onlySlayerName + " " + BlockSlayerCommand.onlySlayerNumber;
/*     */     } 
/*  88 */     int displayWidth = mc.field_71466_p.func_78256_a(displayText);
/*  89 */     new TextRenderer(mc, displayText, this.field_146294_l / 2 - displayWidth / 2, 10, 1.0D);
/*     */     
/*  91 */     super.func_73863_a(mouseX, mouseY, partialTicks);
/*     */   }
/*     */   
/*     */   public void func_146284_a(GuiButton button) {
/*     */     String onlyNumber;
/*  96 */     if (button == this.goBack)
/*  97 */     { DankersSkyblockMod.guiToOpen = "dankergui1"; }
/*  98 */     else { if (button == this.off) {
/*  99 */         BlockSlayerCommand.onlySlayerName = "";
/* 100 */         BlockSlayerCommand.onlySlayerNumber = "";
/* 101 */         ConfigHandler.writeStringConfig("toggles", "BlockSlayer", ""); return;
/*     */       } 
/* 103 */       if (button == this.zombie) {
/* 104 */         this.onlyName = "Revenant Horror";
/* 105 */       } else if (button == this.spider) {
/* 106 */         this.onlyName = "Tarantula Broodfather";
/* 107 */       } else if (button == this.wolf) {
/* 108 */         this.onlyName = "Sven Packmaster";
/* 109 */       } else if (button == this.one) {
/* 110 */         this.onlyNumberInt = 1;
/* 111 */       } else if (button == this.two) {
/* 112 */         this.onlyNumberInt = 2;
/* 113 */       } else if (button == this.three) {
/* 114 */         this.onlyNumberInt = 3;
/* 115 */       } else if (button == this.four) {
/* 116 */         this.onlyNumberInt = 4;
/*     */       }  }
/*     */ 
/*     */     
/* 120 */     switch (this.onlyNumberInt) {
/*     */       case 1:
/* 122 */         onlyNumber = "I";
/*     */         break;
/*     */       case 2:
/* 125 */         onlyNumber = "II";
/*     */         break;
/*     */       case 3:
/* 128 */         onlyNumber = "III";
/*     */         break;
/*     */       
/*     */       default:
/* 132 */         onlyNumber = "IV";
/*     */         break;
/*     */     } 
/* 135 */     BlockSlayerCommand.onlySlayerName = this.onlyName;
/* 136 */     BlockSlayerCommand.onlySlayerNumber = onlyNumber;
/* 137 */     ConfigHandler.writeStringConfig("toggles", "BlockSlayer", BlockSlayerCommand.onlySlayerName + " " + BlockSlayerCommand.onlySlayerNumber);
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\me\Danker\gui\OnlySlayerGui.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */