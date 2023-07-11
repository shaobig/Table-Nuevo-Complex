package amateur.shaobig.tnc.service.genre;

import amateur.shaobig.tnc.entity.AlbumGenre;
import amateur.shaobig.tnc.service.CreateService;
import amateur.shaobig.tnc.service.UpdateService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class AlbumGenreListService implements UpdateService<List<AlbumGenre>, List<AlbumGenre>> {

    private final AlbumGenreProxyService albumGenreProxyService;

    @Override
    public List<AlbumGenre> update(List<AlbumGenre> albumGenres) {
        return albumGenres.stream()
                .filter(albumGenre -> Objects.isNull(albumGenre.getId()))
                .map(getAlbumGenreProxyService()::create)
                .toList();
    }

}
