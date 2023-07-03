package amateur.shaobig.tnc.service.artist;

import amateur.shaobig.tnc.dto.artist.ReadAllArtistDto;
import amateur.shaobig.tnc.dto.artist.ReadArtistDto;
import amateur.shaobig.tnc.service.ReadAllService;
import amateur.shaobig.tnc.service.ReadService;
import amateur.shaobig.tnc.transformer.artist.ReadAllArtistDtoTransformer;
import amateur.shaobig.tnc.transformer.artist.ReadArtistDtoTransformer;
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
