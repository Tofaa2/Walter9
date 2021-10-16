/*     */ package cheaters.get.banned.features;
/*     */ 
/*     */ import cheaters.get.banned.Shady;
/*     */ import cheaters.get.banned.config.Config;
/*     */ import cheaters.get.banned.events.TickEndEvent;
/*     */ import cheaters.get.banned.utils.Utils;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.gui.inventory.GuiChest;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.ContainerChest;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.EnumDyeColor;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.StringUtils;
/*     */ import net.minecraftforge.client.event.GuiScreenEvent;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ 
/*     */ public class AutoTerminals
/*     */ {
/*  26 */   private static final ArrayList<Slot> clickQueue = new ArrayList<>(28);
/*  27 */   private static final int[] mazeDirection = new int[] { -9, -1, 1, 9 };
/*  28 */   private static TerminalType currentTerminal = TerminalType.NONE;
/*     */   private static char letterNeeded;
/*     */   private static String colorNeeded;
/*     */   private static long lastClickTime;
/*     */   private static int windowId;
/*     */   private static int windowClicks;
/*     */   private static boolean recalculate = false;
/*     */   public static boolean testing = false;
/*     */   
/*     */   private enum TerminalType {
/*  38 */     MAZE, NUMBERS, CORRECT_ALL, LETTER, COLOR, NONE;
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onGuiDraw(GuiScreenEvent.BackgroundDrawnEvent event) {
/*  43 */     if (!Config.autoTerminals || !Utils.inDungeon)
/*     */       return; 
/*  45 */     if (event.gui instanceof GuiChest) {
/*  46 */       Container container = ((GuiChest)event.gui).field_147002_h;
/*  47 */       if (container instanceof ContainerChest) {
/*  48 */         List<Slot> invSlots = container.field_75151_b;
/*     */         
/*  50 */         if (currentTerminal == TerminalType.NONE) {
/*  51 */           String chestName = ((ContainerChest)container).func_85151_d().func_145748_c_().func_150260_c();
/*  52 */           if (chestName.equals("Navigate the maze!")) {
/*  53 */             currentTerminal = TerminalType.MAZE;
/*  54 */           } else if (chestName.equals("Click in order!")) {
/*  55 */             currentTerminal = TerminalType.NUMBERS;
/*  56 */           } else if (chestName.equals("Correct all the panes!")) {
/*  57 */             currentTerminal = TerminalType.CORRECT_ALL;
/*  58 */           } else if (chestName.startsWith("What starts with:")) {
/*  59 */             currentTerminal = TerminalType.LETTER;
/*  60 */           } else if (chestName.startsWith("Select all the")) {
/*  61 */             currentTerminal = TerminalType.COLOR;
/*     */           } 
/*     */         } 
/*  64 */         if (currentTerminal != TerminalType.NONE) {
/*  65 */           if (clickQueue.isEmpty() || recalculate) {
/*     */ 
/*     */             
/*  68 */             recalculate = getClicks((ContainerChest)container);
/*     */           } else {
/*     */             
/*  71 */             switch (currentTerminal) {
/*     */               case MAZE:
/*     */               case NUMBERS:
/*     */               case CORRECT_ALL:
/*  75 */                 clickQueue.removeIf(slot -> (((Slot)invSlots.get(slot.field_75222_d)).func_75216_d() && ((Slot)invSlots.get(slot.field_75222_d)).func_75211_c().func_77952_i() == 5));
/*     */                 break;
/*     */               case LETTER:
/*     */               case COLOR:
/*  79 */                 clickQueue.removeIf(slot -> (((Slot)invSlots.get(slot.field_75222_d)).func_75216_d() && ((Slot)invSlots.get(slot.field_75222_d)).func_75211_c().func_77948_v()));
/*     */                 break;
/*     */             } 
/*     */           
/*     */           } 
/*  84 */           if (!clickQueue.isEmpty() && 
/*  85 */             Config.autoTerminals && System.currentTimeMillis() - lastClickTime > Config.terminalClickDelay) {
/*  86 */             switch (currentTerminal) {
/*     */               case MAZE:
/*  88 */                 if (Config.autoMaze) clickSlot(clickQueue.get(0)); 
/*     */                 break;
/*     */               case NUMBERS:
/*  91 */                 if (Config.autoNumbers) clickSlot(clickQueue.get(0)); 
/*     */                 break;
/*     */               case CORRECT_ALL:
/*  94 */                 if (Config.autoCorrectAll) clickSlot(clickQueue.get(0)); 
/*     */                 break;
/*     */               case LETTER:
/*  97 */                 if (Config.autoLetter) clickSlot(clickQueue.get(0)); 
/*     */                 break;
/*     */               case COLOR:
/* 100 */                 if (Config.autoColor) clickSlot(clickQueue.get(0));
/*     */                 
/*     */                 break;
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onTick(TickEndEvent event) {
/* 112 */     if (!Config.autoTerminals || !Utils.inDungeon)
/* 113 */       return;  if (Shady.mc.field_71439_g == null || Shady.mc.field_71441_e == null)
/*     */       return; 
/* 115 */     if (!(Shady.mc.field_71462_r instanceof GuiChest)) {
/* 116 */       currentTerminal = TerminalType.NONE;
/* 117 */       clickQueue.clear();
/* 118 */       letterNeeded = Character.MIN_VALUE;
/* 119 */       colorNeeded = null;
/* 120 */       windowClicks = 0;
/*     */     }  } private boolean getClicks(ContainerChest container) {
/*     */     int startSlot, endSlot;
/*     */     boolean[] mazeVisited;
/*     */     int i;
/* 125 */     List<Slot> invSlots = container.field_75151_b;
/* 126 */     String chestName = container.func_85151_d().func_145748_c_().func_150260_c();
/* 127 */     clickQueue.clear();
/* 128 */     switch (currentTerminal) {
/*     */       
/*     */       case MAZE:
/* 131 */         startSlot = -1; endSlot = -1;
/* 132 */         mazeVisited = new boolean[54];
/*     */         
/* 134 */         for (Slot slot : invSlots) {
/* 135 */           if (startSlot >= 0 && endSlot >= 0)
/* 136 */             break;  if (slot.field_75224_c == Shady.mc.field_71439_g.field_71071_by)
/* 137 */             continue;  ItemStack itemStack = slot.func_75211_c();
/* 138 */           if (itemStack != null && 
/* 139 */             itemStack.func_77973_b() == Item.func_150898_a((Block)Blocks.field_150397_co)) {
/* 140 */             if (itemStack.func_77952_i() == 5) {
/* 141 */               startSlot = slot.field_75222_d; continue;
/* 142 */             }  if (itemStack.func_77952_i() == 14) {
/* 143 */               endSlot = slot.field_75222_d;
/*     */             }
/*     */           } 
/*     */         } 
/*     */         
/* 148 */         while (startSlot != endSlot) {
/* 149 */           boolean newSlotChosen = false;
/* 150 */           for (int j = 0; j < 4; j++) {
/* 151 */             int slotNumber = startSlot + mazeDirection[j];
/* 152 */             if (slotNumber == endSlot) return false; 
/* 153 */             if (slotNumber >= 0 && slotNumber <= 53 && (j != 1 || slotNumber % 9 != 8) && (j != 2 || slotNumber % 9 != 0) && 
/* 154 */               !mazeVisited[slotNumber]) {
/* 155 */               ItemStack itemStack = ((Slot)invSlots.get(slotNumber)).func_75211_c();
/* 156 */               if (itemStack != null && 
/* 157 */                 itemStack.func_77973_b() == Item.func_150898_a((Block)Blocks.field_150397_co) && itemStack.func_77952_i() == 0) {
/* 158 */                 clickQueue.add(invSlots.get(slotNumber));
/* 159 */                 startSlot = slotNumber;
/* 160 */                 mazeVisited[slotNumber] = true;
/* 161 */                 newSlotChosen = true;
/*     */                 break;
/*     */               } 
/*     */             } 
/*     */           } 
/* 166 */           if (!newSlotChosen) {
/* 167 */             System.out.println("Maze calculation aborted");
/* 168 */             return true;
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       
/*     */       case NUMBERS:
/* 174 */         for (; clickQueue.size() < 14; clickQueue.add(null));
/* 175 */         for (i = 10; i <= 25; i++) {
/* 176 */           if (i != 17 && i != 18) {
/* 177 */             ItemStack itemStack = ((Slot)invSlots.get(i)).func_75211_c();
/* 178 */             if (itemStack != null && 
/* 179 */               itemStack.func_77973_b() == Item.func_150898_a((Block)Blocks.field_150397_co) && itemStack.func_77952_i() == 14 && itemStack.field_77994_a < 15)
/* 180 */               clickQueue.set(itemStack.field_77994_a - 1, invSlots.get(i)); 
/*     */           } 
/*     */         } 
/* 183 */         if (clickQueue.removeIf(Objects::isNull)) return true;
/*     */         
/*     */         break;
/*     */       case CORRECT_ALL:
/* 187 */         for (Slot slot : invSlots) {
/* 188 */           if (slot.field_75224_c == Shady.mc.field_71439_g.field_71071_by || 
/* 189 */             slot.field_75222_d < 9 || slot.field_75222_d > 35 || slot.field_75222_d % 9 <= 1 || slot.field_75222_d % 9 >= 7)
/*     */             continue; 
/* 191 */           ItemStack itemStack = slot.func_75211_c();
/* 192 */           if (itemStack == null) return true; 
/* 193 */           if (itemStack.func_77973_b() == Item.func_150898_a((Block)Blocks.field_150397_co) && itemStack.func_77952_i() == 14) {
/* 194 */             clickQueue.add(slot);
/*     */           }
/*     */         } 
/*     */         break;
/*     */       
/*     */       case LETTER:
/* 200 */         letterNeeded = chestName.charAt(chestName.indexOf("'") + 1);
/* 201 */         if (letterNeeded != '\000') {
/* 202 */           for (Slot slot : invSlots) {
/* 203 */             if (slot.field_75224_c == Shady.mc.field_71439_g.field_71071_by || 
/* 204 */               slot.field_75222_d < 9 || slot.field_75222_d > 44 || slot.field_75222_d % 9 == 0 || slot.field_75222_d % 9 == 8)
/*     */               continue; 
/* 206 */             ItemStack itemStack = slot.func_75211_c();
/* 207 */             if (itemStack == null) return true; 
/* 208 */             if (!itemStack.func_77948_v() && 
/* 209 */               StringUtils.func_76338_a(itemStack.func_82833_r()).charAt(0) == letterNeeded) {
/* 210 */               clickQueue.add(slot);
/*     */             }
/*     */           } 
/*     */         }
/*     */         break;
/*     */ 
/*     */       
/*     */       case COLOR:
/* 218 */         for (EnumDyeColor color : EnumDyeColor.values()) {
/* 219 */           String colorName = color.func_176610_l().replaceAll("_", " ").toUpperCase();
/* 220 */           if (chestName.contains(colorName)) {
/* 221 */             colorNeeded = color.func_176762_d();
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/* 226 */         if (colorNeeded != null) {
/* 227 */           for (Slot slot : invSlots) {
/* 228 */             if (slot.field_75224_c == Shady.mc.field_71439_g.field_71071_by || 
/* 229 */               slot.field_75222_d < 9 || slot.field_75222_d > 44 || slot.field_75222_d % 9 == 0 || slot.field_75222_d % 9 == 8)
/*     */               continue; 
/* 231 */             ItemStack itemStack = slot.func_75211_c();
/* 232 */             if (itemStack == null) return true; 
/* 233 */             if (!itemStack.func_77948_v() && 
/* 234 */               itemStack.func_77977_a().contains(colorNeeded)) {
/* 235 */               clickQueue.add(slot);
/*     */             }
/*     */           } 
/*     */         }
/*     */         break;
/*     */     } 
/* 241 */     return false;
/*     */   }
/*     */   
/*     */   private void clickSlot(Slot slot) {
/* 245 */     if (windowClicks == 0) windowId = Shady.mc.field_71439_g.field_71070_bA.field_75152_c; 
/* 246 */     if (testing) {
/* 247 */       Shady.mc.field_71442_b.func_78753_a(windowId + windowClicks, slot.field_75222_d, 0, 1, (EntityPlayer)Shady.mc.field_71439_g);
/*     */     } else {
/* 249 */       Shady.mc.field_71442_b.func_78753_a(windowId + windowClicks, slot.field_75222_d, 2, 0, (EntityPlayer)Shady.mc.field_71439_g);
/*     */     } 
/* 251 */     lastClickTime = System.currentTimeMillis();
/*     */     
/* 253 */     if (Config.terminalPingless) {
/* 254 */       windowClicks++;
/* 255 */       clickQueue.remove(slot);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Desktop\ShadyAddons-2.1.0-pre2.jar!\cheaters\get\banned\features\AutoTerminals.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */