/*     */ package io.github.quantizr.dungeonrooms.dungeons.catacombs;
/*     */ 
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonObject;
/*     */ import io.github.quantizr.dungeonrooms.DungeonRooms;
/*     */ import io.github.quantizr.dungeonrooms.events.PacketEvent;
/*     */ import io.github.quantizr.dungeonrooms.utils.MapUtils;
/*     */ import io.github.quantizr.dungeonrooms.utils.Utils;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.renderer.culling.Frustum;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.network.play.server.S0DPacketCollectItem;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.StringUtils;
/*     */ import net.minecraftforge.client.event.ClientChatReceivedEvent;
/*     */ import net.minecraftforge.client.event.RenderWorldLastEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerInteractEvent;
/*     */ import net.minecraftforge.fml.client.FMLClientHandler;
/*     */ import net.minecraftforge.fml.common.eventhandler.EventPriority;
/*     */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.gameevent.InputEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Waypoints
/*     */ {
/*     */   public static boolean enabled = true;
/*     */   public static boolean showEntrance = true;
/*     */   public static boolean showSuperboom = true;
/*     */   public static boolean showSecrets = true;
/*     */   public static boolean showFairySouls = true;
/*     */   public static boolean sneakToDisable = true;
/*     */   public static boolean disableWhenAllFound = true;
/*     */   public static boolean allFound = false;
/*     */   public static boolean showWaypointText = true;
/*     */   public static boolean showBoundingBox = true;
/*     */   public static boolean showBeacon = true;
/*     */   public static boolean practiceModeOn = false;
/*  71 */   public static int secretNum = 0;
/*  72 */   public static int completedSecrets = 0;
/*     */   
/*  74 */   public static Map<String, List<Boolean>> allSecretsMap = new HashMap<>();
/*  75 */   public static List<Boolean> secretsList = new ArrayList<>(Arrays.asList(new Boolean[10]));
/*     */   
/*  77 */   static long lastSneakTime = 0L;
/*     */   
/*  79 */   Frustum frustum = new Frustum();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onWorldRender(RenderWorldLastEvent event) {
/*     */     // Byte code:
/*     */     //   0: getstatic io/github/quantizr/dungeonrooms/dungeons/catacombs/Waypoints.enabled : Z
/*     */     //   3: ifne -> 7
/*     */     //   6: return
/*     */     //   7: getstatic io/github/quantizr/dungeonrooms/dungeons/catacombs/Waypoints.practiceModeOn : Z
/*     */     //   10: ifeq -> 25
/*     */     //   13: getstatic io/github/quantizr/dungeonrooms/DungeonRooms.keyBindings : [Lnet/minecraft/client/settings/KeyBinding;
/*     */     //   16: iconst_2
/*     */     //   17: aaload
/*     */     //   18: invokevirtual func_151470_d : ()Z
/*     */     //   21: ifne -> 25
/*     */     //   24: return
/*     */     //   25: getstatic io/github/quantizr/dungeonrooms/dungeons/catacombs/RoomDetection.roomName : Ljava/lang/String;
/*     */     //   28: astore_2
/*     */     //   29: aload_2
/*     */     //   30: ldc 'undefined'
/*     */     //   32: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   35: ifne -> 54
/*     */     //   38: getstatic io/github/quantizr/dungeonrooms/DungeonRooms.roomsJson : Lcom/google/gson/JsonObject;
/*     */     //   41: aload_2
/*     */     //   42: invokevirtual get : (Ljava/lang/String;)Lcom/google/gson/JsonElement;
/*     */     //   45: ifnull -> 54
/*     */     //   48: getstatic io/github/quantizr/dungeonrooms/dungeons/catacombs/Waypoints.secretsList : Ljava/util/List;
/*     */     //   51: ifnonnull -> 55
/*     */     //   54: return
/*     */     //   55: getstatic io/github/quantizr/dungeonrooms/DungeonRooms.waypointsJson : Lcom/google/gson/JsonObject;
/*     */     //   58: aload_2
/*     */     //   59: invokevirtual get : (Ljava/lang/String;)Lcom/google/gson/JsonElement;
/*     */     //   62: ifnull -> 1176
/*     */     //   65: getstatic io/github/quantizr/dungeonrooms/DungeonRooms.waypointsJson : Lcom/google/gson/JsonObject;
/*     */     //   68: aload_2
/*     */     //   69: invokevirtual get : (Ljava/lang/String;)Lcom/google/gson/JsonElement;
/*     */     //   72: invokevirtual getAsJsonArray : ()Lcom/google/gson/JsonArray;
/*     */     //   75: astore_3
/*     */     //   76: aload_3
/*     */     //   77: invokevirtual size : ()I
/*     */     //   80: istore #4
/*     */     //   82: iconst_0
/*     */     //   83: istore #5
/*     */     //   85: iload #5
/*     */     //   87: iload #4
/*     */     //   89: if_icmpge -> 1176
/*     */     //   92: aload_3
/*     */     //   93: iload #5
/*     */     //   95: invokevirtual get : (I)Lcom/google/gson/JsonElement;
/*     */     //   98: invokevirtual getAsJsonObject : ()Lcom/google/gson/JsonObject;
/*     */     //   101: astore #6
/*     */     //   103: iconst_1
/*     */     //   104: istore #7
/*     */     //   106: iconst_1
/*     */     //   107: istore #8
/*     */     //   109: iload #8
/*     */     //   111: getstatic io/github/quantizr/dungeonrooms/dungeons/catacombs/Waypoints.secretNum : I
/*     */     //   114: if_icmpgt -> 183
/*     */     //   117: getstatic io/github/quantizr/dungeonrooms/dungeons/catacombs/Waypoints.secretsList : Ljava/util/List;
/*     */     //   120: iload #8
/*     */     //   122: iconst_1
/*     */     //   123: isub
/*     */     //   124: invokeinterface get : (I)Ljava/lang/Object;
/*     */     //   129: checkcast java/lang/Boolean
/*     */     //   132: invokevirtual booleanValue : ()Z
/*     */     //   135: ifne -> 177
/*     */     //   138: aload #6
/*     */     //   140: ldc 'secretName'
/*     */     //   142: invokevirtual get : (Ljava/lang/String;)Lcom/google/gson/JsonElement;
/*     */     //   145: invokevirtual getAsString : ()Ljava/lang/String;
/*     */     //   148: iconst_0
/*     */     //   149: iconst_2
/*     */     //   150: invokevirtual substring : (II)Ljava/lang/String;
/*     */     //   153: ldc '[\D]'
/*     */     //   155: ldc ''
/*     */     //   157: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
/*     */     //   160: iload #8
/*     */     //   162: invokestatic valueOf : (I)Ljava/lang/String;
/*     */     //   165: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   168: ifeq -> 177
/*     */     //   171: iconst_0
/*     */     //   172: istore #7
/*     */     //   174: goto -> 183
/*     */     //   177: iinc #8, 1
/*     */     //   180: goto -> 109
/*     */     //   183: iload #7
/*     */     //   185: ifne -> 191
/*     */     //   188: goto -> 1170
/*     */     //   191: getstatic io/github/quantizr/dungeonrooms/dungeons/catacombs/Waypoints.disableWhenAllFound : Z
/*     */     //   194: ifeq -> 224
/*     */     //   197: getstatic io/github/quantizr/dungeonrooms/dungeons/catacombs/Waypoints.allFound : Z
/*     */     //   200: ifeq -> 224
/*     */     //   203: aload #6
/*     */     //   205: ldc 'category'
/*     */     //   207: invokevirtual get : (Ljava/lang/String;)Lcom/google/gson/JsonElement;
/*     */     //   210: invokevirtual getAsString : ()Ljava/lang/String;
/*     */     //   213: ldc 'fairysoul'
/*     */     //   215: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   218: ifne -> 224
/*     */     //   221: goto -> 1170
/*     */     //   224: new net/minecraft/util/BlockPos
/*     */     //   227: dup
/*     */     //   228: aload #6
/*     */     //   230: ldc 'x'
/*     */     //   232: invokevirtual get : (Ljava/lang/String;)Lcom/google/gson/JsonElement;
/*     */     //   235: invokevirtual getAsInt : ()I
/*     */     //   238: aload #6
/*     */     //   240: ldc 'y'
/*     */     //   242: invokevirtual get : (Ljava/lang/String;)Lcom/google/gson/JsonElement;
/*     */     //   245: invokevirtual getAsInt : ()I
/*     */     //   248: aload #6
/*     */     //   250: ldc 'z'
/*     */     //   252: invokevirtual get : (Ljava/lang/String;)Lcom/google/gson/JsonElement;
/*     */     //   255: invokevirtual getAsInt : ()I
/*     */     //   258: invokespecial <init> : (III)V
/*     */     //   261: astore #8
/*     */     //   263: aload #8
/*     */     //   265: getstatic io/github/quantizr/dungeonrooms/dungeons/catacombs/RoomDetection.roomDirection : Ljava/lang/String;
/*     */     //   268: getstatic io/github/quantizr/dungeonrooms/dungeons/catacombs/RoomDetection.roomCorner : Ljava/awt/Point;
/*     */     //   271: invokestatic relativeToActual : (Lnet/minecraft/util/BlockPos;Ljava/lang/String;Ljava/awt/Point;)Lnet/minecraft/util/BlockPos;
/*     */     //   274: astore #9
/*     */     //   276: invokestatic func_71410_x : ()Lnet/minecraft/client/Minecraft;
/*     */     //   279: invokevirtual func_175606_aa : ()Lnet/minecraft/entity/Entity;
/*     */     //   282: astore #10
/*     */     //   284: aload_0
/*     */     //   285: getfield frustum : Lnet/minecraft/client/renderer/culling/Frustum;
/*     */     //   288: aload #10
/*     */     //   290: getfield field_70165_t : D
/*     */     //   293: aload #10
/*     */     //   295: getfield field_70163_u : D
/*     */     //   298: aload #10
/*     */     //   300: getfield field_70161_v : D
/*     */     //   303: invokevirtual func_78547_a : (DDD)V
/*     */     //   306: aload_0
/*     */     //   307: getfield frustum : Lnet/minecraft/client/renderer/culling/Frustum;
/*     */     //   310: aload #9
/*     */     //   312: invokevirtual func_177958_n : ()I
/*     */     //   315: i2d
/*     */     //   316: aload #9
/*     */     //   318: invokevirtual func_177956_o : ()I
/*     */     //   321: i2d
/*     */     //   322: aload #9
/*     */     //   324: invokevirtual func_177952_p : ()I
/*     */     //   327: i2d
/*     */     //   328: aload #9
/*     */     //   330: invokevirtual func_177958_n : ()I
/*     */     //   333: iconst_1
/*     */     //   334: iadd
/*     */     //   335: i2d
/*     */     //   336: ldc2_w 255.0
/*     */     //   339: aload #9
/*     */     //   341: invokevirtual func_177952_p : ()I
/*     */     //   344: iconst_1
/*     */     //   345: iadd
/*     */     //   346: i2d
/*     */     //   347: invokevirtual func_78548_b : (DDDDDD)Z
/*     */     //   350: ifne -> 356
/*     */     //   353: goto -> 1170
/*     */     //   356: aload #6
/*     */     //   358: ldc 'category'
/*     */     //   360: invokevirtual get : (Ljava/lang/String;)Lcom/google/gson/JsonElement;
/*     */     //   363: invokevirtual getAsString : ()Ljava/lang/String;
/*     */     //   366: astore #12
/*     */     //   368: iconst_m1
/*     */     //   369: istore #13
/*     */     //   371: aload #12
/*     */     //   373: invokevirtual hashCode : ()I
/*     */     //   376: lookupswitch default -> 579, -2094363978 -> 452, -787569677 -> 532, -345239224 -> 565, -332434202 -> 468, 97301 -> 516, 3242771 -> 500, 94627585 -> 484, 102865802 -> 548
/*     */     //   452: aload #12
/*     */     //   454: ldc 'entrance'
/*     */     //   456: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   459: ifeq -> 579
/*     */     //   462: iconst_0
/*     */     //   463: istore #13
/*     */     //   465: goto -> 579
/*     */     //   468: aload #12
/*     */     //   470: ldc 'superboom'
/*     */     //   472: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   475: ifeq -> 579
/*     */     //   478: iconst_1
/*     */     //   479: istore #13
/*     */     //   481: goto -> 579
/*     */     //   484: aload #12
/*     */     //   486: ldc 'chest'
/*     */     //   488: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   491: ifeq -> 579
/*     */     //   494: iconst_2
/*     */     //   495: istore #13
/*     */     //   497: goto -> 579
/*     */     //   500: aload #12
/*     */     //   502: ldc 'item'
/*     */     //   504: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   507: ifeq -> 579
/*     */     //   510: iconst_3
/*     */     //   511: istore #13
/*     */     //   513: goto -> 579
/*     */     //   516: aload #12
/*     */     //   518: ldc 'bat'
/*     */     //   520: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   523: ifeq -> 579
/*     */     //   526: iconst_4
/*     */     //   527: istore #13
/*     */     //   529: goto -> 579
/*     */     //   532: aload #12
/*     */     //   534: ldc 'wither'
/*     */     //   536: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   539: ifeq -> 579
/*     */     //   542: iconst_5
/*     */     //   543: istore #13
/*     */     //   545: goto -> 579
/*     */     //   548: aload #12
/*     */     //   550: ldc 'lever'
/*     */     //   552: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   555: ifeq -> 579
/*     */     //   558: bipush #6
/*     */     //   560: istore #13
/*     */     //   562: goto -> 579
/*     */     //   565: aload #12
/*     */     //   567: ldc 'fairysoul'
/*     */     //   569: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   572: ifeq -> 579
/*     */     //   575: bipush #7
/*     */     //   577: istore #13
/*     */     //   579: iload #13
/*     */     //   581: tableswitch default -> 846, 0 -> 628, 1 -> 654, 2 -> 680, 3 -> 708, 4 -> 735, 5 -> 762, 6 -> 789, 7 -> 817
/*     */     //   628: getstatic io/github/quantizr/dungeonrooms/dungeons/catacombs/Waypoints.showEntrance : Z
/*     */     //   631: ifne -> 637
/*     */     //   634: goto -> 1170
/*     */     //   637: new java/awt/Color
/*     */     //   640: dup
/*     */     //   641: iconst_0
/*     */     //   642: sipush #255
/*     */     //   645: iconst_0
/*     */     //   646: invokespecial <init> : (III)V
/*     */     //   649: astore #11
/*     */     //   651: goto -> 864
/*     */     //   654: getstatic io/github/quantizr/dungeonrooms/dungeons/catacombs/Waypoints.showSuperboom : Z
/*     */     //   657: ifne -> 663
/*     */     //   660: goto -> 1170
/*     */     //   663: new java/awt/Color
/*     */     //   666: dup
/*     */     //   667: sipush #255
/*     */     //   670: iconst_0
/*     */     //   671: iconst_0
/*     */     //   672: invokespecial <init> : (III)V
/*     */     //   675: astore #11
/*     */     //   677: goto -> 864
/*     */     //   680: getstatic io/github/quantizr/dungeonrooms/dungeons/catacombs/Waypoints.showSecrets : Z
/*     */     //   683: ifne -> 689
/*     */     //   686: goto -> 1170
/*     */     //   689: new java/awt/Color
/*     */     //   692: dup
/*     */     //   693: iconst_2
/*     */     //   694: sipush #213
/*     */     //   697: sipush #250
/*     */     //   700: invokespecial <init> : (III)V
/*     */     //   703: astore #11
/*     */     //   705: goto -> 864
/*     */     //   708: getstatic io/github/quantizr/dungeonrooms/dungeons/catacombs/Waypoints.showSecrets : Z
/*     */     //   711: ifne -> 717
/*     */     //   714: goto -> 1170
/*     */     //   717: new java/awt/Color
/*     */     //   720: dup
/*     */     //   721: iconst_2
/*     */     //   722: bipush #64
/*     */     //   724: sipush #250
/*     */     //   727: invokespecial <init> : (III)V
/*     */     //   730: astore #11
/*     */     //   732: goto -> 864
/*     */     //   735: getstatic io/github/quantizr/dungeonrooms/dungeons/catacombs/Waypoints.showSecrets : Z
/*     */     //   738: ifne -> 744
/*     */     //   741: goto -> 1170
/*     */     //   744: new java/awt/Color
/*     */     //   747: dup
/*     */     //   748: sipush #142
/*     */     //   751: bipush #66
/*     */     //   753: iconst_0
/*     */     //   754: invokespecial <init> : (III)V
/*     */     //   757: astore #11
/*     */     //   759: goto -> 864
/*     */     //   762: getstatic io/github/quantizr/dungeonrooms/dungeons/catacombs/Waypoints.showSecrets : Z
/*     */     //   765: ifne -> 771
/*     */     //   768: goto -> 1170
/*     */     //   771: new java/awt/Color
/*     */     //   774: dup
/*     */     //   775: bipush #30
/*     */     //   777: bipush #30
/*     */     //   779: bipush #30
/*     */     //   781: invokespecial <init> : (III)V
/*     */     //   784: astore #11
/*     */     //   786: goto -> 864
/*     */     //   789: getstatic io/github/quantizr/dungeonrooms/dungeons/catacombs/Waypoints.showSecrets : Z
/*     */     //   792: ifne -> 798
/*     */     //   795: goto -> 1170
/*     */     //   798: new java/awt/Color
/*     */     //   801: dup
/*     */     //   802: sipush #250
/*     */     //   805: sipush #217
/*     */     //   808: iconst_2
/*     */     //   809: invokespecial <init> : (III)V
/*     */     //   812: astore #11
/*     */     //   814: goto -> 864
/*     */     //   817: getstatic io/github/quantizr/dungeonrooms/dungeons/catacombs/Waypoints.showFairySouls : Z
/*     */     //   820: ifne -> 826
/*     */     //   823: goto -> 1170
/*     */     //   826: new java/awt/Color
/*     */     //   829: dup
/*     */     //   830: sipush #255
/*     */     //   833: bipush #85
/*     */     //   835: sipush #255
/*     */     //   838: invokespecial <init> : (III)V
/*     */     //   841: astore #11
/*     */     //   843: goto -> 864
/*     */     //   846: new java/awt/Color
/*     */     //   849: dup
/*     */     //   850: sipush #190
/*     */     //   853: sipush #255
/*     */     //   856: sipush #252
/*     */     //   859: invokespecial <init> : (III)V
/*     */     //   862: astore #11
/*     */     //   864: aload #10
/*     */     //   866: getfield field_70142_S : D
/*     */     //   869: aload #10
/*     */     //   871: getfield field_70165_t : D
/*     */     //   874: aload #10
/*     */     //   876: getfield field_70142_S : D
/*     */     //   879: dsub
/*     */     //   880: aload_1
/*     */     //   881: getfield partialTicks : F
/*     */     //   884: f2d
/*     */     //   885: dmul
/*     */     //   886: dadd
/*     */     //   887: dstore #12
/*     */     //   889: aload #10
/*     */     //   891: getfield field_70137_T : D
/*     */     //   894: aload #10
/*     */     //   896: getfield field_70163_u : D
/*     */     //   899: aload #10
/*     */     //   901: getfield field_70137_T : D
/*     */     //   904: dsub
/*     */     //   905: aload_1
/*     */     //   906: getfield partialTicks : F
/*     */     //   909: f2d
/*     */     //   910: dmul
/*     */     //   911: dadd
/*     */     //   912: dstore #14
/*     */     //   914: aload #10
/*     */     //   916: getfield field_70136_U : D
/*     */     //   919: aload #10
/*     */     //   921: getfield field_70161_v : D
/*     */     //   924: aload #10
/*     */     //   926: getfield field_70136_U : D
/*     */     //   929: dsub
/*     */     //   930: aload_1
/*     */     //   931: getfield partialTicks : F
/*     */     //   934: f2d
/*     */     //   935: dmul
/*     */     //   936: dadd
/*     */     //   937: dstore #16
/*     */     //   939: aload #9
/*     */     //   941: invokevirtual func_177958_n : ()I
/*     */     //   944: i2d
/*     */     //   945: dload #12
/*     */     //   947: dsub
/*     */     //   948: dstore #18
/*     */     //   950: aload #9
/*     */     //   952: invokevirtual func_177956_o : ()I
/*     */     //   955: i2d
/*     */     //   956: dload #14
/*     */     //   958: dsub
/*     */     //   959: dstore #20
/*     */     //   961: aload #9
/*     */     //   963: invokevirtual func_177952_p : ()I
/*     */     //   966: i2d
/*     */     //   967: dload #16
/*     */     //   969: dsub
/*     */     //   970: dstore #22
/*     */     //   972: dload #18
/*     */     //   974: dload #18
/*     */     //   976: dmul
/*     */     //   977: dload #20
/*     */     //   979: dload #20
/*     */     //   981: dmul
/*     */     //   982: dadd
/*     */     //   983: dload #22
/*     */     //   985: dload #22
/*     */     //   987: dmul
/*     */     //   988: dadd
/*     */     //   989: dstore #24
/*     */     //   991: invokestatic func_179097_i : ()V
/*     */     //   994: invokestatic func_179129_p : ()V
/*     */     //   997: getstatic io/github/quantizr/dungeonrooms/dungeons/catacombs/Waypoints.showBoundingBox : Z
/*     */     //   1000: ifeq -> 1088
/*     */     //   1003: aload_0
/*     */     //   1004: getfield frustum : Lnet/minecraft/client/renderer/culling/Frustum;
/*     */     //   1007: aload #9
/*     */     //   1009: invokevirtual func_177958_n : ()I
/*     */     //   1012: i2d
/*     */     //   1013: aload #9
/*     */     //   1015: invokevirtual func_177956_o : ()I
/*     */     //   1018: i2d
/*     */     //   1019: aload #9
/*     */     //   1021: invokevirtual func_177952_p : ()I
/*     */     //   1024: i2d
/*     */     //   1025: aload #9
/*     */     //   1027: invokevirtual func_177958_n : ()I
/*     */     //   1030: iconst_1
/*     */     //   1031: iadd
/*     */     //   1032: i2d
/*     */     //   1033: aload #9
/*     */     //   1035: invokevirtual func_177956_o : ()I
/*     */     //   1038: iconst_1
/*     */     //   1039: iadd
/*     */     //   1040: i2d
/*     */     //   1041: aload #9
/*     */     //   1043: invokevirtual func_177952_p : ()I
/*     */     //   1046: iconst_1
/*     */     //   1047: iadd
/*     */     //   1048: i2d
/*     */     //   1049: invokevirtual func_78548_b : (DDDDDD)Z
/*     */     //   1052: ifeq -> 1088
/*     */     //   1055: new net/minecraft/util/AxisAlignedBB
/*     */     //   1058: dup
/*     */     //   1059: dload #18
/*     */     //   1061: dload #20
/*     */     //   1063: dload #22
/*     */     //   1065: dload #18
/*     */     //   1067: dconst_1
/*     */     //   1068: dadd
/*     */     //   1069: dload #20
/*     */     //   1071: dconst_1
/*     */     //   1072: dadd
/*     */     //   1073: dload #22
/*     */     //   1075: dconst_1
/*     */     //   1076: dadd
/*     */     //   1077: invokespecial <init> : (DDDDDD)V
/*     */     //   1080: aload #11
/*     */     //   1082: ldc_w 0.4
/*     */     //   1085: invokestatic drawFilledBoundingBox : (Lnet/minecraft/util/AxisAlignedBB;Ljava/awt/Color;F)V
/*     */     //   1088: invokestatic func_179090_x : ()V
/*     */     //   1091: getstatic io/github/quantizr/dungeonrooms/dungeons/catacombs/Waypoints.showBeacon : Z
/*     */     //   1094: ifeq -> 1129
/*     */     //   1097: dload #24
/*     */     //   1099: ldc2_w 25.0
/*     */     //   1102: dcmpl
/*     */     //   1103: ifle -> 1129
/*     */     //   1106: dload #18
/*     */     //   1108: dload #20
/*     */     //   1110: dconst_1
/*     */     //   1111: dadd
/*     */     //   1112: dload #22
/*     */     //   1114: aload #11
/*     */     //   1116: invokevirtual getRGB : ()I
/*     */     //   1119: ldc_w 0.25
/*     */     //   1122: aload_1
/*     */     //   1123: getfield partialTicks : F
/*     */     //   1126: invokestatic renderBeaconBeam : (DDDIFF)V
/*     */     //   1129: getstatic io/github/quantizr/dungeonrooms/dungeons/catacombs/Waypoints.showWaypointText : Z
/*     */     //   1132: ifeq -> 1158
/*     */     //   1135: aload #6
/*     */     //   1137: ldc 'secretName'
/*     */     //   1139: invokevirtual get : (Ljava/lang/String;)Lcom/google/gson/JsonElement;
/*     */     //   1142: invokevirtual getAsString : ()Ljava/lang/String;
/*     */     //   1145: aload #9
/*     */     //   1147: iconst_2
/*     */     //   1148: invokevirtual func_177981_b : (I)Lnet/minecraft/util/BlockPos;
/*     */     //   1151: aload_1
/*     */     //   1152: getfield partialTicks : F
/*     */     //   1155: invokestatic renderWaypointText : (Ljava/lang/String;Lnet/minecraft/util/BlockPos;F)V
/*     */     //   1158: invokestatic func_179140_f : ()V
/*     */     //   1161: invokestatic func_179098_w : ()V
/*     */     //   1164: invokestatic func_179126_j : ()V
/*     */     //   1167: invokestatic func_179089_o : ()V
/*     */     //   1170: iinc #5, 1
/*     */     //   1173: goto -> 85
/*     */     //   1176: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #83	-> 0
/*     */     //   #84	-> 7
/*     */     //   #85	-> 25
/*     */     //   #86	-> 29
/*     */     //   #87	-> 55
/*     */     //   #88	-> 65
/*     */     //   #89	-> 76
/*     */     //   #90	-> 82
/*     */     //   #91	-> 92
/*     */     //   #93	-> 103
/*     */     //   #94	-> 106
/*     */     //   #95	-> 117
/*     */     //   #96	-> 138
/*     */     //   #97	-> 171
/*     */     //   #98	-> 174
/*     */     //   #94	-> 177
/*     */     //   #102	-> 183
/*     */     //   #104	-> 191
/*     */     //   #106	-> 224
/*     */     //   #107	-> 263
/*     */     //   #108	-> 276
/*     */     //   #109	-> 284
/*     */     //   #110	-> 306
/*     */     //   #111	-> 353
/*     */     //   #116	-> 356
/*     */     //   #118	-> 628
/*     */     //   #119	-> 637
/*     */     //   #120	-> 651
/*     */     //   #122	-> 654
/*     */     //   #123	-> 663
/*     */     //   #124	-> 677
/*     */     //   #126	-> 680
/*     */     //   #127	-> 689
/*     */     //   #128	-> 705
/*     */     //   #130	-> 708
/*     */     //   #131	-> 717
/*     */     //   #132	-> 732
/*     */     //   #134	-> 735
/*     */     //   #135	-> 744
/*     */     //   #136	-> 759
/*     */     //   #138	-> 762
/*     */     //   #139	-> 771
/*     */     //   #140	-> 786
/*     */     //   #142	-> 789
/*     */     //   #143	-> 798
/*     */     //   #144	-> 814
/*     */     //   #146	-> 817
/*     */     //   #147	-> 826
/*     */     //   #148	-> 843
/*     */     //   #150	-> 846
/*     */     //   #153	-> 864
/*     */     //   #154	-> 889
/*     */     //   #155	-> 914
/*     */     //   #157	-> 939
/*     */     //   #158	-> 950
/*     */     //   #159	-> 961
/*     */     //   #160	-> 972
/*     */     //   #162	-> 991
/*     */     //   #163	-> 994
/*     */     //   #164	-> 997
/*     */     //   #165	-> 1055
/*     */     //   #167	-> 1088
/*     */     //   #168	-> 1091
/*     */     //   #169	-> 1129
/*     */     //   #170	-> 1158
/*     */     //   #171	-> 1161
/*     */     //   #172	-> 1164
/*     */     //   #173	-> 1167
/*     */     //   #90	-> 1170
/*     */     //   #176	-> 1176
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   109	74	8	j	I
/*     */     //   651	3	11	color	Ljava/awt/Color;
/*     */     //   677	3	11	color	Ljava/awt/Color;
/*     */     //   705	3	11	color	Ljava/awt/Color;
/*     */     //   732	3	11	color	Ljava/awt/Color;
/*     */     //   759	3	11	color	Ljava/awt/Color;
/*     */     //   786	3	11	color	Ljava/awt/Color;
/*     */     //   814	3	11	color	Ljava/awt/Color;
/*     */     //   843	3	11	color	Ljava/awt/Color;
/*     */     //   103	1067	6	secretsObject	Lcom/google/gson/JsonObject;
/*     */     //   106	1064	7	display	Z
/*     */     //   263	907	8	relative	Lnet/minecraft/util/BlockPos;
/*     */     //   276	894	9	pos	Lnet/minecraft/util/BlockPos;
/*     */     //   284	886	10	viewer	Lnet/minecraft/entity/Entity;
/*     */     //   864	306	11	color	Ljava/awt/Color;
/*     */     //   889	281	12	viewerX	D
/*     */     //   914	256	14	viewerY	D
/*     */     //   939	231	16	viewerZ	D
/*     */     //   950	220	18	x	D
/*     */     //   961	209	20	y	D
/*     */     //   972	198	22	z	D
/*     */     //   991	179	24	distSq	D
/*     */     //   85	1091	5	i	I
/*     */     //   76	1100	3	secretsArray	Lcom/google/gson/JsonArray;
/*     */     //   82	1094	4	arraySize	I
/*     */     //   0	1177	0	this	Lio/github/quantizr/dungeonrooms/dungeons/catacombs/Waypoints;
/*     */     //   0	1177	1	event	Lnet/minecraftforge/client/event/RenderWorldLastEvent;
/*     */     //   29	1148	2	roomName	Ljava/lang/String;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent(priority = EventPriority.HIGHEST)
/*     */   public void onChat(ClientChatReceivedEvent event) {
/* 181 */     if (!Utils.inCatacombs || !enabled)
/*     */       return; 
/* 183 */     if (event.type == 2) {
/* 184 */       String[] actionBarSections = event.message.func_150260_c().split(" {3,}");
/*     */       
/* 186 */       for (String section : actionBarSections) {
/* 187 */         if (section.contains("Secrets") && section.contains("/")) {
/* 188 */           String cleanedSection = StringUtils.func_76338_a(section);
/* 189 */           String[] splitSecrets = cleanedSection.split("/");
/*     */           
/* 191 */           completedSecrets = Integer.parseInt(splitSecrets[0].replaceAll("[^0-9]", ""));
/* 192 */           int totalSecrets = Integer.parseInt(splitSecrets[1].replaceAll("[^0-9]", ""));
/*     */           
/* 194 */           allFound = (totalSecrets == secretNum && completedSecrets == secretNum);
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onInteract(PlayerInteractEvent event) {
/* 203 */     if (!Utils.inCatacombs || !enabled)
/* 204 */       return;  if (disableWhenAllFound && allFound)
/*     */       return; 
/* 206 */     if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
/* 207 */       Block block = event.world.func_180495_p(event.pos).func_177230_c();
/* 208 */       if (block != Blocks.field_150486_ae && block != Blocks.field_150465_bP)
/* 209 */         return;  String roomName = RoomDetection.roomName;
/* 210 */       if (roomName.equals("undefined") || DungeonRooms.roomsJson.get(roomName) == null || secretsList == null)
/* 211 */         return;  if (DungeonRooms.waypointsJson.get(roomName) != null) {
/* 212 */         JsonArray secretsArray = DungeonRooms.waypointsJson.get(roomName).getAsJsonArray();
/* 213 */         int arraySize = secretsArray.size();
/* 214 */         for (int i = 0; i < arraySize; i++) {
/* 215 */           JsonObject secretsObject = secretsArray.get(i).getAsJsonObject();
/* 216 */           if (secretsObject.get("category").getAsString().equals("chest") || secretsObject.get("category").getAsString().equals("wither")) {
/* 217 */             BlockPos relative = new BlockPos(secretsObject.get("x").getAsInt(), secretsObject.get("y").getAsInt(), secretsObject.get("z").getAsInt());
/* 218 */             BlockPos pos = MapUtils.relativeToActual(relative, RoomDetection.roomDirection, RoomDetection.roomCorner);
/*     */             
/* 220 */             if (pos.equals(event.pos)) {
/* 221 */               for (int j = 1; j <= secretNum; j++) {
/* 222 */                 if (secretsObject.get("secretName").getAsString().substring(0, 2).replaceAll("[\\D]", "").equals(String.valueOf(j))) {
/* 223 */                   secretsList.set(j - 1, Boolean.valueOf(false));
/* 224 */                   allSecretsMap.replace(roomName, secretsList);
/* 225 */                   DungeonRooms.logger.info("DungeonRooms: Detected " + secretsObject.get("category").getAsString() + " click, turning off waypoint for secret #" + j);
/*     */                   break;
/*     */                 } 
/*     */               } 
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onReceivePacket(PacketEvent.ReceiveEvent event) {
/* 238 */     if (!Utils.inCatacombs || !enabled)
/* 239 */       return;  if (disableWhenAllFound && allFound)
/* 240 */       return;  Minecraft mc = Minecraft.func_71410_x();
/*     */     
/* 242 */     if (event.packet instanceof S0DPacketCollectItem) {
/* 243 */       S0DPacketCollectItem packet = (S0DPacketCollectItem)event.packet;
/* 244 */       Entity entity = mc.field_71441_e.func_73045_a(packet.func_149354_c());
/* 245 */       if (entity instanceof EntityItem) {
/* 246 */         EntityItem item = (EntityItem)entity;
/* 247 */         entity = mc.field_71441_e.func_73045_a(packet.func_149353_d());
/* 248 */         if (entity == null)
/* 249 */           return;  String name = item.func_92059_d().func_82833_r();
/* 250 */         if (name.contains("Decoy") || name.contains("Defuse Kit") || name.contains("Dungeon Chest Key") || name
/* 251 */           .contains("Healing VIII") || name.contains("Inflatable Jerry") || name.contains("Spirit Leap") || name
/* 252 */           .contains("Training Weights") || name.contains("Trap") || name.contains("Treasure Talisman")) {
/* 253 */           if (!entity.func_174793_f().func_70005_c_().equals(mc.field_71439_g.func_70005_c_())) {
/*     */             return;
/*     */           }
/*     */           
/* 257 */           String roomName = RoomDetection.roomName;
/* 258 */           if (roomName.equals("undefined") || DungeonRooms.roomsJson.get(roomName) == null || secretsList == null)
/* 259 */             return;  if (DungeonRooms.waypointsJson.get(roomName) != null) {
/* 260 */             JsonArray secretsArray = DungeonRooms.waypointsJson.get(roomName).getAsJsonArray();
/* 261 */             int arraySize = secretsArray.size();
/* 262 */             for (int i = 0; i < arraySize; i++) {
/* 263 */               JsonObject secretsObject = secretsArray.get(i).getAsJsonObject();
/* 264 */               if (secretsObject.get("category").getAsString().equals("item") || secretsObject.get("category").getAsString().equals("bat")) {
/* 265 */                 BlockPos relative = new BlockPos(secretsObject.get("x").getAsInt(), secretsObject.get("y").getAsInt(), secretsObject.get("z").getAsInt());
/* 266 */                 BlockPos pos = MapUtils.relativeToActual(relative, RoomDetection.roomDirection, RoomDetection.roomCorner);
/*     */                 
/* 268 */                 if (entity.func_174818_b(pos) <= 36.0D) {
/* 269 */                   for (int j = 1; j <= secretNum; ) {
/* 270 */                     if (!secretsObject.get("secretName").getAsString().substring(0, 2).replaceAll("[\\D]", "").equals(String.valueOf(j)) || 
/* 271 */                       !((Boolean)secretsList.get(j - 1)).booleanValue()) { j++; continue; }
/* 272 */                      secretsList.set(j - 1, Boolean.valueOf(false));
/* 273 */                     allSecretsMap.replace(roomName, secretsList);
/* 274 */                     DungeonRooms.logger.info("DungeonRooms: " + entity.func_174793_f().func_70005_c_() + " picked up " + StringUtils.func_76338_a(name) + " from a " + secretsObject.get("category").getAsString() + " secret, turning off waypoint for secret #" + j);
/*     */                     return;
/*     */                   } 
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onKey(InputEvent.KeyInputEvent event) {
/* 291 */     if (!Utils.inCatacombs || !enabled || !sneakToDisable)
/* 292 */       return;  EntityPlayerSP player = (Minecraft.func_71410_x()).field_71439_g;
/* 293 */     if ((FMLClientHandler.instance().getClient()).field_71474_y.field_74311_E.func_151468_f()) {
/* 294 */       if (System.currentTimeMillis() - lastSneakTime < 500L) {
/* 295 */         String roomName = RoomDetection.roomName;
/* 296 */         if (roomName.equals("undefined") || DungeonRooms.roomsJson.get(roomName) == null || secretsList == null)
/* 297 */           return;  if (DungeonRooms.waypointsJson.get(roomName) != null) {
/* 298 */           JsonArray secretsArray = DungeonRooms.waypointsJson.get(roomName).getAsJsonArray();
/* 299 */           int arraySize = secretsArray.size();
/* 300 */           for (int i = 0; i < arraySize; i++) {
/* 301 */             JsonObject secretsObject = secretsArray.get(i).getAsJsonObject();
/* 302 */             if (secretsObject.get("category").getAsString().equals("chest") || secretsObject.get("category").getAsString().equals("wither") || secretsObject
/* 303 */               .get("category").getAsString().equals("item") || secretsObject.get("category").getAsString().equals("bat")) {
/* 304 */               BlockPos relative = new BlockPos(secretsObject.get("x").getAsInt(), secretsObject.get("y").getAsInt(), secretsObject.get("z").getAsInt());
/* 305 */               BlockPos pos = MapUtils.relativeToActual(relative, RoomDetection.roomDirection, RoomDetection.roomCorner);
/*     */               
/* 307 */               if (player.func_174818_b(pos) <= 16.0D) {
/* 308 */                 for (int j = 1; j <= secretNum; ) {
/* 309 */                   if (!secretsObject.get("secretName").getAsString().substring(0, 2).replaceAll("[\\D]", "").equals(String.valueOf(j)) || 
/* 310 */                     !((Boolean)secretsList.get(j - 1)).booleanValue()) { j++; continue; }
/* 311 */                    secretsList.set(j - 1, Boolean.valueOf(false));
/* 312 */                   allSecretsMap.replace(roomName, secretsList);
/* 313 */                   DungeonRooms.logger.info("DungeonRooms: Player sneaked near " + secretsObject.get("category").getAsString() + " secret, turning off waypoint for secret #" + j);
/*     */                   
/*     */                   return;
/*     */                 } 
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 322 */       lastSneakTime = System.currentTimeMillis();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\io\github\quantizr\dungeonrooms\dungeons\catacombs\Waypoints.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */