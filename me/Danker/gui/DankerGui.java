/*     */ package me.Danker.gui;
/*     */ 
/*     */ import java.awt.Desktop;
/*     */ import java.io.IOException;
/*     */ import java.net.URI;
/*     */ import me.Danker.DankersSkyblockMod;
/*     */ import me.Danker.commands.ToggleCommand;
/*     */ import me.Danker.handlers.ConfigHandler;
/*     */ import me.Danker.handlers.TextRenderer;
/*     */ import me.Danker.utils.Utils;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DankerGui
/*     */   extends GuiScreen
/*     */ {
/*     */   private int page;
/*     */   private GuiButton closeGUI;
/*     */   private GuiButton backPage;
/*     */   private GuiButton nextPage;
/*     */   private GuiButton githubLink;
/*     */   private GuiButton discordLink;
/*     */   private GuiButton editLocations;
/*     */   private GuiButton changeDisplay;
/*     */   private GuiButton onlySlayer;
/*     */   private GuiButton puzzleSolvers;
/*     */   private GuiButton experimentationTableSolvers;
/*     */   private GuiButton skillTracker;
/*     */   private GuiButton gparty;
/*     */   private GuiButton coords;
/*     */   private GuiButton goldenEnch;
/*     */   private GuiButton slayerCount;
/*     */   private GuiButton rngesusAlert;
/*     */   private GuiButton splitFishing;
/*     */   private GuiButton chatMaddox;
/*     */   private GuiButton spiritBearAlert;
/*     */   private GuiButton aotd;
/*     */   private GuiButton petColours;
/*     */   private GuiButton golemAlerts;
/*     */   private GuiButton expertiseLore;
/*     */   private GuiButton skill50Display;
/*     */   private GuiButton outlineText;
/*     */   private GuiButton cakeTimer;
/*     */   private GuiButton lividDagger;
/*     */   private GuiButton sceptreMessages;
/*     */   private GuiButton midasStaffMessages;
/*     */   private GuiButton implosionMessages;
/*     */   private GuiButton healMessages;
/*     */   private GuiButton cooldownMessages;
/*     */   private GuiButton manaMessages;
/*     */   private GuiButton dungeonTimer;
/*     */   private GuiButton lowHealthNotify;
/*     */   private GuiButton lividSolver;
/*     */   private GuiButton stopSalvageStarred;
/*     */   private GuiButton watcherReadyMessage;
/*     */   private GuiButton flowerWeapons;
/*     */   private GuiButton pickBlock;
/*     */   private GuiButton notifySlayerSlain;
/*     */   private GuiButton necronNotifications;
/*     */   private GuiButton bonzoTimer;
/*     */   private GuiButton blockBreakingFarms;
/*     */   private GuiButton autoSkillTracker;
/*     */   
/*     */   public DankerGui(int page) {
/*  72 */     this.page = page;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_73868_f() {
/*  77 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_73866_w_() {
/*  82 */     super.func_73866_w_();
/*     */     
/*  84 */     ScaledResolution sr = new ScaledResolution(Minecraft.func_71410_x());
/*  85 */     int height = sr.func_78328_b();
/*  86 */     int width = sr.func_78326_a();
/*     */ 
/*     */     
/*  89 */     this.closeGUI = new GuiButton(0, width / 2 - 100, (int)(height * 0.9D), "Close");
/*  90 */     this.backPage = new GuiButton(0, width / 2 - 100, (int)(height * 0.8D), 80, 20, "< Back");
/*  91 */     this.nextPage = new GuiButton(0, width / 2 + 20, (int)(height * 0.8D), 80, 20, "Next >");
/*  92 */     this.githubLink = new GuiButton(0, 2, height - 50, 80, 20, "GitHub");
/*  93 */     this.discordLink = new GuiButton(0, 2, height - 30, 80, 20, "Discord");
/*  94 */     this.editLocations = new GuiButton(0, 2, 5, 100, 20, "Edit Locations");
/*     */ 
/*     */     
/*  97 */     this.changeDisplay = new GuiButton(0, width / 2 - 100, (int)(height * 0.1D), "Change Display Settings");
/*  98 */     this.onlySlayer = new GuiButton(0, width / 2 - 100, (int)(height * 0.2D), "Set Slayer Quest");
/*  99 */     this.puzzleSolvers = new GuiButton(0, width / 2 - 100, (int)(height * 0.3D), "Toggle Dungeons Puzzle Solvers");
/* 100 */     this.experimentationTableSolvers = new GuiButton(0, width / 2 - 100, (int)(height * 0.4D), "Toggle Experimentation Table Solvers");
/* 101 */     this.skillTracker = new GuiButton(0, width / 2 - 100, (int)(height * 0.5D), "Toggle Skill XP/Hour Tracking");
/* 102 */     this.outlineText = new GuiButton(0, width / 2 - 100, (int)(height * 0.6D), "Outline Displayed Text: " + Utils.getColouredBoolean(ToggleCommand.outlineTextToggled));
/* 103 */     this.pickBlock = new GuiButton(0, width / 2 - 100, (int)(height * 0.7D), "Auto-Swap to Pick Block: " + Utils.getColouredBoolean(ToggleCommand.swapToPickBlockToggled));
/*     */     
/* 105 */     this.coords = new GuiButton(0, width / 2 - 100, (int)(height * 0.1D), "Coordinate/Angle Display: " + Utils.getColouredBoolean(ToggleCommand.coordsToggled));
/* 106 */     this.chatMaddox = new GuiButton(0, width / 2 - 100, (int)(height * 0.2D), "Click On-Screen to Open Maddox: " + Utils.getColouredBoolean(ToggleCommand.chatMaddoxToggled));
/* 107 */     this.cakeTimer = new GuiButton(0, width / 2 - 100, (int)(height * 0.3D), "Cake Timer: " + Utils.getColouredBoolean(ToggleCommand.cakeTimerToggled));
/* 108 */     this.skill50Display = new GuiButton(0, width / 2 - 100, (int)(height * 0.4D), "Display Progress To Skill Level 50: " + Utils.getColouredBoolean(ToggleCommand.skill50DisplayToggled));
/* 109 */     this.slayerCount = new GuiButton(0, width / 2 - 100, (int)(height * 0.5D), "Count Total 20% Drops: " + Utils.getColouredBoolean(ToggleCommand.slayerCountTotal));
/* 110 */     this.aotd = new GuiButton(0, width / 2 - 100, (int)(height * 0.6D), "Disable AOTD Ability: " + Utils.getColouredBoolean(ToggleCommand.aotdToggled));
/* 111 */     this.lividDagger = new GuiButton(0, width / 2 - 100, (int)(height * 0.7D), "Disable Livid Dagger Ability: " + Utils.getColouredBoolean(ToggleCommand.lividDaggerToggled));
/*     */     
/* 113 */     this.spiritBearAlert = new GuiButton(0, width / 2 - 100, (int)(height * 0.1D), "Spirit Bear Spawn Alerts: " + Utils.getColouredBoolean(ToggleCommand.spiritBearAlerts));
/* 114 */     this.sceptreMessages = new GuiButton(0, width / 2 - 100, (int)(height * 0.2D), "Spirit Sceptre Messages: " + Utils.getColouredBoolean(ToggleCommand.sceptreMessages));
/* 115 */     this.midasStaffMessages = new GuiButton(0, width / 2 - 100, (int)(height * 0.3D), "Midas Staff Messages: " + Utils.getColouredBoolean(ToggleCommand.midasStaffMessages));
/* 116 */     this.implosionMessages = new GuiButton(0, width / 2 - 100, (int)(height * 0.4D), "Implosion Messages: " + Utils.getColouredBoolean(ToggleCommand.implosionMessages));
/* 117 */     this.healMessages = new GuiButton(0, width / 2 - 100, (int)(height * 0.5D), "Heal Messages: " + Utils.getColouredBoolean(ToggleCommand.healMessages));
/* 118 */     this.cooldownMessages = new GuiButton(0, width / 2 - 100, (int)(height * 0.6D), "Cooldown Messages: " + Utils.getColouredBoolean(ToggleCommand.cooldownMessages));
/* 119 */     this.manaMessages = new GuiButton(0, width / 2 - 100, (int)(height * 0.7D), "Mana Messages: " + Utils.getColouredBoolean(ToggleCommand.manaMessages));
/*     */     
/* 121 */     this.goldenEnch = new GuiButton(0, width / 2 - 100, (int)(height * 0.1D), "Golden T10/T6/T4 Enchantments: " + Utils.getColouredBoolean(ToggleCommand.goldenToggled));
/* 122 */     this.petColours = new GuiButton(0, width / 2 - 100, (int)(height * 0.2D), "Colour Pet Backgrounds: " + Utils.getColouredBoolean(ToggleCommand.petColoursToggled));
/* 123 */     this.expertiseLore = new GuiButton(0, width / 2 - 100, (int)(height * 0.3D), "Expertise Kills In Lore: " + Utils.getColouredBoolean(ToggleCommand.expertiseLoreToggled));
/* 124 */     this.gparty = new GuiButton(0, width / 2 - 100, (int)(height * 0.4D), "Guild Party Notifications: " + Utils.getColouredBoolean(ToggleCommand.gpartyToggled));
/* 125 */     this.golemAlerts = new GuiButton(0, width / 2 - 100, (int)(height * 0.5D), "Alert When Golem Spawns: " + Utils.getColouredBoolean(ToggleCommand.golemAlertToggled));
/* 126 */     this.rngesusAlert = new GuiButton(0, width / 2 - 100, (int)(height * 0.6D), "RNGesus Alerts: " + Utils.getColouredBoolean(ToggleCommand.rngesusAlerts));
/* 127 */     this.splitFishing = new GuiButton(0, width / 2 - 100, (int)(height * 0.7D), "Split Fishing Display: " + Utils.getColouredBoolean(ToggleCommand.splitFishing));
/*     */     
/* 129 */     this.lowHealthNotify = new GuiButton(0, width / 2 - 100, (int)(height * 0.1D), "Low Health Notifications: " + Utils.getColouredBoolean(ToggleCommand.lowHealthNotifyToggled));
/* 130 */     this.lividSolver = new GuiButton(0, width / 2 - 100, (int)(height * 0.2D), "Find Correct Livid: " + Utils.getColouredBoolean(ToggleCommand.lividSolverToggled));
/* 131 */     this.dungeonTimer = new GuiButton(0, width / 2 - 100, (int)(height * 0.3D), "Display Dungeon Timers: " + Utils.getColouredBoolean(ToggleCommand.dungeonTimerToggled));
/* 132 */     this.stopSalvageStarred = new GuiButton(0, width / 2 - 100, (int)(height * 0.4D), "Stop Salvaging Starred Items: " + Utils.getColouredBoolean(ToggleCommand.stopSalvageStarredToggled));
/* 133 */     this.watcherReadyMessage = new GuiButton(0, width / 2 - 100, (int)(height * 0.5D), "Display Watcher Ready Message: " + Utils.getColouredBoolean(ToggleCommand.watcherReadyToggled));
/* 134 */     this.flowerWeapons = new GuiButton(0, width / 2 - 100, (int)(height * 0.6D), "Prevent Placing FoT/Spirit Sceptre: " + Utils.getColouredBoolean(ToggleCommand.flowerWeaponsToggled));
/* 135 */     this.notifySlayerSlain = new GuiButton(0, width / 2 - 100, (int)(height * 0.7D), "Notify when Slayer Slain: " + Utils.getColouredBoolean(ToggleCommand.notifySlayerSlainToggled));
/*     */     
/* 137 */     this.necronNotifications = new GuiButton(0, width / 2 - 100, (int)(height * 0.1D), "Necron Phase Notifications: " + Utils.getColouredBoolean(ToggleCommand.necronNotificationsToggled));
/* 138 */     this.bonzoTimer = new GuiButton(0, width / 2 - 100, (int)(height * 0.2D), "Bonzo's Mask Timer: " + Utils.getColouredBoolean(ToggleCommand.bonzoTimerToggled));
/* 139 */     this.blockBreakingFarms = new GuiButton(0, width / 2 - 100, (int)(height * 0.3D), "Prevent Breaking Farms: " + Utils.getColouredBoolean(ToggleCommand.blockBreakingFarmsToggled));
/* 140 */     this.autoSkillTracker = new GuiButton(0, width / 2 - 100, (int)(height * 0.4D), "Auto Start/Stop Skill Tracker: " + Utils.getColouredBoolean(ToggleCommand.autoSkillTrackerToggled));
/*     */     
/* 142 */     switch (this.page) {
/*     */       case 1:
/* 144 */         this.field_146292_n.add(this.changeDisplay);
/* 145 */         this.field_146292_n.add(this.onlySlayer);
/* 146 */         this.field_146292_n.add(this.puzzleSolvers);
/* 147 */         this.field_146292_n.add(this.experimentationTableSolvers);
/* 148 */         this.field_146292_n.add(this.skillTracker);
/* 149 */         this.field_146292_n.add(this.outlineText);
/* 150 */         this.field_146292_n.add(this.pickBlock);
/* 151 */         this.field_146292_n.add(this.nextPage);
/*     */         break;
/*     */       case 2:
/* 154 */         this.field_146292_n.add(this.coords);
/* 155 */         this.field_146292_n.add(this.chatMaddox);
/* 156 */         this.field_146292_n.add(this.cakeTimer);
/* 157 */         this.field_146292_n.add(this.skill50Display);
/* 158 */         this.field_146292_n.add(this.slayerCount);
/* 159 */         this.field_146292_n.add(this.aotd);
/* 160 */         this.field_146292_n.add(this.lividDagger);
/* 161 */         this.field_146292_n.add(this.nextPage);
/* 162 */         this.field_146292_n.add(this.backPage);
/*     */         break;
/*     */       case 3:
/* 165 */         this.field_146292_n.add(this.spiritBearAlert);
/* 166 */         this.field_146292_n.add(this.sceptreMessages);
/* 167 */         this.field_146292_n.add(this.midasStaffMessages);
/* 168 */         this.field_146292_n.add(this.implosionMessages);
/* 169 */         this.field_146292_n.add(this.healMessages);
/* 170 */         this.field_146292_n.add(this.cooldownMessages);
/* 171 */         this.field_146292_n.add(this.manaMessages);
/* 172 */         this.field_146292_n.add(this.nextPage);
/* 173 */         this.field_146292_n.add(this.backPage);
/*     */         break;
/*     */       case 4:
/* 176 */         this.field_146292_n.add(this.goldenEnch);
/* 177 */         this.field_146292_n.add(this.petColours);
/* 178 */         this.field_146292_n.add(this.expertiseLore);
/* 179 */         this.field_146292_n.add(this.gparty);
/* 180 */         this.field_146292_n.add(this.golemAlerts);
/* 181 */         this.field_146292_n.add(this.rngesusAlert);
/* 182 */         this.field_146292_n.add(this.splitFishing);
/* 183 */         this.field_146292_n.add(this.nextPage);
/* 184 */         this.field_146292_n.add(this.backPage);
/*     */         break;
/*     */       case 5:
/* 187 */         this.field_146292_n.add(this.lowHealthNotify);
/* 188 */         this.field_146292_n.add(this.lividSolver);
/* 189 */         this.field_146292_n.add(this.dungeonTimer);
/* 190 */         this.field_146292_n.add(this.stopSalvageStarred);
/* 191 */         this.field_146292_n.add(this.watcherReadyMessage);
/* 192 */         this.field_146292_n.add(this.flowerWeapons);
/* 193 */         this.field_146292_n.add(this.notifySlayerSlain);
/* 194 */         this.field_146292_n.add(this.nextPage);
/* 195 */         this.field_146292_n.add(this.backPage);
/*     */         break;
/*     */       case 6:
/* 198 */         this.field_146292_n.add(this.necronNotifications);
/* 199 */         this.field_146292_n.add(this.bonzoTimer);
/* 200 */         this.field_146292_n.add(this.blockBreakingFarms);
/* 201 */         this.field_146292_n.add(this.autoSkillTracker);
/* 202 */         this.field_146292_n.add(this.backPage);
/*     */         break;
/*     */     } 
/*     */     
/* 206 */     this.field_146292_n.add(this.githubLink);
/* 207 */     this.field_146292_n.add(this.discordLink);
/* 208 */     this.field_146292_n.add(this.closeGUI);
/* 209 */     this.field_146292_n.add(this.editLocations);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
/* 214 */     func_146276_q_();
/* 215 */     String pageText = "Page: " + this.page + "/6";
/* 216 */     int pageWidth = this.field_146297_k.field_71466_p.func_78256_a(pageText);
/* 217 */     new TextRenderer(this.field_146297_k, pageText, this.field_146294_l / 2 - pageWidth / 2, 10, 1.0D);
/* 218 */     super.func_73863_a(mouseX, mouseY, partialTicks);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_146284_a(GuiButton button) {
/* 223 */     if (button == this.closeGUI) {
/* 224 */       (Minecraft.func_71410_x()).field_71439_g.func_71053_j();
/* 225 */     } else if (button == this.nextPage) {
/* 226 */       DankersSkyblockMod.guiToOpen = "dankergui" + (this.page + 1);
/* 227 */     } else if (button == this.backPage) {
/* 228 */       DankersSkyblockMod.guiToOpen = "dankergui" + (this.page - 1);
/* 229 */     } else if (button == this.editLocations) {
/* 230 */       DankersSkyblockMod.guiToOpen = "editlocations";
/* 231 */     } else if (button == this.githubLink) {
/*     */       try {
/* 233 */         Desktop.getDesktop().browse(new URI("https://github.com/bowser0000/SkyblockMod"));
/* 234 */       } catch (IOException|java.net.URISyntaxException ex) {
/* 235 */         ex.printStackTrace();
/*     */       } 
/* 237 */     } else if (button == this.discordLink) {
/*     */       try {
/* 239 */         Desktop.getDesktop().browse(new URI("https://discord.gg/QsEkNQS"));
/* 240 */       } catch (IOException|java.net.URISyntaxException ex) {
/* 241 */         ex.printStackTrace();
/*     */       } 
/* 243 */     } else if (button == this.changeDisplay) {
/* 244 */       DankersSkyblockMod.guiToOpen = "displaygui";
/* 245 */     } else if (button == this.onlySlayer) {
/* 246 */       DankersSkyblockMod.guiToOpen = "onlyslayergui";
/* 247 */     } else if (button == this.puzzleSolvers) {
/* 248 */       DankersSkyblockMod.guiToOpen = "puzzlesolvers";
/* 249 */     } else if (button == this.experimentationTableSolvers) {
/* 250 */       DankersSkyblockMod.guiToOpen = "experimentsolvers";
/* 251 */     } else if (button == this.skillTracker) {
/* 252 */       DankersSkyblockMod.guiToOpen = "skilltracker";
/* 253 */     } else if (button == this.outlineText) {
/* 254 */       ToggleCommand.outlineTextToggled = !ToggleCommand.outlineTextToggled;
/* 255 */       ConfigHandler.writeBooleanConfig("toggles", "OutlineText", ToggleCommand.outlineTextToggled);
/* 256 */       this.outlineText.field_146126_j = "Outline Displayed Text: " + Utils.getColouredBoolean(ToggleCommand.outlineTextToggled);
/* 257 */     } else if (button == this.gparty) {
/* 258 */       ToggleCommand.gpartyToggled = !ToggleCommand.gpartyToggled;
/* 259 */       ConfigHandler.writeBooleanConfig("toggles", "GParty", ToggleCommand.gpartyToggled);
/* 260 */       this.gparty.field_146126_j = "Guild Party Notifications: " + Utils.getColouredBoolean(ToggleCommand.gpartyToggled);
/* 261 */     } else if (button == this.coords) {
/* 262 */       ToggleCommand.coordsToggled = !ToggleCommand.coordsToggled;
/* 263 */       ConfigHandler.writeBooleanConfig("toggles", "Coords", ToggleCommand.coordsToggled);
/* 264 */       this.coords.field_146126_j = "Coordinate/Angle Display: " + Utils.getColouredBoolean(ToggleCommand.coordsToggled);
/* 265 */     } else if (button == this.goldenEnch) {
/* 266 */       ToggleCommand.goldenToggled = !ToggleCommand.goldenToggled;
/* 267 */       ConfigHandler.writeBooleanConfig("toggles", "Golden", ToggleCommand.goldenToggled);
/* 268 */       this.goldenEnch.field_146126_j = "Golden T10/T6/T4 Enchantments: " + Utils.getColouredBoolean(ToggleCommand.goldenToggled);
/* 269 */     } else if (button == this.slayerCount) {
/* 270 */       ToggleCommand.slayerCountTotal = !ToggleCommand.slayerCountTotal;
/* 271 */       ConfigHandler.writeBooleanConfig("toggles", "SlayerCount", ToggleCommand.slayerCountTotal);
/* 272 */       this.slayerCount.field_146126_j = "Count Total 20% Drops: " + Utils.getColouredBoolean(ToggleCommand.slayerCountTotal);
/* 273 */     } else if (button == this.aotd) {
/* 274 */       ToggleCommand.aotdToggled = !ToggleCommand.aotdToggled;
/* 275 */       ConfigHandler.writeBooleanConfig("toggles", "AOTD", ToggleCommand.aotdToggled);
/* 276 */       this.aotd.field_146126_j = "Disable AOTD Ability: " + Utils.getColouredBoolean(ToggleCommand.aotdToggled);
/* 277 */     } else if (button == this.lividDagger) {
/* 278 */       ToggleCommand.lividDaggerToggled = !ToggleCommand.lividDaggerToggled;
/* 279 */       ConfigHandler.writeBooleanConfig("toggles", "LividDagger", ToggleCommand.lividDaggerToggled);
/* 280 */       this.lividDagger.field_146126_j = "Disable Livid Dagger Ability: " + Utils.getColouredBoolean(ToggleCommand.lividDaggerToggled);
/* 281 */     } else if (button == this.sceptreMessages) {
/* 282 */       ToggleCommand.sceptreMessages = !ToggleCommand.sceptreMessages;
/* 283 */       ConfigHandler.writeBooleanConfig("toggles", "SceptreMessages", ToggleCommand.sceptreMessages);
/* 284 */       this.sceptreMessages.field_146126_j = "Spirit Sceptre Messages: " + Utils.getColouredBoolean(ToggleCommand.sceptreMessages);
/* 285 */     } else if (button == this.petColours) {
/* 286 */       ToggleCommand.petColoursToggled = !ToggleCommand.petColoursToggled;
/* 287 */       ConfigHandler.writeBooleanConfig("toggles", "PetColors", ToggleCommand.petColoursToggled);
/* 288 */       this.petColours.field_146126_j = "Colour Pet Backgrounds: " + Utils.getColouredBoolean(ToggleCommand.petColoursToggled);
/* 289 */     } else if (button == this.dungeonTimer) {
/* 290 */       ToggleCommand.dungeonTimerToggled = !ToggleCommand.dungeonTimerToggled;
/* 291 */       ConfigHandler.writeBooleanConfig("toggles", "DungeonTimer", ToggleCommand.dungeonTimerToggled);
/* 292 */       this.dungeonTimer.field_146126_j = "Display Dungeon Timers: " + Utils.getColouredBoolean(ToggleCommand.dungeonTimerToggled);
/* 293 */     } else if (button == this.golemAlerts) {
/* 294 */       ToggleCommand.golemAlertToggled = !ToggleCommand.golemAlertToggled;
/* 295 */       ConfigHandler.writeBooleanConfig("toggles", "GolemAlerts", ToggleCommand.golemAlertToggled);
/* 296 */       this.golemAlerts.field_146126_j = "Alert When Golem Spawns: " + Utils.getColouredBoolean(ToggleCommand.golemAlertToggled);
/* 297 */     } else if (button == this.expertiseLore) {
/* 298 */       ToggleCommand.expertiseLoreToggled = !ToggleCommand.expertiseLoreToggled;
/* 299 */       ConfigHandler.writeBooleanConfig("toggles", "ExpertiseLore", ToggleCommand.expertiseLoreToggled);
/* 300 */       this.expertiseLore.field_146126_j = "Expertise Kills In Lore: " + Utils.getColouredBoolean(ToggleCommand.expertiseLoreToggled);
/* 301 */     } else if (button == this.skill50Display) {
/* 302 */       ToggleCommand.skill50DisplayToggled = !ToggleCommand.skill50DisplayToggled;
/* 303 */       ConfigHandler.writeBooleanConfig("toggles", "Skill50Display", ToggleCommand.skill50DisplayToggled);
/* 304 */       this.skill50Display.field_146126_j = "Display Progress To Skill Level 50: " + Utils.getColouredBoolean(ToggleCommand.skill50DisplayToggled);
/* 305 */     } else if (button == this.splitFishing) {
/* 306 */       ToggleCommand.splitFishing = !ToggleCommand.splitFishing;
/* 307 */       ConfigHandler.writeBooleanConfig("toggles", "SplitFishing", ToggleCommand.splitFishing);
/* 308 */       this.splitFishing.field_146126_j = "Split Fishing Display: " + Utils.getColouredBoolean(ToggleCommand.splitFishing);
/* 309 */     } else if (button == this.chatMaddox) {
/* 310 */       ToggleCommand.chatMaddoxToggled = !ToggleCommand.chatMaddoxToggled;
/* 311 */       ConfigHandler.writeBooleanConfig("toggles", "ChatMaddox", ToggleCommand.chatMaddoxToggled);
/* 312 */       this.chatMaddox.field_146126_j = "Click On-Screen to Open Maddox: " + Utils.getColouredBoolean(ToggleCommand.chatMaddoxToggled);
/* 313 */     } else if (button == this.spiritBearAlert) {
/* 314 */       ToggleCommand.spiritBearAlerts = !ToggleCommand.spiritBearAlerts;
/* 315 */       ConfigHandler.writeBooleanConfig("toggles", "SpiritBearAlerts", ToggleCommand.spiritBearAlerts);
/* 316 */       this.spiritBearAlert.field_146126_j = "Spirit Bear Spawn Alerts: " + Utils.getColouredBoolean(ToggleCommand.spiritBearAlerts);
/* 317 */     } else if (button == this.midasStaffMessages) {
/* 318 */       ToggleCommand.midasStaffMessages = !ToggleCommand.midasStaffMessages;
/* 319 */       ConfigHandler.writeBooleanConfig("toggles", "MidasStaffMessages", ToggleCommand.midasStaffMessages);
/* 320 */       this.midasStaffMessages.field_146126_j = "Midas Staff Messages: " + Utils.getColouredBoolean(ToggleCommand.midasStaffMessages);
/* 321 */     } else if (button == this.lividSolver) {
/* 322 */       ToggleCommand.lividSolverToggled = !ToggleCommand.lividSolverToggled;
/* 323 */       ConfigHandler.writeBooleanConfig("toggles", "LividSolver", ToggleCommand.lividSolverToggled);
/* 324 */       this.lividSolver.field_146126_j = "Find Correct Livid: " + Utils.getColouredBoolean(ToggleCommand.lividSolverToggled);
/* 325 */     } else if (button == this.rngesusAlert) {
/* 326 */       ToggleCommand.rngesusAlerts = !ToggleCommand.rngesusAlerts;
/* 327 */       ConfigHandler.writeBooleanConfig("toggles", "RNGesusAlerts", ToggleCommand.rngesusAlerts);
/* 328 */       this.rngesusAlert.field_146126_j = "RNGesus Alerts: " + Utils.getColouredBoolean(ToggleCommand.rngesusAlerts);
/* 329 */     } else if (button == this.cakeTimer) {
/* 330 */       ToggleCommand.cakeTimerToggled = !ToggleCommand.cakeTimerToggled;
/* 331 */       ConfigHandler.writeBooleanConfig("toggles", "CakeTimer", ToggleCommand.cakeTimerToggled);
/* 332 */       this.cakeTimer.field_146126_j = "Cake Timer: " + Utils.getColouredBoolean(ToggleCommand.cakeTimerToggled);
/* 333 */     } else if (button == this.healMessages) {
/* 334 */       ToggleCommand.healMessages = !ToggleCommand.healMessages;
/* 335 */       ConfigHandler.writeBooleanConfig("toggles", "HealMessages", ToggleCommand.healMessages);
/* 336 */       this.healMessages.field_146126_j = "Heal Messages: " + Utils.getColouredBoolean(ToggleCommand.healMessages);
/* 337 */     } else if (button == this.cooldownMessages) {
/* 338 */       ToggleCommand.cooldownMessages = !ToggleCommand.cooldownMessages;
/* 339 */       ConfigHandler.writeBooleanConfig("toggles", "CooldownMessages", ToggleCommand.cooldownMessages);
/* 340 */       this.cooldownMessages.field_146126_j = "Cooldown Messages: " + Utils.getColouredBoolean(ToggleCommand.cooldownMessages);
/* 341 */     } else if (button == this.manaMessages) {
/* 342 */       ToggleCommand.manaMessages = !ToggleCommand.manaMessages;
/* 343 */       ConfigHandler.writeBooleanConfig("toggles", "ManaMessages", ToggleCommand.manaMessages);
/* 344 */       this.manaMessages.field_146126_j = "Mana Messages: " + Utils.getColouredBoolean(ToggleCommand.manaMessages);
/* 345 */     } else if (button == this.lowHealthNotify) {
/* 346 */       ToggleCommand.lowHealthNotifyToggled = !ToggleCommand.lowHealthNotifyToggled;
/* 347 */       ConfigHandler.writeBooleanConfig("toggles", "LowHealthNotify", ToggleCommand.lowHealthNotifyToggled);
/* 348 */       this.lowHealthNotify.field_146126_j = "Low Health Notifications: " + Utils.getColouredBoolean(ToggleCommand.lowHealthNotifyToggled);
/* 349 */     } else if (button == this.implosionMessages) {
/* 350 */       ToggleCommand.implosionMessages = !ToggleCommand.implosionMessages;
/* 351 */       ConfigHandler.writeBooleanConfig("toggles", "ImplosionMessages", ToggleCommand.implosionMessages);
/* 352 */       this.implosionMessages.field_146126_j = "Implosion Messages: " + Utils.getColouredBoolean(ToggleCommand.implosionMessages);
/* 353 */     } else if (button == this.stopSalvageStarred) {
/* 354 */       ToggleCommand.stopSalvageStarredToggled = !ToggleCommand.stopSalvageStarredToggled;
/* 355 */       ConfigHandler.writeBooleanConfig("toggles", "StopSalvageStarred", ToggleCommand.stopSalvageStarredToggled);
/* 356 */       this.stopSalvageStarred.field_146126_j = "Stop Salvaging Starred Items: " + Utils.getColouredBoolean(ToggleCommand.stopSalvageStarredToggled);
/* 357 */     } else if (button == this.watcherReadyMessage) {
/* 358 */       ToggleCommand.watcherReadyToggled = !ToggleCommand.watcherReadyToggled;
/* 359 */       ConfigHandler.writeBooleanConfig("toggles", "WatcherReadyMessage", ToggleCommand.watcherReadyToggled);
/* 360 */       this.watcherReadyMessage.field_146126_j = "Display Watcher Ready Message: " + Utils.getColouredBoolean(ToggleCommand.watcherReadyToggled);
/* 361 */     } else if (button == this.notifySlayerSlain) {
/* 362 */       ToggleCommand.notifySlayerSlainToggled = !ToggleCommand.notifySlayerSlainToggled;
/* 363 */       ConfigHandler.writeBooleanConfig("toggles", "NotifySlayerSlain", ToggleCommand.notifySlayerSlainToggled);
/* 364 */       this.notifySlayerSlain.field_146126_j = "Notify when Slayer Slain: " + Utils.getColouredBoolean(ToggleCommand.notifySlayerSlainToggled);
/* 365 */     } else if (button == this.necronNotifications) {
/* 366 */       ToggleCommand.necronNotificationsToggled = !ToggleCommand.necronNotificationsToggled;
/* 367 */       ConfigHandler.writeBooleanConfig("toggles", "NecronNotifications", ToggleCommand.necronNotificationsToggled);
/* 368 */       this.necronNotifications.field_146126_j = "Necron Phase Notifications: " + Utils.getColouredBoolean(ToggleCommand.necronNotificationsToggled);
/* 369 */     } else if (button == this.bonzoTimer) {
/* 370 */       ToggleCommand.bonzoTimerToggled = !ToggleCommand.bonzoTimerToggled;
/* 371 */       ConfigHandler.writeBooleanConfig("toggles", "BonzoTimer", ToggleCommand.bonzoTimerToggled);
/* 372 */       this.bonzoTimer.field_146126_j = "Bonzo's Mask Timer: " + Utils.getColouredBoolean(ToggleCommand.bonzoTimerToggled);
/* 373 */     } else if (button == this.blockBreakingFarms) {
/* 374 */       ToggleCommand.blockBreakingFarmsToggled = !ToggleCommand.blockBreakingFarmsToggled;
/* 375 */       ConfigHandler.writeBooleanConfig("toggles", "BlockBreakingFarms", ToggleCommand.blockBreakingFarmsToggled);
/* 376 */       this.blockBreakingFarms.field_146126_j = "Prevent Breaking Farms: " + Utils.getColouredBoolean(ToggleCommand.blockBreakingFarmsToggled);
/* 377 */     } else if (button == this.pickBlock) {
/* 378 */       ToggleCommand.swapToPickBlockToggled = !ToggleCommand.swapToPickBlockToggled;
/* 379 */       ConfigHandler.writeBooleanConfig("toggles", "PickBlock", ToggleCommand.swapToPickBlockToggled);
/* 380 */       this.pickBlock.field_146126_j = "Auto-Swap to Pick Block: " + Utils.getColouredBoolean(ToggleCommand.swapToPickBlockToggled);
/* 381 */     } else if (button == this.flowerWeapons) {
/* 382 */       ToggleCommand.flowerWeaponsToggled = !ToggleCommand.flowerWeaponsToggled;
/* 383 */       ConfigHandler.writeBooleanConfig("toggles", "FlowerWeapons", ToggleCommand.flowerWeaponsToggled);
/* 384 */       this.flowerWeapons.field_146126_j = "Prevent Placing FoT/Spirit Sceptre: " + Utils.getColouredBoolean(ToggleCommand.flowerWeaponsToggled);
/* 385 */     } else if (button == this.autoSkillTracker) {
/* 386 */       ToggleCommand.autoSkillTrackerToggled = !ToggleCommand.autoSkillTrackerToggled;
/* 387 */       ConfigHandler.writeBooleanConfig("toggles", "AutoSkillTracker", ToggleCommand.autoSkillTrackerToggled);
/* 388 */       this.autoSkillTracker.field_146126_j = "Auto Start/Stop Skill Tracker: " + Utils.getColouredBoolean(ToggleCommand.autoSkillTrackerToggled);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\me\Danker\gui\DankerGui.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */