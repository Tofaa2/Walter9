/*    */ package me.Danker.utils;
/*    */ import java.util.Collection;
/*    */ import java.util.Collections;
/*    */ import java.util.Comparator;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class TicTacToeUtils {
/*    */   public static int getBestMove(char[][] board) {
/* 10 */     HashMap<Integer, Integer> moves = new HashMap<>();
/* 11 */     for (int row = 0; row < board.length; row++) {
/* 12 */       for (int col = 0; col < (board[row]).length; col++) {
/* 13 */         if (board[row][col] == '\000') {
/* 14 */           board[row][col] = 'O';
/* 15 */           int score = minimax(board, false, 0);
/* 16 */           board[row][col] = Character.MIN_VALUE;
/* 17 */           moves.put(Integer.valueOf(row * 3 + col + 1), Integer.valueOf(score));
/*    */         } 
/*    */       } 
/* 20 */     }  return ((Integer)((Map.Entry)Collections.<Map.Entry>max((Collection)moves.entrySet(), (Comparator)Map.Entry.comparingByValue())).getKey()).intValue();
/*    */   }
/*    */   
/*    */   public static boolean hasMovesLeft(char[][] board) {
/* 24 */     for (char[] rows : board) {
/* 25 */       for (char col : rows) {
/* 26 */         if (col == '\000') return true; 
/*    */       } 
/*    */     } 
/* 29 */     return false;
/*    */   }
/*    */   
/*    */   public static int getBoardRanking(char[][] board) {
/* 33 */     for (int row = 0; row < 3; row++) {
/* 34 */       if (board[row][0] == board[row][1] && board[row][0] == board[row][2]) {
/* 35 */         if (board[row][0] == 'X')
/* 36 */           return -10; 
/* 37 */         if (board[row][0] == 'O') {
/* 38 */           return 10;
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 43 */     for (int col = 0; col < 3; col++) {
/* 44 */       if (board[0][col] == board[1][col] && board[0][col] == board[2][col]) {
/* 45 */         if (board[0][col] == 'X')
/* 46 */           return -10; 
/* 47 */         if (board[0][col] == 'O') {
/* 48 */           return 10;
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 53 */     if (board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
/* 54 */       if (board[0][0] == 'X')
/* 55 */         return -10; 
/* 56 */       if (board[0][0] == 'O') {
/* 57 */         return 10;
/*    */       }
/* 59 */     } else if (board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
/* 60 */       if (board[0][2] == 'X')
/* 61 */         return -10; 
/* 62 */       if (board[0][2] == 'O') {
/* 63 */         return 10;
/*    */       }
/*    */     } 
/*    */     
/* 67 */     return 0;
/*    */   }
/*    */   
/*    */   public static int minimax(char[][] board, boolean max, int depth) {
/* 71 */     int score = getBoardRanking(board);
/* 72 */     if (score == 10 || score == -10) return score; 
/* 73 */     if (!hasMovesLeft(board)) return 0;
/*    */     
/* 75 */     if (max) {
/* 76 */       int i = -1000;
/* 77 */       for (int j = 0; j < 3; j++) {
/* 78 */         for (int col = 0; col < 3; col++) {
/* 79 */           if (board[j][col] == '\000') {
/* 80 */             board[j][col] = 'O';
/* 81 */             i = Math.max(i, minimax(board, false, depth + 1));
/* 82 */             board[j][col] = Character.MIN_VALUE;
/*    */           } 
/*    */         } 
/*    */       } 
/* 86 */       return i - depth;
/*    */     } 
/* 88 */     int bestScore = 1000;
/* 89 */     for (int row = 0; row < 3; row++) {
/* 90 */       for (int col = 0; col < 3; col++) {
/* 91 */         if (board[row][col] == '\000') {
/* 92 */           board[row][col] = 'X';
/* 93 */           bestScore = Math.min(bestScore, minimax(board, true, depth + 1));
/* 94 */           board[row][col] = Character.MIN_VALUE;
/*    */         } 
/*    */       } 
/*    */     } 
/* 98 */     return bestScore + depth;
/*    */   }
/*    */ }


/* Location:              C:\Users\amine\Deskto\\ultra mods.jar!\me\Danke\\utils\TicTacToeUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */