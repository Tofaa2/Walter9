/*     */ package me.Danker.commands;
/*     */ 
/*     */ import com.google.gson.JsonObject;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import me.Danker.DankersSkyblockMod;
/*     */ import me.Danker.handlers.APIHandler;
/*     */ import me.Danker.handlers.ConfigHandler;
/*     */ import me.Danker.utils.Utils;
/*     */ import net.minecraft.command.CommandBase;
/*     */ import net.minecraft.command.CommandException;
/*     */ import net.minecraft.command.ICommandSender;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ 
/*     */ public class SkyblockPlayersCommand
/*     */   extends CommandBase
/*     */ {
/*     */   public String func_71517_b() {
/*  24 */     return "sbplayers";
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> func_71514_a() {
/*  29 */     return Collections.singletonList("skyblockplayers");
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_71518_a(ICommandSender arg0) {
/*  34 */     return "/" + func_71517_b();
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_82362_a() {
/*  39 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_71515_b(ICommandSender arg0, String[] arg1) throws CommandException {
/*  45 */     (new Thread(() -> {
/*     */           EntityPlayer player = (EntityPlayer)arg0;
/*     */           
/*     */           String key = ConfigHandler.getString("api", "APIKey");
/*     */           
/*     */           if (key.equals("")) {
/*     */             player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "API key not set. Use /setkey."));
/*     */           }
/*     */           
/*     */           String playersURL = "https://api.hypixel.net/gameCounts?key=" + key;
/*     */           
/*     */           System.out.println("Fetching player count...");
/*     */           
/*     */           JsonObject playersResponse = APIHandler.getResponse(playersURL);
/*     */           
/*     */           if (!playersResponse.get("success").getAsBoolean()) {
/*     */             String reason = playersResponse.get("cause").getAsString();
/*     */             
/*     */             player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Failed with reason: " + reason));
/*     */             
/*     */             return;
/*     */           } 
/*     */           
/*     */           int totalPlayers = playersResponse.get("playerCount").getAsInt();
/*     */           
/*     */           int skyblockTotalPlayers = 0;
/*     */           
/*     */           int privateIsland = 0;
/*     */           
/*     */           int hub = 0;
/*     */           
/*     */           int barn = 0;
/*     */           
/*     */           int mushroomDesert = 0;
/*     */           
/*     */           int park = 0;
/*     */           
/*     */           int goldMine = 0;
/*     */           
/*     */           int deepCaverns = 0;
/*     */           
/*     */           int spidersDen = 0;
/*     */           
/*     */           int blazingFortress = 0;
/*     */           
/*     */           int end = 0;
/*     */           
/*     */           int dungeonsHub = 0;
/*     */           
/*     */           int dungeons = 0;
/*     */           
/*     */           int darkAuction = 0;
/*     */           int jerry = 0;
/*     */           if (playersResponse.get("games").getAsJsonObject().get("SKYBLOCK").getAsJsonObject().has("modes")) {
/*     */             JsonObject skyblockPlayers = playersResponse.get("games").getAsJsonObject().get("SKYBLOCK").getAsJsonObject().get("modes").getAsJsonObject();
/*     */             skyblockTotalPlayers = playersResponse.get("games").getAsJsonObject().get("SKYBLOCK").getAsJsonObject().get("players").getAsInt();
/*     */             if (skyblockPlayers.has("dynamic")) {
/*     */               privateIsland = skyblockPlayers.get("dynamic").getAsInt();
/*     */             }
/*     */             if (skyblockPlayers.has("hub")) {
/*     */               hub = skyblockPlayers.get("hub").getAsInt();
/*     */             }
/*     */             if (skyblockPlayers.has("farming_1")) {
/*     */               barn = skyblockPlayers.get("farming_1").getAsInt();
/*     */             }
/*     */             if (skyblockPlayers.has("farming_2")) {
/*     */               mushroomDesert = skyblockPlayers.get("farming_2").getAsInt();
/*     */             }
/*     */             if (skyblockPlayers.has("foraging_1")) {
/*     */               park = skyblockPlayers.get("foraging_1").getAsInt();
/*     */             }
/*     */             if (skyblockPlayers.has("mining_1")) {
/*     */               goldMine = skyblockPlayers.get("mining_1").getAsInt();
/*     */             }
/*     */             if (skyblockPlayers.has("mining_2")) {
/*     */               deepCaverns = skyblockPlayers.get("mining_2").getAsInt();
/*     */             }
/*     */             if (skyblockPlayers.has("combat_1")) {
/*     */               spidersDen = skyblockPlayers.get("combat_1").getAsInt();
/*     */             }
/*     */             if (skyblockPlayers.has("combat_2")) {
/*     */               blazingFortress = skyblockPlayers.get("combat_2").getAsInt();
/*     */             }
/*     */             if (skyblockPlayers.has("combat_3")) {
/*     */               end = skyblockPlayers.get("combat_3").getAsInt();
/*     */             }
/*     */             if (skyblockPlayers.has("dungeon_hub")) {
/*     */               dungeonsHub = skyblockPlayers.get("dungeon_hub").getAsInt();
/*     */             }
/*     */             if (skyblockPlayers.has("dungeon")) {
/*     */               dungeons = skyblockPlayers.get("dungeon").getAsInt();
/*     */             }
/*     */             if (skyblockPlayers.has("dark_auction")) {
/*     */               darkAuction = skyblockPlayers.get("dark_auction").getAsInt();
/*     */             }
/*     */             if (skyblockPlayers.has("winter")) {
/*     */               jerry = skyblockPlayers.get("winter").getAsInt();
/*     */             }
/*     */           } 
/*     */           NumberFormat nf = NumberFormat.getIntegerInstance(Locale.US);
/*     */           player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.DELIMITER_COLOUR + "" + EnumChatFormatting.BOLD + "-------------------\n" + DankersSkyblockMod.TYPE_COLOUR + " Hypixel: " + DankersSkyblockMod.VALUE_COLOUR + nf.format(totalPlayers) + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Skyblock: " + DankersSkyblockMod.VALUE_COLOUR + nf.format(skyblockTotalPlayers) + " / " + Utils.getPercentage(skyblockTotalPlayers, totalPlayers) + "%\n" + DankersSkyblockMod.TYPE_COLOUR + " Private Island: " + DankersSkyblockMod.VALUE_COLOUR + nf.format(privateIsland) + " / " + Utils.getPercentage(privateIsland, skyblockTotalPlayers) + "%\n" + DankersSkyblockMod.TYPE_COLOUR + " Hub: " + DankersSkyblockMod.VALUE_COLOUR + nf.format(hub) + " / " + Utils.getPercentage(hub, skyblockTotalPlayers) + "%\n" + DankersSkyblockMod.TYPE_COLOUR + " Barn: " + DankersSkyblockMod.VALUE_COLOUR + nf.format(barn) + " / " + Utils.getPercentage(barn, skyblockTotalPlayers) + "%\n" + DankersSkyblockMod.TYPE_COLOUR + " Mushroom Desert: " + DankersSkyblockMod.VALUE_COLOUR + nf.format(mushroomDesert) + " / " + Utils.getPercentage(mushroomDesert, skyblockTotalPlayers) + "%\n" + DankersSkyblockMod.TYPE_COLOUR + " Park: " + DankersSkyblockMod.VALUE_COLOUR + nf.format(park) + " / " + Utils.getPercentage(park, skyblockTotalPlayers) + "%\n" + DankersSkyblockMod.TYPE_COLOUR + " Gold Mine: " + DankersSkyblockMod.VALUE_COLOUR + nf.format(goldMine) + " / " + Utils.getPercentage(goldMine, skyblockTotalPlayers) + "%\n" + DankersSkyblockMod.TYPE_COLOUR + " Deep Caverns: " + DankersSkyblockMod.VALUE_COLOUR + nf.format(deepCaverns) + " / " + Utils.getPercentage(deepCaverns, skyblockTotalPlayers) + "%\n" + DankersSkyblockMod.TYPE_COLOUR + " Spider's Den: " + DankersSkyblockMod.VALUE_COLOUR + nf.format(spidersDen) + " / " + Utils.getPercentage(spidersDen, skyblockTotalPlayers) + "%\n" + DankersSkyblockMod.TYPE_COLOUR + " Blazing Fortress: " + DankersSkyblockMod.VALUE_COLOUR + nf.format(blazingFortress) + " / " + Utils.getPercentage(blazingFortress, skyblockTotalPlayers) + "%\n" + DankersSkyblockMod.TYPE_COLOUR + " The End: " + DankersSkyblockMod.VALUE_COLOUR + nf.format(end) + " / " + Utils.getPercentage(end, skyblockTotalPlayers) + "%\n" + DankersSkyblockMod.TYPE_COLOUR + " Dungeons Hub: " + DankersSkyblockMod.VALUE_COLOUR + nf.format(dungeonsHub) + " / " + Utils.getPercentage(dungeonsHub, skyblockTotalPlayers) + "%\n" + DankersSkyblockMod.TYPE_COLOUR + " Dungeons: " + DankersSkyblockMod.VALUE_COLOUR + nf.format(dungeons) + " / " + Utils.getPercentage(dungeons, skyblockTotalPlayers) + "%\n" + DankersSkyblockMod.TYPE_COLOUR + " Dark Auction: " + DankersSkyblockMod.VALUE_COLOUR + nf.format(darkAuction) + " / " + Utils.getPercentage(darkAuction, skyblockTotalPlayers) + "%\n" + DankersSkyblockMod.TYPE_COLOUR + " Jerry's Workshop: " + DankersSkyblockMod.VALUE_COLOUR + nf.format(jerry) + " / " + Utils.getPercentage(jerry, skyblockTotalPlayers) + "%\n" + DankersSkyblockMod.DELIMITER_COLOUR + EnumChatFormatting.BOLD + " -------------------"));
/* 146 */         })).start();
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\me\Danker\commands\SkyblockPlayersCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */