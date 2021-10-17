/*     */ package me.Danker.gui;
/*     */ 
/*     */ import me.Danker.DankersSkyblockMod;
/*     */ import me.Danker.commands.ToggleCommand;
/*     */ import me.Danker.handlers.ConfigHandler;
/*     */ import me.Danker.utils.Utils;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ 
/*     */ public class PuzzleSolversGui
/*     */   extends GuiScreen
/*     */ {
/*     */   private int page;
/*     */   private GuiButton goBack;
/*     */   private GuiButton backPage;
/*     */   private GuiButton nextPage;
/*     */   private GuiButton riddle;
/*     */   private GuiButton trivia;
/*     */   private GuiButton blaze;
/*     */   private GuiButton creeper;
/*     */   private GuiButton water;
/*     */   private GuiButton ticTacToe;
/*     */   private GuiButton startsWith;
/*     */   private GuiButton selectAll;
/*     */   private GuiButton clickOrder;
/*     */   private GuiButton blockClicks;
/*     */   private GuiButton itemFrameOnSeaLanterns;
/*     */   
/*     */   public PuzzleSolversGui(int page) {
/*  32 */     this.page = page;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_73868_f() {
/*  37 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_73866_w_() {
/*  42 */     super.func_73866_w_();
/*     */     
/*  44 */     ScaledResolution sr = new ScaledResolution(Minecraft.func_71410_x());
/*  45 */     int height = sr.func_78328_b();
/*  46 */     int width = sr.func_78326_a();
/*     */     
/*  48 */     this.goBack = new GuiButton(0, 2, height - 30, 100, 20, "Go Back");
/*  49 */     this.backPage = new GuiButton(0, width / 2 - 100, (int)(height * 0.8D), 80, 20, "< Back");
/*  50 */     this.nextPage = new GuiButton(0, width / 2 + 20, (int)(height * 0.8D), 80, 20, "Next >");
/*     */ 
/*     */     
/*  53 */     this.riddle = new GuiButton(0, width / 2 - 100, (int)(height * 0.1D), "Riddle Solver: " + Utils.getColouredBoolean(ToggleCommand.threeManToggled));
/*  54 */     this.trivia = new GuiButton(0, width / 2 - 100, (int)(height * 0.2D), "Trivia Solver: " + Utils.getColouredBoolean(ToggleCommand.oruoToggled));
/*  55 */     this.blaze = new GuiButton(0, width / 2 - 100, (int)(height * 0.3D), "Blaze Solver: " + Utils.getColouredBoolean(ToggleCommand.blazeToggled));
/*  56 */     this.creeper = new GuiButton(0, width / 2 - 100, (int)(height * 0.4D), "Creeper Solver: " + Utils.getColouredBoolean(ToggleCommand.creeperToggled));
/*  57 */     this.water = new GuiButton(0, width / 2 - 100, (int)(height * 0.5D), "Water Solver: " + Utils.getColouredBoolean(ToggleCommand.waterToggled));
/*  58 */     this.ticTacToe = new GuiButton(0, width / 2 - 100, (int)(height * 0.6D), "Tic Tac Toe Solver: " + Utils.getColouredBoolean(ToggleCommand.ticTacToeToggled));
/*  59 */     this.startsWith = new GuiButton(0, width / 2 - 100, (int)(height * 0.7D), "Starts With Letter Terminal Solver: " + Utils.getColouredBoolean(ToggleCommand.startsWithToggled));
/*     */     
/*  61 */     this.selectAll = new GuiButton(0, width / 2 - 100, (int)(height * 0.1D), "Select All Color Terminal Solver: " + Utils.getColouredBoolean(ToggleCommand.selectAllToggled));
/*  62 */     this.clickOrder = new GuiButton(0, width / 2 - 100, (int)(height * 0.2D), "Click in Order Terminal Helper: " + Utils.getColouredBoolean(ToggleCommand.clickInOrderToggled));
/*  63 */     this.blockClicks = new GuiButton(0, width / 2 - 100, (int)(height * 0.3D), "Block Wrong Clicks on Terminals: " + Utils.getColouredBoolean(ToggleCommand.blockWrongTerminalClicksToggled));
/*  64 */     this.itemFrameOnSeaLanterns = new GuiButton(0, width / 2 - 100, (int)(height * 0.4D), "Ignore Arrows On Sea Lanterns: " + Utils.getColouredBoolean(ToggleCommand.itemFrameOnSeaLanternsToggled));
/*     */     
/*  66 */     switch (this.page) {
/*     */       case 1:
/*  68 */         this.field_146292_n.add(this.riddle);
/*  69 */         this.field_146292_n.add(this.trivia);
/*  70 */         this.field_146292_n.add(this.blaze);
/*  71 */         this.field_146292_n.add(this.creeper);
/*  72 */         this.field_146292_n.add(this.water);
/*  73 */         this.field_146292_n.add(this.ticTacToe);
/*  74 */         this.field_146292_n.add(this.startsWith);
/*  75 */         this.field_146292_n.add(this.nextPage);
/*     */         break;
/*     */       case 2:
/*  78 */         this.field_146292_n.add(this.selectAll);
/*  79 */         this.field_146292_n.add(this.clickOrder);
/*  80 */         this.field_146292_n.add(this.blockClicks);
/*  81 */         this.field_146292_n.add(this.itemFrameOnSeaLanterns);
/*  82 */         this.field_146292_n.add(this.backPage);
/*     */         break;
/*     */     } 
/*  85 */     this.field_146292_n.add(this.goBack);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
/*  90 */     func_146276_q_();
/*  91 */     super.func_73863_a(mouseX, mouseY, partialTicks);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_146284_a(GuiButton button) {
/*  96 */     if (button == this.goBack) {
/*  97 */       DankersSkyblockMod.guiToOpen = "dankergui1";
/*  98 */     } else if (button == this.backPage) {
/*  99 */       Minecraft.func_71410_x().func_147108_a(new PuzzleSolversGui(this.page - 1));
/* 100 */     } else if (button == this.nextPage) {
/* 101 */       Minecraft.func_71410_x().func_147108_a(new PuzzleSolversGui(this.page + 1));
/* 102 */     } else if (button == this.riddle) {
/* 103 */       ToggleCommand.threeManToggled = !ToggleCommand.threeManToggled;
/* 104 */       ConfigHandler.writeBooleanConfig("toggles", "ThreeManPuzzle", ToggleCommand.threeManToggled);
/* 105 */       this.riddle.field_146126_j = "Riddle Solver: " + Utils.getColouredBoolean(ToggleCommand.threeManToggled);
/* 106 */     } else if (button == this.trivia) {
/* 107 */       ToggleCommand.oruoToggled = !ToggleCommand.oruoToggled;
/* 108 */       ConfigHandler.writeBooleanConfig("toggles", "OruoPuzzle", ToggleCommand.oruoToggled);
/* 109 */       this.trivia.field_146126_j = "Trivia Solver: " + Utils.getColouredBoolean(ToggleCommand.oruoToggled);
/* 110 */     } else if (button == this.blaze) {
/* 111 */       ToggleCommand.blazeToggled = !ToggleCommand.blazeToggled;
/* 112 */       ConfigHandler.writeBooleanConfig("toggles", "BlazePuzzle", ToggleCommand.blazeToggled);
/* 113 */       this.blaze.field_146126_j = "Blaze Solver: " + Utils.getColouredBoolean(ToggleCommand.blazeToggled);
/* 114 */     } else if (button == this.creeper) {
/* 115 */       ToggleCommand.creeperToggled = !ToggleCommand.creeperToggled;
/* 116 */       ConfigHandler.writeBooleanConfig("toggles", "CreeperPuzzle", ToggleCommand.creeperToggled);
/* 117 */       this.creeper.field_146126_j = "Creeper Solver: " + Utils.getColouredBoolean(ToggleCommand.creeperToggled);
/* 118 */     } else if (button == this.water) {
/* 119 */       ToggleCommand.waterToggled = !ToggleCommand.waterToggled;
/* 120 */       ConfigHandler.writeBooleanConfig("toggles", "WaterPuzzle", ToggleCommand.waterToggled);
/* 121 */       this.water.field_146126_j = "Water Solver: " + Utils.getColouredBoolean(ToggleCommand.waterToggled);
/* 122 */     } else if (button == this.ticTacToe) {
/* 123 */       ToggleCommand.ticTacToeToggled = !ToggleCommand.ticTacToeToggled;
/* 124 */       ConfigHandler.writeBooleanConfig("toggles", "TicTacToePuzzle", ToggleCommand.ticTacToeToggled);
/* 125 */       this.ticTacToe.field_146126_j = "Tic Tac Toe Solver: " + Utils.getColouredBoolean(ToggleCommand.ticTacToeToggled);
/* 126 */     } else if (button == this.startsWith) {
/* 127 */       ToggleCommand.startsWithToggled = !ToggleCommand.startsWithToggled;
/* 128 */       ConfigHandler.writeBooleanConfig("toggles", "StartsWithTerminal", ToggleCommand.startsWithToggled);
/* 129 */       this.startsWith.field_146126_j = "Starts With Letter Terminal Solver: " + Utils.getColouredBoolean(ToggleCommand.startsWithToggled);
/* 130 */     } else if (button == this.selectAll) {
/* 131 */       ToggleCommand.selectAllToggled = !ToggleCommand.selectAllToggled;
/* 132 */       ConfigHandler.writeBooleanConfig("toggles", "SelectAllTerminal", ToggleCommand.selectAllToggled);
/* 133 */       this.selectAll.field_146126_j = "Select All Color Terminal Solver: " + Utils.getColouredBoolean(ToggleCommand.selectAllToggled);
/* 134 */     } else if (button == this.clickOrder) {
/* 135 */       ToggleCommand.clickInOrderToggled = !ToggleCommand.clickInOrderToggled;
/* 136 */       ConfigHandler.writeBooleanConfig("toggles", "ClickInOrderTerminal", ToggleCommand.clickInOrderToggled);
/* 137 */       this.clickOrder.field_146126_j = "Click in Order Terminal Helper: " + Utils.getColouredBoolean(ToggleCommand.clickInOrderToggled);
/* 138 */     } else if (button == this.blockClicks) {
/* 139 */       ToggleCommand.blockWrongTerminalClicksToggled = !ToggleCommand.blockWrongTerminalClicksToggled;
/* 140 */       ConfigHandler.writeBooleanConfig("toggles", "BlockWrongTerminalClicks", ToggleCommand.blockWrongTerminalClicksToggled);
/* 141 */       this.blockClicks.field_146126_j = "Block Wrong Clicks on Terminals: " + Utils.getColouredBoolean(ToggleCommand.blockWrongTerminalClicksToggled);
/* 142 */     } else if (button == this.itemFrameOnSeaLanterns) {
/* 143 */       ToggleCommand.itemFrameOnSeaLanternsToggled = !ToggleCommand.itemFrameOnSeaLanternsToggled;
/* 144 */       ConfigHandler.writeBooleanConfig("toggles", "IgnoreItemFrameOnSeaLanterns", ToggleCommand.itemFrameOnSeaLanternsToggled);
/* 145 */       this.itemFrameOnSeaLanterns.field_146126_j = "Ignore Arrows On Sea Lanterns: " + Utils.getColouredBoolean(ToggleCommand.itemFrameOnSeaLanternsToggled);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\me\Danker\gui\PuzzleSolversGui.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */