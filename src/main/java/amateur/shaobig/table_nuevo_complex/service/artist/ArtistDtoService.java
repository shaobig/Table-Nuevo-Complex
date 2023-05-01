package amateur.shaobig.table_nuevo_complex.service.artist;

import amateur.shaobig.table_nuevo_complex.dto.artist.ReadArtistDto;
import amateur.shaobig.table_nuevo_complex.service.ReadService;
import amateur.shaobig.table_nuevo_complex.transformer.artist.ReadArtistDtoTransformer;
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
