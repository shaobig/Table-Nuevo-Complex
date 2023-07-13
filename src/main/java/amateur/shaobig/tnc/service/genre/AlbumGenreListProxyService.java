package amateur.shaobig.tnc.service.genre;

import amateur.shaobig.tnc.entity.AlbumGenre;
import amateur.shaobig.tnc.service.CreateService;
import amateur.shaobig.tnc.service.UpdateService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class AlbumGenreListProxyService implements CreateService<List<AlbumGenre>, List<AlbumGenre>>, UpdateService<List<AlbumGenre>, List<AlbumGenre>> {

    private final AlbumGenreProxyService albumGenreProxyService;

    @Override
    public List<AlbumGenre> create(List<AlbumGenre> albumGenres) {
        return albumGenres.stream()
                .map(getAlbumGenreProxyService()::create)
                .toList();
    }

    @Override
    public List<AlbumGenre> update(List<AlbumGenre> albumGenres) {
        return albumGenres.stream()
                .map(getAlbumGenreProxyService()::update)
                .toList();
    }

}
