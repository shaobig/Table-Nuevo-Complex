package amateur.shaobig.tnc.transformer.album;

import amateur.shaobig.tnc.dto.album.AlbumMetadataDto;
import amateur.shaobig.tnc.dto.album.CreateAlbumDto;
import amateur.shaobig.tnc.dto.genre.AlbumGenreDto;
import amateur.shaobig.tnc.dto.song.SongDto;
import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.transformer.Transformer;
import amateur.shaobig.tnc.transformer.album.genre.AlbumGenreDtoTransformer;
import amateur.shaobig.tnc.transformer.album.metadata.AlbumMetadataDtoTransformer;
import amateur.shaobig.tnc.transformer.song.SongDtoTransformer;
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
    private final AlbumGenreDtoTransformer albumGenreDtoTransformer;
    private final SongDtoTransformer songDtoTransformer;

    @Override
    public CreateAlbumDto transform(Album album) {
        AlbumMetadataDto albumMetadata = getAlbumMetadataDtoTransformer().transform(album.getMetadata());
        List<AlbumGenreDto> genres = album.getGenres().stream()
                .map(genre -> getAlbumGenreDtoTransformer().transform(genre))
                .collect(Collectors.toList());
        List<SongDto> songs = album.getSongs().stream()
                .map(song -> getSongDtoTransformer().transform(song))
                .collect(Collectors.toList());
        return new CreateAlbumDto(album.getId(), album.getNumber(), album.getName(), album.getYear(), album.getType(), albumMetadata, genres, songs);
    }

}
