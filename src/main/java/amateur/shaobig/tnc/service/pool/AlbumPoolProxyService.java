package amateur.shaobig.tnc.service.pool;

import amateur.shaobig.tnc.dto.album.ReadAllAlbumPoolDto;
import amateur.shaobig.tnc.entity.AlbumPool;
import amateur.shaobig.tnc.exception.types.EntityNotFoundException;
import amateur.shaobig.tnc.service.CreateService;
import amateur.shaobig.tnc.service.DeleteService;
import amateur.shaobig.tnc.service.ReadAllService;
import amateur.shaobig.tnc.service.album.AlbumProxyService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class AlbumPoolProxyService implements CreateService<AlbumPool, AlbumPool>, ReadAllService<ReadAllAlbumPoolDto>, DeleteService<AlbumPool> {

    private final AlbumPoolService albumPoolService;
    private final AlbumProxyService albumProxyService;

    @Override
    public AlbumPool create(AlbumPool albumPool) {
        albumPool.setAlbum(getAlbumProxyService().create(albumPool.getAlbum()));
        return getAlbumPoolService().create(albumPool);
    }

    @Override
    public List<ReadAllAlbumPoolDto> readAll() {
        return getAlbumPoolService().readAll();
    }

    @Override
    public AlbumPool delete(Long id) {
        return getAlbumPoolService().delete(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Can't delete the entity with the id = %d", id)));
    }

}
