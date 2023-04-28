package amateur.shaobig.table_nuevo_complex.transformer;

import amateur.shaobig.table_nuevo_complex.dto.ReadAlbumDto;
import amateur.shaobig.table_nuevo_complex.dto.SongDto;
import amateur.shaobig.table_nuevo_complex.entity.Album;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class ReadAlbumDtoTransformer implements Transformer<Album, ReadAlbumDto> {

    private final SongDtoTransformer songDtoTransformer;

    @Override
    public ReadAlbumDto transform(Album album) {
        List<SongDto> songs = album.getSongs().stream()
                .map(getSongDtoTransformer()::transform)
                .collect(Collectors.toList());
        return new ReadAlbumDto(album.getId(), album.getArtist().getName(), album.getNumber(), album.getName(), album.getYear(), album.getMetadata().getTime(), songs);
    }

}
