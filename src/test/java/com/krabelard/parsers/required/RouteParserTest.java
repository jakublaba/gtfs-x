package com.krabelard.parsers.required;

import com.krabelard.model.enums.RouteType;
import com.krabelard.model.required.Route;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import testutil.TestConstants;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RouteParserTest {
    @Test
    @SneakyThrows
    void shouldParseCsvCorrectly() {
        var routes = RouteParser.of(TestConstants.FEED_DIR).parse();
        var expectedSize = 1;
        var expectedRoute = Route.builder()
                .id("A")
                .shortName("17")
                .longName("Mission")
                .description("The \"A\" route travels from lower Mission to Downtown.")
                .type(RouteType.Bus)
                .build();
        assertEquals(expectedSize, routes.size());
        assertEquals(expectedRoute, routes.get(0));
    }
}
