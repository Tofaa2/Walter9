/*     */ package me.Danker.commands;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import me.Danker.DankersSkyblockMod;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.gui.GuiNewChat;
/*     */ import net.minecraft.command.CommandBase;
/*     */ import net.minecraft.command.CommandException;
/*     */ import net.minecraft.command.ICommand;
/*     */ import net.minecraft.command.ICommandSender;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ 
/*     */ public class RepartyCommand extends CommandBase implements ICommand {
/*     */   public static boolean gettingParty = false;
/*  20 */   public static int Delimiter = 0;
/*     */   public static boolean disbanding = false;
/*     */   public static boolean inviting = false;
/*     */   public static boolean failInviting = false;
/*  24 */   public static List<String> party = new ArrayList<>();
/*  25 */   public static List<String> repartyFailList = new ArrayList<>();
/*  26 */   public static Thread partyThread = null;
/*     */ 
/*     */   
/*     */   public String func_71517_b() {
/*  30 */     return "reparty";
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_71518_a(ICommandSender sender) {
/*  35 */     return "/" + func_71517_b();
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> func_71514_a() {
/*  40 */     return Collections.singletonList("rp");
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_82362_a() {
/*  45 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
/*  50 */     if (args.length > 0 && (args[0].startsWith("fail") || args[0].equals("f"))) {
/*  51 */       partyThread = new Thread(() -> {
/*     */             EntityPlayerSP player = (Minecraft.func_71410_x()).field_71439_g;
/*     */             
/*     */             GuiNewChat chat = (Minecraft.func_71410_x()).field_71456_v.func_146158_b();
/*     */             
/*     */             try {
/*     */               player.func_71165_d("/p " + String.join(" ", (Iterable)repartyFailList));
/*     */               
/*     */               String members = String.join(EnumChatFormatting.WHITE + "\n- " + EnumChatFormatting.YELLOW, (Iterable)repartyFailList);
/*     */               
/*     */               player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.DELIMITER_COLOUR + "-----------------------------\n" + DankersSkyblockMod.MAIN_COLOUR + "Partying:" + EnumChatFormatting.WHITE + "\n- " + EnumChatFormatting.YELLOW + members + "\n" + DankersSkyblockMod.DELIMITER_COLOUR + "-----------------------------"));
/*     */               
/*     */               failInviting = true;
/*     */               
/*     */               while (failInviting) {
/*     */                 Thread.sleep(10L);
/*     */               }
/*     */               
/*     */               if (repartyFailList.size() > 0) {
/*     */                 String repartyFails = String.join("\n- " + EnumChatFormatting.RED, (Iterable)repartyFailList);
/*     */                 
/*     */                 player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.DELIMITER_COLOUR + "-----------------------------\n" + DankersSkyblockMod.MAIN_COLOUR + "Failed to invite:" + EnumChatFormatting.WHITE + "\n- " + EnumChatFormatting.RED + repartyFails + "\n" + DankersSkyblockMod.DELIMITER_COLOUR + "-----------------------------"));
/*     */               } 
/*  74 */             } catch (InterruptedException e) {
/*     */               e.printStackTrace();
/*     */             } 
/*     */           });
/*     */       
/*  79 */       partyThread.start();
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  84 */     party.clear();
/*  85 */     repartyFailList.clear();
/*     */ 
/*     */     
/*  88 */     partyThread = new Thread(() -> {
/*     */           EntityPlayerSP player = (Minecraft.func_71410_x()).field_71439_g;
/*     */           
/*     */           try {
/*     */             player.func_71165_d("/pl");
/*     */             
/*     */             gettingParty = true;
/*     */             
/*     */             while (gettingParty) {
/*     */               Thread.sleep(10L);
/*     */             }
/*     */             
/*     */             if (party.size() == 0) {
/*     */               return;
/*     */             }
/*     */             
/*     */             player.func_71165_d("/p disband");
/*     */             
/*     */             disbanding = true;
/*     */             while (disbanding) {
/*     */               Thread.sleep(10L);
/*     */             }
/*     */             String members = String.join(EnumChatFormatting.WHITE + "\n- " + EnumChatFormatting.YELLOW, (Iterable)party);
/*     */             player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.DELIMITER_COLOUR + "-----------------------------\n" + DankersSkyblockMod.MAIN_COLOUR + "Repartying:" + EnumChatFormatting.WHITE + "\n- " + EnumChatFormatting.YELLOW + members + "\n" + DankersSkyblockMod.DELIMITER_COLOUR + "-----------------------------"));
/*     */             repartyFailList = new ArrayList<>(party);
/*     */             for (String invitee : party) {
/*     */               player.func_71165_d("/p " + invitee);
/*     */               inviting = true;
/*     */               while (inviting) {
/*     */                 Thread.sleep(10L);
/*     */               }
/*     */               Thread.sleep(100L);
/*     */             } 
/*     */             while (inviting) {
/*     */               Thread.sleep(10L);
/*     */             }
/*     */             if (repartyFailList.size() > 0) {
/*     */               String repartyFails = String.join("\n- " + EnumChatFormatting.RED, (Iterable)repartyFailList);
/*     */               player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.DELIMITER_COLOUR + "-----------------------------\n" + DankersSkyblockMod.MAIN_COLOUR + "Failed to invite:" + EnumChatFormatting.WHITE + "\n- " + EnumChatFormatting.RED + repartyFails + "\n" + DankersSkyblockMod.DELIMITER_COLOUR + "-----------------------------"));
/*     */             } 
/* 128 */           } catch (InterruptedException e) {
/*     */             e.printStackTrace();
/*     */           } 
/*     */         });
/*     */     
/* 133 */     partyThread.start();
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\me\Danker\commands\RepartyCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */