package com.krabelard.model.required;

import com.krabelard.model.enums.ExceptionType;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

/**
 * Maps an entry from conditionally required <code>calendar_dates.txt</code> file.
 * <p>
 * Required if {@link Calendar} entities are omitted, in which case {@link CalendarDate} entities must contain all dates of service.
 * <p>
 * For full specification, visit <a href="https://gtfs.org/schedule/reference/#calendar_datestxt">the gtfs reference</a>
 */
@Value
@Builder
public class CalendarDate {
    /**
     * <code>service_id</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Identifies a set of dates when service exception occurs for one or more routes.
     * When both {@link Calendar} and {@link CalendarDate} entities are present,
     * each ({@link CalendarDate#serviceId}, {@link CalendarDate#date}) pair may only appear once.
     * If {@link CalendarDate#serviceId} appears also in {@link Calendar}, the information in this entity
     * modifies data from {@link Calendar}.
     */
    long serviceId;
    /**
     * <code>date</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Date when service exception occurs.
     */
    LocalDate date;
    /**
     * <code>exception_type</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Indicates whether service is available on the date specified in the date field.
     */
    ExceptionType exceptionType;
}
