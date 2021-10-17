/*    */ package me.Danker.commands;
/*    */ 
/*    */ import java.awt.Toolkit;
/*    */ import java.awt.datatransfer.Clipboard;
/*    */ import java.awt.datatransfer.StringSelection;
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
/*    */ public class GetkeyCommand
/*    */   extends CommandBase
/*    */   implements ICommand {
/*    */   public String func_71517_b() {
/* 20 */     return "getkey";
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_71518_a(ICommandSender arg0) {
/* 25 */     return "/" + func_71517_b();
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_82362_a() {
/* 30 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_71515_b(ICommandSender arg0, String[] arg1) throws CommandException {
/* 35 */     EntityPlayer player = (EntityPlayer)arg0;
/* 36 */     Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
/* 37 */     StringSelection stringSelection = new StringSelection(ConfigHandler.getString("api", "APIKey"));
/*    */     
/* 39 */     if (ConfigHandler.getString("api", "APIKey").equals("")) {
/* 40 */       player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "API key not set. Set your API key using /setkey."));
/*    */     }
/*    */     
/* 43 */     clipboard.setContents(stringSelection, null);
/* 44 */     player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Your set API key is " + DankersSkyblockMod.SECONDARY_COLOUR + ConfigHandler.getString("api", "APIKey") + "\n" + DankersSkyblockMod.MAIN_COLOUR + " Your set API key has been copied to the clipboard."));
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\me\Danker\commands\GetkeyCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */