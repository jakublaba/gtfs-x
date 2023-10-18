package com.krabelard.parsers.required;

import com.krabelard.model.enums.RouteType;
import com.krabelard.model.required.Route;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import testutil.TestConstants;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RouteParserTest {
    @Test
    @SneakyThrows
    void shouldParseCsvCorrectly() {
        var routes = RouteParser.of(TestConstants.FEED_DIR).parse();
        var expectedSize = 2;
        var expectedRoute = Route.builder()
                .agencyId("CT")
                .id("303-20670")
                .shortName("303")
                .longName("MAX Orange Brentwood/Saddletowne")
                .type(RouteType.BUS)
                .url("www.calgarytransit.com/content/transit/en/home/rider-information/max.html")
                .color("#ff8000")
                .textColor("#ffffff")
                .build();
        assertEquals(expectedSize, routes.size());
        assertEquals(expectedRoute, routes.get(0));
    }
}
