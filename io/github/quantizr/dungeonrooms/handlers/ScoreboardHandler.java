/*    */ package io.github.quantizr.dungeonrooms.handlers;
/*    */ 
/*    */ import com.google.common.collect.Iterables;
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ import java.util.stream.Collectors;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.scoreboard.Score;
/*    */ import net.minecraft.scoreboard.ScoreObjective;
/*    */ import net.minecraft.scoreboard.ScorePlayerTeam;
/*    */ import net.minecraft.scoreboard.Scoreboard;
/*    */ import net.minecraft.scoreboard.Team;
/*    */ import net.minecraft.util.StringUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ScoreboardHandler
/*    */ {
/*    */   public static String cleanSB(String scoreboard) {
/* 37 */     char[] nvString = StringUtils.func_76338_a(scoreboard).toCharArray();
/* 38 */     StringBuilder cleaned = new StringBuilder();
/*    */     
/* 40 */     for (char c : nvString) {
/* 41 */       if (c > '\024' && c < '') {
/* 42 */         cleaned.append(c);
/*    */       }
/*    */     } 
/*    */     
/* 46 */     return cleaned.toString();
/*    */   }
/*    */   
/*    */   public static List<String> getSidebarLines() {
/* 50 */     List<String> lines = new ArrayList<>();
/* 51 */     if ((Minecraft.func_71410_x()).field_71441_e == null) return lines; 
/* 52 */     Scoreboard scoreboard = (Minecraft.func_71410_x()).field_71441_e.func_96441_U();
/* 53 */     if (scoreboard == null) return lines;
/*    */     
/* 55 */     ScoreObjective objective = scoreboard.func_96539_a(1);
/* 56 */     if (objective == null) return lines;
/*    */     
/* 58 */     Collection<Score> scores = scoreboard.func_96534_i(objective);
/*    */ 
/*    */ 
/*    */     
/* 62 */     List<Score> list = (List<Score>)scores.stream().filter(input -> (input != null && input.func_96653_e() != null && !input.func_96653_e().startsWith("#"))).collect(Collectors.toList());
/*    */     
/* 64 */     if (list.size() > 15) {
/* 65 */       scores = Lists.newArrayList(Iterables.skip(list, scores.size() - 15));
/*    */     } else {
/* 67 */       scores = list;
/*    */     } 
/*    */     
/* 70 */     for (Score score : scores) {
/* 71 */       ScorePlayerTeam team = scoreboard.func_96509_i(score.func_96653_e());
/* 72 */       lines.add(ScorePlayerTeam.func_96667_a((Team)team, score.func_96653_e()));
/*    */     } 
/*    */     
/* 75 */     return lines;
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\io\github\quantizr\dungeonrooms\handlers\ScoreboardHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */