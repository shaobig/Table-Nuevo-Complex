package amateur.shaobig.table_nuevo_complex.service.pool;

import amateur.shaobig.table_nuevo_complex.dto.album.ReadAllAlbumPoolDto;
import amateur.shaobig.table_nuevo_complex.entity.Album;
import amateur.shaobig.table_nuevo_complex.entity.AlbumPool;
import amateur.shaobig.table_nuevo_complex.repository.AlbumPoolRepository;
import amateur.shaobig.table_nuevo_complex.service.CreateService;
import amateur.shaobig.table_nuevo_complex.service.DeleteService;
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
public class AlbumPoolService implements CreateService<AlbumPool, AlbumPool>, ReadAllService<ReadAllAlbumPoolDto>, DeleteService<AlbumPool> {

    private final AlbumPoolRepository albumPoolRepository;

    @Override
    public AlbumPool create(AlbumPool albumPool) {
        return getAlbumPoolRepository().save(albumPool);
    }

    @Override
    public List<ReadAllAlbumPoolDto> readAll() {
        return getAlbumPoolRepository().readAll();
    }

    @Override
    public AlbumPool delete(Long id) {
        AlbumPool albumPool = getAlbumPoolRepository().getReferenceById(id);
        getAlbumPoolRepository().deleteById(id);
        return albumPool;
    }

}
