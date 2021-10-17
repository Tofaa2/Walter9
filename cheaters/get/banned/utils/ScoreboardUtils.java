/*    */ package cheaters.get.banned.utils;
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
/*    */ public class ScoreboardUtils
/*    */ {
/*    */   public static String cleanSB(String scoreboard) {
/* 24 */     char[] nvString = StringUtils.func_76338_a(scoreboard).toCharArray();
/* 25 */     StringBuilder cleaned = new StringBuilder();
/*    */     
/* 27 */     for (char c : nvString) {
/* 28 */       if (c > '\024' && c < '') {
/* 29 */         cleaned.append(c);
/*    */       }
/*    */     } 
/*    */     
/* 33 */     return cleaned.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public static List<String> getScoreboard() {
/* 38 */     List<String> lines = new ArrayList<>();
/* 39 */     if ((Minecraft.func_71410_x()).field_71441_e == null) return lines; 
/* 40 */     Scoreboard scoreboard = (Minecraft.func_71410_x()).field_71441_e.func_96441_U();
/* 41 */     if (scoreboard == null) return lines;
/*    */     
/* 43 */     ScoreObjective objective = scoreboard.func_96539_a(1);
/* 44 */     if (objective == null) return lines;
/*    */     
/* 46 */     Collection<Score> scores = scoreboard.func_96534_i(objective);
/*    */ 
/*    */ 
/*    */     
/* 50 */     List<Score> list = (List<Score>)scores.stream().filter(input -> (input != null && input.func_96653_e() != null && !input.func_96653_e().startsWith("#"))).collect(Collectors.toList());
/*    */     
/* 52 */     if (list.size() > 15) {
/* 53 */       scores = Lists.newArrayList(Iterables.skip(list, scores.size() - 15));
/*    */     } else {
/* 55 */       scores = list;
/*    */     } 
/*    */     
/* 58 */     for (Score score : scores) {
/* 59 */       ScorePlayerTeam team = scoreboard.func_96509_i(score.func_96653_e());
/* 60 */       lines.add(ScorePlayerTeam.func_96667_a((Team)team, score.func_96653_e()));
/*    */     } 
/*    */     
/* 63 */     return lines;
/*    */   }
/*    */   
/*    */   public static boolean scoreboardContains(String string) {
/* 67 */     boolean result = false;
/* 68 */     List<String> scoreboard = getScoreboard();
/* 69 */     for (String line : scoreboard) {
/* 70 */       line = cleanSB(line);
/* 71 */       line = Utils.removeFormatting(line);
/* 72 */       if (line.contains(string)) {
/* 73 */         result = true;
/*    */         break;
/*    */       } 
/*    */     } 
/* 77 */     return result;
/*    */   }
/*    */   
/*    */   public static String getLineThatContains(String string) {
/* 81 */     String result = null;
/* 82 */     List<String> scoreboard = getScoreboard();
/* 83 */     for (String line : scoreboard) {
/* 84 */       line = cleanSB(line);
/* 85 */       if (line.contains(string)) {
/* 86 */         result = line;
/*    */         break;
/*    */       } 
/*    */     } 
/* 90 */     return result;
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\cheaters\get\banne\\utils\ScoreboardUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */