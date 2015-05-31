package org.pshishkanov.sherlock.core.process.algorithm.rkrgst.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by pshishkanov on 28/05/15.
 */
public class GSTHashTable {

    private HashMap<Long, ArrayList<Integer>> matches = new HashMap<>();

    public void put(long hash, int position){
        ArrayList<Integer> list;
        if (matches.containsKey(hash)){
            list = matches.get(hash);
        } else {
            list = new ArrayList<>();
        }
        list.add(position);
        matches.put(hash, list);
    }

    public ArrayList<Integer> get(long key){
        if(matches.containsKey(key))
            return matches.get(key);
        else
            return null;
    }
}
