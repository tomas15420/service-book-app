package cz.uhk.fim.servicebookapp.config.converter;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@ConfigurationPropertiesBinding
public class LocaleDateConverter implements Converter<LocalDate, String> {
    @Override
    public String convert(LocalDate source) {
        System.out.println("source: "+ source.format(DateTimeFormatter.ISO_LOCAL_DATE));
        return source.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
