package com.krabelard.model.required;

import com.krabelard.model.enums.ExceptionType;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

// Conditionally required
@Value
@Builder
public class CalendarDate {
    long serviceId;                 // Required
    LocalDate date;                 // Required
    ExceptionType exceptionType;    // Required
}
