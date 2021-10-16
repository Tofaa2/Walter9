/*     */ package cheaters.get.banned.config;
/*     */ 
/*     */ import cheaters.get.banned.Shady;
/*     */ import cheaters.get.banned.features.AutoTerminals;
/*     */ import cheaters.get.banned.features.CatGirls;
/*     */ import cheaters.get.banned.features.dungeonmap.DungeonMap;
/*     */ import cheaters.get.banned.features.dungeonmap.Room;
/*     */ import cheaters.get.banned.features.dungeonmap.RoomLoader;
/*     */ import cheaters.get.banned.remote.MayorAPI;
/*     */ import cheaters.get.banned.utils.DungeonUtils;
/*     */ import cheaters.get.banned.utils.Utils;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.command.CommandBase;
/*     */ import net.minecraft.command.CommandException;
/*     */ import net.minecraft.command.ICommandSender;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.apache.commons.lang3.RandomStringUtils;
/*     */ 
/*     */ 
/*     */ public class MainCommand
/*     */   extends CommandBase
/*     */ {
/*     */   public String func_71517_b() {
/*  25 */     return "sh";
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> func_71514_a() {
/*  30 */     return new ArrayList<String>()
/*     */       {
/*     */       
/*     */       };
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_71518_a(ICommandSender sender) {
/*  38 */     return "/" + func_71517_b();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
/*  43 */     if (!Shady.enabled) {
/*  44 */       Utils.sendMessageAsPlayer("/" + RandomStringUtils.random(10, true, false));
/*     */       
/*     */       return;
/*     */     } 
/*  48 */     if (args.length > 0) {
/*  49 */       switch (args[0]) {
/*     */         case "force_dungeon":
/*  51 */           Utils.forceDungeon = !Utils.forceDungeon;
/*  52 */           Utils.sendModMessage("Toggled forcing dungeon to " + Utils.forceDungeon);
/*     */           return;
/*     */         
/*     */         case "force_paul":
/*  56 */           MayorAPI.forcePaul();
/*  57 */           Utils.sendModMessage("Paul has committed a coup d'état and is now mayor of SkyBlock");
/*     */           return;
/*     */         
/*     */         case "force_skyblock":
/*  61 */           Utils.forceSkyBlock = !Utils.forceSkyBlock;
/*  62 */           Utils.sendModMessage("Toggled forcing SkyBlock to " + Utils.forceSkyBlock);
/*     */           return;
/*     */         
/*     */         case "debug":
/*  66 */           if (args.length > 1) {
/*  67 */             switch (args[1]) {
/*     */               case "dungeon":
/*  69 */                 if (Utils.inDungeon) DungeonUtils.debug();
/*     */                 
/*     */                 break;
/*     */               case "catperson":
/*     */               case "cat":
/*  74 */                 CatGirls.addRandomCatPerson();
/*  75 */                 Utils.sendModMessage("rawr");
/*     */                 break;
/*     */               
/*     */               case "terminals":
/*     */               case "terms":
/*  80 */                 AutoTerminals.testing = !AutoTerminals.testing;
/*  81 */                 Utils.sendModMessage("Toggled testing terminals to " + AutoTerminals.testing);
/*  82 */                 if (!Utils.forceDungeon) Utils.sendMessageAsPlayer("/shady force_dungeon");
/*     */                 
/*     */                 break;
/*     */               case "rooms":
/*  86 */                 Utils.sendModMessage("There are currently " + RoomLoader.rooms.size() + " rooms loaded");
/*  87 */                 for (Room room : RoomLoader.rooms) {
/*  88 */                   Utils.sendModMessage(room.name);
/*     */                 }
/*     */                 break;
/*     */               
/*     */               case "scan":
/*  93 */                 DungeonMap.debug = !DungeonMap.debug;
/*  94 */                 if (DungeonMap.debug) {
/*  95 */                   if (DungeonMap.activeDungeonLayout != null) {
/*  96 */                     Utils.sendModMessage("Rooms detected: " + DungeonMap.activeDungeonLayout.roomTiles.size());
/*  97 */                     Utils.sendModMessage("Connectors detected: " + DungeonMap.activeDungeonLayout.connectorTiles.size());
/*  98 */                     Utils.sendModMessage("Trap room type: " + DungeonMap.activeDungeonLayout.trapType);
/*  99 */                     Utils.sendModMessage("Total secrets: " + DungeonMap.activeDungeonLayout.totalSecrets);
/* 100 */                     Utils.sendModMessage("Total crypts: " + DungeonMap.activeDungeonLayout.totalCrypts); break;
/*     */                   } 
/* 102 */                   Utils.sendModMessage("No scan exists");
/*     */                 } 
/*     */                 break;
/*     */             } 
/*     */           
/*     */           }
/*     */           return;
/*     */         
/*     */         case "force_dungeon_floor":
/* 111 */           if (args.length > 1) {
/* 112 */             for (DungeonUtils.Floor floor : DungeonUtils.Floor.values()) {
/* 113 */               if (floor.name.replaceAll("[()]", "").equalsIgnoreCase(args[1])) {
/* 114 */                 if (!Utils.forceDungeon) Utils.sendMessageAsPlayer("/shady force_dungeon"); 
/* 115 */                 DungeonUtils.dungeonRun.floor = floor;
/* 116 */                 Utils.sendModMessage("Set floor to " + DungeonUtils.dungeonRun.floor);
/*     */                 return;
/*     */               } 
/*     */             } 
/* 120 */             Utils.sendModMessage("Unable to match \"" + args[1] + "\" to a dungeon floor");
/*     */           } 
/*     */           return;
/*     */         
/*     */         case "disable":
/* 125 */           Shady.disable();
/*     */           return;
/*     */       } 
/*     */       
/* 129 */       Utils.sendModMessage("Unrecognized command");
/*     */     } else {
/*     */       
/* 132 */       Shady.guiToOpen = new ConfigGui(new ResourceLocation("shadyaddons:" + Utils.getLogo() + ".png"));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_82362_a() {
/* 138 */     return 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Desktop\ShadyAddons-2.1.0-pre2.jar!\cheaters\get\banned\config\MainCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */