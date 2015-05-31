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

    public ArrayList<MatchValue> tiles = new ArrayList<>();

    public ArrayList<Queue<MatchValue>> all_match = new ArrayList<>();

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

        Integer search_length = INITIAL_SEARCH_LENGTH;

        boolean stop = false;

        while (!stop) {
            int L_max = scanpattern(search_length, p, t);
            if (L_max > 2 * search_length)
                search_length = L_max;
            else {
                markStrings( p, t);
                if (search_length > (2 * MINIMUM_MATCH_LENGTH))
                    search_length = search_length / 2;
                else if (search_length > MINIMUM_MATCH_LENGTH)
                    search_length = MINIMUM_MATCH_LENGTH;
                else
                    stop = true;
            }
        }

        return similarity(p, t, tiles);
    }

    private Integer scanpattern(Integer search_length, List<String> p, List<String> t) {

        Integer longest_max_match = 0;
        Queue<MatchValue> matches = new LinkedList<>();
        GSTHashTable hash_table = new GSTHashTable();

        Integer t_position = 0;
        Boolean no_next_tile = false;
        Integer hash;

        while (t_position < t.size()) {
            if (isMarked(t.get(t_position))) {
                t_position ++;
                continue;
            }

            Integer distance;
            Optional<Integer> distance_to_next_tile = distanceToNextTile(t_position, t);
            if (distance_to_next_tile.isPresent())
                distance = distance_to_next_tile.get();
            else {
                distance = t.size() - t_position;
                no_next_tile = true;
            }

            if (distance < search_length) {
                if (no_next_tile)
                    t_position = t.size();
                else {
                    Optional<Integer> position_next_tile = jumpToNextUnmarkedTokenAfterTile(t_position, t);
                    if (position_next_tile.isPresent())
                        t_position = position_next_tile.get();
                    else
                        t_position = t.size();
                }
            } else {
                StringBuilder t_substring_builder = new StringBuilder();
                t.subList(t_position, t_position + search_length + 1).forEach(t_substring_builder::append);
                hash = hash(t_substring_builder.toString());
                hash_table.put(hash, t_position);
                t_position ++;
            }
        }

        no_next_tile = false;
        int p_position = 0;

        while (p_position < p.size()) {
            if (isMarked(p.get(p_position))) {
                p_position = p_position + 1;
                continue;
            }

            Integer distance;

            Optional<Integer> distance_to_next_tile = distanceToNextTile(t_position, t);
            if (distance_to_next_tile.isPresent()){
                distance = distance_to_next_tile.get();
            }
            else{
                distance = p.size() - p_position;
                no_next_tile = true;
            }

            if (distance < search_length) {
                if (no_next_tile)
                    p_position = p.size();
                else {
                    Optional<Integer> position_next_tile = jumpToNextUnmarkedTokenAfterTile(p_position, p);
                    if (position_next_tile.isPresent())
                        p_position = position_next_tile.get();
                    else
                        p_position = p.size();

                }
            } else {
                StringBuilder p_substring_builder = new StringBuilder();
                p.subList(p_position, p_position + search_length + 1).forEach(p_substring_builder::append);
                hash = hash(p_substring_builder.toString());

                ArrayList<Integer> values = hash_table.get(hash);

                for (Integer val : values) {
                    StringBuilder newsb = new StringBuilder();
                    for (int i = val; i <= val + search_length -1; i++) {
                        newsb.append(t.get(i));
                    }
                    if (newsb.toString().equals(p_substring_builder.toString())) {
                        t_position = val;
                        int k = search_length;

                        while (p_position + k < p.size() && t_position + k < t.size()
                                && p.get(p_position + k).equals(t.get(t_position + k))
                                && isUnmarked(p.get(p_position + k))
                                && isUnmarked(t.get(t_position + k)))
                            k = k + 1;

                        if (k > 2 * search_length)
                            return k;
                        else {
                            if (longest_max_match < search_length)
                                longest_max_match = search_length;

                            matches.add(new MatchValue(p_position, t_position, k));
                        }
                    }
                }

                p_position += 1;
            }
            //logger.info(queue.toString());
        }
        if (!matches.isEmpty()){
            all_match.add(matches);
        }
        return longest_max_match;

//        Integer longest_max_match = 0;
//
//        GSTHashTable hash_table = new GSTHashTable();
//        Queue<MatchVals> match = new LinkedList<>();
//
//        Integer t_position = 0;
//        Boolean no_next_tile = false;
//        Integer hash;
//
//        while (t_position < t.size()) {
//
//            if (isMarked(t.get(t_position))) {
//                t_position ++;
//                continue;
//            }
//
//            Integer distance;
//
//            Integer distance_to_next_tile = distanceToNextTile(t_position, t);
//
//            if (distance_to_next_tile > 0) {
//                distance = distance_to_next_tile;
//            } else {
//                distance = t.size() - t_position;
//                no_next_tile = true;
//            }
//
//            if (distance < search_length) {
//                if (no_next_tile) {
//                    t_position = t.size();
//                } else {
//                    Integer distance_to_next_unmarked_token_after_tile = jumpToNextUnmarkedTokenAfterTile(t_position, t);
//                    if (distance_to_next_unmarked_token_after_tile > 0) {
//                        t_position = distance_to_next_unmarked_token_after_tile;
//                    } else {
//                        t_position = t.size();
//                    }
//                }
//            } else {
//                StringBuilder t_substring_builder = new StringBuilder();
//                for (int i = t_position; i <= t_position + search_length-1; i++)
//                    t_substring_builder.append(t.get(i));
//               // t.subList(t_position, t_position + search_length - 1).stream().forEach(t_substring_builder::append);
//                String t_substring = t_substring_builder.toString();
//                logger.info(t_substring + " ============");
//                hash = hash(t_substring);
//                hash_table.put((long) hash, t_position);
//                t_position ++;
//            }
//        }
//
//        no_next_tile = false;
//        Integer p_position = 0;
//        while (p_position < p.size()) {
//
//            if (isMarked(p.get(p_position))) {
//                p_position ++;
//                continue;
//            }
//
//            Integer distance;
//
//            Integer distance_to_next_tile = distanceToNextTile(p_position, p);
//            if (distance_to_next_tile > 0) {
//                distance = distance_to_next_tile;
//            } else {
//                distance = p.size() - p_position;
//                no_next_tile = true;
//            }
//
//            if (distance < search_length) {
//                if (no_next_tile) {
//                    p_position = p.size();
//                } else {
//                    Integer distance_to_next_unmarked_token_after_tile = jumpToNextUnmarkedTokenAfterTile(p_position, p);
//                    if (distance_to_next_unmarked_token_after_tile > 0) {
//                        p_position = distance_to_next_unmarked_token_after_tile;
//                    } else {
//                        p_position = p.size();
//                    }
//                }
//            } else {
//                StringBuilder p_substring_builder = new StringBuilder();
//                for (int i = p_position; i <= p_position + search_length-1; i++)
//                    p_substring_builder.append(t.get(i));
//                //p.subList(p_position, p_position + search_length - 1).stream().forEach(p_substring_builder::append);
//                String p_substring = p_substring_builder.toString();
//                hash = hash(p_substring);
//
//                ArrayList<Integer> match_positions = hash_table.get((long) hash);
//
//                for (Integer match_position : match_positions) {
//                    StringBuilder t_substring_builder = new StringBuilder();
//                    for (int i = match_position; i <= match_position + search_length-1; i++)
//                        t_substring_builder.append(t.get(i));
//                    //t.subList(match_position, match_position + search_length - 1).stream().forEach(t_substring_builder::append);
//                    String t_substring = t_substring_builder.toString();
//
//                    if (t_substring.equals(p_substring)) {
//                        t_position = match_position;
//                        Integer k = search_length;
//
//                        while (p_position + k < p.size() && t_position + k < t.size()
//                                && p.get(p_position + k).equals(t.get(t_position + k))
//                                && isUnmarked(p.get(p_position + k))
//                                && isUnmarked(t.get(t_position + k)))
//                            k = k + 1;
//
//                        if (k > 2 * search_length) {
//                            return k;
//                        } else {
//                            longest_max_match = Integer.max(longest_max_match, search_length);
//                            match.put(new MatchVals(p_position, t_position, k));
//                        }
//                    }
//                }
//                p_position ++;
//            }
//        }
//
//        if (!match.isEmpty())
//            all_matches.put(match);
//
//        return longest_max_match;
    }

    private void markStrings(List<String> p, List<String> t) {
        for(Queue<MatchValue> queue: all_match){
            while (!queue.isEmpty()) {
                MatchValue match = queue.poll();
                if (!isOccluded(match, tiles)) {
                    for (int j = 0; j < match.getLengthMatch(); j++) {
                        p.set(match.getPatternPosition() + j, markToken(p.get(match.getPatternPosition() + j)));
                        t.set(match.getTextPosition() + j, markToken(t.get(match.getTextPosition() + j)));
                    }
                    tiles.add(match);
                }
            }
        }
        all_match = new ArrayList<Queue<MatchValue>>();
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
        logger.info(substring + " ============" + hashValue);
        return hashValue;
    }

    /**
     * If string search_length is unmarked returns True otherwise False.
     *
     * @param string
     * @return true or false (i.e., whether marked or unmarked)
     */
    private boolean isUnmarked(String string) {
        if (string.length() > 0 && string.charAt(0) != '*')
            return true;
        else
            return false;
    }

    private boolean isMarked(String string) {
        return (!isUnmarked(string));
    }

    private String markToken(String string) {
        return "*" + string;
    }


    private boolean isOccluded(MatchValue match, ArrayList<MatchValue> tiles) {
        return tiles.stream().anyMatch(tile -> (tile.getPatternPosition() + tile.getLengthMatch() == match.getPatternPosition() + match.getLengthMatch()) &&
                                               (tile.getTextPosition() + tile.getLengthMatch() == match.getTextPosition() + match.getLengthMatch()));
    }

    private Optional<Integer> distanceToNextTile(Integer current_position, List<String> list) {
        Integer distance = (int) StreamUtils.takeWhile(list.stream().skip(current_position + 1), this::isUnmarked).count();
        return current_position + distance + 1 != list.size() ? Optional.of(distance) : Optional.empty();
    }

    private Optional<Integer> jumpToNextUnmarkedTokenAfterTile(Integer position, List<String> list) {

        Optional<Integer> distance_to_next_tile = distanceToNextTile(position, list);

        if (distance_to_next_tile.isPresent()) {
            position += distance_to_next_tile.get();
        } else {
            return Optional.empty();
        }

        while (position + 1 < list.size() && isMarked(list.get(position + 1)))
            position ++;

        if (position + 1 > list.size() - 1) {
            return Optional.empty();
        } else {
            return Optional.of(position + 1);
        }
    }

    private Integer hash(String string) {
        AtomicInteger hash = new AtomicInteger(0);
        string.chars().forEach(symbol -> hash.set((hash.intValue() << 1) + symbol));
        return hash.intValue();
    }

//    private Boolean isOccluded(MatchVals match_value, List<MatchVals> tiles) {
//        return tiles.stream().anyMatch(tile -> (tile.getPatternPosition() + tile.getLengthMatch() == tile.getPatternPosition() + match_value.getLengthMatch()) &&
//                                                (tile.getTextPosition() + tile.getLengthMatch() == match_value.getTextPosition() + match_value.getLengthMatch()));
//    }
//
    private Float similarity(List<String> p, List<String> t, List<MatchValue> tiles) {
        return (float) (2 * coverage(tiles)) / (float) (p.size() + t.size());
    }

    private Integer coverage(List<MatchValue> tiles) {
        return tiles.stream().collect(Collectors.summingInt(MatchValue::getLengthMatch));
    }
}