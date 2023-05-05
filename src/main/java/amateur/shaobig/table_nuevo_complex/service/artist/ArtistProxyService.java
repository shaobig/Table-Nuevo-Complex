package amateur.shaobig.table_nuevo_complex.service.artist;

import amateur.shaobig.table_nuevo_complex.entity.Artist;
import amateur.shaobig.table_nuevo_complex.exception.types.EntityNotFoundException;
import amateur.shaobig.table_nuevo_complex.service.ReadAllService;
import amateur.shaobig.table_nuevo_complex.service.ReadService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class ArtistProxyService implements ReadService<Artist>, ReadAllService<Artist> {

    private final ArtistService artistService;

    @Override
    public Artist read(Long id) {
        return getArtistService().read(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Can't find the artist with the id = %d", id)));
    }

    @Override
    public List<Artist> readAll() {
        return getArtistService().readAll();
    }

}
