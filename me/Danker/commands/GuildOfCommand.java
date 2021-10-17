/*     */ package me.Danker.commands;
/*     */ 
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import java.util.List;
/*     */ import me.Danker.DankersSkyblockMod;
/*     */ import me.Danker.handlers.APIHandler;
/*     */ import me.Danker.handlers.ConfigHandler;
/*     */ import me.Danker.utils.Utils;
/*     */ import net.minecraft.command.CommandBase;
/*     */ import net.minecraft.command.CommandException;
/*     */ import net.minecraft.command.ICommandSender;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ 
/*     */ public class GuildOfCommand
/*     */   extends CommandBase
/*     */ {
/*     */   public String func_71517_b() {
/*  24 */     return "guildof";
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_71518_a(ICommandSender arg0) {
/*  29 */     return "/" + func_71517_b() + " [name]";
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_82362_a() {
/*  34 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> func_180525_a(ICommandSender sender, String[] args, BlockPos pos) {
/*  39 */     if (args.length == 1) {
/*  40 */       return Utils.getMatchingPlayers(args[0]);
/*     */     }
/*  42 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_71515_b(ICommandSender arg0, String[] arg1) throws CommandException {
/*  48 */     (new Thread(() -> {
/*     */           String username;
/*     */           
/*     */           String uuid;
/*     */           
/*     */           EntityPlayer player = (EntityPlayer)arg0;
/*     */           
/*     */           String key = ConfigHandler.getString("api", "APIKey");
/*     */           
/*     */           if (key.equals("")) {
/*     */             player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "API key not set. Use /setkey."));
/*     */           }
/*     */           
/*     */           if (arg1.length == 0) {
/*     */             username = player.func_70005_c_();
/*     */             
/*     */             uuid = player.func_110124_au().toString().replaceAll("[\\-]", "");
/*     */             
/*     */             player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Checking guild of " + DankersSkyblockMod.SECONDARY_COLOUR + username));
/*     */           } else {
/*     */             username = arg1[0];
/*     */             
/*     */             player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Checking guild of " + DankersSkyblockMod.SECONDARY_COLOUR + username));
/*     */             
/*     */             uuid = APIHandler.getUUID(username);
/*     */           } 
/*     */           
/*     */           System.out.println("Fetching guild...");
/*     */           
/*     */           String guildURL = "https://api.hypixel.net/guild?player=" + uuid + "&key=" + key;
/*     */           
/*     */           JsonObject guildResponse = APIHandler.getResponse(guildURL);
/*     */           
/*     */           if (!guildResponse.get("success").getAsBoolean()) {
/*     */             String reason = guildResponse.get("cause").getAsString();
/*     */             
/*     */             player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Failed with reason: " + reason));
/*     */             
/*     */             return;
/*     */           } 
/*     */           
/*     */           System.out.println("Fetching guild stats and members...");
/*     */           
/*     */           String guildName = "N/A";
/*     */           
/*     */           String guildMaster = "N/A";
/*     */           
/*     */           int players = 0;
/*     */           
/*     */           if (!guildResponse.get("guild").isJsonNull()) {
/*     */             guildName = guildResponse.get("guild").getAsJsonObject().get("name").getAsString();
/*     */             JsonArray guildMembers = guildResponse.get("guild").getAsJsonObject().get("members").getAsJsonArray();
/*     */             players = guildMembers.size();
/*     */             for (JsonElement member : guildMembers) {
/*     */               JsonObject memberObject = member.getAsJsonObject();
/*     */               String memberRank = memberObject.get("rank").getAsString();
/*     */               if (memberRank.equals("GUILDMASTER") || memberRank.equals("Guild Master")) {
/*     */                 String gmUUID = memberObject.get("uuid").getAsString();
/*     */                 String gmNameURL = "https://api.mojang.com/user/profiles/" + gmUUID + "/names";
/*     */                 JsonArray gmNameResponse = APIHandler.getArrayResponse(gmNameURL);
/*     */                 guildMaster = gmNameResponse.get(gmNameResponse.size() - 1).getAsJsonObject().get("name").getAsString();
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.DELIMITER_COLOUR + "" + EnumChatFormatting.BOLD + "-------------------\n" + EnumChatFormatting.AQUA + " " + username + "'s Guild:\n" + DankersSkyblockMod.TYPE_COLOUR + " Guild: " + DankersSkyblockMod.VALUE_COLOUR + EnumChatFormatting.BOLD + guildName + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Guildmaster: " + DankersSkyblockMod.VALUE_COLOUR + EnumChatFormatting.BOLD + guildMaster + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Members: " + DankersSkyblockMod.VALUE_COLOUR + EnumChatFormatting.BOLD + players + "\n" + DankersSkyblockMod.DELIMITER_COLOUR + " " + EnumChatFormatting.BOLD + "-------------------"));
/* 114 */         })).start();
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\me\Danker\commands\GuildOfCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */