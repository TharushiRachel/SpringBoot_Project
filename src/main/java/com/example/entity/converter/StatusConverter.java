package com.example.entity.converter;

import com.example.enums.Status;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * This converter will help ORM to map CategoryStatus to a db column.
 */
@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {

    @Override
    public String convertToDatabaseColumn(Status attribute) {
        return attribute.getValue();
    }

    @Override
    public Status convertToEntityAttribute(String dbData) {
        return Status.getEnum(dbData);
    }

}
