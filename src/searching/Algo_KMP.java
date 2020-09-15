package searching;

import java.util.concurrent.TimeUnit;

public class Algo_KMP {

    // Second custom searching algorithem
    public static ResultModel search(String text, String pattern) {
        long startTime = System.nanoTime();
        ResultModel Result = new ResultModel();
        String positionText = "";
        int Count = 0;
        int lengthOfPattern = pattern.length(); 
        int lengthOfText = text.length(); 
        int lps[] = new int[lengthOfPattern]; 
        int j = 0; // index for pat[] 
        computeLPSArray(pattern, lengthOfPattern, lps); 
      
        int i = 0;  
        while (i < lengthOfText) { 
              if (pattern.charAt(j) == text.charAt(i)) { 
                    j++; 
                    i++; 
                } 
                if (j == lengthOfPattern) { 
                	Count ++;
                    // return i
                    if(positionText == "") {
                        positionText += ( i - j);
                    }else {
                        positionText = positionText + ", " + ( i - j);
                    }
                    
                 
                    j = lps[j - 1]; 
                } 
      
                
                else if (i < lengthOfText && pattern.charAt(j) != text.charAt(i)) { 
                    
                    if (j != 0) 
                        j = lps[j - 1]; 
                    else
                        i = i + 1; 
                } 
            } 
            long endTime   = System.nanoTime();
            long totalTime = endTime - startTime;

            Result.setExecutionTime(TimeUnit.NANOSECONDS.toMillis(totalTime));
            Result.setFoundCount(Count);
            Result.setPosition(positionText);
            return Result;
        } 
    
		static void computeLPSArray(String pattern, int lengthOfPattern, int lps[]) 
        { 
            // length of the previous longest prefix suffix 
            int l = 0; 
            int i = 1; 
            lps[0] = 0;  
      
            while (i < lengthOfPattern) { 
                if (pattern.charAt(i) == pattern.charAt(l)) { 
                    l++; 
                    lps[i] = l; 
                    i++; 
                } 
                else  
                { 
                    if (l != 0) { 
                        l = lps[l - 1]; 
                    } 
                    else  
                    { 
                        lps[i] = l; 
                        i++; 
                    } 
                } 
            }
            
        }
}
