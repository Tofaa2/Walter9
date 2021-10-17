/*    */ package me.Danker.handlers;
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
/*    */ public class ScoreboardHandler
/*    */ {
/*    */   public static String cleanSB(String scoreboard) {
/* 20 */     char[] nvString = StringUtils.func_76338_a(scoreboard).toCharArray();
/* 21 */     StringBuilder cleaned = new StringBuilder();
/*    */     
/* 23 */     for (char c : nvString) {
/* 24 */       if (c > '\024' && c < '') {
/* 25 */         cleaned.append(c);
/*    */       }
/*    */     } 
/*    */     
/* 29 */     return cleaned.toString();
/*    */   }
/*    */   
/*    */   public static List<String> getSidebarLines() {
/* 33 */     List<String> lines = new ArrayList<>();
/* 34 */     if ((Minecraft.func_71410_x()).field_71441_e == null) return lines; 
/* 35 */     Scoreboard scoreboard = (Minecraft.func_71410_x()).field_71441_e.func_96441_U();
/* 36 */     if (scoreboard == null) return lines;
/*    */     
/* 38 */     ScoreObjective objective = scoreboard.func_96539_a(1);
/* 39 */     if (objective == null) return lines;
/*    */     
/* 41 */     Collection<Score> scores = scoreboard.func_96534_i(objective);
/*    */ 
/*    */ 
/*    */     
/* 45 */     List<Score> list = (List<Score>)scores.stream().filter(input -> (input != null && input.func_96653_e() != null && !input.func_96653_e().startsWith("#"))).collect(Collectors.toList());
/*    */     
/* 47 */     if (list.size() > 15) {
/* 48 */       scores = Lists.newArrayList(Iterables.skip(list, scores.size() - 15));
/*    */     } else {
/* 50 */       scores = list;
/*    */     } 
/*    */     
/* 53 */     for (Score score : scores) {
/* 54 */       ScorePlayerTeam team = scoreboard.func_96509_i(score.func_96653_e());
/* 55 */       lines.add(ScorePlayerTeam.func_96667_a((Team)team, score.func_96653_e()));
/*    */     } 
/*    */     
/* 58 */     return lines;
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\me\Danker\handlers\ScoreboardHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */