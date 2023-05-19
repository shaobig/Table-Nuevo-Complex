package amateur.shaobig.table_nuevo_complex.service.artist;

import amateur.shaobig.table_nuevo_complex.dto.artist.ReadAllArtistDto;
import amateur.shaobig.table_nuevo_complex.dto.artist.ReadArtistDto;
import amateur.shaobig.table_nuevo_complex.service.ReadAllService;
import amateur.shaobig.table_nuevo_complex.service.ReadService;
import amateur.shaobig.table_nuevo_complex.transformer.artist.ReadAllArtistDtoTransformer;
import amateur.shaobig.table_nuevo_complex.transformer.artist.ReadArtistDtoTransformer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class ArtistDtoService implements ReadService<ReadArtistDto>, ReadAllService<ReadAllArtistDto> {

    private final ReadArtistDtoTransformer readArtistDtoTransformer;
    private final ReadAllArtistDtoTransformer readAllArtistDtoTransformer;
    private final ArtistProxyService artistProxyService;

    @Override
    public ReadArtistDto read(Long id) {
        return getReadArtistDtoTransformer().transform(getArtistProxyService().read(id));
    }

    @Override
    public List<ReadAllArtistDto> readAll() {
        return getArtistProxyService().readAll().stream()
                .map(getReadAllArtistDtoTransformer()::transform)
                .sorted(Comparator.comparing(ReadAllArtistDto::name))
                .collect(Collectors.toList());
    }

}
