package com.example.modelMapper.converter;

import com.example.enums.Status;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;


@Component
public class StringStatusConverter implements Converter<String, Status> {

    @Override
    public Status convert(MappingContext<String, Status> context) {
        return Status.getEnum(context.getSource());
    }

}
