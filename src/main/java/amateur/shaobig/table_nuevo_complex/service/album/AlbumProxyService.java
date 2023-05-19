package amateur.shaobig.table_nuevo_complex.service.album;

import amateur.shaobig.table_nuevo_complex.dto.album.ReadAllAlbumDto;
import amateur.shaobig.table_nuevo_complex.entity.Album;
import amateur.shaobig.table_nuevo_complex.exception.types.EntityNotFoundException;
import amateur.shaobig.table_nuevo_complex.service.ReadAllService;
import amateur.shaobig.table_nuevo_complex.service.ReadService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class AlbumProxyService implements ReadService<Album>, ReadAllService<ReadAllAlbumDto> {

    private final AlbumService albumService;

    @Override
    public Album read(Long id) {
        return getAlbumService().read(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Can't find the album with the id = %d", id)));
    }

    @Override
    public List<ReadAllAlbumDto> readAll() {
        return getAlbumService().readAll();
    }

}
