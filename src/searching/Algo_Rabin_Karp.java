package searching;

import searching.ResultModel;

import java.util.concurrent.TimeUnit;

public class Algo_Rabin_Karp {

	public final static int d = 85; 
	public final static int q = 300761; 
    
    // First custom searching algorithem
    public static ResultModel search(String text, String pattern) {
        long startTime = System.nanoTime();
        ResultModel Result = new ResultModel();
        String positionText = "";
        int Count = 0;
        // Write your code here
        int M = pattern.length(); 
        int N = text.length(); 
        int i, j; 
        int p = 0; // hash value for pattern 
        int t = 0; // hash value for txt 
        int h = 1; 
   
        // The value of h would be "pow(d, M-1)%q" 
        for (i = 0; i < M-1; i++) {
            h = (h*d)%q; 
        }
        // Calculate the hash value of pattern and first 
        // window of text 
        for (i = 0; i < M; i++) 
        { 
            p = (d*p + pattern.charAt(i))%q; 
            t = (d*t + text.charAt(i))%q; 
        } 
       

        // Slide the pattern over text one by one 
        for (i = 0; i <= N - M; i++) 
        { 
      
            // Check the hash values of current window of text 
            // and pattern. If the hash values match then only 
            // check for characters on by one 
            if ( p == t ) 
            { 
                /* Check for characters one by one */
                for (j = 0; j < M; j++) 
                { 
                    if (text.charAt(i+j) != pattern.charAt(j)) 
                        break; 
                } 
      
                // if p == t and pat[0...M-1] = txt[i, i+1, ...i+M-1] 
                if (j == M) { 
                	Count ++;
	                // return i
	                if(positionText == "") {
	                    positionText += (i);
	                }else {
	                    positionText = positionText + ", " + (i);
	                }
                }
            } 
      
            // Calculate hash value for next window of text: Remove 
            // leading digit, add trailing digit 
            if ( i < N-M ) 
            { 
                t = (d*(t - text.charAt(i)*h) + text.charAt(i+M))%q; 
      
                // We might get negative value of t, converting it 
                // to positive 
                if (t < 0) {

                    t = (t + q); 
                }
            } 
        } 

        //End of code

        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;

        Result.setExecutionTime(TimeUnit.NANOSECONDS.toMillis(totalTime));
        Result.setFoundCount(Count);
        Result.setPosition(positionText);
        return Result;
    }
    
    
}
