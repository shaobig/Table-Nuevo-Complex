package amateur.shaobig.table_nuevo_complex.service;

import amateur.shaobig.table_nuevo_complex.dto.ReadAllAlbumPoolDto;
import amateur.shaobig.table_nuevo_complex.entity.AlbumPool;
import amateur.shaobig.table_nuevo_complex.repository.AlbumPoolRepository;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class AlbumPoolService implements CreateService<AlbumPool, AlbumPool>, ReadAllService<ReadAllAlbumPoolDto> {

    private final AlbumPoolRepository albumPoolRepository;

    @Override
    public AlbumPool create(AlbumPool albumPool) {
        return getAlbumPoolRepository().save(albumPool);
    }

    @Override
    public List<ReadAllAlbumPoolDto> readAll() {
        return getAlbumPoolRepository().readAll();
    }

}
