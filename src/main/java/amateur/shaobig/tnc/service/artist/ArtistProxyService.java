package amateur.shaobig.tnc.service.artist;

import amateur.shaobig.tnc.entity.Artist;
import amateur.shaobig.tnc.exception.types.EntityNotFoundException;
import amateur.shaobig.tnc.service.FindService;
import amateur.shaobig.tnc.service.MergeService;
import amateur.shaobig.tnc.service.ReadAllService;
import amateur.shaobig.tnc.service.ReadService;
import amateur.shaobig.tnc.service.artist.sorting.AlbumTypeYearListArranger;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class ArtistProxyService implements ReadService<Artist>, ReadAllService<Artist>, MergeService<Artist>, FindService<Artist> {

    private final ArtistService artistService;
    private final AlbumTypeYearListArranger albumTypeYearListArranger;

    @Override
    public Artist read(Long id) {
        Artist artist = getArtistService().read(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Can't find the artist with the id = %d", id)));
        artist.setAlbums(getAlbumTypeYearListArranger().arrange(artist.getAlbums()));
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
        if (Objects.isNull(artist.getId())) {
            return false;
        }
        return getArtistService().isFound(artist);
    }

}
