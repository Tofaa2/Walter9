/*     */ package io.github.quantizr.dungeonrooms.gui;
/*     */ 
/*     */ import io.github.quantizr.dungeonrooms.DungeonRooms;
/*     */ import io.github.quantizr.dungeonrooms.dungeons.catacombs.RoomDetection;
/*     */ import io.github.quantizr.dungeonrooms.handlers.OpenLink;
/*     */ import io.github.quantizr.dungeonrooms.handlers.TextRenderer;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.event.ClickEvent;
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
/*     */ public class LinkGUI
/*     */   extends GuiScreen
/*     */ {
/*     */   private GuiButton discordClient;
/*     */   private GuiButton discordBrowser;
/*     */   private GuiButton SBPSecrets;
/*     */   private GuiButton close;
/*     */   
/*     */   public boolean func_73868_f() {
/*  44 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_73866_w_() {
/*  49 */     super.func_73866_w_();
/*     */     
/*  51 */     ScaledResolution sr = new ScaledResolution(Minecraft.func_71410_x());
/*  52 */     int height = sr.func_78328_b();
/*  53 */     int width = sr.func_78326_a();
/*     */     
/*  55 */     this.discordClient = new GuiButton(0, width / 2 - 185, height / 6 + 96, 120, 20, "DSG Discord Client");
/*  56 */     this.discordBrowser = new GuiButton(1, width / 2 - 60, height / 6 + 96, 120, 20, "DSG Discord Browser");
/*  57 */     this.SBPSecrets = new GuiButton(2, width / 2 + 65, height / 6 + 96, 120, 20, "SBP Secrets Mod");
/*  58 */     this.close = new GuiButton(3, width / 2 - 60, height / 6 + 136, 120, 20, "Close");
/*     */     
/*  60 */     this.field_146292_n.add(this.discordClient);
/*  61 */     this.field_146292_n.add(this.discordBrowser);
/*  62 */     this.field_146292_n.add(this.SBPSecrets);
/*  63 */     this.field_146292_n.add(this.close);
/*     */   }
/*     */   
/*     */   public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
/*     */     String displayText;
/*  68 */     func_146276_q_();
/*  69 */     Minecraft mc = Minecraft.func_71410_x();
/*     */ 
/*     */     
/*  72 */     if (RoomDetection.roomName.equals("undefined")) {
/*  73 */       displayText = "Where would you like to view secrets for: " + EnumChatFormatting.RED + "undefined";
/*     */     } else {
/*  75 */       displayText = "Where would you like to view secrets for: " + EnumChatFormatting.GREEN + RoomDetection.roomName;
/*     */     } 
/*  77 */     int displayWidth = mc.field_71466_p.func_78256_a(displayText);
/*  78 */     TextRenderer.drawText(mc, displayText, this.field_146294_l / 2 - displayWidth / 2, this.field_146295_m / 6 + 56, 1.0D, false);
/*     */     
/*  80 */     String noteText = EnumChatFormatting.GRAY + "If you wish to have the hotkey go directly to DSG or SBP instead of this GUI run " + EnumChatFormatting.WHITE + "/room set <gui | dsg | sbp>";
/*     */     
/*  82 */     int noteWidth = mc.field_71466_p.func_78256_a(noteText);
/*  83 */     TextRenderer.drawText(mc, noteText, this.field_146294_l / 2 - noteWidth / 2, (int)(this.field_146295_m * 0.9D), 1.0D, false);
/*     */     
/*  85 */     super.func_73863_a(mouseX, mouseY, partialTicks);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_146284_a(GuiButton button) {
/*  90 */     EntityPlayerSP entityPlayerSP = (Minecraft.func_71410_x()).field_71439_g;
/*  91 */     if (button == this.discordClient) {
/*  92 */       OpenLink.openDiscord("client");
/*  93 */       entityPlayerSP.func_71053_j();
/*  94 */     } else if (button == this.discordBrowser) {
/*  95 */       OpenLink.openDiscord("browser");
/*  96 */       entityPlayerSP.func_71053_j();
/*  97 */     } else if (button == this.SBPSecrets) {
/*  98 */       if (DungeonRooms.usingSBPSecrets) {
/*  99 */         OpenLink.openSBPSecrets();
/*     */       } else {
/* 101 */         String sbpURL = "https://discord.gg/2UjaFqfPwJ";
/* 102 */         ChatComponentText sbp = new ChatComponentText(EnumChatFormatting.YELLOW + "" + EnumChatFormatting.UNDERLINE + sbpURL);
/* 103 */         sbp.func_150255_a(sbp.func_150256_b().func_150241_a(new ClickEvent(ClickEvent.Action.OPEN_URL, sbpURL)));
/* 104 */         entityPlayerSP.func_145747_a((new ChatComponentText(EnumChatFormatting.RED + "Dungeon Rooms: You need theSkyblock Personalized (SBP) Mod for this feature, get it from "))
/* 105 */             .func_150257_a((IChatComponent)sbp));
/*     */       } 
/* 107 */       entityPlayerSP.func_71053_j();
/* 108 */     } else if (button == this.close) {
/* 109 */       entityPlayerSP.func_71053_j();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\io\github\quantizr\dungeonrooms\gui\LinkGUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */