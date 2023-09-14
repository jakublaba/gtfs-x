package com.krabelard.model.optional;

import com.krabelard.model.enums.TableName;
import lombok.Builder;
import lombok.Value;

import java.util.Optional;

/**
 * Maps an entry from optional <code>translations.txt</code> file.
 * <p>
 * For full specification, visit <a href="https://gtfs.org/schedule/reference/#translationstxt">the gtfs reference</a>
 */
@Value
@Builder
public class Translation {
    /**
     * <code>table_name</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Defined the table that contains the field to be translated.
     * <p>
     * Any file added to GTFS in the future will have a {@link TableName} value equivalent to the file name without the extension.
     */
    TableName tableName;
    /**
     * <code>field_name</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Name of the field to be translated. Fields with type <code>Text</code> may be translated, fields wih type
     * <code>URL</code>, <code>Email</code> and <code>Phone number</code> may also be "translated" to provide resources
     * in the correct language. Fields with other types should not be translated.
     */
    String fieldName;
    /**
     * <code>language</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Language of translation (expressed as a language code).
     * <p>
     * If the language is the same as in {@link FeedInfo#feedLanguage}, the original value of this field will be assumed
     * to be the default value to use in languages without specific translations (if {@link FeedInfo#defaultLanguage}
     * doesn't specify otherwise).
     */
    String language;
    /**
     * <code>translation</code>
     * <p>
     * <b>Required</b>
     * <p>
     * Translated value.
     */
    String translation;
    /**
     * <code>record_id</code>
     * <p>
     * <b>Conditionally required</b>
     * <p>
     * Defines the record that corresponds to the field to be translated. The value in this field must be the first and
     * only field of a table's primary keys, as defined in the primary key attribute for each table.
     * <p>
     * <b>Conditionally required</b>:
     * <li><b>Forbidden</b> if {@link Translation#tableName} is {@link TableName#FeedInfo}.</li>
     * <li><b>Forbidden</b> if {@link Translation#fieldValue} is defined.</li>
     * <li><b>Required</b> if {@link Translation#fieldValue} is empty.</li>
     */
    String recordId;
    /**
     * <code>record_sub_id</code>
     * <p>
     * <b>Conditionally required</b>
     * <p>
     * Helps the record that contains the field to be translated when the table doesn't have a unique ID.
     * Therefore, the value in this field is the secondary ID of the table.
     * <p>
     * <b>Conditionally required</b>:
     * <li><b>Forbidden</b> if {@link Translation#tableName} is {@link TableName#FeedInfo}</li>
     * <li><b>Forbidden</b> if {@link Translation#fieldValue} is defined</li>
     * <li><b>Required</b> if {@link Translation#fieldValue} is not defined</li>
     */
    String recordSubId;
    /**
     * <code>field_value</code>
     * <p>
     * <b>Conditionally required</b>
     * <p>
     * Instead of defining which record should be translated by using {@link Translation#recordId} and {@link Translation#recordSubId},
     * this field can be used to define the value which should be translated. When used, the translation will be applied
     * when the fields identified by {@link Translation#tableName} and {@link Translation#fieldName} contains the exact
     * same value defined in this field.
     * <p>
     * The field must have <b>exactly</b> the value defined in this field. If only a subset of the value matches this field,
     * the translation won't be applied.
     * <p>
     * If two translation rules match the same record (one with {@link Translation#fieldValue}, and the other one with
     * {@link Translation#recordId}), the rule with {@link Translation#recordId} takes precedence.
     * <p>
     * <b>Conditionally required</b>:
     * <li><b>Forbidden</b> if {@link Translation#tableName} is {@link TableName#FeedInfo}.</li>
     * <li><b>Forbidden</b> if {@link Translation#recordId} is defined.</li>
     * <li><b>Required</b> if {@link Translation#recordId} is not defined.</li>
     */
    String fieldValue;

    public Optional<String> getRecordId() {
        return Optional.ofNullable(recordId);
    }

    public Optional<String> getRecordSubId() {
        return Optional.ofNullable(recordSubId);
    }

    public Optional<String> getFieldValue() {
        return Optional.ofNullable(fieldValue);
    }
}
