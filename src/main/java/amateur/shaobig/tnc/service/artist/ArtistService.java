package amateur.shaobig.tnc.service.artist;

import amateur.shaobig.tnc.entity.Artist;
import amateur.shaobig.tnc.repository.ArtistRepository;
import amateur.shaobig.tnc.service.FindService;
import amateur.shaobig.tnc.service.MergeService;
import amateur.shaobig.tnc.service.ReadAllService;
import amateur.shaobig.tnc.service.ReadService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class ArtistService implements ReadService<Optional<Artist>>, ReadAllService<Artist>, MergeService<Artist>, FindService<Artist> {

    private final ArtistRepository artistRepository;

    @Override
    public Optional<Artist> read(Long id) {
        return getArtistRepository().findById(id);
    }

    @Override
    public List<Artist> readAll() {
        return getArtistRepository().findAll();
    }

    @Override
    public Artist merge(Artist artist) {
        return getArtistRepository().getReferenceById(artist.getId());
    }

    @Override
    public boolean isFound(Artist artist) {
        return getArtistRepository().existsByNameAndStatus(artist.getName(), artist.getStatus());
    }

}
