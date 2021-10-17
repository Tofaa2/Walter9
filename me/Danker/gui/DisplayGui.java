/*     */ package me.Danker.gui;
/*     */ 
/*     */ import me.Danker.DankersSkyblockMod;
/*     */ import me.Danker.commands.DisplayCommand;
/*     */ import me.Danker.handlers.ConfigHandler;
/*     */ import me.Danker.handlers.TextRenderer;
/*     */ import me.Danker.utils.Utils;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ 
/*     */ 
/*     */ public class DisplayGui
/*     */   extends GuiScreen
/*     */ {
/*     */   private boolean addSession = false;
/*     */   private GuiButton goBack;
/*     */   private GuiButton off;
/*     */   private GuiButton showSession;
/*     */   private GuiButton zombie;
/*     */   private GuiButton spider;
/*     */   private GuiButton wolf;
/*     */   private GuiButton auto;
/*     */   private GuiButton fishing;
/*     */   private GuiButton fishingWinter;
/*     */   private GuiButton fishingFestival;
/*     */   private GuiButton fishingSpooky;
/*     */   private GuiButton mythological;
/*     */   private GuiButton catacombsF1;
/*     */   private GuiButton catacombsF2;
/*     */   private GuiButton catacombsF3;
/*     */   private GuiButton catacombsF4;
/*     */   private GuiButton catacombsF5;
/*     */   private GuiButton catacombsF6;
/*     */   private GuiButton catacombsF7;
/*     */   
/*     */   public boolean func_73868_f() {
/*  39 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_73866_w_() {
/*  44 */     super.func_73866_w_();
/*     */     
/*  46 */     ScaledResolution sr = new ScaledResolution(Minecraft.func_71410_x());
/*  47 */     int height = sr.func_78328_b();
/*  48 */     int width = sr.func_78326_a();
/*     */     
/*  50 */     this.goBack = new GuiButton(0, 2, height - 30, 100, 20, "Go Back");
/*  51 */     this.showSession = new GuiButton(0, width / 2 - 100, (int)(height * 0.1D), "Current Session Only: " + Utils.getColouredBoolean(this.addSession));
/*  52 */     this.off = new GuiButton(0, width / 2 - 210, (int)(height * 0.2D), "Off");
/*  53 */     this.auto = new GuiButton(0, width / 2 + 10, (int)(height * 0.2D), "Auto");
/*  54 */     this.zombie = new GuiButton(0, width / 2 - 190, (int)(height * 0.3D), 110, 20, "Zombie");
/*  55 */     this.spider = new GuiButton(0, width / 2 - 55, (int)(height * 0.3D), 110, 20, "Spider");
/*  56 */     this.wolf = new GuiButton(0, width / 2 + 75, (int)(height * 0.3D), 110, 20, "Wolf");
/*  57 */     this.fishing = new GuiButton(0, width / 2 - 230, (int)(height * 0.4D), 100, 20, "Fishing");
/*  58 */     this.fishingWinter = new GuiButton(0, width / 2 - 110, (int)(height * 0.4D), 100, 20, "Fishing Winter");
/*  59 */     this.fishingFestival = new GuiButton(0, width / 2 + 10, (int)(height * 0.4D), 100, 20, "Fishing Festival");
/*  60 */     this.fishingSpooky = new GuiButton(0, width / 2 + 130, (int)(height * 0.4D), 100, 20, "Fishing Spooky");
/*  61 */     this.mythological = new GuiButton(0, width / 2 - 100, (int)(height * 0.5D), 200, 20, "Mythological");
/*  62 */     this.catacombsF1 = new GuiButton(0, width / 2 - 205, (int)(height * 0.65D), 50, 20, "F1");
/*  63 */     this.catacombsF2 = new GuiButton(0, width / 2 - 145, (int)(height * 0.65D), 50, 20, "F2");
/*  64 */     this.catacombsF3 = new GuiButton(0, width / 2 - 85, (int)(height * 0.65D), 50, 20, "F3");
/*  65 */     this.catacombsF4 = new GuiButton(0, width / 2 - 25, (int)(height * 0.65D), 50, 20, "F4");
/*  66 */     this.catacombsF5 = new GuiButton(0, width / 2 + 35, (int)(height * 0.65D), 50, 20, "F5");
/*  67 */     this.catacombsF6 = new GuiButton(0, width / 2 + 95, (int)(height * 0.65D), 50, 20, "F6");
/*  68 */     this.catacombsF7 = new GuiButton(0, width / 2 + 155, (int)(height * 0.65D), 50, 20, "F7");
/*     */     
/*  70 */     this.field_146292_n.add(this.showSession);
/*  71 */     this.field_146292_n.add(this.off);
/*  72 */     this.field_146292_n.add(this.auto);
/*  73 */     this.field_146292_n.add(this.zombie);
/*  74 */     this.field_146292_n.add(this.spider);
/*  75 */     this.field_146292_n.add(this.wolf);
/*  76 */     this.field_146292_n.add(this.fishing);
/*  77 */     this.field_146292_n.add(this.fishingWinter);
/*  78 */     this.field_146292_n.add(this.fishingFestival);
/*  79 */     this.field_146292_n.add(this.fishingSpooky);
/*  80 */     this.field_146292_n.add(this.mythological);
/*  81 */     this.field_146292_n.add(this.catacombsF1);
/*  82 */     this.field_146292_n.add(this.catacombsF2);
/*  83 */     this.field_146292_n.add(this.catacombsF3);
/*  84 */     this.field_146292_n.add(this.catacombsF4);
/*  85 */     this.field_146292_n.add(this.catacombsF5);
/*  86 */     this.field_146292_n.add(this.catacombsF6);
/*  87 */     this.field_146292_n.add(this.catacombsF7);
/*  88 */     this.field_146292_n.add(this.goBack);
/*     */   }
/*     */   
/*     */   public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
/*     */     String displayText;
/*  93 */     func_146276_q_();
/*  94 */     Minecraft mc = Minecraft.func_71410_x();
/*     */ 
/*     */     
/*  97 */     if (DisplayCommand.auto) {
/*  98 */       displayText = "Current Display: auto";
/*     */     } else {
/* 100 */       displayText = "Current Display: " + DisplayCommand.display;
/*     */     } 
/* 102 */     int displayWidth = mc.field_71466_p.func_78256_a(displayText);
/* 103 */     new TextRenderer(mc, displayText, this.field_146294_l / 2 - displayWidth / 2, 10, 1.0D);
/*     */     
/* 105 */     String catacombsTitleText = "Catacombs Dungeon";
/* 106 */     int catacombsTitleWidth = mc.field_71466_p.func_78256_a(catacombsTitleText);
/* 107 */     new TextRenderer(mc, catacombsTitleText, this.field_146294_l / 2 - catacombsTitleWidth / 2, (int)(this.field_146295_m * 0.6D), 1.0D);
/*     */     
/* 109 */     super.func_73863_a(mouseX, mouseY, partialTicks);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_146284_a(GuiButton button) {
/* 114 */     if (button == this.goBack) {
/* 115 */       DankersSkyblockMod.guiToOpen = "dankergui1";
/* 116 */     } else if (button == this.showSession) {
/* 117 */       this.addSession = !this.addSession;
/* 118 */       this.showSession.field_146126_j = "Current Session Only: " + Utils.getColouredBoolean(this.addSession);
/* 119 */     } else if (button == this.off) {
/* 120 */       setDisplay("off", true);
/* 121 */     } else if (button == this.zombie) {
/* 122 */       setDisplay("zombie", false);
/* 123 */     } else if (button == this.spider) {
/* 124 */       setDisplay("spider", false);
/* 125 */     } else if (button == this.wolf) {
/* 126 */       setDisplay("wolf", false);
/* 127 */     } else if (button == this.auto) {
/* 128 */       DisplayCommand.auto = true;
/* 129 */       ConfigHandler.writeBooleanConfig("misc", "autoDisplay", true);
/* 130 */     } else if (button == this.fishing) {
/* 131 */       setDisplay("fishing", false);
/* 132 */     } else if (button == this.fishingWinter) {
/* 133 */       setDisplay("fishing_winter", false);
/* 134 */     } else if (button == this.fishingFestival) {
/* 135 */       setDisplay("fishing_festival", false);
/* 136 */     } else if (button == this.fishingSpooky) {
/* 137 */       setDisplay("fishing_spooky", false);
/* 138 */     } else if (button == this.mythological) {
/* 139 */       setDisplay("mythological", false);
/* 140 */     } else if (button == this.catacombsF1) {
/* 141 */       setDisplay("catacombs_floor_one", false);
/* 142 */     } else if (button == this.catacombsF2) {
/* 143 */       setDisplay("catacombs_floor_two", false);
/* 144 */     } else if (button == this.catacombsF3) {
/* 145 */       setDisplay("catacombs_floor_three", false);
/* 146 */     } else if (button == this.catacombsF4) {
/* 147 */       setDisplay("catacombs_floor_four", false);
/* 148 */     } else if (button == this.catacombsF5) {
/* 149 */       setDisplay("catacombs_floor_five", false);
/* 150 */     } else if (button == this.catacombsF6) {
/* 151 */       setDisplay("catacombs_floor_six", false);
/* 152 */     } else if (button == this.catacombsF7) {
/* 153 */       setDisplay("catacombs_floor_seven", false);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setDisplay(String display, boolean forceNoSession) {
/* 158 */     if (!forceNoSession && this.addSession) display = display + "_session"; 
/* 159 */     DisplayCommand.auto = false;
/* 160 */     DisplayCommand.display = display;
/* 161 */     ConfigHandler.writeBooleanConfig("misc", "autoDisplay", false);
/* 162 */     ConfigHandler.writeStringConfig("misc", "display", display);
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\me\Danker\gui\DisplayGui.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */