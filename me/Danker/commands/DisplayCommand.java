/*     */ package me.Danker.commands;
/*     */ 
/*     */ import java.util.List;
/*     */ import me.Danker.DankersSkyblockMod;
/*     */ import me.Danker.handlers.ConfigHandler;
/*     */ import net.minecraft.command.CommandBase;
/*     */ import net.minecraft.command.CommandException;
/*     */ import net.minecraft.command.ICommandSender;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ 
/*     */ public class DisplayCommand
/*     */   extends CommandBase {
/*     */   public static String display;
/*     */   public static boolean auto;
/*     */   
/*     */   public String func_71517_b() {
/*  20 */     return "display";
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_71518_a(ICommandSender arg0) {
/*  25 */     return "/" + func_71517_b() + " <zombie/spider/wolf/fishing/catacombs/mythological/auto/off> [winter/festival/spooky/session/f(1-7)]";
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_82362_a() {
/*  30 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> func_180525_a(ICommandSender sender, String[] args, BlockPos pos) {
/*  35 */     if (args.length == 1)
/*  36 */       return func_71530_a(args, new String[] { "wolf", "spider", "zombie", "fishing", "catacombs", "mythological", "auto", "off" }); 
/*  37 */     if (args.length == 2 && args[0].equalsIgnoreCase("fishing"))
/*  38 */       return func_71530_a(args, new String[] { "winter", "festival", "spooky", "session" }); 
/*  39 */     if (args.length == 2 && args[0].equalsIgnoreCase("catacombs"))
/*  40 */       return func_71530_a(args, new String[] { "f1", "floor1", "f2", "floor2", "f3", "floor3", "f4", "floor4", "f5", "floor5", "f6", "floor6", "f7", "floor7" }); 
/*  41 */     if (args.length > 1) {
/*  42 */       return func_71530_a(args, new String[] { "session" });
/*     */     }
/*  44 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_71515_b(ICommandSender arg0, String[] arg1) throws CommandException {
/*  49 */     EntityPlayer player = (EntityPlayer)arg0;
/*     */     
/*  51 */     if (arg1.length == 0) {
/*  52 */       player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Usage: " + func_71518_a(arg0)));
/*     */       
/*     */       return;
/*     */     } 
/*  56 */     boolean showSession = false;
/*     */     
/*  58 */     if (arg1[arg1.length - 1].equalsIgnoreCase("session")) showSession = true;
/*     */     
/*  60 */     switch (arg1[0].toLowerCase()) {
/*     */       case "wolf":
/*  62 */         if (showSession) {
/*  63 */           display = "wolf_session"; break;
/*     */         } 
/*  65 */         display = "wolf";
/*     */         break;
/*     */       
/*     */       case "spider":
/*  69 */         if (showSession) {
/*  70 */           display = "spider_session"; break;
/*     */         } 
/*  72 */         display = "spider";
/*     */         break;
/*     */       
/*     */       case "zombie":
/*  76 */         if (showSession) {
/*  77 */           display = "zombie_session"; break;
/*     */         } 
/*  79 */         display = "zombie";
/*     */         break;
/*     */       
/*     */       case "fishing":
/*  83 */         if (arg1.length > 1) {
/*  84 */           switch (arg1[1].toLowerCase()) {
/*     */             case "winter":
/*  86 */               if (showSession) {
/*  87 */                 display = "fishing_winter_session"; break;
/*     */               } 
/*  89 */               display = "fishing_winter";
/*     */               break;
/*     */             
/*     */             case "festival":
/*  93 */               if (showSession) {
/*  94 */                 display = "fishing_festival_session"; break;
/*     */               } 
/*  96 */               display = "fishing_festival";
/*     */               break;
/*     */             
/*     */             case "spooky":
/* 100 */               if (showSession) {
/* 101 */                 display = "fishing_spooky_session"; break;
/*     */               } 
/* 103 */               display = "fishing_spooky";
/*     */               break;
/*     */           } 
/*     */           
/* 107 */           if (showSession) {
/* 108 */             display = "fishing_session"; break;
/*     */           } 
/* 110 */           display = "fishing";
/*     */           
/*     */           break;
/*     */         } 
/* 114 */         if (showSession) {
/* 115 */           display = "fishing_session"; break;
/*     */         } 
/* 117 */         display = "fishing";
/*     */         break;
/*     */ 
/*     */       
/*     */       case "mythological":
/* 122 */         if (showSession) {
/* 123 */           display = "mythological_session"; break;
/*     */         } 
/* 125 */         display = "mythological";
/*     */         break;
/*     */       
/*     */       case "catacombs":
/* 129 */         if (arg1.length == 1) {
/* 130 */           player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Usage: /display catacombs <f1/f2/f3/f4f5/f6/f7>"));
/*     */           
/*     */           return;
/*     */         } 
/* 134 */         switch (arg1[1].toLowerCase()) {
/*     */           case "f1":
/*     */           case "floor1":
/* 137 */             if (showSession) {
/* 138 */               display = "catacombs_floor_one_session"; break;
/*     */             } 
/* 140 */             display = "catacombs_floor_one";
/*     */             break;
/*     */           
/*     */           case "f2":
/*     */           case "floor2":
/* 145 */             if (showSession) {
/* 146 */               display = "catacombs_floor_two_session"; break;
/*     */             } 
/* 148 */             display = "catacombs_floor_two";
/*     */             break;
/*     */           
/*     */           case "f3":
/*     */           case "floor3":
/* 153 */             if (showSession) {
/* 154 */               display = "catacombs_floor_three_session"; break;
/*     */             } 
/* 156 */             display = "catacombs_floor_three";
/*     */             break;
/*     */           
/*     */           case "f4":
/*     */           case "floor4":
/* 161 */             if (showSession) {
/* 162 */               display = "catacombs_floor_four_session"; break;
/*     */             } 
/* 164 */             display = "catacombs_floor_four";
/*     */             break;
/*     */           
/*     */           case "f5":
/*     */           case "floor5":
/* 169 */             if (showSession) {
/* 170 */               display = "catacombs_floor_five_session"; break;
/*     */             } 
/* 172 */             display = "catacombs_floor_five";
/*     */             break;
/*     */           
/*     */           case "f6":
/*     */           case "floor6":
/* 177 */             if (showSession) {
/* 178 */               display = "catacombs_floor_six_session"; break;
/*     */             } 
/* 180 */             display = "catacombs_floor_six";
/*     */             break;
/*     */           
/*     */           case "f7":
/*     */           case "floor7":
/* 185 */             if (showSession) {
/* 186 */               display = "catacombs_floor_seven_session"; break;
/*     */             } 
/* 188 */             display = "catacombs_floor_seven";
/*     */             break;
/*     */         } 
/*     */         
/* 192 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Usage: /display catacombs <f1/f2/f3/f4/f5/f6/f7>"));
/*     */         return;
/*     */ 
/*     */       
/*     */       case "auto":
/* 197 */         auto = true;
/* 198 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Display set to " + DankersSkyblockMod.SECONDARY_COLOUR + "auto" + DankersSkyblockMod.MAIN_COLOUR + "."));
/* 199 */         ConfigHandler.writeBooleanConfig("misc", "autoDisplay", true);
/*     */         return;
/*     */       case "off":
/* 202 */         display = "off";
/*     */         break;
/*     */       default:
/* 205 */         player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Usage: " + func_71518_a(arg0)));
/*     */         return;
/*     */     } 
/*     */     
/* 209 */     auto = false;
/* 210 */     player.func_145747_a((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Display set to " + DankersSkyblockMod.SECONDARY_COLOUR + display + DankersSkyblockMod.MAIN_COLOUR + "."));
/* 211 */     ConfigHandler.writeBooleanConfig("misc", "autoDisplay", false);
/* 212 */     ConfigHandler.writeStringConfig("misc", "display", display);
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\me\Danker\commands\DisplayCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */