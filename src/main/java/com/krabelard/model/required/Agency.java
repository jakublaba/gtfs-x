package com.krabelard.model.required;

import lombok.Builder;
import lombok.Value;

import java.util.Optional;

/**
 * Maps an entry from required <code>agencies.txt</code> file.
 * <p>
 * For full specification, visit <a href="https://gtfs.org/schedule/reference/#agencytxt">the gtfs reference</a>
 */
@Value
@Builder
public class Agency {
    /**
     * <code>agency_id</code>
     * <p>
     * <b>Conditionally required</b>
     * <p>
     * Required when the dataset contains data for multiple transit agencies.
     */
    String id;
    /**
     * <code>agency_name</code>
     * <p>
     * <b>Required</b>
     */
    String name;
    /**
     * <code>agency_url</code>
     * <p>
     * <b>Required</b>
     */
    String url;
    /**
     * <code>agency_timezone</code>
     * <p>
     * <b>Required</b>
     * <p>
     * If multiple agencies are specified in the dataset, each must have the same timezone.
     */
    String timezone;
    /**
     * <code>agency_lang</code>
     * <p>
     * <b>Optional</b>
     */
    String language;
    /**
     * <code>agency_phone</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * May contain punctuation marks to group the digits of the number.
     */
    String phone;
    /**
     * <code>agency_fare_url</code>
     * <p>
     * <b>Optional</b>
     */
    String fareUrl;
    /**
     * <code>agency_email</code>
     * <p>
     * <b>Optional</b>
     */
    String email;

    public Optional<String> getLanguage() {
        return Optional.ofNullable(language);
    }

    public Optional<String> getPhone() {
        return Optional.ofNullable(phone);
    }

    public Optional<String> getFareUrl() {
        return Optional.ofNullable(fareUrl);
    }

    public Optional<String> getEmail() {
        return Optional.ofNullable(email);
    }
}
