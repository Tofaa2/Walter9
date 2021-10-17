/*     */ package io.github.quantizr.dungeonrooms.handlers;
/*     */ 
/*     */ import com.google.gson.JsonObject;
/*     */ import io.github.quantizr.dungeonrooms.DungeonRooms;
/*     */ import io.github.quantizr.dungeonrooms.dungeons.catacombs.RoomDetection;
/*     */ import io.github.quantizr.dungeonrooms.gui.LinkGUI;
/*     */ import java.awt.Desktop;
/*     */ import java.io.IOException;
/*     */ import java.net.URI;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.command.ICommandSender;
/*     */ import net.minecraft.event.ClickEvent;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraftforge.client.ClientCommandHandler;
/*     */ import net.minecraftforge.fml.client.FMLClientHandler;
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
/*     */ public class OpenLink
/*     */ {
/*     */   public static void checkForLink(String type) {
/*     */     String sbpURL;
/*     */     ChatComponentText sbp;
/*  41 */     Minecraft mc = Minecraft.func_71410_x();
/*  42 */     EntityPlayerSP player = mc.field_71439_g;
/*     */     
/*  44 */     if (RoomDetection.roomName.equals("undefined") || DungeonRooms.roomsJson.get(RoomDetection.roomName) == null) {
/*  45 */       player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "Dungeon Rooms: You do not appear to be in a detected Dungeon room right now."));
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  50 */     JsonObject roomJson = DungeonRooms.roomsJson.get(RoomDetection.roomName).getAsJsonObject();
/*  51 */     if (roomJson.get("dsg").getAsString().equals("null") && roomJson.get("sbp") == null) {
/*  52 */       player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "Dungeon Rooms: There are no channels/images for this room."));
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  57 */     switch (type) {
/*     */       case "gui":
/*  59 */         mc.func_152344_a(() -> mc.func_147108_a((GuiScreen)new LinkGUI()));
/*     */         break;
/*     */       case "dsg":
/*  62 */         openDiscord("client");
/*     */         break;
/*     */       case "sbp":
/*  65 */         if (DungeonRooms.usingSBPSecrets) {
/*  66 */           openSBPSecrets(); break;
/*     */         } 
/*  68 */         sbpURL = "https://discord.gg/2UjaFqfPwJ";
/*  69 */         sbp = new ChatComponentText(EnumChatFormatting.YELLOW + "" + EnumChatFormatting.UNDERLINE + sbpURL);
/*  70 */         sbp.func_150255_a(sbp.func_150256_b().func_150241_a(new ClickEvent(ClickEvent.Action.OPEN_URL, sbpURL)));
/*  71 */         player.func_145747_a((new ChatComponentText(EnumChatFormatting.RED + "Dungeon Rooms: You need the SkyblockPersonalized (SBP) Mod for this feature, get it from "))
/*  72 */             .func_150257_a((IChatComponent)sbp));
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void openDiscord(String type) {
/*  80 */     Minecraft mc = Minecraft.func_71410_x();
/*  81 */     EntityPlayerSP player = mc.field_71439_g;
/*     */     
/*  83 */     if (RoomDetection.roomName.equals("undefined") || DungeonRooms.roomsJson.get(RoomDetection.roomName) == null) {
/*  84 */       player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "Dungeon Rooms: You do not appear to be in a detected Dungeon room right now."));
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  89 */     JsonObject roomJson = DungeonRooms.roomsJson.get(RoomDetection.roomName).getAsJsonObject();
/*  90 */     if (roomJson.get("dsg").getAsString().equals("null")) {
/*  91 */       player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "Dungeon Rooms: There is no DSG channel for this room."));
/*     */       
/*     */       return;
/*     */     } 
/*     */     try {
/*  96 */       if (type.equals("client")) {
/*  97 */         player.func_145747_a((IChatComponent)new ChatComponentText("Dungeon Rooms: Opening DSG Discord in Client..."));
/*  98 */         Desktop.getDesktop().browse(new URI("discord://" + roomJson.get("dsg").getAsString()));
/*     */       } else {
/* 100 */         player.func_145747_a((IChatComponent)new ChatComponentText("Dungeon Rooms: Opening DSG Discord in Browser..."));
/* 101 */         Desktop.getDesktop().browse(new URI("https://discord.com" + roomJson.get("dsg").getAsString()));
/*     */       } 
/* 103 */     } catch (IOException|java.net.URISyntaxException e) {
/* 104 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void openSBPSecrets() {
/* 109 */     Minecraft mc = Minecraft.func_71410_x();
/* 110 */     EntityPlayerSP player = mc.field_71439_g;
/*     */     
/* 112 */     if (RoomDetection.roomName.equals("undefined") || DungeonRooms.roomsJson.get(RoomDetection.roomName) == null) {
/* 113 */       player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "Dungeon Rooms: You do not appear to be in a detected Dungeon room right now."));
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 118 */     JsonObject roomJson = DungeonRooms.roomsJson.get(RoomDetection.roomName).getAsJsonObject();
/* 119 */     if (roomJson.get("sbp") == null) {
/* 120 */       player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "Dungeon Rooms: There are no SBP images for this room."));
/*     */       
/*     */       return;
/*     */     } 
/* 124 */     String name = roomJson.get("sbp").getAsString();
/*     */     
/* 126 */     String category = roomJson.get("category").getAsString();
/* 127 */     switch (category) {
/*     */       case "Puzzle":
/*     */       case "Trap":
/* 130 */         category = "puzzles";
/*     */         break;
/*     */       case "L-shape":
/* 133 */         category = "L";
/*     */         break;
/*     */     } 
/* 136 */     ClientCommandHandler.instance.func_71556_a((ICommandSender)FMLClientHandler.instance().getClientPlayerEntity(), "/secretoverride " + category + " " + name);
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\io\github\quantizr\dungeonrooms\handlers\OpenLink.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */