package amateur.shaobig.tnc.entity.enums.converters;

import amateur.shaobig.tnc.entity.enums.ArtistStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

@Converter(autoApply = true)
public class ArtistStatusConverter implements AttributeConverter<ArtistStatus, String> {

    @Override
    public String convertToDatabaseColumn(ArtistStatus status) {
        return Optional.of(status)
                .map(ArtistStatus::getAlias)
                .map(String::toUpperCase)
                .orElseThrow(() -> new NullPointerException("Can't convert the artist status"));
    }

    @Override
    public ArtistStatus convertToEntityAttribute(String attribute) {
        return Optional.of(attribute)
                .map(ArtistStatus::valueOf)
                .orElseThrow(() -> new NullPointerException("Can't find the artist status attribute"));
    }

}
