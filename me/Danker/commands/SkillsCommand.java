/*     */ package me.Danker.commands;
/*     */ 
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
/*     */ public class SkillsCommand
/*     */   extends CommandBase
/*     */ {
/*     */   public String func_71517_b() {
/*  22 */     return "skills";
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_71518_a(ICommandSender arg0) {
/*  27 */     return "/" + func_71517_b() + " [name]";
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_82362_a() {
/*  32 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> func_180525_a(ICommandSender sender, String[] args, BlockPos pos) {
/*  37 */     if (args.length == 1) {
/*  38 */       return Utils.getMatchingPlayers(args[0]);
/*     */     }
/*  40 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_71515_b(ICommandSender arg0, String[] arg1) throws CommandException {
/*  46 */     (new Thread(() -> {
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
/*     */             player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Checking skills of " + DankersSkyblockMod.SECONDARY_COLOUR + username));
/*     */           } else {
/*     */             username = arg1[0];
/*     */             
/*     */             player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Checking skills of " + DankersSkyblockMod.SECONDARY_COLOUR + username));
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
/*     */             
/*     */             return;
/*     */           } 
/*     */           
/*     */           System.out.println("Fetching skills...");
/*     */           
/*     */           JsonObject userObject = profileResponse.get("profile").getAsJsonObject().get("members").getAsJsonObject().get(uuid).getAsJsonObject();
/*     */           
/*     */           double farmingLevel = 0.0D;
/*     */           
/*     */           double miningLevel = 0.0D;
/*     */           
/*     */           double combatLevel = 0.0D;
/*     */           
/*     */           double foragingLevel = 0.0D;
/*     */           
/*     */           double fishingLevel = 0.0D;
/*     */           
/*     */           double enchantingLevel = 0.0D;
/*     */           
/*     */           double alchemyLevel = 0.0D;
/*     */           double tamingLevel = 0.0D;
/*     */           if (userObject.has("experience_skill_farming") || userObject.has("experience_skill_mining") || userObject.has("experience_skill_combat") || userObject.has("experience_skill_foraging") || userObject.has("experience_skill_fishing") || userObject.has("experience_skill_enchanting") || userObject.has("experience_skill_alchemy")) {
/*     */             if (userObject.has("experience_skill_farming")) {
/*     */               farmingLevel = Utils.xpToSkillLevel(userObject.get("experience_skill_farming").getAsDouble(), 60);
/*     */               farmingLevel = Math.round(farmingLevel * 100.0D) / 100.0D;
/*     */             } 
/*     */             if (userObject.has("experience_skill_mining")) {
/*     */               miningLevel = Utils.xpToSkillLevel(userObject.get("experience_skill_mining").getAsDouble(), 50);
/*     */               miningLevel = Math.round(miningLevel * 100.0D) / 100.0D;
/*     */             } 
/*     */             if (userObject.has("experience_skill_combat")) {
/*     */               combatLevel = Utils.xpToSkillLevel(userObject.get("experience_skill_combat").getAsDouble(), 50);
/*     */               combatLevel = Math.round(combatLevel * 100.0D) / 100.0D;
/*     */             } 
/*     */             if (userObject.has("experience_skill_foraging")) {
/*     */               foragingLevel = Utils.xpToSkillLevel(userObject.get("experience_skill_foraging").getAsDouble(), 50);
/*     */               foragingLevel = Math.round(foragingLevel * 100.0D) / 100.0D;
/*     */             } 
/*     */             if (userObject.has("experience_skill_fishing")) {
/*     */               fishingLevel = Utils.xpToSkillLevel(userObject.get("experience_skill_fishing").getAsDouble(), 50);
/*     */               fishingLevel = Math.round(fishingLevel * 100.0D) / 100.0D;
/*     */             } 
/*     */             if (userObject.has("experience_skill_enchanting")) {
/*     */               enchantingLevel = Utils.xpToSkillLevel(userObject.get("experience_skill_enchanting").getAsDouble(), 60);
/*     */               enchantingLevel = Math.round(enchantingLevel * 100.0D) / 100.0D;
/*     */             } 
/*     */             if (userObject.has("experience_skill_alchemy")) {
/*     */               alchemyLevel = Utils.xpToSkillLevel(userObject.get("experience_skill_alchemy").getAsDouble(), 50);
/*     */               alchemyLevel = Math.round(alchemyLevel * 100.0D) / 100.0D;
/*     */             } 
/*     */             if (userObject.has("experience_skill_taming")) {
/*     */               tamingLevel = Utils.xpToSkillLevel(userObject.get("experience_skill_taming").getAsDouble(), 50);
/*     */               tamingLevel = Math.round(tamingLevel * 100.0D) / 100.0D;
/*     */             } 
/*     */           } else {
/*     */             String playerURL = "https://api.hypixel.net/player?uuid=" + uuid + "&key=" + key;
/*     */             System.out.println("Fetching skills from achievement API");
/*     */             JsonObject playerObject = APIHandler.getResponse(playerURL);
/*     */             if (!playerObject.get("success").getAsBoolean()) {
/*     */               String reason = profileResponse.get("cause").getAsString();
/*     */               player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Failed with reason: " + reason));
/*     */               return;
/*     */             } 
/*     */             JsonObject achievementObject = playerObject.get("player").getAsJsonObject().get("achievements").getAsJsonObject();
/*     */             if (achievementObject.has("skyblock_harvester")) {
/*     */               farmingLevel = achievementObject.get("skyblock_harvester").getAsInt();
/*     */             }
/*     */             if (achievementObject.has("skyblock_excavator")) {
/*     */               miningLevel = Math.min(achievementObject.get("skyblock_excavator").getAsInt(), 50);
/*     */             }
/*     */             if (achievementObject.has("skyblock_combat")) {
/*     */               combatLevel = Math.min(achievementObject.get("skyblock_combat").getAsInt(), 50);
/*     */             }
/*     */             if (achievementObject.has("skyblock_gatherer")) {
/*     */               foragingLevel = Math.min(achievementObject.get("skyblock_gatherer").getAsInt(), 50);
/*     */             }
/*     */             if (achievementObject.has("skyblock_angler")) {
/*     */               fishingLevel = Math.min(achievementObject.get("skyblock_angler").getAsInt(), 50);
/*     */             }
/*     */             if (achievementObject.has("skyblock_augmentation")) {
/*     */               enchantingLevel = achievementObject.get("skyblock_augmentation").getAsInt();
/*     */             }
/*     */             if (achievementObject.has("skyblock_concoctor")) {
/*     */               alchemyLevel = Math.min(achievementObject.get("skyblock_concoctor").getAsInt(), 50);
/*     */             }
/*     */             if (achievementObject.has("skyblock_domesticator")) {
/*     */               tamingLevel = Math.min(achievementObject.get("skyblock_domesticator").getAsInt(), 50);
/*     */             }
/*     */           } 
/*     */           double skillAvg = (farmingLevel + miningLevel + combatLevel + foragingLevel + fishingLevel + enchantingLevel + alchemyLevel + tamingLevel) / 8.0D;
/*     */           skillAvg = Math.round(skillAvg * 100.0D) / 100.0D;
/*     */           double trueAvg = (Math.floor(farmingLevel) + Math.floor(miningLevel) + Math.floor(combatLevel) + Math.floor(foragingLevel) + Math.floor(fishingLevel) + Math.floor(enchantingLevel) + Math.floor(alchemyLevel) + Math.floor(tamingLevel)) / 8.0D;
/*     */           player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.DELIMITER_COLOUR + "" + EnumChatFormatting.BOLD + "-------------------\n" + EnumChatFormatting.AQUA + " " + username + "'s Skills:\n" + DankersSkyblockMod.TYPE_COLOUR + " Farming: " + DankersSkyblockMod.VALUE_COLOUR + EnumChatFormatting.BOLD + farmingLevel + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Mining: " + DankersSkyblockMod.VALUE_COLOUR + EnumChatFormatting.BOLD + miningLevel + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Combat: " + DankersSkyblockMod.VALUE_COLOUR + EnumChatFormatting.BOLD + combatLevel + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Foraging: " + DankersSkyblockMod.VALUE_COLOUR + EnumChatFormatting.BOLD + foragingLevel + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Fishing: " + DankersSkyblockMod.VALUE_COLOUR + EnumChatFormatting.BOLD + fishingLevel + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Enchanting: " + DankersSkyblockMod.VALUE_COLOUR + EnumChatFormatting.BOLD + enchantingLevel + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Alchemy: " + DankersSkyblockMod.VALUE_COLOUR + EnumChatFormatting.BOLD + alchemyLevel + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Taming: " + DankersSkyblockMod.VALUE_COLOUR + EnumChatFormatting.BOLD + tamingLevel + "\n" + EnumChatFormatting.AQUA + " Average Skill Level: " + DankersSkyblockMod.SKILL_AVERAGE_COLOUR + EnumChatFormatting.BOLD + skillAvg + "\n" + EnumChatFormatting.AQUA + " True Average Skill Level: " + DankersSkyblockMod.SKILL_AVERAGE_COLOUR + EnumChatFormatting.BOLD + trueAvg + "\n" + DankersSkyblockMod.DELIMITER_COLOUR + " " + EnumChatFormatting.BOLD + "-------------------"));
/* 183 */         })).start();
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\me\Danker\commands\SkillsCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */