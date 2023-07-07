package amateur.shaobig.tnc.service.artist;

import amateur.shaobig.tnc.entity.Artist;
import amateur.shaobig.tnc.exception.types.EntityNotFoundException;
import amateur.shaobig.tnc.service.CreateService;
import amateur.shaobig.tnc.service.ReadService;
import amateur.shaobig.tnc.service.artist.sorting.ArtistAlbumTypeOrderAndYearListArranger;
import amateur.shaobig.tnc.service.location.LocationProxyService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class ArtistProxyService implements CreateService<Artist, Artist>, ReadService<Artist> {

    private final ArtistService artistService;
    private final LocationProxyService locationProxyService;
    private final ArtistAlbumTypeOrderAndYearListArranger artistAlbumTypeOrderAndYearListArranger;

    @Override
    public Artist create(Artist artist) {
        artist.setLocation(getLocationProxyService().create(artist.getLocation()));
        return getArtistService().isFound(artist) ? getArtistService().merge(artist) : getArtistService().create(artist);
    }

    @Override
    public Artist read(Long id) {
        Artist artist = getArtistService().read(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Can't find the artist with the id = %d", id)));
        artist.setAlbums(getArtistAlbumTypeOrderAndYearListArranger().arrange(artist.getAlbums()));
        return artist;
    }

}
