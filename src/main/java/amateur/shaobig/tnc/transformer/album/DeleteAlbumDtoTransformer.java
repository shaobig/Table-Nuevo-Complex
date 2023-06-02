package amateur.shaobig.tnc.transformer.album;

import amateur.shaobig.tnc.dto.album.AlbumMetadataDto;
import amateur.shaobig.tnc.dto.album.DeleteAlbumDto;
import amateur.shaobig.tnc.dto.album.GenreDto;
import amateur.shaobig.tnc.dto.song.SongDto;
import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.transformer.Transformer;
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
public class DeleteAlbumDtoTransformer implements Transformer<Album, DeleteAlbumDto> {

    private final AlbumMetadataDtoTransformer albumMetadataDtoTransformer;
    private final GenreDtoTransformer genreDtoTransformer;
    private final SongDtoTransformer songDtoTransformer;

    @Override
    public DeleteAlbumDto transform(Album album) {
        AlbumMetadataDto albumMetadata = getAlbumMetadataDtoTransformer().transform(album.getMetadata());
        List<GenreDto> genres = album.getGenres().stream()
                .map(genre -> getGenreDtoTransformer().transform(genre))
                .collect(Collectors.toList());
        List<SongDto> songs = album.getSongs().stream()
                .map(song -> getSongDtoTransformer().transform(song))
                .collect(Collectors.toList());
        return new DeleteAlbumDto(album.getId(), album.getNumber(), album.getName(), album.getYear(), album.getType(), albumMetadata, genres, songs);
    }

}