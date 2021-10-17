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
/*    */ public class SwapCommand
/*    */   extends CommandBase implements ICommand {
/*    */   public String func_71517_b() {
/* 16 */     return "swapdelay";
/*    */   }
/*    */   public static int swapDelay;
/*    */   public String func_71518_a(ICommandSender arg0) {
/* 20 */     return "/" + func_71517_b() + " delayMS, 0 disables swap";
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
/* 33 */     swapDelay = Integer.parseInt(arg1[0]);
/* 34 */     ConfigHandler.writeIntConfig("macro", "swap", swapDelay);
/* 35 */     player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Swap delay set to " + Integer.parseInt(arg1[0])));
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\me\Danker\commands\SwapCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */