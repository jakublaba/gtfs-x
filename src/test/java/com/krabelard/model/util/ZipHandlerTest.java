package com.krabelard.model.util;

import com.krabelard.util.ZipHandler;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Objects;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
class ZipHandlerTest {
    @AfterAll
    @SneakyThrows
    static void cleanUp() {
        ZipHandler.cleanUp("src/test/resources", ".txt");
    }

    @Nested
    class Unzip {
        @Test
        @SneakyThrows
        void should_unZIPArchive_whenItExists() {
            var zip = "src/test/resources/sample-feed-1.ZIP";
            assertDoesNotThrow(() -> ZipHandler.unzip(zip));

            var expectedFiles = Set.of(
                    "agency.txt",
                    "calendar.txt",
                    "calendar_dates.txt",
                    "fare_attributes.txt",
                    "fare_rules.txt",
                    "frequencies.txt",
                    "routes.txt",
                    "shapes.txt",
                    "stop_times.txt",
                    "stops.txt",
                    "trips.txt"
            );
            Files.walkFileTree(
                    Paths.get(zip).getParent(),
                    new SimpleFileVisitor<>() {
                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                            if (file.endsWith(".txt")) {
                                assertTrue(expectedFiles.contains(file.getFileName().toString()));
                            }
                            return FileVisitResult.CONTINUE;
                        }
                    }
            );
        }
    }

    @Nested
    class CleanUp {
        @Test
        @SneakyThrows
        void should_removeFiles() {
            var zip = "sample-feed-1.zip";
            var resource = Objects.requireNonNull(ZipHandler.class.getClassLoader().getResource(zip));
            var dir = Paths.get(resource.toURI()).getParent();

            assertDoesNotThrow(() -> ZipHandler.cleanUp(dir.toString(), ".txt"));
            Files.walkFileTree(
                    dir,
                    new SimpleFileVisitor<>() {
                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                            assertFalse(file.endsWith(".txt"));
                            return FileVisitResult.CONTINUE;
                        }
                    }
            );
        }
    }
}