package amateur.shaobig.table_nuevo_complex.entity.enums.converters;

import amateur.shaobig.table_nuevo_complex.entity.enums.ArtistStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

@Converter(autoApply = true)
public class ArtistStatusConverter implements AttributeConverter<ArtistStatus, String> {

    @Override
    public String convertToDatabaseColumn(ArtistStatus status) {
        return Optional.of(status)
                .map(ArtistStatus::getAlias)
                .orElseThrow(() -> new NullPointerException("Can't convert the artist status"));
    }

    @Override
    public ArtistStatus convertToEntityAttribute(String attribute) {
        return Optional.of(attribute)
                .map(ArtistStatus::valueOf)
                .orElseThrow(() -> new NullPointerException("Can't find the artist status attribute"));
    }

}
