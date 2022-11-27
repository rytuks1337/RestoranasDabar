package com.example.restoranasdabar;

public class TimeParser {
    public static int[] timeInMin(String val){
        int min[] = new int[4];
        String[] separated = val.split("[:-]");
        min[0] = Integer.parseInt(separated[0]);
        min[1] =Integer.parseInt(separated[1]);
        min[2] =Integer.parseInt(separated[2]);
        min[3] =Integer.parseInt(separated[3]);
        return min;
    }

}
