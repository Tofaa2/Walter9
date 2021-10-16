/*    */ package cheaters.get.banned.utils;
/*    */ 
/*    */ import com.google.common.collect.ComparisonChain;
/*    */ import com.google.common.collect.Ordering;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.stream.Collectors;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.network.NetworkPlayerInfo;
/*    */ import net.minecraft.scoreboard.ScorePlayerTeam;
/*    */ import net.minecraft.world.WorldSettings;
/*    */ 
/*    */ 
/*    */ public class TabUtils
/*    */ {
/* 16 */   public static final Ordering<NetworkPlayerInfo> playerInfoOrdering = new Ordering<NetworkPlayerInfo>()
/*    */     {
/*    */       public int compare(NetworkPlayerInfo p_compare_1_, NetworkPlayerInfo p_compare_2_) {
/* 19 */         ScorePlayerTeam scoreplayerteam = p_compare_1_.func_178850_i();
/* 20 */         ScorePlayerTeam scoreplayerteam1 = p_compare_2_.func_178850_i();
/* 21 */         return ComparisonChain.start().compareTrueFirst((p_compare_1_.func_178848_b() != WorldSettings.GameType.SPECTATOR), (p_compare_2_.func_178848_b() != WorldSettings.GameType.SPECTATOR)).compare((scoreplayerteam != null) ? scoreplayerteam.func_96661_b() : "", (scoreplayerteam1 != null) ? scoreplayerteam1.func_96661_b() : "").compare(p_compare_1_.func_178845_a().getName(), p_compare_2_.func_178845_a().getName()).result();
/*    */       }
/*    */     };
/*    */   
/*    */   public static List<NetworkPlayerInfo> getTabEntries() {
/* 26 */     if ((Minecraft.func_71410_x()).field_71439_g == null) return Collections.emptyList(); 
/* 27 */     return playerInfoOrdering.sortedCopy((Minecraft.func_71410_x()).field_71439_g.field_71174_a.func_175106_d());
/*    */   }
/*    */   
/*    */   public static List<String> getTabList() {
/* 31 */     return (List<String>)getTabEntries().stream().map(playerInfo -> (Minecraft.func_71410_x()).field_71456_v.func_175181_h().func_175243_a(playerInfo))
/*    */       
/* 33 */       .collect(Collectors.toList());
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Desktop\ShadyAddons-2.1.0-pre2.jar!\cheaters\get\banne\\utils\TabUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */