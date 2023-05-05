package amateur.shaobig.table_nuevo_complex.service.artist;

import amateur.shaobig.table_nuevo_complex.entity.Artist;
import amateur.shaobig.table_nuevo_complex.repository.ArtistRepository;
import amateur.shaobig.table_nuevo_complex.service.ReadAllService;
import amateur.shaobig.table_nuevo_complex.service.ReadService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class ArtistService implements ReadService<Optional<Artist>>, ReadAllService<Artist> {

    private final ArtistRepository artistRepository;

    @Override
    public Optional<Artist> read(Long id) {
        return getArtistRepository().findById(id);
    }

    @Override
    public List<Artist> readAll() {
        return getArtistRepository().findAll();
    }

}
