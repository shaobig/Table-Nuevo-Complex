package amateur.shaobig.tnc.entity.enums.converters;

import amateur.shaobig.tnc.entity.enums.AlbumType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

@Converter(autoApply = true)
public class AlbumTypeConverter implements AttributeConverter<AlbumType, String> {

    @Override
    public String convertToDatabaseColumn(AlbumType type) {
        return Optional.of(type)
                .map(AlbumType::getAlias)
                .map(String::toUpperCase)
                .orElseThrow(() -> new NullPointerException("Can't convert the album type attribute"));
    }

    @Override
    public AlbumType convertToEntityAttribute(String type) {
        return Optional.of(type)
                .map(AlbumType::valueOf)
                .orElseThrow(() -> new NullPointerException("Can't find the album type attribute"));
    }

}
