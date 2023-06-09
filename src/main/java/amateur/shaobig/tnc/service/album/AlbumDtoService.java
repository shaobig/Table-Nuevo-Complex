package amateur.shaobig.tnc.service.album;

import amateur.shaobig.tnc.dto.album.ReadAlbumDto;
import amateur.shaobig.tnc.dto.album.ReadAllAlbumWithSongsDto;
import amateur.shaobig.tnc.service.ReadAllService;
import amateur.shaobig.tnc.service.ReadService;
import amateur.shaobig.tnc.transformer.album.ReadAlbumDtoTransformer;
import amateur.shaobig.tnc.transformer.album.ReadAllAlbumWithSongsDtoTransformer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class AlbumDtoService implements ReadService<ReadAlbumDto>, ReadAllService<ReadAllAlbumWithSongsDto> {

    private final ReadAlbumDtoTransformer readAlbumDtoTransformer;
    private final ReadAllAlbumWithSongsDtoTransformer readAllAlbumWithSongsDtoTransformer;
    private final AlbumProxyService albumProxyService;

    @Override
    public ReadAlbumDto read(Long id) {
        return getReadAlbumDtoTransformer().transform(getAlbumProxyService().read(id));
    }

    @Override
    public List<ReadAllAlbumWithSongsDto> readAll() {
        return getAlbumProxyService().readAll().stream()
                .map(getReadAllAlbumWithSongsDtoTransformer()::transform)
                .collect(Collectors.toList());
    }

}
