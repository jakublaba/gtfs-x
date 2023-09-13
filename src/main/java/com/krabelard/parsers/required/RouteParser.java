package com.krabelard.parsers.required;

import com.krabelard.model.enums.DropOffType;
import com.krabelard.model.enums.PickupType;
import com.krabelard.model.enums.RouteType;
import com.krabelard.model.required.Route;
import com.krabelard.parsers.CsvHeaders;
import com.krabelard.parsers.GtfsCsvParser;
import com.krabelard.parsers.GtfsParsingException;
import com.krabelard.util.CsvUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
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
            var headers = CsvUtil.headersAsStrings(Headers.values());
            return CsvUtil.parseCsv(csv)
                    .stream()
                    .map(r -> {
                        var values = CsvUtil.extractValues(r, headers);
                        return Route.builder()
                                .id(values.get(Headers.RouteId.value))
                                .agencyId(values.get(Headers.AgencyId.value))
                                .shortName(values.get(Headers.RouteShortName.value))
                                .longName(values.get(Headers.RouteLongName.value))
                                .description(values.get(Headers.RouteDescription.value))
                                .type(CsvUtil.parseEnum(
                                        RouteType.class,
                                        Integer.parseInt(values.get(Headers.RouteType.value))
                                ))
                                .url(values.get(Headers.RouteUrl.value))
                                .color(values.get(Headers.RouteColor.value))
                                .textColor(values.get(Headers.RouteTextColor.value))
                                .sortOrder(CsvUtil.parseNullableInt(values.get(Headers.RouteSortOrder.value)))
                                .continuousPickup(CsvUtil.parseEnum(
                                        PickupType.class,
                                        CsvUtil.parseNullableInt(values.get(Headers.ContinuousPickup.value))
                                ))
                                .continuousDropOff(CsvUtil.parseEnum(
                                        DropOffType.class,
                                        CsvUtil.parseNullableInt(values.get(Headers.ContinuousDropOff.value))
                                ))
                                .networkId(values.get(Headers.NetworkId.value))
                                .build();
                    })
                    .toList();
        } catch (IOException e) {
            throw new GtfsParsingException(csv, e);
        }
    }

    @RequiredArgsConstructor
    @Getter
    private enum Headers implements CsvHeaders {
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
