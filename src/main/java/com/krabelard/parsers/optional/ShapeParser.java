package com.krabelard.parsers.optional;

import com.krabelard.model.optional.Shape;
import com.krabelard.parsers.CsvHeaders;
import com.krabelard.parsers.GtfsCsvParser;
import com.krabelard.parsers.GtfsParsingException;
import com.krabelard.util.CsvUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;

@Slf4j
public class ShapeParser implements GtfsCsvParser<Shape> {
    private static final String GTFS_FILE_NAME = "shapes.txt";
    private final String csv;

    private ShapeParser(String dir) {
        this.csv = dir + "/" + GTFS_FILE_NAME;
    }

    public static ShapeParser of(String dir) {
        return new ShapeParser(dir);
    }

    @Override
    public List<Shape> parse() throws GtfsParsingException, NoSuchFileException {
        try {
            log.info("Parsing {}", csv);
            var headers = CsvUtil.headersAsStrings(Headers.class);
            return CsvUtil.parseCsv(csv)
                    .stream()
                    .map(r -> {
                        var values = CsvUtil.extractValues(r, headers);
                        return Shape.builder()
                                .id(values.get(Headers.ShapeId.value))
                                .ptLatitude(Double.parseDouble(values.get(Headers.ShapePtLat.value)))
                                .ptLongitude(Double.parseDouble(values.get(Headers.ShapePtLon.value)))
                                .ptSequence(Integer.parseInt(values.get(Headers.ShapePtSequence.value)))
                                .distTraveled(CsvUtil.parseNullableDouble(values.get(Headers.ShapeDistTraveled.value)))
                                .build();
                    })
                    .toList();
        } catch (NoSuchFileException e) {
            throw e;
        } catch (IOException e) {
            throw new GtfsParsingException(csv, e);
        }
    }

    @RequiredArgsConstructor
    @Getter
    private enum Headers implements CsvHeaders {
        ShapeId("shape_id"),
        ShapePtLat("shape_pt_lat"),
        ShapePtLon("shape_pt_lon"),
        ShapePtSequence("shape_pt_sequence"),
        ShapeDistTraveled("shape_dist_traveled");

        private final String value;
    }
}
