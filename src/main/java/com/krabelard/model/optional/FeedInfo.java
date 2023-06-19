package com.krabelard.model.optional;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.Optional;

// Optional
@Value
@Builder
public class FeedInfo {
    String feedPublisherName;   // Required
    String feedPublisherUrl;    // Required
    String feedLanguage;        // Required
    String defaultLanguage;     // Optional
    LocalDate feedStartDate;    // Optional
    LocalDate feedEndDate;      // Optional
    String feedVersion;         // Optional
    String feedContactEmail;    // Optional
    String feedContactUrl;      // Optional

    public Optional<String> getDefaultLanguage() {
        return Optional.ofNullable(defaultLanguage);
    }

    public Optional<LocalDate> getFeedStartDate() {
        return Optional.ofNullable(feedStartDate);
    }

    public Optional<LocalDate> getFeedEndDate() {
        return Optional.ofNullable(feedEndDate);
    }

    public Optional<String> getFeedVersion() {
        return Optional.ofNullable(feedVersion);
    }

    public Optional<String> getFeedContactEmail() {
        return Optional.ofNullable(feedContactEmail);
    }

    public Optional<String> getFeedContactUrl() {
        return Optional.ofNullable(feedContactUrl);
    }
}
