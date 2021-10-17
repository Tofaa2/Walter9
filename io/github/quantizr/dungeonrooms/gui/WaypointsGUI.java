/*     */ package io.github.quantizr.dungeonrooms.gui;
/*     */ 
/*     */ import io.github.quantizr.dungeonrooms.DungeonRooms;
/*     */ import io.github.quantizr.dungeonrooms.dungeons.catacombs.RoomDetection;
/*     */ import io.github.quantizr.dungeonrooms.dungeons.catacombs.Waypoints;
/*     */ import io.github.quantizr.dungeonrooms.handlers.ConfigHandler;
/*     */ import io.github.quantizr.dungeonrooms.handlers.TextRenderer;
/*     */ import io.github.quantizr.dungeonrooms.utils.Utils;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IChatComponent;
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
/*     */ public class WaypointsGUI
/*     */   extends GuiScreen
/*     */ {
/*     */   private GuiButton waypointsEnabled;
/*     */   private GuiButton practiceModeEnabled;
/*     */   private GuiButton showEntrance;
/*     */   private GuiButton showSuperboom;
/*     */   private GuiButton showSecrets;
/*     */   private GuiButton showFairySouls;
/*     */   private GuiButton disableWhenAllFound;
/*     */   private GuiButton sneakToDisable;
/*     */   private GuiButton close;
/*  53 */   public static List<GuiButton> secretButtonList = new ArrayList<>(Arrays.asList(new GuiButton[10]));
/*     */   
/*     */   private static boolean waypointGuiOpened = false;
/*     */ 
/*     */   
/*     */   public boolean func_73868_f() {
/*  59 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_73866_w_() {
/*  64 */     super.func_73866_w_();
/*  65 */     waypointGuiOpened = true;
/*     */     
/*  67 */     ScaledResolution sr = new ScaledResolution(Minecraft.func_71410_x());
/*  68 */     int height = sr.func_78328_b();
/*  69 */     int width = sr.func_78326_a();
/*     */     
/*  71 */     this.waypointsEnabled = new GuiButton(0, width / 2 - 100 - 110, height / 6 + 5, 200, 20, waypointBtnText());
/*  72 */     this.practiceModeEnabled = new GuiButton(1, width / 2 - 100 + 110, height / 6 + 5, 200, 20, "Practice Mode: " + getOnOff(Waypoints.practiceModeOn));
/*  73 */     this.showEntrance = new GuiButton(2, width / 2 - 100 - 110, height / 6 + 35, 200, 20, "Show Entrance Waypoints: " + getOnOff(Waypoints.showEntrance));
/*  74 */     this.showSuperboom = new GuiButton(3, width / 2 - 100 + 110, height / 6 + 35, 200, 20, "Show Superboom Waypoints: " + getOnOff(Waypoints.showSuperboom));
/*  75 */     this.showSecrets = new GuiButton(4, width / 2 - 100 - 110, height / 6 + 65, 200, 20, "Show Secret Waypoints: " + getOnOff(Waypoints.showSecrets));
/*  76 */     this.showFairySouls = new GuiButton(5, width / 2 - 100 + 110, height / 6 + 65, 200, 20, "Show Fairy Soul Waypoints: " + getOnOff(Waypoints.showFairySouls));
/*  77 */     this.sneakToDisable = new GuiButton(6, width / 2 - 100 - 110, height / 6 + 95, 200, 20, "Double-Tap Sneak to Hide Nearby: " + getOnOff(Waypoints.sneakToDisable));
/*  78 */     this.disableWhenAllFound = new GuiButton(7, width / 2 - 100 + 110, height / 6 + 95, 200, 20, "Disable when all secrets found: " + getOnOff(Waypoints.disableWhenAllFound));
/*  79 */     this.close = new GuiButton(8, width / 2 - 100, height / 6 * 5, 200, 20, "Close");
/*     */     
/*  81 */     this.field_146292_n.add(this.waypointsEnabled);
/*  82 */     this.field_146292_n.add(this.practiceModeEnabled);
/*  83 */     this.field_146292_n.add(this.showEntrance);
/*  84 */     this.field_146292_n.add(this.showSuperboom);
/*  85 */     this.field_146292_n.add(this.showSecrets);
/*  86 */     this.field_146292_n.add(this.showFairySouls);
/*  87 */     this.field_146292_n.add(this.sneakToDisable);
/*  88 */     this.field_146292_n.add(this.disableWhenAllFound);
/*  89 */     this.field_146292_n.add(this.close);
/*     */     
/*  91 */     if (Utils.inCatacombs && 
/*  92 */       Waypoints.secretNum > 0) {
/*  93 */       if (Waypoints.secretNum <= 5) {
/*  94 */         for (int i = 1; i <= Waypoints.secretNum; i++) {
/*  95 */           int adjustPos = -40 * Waypoints.secretNum - 70 + 80 * i;
/*  96 */           secretButtonList.set(i - 1, new GuiButton(10 + i, width / 2 + adjustPos, height / 6 + 170, 60, 20, i + ": " + getOnOff(((Boolean)Waypoints.secretsList.get(i - 1)).booleanValue())));
/*  97 */           this.field_146292_n.add(secretButtonList.get(i - 1));
/*     */         } 
/*     */       } else {
/* 100 */         int i; for (i = 1; i <= (int)Math.ceil(Waypoints.secretNum / 2.0D); i++) {
/* 101 */           int adjustPos = -40 * (int)Math.ceil(Waypoints.secretNum / 2.0D) - 70 + 80 * i;
/* 102 */           secretButtonList.set(i - 1, new GuiButton(10 + i, width / 2 + adjustPos, height / 6 + 170, 60, 20, i + ": " + getOnOff(((Boolean)Waypoints.secretsList.get(i - 1)).booleanValue())));
/* 103 */           this.field_146292_n.add(secretButtonList.get(i - 1));
/*     */         } 
/* 105 */         for (i = (int)Math.ceil(Waypoints.secretNum / 2.0D) + 1; i <= Waypoints.secretNum; i++) {
/* 106 */           int adjustPos = -40 * (Waypoints.secretNum - (int)Math.ceil(Waypoints.secretNum / 2.0D)) - 70 + 80 * (i - (int)Math.ceil(Waypoints.secretNum / 2.0D));
/* 107 */           secretButtonList.set(i - 1, new GuiButton(10 + i, width / 2 + adjustPos, height / 6 + 200, 60, 20, i + ": " + getOnOff(((Boolean)Waypoints.secretsList.get(i - 1)).booleanValue())));
/* 108 */           this.field_146292_n.add(secretButtonList.get(i - 1));
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
/* 117 */     func_146276_q_();
/* 118 */     Minecraft mc = Minecraft.func_71410_x();
/*     */     
/* 120 */     String text1 = "§lDungeon Room Waypoints:";
/* 121 */     int text1Width = mc.field_71466_p.func_78256_a(text1);
/* 122 */     TextRenderer.drawText(mc, text1, this.field_146294_l / 2 - text1Width / 2, this.field_146295_m / 6 - 25, 1.0D, false);
/*     */     
/* 124 */     String text2 = "(Use at your own risk)";
/* 125 */     int text2Width = mc.field_71466_p.func_78256_a(text2);
/* 126 */     TextRenderer.drawText(mc, EnumChatFormatting.GRAY + text2, this.field_146294_l / 2 - text2Width / 2, this.field_146295_m / 6 - 15, 1.0D, false);
/*     */     
/* 128 */     String text3 = "Toggle Room Specific Waypoints:";
/* 129 */     int text3Width = mc.field_71466_p.func_78256_a(text3);
/* 130 */     TextRenderer.drawText(mc, text3, this.field_146294_l / 2 - text3Width / 2, this.field_146295_m / 6 + 140, 1.0D, false);
/*     */     
/* 132 */     String text4 = "(You can also press the # key matching the secret instead)";
/* 133 */     int text4Width = mc.field_71466_p.func_78256_a(text4);
/* 134 */     TextRenderer.drawText(mc, EnumChatFormatting.GRAY + text4, this.field_146294_l / 2 - text4Width / 2, this.field_146295_m / 6 + 150, 1.0D, false);
/*     */ 
/*     */     
/* 137 */     if (!Utils.inCatacombs) {
/* 138 */       String errorText = "Not in dungeons";
/* 139 */       int errorTextWidth = mc.field_71466_p.func_78256_a(errorText);
/* 140 */       TextRenderer.drawText(mc, EnumChatFormatting.RED + errorText, this.field_146294_l / 2 - errorTextWidth / 2, this.field_146295_m / 6 + 170, 1.0D, false);
/* 141 */     } else if (Waypoints.secretNum == 0) {
/* 142 */       String errorText = "No secrets in this room";
/* 143 */       int errorTextWidth = mc.field_71466_p.func_78256_a(errorText);
/* 144 */       TextRenderer.drawText(mc, EnumChatFormatting.RED + errorText, this.field_146294_l / 2 - errorTextWidth / 2, this.field_146295_m / 6 + 170, 1.0D, false);
/*     */     } 
/*     */     
/* 147 */     super.func_73863_a(mouseX, mouseY, partialTicks);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_146284_a(GuiButton button) {
/* 152 */     EntityPlayerSP entityPlayerSP = (Minecraft.func_71410_x()).field_71439_g;
/* 153 */     if (button == this.waypointsEnabled) {
/* 154 */       Waypoints.enabled = !Waypoints.enabled;
/* 155 */       ConfigHandler.writeBooleanConfig("toggles", "waypointsToggled", Waypoints.enabled);
/* 156 */       this.waypointsEnabled.field_146126_j = waypointBtnText();
/* 157 */       if (Waypoints.enabled) {
/* 158 */         entityPlayerSP.func_145747_a((IChatComponent)new ChatComponentText("§eDungeon Rooms: Waypoints will now automatically show up when you enter a new dungeon room."));
/*     */       }
/* 160 */     } else if (button == this.practiceModeEnabled) {
/* 161 */       Waypoints.practiceModeOn = !Waypoints.practiceModeOn;
/* 162 */       ConfigHandler.writeBooleanConfig("waypoint", "practiceModeOn", Waypoints.practiceModeOn);
/* 163 */       this.practiceModeEnabled.field_146126_j = "Practice Mode: " + getOnOff(Waypoints.practiceModeOn);
/* 164 */       if (Waypoints.practiceModeOn) {
/* 165 */         entityPlayerSP.func_145747_a((IChatComponent)new ChatComponentText("§eDungeon Rooms: Practice Mode has been enabled.\n§e Waypoints will ONLY show up while you are pressing \"" + 
/* 166 */               GameSettings.func_74298_c(DungeonRooms.keyBindings[2].func_151463_i()) + "\".\n§r (Hotkey is configurable in Minecraft Controls menu)"));
/*     */       
/*     */       }
/*     */     }
/* 170 */     else if (button == this.showEntrance) {
/* 171 */       Waypoints.showEntrance = !Waypoints.showEntrance;
/* 172 */       ConfigHandler.writeBooleanConfig("waypoint", "showEntrance", Waypoints.showEntrance);
/* 173 */       this.showEntrance.field_146126_j = "Show Entrance Waypoints: " + getOnOff(Waypoints.showEntrance);
/* 174 */     } else if (button == this.showSuperboom) {
/* 175 */       Waypoints.showSuperboom = !Waypoints.showSuperboom;
/* 176 */       ConfigHandler.writeBooleanConfig("waypoint", "showSuperboom", Waypoints.showSuperboom);
/* 177 */       this.showSuperboom.field_146126_j = "Show Superboom Waypoints: " + getOnOff(Waypoints.showSuperboom);
/* 178 */     } else if (button == this.showSecrets) {
/* 179 */       Waypoints.showSecrets = !Waypoints.showSecrets;
/* 180 */       ConfigHandler.writeBooleanConfig("waypoint", "showSecrets", Waypoints.showSecrets);
/* 181 */       this.showSecrets.field_146126_j = "Show Secret Waypoints: " + getOnOff(Waypoints.showSecrets);
/* 182 */     } else if (button == this.showFairySouls) {
/* 183 */       Waypoints.showFairySouls = !Waypoints.showFairySouls;
/* 184 */       ConfigHandler.writeBooleanConfig("waypoint", "showFairySouls", Waypoints.showFairySouls);
/* 185 */       this.showFairySouls.field_146126_j = "Show Fairy Soul Waypoints: " + getOnOff(Waypoints.showFairySouls);
/* 186 */     } else if (button == this.sneakToDisable) {
/* 187 */       Waypoints.sneakToDisable = !Waypoints.sneakToDisable;
/* 188 */       ConfigHandler.writeBooleanConfig("waypoint", "sneakToDisable", Waypoints.sneakToDisable);
/* 189 */       this.sneakToDisable.field_146126_j = "Double-Tap Sneak to Hide Nearby: " + getOnOff(Waypoints.sneakToDisable);
/* 190 */     } else if (button == this.disableWhenAllFound) {
/* 191 */       Waypoints.disableWhenAllFound = !Waypoints.disableWhenAllFound;
/* 192 */       ConfigHandler.writeBooleanConfig("waypoint", "disableWhenAllFound", Waypoints.disableWhenAllFound);
/* 193 */       this.disableWhenAllFound.field_146126_j = "Disable when all secrets found: " + getOnOff(Waypoints.disableWhenAllFound);
/*     */     }
/* 195 */     else if (button == this.close) {
/* 196 */       entityPlayerSP.func_71053_j();
/*     */     } 
/*     */     
/* 199 */     if (Utils.inCatacombs && 
/* 200 */       Waypoints.secretNum > 0) {
/* 201 */       for (int i = 1; i <= Waypoints.secretNum; i++) {
/* 202 */         if (button == secretButtonList.get(i - 1)) {
/* 203 */           Waypoints.secretsList.set(i - 1, Boolean.valueOf(!((Boolean)Waypoints.secretsList.get(i - 1)).booleanValue()));
/* 204 */           if (!RoomDetection.roomName.equals("undefined")) {
/* 205 */             Waypoints.allSecretsMap.replace(RoomDetection.roomName, Waypoints.secretsList);
/*     */           }
/* 207 */           ((GuiButton)secretButtonList.get(i - 1)).field_146126_j = i + ": " + getOnOff(((Boolean)Waypoints.secretsList.get(i - 1)).booleanValue());
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_146281_b() {
/* 217 */     waypointGuiOpened = false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_73869_a(char c, int keyCode) throws IOException {
/* 223 */     super.func_73869_a(c, keyCode);
/*     */     
/* 225 */     if (waypointGuiOpened && Utils.inCatacombs && 
/* 226 */       Waypoints.secretNum > 0) {
/* 227 */       for (int i = 1; i <= Waypoints.secretNum; i++) {
/* 228 */         if (keyCode - 1 == i) {
/* 229 */           Waypoints.secretsList.set(i - 1, Boolean.valueOf(!((Boolean)Waypoints.secretsList.get(i - 1)).booleanValue()));
/* 230 */           if (!RoomDetection.roomName.equals("undefined")) {
/* 231 */             Waypoints.allSecretsMap.replace(RoomDetection.roomName, Waypoints.secretsList);
/*     */           }
/* 233 */           ((GuiButton)secretButtonList.get(i - 1)).field_146126_j = i + ": " + getOnOff(((Boolean)Waypoints.secretsList.get(i - 1)).booleanValue());
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static String waypointBtnText() {
/* 242 */     if (Waypoints.enabled) {
/* 243 */       return EnumChatFormatting.GREEN + "§lWaypoints Enabled";
/*     */     }
/* 245 */     return EnumChatFormatting.RED + "§lWaypoints Disabled";
/*     */   }
/*     */ 
/*     */   
/*     */   private static String getOnOff(boolean bool) {
/* 250 */     if (bool) {
/* 251 */       return EnumChatFormatting.GREEN + "On";
/*     */     }
/* 253 */     return EnumChatFormatting.RED + "Off";
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\io\github\quantizr\dungeonrooms\gui\WaypointsGUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */