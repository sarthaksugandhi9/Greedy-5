// O(mn)

class Solution {

    public boolean isMatch(String s, String p) {
        if(s.equals(p) || p.equals("*")) return true;
        if(s.isEmpty() || p.isEmpty()) return false;
        int sl = s.length(); int pl = p.length();
        boolean [][] dp = new boolean[pl+1][sl+1];
        dp[0][0] = true;
        for(int i = 1; i < dp.length; i++){
            if(p.charAt(i-1) == '*'){
                dp[i][0] = dp[i-1][0]; // in first col only zero case
                int j = 1;
                while(j < dp[0].length){
                   dp[i][j] = dp[i-1][j-1] || dp[i-1][j];// zero case and 1 case
                    if(dp[i][j]){ // if at any index dp[i][j] is true make whole row true 
                        while(j < dp[0].length){
                            dp[i][j] = true;
                            j++;
                        }
                    }
                    j++;
                }
            } else if(p.charAt(i-1) == '?'){
                for(int j = 1; j < dp[0].length; j++){
                   dp[i][j] = dp[i-1][j-1]; 
                }
            } else {
                 for(int j = 1; j < dp[0].length; j++){
                   dp[i][j] = p.charAt(i-1) == s.charAt(j-1) && dp[i-1][j-1]; 
                }
            }
        }
        return dp[pl][sl];
    }
}
