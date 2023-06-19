package com.krabelard.model.optional;

import com.krabelard.model.enums.TableName;
import lombok.Builder;
import lombok.Value;

import java.util.Optional;

// Optional
@Value
@Builder
public class Translation {
    TableName tableName;    // Required
    String fieldName;       // Required
    String language;        // Required
    String translation;     // Required
    Long recordId;          // Conditionally required
    Long recordSubId;       // Conditionally required
    String fieldValue;      // Conditionally required

    public Optional<Long> getRecordId() {
        return Optional.ofNullable(recordId);
    }

    public Optional<Long> getRecordSubId() {
        return Optional.ofNullable(recordSubId);
    }

    public Optional<String> getFieldValue() {
        return Optional.ofNullable(fieldValue);
    }
}
