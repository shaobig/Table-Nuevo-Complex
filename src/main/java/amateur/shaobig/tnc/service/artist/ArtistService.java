package amateur.shaobig.tnc.service.artist;

import amateur.shaobig.tnc.entity.Artist;
import amateur.shaobig.tnc.repository.ArtistRepository;
import amateur.shaobig.tnc.service.CreateService;
import amateur.shaobig.tnc.service.FindService;
import amateur.shaobig.tnc.service.MergeService;
import amateur.shaobig.tnc.service.ReadService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class ArtistService implements CreateService<Artist, Artist>, ReadService<Optional<Artist>>, FindService<Artist>, MergeService<Artist> {

    private final ArtistRepository artistRepository;

    @Override
    public Artist create(Artist artist) {
        return getArtistRepository().save(artist);
    }

    @Override
    public Optional<Artist> read(Long id) {
        return getArtistRepository().findById(id);
    }

    @Override
    public boolean isFound(Artist artist) {
        return getArtistRepository().existsByNameAndStatusAndLocation(artist.getName(), artist.getStatus(), artist.getLocation());
    }

    @Override
    public Artist merge(Artist artist) {
        return getArtistRepository().findByNameAndStatusAndLocation(artist.getName(), artist.getStatus(), artist.getLocation());
    }

}
