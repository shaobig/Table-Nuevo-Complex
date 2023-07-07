package amateur.shaobig.tnc.service.artist;

import amateur.shaobig.tnc.dto.artist.ReadArtistDto;
import amateur.shaobig.tnc.service.ReadService;
import amateur.shaobig.tnc.transformer.artist.ReadArtistDtoTransformer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class ArtistDtoService implements ReadService<ReadArtistDto> {

    private final ReadArtistDtoTransformer readArtistDtoTransformer;
    private final ArtistProxyService artistProxyService;

    @Override
    public ReadArtistDto read(Long id) {
        return getReadArtistDtoTransformer().transform(getArtistProxyService().read(id));
    }

}
