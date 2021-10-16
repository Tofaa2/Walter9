/*    */ package cheaters.get.banned.features.dungeonmap;
/*    */ import cheaters.get.banned.Shady;
/*    */ import com.google.gson.JsonArray;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParser;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.InputStream;
/*    */ import java.io.InputStreamReader;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.HashSet;
/*    */ import java.util.Map;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class RoomLoader {
/* 18 */   public static ArrayList<Room> rooms = new ArrayList<>();
/* 19 */   public static HashSet<Integer> yLevels = new HashSet<>();
/*    */   
/*    */   public static void load() {
/*    */     try {
/* 23 */       ResourceLocation roomsResource = new ResourceLocation("shadyaddons:dungeonscanner/rooms.json");
/* 24 */       InputStream inputStream = Shady.mc.func_110442_L().func_110536_a(roomsResource).func_110527_b();
/* 25 */       BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
/*    */       
/* 27 */       JsonParser parser = new JsonParser();
/* 28 */       JsonObject fullJson = parser.parse(reader).getAsJsonObject();
/* 29 */       JsonArray roomsArray = fullJson.getAsJsonArray("dungeonRooms");
/*    */       
/* 31 */       for (JsonElement roomElement : roomsArray) {
/* 32 */         Room room = new Room();
/* 33 */         JsonObject roomObject = roomElement.getAsJsonObject();
/*    */         
/* 35 */         room.name = roomObject.get("roomName").getAsString();
/* 36 */         room.type = getTypeFromString(roomObject.get("roomType").getAsString());
/* 37 */         room.secrets = roomObject.get("secrets").getAsInt();
/* 38 */         room.yLevel = roomObject.get("yLevel").getAsInt();
/* 39 */         room.crypts = null;
/*    */         try {
/* 41 */           room.crypts = Integer.valueOf(Integer.parseInt(roomObject.get("crypts").getAsString()));
/* 42 */         } catch (NumberFormatException numberFormatException) {}
/* 43 */         JsonObject blocksObject = roomObject.getAsJsonObject("blocks");
/* 44 */         HashMap<String, Object> stringBlocksMap = (HashMap<String, Object>)(new Gson()).fromJson((JsonElement)blocksObject, HashMap.class);
/* 45 */         HashMap<Block, String> blockConditions = new HashMap<>();
/* 46 */         for (Map.Entry<String, Object> block : stringBlocksMap.entrySet()) {
/* 47 */           blockConditions.put(Block.func_149684_b("minecraft:" + (String)block.getKey()), (String)block.getValue());
/*    */         }
/* 49 */         room.blockConditions = blockConditions;
/*    */         
/* 51 */         yLevels.add(Integer.valueOf(room.yLevel));
/* 52 */         rooms.add(room);
/*    */       } 
/* 54 */     } catch (Exception exception) {}
/*    */   }
/*    */   
/*    */   private static Room.Type getTypeFromString(String string) {
/* 58 */     switch (string) {
/*    */       case "normal":
/* 60 */         return Room.Type.NORMAL;
/*    */       
/*    */       case "blood":
/* 63 */         return Room.Type.BLOOD;
/*    */       
/*    */       case "fairy":
/* 66 */         return Room.Type.FAIRY;
/*    */       
/*    */       case "green":
/* 69 */         return Room.Type.ENTRANCE;
/*    */       
/*    */       case "puzzle":
/* 72 */         return Room.Type.PUZZLE;
/*    */       
/*    */       case "trap":
/* 75 */         return Room.Type.TRAP;
/*    */       
/*    */       case "yellow":
/* 78 */         return Room.Type.MINIBOSS;
/*    */     } 
/*    */     
/* 81 */     return Room.Type.UNKNOWN;
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Desktop\ShadyAddons-2.1.0-pre2.jar!\cheaters\get\banned\features\dungeonmap\RoomLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */