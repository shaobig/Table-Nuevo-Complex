package amateur.shaobig.table_nuevo_complex.service.artist;

import amateur.shaobig.table_nuevo_complex.entity.Album;
import amateur.shaobig.table_nuevo_complex.entity.Artist;
import amateur.shaobig.table_nuevo_complex.exception.types.EntityNotFoundException;
import amateur.shaobig.table_nuevo_complex.service.FindService;
import amateur.shaobig.table_nuevo_complex.service.MergeService;
import amateur.shaobig.table_nuevo_complex.service.ReadAllService;
import amateur.shaobig.table_nuevo_complex.service.ReadService;
import amateur.shaobig.table_nuevo_complex.service.location.LocationService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class ArtistProxyService implements ReadService<Artist>, ReadAllService<Artist>, MergeService<Artist>, FindService<Artist> {

    private final ArtistService artistService;
    private final LocationService locationService;

    @Override
    public Artist read(Long id) {
        Artist artist = getArtistService().read(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Can't find the artist with the id = %d", id)));
        artist.getAlbums().sort(Comparator.<Album>comparingInt(album -> album.getType().getOrder()).thenComparingInt(Album::getYear));
        return artist;
    }

    @Override
    public List<Artist> readAll() {
        return getArtistService().readAll();
    }

    @Override
    public Artist merge(Artist artist) {
        return getArtistService().merge(artist);
    }

    @Override
    public boolean isFound(Artist artist) {
        return getArtistService().isFound(artist) && getLocationService().isFound(artist.getLocation());
    }

}
