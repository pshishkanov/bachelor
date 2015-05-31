package org.pshishkanov.sherlock.core.process.algorithm.rkrgst.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by pshishkanov on 28/05/15.
 */
public class GSTHashTable {

    private HashMap<Long, ArrayList<Integer>> matchers = new HashMap<>();

    public void put(long hash, int position){
        ArrayList<Integer> list;
        if (matchers.containsKey(hash)){
            list = matchers.get(hash);
        } else {
            list = new ArrayList<>();
        }
        list.add(position);
        matchers.put(hash, list);
    }

    public ArrayList<Integer> get(long key){
        if(matchers.containsKey(key))
            return matchers.get(key);
        else
            return null;
    }
}
