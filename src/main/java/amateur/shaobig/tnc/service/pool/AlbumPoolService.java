package amateur.shaobig.tnc.service.pool;

import amateur.shaobig.tnc.dto.album.ReadAllAlbumPoolDto;
import amateur.shaobig.tnc.entity.AlbumPool;
import amateur.shaobig.tnc.repository.AlbumPoolRepository;
import amateur.shaobig.tnc.service.CreateService;
import amateur.shaobig.tnc.service.DeleteService;
import amateur.shaobig.tnc.service.ReadAllService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class AlbumPoolService implements CreateService<AlbumPool, AlbumPool>, ReadAllService<ReadAllAlbumPoolDto>, DeleteService<Optional<AlbumPool>> {

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
    public Optional<AlbumPool> delete(Long id) {
        Optional<AlbumPool> albumPool = getAlbumPoolRepository().findById(id);
        getAlbumPoolRepository().deleteById(id);
        return albumPool;
    }

}
