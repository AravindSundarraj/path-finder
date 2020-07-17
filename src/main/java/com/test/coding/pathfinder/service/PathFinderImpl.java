package com.test.coding.pathfinder.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.*;

@Slf4j
@Component
public class PathFinderImpl {


    public static final Map<String, Set<String>> pathFinderMap
            = new HashMap<>();

    @Value("${data.file:classpath:city.txt}")
    private String city;

    @Autowired
    private ResourceLoader resourceLoader;

    @PostConstruct
    public void constructPathFinder() throws IOException {

        log.info("Extract Source and Destination cities in Graph Structure is In-Progress....");

        Resource resource = resourceLoader.getResource(city);

        InputStream inputStream;

        if (!resource.exists()) {

            inputStream = new FileInputStream(new File(city));
        } else {

            inputStream = resource.getInputStream();
        }

        Scanner scanner = new Scanner(inputStream);

        while (scanner.hasNext()) {

            String line = scanner.nextLine();
            if (StringUtils.isEmpty(line)) continue;

            log.info(line);

            String[] split = line.split(",");
            String source = split[0].trim().toLowerCase();
            String destination = split[1].trim().toLowerCase();


            pathFinderMap.computeIfAbsent(source, f -> {
                Set<String> set = new HashSet<String>();
                return set;
            }).add(destination);

            pathFinderMap.computeIfAbsent(destination, f -> {
                Set<String> set = new HashSet<String>();
                return set;
            }).add(source);


        }

        pathFinderMap.entrySet().stream().forEach(e ->
                log.info("City-Vertex: " + e.getKey() + " City-Edges: " + e.getValue())
        );

        log.info("Extract Source and Destination cities in Graph Structure is completed....");

    }

}

