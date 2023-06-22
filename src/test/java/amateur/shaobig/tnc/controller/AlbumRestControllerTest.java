package amateur.shaobig.tnc.controller;

import amateur.shaobig.tnc.dto.album.AlbumMetadataDto;
import amateur.shaobig.tnc.dto.album.AlbumStatisticsDto;
import amateur.shaobig.tnc.dto.album.ReadAlbumDto;
import amateur.shaobig.tnc.dto.album.ReadAllAlbumWithSongsDto;
import amateur.shaobig.tnc.dto.album.ReadFullAlbumDto;
import amateur.shaobig.tnc.dto.album.UpdateAlbumDto;
import amateur.shaobig.tnc.dto.artist.ArtistDto;
import amateur.shaobig.tnc.dto.artist.location.LocationDto;
import amateur.shaobig.tnc.entity.Album;
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
        LocalDateTime sourceAlbumMetadataTime = LocalDateTime.now();
        ReadAlbumDto sourceResponseBody = new ReadAlbumDto(1L, "", 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadataDto(sourceAlbumMetadataTime, false), List.of());
        Long sourceId = 1L;
        Mockito.when(albumDtoService.read(Mockito.anyLong())).thenReturn(sourceResponseBody);

        ResponseEntity<ReadAlbumDto> actual = albumRestController.read(sourceId);

        ReadAlbumDto expectedResponseBody = new ReadAlbumDto(1L, "", 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadataDto(sourceAlbumMetadataTime, false), List.of());
        ResponseEntity<ReadAlbumDto> expected = ResponseEntity.status(HttpStatus.OK).body(expectedResponseBody);
        assertEquals(expected, actual);
    }

    @Test
    void readFullCheckAlbumId() {
        Long sourceId = 1L;

        albumRestController.readFull(sourceId);

        Long expectedId = 1L;
        Mockito.verify(albumDtoService).readFull(expectedId);
    }

    @Test
    void readFull() {
        LocalDateTime sourceAlbumMetadataTime = LocalDateTime.now();
        ReadFullAlbumDto sourceResponseBody = new ReadFullAlbumDto(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadataDto(sourceAlbumMetadataTime, false), new ArtistDto(1L, "", ArtistStatus.ACTIVE, new LocationDto(1L, "", "", "")), List.of(), List.of());
        Long sourceId = 1L;
        Mockito.when(albumDtoService.readFull(Mockito.anyLong())).thenReturn(sourceResponseBody);

        ResponseEntity<ReadFullAlbumDto> actual = albumRestController.readFull(sourceId);

        ReadFullAlbumDto expectedResponseBody = new ReadFullAlbumDto(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadataDto(sourceAlbumMetadataTime, false), new ArtistDto(1L, "", ArtistStatus.ACTIVE, new LocationDto(1L, "", "", "")), List.of(), List.of());
        ResponseEntity<ReadFullAlbumDto> expected = ResponseEntity.status(HttpStatus.OK).body(expectedResponseBody);
        assertEquals(expected, actual);
    }

    @Test
    void readAll() {
        List<ReadAllAlbumWithSongsDto> sourceResponseBody = List.of(new ReadAllAlbumWithSongsDto(1L, "", "", "ALBUM_NAME",  AlbumType.LP, 0, new AlbumStatisticsDto(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO)));
        Mockito.when(albumDtoService.readAll()).thenReturn(sourceResponseBody);

        ResponseEntity<List<ReadAllAlbumWithSongsDto>> actual = albumRestController.readAll();

        List<ReadAllAlbumWithSongsDto> expectedResponseBody = List.of(new ReadAllAlbumWithSongsDto(1L, "", "", "ALBUM_NAME",  AlbumType.LP, 0, new AlbumStatisticsDto(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO)));
        ResponseEntity<List<ReadAllAlbumWithSongsDto>> expected = ResponseEntity.status(HttpStatus.OK).body(expectedResponseBody);
        assertEquals(expected, actual);
    }

    @Test
    void updateCheckAlbum() {
        Album sourceAlbum = new Album("ALBUM_NAME", 0, AlbumType.LP);

        albumRestController.update(sourceAlbum);

        Album expectedAlbum = new Album("ALBUM_NAME", 0, AlbumType.LP);
        Mockito.verify(albumDtoService).update(expectedAlbum);
    }

    @Test
    void update() {
        LocalDateTime sourceAlbumMetadataTime = LocalDateTime.now();
        UpdateAlbumDto sourceResponseBody = new UpdateAlbumDto(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadataDto(sourceAlbumMetadataTime, false), List.of(), List.of());
        Album sourceAlbum = new Album("ALBUM_NAME", 0, AlbumType.LP);
        Mockito.when(albumDtoService.update(Mockito.any())).thenReturn(sourceResponseBody);

        ResponseEntity<UpdateAlbumDto> actual = albumRestController.update(sourceAlbum);

        UpdateAlbumDto expectedResponseBody = new UpdateAlbumDto(1L, 0, "ALBUM_NAME", 0, AlbumType.LP, new AlbumMetadataDto(sourceAlbumMetadataTime, false), List.of(), List.of());
        ResponseEntity<UpdateAlbumDto> expected = ResponseEntity.status(HttpStatus.OK).body(expectedResponseBody);
        assertEquals(expected, actual);
    }

}
