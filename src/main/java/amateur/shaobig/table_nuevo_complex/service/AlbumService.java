package amateur.shaobig.table_nuevo_complex.service;

import amateur.shaobig.table_nuevo_complex.dto.ReadAllAlbumDto;
import amateur.shaobig.table_nuevo_complex.entity.Album;
import amateur.shaobig.table_nuevo_complex.repository.AlbumRepository;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class AlbumService implements CreateService<Album, Album>, ReadService<Optional<Album>>, ReadAllService<ReadAllAlbumDto> {

    private final AlbumRepository albumRepository;

    @Override
    public Album create(Album album) {
        return getAlbumRepository().save(album);
    }

    @Override
    public Optional<Album> read(Long id) {
        return getAlbumRepository().findById(id);
    }

    @Override
    public List<ReadAllAlbumDto> readAll() {
        return getAlbumRepository().readAll();
    }

}
