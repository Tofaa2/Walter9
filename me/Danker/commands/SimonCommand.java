/*    */ package me.Danker.commands;
/*    */ 
/*    */ import me.Danker.DankersSkyblockMod;
/*    */ import me.Danker.handlers.ConfigHandler;
/*    */ import net.minecraft.command.CommandBase;
/*    */ import net.minecraft.command.CommandException;
/*    */ import net.minecraft.command.ICommand;
/*    */ import net.minecraft.command.ICommandSender;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.ChatComponentText;
/*    */ import net.minecraft.util.IChatComponent;
/*    */ 
/*    */ public class SimonCommand
/*    */   extends CommandBase implements ICommand {
/*    */   public String func_71517_b() {
/* 16 */     return "rightclickamount";
/*    */   }
/*    */   public static int simonAmount;
/*    */   public String func_71518_a(ICommandSender arg0) {
/* 20 */     return "/" + func_71517_b() + " amount clicked per click";
/*    */   }
/*    */   
/*    */   public int func_82362_a() {
/* 24 */     return 0;
/*    */   }
/*    */   
/*    */   public void func_71515_b(ICommandSender arg0, String[] arg1) throws CommandException {
/* 28 */     EntityPlayer player = (EntityPlayer)arg0;
/* 29 */     if (arg1.length == 0) {
/* 30 */       player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Usage: " + func_71518_a(arg0)));
/*    */       return;
/*    */     } 
/* 33 */     if (Integer.parseInt(arg1[0]) >= 1000)
/* 34 */       player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "SIMON WR!!!")); 
/* 35 */     simonAmount = Integer.parseInt(arg1[0]);
/* 36 */     ConfigHandler.writeIntConfig("macro", "simon", simonAmount);
/* 37 */     player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Clicks per click set to " + Integer.parseInt(arg1[0])));
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\me\Danker\commands\SimonCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */