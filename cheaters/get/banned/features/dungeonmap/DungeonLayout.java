/*    */ package cheaters.get.banned.features.dungeonmap;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ 
/*    */ 
/*    */ public class DungeonLayout
/*    */ {
/* 11 */   public ArrayList<RoomTile> roomTiles = new ArrayList<>();
/* 12 */   public ArrayList<ConnectorTile> connectorTiles = new ArrayList<>();
/*    */   public int totalSecrets;
/*    */   public int totalCrypts;
/*    */   public int totalPuzzles;
/*    */   public boolean uncertainCrypts = false;
/*    */   public TrapType trapType;
/*    */   public boolean sentScoreMessage = false;
/*    */   
/*    */   enum TrapType {
/* 21 */     OLD, NEW;
/*    */   }
/*    */   
/*    */   public static class ConnectorTile {
/*    */     public int x;
/*    */     public int z;
/*    */     public Type type;
/*    */     public boolean isOpen = false;
/*    */     public EnumFacing direction;
/*    */     
/*    */     public ConnectorTile(int x, int z, Type type, EnumFacing direction) {
/* 32 */       this.x = x;
/* 33 */       this.z = z;
/* 34 */       this.type = type;
/* 35 */       this.direction = direction;
/*    */     }
/*    */     
/*    */     public BlockPos getPosition(int y) {
/* 39 */       return new BlockPos(this.x, y, this.z);
/*    */     }
/*    */     
/*    */     enum Type {
/* 43 */       WITHER_DOOR((String)Color.BLACK),
/* 44 */       BLOOD_DOOR((String)Room.Type.BLOOD.color),
/* 45 */       ENTRANCE_DOOR((String)Room.Type.ENTRANCE.color),
/* 46 */       NORMAL_DOOR((String)Room.Type.NORMAL.color),
/* 47 */       CONNECTOR((String)Room.Type.NORMAL.color),
/* 48 */       DEBUG((String)Color.MAGENTA);
/*    */       Color color;
/*    */       
/*    */       Type(Color color) {
/* 52 */         this.color = color;
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public static class RoomTile {
/*    */     public int x;
/*    */     public int z;
/*    */     public Room room;
/*    */     public boolean isLarge;
/*    */     public boolean explored;
/*    */     public boolean greenCheck;
/*    */     
/*    */     public RoomTile(int x, int z, Room room) {
/* 66 */       this.x = x;
/* 67 */       this.z = z;
/* 68 */       this.room = room;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Desktop\ShadyAddons-2.1.0-pre2.jar!\cheaters\get\banned\features\dungeonmap\DungeonLayout.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */