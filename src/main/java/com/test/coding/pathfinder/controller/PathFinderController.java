package com.test.coding.pathfinder.controller;

import static com.test.coding.pathfinder.service.PathFinderImpl.*;

import com.test.coding.pathfinder.exception.handler.PathFinderException;
import com.test.coding.pathfinder.service.PathTraversalImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static java.util.Objects.*;

@RestController
@RequestMapping(value = "v1/route")
@Api(value = "Find  a path exists between two cities")
public class PathFinderController {

    @Autowired
    PathTraversalImpl pathTraversalImpl;

    @ApiOperation(value = "Find if a path exists between two cities",
            notes = "Returns Yes if Path exists otherwise No ",
            response = String.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid Origin or Destination city", response = NullPointerException.class),
            @ApiResponse(code = 500, message = "Generic error", response = Exception.class),
            @ApiResponse(code = 404, message = "The resource you are trying is not found")
    })
    @GetMapping(produces = "text/plain")
    public String getConnectedRoute(
            @ApiParam(name = "origin", value = "Origin City name", required = true) @RequestParam String origin,
            @ApiParam(name = "destination", value = "Destination City name", required = true) @RequestParam String destination) {

            if(isNull(pathFinderMap.get(origin.toLowerCase()))
            ||isNull(pathFinderMap.get(destination.toLowerCase()) )){
                throw new PathFinderException("please enter valid Origin and Destination !!!");
            }
        return pathTraversalImpl.getRoute(origin.toLowerCase(), destination.toLowerCase());


    }

}
