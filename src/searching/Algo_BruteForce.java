package searching;

import java.awt.EventQueue;
import java.util.concurrent.TimeUnit;

import searching.ResultModel;

public class Algo_BruteForce {


//	public static void main(String[] args) {
//		String TestMainString = "AMANCHOOSESASLAVEOBEYS";
//		String TestSubstring = "SLAVE";
//
//		System.out.println(search(TestMainString,TestSubstring));
//	}



    public static ResultModel search(String text, String pattern) {
        long startTime = System.nanoTime();
        ResultModel Result = new ResultModel();

        int lengthOfPattern = pattern.length();
        int lengthOfText = text.length();
        int Count = 0;
        String positionText = "";
        for(int i=0; i<=lengthOfText - lengthOfPattern; i++) {
            int j=0;

            while(j<lengthOfPattern) {
            	 if(text.charAt(i+j)!=pattern.charAt(j))
                     break;
            	 j++;
            }
            
            // for(j=0;j<lengthOfPattern; j++) {
            //     if(text.charAt(i+j)!=pattern.charAt(j))
            //         break;
            //  }

            if(j==lengthOfPattern) {
                Count ++;
                // return i
                if(positionText == "") {
                    positionText += (i + 1) + "th";
                }else {
                    positionText = positionText + ", " + (i + 1) + "th";
                }
            }
        }
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;

        Result.setExecutionTime(TimeUnit.NANOSECONDS.toMillis(totalTime));
        Result.setFoundCount(Count);
        Result.setPosition(positionText);

        return Result;
    }
}
