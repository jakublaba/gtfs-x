package com.krabelard.model.required;

import com.krabelard.model.enums.ServiceAvailability;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

/**
 * Maps an entry from required <code>calendar.txt</code> file.
 * <p>
 * For full specification, visit <a href="https://gtfs.org/schedule/reference/#calendartxt">the gtfs reference</a>
 */
@Value
@Builder
public class Calendar {
    /**
     * <code>service_id</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Identifies a set of dates when service is available for one or more routes. Unique.
     */
    String serviceId;
    /**
     * <code>monday</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Indicates whether the service operates on all Mondays in the date range specified by the
     * {@link Calendar#startDate} and {@link Calendar#endDate} fields. Note that exceptions for
     * particular dates may be listed in {@link CalendarDate} entities.
     */
    ServiceAvailability monday;
    /**
     * <code>tuesday</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Functions the same way as {@link Calendar#monday}
     */
    ServiceAvailability tuesday;
    /**
     * <code>wednesday</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Functions the same way as {@link Calendar#monday}
     */
    ServiceAvailability wednesday;
    /**
     * <code>thursday</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Functions the same way as {@link Calendar#monday}
     */
    ServiceAvailability thursday;
    /**
     * <code>friday</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Functions the same way as {@link Calendar#monday}
     */
    ServiceAvailability friday;
    /**
     * <code>saturday</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Functions the same way as {@link Calendar#monday}
     */
    ServiceAvailability saturday;
    /**
     * <code>sunday</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Functions the same way as {@link Calendar#monday}
     */
    ServiceAvailability sunday;
    /**
     * <code>start_date</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Start service day for the service interval
     */
    LocalDate startDate;
    /**
     * <code>end_date</code>
     * <p>
     * <b>Required</b>
     * <p>
     * End service day for the service interval. (inclusive)
     */
    LocalDate endDate;
}
