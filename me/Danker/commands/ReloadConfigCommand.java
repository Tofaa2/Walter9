/*    */ package me.Danker.commands;
/*    */ 
/*    */ import me.Danker.DankersSkyblockMod;
/*    */ import me.Danker.handlers.ConfigHandler;
/*    */ import net.minecraft.command.CommandBase;
/*    */ import net.minecraft.command.CommandException;
/*    */ import net.minecraft.command.ICommandSender;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.ChatComponentText;
/*    */ import net.minecraft.util.IChatComponent;
/*    */ 
/*    */ public class ReloadConfigCommand
/*    */   extends CommandBase {
/*    */   public String func_71517_b() {
/* 15 */     return "reloadconfig";
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_71518_a(ICommandSender arg0) {
/* 20 */     return "/" + func_71517_b();
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_82362_a() {
/* 25 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_71515_b(ICommandSender arg0, String[] arg1) throws CommandException {
/* 30 */     EntityPlayer player = (EntityPlayer)arg0;
/* 31 */     ConfigHandler.reloadConfig();
/* 32 */     player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Reloaded config."));
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\me\Danker\commands\ReloadConfigCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */