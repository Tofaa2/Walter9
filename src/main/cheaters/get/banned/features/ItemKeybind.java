/*     */ package cheaters.get.banned.features;
/*     */ 
/*     */ import cheaters.get.banned.Shady;
/*     */ import cheaters.get.banned.config.Config;
/*     */ import cheaters.get.banned.utils.KeybindUtils;
/*     */ import cheaters.get.banned.utils.Utils;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.gameevent.InputEvent;
/*     */ 
/*     */ public class ItemKeybind {
/*     */   public ItemKeybind() {
/*  17 */     KeybindUtils.register("Use Ice Spray", 0);
/*  18 */     KeybindUtils.register("Use Power Orb", 0);
/*  19 */     KeybindUtils.register("Use Weird Tuba", 0);
/*  20 */     KeybindUtils.register("Use Gyrokinetic Wand", 0);
/*  21 */     KeybindUtils.register("Use Pigman Sword", 0);
/*  22 */     KeybindUtils.register("Use Healing Wand", 0);
/*  23 */     KeybindUtils.register("Use Rogue Sword", 0);
/*  24 */     KeybindUtils.register("Use Fishing Rod", 0);
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onInput(InputEvent.KeyInputEvent event) {
/*  29 */     if (Config.iceSprayHotkey && KeybindUtils.isPressed("Use Ice Spray") && 
/*  30 */       !useSkyBlockItem("ICE_SPRAY_WAND", true)) {
/*  31 */       sendMissingItemMessage("Ice Spray Wand");
/*     */     }
/*     */ 
/*     */     
/*  35 */     if (Config.powerOrbHotkey && KeybindUtils.isPressed("Use Power Orb") && 
/*  36 */       !useSkyBlockItem("PLASMAFLUX_POWER_ORB", true) && !useSkyBlockItem("OVERFLUX_POWER_ORB", true) && !useSkyBlockItem("MANA_FLUX_POWER_ORB", true) && !useSkyBlockItem("RADIANT_POWER_ORB", true)) {
/*  37 */       sendMissingItemMessage("Power Orb");
/*     */     }
/*     */ 
/*     */     
/*  41 */     if (Config.weirdTubaHotkey && KeybindUtils.isPressed("Use Weird Tuba") && 
/*  42 */       !useSkyBlockItem("WEIRD_TUBA", true)) {
/*  43 */       sendMissingItemMessage("Weird Tuba");
/*     */     }
/*     */ 
/*     */     
/*  47 */     if (Config.gyrokineticWandHotkey && KeybindUtils.isPressed("Use Gyrokinetic Wand") && 
/*  48 */       !useSkyBlockItem("GYROKINETIC_WAND", false)) {
/*  49 */       sendMissingItemMessage("Gyrokinetic Wand");
/*     */     }
/*     */ 
/*     */     
/*  53 */     if (Config.pigmanSwordHotkey && KeybindUtils.isPressed("Use Pigman Sword") && 
/*  54 */       !useSkyBlockItem("PIGMAN_SWORD", true)) {
/*  55 */       sendMissingItemMessage("Pigman Sword");
/*     */     }
/*     */ 
/*     */     
/*  59 */     if (Config.healingWandHotkey && KeybindUtils.isPressed("Use Healing Wand") && 
/*  60 */       !useSkyBlockItem("WAND_OF_ATONEMENT", true) && !useSkyBlockItem("WAND_OF_RESTORATION", true) && !useSkyBlockItem("WAND_OF_MENDING", true) && !useSkyBlockItem("WAND_OF_HEALING", true)) {
/*  61 */       sendMissingItemMessage("Healing Wand");
/*     */     }
/*     */ 
/*     */     
/*  65 */     if (Config.rogueSwordHotkey && KeybindUtils.isPressed("Use Rogue Sword") && 
/*  66 */       !useRogueSword()) {
/*  67 */       sendMissingItemMessage("Rogue Sword");
/*     */     }
/*     */ 
/*     */     
/*  71 */     if (Config.fishingRodHotkey && KeybindUtils.isPressed("Use Fishing Rod") && 
/*  72 */       !useVanillaItem((Item)Items.field_151112_aM)) {
/*  73 */       sendMissingItemMessage("Fishing Rod");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean useSkyBlockItem(String itemId, boolean rightClick) {
/*  79 */     for (int i = 0; i < 9; i++) {
/*  80 */       ItemStack item = Shady.mc.field_71439_g.field_71071_by.func_70301_a(i);
/*  81 */       if (itemId.equals(Utils.getSkyBlockID(item))) {
/*  82 */         int previousItem = Shady.mc.field_71439_g.field_71071_by.field_70461_c;
/*  83 */         Shady.mc.field_71439_g.field_71071_by.field_70461_c = i;
/*  84 */         if (rightClick) {
/*  85 */           Shady.mc.field_71442_b.func_78769_a((EntityPlayer)Shady.mc.field_71439_g, (World)Shady.mc.field_71441_e, item);
/*     */         } else {
/*  87 */           KeybindUtils.leftClick();
/*     */         } 
/*  89 */         Shady.mc.field_71439_g.field_71071_by.field_70461_c = previousItem;
/*  90 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/*  94 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean useRogueSword() {
/*  98 */     for (int i = 0; i < 9; i++) {
/*  99 */       ItemStack item = Shady.mc.field_71439_g.field_71071_by.func_70301_a(i);
/* 100 */       if ("ROGUE_SWORD".equals(Utils.getSkyBlockID(item))) {
/* 101 */         int previousItem = Shady.mc.field_71439_g.field_71071_by.field_70461_c;
/* 102 */         Shady.mc.field_71439_g.field_71071_by.field_70461_c = i;
/* 103 */         for (int j = 0; j < 40; j++) {
/* 104 */           Shady.mc.field_71442_b.func_78769_a((EntityPlayer)Shady.mc.field_71439_g, (World)Shady.mc.field_71441_e, item);
/*     */         }
/* 106 */         Shady.mc.field_71439_g.field_71071_by.field_70461_c = previousItem;
/* 107 */         return true;
/*     */       } 
/*     */     } 
/* 110 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean useVanillaItem(Item item) {
/* 114 */     for (int i = 0; i < 9; i++) {
/* 115 */       ItemStack itemStack = Shady.mc.field_71439_g.field_71071_by.func_70301_a(i);
/* 116 */       if (itemStack.func_77973_b() == item) {
/* 117 */         int previousItem = Shady.mc.field_71439_g.field_71071_by.field_70461_c;
/* 118 */         Shady.mc.field_71439_g.field_71071_by.field_70461_c = i;
/* 119 */         Shady.mc.field_71442_b.func_78769_a((EntityPlayer)Shady.mc.field_71439_g, (World)Shady.mc.field_71441_e, itemStack);
/* 120 */         Shady.mc.field_71439_g.field_71071_by.field_70461_c = previousItem;
/* 121 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 125 */     return false;
/*     */   }
/*     */   
/*     */   private static void sendMissingItemMessage(String itemName) {
/* 129 */     Utils.sendModMessage("No &9" + itemName + "&r found in your hotbar");
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Desktop\ShadyAddons-2.1.0-pre2.jar!\cheaters\get\banned\features\ItemKeybind.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */