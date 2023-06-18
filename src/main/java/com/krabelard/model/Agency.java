package com.krabelard.model;

import lombok.Builder;
import lombok.Value;

import javax.swing.text.html.Option;
import java.util.Optional;

@Value
@Builder
public class Agency {
    Long id;            // Conditionally required
    String name;        // Required
    String url;         // Required
    String timezone;    // Required
    String language;    // Optional
    String phoneNumber; // Optional
    String fareUrl;     // Optional
    String email;       // Optional

    public Optional<String> getLanguage() {
        return Optional.ofNullable(language);
    }

    public Optional<String> getPhoneNumber() {
        return Optional.ofNullable(phoneNumber);
    }

    public Optional<String> getFareUrl() {
        return Optional.ofNullable(fareUrl);
    }

    public Optional<String> getEmail() {
        return Optional.ofNullable(email);
    }
}
