package com.krabelard.model.optional;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Maps an entry from optional <code>feed_info.txt</code> file.
 * <p>
 * For full specification, visit <a href="https://gtfs.org/schedule/reference/#feed_infotxt">the gtfs reference</a>
 */
@Value
@Builder
public class FeedInfo {
    /**
     * <code>feed_publisher_name</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Full name of the organization that publishes the dataset. May be the same one as one of the {@link com.krabelard.model.required.Agency#name}
     */
    String feedPublisherName;
    /**
     * <code>feed_publisher_url</code>
     * <p>
     * <b>Required</b>
     * <p>
     * URL of the dataset publishing organization's website. May be the same as one of the {@link com.krabelard.model.required.Agency#url}
     */
    String feedPublisherUrl;
    /**
     * <code>feed_lang</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Default language used for the text in this dataset. This setting helps GTFS consumers choose capitalization rules
     * and other language-specific settings for the dataset. {@link Translation} entities can be used if the text needs
     * to be translated into other languages than the default one.
     */
    String feedLanguage;
    /**
     * <code>default_lang</code>
     * <p>
     * <b>Optional</b>
     * <p>
     *  Defines the language that should be used when the data consumer doesn't know the language of the rider.
     *  It will often be <code>en</code> (English)
     */
    String defaultLanguage;
    /**
     * <code>feed_start_date</code>
     * <p>
     * <b>Recommended</b>
     * <p>
     * The dataset provides complete and reliable schedule information for service in the period from the beginning of
     * the {@link FeedInfo#feedStartDate} to {@link FeedInfo#feedEndDate}. Both days mey be left empty if unavailable.
     * The {@link FeedInfo#feedEndDate} must not precede the {@link FeedInfo#feedStartDate} if both are given.
     * It is recommended that dataset provider give scheduled data outside this period to advise of likely future service,
     * but dataset consumers should treat it mindful of its non-authoritative status. If {@link FeedInfo#feedStartDate}
     * or {@link FeedInfo#feedEndDate} extend beyond the active calendar dates defined in {@link com.krabelard.model.required.Calendar}
     * and {@link com.krabelard.model.required.CalendarDate} entities, the dataset is making an explicit assertion that
     * there is no service for dates within the {@link FeedInfo#feedStartDate}-{@link FeedInfo#feedEndDate} range but
     * not included in the active calendar dates.
     */
    LocalDate feedStartDate;
    /**
     * <code>feed_end_date</code>
     * <p>
     * <b>Recommended</b>
     * <p>
     * See {@link FeedInfo#feedStartDate}
     */
    LocalDate feedEndDate;
    /**
     * <code>feed_version</code>
     * <p>
     * <b>Recommended</b>
     * <p>
     * Indicates current version of the GTFS dataset. GTFS-consuming applications can display this value to help dataset
     * publishers determine whether the latest dataset has been incorporated.
     */
    String feedVersion;
    /**
     * <code>feed_contact_mail</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * Email address for the communication regarding the GTFS dataset data publishing practices. This field is a technical
     * contact for GTFS-consuming applications. Customer service contact should be provided with {@link com.krabelard.model.required.Agency} entities.
     * It's recommended that at least on of {@link FeedInfo#feedContactUrl} or {@link FeedInfo#feedContactEmail} is provided.
     */
    String feedContactEmail;
    /**
     * <code>feed_contact_url</code>
     * <p>
     * <b>Optional</b>
     * <p>
     * URL for contact information, a web-form, support desk, or other tools for communication regarding the GTFS dataset
     * and data publishing practices. This field is a technical contact for GTFS-consuming applications, same as {@link FeedInfo#feedContactEmail}.
     */
    String feedContactUrl;

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
