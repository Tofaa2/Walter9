/*     */ package me.Danker.commands;
/*     */ 
/*     */ import com.google.gson.JsonObject;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Base64;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import me.Danker.DankersSkyblockMod;
/*     */ import me.Danker.handlers.APIHandler;
/*     */ import me.Danker.handlers.ConfigHandler;
/*     */ import me.Danker.utils.Utils;
/*     */ import net.minecraft.command.CommandBase;
/*     */ import net.minecraft.command.CommandException;
/*     */ import net.minecraft.command.ICommandSender;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.CompressedStreamTools;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ 
/*     */ public class ArmourCommand
/*     */   extends CommandBase
/*     */ {
/*     */   public String func_71517_b() {
/*  30 */     return "armor";
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> func_71514_a() {
/*  35 */     return Collections.singletonList("armour");
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_71518_a(ICommandSender arg0) {
/*  40 */     return "/" + func_71517_b() + " [name]";
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_82362_a() {
/*  45 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> func_180525_a(ICommandSender sender, String[] args, BlockPos pos) {
/*  50 */     if (args.length == 1) {
/*  51 */       return Utils.getMatchingPlayers(args[0]);
/*     */     }
/*  53 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_71515_b(ICommandSender arg0, String[] arg1) throws CommandException {
/*  59 */     (new Thread(() -> {
/*     */           String username;
/*     */           String uuid;
/*     */           EntityPlayer player = (EntityPlayer)arg0;
/*     */           String key = ConfigHandler.getString("api", "APIKey");
/*     */           if (key.equals("")) {
/*     */             player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "API key not set. Use /setkey."));
/*     */           }
/*     */           if (arg1.length == 0) {
/*     */             username = player.func_70005_c_();
/*     */             uuid = player.func_110124_au().toString().replaceAll("[\\-]", "");
/*     */             player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Checking armour of " + DankersSkyblockMod.SECONDARY_COLOUR + username));
/*     */           } else {
/*     */             username = arg1[0];
/*     */             player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Checking armour of " + DankersSkyblockMod.SECONDARY_COLOUR + username));
/*     */             uuid = APIHandler.getUUID(username);
/*     */           } 
/*     */           String latestProfile = APIHandler.getLatestProfileID(uuid, key);
/*     */           if (latestProfile == null) {
/*     */             return;
/*     */           }
/*     */           String profileURL = "https://api.hypixel.net/skyblock/profile?profile=" + latestProfile + "&key=" + key;
/*     */           System.out.println("Fetching profile...");
/*     */           JsonObject profileResponse = APIHandler.getResponse(profileURL);
/*     */           if (!profileResponse.get("success").getAsBoolean()) {
/*     */             String reason = profileResponse.get("cause").getAsString();
/*     */             player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Failed with reason: " + reason));
/*     */             return;
/*     */           } 
/*     */           String armourBase64 = profileResponse.get("profile").getAsJsonObject().get("members").getAsJsonObject().get(uuid).getAsJsonObject().get("inv_armor").getAsJsonObject().get("data").getAsString();
/*     */           InputStream armourStream = new ByteArrayInputStream(Base64.getDecoder().decode(armourBase64));
/*     */           try {
/*     */             NBTTagCompound armour = CompressedStreamTools.func_74796_a(armourStream);
/*     */             NBTTagList armourList = armour.func_150295_c("i", 10);
/*     */             String helmet = EnumChatFormatting.RED + "None";
/*     */             String chest = EnumChatFormatting.RED + "None";
/*     */             String legs = EnumChatFormatting.RED + "None";
/*     */             String boots = EnumChatFormatting.RED + "None";
/*     */             for (int i = 0; i < armourList.func_74745_c(); i++) {
/*     */               NBTTagCompound armourPiece = armourList.func_150305_b(i);
/*     */               if (!armourPiece.func_82582_d()) {
/*     */                 String armourPieceName = armourPiece.func_74775_l("tag").func_74775_l("display").func_74779_i("Name");
/*     */                 switch (i) {
/*     */                   case 0:
/*     */                     boots = armourPieceName;
/*     */                     break;
/*     */ 
/*     */ 
/*     */ 
/*     */                   
/*     */                   case 1:
/*     */                     legs = armourPieceName;
/*     */                     break;
/*     */ 
/*     */ 
/*     */ 
/*     */                   
/*     */                   case 2:
/*     */                     chest = armourPieceName;
/*     */                     break;
/*     */ 
/*     */ 
/*     */ 
/*     */                   
/*     */                   case 3:
/*     */                     helmet = armourPieceName;
/*     */                     break;
/*     */ 
/*     */ 
/*     */                   
/*     */                   default:
/*     */                     System.err.println("An error has occurred.");
/*     */                     break;
/*     */                 } 
/*     */ 
/*     */ 
/*     */               
/*     */               } 
/*     */             } 
/*     */             armourStream.close();
/*     */             player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.DELIMITER_COLOUR + "" + EnumChatFormatting.BOLD + "-------------------\n" + EnumChatFormatting.AQUA + " " + username + "'s Armour:\n" + DankersSkyblockMod.TYPE_COLOUR + " Helmet:      " + helmet + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Chestplate: " + chest + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Leggings:   " + legs + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Boots:       " + boots + "\n" + DankersSkyblockMod.DELIMITER_COLOUR + " " + EnumChatFormatting.BOLD + "-------------------"));
/* 140 */           } catch (IOException ex) {
/*     */             player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "An error has occurred while reading inventory data. See logs for more info."));
/*     */             ex.printStackTrace();
/*     */           } 
/* 144 */         })).start();
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\me\Danker\commands\ArmourCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */