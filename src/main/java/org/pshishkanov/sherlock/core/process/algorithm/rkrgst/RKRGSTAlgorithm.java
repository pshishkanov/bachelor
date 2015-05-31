package org.pshishkanov.sherlock.core.process.algorithm.rkrgst;

import com.codepoetics.protonpack.StreamUtils;
import org.pshishkanov.sherlock.core.process.IAlgorithm;
import org.pshishkanov.sherlock.core.process.algorithm.rkrgst.model.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Created by pshishkanov on 14/05/15.
 */
public class RKRGSTAlgorithm implements IAlgorithm {

    static Logger logger = Logger.getLogger(RKRGSTAlgorithm.class.getName());

    public static ArrayList<MatchValue> tiles = new ArrayList<>();

    public static ArrayList<Queue<MatchValue>> matchList = new ArrayList<Queue<MatchValue>>();

    /* Число подряд идущих совпадений, ниже которого совпадения не учитываются */
    private static final Integer MINIMUM_MATCH_LENGTH = 3;

    /* Начальное значение для длины максимальной последовательности совпадений */
    private static final Integer INITIAL_SEARCH_LENGTH = 20;

    @Override
    public String getName() {
        return "RKR-GST";
    }

    @Override
    public Float process(List<String> p, List<String> t) {

        Integer minimalMatchingLength = 2;
        Integer initsearchSize = 20;

            if (minimalMatchingLength < 1)
                minimalMatchingLength = 3;

            if (initsearchSize < 5)
                initsearchSize = 20;

            int s = 0;

            s = initsearchSize;
            boolean stop = false;

            while (!stop) {
                //logger.info(Integer.toString(s));
                // Lmax is size of largest maximal-matches from this scan
                int Lmax = scanpattern(s, p, t);
                //logger.info(tiles.toString());
                // if very long string no tiles marked. Iterate with larger s
                if (Lmax > 2 * s)
                    s = Lmax;
                else {
                    markStrings(p, t);
                    if (s > (2 * minimalMatchingLength))
                        s = s/2;
                    else if (s > minimalMatchingLength)
                        s = minimalMatchingLength;
                    else
                        stop = true;
                }
            }

        return similarity(p,t,tiles);


//        Integer search_length = INITIAL_SEARCH_LENGTH;
//
//        boolean stop = false;
//
//        while (!stop) {
//            int L_max = scanpattern(search_length, p, t);
//            if (L_max > 2 * search_length)
//                search_length = L_max;
//            else {
//                markStrings( p, t);
//                if (search_length > (2 * MINIMUM_MATCH_LENGTH))
//                    search_length = search_length / 2;
//                else if (search_length > MINIMUM_MATCH_LENGTH)
//                    search_length = MINIMUM_MATCH_LENGTH;
//                else
//                    stop = true;
//            }
//        }
//
//        return similarity(p, t, tiles);
    }

    public static int scanpattern(int s, List<String> P, List<String> T) {

        int longestMaxMatch = 0;
        Queue<MatchValue> queue = new LinkedList<MatchValue>();
        GSTHashTable hashtable = new GSTHashTable();
        /**
         * Starting at the first unmarked token in T for each unmarked Tt do if
         * distance to next tile <= s then advance t to first unmarked token
         * after next tile else create the KR-hash value for substring Tt to
         * Tt+s-1 and add to hashtable
         */
        int t = 0;
        boolean noNextTile = false;
        int h;
        while (t < T.size()) {
            if (isMarked(T.get(t))) {
                t = t+1;
                continue;
            }

            int dist;
            if(distToNextTile(t, T) instanceof Integer)
                dist = (int)distToNextTile(t, T);
            else{
                dist = 0;
                dist = T.size() - t;
                noNextTile = true;
            }
            //int dist = distToNextTile(t, T);
            // No next tile found

            if (dist < s) {
                if (noNextTile)
                    t = T.size();
                else {
                    if(jumpToNextUnmarkedTokenAfterTile(t, T) instanceof Integer)
                        t = (int)jumpToNextUnmarkedTokenAfterTile(t, T);
                    else
                        t = T.size();
                }
            } else {
                StringBuilder sb = new StringBuilder();

                for (int i = t; i <= t + s-1; i++)
                    sb.append(T.get(i));
                String substring = sb.toString();
                h = createKRHashValue(substring);
                hashtable.put(h, t);
                t = t+1;
            }
        }

        /**
         * Starting at the first unmarked token of P for each unmarked Pp do if
         * distance to next tile <= s then advance p to first unmarked token
         * after next tile else create the KR hash-value for substring Pp to
         * Pp+s-1 check hashtable for hash of KR hash-value for each hash-table
         * entry with equal hashed KR hash-value do if for all j from 0 to s-1,
         * Pp+ j = Tt+ j then k: = s while Pp+k = Tt+k AND unmarked(Pp+k) AND
         * unmarked(Tt+k) do k := k + 1 if k > 2 *s then return(k) else record
         * new maximal-match
         */
        noNextTile = false;
        int p = 0;
        while (p < P.size()) {
            if (isMarked(P.get(p))) {
                p = p + 1;
                continue;
            }

            int dist;

            if(distToNextTile(p, P) instanceof Integer){
                dist = (int)distToNextTile(p, P);
            }
            else{
                dist = 0;
                dist = P.size() - p;
                noNextTile = true;
            }

            if (dist < s) {
                if (noNextTile)
                    p = P.size();
                else {

                    if(jumpToNextUnmarkedTokenAfterTile(p, P) instanceof Integer)
                        p = (int)jumpToNextUnmarkedTokenAfterTile(p, P);
                    else{
                        p = 0;
                        p = P.size();
                    }
                }
            } else {
                StringBuilder sb = new StringBuilder();
                for (int i = p; i <= p + s-1; i++) {
                    sb.append(P.get(i));
                }
                String substring = sb.toString();
                h = createKRHashValue(substring);
                ArrayList<Integer> values = hashtable.get(h);
                if (values != null) {
                    for (Integer val : values) {
                        StringBuilder newsb = new StringBuilder();
                        for (int i = val; i <= val + s-1; i++) {
                            newsb.append(T.get(i));
                        }
                        if (newsb.toString().equals(substring)) {
                            t = val;
                            int k = s;

                            while (p + k < P.size() && t + k < T.size()
                                    && P.get(p + k).equals(T.get(t + k))
                                    && isUnmarked(P.get(p+k))
                                    && isUnmarked(T.get(t+k)))
                                k = k + 1;

                            if (k > 2 * s)
                                return k;
                            else {
                                if (longestMaxMatch < s)
                                    longestMaxMatch = s;
                                MatchValue mv = new MatchValue(p, t, k);
                                queue.add(mv);
                            }
                        }
                    }
                }
                p += 1;
            }
            //logger.info(queue.toString());
        }
        if (!queue.isEmpty()){
            matchList.add(queue);
        }
        return longestMaxMatch;
    }

    private static void markStrings(List<String> P, List<String> T) {
        for(Queue<MatchValue> queue:matchList){
            while (!queue.isEmpty()) {
                MatchValue match = queue.poll();
                if (!isOccluded(match, tiles)) {
                    for (int j = 0; j < match.getLengthMatch(); j++) {
                        P.set(match.getPatternPosition() + j, markToken(P.get(match.getPatternPosition() + j)));
                        T.set(match.getTextPosition() + j, markToken(T.get(match.getTextPosition() + j)));
                    }
                    tiles.add(match);
                }
            }
        }
        matchList = new ArrayList<Queue<MatchValue>>();
    }

    /**
     * Creates a Karp-Rabin Hash Value for the given substring and returns it.
     *
     * Based on: http://www-igm.univ-mlv.fr/~lecroq/string/node5.html
     *
     * @param substring
     * @return hash value for any given string
     */

    private static int createKRHashValue(String substring) {
        int hashValue = 0;
        for (int i = 0; i < substring.length(); i++)
            hashValue = ((hashValue << 1) + (int) substring.charAt(i));
        return hashValue;
    }

    /**
     * If string s is unmarked returns True otherwise False.
     *
     * @param string
     * @return true or false (i.e., whether marked or unmarked)
     */
    private static boolean isUnmarked(String string) {
        if (string.length() > 0 && string.charAt(0) != '*')
            return true;
        else
            return false;
    }

    private static boolean isMarked(String string) {
        return (!isUnmarked(string));
    }

    private static String markToken(String string) {
        StringBuilder sb = new StringBuilder();
        sb.append("*");
        sb.append(string);
        return sb.toString();
    }

    private static boolean isOccluded(MatchValue match, ArrayList<MatchValue> tiles) {
        if(tiles.equals(null) || tiles == null || tiles.size() == 0)
            return false;
        for (MatchValue matches : tiles) {
            if ((matches.getPatternPosition() + matches.getLengthMatch() == match.getPatternPosition()
                    + match.getLengthMatch())
                    && (matches.getTextPosition() + matches.getLengthMatch() == match.getTextPosition()
                    + match.getLengthMatch()))
                return true;
        }
        return false;

//        return tiles.stream().anyMatch(tile -> (tile.getPatternPosition() + tile.getLengthMatch() == tile.getPatternPosition() + match_value.getLengthMatch()) &&
//                (tile.getTextPosition() + tile.getLengthMatch() == match_value.getTextPosition() + match_value.getLengthMatch()));
//////
    }


    private static Object distToNextTile(int pos, List<String> stringList) {
        if (pos == stringList.size())
            return null;
        int dist = 0;
        while (pos+dist+1<stringList.size() && isUnmarked(stringList.get(pos+dist+1)))
            dist += 1;
        if (pos+dist+1 == stringList.size())
            return null;
        return dist+1;
    }

    /**
     * Returns the first postion of an unmarked token after the next tile.

     case 1: -> normal case
     -> tile exists
     -> there is an unmarked token after the tile
     case 2:
     -> tile exists
     -> but NO unmarked token after the tile
     case 3:
     -> NO tile exists
     * @param pos
     * @param stringList
     * @return the position to jump to the next unmarked token after tile
     */
    private static Object jumpToNextUnmarkedTokenAfterTile(int pos, List<String> stringList) {
        Object dist = distToNextTile(pos, stringList);
        if(dist instanceof Integer)
            pos = pos+ (int)dist;
        else
            return null;
        while (pos+1<stringList.size() && (isMarked(stringList.get(pos+1))))
            pos = pos+1;
        if (pos+1> stringList.size() - 1)
            return null;
        return pos+1;
    }


//    private Integer scanpattern(Integer search_length, List<String> p, List<String> t) {
//
//        Integer longest_max_match = 0;
//        Queue<MatchValue> matches = new LinkedList<>();
//        GSTHashTable hash_table = new GSTHashTable();
//
//        Integer t_position = 0;
//        Boolean no_next_tile = false;
//        Integer hash;
//
//        while (t_position < t.size()) {
//            if (isMarked(t.get(t_position))) {
//                t_position ++;
//                continue;
//            }
//
//            Integer distance;
//            Optional<Integer> distance_to_next_tile = distanceToNextTile(t_position, t);
//            if (distance_to_next_tile.isPresent())
//                distance = distance_to_next_tile.get();
//            else {
//                distance = t.size() - t_position;
//                no_next_tile = true;
//            }
//
//            if (distance < search_length) {
//                if (no_next_tile)
//                    t_position = t.size();
//                else {
//                    Optional<Integer> position_next_tile = jumpToNextUnmarkedTokenAfterTile(t_position, t);
//                    if (position_next_tile.isPresent())
//                        t_position = position_next_tile.get();
//                    else
//                        t_position = t.size();
//                }
//            } else {
//                StringBuilder t_substring_builder = new StringBuilder();
//                t.subList(t_position, t_position + search_length + 1).forEach(t_substring_builder::append);
//                hash = hash(t_substring_builder.toString());
//                hash_table.put(hash, t_position);
//                t_position ++;
//            }
//        }
//
//        no_next_tile = false;
//        int p_position = 0;
//
//        while (p_position < p.size()) {
//            if (isMarked(p.get(p_position))) {
//                p_position = p_position + 1;
//                continue;
//            }
//
//            Integer distance;
//
//            Optional<Integer> distance_to_next_tile = distanceToNextTile(t_position, t);
//            if (distance_to_next_tile.isPresent()){
//                distance = distance_to_next_tile.get();
//            }
//            else{
//                distance = p.size() - p_position;
//                no_next_tile = true;
//            }
//
//            if (distance < search_length) {
//                if (no_next_tile)
//                    p_position = p.size();
//                else {
//                    Optional<Integer> position_next_tile = jumpToNextUnmarkedTokenAfterTile(p_position, p);
//                    if (position_next_tile.isPresent())
//                        p_position = position_next_tile.get();
//                    else
//                        p_position = p.size();
//
//                }
//            } else {
//                StringBuilder p_substring_builder = new StringBuilder();
//                p.subList(p_position, p_position + search_length + 1).forEach(p_substring_builder::append);
//                hash = hash(p_substring_builder.toString());
//
//                ArrayList<Integer> values = hash_table.get(hash);
//
//                for (Integer val : values) {
//                    StringBuilder newsb = new StringBuilder();
//                    for (int i = val; i <= val + search_length -1; i++) {
//                        newsb.append(t.get(i));
//                    }
//                    if (newsb.toString().equals(p_substring_builder.toString())) {
//                        t_position = val;
//                        int k = search_length;
//
//                        while (p_position + k < p.size() && t_position + k < t.size()
//                                && p.get(p_position + k).equals(t.get(t_position + k))
//                                && isUnmarked(p.get(p_position + k))
//                                && isUnmarked(t.get(t_position + k)))
//                            k = k + 1;
//
//                        if (k > 2 * search_length)
//                            return k;
//                        else {
//                            if (longest_max_match < search_length)
//                                longest_max_match = search_length;
//
//                            matches.add(new MatchValue(p_position, t_position, k));
//                        }
//                    }
//                }
//
//                p_position += 1;
//            }
//            //logger.info(queue.toString());
//        }
//        if (!matches.isEmpty()){
//            all_match.add(matches);
//        }
//        return longest_max_match;
//
////        Integer longest_max_match = 0;
////
////        GSTHashTable hash_table = new GSTHashTable();
////        Queue<MatchVals> match = new LinkedList<>();
////
////        Integer t_position = 0;
////        Boolean no_next_tile = false;
////        Integer hash;
////
////        while (t_position < t.size()) {
////
////            if (isMarked(t.get(t_position))) {
////                t_position ++;
////                continue;
////            }
////
////            Integer distance;
////
////            Integer distance_to_next_tile = distanceToNextTile(t_position, t);
////
////            if (distance_to_next_tile > 0) {
////                distance = distance_to_next_tile;
////            } else {
////                distance = t.size() - t_position;
////                no_next_tile = true;
////            }
////
////            if (distance < search_length) {
////                if (no_next_tile) {
////                    t_position = t.size();
////                } else {
////                    Integer distance_to_next_unmarked_token_after_tile = jumpToNextUnmarkedTokenAfterTile(t_position, t);
////                    if (distance_to_next_unmarked_token_after_tile > 0) {
////                        t_position = distance_to_next_unmarked_token_after_tile;
////                    } else {
////                        t_position = t.size();
////                    }
////                }
////            } else {
////                StringBuilder t_substring_builder = new StringBuilder();
////                for (int i = t_position; i <= t_position + search_length-1; i++)
////                    t_substring_builder.append(t.get(i));
////               // t.subList(t_position, t_position + search_length - 1).stream().forEach(t_substring_builder::append);
////                String t_substring = t_substring_builder.toString();
////                logger.info(t_substring + " ============");
////                hash = hash(t_substring);
////                hash_table.put((long) hash, t_position);
////                t_position ++;
////            }
////        }
////
////        no_next_tile = false;
////        Integer p_position = 0;
////        while (p_position < p.size()) {
////
////            if (isMarked(p.get(p_position))) {
////                p_position ++;
////                continue;
////            }
////
////            Integer distance;
////
////            Integer distance_to_next_tile = distanceToNextTile(p_position, p);
////            if (distance_to_next_tile > 0) {
////                distance = distance_to_next_tile;
////            } else {
////                distance = p.size() - p_position;
////                no_next_tile = true;
////            }
////
////            if (distance < search_length) {
////                if (no_next_tile) {
////                    p_position = p.size();
////                } else {
////                    Integer distance_to_next_unmarked_token_after_tile = jumpToNextUnmarkedTokenAfterTile(p_position, p);
////                    if (distance_to_next_unmarked_token_after_tile > 0) {
////                        p_position = distance_to_next_unmarked_token_after_tile;
////                    } else {
////                        p_position = p.size();
////                    }
////                }
////            } else {
////                StringBuilder p_substring_builder = new StringBuilder();
////                for (int i = p_position; i <= p_position + search_length-1; i++)
////                    p_substring_builder.append(t.get(i));
////                //p.subList(p_position, p_position + search_length - 1).stream().forEach(p_substring_builder::append);
////                String p_substring = p_substring_builder.toString();
////                hash = hash(p_substring);
////
////                ArrayList<Integer> match_positions = hash_table.get((long) hash);
////
////                for (Integer match_position : match_positions) {
////                    StringBuilder t_substring_builder = new StringBuilder();
////                    for (int i = match_position; i <= match_position + search_length-1; i++)
////                        t_substring_builder.append(t.get(i));
////                    //t.subList(match_position, match_position + search_length - 1).stream().forEach(t_substring_builder::append);
////                    String t_substring = t_substring_builder.toString();
////
////                    if (t_substring.equals(p_substring)) {
////                        t_position = match_position;
////                        Integer k = search_length;
////
////                        while (p_position + k < p.size() && t_position + k < t.size()
////                                && p.get(p_position + k).equals(t.get(t_position + k))
////                                && isUnmarked(p.get(p_position + k))
////                                && isUnmarked(t.get(t_position + k)))
////                            k = k + 1;
////
////                        if (k > 2 * search_length) {
////                            return k;
////                        } else {
////                            longest_max_match = Integer.max(longest_max_match, search_length);
////                            match.put(new MatchVals(p_position, t_position, k));
////                        }
////                    }
////                }
////                p_position ++;
////            }
////        }
////
////        if (!match.isEmpty())
////            all_matches.put(match);
////
////        return longest_max_match;
//    }
//
//    private void markStrings(List<String> p, List<String> t) {
//        for(Queue<MatchValue> queue: all_match){
//            while (!queue.isEmpty()) {
//                MatchValue match = queue.poll();
//                if (!isOccluded(match, tiles)) {
//                    for (int j = 0; j < match.getLengthMatch(); j++) {
//                        p.set(match.getPatternPosition() + j, markToken(p.get(match.getPatternPosition() + j)));
//                        t.set(match.getTextPosition() + j, markToken(t.get(match.getTextPosition() + j)));
//                    }
//                    tiles.add(match);
//                }
//            }
//        }
//        all_match = new ArrayList<Queue<MatchValue>>();
//    }
//
//    /**
//     * Creates a Karp-Rabin Hash Value for the given substring and returns it.
//     *
//     * Based on: http://www-igm.univ-mlv.fr/~lecroq/string/node5.html
//     *
//     * @param substring
//     * @return hash value for any given string
//     */
//
//    private static int createKRHashValue(String substring) {
//        int hashValue = 0;
//        for (int i = 0; i < substring.length(); i++)
//            hashValue = ((hashValue << 1) + (int) substring.charAt(i));
//        logger.info(substring + " ============" + hashValue);
//        return hashValue;
//    }
//
//    /**
//     * If string search_length is unmarked returns True otherwise False.
//     *
//     * @param string
//     * @return true or false (i.e., whether marked or unmarked)
//     */
//    private boolean isUnmarked(String string) {
//        if (string.length() > 0 && string.charAt(0) != '*')
//            return true;
//        else
//            return false;
//    }
//
//    private boolean isMarked(String string) {
//        return (!isUnmarked(string));
//    }
//
//    private String markToken(String string) {
//        return "*" + string;
//    }
//
//
//    private boolean isOccluded(MatchValue match, ArrayList<MatchValue> tiles) {
//        return tiles.stream().anyMatch(tile -> (tile.getPatternPosition() + tile.getLengthMatch() == match.getPatternPosition() + match.getLengthMatch()) &&
//                                               (tile.getTextPosition() + tile.getLengthMatch() == match.getTextPosition() + match.getLengthMatch()));
//    }
//
//    private Optional<Integer> distanceToNextTile(Integer current_position, List<String> list) {
//
//        if (current_position == list.size())
//            return Optional.empty();
//
//        Integer distance = 0;
//
//        while (current_position + distance + 1 < list.size() && isUnmarked(list.get(current_position + distance + 1)))
//            distance ++;
//
//        if (current_position + distance + 1 == list.size())
//            return Optional.empty();
//
//        return Optional.of(distance +1);
//
////        Integer distance = (int) StreamUtils.takeWhile(list.stream().skip(current_position + 1), this::isUnmarked).count();
////        return current_position + distance + 1 != list.size() ? Optional.of(distance) : Optional.empty();
//    }
//
//    private Optional<Integer> jumpToNextUnmarkedTokenAfterTile(Integer position, List<String> list) {
//
//        Optional<Integer> distance_to_next_tile = distanceToNextTile(position, list);
//
//        if (distance_to_next_tile.isPresent()) {
//            position += distance_to_next_tile.get();
//        } else {
//            return Optional.empty();
//        }
//
//        while (position + 1 < list.size() && isMarked(list.get(position + 1)))
//            position ++;
//
//        if (position + 1 > list.size() - 1)
//            return Optional.empty();
//
//        return Optional.of(position + 1);
//
//
////        Optional<Integer> distance_to_next_tile = distanceToNextTile(position, list);
////
////        if (distance_to_next_tile.isPresent()) {
////            position += distance_to_next_tile.get();
////        } else {
////            return Optional.empty();
////        }
////
////        while (position + 1 < list.size() && isMarked(list.get(position + 1)))
////            position ++;
////
////        if (position + 1 > list.size() - 1) {
////            return Optional.empty();
////        } else {
////            return Optional.of(position + 1);
////        }
//    }
//
//    private Integer hash(String string) {
//        AtomicInteger hash = new AtomicInteger(0);
//        string.chars().forEach(symbol -> hash.set((hash.intValue() << 1) + symbol));
//        return hash.intValue();
//    }
//
////    private Boolean isOccluded(MatchVals match_value, List<MatchVals> tiles) {

////
    private Float similarity(List<String> p, List<String> t, List<MatchValue> tiles) {
        return (float) (2 * coverage(tiles)) / (float) (p.size() + t.size());
    }

    private Integer coverage(List<MatchValue> tiles) {
        return tiles.stream().collect(Collectors.summingInt(MatchValue::getLengthMatch));
    }
}