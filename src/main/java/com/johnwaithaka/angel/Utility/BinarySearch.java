package com.johnwaithaka.angel.Utility;

import com.johnwaithaka.angel.entities.Admin;
import com.johnwaithaka.angel.entities.Level;

import java.util.List;

public class BinarySearch {

    public static Admin getLevelById(List<Admin> levels, String levelId) {
        if (levels.isEmpty()){
            return null;
        }

        int low = 0;
        int high = levels.size()-1;

        while(low <= high){
            int mid = (low + high)/2;

            if(getLong(levels.get(mid).getId()) > getLong(levelId)){
                high = mid;
            } else if(getLong(levels.get(mid).getId()) > getLong(levelId)){
                low = mid;
            } else {
                return levels.get(mid);
            }
        }
        return null;
    }

    private static long getLong(String id) {
        return Long.parseLong(id, 16);
    }

}
