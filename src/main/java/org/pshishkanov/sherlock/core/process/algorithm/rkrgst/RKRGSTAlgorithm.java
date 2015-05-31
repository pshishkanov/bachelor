package org.pshishkanov.sherlock.core.process.algorithm.rkrgst;

import com.codepoetics.protonpack.StreamUtils;
import org.pshishkanov.sherlock.core.process.IAlgorithm;
import org.pshishkanov.sherlock.core.process.algorithm.rkrgst.model.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RKRGSTAlgorithm implements IAlgorithm {

    /* Число подряд идущих совпадений, ниже которого совпадения не учитываются */
    private static final Integer MINIMUM_MATCH_LENGTH = 3;

    /* Начальное значение для длины максимальной последовательности совпадений */
    private static final Integer INITIAL_SEARCH_LENGTH = 20;

    List<List<MatchValue>> all_matches = new ArrayList<>();

    List<MatchValue> tiles = new ArrayList<>();
    @Override
    public String getName() {
        return "RKR-GST";
    }

    @Override
    public Float process(List<String> p, List<String> t) {

            Integer search_length = INITIAL_SEARCH_LENGTH;

            boolean stop = false;

            while (!stop) {

                Integer L_max = scanPattern(search_length, p, t);

                if (L_max > 2 * search_length)
                    search_length = L_max;
                else {
                    markStrings(p, t);
                    if (search_length > 2 * MINIMUM_MATCH_LENGTH)
                        search_length = search_length / 2;
                    else if (search_length > MINIMUM_MATCH_LENGTH)
                        search_length = MINIMUM_MATCH_LENGTH;
                    else
                        stop = true;
                }
            }

        return similarity(p, t, tiles);
    }

    public Integer scanPattern(Integer search_length, List<String> P, List<String> T) {

        Integer longest_max_match = 0;

        List<MatchValue> matches = new ArrayList<>();

        GSTHashTable hash_table = new GSTHashTable();

        Integer t_position = 0;

        Boolean no_next_tile = false;

        Integer distance;

        while (t_position < T.size()) {
            if (isMarked(T.get(t_position))) {
                t_position ++;
                continue;
            }

            Optional<Integer> distance_to_next_tile = distanceToNextTile(t_position, T);
            if (distance_to_next_tile.isPresent())
                distance = distance_to_next_tile.get();
            else {
                distance = T.size() - t_position;
                no_next_tile = true;
            }

            if (distance < search_length) {
                if (no_next_tile)
                    t_position = T.size();
                else {
                    if (jumpToNextUnmarkedTokenAfterTile(t_position, T).isPresent())
                        t_position = jumpToNextUnmarkedTokenAfterTile(t_position, T).get();
                    else
                        t_position = T.size();
                }
            } else {
                String t_substring = T.subList(t_position, t_position + search_length).stream().collect(Collectors.joining());
                hash_table.put(hash(t_substring), t_position);
                t_position ++;
            }
        }

        no_next_tile = false;
        Integer p_position = 0;

        while (p_position < P.size()) {

            if (isMarked(P.get(p_position))) {
                p_position ++;
                continue;
            }

            Optional<Integer> distance_to_next_tile = distanceToNextTile(p_position, P);
            if (distance_to_next_tile.isPresent())
                distance = distance_to_next_tile.get();
            else {
                distance = P.size() - p_position;
                no_next_tile = true;
            }

            if (distance < search_length) {
                if (no_next_tile)
                    p_position = P.size();
                else {
                    if(jumpToNextUnmarkedTokenAfterTile(p_position, P).isPresent())
                        p_position = jumpToNextUnmarkedTokenAfterTile(p_position, P).get();
                    else {
                        p_position = P.size();
                    }
                }
            } else {

                String p_substring = P.subList(p_position, p_position + search_length).stream().collect(Collectors.joining());
                
                ArrayList<Integer> positions = hash_table.get(hash(p_substring));

                for (Integer position : positions) {

                    String t_substring = T.subList(position, position + search_length).stream().collect(Collectors.joining());

                    if (t_substring.equals(p_substring)) {

                        t_position = position;

                        Integer k = search_length;

                        while (p_position + k < P.size() && t_position + k < T.size()
                                && P.get(p_position + k).equals(T.get(t_position + k))
                                && isUnmarked(P.get(p_position + k))
                                && isUnmarked(T.get(t_position + k)))
                            k ++;

                        if (k > 2 * search_length)
                            return k;
                        else {
                            if (longest_max_match < search_length)
                                longest_max_match = search_length;

                            matches.add(new MatchValue(p_position, t_position, k));
                        }
                    }
                }
                p_position ++;
            }
        }
        if (!matches.isEmpty()){
            all_matches.add(matches);
        }
        return longest_max_match;
    }

    private void markStrings(List<String> p, List<String> t) {
        all_matches.forEach(matches -> matches.stream().filter(match -> !isOccluded(match, tiles)).forEach(match -> {
            IntStream.range(0, match.getLengthMatch()).forEach(i -> {

                Integer pattern_position = match.getPatternPosition() + i;
                Integer text_position = match.getTextPosition() + i;

                p.set(pattern_position, markToken(p.get(pattern_position)));
                t.set(text_position, markToken(t.get(text_position)));
            });
            tiles.add(match);
        }));
        all_matches.clear();
    }

    private static Long hash(String string) {
        AtomicLong hash = new AtomicLong(0);
        string.chars().forEach(symbol -> hash.set((hash.intValue() << 1) + symbol));
        return hash.longValue();
    }

    private Boolean isUnmarked(String string) {
        return string.length() > 0 && string.charAt(0) != '*';
    }

    private Boolean isMarked(String string) {
        return (!isUnmarked(string));
    }

    private String markToken(String string) {
        return "*" + string;
    }

    private Boolean isOccluded(MatchValue match_value, List<MatchValue> tiles) {
        return tiles.stream().anyMatch(tile -> (tile.getPatternPosition() + tile.getLengthMatch() == match_value.getPatternPosition() + match_value.getLengthMatch()) &&
                                      (tile.getTextPosition() + tile.getLengthMatch() == match_value.getTextPosition() + match_value.getLengthMatch()));
    }


    private Optional<Integer> distanceToNextTile(Integer current_position, List<String> tokens) {
        Integer distance_to_next_tile = (int) StreamUtils.takeWhile(tokens.stream().skip(current_position + 1), this::isUnmarked).count();
        return current_position + distance_to_next_tile + 1 != tokens.size() ? Optional.of(distance_to_next_tile + 1) : Optional.empty(); // ????????
    }

    private Optional<Integer> jumpToNextUnmarkedTokenAfterTile(Integer current_position, List<String> tokens) {

        Optional<Integer> position_after_next_tile = Optional.empty();

        Optional<Integer> distance_to_next_tile = distanceToNextTile(current_position, tokens);

        if (distance_to_next_tile.isPresent()) {

            Integer count_marked_tokens = (int) StreamUtils.takeWhile(tokens.stream().skip(current_position + distance_to_next_tile.get() + 1), this::isMarked).count();

            if (current_position + distance_to_next_tile.get() + count_marked_tokens + 1 <= tokens.size() - 1) {
                position_after_next_tile = Optional.of(current_position + distance_to_next_tile.get() + count_marked_tokens + 1);
            }
        }

        return position_after_next_tile;
    }

    private Float similarity(List<String> p, List<String> t, List<MatchValue> tiles) {
        return (float) (2 * coverage(tiles)) / (float) (p.size() + t.size());
    }

    private Integer coverage(List<MatchValue> tiles) {
        return tiles.stream().collect(Collectors.summingInt(MatchValue::getLengthMatch));
    }
}