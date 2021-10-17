/*     */ package me.Danker.commands;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonObject;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.stream.Collectors;
/*     */ import me.Danker.DankersSkyblockMod;
/*     */ import me.Danker.handlers.APIHandler;
/*     */ import me.Danker.utils.Utils;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.network.NetworkPlayerInfo;
/*     */ import net.minecraft.command.CommandBase;
/*     */ import net.minecraft.command.CommandException;
/*     */ import net.minecraft.command.ICommandSender;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ 
/*     */ public class LobbySkillsCommand extends CommandBase {
/*  24 */   Thread mainThread = null;
/*     */ 
/*     */   
/*     */   public String func_71517_b() {
/*  28 */     return "lobbyskills";
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_71518_a(ICommandSender arg0) {
/*  33 */     return "/" + func_71517_b();
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_82362_a() {
/*  38 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_71515_b(ICommandSender arg0, String[] arg1) throws CommandException {
/*  43 */     EntityPlayer playerSP = (EntityPlayer)arg0;
/*  44 */     Map<String, Double> unsortedSAList = new HashMap<>();
/*  45 */     ArrayList<Double> lobbySkills = new ArrayList<>();
/*     */     
/*  47 */     String key = ConfigHandler.getString("api", "APIKey");
/*  48 */     if (key.equals("")) {
/*  49 */       playerSP.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "API key not set. Use /setkey."));
/*     */       
/*     */       return;
/*     */     } 
/*  53 */     this.mainThread = new Thread(() -> {
/*     */           try {
/*     */             Collection<NetworkPlayerInfo> players = new ArrayList<>(Minecraft.func_71410_x().func_147114_u().func_175106_d());
/*     */             
/*     */             playerSP.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Checking skill average of lobby. Estimated time: " + (int)(players.size() * 1.2D + 1.0D) + " seconds."));
/*     */             
/*     */             for (NetworkPlayerInfo player : players) {
/*     */               if (player.func_178845_a().getName().startsWith("!")) {
/*     */                 continue;
/*     */               }
/*     */               
/*     */               String UUID = player.func_178845_a().getId().toString().replaceAll("-", "");
/*     */               
/*     */               long biggestLastSave = 0L;
/*     */               
/*     */               int profileIndex = -1;
/*     */               
/*     */               Thread.sleep(600L);
/*     */               
/*     */               JsonObject profileResponse = APIHandler.getResponse("https://api.hypixel.net/skyblock/profiles?uuid=" + UUID + "&key=" + key);
/*     */               
/*     */               if (!profileResponse.get("success").getAsBoolean()) {
/*     */                 String reason = profileResponse.get("cause").getAsString();
/*     */                 
/*     */                 System.out.println("User " + player.func_178845_a().getName() + " failed with reason: " + reason);
/*     */                 
/*     */                 continue;
/*     */               } 
/*     */               
/*     */               if (profileResponse.get("profiles").isJsonNull()) {
/*     */                 continue;
/*     */               }
/*     */               
/*     */               JsonArray profiles = profileResponse.get("profiles").getAsJsonArray();
/*     */               
/*     */               for (int j = 0; j < profiles.size(); j++) {
/*     */                 JsonObject profile = profiles.get(j).getAsJsonObject();
/*     */                 
/*     */                 if (profile.get("members").getAsJsonObject().get(UUID).getAsJsonObject().has("last_save") && profile.get("members").getAsJsonObject().get(UUID).getAsJsonObject().get("last_save").getAsLong() > biggestLastSave) {
/*     */                   biggestLastSave = profile.get("members").getAsJsonObject().get(UUID).getAsJsonObject().get("last_save").getAsLong();
/*     */                   
/*     */                   profileIndex = j;
/*     */                 } 
/*     */               } 
/*     */               
/*     */               if (profileIndex == -1 || biggestLastSave == 0L) {
/*     */                 continue;
/*     */               }
/*     */               
/*     */               JsonObject latestProfile = profiles.get(profileIndex).getAsJsonObject().get("members").getAsJsonObject().get(UUID).getAsJsonObject();
/*     */               double farmingLevel = 0.0D;
/*     */               double miningLevel = 0.0D;
/*     */               double combatLevel = 0.0D;
/*     */               double foragingLevel = 0.0D;
/*     */               double fishingLevel = 0.0D;
/*     */               double enchantingLevel = 0.0D;
/*     */               double alchemyLevel = 0.0D;
/*     */               double tamingLevel = 0.0D;
/*     */               if (latestProfile.has("experience_skill_farming") || latestProfile.has("experience_skill_mining") || latestProfile.has("experience_skill_combat") || latestProfile.has("experience_skill_foraging") || latestProfile.has("experience_skill_fishing") || latestProfile.has("experience_skill_enchanting") || latestProfile.has("experience_skill_alchemy")) {
/*     */                 if (latestProfile.has("experience_skill_farming")) {
/*     */                   farmingLevel = Utils.xpToSkillLevel(latestProfile.get("experience_skill_farming").getAsDouble(), 60);
/*     */                   farmingLevel = Math.round(farmingLevel * 100.0D) / 100.0D;
/*     */                 } 
/*     */                 if (latestProfile.has("experience_skill_mining")) {
/*     */                   miningLevel = Utils.xpToSkillLevel(latestProfile.get("experience_skill_mining").getAsDouble(), 50);
/*     */                   miningLevel = Math.round(miningLevel * 100.0D) / 100.0D;
/*     */                 } 
/*     */                 if (latestProfile.has("experience_skill_combat")) {
/*     */                   combatLevel = Utils.xpToSkillLevel(latestProfile.get("experience_skill_combat").getAsDouble(), 50);
/*     */                   combatLevel = Math.round(combatLevel * 100.0D) / 100.0D;
/*     */                 } 
/*     */                 if (latestProfile.has("experience_skill_foraging")) {
/*     */                   foragingLevel = Utils.xpToSkillLevel(latestProfile.get("experience_skill_foraging").getAsDouble(), 50);
/*     */                   foragingLevel = Math.round(foragingLevel * 100.0D) / 100.0D;
/*     */                 } 
/*     */                 if (latestProfile.has("experience_skill_fishing")) {
/*     */                   fishingLevel = Utils.xpToSkillLevel(latestProfile.get("experience_skill_fishing").getAsDouble(), 50);
/*     */                   fishingLevel = Math.round(fishingLevel * 100.0D) / 100.0D;
/*     */                 } 
/*     */                 if (latestProfile.has("experience_skill_enchanting")) {
/*     */                   enchantingLevel = Utils.xpToSkillLevel(latestProfile.get("experience_skill_enchanting").getAsDouble(), 60);
/*     */                   enchantingLevel = Math.round(enchantingLevel * 100.0D) / 100.0D;
/*     */                 } 
/*     */                 if (latestProfile.has("experience_skill_alchemy")) {
/*     */                   alchemyLevel = Utils.xpToSkillLevel(latestProfile.get("experience_skill_alchemy").getAsDouble(), 50);
/*     */                   alchemyLevel = Math.round(alchemyLevel * 100.0D) / 100.0D;
/*     */                 } 
/*     */                 if (latestProfile.has("experience_skill_taming")) {
/*     */                   tamingLevel = Utils.xpToSkillLevel(latestProfile.get("experience_skill_taming").getAsDouble(), 50);
/*     */                   tamingLevel = Math.round(tamingLevel * 100.0D) / 100.0D;
/*     */                 } 
/*     */               } else {
/*     */                 Thread.sleep(600L);
/*     */                 System.out.println("Fetching skills from achievement API");
/*     */                 JsonObject playerObject = APIHandler.getResponse("https://api.hypixel.net/player?uuid=" + UUID + "&key=" + key);
/*     */                 if (!playerObject.get("success").getAsBoolean()) {
/*     */                   String reason = profileResponse.get("cause").getAsString();
/*     */                   System.out.println("User " + player.func_178845_a().getName() + " failed with reason: " + reason);
/*     */                   continue;
/*     */                 } 
/*     */                 JsonObject achievementObject = playerObject.get("player").getAsJsonObject().get("achievements").getAsJsonObject();
/*     */                 if (achievementObject.has("skyblock_harvester")) {
/*     */                   farmingLevel = achievementObject.get("skyblock_harvester").getAsInt();
/*     */                 }
/*     */                 if (achievementObject.has("skyblock_excavator")) {
/*     */                   miningLevel = Math.min(achievementObject.get("skyblock_excavator").getAsInt(), 50);
/*     */                 }
/*     */                 if (achievementObject.has("skyblock_combat")) {
/*     */                   combatLevel = Math.min(achievementObject.get("skyblock_combat").getAsInt(), 50);
/*     */                 }
/*     */                 if (achievementObject.has("skyblock_gatherer")) {
/*     */                   foragingLevel = Math.min(achievementObject.get("skyblock_gatherer").getAsInt(), 50);
/*     */                 }
/*     */                 if (achievementObject.has("skyblock_angler")) {
/*     */                   fishingLevel = Math.min(achievementObject.get("skyblock_angler").getAsInt(), 50);
/*     */                 }
/*     */                 if (achievementObject.has("skyblock_augmentation")) {
/*     */                   enchantingLevel = achievementObject.get("skyblock_augmentation").getAsInt();
/*     */                 }
/*     */                 if (achievementObject.has("skyblock_concoctor")) {
/*     */                   alchemyLevel = Math.min(achievementObject.get("skyblock_concoctor").getAsInt(), 50);
/*     */                 }
/*     */                 if (achievementObject.has("skyblock_domesticator")) {
/*     */                   tamingLevel = Math.min(achievementObject.get("skyblock_domesticator").getAsInt(), 50);
/*     */                 }
/*     */               } 
/*     */               double skillAvg = (farmingLevel + miningLevel + combatLevel + foragingLevel + fishingLevel + enchantingLevel + alchemyLevel + tamingLevel) / 8.0D;
/*     */               skillAvg = Math.round(skillAvg * 100.0D) / 100.0D;
/*     */               unsortedSAList.put(player.func_178845_a().getName(), Double.valueOf(skillAvg));
/*     */               lobbySkills.add(Double.valueOf(skillAvg));
/*     */             } 
/*     */             Map<String, Double> sortedSAList = (Map<String, Double>)unsortedSAList.entrySet().stream().sorted(Map.Entry.comparingByValue().reversed()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (), java.util.LinkedHashMap::new));
/*     */             String[] sortedSAListKeys = (String[])sortedSAList.keySet().toArray((Object[])new String[0]);
/*     */             String top3 = "";
/*     */             int i = 0;
/*     */             while (i < 3 && i < sortedSAListKeys.length) {
/*     */               top3 = top3 + "\n " + EnumChatFormatting.AQUA + sortedSAListKeys[i] + ": " + DankersSkyblockMod.SKILL_AVERAGE_COLOUR + EnumChatFormatting.BOLD + sortedSAList.get(sortedSAListKeys[i]);
/*     */               i++;
/*     */             } 
/*     */             double lobbySA = 0.0D;
/*     */             for (Double playerSkills : lobbySkills) {
/*     */               lobbySA += playerSkills.doubleValue();
/*     */             }
/*     */             lobbySA = Math.round(lobbySA / lobbySkills.size() * 100.0D) / 100.0D;
/*     */             playerSP.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.DELIMITER_COLOUR + "" + EnumChatFormatting.BOLD + "-------------------\n" + DankersSkyblockMod.TYPE_COLOUR + " Lobby Skill Average: " + DankersSkyblockMod.SKILL_AVERAGE_COLOUR + EnumChatFormatting.BOLD + lobbySA + "\n" + DankersSkyblockMod.TYPE_COLOUR + " Highest Skill Averages:" + top3 + "\n" + DankersSkyblockMod.DELIMITER_COLOUR + "" + EnumChatFormatting.BOLD + " -------------------"));
/* 198 */           } catch (InterruptedException ex) {
/*     */             System.out.println("Current skill average list: " + unsortedSAList.toString());
/*     */             
/*     */             Thread.currentThread().interrupt();
/*     */             System.out.println("Interrupted /lobbyskills thread.");
/*     */           } 
/*     */         });
/* 205 */     this.mainThread.start();
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\me\Danker\commands\LobbySkillsCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */