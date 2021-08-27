package com.example.modelMapper.converter;

import com.example.enums.Status;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;


@Component
public class StatusToStringConverter implements Converter<Status, String> {

    @Override
    public String convert(MappingContext<Status, String> context) {
        if (context.getSource() == null) {
            return null;
        }
        return context.getSource().getValue();
    }

}
