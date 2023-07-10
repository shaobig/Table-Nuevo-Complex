package amateur.shaobig.tnc.transformer.album;

import amateur.shaobig.tnc.dto.album.AlbumMetadataDto;
import amateur.shaobig.tnc.dto.album.ReadAlbumDto;
import amateur.shaobig.tnc.dto.artist.ArtistDto;
import amateur.shaobig.tnc.dto.genre.GenreDto;
import amateur.shaobig.tnc.dto.song.SongDto;
import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.transformer.Transformer;
import amateur.shaobig.tnc.transformer.album.genre.GenreDtoTransformer;
import amateur.shaobig.tnc.transformer.album.metadata.AlbumMetadataDtoTransformer;
import amateur.shaobig.tnc.transformer.artist.ArtistDtoTransformer;
import amateur.shaobig.tnc.transformer.song.SongDtoTransformer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Getter(value = AccessLevel.PACKAGE)
public class ReadAlbumDtoTransformer implements Transformer<Album, ReadAlbumDto> {

    private final AlbumMetadataDtoTransformer albumMetadataDtoTransformer;
    private final ArtistDtoTransformer artistDtoTransformer;
    private final GenreDtoTransformer genreDtoTransformer;
    private final SongDtoTransformer songDtoTransformer;

    @Override
    public ReadAlbumDto transform(Album album) {
        AlbumMetadataDto albumMetadata = getAlbumMetadataDtoTransformer().transform(album.getMetadata());
        ArtistDto artist = getArtistDtoTransformer().transform(album.getArtist());
        List<GenreDto> genres = album.getGenres().stream()
                .map(getGenreDtoTransformer()::transform)
                .toList();
        List<SongDto> songs = album.getSongs().stream()
                .map(getSongDtoTransformer()::transform)
                .toList();
        return new ReadAlbumDto(album.getId(), album.getNumber(), album.getName(), album.getYear(), album.getType(), albumMetadata, artist, genres, songs);
    }

}
