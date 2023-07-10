package amateur.shaobig.tnc.controller;

import amateur.shaobig.tnc.dto.album.AlbumMetadataDto;
import amateur.shaobig.tnc.dto.album.AlbumStatisticsDto;
import amateur.shaobig.tnc.dto.album.ReadAlbumDto;
import amateur.shaobig.tnc.dto.album.ReadAllAlbumDto;
import amateur.shaobig.tnc.dto.album.UpdateAlbumDto;
import amateur.shaobig.tnc.dto.artist.ArtistDto;
import amateur.shaobig.tnc.dto.location.LocationDto;
import amateur.shaobig.tnc.entity.Album;
import amateur.shaobig.tnc.entity.AlbumMetadata;
import amateur.shaobig.tnc.entity.Artist;
import amateur.shaobig.tnc.entity.enums.AlbumType;
import amateur.shaobig.tnc.entity.enums.ArtistStatus;
import amateur.shaobig.tnc.service.album.AlbumDtoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlbumRestControllerTest {

    private AlbumDtoService albumDtoService;

    private AlbumRestController albumRestController;

    @BeforeEach
    void init() {
        this.albumDtoService = Mockito.mock(AlbumDtoService.class);

        this.albumRestController = new AlbumRestController(albumDtoService);
    }

    @Test
    void readCheckAlbumId() {
        Long sourceId = 1L;

        albumRestController.read(sourceId);

        Long expectedId = 1L;
        Mockito.verify(albumDtoService).read(expectedId);
    }

    @Test
    void read() {
        ReadAlbumDto sourceResponseBody = new ReadAlbumDto(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadataDto(LocalDateTime.of(0, 1, 1, 0, 0, 0), false), new ArtistDto(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new LocationDto(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME")), List.of(), List.of());
        Long sourceId = 1L;
        Mockito.when(albumDtoService.read(Mockito.anyLong())).thenReturn(sourceResponseBody);

        ResponseEntity<ReadAlbumDto> actual = albumRestController.read(sourceId);

        ReadAlbumDto expectedResponseBody = new ReadAlbumDto(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadataDto(LocalDateTime.of(0, 1, 1, 0, 0, 0), false), new ArtistDto(1L, "ARTIST_NAME", ArtistStatus.ACTIVE, new LocationDto(1L, "COUNTRY_NAME", "REGION_NAME", "LOCALITY_NAME")), List.of(), List.of());
        ResponseEntity<ReadAlbumDto> expected = ResponseEntity.status(HttpStatus.OK).body(expectedResponseBody);
        assertEquals(expected, actual);
    }

    @Test
    void readAll() {
        List<ReadAllAlbumDto> sourceResponseBody = List.of(new ReadAllAlbumDto(1L,  "ALBUM_NAME", AlbumType.LP, 0, 1L, "ARTIST_NAME", "COUNTRY_NAME", new AlbumStatisticsDto(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO)));
        Mockito.when(albumDtoService.readAll()).thenReturn(sourceResponseBody);

        ResponseEntity<List<ReadAllAlbumDto>> actual = albumRestController.readAll();

        List<ReadAllAlbumDto> expectedResponseBody = List.of(new ReadAllAlbumDto(1L,  "ALBUM_NAME", AlbumType.LP, 0, 1L, "ARTIST_NAME", "COUNTRY_NAME", new AlbumStatisticsDto(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO)));
        ResponseEntity<List<ReadAllAlbumDto>> expected = ResponseEntity.status(HttpStatus.OK).body(expectedResponseBody);
        assertEquals(expected, actual);
    }

    @Test
    void updateCheckAlbum() {
        Album sourceAlbum = new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of());

        albumRestController.update(sourceAlbum);

        Album expectedAlbum = new Album(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadata(), new Artist(), List.of(), List.of());
        Mockito.verify(albumDtoService).update(expectedAlbum);
    }

    @Test
    void update() {
        LocalDateTime sourceAlbumMetadataTime = LocalDateTime.now();
        UpdateAlbumDto sourceResponseBody = new UpdateAlbumDto(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadataDto(sourceAlbumMetadataTime, false), List.of(), List.of());
        Album sourceAlbum = new Album();
        Mockito.when(albumDtoService.update(Mockito.any())).thenReturn(sourceResponseBody);

        ResponseEntity<UpdateAlbumDto> actual = albumRestController.update(sourceAlbum);

        UpdateAlbumDto expectedResponseBody = new UpdateAlbumDto(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadataDto(sourceAlbumMetadataTime, false), List.of(), List.of());
        ResponseEntity<UpdateAlbumDto> expected = ResponseEntity.status(HttpStatus.OK).body(expectedResponseBody);
        assertEquals(expected, actual);
    }

}
