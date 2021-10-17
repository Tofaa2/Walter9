/*     */ package cheaters.get.banned.utils;
/*     */ import cheaters.get.banned.Shady;
/*     */ import cheaters.get.banned.events.TickEndEvent;
/*     */ import java.awt.Color;
/*     */ import java.awt.Desktop;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.datatransfer.StringSelection;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Calendar;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.scoreboard.ScoreObjective;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import org.lwjgl.util.vector.Vector3f;
/*     */ 
/*     */ public class Utils {
/*     */   public static boolean inSkyBlock = false;
/*     */   public static boolean inDungeon = false;
/*     */   
/*     */   public static int romanToInt(String s) {
/*  32 */     Map<Character, Integer> numerals = new HashMap<>();
/*  33 */     numerals.put(Character.valueOf('I'), Integer.valueOf(1));
/*  34 */     numerals.put(Character.valueOf('V'), Integer.valueOf(5));
/*  35 */     numerals.put(Character.valueOf('X'), Integer.valueOf(10));
/*  36 */     numerals.put(Character.valueOf('L'), Integer.valueOf(50));
/*  37 */     numerals.put(Character.valueOf('C'), Integer.valueOf(100));
/*  38 */     numerals.put(Character.valueOf('D'), Integer.valueOf(500));
/*  39 */     numerals.put(Character.valueOf('M'), Integer.valueOf(1000));
/*     */     
/*  41 */     int result = 0;
/*  42 */     for (int i = 0; i < s.length(); i++) {
/*  43 */       int add = ((Integer)numerals.get(Character.valueOf(s.charAt(i)))).intValue();
/*  44 */       if (i < s.length() - 1) {
/*  45 */         int next = ((Integer)numerals.get(Character.valueOf(s.charAt(i + 1)))).intValue();
/*  46 */         if (next / add == 5 || next / add == 10) {
/*  47 */           add = next - add;
/*  48 */           i++;
/*     */         } 
/*     */       } 
/*  51 */       result += add;
/*     */     } 
/*     */     
/*  54 */     return result;
/*     */   }
/*     */   public static boolean forceSkyBlock = false; public static boolean forceDungeon = false;
/*     */   public static String getLogo() {
/*  58 */     ArrayList<String> logos = new ArrayList<>(Arrays.asList(new String[] { "logo-fsr", "logo-badlion", "logo-impact", "logo-sbe", "logo-skytils", "logo-pride", "logo-lunar" }));
/*     */     
/*  60 */     int month = Calendar.getInstance().get(2);
/*  61 */     int day = Calendar.getInstance().get(5);
/*     */ 
/*     */     
/*  64 */     if (month == 11 && day > 20) return "logo-christmas";
/*     */     
/*  66 */     if (month == 9 && day > 25) return "logo-halloween";
/*     */     
/*  68 */     if (month == 9 && day == 11) return "logo-pride";
/*     */ 
/*     */     
/*  71 */     if (Math.random() < 0.8D) return "logo";
/*     */     
/*  73 */     return (String)getRandomItem(logos);
/*     */   }
/*     */   
/*     */   public static Object getRandomItem(List<?> list) {
/*  77 */     return list.get((new Random()).nextInt(list.size()));
/*     */   }
/*     */   
/*     */   public static void openUrl(String url) {
/*     */     try {
/*  82 */       Desktop.getDesktop().browse(new URI(url));
/*  83 */     } catch (Exception exception) {}
/*     */   }
/*     */   
/*     */   public static String removeFormatting(String input) {
/*  87 */     return input.replaceAll("§[0-9a-fk-or]", "");
/*     */   }
/*     */   
/*     */   public static String getSkyBlockID(ItemStack item) {
/*  91 */     if (item != null) {
/*  92 */       NBTTagCompound extraAttributes = item.func_179543_a("ExtraAttributes", false);
/*  93 */       if (extraAttributes != null && extraAttributes.func_74764_b("id")) {
/*  94 */         return extraAttributes.func_74779_i("id");
/*     */       }
/*     */     } 
/*  97 */     return "";
/*     */   }
/*     */   
/*     */   public static boolean facingBlock(BlockPos block, float range) {
/* 101 */     float stepSize = 0.15F;
/* 102 */     if (Shady.mc.field_71439_g != null && Shady.mc.field_71441_e != null) {
/* 103 */       Vector3f position = new Vector3f((float)Shady.mc.field_71439_g.field_70165_t, (float)Shady.mc.field_71439_g.field_70163_u + Shady.mc.field_71439_g.func_70047_e(), (float)Shady.mc.field_71439_g.field_70161_v);
/*     */       
/* 105 */       Vec3 look = Shady.mc.field_71439_g.func_70676_i(0.0F);
/*     */       
/* 107 */       Vector3f step = new Vector3f((float)look.field_72450_a, (float)look.field_72448_b, (float)look.field_72449_c);
/* 108 */       step.scale(stepSize / step.length());
/*     */       
/* 110 */       for (int i = 0; i < Math.floor((range / stepSize)) - 2.0D; i++) {
/* 111 */         BlockPos blockAtPos = new BlockPos(position.x, position.y, position.z);
/* 112 */         if (blockAtPos.equals(block)) return true; 
/* 113 */         position.translate(step.x, step.y, step.z);
/*     */       } 
/*     */     } 
/* 116 */     return false;
/*     */   }
/*     */   
/*     */   public static List<String> getLore(ItemStack item) {
/* 120 */     if (item != null) {
/* 121 */       return item.func_82840_a((EntityPlayer)Shady.mc.field_71439_g, false);
/*     */     }
/* 123 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getInventoryName() {
/* 128 */     if (Shady.mc.field_71439_g == null || Shady.mc.field_71441_e == null) return "null"; 
/* 129 */     String inventoryName = ((Slot)Shady.mc.field_71439_g.field_71070_bA.field_75151_b.get(0)).field_75224_c.func_70005_c_();
/* 130 */     if (inventoryName == null) return "null"; 
/* 131 */     return inventoryName;
/*     */   }
/*     */   
/*     */   public static void sendMessage(String message) {
/* 135 */     if (Shady.mc.field_71439_g != null && Shady.mc.field_71441_e != null) {
/* 136 */       if (!message.contains("§")) {
/* 137 */         message = message.replace("&", "§");
/*     */       }
/* 139 */       Shady.mc.field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(message));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void sendMessageAsPlayer(String message) {
/* 144 */     Shady.mc.field_71439_g.func_71165_d(message);
/*     */   }
/*     */   
/*     */   public static void sendModMessage(String message) {
/* 148 */     if (message.contains("§")) {
/* 149 */       sendMessage("§cShadyAddons > §f" + message);
/*     */     } else {
/* 151 */       sendMessage("&cShadyAddons > &f" + message);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Color addAlpha(Color color, int alpha) {
/* 156 */     return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
/*     */   }
/*     */   
/*     */   public static boolean isInteractable(Block block) {
/* 160 */     return (new ArrayList(Arrays.asList((Object[])new Block[] { (Block)Blocks.field_150486_ae, Blocks.field_150442_at, Blocks.field_150447_bR, Blocks.field_150471_bO, Blocks.field_150430_aB, (Block)Blocks.field_150465_bP
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 167 */           }))).contains(block);
/*     */   }
/*     */   
/*     */   public static void copyToClipboard(String text) {
/* 171 */     Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(text), null);
/*     */   }
/*     */   
/*     */   public static <T> T firstOrNull(Iterable<T> iterable) {
/* 175 */     return (T)Iterables.getFirst(iterable, null);
/*     */   }
/*     */   
/*     */   @SafeVarargs
/*     */   public static <T> T firstNotNull(T... args) {
/* 180 */     for (T arg : args) {
/* 181 */       if (arg != null) {
/* 182 */         return arg;
/*     */       }
/*     */     } 
/* 185 */     return null;
/*     */   }
/*     */   
/* 188 */   private int ticks = 0;
/*     */   @SubscribeEvent
/*     */   public void onTick(TickEndEvent event) {
/* 191 */     if (forceDungeon || forceSkyBlock) {
/* 192 */       if (forceSkyBlock) inSkyBlock = true; 
/* 193 */       if (forceDungeon) inSkyBlock = true;  inDungeon = true;
/*     */       
/*     */       return;
/*     */     } 
/* 197 */     if (this.ticks % 20 == 0) {
/* 198 */       if (Shady.mc.field_71439_g != null && Shady.mc.field_71441_e != null) {
/* 199 */         ScoreObjective scoreboardObj = Shady.mc.field_71441_e.func_96441_U().func_96539_a(1);
/* 200 */         if (scoreboardObj != null) {
/* 201 */           inSkyBlock = removeFormatting(scoreboardObj.func_96678_d()).contains("SKYBLOCK");
/*     */         }
/*     */         
/* 204 */         inDungeon = (inSkyBlock && ScoreboardUtils.scoreboardContains("The Catacombs"));
/*     */       } 
/* 206 */       this.ticks = 0;
/*     */     } 
/* 208 */     this.ticks++;
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onWorldLoad(WorldEvent.Load event) {
/* 213 */     forceDungeon = false;
/* 214 */     forceSkyBlock = false;
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\cheaters\get\banne\\utils\Utils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */