package searching;

import searching.ResultModel;

import java.util.concurrent.TimeUnit;

public class Algo_FCustom {

    // First custom searching algorithem
    public static ResultModel search(String text, String pattern) {
        long startTime = System.nanoTime();
        ResultModel Result = new ResultModel();

        // Write your code here


        //End of code

        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;

        Result.setExecutionTime(TimeUnit.NANOSECONDS.toMillis(totalTime));
        return Result;
    }
}
