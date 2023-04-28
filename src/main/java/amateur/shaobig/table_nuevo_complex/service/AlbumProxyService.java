package amateur.shaobig.table_nuevo_complex.service;

import amateur.shaobig.table_nuevo_complex.dto.ReadAllAlbumDto;
import amateur.shaobig.table_nuevo_complex.entity.Album;
import amateur.shaobig.table_nuevo_complex.entity.AlbumPool;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class AlbumProxyService implements CreateService<Album, Album>, ReadService<Optional<Album>>, ReadAllService<ReadAllAlbumDto> {

    private final AlbumPoolService albumPoolService;
    private final AlbumService albumService;

    @Override
    public Album create(Album album) {
        return getAlbumPoolService().create(new AlbumPool(album)).getAlbum();
    }

    @Override
    public Optional<Album> read(Long id) {
        return getAlbumService().read(id);
    }

    @Override
    public List<ReadAllAlbumDto> readAll() {
        return getAlbumService().readAll();
    }

}
