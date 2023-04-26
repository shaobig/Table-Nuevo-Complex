package amateur.shaobig.table_nuevo_complex.entity.enums.converters;

import amateur.shaobig.table_nuevo_complex.entity.enums.SongType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

@Converter(autoApply = true)
public class SongTypeConverter implements AttributeConverter<SongType, String> {

    @Override
    public String convertToDatabaseColumn(SongType attribute) {
        return Optional.of(attribute)
                .map(SongType::getAlias)
                .orElseThrow(() -> new NullPointerException("Can't convert the song type attribute"));
    }

    @Override
    public SongType convertToEntityAttribute(String attribute) {
        return Optional.of(attribute)
                .map(SongType::valueOf)
                .orElseThrow(() -> new NullPointerException("Can't find the song type attribute"));
    }

}
