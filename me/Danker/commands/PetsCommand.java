/*     */ package me.Danker.commands;
/*     */ 
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import java.util.ArrayList;
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
/*     */ public class PetsCommand
/*     */   extends CommandBase {
/*     */   static int petXpToLevel(double xp, String rarity) {
/*  24 */     int[] xpPerLevel = { 100, 110, 120, 130, 145, 160, 175, 190, 210, 230, 250, 275, 300, 330, 360, 400, 440, 490, 540, 600, 660, 730, 800, 880, 960, 1050, 1150, 1260, 1380, 1510, 1650, 1800, 1960, 2130, 2310, 2500, 2700, 2920, 3160, 3420, 3700, 4000, 4350, 4750, 5200, 5700, 6300, 7000, 7800, 8700, 9700, 10800, 12000, 13300, 14700, 16200, 17800, 19500, 21300, 23200, 25200, 27400, 29800, 32400, 35200, 38200, 41400, 44800, 48400, 52200, 56200, 60400, 64800, 69400, 74200, 79200, 84700, 90700, 97200, 104200, 111700, 119700, 128200, 137200, 146700, 156700, 167700, 179700, 192700, 206700, 221700, 237700, 254700, 272700, 291700, 311700, 333700, 357700, 383700, 411700, 441700, 476700, 516700, 561700, 611700, 666700, 726700, 791700, 861700, 936700, 1016700, 1101700, 1191700, 1286700, 1386700, 1496700, 1616700, 1746700, 1886700 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  33 */     int levelOffset = 0;
/*  34 */     switch (rarity) {
/*     */       case "UNCOMMON":
/*  36 */         levelOffset = 6;
/*     */         break;
/*     */       case "RARE":
/*  39 */         levelOffset = 11;
/*     */         break;
/*     */       case "EPIC":
/*  42 */         levelOffset = 16;
/*     */         break;
/*     */       case "LEGENDARY":
/*  45 */         levelOffset = 20;
/*     */         break;
/*     */     } 
/*     */     
/*  49 */     for (int i = levelOffset, xpAdded = 0; i < levelOffset + 99; i++) {
/*  50 */       xpAdded += xpPerLevel[i];
/*  51 */       if (xp < xpAdded) {
/*  52 */         return i + 1 - levelOffset;
/*     */       }
/*     */     } 
/*  55 */     return 100;
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_71517_b() {
/*  60 */     return "petsof";
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_71518_a(ICommandSender arg0) {
/*  65 */     return "/" + func_71517_b() + " [name]";
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_82362_a() {
/*  70 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> func_180525_a(ICommandSender sender, String[] args, BlockPos pos) {
/*  75 */     if (args.length == 1) {
/*  76 */       return Utils.getMatchingPlayers(args[0]);
/*     */     }
/*  78 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_71515_b(ICommandSender arg0, String[] arg1) throws CommandException {
/*  84 */     (new Thread(() -> {
/*     */           String username;
/*     */           String uuid;
/*     */           EntityPlayer player = (EntityPlayer)arg0;
/*     */           String key = ConfigHandler.getString("api", "APIKey");
/*     */           if (key.equals("")) {
/*     */             player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "API key not set. Use /setkey."));
/*     */           }
/*     */           if (arg1.length == 0) {
/*     */             username = player.func_70005_c_();
/*     */             uuid = player.func_110124_au().toString().replaceAll("[\\-]", "");
/*     */             player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Checking pets of " + DankersSkyblockMod.SECONDARY_COLOUR + username));
/*     */           } else {
/*     */             username = arg1[0];
/*     */             player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Checking pets of " + DankersSkyblockMod.SECONDARY_COLOUR + username));
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
/*     */           System.out.println("Fetching pets...");
/*     */           JsonArray petsArray = profileResponse.get("profile").getAsJsonObject().get("members").getAsJsonObject().get(uuid).getAsJsonObject().get("pets").getAsJsonArray();
/*     */           if (petsArray.size() == 0) {
/*     */             player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + username + " has no pets."));
/*     */             return;
/*     */           } 
/*     */           System.out.println("Looping through pets...");
/*     */           List<JsonElement> sortedPets = new ArrayList<>();
/*     */           for (JsonElement petElement : petsArray) {
/*     */             sortedPets.add(petElement);
/*     */           }
/*     */           sortedPets.sort(());
/*     */           List<JsonObject> commonPets = new ArrayList<>();
/*     */           List<JsonObject> uncommonPets = new ArrayList<>();
/*     */           List<JsonObject> rarePets = new ArrayList<>();
/*     */           List<JsonObject> epicPets = new ArrayList<>();
/*     */           List<JsonObject> legendaryPets = new ArrayList<>();
/*     */           for (JsonElement petElement : sortedPets) {
/*     */             JsonObject pet = petElement.getAsJsonObject();
/*     */             String rarity = pet.get("tier").getAsString();
/*     */             switch (rarity) {
/*     */               case "COMMON":
/*     */                 commonPets.add(pet);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/*     */               case "UNCOMMON":
/*     */                 uncommonPets.add(pet);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/*     */               case "RARE":
/*     */                 rarePets.add(pet);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/*     */               case "EPIC":
/*     */                 epicPets.add(pet);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/*     */               case "LEGENDARY":
/*     */                 legendaryPets.add(pet);
/*     */             } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           } 
/*     */           int totalPets = commonPets.size() + uncommonPets.size() + rarePets.size() + epicPets.size() + legendaryPets.size();
/*     */           String finalMessage = DankersSkyblockMod.DELIMITER_COLOUR + "" + EnumChatFormatting.BOLD + "-------------------\n" + EnumChatFormatting.AQUA + " " + username + "'s Pets (" + totalPets + "):\n";
/*     */           for (JsonObject legPet : legendaryPets) {
/*     */             String messageToAdd;
/*     */             String petName = Utils.capitalizeString(legPet.get("type").getAsString());
/*     */             int level = petXpToLevel(legPet.get("exp").getAsDouble(), "LEGENDARY");
/*     */             if (legPet.get("active").getAsBoolean()) {
/*     */               messageToAdd = EnumChatFormatting.GOLD + " " + EnumChatFormatting.BOLD + ">>> Legendary " + petName + " (" + level + ") <<<";
/*     */             } else {
/*     */               messageToAdd = EnumChatFormatting.GOLD + " Legendary " + petName + " (" + level + ")";
/*     */             } 
/*     */             finalMessage = finalMessage + messageToAdd + "\n";
/*     */           } 
/*     */           for (JsonObject epicPet : epicPets) {
/*     */             String messageToAdd;
/*     */             String petName = Utils.capitalizeString(epicPet.get("type").getAsString());
/*     */             int level = petXpToLevel(epicPet.get("exp").getAsDouble(), "EPIC");
/*     */             if (epicPet.get("active").getAsBoolean()) {
/*     */               messageToAdd = EnumChatFormatting.DARK_PURPLE + " " + EnumChatFormatting.BOLD + ">>> Epic " + petName + " (" + level + ") <<<";
/*     */             } else {
/*     */               messageToAdd = EnumChatFormatting.DARK_PURPLE + " Epic " + petName + " (" + level + ")";
/*     */             } 
/*     */             finalMessage = finalMessage + messageToAdd + "\n";
/*     */           } 
/*     */           for (JsonObject rarePet : rarePets) {
/*     */             String messageToAdd;
/*     */             String petName = Utils.capitalizeString(rarePet.get("type").getAsString());
/*     */             int level = petXpToLevel(rarePet.get("exp").getAsDouble(), "RARE");
/*     */             if (rarePet.get("active").getAsBoolean()) {
/*     */               messageToAdd = EnumChatFormatting.BLUE + " " + EnumChatFormatting.BOLD + ">>> Rare " + petName + " (" + level + ") <<<";
/*     */             } else {
/*     */               messageToAdd = EnumChatFormatting.BLUE + " Rare " + petName + " (" + level + ")";
/*     */             } 
/*     */             finalMessage = finalMessage + messageToAdd + "\n";
/*     */           } 
/*     */           for (JsonObject uncommonPet : uncommonPets) {
/*     */             String messageToAdd;
/*     */             String petName = Utils.capitalizeString(uncommonPet.get("type").getAsString());
/*     */             int level = petXpToLevel(uncommonPet.get("exp").getAsDouble(), "UNCOMMON");
/*     */             if (uncommonPet.get("active").getAsBoolean()) {
/*     */               messageToAdd = EnumChatFormatting.GREEN + " " + EnumChatFormatting.BOLD + ">>> Uncommon " + petName + " (" + level + ") <<<";
/*     */             } else {
/*     */               messageToAdd = EnumChatFormatting.GREEN + " Uncommon " + petName + " (" + level + ")";
/*     */             } 
/*     */             finalMessage = finalMessage + messageToAdd + "\n";
/*     */           } 
/*     */           for (JsonObject commonPet : commonPets) {
/*     */             String messageToAdd;
/*     */             String petName = Utils.capitalizeString(commonPet.get("type").getAsString());
/*     */             int level = petXpToLevel(commonPet.get("exp").getAsDouble(), "COMMON");
/*     */             if (commonPet.get("active").getAsBoolean()) {
/*     */               messageToAdd = EnumChatFormatting.BOLD + ">>> Common " + petName + " (" + level + ") <<<";
/*     */             } else {
/*     */               messageToAdd = " Common " + petName + " (" + level + ")";
/*     */             } 
/*     */             finalMessage = finalMessage + messageToAdd + "\n";
/*     */           } 
/*     */           finalMessage = finalMessage + DankersSkyblockMod.DELIMITER_COLOUR + " " + EnumChatFormatting.BOLD + "-------------------";
/*     */           player.func_145747_a((IChatComponent)new ChatComponentText(finalMessage));
/* 248 */         })).start();
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\me\Danker\commands\PetsCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */