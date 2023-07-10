package amateur.shaobig.tnc.service.album;

import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.entity.Song;
import amateur.shaobig.tnc.exception.types.EntityNotFoundException;
import amateur.shaobig.tnc.service.CreateService;
import amateur.shaobig.tnc.service.ReadAllService;
import amateur.shaobig.tnc.service.ReadService;
import amateur.shaobig.tnc.service.UpdateService;
import amateur.shaobig.tnc.service.artist.ArtistProxyService;
import amateur.shaobig.tnc.service.genre.AlbumGenreListService;
import amateur.shaobig.tnc.sorting.ComparatorListArranger;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class AlbumProxyService implements CreateService<Album, Album>, ReadService<Album>, ReadAllService<Album>, UpdateService<Album, Album> {

    private final AlbumService albumService;
    private final ArtistProxyService artistProxyService;
    private final AlbumGenreListService albumGenreListService;
    private final ComparatorListArranger<Song> songComparatorListArranger;

    @Override
    public Album create(Album album) {
        album.setArtist(getArtistProxyService().create(album.getArtist()));
        album.setGenres(getAlbumGenreListService().create(album.getGenres()));
        return getAlbumService().create(album);
    }

    @Override
    public Album read(Long id) {
        Album album = getAlbumService().read(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Can't find the album with the id = %d", id)));
        album.setSongs(getSongComparatorListArranger().arrange(album.getSongs()));
        return album;
    }

    @Override
    public List<Album> readAll() {
        return getAlbumService().readAll();
    }

    @Override
    public Album update(Album album) {
        return getAlbumService().update(album);
    }

}
