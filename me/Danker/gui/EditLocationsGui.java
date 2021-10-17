/*     */ package me.Danker.gui;
/*     */ 
/*     */ import me.Danker.DankersSkyblockMod;
/*     */ import me.Danker.commands.MoveCommand;
/*     */ import me.Danker.commands.ScaleCommand;
/*     */ import me.Danker.gui.buttons.LocationButton;
/*     */ import me.Danker.handlers.ConfigHandler;
/*     */ import me.Danker.utils.Utils;
/*     */ import net.minecraft.client.gui.Gui;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class EditLocationsGui
/*     */   extends GuiScreen {
/*  17 */   private String moving = null;
/*  18 */   private int lastMouseX = -1;
/*  19 */   private int lastMouseY = -1;
/*     */   
/*     */   private LocationButton display;
/*     */   
/*     */   private LocationButton dungeonTimer;
/*     */   private LocationButton coords;
/*     */   private LocationButton skill50;
/*     */   private LocationButton lividHP;
/*     */   private LocationButton cakeTimer;
/*     */   private LocationButton skillTracker;
/*     */   private LocationButton waterAnswer;
/*     */   private LocationButton bonzoTimer;
/*     */   
/*     */   public boolean func_73868_f() {
/*  33 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_73866_w_() {
/*  38 */     super.func_73866_w_();
/*     */     
/*  40 */     String displayText = EnumChatFormatting.GOLD + "Svens Killed:\n" + EnumChatFormatting.GREEN + "Wolf Teeth:\n" + EnumChatFormatting.BLUE + "Hamster Wheels:\n" + EnumChatFormatting.AQUA + "Spirit Runes:\n" + EnumChatFormatting.WHITE + "Critical VI Books:\n" + EnumChatFormatting.DARK_RED + "Red Claw Eggs:\n" + EnumChatFormatting.GOLD + "Couture Runes:\n" + EnumChatFormatting.AQUA + "Grizzly Baits:\n" + EnumChatFormatting.DARK_PURPLE + "Overfluxes:\n" + EnumChatFormatting.AQUA + "Time Since RNG:\n" + EnumChatFormatting.AQUA + "Bosses Since RNG:";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  60 */     String displayNums = EnumChatFormatting.GOLD + "1,024\n" + EnumChatFormatting.GREEN + "59,719\n" + EnumChatFormatting.BLUE + "36\n" + EnumChatFormatting.AQUA + "64\n" + EnumChatFormatting.WHITE + "17\n" + EnumChatFormatting.DARK_RED + "3\n" + EnumChatFormatting.GOLD + "4\n" + EnumChatFormatting.AQUA + "0\n" + EnumChatFormatting.DARK_PURPLE + "5\n" + EnumChatFormatting.AQUA + Utils.getTimeBetween(0.0D, 2678400.0D) + "\n" + EnumChatFormatting.AQUA + "5,000";
/*     */ 
/*     */     
/*  63 */     String dungeonTimerText = EnumChatFormatting.GRAY + "Wither Doors:\n" + EnumChatFormatting.DARK_RED + "Blood Open:\n" + EnumChatFormatting.RED + "Watcher Clear:\n" + EnumChatFormatting.BLUE + "Boss Clear:\n" + EnumChatFormatting.YELLOW + "Deaths:\n" + EnumChatFormatting.YELLOW + "Puzzle Fails:";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  72 */     String dungeonTimerNums = EnumChatFormatting.GRAY + "" + '\005' + "\n" + EnumChatFormatting.DARK_RED + Utils.getTimeBetween(0.0D, 33.0D) + "\n" + EnumChatFormatting.RED + Utils.getTimeBetween(0.0D, 129.0D) + "\n" + EnumChatFormatting.BLUE + Utils.getTimeBetween(0.0D, 169.0D) + "\n" + EnumChatFormatting.YELLOW + '\002' + "\n" + EnumChatFormatting.YELLOW + '\001';
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  77 */     String skillTrackerText = DankersSkyblockMod.SKILL_TRACKER_COLOUR + "Farming XP Earned: 462,425.3\n" + DankersSkyblockMod.SKILL_TRACKER_COLOUR + "Time Elapsed: " + Utils.getTimeBetween(0.0D, 3602.0D) + "\n" + DankersSkyblockMod.SKILL_TRACKER_COLOUR + "XP Per Hour: 462,168";
/*     */ 
/*     */     
/*  80 */     String waterAnswerText = DankersSkyblockMod.MAIN_COLOUR + "The following levers must be down:\n" + EnumChatFormatting.DARK_PURPLE + "Purple: " + EnumChatFormatting.WHITE + "Quartz, " + EnumChatFormatting.YELLOW + "Gold, " + EnumChatFormatting.GREEN + "Emerald, " + EnumChatFormatting.RED + "Clay\n" + EnumChatFormatting.GOLD + "Orange: " + EnumChatFormatting.YELLOW + "Gold, " + EnumChatFormatting.DARK_GRAY + "Coal\n" + EnumChatFormatting.BLUE + "Blue: " + EnumChatFormatting.WHITE + "Quartz, " + EnumChatFormatting.YELLOW + "Gold, " + EnumChatFormatting.DARK_GRAY + "Coal, " + EnumChatFormatting.GREEN + "Emerald, " + EnumChatFormatting.RED + "Clay\n" + EnumChatFormatting.GREEN + "Green: " + EnumChatFormatting.YELLOW + "Gold, " + EnumChatFormatting.GREEN + "Emerald\n" + EnumChatFormatting.RED + "Red: " + EnumChatFormatting.YELLOW + "Gold, " + EnumChatFormatting.AQUA + "Diamond, " + EnumChatFormatting.GREEN + "Emerald, " + EnumChatFormatting.RED + "Clay";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  87 */     this.display = new LocationButton(0, MoveCommand.displayXY[0], MoveCommand.displayXY[1], 145.0D * ScaleCommand.displayScale, 102.0D * ScaleCommand.displayScale, ScaleCommand.displayScale, displayText, displayNums, Integer.valueOf(110));
/*  88 */     this.dungeonTimer = new LocationButton(0, MoveCommand.dungeonTimerXY[0], MoveCommand.dungeonTimerXY[1], 113.0D * ScaleCommand.dungeonTimerScale, 57.0D * ScaleCommand.dungeonTimerScale, ScaleCommand.dungeonTimerScale, dungeonTimerText, dungeonTimerNums, Integer.valueOf(80));
/*  89 */     this.coords = new LocationButton(0, MoveCommand.coordsXY[0], MoveCommand.coordsXY[1], 141.0D * ScaleCommand.coordsScale, 12.0D * ScaleCommand.coordsScale, ScaleCommand.coordsScale, DankersSkyblockMod.COORDS_COLOUR + "74 / 14 / -26 (141.1 / 6.7)", null, null);
/*  90 */     this.skill50 = new LocationButton(0, MoveCommand.skill50XY[0], MoveCommand.skill50XY[1], 233.0D * ScaleCommand.skill50Scale, 12.0D * ScaleCommand.skill50Scale, ScaleCommand.skill50Scale, DankersSkyblockMod.SKILL_50_COLOUR + "+3.5 Farming (28,882,117.7/55,172,425) 52.34%", null, null);
/*  91 */     this.lividHP = new LocationButton(0, MoveCommand.lividHpXY[0], MoveCommand.lividHpXY[1], 85.0D * ScaleCommand.lividHpScale, 12.0D * ScaleCommand.lividHpScale, ScaleCommand.lividHpScale, EnumChatFormatting.WHITE + "﴾ Livid " + EnumChatFormatting.YELLOW + "6.9M" + EnumChatFormatting.RED + "❤ " + EnumChatFormatting.WHITE + "﴿", null, null);
/*  92 */     this.cakeTimer = new LocationButton(0, MoveCommand.cakeTimerXY[0], MoveCommand.cakeTimerXY[1] + 5, 85.0D * ScaleCommand.cakeTimerScale, 18.0D * ScaleCommand.cakeTimerScale, ScaleCommand.cakeTimerScale, DankersSkyblockMod.CAKE_COLOUR + "     11h16m", null, null);
/*  93 */     this.skillTracker = new LocationButton(0, MoveCommand.skillTrackerXY[0], MoveCommand.skillTrackerXY[1], 150.0D * ScaleCommand.skillTrackerScale, 28.0D * ScaleCommand.skillTrackerScale, ScaleCommand.skillTrackerScale, skillTrackerText, null, null);
/*  94 */     this.waterAnswer = new LocationButton(0, MoveCommand.waterAnswerXY[0], MoveCommand.waterAnswerXY[1], 190.0D * ScaleCommand.waterAnswerScale, 54.0D * ScaleCommand.waterAnswerScale, ScaleCommand.waterAnswerScale, waterAnswerText, null, null);
/*  95 */     this.bonzoTimer = new LocationButton(0, MoveCommand.bonzoTimerXY[0], MoveCommand.bonzoTimerXY[1] + 5, 85.0D * ScaleCommand.bonzoTimerScale, 18.0D * ScaleCommand.bonzoTimerScale, ScaleCommand.bonzoTimerScale, DankersSkyblockMod.BONZO_COLOR + "     3m30s", null, null);
/*     */ 
/*     */     
/*  98 */     this.field_146292_n.add(this.coords);
/*  99 */     this.field_146292_n.add(this.dungeonTimer);
/* 100 */     this.field_146292_n.add(this.lividHP);
/* 101 */     this.field_146292_n.add(this.cakeTimer);
/* 102 */     this.field_146292_n.add(this.skillTracker);
/* 103 */     this.field_146292_n.add(this.waterAnswer);
/* 104 */     this.field_146292_n.add(this.bonzoTimer);
/* 105 */     this.field_146292_n.add(this.display);
/* 106 */     this.field_146292_n.add(this.skill50);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
/* 111 */     func_146276_q_();
/* 112 */     mouseMoved(mouseX, mouseY);
/*     */     
/* 114 */     double cakeTimerScale = ScaleCommand.cakeTimerScale;
/* 115 */     double cakeTimerScaleReset = Math.pow(cakeTimerScale, -1.0D);
/* 116 */     GL11.glScaled(cakeTimerScale, cakeTimerScale, cakeTimerScale);
/* 117 */     this.field_146297_k.func_110434_K().func_110577_a(DankersSkyblockMod.CAKE_ICON);
/* 118 */     Gui.func_146110_a(MoveCommand.cakeTimerXY[0], MoveCommand.cakeTimerXY[1], 0.0F, 0.0F, 16, 16, 16.0F, 16.0F);
/* 119 */     GL11.glScaled(cakeTimerScaleReset, cakeTimerScaleReset, cakeTimerScaleReset);
/*     */     
/* 121 */     double bonzoTimerScale = ScaleCommand.bonzoTimerScale;
/* 122 */     double bonzoTimerScaleReset = Math.pow(bonzoTimerScale, -1.0D);
/* 123 */     GL11.glScaled(bonzoTimerScale, bonzoTimerScale, bonzoTimerScale);
/* 124 */     this.field_146297_k.func_110434_K().func_110577_a(DankersSkyblockMod.BONZO_ICON);
/* 125 */     Gui.func_146110_a(MoveCommand.bonzoTimerXY[0], MoveCommand.bonzoTimerXY[1], 0.0F, 0.0F, 16, 16, 16.0F, 16.0F);
/* 126 */     GL11.glScaled(bonzoTimerScaleReset, bonzoTimerScaleReset, bonzoTimerScaleReset);
/*     */ 
/*     */     
/* 129 */     super.func_73863_a(mouseX, mouseY, partialTicks);
/*     */   }
/*     */   
/*     */   private void mouseMoved(int mouseX, int mouseY) {
/* 133 */     int xMoved = mouseX - this.lastMouseX;
/* 134 */     int yMoved = mouseY - this.lastMouseY;
/*     */     
/* 136 */     if (this.moving != null) {
/* 137 */       switch (this.moving) {
/*     */         case "display":
/* 139 */           MoveCommand.displayXY[0] = MoveCommand.displayXY[0] + xMoved;
/* 140 */           MoveCommand.displayXY[1] = MoveCommand.displayXY[1] + yMoved;
/* 141 */           this.display.field_146128_h = MoveCommand.displayXY[0];
/* 142 */           this.display.field_146129_i = MoveCommand.displayXY[1];
/*     */           break;
/*     */         case "dungeonTimer":
/* 145 */           MoveCommand.dungeonTimerXY[0] = MoveCommand.dungeonTimerXY[0] + xMoved;
/* 146 */           MoveCommand.dungeonTimerXY[1] = MoveCommand.dungeonTimerXY[1] + yMoved;
/* 147 */           this.dungeonTimer.field_146128_h = MoveCommand.dungeonTimerXY[0];
/* 148 */           this.dungeonTimer.field_146129_i = MoveCommand.dungeonTimerXY[1];
/*     */           break;
/*     */         case "coords":
/* 151 */           MoveCommand.coordsXY[0] = MoveCommand.coordsXY[0] + xMoved;
/* 152 */           MoveCommand.coordsXY[1] = MoveCommand.coordsXY[1] + yMoved;
/* 153 */           this.coords.field_146128_h = MoveCommand.coordsXY[0];
/* 154 */           this.coords.field_146129_i = MoveCommand.coordsXY[1];
/*     */           break;
/*     */         case "skill50":
/* 157 */           MoveCommand.skill50XY[0] = MoveCommand.skill50XY[0] + xMoved;
/* 158 */           MoveCommand.skill50XY[1] = MoveCommand.skill50XY[1] + yMoved;
/* 159 */           this.skill50.field_146128_h = MoveCommand.skill50XY[0];
/* 160 */           this.skill50.field_146129_i = MoveCommand.skill50XY[1];
/*     */           break;
/*     */         case "lividHP":
/* 163 */           MoveCommand.lividHpXY[0] = MoveCommand.lividHpXY[0] + xMoved;
/* 164 */           MoveCommand.lividHpXY[1] = MoveCommand.lividHpXY[1] + yMoved;
/* 165 */           this.lividHP.field_146128_h = MoveCommand.lividHpXY[0];
/* 166 */           this.lividHP.field_146129_i = MoveCommand.lividHpXY[1];
/*     */           break;
/*     */         case "cakeTimer":
/* 169 */           MoveCommand.cakeTimerXY[0] = MoveCommand.cakeTimerXY[0] + xMoved;
/* 170 */           MoveCommand.cakeTimerXY[1] = MoveCommand.cakeTimerXY[1] + yMoved;
/* 171 */           this.cakeTimer.field_146128_h = MoveCommand.cakeTimerXY[0];
/* 172 */           this.cakeTimer.field_146129_i = MoveCommand.cakeTimerXY[1];
/*     */           break;
/*     */         case "skillTracker":
/* 175 */           MoveCommand.skillTrackerXY[0] = MoveCommand.skillTrackerXY[0] + xMoved;
/* 176 */           MoveCommand.skillTrackerXY[1] = MoveCommand.skillTrackerXY[1] + yMoved;
/* 177 */           this.skillTracker.field_146128_h = MoveCommand.skillTrackerXY[0];
/* 178 */           this.skillTracker.field_146129_i = MoveCommand.skillTrackerXY[1];
/*     */           break;
/*     */         case "waterAnswer":
/* 181 */           MoveCommand.waterAnswerXY[0] = MoveCommand.waterAnswerXY[0] + xMoved;
/* 182 */           MoveCommand.waterAnswerXY[1] = MoveCommand.waterAnswerXY[1] + yMoved;
/* 183 */           this.waterAnswer.field_146128_h = MoveCommand.waterAnswerXY[0];
/* 184 */           this.waterAnswer.field_146129_i = MoveCommand.waterAnswerXY[1];
/*     */           break;
/*     */         case "bonzoTimer":
/* 187 */           MoveCommand.bonzoTimerXY[0] = MoveCommand.bonzoTimerXY[0] + xMoved;
/* 188 */           MoveCommand.bonzoTimerXY[1] = MoveCommand.bonzoTimerXY[1] + yMoved;
/* 189 */           this.bonzoTimer.field_146128_h = MoveCommand.bonzoTimerXY[0];
/* 190 */           this.bonzoTimer.field_146129_i = MoveCommand.bonzoTimerXY[1];
/*     */           break;
/*     */       } 
/* 193 */       this.field_146292_n.clear();
/* 194 */       func_73866_w_();
/*     */     } 
/*     */     
/* 197 */     this.lastMouseX = mouseX;
/* 198 */     this.lastMouseY = mouseY;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_146284_a(GuiButton button) {
/* 203 */     if (button instanceof LocationButton) {
/* 204 */       if (button == this.display) {
/* 205 */         this.moving = "display";
/* 206 */       } else if (button == this.dungeonTimer) {
/* 207 */         this.moving = "dungeonTimer";
/* 208 */       } else if (button == this.coords) {
/* 209 */         this.moving = "coords";
/* 210 */       } else if (button == this.skill50) {
/* 211 */         this.moving = "skill50";
/* 212 */       } else if (button == this.lividHP) {
/* 213 */         this.moving = "lividHP";
/* 214 */       } else if (button == this.cakeTimer) {
/* 215 */         this.moving = "cakeTimer";
/* 216 */       } else if (button == this.skillTracker) {
/* 217 */         this.moving = "skillTracker";
/* 218 */       } else if (button == this.waterAnswer) {
/* 219 */         this.moving = "waterAnswer";
/* 220 */       } else if (button == this.bonzoTimer) {
/* 221 */         this.moving = "bonzoTimer";
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_146286_b(int mouseX, int mouseY, int state) {
/* 228 */     super.func_146286_b(mouseX, mouseY, state);
/* 229 */     this.moving = null;
/* 230 */     ConfigHandler.writeIntConfig("locations", "coordsX", MoveCommand.coordsXY[0]);
/* 231 */     ConfigHandler.writeIntConfig("locations", "coordsY", MoveCommand.coordsXY[1]);
/* 232 */     ConfigHandler.writeIntConfig("locations", "displayX", MoveCommand.displayXY[0]);
/* 233 */     ConfigHandler.writeIntConfig("locations", "displayY", MoveCommand.displayXY[1]);
/* 234 */     ConfigHandler.writeIntConfig("locations", "dungeonTimerX", MoveCommand.dungeonTimerXY[0]);
/* 235 */     ConfigHandler.writeIntConfig("locations", "dungeonTimerY", MoveCommand.dungeonTimerXY[1]);
/* 236 */     ConfigHandler.writeIntConfig("locations", "skill50X", MoveCommand.skill50XY[0]);
/* 237 */     ConfigHandler.writeIntConfig("locations", "skill50Y", MoveCommand.skill50XY[1]);
/* 238 */     ConfigHandler.writeIntConfig("locations", "lividHpX", MoveCommand.lividHpXY[0]);
/* 239 */     ConfigHandler.writeIntConfig("locations", "lividHpY", MoveCommand.lividHpXY[1]);
/* 240 */     ConfigHandler.writeIntConfig("locations", "cakeTimerX", MoveCommand.cakeTimerXY[0]);
/* 241 */     ConfigHandler.writeIntConfig("locations", "cakeTimerY", MoveCommand.cakeTimerXY[1]);
/* 242 */     ConfigHandler.writeIntConfig("locations", "skillTrackerX", MoveCommand.skillTrackerXY[0]);
/* 243 */     ConfigHandler.writeIntConfig("locations", "skillTrackerY", MoveCommand.skillTrackerXY[1]);
/* 244 */     ConfigHandler.writeIntConfig("locations", "waterAnswerX", MoveCommand.waterAnswerXY[0]);
/* 245 */     ConfigHandler.writeIntConfig("locations", "waterAnswerY", MoveCommand.waterAnswerXY[1]);
/* 246 */     ConfigHandler.writeIntConfig("locations", "bonzoTimerX", MoveCommand.bonzoTimerXY[0]);
/* 247 */     ConfigHandler.writeIntConfig("locations", "bonzoTimerY", MoveCommand.bonzoTimerXY[1]);
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\me\Danker\gui\EditLocationsGui.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */