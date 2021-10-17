/*     */ package me.Danker.commands;
/*     */ 
/*     */ import com.google.gson.JsonObject;
/*     */ import java.text.NumberFormat;
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
/*     */ public class SlayerCommand
/*     */   extends CommandBase
/*     */ {
/*     */   public String func_71517_b() {
/*  24 */     return "slayer";
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
/*     */             player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Checking slayer of " + DankersSkyblockMod.SECONDARY_COLOUR + username));
/*     */           } else {
/*     */             username = arg1[0];
/*     */             
/*     */             player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Checking slayer of " + DankersSkyblockMod.SECONDARY_COLOUR + username));
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
/*     */             player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Failed with reason: " + reason));
/*     */             return;
/*     */           } 
/*     */           System.out.println("Fetching slayer stats...");
/*     */           JsonObject slayersObject = profileResponse.get("profile").getAsJsonObject().get("members").getAsJsonObject().get(uuid).getAsJsonObject().get("slayer_bosses").getAsJsonObject();
/*     */           int zombieXP = 0;
/*     */           if (slayersObject.get("zombie").getAsJsonObject().has("xp")) {
/*     */             zombieXP = slayersObject.get("zombie").getAsJsonObject().get("xp").getAsInt();
/*     */           }
/*     */           int spiderXP = 0;
/*     */           if (slayersObject.get("spider").getAsJsonObject().has("xp")) {
/*     */             spiderXP = slayersObject.get("spider").getAsJsonObject().get("xp").getAsInt();
/*     */           }
/*     */           int wolfXP = 0;
/*     */           if (slayersObject.get("wolf").getAsJsonObject().has("xp")) {
/*     */             wolfXP = slayersObject.get("wolf").getAsJsonObject().get("xp").getAsInt();
/*     */           }
/*     */           NumberFormat nf = NumberFormat.getIntegerInstance(Locale.US);
/*     */           player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.DELIMITER_COLOUR + "" + EnumChatFormatting.BOLD + "-------------------\n" + EnumChatFormatting.AQUA + " " + username + "'s Total XP: " + EnumChatFormatting.GOLD + EnumChatFormatting.BOLD + nf.format((zombieXP + spiderXP + wolfXP)) + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Zombie XP: " + DankersSkyblockMod.VALUE_COLOUR + EnumChatFormatting.BOLD + nf.format(zombieXP) + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Spider XP: " + DankersSkyblockMod.VALUE_COLOUR + EnumChatFormatting.BOLD + nf.format(spiderXP) + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Wolf XP: " + DankersSkyblockMod.VALUE_COLOUR + EnumChatFormatting.BOLD + nf.format(wolfXP) + "\n" + DankersSkyblockMod.DELIMITER_COLOUR + " " + EnumChatFormatting.BOLD + "-------------------"));
/* 109 */         })).start();
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\me\Danker\commands\SlayerCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */