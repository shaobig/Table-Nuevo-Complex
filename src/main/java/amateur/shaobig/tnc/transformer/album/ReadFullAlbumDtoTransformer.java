package amateur.shaobig.tnc.transformer.album;

import amateur.shaobig.tnc.dto.album.AlbumMetadataDto;
import amateur.shaobig.tnc.dto.album.ReadFullAlbumDto;
import amateur.shaobig.tnc.dto.artist.ArtistDto;
import amateur.shaobig.tnc.dto.genre.GenreDto;
import amateur.shaobig.tnc.dto.song.SongDto;
import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.transformer.Transformer;
import amateur.shaobig.tnc.transformer.artist.ArtistDtoTransformer;
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
public class ReadFullAlbumDtoTransformer implements Transformer<Album, ReadFullAlbumDto> {

    private final ArtistDtoTransformer artistDtoTransformer;
    private final AlbumMetadataDtoTransformer albumMetadataDtoTransformer;
    private final GenreDtoTransformer genreDtoTransformer;
    private final SongDtoTransformer songDtoTransformer;

    @Override
    public ReadFullAlbumDto transform(Album album) {
        ArtistDto artist = getArtistDtoTransformer().transform(album.getArtist());
        AlbumMetadataDto albumMetadata = getAlbumMetadataDtoTransformer().transform(album.getMetadata());
        List<GenreDto> genres = album.getGenres().stream()
                .map(getGenreDtoTransformer()::transform)
                .toList();
        List<SongDto> songs = album.getSongs().stream()
                .map(getSongDtoTransformer()::transform)
                .collect(Collectors.toList());
        return new ReadFullAlbumDto(album.getId(), album.getNumber(), album.getName(), album.getYear(), album.getType(), albumMetadata, artist, genres, songs);
    }

}
