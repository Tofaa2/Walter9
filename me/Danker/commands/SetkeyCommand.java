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
/*    */ public class SetkeyCommand
/*    */   extends CommandBase implements ICommand {
/*    */   public String func_71517_b() {
/* 16 */     return "setkey";
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_71518_a(ICommandSender arg0) {
/* 21 */     return "/" + func_71517_b() + " <key>";
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_82362_a() {
/* 26 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_71515_b(ICommandSender arg0, String[] arg1) throws CommandException {
/* 31 */     EntityPlayer player = (EntityPlayer)arg0;
/*    */     
/* 33 */     if (arg1.length == 0) {
/* 34 */       player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Usage: " + func_71518_a(arg0)));
/*    */       
/*    */       return;
/*    */     } 
/* 38 */     ConfigHandler.writeStringConfig("api", "APIKey", arg1[0]);
/* 39 */     player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Set API key to " + DankersSkyblockMod.SECONDARY_COLOUR + arg1[0]));
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\me\Danker\commands\SetkeyCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */