package amateur.shaobig.tnc.transformer.album;

import amateur.shaobig.tnc.dto.album.AlbumMetadataDto;
import amateur.shaobig.tnc.dto.album.ReadFullAlbumDto;
import amateur.shaobig.tnc.dto.artist.ArtistDto;
import amateur.shaobig.tnc.dto.artist.location.LocationDto;
import amateur.shaobig.tnc.dto.genre.GenreDto;
import amateur.shaobig.tnc.dto.song.SongDto;
import amateur.shaobig.tnc.dto.song.SongMetadataDto;
import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.entity.AlbumMetadata;
import amateur.shaobig.tnc.entity.Artist;
import amateur.shaobig.tnc.entity.Genre;
import amateur.shaobig.tnc.entity.Location;
import amateur.shaobig.tnc.entity.Song;
import amateur.shaobig.tnc.entity.SongMetadata;
import amateur.shaobig.tnc.entity.enums.AlbumType;
import amateur.shaobig.tnc.entity.enums.ArtistStatus;
import amateur.shaobig.tnc.entity.enums.SongType;
import amateur.shaobig.tnc.transformer.album.genre.GenreDtoTransformer;
import amateur.shaobig.tnc.transformer.album.metadata.AlbumMetadataDtoTransformer;
import amateur.shaobig.tnc.transformer.artist.ArtistDtoTransformer;
import amateur.shaobig.tnc.transformer.song.SongDtoTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReadFullAlbumDtoTransformerTest {

    private AlbumMetadataDtoTransformer albumMetadataDtoTransformer;
    private ArtistDtoTransformer artistDtoTransformer;
    private GenreDtoTransformer genreDtoTransformer;
    private SongDtoTransformer songDtoTransformer;

    private ReadFullAlbumDtoTransformer readFullAlbumDtoTransformer;

    @BeforeEach
    void init() {
        this.albumMetadataDtoTransformer = Mockito.mock(AlbumMetadataDtoTransformer.class);
        this.artistDtoTransformer = Mockito.mock(ArtistDtoTransformer.class);
        this.genreDtoTransformer = Mockito.mock(GenreDtoTransformer.class);
        this.songDtoTransformer = Mockito.mock(SongDtoTransformer.class);

        this.readFullAlbumDtoTransformer = new ReadFullAlbumDtoTransformer(albumMetadataDtoTransformer, artistDtoTransformer, genreDtoTransformer, songDtoTransformer);
    }

    @Test
    void transformCheckAlbumMetadataDtoTransformer() {
        Album sourceAlbum = new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(1L, LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC), false, new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of())), new Artist(), List.of(), List.of());

        readFullAlbumDtoTransformer.transform(sourceAlbum);

        AlbumMetadata expectedAlbumMetadata = new AlbumMetadata(1L, LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC), false, new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()));
        Mockito.verify(albumMetadataDtoTransformer).transform(expectedAlbumMetadata);
    }

    @Test
    void transformCheckArtistDtoTransformer() {
        Album sourceAlbum = new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME"), List.of()), List.of(), List.of());

        readFullAlbumDtoTransformer.transform(sourceAlbum);

        Artist expectedArtist = new Artist(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new Location(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME"), List.of());
        Mockito.verify(artistDtoTransformer).transform(expectedArtist);
    }

    @Test
    void transformCheckGenreDtoTransformer() {
        Album sourceAlbum = new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(new Genre(1L, "GENRE_NAME_1", false, new Album()), new Genre(1L, "GENRE_NAME_2", false, new Album())), List.of());

        readFullAlbumDtoTransformer.transform(sourceAlbum);
        ArgumentCaptor<Genre> genreArgumentCaptor = ArgumentCaptor.forClass(Genre.class);
        Mockito.verify(genreDtoTransformer, Mockito.atLeastOnce()).transform(genreArgumentCaptor.capture());
        List<Genre> actual = genreArgumentCaptor.getAllValues();

        List<Genre> expected = List.of(new Genre(1L, "GENRE_NAME_1", false, new Album()), new Genre(1L, "GENRE_NAME_2", false, new Album()));
        assertEquals(expected, actual);
    }

    @Test
    void transformCheckSongDtoTransformer() {
        Album sourceAlbum = new Album(1L, 0, "", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of(new Song(1L, 0, "SONG_NAME_1", new SongMetadata(), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of())), new Song(1L, 0, "SONG_NAME_1", new SongMetadata(), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of()))));

        readFullAlbumDtoTransformer.transform(sourceAlbum);
        ArgumentCaptor<Song> songArgumentCaptor = ArgumentCaptor.forClass(Song.class);
        Mockito.verify(songDtoTransformer, Mockito.atLeastOnce()).transform(songArgumentCaptor.capture());
        List<Song> actual = songArgumentCaptor.getAllValues();

        List<Song> expected = List.of(new Song(1L, 0, "SONG_NAME_1", new SongMetadata(), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of())), new Song(1L, 0, "SONG_NAME_1", new SongMetadata(), new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of())));
        assertEquals(expected, actual);
    }

    @Test
    void transform() {
        AlbumMetadataDto sourceAlbumMetadataDto = new AlbumMetadataDto(LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC), false);
        ArtistDto sourceArtistDto = new ArtistDto(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new LocationDto(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME"));
        GenreDto sourceFirstGenreDto = new GenreDto(1L, "GENRE_NAME_1", false);
        GenreDto sourceSecondGenreDto = new GenreDto(1L, "GENRE_NAME_2", false);
        SongDto sourceFirstSongDto = new SongDto(1L, 0, "SONG_NAME_1", new SongMetadataDto(SongType.DEFAULT, 1));
        SongDto sourceSecondSongDto = new SongDto(1L, 0, "SONG_NAME_2", new SongMetadataDto(SongType.DEFAULT, 1));
        Album sourceAlbum = new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(new Genre(), new Genre()), List.of(new Song(), new Song()));
        Mockito.when(albumMetadataDtoTransformer.transform(Mockito.any())).thenReturn(sourceAlbumMetadataDto);
        Mockito.when(artistDtoTransformer.transform(Mockito.any())).thenReturn(sourceArtistDto);
        Mockito.when(genreDtoTransformer.transform(Mockito.any())).thenReturn(sourceFirstGenreDto).thenReturn(sourceSecondGenreDto);
        Mockito.when(songDtoTransformer.transform(Mockito.any())).thenReturn(sourceFirstSongDto).thenReturn(sourceSecondSongDto);

        ReadFullAlbumDto actual = readFullAlbumDtoTransformer.transform(sourceAlbum);

        ReadFullAlbumDto expected = new ReadFullAlbumDto(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadataDto(LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC), false), new ArtistDto(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new LocationDto(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME")), List.of(new GenreDto(1L, "GENRE_NAME_1", false), new GenreDto(1L, "GENRE_NAME_2", false)), List.of(new SongDto(1L, 0, "SONG_NAME_1", new SongMetadataDto(SongType.DEFAULT, 1)), new SongDto(1L, 0, "SONG_NAME_2", new SongMetadataDto(SongType.DEFAULT, 1))));
        assertEquals(expected, actual);
    }

}
