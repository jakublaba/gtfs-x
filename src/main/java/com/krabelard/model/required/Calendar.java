package com.krabelard.model.required;

import com.krabelard.model.enums.ServiceAvailability;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

// Conditionally required
@Value
@Builder
public class Calendar {
    long serviceId;                 // Required
    ServiceAvailability monday;     // Required
    ServiceAvailability tuesday;    // Required
    ServiceAvailability wednesday;  // Required
    ServiceAvailability thursday;   // Required
    ServiceAvailability friday;     // Required
    ServiceAvailability saturday;   // Required
    ServiceAvailability sunday;     // Required
    LocalDate startDate;            // Required
    LocalDate endDate;              // Required
}
