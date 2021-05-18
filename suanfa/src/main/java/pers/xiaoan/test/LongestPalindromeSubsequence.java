package main.java.pers.xiaoan.test;

/**
 * 最长回文子序列
 * @author: Kangxiaoan
 * @createDate: 2020/11/25 12:12
 */
public class LongestPalindromeSubsequence {
    public static void main(String[] args) {
        String s = "aabcaca";
        System.out.println(longestPalindromeSubseq(s));
    }

    public static int longestPalindromeSubseq(String s) {
        int len = s.length();
        int [][] dp = new int[len][len];
        for(int i = len - 1; i>=0; i--){
            dp[i][i] = 1;
            System.out.println("i:" + i);
            for(int j = i+1; j < len; j++){
                System.out.println("i:" + i + " j:" + j);
                if(s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][len-1];
    }
    
}
