package com.test.coding.pathfinder.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.test.coding.pathfinder.service.PathFinderImpl.pathFinderMap;

@Component
@Slf4j
public class PathTraversalImpl {

    private static final String YES = "yes";

    private static final String NO = "no";

    public String getRoute(String source, String destination) {


        log.info("is the path exist between {} and {}", source, destination);
        if (Objects.isNull(pathFinderMap.get(source)) || Objects.isNull(pathFinderMap.
                get(destination))) return NO;
        else if (source.equalsIgnoreCase(destination)) return YES;

        else if (pathFinderMap.get(source).contains(destination)) return YES;

        Queue<String> queue = new LinkedList<>();

        Set<String> verifiedSet = new HashSet<>();

        queue.add(source);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (!verifiedSet.contains(current))
                verifiedSet.add(current);

            for (String string : pathFinderMap.get(current)) {
                if (string.equalsIgnoreCase(destination)) return YES;
                if (!verifiedSet.contains(string)) {
                    queue.add(string);
                }

            }

        }
        return NO;

    }
}
