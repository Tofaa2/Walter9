/*    */ package me.Danker.commands;
/*    */ 
/*    */ import java.util.List;
/*    */ import me.Danker.DankersSkyblockMod;
/*    */ import me.Danker.handlers.ConfigHandler;
/*    */ import net.minecraft.command.CommandBase;
/*    */ import net.minecraft.command.CommandException;
/*    */ import net.minecraft.command.ICommandSender;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.ChatComponentText;
/*    */ import net.minecraft.util.IChatComponent;
/*    */ 
/*    */ public class BlockSlayerCommand
/*    */   extends CommandBase {
/* 16 */   public static String onlySlayerName = "";
/* 17 */   public static String onlySlayerNumber = "";
/*    */ 
/*    */   
/*    */   public String func_71517_b() {
/* 21 */     return "onlyslayer";
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_71518_a(ICommandSender arg0) {
/* 26 */     return "/" + func_71517_b() + " <zombie/spider/wolf> <1/2/3/4>";
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_82362_a() {
/* 31 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> func_180525_a(ICommandSender sender, String[] args, BlockPos pos) {
/* 36 */     if (args.length == 1)
/* 37 */       return func_71530_a(args, new String[] { "zombie", "spider", "wolf" }); 
/* 38 */     if (args.length == 2) {
/* 39 */       return func_71530_a(args, new String[] { "1", "2", "3", "4" });
/*    */     }
/* 41 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_71515_b(ICommandSender arg0, String[] arg1) throws CommandException {
/* 46 */     EntityPlayer player = (EntityPlayer)arg0;
/*    */     
/* 48 */     if (arg1.length == 0 || (arg1.length == 1 && !arg1[0].equalsIgnoreCase("off"))) {
/* 49 */       player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Usage: " + func_71518_a(arg0)));
/*    */       
/*    */       return;
/*    */     } 
/* 53 */     switch (arg1[0].toLowerCase()) {
/*    */       case "zombie":
/* 55 */         onlySlayerName = "Revenant Horror";
/*    */         break;
/*    */       case "spider":
/* 58 */         onlySlayerName = "Tarantula Broodfather";
/*    */         break;
/*    */       case "wolf":
/* 61 */         onlySlayerName = "Sven Packmaster";
/*    */         break;
/*    */       case "off":
/* 64 */         onlySlayerName = "";
/* 65 */         onlySlayerNumber = "";
/* 66 */         ConfigHandler.writeStringConfig("toggles", "BlockSlayer", "");
/* 67 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Slayer blocking turned off."));
/*    */         return;
/*    */       default:
/* 70 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Usage: " + func_71518_a(arg0)));
/*    */         return;
/*    */     } 
/*    */     
/* 74 */     int slayerNumber = Integer.parseInt(arg1[1]);
/*    */     
/* 76 */     switch (slayerNumber) {
/*    */       case 1:
/* 78 */         onlySlayerNumber = "I";
/*    */         break;
/*    */       case 2:
/* 81 */         onlySlayerNumber = "II";
/*    */         break;
/*    */       case 3:
/* 84 */         onlySlayerNumber = "III";
/*    */         break;
/*    */       case 4:
/* 87 */         onlySlayerNumber = "IV";
/*    */         break;
/*    */       default:
/* 90 */         onlySlayerName = "";
/* 91 */         onlySlayerNumber = "";
/* 92 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Usage: " + func_71518_a(arg0)));
/*    */         return;
/*    */     } 
/*    */     
/* 96 */     ConfigHandler.writeStringConfig("toggles", "BlockSlayer", onlySlayerName + " " + onlySlayerNumber);
/* 97 */     player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Slayer blocking set to " + DankersSkyblockMod.SECONDARY_COLOUR + onlySlayerName + " " + onlySlayerNumber));
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\me\Danker\commands\BlockSlayerCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */