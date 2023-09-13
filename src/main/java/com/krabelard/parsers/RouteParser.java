package com.krabelard.parsers;

import com.krabelard.model.required.Route;
import com.krabelard.util.CsvUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

@Slf4j
public class RouteParser implements GtfsCsvParser<Route> {
    private static final String GTFS_FILE_NAME = "routes.txt";
    private final String csv;

    private RouteParser(String dir) {
        this.csv = dir + "/" + GTFS_FILE_NAME;
    }

    public static RouteParser of(String dir) {
        return new RouteParser(dir);
    }

    @Override
    public Collection<Route> parse() throws GtfsParsingException {
        try {
            log.info("Parsing {}", csv);
            var headers = Arrays.stream(Headers.values())
                    .map(h -> h.value)
                    .toArray(String[]::new);
            return CsvUtil.parseCsv(csv)
                    .stream()
                    .map(r -> {
                        var values = CsvUtil.extractValues(r, headers);
                        return Route.builder()
                                .id(values.get(Headers.RouteId.value))
                                // TODO left off here
                                .build();
                    })
                    .toList();
        } catch (IOException e) {
            throw new GtfsParsingException(csv, e);
        }
    }

    @RequiredArgsConstructor
    private enum Headers {
        RouteId("route_id"),
        AgencyId("agency_id"),
        RouteShortName("route_short_name"),
        RouteLongName("route_long_name"),
        RouteDescription("route_desc"),
        RouteType("route_type"),
        RouteUrl("route_url"),
        RouteColor("route_color"),
        RouteTextColor("route_text_color"),
        RouteSortOrder("route_sort_order"),
        ContinuousPickup("continuous_pickup"),
        ContinuousDropOff("continuous_drop_off"),
        NetworkId("network_id");

        private final String value;
    }
}
