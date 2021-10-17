/*    */ package cheaters.get.banned.utils;
/*    */ 
/*    */ import cheaters.get.banned.events.TickEndEvent;
/*    */ import java.util.HashMap;
/*    */ import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LocationUtils
/*    */ {
/* 11 */   private static String prevIsland = null;
/* 12 */   private static Island island = null;
/* 13 */   public static HashMap<String, Island> islandLookup = new HashMap<String, Island>()
/*    */     {
/*    */     
/*    */     };
/*    */   
/*    */   public enum Island
/*    */   {
/* 20 */     PRIVATE_ISLAND("Private World"),
/* 21 */     HUB("Hub"),
/* 22 */     SPIDERS_DEN("Spider's Den"),
/* 23 */     BLAZING_FORTRESS("Blazing Fortress"),
/* 24 */     THE_END("The End"),
/* 25 */     THE_PARK("The Park"),
/* 26 */     GOLD_MINE("Gold Mine"),
/* 27 */     DEEP_CAVERNS("Deep Caverns"),
/* 28 */     DWARVEN_MINES("Dwarven Mines"),
/* 29 */     FARMING_ISLANDS("The Farming Islands"),
/* 30 */     DUNGEON_HUB("Dungeon Hub"),
/* 31 */     CRYSTAL_HOLLOWS("Crystal Hollows"),
/* 32 */     JERRYS_WORKSHOP("Jerry's Workshop");
/*    */     private String tabName;
/*    */     
/*    */     Island(String tabName) {
/* 36 */       this.tabName = tabName; } public String getName() {
/* 37 */       return this.tabName;
/*    */     } }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onTick(TickEndEvent event) {
/* 42 */     if (Utils.inSkyBlock) {
/* 43 */       for (String name : TabUtils.getTabList()) {
/* 44 */         if (!name.equals(prevIsland) && name.contains("Area:")) {
/* 45 */           prevIsland = name;
/* 46 */           name = Utils.removeFormatting(name).replace("Area: ", "");
/* 47 */           island = islandLookup.get(name);
/*    */         } 
/*    */       } 
/*    */     } else {
/* 51 */       island = null;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static Island getIsland() {
/* 56 */     return island;
/*    */   }
/*    */   
/*    */   public static boolean onIsland(Island island) {
/* 60 */     return (getIsland() != null && getIsland().equals(island));
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\cheaters\get\banne\\utils\LocationUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */