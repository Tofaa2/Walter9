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
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ 
/*     */ public class BankCommand
/*     */   extends CommandBase
/*     */ {
/*     */   public String func_71517_b() {
/*  25 */     return "bank";
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> func_71514_a() {
/*  30 */     return Collections.singletonList("purse");
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_71518_a(ICommandSender arg0) {
/*  35 */     return "/" + func_71517_b() + " [name]";
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_82362_a() {
/*  40 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> func_180525_a(ICommandSender sender, String[] args, BlockPos pos) {
/*  45 */     if (args.length == 1) {
/*  46 */       return Utils.getMatchingPlayers(args[0]);
/*     */     }
/*  48 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_71515_b(ICommandSender arg0, String[] arg1) throws CommandException {
/*  54 */     (new Thread(() -> {
/*     */           String username;
/*     */           
/*     */           String uuid;
/*     */           
/*     */           EntityPlayer player = (EntityPlayer)arg0;
/*     */           
/*     */           String key = ConfigHandler.getString("api", "APIKey");
/*     */           
/*     */           if (key.equals("")) {
/*     */             player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "API key not set. Use /setkey."));
/*     */           }
/*     */           
/*     */           if (arg1.length == 0) {
/*     */             username = player.func_70005_c_();
/*     */             
/*     */             uuid = player.func_110124_au().toString().replaceAll("[\\-]", "");
/*     */             
/*     */             player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Checking coins of " + DankersSkyblockMod.SECONDARY_COLOUR + username));
/*     */           } else {
/*     */             username = arg1[0];
/*     */             
/*     */             player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Checking coins of " + DankersSkyblockMod.SECONDARY_COLOUR + username));
/*     */             
/*     */             uuid = APIHandler.getUUID(username);
/*     */           } 
/*     */           
/*     */           String latestProfile = APIHandler.getLatestProfileID(uuid, key);
/*     */           
/*     */           if (latestProfile == null) {
/*     */             return;
/*     */           }
/*     */           
/*     */           String profileURL = "https://api.hypixel.net/skyblock/profile?profile=" + latestProfile + "&key=" + key;
/*     */           
/*     */           System.out.println("Fetching profile...");
/*     */           
/*     */           JsonObject profileResponse = APIHandler.getResponse(profileURL);
/*     */           
/*     */           if (!profileResponse.get("success").getAsBoolean()) {
/*     */             String reason = profileResponse.get("cause").getAsString();
/*     */             
/*     */             player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "Failed with reason: " + reason));
/*     */             
/*     */             return;
/*     */           } 
/*     */           
/*     */           System.out.println("Fetching bank + purse coins...");
/*     */           double purseCoins = profileResponse.get("profile").getAsJsonObject().get("members").getAsJsonObject().get(uuid).getAsJsonObject().get("coin_purse").getAsDouble();
/*     */           purseCoins = Math.floor(purseCoins * 100.0D) / 100.0D;
/*     */           NumberFormat nf = NumberFormat.getIntegerInstance(Locale.US);
/*     */           if (profileResponse.get("profile").getAsJsonObject().has("banking")) {
/*     */             double bankCoins = profileResponse.get("profile").getAsJsonObject().get("banking").getAsJsonObject().get("balance").getAsDouble();
/*     */             bankCoins = Math.floor(bankCoins * 100.0D) / 100.0D;
/*     */             player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.DELIMITER_COLOUR + "" + EnumChatFormatting.BOLD + "-------------------\n" + EnumChatFormatting.AQUA + " " + username + "'s Coins:\n" + DankersSkyblockMod.TYPE_COLOUR + " Bank: " + EnumChatFormatting.GOLD + nf.format(bankCoins) + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Purse: " + EnumChatFormatting.GOLD + nf.format(purseCoins) + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Total: " + EnumChatFormatting.GOLD + nf.format(bankCoins + purseCoins) + "\n" + DankersSkyblockMod.DELIMITER_COLOUR + " " + EnumChatFormatting.BOLD + "-------------------"));
/*     */           } else {
/*     */             player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.DELIMITER_COLOUR + "" + EnumChatFormatting.BOLD + "-------------------\n" + EnumChatFormatting.AQUA + " " + username + "'s Coins:\n" + DankersSkyblockMod.TYPE_COLOUR + " Bank: " + EnumChatFormatting.RED + "Bank API disabled.\n" + DankersSkyblockMod.TYPE_COLOUR + " Purse: " + EnumChatFormatting.GOLD + nf.format(purseCoins) + "\n" + DankersSkyblockMod.DELIMITER_COLOUR + " " + EnumChatFormatting.BOLD + "-------------------"));
/*     */           } 
/* 112 */         })).start();
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\me\Danker\commands\BankCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */