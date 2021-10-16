/*    */ package cheaters.get.banned.features.dungeonmap;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import net.minecraft.block.Block;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Room
/*    */ {
/*    */   public String name;
/*    */   public Type type;
/*    */   public int secrets;
/*    */   public Integer crypts;
/*    */   public int yLevel;
/*    */   public HashMap<Block, String> blockConditions;
/* 18 */   public static final Room GENERIC_NORMAL_ROOM = new Room("", Type.NORMAL, 0, Integer.valueOf(0), 0, new HashMap<>());
/*    */ 
/*    */   
/*    */   public Room() {}
/*    */ 
/*    */   
/*    */   public Room(String name, Type type, int secrets, Integer crypts, int yLevel, HashMap<Block, String> blockConditions) {
/* 25 */     this.name = name;
/* 26 */     this.type = type;
/* 27 */     this.secrets = secrets;
/* 28 */     this.crypts = crypts;
/* 29 */     this.yLevel = yLevel;
/* 30 */     this.blockConditions = blockConditions;
/*    */   }
/*    */   
/*    */   enum Type {
/* 34 */     NORMAL((String)new Color(107, 58, 17)),
/* 35 */     ENTRANCE((String)new Color(20, 133, 0)),
/* 36 */     BLOOD((String)new Color(255, 0, 0)),
/* 37 */     PUZZLE((String)new Color(117, 0, 133)),
/* 38 */     MINIBOSS((String)new Color(254, 223, 0)),
/* 39 */     FAIRY((String)new Color(224, 0, 255)),
/* 40 */     TRAP((String)new Color(216, 127, 51)),
/* 41 */     UNKNOWN((String)new Color(83, 83, 83));
/*    */     Color color;
/*    */     
/*    */     Type(Color color) {
/* 45 */       this.color = color;
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean matches(BlockList blockList) {
/* 50 */     int conditionsMet = 0;
/*    */     
/* 52 */     for (Map.Entry<Block, String> condition : this.blockConditions.entrySet()) {
/* 53 */       Block block = condition.getKey();
/* 54 */       char comparisonOperator = ((String)condition.getValue()).charAt(0);
/* 55 */       int valueToCompareAgainst = Integer.parseInt(((String)condition.getValue()).substring(1));
/* 56 */       HashMap<Block, Integer> scannedBlocks = blockList.getBlocks();
/*    */       
/* 58 */       if (scannedBlocks.containsKey(block)) {
/* 59 */         switch (comparisonOperator) {
/*    */           case '=':
/* 61 */             if (valueToCompareAgainst == ((Integer)scannedBlocks.get(block)).intValue()) conditionsMet++;
/*    */           
/*    */           
/*    */           case '<':
/* 65 */             if (valueToCompareAgainst > ((Integer)scannedBlocks.get(block)).intValue()) conditionsMet++;
/*    */           
/*    */           
/*    */           case '>':
/* 69 */             if (valueToCompareAgainst < ((Integer)scannedBlocks.get(block)).intValue()) conditionsMet++;
/*    */           
/*    */         } 
/*    */       
/*    */       }
/*    */     } 
/* 75 */     return (conditionsMet == this.blockConditions.size());
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Desktop\ShadyAddons-2.1.0-pre2.jar!\cheaters\get\banned\features\dungeonmap\Room.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */