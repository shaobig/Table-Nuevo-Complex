package amateur.shaobig.table_nuevo_complex.transformer;

import amateur.shaobig.table_nuevo_complex.dto.AlbumMetadataDto;
import amateur.shaobig.table_nuevo_complex.dto.CreateAlbumDto;
import amateur.shaobig.table_nuevo_complex.dto.GenreDto;
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
public class CreateAlbumDtoTransformer implements Transformer<Album, CreateAlbumDto> {

    private final AlbumMetadataDtoTransformer albumMetadataDtoTransformer;
    private final GenreDtoTransformer genreDtoTransformer;
    private final SongDtoTransformer songDtoTransformer;

    @Override
    public CreateAlbumDto transform(Album album) {
        AlbumMetadataDto albumMetadata = getAlbumMetadataDtoTransformer().transform(album.getMetadata());
        List<GenreDto> genres = album.getGenres().stream()
                .map(genre -> getGenreDtoTransformer().transform(genre))
                .collect(Collectors.toList());
        List<SongDto> songs = album.getSongs().stream()
                .map(song -> getSongDtoTransformer().transform(song))
                .collect(Collectors.toList());
        return new CreateAlbumDto(album.getId(), album.getNumber(), album.getName(), album.getYear(), album.getType(), albumMetadata, genres, songs);
    }

}
